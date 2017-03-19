<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>经销商汇总订单明细</title>
	<%@ include file="../common/head.jsp"%>
</head>
<body>
<div class="wrap-bd">
    <div class="mb-default">
        <a class="crumb" href="${root}/scms/scOrder/listPage.do">经销商汇总订单</a>
        <a class="crumb crumb-active">明细</a>
    </div>
    <div class="wrap-bd bg mb-small clearfix">
    	<input name="itemBaseId" type="hidden" value="${orderInfo.itemBaseId }">
    	<input name="maOrderInfoId" type="hidden" value="${orderInfo.maOrderInfoId }">
    	<c:choose>
       		<c:when test="${orderInfo.imgS != '' && orderInfo.imgS != null && orderInfo.imgS != 'null'}">
           		<img class="fl" src="${USER_FASTFDSPREURL }${orderInfo.imgS}" width="90" height="90" alt="缩略图" onerror="javascript:this.src='${USER_DEFAULTIMG_URL}'"/>
           	</c:when>
           	<c:otherwise>
           		<img class="fl" src="${USER_DEFAULTIMG_URL}" width="90" height="90" alt="缩略图"/>
           	</c:otherwise>
       	</c:choose>
        <div class="fl ml-default">
            ${orderInfo.name}<br>
            <span style="font-size: 12px">规格：${orderInfo.spec}</span><br>
            <span>区域：${orderInfo.groupName }</span>
            <span class="ml-default">仓库：${orderInfo.warehouseName}</span>
            <span class="ml-default">总数：${orderInfo.totQuantity}${orderInfo.pkg}</span>
            <span class="ml-default">总金额：￥${orderInfo.totalPrice }</span>
            <span class="ml-default">自提：
            	<c:choose>
            		<c:when test="${orderInfo.ordertype2 != null && orderInfo.ordertype2 != ''}">
            			${orderInfo.ordertype2 }
            		</c:when>
            		<c:otherwise>
           				0
           			</c:otherwise>
            	</c:choose>
            	${orderInfo.pkg}
            </span>
            <span class="ml-default">送货上门：
            	<c:choose>
            		<c:when test="${orderInfo.ordertype0 != null && orderInfo.ordertype0 != ''}">
            			${orderInfo.ordertype0 }
            		</c:when>
            		<c:otherwise>
           				0
           			</c:otherwise>
            	</c:choose>
            	${orderInfo.pkg}
			</span>
        </div>
    </div>
    <table class="table-list table-border">
        <thead class="table-thead">
        <tr>
            <th>订单编号</th>
            <th>批发商名称</th>
            <th>经销区域</th>
            <th>单价</th>
            <th>数量</th>
            <th>总计</th>
            <th>配送方式</th>
            <th>订单提交时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody class="table-tbody">
        	<c:forEach items="${list}" varStatus="i" var="order" >
	        	<tr>
			        <td>${order.orderId}</td>
					<td>${order.supplierName}</td>
					<td>${order.groupName}</td>
					<td>￥${order.zjjfPrice}</td>
					<td>${order.quantity}${orderInfo.pkg}</td>
					<td>￥${order.totalPrice}</td>
					<td>
						<c:choose>
		            		<c:when test="${order.ordertype == 0}"> 送货上门 </c:when>
		            		<c:when test="${order.ordertype == 2}"> 自提 </c:when>
		            	</c:choose>
            	</td>
					<td><fmt:formatDate value="${order.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td><a href="${root}/scms/purchase/listdetail.do?orderId=${order.orderId}">查看订单</a></td>
				</tr>
			</c:forEach>
        </tbody>
    </table>
    <c:if test="${fn:length(list)>0}">
		  <%@ include file="../common/pagination-kk.jsp"%>
   	</c:if>
</div>
<script>
function getRadio(){
	var url = "${root}/scms/scOrder/listDetailPage.do?itemBaseId="+$('input[name="itemBaseId"]').val()+"&maOrderInfoId=" + $('input[name="maOrderInfoId"]').val();
	window.location.href=url;
}
</script>
</body>
</html>