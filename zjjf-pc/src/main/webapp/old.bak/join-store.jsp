<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>转角街坊-合作加盟</title>
<link href="favicon.ico" rel="icon" type="image/x-icon" />
<link rel="stylesheet" href="plugs/amazeui/css/amazeui.css" type="text/css">
<link rel="stylesheet" href="css/comm.css?123" type="text/css">
<link rel="stylesheet" href="css/join.css?122aa" type="text/css">
<!--[if lt IE 9]><script src="plugs/html5shiv.js"></script><![endif]-->
<script src="plugs/jquery-1.11.3.min.js"></script>
<script src="js/comm.js?122"></script>
</head>
<body>
<header id="header">
	<div class="topnav wrap">
		<h1 class="logo"><a href="./index.jsp"><img src="images/logo.png" alt=""></a></h1>
		<nav>
			<ul>
				<li><a href="index.jsp">首页</a></li>
				<li><a href="product-jfdb.jsp">街坊店宝</a></li>
				<li><a href="about.jsp">关于街坊</a></li>
				<li><a href="hiring.jsp">街坊招聘</a></li>
				<li><a href="join.jsp" class="curr">合作加盟</a></li>
				<div class="lavalamp lavalamp5"></div>
			</ul>
		</nav>
	</div>
</header>
<main>
	<section class="banner-join">
		<nav class="join-nav">
			<div class="wrap">
				<a href="join.jsp">
					<div class="join-nav-item">
						<div class="div supplier"></div>
						<div class="div">
							<span class="h2">我是批发商</span>
							<span class="h3">零风险，零加盟费</span>
						</div>
					</div>
				</a>
				<a href="join-store.jsp">
					<div class="join-nav-item join-nav-item-active">
						<div class="div store"></div>
						<div class="div">
							<span class="h2">我是便利店</span>
							<span class="h3">最优价格，快速送货服务</span>
						</div>
					</div>
				</a>
			</div>
		</nav>
	</section>
	<article class="article article1">
		<section class="wrap">
			<div class="title title1">便利店加盟优势</div>
			<div class="box-contain">
				<div class="box">
					<div class="icon icon1a"></div>
					<h2>保证正品</h2>
					<hr />
					<p>所有商品转角街坊都严格把关，为您提供货物保障，保证正品。</p>
				</div>
				<div class="box">
					<div class="icon icon2a"></div>
					<h2>价格更优</h2>
					<hr />
					<p>加入转角街坊，我们将为您提供更优的价格，增进收入。</p>
				</div>
				<div class="box last">
					<div class="icon icon3a"></div>
					<h2>送货快捷</h2>
					<hr />
					<p>转角街坊实时跟进订单状况，为您以更快的速度送货。</p>
				</div>
				<div class="box">
					<div class="icon icon4a"></div>
					<h2>便捷下单</h2>
					<hr />
					<p>APP简单操作下单，更支持货到付款与在线支付两种付款形式，坐等送货上门，简单便捷。</p>
				</div>
				<div class="box">
					<div class="icon icon5a"></div>
					<h2>降低库存</h2>
					<hr />
					<p>即点即送，方便快捷，为您减轻库存压力，且无断货之忧。</p>
				</div>
				<div class="box last">
					<div class="icon icon6a"></div>
					<h2>售后保障</h2>
					<hr />
					<p>转角街坊有专业的渠道人员及客服人员，及时跟进订单，保障售后服务。</p>
				</div>
			</div>
		</section>
	</article>
	<article class="article article3 article3-nobg">
		<section id="join" class="wrap">
			<div class="title title3">加入转角街坊&nbsp;&nbsp;携手共赢</div>
			<div class="content">
				<div class="left"><img src="images/join_tell.png" alt=""></div>
				<div class="right">
					<form id="formjoin" method="post" target="hiddeniframe">
						<div class="box">
							<label>姓名：</label>
							<input type="text" name="name" id="name" /><span class="star">*</span>
							<span class="tips"></span>
						</div>
						<div class="box">
							<label>电话：</label>
							<input type="text" name="mobile" id="mobile" /><span class="star">*</span>
							<span class="tips"></span>
						</div>
						<div class="box">
							<label>店铺名称：</label>
							<input type="text" name="storeName" id="storeName" /><span class="star">*</span>
							<span class="tips"></span>
						</div>
						<!--<div class="box">-->
							<!--<label>所属区域：</label>-->
							<!--<input type="text" name="area" class="small" readonly="true" value="深圳市" /><span class="star">*</span>-->
							<!--<select name="" id="region" class="select">-->
								<!--<option value="">请选择</option>-->
								<!--<option value="001">南山区</option>-->
								<!--<option value="001">龙岗区</option>-->
							<!--</select>-->
							<!--<span class="tips"></span>-->
						<!--</div>-->
						<div class="box">
							<label>店铺地址：</label>
							<input type="text" name="storeAdress" id="storeAdress" /><span class="star">*</span>
							<span class="tips"></span>
						</div>
						<div class="box">
							<label>留言：</label>
							<textarea name="userMessage" id="userMessage" cols="30" rows="10"></textarea>
							<span class="tips"></span>
						</div>
						<div class="box">
							<label>验证码：</label>
							<input class="checkCode" type="text" name="checkCode" id="checkCode" />&nbsp;<img src="./checkcode.do" onclick="javascript:changeImg()" width="120" height="35" id="codeimg" alt="checkcode">
							<span class="tips"></span>
						</div>
						<br /><br />
						<input type="submit" class="submit" value="">
					</form>
					<iframe name="hiddeniframe" style="display:none"></iframe>
					<script src="js/join.js"></script>
				</div>
			</div>
		</section>
	</article>
</main>
<section class="tips-success-contain">
	<div class="tips-success-title">
		&nbsp;&nbsp;提示
		<span class="close">X</span>
	</div>
	<div class="tips-success-content">
		<h2><img src="images/success.png" alt="">&nbsp;&nbsp;提交成功</h2>
		<p>您的信息已经提交成功，请等待转角街坊审核。</p>
	</div>
</section>
<jsp:include page="footer.html" flush="true" />
</body>
</html>