<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>库存明细</title>
		<%@ include file="../common/head.jsp"%>
		<link rel="stylesheet" type="text/css" href="../../resources/css/base.css" />
		<script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
		<script src="${root}/resources/js/comm.js?vvv"></script>
	</head>
	<body>
		<div class="wrap-bd">
			<div class="mb-small clearfix">
				<div class="fl title">库位明细</div>
			</div>
			<div class="op-section clearfix">
			    <form action="" name="orderid" method="post" id="fm">
			        <label class="ml-default">时间</label>
			        <input type="text" name="beginTime" value="" class="input-search-date J_DATE_START" placeholder="开始时间">&nbsp;至&nbsp;
			        <input type="text" name="endTime" value="" class="input-search-date J_DATE_END" placeholder="结束时间">
			        <label class="ml-default">类型</label>
			        <select name="operationType" class="select" id="operationType">
			        	<option value="" selected="selected">全部</option>
			            <option value="1">出库</option>
			            <option value="2">入库</option>
			            <!-- <option value="3">库存修正</option> -->
			        </select>
			        <input type="hidden" value="${warehouseId }" name="warehouseId">
					<input type="hidden" value="${itemBaseId }" name="itemBaseId">
			        <input type="button" class="input-search-button ml-default" id="queryButton" value="搜索">
			    </form>
		    </div>
			<div class="op-section clearfix">
				<div class="clearfix">
					<div class="fl" style="margin-right: 150px;">商品编号：<span>${mdseId }</span></div>
					<div class="fl" style="margin-right: 150px;">商品名称：<span>${goodsName }</span></div>
					<div class="fl" style="margin-right: 150px;">规格：<span>${spec }</span></div>
					<div class="fl" style="margin-right: 150px;">
						单位：
						<span>
							<c:choose>
								<c:when test="${pkg != null && pkg != '' && pkg != 'null' }">
									${pkg }
								</c:when>
								<c:otherwise>
									箱
								</c:otherwise>
							</c:choose>
						</span>
					</div>
				</div>
			</div>
			<table class="table-list table-border mt-default">
				<thead>
					<tr>
						<th>订单号</th>
						<th>仓库</th>
						<th>库区</th>
						<th>库位</th>
						<th>类型</th>
						<th>出入库数量</th>
						<!-- <th>库存数量</th> -->
						<th>时间</th>
						<th>备注</th>
					</tr>
				</thead>
				<tbody class="table-tbody">

				</tbody>
			</table>
		</div>
		<%@ include file="../common/pagination.jsp"%>
	</body>
	<script type="text/javascript">
	dateRange('.J_DATE_START', '.J_DATE_END',1);
	$("#jpagination").pagination({
	    pageSize: 10,
	    remote: {
	        url: '${root}/ERPStockManager/getPhysicsStockLog.do',
	        params: $('#fm').serializeArray(),
	        success: function(data) {
	            var html='';
	            $.each(data.list, function(i,item) {
	            	html+='<tr >';
					html+='<td>'+item.orderNo+'</td>';
					html+='<td>'+item.whName+'</td>';
					html+='<td>'+item.waName+'</td>';
					html+='<td>'+item.wpName+'</td>';
					html+='<td>'+item.typeName+'</td>';
					html+='<td>'+item.operationNum+'</td>';
					/* html+='<td>'+item.stockNum+'</td>'; */
					html+='<td>'+item.operationTime+'</td>';
					html+='<td>'+ (item.remark == null ? '' : item.remark)+'</td>';
					html+='</tr>';
	            });
	            if(html == "") {
                 	html = '<tr><td colspan="8" class="no-data"></td></tr>';
                }
	            $('.table-tbody').html(html);
	        },
	        totalName:'totalSize'
	    }
	});
	$('#queryButton').on('click', function(e) {
      	$("#jpagination").pagination('setParams', $('#fm').serializeArray());
      	$("#jpagination").pagination('setPageIndex', 0);
      	$("#jpagination").pagination('remote');
     });
	</script>
</html>
