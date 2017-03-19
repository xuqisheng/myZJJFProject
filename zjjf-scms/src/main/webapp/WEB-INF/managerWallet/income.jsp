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
	<div class="mb-small">
	    <span style="font-size: 14px;">当前位置：</span>
	    <a href="${root}/scms/manager/toWallet.do" class="crumb">我的钱包</a><span class="crumb crumb-active">收支明细</span>
	</div>
	<div class="op-section">
		<div class="title mb-default">收支明细</div>
		<form action="${root}/scms/manager/toMaWalletLog.do" name="frm_query" method="get" id="incomForm">
			<label>查询日期</label>
			<input class="input-search-date" type="text" name="startDate" id="time_start" value="<fmt:formatDate value="${command.startDate}" pattern="yyyy-MM-dd"/>" placeholder="请选择日期">&nbsp;至&nbsp;
			<input class="input-search-date" type="text" name="endDate" id="time_end" value="<fmt:formatDate value="${command.endDate}" pattern="yyyy-MM-dd"/>" placeholder="请选择日期">
			<input class="input-search-text ml-default" type="text" name="orderId" id="orderId" value="${command.orderId}" placeholder="交易编号/订单号">
			<button class="input-search-button" type="button" id="sub">查询</button>
			<br /><br />
			收入：<b class="wallet-money color-green" id="income">${income }</b>&nbsp;元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			支出：<b class="wallet-money color-red" id="outlay">${outlay }</b>&nbsp;元
		</form>
	</div>
	<div>
		<table class="table-list">
			<thead class="table-thead">
				<tr class="table-header">
					<th>创建时间</th>
					<th>交易编号</th>
					<th>名称|交易号</th>
					<th>收入（元）</th>
					<th>支出（元）</th>
					<th>账户余额</th>
					<th>支付方式</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody class="table-tbody">
				<c:forEach items="${list}" var="log">
					<tr>
						<td><span class="color-gray"><fmt:formatDate value="${log.actionTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></td>
						<td><span class="color-gray">${log.id}</span></td>
						<td>
							<c:choose>
								<c:when test="${log.optType == 0 && log.actionType==1}">默认收入</c:when>
								<c:when test="${log.optType == 1 && log.actionType==1}">在线订单费用收入</c:when>
								<c:when test="${log.optType == 2 && log.actionType==1}">在线订单金额收入</c:when>
								<c:when test="${log.optType == 3 && log.actionType==1}">在线订单补贴收入</c:when>
								<c:when test="${log.optType == 4 && log.actionType==1}">提现收入</c:when>
								<c:when test="${log.optType == 5 && log.actionType==1}">进货收入</c:when>
								<c:when test="${log.optType == 6 && log.actionType==1}">运费收入</c:when>
								<c:when test="${log.optType == 7 && log.actionType==1}">现金券收入</c:when>
								<c:when test="${log.optType == 8 && log.actionType==1}">微信手续费收入</c:when>
								<c:when test="${log.optType == 9 && log.actionType==1}">提现手续费收入</c:when>
								<c:when test="${log.optType == 10 && log.actionType==1}">联合采购订单退款收入</c:when>
								<c:when test="${log.optType == 11 && log.actionType==1}">充值收入</c:when>
								<c:when test="${log.optType == 0 && log.actionType==2}">默认支出</c:when>
								<c:when test="${log.optType == 1 && log.actionType==2}">在线订单费用支出</c:when>
								<c:when test="${log.optType == 2 && log.actionType==2}">在线订单金额支出</c:when>
								<c:when test="${log.optType == 3 && log.actionType==2}">在线订单补贴支出</c:when>
								<c:when test="${log.optType == 4 && log.actionType==2}">提现支出</c:when>
								<c:when test="${log.optType == 5 && log.actionType==2}">进货支出</c:when>
								<c:when test="${log.optType == 6 && log.actionType==2}">运费支出</c:when>
								<c:when test="${log.optType == 7 && log.actionType==2}">现金券支出</c:when>
								<c:when test="${log.optType == 8 && log.actionType==2}">微信手续费支出</c:when>
								<c:when test="${log.optType == 9 && log.actionType==2}">提现手续费支出</c:when>
								<c:when test="${log.optType == 10 && log.actionType==2}">联合采购订单退款支出</c:when>
								<c:when test="${log.optType == 11 && log.actionType==2}">充值支出</c:when>
								<c:otherwise>其他</c:otherwise>
							</c:choose>
							:
							<span class="color-gray">
								<c:choose>
									<c:when test="${log.optType == 4 || log.optType == 9}">${log.maWithDrawId }</c:when>
									<c:otherwise>${log.orderId }</c:otherwise>
								</c:choose>
							</span>
						</td>
						<td class="wallet-money color-green">
							<c:if test="${log.actionType == 1}">${log.money}</c:if>
						</td>
						<td class="wallet-money color-red">
							<c:if test="${log.actionType == 2}">${log.money}</c:if>
						</td>
						<td class="wallet-money">${log.balance}</td>
						<td>
							<c:choose>
								<c:when test="${log.tradeWay == 1}">快钱支付</c:when>
								<c:when test="${log.tradeWay == 2}">货到付款</c:when>
								<c:when test="${log.tradeWay == 3}">支付宝支付</c:when>
								<c:when test="${log.tradeWay == 4}">微信支付</c:when>
								<c:otherwise>转角账户</c:otherwise>
							</c:choose>
						<td>
							<c:choose>
								<c:when test="${log.optType == 4 || log.optType == 9}">
									<a href="${root}/scms/manager/toMaWithDrawById.do?id=${log.orderId}" class="">查看</a>
								</c:when>
								<c:otherwise>
									<c:if test="${log.orderId != null && log.orderId != ''}"><a href="${root}/scms/scOrder/returnDetailsAndPrintPage.do?id=${log.sheetId}">查看</a></c:if>
								</c:otherwise>
							</c:choose>
						</td>
				</c:forEach>
				<c:if test="${fn:length(list) == 0}">
		    		<tr><td colspan="8"  class="no-data"></td></tr>
		    	</c:if>
			</tbody>
		</table>
	</div>
	<c:if test="${fn:length(list)>0}">
		  <%@ include file="../common/pagination-kk.jsp"%>
    </c:if>
</div>
<script type="text/javascript">
$(function() {
	dateRange('#time_start', '#time_end', 1);
    $('#sub').on('click', function(e) {
    	e.preventDefault();
    	$('#test').submit();
    });
    $('#sub').on('click', function(e) {
    	e.preventDefault();
    	$('#incomForm').submit();
    });
})
</script>
</body>
</html>