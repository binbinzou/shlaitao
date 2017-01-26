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
public class PageHelperMysql extends PageHelper {


	/**
	 * 修改原SQL为分页SQL
	 * 
	 * @param sql
	 * @param page
	 * @return
	 */
	public String buildPageSql(String sql, Page page) {
		StringBuilder pageSql = new StringBuilder(200);
		pageSql.append("select * from ( ");

		if (!StringUtils.isEmpty(page.getSidx()) && !StringUtils.isBlank(page.getSidx().trim()) && !StringUtils.isEmpty(page.getSord())
				&& !StringUtils.isBlank(page.getSord().trim())) {
			pageSql.append("select * from ( ");
			pageSql.append(sql);
			pageSql.append(") temp order by ");
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
		pageSql.append(" ) temp1");
		if (page.getEndRow() > 0)
		{
			pageSql.append(" limit ").append(page.getStartRow()).append(",").append(page.getPageSize());
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
		String countSql = "select count(0) from (" + sql + ") as temp";
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
	
}
