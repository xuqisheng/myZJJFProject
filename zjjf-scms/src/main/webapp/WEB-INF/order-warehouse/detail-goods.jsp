<%@page import="com.corner.core.config.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>街坊店宝</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <%@ include file="../common/head.jsp"%>
    <script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
    <script src="${root}/resources/js/comm.js"></script>
</head>
<body>
<div class="wrap-bd">
    <div>
		当前位置：
        <a href="${root }/scms/scOrder/getWarehouseOrderList.do" class="crumb">订单管理</a>
        <span class="crumb crumb-active">明细</span>
    </div>
    <table class="mt-small mb-default table-list table-border">
        <thead class="table-thead">
        <tr>
            <th colspan="8" class="ta-l bg">
              <c:choose>
              	<c:when test="${scOrderDetailVoCount.img != '' && scOrderDetailVoCount.img != null && scOrderDetailVoCount.img != 'null'}">
              		<img class="fl" src="${USER_FASTFDSPREURL }${scOrderDetailVoCount.img }" width="90" height="90" alt="缩略图" onerror="javascript:this.src='${USER_DEFAULTIMG_URL}'">
              	</c:when>
              	<c:otherwise>
              		<img class="fl" src="${USER_DEFAULTIMG_URL}" width="90" height="90" alt="缩略图">
              	</c:otherwise>
              </c:choose>
                <div class="fl ml-default">
                ${scOrderDetailVoCount.name } <br>
                <input type="hidden" name="maOrderInfoId" id="maOrderInfoId" value="${scOrderDetailVoCount.maOrderInfoId }">
                <input type="hidden" name="itemId" id="itemId" value="${scOrderDetailVoCount.itemId }">
                    <span class="mr-default">规格：${scOrderDetailVoCount.spec }</span>
                    <span class="ml-default mr-default">箱码：${scOrderDetailVoCount.wayCode }</span>
                    <span class="ml-default">单品条形码：${scOrderDetailVoCount.barCode }</span>
                    <br>
                    <span class="mr-default">区域：${scOrderDetailVoCount.areaName }</span>
                    <span class="ml-default">总数：${scOrderDetailVoCount.countNum }<c:if test="${scOrderDetailVoCount.pkg !=null && scOrderDetailVoCount.pkg != ''}">${scOrderDetailVoCount.pkg }</c:if></span>
                    <span class="ml-default">自提：${scOrderDetailVoCount.own }<c:if test="${scOrderDetailVoCount.pkg !=null && scOrderDetailVoCount.pkg != ''}">${scOrderDetailVoCount.pkg }</c:if></span>
                    <span class="ml-default">送货上门：${scOrderDetailVoCount.visit }<c:if test="${scOrderDetailVoCount.pkg !=null && scOrderDetailVoCount.pkg != ''}">${scOrderDetailVoCount.pkg }</c:if></span>
                </div>
            </th>
        </tr>
        <tr>
            <th>订单编号</th>
            <th>批发商名称</th>
            <th>地址</th>
            <th>手机号</th>
            <th>单价</th>
            <th>数量</th>
            <th>配送方式</th>
            <th>订单提交时间</th>
        </tr>
        </thead>
        <tbody class="table-tbody">
        <!-- <tr>
            <td>0099911</td>
            <td>大昌批发行</td>
            <td>南山区桃园路12号</td>
            <td>15012345678</td>
            <td>￥120</td>
            <td>100</td>
            <td>2015-12-11 15:44:15</td>
        </tr> -->
        </tbody>
    </table>
    <%@ include file="../common/pagination.jsp"%>
</div>
 
</body>
<script>
$(function(){
	$("#jpagination").pagination({
	    pageSize: 10,
	    remote: {
	        url: '${root}/scms/orderDetail/getOrderDetailList.do',
	        params: {maOrderInfoId:$("#maOrderInfoId").val(),itemId:$("#itemId").val()},
	        success: function(data) {
	            var html='';
	            $.each(data.list, function(i,item) {
	            	html+='<tr>';
	            	html+='<td>'+item.orderId+'</td>';
	            	html+='<td>'+item.supplierName+'</td>';
	            	html+='<td>'+item.address+'</td>';
	            	html+='<td>'+item.mobile+'</td>';
	            	html+='<td>'+item.zjjfPrice+'</td>';
	            	if(item.pkg != null && item.pkg != ''){
	            		html+='<td>'+item.quantity+item.pkg+'</td>';
	            	}else{
	            		html+='<td>'+item.quantity+'</td>';
	            	}
	            	html+='<td>'+item.ordertype+'</td>';
	            	html+='<td>'+item.supportTime+'</td>';
	            	html+='</tr>';
	            });
	            if(html == "") {
	             	html = '<tr><td colspan="8" class="no-data"></td></tr>';
	            }
	            $('.table-tbody').html(html);
	        },
	        totalName:'totalSize',
	        pageParams: function (data) {
	           	return {
	           			pageIndex: data.pageIndex + 1,
	           			pageSize: data.pageSize
	           		}
	        },
	    }
	});
});
</script>

</html>