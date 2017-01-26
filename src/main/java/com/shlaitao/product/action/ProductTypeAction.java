package com.shlaitao.product.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.shlaitao.base.action.BaseAction;
import com.shlaitao.mybatis.dao.ResultData;
import com.shlaitao.product.domain.TProduct;
import com.shlaitao.product.domain.TProductType;
import com.shlaitao.product.service.ProductService;
import com.shlaitao.product.service.ProductTypeService;
@Controller
@Scope("prototype")
public class ProductTypeAction extends BaseAction{

	private TProductType productType;
	public TProductType getProductType() {
		return productType;
	}
	public void setProductType(TProductType productType) {
		this.productType = productType;
	}
	private String[] productTypeIds;
	public String[] getProductTypeIds() {
		return productTypeIds;
	}
	public void setProductTypeIds(String[] productTypeIds) {
		this.productTypeIds = productTypeIds;
	}
	@Autowired
	@Qualifier("productTypeService")
	private ProductTypeService productTypeService;
	
	public String add(){
		productType.setStatus("1");
		boolean newFlag = productTypeService.add(productType);
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
		productType = productTypeService.queryDetail(productType);
		this.renderAction = "productTypeUpdate.jsp"; 
		return "render";
	}
	
	public String update(){
		boolean newFlag = productTypeService.update(productType);
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
		ResultData resultData = productTypeService.query(productType,gridPara);
		this.resultList = resultData.getResultList();
		this.gridPara = resultData.getGridInfo();
		return "success";
	}
	
	public String delete(){
		List paraList = new ArrayList();
		int size = productTypeIds.length;
		for (int i = 0; i < size; i++) {
			TProductType productTypeTmp = new TProductType();
			productTypeTmp.setStatus("0");
			productTypeTmp.setId(Integer.parseInt(productTypeIds[i]));
			paraList.add(productTypeTmp);
		}
		productTypeService.delete(paraList);
		return "success";
	}
	
	
}
