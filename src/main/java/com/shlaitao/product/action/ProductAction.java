package com.shlaitao.product.action;

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
import com.shlaitao.product.domain.TProduct;
import com.shlaitao.product.service.ProductService;
import com.shlaitao.techdata.domain.TTechData;

@Controller
@Scope("prototype")
public class ProductAction extends BaseAction{

	private TProduct product;
	public TProduct getProduct() {
		return product;
	}
	public void setProduct(TProduct product) {
		this.product = product;
	}
	private String[] productIds;
	public String[] getProductIds() {
		return productIds;
	}
	public void setProductIds(String[] productIds) {
		this.productIds = productIds;
	}
	@Autowired
	@Qualifier("productService")
	private ProductService productService;
	
	public String add(){
		product.setStatus("1");
		boolean newFlag = productService.add(product);
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
		product = productService.queryDetail(product);
		this.renderAction = "productUpdate.jsp"; 
		return "render";
	}
	
	public String update(){
		boolean newFlag = productService.update(product);
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
		ResultData resultData = productService.query(product,gridPara);
		this.resultList = resultData.getResultList();
		this.gridPara = resultData.getGridInfo();
		return "success";
	}
	
	public String delete(){
		List paraList = new ArrayList();
		int size = productIds.length;
		for (int i = 0; i < size; i++) {
			TProduct productTmp = new TProduct();
			productTmp.setStatus("0");
			productTmp.setId(Integer.parseInt(productIds[i]));
			paraList.add(productTmp);
		}
		productService.delete(paraList);
		return "success";
	}
	
	
}
