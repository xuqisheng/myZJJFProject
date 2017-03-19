<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<%
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragrma","no-cache");
response.setDateHeader("Expires",0);
%> 
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<title>支付密码修改验证</title>
	<%@ include file="../common/head.jsp"%>
</head>
<body class="wrap-bd">
    <div class="mb-small">
        <span style="font-size: 14px;">当前位置：</span>
        <a href="${root}/scms/password/toEditPassWord.do" class="crumb">密码管理</a>
        <span class="crumb crumb-active"><c:choose><c:when test="${noPayPassword}">支付密码设置验证</c:when><c:otherwise>支付密码修改验证</c:otherwise></c:choose></span>
    </div>
<div class="wrap-bd bg">
	<%-- <form action="${root}/scms/password/toNext.do" id="saveForm" method="post"> --%>
	<b>支付密码修改验证</b>
	<p>
		<label class="label">已验证手机：</label>
		${supplierMobile}
	</p>
	<p>
		<label class="label">手机校验码：</label>
		<input type="text" name="phoneCode" class="input-search-text" id="phoneCode">
		<input type="button" name="" class="input-search-button" value="获取短信校验码" id="sendMsgCode">
	</p>
	<!-- <p>
		<label class="label">验证码：</label>
		<input type="text" name="" class="input-search-text">
		<img src="http://www.mayihd.com/index/verify" width="90" height="32" alt="">
	</p> -->
	<p>
		<label class="label"></label>
		<input class="button-save" type="button" name="save" value="提交" id="save">
		<span style="color: red;" class="hidden" id="phoneCodeError">${errorMessage}</span>
	</p>
	<!-- </form> -->
</div>
<script>
	$(function() {
		//如果错误信息不为空,则显示
		if($('#phoneCodeError').text()!=''){
			$('#phoneCodeError').removeClass('hidden');
		}
		if($('#phoneCodeError').text()=='请再次获取短信校验码'){
			$('#phoneCodeError').removeClass('hidden');
			$sendMsgCode.prop('disabled', '').css('background', '');
			$sendMsgCode.val('重新获取短信校验码');
		}
		var $sendMsgCode = $('#sendMsgCode');
		$sendMsgCode.click(function() {
			$.ajax({
				type: "POST",
				url: "${root}/scms/password/sendMsgCode.do",
				success: function(da) {
					if(da.success){
						if(da.message=='短信验证码已经发送!'){
							layer.msg('短信验证码已经发送!',{time:2000});
						}
						$sendMsgCode.prop('disabled', 'disabled');
						$sendMsgCode.css('background', '#ccc');
						var i = 60;
						function cd() {
							i--;
							if(i == 0) {
								$sendMsgCode.prop('disabled', '').css('background', '');
								$sendMsgCode.val('重新获取短信校验码');
								clearInterval(c);
							} else {
								$sendMsgCode.val(i + '秒后可重新获取短信校验码');
							}
						}
						var c = setInterval(cd, 1000);
					}
				},
				error: function(da) {
					alert("失败的请求!");
				}
			});
		});
	});
		
	//提交按钮
	$('#save').on('click',function(){
		if($.trim($('#phoneCode').val())==''){
			$('#phoneCodeError').removeClass('hidden');
			$('#phoneCodeError').text('请输入手机验证码!');
			return;
		}else{
			$('#phoneCodeError').addClass('hidden');
			$('#phoneCodeError').text('');
		}
		if($.trim($('#phoneCode').val()).length!=6){
			$('#phoneCodeError').removeClass('hidden');
			$('#phoneCodeError').text('请输入6位手机验证码!');
			return;
		}else{
			$('#phoneCodeError').addClass('hidden');
			$('#phoneCodeError').text('');
		}
		
		$.ajax({
			type: "POST",
			data:{"phoneCode":$('#phoneCode').val()},
			url: "${root}/scms/password/validatePhoneCode.do",
			success: function(da) {
				if(da.success){
					$('#phoneCodeError').addClass('hidden');
					location.href="${root}/scms/password/toNext.do";
				}else{
					$('#phoneCodeError').removeClass('hidden');
					$('#phoneCodeError').text(da.message);
				}
			},
			error: function(da) {
				layer.msg('失败的请求!',{time:1000});
			}
		});
		$('#saveForm').submit();
	});

</script>
</body>
</html>