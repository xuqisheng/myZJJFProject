<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>转角店宝</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=720, user-scalable = no">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <link rel="icon" href="${root}/resources/images/favicon.png" type="image/x-icon">
    <link rel="stylesheet" href="${root}/resources/css/normalize.css">
    <link rel="stylesheet" href="${root}/resources/css/invite-reg.css">
        <script src="${root}/resources/vendor/jquery/jquery-2.2.3.min.js"></script>
</head>
<body>
    <div class="top">
        欢<span class="sep">·</span>
        迎<span class="sep">·</span>
        光<span class="sep">·</span>
        临<span class="sep">·</span>
        转<span class="sep">·</span>
        角<span class="sep">·</span>
        街<span class="sep">·</span>
        坊
    </div>
    <div class="logo"></div>
    <div class="success">
        <div class="h2">
            <img src="../../resources/images/invite-reg-success.png" alt="">
            注册成功 资料审核中
        </div>
        <div class="h3">
            审核通过后我们会以短信通知您<br>
            届时即可使用转角店宝<br>
            享受在线低价订货
        </div>
        <i class="mm"></i>
    </div>
    <a href="" class="btn-submit" id="J_dlLink">立即下载 转角店宝</a>
    <span class="tel">
        客服热线：400-664-3833
    </span>
    <script>
    function getQueryString(name) {
    	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    	var r = window.location.search.substr(1).match(reg);
    	if (r != null) return unescape(r[2]); return null;
    	}

    function osType() {
        var userAgentInfo = navigator.userAgent;
        var agentsIOS = ['iPhone', 'iPad', 'iPod'];
        var osType = '';
        if (userAgentInfo.indexOf('Android') > 0) {
            osType = 'android';
        }
        for(var v = 0; v < agentsIOS.length; v++) {
            if(userAgentInfo.indexOf(agentsIOS[v]) > 0) {
                osType = 'ios';
                break;
            }
        }
        return osType;
    }

    $(function() {
    	 var host = getQueryString("server");
    	// $.post("http://"+host+"/CornerV2/anno/getKDBVersionInfo.do",null,

    		 		//alert(data.message);
		    		 if(osType() == 'android') {

		    	    		$('#J_dlLink').attr('href',"http://"+host+"/zjjf-kefu/view/html/download-zjdb-app.html");
		    	    	} else if(osType() == 'ios')  {
		    	    		$('#J_dlLink').attr('href', "http://"+host+"/zjjf-kefu/view/html/download-zjdb-app.html");
		    	    	} else {
		    	    		alert("对不起，只支持android和ios系统。");
		    	    		//alert(data.message.installUrl);
		    	    		$('#J_dlLink').attr('href', "http://"+host+"/zjjf-kefu/view/html/download-zjdb-app.html");
		    	    	}

    			//  "json");//这里返回的类型有：json,html,xml,text

    });

    </script>
</body>
</html>
