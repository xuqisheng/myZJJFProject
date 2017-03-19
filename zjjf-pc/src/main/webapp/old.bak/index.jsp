<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>转角街坊</title>
<link href="favicon.ico" rel="icon" type="image/x-icon" />
<link rel="stylesheet" href="plugs/amazeui/css/amazeui.css" type="text/css">
<link rel="stylesheet" href="plugs/amazeui/plugs/amazeui.swiper.min.css" type="text/css">
<link rel="stylesheet" href="css/comm.css?122" type="text/css">
<link rel="stylesheet" href="css/index.css?122" type="text/css">
<!--[if lt IE 9]><script src="plugs/html5shiv.js"></script><![endif]-->
<script src="plugs/jquery-1.11.3.min.js"></script>
<script src="js/comm.js?122"></script>
<script src="plugs/amazeui/js/amazeui.min.js"></script>
<!--[if lte IE 8 ]>
	<script src="plugs/amazeui/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->
<script src="plugs/amazeui/plugs/amazeui.swiper.min.js"></script>
<script>
	$(function() {
		//导航
		$('#header .subnav').hide();
		$('#header .topnav nav').on('mouseover', '.sub', function(){
			var index = $('#header .topnav nav .sub').index(this);
			$('#header .subnav').show().find('.item').eq(index).addClass('item_curr').siblings('.item').removeClass('item_curr');
		}).on('mouseout', function(){
			$('#header .subnav').hide().on('mouseover', function(){
				$(this).show();
			}).on('mouseout', function(){
				$(this).hide();
			});
		});
		//幻灯片
//	    $('#swiper').swiper({
//			pagination: '#swiper .swiper-pagination',
//			paginationClickable: true,
//			autoplay: 5000,
//    		autoplayDisableOnInteraction: false,
//    		loop: true
//		});
	});
</script>
</head>
<body>
<header id="header">
	<div class="topnav wrap">
		<h1 class="logo"><a href="./index.jsp"><img src="images/logo.png" alt=""></a></h1>
		<nav>
			<ul>
				<li><a href="index.jsp" class="curr">首页</a></li>
				<li><a href="product-jfdb.jsp">街坊店宝</a></li>
				<li><a class="sub" href="about.jsp">关于街坊</a></li>
				<li><a class="sub" href="hiring.jsp">街坊招聘</a></li>
				<li><a href="join.jsp">合作加盟</a></li>
				<div class="lavalamp"></div>
			</ul>
		</nav>
	</div>
	<nav class="subnav">
		<section class="wrap">
			<%--
			<div class="item product">
				<a href="product-jfdb.jsp">街坊店宝</a>
				<!--<a href="product.jsp">转角街坊</a>-->
			</div>
			--%>
			<div class="item about">
				<span><a href="about.jsp">公司简介</a></span>
				<span><a href="about-record.jsp">记录街坊</a></span>
				<span><a href="about-contact.jsp">联系我们</a></span>
			</div>
			<div class="item jiefang">
				<a href="hiring.jsp">街坊招聘</a>
				<a href="in-zj.jsp">人在街坊</a>
			</div>
		</section>
	</nav>
</header>
<main>
	<section id="swiper" class="swiper-container">
	    <div class="swiper-wrapper">
	        <div class="swiper-slide"><a style="display:block;width:100%;height:606px;background:url(./images/index_banner.png) center top no-repeat;" href="product-jfdb.jsp"></a></div>
	        <!--<div class="swiper-slide"><a style="display:block;width:100%;height:606px;background:url(./images/index_banner2.png) center top no-repeat;" href="product-jfdb.jsp"></a></div>-->
	    </div>
		<!--<div class="swiper-pagination"></div>-->
	</section>
	<section class="content wrap">
		<div class="box">
			<h2><i class="icon icon1"></i>关于街坊<a href="about.jsp">更多&gt;</a></span></h2>
			<a href="about.jsp"><img src="images/index_box1.png" width="" alt=""></a>
			<div class="p">
				<dd><a href="about.jsp">公司简介 - 深圳转角街坊电子商务公司……</a></dd>
				<dd><a href="about-record.jsp">记录街坊 - “精诚合作、携手共赢”合作……</a></dd>
				<dd><a href="about-contact.jsp">联系我们 - 客服热线：400-664-3833</a></dd>
			</div>
		</div>
		<div class="box">
			<h2><i class="icon icon2"></i>最新招聘<a href="hiring.jsp">更多&gt;</a></span></h2>
			<a href="hiring.jsp"><img src="images/index_box2.png" width="" alt=""></a>
			<div class="p">
				<p><a href="hiring.jsp">产品类 - 产品经理</a></p>
				<p><a href="hiring.jsp">运营类 - 微信运营专员</a></p>
				<p><a href="hiring.jsp">设计类 - GUI设计师</a></p>
				<p><a href="hiring.jsp">研发类 - WEB前端开发工程师</a></p>
				<p><a href="hiring.jsp">市场类 - 渠道专员</a></p>
			</div>
		</div>
		<div class="box last">
			<h2><i class="icon icon3"></i>合作加盟<a href="join.jsp">更多&gt;</a></span></h2>
			<a href="join.jsp"><img src="images/index_box3.png" width="" alt=""></a>
			<div class="p">
				<p><a href="join.jsp">批发商合作加盟</a></p>
				<p><a href="join-store.jsp">便利店合作加盟</a></p>
			</div>			
		</div>
	</section>
</main>
<jsp:include page="footer.html" flush="true" />
</body>
</html>