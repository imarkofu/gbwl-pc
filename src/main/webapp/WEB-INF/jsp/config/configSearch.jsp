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
	
	<div id="editObj" style="display: none;">
		<form id="editForm" method="post">
			<input type="hidden" id="id" name="id" value="">
			<table width="100%" style="padding: 3px">
				<tbody>
					<tr>
						<td width="60px">keyword</td>
						<td><input type="text" id="keyword" name="keyword" value="" /></td>
					</tr>
					<tr>
						<td>val</td>
						<td><input type="text" id="val" name="val" value="" /></td>
					</tr>
					<tr>
						<td>description</td>
						<td><input type="text" id="des" name="des" value="" /></td>
					</tr>
				</tbody>
			</table>
			<table border="0" width="100%" id="formOperate">
				<tr>
					<td align="center"><input type="button" id="editSubmit" value="确定" /><input type="reset" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
$(function(){
	searchInit("${sessionScope.apppath}/config/search.do");
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
			{field:'id',title:'ID',sortable:true,
				formatter:function(value,row,index){return row.id;} 
			},
			{field:'keyword',title:'keyword',sortable:false,
				formatter:function(value,row,index){return row.keyword;} 
			},
			{field:'val',title:'val',sortable:false,width:80,
				formatter:function(value,row,index){return row.val;} 
			},
			{field:'des',title:'description',sortable:false,
				formatter:function(value,row,index){return row.des;} 
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
				text:'编辑',
				iconCls:'icon-edit',
				handler:function(){
					editObj("编辑配置信息", "${sessionScope.apppath}/config/view.do?id=", "${sessionScope.apppath}/config/update.do", 300, 180);
				}
			},{
				text:'重启配置',
				iconCls:'icon-reload',
				handler:function(){
					sendPost("${sessionScope.apppath}/config/restartConfig.do", "您确定要重启配置信息吗？");
				}
			}
		]
	});
}
</script>
</html>