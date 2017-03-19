<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>深圳市转角街坊网络科技有限公司</title>
    <jsp:include page="head.jsp" flush="true" />

    <link rel="stylesheet" href="../resources/css/join-shop.css">
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
                <a href="join-shop.jsp" class="active">便利店订货</a>
                <a href="join-supplier.jsp">合作商入驻</a>
                <a href="join-dealer.jsp">供应商合作</a>
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
            <a href="join.jsp" class="btn-join">我要订货</a>
        </div>
    </div>
</header>
<main>
    <div class="content-advantage">
        <div class="g-title">
            <div class="main">订货优势</div>
            <div class="subtitle">Join advantage</div>
        </div>
        <div class="w info clearfix">
            <dl class="ml">
                <dt class="icon1"></dt>
                <dd class="title">保证正品</dd>
                <dd>
                    所有商品转角街坊都严格把关，<br>
                    为您提供货物保障，保证正品
                </dd>
            </dl>
            <dl class="mr">
                <dt class="icon2"></dt>
                <dd class="title">送货快捷</dd>
                <dd>
                    实时跟进订单状态，<br>
                    确保更高效快捷的送货
                </dd>
            </dl>
            <dl class="ml">
                <dt class="icon3"></dt>
                <dd class="title">价格更优</dd>
                <dd>
                    加入转角街坊， <br>
                    我们将为您提供更优的价格， 增进收入
                </dd>
            </dl>
            <dl class="mr">
                <dt class="icon4"></dt>
                <dd class="title">降低库存</dd>
                <dd>
                    即点即送， 方便快捷，<br>
                    为您减轻库存压力， 且无断货之忧
                </dd>
            </dl>
            <dl class="ml">
                <dt class="icon5"></dt>
                <dd class="title">便捷下单</dd>
                <dd>
                    APP下单， 支持货到付款与在线支付<br>
                    两种付款模式， 坐等送货上门， 简单便捷
                </dd>
            </dl>
            <dl class="mr">
                <dt class="icon6"></dt>
                <dd class="title">售后保障</dd>
                <dd>
                    有专业的渠道人员及客服人员， <br>
                    及时跟进订单， 保障售后服务
                </dd>
            </dl>
        </div>
    </div>
    <div class="content-process">
        <div class="g-title">
            <div class="main">注册流程</div>
            <div class="subtitle">Joining process</div>
        </div>
        <div class="info">
            <div class="w btn-join-box">
                <a href="join.jsp" class="btn-join">我要订货</a>
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
