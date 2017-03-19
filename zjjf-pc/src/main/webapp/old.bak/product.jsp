<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>转角街坊-转角街坊</title>
<link href="favicon.ico" rel="icon" type="image/x-icon" />
<link rel="stylesheet" href="plugs/amazeui/css/amazeui.css" type="text/css">
<link rel="stylesheet" href="css/comm.css?22" type="text/css">
<link rel="stylesheet" href="css/product.css?22" type="text/css">
<!--[if lt IE 9]><script src="plugs/html5shiv.js"></script><![endif]-->
<script src="plugs/jquery-1.11.3.min.js"></script>
<script src="js/comm.js"></script>
<script src="plugs/amazeui/js/amazeui.min.js"></script>
<!--[if lte IE 8 ]>
	<script src="plugs/amazeui/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->
</head>
<body>
<header id="header">
	<div class="topnav wrap">
		<h1 class="logo"><a href="./index.jsp"><img src="images/logo.png" alt=""></a></h1>
		<nav>
			<ul>
				<li><a href="index.jsp">首页</a></li>
				<li><a href="product.jsp" class="curr">街坊产品</a></li>
				<li><a href="about.jsp">关于街坊</a></li>
				<li><a href="hiring.jsp">街坊招聘</a></li>
				<li><a href="join.jsp">合作加盟</a></li>
				<div class="lavalamp lavalamp2"></div>
			</ul>
		</nav>
	</div>
	<nav class="subnav">
		<section class="wrap">
			<div class="item item_curr product">
				<!--<a href="product-jfdb.jsp">街坊店宝</a>-->
				<a href="product.jsp" class="orange">转角街坊</a>
			</div>
		</section>
	</nav>
</header>
<div class="banner-product1"></div>
<main>
	<div class="index-main bg-gray" >
		<div class="wrap">
			<div class="txt01" data-am-scrollspy="{animation: 'slide-left'}"><img src="images/text01.gif" alt=""></div>
			<div class="img01" data-am-scrollspy="{animation: 'fade', delay: 100}"><img src="images/img01.png" alt=""></div>
		</div>
	</div>
	<div class="index-main">
		<div class="wrap">
			<div class="img02" data-am-scrollspy="{animation: 'fade', delay: 100}"><img src="images/img02.png" alt=""></div>
			<div class="txt02" data-am-scrollspy="{animation: 'slide-right'}"><img src="images/text02.gif" alt=""></div>
		</div>
	</div>
	<div class="index-main bg-gray">
		<div class="wrap">
			<div class="txt01" data-am-scrollspy="{animation: 'slide-left'}"><img src="images/text03.gif" alt=""></div>
			<div class="img03" data-am-scrollspy="{animation: 'fade', delay: 100}"><img src="images/img03.png" alt=""></div>
		</div>
	</div>
</main>
<jsp:include page="footer.html" flush="true" />
</body>
</html>