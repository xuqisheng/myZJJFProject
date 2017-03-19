<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>

<head>
	<meta charset="UTF-8">
	<title>供应商管理</title>
	<link rel="stylesheet" type="text/css" href="${root }/resources/css/base.css" />
	<script src="${root }/resources/vendor/jquery/jquery-1.12.4.min.js"></script>
	<script src="${root }/resources/js/comm.js"></script>
</head>
<style type="text/css">
	.t_sp {
		display: inline-block;
		width: 22%;
	}
</style>

<body>
	<div class="wrap-bd">
		<div class="">
			<div class="mb-default title">损益单详情</div>
		</div>
		<div class="bg wrap-bd">
			<div>
				<span class="t_sp">单据编号：${detail.orderId}</span>
				<span class="t_sp">单据类型：${detail.typeStr}</span>
				<span class="t_sp">创建人：${detail.addUserName}</span>
				<span class="t_sp">创建时间：<fmt:formatDate value="${detail.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
			</div>
			<table class="table-list table-border mt-default" id="stock_info">
				<thead>
					<tr>
						<th>序号</th>
						<th>商品条形码</th>
						<th>商品名称</th>
						<th>规格</th>
						<th>单位</th>
						<th>数量</th>
						<th>金额</th>
						<th>备注</th>
					</tr>
				</thead>
				<tbody class="table-tbody">
					<c:forEach items="${detail.details}" var="ele" varStatus="eleStat">
						<tr>
							<td>${eleStat.count}</td>
							<td>${ele.barCode}</td>
							<td>${ele.name}</td>
							<td>${ele.spec}</td>
							<td>${ele.pkg}</td>
							<td>${ele.quantity}</td>
							<td>${ele.price}</td>
							<td>${ele.remark}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<input type="button" class="button-cancel mt-default" value="返回"/>
		</div>

	</div>
    <script>
        $(function () {
            $('.button-cancel').on('click', function() {
                window.location.href = '${root}/scms/ERPProfit.do';
            });
        })
    </script>
</body>

</html>
