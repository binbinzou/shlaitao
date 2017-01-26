<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="imgIndex.js"/></script>
<title>滚动图片管理主页</title>
</head>
<body>
<div class="sysMC">
	<form id="imgForm" method="post">
		<input name="imgs.id" id="id" type="hidden" />
   		</form>
	<div style="margin:5px 5px;">
     	<div class="c_operate">
			<input class="small button_light" type="button" id="add" onclick="button_add_onclick();" value="新增图片" >
	        <input class="small button_light" type="button" id="del" onclick="button_delete_onclick();" value="删除图片" >
		</div>
		<table id="gridTable"></table>
		<div id="gridPager"></div>
		<!-- 模态对话框 -->
		<div id="consoleDlg"></div>
	  </div>
</body>
</html>