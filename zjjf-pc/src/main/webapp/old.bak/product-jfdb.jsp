<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>转角街坊-街坊店宝</title>
<link href="favicon.ico" rel="icon" type="image/x-icon" />
<link rel="stylesheet" href="plugs/amazeui/css/amazeui.css" type="text/css">
<link rel="stylesheet" href="css/comm.css?122" type="text/css">
<link rel="stylesheet" href="css/product.css?122" type="text/css">
<!--[if lt IE 9]><script src="plugs/html5shiv.js"></script><![endif]-->
<script src="plugs/jquery-1.11.3.min.js"></script>
<script src="js/comm.js?122"></script>
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
				<li><a href="product-jfdb.jsp" class="curr">街坊店宝</a></li>
				<li><a href="about.jsp">关于街坊</a></li>
				<li><a href="hiring.jsp">街坊招聘</a></li>
				<li><a href="join.jsp">合作加盟</a></li>
				<div class="lavalamp lavalamp2"></div>
			</ul>
		</nav>
	</div>
</header>
<div class="banner-product2"></div>
<main>
	<div class="index-main bg-gray" >
		<div class="wrap">
			<div class="txt01" data-am-scrollspy="{animation: 'slide-left'}"><img src="images/product2_t1.png" alt=""></div>
			<div class="img01" data-am-scrollspy="{animation: 'fade', delay: 100}"><img src="images/product2_i1.png" alt=""></div>
		</div>
	</div>
	<div class="index-main">
		<div class="wrap">
			<div class="img02" data-am-scrollspy="{animation: 'fade', delay: 100}"><img src="images/product2_i2.png" alt=""></div>
			<div class="txt02" data-am-scrollspy="{animation: 'slide-right'}"><img src="images/product2_t2.png" alt=""></div>
		</div>
	</div>
	<div class="index-main bg-gray">
		<div class="wrap">
			<div class="txt01" data-am-scrollspy="{animation: 'slide-left'}"><img src="images/product2_t3.png" alt=""></div>
			<div class="img03" data-am-scrollspy="{animation: 'fade', delay: 100}"><img src="images/product2_i3.png" alt=""></div>
		</div>
	</div>
</main>
<jsp:include page="footer.html" flush="true" />
</body>
</html>