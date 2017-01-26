package com.shlaitao.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.shlaitao.base.util.EncoderUtils;
import com.shlaitao.mybatis.dao.Dao;
import com.shlaitao.user.domain.TUser;

@Service("userService")
@Transactional
public class UserService {

	@Autowired
	@Qualifier("sqlMapDao")
	protected Dao dao;
	
	public boolean updatePassword(TUser user){
		List list = dao.query("user.queryByUserId", user);
		if (list != null && list.size() > 0) {
			TUser oldUser = (TUser) list.get(0);
			if (EncoderUtils.CheckEqualWithMd5(user.getPassword(), oldUser.getPassword())) {
				user.setNewPassword(EncoderUtils.md5Encode(user.getNewPassword()));
				int flag = dao.update("user.updatePassword", user);
				return true;
			} else {
				return false;//当前密码输入错误
			}
		} else {
			return false;
		}
	}
	
	public TUser login(TUser user){
		List<TUser> users = dao.query("user.queryByUserName", user);
		if(users!=null&&users.size()>0){
			String password = users.get(0).getPassword();
			String status = users.get(0).getStatus();
			if (EncoderUtils.CheckEqualWithMd5(user.getPassword(), password)
					&& status.equalsIgnoreCase("1")) {
				return users.get(0);
			}else{
				return null;
			}
		}
		return null;
	}
	
	
}
