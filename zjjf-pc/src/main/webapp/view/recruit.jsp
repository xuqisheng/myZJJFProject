<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>深圳市转角街坊网络科技有限公司</title>
    <jsp:include page="head.jsp" flush="true" />
    <link rel="stylesheet" href="../resources/css/recruit.css?2">
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
                <a href="recruit.jsp" class="active">转角招聘</a>
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
    <div class="banner"></div>
    <div class="w content">
        <div class="contact">
            <span class="h2">招聘岗位</span>
            <span class="h3">Recruitment post</span>
            <span class="info">
                请将简历投递至邮箱：<a class="bl" href="mailto:vivian@izjjf.cn">hr@izjjf.cn</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                招聘电话：<span class="bl">0755-86966002</span>
            </span>
        </div>
	        <div class="tab clearfix" id="tabclearfix">
	            <!-- <div data-groupname="tab" data-tab="item" class="tab-item active">研发类</div> -->
	        </div>
	        <div id="tabcontent">
	            <!--<div data-groupname="tab" data-tab="content" class="tab-content">
	                 <div class="tab-cont-title active" data-groupname="stab1" data-tab="item">
	                    · 产品经理<span class="triangle"></span>
	                </div>
	                <div class="tab-cont-desc" data-groupname="stab1" data-tab="content">
	                    1.负责公司街坊店宝前后台产品设计、策划及实施；<br>
	                    2.参与产品生命周期的各个环节，从初期的想法到上线后的数据分析和用户反馈收集，不断优化产品体验；
	                </div>
	            </div>-->
	        </div>
    </div>
</main>
<jsp:include page="footer.jsp" flush="true" />
<script>
    $(function() {
    	//加载招聘信息
    	 $.ajax({
             type: "POST",
             url: "../pc/recruitType/getRecruitInfo.do",
             success: function(data) {
            	 var tabclearfix = '';
            	 var tabcontent = '';
            	 console.log(data);
            	 if(data.success){
            		 $.each(data.message,function(i,item){
            			 if(i==0){
            				 tabclearfix+='<div data-groupname="tab" data-tab="item" class="tab-item active">'+item.typeName+'</div>';
            			 }else{
            				 tabclearfix+='<div data-groupname="tab" data-tab="item" class="tab-item">'+item.typeName+'</div>';
            			 }
            			 var n = i+1;

            			 tabcontent+='<div data-groupname="tab" data-tab="content" class="tab-content">';
            			 $.each(item.recruits,function(j,j_item){
            				 if(j==0){
            					 tabcontent+='<div class="tab-cont-title active" data-groupname="stab'+n+'" data-tab="item">';
            				 }else{
            					 tabcontent+='<div class="tab-cont-title" data-groupname="stab'+n+'" data-tab="item">';
            				 }
            				 tabcontent+='· '+j_item.postName+'<span class="triangle"></span>';
            				 tabcontent+='</div>';
            				 tabcontent+='<div class="tab-cont-desc" data-groupname="stab'+n+'" data-tab="content">';
            				 tabcontent+=''+j_item.content+'';
            				 tabcontent+='</div>';
            			 });
            			 tabcontent+='</div>';

            		 });
            	 }
            	 $("#tabclearfix").html(tabclearfix);
            	 $("#tabcontent").html(tabcontent);
           	   	 tab('tab', 'mouseover');
                 var total = $('[data-groupname="tab"][data-tab="item"]').length;
                 for(var i = 1; i <= total; i++) {
                     tab('stab' + i);
                 }
             }
         });
    });
</script>
<script type="text/javascript">
    var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
    document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fc3135c3950bcfd3fb1df74b0cb41edbd' type='text/javascript'%3E%3C/script%3E"));
</script>
</body>
</html>
