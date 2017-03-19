<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="../common/header.jsp"></jsp:include>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" style="padding:5px;">
		<div style="padding:10px 10px 10px 10px">
	       	<form id="ff" method="post">
	            <table cellpadding="5">
	                <tr>
	                    <td colspan="4"><input class="easyui-textbox" value="${Model.content}" name="content" 
	                    	data-options="multiline:true, required:true" style="width:360px; height:240px"></input></td>
	                </tr>
	            </table>
	       	</form>
		</div>
	</div>
</body>
</html>