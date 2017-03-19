<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<title>帮助中心 - 代金券使用规则</title>
<style>
	* {
		-webkit-tap-highlight-color: rgba(0,0,0,0) /* 默认点击蓝底 */
	}
    body {
        margin: 0;
        background: #e6e6e6
    }
    .help-faq {
        min-width: 320px;
        height: auto;
        background: #fff;
        word-wrap: break-word
    }
    .help-faq-item {
        border-bottom: 1px solid #ededed
    }
    .help-faq-item:last-child {
        border-bottom: 0 none
    }
    .help-faq-title {
        position: relative;
        padding: 15px 15px 8px;
        line-height: 22px;
        font-size: 16px;
        color: #333;
        overflow: hidden
    }
    .help-faq-title .i {
        margin-right: 8px;
        width: 10px;
        height: 10px;
        display: inline-block;
        background: #ed4b1c;
        border-radius: 50%
    }
    .help-faq-content {
        padding: 0px 34px 8px;
        line-height: 20px;
        font-size: 14px;
        color: #aaa;
    }
</style>
</head>
<body>
<div class="help-faq">
	<div class="help-faq-item">
		<div class="help-faq-title">
			<span class="i"></span>代金券可以干什么？
		</div>
		<div class="help-faq-content">代金券可以在下单结算时，直接抵扣订单金额。</div>
	</div>
	<div class="help-faq-item">
		<div class="help-faq-title">
			<span class="i"></span>怎么获得代金券？
		</div>
		<div class="help-faq-content">街坊店宝会在不定期推出的活动中，发放代金券，请多关注店宝APP及转角街坊公众账号。</div>
	</div>
	<div class="help-faq-item">
		<div class="help-faq-title">
			<span class="i"></span>一张代金券能拆开多次使用吗？
		</div>
		<div class="help-faq-content">不能，一张代金券只能一次性使用，不能分开使用。不叠加，不兑现，不找零。</div>
	</div>
	<div class="help-faq-item">
		<div class="help-faq-title">
			<span class="i"></span>多张代金券可以叠加使用吗？
		</div>
		<div class="help-faq-content">不可以，每笔订单仅限使用一张代金券。</div>
	</div>
	<div class="help-faq-item">
		<div class="help-faq-title">
			<span class="i"></span>如何使用代金券？
		</div>
		<div class="help-faq-content">在订单结算时，如果有可用代金券，系统会默认显示代金券金额，如果有多张代金券，系统会自动分配最优的代金券，保证您可以获得最大的优惠，您也可点击“选择代金券”，手动选择其他可用代金券。</div>
	</div>
	<div class="help-faq-item">
		<div class="help-faq-title">
			<span class="i"></span>下单的时候使用了代金券，后来订单取消了，代金券会返还吗？
		</div>
		<div class="help-faq-content">会，您取消订单后，代金券会立即返还到您的代金券列表里。</div>
	</div>
	<div class="help-faq-item">
		<div class="help-faq-title">
			<span class="i"></span>代金券使用的条件？
		</div>
		<div class="help-faq-content">不同的代金券有不同的使用条件，符合条件时，确认订单里，可见对应的代金券。</div>
	</div>
	<div class="help-faq-item">
		<div class="help-faq-title">
			<span class="i"></span>使用代金券注意事项
		</div>
		<div class="help-faq-content">用户使用代金券时，同一手机号、同一设备、同一支付宝账号、同一微信账号、同一收货地址等均视为同一个用户。</div>
	</div>
</div>
</body>
</html>