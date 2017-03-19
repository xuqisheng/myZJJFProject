<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>深圳市转角街坊网络科技有限公司</title>
    <jsp:include page="head.jsp" flush="true" />

    <link rel="stylesheet" href="../resources/css/join.css">
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
<main>
    <div class="content">
        <div class="g-title">
            <div class="main">极速加入</div>
            <div class="subtitle">Speed join</div>
        </div>
        <div class="info-box">
            <div class="info">
                <div class="title"></div>
                <form id="formjoin" method="post" target="hiddeniframe">
                    <div class="item">
                        <select name="type">
                            <option value="1">便利店</option>
                            <option value="2">合作商</option>
                            <option value="3">供应商</option>
                        </select>
                    </div>
	                <div class="item">
	                    <input type="text" name="name" id="name" placeholder="姓名">
	                    <span class="star">*</span>
	                    <span class="tips"></span>
	                </div>
	                <div class="item">
	                    <input type="text" name="mobile" id="mobile" placeholder="电话">
	                    <span class="star">*</span>
	                    <span class="tips"></span>
	                </div>
	                <div class="item">
	                    <input type="text" name="storeName" id="storeName" placeholder="商户名称">
	                    <span class="star">*</span>
	                    <span class="tips"></span>
	                </div>
	                <div class="item">
	                    <input type="text" name="storeAdress" id="storeAdress" placeholder="商户地址">
	                    <span class="star">*</span>
	                    <span class="tips"></span>
	                </div>
	                <div class="item textarea">
	                    <textarea name="userMessage" id="userMessage" placeholder="留言"></textarea>
	                </div>
	                <div class="item code clearfix">
	                    <input type="text" name="checkCode" id="checkCode" placeholder="验证码">
	                    <img src="" onclick="javascript:changeImg()" id="codeimg" alt="checkcode" height="50" width="120">
	                    <span class="star">*</span>
	                    <span class="tips"></span>
	                </div>
	                <div class="item">
	                    <input type="submit" value="提交" class="btn-submit">
	                </div>
                </form>
                <iframe name="hiddeniframe" src="" style="display:none"></iframe>
                <script src="../resources/js/join.js?abc"></script>
            </div>
            <div class="banner"></div>
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
