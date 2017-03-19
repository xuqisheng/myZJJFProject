<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>供货单列表</title>
	<%@ include file="../common/head.jsp"%>
</head>
<body>
<div class="wrap-bd">
	<div class="mb-small clearfix">
		<form action="${root }/Customer/active/toSeeOrders.do" method="post" class="fr">
			<input class="input input-search-text" type="text" name="orderId" placeholder="订单号" value="${orderId}">
			<input type="hidden" name="activeId" value="${activeId}">
			<input type="hidden" name="spId" value="${spId}" id="status">
			<input type="hidden" name="storeId" value="${storeId}" id="status">
			<input type="submit" class="input input-search-button" value="搜索">
		</form>
	</div>
	<div>
		<table class="table-list table-border">
			<thead class="table-thead">
				<tr>
					<th>订单编号</th>
					<!-- <th>店主昵称</th> -->
					<th>店铺联系人</th>
					<th>手机号</th>
					<th>店名</th>
					<th>金额合计</th>
					<!-- <th>批发商手机号</th>
					<th>批发商名称</th> -->
					<th>支付方式</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody class="table-tbody">
				<c:forEach var="spOrderInfo" items="${list}">
					<tr>
						<td>${spOrderInfo.orderId }</td>
						<%-- <td>${spOrderInfo.userName }</td> --%>
						<td>${spOrderInfo.consignee }</td>
						<td>${spOrderInfo.mobile }</td>
						<td>${spOrderInfo.storeName }</td>
						<td class="txt-warn">${spOrderInfo.orderPrice }</td>
						<%-- <td>${spOrderInfo.supplierTel }</td>
						<td>${spOrderInfo.supplierNam }</td> --%>
						<td>
							<c:choose>
								<c:when test="${spOrderInfo.supportmetho==1 }">
									银行卡支付
								</c:when>
								<c:when test="${spOrderInfo.supportmetho==2 }">
									货到付款
								</c:when>
								<c:when test="${spOrderInfo.supportmetho==3 }">
									支付宝支付
								</c:when>
								<c:when test="${spOrderInfo.supportmetho==4 }">
									微信支付
								</c:when>
							</c:choose>
						</td>
						<td>
							<c:if test="${spOrderInfo.supportmetho==2}">
								<a href="${root }/Customer/SpOrderInfo/getSpOrderInfo.do?orderid=${spOrderInfo.orderId}">详情</a>
							</c:if>
							<c:if test="${spOrderInfo.supportmetho != 2}">
								<c:if test="${spOrderInfo.supportStatus != 1}">
									<span class="txt-warn">未支付</span>
								</c:if>
								<c:if test="${spOrderInfo.supportStatus == 1}">
									<a href="${root }/Customer/SpOrderInfo/getSpOrderInfo.do?orderid=${spOrderInfo.orderId}">详情</a>
								</c:if>
							</c:if>
							<c:if test="${!empty service && spOrderInfo.status==2 && spOrderInfo.level==2}">
							     &nbsp;<a href="${root }/Customer/SpOrderInfo/getSpOrderInfo.do?orderid=${spOrderInfo.orderid}&send_agin=1">重新派送</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<c:if test="${fn:length(list)>0}">
		<%@ include file="../common/pagination-kk.jsp"%>
	</c:if>
</div>
</body>
</html>