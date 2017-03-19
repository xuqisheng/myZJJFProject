<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>深圳市转角街坊网络科技有限公司</title>
    <jsp:include page="head.jsp" flush="true" />

    <link rel="stylesheet" href="../resources/css/product-kxb.css">
    <script>
        $(function() {
            tab('tab', 'mouseover');
        });
    </script>
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
                <a href="javascript:void(0)" class="active J-navLi">转角产品<i class="triangle-down"></i></a>
                <a href="join-shop.jsp">便利店订货</a>
                <a href="join-supplier.jsp">合作商入驻</a>
                <a href="join-dealer.jsp">供应商合作</a>
                <a href="javascript:void(0)" class="about J-navLi">关于转角<i class="triangle-down"></i></a>
                <a href="javascript:void(0)" class="login J-navLi">登录<i class="triangle-down"></i></a>
            </section>
        </div>
        <div class="subnav J-subNav">
            <section class="w hidden J-navLiSub">
                <a href="product-zjdb.jsp">转角店宝</a>
                <a href="product-kxb.jsp" class="active">快销宝</a>
                <span style="width: 586px; display: inline-block;"></span>
            </section>
            <section class="w J-navLiSub">
                <a href="about.jsp">公司简介</a>
                <a href="recruit.jsp">转角招聘</a>
                <a href="help.jsp">帮助中心</a>
                <a href="contact.jsp">联系我们</a>
            </section>
            <section class="w J-navLiSub">
                <a href="http://www.izjjf.cn/scms/scms/authority/scmsLoginPage.do" target="_blank">合作商</a>
            </section>
        </div>
    </nav>
    <div class="banner"></div>
</header>
<main>
    <div class="content-advantage">
        <div class="g-title">
            <div class="main">产品特征</div>
            <div class="subtitle">Product features</div>
        </div>
        <div class="w clearfix">
            <nav class="nav">
                <div data-groupname="tab" data-tab="item" class="nav-item active">提高销售量</div>
                <div data-groupname="tab" data-tab="item" class="nav-item">增强用户粘度</div>
                <div data-groupname="tab" data-tab="item" class="nav-item">提高效率</div>
                <div data-groupname="tab" data-tab="item" class="nav-item">提高回款效率</div>
                <div data-groupname="tab" data-tab="item" class="nav-item">商品齐全</div>
            </nav>
            <section data-groupname="tab" data-tab="content" class="show bg1"></section>
            <section data-groupname="tab" data-tab="content" class="show bg2"></section>
            <section data-groupname="tab" data-tab="content" class="show bg3"></section>
            <section data-groupname="tab" data-tab="content" class="show bg4"></section>
            <section data-groupname="tab" data-tab="content" class="show bg5"></section>
        </div>
    </div>
    <div class="content-process">
        <div class="g-title">
            <div class="main">产品优势</div>
            <div class="subtitle">Product advantage</div>
        </div>
        <div class="info">
            <div class="w btn-join-box">
                <a href="join.jsp" class="btn-join">立即加入</a>
            </div>
        </div>
    </div>
</main>
<jsp:include page="footer.jsp" flush="true" />
<script type="text/javascript">
    var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
    document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fc3135c3950bcfd3fb1df74b0cb41edbd' type='text/javascript'%3E%3C/script%3E"));
</script>
</body>
</html>
