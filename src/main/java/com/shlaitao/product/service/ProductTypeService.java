package com.shlaitao.product.service;

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
import com.shlaitao.product.domain.TProduct;
import com.shlaitao.product.domain.TProductType;

@Service("productTypeService")
@Transactional
public class ProductTypeService {

	@Autowired
	@Qualifier("sqlMapDao")
	protected Dao dao;
	
	public boolean add(TProductType productType) {
		int flag = dao.insert("productType.insert", productType);
		if(flag==1){
			return true;
		}else{
			return false;
		}
	}

	public TProductType queryDetail(TProductType productType) {
		// TODO Auto-generated method stub
		List<TProductType> lists = dao.query("productType.queryDetail", productType);
		return lists.get(0);
	}

	public boolean update(TProductType productType) {
		int flag = dao.update("productType.update", productType);
		if(flag==1){
			return true;
		}else{
			return false;
		}
	}

	public ResultData query(TProductType productType, GridInfo gridPara) {
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
		Page page = (Page) dao.query("productType.query", productType, offset, limit, true, gridPara.getSidx(), gridPara.getSord());
		resultData.setResultList(page.getResult());
		gridPara.setRecords(page.getTotal() + "");
		gridPara.setTotal(page.getPages() + "");
		resultData.setGridInfo(gridPara);
		return resultData;
	}

	public boolean delete(List<TProductType> paraList) {
		int size = paraList.size();
		for (int i = 0; i < size; i++) {
			TProductType productTypeTmp =  (TProductType) paraList.get(i);
			dao.delete("productType.delete", productTypeTmp);
		}
		return true;
	}

}
