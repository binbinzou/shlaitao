<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/ajaxfileupload.js"></script>
<link rel="stylesheet" href="../css/pintuer.css">
<link rel="stylesheet" href="../css/admin.css">
<script type="text/javascript" src="imgCreate.js"/></script>
<title>滚动图片新建</title>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span>图片信息</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="">      
      <div class="form-group">
        <div class="label">
          <label>图片名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="img.name" id="name" value="" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>图片文件：</label>
        </div>
        <div class="field">
          <input type="file" class="input" name="fileId" id="fileId" value="" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="button" onclick="add_img();"> 提交</button>
        </div>
      </div>
    </form>
  </div>
</div>
</body>
</html>