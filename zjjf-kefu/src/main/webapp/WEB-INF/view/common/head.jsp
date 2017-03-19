<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="${root }/resources/images/favicon.png?v" rel="icon" type="image/x-icon">
<link href="${root }/resources/css/base.css?v20160729" rel="stylesheet">
<script src="${root }/resources/vendor/jquery/jquery-1.12.4.min.js"></script>
<!--[if lt IE 10 ]>
    <script src="${root}/resources/vendor/jquery/jquery.placeholder.js"></script>
<![endif]-->
<script src="${root}/resources/vendor/layer/layer.js"></script>
<script>
	$(function(){
	   $.ajaxSetup({
	        complete:function(XMLHttpRequest,textStatus){
	            console.log(XMLHttpRequest);
	            if(textStatus=="parsererror"){
	                alert("登录超时，请从新登陆！");
	                location.href = "${root}/kefu/authority/scmsLoginPage.do";
	            }
	        }
	    });
	});
</script>
