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
	<script src="${root}/resources/vendor/layer/layer.js"></script>
    <link rel="stylesheet" href="../../resources/css/forget-password.css">
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
	<c:when test="${ro.checkCode==1}">  <a href="${root}/scms/authority/scmsLoginPage.do" class="logo logo-sp"></a></c:when>
	<c:when test="${ro.checkCode==2}"><a href="${root}/scms/authority/dealerLoginPage.do" class="logo logo-dealer"></a></c:when>
	<c:otherwise> <a href="${root}/scms/authority/warehouseLoginPage.do" class="logo logo-warehouse"></a></c:otherwise>

</c:choose>



    <div class="bar">
        <div class="bar-item curr">
            <span class="number">1</span>
            手机验证
        </div>
        <div class="bar-item">
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
    <div class="title">手机号码验证</div>
    <div class="info">
        <form >
            <p>
                已验证手机：
                <input type="text" class="input input-small" placeholder="请输入手机号码" value="" id="phoneNumber">
                <label class="label"></label>
            </p>
            <p>
                手机验证码：
                <input type="text" class="input input-small" placeholder="请输入手机验证码" value="" id="check">
                <label class="label">
                    <input type="button" id="sendMsgCode" value="获取短信验证码" class="input input-button" style="margin-left:-6px;margin-top:-1px" onclick="getCode()">
                </label>
            </p>
            <br>
            <div>
                <input type="button" value="提交" class="button-ok" onclick="editpasswd()">
            </div>
        </form>
    </div>
</main>
<input type="hidden" id="checkCode" value="${ro.checkCode}">
<script type="text/javascript" >
function getCode(){
	var phoneNumber=$.trim($("#phoneNumber").val());
	if(phoneNumber==""){layer.msg("手机号不能为空"); $("#phoneNumber").focus();return;}
	if(!checkSubmitMobil(phoneNumber)){
		layer.msg("手机号码格式错误！");
		return ;
	}
	var checkCode=$.trim($("#checkCode").val());
	var $sendMsgCode = $('#sendMsgCode');


	$.post("${root}/scms/password/login_passwd_checkphone.do", {"phoneNumber":phoneNumber,"checkCode":checkCode}, function (data) {
		$sendMsgCode.prop('disabled', 'disabled');
		layer.msg(data.message);
		$sendMsgCode.css('background', '#ccc');
		if(data.success){
			var i = 60;
			function cd() {
				i--;
				if(i == 0) {
					$sendMsgCode.prop('disabled', '').css('background', '');
					$sendMsgCode.val('重新获取短信校验码');
					clearInterval(c);
				} else {
					$sendMsgCode.val(i + '秒');
				}
			}
			var c = setInterval(cd, 1000);
		}else{
			$sendMsgCode.prop('disabled', '').css('background', '');
			$sendMsgCode.val('获取短信校验码');
		}

      	},"json");
}
//验证手机号码
function checkSubmitMobil(mobile) {

 if (!mobile.match(/^(0|86|17951)?(1[3-9])[0-9]{9}$/)) {
 	layer.msg("手机号码格式不正确！");
 		return false;
	 	}
	return true;
}


function editpasswd(){
	var phoneNumber=$.trim($("#phoneNumber").val());
	var check=$.trim($("#check").val());
	if(phoneNumber==""){layer.msg("手机号不能为空"); $("#phoneNumber").focus();return;}
	if(check==""){layer.msg("验证码不能为空"); $("#check").focus();return;}
	if(!checkSubmitMobil(phoneNumber)){
		layer.msg("手机号码格式错误！");
		return ;
	}
	var checkCode=$.trim($("#checkCode").val());
	$.post("${root}/scms/password/login_passwd_edit.do",{"phoneNumber":phoneNumber,"check":check,"checkCode":checkCode}, function (data) {
		if(data.success){
			window.location.href="${root}/scms/"+data.message;
		}else{
			layer.msg(data.message);
		}
      	},"json");

}

</script>
</body>
</html>
