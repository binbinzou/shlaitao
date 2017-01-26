function change_page(id){
	for(var i=1;i<7;i++){
		$("#tab"+i,parent.document).css("color","#000");
	}
	$("#tab3",parent.document).css("color","#00A2E6");
	$("#iframepage",parent.document).attr("src",id);
}
function back_page(id){
	$("#iframepage", parent.document).attr("src",id);
	for(var i=1;i<7;i++){
		$("#tab"+i,parent.document).css("color","#000");
	}
	$("#tab1",parent.document).css("color","#00A2E6");
	$("body",parent.document).scrollIntoView();
}
function change_procuct(id,name){
	for(var i=1;i<7;i++){
		$("#tab"+i,parent.document).css("color","#000");
	}
	$("#tab3",parent.document).css("color","#00A2E6");
	$("#iframepage",parent.document).attr("src","productMiddle1.html");
}
$(function() {
	$.ajax({
		url : "productType!query",
		dataType : "json",
		cache : false,
		type : "post",
		error : function(textStatus, errorThrown) {
		},
		success : function(data, textStatus) {
			var size = data.resultList.length;
			if(size>0){
				$("#productType").empty();
			}
			for(var i=0;i<size;i++){
				var str = "<div class='div2'><div class='jbsz'></div><a href='javascript:void(0)' onclick='change_procuct("+'"'+data.resultList[i].id+'"'+","+'"'+data.resultList[i].typename+'"'+")'>"+data.resultList[i].typename+"</a></div>";
				$("#productType").append(str);
			}
		}

	});
});

