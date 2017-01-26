package com.shlaitao.techdata.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shlaitao.mybatis.dao.Dao;
import com.shlaitao.mybatis.dao.GridInfo;
import com.shlaitao.mybatis.dao.ResultData;
import com.shlaitao.mybatis.dao.PageHelper.Page;
import com.shlaitao.news.domain.TNews;
import com.shlaitao.techdata.domain.TTechData;

@Service("techService")
@Transactional
public class TechService {

	@Autowired
	@Qualifier("sqlMapDao")
	protected Dao dao;
	
	public boolean add(TTechData techData){
		int flag = dao.insert("techdata.insert", techData);
		if(flag==1){
			return true;
		}else{
			return false;
		}
	}
	
	public TTechData queryDetail(TTechData techData){
		// TODO Auto-generated method stub
		List<TTechData> lists = dao.query("techdata.queryDetail", techData);
		return lists.get(0);
	}
	
	public boolean update(TTechData techData){
		int flag = dao.update("techdata.update", techData);
		if(flag==1){
			return true;
		}else{
			return false;
		}
	}
	
	public ResultData query(TTechData techData, GridInfo gridPara){
		// TODO Auto-generated method stub
		ResultData resultData = new ResultData();
		int offset;
		int limit;
		try {
			offset = Integer.parseInt(gridPara.getPage());
			limit = Integer.parseInt(gridPara.getRows());
		} catch (NumberFormatException e) {
			// e.printStackTrace();
			offset = 1;
			limit = 99999999;
		}
		Page page = (Page) dao.query("techdata.query", techData, offset, limit, true, gridPara.getSidx(), gridPara.getSord());
		resultData.setResultList(page.getResult());
		gridPara.setRecords(page.getTotal() + "");
		gridPara.setTotal(page.getPages() + "");
		resultData.setGridInfo(gridPara);
		return resultData;
	}
	
	public boolean delete(List<TTechData> paraList){
		int size = paraList.size();
		for (int i = 0; i < size; i++) {
			TTechData dataTmp =  (TTechData) paraList.get(i);
			dao.delete("techdata.delete", dataTmp);
		}
		return true;
	}

	public TTechData queryBefore(TTechData techData) {
		List<TTechData> lists = dao.query("techdata.queryBefore", techData);
		if(lists.size()>0){
			return lists.get(0);
		}else{
			return null;
		}
	}

	public TTechData queryAfter(TTechData techData) {
		List<TTechData> lists = dao.query("techdata.queryAfter", techData);
		if(lists.size()>0){
			return lists.get(0);
		}else{
			return null;
		}
	}
}
