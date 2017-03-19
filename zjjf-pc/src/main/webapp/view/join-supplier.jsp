<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>深圳市转角街坊网络科技有限公司</title>
    <jsp:include page="head.jsp" flush="true" />
    <link rel="stylesheet" href="../resources/css/join-supplier.css">
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
                <a href="javascript:void(0)" class="J-navLi">转角产品<i class="triangle-down"></i></a>
                <a href="join-shop.jsp">便利店订货</a>
                <a href="join-supplier.jsp" class="active">合作商入驻</a>
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
</header>
<div class="banner">
    <div class="w btn-join-box">
        <a href="join.jsp" class="btn-join">我要入驻</a>
    </div>
</div>
<main>
    <div class="content-advantage">
        <div class="g-title">
            <div class="main">入驻优势</div>
            <div class="subtitle">Join advantage</div>
        </div>
        <div class="w info clearfix">
            <dl>
                <dt><img src="../resources/images/join-supplier-advantage-icon1.png" alt=""></dt>
                <dd>拥有大量已注册的中小型便利店</dd>
            </dl>
            <dl>
                <dt><img src="../resources/images/join-supplier-advantage-icon2.png" alt=""></dt>
                <dd>软件接地气，便利店粘性强</dd>
            </dl>
            <dl>
                <dt><img src="../resources/images/join-supplier-advantage-icon3.png" alt=""></dt>
                <dd>“转角街坊”自有品牌的竞争力</dd>
            </dl>
            <dl>
                <dt><img src="../resources/images/join-supplier-advantage-icon4.png" alt=""></dt>
                <dd>与畅销产品品牌商直接合作</dd>
            </dl>
            <dl>
                <dt><img src="../resources/images/join-supplier-advantage-icon5.png" alt=""></dt>
                <dd>整合两个B端的ERP信息系统</dd>
            </dl>
            <dl>
                <dt><img src="../resources/images/join-supplier-advantage-icon6.png" alt=""></dt>
                <dd>良好的商家营销平台</dd>
            </dl>
        </div>
    </div>
    <div class="content-support">
        <div class="g-title">
            <div class="main">入驻支持</div>
            <div class="subtitle">Join support</div>
        </div>
        <div class="w info clearfix">
            <dl class="ml">
                <dt class="icon1"></dt>
                <dd class="title">软件技术支持</dd>
                <dd>
                    提供专业的进销存数据分析平台<br>
                    等互联网管理工具
                </dd>
            </dl>
            <dl class="mr">
                <dt class="icon2"></dt>
                <dd class="title">拓展服务支持</dd>
                <dd>
                    获得合理的配送利润<br>
                    拓展配送服务规模
                </dd>
            </dl>
            <dl class="ml">
                <dt class="icon3"></dt>
                <dd class="title">专员协助支持</dd>
                <dd>
                    专业的渠道专员协助维护<br>
                    和开拓其他商户
                </dd>
            </dl>
            <dl class="mr">
                <dt class="icon4"></dt>
                <dd class="title">客户服务支持</dd>
                <dd>
                    专业的客服人员提供售后服务
                </dd>
            </dl>
            <dl class="ml">
                <dt class="icon5"></dt>
                <dd class="title">品牌商品支持</dd>
                <dd>
                    众多转角合作品牌商的<br>
                    配送权或者分销权
                </dd>
            </dl>
            <dl class="mr">
                <dt class="icon6"></dt>
                <dd class="title">专业培训支持</dd>
                <dd>
                    提供专业的行业知识培训<br>
                    帮助进一步成长与发展
                </dd>
            </dl>
        </div>
    </div>
    <div class="content-demo">
        <div class="g-title">
            <div class="main">平台演示</div>
            <div class="subtitle">Demo platform</div>
        </div>
        <div class="w clearfix" id="J_demo">
            <nav class="nav">
                <div data-groupname="tab" data-tab="item" class="nav-item active"><i class="icon icon1"></i>APP订货，直接配送，方便快捷，无需手写，无需电话</div>
                <div data-groupname="tab" data-tab="item" class="nav-item"><i class="icon icon2"></i>商品齐全，信息统一维护</div>
                <div data-groupname="tab" data-tab="item" class="nav-item"><i class="icon icon3"></i>进、销、存统一管理，提升仓库利用率</div>
                <div data-groupname="tab" data-tab="item" class="nav-item"><i class="icon icon4"></i>联合采购，价格更优</div>
            </nav>
            <section data-groupname="tab" data-tab="content" class="show bg1"></section>
            <section data-groupname="tab" data-tab="content" class="show bg2"></section>
            <section data-groupname="tab" data-tab="content" class="show bg3"></section>
            <section data-groupname="tab" data-tab="content" class="show bg4"></section>
        </div>
    </div>
    <div class="content-partner">
        <div class="g-title">
            <div class="main">合作伙伴</div>
            <div class="subtitle">Partner</div>
        </div>
        <div class="info">
            <div class="img1"></div>
            <div class="img2"></div>
        </div>
    </div>
    <div class="content-process">
        <div class="g-title">
            <div class="main">注册流程</div>
            <div class="subtitle">Joining process</div>
        </div>
        <div class="info">
            <div class="w btn-join-box">
                <a href="join.jsp" class="btn-join">我要入驻</a>
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
