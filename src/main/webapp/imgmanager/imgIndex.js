/**
 * 
 */
$(function() {
	$("#gridTable").jqGrid(
			{
				url : "img!query",
				rownumbers : true,
				datatype : "json",
				mtype : "post",
				height: 300,
				autowidth : true,
				colModel : [ {
					name : "id",
					index : "id",
					label : "图片编号",
					hidden : false,
					width : 20,
					sortable : true,
					align : 'center',
					key : true
				}, {
					name : "name",
					index : "name",
					label : "图片名称",
					width :60,
					sortable : true,
					align : 'center'
				}, {
					name : "url",
					index : "url",
					label : "查看",
					width : 180,
					sortable : true,
					align : 'center',
					key : true,
					formatter: function(value, row, index){
						if(value==''||value==null||value==undefined){
							return '';
						} else{
							return "<img style='width:180px;height:100px' src="+value+">";
						}
					}
				},{
					name : "status",
					index : "status",
					label : "状态 ",
					width : 40,
					sortable : true,
					align : 'center',
					key : true
				},{
					name : "process",
					index : "process",
					label : "操作",
					width : 40,
					sortable : false,
					align : 'center'
				} ],
				gridComplete : function() {
					$("#_empty", "#gridTable")
							.addClass("nodrag nodrop");

					var ids = jQuery("#gridTable").jqGrid('getDataIDs');
					for ( var i = 0; i < ids.length; i++) {
						var cl = ids[i];
						update = "<input type='button' value='修改' onclick=\"button_update_onclick('"
								+ cl + "')\"/>&nbsp;";
						jQuery("#gridTable").jqGrid('setRowData',
								ids[i], {
									process : update
								});
					}

				},
				viewrecords : true,
				rowNum : 10,
				rowList : [ 10, 15, 20, 30 ],
				multiselect : true,
				prmNames : {
					page : "gridPara.page", // 表示请求页码的参数名称
					rows : "gridPara.rows", // 表示请求行数的参数名称
					sort : "gridPara.sidx", // 表示用于排序的列名的参数名称
					order : "gridPara.sord", // 表示采用的排序方式的参数名称
					search : "gridPara.search", // 表示是否是搜索请求的参数名称
					nd : "gridPara.nd", // 表示已经发送请求的次数的参数名称

					totalrows : "totalrows" // 表示需从Server得到总共多少行数据的参数名称，参见jqGrid选项中的rowTotal
				},
				jsonReader : {
					root : "resultList",
					total : "gridPara.total",
					records : "gridPara.records",
					repeatitems : false,
					cell : "cell",
					id : "id"
				},
				pager : "#gridPager",
				caption : "信息统计",
				hidegrid : false
				});
				$("#gridTable").jqGrid('navGrid', '#gridPager', {
				edit : false,
				add : false,
				del : false,
				search : false
				});
				$("#consoleDlg").dialog({
				autoOpen : false,
				modal : true,
				resizable : true,
				width : 500,
				height : 300,
				position : "center"
				});

});
button_query_onclick = function() {
	param = $(":input").serializeArray();
	var jsonStr = convertArray(param);
	$("#gridTable").jqGrid("setGridParam", {
		page : 1,
		datatype : "json",
		search : true,
		mtype : "post",
		postData:jsonStr
	}).trigger("reloadGrid");
};
function convertArray(o) {
	var v = {};
	for ( var i in o) {
		if (typeof (v[o[i].name]) == 'undefined')
			v[o[i].name] = o[i].value;
		else
			v[o[i].name] += "," + o[i].value;
	}
	return v;
}
button_reset_onclick  = function() {
	$("input:text").val("");
	$("input:hidden").val("");
};
button_add_onclick = function(){
	window.location.href="imgCreate.jsp";
}
button_delete_onclick = function(){
	var slt = $("#gridTable").jqGrid('getGridParam', 'selarrrow');
	if(slt==null || slt.length<1){
		alert("请选择删除图片！");
		return;
	}
	if(confirm("确认要删除？")){ 
		$.ajax({
			url : "img!delete",
			data : $.param({
				imgsIds : slt
			}, true),
			dataType : "json",
			cache : false,
			type : "post",
			error : function(textStatus, errorThrown) {
				alert("删除图片失败。");
			},
			success : function(data, textStatus) {
				alert("删除图片成功！");
				button_query_onclick();
			}
	
		});
	} 
}

button_update_onclick = function(selectedRowId){
	var sel = $("#gridTable").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
	$("#id").val(sel.id);
	$('#imgForm').attr('action','img!queryDetail');
	$('#imgForm').submit();
}