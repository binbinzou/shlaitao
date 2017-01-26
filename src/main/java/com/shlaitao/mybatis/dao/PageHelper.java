package com.shlaitao.mybatis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;


@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }),
		@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = { Statement.class }) })
public class PageHelper implements Interceptor {

	public static final ThreadLocal<Page> localPage = new ThreadLocal<Page>();

	/**
	 * 开始分页
	 * 
	 * @param pageNum
	 * @param pageSize
	 */
	public static void startPage(int pageNum, int pageSize, boolean isCount, String sidx, String sord) {
		localPage.set(new Page(pageNum, pageSize, isCount, sidx, sord));
	}

	/**
	 * 结束分页并返回结果，该方法必须被调用，否则localPage会一直保存下去，直到下一次startPage
	 * 
	 * @return
	 */
	public static Page endPage() {
		Page page = localPage.get();
		localPage.remove();
		return page;
	}

	public Object intercept(Invocation invocation) throws Throwable {
		if (localPage.get() == null) {
			return invocation.proceed();
		}
		if (invocation.getTarget() instanceof StatementHandler) {
			StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
			MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
			// 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环
			// 可以分离出最原始的的目标类)
			while (metaStatementHandler.hasGetter("h")) {
				Object object = metaStatementHandler.getValue("h");
				metaStatementHandler = SystemMetaObject.forObject(object);
			}
			// 分离最后一个代理对象的目标类
			while (metaStatementHandler.hasGetter("target")) {
				Object object = metaStatementHandler.getValue("target");
				metaStatementHandler = SystemMetaObject.forObject(object);
			}
			MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
			// 分页信息if (localPage.get() != null) {
			Page page = localPage.get();
			BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
			// 分页参数作为参数对象parameterObject的一个属性
			String sql = boundSql.getSql();
			// 重写sql
			String pageSql = buildPageSql(sql, page);
			// 重写分页sql
			metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
			Connection connection = (Connection) invocation.getArgs()[0];
			// 重设分页参数里的总页数等
			if (page.isCount())
				setPageParameter(sql, connection, mappedStatement, boundSql, page);
			// 将执行权交给下一个拦截器
			return invocation.proceed();
		} else if (invocation.getTarget() instanceof ResultSetHandler) {
			Object result = invocation.proceed();
			Page page = localPage.get();
			page.setResult((List) result);
			return result;
		}
		return null;
	}

	/**
	 * 只拦截这两种类型的 <br>
	 * StatementHandler <br>
	 * ResultSetHandler
	 * 
	 * @param target
	 * @return
	 */
	public Object plugin(Object target) {
		if (target instanceof StatementHandler || target instanceof ResultSetHandler) {
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}

	public void setProperties(Properties properties) {

	}

	/**
	 * 修改原SQL为分页SQL
	 * 
	 * @param sql
	 * @param page
	 * @return
	 */
	public String buildPageSql(String sql, Page page) {
		StringBuilder pageSql = new StringBuilder(200);
		pageSql.append("select * from ( select temp.*, rownum row_id from ( ");

		if (!StringUtils.isEmpty(page.getSidx()) && !StringUtils.isBlank(page.getSidx().trim()) && !StringUtils.isEmpty(page.getSord())
				&& !StringUtils.isBlank(page.getSord().trim())) {
			pageSql.append("select * from ( ");
			pageSql.append(sql);
			pageSql.append(") order by ");
			if (DBConstants.DB_COLUMN_NAME.get(page.getSidx().trim()) != null) {
				pageSql.append(DBConstants.DB_COLUMN_NAME.get(page.getSidx().trim()));
			} else {
				String sidx = page.getSidx().trim();
				String newSidx = "";
				for (int i = 0; i <= sidx.length() - 1; i++) {
					if (Character.isLowerCase(sidx.charAt(i))) {
						newSidx = newSidx + Character.toUpperCase(sidx.charAt(i));
					} else if (Character.isUpperCase(sidx.charAt(i))) {
						newSidx = newSidx + "_" + sidx.charAt(i);
					}
				}
				pageSql.append(newSidx);
			}
			pageSql.append(" " + page.getSord().trim());
		} else {
			pageSql.append(sql);
		}
//		pageSql.append(" ) temp where rownum <= ").append(page.getEndRow());
		pageSql.append(" ) temp");
		if (page.getEndRow() > 0)
		{
			pageSql.append(") where row_id > ").append(page.getStartRow());
			pageSql.append("and row_id <= ").append(page.getEndRow());
		}
		else
		{
			pageSql.append(")");
		}
		return pageSql.toString();
	}

	/**
	 * 获取总记录数
	 * 
	 * @param sql
	 * @param connection
	 * @param mappedStatement
	 * @param boundSql
	 * @param page
	 */
	public void setPageParameter(String sql, Connection connection, MappedStatement mappedStatement, BoundSql boundSql, Page page) {
		// 记录总记录数
		String countSql = "select count(0) from (" + sql + ")";
		PreparedStatement countStmt = null;
		ResultSet rs = null;
		try {
			countStmt = connection.prepareStatement(countSql);
			BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql, boundSql.getParameterMappings(),
					boundSql.getParameterObject());
			setParameters(countStmt, mappedStatement, countBS, boundSql.getParameterObject());
			rs = countStmt.executeQuery();
			int totalCount = 0;
			if (rs.next()) {
				totalCount = rs.getInt(1);
			}
			page.setTotal(totalCount);
			int totalPage = totalCount / page.getPageSize() + ((totalCount % page.getPageSize() == 0) ? 0 : 1);
			page.setPages(totalPage);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				countStmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 代入参数值
	 * 
	 * @param ps
	 * @param mappedStatement
	 * @param boundSql
	 * @param parameterObject
	 * @throws SQLException
	 */
	public void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject) throws SQLException {
		ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
		parameterHandler.setParameters(ps);
	}

	public static class Page<E> {
		private int pageNum; // 哪一页
		private int pageSize; // 每一页多少天记录
		private int startRow; // 从多少条
		private int endRow; // 到多少条
		private long total; // 记录总数
		private int pages; // 页总数
		private List<E> result; // 记录结果
		private boolean isCount;
		public String sidx;// 请求排序列
		public String sord;// 请求排序标识

		public Page(int pageNum, int pageSize, boolean iscount, String sidx, String sord) {
			this.pageNum = pageNum;
			this.pageSize = pageSize;
			this.startRow = pageNum > 0 ? (pageNum - 1) * pageSize : 0;
			this.endRow = pageNum * pageSize;
			this.isCount = iscount;
			this.sidx = sidx;
			this.sord = sord;
		}

		public List<E> getResult() {
			return result;
		}

		public void setResult(List<E> result) {
			this.result = result;
		}

		public int getPages() {
			return pages;
		}

		public void setPages(int pages) {
			this.pages = pages;
		}

		public int getEndRow() {
			return endRow;
		}

		public void setEndRow(int endRow) {
			this.endRow = endRow;
		}

		public int getPageNum() {
			return pageNum;
		}

		public void setPageNum(int pageNum) {
			this.pageNum = pageNum;
		}

		public int getPageSize() {
			return pageSize;
		}

		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}

		public int getStartRow() {
			return startRow;
		}

		public void setStartRow(int startRow) {
			this.startRow = startRow;
		}

		public long getTotal() {
			return total;
		}

		public void setTotal(long total) {
			this.total = total;
		}

		public boolean isCount() {
			return isCount;
		}

		public void setCount(boolean isCount) {
			this.isCount = isCount;
		}

		public String getSidx() {
			return sidx;
		}

		public void setSidx(String sidx) {
			this.sidx = sidx;
		}

		public String getSord() {
			return sord;
		}

		public void setSord(String sord) {
			this.sord = sord;
		}

		@Override
		public String toString() {
			return "Page{" + "pageNum=" + pageNum + ", pageSize=" + pageSize + ", startRow=" + startRow + ", endRow=" + endRow + ", total=" + total
					+ ", pages=" + pages + ", sidx=" + sidx + ", sord=" + sord + '}';
		}
	}
}