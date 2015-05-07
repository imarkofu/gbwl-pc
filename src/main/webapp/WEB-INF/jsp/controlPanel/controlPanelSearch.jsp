<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>控制面板</title>
</head>
<body>
	<div id="main_data" style="height:395px;">
		<table id="main_data_table" width="100%" border="1" cellpadding="0" cellspacing="0">
			<tr>
				<th width="60%" scope="col">等待爬取的贴吧数</th><td>${requestScope.tiebaSize}</td>
			</tr>
			<tr>
				<th scope="col">等待爬取的贴吧详情页面数</th><td>${requestScope.tiebaDetailSize}</td>
			</tr>
			<tr>
				<th scope="col">等待爬取的天涯板块</th><td>${requestScope.tianyaSize}</td>
			</tr>
			<tr>
				<th scope="col">等待爬取的天涯详情页面数</th><td>${requestScope.tianyaDetailSize}</td>
			</tr>
			<tr>
				<th scope="col">等待爬取的中纪委页面数</th><td>${requestScope.jlscSize}</td>
			</tr>
			<tr>
				<th scope="col">重启贴吧</th><td> <input type="button" value="重启" onclick="sendPost('${sessionScope.apppath}/controlPanel/restartTieBa.do')" /> </td>
			</tr>
			<tr>
				<th scope="col">重启天涯</th><td> <input type="button" value="重启" onclick="sendPost('${sessionScope.apppath}/controlPanel/restartTianYa.do')" /> </td>
			</tr>
			<tr>
				<th scope="col">重启中纪委</th><td> <input type="button" value="重启" onclick="sendPost('${sessionScope.apppath}/controlPanel/restartJLSC.do')" /> </td>
			</tr>
			<tr>
				<th scope="col">是否发送异常邮件</th><td>${requestScope.sendEmail==1?"是":"否"}&nbsp;&nbsp;<input type="button" value="改变" onclick="sendPost('${sessionScope.apppath}/controlPanel/changeSendEmail.do')" /></td>
			</tr>
			<tr>
				<th scope="col">是否推送Android</th><td>${requestScope.push==1?"是":"否"}&nbsp;&nbsp;<input type="button" value="改变" onclick="sendPost('${sessionScope.apppath}/controlPanel/changePush.do')" /></td>
			</tr>
			<tr><th scope="col">清除贴吧历史记录</th><td><input type="button" value="清除" onclick="sendPost('${sessionScope.apppath}/controlPanel/clearTieba.do')" /></td></tr>
			<tr><th scope="col">清除天涯历史记录</th><td><input type="button" value="清除" onclick="sendPost('${sessionScope.apppath}/controlPanel/clearTianya.do')" /></td></tr>
			<tr><th scope="col">清除中纪委历史记录</th><td><input type="button" value="清除" onclick="sendPost('${sessionScope.apppath}/controlPanel/clearJLSC.do')" /></td></tr>
		</table>
	</div>
</body>
<script type="text/javascript">
function sendPost(action) {
	$.ajax({
		type: "post",
		dataType: "json",
        url: action,
        data:{},
        contentType: 'application/x-www-form-urlencoded; charset=utf-8',
        success:function(data) {
        	if (data!=undefined) {
        		show_my_dmessager(data.msg);
        		if (data.result==true) {
        			setTimeout(function(){
        				location.reload();
        			}, 1000);
        		}
        	} else {
        		show_my_dmessager("网络连接错误");
        	}
        }
	});
}

</script>
</html>