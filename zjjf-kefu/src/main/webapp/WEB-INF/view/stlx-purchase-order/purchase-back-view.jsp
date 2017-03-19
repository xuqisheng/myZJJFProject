<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setAttribute("root", request.getContextPath());%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>采购退货</title>
		<link rel="stylesheet" type="text/css" href="${root}/resources/css/base.css" />
		<script src="${root}/resources/vendor/jquery/jquery-1.12.4.min.js"></script>
		<script src="${root}/resources/js/comm.js"></script>
	</head>
	<style type="text/css">
		ul {
			list-style: none;
			padding: 0;
			margin: 0;
		}
		
		li {
			margin-bottom: 18px;
		}
	</style>

	<body>
		<div class="wrap-bd">
			<div class="mb-small">
				<a href="${root}/kefu/erpOrder/toPurchaseBaceOrder.do" class="crumb">采购管理</a>
				<a href="${root}/kefu/erpOrder/toPurchaseBaceOrder.do" class="crumb">采购退货管理</a>
				<a href="#" class="crumb">采购退货查看</a>
			</div>
			<div class="mb-small clearfix">
				<div class="fl mt-default">采购退货管理</div>
			</div>
			<div class="bg wrap-bd mb-default">
				<div class="clearfix">
					<ul style="width: 33%;" class="fl">
						<li>退货单号：${purchaseStockInfo.orderId}</li>
						<li>退货人：${purchaseStockInfo.addUserName}</li>
						<li>退货时间：<fmt:formatDate value="${purchaseStockInfo.addTime}" pattern="yyyy-MM-dd HH:mm:ss" /></li>
						<li>审核时间：<fmt:formatDate value="${purchaseStockInfo.checkTime}" pattern="yyyy-MM-dd HH:mm:ss" /></li>
					</ul>
					<ul style="width: 33%;" class="fl">
						<li>退货仓库：${purchaseStockInfo.whName}</li>
						<li>退货金额：${purchaseStockInfo.totalPrice}</li>
						<li>是否审核：
						  <c:if test="${purchaseStockInfo.checkStatus==1}">
						     未审核
						  </c:if>
						  <c:if test="${purchaseStockInfo.checkStatus==2}">
						     已审核
						  </c:if>
						</li>
						<li>备注：${purchaseStockInfo.remark}</li>
					</ul>
					<ul style="width: 33%;" class="fl">
						<li>供应商名称：${purchaseStockInfo.managerName}</li>
						<li>退货数量：${purchaseStockInfo.totalProductNum}</li>
						<li>审核人：${purchaseStockInfo.checkUserName}</li>
					</ul>
				</div>
			</div>

			<table class="table-list mb-default">
				<thead>
					<tr>
						<th>序号</th>
						<th>商品供应码</th>
						<th>箱码</th>
						<th>商品条形码</th>
						<th>商品名称</th>
						<th>规格</th>
						<!-- <th>入库数量</th> -->
						<th>退货数量</th>
						<th>备注</th>
					</tr>
				</thead>
				<tbody class="table-tbody">
				<c:forEach items="${purchaseStockDetailVos}" var="item" varStatus="status">
					<tr>
						<td>${status.index+1}</td>
						<td>${item.itemCode}</td>
						<td>${item.mdseId}</td>
						<td>${item.barCode}</td>
						<td>${item.name}</td>
						<td>${item.spec}</td>
						<%-- <td>${item.operateQuantity}</td> --%>
						<td>${item.operateStock}</td>
						<td>${item.remark}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			<div class="mb-default">
				<input type="button" class="button button-ok goBack" name="" id="" value="确认" />
				<input type="button" class="button button-cancel goBack" name="" id="" value="取消" />
			</div>
		</div>
	</body>
	<script>
	$('.goBack').on('click',function(){
		location.href='${root}/kefu/erpOrder/toPurchaseBaceOrder.do';
	})
	</script>

</html>