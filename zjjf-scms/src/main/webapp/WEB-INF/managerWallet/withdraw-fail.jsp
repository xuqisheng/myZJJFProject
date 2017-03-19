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
</head>
<body class="wrap-bd">
<div class="mb-small">
    <span style="font-size: 14px;">当前位置：</span>
    <a href="${root}/scms/manager/toWallet.do" class="crumb">我的钱包</a><span class="crumb crumb-active">提现状态</span>
</div>
<div class="wallet-withdraw-fail">
	<div class="wallet-notice">
		<img src="${root}/resources/images/walletnotice1.png" alt=""/>
		提现失败
	</div>
	<br />
	<table class="table-list">
		<thead class="table-thead">
		<tr class="table-header">
			<th>类型</th>
			<th>消费名称</th>
			<th>金额（元）</th>
		</tr>
		</thead>
		<tbody class="table-tbody">
		<tr>
			<td>提现</td>
			<td>
				普通提现<br />
				流水号 ${maWithDraw.bankDealNo}<!-- 读取的是银行流水号字段 -->
			</td>
			<td class="wallet-money color-red">${maWithDraw.amount}</td>
		</tr>
		</tbody>
	</table>
	<br />
	<table class="table-list table-list-border">
		<tbody class="table-tbody">
			<tr>
				<td>提现信息：</td>
				<td>${maWithDraw.cardUserName}（${maWithDraw.cardBankName}：${maWithDraw.cardNo}）</td>
			</tr>
			<tr>
				<td>时间报告：</td>
				<td>创建时间：</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><fmt:formatDate value="${maWithDraw.applyTime}" pattern="yyyy-MM-dd HH:mm"/></td>
			</tr>
		</tbody>
	</table>
</div>
</body>
</html>