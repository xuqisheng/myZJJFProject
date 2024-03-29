<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>魔方 - 转角街坊数据分析平台</title>
    <%@ include file="./common/head.jsp"%>
	<link href="<%=request.getContextPath() %>/resources/css/layout.css?v2016" rel="stylesheet">
	<script src="<%=request.getContextPath() %>/resources/vendor/jquery/jquery-1.12.3.min.js"></script>
	<script src="<%=request.getContextPath() %>/resources/js/layout.js?v2016"></script>
</head>
<body>
	<%@ include file="./common/nav.jsp"%>
	<main id="main">
		<%@ include file="./common/header.jsp"%>
		<article id="article">
			<iframe id="mainiframe" name="mainiframe" src="<%=request.getContextPath() %>/toMainPage.do" width="100%" height="100%" frameborder="0"></iframe>
		</article>
	</main>
</body>
</html>
