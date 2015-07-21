var isDelete_map={"0":"未删除","1":"已删除"};
var keywords_type_map={"1":"贴吧关键词", "2":"天涯关键词"}

function getSelectList(map, selectedId) {
	var s = "";
	$.each(map, function(key, val) {
		if (val == undefined || key == undefined) {

		} else {
			if (selectedId != undefined && selectedId == key)
				s+="<option value='"+key+"' selected>"+val+"</option>";
			else
				s+="<option value='"+key+"'>"+val+"</option>";
		}
	});
	return s;
}

function noPermis(path, method) {
	if (parent.permissionList != undefined && parent.permissionList.length > 0) {
		for (i = 0; i < parent.permissionList.length; i++) {
			var obj = eval('(' + parent.permissionList[i] + ')');
			if (obj != undefined && obj.path != undefined
					&& obj.method != undefined && path == obj.path
					&& method == obj.method)
				return "";
		}
	}
	return "disabled=\"disabled\"";
}

function booleanPermis(path, method) {
	if (parent.permissionList != undefined && parent.permissionList.length > 0) {
		for (i = 0; i < parent.permissionList.length; i++) {
			var obj = eval('(' + parent.permissionList[i] + ')');
			if (obj != undefined && obj.path != undefined
					&& obj.method != undefined && path == obj.path
					&& method == obj.method)
				return true;
		}
	}
	return false;
}

// 首页、上页、下页、末页
function submitPage1(action, page) {
	$("#page").val(page);
	searchInit(action);
}

// 手动输入页码
function submitPage(action, pageCount) {
	var page = $("#searchPage").val();
	if (page == '') {
		return;
	}
	if (isNaN(page)) {
		show_my_dmessager("指定的范围无效");
		return;
	}
	if (parseInt(page) > parseInt(pageCount) || parseInt(page) < 1) {
		show_my_dmessager("指定的范围无效")
		return;
	}
	submitPage1(action, page);
}

function newObj(title, action, width, height) {
	if(width==undefined||width==''){
		width=500;
	}
	if(height==undefined||height==''){
		height=350;
	}
	$('#editObj').show();
	$("#editObj").dialog({
		title:'添加'+name,
		top:100,
		width:width,
		height:height,
		cache: false,
		closed: true,
	});
	$('#editForm')[0].reset();
	if ($.isFunction(window.newInitCombobox))
		newInitCombobox($('#editForm'),"");
	$("#editSubmit").unbind();
	$("#editSubmit").click(function(){
		if ($.isFunction(window.validation)){
			var rs=validation($('#editForm'));
			if(rs==undefined||rs==false){
				return ;
			}
		}
		$.ajax({
            type: "post",
            dataType: "json",
            url: action,
            data: $('#editForm').serializeArray(),
            contentType: 'application/x-www-form-urlencoded; charset=utf-8', 
            success: function(data){
            	if(data!=undefined&&data.result==true){
            		$('#editObj').dialog('close');
            		$('#dg').datagrid('reload');
            		$.messager.show({
            			title:'添加'+name,
            			msg:'添加成功！',
            			timeout:5000,
            			showType:'slide'
            		});
            	}else{
            		returnError(data);
            	}
            }
		});
	});
	$('#editObj').dialog('open');
}

//function newObj(title, action, width, height) {
//	if (width == undefined || width == '') {
//		width = 400;
//	}
//	if (height == undefined || height == '') {
//		height = 200;
//	}
//	$("#editObj").show();
//	$('#editObj').prop("title", '添加'+title);
//	var dialog = $("#editObj").dialog({
//			autoOpen : false,
//			height : height,
//			width : width,
//			modal : false,
//			resizable : false,
//			buttons : {
//				"确定" : function() {
//					if ($.isFunction(window.validation)) {
//						var rs = validation($('#editForm'));
//						if (rs == undefined || rs == false) {
//							return;
//						}
//					}
//					// ajax提交
//					$.ajax({
//						type : "post",
//						dataType : "json",
//						url : action,
//						data : $('#editForm').serializeArray(),
//						contentType : 'application/x-www-form-urlencoded; charset=utf-8',
//						success : function(data) {
//							if (data != undefined && data.result == true) {
//								dialog.dialog("close");
//								show_my_messager("添加" + title, "添加成功");
//								searchInit(url);
//							} else {
//								returnError(data);
//							}
//						}
//					});
//				},
//				"取消" : function() {
//					dialog.dialog("close");
//				}
//			},
//			close : function() {
//				$(this).dialog("destroy");
//				$(this).hide();
//			}
//		}).dialog("open");
//	$('#editForm')[0].reset();
//	if ($.isFunction(window.newInitCombobox))
//		newInitCombobox($('#editForm'), "");
//	//dialog.dialog("open");	//解决在初始化时出现问题，不能关闭dialog的问题
//}

function editObj(title, viewAction, updateAction, url, width, height) {
	if (width == undefined || width == '') {
		width = 400;
	}
	if (height == undefined || height == '') {
		height = 200;
	}
	var count = 0;
	var id;
	$('input[name="keyIds"]:checked').each(function(i, d) {
		count = count + 1;
		id = $(this).val();
	});
	if (count <= 0 || count > 1) {
		show_my_messager("提示", "请选择一条记录进行编辑");
		return;
	}
	viewAction = viewAction + id;
	$('#editObj').show();
	$('#editObj').prop("title", title);
	var dialog = $("#editObj").dialog({
			autoOpen : false,
			height : height,
			width : width,
			modal : false,
			resizable : false,
			buttons : {
				"确定" : function() {
					if ($.isFunction(window.validation)) {
						var rs = validation($('#editForm'));
						if (rs == undefined || rs == false) {
							return;
						}
					}
					// ajax提交
					$.ajax({
						type : "post",
						dataType : "json",
						url : updateAction,
						data : $('#editForm').serializeArray(),
						contentType : 'application/x-www-form-urlencoded; charset=utf-8',
						success : function(data) {
							if (data != undefined
									&& data.result == true) {
								dialog.dialog("close");
								show_my_messager("修改"
										+ title, "修改成功");
								searchInit(url);
								// location.reload();
							} else {
								returnError(data);
							}
						}
					});
				},
				"取消" : function() {
					dialog.dialog("close");
				}
			},
			close : function() {
				$(this).dialog("destroy");
				$(this).hide();
			}
		});
	$.get(viewAction, function(data) {
		if (data != undefined && data.result != undefined && data.result) {
			var obj = $("#editForm");
			$.each(data.row, function(id, ival) {
				if (obj.find("#" + id) != undefined)
					obj.find("#" + id).val(ival);
			});
			if ($.isFunction(window.editInitCombobox))
				editInitCombobox(obj, data.row);
			dialog.dialog("open");
		} else {
			returnError(data);
		}
	});
}

function removeObj(action){
	var rows = $('#dg').datagrid('getSelections');
	var size=0;
    $.each(rows,function(i,n){
    	size++;
    });
    if(size<=0){
    	$.messager.alert('提示','未选择所需删除的项！','info');
    	return;
    }
	$.messager.confirm('提示','确定要删除 '+size+' 项信息吗?',function(result){
		if (result){
	        var ps = "";
	        $.each(rows,function(i,n){
	        	if(i==0) 
	        		ps += "?id="+n.id;
	        	else
	        		ps += "&id="+n.id;
	        });
	        $.post(action+ps,function(data){
	        	if(data!=undefined&&data.result==true){
			       	$('#dg').datagrid('reload'); 
	        		$.messager.show({
	            		title:'提示信息：',
	            		msg:size+' 项信息删除成功！',
	            		timeout:5000,
	            		showType:'slide'
	        		});
	        	}else{
	        		returnError(data);
	        	}
	        });
	    }
	});
}

//function removeObj(title, action, url, append) {
//	var count = 0;
//	var id = "";
//	$('input[name="keyIds"]:checked').each(function(i, d) {
//		count++;
//		if (i == 0)
//			id = ("?id=" + $(this).val());
//		else
//			id += ("&id=" + $(this).val());
//	});
//	if (count <= 0) {
//		show_my_messager("提示", "未选择需要删除的项");
//		return;
//	}
//	$("#delObj").show();
//	if (append == undefined || append == '')
//		$("#delObj").prop("title", "删除" + title + "提示").html("您确定要删除" + title + "的" + count + "项信息吗？");
//	else 
//		$("#delObj").prop("title", "删除" + title + "提示").html("您确定要删除" + title + "的" + count + "项信息吗？"+append);
//	var dialog = $("#delObj").dialog({
//		resizable : false,
//		height : 150,
//		modal : true,
//		buttons : {
//			"确定" : function() {
//				$.post(action + id, function(data) {
//					if (data != undefined && data.result == true) {
//						dialog.dialog("close");
//						show_my_messager("删除" + title, count + "项删除成功");
//						// searchInit(url, page, rows, sort, order);
//						searchInit(url);
//					} else {
//						returnError(data);
//					}
//				});
//			},
//			"取消" : function() {
//				$(this).dialog("close");
//			}
//		},
//		close : function() {
//			$(this).dialog("destroy");
//			$(this).hide();
//		}
//	});
//}

function returnError(data){
	if(data!=undefined&&data.value!=undefined&&data.value=='nologin'){
		window.location.reload();
	}else{
		if(data==undefined||data.msg==undefined)
			$.messager.alert('提示','未知错误！','info');
		else
			$.messager.alert('提示',data.msg,'info');
	}
}

//function returnError(data) {
//	if (data != undefined && data.value != undefined && data.value == 'nologin') {
//		window.location.reload();
//	} else if (data == undefined || data.msg == undefined) {
//		show_my_messager('提示', '未知错误！');
//	} else {
//		show_my_messager('提示', data.msg);
//	}
//}

function rechargeCheck(amount) {
	if (amount == '')
		return false;
	var re = /^(([1-9]\d{0,4})(\.\d{1,2})?)$|(0\.0?([1-9]))$|(0\.([1-9])\d?)$/;
	return re.test(amount);
}


// editObjWithLimit
// 执行一个动作
// @param title 弹出确认窗口名字；
// @param action 所指向的action；
// @param url 返回页面；
// @param event 事件名称；
// @param limit 长度限制；
// 自动传入keyId；

function editObjWithLimit(title, action, url, limit, event) {
	var count = 0;
	var id = "";
	$('input[name="keyIds"]:checked').each(function(i, d) {
		count++;
		if (i == 0)
			id = ("?id=" + $(this).val());
		else
			id += ("&id=" + $(this).val());
	});
	if (count <= 0) {
		show_my_messager("提示", "未选择需要" + title + "的项");
		return;
	}

	if (limit != undefined && limit != 0 && limit != "") {
		if (count > limit) {
			show_my_messager("提示", "选择不能超过" + limit + "项");
			return;
		}
	}
	$("#showObj").show();
	$("#showObj").prop("title", title + "提示").html("您确定要" + title + "的" + count + "项吗？");
	var dialog = $("#showObj").dialog({
		resizable : false,
		height : 140,
		modal : true,
		buttons : {
			"确定" : function() {
				$.post(action + id, function(data) {
					if (data != undefined && data.result == true) {
						show_my_messager(title, title + count + "项计划完成");
						// searchInit(url, page, rows, sort, order);
						searchInit(url);
					} else {
						returnError(data);
					}
				});
				dialog.dialog("close");
			},
			"取消" : function() {
				$(this).dialog("close");
			}
		},
		close : function() {
			$(this).dialog("destroy");
			$(this).hide();
		}
	});

}

//对Date的扩展，将 Date 转化为指定格式的String 
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
//例子： 
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function(fmt) {
	var o = { 
		"M+" : this.getMonth()+1,                 //月份 
		"d+" : this.getDate(),                    //日 
		"h+" : this.getHours(),                   //小时 
		"m+" : this.getMinutes(),                 //分 
		"s+" : this.getSeconds(),                 //秒 
		"q+" : Math.floor((this.getMonth()+3)/3), //季度 
		"S"  : this.getMilliseconds()             //毫秒 
	}; 
	if(/(y+)/.test(fmt)) 
		fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	for(var k in o) 
		if(new RegExp("("+ k +")").test(fmt)) 
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
	return fmt; 
}

function formatDate (date, format){
	if(format==undefined||format!=""||format==null)
		format="yyyy-MM-dd hh:mm:ss"
	var newdate= new Date(date).Format(format);
	return newdate;
}

/**重组定向包条件类型中的文字，根据库中保存多个数字得到对应文字返回.（需要 orientationPack_conditionType ）
* 例子传入（1,2） 返回（关键词，url）
* @constr 传入文字
**/
function dxbConditionTypeFix(constr){
	var dxbscnames="";
	var dxbnamefull="";
	if( typeof(constr) != "undefined" && constr!=null){
		if(constr.replace(/(^\s*)|(\s*$)/g,'').length > 1){
			dxbscnames=constr.split(",");
			for(var o = 0 ; o < dxbscnames.length ;o++){
				if(typeof(dxbscnames[o]) != "undefined" && dxbscnames[o] != "null" && dxbscnames[o] != ""){
					if(dxbnamefull==""){
						dxbnamefull +=orientationPack_conditionType[dxbscnames[o]];
					}else{
						dxbnamefull +=","+orientationPack_conditionType[dxbscnames[o]];
					}
				}
			}
		}else if (constr.replace(/(^\s*)|(\s*$)/g,'').length < 0){
			dxbnamefull = null;
		}else{
			dxbnamefull = orientationPack_conditionType[constr];
		}
		
	}
	return dxbnamefull;
}
