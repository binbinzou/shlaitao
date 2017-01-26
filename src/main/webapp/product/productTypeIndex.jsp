<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="productTypeIndex.js"/></script>
<title>产品类别管理</title>
</head>
<body>
<div class="sysMC">
	<div style="margin:5px 5px;">
	<div class="sysMC_con">
	<form id="productTypeForm" method="post">
		<input name="productType.id" id="id" type="hidden" />
		<fieldset class="box cont_box">
		<legend align="">查询</legend>
		 	<dl class="item_con">
			 	<dd class="long"><p>产品类别名称： </p><input class="queryEnter" name="productType.typename" id="typename" type="text"  >
			 	</dd>
			 	<dd class="Swidth"><div class="but_con bacG"><input class="butSize search_b queryEnter" type="button" id="query" onclick="button_query_onclick();" value="" /></div></dd>
				<dd class="Swidth"><div class="but_con bacR"><input class="butSize reset_b" type="button" id="reset" onclick="button_reset_onclick();" value="" /></div></dd>
			</dl>
		</fieldset>
   		</form>
     	</div>
     	<div class="c_operate">
			<input class="small button_light" type="button" id="add" onclick="button_add_onclick();" value="新增产品类别" >
	        <input class="small button_light" type="button" id="del" onclick="button_delete_onclick();" value="删除产品类别" >
		</div>
		<table id="gridTable"></table>
		<div id="gridPager"></div>
		<!-- 模态对话框 -->
		<div id="consoleDlg"></div>
	  </div>
</body>
</html>