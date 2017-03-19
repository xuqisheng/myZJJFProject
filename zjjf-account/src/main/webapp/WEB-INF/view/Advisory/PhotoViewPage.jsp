<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="../common/header.jsp"></jsp:include>
</head>
<body>
	<div class="easyui-layout" style="padding:5px;">
       	<div style="padding:10px 10px 10px 10px">
        	<form id="ff" method="post" enctype="multipart/form-data">
	            <table cellpadding="5">
	            	<tr>
	            		<td>
	            			<img id="pic" name="pic" src="${Model.picUrl}" style="width:340px; height:200px"/>
	            		</td>
	            	</tr>
	            </table>
        	</form>
        </div>
	</div>
</body>
</html>