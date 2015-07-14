//将array拼接成字符串，默认分隔符为逗号
//如[1,2,3]转换为1,2,3
function joinArray(array,spot){
	var str='';
	if(isundefined(spot)){
		spot=',';
	}
	for(var i in array){
		str+=array[i]+''+spot;
	}
	if(str.length!=0){
		str=str.slice(0, str.length-1);
	}
	return str;
}
//验证是否为整形数字
function isInteger( str ){
	var regu = /^[-]{0,1}[0-9]{1,}$/;
	return regu.test(str);
}
//验证是否为数字
function isNum(num){
	if(num=='')return false;
	if(isundefined(num))return false;
	return !isNaN(num);
}
function checkLength(str,min,max){
	if(str.length>=min&&str.length<=max)
		return true;
	else
		return false;
}
function isNull( str ){ 
	if ( str == "" ) return true; 
	var regu = "^[ ]+$"; 
	var re = new RegExp(regu); 
	return re.test(str); 
}
function checkAllOrNot() {
	if($("#ids").prop("checked") == false){
		$("[name='keyIds']").prop("checked", false);
	} else {
		$("[name='keyIds']").prop("checked", true);
	}
}
//判断是否未定义
function isundefined(params){
	return typeof(params)=="undefined";
}
//提取url中的参数
function geturlp(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}
//将未定义数值转换为0
function undefined2int(param) {
	if (typeof (param) == 'undefined')
		return 0;
	else
		return param;
}
//将未定义数值转换为空字符串
function undefined2empty(param) {
	if (typeof (param) == 'undefined')
		return '';
	else
		return param;
}

function debugObject(obj){
	var str='';
	for(var i in obj){
		str+=obj[i]+'\n';
	}
	return str;
}
function totalAjax(url,method, pd, fn) {
	$.ajax({
		url : url,
		type : method,
		data : pd,
		dataType : 'json',
		contentType: 'application/x-www-form-urlencoded; charset=utf-8', 
		success : function(data) {
			fn(data);
		},
		error : function(data) {
			dealErrorMessage("提交失败，请稍候再试！");
		}
	});
}
function getCheckBoxVal(ckName) {
    var arrayObj = new Array();
    $("input[name='" + ckName + "']:checked").each(function (i, obj) {
        if ($(obj).attr("checked")) {
            arrayObj.push($(obj).val());
        }
    });
    if (arrayObj.length > 0) {
        return arrayObj.join(',');
    }return "";
 }
function initCheckBox(ckName, valList){
    $("input[name='" + ckName + "']").each(function (i, obj) {
    	if(valList.indexOf($(obj).val())>=0){
    		$(obj).attr("checked","true");
    	}
    });}
function afterSumbit(fid){
	setAbledFormElement(fid, 1);
	setTimeout('setAbledFormElement("'+ fid +'", 0)',3000);
}

function setAbledFormElement(fid, flag){
	if(flag==0){
		$("#"+fid).attr("disabled",false); 
	}else{
		 $("#"+fid).attr("disabled",true); 
	}
}

function sendPost(action, title) {
	$.messager.confirm('提示',title,function(result){
		if (result) {
			$.ajax({
				type: "post",
				dataType: "json",
		        url: action,
		        data:{},
		        contentType: 'application/x-www-form-urlencoded; charset=utf-8',
		        success:function(data) {
		        	if (data!=undefined) {
		        		$.messager.show({
		            		title:'提示信息：',
		            		msg:data.msg,
		            		timeout:3000,
		            		showType:'slide'
		        		});
		        		if (data.result==true) {
		        			$('#dg').datagrid('reload');
		        		}
		        	} else {
		        		$.messager.show({
		            		title:'提示信息：',
		            		msg:'网络异常',
		            		timeout:3000,
		            		showType:'slide'
		        		});
		        	}
		        }
			});
		}
	});
}