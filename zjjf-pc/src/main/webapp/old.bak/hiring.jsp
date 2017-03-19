<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>转角街坊-街坊招聘</title>
<link href="favicon.ico" rel="icon" type="image/x-icon" />
<link rel="stylesheet" href="plugs/amazeui/css/amazeui.css" type="text/css">
<link rel="stylesheet" href="css/comm.css?122" type="text/css">
<link rel="stylesheet" href="css/style.css?122" type="text/css">
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
				<li><a href="hiring.jsp" class="curr">街坊招聘</a></li>
				<li><a href="join.jsp">合作加盟</a></li>
				<div class="lavalamp lavalamp4"></div>
			</ul>
		</nav>
	</div>
	<nav class="subnav">
		<section class="wrap">
			<div class="item item_curr jiefang">
				<a href="hiring.jsp" class="orange">街坊招聘</a>
				<a href="in-zj.jsp">人在街坊</a>
			</div>
		</section>
	</nav>
</header>
<div class="banner-hiring"></div>
<main>
	<div class="wrap recruit">
		<div class="am-tabs" data-am-tabs="{noSwipe: 1}">
			<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="tab-name curr" name="tab1"><img class="tab-pic selected" src="images/r-pro02.gif" alt="产品" replace-src="images/r-pro01.gif"><span>产品<i style="font-style:normal;">汪</i></span></li>
			<li class="tab-name" name="tab2"><img class="tab-pic" src="images/r-op01.gif"  alt="运营" replace-src="images/r-op02.gif"><span>运营<i style="font-style:normal;display:none;">鹰</i></span></li>
			<li class="tab-name" name="tab3"><img class="tab-pic" src="images/r-dp01.gif"  alt="程序" replace-src="images/r-dp02.gif"><span>程序<i style="font-style:normal;display:none;">猿</i></span></li>
			<li class="tab-name" name="tab4"><img class="tab-pic" src="images/r-dg01.gif"  alt="设计" replace-src="images/r-dg02.gif"><span>设计<i style="font-style:normal;display:none;">狮</i></span></li>
			<li class="tab-name" name="tab5"><img class="tab-pic" src="images/r-mk01.gif"  alt="业务" replace-src="images/r-mk02.gif"><span>业务<i style="font-style:normal;display:none;">狼</i></span></li>
			</ul>
			<div class="am-tabs-bd recruit-main">
				<div class="tab-panel" id="tab1"></div>
				<div class="tab-panel hidden" id="tab2"></div>
				<div class="tab-panel hidden" id="tab3"></div>
				<div class="tab-panel hidden" id="tab4"></div>
				<div class="tab-panel hidden" id="tab5"></div>
			</div>
			<div class="r-contact">
				<span>请将简历和作品发送至邮箱：hr@izjjf.cn</span><span>招聘电话：0755-86966002</span>
			</div>
		</div>
	</div>
</main>
<jsp:include page="footer.html" flush="true" />
<script>
	$(function(){
	   $(".tab-name").on('click', function(){
	   	  var id = $(this).attr("name"), $img = $(this).find("img"), $title=$(this).find("span"),
     		  oldsrc = $img.attr("src"), newsrc = $img.attr("replace-src");
	   	  $("#"+id).removeClass("hidden");
	   	  $(this).addClass("curr").find("img").addClass("selected");
		  $img.attr({"src": newsrc}).attr({"replace-src" : oldsrc});
		  $img.next("span").find("i").toggle();
	   	  $(".tab-panel").not($("#"+id)).addClass("hidden");
	   	  $(".tab-name").not($(this)).removeClass("curr");
		  //还原之前人物头像
		  var $select = $(".selected").not($img), ogsrc = $select.attr("src"), ngsrc =  $select.attr("replace-src");
		  $select.next("span").find("i").toggle();
		  $select.attr({"src": ngsrc}).attr({"replace-src" : ogsrc}).removeClass("selected");
	   });
	   
	   $.ajax({
			type : "POST",
			url : "./pc/recruit/List.do",
			data : {"isDelete":false,"pageIndex":0,"pageSize":10,"sortOrder":"asc","sortName":"id"},
			success : function(data) {
				var recruitlist = data.rows;
				var total=data.total;
				if(total>0){
					for(var i=1 ; i<= total;i++){
						$("#tab"+i).empty().html(recruitlist[i-1].content);
					}
				}else{
					alert("未取到数据");
				}
			}
		});
	});
</script>
</body>
</html>