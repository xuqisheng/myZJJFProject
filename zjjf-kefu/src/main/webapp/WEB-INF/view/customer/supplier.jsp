<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>批发商列表</title>
    <%@ include file="../common/head.jsp"%>
</head>
<body>
<div class="wrap-bd">
    <div class="mb-default">
        <a class="crumb" href="${root}/customer/supplier/supplierPage.do?s=1">批发商管理</a><a class="crumb crumb-active">批发商列表</a>
    </div>
	<div class="mb-small clearfix">
		<form class="fl" id="searchForm" method="post">
			<input class="input input-search-text" type="text" name="mobile" value="${supplierRo.mobile}" placeholder="请输入批发商名称/手机号查询"/>
			<input class="input input-search-button" value="搜索" type="submit" id="search">
		</form>
		<div class="fr">
			<a class="button button-default" href="${root}/customer/supplier/addSupplierPage.do">添加批发商</a>
		</div>
	</div>
	<table class="table-list">
		<thead>
			<tr>
				<th width="165">批发商名称</th>
				<th>批发商编号</th>
				<th>手机号码</th>
				<th>电话号码</th>
				<th width="80">负责人名称</th>
				<th>负责人电话</th>
				<th width="120">批发商区域</th>
				<th>开户银行卡号</th>
				<th width="120">开户银行名称</th>
				<th width="80">用户状态</th>
				<th width="60">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="su" items="${suppliers}">
				<tr>
				    <td>${su.supplierName}</td>
					<td>${su.supplierCode }</td>
					<td>${su.mobile }</td>
					<td>${su.callNum }</td>
					<td>${su.managerName }</td>
					<td>${su.managerTel }</td>
					<td>${su.areaName }</td>
					<td>${su.bankNum }</td>
					<td>${su.bankName }</td>
					<td>
					<c:choose>
						<c:when test="${su.status==0 }">
						    <span class="txt-warn">关闭</span>
						</c:when>
						<c:when test="${su.status==1 }">
                            <span>审核</span>
						</c:when>
						<c:when test="${su.status==2 }">
                            <span class="txt-success">正常</span>
						</c:when>
					</c:choose>
					</td>
					<td><a href="${root }/customer/supplier/updateSupplierPage.do?id=${su.id}" class="icon-op icon-op-edit" title="编辑"></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<c:if test="${fn:length(suppliers)>0}">
	<%@ include file="../common/pagination-kk.jsp"%>
</c:if>
</body>
<script type="text/javascript">
$(function(){
	//搜索
	$("#search").click(function(){
	    $("#searchForm").attr("action","${root}/customer/supplier/supplierPage.do?s=1");
		$("#searchForm").submit();
	});
});
</script>
</html>