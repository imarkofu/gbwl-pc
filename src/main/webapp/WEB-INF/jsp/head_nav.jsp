<%@ page language="java" contentType="text/html; charset=UTF-8"
	isErrorPage="true" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- x=[['','',''],['','',''].['','','']] -->
<div id="header" class="png_bg">
	<div id="head_wrap" class="container_12">
		<div id="logo" class="grid_4">
			<h1>
				<a style="text-decoration: none; color: #F1F1F1;" href="${sessionScope.apppath}/index.do">爬虫</a>
				<span>管理平台</span>
			</h1>
		</div>
		<!-- end logo -->
		<!-- start control panel -->
		<div id="controlpanel" class="grid_8">
			<ul>
				<%-- <li><p>欢 迎 <strong id="managername">${sessionScope.session_user.account}</strong></p></li>
				<li><a onclick="modifyPassword('${sessionScope.apppath}/manager/modify.do')" class="last" style="cursor:pointer;" id="outdoor"> 修改密码 </a></li>
				<li><a onclick="logoutConfirm('${sessionScope.apppath}/logout.do')" class="last" style="cursor:pointer;" id="outdoor"> 退出 </a></li> --%>
			</ul>
		</div>
		<!-- end control panel -->
		<!-- start navigation -->
		<div id="navigation" class="grid_12">
			<ul>
				<li><a style="cursor:pointer;">贴吧管理</a></li>
				<li><a style="cursor:pointer;">天涯管理</a></li>
				<li><a style="cursor:pointer;">控制面板</a></li>
			</ul>
		</div>
		<!-- end navigation -->
	</div>
	<!-- end headwarp  -->
</div>
<!-- end header -->
<!-- staqrt subnav -->
<div id="sub_nav">
	<div id="subnav_wrap" class="container_12">
		<!-- start sub nav list -->
		<div id="subnav" class="grid_12">
			<ul class="sub">
				<li><a onclick="menuClick('${sessionScope.apppath}/tb/preSearch.do', 1)" style="cursor:pointer;text-decoration:none;" >贴吧管理</a></li>
			</ul>
			<ul class="sub">
				<li><a onclick="menuClick('${sessionScope.apppath}/ty/preSearch.do', 2)" style="cursor:pointer;text-decoration:none;" >天涯管理</a></li>
			</ul>
			<ul class="sub">
				<li><a onclick="menuClick('${sessionScope.apppath}/controlPanel/preSearch.do', 3)" style="cursor:pointer;text-decoration:none;" >控制面板</a></li>
			</ul>
		</div>
		<!-- end subnavigation list -->
	</div>
</div>
<div id="logout_confirm" style="display:none;"></div>
<div id="modify" style="display:none;">
	<form id="modifyForm">
		<table>
			<tr>
				<td>原始密码</td><td><input type="password" id="opasswd" name="opasswd" /></td>
			</tr>
			<tr>
				<td>新密码</td><td><input type="password" id="npasswd" name="npasswd" /></td>
			</tr>
			<tr>
				<td>确认密码</td><td><input type="password" id="repasswd" name="repasswd" /></td>
			</tr>
		</table>
	</form>
</div>
<!-- end sub_nav -->
<script type="text/javascript">
var current_index = 0;
$(function () {
	// 主菜单切换
	var $navigation = $("#navigation li");
	var $main_cont = $(".sub");
	$navigation.hover(function () {
		$(this).addClass("active").siblings().removeClass("active");
		var main_index = $navigation.index(this);
		$main_cont.eq(main_index).show().siblings().hide();
	});
	getHoverNav();
});

function getHoverNav(){
    var sub_List = $(".sub");
    var nav_List = $("#navigation li");
    var loc_url = location.href;
    var urlLen = loc_url.indexOf("?");
    if (urlLen >= 0) {
        loc_url = loc_url.substring(0, urlLen);
    }
    urlLen = loc_url.indexOf("#");
    if (urlLen >= 0) {
        loc_url = loc_url.substring(0, urlLen);
    }
    
    var ew_url_len = loc_url.indexOf("/sp");
    if(ew_url_len>0 && ew_url_len + 3 == loc_url.length){
    	$(nav_List[1]).addClass("active").siblings().removeClass("active");
    	sub_List.eq(1).show().siblings().hide();
    }
    else{
    	var search_url_len = loc_url.indexOf("/search");
    	if(search_url_len>0 && search_url_len + 7 == loc_url.length){
    		loc_url = loc_url.substring(0,search_url_len);
    	}
        for (var i = 0; i < sub_List.length; i++) {
            if ($(sub_List[i]).html().indexOf(loc_url) >= 0) {
                $(nav_List[i]).addClass("active").siblings().removeClass("active");
                sub_List.eq(i).show().siblings().hide();
                
                var sub_link = sub_List.eq(i).children();
                for (var j = 0; j < sub_link.length; j++) {
                    var link_child = $(sub_link[j]).children("a");
                    for (var n = 0; n < link_child.length; n++) {
                    	var link_href = $(link_child[n]).attr("href");
                    	if(link_href.indexOf(location.href)==0 && link_href.indexOf(location.href+"/")<0){
                    		$(link_child[n]).addClass("sub_nav_active");
                    	}
                        else if($(link_child[n]).hasClass("sub_nav_active")){
                        	$(link_child[n]).removeClass("sub_nav_active");
                        }
                    }
                }
            }
        }
    }//else_for
}

function menuClick(url, index) {
	current_index = index;
	$("#main_content_wrap").empty();;
	$("#main_content_wrap").load(url);
}

function logoutConfirm(url) {
	$("#logout_confirm").show();
	$("#logout_confirm").prop("title", "退出确认").html("您确定要退出吗？");
	var dialog = $("#logout_confirm").dialog({
		 resizable: false,
		 height:140,
		 modal: true,
		 buttons: {
			 "确定": function() {
				 	location.href = url;
			 },"取消": function() {
				 $(this).dialog( "close" );
			 }
		 },
		 close:function() {
			 $(this).dialog("destroy");
			 $(this).hide();
		 }
	});
	dialog.dialog("open");
}

function modifyPassword(action) {
	$("#modify").show();
	$("#modify").prop("title", "修改密码");
	$('#modifyForm')[0].reset();
	var dialog = $("#modify").dialog({
		resizable: false,
		height:260,
		modal: true,
		buttons: {
			"确定": function() {
				if ($.isFunction(window.validationPassword)){
					var rs=validationPassword();
					if(rs==undefined||rs==false){ return; }
				}
				$.ajax({
					type: "post",
		            dataType: "json",
		            url: action,
		            data: $('#modifyForm').serializeArray(),
		            contentType: 'application/x-www-form-urlencoded; charset=utf-8', 
		            success: function(data){
		            	if(data!=undefined&&data.result==true){
		            		dialog.dialog("close");
		            		show_my_dmessager("密码修改成功");
		            	} else {
		            		returnError(data);
		            	}
		            }
				});
			},"取消": function() {
				$(this).dialog( "close" );
			}
		},
		close:function() {
			$(this).dialog("destroy");
			$(this).hide();
		}
	});
	dialog.dialog("open");
}

function validationPassword() {
	var err = "";
	var errId = "";
	var opasswd = $("#opasswd").val();
	var npasswd = $("#npasswd").val();
	var repasswd = $("#repasswd").val();
	if(isNull(opasswd)) {
		err = "原始密码不能为空！";
		errId = "opasswd";
	} else if (isNull(npasswd)) {
		err = "新密码不能为空！";
		errId = "npasswd";
	} else if (!checkLength(npasswd, 6, 16)) {
		err = "新密码不能少于6位且不能超过16位！";
		errId = "npasswd";
	} else if (opasswd == npasswd) {
		err = "新密码不能与原始密码相同！";
		errId = "npasswd";
	} else if (repasswd != npasswd) {
		err = "两次输入的密码不相同！";
		errId = "repasswd";
	} else {
		return true;
	}
	show_my_messager("编辑错误提示", err);
	$("#"+errId).focus();
	
}
</script>