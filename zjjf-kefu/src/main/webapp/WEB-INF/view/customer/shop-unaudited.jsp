<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>商铺管理 - 待审核列表</title>
<%@ include file="../common/head.jsp"%>
</head>
<body class="wrap-bd">
<div class="mb-small">
	<form action="${root }/customer/appStore/AppStores.do" id="sesrch_form" method="post">
		<input class="input input-search-text" type="text" placeholder="商铺名/手机号" value="${keywords}" id="keywords" name="keywords">
		<input type="button" class="input input-search-button" value="搜索" id="search_btn">
	</form>
</div>
<div>
	<div>
		<table class="table-list table-border">
			<thead class="table-thead">
				<tr>
					<th>连锁编号</th>
					<th>商户名</th>
					<th>店主名</th>
					<th>手机号</th>
					<th>申请时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody class="table-tbody">
				<c:forEach var="applyStore" items="${applyStores}">
					<tr>
						<td>${applyStore.id}</td>
						<td>${applyStore.name}</td>
						<td>${applyStore.contact}</td>
						<td id="mobile">${applyStore.mobile}</td>
						<td><fmt:formatDate value="${applyStore.addtime}"/></td>
						<td>
						    <a href="${root}/customer/appStore/getStoreById.do?id=${applyStore.id}">审核</a>
							<a href="${root}/customer/appStore/deleteAppStoreById.do?id=${applyStore.id}&mobile=${applyStore.mobile}" onclick="return confirm('确定删除该条记录?')" class="delete">删除</a>	
						</td>
					</tr>
				</c:forEach>
				
			</tbody>
		</table>
	</div>
	<c:if test="${fn:length(applyStores)>0}">
		<%@ include file="../common/pagination-kk.jsp"%>
	</c:if>
</div>
<script>
	$(function(){
		$("#search_btn").click(function(){
			$("#sesrch_form").submit();
		});
	});
</script>
</body>
</html>