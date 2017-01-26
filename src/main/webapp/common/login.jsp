<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if lt IE 7 ]> <html lang="en" class="ie6 ielt8"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="ie7 ielt8"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="ie8"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--> <html lang="en"> <!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/login.css" />
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/login.js"></script>
<title>管理员登录</title>
</head>
<body>
<div class="container">
	<section id="content">
		<form action="" id="loginFrom">
			<h1>管理员登录</h1>
			<div>
				<input type="text" placeholder="登录名" required="" id="username" name="user.username"/>
			</div>
			<div>
				<input type="password" placeholder="密码" required="" id="password" name="user.password"/>
			</div>
			<div>
				<input type="button" onclick="button_login_onclick();" value="登录" />
			</div>
		</form><!-- form -->
	</section><!-- content -->
</div><!-- container -->
</body>
</html>