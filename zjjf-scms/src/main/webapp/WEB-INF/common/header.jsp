<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<% request.setAttribute("root", request.getContextPath());%>
<header id="header">
	<input id="currentUserId" type="hidden" value="${logInVo.userId}">
    <div class="info">
        <span class="username">您好&nbsp;&nbsp;
            <c:choose>
                <c:when test="${ERP_WAREHOUSE_SESSION_KEY != null}">${ERP_WAREHOUSE_USER_SESSION_KEY.name}</c:when>
                <c:otherwise>${logInVo.userName}</c:otherwise>
            </c:choose>
        </span>
                    综合评分：
        <div class="fraction-box" id="J_fraction">
            <span id="star"></span>

            <div class="fraction-detail" id="fraction-detail">
                <section class="wrap-bd op" id="op">
                   <!--  <span class="txt-warn" id="queryTime1" onclick="queryTime(1)">最近一周</span>
                    <span class="ml-default" id="queryTime1" onclick="queryTime(2)">最近一月</span>
                    <span class="ml-default" id="queryTime1" onclick="queryTime(3)">最近半年</span> -->
                </section>
                <input type="hidden" id="time" value="">
                <section class="wrap-bd" id="wrap-bd">
                   <!--  <div>
                        <span class="star sum"><span class="star-active" style="width:96%;"></span></span><span class="txt-warn">4.8分</span>&nbsp;&nbsp;共XX人
                    </div>
                    <div>
                        <span class="star"><span class="star-active" style="width:100%;"></span></span>&nbsp;&nbsp;5分
                        <span class="progressbar ml-default"><span class="progressbar-active" style="background: #ffa200;width:80%;"></span></span>&nbsp;&nbsp;共222222人
                    </div>
                    <div>
                        <span class="star"><span class="star-active" style="width:80%;"></span></span>&nbsp;&nbsp;4分
                        <span class="progressbar ml-default"><span class="progressbar-active" style="background: #ec8566;width:60%;"></span></span>&nbsp;&nbsp;共XX人
                    </div>
                    <div>
                        <span class="star"><span class="star-active" style="width:60%;"></span></span>&nbsp;&nbsp;3分
                        <span class="progressbar ml-default"><span class="progressbar-active" style="background: #a388d5;width:50%;"></span></span>&nbsp;&nbsp;共XX人
                    </div>
                    <div>
                        <span class="star"><span class="star-active" style="width:40%;"></span></span>&nbsp;&nbsp;2分
                        <span class="progressbar ml-default"><span class="progressbar-active" style="background: #1fb5aa;width:40%;"></span></span>&nbsp;&nbsp;共XX人
                    </div>
                    <div>
                        <span class="star"><span class="star-active" style="width:20%;"></span></span>&nbsp;&nbsp;1分
                        <span class="progressbar ml-default"><span class="progressbar-active" style="background: #0a9fe4;width:10%;"></span></span>&nbsp;&nbsp;共XX人
                    </div> -->
                </section>
            </div>
        </div>
        <span class="sep">|</span>
        <a href="${root}/scms/authority/doLoginOut.do?loginOut=${loginStr}">注销</a>
    </div>
    <script type="text/javascript">
    $(function(){
    	$.post("${root}/supplier/spComment/getSumSpComment.do",function(data){
    		var html='';
    		if(data.success){
    			html+='<span class="star"><span class="star-active" style="width:'+(data.message.avgScore/5*100)+'%;"></span></span>&nbsp;&nbsp;';
    			html+='<span class="fraction">'+(data.message.avgScore).toFixed(1)+'</span> 分';

    		}
    		if(html==''){
    			html+='<span class="star"><span class="star-active" style="width:0%;"></span></span>&nbsp;&nbsp;';
    			html+='<span class="fraction">0</span> 分';
    		}
    		$("#star").html(html);
    	},'json');
    	initialize();
    })

    function queryTime(time){
    	$("#time").val(time);
    	initialize();
    }

    function initialize(){
    	var time = $("#time").val();
    	var queryHtml = "";
    	if(time == null || time=='null' || time=='' || time=='undefined'||time==1){
    		console.log(1)
    		queryHtml+='<span class="txt-warn" id="queryTime1" onclick="queryTime(1)">最近一周</span>';
    		queryHtml+='<span class="ml-default" id="queryTime1" onclick="queryTime(2)">最近一月</span>';
    		queryHtml+='<span class="ml-default" id="queryTime1" onclick="queryTime(3)">最近半年</span>';
		}else if(time==2){
			console.log(2)
			queryHtml+='<span id="queryTime1" onclick="queryTime(1)">最近一周</span>';
    		queryHtml+='<span class="ml-default txt-warn" id="queryTime1" onclick="queryTime(2)">最近一月</span>';
    		queryHtml+='<span class="ml-default" id="queryTime1" onclick="queryTime(3)">最近半年</span>';
		}else if(time==3){
			console.log(3)
			queryHtml+='<span id="queryTime1" onclick="queryTime(1)">最近一周</span>';
    		queryHtml+='<span class="ml-default" id="queryTime1" onclick="queryTime(2)">最近一月</span>';
    		queryHtml+='<span class="ml-default txt-warn" id="queryTime1" onclick="queryTime(3)">最近半年</span>';
		}
    	$("#op").html(queryHtml);
    		$.post("${root}/supplier/spComment/getSpComment.do",{time:time},function(data){
        		var html='';
        		if(data.success){
        			html+='<div>';
        			html+='<span class="star sum"><span class="star-active" style="width:'+(data.message.starCount.avgScore/5*100)+'%;"></span></span><span class="txt-warn">'+(data.message.starCount.avgScore).toFixed(1)+'分</span>&nbsp;&nbsp;共'+data.message.starCount.countNum+'人';
        			html+='</div>';
        			html+='<div>';
        			html+='<span class="star"><span class="star-active" style="width:100%;"></span></span>&nbsp;&nbsp;5分';
        			html+='<span class="progressbar ml-default"><span class="progressbar-active" style="background: #ffa200;width:'+(data.message.star5.num/data.message.starCount.countNum*100)+'%;"></span></span>&nbsp;&nbsp;共'+data.message.star5.num+'人';
        			html+='</div>';
        			html+='<div>';
        			html+='<span class="star"><span class="star-active" style="width:80%;"></span></span>&nbsp;&nbsp;4分';
        			html+='<span class="progressbar ml-default"><span class="progressbar-active" style="background: #ec8566;width:'+(data.message.star4.num/data.message.starCount.countNum*100)+'%;"></span></span>&nbsp;&nbsp;共'+data.message.star4.num+'人';
        			html+='</div>';
        			html+='<div>';
        			html+='<span class="star"><span class="star-active" style="width:60%;"></span></span>&nbsp;&nbsp;3分';
        			html+='<span class="progressbar ml-default"><span class="progressbar-active" style="background: #a388d5;width:'+(data.message.star3.num/data.message.starCount.countNum*100)+'%;"></span></span>&nbsp;&nbsp;共'+data.message.star3.num+'人';
        			html+='</div>';
        			html+='<div>';
        			html+='<span class="star"><span class="star-active" style="width:40%;"></span></span>&nbsp;&nbsp;2分';
        			html+='<span class="progressbar ml-default"><span class="progressbar-active" style="background: #1fb5aa;width:'+(data.message.star2.num/data.message.starCount.countNum*100)+'%;"></span></span>&nbsp;&nbsp;共'+data.message.star2.num+'人';
        			html+='</div>';
        			html+='<div>';
        			html+='<span class="star"><span class="star-active" style="width:20%;"></span></span>&nbsp;&nbsp;1分';
        			html+='<span class="progressbar ml-default"><span class="progressbar-active" style="background: #0a9fe4;width:'+(data.message.star1.num/data.message.starCount.countNum*100)+'%;"></span></span>&nbsp;&nbsp;共'+data.message.star1.num+'人';
        			html+='</div>';
        			$("#wrap-bd").html(html);
        		}
        	},'json');
    	}
    </script>
</header>
