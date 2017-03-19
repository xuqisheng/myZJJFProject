<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>促销管理</title>
<%@ include file="../common/head.jsp"%>
</head>
<body>
	<form action="#" id="formId">
		<input type="hidden" name="id" value="${condition.id}">
	</form>
	<div class="wrap-bd">
		<div>
			<span style="font-size: 14px;">当前位置：</span> <a class="crumb">促销管理</a><span
				class="crumb crumb-active">活动情况</span>
		</div>
		<div class="title mb-small">活动情况</div>
		<table class="table-list table-border">
			<thead class="table-thead">
				<tr>
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																													<th>客户名称</th>
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																													<th>订单编号</th>
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																													<th>优惠券面额</th>
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																													<th>批发商分摊</th>
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																													<th>批发商成本</th>
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																													<th>转角分摊</th>
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																													<th>转角成本</th>
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																													<th>订单时间</th>
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																													<th>操作</th>
				</tr>
			</thead>
			<tbody class="table-tbody" id="table-tbody">
			</tbody>
			<tfoot class="bg">
				<tr>
					<td colspan="10"><%@ include file="../common/pagination.jsp"%></td>
				</tr>
				<tr>
					<td colspan="4" class="ta-l">总参与人次：${count}</td>
					<td>总成本：</td>
					<td><c:if test="${num==null}">0</c:if>
						<c:if test="${num!=null}">${num}</c:if>元</td>
					<td></td>
					<td></td>
					<td colspan="2"></td>
				</tr>
			</tfoot>
		</table>
	</div>
	<script type="text/javascript">
	
	
$(function() {
	 $("#jpagination").pagination({
        pageSize:10,
        remote: {
            url: '${root}/scms/promotion/moneycheck.do',
            params:$("#formId").serialize(),
            success: function(data) {
                var html='';
                $.each(data.list, function(i,item) {
                    html+='<tr><td>'+item.name+'</td>';
                	html+='<td>'+item.orderId+'</td>';
	                html+='<td>'+item.money+'</td>';
	                html+='<td>'+item.plantHalve+'%</td>';
                    html+='<td>'+floatvar((item.money*item.plantHalve)/100)+'</td>';
                    html+='<td>'+(100-item.plantHalve)+'%</td>';
                    html+='<td>'+(item.money-floatvar(item.money*item.plantHalve/100)).toFixed(2)+'</td>';
                    html+='<td>'+item.time+'</td>';
	                html+='<td><a target="_blank" href="${root}/scms/orderctl/GetSpOrderInfo.do?storeid='+item.orderId+'" >订单详情</a></td></tr>';
                });
                if(html == "") {
                 	html = '<tr><td colspan="10" class="no-data"></td></tr>';
                 }
                $('#table-tbody').html(html);
            },
            totalName:'totalSize'
        }
   });
   
   
});

function floatvar(floatvar){ 
    var f_x = parseFloat(floatvar); 
    if (isNaN(f_x)){ 
        return '0.00'; 
    } 
    var f_x = Math.round(f_x*100)/100; 
    var s_x = f_x.toString(); 
    var pos_decimal = s_x.indexOf('.'); 
    if (pos_decimal < 0){ 
        pos_decimal = s_x.length; 
        s_x += '.'; 
    } 
    while (s_x.length <= pos_decimal + 2){ 
        s_x += '0'; 
    } 
    return s_x; 
}
</script>
</body>
</html>
