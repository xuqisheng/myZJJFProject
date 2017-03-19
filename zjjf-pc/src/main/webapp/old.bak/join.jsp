<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>转角街坊-合作加盟</title>
<link href="favicon.ico" rel="icon" type="image/x-icon" />
<link rel="stylesheet" href="plugs/amazeui/css/amazeui.css" type="text/css">
<link rel="stylesheet" href="css/comm.css?122" type="text/css">
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
					<div class="join-nav-item join-nav-item-active">
						<div class="div supplier"></div>
						<div class="div">
							<span class="h2">我是批发商</span>
							<span class="h3">零风险，零加盟费</span>
						</div>
					</div>
				</a>
				<a href="join-store.jsp">
					<div class="join-nav-item">
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
			<div class="title title1">批发商加盟优势</div>
			<div class="box-contain">
				<div class="box">
					<div class="icon icon1"></div>
					<h2>专员协助</h2>
					<hr />
					<p>专业的渠道专员协助维护和开拓其他商户</p>
				</div>
				<div class="box">
					<div class="icon icon2"></div>
					<h2>品牌上支持</h2>
					<hr />
					<p>众多街坊合作品牌商的配送权或者分销权</p>
				</div>
				<div class="box last">
					<div class="icon icon3"></div>
					<h2>软件技术支持</h2>
					<hr />
					<p>提供专业的进销存数据分析平台等互联网管理工具</p>
				</div>
				<div class="box">
					<div class="icon icon4"></div>
					<h2>客服支持</h2>
					<hr />
					<p>专业的客服人员提供售后服务</p>
				</div>
				<div class="box">
					<div class="icon icon5"></div>
					<h2>拓展服务</h2>
					<hr />
					<p>获得合理的配送利润，拓展配送服务规模</p>
				</div>
				<div class="box last">
					<div class="icon icon6"></div>
					<h2>专业知识培训</h2>
					<hr />
					<p>提供专业的行业知识培训，帮助进一步成长与发展</p>
				</div>
			</div>
		</section>
	</article>
	<article class="article article2">
		<section class="wrap">
			<div class="title title2">转角街坊优势</div>
			<img class="img" src="images/join_ys.png" alt="">
		</section>
	</article>
	<article class="article article3">
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
							<label>商户名称：</label>
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
							<label>商户地址：</label>
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
					<iframe name="hiddeniframe" src="" style="display:none"></iframe>
					<script src="js/join.js?dda"></script>
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