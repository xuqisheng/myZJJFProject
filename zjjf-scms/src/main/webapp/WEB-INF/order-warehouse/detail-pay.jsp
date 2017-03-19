<%@page import="com.corner.core.config.Constants"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>街坊店宝</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <link rel="icon" href="../../resources/images/favicon.png" type="image/x-icon">
    <%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="../../resources/vendor/layer/layer.js"></script>
</head>
<body>
<div class="wrap-bd">
    <div>
        当前位置：
        <a href="${root}/scms/scOrder/getWarehouseOrderList.do" class="crumb">入库管理</a>
        <span class="crumb crumb-active">货款结算</span>
    </div>
    <div class="title mb-small">货款结算</div>
    <table class="mt-small mb-default table-list table-border">
        <thead class="table-thead">
        <tr>
            <th>条形码</th>
            <th>商品</th>
            <th>规格</th>
            <th>数量</th>
            <th>货款金额</th>
            <th>配送费</th>
        </tr>
        </thead>
        <tbody class="table-tbody">
        <c:forEach var="scOrderDetailVo" items="${maOrderInfoMgVo.scOrderDetailVoList }">
        <tr>
        	<td>${scOrderDetailVo.barCode }</td>
            <td>${scOrderDetailVo.name }</td>
            <td>${scOrderDetailVo.spec }</td>
            <td>${scOrderDetailVo.countNum }${scOrderDetailVo.pkg }</td>
            <td>￥${scOrderDetailVo.freight+scOrderDetailVo.totalPrice }</td>
            <td>￥${scOrderDetailVo.freight }</td>
        </tr>
        </c:forEach>
        <tr>
            <td colspan="7" style="background:#eef0f6;" class="ta-r">
                货款总金额：<span class="txt-warn mr-default">￥${maOrderInfoMgVo.orderPrice }</span>
                配送费总额： <span class="txt-warn mr-default">￥${maOrderInfoMgVo.whFreight }</span>
<%--                 订单总额：<span class="txt-warn">￥${maOrderInfoMgVo.orderPrice+maOrderInfoMgVo.whFreight }</span> --%>
            </td>
        </tr>
        </tbody>
    </table>

    <style>
        .bg-gray {
            background: #f4f5f8;
        }
        .payment-border {
            border: 1px solid #d1d3d9;
        }
        .payment {
            border: 4px solid #f4f5f8;
            padding: 20px;
            background: #fff;
        }
        .payment .h3 {
            font-size: 16px;
            font-weight: bold;
        }
        .payment-title {
            margin: 30px 0;
        }
        .payment .wallet {
            padding-bottom: 30px;
            border-bottom: 1px solid #e7e8eb;
        }
        .payment .chk {
            margin-top: -2px;
            margin-right: 3px;
        }
        .wallet .input-box {
            margin-left: 20px;
            font-size: 16px;
        }
        .wallet .input-box input {
            box-sizing: border-box;
            padding: 0 8px;
            margin: 0;
            height: 32px;
        }
        .wallet .input-box input[type="password"] {
            width: 260px;
        }
        .wallet .input-box input[type="button"] {
            border: 0 none;
            padding: 0 20px;
            background: #ed4b1c;
            color: #fff;
        }
        .wallet .input-box input[type="button"]:hover {
            background: #e54213;
        }
        .giro .div {
            padding-left: 15px;
        }
    </style>
    <c:if test="${maOrderInfoMgVo.whPayStatus == false }">
	    <div>
	        <div class="mb-small">
	            应付货款总额：<b class="txt-warn">￥${maOrderInfoMgVo.orderPrice }</b>
	        </div>
	        <div class="payment-border">
	            <div class="payment">
	                <div class="wallet">
	                    <div class="payment-title clearfix">
	                        <div class="fl">
	                        	<c:choose>
	                        		<c:when test="${maOrderInfoMgVo.orderPrice <= wallet}">
	                        			<img src="../../resources/images/chked.png" id="wallet" alt="" class="chk"> <span class="h3">钱包支付</span>
	                        		</c:when>
	                        		<c:otherwise>
	                        			<img src="../../resources/images/chk.png" id="wallet" alt="" class="chk"> <span class="h3">钱包支付</span>
	                        		</c:otherwise>
	                        	</c:choose>
	                            <span class="ml-small txt-log">可用余额：<span class="txt-warn">${wallet }元</span></span>
	                        </div>
	                        <span class="fr h3">支付 <span class="txt-warn">${maOrderInfoMgVo.orderPrice }元</span></span>
	                    </div>
	                    <div class="wrap-bd bg-gray clearfix walletDeltail">
	                        <div class="fl input-box">
	                            请输入支付密码&nbsp;&nbsp;
	                            <input type="password" name="payPassword" placeholder="请输入支付密码">&nbsp;&nbsp;
	                            <input type="button" id="pay" value="立即支付">
	                        </div>
	                        <a class="fr" href="${root}/scms/password/toUpdatePayPassword.do">忘记支付密码？</a>
	                    </div>
	                </div>
	                <div class="giro">
	                    <div class="payment-title h3">
	                    	<c:choose>
		                   		<c:when test="${maOrderInfoMgVo.orderPrice <= wallet}">
		                   			<img src="../../resources/images/chk.png" id="accounts" alt="" class="chk"> 转账
		                  		</c:when>
		                  		<c:otherwise>
		                  			<img src="../../resources/images/chked.png" id="accounts" alt="" class="chk"> 转账
		                  		</c:otherwise>
	                  		</c:choose>
	                    </div>
	                    <div class="wrap-bd bg-gray accounts">
	                        <span class="h3">企业汇款</span><span class="txt-log">（到账周期为1-2个工作日）</span>
	                        <div class="div">
	                            <p>
	                                <b>注意事项：汇款时需要注意以下信息，请牢记！</b><br>
	                                1.<b>您的汇款识别码为：</b><span class="txt-warn">ZJ${maOrderInfoMgVo.orderId }</span>，<b>线下公司转账</b>需将此汇款识别码填写至电汇凭证的<span class="txt-warn">【汇款用途】、【附言】、【摘要】</span>等栏内，汇款识别码组成：（ZJ+订单号）。<b>企业网银
	                                转账</b>请务必将汇款识别码填写到<span class="txt-warn">附言、摘要、备注</span>等栏内。<br>
	                                （<b>提醒：</b>因不同银行备注字段不同，最好是将所有的可备注的地方都填写上<span class="txt-warn">汇款识别码</span>）。<br>
	                                2.<b>线下公司转账汇款时备注汇款识别码，可确保订单及时核销，请务必填写正确，勿私自增加其他文字说明。</b><br>
	                                3.线下公司转账订单，一个识别码对应一个订单和相应的金额，请勿多转账或者少转账。<br>
	                                <b>注意事项：汇款时需要注意以下信息，请牢记！</b>
	                            </p>
	                            <p>
	                                <b>户名：</b>${bankInfo.bankUserName }<br>
	                                <b>账户：</b>${bankInfo.bankNum }<br>
	                                <b>开户行：</b>${bankInfo.bankName }<br>
	<!--                            <b>联行号：</b>4514 5859 5745 （<span class="txt-warn">非必填项</span>）<br> -->
	                                <b>汇付识别码：</b><span class="txt-warn">ZJ${maOrderInfoMgVo.orderId }</span>
	                            </p>
	                            <p>
	                                <b>您的汇款验证码<span class="txt-warn">ZJ${maOrderInfoMgVo.orderId }</span><!-- 已发送手机${SCMSWAREHOUSE_SESSION_KEY.branderTel }</b> -->
	                            </p>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
    </c:if>
</div>
<script type="text/javascript">
	var wallet = '${wallet }';
	var goodsPrice = '${maOrderInfoMgVo.orderPrice }';
	$(function(){
		var chked = '../../resources/images/chked.png';
		var chk = '../../resources/images/chk.png';
		if(parseFloat(wallet) <= parseFloat(goodsPrice)){
			$('.accounts').show();
			$('.walletDeltail').hide();
		}else{
			$('.accounts').hide();
			$('.walletDeltail').show();
		}
		$('#accounts').on('click' , function(){
			if($(this).attr('src') == chked){
				$(this).attr('src' , chk);
				$('#wallet').attr('src' , chked);
				$('.accounts').hide();
				$('.walletDeltail').show();
			}else{
				$(this).attr('src' , chked);
				$('#wallet').attr('src' , chk);
				$('.accounts').show();
				$('.walletDeltail').hide();
			}
		});
		$('#wallet').on('click' , function(){
			if(parseFloat(wallet) <= parseFloat(goodsPrice)){
				
			}else{
				if($(this).attr('src') == chked){
					$(this).attr('src' , chk);
					$('#accounts').attr('src' , chked);
					$('.walletDeltail').hide();
					$('.accounts').show();
				}else{
					$(this).attr('src' , chked);
					$('#accounts').attr('src' , chk);
					$('.walletDeltail').show();
					$('.accounts').hide();
				}
			}
		});
		$('#pay').on('click' , function(){
			if($.trim($('input[name="payPassword"]').val()) == ''){
				
			}
			if(parseFloat(wallet) >= parseFloat(goodsPrice)){
				layer.msg('提交中', {icon: 16});
		    	$.post("${root}/scms/whPay/pay.do",{id:'${maOrderInfoMgVo.id }',payPassword : $('input[name="payPassword"]').val()},function(data){
					if(data.success){
						layer.closeAll('loading');
						layer.msg("支付成功", {
						    icon: 1,
						    time: 2000 //2秒关闭（如果不配置，默认是3秒）
						}, function(){
							window.location.href = '${root}/scms/scOrder/getWarehouseOrderList.do?orderIdAndSupply=${maOrderInfoMgVo.orderId }';
						});
					}else{
						layer.closeAll('loading');
						layer.msg(data.message, {icon: 5},function(){
							location.reload();
						});
					}
				 },'json');
			}
		});
	});
</script>
</body>
</html>