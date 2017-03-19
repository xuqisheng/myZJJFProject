<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>转角街坊-人在街坊</title>
<link href="favicon.ico" rel="icon" type="image/x-icon" />
<link rel="stylesheet" href="plugs/amazeui/css/amazeui.css" type="text/css">
<link rel="stylesheet" href="css/comm.css?122" type="text/css">
<link rel="stylesheet" href="css/style.css?122" type="text/css">
<!--[if lt IE 9]><script src="plugs/html5shiv.js"></script><![endif]-->
<script src="plugs/jquery-1.11.3.min.js"></script>
<script src="js/comm.js?122"></script>
<script>
	$(function(){
	    $(".tab-name").on('click', function(){
			var id = $(this).attr("name");
			$("#"+id).removeClass("hidden");
			$(this).addClass("selected");
			$(".tag").not($("#"+id)).addClass("hidden")
			$(".tab-name").not($(this)).removeClass("selected");
	    });
	});
</script>
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
				<li><a href="hiring.jsp" class="curr">街坊招聘</a></li>
				<li><a href="join.jsp">合作加盟</a></li>
				<div class="lavalamp lavalamp4"></div>
			</ul>
		</nav>
	</div>
	<nav class="subnav">
		<section class="wrap">
			<div class="item item_curr jiefang">
				<a href="hiring.jsp">街坊招聘</a>
				<a href="in-zj.jsp" class="orange">人在街坊</a>
			</div>
		</section>
	</nav>
</header>
<div class="banner-inzj"></div>
<main>
	<div class="wrap">
		<div class="ren-main">
			<!-- 		
			<ul id="nav">
		        <li><span name="fuli" class="tab-name selected">街坊福利</span></li>
		        <li><span name="huodong" class="tab-name">街坊活动</span></li>
		    </ul>
		    -->
		    <div id="menu_con">
		    <!-- ///////////////切换一 //////////////-->
		        <div class="tag" id="fuli">
		            <div class="r-txt01">舒适的环境 ：办公地点就在保利大厦，交通便利，环境更像home，舒适温馨</div>
		            <div class="pic-box">
		            	<div class="cols cols1">
		            		<div class="pic"><a><img src="images/f-pic01.jpg" width="292" height="473"><div class="black">孵化创意</div></a></div>
		            	</div>
		            	<div class="cols cols2">
	            			<div class="pic"><a ><img src="images/f-pic02.jpg" width="431" height="234"><div class="black">形象代言区</div></a></div>
	            			<div class="pic pic-right"><a ><img src="images/f-pic03.jpg" width="207" height="234"><div class="black">等你的照片</div></a></div>
	            			<div class="pic"><a ><img src="images/f-pic04.jpg" width="207" height="234"><div class="black">全力以赴</div></a></div>
	            			<div class="pic pic-right"><a ><img src="images/f-pic05.jpg" width="431" height="234"><div class="black">简约</div></a></div>
		            	</div>
		            </div>

		            <div class="txt-box">
		            	<div class="cols cols1 r-txt02">
		            		<div class="f-txt01">完善的福利体系，实实在在的收获，提供行业内极具竞争力的薪酬系数，让你拥有一个极致快乐的工作，品质快乐的生活。</div>
		            	</div>
		            	<div class="cols cols2 r-txt03">
		            		<div class="f-txt02"><p>五险一金</p><p>绩效奖金</p><p>奖金丰厚</p></div>
		            	</div>
		            	<div class="cols">
	            			<div class="cols3 r-txt04">带薪年假 &nbsp;&nbsp;&nbsp;&nbsp;员工旅游</div>
	            			<div class="cols3 r-txt05">团队聚餐 &nbsp;&nbsp;&nbsp;&nbsp;生日礼物</div>
		            	</div>
		            </div>
		            <div class="r-txt01 r-txt06">畅快的心情：你将遇见一群热情，热心，热爱生活的同事，每月都有员工聚餐日，每周都有活动日和丰富的下午茶，与其说是工作，不如说是朋友的一次团队协作游戏，很感动，很齐心
		            </div>

		            <div class="pic-box pic-box2">
		            	<div class="cols cols2">
	            			<div class="pic"><a ><img src="images/f-pic06.jpg" width="397" height="234"><div class="black">爱的贺卡</div></a></div>
	            			<div class="pic"><a ><img src="images/f-pic07.jpg" width="240" height="234"><div class="black">抢个红包先</div></a></div>
	            			<div class="pic"><a ><img src="images/f-pic08.jpg" width="315" height="234"><div class="black">这么认真的K歌之王？</div></a></div>
	            			<div class="pic"><a ><img src="images/f-pic09.jpg" width="323" height="234"><div class="black">街坊喊你来吃饭</div></a></div>
		            	</div>
		            	<div class="cols cols1">
		            		<div class="pic"><a ><img src="images/f-pic10.jpg" width="292" height="473"><div class="black">生日一起嗨</div></a></div>
		            	</div>
		            </div>
		            <div class="r-txt01 r-txt07">......<br>更多乐趣等你来建议</div>
		         </div> 
		         <!--//////////// 切换二 ////////////-->
		        <div class="tag hidden" id="huodong">
		            <div class="r-txt01 act-txt01">人在一起叫聚会   心在一起叫团队</div>

    	            <div class="pic-box pic-box2">
    	            	<div class="cols cols2">
    	            		<div class="pic"><a ><img src="images/act-pic01.jpg" width="207" height="234"><div class="black">来来.....十元三串咯</div></a></div>
                			<div class="pic"><a ><img src="images/act-pic02.jpg" width="431" height="234"><div class="black">到海边还穿什么衣服</div></a></div>
                			<div class="pic"><a ><img src="images/act-pic03.jpg" width="431" height="234"><div class="black">再使点劲 咱们就赢啦</div></a></div>
                			<div class="pic active-txt act-txt01">西冲烧烤</div>
    	            	</div>
    	            	<div class="cols cols1">
    	            		<div class="pic"><a ><img src="images/act-pic04.jpg" width="292" height="473"><div class="black">“煮夫”当道</div></a></div>
    	            	</div>
    	            </div>

		            <div class="pic-box">
		            	<div class="cols cols1">
		            		<div class="pic"><a ><img src="images/act-pic05.jpg" width="292" height="473"><div class="black">欢迎带娃来团建</div></a></div>
		            	</div>
		            	<div class="cols cols2">
		            		<div class="pic"><a ><img src="images/act-pic06.jpg" width="400" height="234"><div class="black">快到碗里来</div></a></div>
	            			<div class="pic pic-right"><a ><img src="images/act-pic07.jpg" width="238" height="234"><div class="black">许个愿吧</div></a></div>
	            			<div class="pic"><a ><img src="images/act-pic08.jpg" width="431" height="234"><div class="black">蜡烛+鲜花 集体浪漫</div></a></div>
	            			<div class="pic pic-right active-txt act-txt02">生日聚会</div>
		            	</div>
		            </div>

		            <div class="pic-box pic-box2">
    	            	<div class="cols cols2">
    	            		<div class="pic"><a ><img src="images/act-pic09.jpg" width="431" height="234"><div class="black">时间过去了许久......</div></a></div>
    	            		<div class="pic active-txt act-txt03">户外拓展</div>
                			<div class="pic"><a ><img src="images/act-pic10.jpg" width="212" height="234"><div class="black">考验小脑的时候到了</div></a></div>
                			<div class="pic"><a ><img src="images/act-pic11.jpg" width="425" height="234"><div class="black">我要跳的更高</div></a></div>
    	            	</div>
    	            	<div class="cols cols1">
    	            		<div class="pic"><a ><img src="images/act-pic12.jpg" width="292" height="473"><div class="black">萝卜蹲 萝卜蹲完萝卜蹲</div></a></div>
    	            	</div>
    	            </div>

		            <div class="pic-box">
		            	<div class="cols cols1">
		            		<div class="pic"><a ><img src="images/act-pic13.jpg" width="292" height="473"><div class="black">为自闭症儿童而跑</div></a></div>
		            	</div>
		            	<div class="cols cols2">
		            		<div class="pic"><a ><img src="images/act-pic14.jpg" width="430" height="234"><div class="black">我们一起为爱加油吧</div></a></div>
		            		<div class="pic pic-right active-txt act-txt04">公益马拉松</div>
	            			<div class="pic"><a ><img src="images/act-pic15.jpg" width="319" height="234"><div class="black">哇...好多小伙伴啊</div></a></div>
	            			<div class="pic pic-right"><a ><img src="images/act-pic16.jpg" width="318" height="234"><div class="black">此处有掌声</div></a></div>
	            			
		            	</div>
		            </div>
		            <div class="r-txt01 r-txt07 act-txt05">转角街坊  梦想起航</div> 
		        </div>
			</div>
		</div>
	</div>
</main>
<jsp:include page="footer.html" flush="true" />
</body>
</html>