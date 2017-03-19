<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="./common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>阿街足迹</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <%@ include file="./common/head.jsp"%>
    <link rel="stylesheet" href="${root}/resources/css/layout.css?v20160728scms">
	<script src="${root}/resources/js/layout.js?v20160728"></script>
</head>
<body>
<%@ include file="./common/nav.jsp"%>
<main id="main">
	<%@ include file="./common/header.jsp"%>
    <article id="article">
		<iframe onLoad="iframeHeight()" id="mainiframe" name="mainiframe" src="${root}/account/index.do" width="100%" height="100%" frameborder="0"></iframe>
    </article>
</main>
<%-- <%@ include file="./common/analysis.jsp"%> --%>
</body>
</html>