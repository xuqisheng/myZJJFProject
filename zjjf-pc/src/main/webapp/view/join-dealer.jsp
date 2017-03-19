<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>深圳市转角街坊网络科技有限公司</title>
    <jsp:include page="head.jsp" flush="true" />

    <link rel="stylesheet" href="../resources/css/join-dealer.css">
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
                <a href="join-dealer.jsp" class="active">供应商合作</a>
                <a href="javascript:void(0)" class="about J-navLi">关于转角<i class="triangle-down"></i></a>
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
                <a href="contact.jsp">联系我们</a>
            </section>
            <section class="w J-navLiSub">
                <a href="http://www.izjjf.cn/scms/scms/authority/scmsLoginPage.do" target="_blank">合作商</a>
            </section>
        </div>
    </nav>
    <div class="banner">
        <div class="w btn-join-box">
            <a href="join.jsp" class="btn-join">我要供货</a>
        </div>
    </div>
</header>
<main>
    <div class="content-advantage">
        <div class="g-title">
            <div class="main">合作优势</div>
            <div class="subtitle">Join advantage</div>
        </div>
        <div class="w info clearfix">
            <dl class="ml">
                <dt class="icon1"></dt>
                <dd>
                    扁平化的渠道， <br>
                    商品快速直达终端
                </dd>
            </dl>
            <dl class="mr">
                <dt class="icon2"></dt>
                <dd>
                    供应链统一定价，<br>
                    严格保护品牌商市场价格标准
                </dd>
            </dl>
            <dl class="ml">
                <dt class="icon3"></dt>
                <dd>
                    线上线下联合促销， <br>
                    确保营销落地
                </dd>
            </dl>
            <dl class="mr">
                <dt class="icon4"></dt>
                <dd>
                    独有的软件平台，<br>
                    与您共享核心大数据
                </dd>
            </dl>
            <dl class="ml">
                <dt class="icon5"></dt>
                <dd class="dd">
                    创新的终端新媒体广告位
                </dd>
            </dl>
            <dl class="mr">
                <dt class="icon6"></dt>
                <dd class="dd">
                    专业的运营、业务团队跟进
                </dd>
            </dl>
        </div>
    </div>
    <div class="content-brand">
        <div class="g-title">
            <div class="main">合作品牌</div>
            <div class="subtitle">Cooperation brand</div>
        </div>
        <div class="info"></div>
    </div>
    <div class="content-process">
        <div class="g-title">
            <div class="main">注册流程</div>
            <div class="subtitle">Joining process</div>
        </div>
        <div class="info">
            <div class="w btn-join-box">
                <a href="join.jsp" class="btn-join">我要供货</a>
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
