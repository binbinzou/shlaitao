package com.shlaitao.mybatis.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface Dao {

	List query(String name, Object parameters);

	List query(String name, Object parameters, int offset, int limit);

	Object query(String name, Object parameters, int offset, int limit, boolean isCount);

	List query(String name, Object parameters, int offset, int limit, String sidx, String sord);

	Object query(String name, Object parameters, int offset, int limit, boolean isCount, String sidx, String sord);

	int insert(String name, Object parameters);

	int update(String name, Object parameters);

	int delete(String name, Object parameters);

	void saveFile(byte[] byt, String keyName);

	byte[] readFile(String keyName);

	int[] insertBatch(String name, Object parameters);

	int[] updateBatch(String name, Object parameters);
	
	int[] deleteBatch(String name, Object parameters);
}
