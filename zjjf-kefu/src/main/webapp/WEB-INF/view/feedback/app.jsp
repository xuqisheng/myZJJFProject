<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>对APP意见反馈 - 反馈列表</title>
    <%@ include file="../common/head.jsp"%>
    <script src="../../resources/vendor/My97DatePicker/WdatePicker.js"></script>
    <script src="../../resources/js/comm.js"></script>
</head>
<body class="wrap-bd">
<div class="mb-small">
    <form action="${root }/CornerV2/SpFeedblack/getAllSpFeedbackByParam.do" method="post">
        <label>版本号：</label>
        <input class="input input-search-text" type="text" name="appVersion" value="${spFeedbackRo.appVersion }" placeholder="">
        <label class="ml-default">反馈时间：</label>
        <input type="text" name="beginTime" value="<fmt:formatDate value="${spFeedbackRo.beginTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" class="input input-date J_timeS"> -
        <input type="text" name="endTime" value="<fmt:formatDate value="${spFeedbackRo.endTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" class="input input-date J_timeE">
        <input type="submit" name="" value="搜索" class="input input-search-button ml-default">
    </form>
</div>
<table class="table-list table-border">
    <thead class="table-thead">
    <tr>
        <th>序号</th>
        <th>版本号</th>
        <th>反馈账号</th>
        <th>反馈内容</th>
        <th>反馈时间</th>
        <th>是否处理</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody class="table-tbody">
	    <c:forEach var="spFeedback" items="${SpFeedbackList }" varStatus="var">
	    	<tr>
	            <td width="30">${var.index+1 }</td>
	            <td width="50">${spFeedback.appVersion }</td>
	            <td width="130">${spFeedback.storeMobile }</td>
	            <td width="130">${spFeedback.content }</td>
	            <td width="120"><fmt:formatDate value="${spFeedback.addTime }" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
	            <td width="60">
	            	<c:if test="${spFeedback.revertStatus == 1 }">
	            		<span class="txt-success">是</span>
	            	</c:if>
	                <c:if test="${spFeedback.revertStatus == 0 }">
	            		<span class="txt-warn">否</span>
	            	</c:if>
	            </td>
	            <td width="30">
	                <a href="${root }/CornerV2/SpFeedblack/returnHuiFuPage.do?id=${spFeedback.id }">查看</a>
	            </td>
        </tr>
	    </c:forEach>
    </tbody>
</table>
<c:if test="${fn:length(SpFeedbackList)>0}">
	<%@ include file="../common/pagination-kk.jsp"%>
</c:if>
<script>
    $(function() {
        dateRange('.J_timeS', '.J_timeE', 1);
    });
</script>
</body>
</html>
