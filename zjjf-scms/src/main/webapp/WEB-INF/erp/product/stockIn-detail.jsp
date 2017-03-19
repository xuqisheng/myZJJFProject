<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="${root}/resources/css/base.css" />
		<link rel="stylesheet" href="${root}/resources/css/order-detail.css">
		<script src="${root}/resources/vendor/jquery/jquery-1.12.4.min.js"></script>
		<script src="${root}/resources/js/comm.js"></script>
		<script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
		<script src="${root}/resources/vendor/drag/drag.1.0-min.js"></script>
	</head>
	<style type="text/css">
		label {
			display: inline-block;
			width: 80px;
		}
		li{
			margin-bottom: 18px;
		}
	</style>

	<body>
		<div class="wrap-bd">
			<div class="mb-small">
				<div class="title">采购入库单</div>
			</div>
			<div class="bg wrap-bd clearfix">
                <div class="clearfix mb-default">
                    <ul class="fl" style="width: 33%;">
                        <li>入库单号：${detail.orderId}</li>
                        <li>入库人：${detail.addUserName}</li>
                        <li>入库时间：<fmt:formatDate value="${detail.taskTime}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
                        <li>审核时间：<fmt:formatDate value="${detail.checkTime}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
                    </ul>
                    <ul class="fl" style="width: 33%;">
                        <li>入库仓库：${detail.whName}</li>
                        <li>入库金额：${detail.itemPrice}</li>
                        <li>是否审核：${detail.checkStatusStr}</li>
                        <li>备注：${detail.remark}</li>
                    </ul>
                    <ul class="fl" style="width: 33%;">
                        <li>供应商名称：${detail.managerName}</li>
                        <li>入库数量：${detail.itemQuantity}</li>
                        <li>审核人：${detail.checkUserName}</li>
                    </ul>
                </div>
				<table class="table-list table-border">
					<thead>
						<tr>
							<th>序号</th>
                            <th>商品供应码</th>
                            <th>箱码</th>
                            <th>商品条形码</th>
                            <th>商品名称</th>
                            <th>规格</th>
                            <th>待入库数量</th>
                            <th>入库数量</th>
                            <th>入库价格</th>
                            <th>生产日期</th>
                            <%--<th>备注</th>--%>
						</tr>
					</thead>
					<tbody class="table-tbody">
                        <c:forEach items="${detail.details}" var="ele" varStatus="eleStat">
						<tr>
                            <td>${eleStat.count}</td>
                            <td>${ele.itemCode}</td>
                            <td>${ele.barCode}</td>
                            <td>${ele.mdseId}</td>
                            <td>${ele.name}</td>
                            <td>${ele.spec}</td>
                            <td>${ele.quantity - ele.operateQuantity}</td>
                            <td>${ele.operateStock}</td>
                            <td>${ele.areaPrice}</td>
                            <td><fmt:formatDate value="${ele.productionTime}" pattern="yyyy-MM-dd"/></td>
                            <%--<td>${ele.remark}</td>--%>
                        </tr>
                        </c:forEach>
					</tbody>
					</table>
       			 <input type="button" class="button-cancel mt-default" value="返回"/>
            </div>
		</div>

	</body>
<script>
    $(function () {
        $('.button-cancel').on('click', function() {
            window.location.href = '${root}/scms/ERPPurchaseStock/list/${detail.level}.do';
        });
    })
</script>
</html>
