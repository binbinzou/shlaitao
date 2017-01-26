function tips(msg){
	Util.Dialog({
		title: "提示",
		width: 250,
		height: 100,
		showbg : true,
		content : "text:<div style='padding:8px 15px'>"+msg+"</div>",
		yesBtn: ["关闭", function(){
			return true;
		}]
	});
}
function tipsWithBack(msg){
	Util.Dialog({
		title: "提示",
		width: 250,
		height: 100,
		showbg : true,
		content : "text:<div style='padding:8px 15px'>"+msg+"</div>",
		yesBtn: ["关闭", function(){
			window.history.go(-1);
		}]
	});
}
function confirmWithCallback(msg, callback_yes){
	Util.Dialog({
		title: "提示",
		width: 250,
		height: 100,
		showbg : true,
		content : "text:<div style='padding:8px 15px'>"+msg+"</div>",
		yesBtn: ["确定", function(){
			callback_yes();
		}],
		noBtn: ["取消", function(){
			return true;
		}]
	});
}
function loadShow(second){
	Util.Dialog({
		title: "读取中",
		boxID : "loadingBox",
		fixed : true,
		height: 30,
		content : "text:<div class='loadshow'>读取中</div>",
		showtitle : "remove",
		showbg : true,
		time : second,
		border : {opacity:"0"},
		bordercolor : "#666"		
	});
	return false;
}

function SysnShow(second){
	Util.Dialog({
		title: "读取中",
		boxID : "sysnBox",
		fixed : true,
		height: 30,
		content : "text:<div class='loadshow'>同步中</div>",
		showtitle : "remove",
		showbg : true,
		time : second,
		border : {opacity:"0"},
		bordercolor : "#666"		
	});
	return false;
}
function sendShow(second){
	Util.Dialog({
		title: "发送中",
		boxID : "loadingBox",
		fixed : true,
		height: 33,
		content : "text:<div class='loadshow'>发送中</div>",
		showtitle : "remove",
		showbg : true,
		time : second,
		border : {opacity:"0"},
		bordercolor : "#666"
	});
	return false;
}
function closeBox(id){
	$("#"+id).parent().remove();
}