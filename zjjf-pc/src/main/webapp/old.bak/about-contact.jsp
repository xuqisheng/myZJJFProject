<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>转角街坊-关于我们</title>
<link href="favicon.ico" rel="icon" type="image/x-icon" />
<link rel="stylesheet" href="plugs/amazeui/css/amazeui.css" type="text/css">
<link rel="stylesheet" href="css/comm.css?122" type="text/css">
<link rel="stylesheet" href="css/about.css?122" type="text/css">
<!--[if lt IE 9]><script src="plugs/html5shiv.js"></script><![endif]-->
<script src="plugs/jquery-1.12.0.min.js"></script>
<script src="js/comm.js?122"></script>
<script src="http://api.map.baidu.com/api?v=1.4"></script>
<script>
	$(function(){
		var map = new BMap.Map("map");
		map.centerAndZoom(new BMap.Point(113.951444,22.536345),18);
		map.enableScrollWheelZoom(true);
		var marker = new BMap.Marker(new BMap.Point(113.951444,22.536345));
		var infoWindow = new BMap.InfoWindow("<span style='font-size:14px'>深圳市南山区粤兴二道6号武汉大学产学研大楼B座708</span>", {width : 220,height: 80,title : "<span style='font-size:16px;color:#ed4b1c;font-weight:bold;'>转角街坊电子商务有限公司</span>"});
		map.openInfoWindow(infoWindow, new BMap.Point(113.951444,22.536345));
		marker.addEventListener("click",function(){
			marker.hide();
			map.openInfoWindow(infoWindow, new BMap.Point(113.951444,22.536345));
		});
		infoWindow.addEventListener("close",function(){
			marker.show();
			map.addOverlay(marker);
		});
	});
</script>
<style>
	#map .BMap_cpyCtrl{display:none}
	#map img[src="http://api.map.baidu.com/images/copyright_logo.png"]{display:none}
</style>
</head>
<body>
<header id="header">
	<div class="topnav wrap">
		<h1 class="logo"><a href="./index.jsp"><img src="images/logo.png" alt=""></a></h1>
		<nav>
			<ul>
				<li><a href="index.jsp">首页</a></li>
				<li><a href="product-jfdb.jsp">街坊店宝</a></li>
				<li><a href="about.jsp" class="curr">关于街坊</a></li>
				<li><a href="hiring.jsp">街坊招聘</a></li>
				<li><a href="join.jsp">合作加盟</a></li>
				<div class="lavalamp lavalamp3"></div>
			</ul>
		</nav>
	</div>
	<nav class="subnav">
		<section class="wrap">
			<div class="item item_curr about">
				<span><a href="about.jsp">公司简介</a></span>
				<span><a href="about-record.jsp">记录街坊</a></span>
				<span><a class="orange" href="about-contact.jsp">联系我们</a></span>
			</div>
		</section>
	</nav>
</header>
<div class="banner-about"></div>
<main>
	<section>
		<div class="contact-box wrap">
			<div class="contact-txt">
				<h1>联系我们</h1>
				<p><span>客服热线：</span>400-664-3833</p>
				<p><span>电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：</span>0755-86966002</p>
				<p><span>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：</span>service@izjjf.cn</p>
				<p>
					<span style="height: 22px;">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：</span>深圳市南山区粤兴二道6号武汉大学产学研大楼
				</p>
			</div>
			<!--<div class="contact-img">-->
				<!--<img src="images/contact.png" width="291" alt="">-->
			<!--</div>-->
			<div id="map" class="contact-map" width="435" height="320"></div>
		</div>
	</section>
</main>
<jsp:include page="footer.html" flush="true" />
</body>
</html>