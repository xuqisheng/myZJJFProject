<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>深圳市转角街坊网络科技有限公司</title>
    <%--<title>转角街坊 — 打造国际领先的消费品行业互联网智慧平台</title>--%>
    <jsp:include page="head.jsp" flush="true" />
    <link rel="stylesheet" href="../resources/css/index.css">
    <script src="../resources/vendor/slick/slick.min.js"></script>
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
                <a href="index.jsp" class="active">首页</a>
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
            <section class="w hidden J-navLiSub">
                <a href="about.jsp">公司简介</a>
                <a href="recruit.jsp">转角招聘</a>
                <a href="help.jsp">帮助中心</a>
                <a href="contact.jsp">联系我们</a>
            </section>
            <section class="w hidden J-navLiSub">
                <a href="http://www.izjjf.cn/scms/scms/authority/scmsLoginPage.do" target="_blank">合作商</a>
            </section>

        </div>
    </nav>
    <div class="slide" id="slide">
<!--        <div class="slide-item" style="background: #acdfef;">
            <a href="about.jsp" style="background: url('../resources/images/banner-index/201604001.png') no-repeat bottom center"></a>
        </div>
        <div class="slide-item" style="background: #b9dcff;">
            <a href="recruit.jsp" style="background: url('../resources/images/banner-index/201604002.png') no-repeat bottom center"></a>
        </div>
        <div class="slide-item" style="background: #fff2de;">
            <a href="join-supplier.jsp" style="background: url('../resources/images/banner-index/201604004.png') no-repeat bottom center"></a>
        </div>  -->
    </div>
</header>

<script>
$(function(){
	$.post("/zjjf-pc/pc/advertisement/getAdvertisementByPositionId.do",{positionId:0,pageIndex:1,pageSize:5},function(data){
		var html = "";
		if(data.success){
			$.each(data.message,function(i,item){
				html+='<div class="slide-item" style="background: '+item.backgroud+';">';
				if(item.menuPage==null || item.menuPage==""){
					html+='<a href="#" style="background: url('+'http://www.izjjf.cn/'+item.adUrl+') no-repeat bottom center"></a>'
				}else{
					html+='<a href="'+item.menuPage+'?id='+item.id+'" style="background: url('+'http://www.izjjf.cn/'+item.adUrl+') no-repeat bottom center"></a>'
				}
				html+='</div>';
			})
		}
		if(html == ""){
			html="<div>无数据</div>";
		}
		console.log(html)
		$("#slide").html(html);
        $('#slide').slick({
            autoplay: true,
            speed: 500,
            arrows: false,
            dots: true,
            infinite: true
        });

	},'json')
})

</script>
<!--     <script>
        $(function() {
            $('.slide').slick({
                autoplay: true,
                speed: 500,
                arrows: false,
                dots: true,
                infinite: true
            });
        });
    </script> -->
<main class="w content clearfix">
    <div class="box">
        <h1>便利店</h1>
        <h2>CVS</h2>
        <div class="link-btn-group">
            <a href="join-shop.jsp" class="link-btn">订货<span class="gt"></span></a>
        </div>
        <div class="pic pic-shop"></div>
    </div>
    <div class="box">
        <h1>区域合作商</h1>
        <h2>Regional partner</h2>
        <div class="link-btn-group">
            <%--<a href="join-supplier.jsp" class="link-btn">加盟<span class="gt"></span></a>--%>
            <a href="http://www.izjjf.cn/scms/scms/authority/scmsLoginPage.do" class="link-btn" target="_blank">登录<span class="gt"></span></a>
        </div>
        <div class="pic pic-supplier"></div>
    </div>
    <div class="box last">
        <h1>供应商</h1>
        <h2>Brand manufacturers</h2>
        <div class="link-btn-group">
            <a href="join-dealer.jsp" class="link-btn">合作<span class="gt"></span></a>
        </div>
        <div class="pic pic-dealer"></div>
    </div>
</main>
<jsp:include page="footer.jsp" flush="true" />
<script type="text/javascript">
    var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
    document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fc3135c3950bcfd3fb1df74b0cb41edbd' type='text/javascript'%3E%3C/script%3E"));
</script>
</body>
</html>
