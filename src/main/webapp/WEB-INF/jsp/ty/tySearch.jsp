<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="${sessionScope.apppath}/css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${sessionScope.apppath}/css/themes/icon.css">
<script type="text/javascript" src="${sessionScope.apppath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${sessionScope.apppath}/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${sessionScope.apppath}/js/index.js"></script>
<script type="text/javascript" src="${sessionScope.apppath}/js/tools.js"></script>
</head>
<body>
	<table id="dg"></table>
</body>
<script type="text/javascript">
$(function(){
	searchInit("${sessionScope.apppath}/tianya/search.do");
});
function searchInit(action) {
	$("#dg").datagrid({
		method:'POST',
		iconCls:'icon-edit',
		singleSelect:false,
		fitColumns: true,
		fit: true,
		striped: true,
		collapsible:false,
		url:action,
		queryParams:{},
		pagination:true,
		rownumbers:true,
		pageSize:20,
		pagePosition:'both',
		border:false,
		sortName:'id',
		sortOrder:'desc',
		columns:[[
			{field:'ck',checkbox:true,width:2},
			{field:'pTitle',title:'标题',sortable:false,
				formatter:function(value,row,index){return '<a href="http://bbs.tianya.cn/'+row.pId+'.shtml" target="_black">'+row.pTitle+'</a>';} 
			},
			{field:'pAuthor',title:'发布时间',sortable:false,
				formatter:function(value,row,index){return row.pAuthor;} 
			},
			{field:'pReplyTime',title:'发布时间',sortable:false,
				formatter:function(value,row,index){return formatDate(row.pReplyTime);} 
			},
			{field:'pName',title:'来源',sortable:false,
				formatter:function(value,row,index){return row.pName;} 
			},
			{field:'Confirmation',title:'操作',width:100,sortable:false,
				formatter:function(value,row,index){
					return '';
				}
			}
		]],
		loadFilter:function(data){
			
			return data;
		},
		onLoadSuccess:function(){
			$('#dg').datagrid('clearSelections'); 
		},
		onClickRow:function (rowIndex, rowData){
			//onClickRowBySingle(rowIndex,rowData);
		}
	});
	var pager = $('#dg').datagrid('getPager');
	pager.pagination({
		pageSize: 20,
		pageList: [5,10,15,20,50,100],
		beforePageText: '第',
		afterPageText: '页    共 {pages} 页',  
		displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		buttons:[
			{
				text:'清空数据',
				iconCls:'icon-clear',
				handler:function(){
					sendPost('${sessionScope.apppath}/controlPanel/clearTianya.do', '您确定要清空天涯的数据吗？');
				}
			}
		]
	});
}
</script>
</html>