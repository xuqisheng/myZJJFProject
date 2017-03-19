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
    <link rel="stylesheet" href="${root}/resources/css/layout.css?v20160108000">
	<script src="${root}/resources/js/layout.js?v20160108000"></script>
</head>
 
<body> 
  <div class=""> 
    <h1>应用程序异常</h1> 
    <p>抱歉！您访问的页面出现异常，请稍后重试或联系管理员。</p> 
    <%-- <div style="display:none;text-align: left;" id="err">${exception }</div> --%>
    <div style="text-align: left;" id="err">${exception }</div>
  </div>
</body> 
</html>
