<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>经销商汇总订单 - 打印清单</title>
	<%@ include file="../common/head.jsp"%>
</head>
<body>
<div class="wrap-bd">
    <div class="mb-default no-print">
        <a class="crumb" href="javascript:location.href=document.referrer;">经销商汇总订单</a>
        <a class="crumb crumb-active">打印清单</a>
    </div>
    <table class="table-list table-border">
        <thead class="table-thead">
        <tr>
            <th colspan="5" class="ta-l">
                <b>采购单号：${orderInfo.orderId }</b>
                <div style="font-weight: normal;">
					<%--<span>经销商：<a href="${root}/scms/manager/addOrUpdate.do?id=${order.managerId}">${order.managerName }</a></span> --%>
					<span>经销商：${orderInfo.managerName }</span>
                    <span class="ml-default">区域：${orderInfo.groupName }</span>
                    <span class="ml-default">订单总量：${orderInfo.totQuantity }</span>
                </div>
            </th>
        </tr>
        <tr>
            <th>订单编号</th>
            <th>批发商名称</th>
            <th>订单提交时间</th>
            <th>商品名称</th>
            <th>数量</th>
        </tr>
        </thead>
        <tbody class="table-tbody">
        <c:forEach items="${list}" varStatus="i" var="order">
	        <tr>
	            <td>${order.orderId}</td>
	            <td>${order.supplierName}<br>
			       ${order.address}南山区南新路120号
	            </td>
	            <td><fmt:formatDate value="${order.supportTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	            <td>
	            <c:forEach items="${order.scOrderDetailVos}" varStatus="i" var="detail">
	            	${detail.name}  规格：${detail.spec}<br> 
			    </c:forEach>
	            </td>
	            <td>
	            <c:forEach items="${order.scOrderDetailVos}" varStatus="i" var="detail">
	            	${detail.quantity}${detail.pkg}<br> 
	            </c:forEach>
	            </td>
	        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script>
	$(function(){
		window.print();
		location.href=document.referrer;
	});
</script>
</body>
</html>