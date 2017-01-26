package com.shlaitao.base.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.StrutsStatics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.fastjson.util.Base64;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthInterceptor extends AbstractInterceptor {
	private static final String[] DEFAULT_LOGIN_URL = {
			//平台不需要权限请求
			"admin",
			"user", "imgUpload", "img", "news", "techdata", "product",
			"productType"
			};

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) actionContext
				.get(StrutsStatics.HTTP_REQUEST);

		HttpSession session = request.getSession();
		if (session != null) {
			String resourceCode = invocation.getProxy().getActionName();
			if (!StringUtils.isBlank(resourceCode)) {
				for (int i = 0; i < DEFAULT_LOGIN_URL.length; i++) {
					if (resourceCode.equals(DEFAULT_LOGIN_URL[i]))
						return invocation.invoke();
				}
			}

			if ( session.getAttribute("userId")!=null) {
				return invocation.invoke();
			}else{
				return "error";
			}
		}
		try {
			return Action.ERROR;
		} finally {
			ActionContext.getContext().getSession().clear();
		}
	}
}
