/*function iFrameHeight() {   
	var ifm= window.parent.document.getElementById("iframepage");   
	var subWeb = window.parent.document.frames ? window.parent.document.frames["iframepage"].document : ifm.contentDocument;   
	if(ifm != null && subWeb != null) {
		console.debug(subWeb.getElementById("main"));
	  // ifm.height = subWeb.getElementById("main").offsetHeight;
	  // ifm.width = subWeb.getElementById("main").offsetWidth;
	   $("#other_box").css("height",subWeb.getElementById("main").offsetHeight+"px");
	}   
}*/
 var TotalNum =10;
 var page=0;
 var type=1;
function change_procuct(id,name){
	$("#daohang").empty();
	$("#daohang").append("您现在的位置：<a href='#' onclick='back_page('firstMiddle.html');'>网站首页</a> &gt; 产品系列   &gt; "+name);
	type=id;
	pageselectCallback(0);
	 iFrameHeight();
	 $("#Pagination").pagination(TotalNum, { 
	        prev_text: "上一页", 
	        next_text: "下一页", 
	        items_per_page:5,
	        num_edge_entries: 2, 
	        num_display_entries: 8, 
	        current_page: 0,
	        //回调 
	        callback: pageselectCallback 
	  });	
}
$(function() {
	$.ajax({
		url : "productType!query",
		dataType : "json",
		cache : false,
		type : "post",
		 async: false,
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
			change_procuct(data.resultList[0].id,data.resultList[0].typename);
		}

	});
	
	 //pageselectCallback(0);
	 $("#Pagination").pagination(TotalNum, { 
	        prev_text: "上一页", 
	        next_text: "下一页", 
	        items_per_page:5,
	        num_edge_entries: 2, 
	        num_display_entries: 8, 
	        current_page: 0,
	        //回调 
	        callback: pageselectCallback 
	  });	 
	 
});
function pageselectCallback(page) { 
    var result = ""; 
    $.ajax({ 
        type: "post", 
        dataType: "json",
        async: false,
        url: "product!query", //请求的url 
        data: { 
        	"gridPara.page": parseInt(page + 1),
        	"gridPara.rows": 5,
        	"product.type":type
        }, 
        success: function (data) { 
        	
        	TotalNum = parseInt(data.gridPara.records);
            //使用微软jQuery Templates绑定数据列表,实现了HTML与js分离,使得页面整洁 
        	if(data.resultList.length>0){
        		 $("#tbody").empty();
        	}
        	var temp = $("#Template");
           $("#tbody").append($("#Template").tmpl(data.resultList)); 
           iFrameHeight();
        } 
    });
     
} 
function iFrameHeight() {  
		window.parent.window.iFrameHeight();
	   /*var other_box = $("#other_box");
	   if(other_box!=null){
		   subTop = 20;
		   var divc1 = $(other_box).children();
		   var size1 = divc1.length;
		   var height1 = 0;
		   for(var i = 0 ;i < size1 ;i++){
			   if(divc1[i].offsetHeight>height1){
				   height1 =  divc1[i].offsetHeight;
			   }
		   }
		   $(other_box).css("height",height1+"px");
	   }*/
	   
}
function change_page(id,id2){
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