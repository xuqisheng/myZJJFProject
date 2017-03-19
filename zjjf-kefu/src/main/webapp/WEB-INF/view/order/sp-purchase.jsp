<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>批发商采购订单</title>
	<%@ include file="../common/head.jsp"%>
</head>
<body>
<div class="wrap-bd">
    <div class="mb-default">
        <a class="crumb" href="${root}/scms/purchase/listPage.do">批发商采购订单</a><a class="crumb crumb-active">列表</a>
    </div>
    <div>
        <div class="mb-default">
            <form method="post" action="${root }/scms/purchase/listPage.do"> 
            	<!-- 配送方式：<select id="ordertype" class="select">
            		<option value=''>全部</option>
            		<option value="2">自提</option>
            		<option value="1">货到付款</option>
            		<option value="0">送货上门</option>
            	</select> -->
                <input class="input input-search-text" type="text" id="orderName" name="name" value="${condition.name}" placeholder="请输入订单编号/批发商名称">
                <input class="input input-search-button" value="搜索" type="button" onclick="getRadio()">
            </form>
        </div>
        <div>
        	<c:choose>
		             	<c:when test="${condition.check==1}">
		             		<input type="radio" name="states" value="" onclick="getRadio()" > 全部
		             		<input class="ml-default" type="radio" name="states" value="1" onclick="getRadio()" checked="checked"> 未配送
		             		<input class="ml-default" type="radio" name="states" value="2" onclick="getRadio()"> 配送中
		             		<input class="ml-default" type="radio" name="states" value="3" onclick="getRadio()"> 已完成
				            <input class="ml-default" type="radio" name="states" value="4" onclick="getRadio()" > 未支付
				            <input class="ml-default" type="radio" name="states" value="5" onclick="getRadio()"> 已取消
		             	</c:when>
		             	<c:when test="${condition.check==2}">
		             		<input type="radio" name="states" value="" onclick="getRadio()" > 全部
		             		<input class="ml-default" type="radio" name="states" value="1" onclick="getRadio()" > 未配送
		             		<input class="ml-default" type="radio" name="states" value="2" onclick="getRadio()" checked="checked"> 配送中
		             		<input class="ml-default" type="radio" name="states" value="3" onclick="getRadio()"> 已完成
				            <input class="ml-default" type="radio" name="states" value="4" onclick="getRadio()" > 未支付
				            <input class="ml-default" type="radio" name="states" value="5" onclick="getRadio()"> 已取消
		             	</c:when>
		             	<c:when test="${condition.check==3}">
		             		<input type="radio" name="states" value="" onclick="getRadio()" > 全部
		             		<input class="ml-default" type="radio" name="states" value="1" onclick="getRadio()"> 未配送
		             		<input class="ml-default" type="radio" name="states" value="2" onclick="getRadio()" > 配送中
		             		<input class="ml-default" type="radio" name="states" value="3" onclick="getRadio()" checked="checked"> 已完成
				            <input class="ml-default" type="radio" name="states" value="4" onclick="getRadio()" > 未支付
				            <input class="ml-default" type="radio" name="states" value="5" onclick="getRadio()"> 已取消
		             	</c:when>
		             	<c:when test="${condition.check==4}">
		             		<input type="radio" name="states" value="" onclick="getRadio()" > 全部
		             		<input class="ml-default" type="radio" name="states" value="1" onclick="getRadio()"> 未配送
		             		<input class="ml-default" type="radio" name="states" value="2" onclick="getRadio()"> 配送中
		             		<input class="ml-default" type="radio" name="states" value="3" onclick="getRadio()"> 已完成
				            <input class="ml-default" type="radio" name="states" value="4" onclick="getRadio()"  checked="checked"> 未支付
				            <input class="ml-default" type="radio" name="states" value="5" onclick="getRadio()"> 已取消
		             	</c:when>
		             	<c:when test="${condition.check==5}">
		             		<input type="radio" name="states" value="" onclick="getRadio()" > 全部
		             		<input class="ml-default" type="radio" name="states" value="1" onclick="getRadio()"> 未配送
		             		<input class="ml-default" type="radio" name="states" value="2" onclick="getRadio()"> 配送中
		             		<input class="ml-default" type="radio" name="states" value="3" onclick="getRadio()"> 已完成
				            <input class="ml-default" type="radio" name="states" value="4" onclick="getRadio()" > 未支付
				            <input class="ml-default" type="radio" name="states" value="5" onclick="getRadio()" checked="checked"> 已取消
		             	</c:when>
		             	<c:otherwise>
		             		<input type="radio" name="states" value="" onclick="getRadio()" checked="checked"> 全部
		             		<input class="ml-default" type="radio" name="states" value="1" onclick="getRadio()"> 未配送
		             		<input class="ml-default" type="radio" name="states" value="2" onclick="getRadio()"> 配送中
		             		<input class="ml-default" type="radio" name="states" value="3" onclick="getRadio()"> 已完成
				            <input class="ml-default" type="radio" name="states" value="4" onclick="getRadio()" > 未支付
				            <input class="ml-default" type="radio" name="states" value="5" onclick="getRadio()"> 已取消
		             	</c:otherwise>
		     </c:choose>
            
        </div>
    </div>
    <table class="table-list table-border">
        <thead class="table-thead">
        <tr>
            <th>订单编号</th>
            <th>批发商名称</th>
            <th>联系人</th>
            <th>联系电话</th>
            <th>总金额</th>
            <th>配送方式</th>
            <th>订单状态</th>
            <th>支付状态</th>
            <th>订单提交时间</th>
            <th>管理</th>
        </tr>
        </thead>
        <tbody class="table-tbody">
        
        	<c:forEach items="${list}" varStatus="i" var="item" > 
         		<tr>
	            	<td>${item.orderId}</td>
	            	<td>${item.supplierName}</td>
		            <td>${item.consignee}</td>
		            <td>${item.mobile}</td>
		             <td>￥${item.orderPrice}</td>
		             <td><c:choose>
        					<c:when test="${item.ordertype==1}">货到付款</c:when>
        					<c:when test="${item.ordertype==2}">自提</c:when>
        					<c:when test="${item.ordertype==0}">送货上门</c:when>
        					</c:choose></td>
		             <c:choose>
		             <c:when test="${item.status==1}">
		             		<td>已下单</td>
		             	</c:when>
		             	<c:when test="${item.status==2}">
		             		<td>未配送</td>
		             	</c:when>
		             	<c:when test="${item.status==3}">
		             		<td>配送中</td>
		             	</c:when>
		             
		             	<c:when test="${item.status==4}">
		             		<td>已完成</td>
		             	</c:when>
		             	<c:when test="${item.status==5}">
		             		<td>已取消</td>
		             	</c:when>
		             	<c:otherwise>
		             		<td></td>
		             	</c:otherwise>
		             </c:choose>
		           <c:choose>
		             	<c:when test="${item.supportStatus==0}">
		             		<td>未支付</td>
		             	</c:when>
		             	<c:when test="${item.supportStatus==1}">
		             		<td>已支付</td>
		             	</c:when>
		             	<c:otherwise>
		             		<td></td>
		             	</c:otherwise>
		             </c:choose>
		             <td><fmt:formatDate value="${item.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	            	<td>
		                <a href="${root}/scms/purchase/listdetail.do?orderId=${item.orderId}" class="button button-operate">详情</a>
		            </td> 
           
        	</tr>  
        </c:forEach>  
       
        </tbody>
    </table>
    <c:if test="${fn:length(list)>0}">
		  <%@ include file="../common/pagination-kk.jsp"%>
    </c:if>
</div>
<script type="text/javascript">
$(function() {
	$(document).keypress(function(event){  
        var keycode = (event.keyCode ? event.keyCode : event.which);  
        if(keycode == '13'){  
        	var url = kkpager.getHref(1);
            window.location.href=url;
        }  
    });   
})

function getParameter(name) { 
		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); 
		var r = window.location.search.substr(1).match(reg); 
		if (r!=null) return unescape(r[2]); return null;
	}
	
function search (){
	$("input:radio[value='']").attr('checked','true');
	var url = kkpager.getHref(1);
	 window.location.href=url;
	
}

function getRadio(){
	var orderName= $.trim($('#orderName').val());
	var url="${root }/scms/purchase/listPage.do?1=1";
	if(orderName != null &&orderName != undefined && orderName != ''){
		   url=url+"&name="+orderName;
	   }
	var states=$('input[name="states"]:checked ').val(); 
	if(states != null &&states != undefined && states != ''){
		  
		   url=url+"&check="+states;
	   }
	var ordertype=$.trim($('#ordertype').val());
	if(ordertype != null &&ordertype != undefined && ordertype != ''){
		   url=url+"&ordertype="+ordertype;
	   }
	location.href=url;
}

$('#ordertype').val('${condition.ordertype}');

</script>
</body>
</html>