<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>转角街坊 - 快销宝</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="../../resources/images/favicon.png" type="image/x-icon">
    <link rel="stylesheet" href="../../resources/css/normalize.css">
    <script src="../../resources/vendor/jquery/jquery-1.12.4.min.js"></script>
    <!--[if lt IE 9 ]>
        <script src="${root}/resources/vendor/lib/html5shiv.js"></script>
        <script src="${root}/resources/vendor/lib/es5-shim.js"></script>
    <![endif]-->
    <!--[if lt IE 10 ]>
        <script src="${root}/resources/vendor/jquery/jquery.placeholder.js"></script>
    <![endif]-->
    <link rel="stylesheet" href="../../resources/css/forget-password.css">

    <style>
        .bar-box {
            position: relative;
            width: 90px;
            height: 6px;
            display: inline-block;
            background: #ccc;
            vertical-align: middle;
        }
        .bar-box .bar-i {
            position: absolute;
            top: 0;
            left: 0;
            height: 6px;
            display: inline-block;
        }
        .bar-box .red {
            width: 33.33%;
            background: #ed4b1c;
        }
        .bar-box .yellow {
            width: 66.66%;
            background: #f4a925;
        }
        .bar-box .green {
            width: 100%;
            background: #62b618;
        }
    </style>
    <script src="${root}/resources/vendor/layer/layer.js"></script>
</head>
<body>
<nav class="nav">
    <section class="w">
        <div class="welcome">
            <img src="../../resources/purchase/images/top-logo.png" alt="">  欢迎来到  转角街坊
        </div>
    </section>
</nav>
<header class="header">
	<c:choose>
		<c:when test="${type==1}">
		<a href="${root}/scms/authority/scmsLoginPage.do" class="logo"></a>
		</c:when>
<!-- 		<c:when test="${type==2}">
		<a href="${root}/scms/authority/dealerLoginPage.do" class="logo logo-dealer"></a>
		</c:when>
		<c:when test="${type==3}">
		<a href="${root}/scms/authority/warehouseLoginPage.do" class="logo logo-warehouse"></a>
		</c:when> -->
	</c:choose>



    <div class="bar">
        <div class="bar-item active">
            <span class="number">1</span>
            手机验证
        </div>
        <div class="bar-item curr">
            <span class="number">2</span>
            修改密码
        </div>
        <div class="bar-item">
            <span class="number">3</span>
            完成
        </div>
    </div>
</header>
<main class="main">
    <div class="title">设置新密码</div>
    <div class="info">
        <form action="${root}/scms/password/login_passwd_update.do" id="passwordFrom" method="post">
            <p style="position:relative">
                <label class="label">请输入新密码：</label>
                <input type="text" class="input input-default" name="password" id="nowPassword">
                <label style="position: absolute; color: green; right: 300px; top: 4px;" id="newpwdmes"></label>
            </p>
            <p>
                <label class="label">请再次输入新密码：</label>
                <input type="text" class="input input-default" id="checkPassword">
            </p>
            <br>
        </form>
       <div>
           <input type="button" value="提交" class="button-ok">
       </div>
    </div>
</main>
<script type="text/javascript">
	$(function(){
		$('#nowPassword').on('focus' , function(){
			$('#nowPassword').attr('type','password');
		});
		$('#checkPassword').on('focus' , function(){
			$('#checkPassword').attr('type','password');
		});
		$('#nowPassword').on('keyup' , function(){
			  var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
		      var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
		      var enoughRegex = new RegExp("(?=.{6,}).*", "g");
		      if(false==enoughRegex.test($.trim($(this).val()))){
		    	  $('#newpwdmes').text('');
		      }else if(strongRegex.test($.trim($(this).val()))){
		    	  $('#newpwdmes').html('<span class="bar-box"><span class="bar-i green"></span></span> 高');
		      }else if(mediumRegex.test($.trim($(this).val()))){
		    	  $('#newpwdmes').html('<span class="bar-box"><span class="bar-i yellow"></span></span> 中');
		      }else{
		    	  $('#newpwdmes').html('<span class="bar-box"><span class="bar-i red"></span></span> 低');
		      }
		      return true;
		});
		$('.button-ok').on('click' , function(){
			if($.trim($('#nowPassword').val()) == ''){
				layer.tips('新密码不能为空！' , '#nowPassword');
				$('#nowPassword').focus();
			}else if($.trim($('#nowPassword').val()).length <6){
				layer.tips('密码长度不能小于6位！' , '#nowPassword');
				$('#nowPassword').focus();
			}else if($.trim($('#checkPassword').val()) == ''){
				layer.tips('确认密码不能为空！' , '#checkPassword');
				$('#checkPassword').focus();
			}else if($.trim($('#checkPassword').val()) != $.trim($('#nowPassword').val())){
				layer.tips('两次输入的新密码不一致！' , '#checkPassword');
				$('#checkPassword').focus();
			}else{
				$('#passwordFrom').submit();
			}
		});
	});
</script>
</body>
</html>
