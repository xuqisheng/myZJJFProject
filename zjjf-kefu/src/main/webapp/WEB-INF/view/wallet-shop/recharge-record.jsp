<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>充值记录</title>
    <%@ include file="../common/head.jsp"%>
    <script src="${root }/resources/vendor/My97DatePicker/WdatePicker.js"></script>
    <script src="${root }/resources/js/comm.js"></script>
</head>
<body class="wrap-bd">
<div class="mb-small">
    <div>
	    <form action="${root }/wallet/toRechargeInfo.do" method="post">
	    	<label for="account">充值账号：</label>
	        <input type="text" name="mobile" id="mobile" value="${mobile }" class="input input-search-text">
	        <label for="J_timeS" class="ml-default">充值时间：</label>
	        <input type="text" name="beginTime" value="<fmt:formatDate value="${beginTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" class="input input-date" id="J_timeS"> -
	        <input type="text" name="endTime" value="<fmt:formatDate value="${endTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" class="input input-date" id="J_timeE">
	        <input type="submit" name="" value="搜索" class="input input-search-button ml-default">
	    </form>
    </div>
</div>
<div>
    <table class="table-list">
        <thead>
        <tr>
            <th>序号</th>
            <th>充值账号</th>
            <th>用户类型</th>
            <th>充值金额</th>
            <th>赠送优惠券</th>
            <th>充值时间</th>
        </tr>
        </thead>
        <tbody class="table-tbody" id="J_tableBbody">
        <c:choose>
        	<c:when test="${rechargeInfoList != null && rechargeInfoList.size()>0 }">
        		<c:forEach items="${rechargeInfoList}" var="rechargeInfo" varStatus="var">
        			<tr>
			            <td>${var.index+1 }</td>
			            <td>${rechargeInfo.mobile }</td>
			            <td>${rechargeInfo.userTypeStr }</td>
			            <td>${rechargeInfo.money }</td>
			            <td>${rechargeInfo.ruleName }</td>
			            <td><fmt:formatDate value="${rechargeInfo.paySuccessTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			        </tr>
        		</c:forEach>
        	</c:when>
        	<c:otherwise>
        		<tr>
		            <td colspan="6">无数据</td>
		        </tr>
        	</c:otherwise>
        </c:choose>
        </tbody>
    </table>
    <c:if test="${fn:length(rechargeInfoList)>0}">
		<%@ include file="../common/pagination-kk.jsp"%>
	</c:if>
</div>
<script>
    $(function() {
        dateRange('#J_timeS', '#J_timeE', 1);
    });
</script>
</body>
</html>
