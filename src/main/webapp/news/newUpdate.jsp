<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" charset="utf-8" src="../kindEditor/kindeditor.js"></script>
<link rel="stylesheet" href="../css/pintuer.css">
<link rel="stylesheet" href="../css/admin.css">
<script type="text/javascript" src="newUpdate.js"/></script>
<title>新闻更新</title>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span>新闻信息</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="">    
    <input name="news.id" id="id" type="hidden" value="<s:property value="news.id"/>"/>
      <div class="form-group">
        <div class="label">
          <label>新闻标题：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="title" id="title" value="<s:property value="news.title"/>" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>新闻内容：</label>
        </div>
        <div class="field">
         <textarea id="newContent" name="newContent" cols="60" rows="10" style="width:100%;height:280px;visibility:hidden;"
		onpropertychange="if(value.length>65535) value=value.substr(0,65535)"><s:property value="news.content"/>
		</textarea>
		<br />
          <div class="tips">提示：最大输入长度65535，您还可以输入 <span id="word_count" class="count_tip2">65535</span> 个文字。（字数统计包含HTML代码。）</div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="button" onclick="update_new();"> 提交</button>
        </div>
      </div>
    </form>
  </div>
</div>
</body>
</html>