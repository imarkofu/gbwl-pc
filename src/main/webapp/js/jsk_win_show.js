var spaceList = "||6||7||8||9||10||11||12||19||20||21||22||23||24||26||27||28||29||30||31||33||34||36||37||38||40||42||"; 
function imageEvent() {
    if ($(".win_img_hide").length == 0) {
        $("body").prepend("<img src='" + $(this).attr("src") + "' class='win_img_hide' alt='0' />");
    } else {
        $(".win_img_hide").attr("src", $(this).attr("src"));
        $(".win_img_hide").attr("alt", $(".win_img_hide").width() + "x" + $(".win_img_hide").height());
    }
    window.setTimeout(showImage, 50);
}
function showImage() {
    if ($(".win_img_hide").length > 0) {
        var sWidth = $(".win_img_hide").width();
		sWidth = sWidth < 310 ? 310:sWidth;
        var sHeight = $(".win_img_hide").height();
        showWin(sWidth, sHeight, "<img src='" + $(".win_img_hide").attr("src") + "' alt='' />");
    }
}
function showWin(sWidth, sHeigth, txtHtml) {
    sWidth = sWidth < 100 ? 100 : sWidth;
    sHeigth = sHeigth < 100 ? 100 : sHeigth + 25;
    var bgWidth = $(window).width();
    var bgHeight = $(window).height();
    var sLeft = (bgWidth - sWidth) / 2;
    var sTop = (bgHeight - sHeigth) / 2;
    sLeft = sLeft < 0 ? 0 : sLeft;
    sTop = sTop < 0 ? 0 : sTop;
    if ($(".win_bg_layer").length == 0) {
        $("body").prepend("<div class='win_bg_layer' style='width:" + bgWidth + "px;height:" + bgHeight + "px'></div>");
        $("body").prepend("<div class='win_show' style='width:" + sWidth + "px;height:" + sHeigth + "px;left:"
        + sLeft + "px;top:" + sTop + "px;'><div class='win_header'><a class='close' onclick='closeWin();'>关闭</a></div><div class='win_contxt'>" + txtHtml + "</div></div>");
    }
    else {
        $(".win_bg_layer").css({ width: bgWidth + "px", height: bgHeight + "px", display: "block" });
        $(".win_show").css({ width: sWidth + "px", height: sHeigth + "px", left: sLeft + "px", top: sTop + "px", display: "block" });
        $(".win_contxt").html(txtHtml);
    }
}
function closeWin() {
    $(".win_show").css({ display: "none" });
    $(".win_bg_layer").css({ display: "none" });
}
function showSpreadWin(spreadId, spaceId, showSize){
	var sizeSpit =  showSize.split("x");
	var wWidth = 320;
	var wHeight = 200;
	if(sizeSpit.length == 2){
		if(spaceList.indexOf("||"+spaceId+"||")>=0){
			wWidth = parseInt(sizeSpit[0]);
			wHeight = parseInt(sizeSpit[1]);
			var spaceIdView = spaceId.toString();
			if(spaceId==12){
				spaceIdView = "10";
			}
			else if(spaceId ==21){
				spaceIdView = "20";
			}
			else if(spaceId == 24){
			    spaceIdView = "23";
			}
			//545x210
			else if(spaceId == 26 || spaceId == 28 || spaceId == 29 || spaceId == 31 || spaceId == 34){
			    wHeight += 10;
			    spaceIdView = "26";
			}
			//300x440
			else if(spaceId == 30 || spaceId == 33 || spaceId == 36 || spaceId == 37 || spaceId == 38||spaceId==40||spaceId==42){
			    spaceIdView = "9";
			}
			var ifm_url = "<iframe width=\""+ wWidth +"px\" scrolling=\"no\" height=\""+ wHeight 
			+"px\" frameborder=\"0\" name=\"ifm\" id=\"ifm\" src=\"http://t.mmhub.com/mmh/demo/s"+ spaceIdView +"/index.htm?spreadId="+
			spreadId+"&stx="+ (new Date()).valueOf()
			+"\"></iframe>";
			wWidth += 2;
			showWin(wWidth,wHeight,ifm_url);
		}
		else{
			alert("此展位类型的推广计划暂时未开通预览功能！");
			//showWin(wWidth,wHeight,"&nbsp;&nbsp;&nbsp;&nbsp;此展位类型的推广计划暂时未开通预览功能！");
		}
	}
	else{
		alert("此展位类型的推广计划暂时未开通预览功能！");
	}
}
$(function(){
	$("img.show_small_img").click(imageEvent);
});
