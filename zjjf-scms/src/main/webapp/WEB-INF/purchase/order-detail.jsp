﻿<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>快销宝 - 采购单详情</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <%@ include file="../common/purchase-head.jsp"%>
    <link rel="stylesheet" href="${root}/resources/purchase/css/order-detail.css">
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
<main class="w">
    <section class="title">采购单信息</section>
    <section class="order-section">
        <div class="order-info">
            <div>
                <b>订单号：${scOrderInfo.orderId} &nbsp;&nbsp;&nbsp;&nbsp; 状态：<span class="main-color">
                <c:if test="${scOrderInfo.status==1}">已下单 </c:if>
                <c:if test="${scOrderInfo.status==2}">已付款</c:if>
                <c:if test="${scOrderInfo.status==3}">待收货 </c:if>
                <c:if test="${scOrderInfo.status==4}">已完成</c:if>
                <c:if test="${scOrderInfo.status==5}">已取消</c:if>
                </span></b>
                 <c:if test="${scOrderInfo.status==2||scOrderInfo.status==3}">
                 	  <input type="button" value="确认收货" class="button-ok" onclick="updateState('${scOrderInfo.orderId}')">
            </div>
            <div class="info">
                您的采购单已经付款，请注意查收！
            </div>
                  </c:if>
              
        </div>
    </section>
    <section class="order-detail">
        <div class="div">
            <b class="b">联系人</b>
            <p class="p">
                ${scOrderInfo.supplierName} &nbsp;&nbsp;&nbsp;
                 ${scOrderInfo.mobile} &nbsp;&nbsp;&nbsp;
                ${scOrderInfo.address}
            </p>
        </div>
        <div class="div">
            <b class="b">支付方式</b>
            <p class="p">
             <c:if test="${scOrderInfo.supportmetho==1}">快钱支付</c:if>
               <c:if test="${scOrderInfo.supportmetho==2}">货到付款</c:if>
                 <c:if test="${scOrderInfo.supportmetho==3}">支付宝支付</c:if>
                  <c:if test="${scOrderInfo.supportmetho==54}">微信支付和钱包支付</c:if>
                  <c:if test="${scOrderInfo.supportmetho==4}">微信支付</c:if>
                  <c:if test="${scOrderInfo.supportmetho==5}">钱包支付</c:if>
            </p>
        </div>
        <div class="div">
            <b class="b">提交时间</b>
            <p class="p">
            <fmt:formatDate value="${scOrderInfo.addTime}" pattern="yyyy年MM月dd日 HH:mm:ss"/>
                
            </p>
        </div>
        <div>
            <b class="b">采购清单</b>
            <div class="order-list">
                <div class="list-head">
                    <span class="w-info"><span style="display: inline-block;width:90px"></span>商品</span>
                    <span class="w-price">价格</span>
                    <span class="w-price">数量</span>
                    <span class="w-price">小计</span>
                    <span class="w-price">操作</span>
                </div>
                <div class="list-body">
                <c:forEach items="${scOrderInfo.scorders}" var="item" >
                	<div class="list-box-item">
                        <span class="name w-info">
                            <c:choose>
			            		<c:when test="${item.img != '' && item.img != null}">
			                		<img class="pic" src="${USER_FASTFDSPREURL }${item.img}" width="80" height="80" alt="" onerror="javascript:this.src='${USER_DEFAULTIMG_URL}'"/>
			                	</c:when>
			                	<c:otherwise>
			                		<img class="pic" src="${USER_DEFAULTIMG_URL}" width="80" height="80" alt=""/>
			                	</c:otherwise>
			            	</c:choose>
                            <div class="text">
                               ${item.name}<br>
                                规格：${item.spec}
                            </div>
                        </span>
                        <span class="price w-price">￥${item.zjjfPrice}</span>
                        <span class="number w-price">${item.quantity}${item.pkg}</span>
                        <span class="price totle-price w-price">￥${item.totalPrice}</span>
                        <c:if test="${item.status!=3}">
							<span class="operate w-price">
	                            <input type="button" value="确认收货" class="button-ok" onclick="updateDetailState('${item.id}')">
	                        </span>
						</c:if>
                        
                    </div>
                </c:forEach>
                    
               
                </div>
                <div class="list-foot">
   
                	<c:if test="${scOrderInfo.supportmetho==null}">
                		<div>
		                        <label>共 <span class="main-color">${scOrderInfo.scorders.size()}</span> 件商品</label>
		                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                        <label>金&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;额&nbsp;：</label>
		                        <span class="price">￥${scOrderInfo.goodsPrice}</span>
		                    </div>
		                    <c:if test="${scOrderInfo.freight!=null&&scOrderInfo.freight>0}">
		                     <div>
		                        <label>运&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;费&nbsp;：</label>
		                        <span class="price">￥${scOrderInfo.freight}</span>
		                    </div>
		                    </c:if>
		                   
		                    <div>
		                        <label>手&nbsp;续&nbsp;费&nbsp;：</label>
		                        <span class="price">￥${scOrderInfo.thirdPayFee}</span>
		                    </div>
		                    <div class="totle-price">
		                        <b>
		                            <label>订单金额：</label>
		                            <span class="price main-color">￥${scOrderInfo.outOfPrice}</span>
		                        </b>
		                        
		                    </div>
                	</c:if>
                	<c:if test="${scOrderInfo.supportmetho==54}">
		                	<div>
		                        <label>共 <span class="main-color">${scOrderInfo.scorders.size()}</span> 件商品</label>
		                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                        <label>金&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;额&nbsp;：</label>
		                        <span class="price">￥${scOrderInfo.goodsPrice}</span>
		                    </div>
		                    <c:if test="${scOrderInfo.freight!=null&&scOrderInfo.freight>0}">
		                     <div>
		                        <label>运&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;费&nbsp;：</label>
		                        <span class="price">￥${scOrderInfo.freight}</span>
		                    </div>
		                    </c:if>
		                   
		                    <div>
		                        <label>手&nbsp;续&nbsp;费&nbsp;：</label>
		                        <span class="price">￥${scOrderInfo.thirdPayFee}</span>
		                    </div>
		                    <div class="totle-price">
		                        <b>
		                            <label>订单金额：</label>
		                            <span class="price main-color">￥${scOrderInfo.outOfPrice}</span>
		                        </b>
		                        <div class="box">
		                            <i class="i"></i>
		                            <div class="p">
		                                                                                微信支付：￥${scOrderInfo.thirdPayRealNum}<br>
		                                                                                钱包支付：￥${scOrderInfo.balanceUsedNum}
		                            </div>
		                        </div>
		                    </div>
                	</c:if>
                	<c:if test="${scOrderInfo.supportmetho==4}">
                			<div>
		                        <label>共 <span class="main-color">${scOrderInfo.scorders.size()}</span> 件商品</label>
		                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                        <label>金&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;额&nbsp;：</label>
		                        <span class="price">￥${scOrderInfo.goodsPrice}</span>
		                    </div>
		                    <c:if test="${scOrderInfo.freight!=null&&scOrderInfo.freight>0}">
		                     <div>
		                        <label>运&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;费&nbsp;：</label>
		                        <span class="price">￥${scOrderInfo.freight}</span>
		                    </div>
		                     
		                    </c:if>
		                    <div>
		                        <label>手&nbsp;续&nbsp;费&nbsp;：</label>
		                        <span class="price">￥${scOrderInfo.thirdPayFee}</span>
		                    </div>
                            <div class="totle-price">
                                <b>
                                    <label>订单金额：</label>
                                    <span class="price main-color">￥${scOrderInfo.outOfPrice}</span>
                                </b>
                                <div class="box">
                                    <i class="i"></i>
                                    <div class="p">
                                                                                                    微信支付：￥${scOrderInfo.thirdPayRealNum}                                         
                                    </div>
                                </div>
                            </div>
                	</c:if>
                	<c:if test="${scOrderInfo.supportmetho==5}">
                			<div>
		                        <label>共 <span class="main-color">${scOrderInfo.scorders.size()}</span> 件商品</label>
		                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                        <label>金&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;额&nbsp;：</label>
		                        <span class="price">￥${scOrderInfo.goodsPrice}</span>
		                    </div>
		                    <c:if test="${scOrderInfo.freight!=null&&scOrderInfo.freight>0}">
		                     <div>
		                        <label>运&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;费&nbsp;：</label>
		                        <span class="price">￥${scOrderInfo.freight}</span>
		                    </div>
		                    </c:if>
                            <div class="totle-price">
                                <b>
                                    <label>订单金额：</label>
                                    <span class="price main-color">￥${scOrderInfo.outOfPrice}</span>
                                </b>
                            </div>
                	</c:if>
                    
                </div>
            </div>
        </div>
    </section>
</main>

<script type="text/javascript">
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

function search(){
	var url = '${root}/scms/procurementcenter/listPage.do?';
	var name=$("#name").val();
	if(name != null &&name != undefined && name != ''){
		   url=url+"name="+name;
	  	 }
	 window.location.href=url;
}

function updateDetailState(id){
	layer.confirm('您确定要收货吗？', {
	    btn: ['确定','取消'] //按钮
	}, function(){
		$.post('${root}/scms/scOrder/updateDetailState.do', { "id": id}, function (data) { 
			layer.msg(data.message);
			if(data.success){
				sleep(1000);
				window.location.reload();
			}
		},"json");
	}, function(){
	    
	});
	
}

function sleep(numberMillis) { 
	var now = new Date(); 
	var exitTime = now.getTime() + numberMillis; 
	while (true) { 
    	now = new Date(); 
    	if (now.getTime() > exitTime) {return; }
	}
}
</script>


<%@ include file="../common/purchase-footer.jsp"%>
</body>
</html>