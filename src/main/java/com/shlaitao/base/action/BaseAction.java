package com.shlaitao.base.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.shlaitao.mybatis.dao.GridInfo;
import com.shlaitao.news.domain.TNews;

public class BaseAction extends ActionSupport{

	protected Map result = new HashMap();

	public Map getResult() {
		return result;
	}

	public void setResult(Map result) {
		this.result = result;
	}
	
	protected List resultList = new ArrayList();

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
	
	protected GridInfo gridPara = new GridInfo();

	public GridInfo getGridPara() {
		return gridPara;
	}

	public void setGridPara(GridInfo gridPara) {
		this.gridPara = gridPara;
	}

	protected String renderAction="" ;

	public String getRenderAction() {
		return renderAction;
	}

	public void setRenderAction(String renderAction) {
		this.renderAction = renderAction;
	}
	
	
}
