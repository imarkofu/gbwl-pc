<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>天涯管理</title>
</head>
<body>
	<div id="tb" style="padding-left: 0px 20x 0px 20px;">
		<form id="searchForm">
			帖子标题:<input type="text" id="search_pTitle" name="search.LIKES_pTitle" value=""/>
			帖子作者:<input type="text" id="search_pAuthor" name="search.LIKES_pAuthor" value=""/>
			板块:<input type="text" id="search_pName" name="search.LIKES_pName" value=""/>
			是否异常:<select id="search_isMatch" name="search.EQS_isMatch">
				<option value="">请选择</option><option value="1">是</option><option value="0">否</option></select>
			<input type="button" onclick="searchInit('${sessionScope.apppath}/ty/search.do');" value="查询"/>
			<input type="button" onclick="resetForm()" value="清空"/>
			<input type="hidden" id="page" name="page" value="1" />
			<input type="hidden" id="rows" name="rows" value="10" />
			<input type="hidden" id="sort" name="sort" value="id" />
			<input type="hidden" id="order" name="order" value="desc" />			
		</form>
	</div>
	<div id="main_data" style="height:395px;">
		<table id="main_data_table" width="100%" border="1" cellpadding="0" cellspacing="0"></table>
	</div>
	<div class="pagebox" id="pagebox"></div>
	<div id="delObj" style="display:none;"></div>
</body>
<script type="text/javascript">
$(function(){
	searchInit("${sessionScope.apppath}/ty/search.do");
	setInterval(function(){
		if (current_index === 2)
			searchInit("${sessionScope.apppath}/ty/search.do");
	}, 10000);
});

function searchInit(action) {
	var page = parseInt($("#page").val());
	var rows = parseInt($("#rows").val());
	
	$.ajax({
		type: "post",
		dataType: "json",
        url: action,
        data:$('#searchForm').serializeArray(),
        contentType: 'application/x-www-form-urlencoded; charset=utf-8',
        success:function(data) {
        	//插入表头
        	$("#main_data_table").html(
        			'<tr><th width="2%" scope="col">'+
        			'<input type="checkbox" name="ids" id="ids" onclick="checkAllOrNot()" class="checkall" /><label for="checkbox"></label></th>' + 
        			'<th width="6%" scope="col">序号</th>' + 
        			'<th width="46%" scope="col">标题</th>' + 
        			'<th width="15%" scope="col">作者</th>' + 
        			'<th width="14%" scope="col">最后更新时间</th>' + 
        			'<th width="7%" scope="col">来源</th></tr>');
        	//插入数据部分 
        	var tmp = '';
        	for (var i = 0; i < data.rows.length; i ++) {
        		var row = data.rows[i];
        		var ct = formatDate(row.createTime);
        		var lt = formatDate(row.lastLoginTime);
        		tmp += ('<tr><td scope="col">'+
        				'<input type="checkbox" name="keyIds" id="keyIds" value="'+row.id+'" /></td>'+
        				'<td scope="col">'+row.id+'</td>'+
        				'<td scope="col"><a href="http://bbs.tianya.cn/'+row.pId+'.shtml" target="_blank" title="'+row.pTitle+'">'+(row.pTitle.length>34?(row.pTitle.substring(0, 34)+"..."):row.pTitle)+'</a></td>' +
        				'<td scope="col">'+(row.pAuthor)+'</td>' +
        				'<td scope="col">'+(formatDate(row.pReplyTime, "yyyy-mm-dd hh:mm"))+'</td>' +
        				'<td scope="col">'+(row.pName)+'</td>' +
        				'</tr>');
        	}
        	$("#main_data_table").append(tmp);
        	//区别奇偶行
        	$("#main_data_table tbody tr:odd").addClass("odd");
        	$("#main_data_table tbody tr:even").addClass("even")
        	//分页相关 
        	tmp ='<p class="r" style="float:left;">'+
        	'<input type="button" value="删除" onclick="removeObj(\'天涯帖子\', \'${sessionScope.apppath}/ty/delete.do\', \'${sessionScope.apppath}/ty/search.do\')" class="btn4" /></p>';
        	
        	tmp += '<p class="r" style="float: right;">';
        	var pageCount = Math.ceil(data.total/rows);
        	if (page > 1) { tmp += '<a href="javascript:submitPage1(\''+action+'\', 1)" title="">首页</a> <a href="javascript:submitPage1(\''+action+'\','+(page-1)+')">上页</a> ';
        	} else { tmp += '<span>首页</span> <span>上页</span> '; }
        	
        	if (page < pageCount) { tmp += ('<a href="javascript:submitPage1(\''+action+'\','+(page+1)+')">下页</a> <a href="javascript:submitPage1(\''+action+'\', '+pageCount+')">末页</a> ');
        	} else { tmp += '<span>下页</span> <span>末页</span> '; }
        	
        	tmp += '每页'+rows+'条记录,当前是第'+page+'页/共'+pageCount+'页 '+data.total+
        			'条记录 跳转到 <input type="text" class="page" name="searchPage" id="searchPage" style="width: 20px;" /> 页 '+
        			'<input type="button" value="确定" class="btn4" onclick="submitPage(\''+action+'\', '+pageCount+')" />';
        	tmp += '</p>';
        	$("#pagebox").html(tmp);
        }
	});
}

function resetForm() {
	$("#search_pTitle").val("");
	$("#search_pAuthor").val("");
	$("#search_pName").val("");
	$("#search_isMatch").val("");
}
</script>
</html>