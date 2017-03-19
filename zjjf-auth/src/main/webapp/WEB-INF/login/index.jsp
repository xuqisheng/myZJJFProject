<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>街坊店宝</title>
<%@ include file="../common/head.jsp"%>
<link rel="stylesheet" href="${root}/resources/css/login.css?v">
<!--[if lt IE 9 ]>
	<script>alert("建议升级或更换浏览器获得更好的体验！");</script>
<![endif]-->
<script>
	if(self!=top) {
		top.location=self.location.href;
	}
</script>
</head>
<body>
<div class="headbox">
	<div class="head wrap">街坊店宝 - 权限系统</div>
</div>
<div class="contontbox">
	<div class="wrap login-info">
		<div class="login-img"><img src="${root}/resources/images/login_img.png" width="494" height="458" alt=""></div>
		<div class="login">
			<div class="loginbox">
				<h1>登录系统</h1>
				<div class="tip" style="display:none"></div>
				<form id="entryform" >
					<div class="text"><input type="text" class="form-control" id="userName" name="userName" placeholder="帐号" value=""></div>
					<div class="text"><input type="password" class="form-control" id="password" name="password" placeholder="密码" value=""></div>
					<input type="hidden" name="type" value="1">
					<button type="button" class="login-btn" id="submitBtn">登　录</button>
				</form>
		     </div>
		</div>
	</div>
</div>
<script>
	var ROOT = '${root}';
	$(function() {
		$('#userName').focus();
		$('input').on('focus', function(e) {
			$('.tip').hide();
		});
		$('#submitBtn').on('click', function() {
			var username = $('#userName').val().trim();
			var password = $('#password').val().trim();
			if("" == username) {
				$('.tip').text('用户名不能为空');
		    	$('.tip').show();
		    	return false;
			} else if("" == password) {
				$('.tip').text('密码不能为空');
		    	$('.tip').show();
		    	return false;
			} else {
				$.post(ROOT+'/auth/authority/authLoginIn.do',encodeURI($('#entryform').serialize()),function(data){
					if(!data.success) {
						$('.tip').text(data.message);
						$('.tip').show();
					} else {
						
						location.href = ROOT + data.url;
					}
				},'json');
			}
		});
		$('#submitBtn').keydown(function() {
			$('input').css('background','#ffc');
		});

		// 回车
		document.onkeydown = function(e){
			var ev = document.all ? window.event : e;
		    if(ev.keyCode==13) {
		    	$('#submitBtn').click();
			}
		}
	});
</script>
</body>
</html>