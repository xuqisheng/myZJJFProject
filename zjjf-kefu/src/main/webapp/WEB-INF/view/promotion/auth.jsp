<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>权限校验</title>
    <%@ include file="../common/head.jsp"%>
</head>
<body class="wrap-bd">
<div style="padding-top:20px;width:360px;height:240px;background:#ececec;text-align:center">
	<h3>超级管理登陆</h3>
	<form id="J_form" action="${root}/Customer/proManage/check.do"  method="post">
		<input type="hidden" name="url" value="" >
	    <p>
	    	<label class="label">用户名：</label>
	      	<input name="username" value="admin" class="input input-default">
	    </p>
	    <p>
	    	<label class="label">密码：</label>
	    	<input name="password" type="password" id="password" class="input input-default">
	    </p>
	    <input type="hidden" value="${st}" name="st">
	    <input type="hidden" value="${id}" name="id">
	    <input type="submit" value="登陆" class="button button-white">
	</form>
</div>
</body>
</html>