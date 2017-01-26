package com.shlaitao.mybatis.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: DBConstants
 * @Description: TODO
 * @author
 * @date 2014-12-2 下午12:50:10
 * 
 */

public class DBConstants {
	public final static Map<String, String> DB_COLUMN_NAME;
	static {
		DB_COLUMN_NAME = new HashMap<String, String>();
		// DB_COLUMN_NAME.put("userId", "USER_ID");
		// DB_COLUMN_NAME.put("loginName", "LOGIN_NAME");
		// DB_COLUMN_NAME.put("userDisplayName", "USER_DISPLAY_NAME");
		// DB_COLUMN_NAME.put("orgName", "ORG_NAME");
		// DB_COLUMN_NAME.put("status", "STATUS");
	}
}
