/**
 * 
 */
 var TotalNum =0;
 var opt;
$(function() {
	opt = {
	        prev_text: "上一页", 
	        next_text: "下一页", 
	        items_per_page:10,
	        num_edge_entries: 2, 
	        num_display_entries: 8, 
	        current_page: 0,
	        //回调 
	        callback: pageselectCallback 
	};
	 $("#Pagination").pagination(TotalNum, opt);	 
	 
});
function pageselectCallback(page) { 
    $.ajax({ 
        type: "post", 
        dataType: "json",
        async: false,
        url: "news!query", //请求的url 
        data: { 
        	"gridPara.page": parseInt(page + 1),
        	"gridPara.rows": 10
        }, 
        success: function (data) { 
        	
        	TotalNum = parseInt(data.gridPara.records);
            //使用微软jQuery Templates绑定数据列表,实现了HTML与js分离,使得页面整洁 
        	console.debug(data.resultList);
        	if(data.resultList.length>0){
        		 $("#news_ul").empty();
        	}
           $("#news_ul").append($("#Template").tmpl(data.resultList)); 
           iFrameHeight();
        } 
    });
     
} 
function openNew(str){
	$("#newsContent").empty();
	$("#hiddenresult").css("display","none");
	$("#newsMsg").css("display","block");
	$.ajax({ 
        type: "post", 
        dataType: "json",
        async: false,
        url: "news!queryDetailForJson", //请求的url 
        data: { 
        	"news.id": str
        }, 
        success: function (data) { 
        	$(".newsend-btn").empty();
        	$("#newsContent").append(data.news.content);
        	if(data.newsBefore!=null){
        		$(".newsend-btn").append("<a href='javascript:openNew("+data.newsBefore.id+");' class='t' id='before' >上一条:"+data.newsBefore.title+"</a>");
        		//$("#before").html("上一条:"+data.newsBefore.title);
        	}else{
        		$(".newsend-btn").append("<a href='javascript:scroll(0,0);' class='t' id='before' >上一条:没有更多了</a>");
        		//$("#before").html("");
        	}
        	$(".newsend-btn").append("<a href='' class='btn'>返&nbsp;&nbsp;回</a>");
        	if(data.newsAfter!=null){
        		$(".newsend-btn").append("<a href='javascript:openNew("+data.newsAfter.id+")' class='t' id='after' >下一条:"+data.newsAfter.title+"</a>");
        		//$("#after").html("下一条:"+data.newsAfter.title);
        	}else{
        		$(".newsend-btn").append("<a href='javascript:scroll(0,0);' class='t' id='after' >下一条:没有更多了</a>");
        		//$("#after").html("下一条:没有更多了");
        	}
        } 
    });
	setTimeout(iFrameHeight,500);
	
}
function iFrameHeight() {  
	window.parent.window.iFrameHeight();
}
