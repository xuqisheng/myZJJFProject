<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>错误页</title>
    <%@ include file="../common/head.jsp"%>
<style>
	.h1 {
		font-family: '微软雅黑';
		color: #b08d00;
		font-size: 24px;
	}
	.com_bg {
	    padding: 200px 0 0 248px;
		margin: 0 auto;
		width: 650px;
		height: 535px;
	}
	.com_text {
		font-size: 14px;
		font-family: '微软雅黑';
		color: #aea375;
		line-height: 8px;
		color: #ed4b1c;
	}
</style>
</head>
<body>
<div class="com_bg">
    <span class="h1">
	    <i>出错啦！</i><i class="com_text"> ${message} </i>
		</span><br/>
	<span class="orange"><i id="mes">5</i>s</span>后自动跳转！
</div>
<script>
$(function(){
    var miu = $("#mes").html();
		countDown(miu);
	});
	function countDown(miu){     
		   if(--miu>=0){
			 $("#mes").html(miu);
		     setTimeout("countDown("+miu+")",1000);     
		 } else {  
		history.go(-1);
	}     
}
</script>
</body>
</html>