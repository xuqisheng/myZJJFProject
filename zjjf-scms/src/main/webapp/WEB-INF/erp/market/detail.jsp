<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="${root}/resources/css/base.css" />
		<script src="${root}/resources/vendor/jquery/jquery-1.12.4.min.js"></script>
		<script src="${root}/resources/js/comm.js"></script>
		<script src="${root}/resources/vendor/drag/drag.1.0-min.js"></script>

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
				<div class="title">销售退货查看</div>
			</div>
			<div class="bg wrap-bd mb-default">
				<div class="clearfix">
					<ul style="width: 33%;" class="fl">
						<li>退货单号：${detail.orderId}</li>
						<li>联系人：${detail.supplierName}</li>
						<li>退货金额：${detail.itemPrice}</li>
						<li>退货时间：<fmt:formatDate value="${detail.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
						<li>审核时间：<fmt:formatDate value="${detail.checkTime}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
					</ul>
					<ul style="width: 33%;" class="fl">
						<li>出库单号：${detail.pOrderId}</li>
						<%--<li>手机号：${detail.mobile}</li>--%>
						<li>退货数量：${detail.itemQuantity}</li>
						<li>是否审核：${detail.checkStatusStr}</li>
					</ul>
					<ul style="width: 33%;" class="fl">
						<li>店名：${detail.storeName}</li>
						<%--<li>订单金额：${}</li>--%>
						<li>退货人：${detail.addUserName}</li>
						<li>审核人：${detail.checkUserName}</li>
					</ul>
				</div>
			</div>
			<table class="table-list table-border" id="stock_info">
				<thead>
					<tr>
						<th>序号</th>
						<th>商品条形码</th>
						<th>商品名称</th>
						<th>规格</th>
						<th>销售数量</th>
						<th>退货数量</th>
						<th>退货原因</th>
					</tr>
				</thead>
				<tbody class="table-tbody">
                    <c:forEach items="${detail.details}" var="ele" varStatus="eleStat">
                        <tr>
                            <td>${eleStat.count}</td>
                            <td>${ele.barCode}</td>
                            <td>${ele.name}</td>
                            <td>${ele.spec}</td>
                            <td>${ele.quantity}</td>
                            <td>${ele.operateStock}</td>
                            <td>${ele.remark}</td>
                        </tr>
                        </c:forEach>
				</tbody>
			</table>
			<div class="mb-default mt-default">
				<input type="button" class="button-cancel" value="返回"/>
			</div>
		</div>

	</body>
	<script>
        $(function () {
            $('.button-cancel').on('click' , function () {
                window.location.href='${root}/scms/ERPMarketStock/list/2.do';
            });
        });
	</script>

</html>
