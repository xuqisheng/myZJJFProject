<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>快销宝 - 登录</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <%@ include file="../common/head.jsp"%>
    <link rel="stylesheet" href="${root}/resources/css/login.css?v20160707">
    <script src="${root}/resources/vendor/jquery/jquery.cookie.js"></script>
    <script type="text/javascript">
        if(self != top) {
        	top.location=self.location.href;
        }
    </script>
</head>
<body>
<div class="header">
	<div class="fl">
		<img src="../../resources/purchase/images/top-logo.png" alt="">
		欢迎来到 转角街坊
	</div>
	<div class="fr">客服热线: 400-664-3833</div>
</div>
<div class="w main">
	<div class="login">
		<img src="../../resources/images/logo-login-tmp.png" width="258" alt="">
		<div class="form">
            <div class="tips" id="J_tips"></div>
			<form id="entryform">
				<input type="text" value='' class="input icon-user" id="userName" name="userName" placeholder="帐号" autocomplete="off">
				<input type="password" value='' class="input icon-pwd" id="password" name="password" placeholder="密码">
				<div class="remember">
					<span class="fl">
						<input type="checkbox" id="J_remember" class="checkbox">&nbsp;<label for="J_remember">记住我</label>
					</span>
					<a href="${root}/scms/password/login_passwd.do?checkCode=1" class="fr">忘记密码</a>
				</div>
				<input type="button" class="input submit" id="J_submitBtn" value="登　录">
			</form>
		</div>
	</div>
</div>
<div class="footer">
	深圳市转角街坊网络科技有限公司&nbsp;&nbsp;&nbsp;Copyright&nbsp;&copy;&nbsp;izjjf.com
</div>
<script>
	$(function() {
		$('#userName').focus();
        if ($.cookie('rmbUser') == 'true') {
            $('#J_remember').attr('checked', true);
            $('#userName').val($.cookie('username'));
            $('#password').val($.cookie('password'));
        }
		$('#J_submitBtn').on('click', function() {
			var username = $('#userName').val().trim();
			var password = $('#password').val().trim();
			if('' == username) {
				$('#J_tips').addClass('active').text('账号不能为空');
		    	return false;
			} else if ('' == password) {
                $('#J_tips').addClass('active').text('密码不能为空');
		    	return false;
			} else {
				$.ajax({
					type: "POST",
					url: "${root}/scms/authority/supplierLoginIn.do",
					async: true,
					data: encodeURI($("#entryform").serialize()),
					success: function(date) {
						if(date.success) {
			                if ($('#J_remember').is(':checked')) {
			                    var username = $('#userName').val();
			                    var password = $('#password').val();
			                    $.cookie('rmbUser', true, { expires: 7 });
			                    $.cookie('username', username, { expires: 7 });
			                    $.cookie('password', password, { expires: 7 });
			                } else {
			                    $.cookie('rmbUser', false, { expire: -1 });
			                    $.cookie('username', '', { expires: -1 });
			                    $.cookie('password', '', { expires: -1 });
			                }
							location.href = "${root}" + date.url + "?loginStr=supply";
						} else {
							$('#J_tips').addClass('active').text(date.message);
						}
					},
					error: function(date) {
					}
				});
			}
		});
        $('input').on('focus', function() {
            $('#J_tips').removeClass('active').text('');
        });
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
