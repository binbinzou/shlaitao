$(function() {
	$.ajax({ 
        type: "post", 
        dataType: "json",
        async: false,
        url: "img!query", //请求的url 
        data: { 
        	"gridPara.page": 1,
        	"gridPara.rows": 5
        }, 
        success: function (data) { 
        	var size = data.resultList.length;
        	if(size>0){
        		$("#slider").empty();
        	}
        	for(var i=0;i<size;i++){
        		$("#slider").append('<img src='+data.resultList[i].url+' alt="" title=""  >');
        	}
        	var left1 = $("#gsjj_content")[0].offsetHeight;
        	var left2 = $("#gsjj_title")[0].offsetHeight;
        	$("#box1_left").css("height",(left1+left2)+"px");
        	$("#box1").css("height",(left1+left2)+"px");
           iFrameHeight();
           
        } 
    });
	 $('#slider').nivoSlider({effect: 'slideInLeft',pauseTime: 5000,controlNav:true});
	 var speed=30//速度数值越大速度越慢
		var colee_left2=document.getElementById("colee_left2");
		var colee_left1=document.getElementById("colee_left1");
		var colee_left=document.getElementById("colee_left");
		colee_left2.innerHTML=colee_left1.innerHTML;
		function Marquee3(){
		if(colee_left2.offsetWidth-colee_left.scrollLeft<=0)//offsetWidth 是对象的可见宽度
		colee_left.scrollLeft-=colee_left1.offsetWidth;//scrollWidth 是对象的实际内容的宽，不包边线宽度
		else{
		colee_left.scrollLeft++;
		}
		}
		var MyMar3=setInterval(Marquee3,speed)
		colee_left.onmouseover=function() {clearInterval(MyMar3)}
		colee_left.onmouseout=function() {MyMar3=setInterval(Marquee3,speed)}
});
function change_page(j,id){
	$("#iframepage",parent.document).attr("src",id);
	parent.document.getElementById('body').scrollIntoView();
	for(var i=1;i<7;i++){
		$("#tab"+i,parent.document).css("color","#000");
	}
	$("#tab"+j,parent.document).css("color","#00A2E6");
}
function back_page(id){
	$("#iframepage", parent.document).attr("src",id);
}