package com.shlaitao.news.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.shlaitao.base.action.BaseAction;
import com.shlaitao.mybatis.dao.ResultData;
import com.shlaitao.news.domain.TNews;
import com.shlaitao.news.service.NewsService;
import com.shlaitao.product.service.ProductService;
import com.shlaitao.user.domain.TUser;

@Controller
@Scope("prototype")
public class NewsAction extends BaseAction{

	private TNews news;
	public TNews getNews() {
		return news;
	}
	public void setNews(TNews news) {
		this.news = news;
	}
	private TNews newsAfter;
	private TNews newsBefore;
	public TNews getNewsAfter() {
		return newsAfter;
	}
	public void setNewsAfter(TNews newsAfter) {
		this.newsAfter = newsAfter;
	}
	public TNews getNewsBefore() {
		return newsBefore;
	}
	public void setNewsBefore(TNews newsBefore) {
		this.newsBefore = newsBefore;
	}
	private String[] newsIds;
	public String[] getNewsIds() {
		return newsIds;
	}
	public void setNewsIds(String[] newsIds) {
		this.newsIds = newsIds;
	}
	@Autowired
	@Qualifier("newsService")
	private NewsService newsService;
	
	public String add(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
		news.setCreatetime(sdf.format(new Date()));
		news.setStatus("1");
		boolean newFlag = newsService.add(news);
		if(newFlag){
			result.put("status", "success");
			result.put("message", "新增成功");
		}else{
			result.put("status", "fail");
			result.put("message", "新增失败");
		}
		return "success";
	}
	
	public String query(){
		ResultData resultData = newsService.query(news,gridPara);
		this.resultList = resultData.getResultList();
		this.gridPara = resultData.getGridInfo();
		return "success";
	}
	
	public String queryDetail(){
		news = newsService.queryDetail(news);
		this.renderAction = "newUpdate.jsp"; 
		return "render";
	}
	
	public String queryDetailForJson(){
		news = newsService.queryDetail(news);
		newsBefore = newsService.queryBefore(news);
		newsAfter = newsService.queryAfter(news);
		return "success";
	}
	
	public String update(){
		boolean newFlag = newsService.update(news);
		if(newFlag){
			result.put("status", "success");
			result.put("message", "更新成功");
		}else{
			result.put("status", "fail");
			result.put("message", "更新失败");
		}
		return "success";
	}
	
	public String delete(){
		List paraList = new ArrayList();
		int size = newsIds.length;
		for (int i = 0; i < size; i++) {
			TNews newTmp = new TNews();
			newTmp.setStatus("0");
			newTmp.setId(Integer.parseInt(newsIds[i]));
			paraList.add(newTmp);
		}
		newsService.delete(paraList);
		return "success";
	}
	
}
