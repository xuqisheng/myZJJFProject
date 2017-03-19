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
    <a href="${root}/scms/manager/toWallet.do" class="crumb">我的钱包</a><span class="crumb crumb-active">提现</span>
</div>
<div class="bg">
	<div class="wallet-withdraw-title">
		<h2>提取余额到银行卡</h2>
		余额:&nbsp;<span class="yue">${wallet}</span>&nbsp;元
	</div>
	<div class="wallet-withdraw-content">
		<table>
			<tbody>
				<tr>
					<td class="td1">银&nbsp;&nbsp;行&nbsp;&nbsp;卡：</td>
					<td colspan="2" class="td1">
                        <div class="info">
                            <span>户名：${scmsManager.bankUserName}</span><br />
                            <span>${scmsManager.bankName}  卡号：${scmsManager.bankNum}</span>
                        </div>
					</td>
				</tr>
				<tr>
					<td>提现金额：</td>
					<td class="td2"><input id="withdrawMoney" name="presentMoney" type="text" placeholder="提现金额为1元以上"></td>
					<td class="color-red" id="J_tips"></td>
				</tr>
				<tr>
					<td>到账金额：</td>
					<td colspan="2" class="td2">
                       <span id="presentMoney">--</span>
                       <span class="color-gray" id="feeRates"></span>
					</td>
				</tr>
				<tr>
					<td>到账时间：</td>
					<td colspan="2">3 个工作日内到账</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="2" ><button id="btn_next" class="button-save">下一步</button></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<script>
	function check(){
		var ye = ${wallet};
		var $presentMoney = $('#withdrawMoney');
		var money = $.trim($presentMoney.val());
		if(parseFloat(ye) < 1) {
			 $('#J_tips').text('账户余额不足1元，无法取现！');
			return false;
		}
		if(parseFloat(money) < 1 || parseFloat(money) > parseFloat(ye)) {
            $('#J_tips').text('提现金额需大于等于1元，小于等于账户余额！');
			$presentMoney.focus();
			return false;
		}
		if("" == money || (!/^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/.test(money))) {
            $('#J_tips').text('输入内容为空或不是金额（最多保留2位小数），请核对！');
			$presentMoney.focus();
			return false;
		}
		return true;
	};
	$(function() {
		$('#withdrawMoney').keyup(function() {
			$('#presentMoney').text($('#withdrawMoney').val());
			$('#J_tips').text('');
			
			var reg = /^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/;
			if(!reg.test($(this).val())){
				$('#presentMoney').text('--');
				$('#feeRates').text('');
				return;
			}
			
			$.ajax({
				type: "POST",
				url: "${root}/scms/public/caculateFee.do",
				dataType: "json",
				data: {money:$.trim($(this).val()) , type : '0'},
				success: function(data) {
					if(data.success) {
						if(data.message.feeRates == '0')
							$('#feeRates').text('免手续费');
						else
							$('#feeRates').text('（扣除'+data.message.feeRates+'的手续费 : '+data.message.fee+'）');
						
						$('#presentMoney').text(data.message.toMoney);
					} else {
						layer.msg(data.message.msg);
					} 
				},
				error: function(data) {
				}
			});
		});
		$('#btn_next').on('click', function(){
			if('${scmsManager.bankUserName}' == '' || '${scmsManager.bankName}' == '' || '${scmsManager.bankNum}' == ''){
				alert("账户信息不能为空、请联系客服修改 400-664-3833");
			}else{
				var flag = check();
				if(flag) {
					$.ajax({
						type: "GET",
						url: "${root}/scms/manager/doWithDraw.do",
						dataType: "json",
						data: {presentMoney:$.trim($('#withdrawMoney').val())},
						success: function(data) {
							if(data.success) {
								layer.msg(data.message.msg);
								location.href = "${root}/scms/manager/toMaWithDrawById.do?id=" + data.message.status;
							} else {
								layer.msg(data.message.msg);
							} 
						},
						error: function(data) {
						}
					});						
				}
			}
		});
	}); 
</script>
</body>
</html>