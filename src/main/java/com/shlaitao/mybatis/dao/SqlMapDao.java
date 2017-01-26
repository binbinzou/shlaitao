package com.shlaitao.mybatis.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.shlaitao.mybatis.dao.PageHelper.Page;





@Repository
public class SqlMapDao implements Dao{

	@Resource
	private SqlSession sqlSession;
	
	public List query(String name, Object parameters) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(name, parameters);
	}

	public List query(String name, Object parameters, int offset, int limit, String sidx, String sord) {
		// TODO Auto-generated method stub
		PageHelper.startPage(offset, limit, false, sidx, sord);
		query(name, parameters);
		Page page = PageHelper.endPage();

		return page.getResult();
	}

	public Object query(String name, Object parameters, int offset, int limit, boolean isCount, String sidx, String sord) {
		PageHelper.startPage(offset, limit, isCount, sidx, sord);
		query(name, parameters);
		Page page = PageHelper.endPage();

		return page;
	}

	public int insert(String name, Object parameters) {

		return sqlSession.insert(name, parameters);
	}

	public int update(String name, Object parameters) {
		// TODO Auto-generated method stub
		return sqlSession.update(name, parameters);
	}

	public int delete(String name, Object parameters) {
		// TODO Auto-generated method stub
		return sqlSession.delete(name, parameters);

	}

	public void saveFile(byte[] byt, String keyName) {
		// TODO Auto-generated method stub

	}

	public byte[] readFile(String keyName) {
		// TODO Auto-generated method stub
		return null;
	}

	public List query(String name, Object parameters, int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object query(String name, Object parameters, int offset, int limit, boolean isCount) {
		// TODO Auto-generated method stub
		return null;
	}

	public int[] insertBatch(String name, Object parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	public int[] updateBatch(String name, Object parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	public int[] deleteBatch(String name, Object parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
