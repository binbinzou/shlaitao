<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理中心</title>
<link rel="stylesheet" href="../css/pintuer.css">
<link rel="stylesheet" href="../css/admin.css">
<script type="text/javascript" src="../js/common.js"></script>
</head>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
    <h1><img src="../images/admin/y.jpg" class="radius-circle rotate-hover" height="50" alt="" />莱涛后台管理中心</h1>
  </div>
  <div class="head-l">
  <a class="button button-little bg-green" href="../../index.html" target="_blank">
  <span class="icon-home"></span> 官网首页</a> &nbsp;&nbsp;<a class="button button-little bg-red" href="#" onclick="logout();">
  <span class="icon-power-off"></span> 退出登录</a> </div>
</div>
<div class="leftnav">
  <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
  <h2><span class="icon-user"></span>用户设置</h2>
  <ul style="display:block">
    <li><a href="../user/userUpdate.jsp" target="right"><span class="icon-caret-right"></span>修改密码</a></li>
  </ul>   
  <h2><span class="icon-pencil-square-o"></span>其他管理</h2>
  <ul>
    <li><a href="../news/newIndex.jsp" target="right"><span class="icon-caret-right"></span>新闻管理</a></li>
    <li><a href="../techdata/techdataIndex.jsp" target="right"><span class="icon-caret-right"></span>技术资料</a></li>
    <li><a href="../product/productIndex.jsp" target="right"><span class="icon-caret-right"></span>产品管理</a></li>   
    <li><a href="../product/productTypeIndex.jsp" target="right"><span class="icon-caret-right"></span>产品类别管理</a></li>        
    <li><a href="../imgmanager/imgIndex.jsp" target="right"><span class="icon-caret-right"></span>滚动图片管理</a></li>        
  </ul>  
</div>
<script type="text/javascript">
$(function(){
  $(".leftnav h2").click(function(){
	  $(this).next().slideToggle(200);	
	  $(this).toggleClass("on"); 
  })
  $(".leftnav ul li a").click(function(){
	    $("#a_leader_txt").text($(this).text());
  		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
  })
});
logout = function(){
	window.location.href="user!logout";
}
</script>
<ul class="bread">
  <li><a href="http://www.shlaitao.com" target="right" class="icon-home"> 首页</a></li>
  <li><a href="javascript:void(0)" id="a_leader_txt"></a></li>
  <!-- <li><b>当前语言：</b><span style="color:red;">中文</php></span>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;切换语言：<a href="##">中文</a> &nbsp;&nbsp;<a href="##">英文</a> </li> -->
</ul>
<div class="admin">
  <iframe scrolling="auto" rameborder="0" src="http://www.shlaitao.com" name="right" width="100%" height="100%"></iframe>
</div>

</body>
</html>