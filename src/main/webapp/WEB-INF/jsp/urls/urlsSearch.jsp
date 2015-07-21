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

	<div id="tb" style="padding:5px;height:auto">
		<form id="searchForm">
		   	<div>
				名称:<input type="text" name="search.LIKES_name" value=""/>
				URL:<input type="text" name="search.LIKES_url" value=""/>
				分类:<select id="search_type" name="search.EQS_type"></select>
				<input type="button" onclick="search();" value="查询"/><input type="reset" value="清空"/>
			</div>
		</form>         
	</div>

	<div id="editObj" style="display: none;">
		<form id="editForm" method="post">
			<table width="100%" style="padding: 3px">
				<tbody>
					<tr>
						<td width="60px">名称</td>
						<td><input type="text" name="name" value="" /></td>
					</tr>
					<tr>
						<td width="60px">URL</td>
						<td><input type="text" name="url" value="" /></td>
					</tr>
					<tr>
						<td>分类</td>
						<td><select id="type" name="type"></select> </td>
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
	searchInit("${sessionScope.apppath}/urls/search.do");
	$("#search_type").html("<option value='' selected>请选择</option>");
	$("#search_type").append(getSelectList(urls_type_map,""));
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
		toolbar:'#tb',
		pagePosition:'both',
		border:false,
		sortName:'id',
		sortOrder:'desc',
		columns:[[
			{field:'ck',checkbox:true,width:2},
			{field:'id',title:'ID',sortable:true,
				formatter:function(value,row,index){return row.id;} 
			},
			{field:'name',title:'名称',sortable:true,
				formatter:function(value,row,index){return row.name;} 
			},
			{field:'url',title:'URL',sortable:true,
				formatter:function(value,row,index){return row.url;} 
			},
			{field:'type',title:'分类',sortable:true,
				formatter:function(value,row,index){return urls_type_map[row.type];} 
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
				text:'添加',
				iconCls:'icon-add',
				handler:function(){
					newObj("添加URL","${sessionScope.apppath}/urls/save.do", 300, 200);
				}
			},{
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					removeObj("${sessionScope.apppath}/urls/delete.do");
				}
			}
		]
	});
}

function newInitCombobox(obj, data) {
	obj.find("#type").html("<option value='' selected>请选择</option>");
	obj.find("#type").append(getSelectList(urls_type_map,data.menu));
}
</script>
</html>