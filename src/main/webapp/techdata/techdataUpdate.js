/**
 * 
 */
var editor;
 KindEditor.ready(function(K) {
     editor = K.create('#newContent', {
        resizeType : 2,
        //uploadJson : 'kindEditor/jsp/upload_json.jsp', // 相对于当前页面的路径
        uploadJson : 'imgUpload!img', // 相对于当前页面的路径
       	//fileManagerJson : 'kindEditor/jsp/file_manager_json.jsp',
		allowFileManager : true,
		afterChange : function() {
			K('#word_count').html(65535 - this.count());
		},
     	afterBlur: function(){this.sync();}
    });
 });
function check(){
	  var count = document.getElementById("word_count").innerHTML ;
	  if(count==65535)
		  {
		  alert("请输入内容");
		  return false ;
		  }
	  else if(parseInt(count)/1 < 65535  && parseInt(count)/1 > 0)
		  {
		  return true ;
		  }
	  else{
		  alert("字数超出范围");
		  return false ;
	  }
}
update_techdata = function(){
	var id = $("#id").val();
	var title = $("#title").val();
	var newContent = $("#newContent").val();
	console.debug(newContent);
	if(validate(title)){
		alert("请输入资料标题");
		return;
	}
	if(validate(newContent)){
		alert("请输入资料内容");
		return;	
	}
	$.ajax({
		url : "techdata!update",
		data : {
			"techData.id":id,
			"techData.title":title,
			"techData.content":newContent
		},
		dataType : "json",
		cache : false,
		type : "post",
		error : function(textStatus, errorThrown) {
			alert("系统错误");
		},
		success : function(data, textStatus) {
			alert(data.result.message);
			if(data.result.status=="success"){
				window.location.href="techdataIndex.jsp";
			}else{
				
			}
		}
	});
}
   