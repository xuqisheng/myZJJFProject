<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<title>转角店宝</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width = 720, user-scalable = no">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="icon" href="${root}/resources/images/favicon.png"
	type="image/x-icon">
<link rel="stylesheet" href="${root}/resources/css/normalize.css">
<link rel="stylesheet" href="${root}/resources/css/share.css">

<script src="${root}/resources/vendor/jquery/jquery-2.2.3.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
</head>
<body>
	<img src="${root}/resources/images/share-banner.png" alt=""
		class="banner">
	<div class="box box1">
		<div class="h2">邀请好友</div>
		<div class="h3">分享邀请便利店加入转角店宝。</div>
	</div>
	<div class="box box2">
		<div class="h2">便利店注册</div>
		<div class="h3">便利店转角店宝并成功注册。</div>
	</div>
	<div class="box box3">
		<div class="h2">成为您的客户</div>
		<div class="h3">该便利店订单自动分配在您的批发部。</div>
	</div>
	<input type="button" value="立即邀请" class="button" id="J_invite">
	<div class="cover" id="J_cover">
		<div class="txt"></div>
	</div>
	<script>
function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]); return null;
	}

    function isWeChat() {
        var ua = window.navigator.userAgent.toLowerCase();
        if(ua.match(/MicroMessenger/i) == 'micromessenger') {
            return true;
        } else {
            return false;
        }
    }
    $(function() {
        $('#J_cover').hide();
        $('#J_invite').on('click', function() {
            if(isWeChat()) {
                $('#J_cover').show();
            } else {
                $('#J_cover .txt').css('background','none').text('对不起，请使用微信浏览分享！');
                $('#J_cover').show();
            }
        });
        var supplierId=getQueryString("suid");
        var host = getQueryString("server");
        var picpath=getQueryString("picpath");

        $.ajax({
            type : "post",
            url :"${root}/scms/promotion/wxsign.do",
            data : {
                "url" : encodeURIComponent(location.href.split('#')[0])
            },
            async : true,
            dataType: "json",
            success : function(dataStr) {
                if (dataStr.success) {
                    // 获取加密数据成功
                    wx.config({
                        debug : false,
                        appId : dataStr.message.appid, // 必填，公众号的唯一标识
                        timestamp : dataStr.message.timestamp, // 必填，生成签名的时间戳
                        nonceStr : dataStr.message.nonceStr, // 必填，生成签名的随机串
                        signature : dataStr.message.signature,// 必填，签名，见附录1
                        jsApiList: [
                                // 所有要调用的 API 都要加到这个列表中
                                'checkJsApi',
                                'onMenuShareTimeline',
                                'onMenuShareAppMessage'
                            ]
                    // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
                    });

                    wx.checkJsApi({
                        jsApiList: [
                            'onMenuShareTimeline',
                            'onMenuShareAppMessage'
                        ], // 需要检测的JS接口列表，所有JS接口列表见附录2,

                        success: function(res) {
                            // 以键值对的形式返回，可用的api值true，不可用为false
                            // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
                        }

                    });
                    wx.ready(function () {
                        wx.onMenuShareTimeline({
                            title: '转角店宝，您的进货法宝！', // 分享标题
                            desc: '注册送大礼，20元优惠券等你来领！', // 分享描述
                            link: 'http://'+host+'${root}/view/invite/reg.jsp?fromWho=1&suid='+supplierId+'&server='+host, // 分享链接
                            imgUrl: picpath, // 分享图标
                            type: '', // 分享类型,music、video或link，不填默认为link
                            dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
                            success: function () {
                                // 用户确认分享后执行的回调函数
                            },
                            cancel: function () {
                                // 用户取消分享后执行的回调函数
                            }
                        });
                        wx.onMenuShareAppMessage({
                            title: '转角店宝，您的进货法宝！', // 分享标题
                            desc: '注册送大礼，20元优惠券等你来领！', // 分享描述
                            link: 'http://'+host+'${root}/view/invite/reg.jsp?fromWho=1&suid='+supplierId+'&server='+host, // 分享链接
                            imgUrl: picpath, // 分享图标
                            type: '', // 分享类型,music、video或link，不填默认为link
                            dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
                            success: function () {
                                // 用户确认分享后执行的回调函数
                            },
                            cancel: function () {
                                // 用户取消分享后执行的回调函数
                            }
                        });
                    });
                }
            }
        });
    });
</script>
</body>
</html>
