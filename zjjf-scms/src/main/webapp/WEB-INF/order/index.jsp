<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>快销宝 - 转角街坊</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <%@ include file="../common/head.jsp"%>

    <script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
    <script src="${root}/resources/js/comm.js?vvv"></script>
</head>
<body>
<div class="wrap-bd">
	<div class="mb-small title">全部订单</div>
	<div class="op-section clearfix">
		<ul class="fl" id="orderNav">
			<span onclick="selectOrder(-1)" date-status="-1" class="pills">全部</span>
			<span onclick="selectOrder(2)" date-status="2" class="pills pills-active">已派单</span>
			<span onclick="selectOrder(3)" date-status="3" class="pills">已提单</span>
			<span onclick="selectOrder(4)" date-status="4" class="pills">已打印</span>
			<span onclick="selectOrder(40)" date-status="40" class="pills">已发货</span>
			<span onclick="selectOrder(50)" date-status="50" class="pills">已送达</span>
			<span onclick="selectOrder(5)" date-status="5" class="pills">已完成</span>
		</ul>
		<form class="fr" id="param">
		下单时间：
			<input class="input-search-date J_s" type="text" id="dateFrom" name="dateFrom" placeholder="选择时间"> -
			<input class="input-search-date J_e" type="text" id="dateTo" name="dateTo" placeholder="选择时间">
			<span class="ml-default">支付方式：</span>
			<select id="supportmetho" name="supportmetho" value="" class="select">
                <option value ="" >请选择</option>
               <%-- <c:forEach var="tradeWay" items="${tradeWays}">
                    <option value ="${tradeWay.index}" >${tradeWay.name}</option>
                </c:forEach>--%>
			      <!-- <option value ="1" >银行卡支付</option> -->
			      <option value ="2" >货到付款</option>
			      <!-- <option value="3">支付宝支付</option> -->
			      <option value="4">微信支付</option>
			      <option value="5">钱包支付</option>
			    </select>
			<input class="input-search-text" type="text" name="storeid" id="storeid" placeholder="订单号/店名" value="">
			<input type="hidden" name="status" value="2" id="status">
			<input type="button" class="input-search-button ml-default" value="搜索"  id="sub"/>
		</form>
	</div>
	<div>
		<table class="table-list table-border">
			<thead class="table-thead">
				<tr class="table-header">
					<th>订单编号</th>
					<th>店名</th>
					<th>联系人</th>
					<th>手机号</th>
					<th>订单金额</th>
					<th>支付方式</th>
					<th>订单状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody class="table-tbody"></tbody>
		</table>
	</div>
    <%@ include file="../common/pagination.jsp"%>
</div>
<input type="hidden" id="pageIndex" value="" />
<script>
layer.load(2);
	$(function() {
		$('.table-list').on('click', 'td', function() {
            $(this).parent().css({
                'background': '#009dd9',
                'color': 'white'
            }).siblings().css({
                'background': '#fff',
                'color': 'black'
            })
        });
 		 $('#orderNav').on('click', '.pills', function() {
 		 	$(this).addClass('pills-active').siblings('.pills').removeClass('pills-active');
 		 });
 		 dateRange('.J_s', '.J_e', 1);
		 $("#jpagination").pagination({
             pageSize: 10,
             remote: {
                 url: '${root}/scms/orderctl/GetSpOrderInfoslist.do',
                 params: $('#param').serializeArray(),
                 success: function(data) {
                	 layer.closeAll('loading');
                     var html='';
                     $.each(data.list, function(i,item) {
                     	html+='<tr><td>'+item.orderId+'</td>';
                     	html+='<td>'+item.storeName+'</td>';
                     	 html+='<td>'+item.consignee+'</td>';
                     	 html+='<td>'+item.mobile+'</td>';
                     	html+='<td class="txt-warn">'+item.orderPrice+'</td>';
                     	if(item.supportmetho==1){
   	                	 html+='<td class="txt-success">银行卡支付</td>';
	   	                }else if(item.supportmetho==2){
	   	                	 html+='<td class="txt-warn">货到付款</td>';
	   	                }else if(item.supportmetho==3){
	   	                	 html+='<td class="txt-success">支付宝支付</td>';
	   	                }else if(item.supportmetho==4){
	   	                	 html+='<td class="txt-success">微信支付</td>';
	   	                }else if(item.supportmetho==5){
	   	                	 html+='<td class="txt-success">钱包支付</td>';
	   	                }else {
	   	                	html+='<td></td>';
	   	                }
                     	if(item.status==2){
                     		html+='<td>已派单</td>';
                     	}else if(item.status==3){
                     		html+='<td>已提单</td>';
                     	}else if(item.status==4){
                     		if(item.logisticsStatus==4){
                     			html+='<td class="txt-success">配送中</td>';
                     		}else if(item.logisticsStatus==5){
                     			html+='<td class="txt-success">已送达</td>';
                     		}else{
                     			html+='<td>已打印</td>';
                     		}
                     	}else if(item.status==5){
                     		html+='<td class="txt-success">已完成</td>';

                        }else {
                            html+='<td></td>';
                        }
                        if(item.status==3 && '${SUPPLY_SESSION_KEY.supplierType}'== 2){
                            html+='<td><a class="button button-operate" href="${root }/scms/ERPSpOrderOwner/toAddERPSpOrderOwner/'+item.orderId+'.do" target="_blank" style="margin-right:12px">附属订单</a>';
                        }else{
                            html+='<td>';
                        }
    	                if(item.supportmetho==2){
    	                	 html+='<a class="button button-operate" href="${root }/scms/orderctl/GetSpOrderInfo.do?storeid='+item.orderId+'" target="_blank">详情</a></td></tr>';
    	                }else{
    	                	if(item.supportStatus == 0) {
    	                		html+='<td><span style="color:red">未支付</span></td></tr>';
    	                	}
    	                	else if(item.supportStatus == 1) {
    	                		html+='<a class="button button-operate" href="${root }/scms/orderctl/GetSpOrderInfo.do?storeid='+item.orderId+'" target="_blank">详情</a></td></tr>';
    	                	}
    	                }
                     });
                     if(html == "") {
                     	html = '<tr><td colspan="10" class="no-data"></td></tr>';
                     }
                     $('.table-tbody').html(html);
                 },
                 totalName:'totalSize'
             }
         });

		 $('#sub').on('click', function(e) {
			 layer.load(2);
			 //$('#orderNav .pills').removeClass('pills-active');
			//$("#status").val("-10");
             var orderId = $('#storeid').val();
             if(orderId != ''&& orderId != null){
                $('#status').val('-1');
                 $('span[date-status="-1"]').addClass('pills-active').siblings('.pills').removeClass('pills-active');
             }
        	$("#jpagination").pagination('setParams', $('#param').serializeArray());
       		$("#jpagination").pagination('setPageIndex', 0);
       	 	$("#jpagination").pagination('remote');
        });

	})

	function selectOrder(num){
		layer.load(2);
		$("#dateFrom").val("");
		$("#dateTo").val("");
		$("#supportmetho").val("");
		$("#storeid").val("");
		$("#status").val(num);
		$("#jpagination").pagination('setParams', $('#param').serializeArray());
   		$("#jpagination").pagination('setPageIndex', 0);
   	 	$("#jpagination").pagination('remote');
	}
</script>
</body>
</html>
