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
<body>
<div class="wrap-bd">
    <div class="mb-small">
        <span style="font-size: 14px;">当前位置：</span>
        <a href="${root}/scms/sp/toSpWalletIndex.do" class="crumb">我的钱包</a><span class="crumb crumb-active">提现状态</span>
    </div>
	<div class="wallet-notice">
		<img src="${root }/resources/images/walletnotice2.png" alt=""/>
		提现成功
	</div>
	<div class="wallet-withdraw-handle">
		<div class="handle-title">如果提交提现失败或不通过，资金将自动退还到您的钱包。</div>
		<div class="handle-item handle-item-active">
			<div class="handle-item-border">
				<i class="handle-item-circle"></i>
				<b class="handle-item-status">提交申请</b><br >
				<!-- 2015-09-17 15:50 -->
				<fmt:formatDate value="${spWithDraw.applyTime}" pattern="yyyy-MM-dd HH:mm"/>
			</div>
		</div>
		<div class="handle-item handle-item-active">
			<div class="handle-item-border">
				<i class="handle-item-circle"></i>
				<b class="handle-item-status">财务审核</b><br >
				<!-- 2015-09-17 15:50 -->
				<fmt:formatDate value="${spWithDraw.checkTime}" pattern="yyyy-MM-dd HH:mm"/><!--提现成功,时间读取的是审核时间字段  -->
			</div>
		</div>
		<div class="handle-item handle-item-active">
			<div class="handle-item-border-transparent">
				<i class="handle-item-circle"></i>
				<b class="handle-item-status">提现成功</b><br >
				<!-- 2015-09-20 24:00 前 -->
				<fmt:formatDate value="${spWithDraw.payTime}" pattern="yyyy-MM-dd HH:mm"/><!--提现成功,时间读取的是支付时间  -->
			</div>
		</div>
	</div>
	<div class="wallet-withdraw-ed">
		<!-- <a class="color-a" href="">查看提现记录</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
		<a class="color-a" href="${root }/scms/sp/toSpWalletIndex.do">返回我的钱包</a>
	</div>
</div>
</body>
</html>