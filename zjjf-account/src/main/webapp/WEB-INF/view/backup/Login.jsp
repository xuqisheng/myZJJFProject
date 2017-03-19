<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>觅博客户管理系统-用户登录</title>
<link rel="shortcut icon" href="resources/images/logo.ico" type="image/x-icon">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta name="viewport" content="width=device-width" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%-- <jsp:include page="./common/header.jsp"></jsp:include> --%>

<!-- CSS for Page-->
<style type="text/css">
* {	margin: 0;	padding: 0;	font-size: 12px;}
.panel-center{ margin:0 auto; margin-top:200px;}
.input-title{ line-height:28px; text-align:center; width: 54px; float: left;}
.input-title2 {width: 250px;float: left; }
.itemdiv { height: 48px;}
#checkCodeImg{cursor: hand;}
#adminlog-top{height: 80px; background:url(<c:url value='/resources/images/logtop.png'/>) 0 0 repeat-x;}
#adminlog-centerbg{position: relative;}
#centerbgdiv{ position: absolute;top:145px; height: 306px; background: #b1ddf2; width: 100%;z-index: -10000}
#formWap{position: absolute; width: 320px; height: 200px;top:100px; right:250px; }
#submitBt{ font-size:16px; font-weight:bord; color:white; display:block; height:43px;line-height:43px; width:301px; text-align:center; bolder;background:url(<c:url value='/resources/images/loginbt.png'/>) 0 0  no-repeat; margin: 0 auto;}
a{ text-decoration: none;}
</style>

<!-- JS for Page-->
<script>

$(function() {
	$('#ff').form({
		url:root+'/account/authority/doLoginIn.do',	
		onSubmit: function(){
 		    return $(this).form('validate');
		},
		success:function(data){
			var data = eval('(' + data + ')');
			if (data.success){
				window.location.href=root+'/account/authority/AccountIndexPage.do';
			}else{
				$.messager.alert('登录失败', data.message, 'info');
				$("#checkCodeImg").trigger("click");
			}
		},
		error:function(){
			$.messager.alert('登录失败', "系统错误，请联系技术人员！", 'error');
		}
	});
	//bind click event
	$("#submitBt").click(function(){
		$('#ff').form("submit");
	});
	
	//clear value
	$('#password').textbox("setValue","");
	$('#checkcode').textbox("setValue","");
	$('#loginName').textbox("setValue","");

	$(document).keydown(function(event){
		if( event.keyCode == 13){
			$('#ff').form("submit");
		}
	}); 
	//focus
	$('#ff').form("submit");
});
</script>
</head>

<body>


<div id="adminlog-top"></div>
<div id="adminlog-centerbg">
	 	<div id="centerbgdiv"></div>
	 	<div style="height: 120px;"></div>
	    <div style="width: 1350px; height: 355px; overflow: hidden;margin:0 auto;background:url(<c:url value='/resources/images/adminlog.png'/>) center center no-repeat; position: relative;">
	    	<div id="formWap">
	        <form id="ff" method="post" >
	            <div class="itemdiv">
	                <div class="input-title">用户名</div>
	                <div class="input-title2">
	                <input id="loginName" autocomplete="off" name="userName" class="easyui-textbox" style="width:100%;height:28px; font-size:14px;" data-options="iconCls:'icon-man',iconWidth:38,required:true,missingMessage:'用户名不能为空'">
	                </div>
	                <div style="clear: both;"></div>
	            </div>
	            <div class="itemdiv">
	                <div class="input-title">密&nbsp;&nbsp;码</div>
	                <div class="input-title2">
	                <input id="password" name="password"  class="easyui-textbox" type="password" style="width:100%;height:28px; font-size:14px;" data-options="iconCls:'icon-lock',iconWidth:38,required:true,missingMessage:'密码不能为空'">
	                </div>
	                <div style="clear: both;"></div>
	            </div>
	            <div class="itemdiv">
	                <table width="100%">
	                	<tr>
	                		<td width="50" align="center"><span>验证码</span></td>
	                		<td width="130" ><input id="checkcode" name="checkCode"  class="easyui-textbox"  style="width:110px;height:28px; font-size:14px;" data-options="iconCls:'icon-edit',iconWidth:38,required:true,missingMessage:'验证码不能为空',tipPosition:'left'"></td>
	                		<td align="left" ><img id="checkCodeImg" alt="图片看不清点击来刷新!"  src="${root}/checkcode.do?"  onclick="this.src+= Math.random()" ></td>
	                	</tr>
	                </table>                
	            </div>
	        </form>
	        <div style="height: 20px;"></div>
			<a id= "submitBt" href="javascript:void(0)" ></a> 
	      </div><!-- formWap -->
	      
   		 </div>
</div>
</body>
</html>
