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
import com.shlaitao.techdata.domain.TTechData;

@Service("productService")
@Transactional
public class ProductService {

	@Autowired
	@Qualifier("sqlMapDao")
	protected Dao dao;
	
	public boolean add(TProduct product){
		int flag = dao.insert("product.insert", product);
		if(flag==1){
			return true;
		}else{
			return false;
		}
	}
	
	public TProduct queryDetail(TProduct product){
		// TODO Auto-generated method stub
		List<TProduct> lists = dao.query("product.queryDetail", product);
		return lists.get(0);
	}
	
	public boolean update(TProduct product){
		int flag = dao.update("product.update", product);
		if(flag==1){
			return true;
		}else{
			return false;
		}
	}
	
	public ResultData query(TProduct product, GridInfo gridPara){
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
		Page page = (Page) dao.query("product.query", product, offset, limit, true, gridPara.getSidx(), gridPara.getSord());
		resultData.setResultList(page.getResult());
		gridPara.setRecords(page.getTotal() + "");
		gridPara.setTotal(page.getPages() + "");
		resultData.setGridInfo(gridPara);
		return resultData;
	}
	
	public boolean delete(List<TProduct> paraList){
		int size = paraList.size();
		for (int i = 0; i < size; i++) {
			TProduct productTmp =  (TProduct) paraList.get(i);
			dao.delete("product.delete", productTmp);
		}
		return true;
	}
	
}
