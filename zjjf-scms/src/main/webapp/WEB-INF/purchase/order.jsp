﻿<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>

<% request.setAttribute("root", request.getContextPath());%>
<%
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragrma","no-cache");
response.setDateHeader("Expires",0);
%> 
<!DOCTYPE html>
<html lang="zh">
<head>   
    <meta charset="UTF-8">
    <title>快销宝 - 我的采购单</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta HTTP-EQUIV="pragma" CONTENT="no-cache">
    <meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
    <meta HTTP-EQUIV="expires" CONTENT="0">
    <%@ include file="../common/purchase-head.jsp"%>
    <link rel="stylesheet" href="${root}/resources/purchase/css/order.css">
	<link rel="stylesheet" type="text/css"	href="${root}/resources/vendor/kkpager-1.3/kkpager_orange_custom.css">
    <script src="${root}/resources/vendor/kkpager-1.3/kkpager.min.js"></script>
</head>
<body>
<%@ include file="../common/purchase-nav.jsp"%>
<header>
    <a href="${root}/scms/procurementcenter/listPage.do"><div class="logo"></div></a>
    <div class="search-box">
        <input type="text" placeholder="搜搜您需要的商品" class="search-text" id="name">
        <input type="button" value="搜索" class="search-button" onclick="search()">
    </div>
</header>
<input type="hidden" id="state" value="${Ro.state}"> 
<main class="w">
    <section class="title">我的采购单</section>
    <section class="nav" id="J_nav">
        <span class="li active" onclick="state()">全部订单</span>
        <span class="li" onclick="state(1)">未付款</span>
        <span class="li" onclick="state(2)">待收货</span>
    </section>
    <section class="goods-list">
		        <div class="list-head">
		            <span class="w-goods-info">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;订单详情</span>
		            <span class="w-goods-price">单价</span>
		            <span class="w-goods-price">数量</span>
		            <span class="w-goods-price">状态</span>
		            <span class="w-goods-price">实付款</span>
		            <span class="w-goods-price">操作</span>
		        </div>
                <c:if test="${scOrderInfo.size()<=0}">
		        <div class="list-body">
		            <div class="no-data">
		                                       暂无订单
		                <a href="${root}/scms/procurementcenter/listPage.do">继续购物</a>
		            </div>
                </div>
                </c:if>
		        <c:forEach var="item" varStatus="i" items="${scOrderInfo}">   
		        		<div class="list-body">
			            <div class="list-title">
			                <b> <fmt:formatDate value="${item.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></b>&nbsp;&nbsp;
			                <span>订单号：${item.orderId}</span>
			                <c:if test="${item.status==4}">
			                <span class="icon-del" onclick="deleteState('${item.orderId}')"></span>
			                </c:if>
			            </div>
			            <c:forEach var="son" items="${item.scorders}" varStatus="s">
							            		 <div>
						                <section class="list-box">
						                	 <c:if test="${!s.first}">
						                	 	<div class="list-box-item" >
    										 </c:if>
    										 <c:if test="${s.first}">
						                	 	<div class="list-box-item" style="border-top: 0 none;">
    										 </c:if>
						                        <span class="name w-goods-info">
						                            <c:choose>
									            		<c:when test="${son.img != '' && son.img != null}">
									                		<img class="pic" src="${USER_FASTFDSPREURL }${son.img}" width="80" height="80"  alt="" onerror="javascript:this.src='${USER_DEFAULTIMG_URL}'"/>
									                	</c:when>
									                	<c:otherwise>
									                		<img class="pic" src="${USER_DEFAULTIMG_URL}" width="80" height="80"  alt=""/>
									                	</c:otherwise>
									            	</c:choose>
						                            <div class="text">
						                              		${son.name}
						                            </div>
						                            <div class="info">规格：${son.spec}</div>
						                        </span>
						                        <span class="price w-goods-price">￥${son.zjjfPrice}</span>
						                        <span class="number w-goods-price">${son.quantity}${son.pkg}</span>
						                        <span class="number w-goods-price"> 
						                        <c:choose>
						                        	<c:when test="${son.status==3}">
						                        		已完成
						                        	</c:when>
						                        	<c:otherwise>
						                        		<c:if test="${item.status==1}">
										                	 	已下单
				    										 </c:if><c:if test="${item.status==2}">
										                	 	已付款
				    										 </c:if>
				    										  <c:if test="${item.status==3}">
										                	 	待收货
				    										 </c:if>
				    										  <c:if test="${item.status==4}">
										                	 	完成
			    										 </c:if>
						                        	</c:otherwise>
						                        </c:choose>
						                        
    										 </span>
						                    </div>
						                </section>
						                <section class="list-totleprice w-goods-price">
						                <c:if test="${s.first}">
						                	总额
						                	<div class="price">￥${item.outOfPrice}</div>
						                	<!-- 该处不显示运费 -->
						                	<!--<c:if test="${item.freight!=null&&item.freight>0}">(含运费￥${item.freight})</c:if>--> 
    									</c:if>
						                </section>
						                <section class="list-operate w-goods-price">
						                    <input type="hidden" value="${item.id}">
						                     <c:if test="${s.first}">
						                       <c:if test="${item.status==1}">
						                	 	<input type="button" value="立即付款" class="button-ok payNow" id="payNow">
						                       </c:if>
						                       <c:if test="${item.status==2||item.status==3}">
						                	 	<input type="button" value="确认收货" class="button-ok" onclick="updateState('${item.orderId}')">
						                       </c:if>
						                      	<%-- <c:if test="${item.status==4}">
						                	 	<input type="button" value="删除" class="button-ok" onclick="deleteState('${item.orderId}')">
						                       </c:if> --%>
						                	 	<!-- <input type="button" value="确认收货" class="button-ok"> -->
						                    	<a href="${root}/scms/scOrder/scorderdetail.do?orderId=${item.orderId}" target="_blank">订单详情</a>
    										 </c:if>
						                    
						                </section>
						            </div>
			            </c:forEach>
		        </div>
        </c:forEach>
    </section>
    <div class="w clearfix">
        <div id="kkpager"></div>
    </div>
    <div id="payDiv"></div>
</main>
<%@ include file="../common/purchase-footer.jsp"%>
<script>
function state(state){
	$("#state").val(state);
	var url = kkpager.getHref(1);
	window.location.href=url;
}
function deleteState(orderId){
	layer.confirm('确认删除？', {
	    btn: ['确定','取消'] //按钮
	}, function(){
		$.post('${root}/scms/scOrder/deleteinfo.do', { "orderId": orderId}, function (data) { 
			layer.msg(data.message);
			if(data.success){
				sleep(1000);
				window.location.reload();
			}
	 },"json");
	}, function(){
		
	});
	 
}


function search(){
	var url = '${root}/scms/procurementcenter/listPage.do?';
	var name=$("#name").val();
	if(name != null &&name != undefined && name != ''){
		   url=url+"name="+name;
	  	 }
	 window.location.href=url;
}

function updateState(id){
	layer.confirm('您确定要收货吗？', {
	    btn: ['确定','取消'] //按钮
	}, function(){
		 $.post('${root}/scms/scOrder/confirmation.do', { "orderId": id}, function (data) { 
				layer.msg(data.message);
				if(data.success){
					sleep(1000);
					window.location.reload();
				}
			},"json");
	}, function(){
	    
	});
	
}

function getParameter(name) { 
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r!=null) return unescape(r[2]); return null;
}
    $(function() {
//分页
		var totalRecords = ${size}; 
		 var totalPage = (totalRecords % 5 == 0 ? totalRecords / 5
				   : Math.ceil(totalRecords / 5));

		var pageIndex = getParameter('pageIndex');
		if(!pageIndex){
			pageIndex = 1;
		}
		//生成分页
		//有些参数是可选的，比如lang，若不传有默认值
		kkpager.generPageHtml({
			pno : pageIndex,
			//总页码
			total :  totalPage,
			//链接前部
			hrefFormer : 'myOrderInfo',
			//链接尾部
			hrefLatter : '.do',
			mode:'click',
			click:function(n){
				window.location.href=this.getHref(n);
			},
			getHref : function(n){
				var url=this.hrefFormer + this.hrefLatter + "?pageIndex="+n;
				var state=$("#state").val();
				if(state != null &&state != undefined && state != ''){
					   url=url+"&state="+state;
				  	 }
				return url;
			}
		});
    	
        var ii = $('#state').val();
        if(1 == ii) {
        	$('#J_nav .li').eq(1).toggleClass('active').siblings('.li').removeClass('active');
        } else if(2 == ii) {
        	$('#J_nav .li').eq(2).toggleClass('active').siblings('.li').removeClass('active');
        }
        
        //立即付款
        $('.payNow').click(function(){
        	var html='';
			html+='<form action="${root}/scms/cart/toSettlement.do" method="post" id="toSettlementForm"  target="pay">'
			    +'<input type="hidden" name="orderid" value="'+$(this).prev('input').val()+'">'
			    +'</form>';
			    $('#payDiv').html(html);
			    $('#toSettlementForm').submit();
        })
    });
    
    function sleep(numberMillis) { 
    	var now = new Date(); 
    	var exitTime = now.getTime() + numberMillis; 
    	while (true) { 
	    	now = new Date(); 
	    	if (now.getTime() > exitTime) {return; }
    	}
    }
    
</script>
</body>
</html>