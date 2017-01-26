package com.shlaitao.base.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class AdminAction extends BaseAction {

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		this.renderAction = "common/login.jsp";
		return "render";
	}
	
}
