<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>街坊店宝 - 作业管理</title>
	<%@ include file="../common/head.jsp"%>
	<script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
	<script src="${root}/resources/js/comm.js"></script> 
	<script src="${root}/resources/js/comm.js?v"></script>
</head>
<body>
<div class="wrap-bd">
    <div class="mb-small clearfix">
        <form class="fl" method="get" action="${root}/task/spOrder/selectSpOrderSales.do">
            <input class="input input-search-text" type="text" name="spName" value="${ro.spName }" placeholder="批发商名称">
            <input class="input-search-date" type="text" name="addTime" id="addTime" value="${ro.addTime}" placeholder="请选择日期">
            <input class="input input-search-button" value="搜索" type="submit">
        </form>
    </div>
    <div>
        <table class="table-list table-border">
            <thead class="table-thead">
            <tr class="table-header">
            	<th width="30%">批发商</th>
            	<th width="30%">名称</th>
                <th width="10%">条码</th>
                <th width="20%">规格</th>
                <th width="10%">销量</th>
            </tr>
            </thead>
            <tbody class="table-tbody">
	            <c:forEach items="${list}" var="task">
	            	<tr>
		            	<td>${task.supplierName}</td>
		            	<td>${task.name}</td>
		                <td>${task.mdseId}</td>
		                <td>${task.spec}</td>
		                <td>${task.sales}</td>
		            </tr>
	            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script type="text/javascript">
	$(function(){
		dateRangeSimple('#addTime');
	});
</script>
</body>
</html>