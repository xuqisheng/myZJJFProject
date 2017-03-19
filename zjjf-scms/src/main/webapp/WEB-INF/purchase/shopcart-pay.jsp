﻿<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<%
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragrma","no-cache");
response.setDateHeader("Expires",0);
%> 
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>快销宝 - 支付</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <%@ include file="../common/purchase-head.jsp"%>
    <link rel="stylesheet" href="${root}/resources/purchase/css/shopcart-pay.css">
</head>
<body>
<%@ include file="../common/purchase-nav.jsp"%>
<header>
    <a href="${root}/scms/procurementcenter/listPage.do"><div class="logo"></div></a>
    <div class="shopcart-bar">
        <div class="shopcart-bar-item active">
            <span class="number">1</span>
            采购单
        </div>
        <div class="shopcart-bar-item active">
            <span class="number">2</span>
            确认采购单
        </div>
        <div class="shopcart-bar-item curr">
            <span class="number">3</span>
            结算
        </div>
    </div>
</header>
<main class="w">
    <section class="title">结算</section>
    <section class="order">
        <div class="info">
            <img src="../../resources/purchase/images/ok.png" alt="">
            订单提交成功，请您尽快付款！订单号：${orderId}
        </div>
        <div class="warn">
            请您在提交订单后 <span class="main-color">24小时</span>内完成支付，否则订单会自动取消。
        </div>
    </section>
    <section class="total-price">
        应付金额 <span class="price">￥${totalPrice}</span> &nbsp;&nbsp;&nbsp;(在线支付)<br/>
        <c:if test="${isUsedBalance}">
              该笔订单已使用钱包支付${balanceUsedNum}元
        </c:if>
    </section>
    <section class="pay-contain">
        <div class="pay">
            <div class="box">
                <div>
                  <c:if test="${payMethod==0}">
                    <img src="../../resources/purchase/images/unchecked.png" alt="" class="img">
                   </c:if>
                   <c:if test="${payMethod==1}">
                    <img src="../../resources/purchase/images/checked.png" alt="" class="img">
                   </c:if>
                   <c:if test="${payMethod==2}">
                    <img src="../../resources/purchase/images/checked.png" alt="" class="img">
                   </c:if>
                    <b class="b">&nbsp;&nbsp;钱包支付</b>
                    可用余额：<span class="main-color" id ="wallet">${supplier.wallet}</span> 元
                     <c:choose>
                       <c:when test="${empty QBpayPrice}">
                       </c:when>                     
                       <c:otherwise>
                       <span class="pay-price">支付：<span class="main-color">￥${QBpayPrice}</span></span>
                       </c:otherwise>
                     </c:choose>
                    <c:if test="${payMethod!=0}">
                    <div class="password">
                       <b>支付密码</b>
		               <input type="text" placeholder="请输入支付密码" class="input-text" id="password" onfocus="this.type='password'" autocomplete="off">
		               <input type="button" value="立即支付" id="creatQrImage" class="input-button">
                        <a href="${root}/scms/password/forgerPassword.do" target="_blank">
                        <c:choose>
                          <c:when test="${noPayPassword}">
                                                    设置支付密码
                          </c:when>
                          <c:otherwise>
                                                   忘记支付密码?
                          </c:otherwise>
                        </c:choose>
                        </a>
                    </div>                    
                    </c:if>
                </div>
                <hr class="hr">
                <c:if test="${payMethod==0||payMethod==2}">
                <div class="bg" id="WXDiv">
                    <img src="../../resources/purchase/images/checked.png" alt="" class="img"> 
                </c:if>
                <c:if test="${payMethod==1}">
                <div class="" id="WXDiv">
                    <img src="../../resources/purchase/images/unchecked.png" alt="" class="img"> 
                </c:if>
                &nbsp;&nbsp;
                    <img src="../../resources/purchase/images/wechat.png" alt="" class="img">
                    <b class="b">微信支付</b>
                                                        扫码支付
                    <c:choose>
                      <c:when test="${empty outOfWxPayPrice}">
                      </c:when>
                      <c:otherwise>
                      <span class="pay-price" style="color:#000;">
                                                                支付：<span class="main-color">￥${outOfWxPayPrice}</span><span style="font-size:12px;color:#aaa">（含手续费￥${WXpayFee}）</span>
                      </span>
                      </c:otherwise>
                    </c:choose>
                    
                    <div class="qr-code" id="qr-code">
                        <c:if test="${payMethod==0}">
                        <img src="${root}/scms/pay/creatQrImage.do?scOrderInfoId=${scOrderInfoId}" width="210" height="210" alt="">
                        <p>使用微信扫一扫即可付款</p>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>
<%@ include file="../common/purchase-footer.jsp"%>
<script>
$(function() {
	$('#password').val('');
	function xxx() {
		var orderId = "${scOrderInfoId}"
		$.ajax({
			type : "POST",
			url : "${root}/scms/pay/getOrderStatus.do",
			data :{"orderId":orderId},
			success : function(da) {
				if (da.success) {
					var html = '<div>'
				           +'<form id="toStatus" action="${root}/scms/pay/toStatus.do" method="post">'
				           +'<input type="hidden" value="'+orderId+'" name="scOrderInfoId">'
				           +'</form>'
				           +'</div>';
				           $('#WXDiv').after(html);
				           $('#toStatus').submit();
				}else{
					
				}
			},
			error : function(da) {
			}
		});
		}
	if(typeof $('#qr-code img').attr('src')!='undefined'){
		if($('#qr-code img').attr('src') != '') {
			setInterval(xxx, 5000);
		}
	}
	//使用微信支付,生成二维码
	$('#creatQrImage').on('click',function(){
		$('#creatQrImage').hide();
		var scOrderInfoId = "${scOrderInfoId}";
		$password = $('#password');
		$.ajax({
			type : "POST",
			url : "${root}/scms/pay/verfifypp.do",
			data :"scOrderInfoId="+scOrderInfoId+"&password="+$password.val()+"&money="+$('#wallet').text(),
			success : function(da) {
				if (da.success) {
					if(da.message=="s1"){
						var html = '<div>'
						           +'<form id="toStatus" action="${root}/scms/pay/toStatus.do" method="post">'
						           +'<input type="hidden" value="'+scOrderInfoId+'" name="scOrderInfoId">'
						           +'</form>'
						           +'</div>';
						           $('#WXDiv').after(html);
						           $('#toStatus').submit();
					}else{
						var wallet = da.message.wallet;
						$('#wallet').text(wallet);
						$('#creatQrImage').after('已扣除钱包余额,请扫描微信二维码,继续支付!');
						$('#creatQrImage').hide();
						var html = '<img src="${root}/scms/pay/creatQrImage.do?scOrderInfoId='+scOrderInfoId+'" width="210" height="210" alt="">';
						html+='<p>使用微信扫一扫即可付款</p>';
						$('#qr-code').html(html);
						setInterval(xxx, 5000);
					}
				}else{
					$('#creatQrImage').show();
					var html='';
					html+='<form action="${root}/scms/cart/toSettlement.do" method="post" id="toSettlementForm">'
					    +'<input type="hidden" name="orderid" value="'+scOrderInfoId+'">'
					    +'</form>';
					if(da.message=='e2'){
                      location.href="${root}/scms/procurementcenter/listPage.do"    						
					}else if(da.message=='e1'){
						layer.msg('当前账户钱包余额有变动,页面将自动刷新',{time:2000});
						$('#WXDiv').after(html);
						$('#toSettlementForm').submit();
					}else if(da.message=='e3'){
						layer.msg('支付密码错误!',{time:2000})
					}else if(da.message=='e4'){
						location.href='${root}/scms/authority/scmsMainPage.do';
					}else if(da.message=='e5'){
						layer.msg('您还没有设置支付密码,请点击设置支付密码进行设置!',{time:3000})
					}else if(da.message=='e6'){
						layer.msg('订单金额变动,页面将自动刷新!',{time:2000},function(){
							$('#WXDiv').after(html);
							$('#toSettlementForm').submit();							
						});
					}else{
					layer.msg('结算失败,请联系客服!');
					console.log(da.message);
					}
				}
			},
			error : function(da) {
				$('#creatQrImage').show();
				layer.msg("失败的请求!");
			}
		});
	});
});

</script>
</body>
</html>