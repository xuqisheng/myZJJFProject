<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<% request.setAttribute("root", request.getContextPath());%>
<header id="header">
	<input id="currentUserId" type="hidden" value="${logInVo.userId}">
	<div class="info"><span class="username mr-small">您好&nbsp;&nbsp;${logInVo.userName}</span> | <a class="ml-small" href="${root}/scms/authority/doLoginOut.do">注销</a></div>
</header>