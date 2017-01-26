<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/ajaxfileupload.js"></script>
<link rel="stylesheet" href="../css/pintuer.css">
<link rel="stylesheet" href="../css/admin.css">
<script type="text/javascript" src="imgUpdate.js"/></script>
<title>滚动图片更新</title>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span>图片信息</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="">    
        <input name="imgs.id" id="id" type="hidden" value="<s:property value="imgs.id"/>"/>
      <div class="form-group">
        <div class="label">
          <label>图片名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="img.name" id="name" value="<s:property value="imgs.name"/>" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>图片文件：</label>
        </div>
        <div class="field">
          <input style="float:left;" type="file" class="input" name="fileId" id="fileId" value="" />
          <%-- <a style="float:left;" href="#" onclick='see_photo("<s:property value="imgs.url"/>");'>查看</a> --%>
          <img style="width:200px;height:100px" alt="" src="<s:property value="imgs.url"/>">
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="button" onclick="update_img();"> 提交</button>
        </div>
      </div>
    </form>
  </div>
</div>
</body>
</html>