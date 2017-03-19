<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>活动管理</title>
	<%@ include file="../common/head.jsp"%>
    <script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
    <script src="${root}/resources/js/comm.js"></script>
</head>
<body>
<div class="wrap-bd">
    <div class="mb-small clearfix">
        <div class="fl">
            <form method="post" action="${root}/Customer/active//toAccumulation.do">
                <%--<label>活动时间：</label>--%>
	            <%--<input type="text" name="ruleStart" value="${ruleStart}" class="input input-date J_timeS"> -
	            <input type="text" name="ruleEnd" value="${ruleEnd}" class="input input-date J_timeE">--%>
                <input type="text" name="keyStr" value="${keyStr}" placeholder="店铺名称/手机号/店主" class="input input-search-text ml-default">
                <input value="搜索" type="submit" class="input input-search-button ml-default" id="">
                <input type="hidden" name="id" value="${id}">
            </form>
        </div>
    </div>
    <table class="table-list table-border">
        <thead class="table-thead">
        <tr>
        	<th>编号</th>
        	<th>店铺名称</th>
        	<th>累计金额</th>
        	<th>是否发券</th>
        	<th>赠送金额</th>
        	<th>开始时间</th>
        	<th>结束时间</th>
        </tr>
        </thead>
        <tbody class="table-tbody">
        <c:forEach items="${list}" var="item">
            <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.totalOrderPrice}</td>
            <td>
                <c:if test="${empty item.totalSendPrice||item.totalSendPrice==0}">
                   否
                </c:if>
                <c:if test="${item.totalSendPrice!=0}">
                    是
                </c:if>
            </td>
            <td>${item.totalSendPrice}</td>
            <td>${ruleStartStr}</td>
            <td>${ruleEndStr}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${fn:length(list)>0}">
        <%@ include file="../common/pagination-kk.jsp"%>
    </c:if>
</div>
</body>
<script type="text/javascript">
    $(function() {
    	//dateRange('.J_timeS', '.J_timeE', 1);
    })
</script>
</html>
