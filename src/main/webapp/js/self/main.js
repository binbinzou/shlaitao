/**
 * 
 */
$(function() {
	//iFrameHeight();
});

function js_menu(id){
	console.debug(id);
	var js_index=0;
	for(var i=1;i<=3;i++){
		var obj_menu=$("#menu_" + i);//window.document.getElementById("menu_" + i);
		var obj=$("#cc_menu_" + i);//window.document.getElementById("cc_menu_" + i);
		if(i==Number(id)){
			obj.css('display',''); 
			//obj.style.display="";
		}
		else{
			obj.css('display','none'); 
			// obj.style.display="none";
		}
	}
}

function iFrameHeight() {   
	var ifm= document.getElementById("iframepage");   
	var subWeb = document.frames ? document.frames["iframepage"].document : ifm.contentDocument;   
	if(ifm != null && subWeb != null) {
		var subTop = 40;
		
	   var other_box = subWeb.getElementById("other_box");
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
	   }
	   
		var main = subWeb.getElementById("main");
		var divc = $(main).children();
		var size = divc.length;
		var height = 0;
		for(var i = 0 ;i < size ;i++){
			height+=divc[i].offsetHeight;
		}
	    ifm.height = height-subTop;
	   
	  // ifm.width = subWeb.getElementById("main").offsetWidth;
	    var big_box = $("#big_box");
	    var header = $("#header")[0].offsetHeight;
	    var footer = $("#footer")[0].offsetHeight;
		var height2 = height+header+footer;
		$(big_box).css("height",(height2-30)+"px");
	}
}
function change_page(id,id2){
	$("#iframepage").attr("src",id);
	document.getElementById('body').scrollIntoView();
	for(var i=1;i<7;i++){
		$("#tab"+i).css("color","#000");
	}
	$("#"+id2).css("color","#00A2E6");
	iFrameHeight();
}
function back_page(id){
	$("#iframepage", parent.document).attr("src",id);
}
function returnFalse(){
	return false;
}
$(document).bind("contextmenu",function(e){
    if (!jQuery_isTagName(e, ['div', 'TEXTAREA'])) {
        e.preventDefault();
        return false;
    }
    return true;
});