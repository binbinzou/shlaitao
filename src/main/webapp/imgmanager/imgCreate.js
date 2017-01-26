/**
 * 
 */
add_img = function(){
	$.ajaxFileUpload({
			url : "img!add",
			secureuri : false,
			fileElementId : "fileId",
			data : {
				"name":$("#name").val()
			},
			dataType : "json",
			type : "post",
			error : function(jqXHR,textStatus, errorThrown) {
				if(jqXHR.indexOf("success") > 0){
					alert("新增成功");
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