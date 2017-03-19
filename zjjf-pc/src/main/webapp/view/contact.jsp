<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>深圳市转角街坊网络科技有限公司</title>
    <jsp:include page="head.jsp" flush="true" />

    <link rel="stylesheet" href="../resources/css/contact.css">
</head>
<body>
<header>
    <nav class="g-nav" id="J-gNav">
        <div class="w clearfix">
            <section class="logo">
                <a href="index.jsp">
                    <img src="../resources/images/logo.png" width="263" height="34" alt="">
                </a>
            </section>
            <section class="menu">
                <a href="index.jsp">首页</a>
                <a href="javascript:void(0)" class="J-navLi">转角产品<i class="triangle-down"></i></a>
                <a href="join-shop.jsp">便利店订货</a>
                <a href="join-supplier.jsp">合作商入驻</a>
                <a href="join-dealer.jsp">供应商合作</a>
                <a href="javascript:void(0)" class="about active J-navLi">关于转角<i class="triangle-down"></i></a>
                <a href="javascript:void(0)" class="login J-navLi">登录<i class="triangle-down"></i></a>
            </section>
        </div>
        <div class="subnav J-subNav">
            <section class="w hidden J-navLiSub">
                <a href="product-zjdb.jsp">转角店宝</a>
                <a href="product-kxb.jsp">快销宝</a>
                <span style="width: 586px; display: inline-block;"></span>
            </section>
            <section class="w J-navLiSub">
                <a href="about.jsp">公司简介</a>
                <a href="recruit.jsp">转角招聘</a>
                <a href="help.jsp">帮助中心</a>
                <a href="contact.jsp" class="active">联系我们</a>
            </section>
            <section class="w J-navLiSub">
                <a href="http://www.izjjf.cn/scms/scms/authority/scmsLoginPage.do" target="_blank">合作商</a>
            </section>
        </div>
    </nav>
    <div class="banner"></div>
</header>
<main class="content clearfix">
    <div class="info">
        <div class="g-title">
            <div class="main">联系我们</div>
            <div class="subtitle">contact us</div>
        </div>
        <p>
            <b>客服热线：</b>400-664-3833
        </p>
        <p>
            <b>电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：</b>0755-86966002
        </p>
        <p>
            <b>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：</b>service@izjjf.cn
        </p>
        <p>
            <b>地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：</b>深圳市南山区粤兴二道6号武汉大学产学研大楼B座708
        </p>
    </div>
    <div id="map" class="map" width="435" height="320"></div>
</main>
<jsp:include page="footer.jsp" flush="true" />

<style>
    #map .BMap_cpyCtrl{display:none}
    #map img[src="http://api.map.baidu.com/images/copyright_logo.png"]{display:none}
</style>
<script src="http://api.map.baidu.com/api?v=1.4"></script>
<script>
    $(function(){
        var map = new BMap.Map("map");
        map.centerAndZoom(new BMap.Point(113.95, 22.53),17);
        map.enableScrollWheelZoom(true);
        var marker = new BMap.Marker(new BMap.Point(113.951444, 22.536345));
        var infoWindow = new BMap.InfoWindow("<span style='font-size:14px'>深圳市南山区粤兴二道6号武汉大学产学研大楼B座708</span>", {width : 220,height: 80,title : "<span style='font-size:16px;color:#ed4b1c;font-weight:bold;'>转角街坊网络科技有限公司</span>"});
        map.openInfoWindow(infoWindow, new BMap.Point(113.951444, 22.536345));
        marker.addEventListener("click", function(){
            marker.hide();
            map.openInfoWindow(infoWindow, new BMap.Point(113.951444, 22.536345));
        });
        infoWindow.addEventListener("close", function(){
            marker.show();
            map.addOverlay(marker);
        });
    });
</script>
<script type="text/javascript">
    var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
    document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fc3135c3950bcfd3fb1df74b0cb41edbd' type='text/javascript'%3E%3C/script%3E"));
</script>
</body>
</html>
