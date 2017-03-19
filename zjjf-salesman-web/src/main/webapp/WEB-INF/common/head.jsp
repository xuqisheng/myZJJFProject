<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setAttribute("root", request.getContextPath()); %>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="icon" href="${root}/resources/images/favicon.png?v20160728" type="image/x-icon">
<link rel="stylesheet" href="${root}/resources/css/base.css?v20160728scms">
<script src="${root}/resources/vendor/jquery/jquery-1.12.0.min.js"></script>
<!--[if lt IE 9 ]>
	<script src="${root}/resources/vendor/lib/html5shiv.js"></script>
	<script src="${root}/resources/vendor/lib/es5-shim.js"></script>
<![endif]-->
<!--[if lt IE 10 ]>
	<script src="${root}/resources/vendor/jquery/jquery.placeholder.js"></script>
<![endif]-->
<%@ include file="./layer.jsp"%>
 <script>
 	var rootPath="${root}";
 </script>
