<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../common/header.jsp"></jsp:include>
<title>${UserInfoSession.realName }欢迎您！</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${root }/resources/css/mongo/index.css" rel="stylesheet"
	type="text/css" media="all"></link>
</head>
<body>
	<center>
		<h1>MongoDB-Demo</h1>
		<form action="${root }/mongo/add.do" method="post">
			帐号：<input type="text" name="userName"/>
			姓名：<input type="text" name="passWord"/>
			<input type="submit" value="mongo新增" />
		</form>
		<c:if test="${not empty model }">
		<form action="${root }/mongo/update.do" method="post">
			<input type="hidden" name="objectId" value="${model.objectId }">
			帐号：<input type="text" name="userName"  value="${model.userName }"/>
			姓名：<input type="text" name="passWord"  value="${model.passWord }"/>
			<input type="submit" value="mongo修改" />
		</form>
		</c:if>
		<a href="${root }/mongo/deleteAll.do">删除全部</a>
		<table cellpadding="5" cellspacing="5" align="center" border="1">
			<tr>
				<td>帐号</td>
				<td>姓名</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${list }" var="userInfo">
				<tr>
					<td width="120">${userInfo.userName }</td>
					<td width="120">${userInfo.passWord }</td>
					<td>
						<a href="${root }/mongo/delete.do?id=${userInfo.objectId}">删除</a>
						<a href="${root }/mongo/update.do?id=${userInfo.objectId}">编辑</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</center>
</body>
</html>