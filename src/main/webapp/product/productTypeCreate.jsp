<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/common.js"></script>
<link rel="stylesheet" href="../css/pintuer.css">
<link rel="stylesheet" href="../css/admin.css">
<script type="text/javascript" src="productTypeCreate.js"/></script>
<title>新增产品类别</title>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span>产品类别信息</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="">      
      <div class="form-group">
        <div class="label">
          <label>产品类别名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="productType.typename" id="typename" value="" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>产品类别描述：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="productType.typedesc"  id="typedesc" value="" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="button" onclick="add_productType();"> 提交</button>
        </div>
      </div>
    </form>
  </div>
</div>
</body>
</html>