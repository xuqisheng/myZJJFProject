<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>转角店宝 - 数据分析</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
    <%@ include file="../common/head.jsp"%>
    <script src="<%=request.getContextPath() %>/resources/vendor/jquery/jquery-1.12.3.min.js"></script>
    <link rel="stylesheet" href="${root}/resources/css/login.css?v">
	<!--[if lt IE 8 ]>
		<script>alert("建议您升级浏览器或更换浏览器获得更好的体验！祝您生活愉快！");</script>
	<![endif]-->
</head>
<body>
<div class="headbox">
	<div class="head">欢迎光临转角店宝！</div>
</div>
<div class="login-content">
    <div class="contontbox">
        <div class="wrap login-info">
            <%--no<div class="login-img"><img src="${root}/resources/images/login-img.png" width="494" height="458" alt=""></div>--%>
            <div class="login">
                <div class="loginbox">
                    <%--no<h1>登录系统</h1>
                    <div class="tip" style="display:none"></div>
                    <form id="entryform" >
                        <div class="text"><input type="text" value='' class="form-control" id="userName" name="userName" placeholder="帐号"></div>
                        <div class="text"><input type="password" value='' class="form-control" id="password" name="password" placeholder="密码"></div>
                        <div class="text">
                            <input type="text" style="padding: 0 3px; width: 80px; height: 30px; border: 1px solid #ccc" name="checkCode" placeholder="验证码">
                            <img class="clear-img" src="${root}/checkcode.do" id="J_checkCodeImg" width="100" height="32">
                        </div>
                        <button type="button" class="login-btn" id="J_submitBtn">登　录</button>
                    </form>--%>
                    <div>
                        <img src="${root}/resources/images/login-logo.png" width="206" height="34" alt="">
                    </div>
                    <h1>平台登录</h1>
                    <div class="tip" style="display:none"></div>
                    <form id="entryform" >
                        <div class="text"><input type="text" value='' class="form-control" id="userName" name="userName" placeholder="帐号"></div>
                        <div class="text"><input type="password" value='' class="form-control" id="password" name="password" placeholder="密码"></div>
                        <div class="text">
                            <input type="text" style="padding: 0 3px;width: 105px;height: 30px;border: 1px solid #ccc;text-indent: 7px;" name="checkCode" placeholder="验证码">
                            <img class="clear-img" src="${root}/checkcode.do" id="J_checkCodeImg" width="100" height="32">
                        </div>
                        <button type="button" class="login-btn" id="J_submitBtn">登　录</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<footer class="footer">
    <img src="${root}/resources/images/login-footlogo.png"/> 深圳市转角街坊网络科技有限公司   Copyright © izjjf.com   V1.3
</footer>
<script>
	$(function() {
		$('#J_checkCodeImg').on('click', function() {
			$(this).attr('src', '${root}/checkcode.do?' + Math.random());
		});
		$('#J_submitBtn').on('click', function() {
			var username = $("#userName").val().trim();
			var password = $("#password").val().trim();
			if("" == username) {
				$('.tip').text("用户名不能为空");
		    	$('.tip').show();
		    	return false;
			} else if ("" == password) {
				$('.tip').text("密码不能为空");
		    	$('.tip').show();
		    	return false;
			} else {
				$.ajax({
					type: "POST",
					url: "${root}/analysis/authority/userLoginIn.do",
					async: true,
					data: encodeURI($("#entryform").serialize()),
					success: function(date) {
						if(date.success) {
							location.href = "${root}"+date.url;
							//location.href = "${root}/toMain.do";
						} else {
							if(date.message == '验证码错误') {
								$('#J_checkCodeImg').trigger('click');
							};
							$(".tip").text(date.message);
							$(".tip").show();
						}
					},
					error: function(date) {
					}
				});
			}
		});
		$("input").on("focus", function(e) {
			$(".tip").hide();
		});
		// 回车
		document.onkeydown = function(e) {
			var ev = document.all ? window.event : e;
		    if(ev.keyCode==13) {
		    	$("#J_submitBtn").click();
			}
		}
	});
</script>
</body>
</html>
