
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta http-equiv="refresh" content="20">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>经销商汇总订单</title>
	<%@ include file="../common/head.jsp"%>
    <style>
        .order-track {
            position: absolute;
            top: 36px;
            right: 0;
            padding: 6px 18px;
            width: 300px;
            background: #ccc;
        }
    </style>
</head>
<body>
<div class="wrap-bd">
    <div class="mb-default">
        <a class="crumb" href="javascript:location.reload();">经销商汇总订单</a><a class="crumb crumb-active">列表</a>
    </div>
    <div class="mb-small">
	    <form id="orderFrom" action="${root }/scms/scOrder/listPage.do">
	        <input type="text" class="input input-search-text" name="managerName" placeholder="请输入经销商名称/汇总订单编号" value="${maOrderInfo.managerName }">
	        <input type="hidden" name="status" value="${maOrderInfo.status}">
	        <input type="button" class="input input-search-button" value="搜索" onclick="getRadio()">
	    </form>
    </div>
    <div class="fl mt-small" id="orderNav">
		<span onclick="pillClick('0')" class="pills <c:if test='${maOrderInfo.status==0}'>pills-active</c:if>">全部订单</span>
		<span onclick="pillClick('1')" class="pills <c:if test='${maOrderInfo.status==1}'>pills-active</c:if>">未接单</span>
		<span onclick="pillClick('2')" class="pills <c:if test='${maOrderInfo.status==2}'>pills-active</c:if>">待入库</span>
		<span onclick="pillClick('3')" class="pills <c:if test='${maOrderInfo.status==3}'>pills-active</c:if>">已入库</span>
	</div>
    <div id="orderTable">
    	<c:forEach items="${orderList}" varStatus="i" var="order" >
	    	<table class="table-list table-border mb-small">
	        <thead class="table-thead">
	        <tr>
	            <th colspan="8" class="ta-l">
	                <b>采购单号：${order.orderId }</b>
	                <div style="font-weight: normal;">
						<%--<span>经销商：<a href="${root}/scms/manager/addOrUpdate.do?id=${order.managerId}">${order.managerName }</a></span> --%>
						<span>经销商：${order.managerName }</span>
	                    <span class="ml-default">区域：${order.groupName }</span>
	                    <span class="ml-default">仓库：${order.warehouseName }</span>
	                    <span class="ml-default">订单总量：${order.totQuantity }</span>
	                    <span class="ml-default">货款总额：￥${order.orderPrice }</span>
	                    <span class="ml-default">运费：￥
	                    	<c:choose>
	                    		<c:when test="${order.whFreight == null || order.whFreight == ''}">0.00</c:when>
	                    		<c:otherwise>${order.whFreight }</c:otherwise>
	                    	</c:choose>
	                    </span>
	                    <span class="ml-default">
	                    	<c:choose>
	                    		<c:when test="${order.kfStatus != 3 }">未提交</c:when>
	                    		<c:when test="${order.whPayStatus == false}">仓库未接单</c:when>
	                    		<c:when test="${order.managerStatus == 1}">未发货（经销商）</c:when>
	                    		<c:when test="${order.managerStatus == 2}">配送中（经销商）</c:when>
	                    		<c:when test="${order.warehouseStatus == 1}">待入库（仓库）</c:when>
	                    		<c:when test="${order.warehouseStatus == 2}">未派送（仓库）</c:when>
	                    		<c:when test="${order.warehouseStatus == 3}">配送中（仓库）</c:when>
	                    		<c:when test="${order.warehouseStatus == 4}">确认收货</c:when>
	                    		<c:otherwise>其他</c:otherwise>
	                    	</c:choose>
	                    </span>
	                    <div class="fr pos-r">
	                    	<c:if test="${order.kfStatus != 3}"><input type="button" class="button button-default" value="提交订单" onclick="orderSubmit('${order.id}')"></c:if>
	                        <a href="${root}/scms/scOrder/printOrder.do?id=${order.id}" class="button button-default" >打印订单</a>
	                        <input type="button" class="button button-default J_orderTrack" value="订单跟踪">
	                        <div class="order-track hidden J_orderTrackDetail">
	                            <c:if test="${order.addTime != null &&order.addTime != ''}"><label class="label mr-default">订单已生成</label> <fmt:formatDate value="${order.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/><br></c:if>
	                            <c:if test="${order.kfSubmitTime != null &&order.kfSubmitTime != ''}"><label class="label mr-default">订单已提交</label> <fmt:formatDate value="${order.kfSubmitTime}" pattern="yyyy-MM-dd HH:mm:ss"/><br></c:if>
								<c:if test="${order.whPayTime != null &&order.whPayTime != ''}"><label class="label mr-default">仓库已接单</label> <fmt:formatDate value="${order.whPayTime}" pattern="yyyy-MM-dd HH:mm:ss"/><br></c:if>
								<c:if test="${order.managerCheckTime != null &&order.managerCheckTime != ''}"><label class="label mr-default">经销商出库</label> <fmt:formatDate value="${order.managerCheckTime}" pattern="yyyy-MM-dd HH:mm:ss"/><br></c:if>
								<c:if test="${order.warehouseTime != null &&order.warehouseTime != ''}"><label class="label mr-default">订单入库(仓库)</label> <fmt:formatDate value="${order.warehouseTime}" pattern="yyyy-MM-dd HH:mm:ss"/><br></c:if>
								<c:if test="${order.fistTime != null &&order.fistTime != ''}"><label class="label mr-default">配送中(仓库)</label><fmt:formatDate value="${order.fistTime}" pattern="yyyy-MM-dd HH:mm:ss"/><br></c:if>
								<c:if test="${order.endTime != null &&order.endTime != ''}"><label class="label mr-default">确认收货</label><fmt:formatDate value="${order.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/><br></c:if>
								<c:if test="${order.acPayTime != null &&order.acPayTime != ''}"><label class="label mr-default">订单已付款</label> <fmt:formatDate value="${order.acPayTime}" pattern="yyyy-MM-dd HH:mm:ss"/></c:if>
	                        </div>
	                    </div>
	                </div>
	            </th>
	        </tr>
	        <tr>
	            <th>条形码</th>
	            <th>商品</th>
	            <th>规格</th>
	            <th>数量</th>
	            <th>货款金额</th>
	            <th>配送费</th>
	            <th></th>
	        </tr>
	        </thead>
	        <tbody class="table-tbody">
	        	<c:forEach items="${order.scOrderDetails}" varStatus="i" var="detail" >
			        <tr>
			            <td>${detail.barCode }</td>
			            <td>${detail.name }</td>
			            <td>${detail.spec }</td>
			            <td>${detail.quantity }${detail.pkg }</td>
			            <td>￥${detail.freight+detail.totalPrice }</td>
			            <td>￥${detail.freight }</td>
			            <td>
			                <a href="${root}/scms/scOrder/listDetailPage.do?itemBaseId=${detail.itemId }&maOrderInfoId=${order.id }" class="button button-operate">明细</a>
			            </td>
			        </tr>
		        </c:forEach>
	        </tbody>
	    </table>
	    </c:forEach>
    </div>
    <c:if test="${fn:length(orderList)>0}">
		  <%@ include file="../common/pagination-kk.jsp"%>
   	</c:if>
</div>
<script>
    function orderSubmit(id){
    	layer.msg('提交中', {icon: 16});
    	$.post("${root}/scms/scOrder/orderSubmit.do",{id:id},function(data){
			if(data.success){
				layer.closeAll('loading');
				layer.msg(data.message, {
				    icon: 1,
				    time: 2000 //2秒关闭（如果不配置，默认是3秒）
				}, function(){
					getRadio();
				});
			}else{
				layer.closeAll('loading');
				layer.msg(data.message, {icon: 5});
			}
		 },'json');
    }
    $(function(){
    	$('#orderTable').on('mouseover', '.J_orderTrack', function() {
            $(this).next('.J_orderTrackDetail').show();
        }).on('mouseout', '.J_orderTrack', function() {
        	$(this).next('.J_orderTrackDetail').hide();
        });
    });
    function getRadio(){
    	var url="${root}/scms/scOrder/listPage.do";
    	var managerName= $('input[name="managerName"]').val();
    	var status= $('input[name="status"]').val();
    	if(managerName != null &&managerName != undefined && managerName != ''){
    		   url=url+"?managerName="+managerName;
    	}
    	if(status != null &&status != undefined && status != ''){
    		   url=url+"?status="+status;
    	}
    	location.href=url;
    }
    function pillClick(status){
    	$('input[name="status"]').val(status);
    	getRadio();
    }
</script>
</body>
</html>