package com.shlaitao.imgs.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.shlaitao.base.action.BaseAction;
import com.shlaitao.base.util.ResourceUtils;
import com.shlaitao.imgs.domain.TImgs;
import com.shlaitao.imgs.service.ImgService;
import com.shlaitao.mybatis.dao.ResultData;
import com.shlaitao.news.domain.TNews;
import com.shlaitao.product.service.ProductService;

@Controller
@Scope("prototype")
public class ImgAction extends BaseAction{
	protected static final int BUFFER_SIZE = 16 * 1024;
	private static final String FILE_PATH = "filepath";//文件路径相关配置文件

	private File fileId;
	public File getFileId() {
		return fileId;
	}
	public void setFileId(File fileId) {
		this.fileId = fileId;
	}
	private String fileFileName;
	private String contentType;
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	private String id ;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Autowired
	@Qualifier("imgService")
	private ImgService imgService;
	
	private String[] imgsIds;
    public String[] getImgsIds() {
		return imgsIds;
	}
	public void setImgsIds(String[] imgsIds) {
		this.imgsIds = imgsIds;
	}

	public String add(){
		
    	HttpServletRequest request = ServletActionContext.getRequest();
    	MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper)request;  
        File file = wrapper.getFiles("fileId")[0];
        //文件保存目录URL
        String fileName = wrapper.getFileNames("fileId")[0];  
        String name = wrapper.getParameter("name");
        String saveUrl  = request.getContextPath() + "/attached/";
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
        saveUrl += newFileName;  
        String savePath = ResourceUtils.getProperty(FILE_PATH, "fileurl");//request.getRealPath("/")+"attached/";
        File test = new File(savePath);
        if(!test.exists()){
            test.mkdirs();
        }
        /** 从源文件中读出数据写入目标文件中 **/
		try {
			InputStream in = null;
			OutputStream out = null;
			try { 
				int byteread = 0; // 读取的字节数  
				in = new FileInputStream(file);
				out = new FileOutputStream(savePath+newFileName);
				byte[] buffer = new byte[BUFFER_SIZE];
			    while ((byteread = in.read(buffer)) != -1) {  
	                out.write(buffer, 0, byteread);  
	            }  
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(saveUrl);
		TImgs imgs = new TImgs();
		imgs.setName(name);
		imgs.setUrl(saveUrl);
		imgs.setStatus("1");
		boolean newFlag = imgService.add(imgs);
		if(newFlag){
			result.put("stus", "success");
			result.put("message", "新增成功");
		}else{
			result.put("stus", "fail");
			result.put("message", "新增失败");
		}
		return "filesuccess";
    }
	
    private TImgs imgs = new TImgs();
	public TImgs getImgs() {
		return imgs;
	}

	public void setImgs(TImgs imgs) {
		this.imgs = imgs;
	}

	public String query(){
		ResultData resultData = imgService.query(imgs,gridPara);
		this.resultList = resultData.getResultList();
		this.gridPara = resultData.getGridInfo();
		return "success";
	}
    
	public String queryDetail(){
		imgs = imgService.queryDetail(imgs);
		this.renderAction = "imgUpdate.jsp"; 
		return "render";
	}
	
	public String update(){
    	HttpServletRequest request = ServletActionContext.getRequest();
    	MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper)request;  
        //File[] file = wrapper.getFiles("fileId");  
    	File file = wrapper.getFiles("fileId")[0];
        //文件保存目录URL
    	 String fileName = wrapper.getFileNames("fileId")[0];  
        //文件保存目录URL
        String name = this.getName();
        String id = this.getId();
        if(file==null){
        	imgs.setId(Integer.parseInt(id));
    		imgs.setName(name);
    		boolean newFlag = imgService.update(imgs);
    		if(newFlag){
    			result.put("stus", "success");
    			result.put("message", "更新成功");
    		}else{
    			result.put("stus", "fail"); 
    			result.put("message", "更新失败");
    		}
    		return "filesuccess";
        }
        String saveUrl  = request.getContextPath() + "/attached/";
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
        saveUrl += newFileName;  
        String savePath = ResourceUtils.getProperty(FILE_PATH, "fileurl");
        File test = new File(savePath);
        if(!test.exists()){
            test.mkdirs();
        }
        /** 从源文件中读出数据写入目标文件中 **/
		try {
			InputStream in = null;
			OutputStream out = null;
			try { 
				int byteread = 0; // 读取的字节数  
				in = new FileInputStream(file);
				out = new FileOutputStream(savePath+newFileName);
				byte[] buffer = new byte[BUFFER_SIZE];
			    while ((byteread = in.read(buffer)) != -1) {  
	                out.write(buffer, 0, byteread);  
	            }  
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(saveUrl);
		imgs.setId(Integer.parseInt(id));
		imgs.setName(name);
		imgs.setUrl(saveUrl);
		boolean newFlag = imgService.update(imgs);
		if(newFlag){
			result.put("stus", "success");
			result.put("message", "新增成功");
		}else{
			result.put("stus", "fail");
			result.put("message", "新增失败");
		}
		return "filesuccess";
    }
	
	public String delete(){
		List paraList = new ArrayList();
		int size = imgsIds.length;
		for (int i = 0; i < size; i++) {
			TImgs imgsTmp = new TImgs();
			imgsTmp.setStatus("0");
			imgsTmp.setId(Integer.parseInt(imgsIds[i]));
			paraList.add(imgsTmp);
		}
		imgService.delete(paraList);
		return "success";
	}
	
}
