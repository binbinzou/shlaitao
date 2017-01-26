efImportFile = function(libraryName) {
    document.write('<script type="text/javascript" src="'+libraryName+'"></script>');
}
efImportCSS = function(cssName) {
    document.write('<link href="' + cssName + '" rel="stylesheet" type="text/css"/>');
}
var CONTEXT_PATH;
if ( typeof( CONTEXT_PATH ) == 'undefined' ) 	CONTEXT_PATH="";

//css引用放在JS前面
//efImportCSS(CONTEXT_PATH + "/themes/ui-lightness/jquery-ui.css");
efImportCSS(CONTEXT_PATH + "../themes/ui-lightness/jquery-ui.structure.css");
efImportCSS(CONTEXT_PATH + "../themes/ui-lightness/jquery-ui.theme.css");
efImportCSS(CONTEXT_PATH + "../jqgrid/css/ui.jqgrid.css");
efImportCSS(CONTEXT_PATH + "../css/all_base.css");
//定义引用文件清单
var _ui_include_files = new Array(
		"../js/jquery-1.10.2.js",
		"../js/jquery-ui.js",
		"../js/jquery-migrate-1.2.1.js",	
		"../jqgrid/js/i18n/grid.locale-cn.js",
		"../jqgrid/js/jquery.jqGrid.src.js",
		"../js/validate.js",
		"../js/dialog.js"
);

/*for (_iplat_ui_file in _iplat_ui_include_files) {
	efImportFile(CONTEXT_PATH + _iplat_ui_include_files[_iplat_ui_file]);
}*/

for (var i=0;i<_ui_include_files.length;i++) {
	efImportFile(CONTEXT_PATH + _ui_include_files[i]);
}

//dialog
if (typeof Util == "undefined")
	var Util = {};
//配置信息
Util.config = {
	JSfile : "./js/XYTipsWindow/", //设置JS文件夹路径
	loadingICO : "./js/XYTipsWindow/loading.gif" //默认载入时图片路径
};


