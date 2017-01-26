package com.shlaitao.news.service;

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

@Service("newsService")
@Transactional
public class NewsService {

	@Autowired
	@Qualifier("sqlMapDao")
	protected Dao dao;
	
	public boolean add(TNews news){
		int flag = dao.insert("new.insert", news);
		if(flag==1){
			return true;
		}else{
			return false;
		}
	}

	public ResultData query(TNews news, GridInfo gridPara) {
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
		Page page = (Page) dao.query("new.query", news, offset, limit, true, gridPara.getSidx(), gridPara.getSord());
		resultData.setResultList(page.getResult());
		gridPara.setRecords(page.getTotal() + "");
		gridPara.setTotal(page.getPages() + "");
		resultData.setGridInfo(gridPara);
		return resultData;
	}

	public TNews queryDetail(TNews news) {
		// TODO Auto-generated method stub
		List<TNews> lists = dao.query("new.queryDetail", news);
		return lists.get(0);
	}

	public boolean update(TNews news) {
		// TODO Auto-generated method stub
		int flag = dao.update("new.update", news);
		if(flag==1){
			return true;
		}else{
			return false;
		}
	}

	public boolean delete(List paraList) {
		int size = paraList.size();
		for (int i = 0; i < size; i++) {
			TNews newTmp =  (TNews) paraList.get(i);
			dao.delete("new.delete", newTmp);
		}
		return true;
	}

	public TNews queryBefore(TNews news) {
		List<TNews> lists = dao.query("new.queryBefore", news);
		if(lists.size()>0){
			return lists.get(0);
		}else{
			return null;
		}
	}

	public TNews queryAfter(TNews news) {
		List<TNews> lists = dao.query("new.queryAfter", news);
		if(lists.size()>0){
			return lists.get(0);
		}else{
			return null;
		}
	}
	
}
