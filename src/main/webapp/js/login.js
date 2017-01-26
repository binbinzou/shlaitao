/**
 * 
 */
function button_login_onclick() {
	var param = $("#loginFrom").serializeArray();
	$.ajax({
		url : "user!login",
		data : param,
		dataType : "json",
		cache : false,
		type : "post",
		error : function(textStatus, errorThrown) {
			alert("系统错误");
		},
		success : function(data, textStatus) {
			if(data.result.status=="success"){
				window.location.href="../common/index.jsp";
			}else{
				alert(data.result.message);
			}
		}
	});
	
}