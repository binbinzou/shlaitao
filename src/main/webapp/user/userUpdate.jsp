<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/common.js"></script>
<link rel="stylesheet" href="../css/pintuer.css">
<link rel="stylesheet" href="../css/admin.css">
<script type="text/javascript" src="userUpdate.js"/></script>
<title>修改用户</title>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span>更新用户信息</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="">      
      <div class="form-group">
        <div class="label">
          <label>原密码：</label>
        </div>
        <div class="field">
          <input type="password" class="input" name="user.password" id="password" value="" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>新密码：</label>
        </div>
        <div class="field">
          <input type="password" class="input" name="user.newPassword" id="newPassword" value="" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>确认新密码：</label>
        </div>
        <div class="field">
          <input type="password" class="input" name="user.newPasswordTmp" id="newPasswordTmp" value="" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="button" onclick="update_password();"> 提交</button>
        </div>
      </div>
    </form>
  </div>
</div>
</body>
</html>