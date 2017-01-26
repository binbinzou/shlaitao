package com.shlaitao.techdata.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.shlaitao.base.action.BaseAction;
import com.shlaitao.mybatis.dao.ResultData;
import com.shlaitao.news.domain.TNews;
import com.shlaitao.techdata.domain.TTechData;
import com.shlaitao.techdata.service.TechService;
import com.shlaitao.user.service.UserService;

@Controller
@Scope("prototype")
public class TechdataAction extends BaseAction{

	private TTechData techData;
	public TTechData getTechData() {
		return techData;
	}
	public void setTechData(TTechData techData) {
		this.techData = techData;
	}
	private TTechData techDataAfter;
	private TTechData techDataBefore;
	public TTechData getTechDataAfter() {
		return techDataAfter;
	}
	public void setTechDataAfter(TTechData techDataAfter) {
		this.techDataAfter = techDataAfter;
	}
	public TTechData getTechDataBefore() {
		return techDataBefore;
	}
	public void setTechDataBefore(TTechData techDataBefore) {
		this.techDataBefore = techDataBefore;
	}
	private String[] dataIds;
	public String[] getDataIds() {
		return dataIds;
	}
	public void setDataIds(String[] dataIds) {
		this.dataIds = dataIds;
	}
	@Autowired
	@Qualifier("techService")
	private TechService techService;
	
	public String add(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
		techData.setCreatetime(sdf.format(new Date()));
		techData.setStatus("1");
		boolean newFlag = techService.add(techData);
		if(newFlag){
			result.put("status", "success");
			result.put("message", "新增成功");
		}else{
			result.put("status", "fail");
			result.put("message", "新增失败");
		}
		return "success";
	}
	
	public String queryDetail(){
		techData = techService.queryDetail(techData);
		this.renderAction = "techdataUpdate.jsp"; 
		return "render";
	}
	
	public String queryDetailForJson(){
		techData = techService.queryDetail(techData);
		techDataBefore = techService.queryBefore(techData);
		techDataAfter = techService.queryAfter(techData);
		return "success";
	}
	
	public String update(){
		boolean newFlag = techService.update(techData);
		if(newFlag){
			result.put("status", "success");
			result.put("message", "更新成功");
		}else{
			result.put("status", "fail");
			result.put("message", "更新失败");
		}
		return "success";
	}
	
	public String query(){
		ResultData resultData = techService.query(techData,gridPara);
		this.resultList = resultData.getResultList();
		this.gridPara = resultData.getGridInfo();
		return "success";
	}
	
	public String delete(){
		List paraList = new ArrayList();
		int size = dataIds.length;
		for (int i = 0; i < size; i++) {
			TTechData dataTmp = new TTechData();
			dataTmp.setStatus("0");
			dataTmp.setId(Integer.parseInt(dataIds[i]));
			paraList.add(dataTmp);
		}
		techService.delete(paraList);
		return "success";
	}
	
	
}
