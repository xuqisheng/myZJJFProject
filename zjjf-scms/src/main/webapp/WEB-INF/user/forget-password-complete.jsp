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
    <link rel="stylesheet" href="../../resources/css/forget-password.css">
    <script src="../../resources/vendor/jquery/jquery-1.12.4.min.js"></script>
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
		<a href="${root}/scms/authority/scmsLoginPage.do" class="logo logo-sp"></a>
		</c:when>
		<c:when test="${type==2}">
		<a href="${root}/scms/authority/dealerLoginPage.do" class="logo logo-dealer"></a>
		</c:when>
		<c:when test="${type==3}">
		<a href="${root}/scms/authority/warehouseLoginPage.do" class="logo logo-warehouse"></a>
		</c:when>
	</c:choose>
    <div class="bar">
        <div class="bar-item active">
            <span class="number">1</span>
            手机验证
        </div>
        <div class="bar-item active">
            <span class="number">2</span>
            修改密码
        </div>
        <div class="bar-item curr">
            <span class="number">3</span>
            完成
        </div>
    </div>
</header>
<main class="main">
    <div class="title">修改密码完成</div>
    <div class="info">
        <img src="../../resources/images/ok.png" alt="">
                    恭喜密码修改成功，请妥善管理好您的密码！
        <div id='div1'></div>
    </div>

</main>
<input type="hidden" value="${type}" id='type'>
</body>
<script type="text/javascript">
$(function() {
	//设定倒数秒数
	var t = 5;
	var type=$("#type").val();

	if(type==null||type==''||type==undefined){
		//阻止往下执行代码
		return false;
	}



	//执行showTime()
	showTime(t);

});

//显示倒数秒数
function showTime(t){
	t -= 1;
    document.getElementById('div1').innerHTML= t+"秒后跳到登录界面";
    if(t==0){
    	var hrefp;
    	if(type==1){//批发商
    		hrefp='${root}/scms/authority/scmsLoginPage.do';
    	}else if(type==2){//经销商
    		hrefp='${root}/scms/authority/dealerLoginPage.do';
    	}else if(type==3){//仓库
    		hrefp='${root}/scms/authority/warehouseLoginPage.do';
    	}
        location.href=hrefp;
    }
    //每秒执行一次,showTime()
    setTimeout("showTime("+t+")",1000);
}

</script>
</html>
