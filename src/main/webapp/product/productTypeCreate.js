/**
 * 
 */
add_productType = function(){
	var typename = $("#typename").val();
	var typedesc = $("#typedesc").val();
	if(validate(typename)){
		alert("请输入产品类别名称");
		return;
	}
	if(validate(typedesc)){
		alert("请输入产品类别描述");
		return;
	}
	$.ajax({
		url : "productType!add",
		data : {
			"productType.typename":typename,
			"productType.typedesc":typedesc
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
				window.location.href="productTypeIndex.jsp";
			}else{
				
			}
		}
	});
}
   