<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>深圳市转角街坊网络科技有限公司</title>
    <jsp:include page="head.jsp" flush="true" />

    <link rel="stylesheet" href="../resources/css/help.css">
    <script>
        $(function() {
            tab('tab');
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
                <a href="help.jsp" class="active">帮助中心</a>
                <a href="contact.jsp">联系我们</a>
            </section>
            <section class="w J-navLiSub">
                <a href="http://www.izjjf.cn/scms" target="_blank">合作商</a>
            </section>
        </div>
    </nav>
</header>
<main>
    <div class="banner"></div>
    <div class="content">
        <div class="w clearfix">
            <div class="info-item">
                <a href="#shop" class="item shop">便利店答疑</a>
            </div>
            <div class="info-item">
                <a href="#supplier" class="item supplier">合作商答疑</a>
            </div>
            <div class="info-item">
                <a href="#dealer" class="item dealer">供应商答疑</a>
            </div>
        </div>
        <div class="w info-content">
            <div id="shop">
                <div class="title one">便利店</div>
                <div class="cont">
                    <div data-groupname="tab" data-tab="item" class="tit active">1、加入转角街坊有哪些优势呢？</div>
                    <div data-groupname="tab" data-tab="content" class="tit-cont">
                        <span class="emphasize">保证正品</span>  所有商品转角街坊都严格把关，为您提供货物保障，保证正品。<br>
                        <span class="emphasize">价格更优</span>  加入转角街坊，我们将为您提供更优的价格，增进收入。<br>
                        <span class="emphasize">便捷下单</span>  APP操作下单，支持货到付款与在线支付两种付款模式，坐等送货上门，简单便捷。<br>
                        <span class="emphasize">送货快捷</span>  实时跟进订单状态，确保更高效快捷的送货。<br>
                        <span class="emphasize">降低库存</span>  即点即送，方便快捷，为您减轻库存压力，且无断货之忧。<br>
                        <span class="emphasize">售后保障</span>  有专业的渠道人员及客服人员，及时跟进订单，保障售后服务。
                    </div>
                    <div data-groupname="tab" data-tab="item" class="tit">2、便利店希望和转角街坊合作，需要通过什么方式？</div>
                    <div data-groupname="tab" data-tab="content" class="tit-cont">
                        您可直接在转角街坊官网“便利店订货”页面提交合作意向，也可以拨打400-664-3833将贵司基本信息提交给客服，我们的同事会在5个工作日内和您取得联系，洽谈具体合作事宜。
                    </div>
                    <div data-groupname="tab" data-tab="item" class="tit">3、提交申请后多久会得到回复？</div>
                    <div data-groupname="tab" data-tab="content" class="tit-cont">
                        我们的同事会在5个工作日内和您取得联系，洽谈具体合作事宜
                    </div>
                    <div data-groupname="tab" data-tab="item" class="tit">4、若遇到问题如何联系转角街坊？</div>
                    <div data-groupname="tab" data-tab="content" class="tit-cont">
                        拨打400-664-3833客服热线
                    </div>
                    <div data-groupname="tab" data-tab="item" class="tit">5、如何进行产品更新？</div>
                    <div data-groupname="tab" data-tab="content" class="tit-cont">
                        <span class="emphasize">安卓手机：</span>各大应用商城下载更新（如腾讯应用宝）。<br>
                        <span class="emphasize">苹果手机：</span>苹果应用商城下载更新。
                    </div>
                    <div data-groupname="tab" data-tab="item" class="tit">6、如何修改密码？</div>
                    <div data-groupname="tab" data-tab="content" class="tit-cont">
                        用户登陆转角店宝APP后，点击“我的”，“设置”，“修改密码”，输入注册手机号码，获取验证码，输入新密码，确认新密码，点击提交即可。
                    </div>
                    <div data-groupname="tab" data-tab="item" class="tit">7、忘记密码了怎么办？</div>
                    <div data-groupname="tab" data-tab="content" class="tit-cont">
                        登录页面点击“忘记密码”，输入手机号，点击获取验证码，输入短信内6位数字，输入新密码，确认新密码，点击提交即可。
                    </div>
                    <div data-groupname="tab" data-tab="item" class="tit">8、如何进行订单管理？</div>
                    <div data-groupname="tab" data-tab="content" class="tit-cont">
                        用户登陆转角店宝APP后，点击“订单”，即可看到“全部”、“待付款”、“待发货”、“待收货”、“待评价”进行管理。
                    </div>
                    <div data-groupname="tab" data-tab="item" class="tit">9、商品配送时间以及方式？</div>
                    <div data-groupname="tab" data-tab="content" class="tit-cont">
                        专业的配送人员将在24小时内配送完成。
                    </div>
                    <div data-groupname="tab" data-tab="item" class="tit">10、商品进价是否有优势？</div>
                    <div data-groupname="tab" data-tab="content" class="tit-cont">
                        我们的价格低于市场价。
                    </div>
                    <div data-groupname="tab" data-tab="item" class="tit">11、是否有商品退换货政策？</div>
                    <div data-groupname="tab" data-tab="content" class="tit-cont">
                        实行三包政策。
                    </div>
                    <div data-groupname="tab" data-tab="item" class="tit">12、优惠券怎么使用？</div>
                    <div data-groupname="tab" data-tab="content" class="tit-cont">
                        优惠劵在有效期内当次使用，直接减免费用。
                    </div>
                </div>
            </div>
            <div id="supplier">
                <div class="title two">合作商</div>
                <div class="cont">
                    <div data-groupname="tab" data-tab="item" class="tit">1、加入转角街坊有哪些优势呢？</div>
                    <div data-groupname="tab" data-tab="content" class="tit-cont">
                        （1）、拥有大量已注册的中小型便利店<br>
                        （2）、软件接地气，便利店粘性强<br>
                        （3）、“转角街坊”自有品牌的竞争力<br>
                        （4）、与畅销产品供应商直接合作<br>
                        （5）、整合两个B端的ERP信息系统<br>
                        （6）、良好的商家营销平台
                    </div>
                    <div data-groupname="tab" data-tab="item" class="tit">2、转角街坊为合作商提供哪些支持？</div>
                    <div data-groupname="tab" data-tab="content" class="tit-cont">
                        <span class="emphasize">软件技术支持</span>  提供专业的进销存数据分析平台等互联网管理工具<br>
                        <span class="emphasize">专员协助支持</span>  专业的渠道专员协助维护和开拓其他商户<br>
                        <span class="emphasize">供应商品支持</span>  众多转角合作供应商的配送权或者分销权<br>
                        <span class="emphasize">拓展服务支持</span>  获得合理的配送利润，拓展配送服务规模<br>
                        <span class="emphasize">客户服务支持</span>  专业的客服人员提供售后服务<br>
                        <span class="emphasize">专业培训支持</span>  提供专业的行业知识培训，帮助进一步成长与发展<br>
                    </div>
                    <div data-groupname="tab" data-tab="item" class="tit">3、合作商希望和转角街坊合作，需要通过什么方式</div>
                    <div data-groupname="tab" data-tab="content" class="tit-cont">
                        您可直接在转角街坊官网“合作商入驻”页面提交合作意向，也可以拨打400-664-3833将贵司基本信息提交给客服，我们的同事会在5个工作日内和您取得联系，洽谈具体合作事宜。
                    </div>
                    <div data-groupname="tab" data-tab="item" class="tit">4、加入转角街坊需要什么样的条件？</div>
                    <div data-groupname="tab" data-tab="content" class="tit-cont">
                        双方签署合作商协议、提供合法公司证件、银行账号信息。
                    </div>
                    <div data-groupname="tab" data-tab="item" class="tit">5、忘记密码怎么办？</div>
                    <div data-groupname="tab" data-tab="content" class="tit-cont">
                        在登录页面，点击“忘记密码”，输入手机号获取验证码进行找回。
                    </div>
                    <div data-groupname="tab" data-tab="item" class="tit">6、如何打印订单？</div>
                    <div data-groupname="tab" data-tab="content" class="tit-cont">
                        登录合作商后台，进入订单模块，进行打印订单。
                    </div>
                </div>
            </div>
            <div id="dealer">
                <div class="title three">供应商</div>
                <div class="cont">
                    <div data-groupname="tab" data-tab="item" class="tit">1、供应商入驻有哪些优势？</div>
                    <div data-groupname="tab" data-tab="content" class="tit-cont">
                        （1）、扁平化的渠道 商品快速直达终端<br>
                        （2）、线上线下联合促销 确保营销落地<br>
                        （3）、创新的终端新媒体广告位<br>
                        （4）、供应链统一定价，严格保护供应商市场价格标准<br>
                        （5）、独有的软件平台，与您共享核心大数据<br>
                        （6）、专业的运营、业务团队跟进
                    </div>
                    <div data-groupname="tab" data-tab="item" class="tit">2、现在与转角街坊合作的供应商有哪些？</div>
                    <div data-groupname="tab" data-tab="content" class="tit-cont">
                        现阶段与转角街坊合作的供应商有百威、怡宝、东鹏特饮、红牛、景田、百事等品牌。
                    </div>
                    <div data-groupname="tab" data-tab="item" class="tit">3、品牌希望和转角街坊合作，需要通过什么方式？</div>
                    <div data-groupname="tab" data-tab="content" class="tit-cont">
                        您可直接在转角街坊官网“供应商合作”页面提交合作意向，也可以拨打400-664-3833将贵司基本信息提交给客服，我们的同事会在5个工作日内和您取得联系，洽谈具体合作事宜。
                    </div>
                    <div data-groupname="tab" data-tab="item" class="tit">4、加入转角街坊，需要缴纳费用吗？</div>
                    <div data-groupname="tab" data-tab="content" class="tit-cont">
                        需要缴纳保证金。
                    </div>
                </div>
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
