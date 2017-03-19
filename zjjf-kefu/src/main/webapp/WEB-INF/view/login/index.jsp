<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>客服系统 - 登录</title>
<%@ include file="../common/head.jsp"%>
<link rel="stylesheet" href="${root}/resources/css/login.css?v123">
<!--[if lt IE 10 ]>
	<script>alert("建议更换（切换模式）或升级浏览器获得更好的体验！");</script>
<![endif]-->
<script>
	if(self!=top) {
		top.location = self.location.href;
	}
</script>
</head>
<body>
<div class="contontbox">
	<div class="wrap">
		<div class="loginbox">
			<h1>登录系统</h1>
			<div class="tip" style="display:none"></div>
			<form id="entryform" >
				<%--<div class="text"><input type="text" class="form-control" id="userName" name="userName" placeholder="帐号" value=""></div>
                <div class="text"><input type="password" class="form-control" id="password" name="password" placeholder="密码" value=""></div>--%>

                <div class="text"><input type="text" class="form-control" id="userName" name="userName" placeholder="帐号" value="admin"></div>
                <div class="text"><input type="password" class="form-control" id="password" name="password" placeholder="密码" value="111111"></div>

				<input type="hidden" name="type" value="1">
				<button type="button" class="login-btn" id="submitBtn">登　录</button>
			</form>
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
				$.post(ROOT+'/kefu/authority/kefuLoginIn.do',encodeURI($('#entryform').serialize()),function(data){
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

		$('#submitBtn').trigger('click');
	});
</script>
</body>
</html>
