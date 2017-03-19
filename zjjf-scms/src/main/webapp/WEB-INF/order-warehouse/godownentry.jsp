<%@page import="com.corner.core.config.Constants"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>街坊店宝</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <%@ include file="../common/head.jsp"%>
   <script src="${root}/resources/vendor/kkpager-1.3/kkpager.min.js"></script>
    <link rel="stylesheet" type="text/css"	href="${root}/resources/vendor/kkpager-1.3/kkpager_orange_custom.css">
    <script type="text/javascript" src="../../resources/vendor/layer/layer.js"></script>
</head>
<body>
<div class="wrap-bd">
    <div class="op-section mt-small mb-small clearfix">
    <form id="orderFrom" action="${root }/scms/scOrder/getWarehouseOrderList.do">
        <div class="fl">
        	<span class="pills <c:if test="${maOrderInfoMgRo.queryStatus == null && maOrderInfoMgRo.whPayStatus == null}">pills-active</c:if>" onclick="orderStatus(1)">全部订单</span>
        	<span class="pills <c:if test="${maOrderInfoMgRo.queryStatus == null && maOrderInfoMgRo.whPayStatus == 0}">pills-active</c:if>" onclick="orderStatus(4)">未接单</span>
        	<span class="pills <c:if test="${maOrderInfoMgRo.queryStatus == 1 && maOrderInfoMgRo.whPayStatus == null}">pills-active</c:if>" onclick="orderStatus(2)">待入库</span>
        	<span class="pills <c:if test="${maOrderInfoMgRo.queryStatus == 2 && maOrderInfoMgRo.whPayStatus == null}">pills-active</c:if>" onclick="orderStatus(3)">已入库</span>
        	<input type="hidden" value="${maOrderInfoMgRo.queryStatus}" name="queryStatus" id="queryStatus">
        	<input type="hidden" value="${maOrderInfoMgRo.whPayStatus}" name="whPayStatus" id="whPayStatus">
        </div>
        <div class="fr">
            <input class="input-search-text" type="text" value="${maOrderInfoMgRo.orderIdAndSupply}" name="orderIdAndSupply" id="orderIdAndSupply" class="orderIdAndSupply" placeholder="采购单号/经销商">
            <input class="input-search-button ml-default" type="button" value="搜索" onclick="shouSuo()">
        </div>
    </form>
    </div>
    <c:choose>
    	<c:when test="${fn:length(maOrderInfoMgVoList)==0}">
    		<div class="no-data"></div>
    	</c:when>
    	<c:otherwise>
    		<c:forEach var="maOrderInfoMgVo" items="${maOrderInfoMgVoList }" varStatus="v">
			    <table class="mt-small mb-default table-list table-border">
			        <thead class="table-thead">
			        <c:if test="${v.index==0 }">
			            <tr>
			                <th>条形码</th>
				            <th>商品</th>
				            <th>规格</th>
				            <th>数量</th>
				            <th>货款金额</th>
				            <th>配送费</th>
				            <th></th>
			            </tr>
		            </c:if>
			        <tr>
			            <th colspan="8" class="ta-l">
			                <b>采购单号：${maOrderInfoMgVo.orderId }</b>
			                <div>
			                    <span class="mr-default">区域：${maOrderInfoMgVo.groupName }</span>
			                    <span class="ml-default mr-default">订单总量：${maOrderInfoMgVo.countNumber }</span>
			                    <span class="ml-default mr-default">订单状态：
			                        <span class="orange">
			                        	<c:choose>
			                        		<c:when test="${maOrderInfoMgVo.whPayStatus == false }">未接单</c:when>
			                        		<c:when test="${maOrderInfoMgVo.warehouseStatus == 1 }">待入库</c:when>
			                        		<c:otherwise>已入库</c:otherwise>
			                        	</c:choose>
			                    	</span>
								</span>
			                    <span class="ml-default mr-default">经销商：${maOrderInfoMgVo.managerName }</span>
			                    <c:choose>
	                        		<c:when test="${maOrderInfoMgVo.whPayStatus == false }">
	                        			<a href="${root }/scms/whPay/toPay.do?id=${maOrderInfoMgVo.id}" class="button fr">去支付</a>
	                        			
	                        			
	                        		</c:when>
	                        		<c:when test="${maOrderInfoMgVo.warehouseStatus == 1 && maOrderInfoMgVo.managerStatus==2}">
	                        			<a><input type="button" id="ruku" value="确认入库" class="button fr" onclick="ruku('${maOrderInfoMgVo.id}')"></a>	
									</c:when>
	                        	</c:choose>
			                </div>
			            </th>
			        </tr>
			        </thead>
			        <tbody class="table-tbody">
			        <c:forEach var="scOrderDetailVo" items="${maOrderInfoMgVo.scOrderDetailVoList }">
				        <tr>
				        	<td>${scOrderDetailVo.barCode }</td>
				            <td>${scOrderDetailVo.name }</td>
				            <td>${scOrderDetailVo.spec }</td>
				            <td width="180">${scOrderDetailVo.countNum }${scOrderDetailVo.pkg }</td>
				            <td>￥${scOrderDetailVo.freight+scOrderDetailVo.totalPrice }</td>
				            <td>￥${scOrderDetailVo.freight }</td>
				            <td width="180">
				            	<c:choose>
				            		<c:when test="${maOrderInfoMgVo.warehouseStatus == 1 }"></c:when>
				            		<c:when test="${maOrderInfoMgVo.whPayStatus == false }"></c:when>
				            		<c:otherwise>
				            			<a href="${root}/scms/orderDetail/returnWarehouseOrderDetailPage.do?maOrderInfoId=${maOrderInfoMgVo.id}&itemId=${scOrderDetailVo.itemId}">明细</a>
				            		</c:otherwise>
				            	</c:choose>
				            </td>
				        </tr>
			        </c:forEach>
			        </tbody>
			    </table>
		  	</c:forEach>
		  	<div id="kkpager"></div>
    	</c:otherwise>
    </c:choose>
    <c:if test="${fn:length(maOrderInfoMgVoList)>0}">
		  <%@ include file="../common/pagination-kk.jsp"%>
    </c:if>
</div>
 <!-- 入库 -->
 <script type="text/javascript">
	function ruku(id){
		/* var id = "${ScOrderInfoMgVo.id}"; */
		var str = "warehousing";
		$.post("${root}/scms/scOrder/updateOrderStatus.do",{id:id,str:str},function(data){
			if(data.success){
				layer.msg("入库成功！");
				location.reload();
			}else{
				layer.msg("入库失败！");
			}
		})
	}
 </script>

<script type="text/javascript">
	function orderStatus(num){
		$('#queryStatus').val('');
		$('#whPayStatus').val('');
		if(num==1)
			$('#queryStatus').val('');
		else if(num == 2)
			$('#queryStatus').val('1');
		else if(num == 3)
			$('#queryStatus').val('2');
		else if(num == 4)
			$('#whPayStatus').val('0');
		getRadio();
	}

	function shouSuo(){
		getRadio();
	}
	function getParameter(name) { 
		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); 
		var r = window.location.search.substr(1).match(reg); 
		if (r!=null) return unescape(r[2]); return null;
	}
	function getRadio(){
		$('#orderFrom').submit();
	}
</script>

</body>
</html>