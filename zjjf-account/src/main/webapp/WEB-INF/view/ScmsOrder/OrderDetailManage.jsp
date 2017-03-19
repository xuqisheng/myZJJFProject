<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="easyui-layout"  fit="true">
	<div region="center"  style="font-size: 14px;">
		<table cellspacing="16" style="border:1px solid #000;padding:2px;font-size: 14px;width: 800px;" >
			<tr>
				<td>采购单号:</td><td><h1>${scOrderInfo.orderId }</h1></td>
				<td></td><td></td>
				<td></td><td></td>
				<td></td><td></td>
			</tr>
			<tr>
				<td>区域: </td><td>${scOrderInfo.groupName }</td>
				<td>经销商:</td><td>${scOrderInfo.managerName }</td>
				<td>仓库:</td><td>${scOrderInfo.warehouseName }</td>
				<td>状态:</td><td><c:if test="${scOrderInfo.whPayStatus == true}">已接单</c:if><c:if test="${scOrderInfo.whPayStatus == false}">未接单</c:if></td>
			</tr>					
		</table>
		<table style="border:1px solid #000;padding:2px;font-size: 14px;width: 800px;" cellspacing="16">
	        <thead>
	        <tr>
	            <th>条形码</th>
	            <th>商品</th>
	            <th>规格</th>
	            <th>数量</th>
	            <th>货款金额</th>
	            <th>配送成本</th>
	            <th>采购成本</th>
	        </tr>
	        </thead>
	        <tbody>
	        <c:forEach var="scOrderDetail" items="${scOrderInfo.scOrderDetails }">
	        <tr>
	        	<td>${scOrderDetail.barCode }</td>
	            <td>${scOrderDetail.name }</td>
	            <td>${scOrderDetail.spec }</td>
	            <td>${scOrderDetail.quantity }${scOrderDetailVo.pkg }</td>
	            <td>￥${scOrderDetail.freight+scOrderDetail.totalPrice }</td>
	            <td>￥${scOrderDetail.freight }</td>
	            <td>￥${scOrderDetail.areaTotalPrice }</td>
	        </tr>
	        </c:forEach>
	        <tr>
	    		<td colspan="7" style="background:#eef0f6;">
		                        货款总金额：<span>￥${scOrderInfo.orderPrice }</span>
		                        配送成本总金额： <span>￥${scOrderInfo.whFreight }</span>
		                        采购成本总金额：<span>￥${scOrderInfo.outOfPrice }</span>
	            </td>
	        </tr>
	        </tbody>
	    </table>
	    <table cellspacing="16" style="border:1px solid #000;padding:2px;font-size: 14px;width: 800px;" >
			<tr>
				<td>订单生成:</td><td><fmt:formatDate value="${scOrderInfo.addTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>订单提交:</td><td><fmt:formatDate value="${scOrderInfo.kfSubmitTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>订单接单:</td><td><fmt:formatDate value="${scOrderInfo.whPayTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			<tr>
				<td>银行流水号：</td>
				<td>
					<c:if test="${scOrderInfo.whPayStatus == false}"><input type="text" name="whTradeNo" id="whTradeNo"></c:if>
					<c:if test="${scOrderInfo.whPayStatus == true}">${scOrderInfo.whTradeNo}</c:if>
				</td>
				<td>备注：</td>
				<td>
					<c:if test="${scOrderInfo.whPayStatus == false}"><textarea name="whAcRemark" id="whAcRemark" ></textarea></c:if>
					<c:if test="${scOrderInfo.whPayStatus == true}">${scOrderInfo.whAcRemark }</c:if>
				</td>
				<td><span>应收金额:￥${scOrderInfo.orderPrice }</span></td>
				<td>
					<c:if test="${scOrderInfo.whPayStatus == false}"><input type="button" id="isSubmit" value="确认收款"></c:if>
				</td>
			</tr>				
		</table>
	</div>
	<script type="text/javascript">
		$('#isSubmit').on('click' , function(){
			$('#isSubmit').attr('readonly' , "readonly");
			if($.trim($('#whTradeNo').val()) == ''){
				alert("请输入流水号");
				$('#whTradeNo').focus();
			}else{
				$.ajax({
	       			type : "POST",
	       			url : root + '/account/scmsorderinfoctl/orderSubmit.do',
	       			dataType : "json",
	       			data:{id:'${scOrderInfo.id }' , whTradeNo : $('#whTradeNo').val() , whAcRemark : $('#whAcRemark').html()},
	       			success : function(data) {
	       				if (data.success) {
	       					alert(data.message);
	       					var url="/account/scmsorderinfoctl/toMaOrderInfoDetail.do?id=${scOrderInfo.id }";
	       					addTab("详情页",url,"icon icon-sys");
	       				}else{
	       					alert(data.message);
	       				}
	       			},
	       			error : function(data) {
	       			}
	       		});
			}
		});
	</script>
</div>

