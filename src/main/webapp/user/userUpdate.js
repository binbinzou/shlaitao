/**
 * 
 */
update_password = function(){
	var password = $("#password").val();
	var newPassword = $("#newPassword").val();
	var newPasswordTmp = $("#newPasswordTmp").val();
	if(validate(password)){
		alert("请输入原密码");
		return;
	}
	if(validate(newPassword)){
		alert("请输入新密码");
		return;	
	}
	if(validate(newPasswordTmp)){
		alert("请输入确认新密码");
		return;	
	}
	if(newPassword!=newPasswordTmp){
		alert("新密码不一致");
		return;	
	}
	$.ajax({
		url : "user!updatePassword",
		data : {
			"user.password":password,
			"user.newPassword":newPassword
		},
		dataType : "json",
		cache : false,
		type : "post",
		error : function(textStatus, errorThrown) {
			alert("系统错误");
		},
		success : function(data, textStatus) {
			if(data.result.status=="success"){
				alert("密码修改成功");
				$("#password").val("");
				$("#newPassword").val("");
				$("#newPasswordTmp").val("");
			}else{
				alert(data.result.message);
			}
		}
	});
}