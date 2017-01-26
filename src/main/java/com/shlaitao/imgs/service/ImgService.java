package com.shlaitao.imgs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shlaitao.imgs.domain.TImgs;
import com.shlaitao.mybatis.dao.Dao;
import com.shlaitao.mybatis.dao.GridInfo;
import com.shlaitao.mybatis.dao.ResultData;
import com.shlaitao.mybatis.dao.PageHelper.Page;
import com.shlaitao.news.domain.TNews;

@Service("imgService")
@Transactional
public class ImgService {

	@Autowired
	@Qualifier("sqlMapDao")
	protected Dao dao;
	
	public boolean add(TImgs imgs){
		int flag = dao.insert("img.insert", imgs);
		if(flag==1){
			return true;
		}else{
			return false;
		}
	}
	
	public ResultData query(TImgs imgs, GridInfo gridPara) {
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
		Page page = (Page) dao.query("img.query", imgs, offset, limit, true, gridPara.getSidx(), gridPara.getSord());
		resultData.setResultList(page.getResult());
		gridPara.setRecords(page.getTotal() + "");
		gridPara.setTotal(page.getPages() + "");
		resultData.setGridInfo(gridPara);
		return resultData;
	}
	
	public TImgs queryDetail(TImgs imgs) {
		// TODO Auto-generated method stub
		List<TImgs> lists = dao.query("img.queryDetail", imgs);
		return lists.get(0);
	}
	
	public boolean update(TImgs imgs) {
		// TODO Auto-generated method stub
		int flag = dao.update("img.update", imgs);
		if(flag==1){
			return true;
		}else{
			return false;
		}
	}
	
	
	public boolean delete(List paraList) {
		int size = paraList.size();
		for (int i = 0; i < size; i++) {
			TImgs imgTmp =  (TImgs) paraList.get(i);
			dao.delete("img.delete", imgTmp);
		}
		return true;
	}
	
}
