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
    <link rel="stylesheet" href="${root}/resources/css/wallet.css?v">
    <script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
    <script src="${root}/resources/js/comm.js?vvv"></script>
</head>
<body>
<div class="wrap-bd">
	<div class="wallet-center">
	    <div class="wallet-center-title">
	        <h2>钱包中心</h2>
	        <a href="${root}/scms/wh/toWhWalletLog.do" class="color-a">收支明细</a>
	    </div>
	    <div class="wallet-center-content">
	        <h3>钱包余额（元）</h3>
	        <div>
	            <span class="balance">${wallet}</span>
	            <a href="${root}/scms/wh/toWhWithDraw.do" class="color-a"><button class="button-save fr">提现</button></a>
	        </div>
	    </div>
	</div>
	<div class="title mt-default mb-small">提现记录</div>
	<div class="op-section mt-small">
		<form action="${root}/scms/wh/toWhWalletIndex.do" name="frm_query" method="post" id="test" >
			<label>起止日期：</label>
			<input class="input-search-date" type="text" name="startDate" id="time_start" value="<fmt:formatDate value="${command.startDate}" pattern="yyyy-MM-dd"/>" placeholder="选择日期"> -
			<input class="input-search-date" type="text" name="endDate" id="time_end" value="<fmt:formatDate value="${command.endDate}" pattern="yyyy-MM-dd"/>" placeholder="选择日期">
			<input type="button" class="input-search-button" value="查询" id="sub"/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<span class="pills <c:if test="${command.findType == 1 }">pills-active</c:if>" id="thisDay">今天</span>
			<span class="pills <c:if test="${command.findType == 2 }">pills-active</c:if>" id="thisMonth">本月</span><!-- pills-active -->
		</form>
	</div>
	<div>
        <table class="table-list table-border">
            <thead class="table-thead">
                <tr>
                    <th>提现编号</th>
                    <th>创建时间</th>
                    <th>提现记录</th>
                    <th>对方</th>
                    <th>金额（元）</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody class="table-tbody">
            	<c:forEach items="${list}" var="log">
            		<tr>
	            		<td><span class="color-gray">${log.id }</span></td>
	            		<td><span class="color-gray"><fmt:formatDate value="${log.applyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></td>
	            		<td>钱包-提现<br /><span class="color-gray">银行流水号：${log.bankDealNo }</span></td>
	            		<td>${log.cardBankName }<br/><span class="color-gray">${log.cardUserName }|${log.cardNo}</span></td>
	                    <td class="money">${log.amount }</td>
	                    <td>
	                    	<c:choose>
	                    		<c:when test="${log.status == 1}"><span style="color: #333">待处理</span></c:when>
	                    		<c:when test="${log.status == 2}"><span style="color: #06ada3">审核通过，等待打款</span></c:when>
	                    		<c:when test="${log.status == 3}"><span style="color: #ed4b1c">没能通过审核，提现失败</span></c:when>
	                    		<c:when test="${log.status == 4}"><span style="color: #333">生成计算单，打款中</span></c:when>
	                    		<c:when test="${log.status == 5}"><span style="color: #333">已打款</span></c:when>
	                    		<c:when test="${log.status == 6}"><span style="color: #333">已退款</span></c:when>
	                    	</c:choose>
	                    </td>
						<td><a href="${root}/scms/wh/toWhWithDrawById.do?id=${log.id}">查看</a></td>
            		</tr>
            	</c:forEach>
		    	<c:if test="${fn:length(list) == 0}">
		    		<tr><td colspan="7"  class="no-data"></td></tr>
		    	</c:if>
            </tbody>
        </table>
    </div>
    <c:if test="${fn:length(list)>0}">
		  <%@ include file="../common/pagination-kk.jsp"%>
    </c:if>
</div>
<script>
	$(function() {
		dateRange('#time_start', '#time_end', 1);
           $('#sub').on('click', function(e) {
           	e.preventDefault();
           	$('#test').submit();
           });
           
           if($("#thisMonthStr").val()==1){
			$("#thisMonth").addClass("pills-active");
		}
		
		if($("#thisDayStr").val()==1){
			$("#thisDay").addClass("pills-active");
		}
		
		$("#thisDay").click(function(e){
			location.href='${root}/scms/wh/toWhWalletIndex.do?findType=1';
		});
		
		$("#thisMonth").click(function(e){
			location.href='${root}/scms/wh/toWhWalletIndex.do?findType=2';
		});
           
	})
</script>
</body>
</html>