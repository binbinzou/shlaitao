/**
 * 
 */
update_img = function(){
	$.ajaxFileUpload({
		url : "img!update",
		secureuri : false,
		fileElementId : "fileId",
		data : {
			"name":$("#name").val(),
			"id":$("#id").val()
		},
		 dataType: 'json',//返回数据类型
		type : "post",
		error : function(jqXHR,textStatus, errorThrown) {
			console.debug(jqXHR);
			if(jqXHR.responseText.indexOf("success") > 0){
				alert("更新成功");
				window.location.href="imgIndex.jsp";
			}else{
				if(jqXHR.responseText.toString().indexOf('文件大小不能大于10M')>=0){
					alert("文件大小不能大于10M,请重新选择文件!");
				}else{
					alert("滚动图片创建失败，失败原因：系统异常");
				}
			}
		},
		success : function(data, textStatus) {
			alert(data.result.message);
			if(data.result.status=="success"){
				window.location.href="imgIndex.jsp";
			}else{
				
			}
		}
	});
}
see_photo=function(url){
	window.open(url);
}