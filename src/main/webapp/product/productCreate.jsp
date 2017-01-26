<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/common.js"></script>
<link rel="stylesheet" href="../css/pintuer.css">
<link rel="stylesheet" href="../css/admin.css">
<script type="text/javascript" src="productCreate.js"/></script>
<title>新建产品</title>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span>产品信息</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="">      
      <div class="form-group">
        <div class="label">
          <label>产品编号：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="product.number" id="number" value="" />
          <div class="tips"></div>
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label>产品名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="product.name" id="name" value="" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>产品剂量：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="product.dose" id="dose" value="" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>资料内容：</label>
        </div>
        <div class="field">
         <textarea  name="product.content" id="content" cols="60" rows="10" style="border-radius:3px;border:solid 1px #ddd;width:100%;">★</textarea>
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label>产品浓度：</label>
        </div>
        <div class="field">
          <textarea cols="60" rows="10" style="border-radius:3px;border:solid 1px #ddd;width:100%;" name="product.consistence" id="consistence" value="" />★</textarea>
          <div class="tips"></div>
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label>产品类别：</label>
        </div>
        <div class="field">
        	<select name="product.type" id="type" class="input">
        	</select>
          <!-- <input type="text" class="input" name="product.type" id="type" value="" /> -->
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="button" onclick="add_product();"> 提交</button>
        </div>
      </div>
    </form>
  </div>
</div>
</body>
</html>