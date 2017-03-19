<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>深圳市转角街坊网络科技有限公司</title>
    <jsp:include page="head.jsp" flush="true" />

    <link rel="stylesheet" href="../resources/css/about.css">
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
                <a href="about.jsp" class="active">公司简介</a>
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
    <div class="content info-introduction">
        <div class="w">
            <div class="g-title">
                <div class="main">公司简介</div>
                <div class="subtitle">Company profile</div>
            </div>
            <div class="info">
            深圳市转角街坊网络科技有限公司是一家创新型互联网公司，以社区便利店为核心，将传统便利店升级为社区生活服务的入口，使人人从中受益。转角街坊从深圳出发，精耕细作，活跃便利店上万家，月交易额过亿，市场占有率第一，全国领先。现转角街坊走出深圳，并逐步打开其他重要区域。通过移动互联整合消费品供应链，打造国际领先的消费品行业互联网智慧平台。
            </div>
        </div>
    </div>
    <div class="content info-mission">
            <div class="w">
            <div class="g-title">
                <div class="main">企业文化</div>
                <div class="subtitle">Core team</div>
            </div>
            <div class="info info-about">
                <p>
                    <b>使&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;命</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;通过移动互联整合消费品供应链，使人人从中受益
                </p>
                <p>
                    <b>愿&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;景</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;成为国际领先的消费品行业互联网智慧平台
                </p>
                <p>
                    <b>价&nbsp;&nbsp;&nbsp;值&nbsp;&nbsp;&nbsp;观</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;简单、创新、责任、共赢
                </p>
                <p>
                    <b>经&nbsp;营&nbsp;理&nbsp;念</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;技术领先、便捷高效、有道理的便宜、注重用户体验
                </p>
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
