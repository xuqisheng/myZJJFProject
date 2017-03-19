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
    <script src="../../resources/vendor/layer/layer.js"></script>
</head>
<body>
<div class="wrap-bd no-print">
    <div class="op-section clearfix">
        <div class="fl">
            <a href="#"><span onclick="statusClick('')" class="pills <c:if test="${scOrderInfoMgRo.status == null}">pills-active</c:if>">全部订单</span></a>
            <a href="#"><span onclick="statusClick('0')" class="pills <c:if test="${scOrderInfoMgRo.status == 0}">pills-active</c:if>">待入库</span></a>
            <a href="#"><span onclick="statusClick('1')" class="pills <c:if test="${scOrderInfoMgRo.status == 1}">pills-active</c:if>">已入库</span></a>
            <a href="#"><span onclick="statusClick('2')" class="pills <c:if test="${scOrderInfoMgRo.status == 2}">pills-active</c:if>">配送中</span></a>
            <a href="#"><span onclick="statusClick('3')" class="pills <c:if test="${scOrderInfoMgRo.status == 3}">pills-active</c:if>">已收货</span></a>
        </div>
        <div class="fr">
            <form id="orderFrom" action="${root }/scms/scOrder/getWarehouseDeliveryList.do">
                <input type="text" class="input-search-text" name="supplierName" value="${scOrderInfoMgRo.supplierName }" placeholder="订单号/收货人/收货人电话">
                <select class="select" name="ordertype" hidden="true">
                    <option value="" <c:if test="${scOrderInfoMgRo.ordertype == null || scOrderInfoMgRo.ordertype == ''}">selected="selected"</c:if>>全部</option>
                    <option value="2" <c:if test="${scOrderInfoMgRo.ordertype == 2}">selected="selected"</c:if>>自提</option>
                    <option value="0" <c:if test="${scOrderInfoMgRo.ordertype == 0}">selected="selected"</c:if>>送货上门</option>
                </select>
                <input type="hidden" name="status" value="${scOrderInfoMgRo.status}">
                <input type="button" class="input-search-button" value="搜索" onclick="getRadio()">
            </form>
        </div>
    </div>
    <c:choose>
    	<c:when test="${fn:length(list) == 0}">
    		<div class="no-data"></div>
    	</c:when>
    	<c:otherwise>
    		<c:forEach items="${list}" varStatus="i" var="order">
			    <table class="mt-small mb-default table-list table-border J_table">
			        <thead class="table-thead">
			        <c:if test="${i.index==0 }">
			            <tr>
			                <th width="20" style="border-bottom:2px solid #e7e8eb"></th>
			                <th width="120" style="border-bottom:2px solid #e7e8eb">商品条码</th>
			                <th width="120" style="border-bottom:2px solid #e7e8eb">商品</th>
			                <th width="120" style="border-bottom:2px solid #e7e8eb">规格</th>
			                <th width="120" style="border-bottom:2px solid #e7e8eb">数量</th>
			                <th width="120" style="border-bottom:2px solid #e7e8eb">货款金额</th>
			                <th width="120" style="border-bottom:2px solid #e7e8eb">配送费</th>
			                <th width="120" style="border-bottom:2px solid #e7e8eb">状态</th>
			            </tr>
		            </c:if>
			        <tr>
			            <th colspan="8" class="ta-l">
			                <div>
			                    <b class="mr-default">订单号：<span class="J_orderId">${order.orderId }</span></b>
			                    <span class="ml-default mr-default">订单时间：<fmt:formatDate value="${order.addTime}" pattern="yyyy年MM月dd日 HH:mm:ss"/></span>
			                    <span class="ml-default">配送方式：
			       					<c:if test="${order.ordertype == 0}">送货上门</c:if> 
			       					<c:if test="${order.ordertype == 2}">自提</c:if>            
			       				</span>
			                </div>
			                <div>
			                    <span class="mr-default">${order.supplierName}</span>&nbsp;&nbsp;
			                    <span class="ml-default mr-default">${order.consignee}</span>&nbsp;&nbsp;
			                    <span class="ml-default mr-default">${order.address}</span>&nbsp;&nbsp;
			                    <span class="ml-default mr-default">${order.mobile}</span>&nbsp;&nbsp;
			                    <span class="ml-default mr-default">已付款</span>
			                    <c:choose>
			                    	<c:when test="${order.status == 4}">
			                    		<span class="ml-default mr-default">已收货</span>&nbsp;&nbsp;
			                    	</c:when>
			                    	<c:otherwise>
			                    		<span class="ml-default mr-default">未收货</span>&nbsp;&nbsp;
			                    	</c:otherwise>
			                    </c:choose>
						                         货款总金额：<span class="txt-warn mr-default">￥${order.orderPrice }</span>
			                	配送费总额： <span class="txt-warn mr-default">￥${order.freight }</span>
			                    <span class="fr">
				                    <c:if test="${order.status < 4}">
				                    	<c:if test="${order.ordertype == 2}"><input type="button" value="自提" class="button J_shfs" <%-- onclick="delivery('ziti','${order.orderId }')" --%>></c:if>
			                        	<c:if test="${order.ordertype == 0}"><input type="button" value="配送" class="button J_shfs" <%-- onclick="delivery('peisong','${order.orderId }')" --%>></c:if>
				                    </c:if>
			                        <input type="button" value="打印清单" class="button J_btnPrint">
			                    </span>
			                </div>
			            </th>
			        </tr>
			        </thead>
			        <tbody class="table-tbody">
				        <c:forEach items="${order.scOrderDetailVos }" varStatus="i" var="detail" >
				        	<tr>
				        	<td width="20">
				        		<input type="checkbox" value="${detail.id }" name="id" class="J_checkbox">
				        	</td>
				            <td width="120" class="ta-l">${detail.barCode }</td>
				            <td width="120">${detail.name }</td>
				            <td width="120">${detail.spec }</td>
				            <td width="120">${detail.quantity }<c:if test="${detail.pkg != null && detail.pkg != '' }">${detail.pkg }</c:if> </td>
				            <td width="120">￥${detail.totalPrice+detail.freight}</td>
				            <td width="120">￥${detail.freight}</td>
				            <td width="120">
				            	<c:if test="${detail.status == 0}">未入库</c:if>
				            	<c:if test="${detail.status == 1}">已入库</c:if>
				            	<c:if test="${detail.status == 2 && order.ordertype == 0}">配送中</c:if>
				            	<c:if test="${detail.status == 2 && order.ordertype == 2}">已自提</c:if>
				            	<c:if test="${detail.status == 3}">已收货</c:if>
				            </td>
				        </tr>
				        </c:forEach>
			        </tbody>
			    </table>
		    </c:forEach>
    	</c:otherwise>
    </c:choose>
    <c:if test="${fn:length(list)>0}">
		  <%@ include file="../common/pagination-kk.jsp"%>
    </c:if>
</div>
<style>
    #J_printSection .table-list th {
        padding: 1px;
        border: 0 none;
        font-size: 12px;
    }
    #J_printSection .table-list td {
        padding: 1px;
        border: 1px solid #000;
        line-height: 14px;
        font-size: 12px;
    }
</style>
<div id="J_printSection" class="hidden">
    <!-- <table class="table-list table-border">
        <thead class="table-thead">
        <tr>
            <th colspan="8">
                <b>转角街坊送货单</b>
                <p class="ta-l">
                    <span class="mr-default">订单编号：8924852527</span>
                    <span class="mr-default">订单时间：2016-1-6 11:32:24</span>
                    <span class="mr-default">地区：南山区</span>
                    <span>配送方式：送货上门</span>
                </p>
                <p class="ta-l">
                    <span class="mr-default">收货人：大昌批发部</span>
                    <span class="mr-default">张老板</span>
                    <span class="mr-default">地址：南山区南新路120号</span>
                    <span>电话：15013245678</span>
                </p>
            </th>
        </tr>
        <tr>
            <th>序号</th>
            <th>箱码</th>
            <th>商品名称</th>
            <th>规格</th>
            <th>单品条形码</th>
            <th>单价</th>
            <th>数量</th>
            <th>小计</th>
        </tr>
        </thead>
        <tbody class="table-tbody">
        <tr>
            <td>1</td>
            <td>1234567891234</td>
            <td>青岛啤酒纯生罐装500ml</td>
            <td>500ml x 12瓶</td>
            <td>6980589593</td>
            <td>￥123</td>
            <td>100箱</td>
            <td>￥12300</td>
        </tr>
        <tr>
            <td colspan="3" class="ta-l">
                客服热线：400-664-3833
            </td>
            <td colspan="5" class="ta-r">
                总数量：400箱&nbsp;&nbsp;&nbsp;&nbsp;
                总计：￥123999
                <br>
                运费：￥100
                <br>
                付款状态：已付款&nbsp;&nbsp;&nbsp;&nbsp;
                实付金额：￥123999
                <br>
                收货人签名：<span class="label"></span>
            </td>
        </tr>
        </tbody>
    </table> -->
</div>
<script>
    $(function() {
		$(".J_shfs").on('click', function() {
			var $checkbox = $(this).parents('.J_table').find('.J_checkbox:checked');
			var ids = [];
			$checkbox.each(function() {
				ids.push($(this).val());
			});
			console.log(ids)
			var orderId = $(this).parents('.J_table').find('.J_orderId').text();
			var $name = $checkbox.parents('tr').find('.J_name');
			var names = [];
			$name.each(function(){
				names.push($(this).text());
			}); 
			$.post("${root}/scms/scOrder/updateGoodsStatus.do",{ids:ids,orderId:orderId,names:names},function(data){
				if(data.success){
					layer.msg(data.message);
					location.reload(); 
				}else{
					layer.msg(data.message);
				}
			},'json');
    	});
    	
        $('.J_btnPrint').on('click', function() {
           
            var orderId = $(this).parents('.J_table').find('.J_orderId').text();
            console.log(orderId)
            var $checkbox = $(this).parents('.J_table').find('.J_checkbox:checked');
			var ids = [];
			$checkbox.each(function() {
				ids.push($(this).val());
			});
			 console.log(ids)
            $.post("${root}/scms/scOrder/printWarehouseOrder.do",{orderId:orderId,ids:ids},function(data){
            	if(data.success){
            		$('.no-print').hide();
            		$('#J_printSection').show();
            		var html = '';
            		html+='<table class="table-list">';
            		html+='<thead>';
            		html+=' <tr>';
            		html+='<th colspan="8">';
            		html+='<b>转角街坊送货单</b>';
            		html+='<div class="ta-l">';
            		html+='<span class="mr-default">订单编号：'+data.message.orderId+'</span>';
            		html+='<span class="mr-default">订单时间：'+data.message.addTime+'</span>';
            		html+='<span class="mr-default">地区：'+data.message.groupName+'</span>';
            		if(data.message.ordertype == 0){
            			html+='<span>配送方式：送货上门</span>';
            		}else if(data.message.ordertype == 2){
            			html+='<span>配送方式：自提</span>';
            		}
            		html+='</div>';
            		html+='<div class="ta-l">';
            		html+='<span class="mr-default">收货人：'+data.message.supplierName+'</span>';
            		html+='<span class="mr-default">'+data.message.consignee+'</span>';
            		html+='<span class="mr-default">地址：'+data.message.address+'</span>';
            		html+='<span>电话：'+data.message.mobile+'</span>';
            		html+='</div>';
            		html+='</th>';
            		html+='</tr>';
            		html+='</thead>';
            		html+='<tbody>';
                    html+='<tr>';
                    html+='<td>序号</td>';
                    html+='<td>箱码</td>';
                    html+='<td>商品名称</td>';
                    html+='<td>规格</td>';
                    html+='<td>单品条形码</td>';
                    html+='<td>单价</td>';
                    html+='<td>数量</td>';
                    html+='<td>小计</td>';
                    html+='</tr>';
            		$.each(data.message.scOrderDetailVos,function(i,item){
            			html+='<tr>';
                		html+='<td>'+(i+=1)+'</td>';
                		html+='<td>'+item.wayCode+'</td>';
                		html+='<td>'+item.name+'</td>';
                		html+='<td>'+item.spec+'</td>';
                		html+='<td>'+item.barCode+'</td>';
                		html+='<td>￥'+item.zjjfPrice+'</td>';
                		if(item.pkg != null && item.pkg != ''){
                			html+='<td>'+item.quantity+''+item.pkg+'</td>';
                		}else{
                			html+='<td>'+item.quantity+'</td>';
                		}
                		
                		html+='<td>￥'+(item.totalPrice + item.freight)+'</td>';
                		html+='</tr>';
            		})
            		html+='<tr>';
            		html+='<td colspan="3" class="ta-l" style="border-right: 0 none">';
            		html+='客服热线：400-664-3833';
            		html+='</td>';
            		html+='<td colspan="5" class="ta-r" style="border-left: 0 none;line-height:18px">';
            		html+='付款状态：已付款&nbsp;&nbsp;&nbsp;&nbsp;';
            		html+='总数量：'+data.message.countNumber+'&nbsp;&nbsp;&nbsp;&nbsp;';
            		html+='总计：￥'+data.message.printPrice+'';
            		html+='<br>';
            		html+='收货人签名：<span class="label"></span>';
            		html+='</td>';
            		html+='</tr>';
            		html+='</tbody>';
            		html+='</table>'; 
            		$("#J_printSection").html(html);
            		window.print();
            		$('#J_printSection').hide();
            		$('.no-print').show();
             	}else{
             		layer.msg(data.message);
             	} 
            })
            
            /* if(confirm('是否打印成功？')) {
                
            } */
        });
    });
    function getRadio(){
    	$('#orderFrom').submit();
    }
    function statusClick(status){
    	$('input[name="status"]').val(status);
    	getRadio();
    }
</script>
</body>
</html>