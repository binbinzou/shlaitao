/**
 * 
 */
$(function() {
	$.ajax({
		url : "productType!query",
		data : {
			"gridPara.page":1,
			"gridPara.rows":999,
		},
		dataType : "json",
		cache : false,
		type : "post",
		error : function(textStatus, errorThrown) {
			alert("系统错误");
		},
		success : function(data, textStatus) {
			console.debug(data.resultList);
			var dataTmp = data.resultList;
			var size = dataTmp.length;
			for(var i=0;i<size;i++){
				if($("#productTypeTmp").val()==dataTmp[i].id){
					$("#type").append("<option selected=selected value='"+dataTmp[i].id+"'>"+dataTmp[i].typename+"</option>")
				}else{
					$("#type").append("<option value='"+dataTmp[i].id+"'>"+dataTmp[i].typename+"</option>")
				}
			}
		}
	});
});

update_product = function(){
	var id = $("#id").val();
	var number = $("#number").val();
	var name = $("#name").val();
	var dose = $("#dose").val();
	var content = $("#content").val();
	var consistence = $("#consistence").val();
	var type = $('#type option:selected').val();
	if(validate(number)){
		alert("请输入产品编号");
		return;
	}
	if(validate(name)){
		alert("请输入产品名称");
		return;
	}
	if(validate(dose)){
		alert("请输入产品剂量");
		return;
	}
	if(validate(content)){
		alert("请输入产品内容");
		return;
	}
	if(validate(consistence)){
		alert("请输入产品浓度");
		return;
	}
	if(validate(type)){
		alert("请输入产品类型");
		return;	
	}
	$.ajax({
		url : "product!update",
		data : {
			"product.id":id,
			"product.number":number,
			"product.name":name,
			"product.dose":dose,
			"product.content":content,
			"product.consistence":consistence,
			"product.type":type
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
				window.location.href="productIndex.jsp";
			}else{
				
			}
		}
	});
}