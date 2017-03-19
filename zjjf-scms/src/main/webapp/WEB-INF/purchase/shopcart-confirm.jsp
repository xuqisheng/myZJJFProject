<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
    <title>快销宝 - 确认采购单</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta HTTP-EQUIV="pragma" CONTENT="no-cache">
    <meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
    <meta HTTP-EQUIV="expires" CONTENT="0">
    <%@ include file="../common/purchase-head.jsp"%>
    <link rel="stylesheet" href="${root}/resources/purchase/css/shopcart-confirm.css">
</head>
<body>
<%@ include file="../common/purchase-nav.jsp"%>
<header>
    <a href="${root}/scms/procurementcenter/listPage.do"><div class="logo"></div></a>
    <div class="shopcart-bar">
        <div class="shopcart-bar-item active">
            <span class="number">1</span>
            采购单
        </div>
        <div class="shopcart-bar-item curr">
            <span class="number">2</span>
            确认采购单
        </div>
        <div class="shopcart-bar-item">
            <span class="number">3</span>
            结算
        </div>
    </div>
</header>
<main class="w">
    <section class="title">填写并核对订单信息</section>
    <section class="shopcart-detail">
        <div class="div">
            <b class="b">收货信息</b>
            <p class="p">
            ${supplier.supplierName}&nbsp;&nbsp;&nbsp;
            ${supplier.mobile} &nbsp;&nbsp;&nbsp;
            ${supplier.supplierAddress}
            </p>
        </div>
        <div class="div">
            <b class="b">配送方式</b>
            <p class="p" id="J_radioGroupOne">
                <span class="radio ordertype active" value="0" ></span> 送货上门&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <!-- <span class="radio ordertype" value="2"></span> 自提（自提地址：${scmsWarehouse.houseAddress} ${scmsWarehouse.name} ${scmsWarehouse.branderTel}） -->
            </p>
        </div>
        <div class="div">
            <b class="b">支付方式</b>
            <p class="p">
                在线方式
            </p>
        </div>
        <div>
            <b class="b">送货清单</b>
            <p class="back">
                <a href="${root}/scms/cart/getCartList.do">返回修改采购单</a>
            </p>
            <div class="order-list">
                <div class="list-head">
                    <span class="w-info"><span style="display: inline-block;width:90px"></span>商品</span>
                    <span class="w-price">价格</span>
                    <span class="w-price">数量</span>
                    <span class="w-price">小计</span>
                </div>
                <c:forEach items="${list}" var="item">
                <c:forEach items="${item.list}" var="childrenItem">
                <c:if test="${childrenItem.status==1}">
                <div class="list-body">
                    <div class="list-box-item">
                        <span class="name w-info">
                        <c:choose>
                           <c:when test="${childrenItem.imgS!=''}">
                            <img src="${USER_FASTFDSPREURL}${childrenItem.imgS}" width="80" height="80" alt="" class="pic" onerror="javascript:this.src='${USER_DEFAULTIMG_URL}'">
                           </c:when>
                           <c:otherwise>
                             <img src="${USER_DEFAULTIMG_URL}" width="80" height="80" alt="" class="pic">
                           </c:otherwise>
                        </c:choose>
                            <div class="text">
                                ${childrenItem.itemBaseName}<br>
                                                                    规格：${childrenItem.spec}
                            </div>
                        </span>
                        <span class="price w-price">￥${childrenItem.zjjfPrice}</span>
                        <span class="number w-price">${childrenItem.num}</span>
                        <span class="price totleprice w-price">￥${childrenItem.totalPrice}</span>
                    </div>
                </div>
                </c:if>
                </c:forEach>
               </c:forEach>
                <div class="list-foot">
                    <span class="main-color">${totalProduct}</span> 件商品 &nbsp;&nbsp;&nbsp;&nbsp;
                                                   合计金额：￥<span id="totalPrice">${totalPrice}</span><br/>
                    <span id="freight">运费:￥${freight}</span>
                </div>
            </div>
        </div>
    </section>
    <section class="shopcart-operate" id="nextForm">
        应付总额：<span class="price" id="totalAmount">￥${totalPrice+freight}</span>
        <input type="submit" class="button-balance" value="提交订单" id="submitOrders">
    </section>
</main>
<%@ include file="../common/purchase-footer.jsp"%>
<script>
    $(function() {
    	
    	//提交订单按钮
    	$('#submitOrders').on('click',function(){
    		if($('#J_radioGroupOne .active').size()==0){
            	layer.msg('请选择配送方式!',{time:1000});
            	return;
            }else{
    		var ordertype = $('#J_radioGroupOne .active').attr('value');
    		$('#submitOrders').hide();
    		layer.load();
    		  $.ajax({
    				type : "POST",
    				url : "${root}/scms/cart/submitOrders.do",
    				data :{"ordertype":ordertype},
    				success : function(da) {
    					if (da.success) {
    						var html='';
    						html+='<form action="${root}/scms/cart/toSettlement.do" method="post" id="toSettlementForm">'
    						    +'<input type="hidden" name="orderid" value="'+da.message+'">'
    						    +'</form>';
    						    $('#nextForm').append(html);
    						    $('#toSettlementForm').submit();
    					}else{
    						if(da.message==1){
    							layer.msg('该笔订单中存在不满足起购量的商品,请回到购物车中修改!'
    									,{time:2000}
    							        ,function(){
    							          location.href='${root}/scms/cart/getCartList.do';
    							        })
    						}else if(da.message==2){
    							layer.msg('购物车为空,请到采购中心购买商品!',{time:2000}
    							          ,function(){
    							            location.href='${root}/scms/procurementcenter/listPage.do';
    							          });
    						}else if(da.message==3){
    							layer.msg('购物车中没有选中的商品,请回到购物车中修改！'
    									,{time:2000}
    							        ,function(){
    							          location.href='${root}/scms/cart/getCartList.do';
    							        })
    						}
    						else{
    							layer.msg('提交订单失败,请联系客服!',{time:1000});
        						console.log(da.message);
        						$('#submitOrders').show();
    						}
    					}
    				},
    				error : function(da) {
    					layer.msg('失败的请求！',{time:2000});
    				}
    			});
            }
    	});
    	
    	//配送方式
        $('#J_radioGroupOne').on('click', '.radio', function() {
            $(this).toggleClass('active').siblings('.radio').removeClass('active');
            var totalPrice = '${totalPrice}';
            var freight = '${freight}';
            totalPrice = parseFloat(totalPrice);
            freight = parseFloat(freight);
            if($('#J_radioGroupOne .active').size()==0){
            	layer.msg('请选择配送方式!',{time:1000});
            }
            //计算应付总额
            if($(this).attr('value')==0){//送货上门
	            $('#totalAmount').html('￥'+(totalPrice+freight));
	            $('#freight').text('运费:￥${freight}');
            }else{
	            $('#freight').text('免运费');
	            $('#totalAmount').html('￥'+totalPrice);
            }
        });
    });
</script>
</body>
</html>