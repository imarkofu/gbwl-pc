<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理首页</title>
<style>
.menuStyle {text-align:center;font-size:14px;padding:10px 0 0 0;overflow:hidden;}
</style>
<link rel="stylesheet" type="text/css" href="${sessionScope.apppath}/css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${sessionScope.apppath}/css/themes/icon.css">
<script type="text/javascript" src="${sessionScope.apppath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${sessionScope.apppath}/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${sessionScope.apppath}/js/index.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',split:true" style="text-align:center;background:#666;direction:ltr;font-size:12px;height:80px;">
		<div style="text-align:left;margin:0 auto;width:960px;padding:10px 0;">
			<table cellpadding="0" cellspacing="0" style="width:100%;">
				<tr>
					<td rowspan="2" style="width:20px;"></td>
					<td style="height:52px;">
						<div style="color:#fff;font-size:22px;font-weight:bold;">
							<a style="color:#fff;font-size:22px;font-weight:bold;text-decoration:none;cursor:pointer;">Spider管理平台</a>
						</div>
						<div style="color:#fff">
							<a style="color:#fff;text-decoration:none;cursor:pointer;">easyui helps you build your web pages easily!</a>
						</div>
					</td>
					<td style="padding-right:5px;text-align:right;vertical-align:bottom;"></td>
				</tr>
			</table>
		</div>
	</div>
	<div data-options="region:'south',split:true"
		style="height: 60px; text-align: center; padding: 18px;">
		<div>Copyright © 2015-2016 Imarkofu</div>
	</div>
	<div data-options="region:'west',title:'导航栏',split:true" style="width:160px;">
		<div class="easyui-accordion" fit="true" border="false" animate="false">
			<div title="数据管理" style="overflow:hidden;">
				<div class="menuStyle">
					<a href="javascript:void(0);" onclick="addTabs('贴吧数据', '${sessionScope.apppath}/tieba/preSearch.do')">贴吧数据</a>
				</div>
				<div class="menuStyle">
					<a href="javascript:void(0);" onclick="addTabs('天涯数据', '${sessionScope.apppath}/tianya/preSearch.do')">天涯数据</a>
				</div>
				<div class="menuStyle">
					<a href="javascript:void(0);" onclick="addTabs('全国纪委数据', '${sessionScope.apppath}/jlsc/preSearch.do')">全国纪委数据</a>
				</div>
			</div>
			<div title="系统管理" style="overflow:hidden;">
				<div class="menuStyle">
					<a href="javascript:void(0);" onclick="addTabs('关键词管理', '${sessionScope.apppath}/keywords/preSearch.do')">关键词管理</a>
				</div>
				<div class="menuStyle">
					<a href="javascript:void(0);" onclick="addTabs('URL管理', '${sessionScope.apppath}/urls/preSearch.do')">URL管理</a>
				</div>
				<div class="menuStyle">
					<a href="javascript:void(0);" onclick="addTabs('系统配置', '${sessionScope.apppath}/config/preSearch.do')">系统配置</a>
				</div>
			</div>
		</div>
	</div>
	<div data-options="region:'center',title:''" style="padding:5px;background:#eee;">
		<div id="main_tabs" class="easyui-tabs" fit="true" border="false" style="width:100%;height:100%;">
    		<div title="主页" style="padding:20px;overflow:hidden;font-size:14px;">
    			<p>欢迎访问Spider管理平台</p>
    			<table id="main_table" style="float:left;widows:500px;text-align:center;" border="1px" cellspacing="0" cellpadding="0">
    				
    			</table>
    		</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	var d = function(){
		$.ajax({
			type: "post",
			dataType: "json",
			url: '${sessionScope.apppath}/manage/index',
			data: {},
			contentType: 'application/x-www-form-urlencoded; charset=utf-8', 
			success: function(data){
				if(data!=undefined){
					if (data.result==true){
						var tmp = '<tr><th scope="col" width="50%">投放域名数</th><td width="50%">'+data.domainSize+'</td></tr>' +
									'<tr><th scope="col">期望触发PV的物料数</th><td>'+data.expectPageViewSize+'</td></tr>'+
									'<tr><th scope="col">实际触发PV的物料数</th><td>'+data.realPageViewSize+'</td></tr>'+
									'<tr><th scope="col">每日触发PV的物料数</th><td>'+data.dayPageViewSize+'</td></tr>'+
									'<tr><th scope="col">过期时间的物料数</th><td>'+data.expiredTimeSize+'</td></tr>'+
									'<tr><th scope="col">URL物料数</th><td>'+data.urlSize+'</td></tr>'+
									'<tr><th scope="col">标签物料数</th><td>'+data.tagSize+'</td></tr>'+
									'<tr><th scope="col">是否接收广播推送</th><td>'+data.receiveBroadcast+'&nbsp;<input type="button" value="改变状态" onclick="pauseReceiveBroadcast(7)"></td></tr>'+
									'<tr><th scope="col">是否正在备份数据</th><td>'+data.restoreData+'</td></tr>'+
									'<tr><th scope="col">是否回Call默认物料</th><td>'+data.isEveryResponse+'&nbsp;<input type="button" value="改变状态" onclick="pauseReceiveBroadcast(8)"></td></tr>'+
									'<tr><th scope="col">今日收到广播数</th><td>'+data.dayReceiveBroadcastCount+'</td></tr>'+
									'<tr><th scope="col">今日响应的广播数</th><td>'+data.dayResponseBroadcastCount+'</td></tr>'+
									'<tr><th scope="col">黑名单域名数</th><td>'+data.blackDomainSize+'</td></tr>';
						$("#main_table").html(tmp);
					} else {
						$.messager.show({
                			title:'系统提示',
                			msg:'请求错误',
                			timeout:2000,
                			showType:'slide'
                		});
					}
				} else {
					$.messager.show({
            			title:'系统提示',
            			msg:'未知错误',
            			timeout:2000,
            			showType:'slide'
            		});
				}
			}
		});
	}
	//setInterval(d, 5000);
});
var i = 1;
function addTabs(title, action) {
	var mainTabs = $("#main_tabs");
	if (mainTabs.tabs('exists', title)) {
		mainTabs.tabs('select', title);
	} else {
		var content = '<iframe scrolling="auto" frameborder="0" src="'+action+'" style="width:100%;height:100%"></iframe>';
        mainTabs.tabs('add',{
        	id:i,
            title:title,
            content:content,
            closable:true,
            cache:false,
            border:false,
            fit:true
        });
        $("#"+i).css("overflow","hidden"); 
        i++;
	}
}
function pauseReceiveBroadcast(type) {
	$.messager.confirm('提示','确定要删除改变状态吗?',function(result){
		if (result) {
			$.ajax({
				type: "post",
				dataType: "json",
				url: '${sessionScope.apppath}/manage/index',
				data: {type:type},
				contentType: 'application/x-www-form-urlencoded; charset=utf-8', 
				success: function(data){
					if(data!=undefined){
						$.messager.show({
                			title:'系统提示',
                			msg:data.msg,
                			timeout:2000,
                			showType:'slide'
                		});
					} else {
						$.messager.show({
	            			title:'系统提示',
	            			msg:'未知错误',
	            			timeout:2000,
	            			showType:'slide'
	            		});
					}
				}
			});
		}
	});
}
</script>
</html>