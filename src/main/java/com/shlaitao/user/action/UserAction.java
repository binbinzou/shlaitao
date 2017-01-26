package com.shlaitao.user.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shlaitao.base.action.BaseAction;
import com.shlaitao.user.domain.TUser;
import com.shlaitao.user.service.UserService;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction{

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	private TUser user = new TUser();
	public TUser getUser() {
		return user;
	}
	public void setUser(TUser user) {
		this.user = user;
	}
	
	public String login(){
		TUser userTmp = userService.login(user);
		if(userTmp!=null){
			result.put("status", "success");
			result.put("message", "登录成功");
			ActionContext.getContext().getSession().put("userId", userTmp.getId());
		}else{
			result.put("status", "fail");
			result.put("message", "登录名密码出错");
		}
		return "success";
	}
	
	public String updatePassword(){
		int userId = (Integer)ActionContext.getContext().getSession().get("userId"); 
		user.setId(userId);
		boolean flag = userService.updatePassword(user);
		if(flag){
			result.put("status", "success");
			result.put("message", "密码修改成功");
		}else{
			result.put("status", "fail");
			result.put("message", "原密码出错");//343b1c4a3ea721b2d640fc8700db0f36
		}
		return "success";
	}
	
	public String logout(){
		ActionContext.getContext().getSession().clear();
		this.renderAction = "login.jsp"; 
		return "render";
	} 
	
	
}
