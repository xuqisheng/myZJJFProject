<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>街坊店宝</title>
	<%@ include file="../common/head.jsp"%>
</head>
<body class="wrap-bd">
<div class="mb-small clearfix">
	 <form class="fl" action="${root}/customer/salesman/SalesListView.do?s=1" id="sesrch_form" method="post">
		<input class="input input-search-text" type="text" placeholder="业务员姓名/手机号码" value="${keywords}" id="keywords" name="keywords">
		<input class="input input-search-button" type="button" value="搜索" id="search_btn">
	</form>
	<a class="fr button button-default" href="${root}/customer/salesman/SalesManView.do">业务员录入</a>
</div>
<div>
	<table class="table-list">
		<thead>
			<tr>
				<th>业务员姓名</th>
				<th>手机号码</th>
				<th>电子邮箱</th>
				<th>性别</th>
				<th>所住地址</th>
				<th>分配区域</th>
				<th>身份证号码</th>
				<th>出生日期</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="list" items="${list}">
				<tr>
				    <td>${list.userName}</td>
					<td>${list.mobile}</td>
					<td>${list.email}</td>
					<td>
					<c:if test="${list.gender == 0}">女</c:if>
					<c:if test="${list.gender == 1}">男</c:if>
					</td>
					<td>${list.address}</td>
					<td>${list.areaname}</td>
					<td>${list.identitycard}</td>
					<td><fmt:formatDate value="${list.birthday}" type="date"/></td>
					<td>
					   <a href="${root }/customer/salesman/updateSalesmanView.do?id=${list.id}" class="icon-op icon-op-edit" title="编辑"></a>
					   <a href="${root}/customer/salesman/deleteSalesman.do?id=${list.id}" onclick="return confirm('确定删除该条记录?')" class="icon-op icon-op-del delete" title="删除"></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<c:if test="${fn:length(list)>0}">
	<%@ include file="../common/pagination-kk.jsp"%>
</c:if>
<script>
	$(function(){
		$("#search_btn").click(function(){
			$("#sesrch_form").submit();
		});
	});
</script>
</body>
</html>