<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<title>街坊店宝</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<%@ include file="../common/head.jsp"%>

<link rel="stylesheet" href="${root}/resources/css/invite.css">
<script
	src="${root}/resources/vendor/zeroclipboard/ZeroClipboard.min.js"></script>
</head>
<body>
	<div class="content">
		<div class="banner"></div>
		<div class="box margin">
			<div class="box-top">邀请方式一</div>
			<div class="box-cont">
				<i class="icon icon1"></i>
				<div class="h2">微信邀请</div>
				<div>
					这是您的专属邀请链接，打开微信“扫一扫”，点击右上角“发送给朋友”<br> 或“分享到朋友圈”即可。<br> <img
						src="${root}/scms/storegruop/creatQrImage.do" width="200"
						height="200" alt="">
				</div>
			</div>
		</div>
		<div class="box">
			<div class="box-top">邀请方式二</div>
			<div class="box-cont">
				<i class="icon icon2"></i>
				<div class="h2">链接邀请</div>
				<div>您可以复制该链接发送给您的好友。</div>
				<div class="mt-default mb-default">
					<input type="text" value="${condition}" id="J_link"
						class="input-search-text" style="width: 100%">
				</div>
				<input type="button" id="J_copyLink" data-clipboard-target="J_link"
					value="复制链接" class="button">
			</div>
		</div>
		<script>
    function flashChecker() {
    var hasFlash = 0;//是否安装了flash
    var flashVersion = 0;//flash版本
    if(document.all) {
    var swf = new ActiveXObject('ShockwaveFlash.ShockwaveFlash');
    if(swf) {
    hasFlash = 1;
    VSwf = swf.GetVariable("$version");
    flashVersion = parseInt(VSwf.split(" ")[1].split(",")[0]);
    }
    } else {
    if (navigator.plugins && navigator.plugins.length > 0) {
    var swf = navigator.plugins["Shockwave Flash"];
    if (swf) {
    hasFlash = 1;
    var words = swf.description.split(" ");
    for (var i = 0; i < words.length; ++i) {
    if (isNaN(parseInt(words[i]))) continue;
    flashVersion = parseInt(words[i]);
    }
    }
    }
    }
    return {
        f: hasFlash,
        v: flashVersion
        };
    }
        $(function() {
            $('#J_copyLink').on('click', function() {
                var fls = flashChecker();
                if(!fls.f) {
                    alert("您没有安装flash，请手动复制。");
                }
            });
            var client = new ZeroClipboard($('#J_copyLink'));
            client.on('ready', function(readyEvent) {
                client.on('aftercopy', function(event) {
                    // event.target.style.display = "none";
                    // alert("Copied text to clipboard: " + event.data["text/plain"] );
                    alert("已经复制到粘贴板！" );
                } );
            } );
        });
    </script>
</body>
</html>
