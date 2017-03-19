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
    <title>快销宝 - 采购单</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <%@ include file="../common/purchase-head.jsp"%>
    <link rel="stylesheet" href="${root}/resources/purchase/css/shopcart.css">
</head>
<body>
<%@ include file="../common/purchase-nav.jsp"%>
<header>
    <a href="${root}/scms/procurementcenter/listPage.do"><div class="logo"></div></a>
    <div class="shopcart-bar">
        <div class="shopcart-bar-item curr">
            <span class="number">1</span>
        	采购单
        </div>
        <div class="shopcart-bar-item">
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
    <section class="title">
       	 采购单&nbsp;
        <a href="${root}/scms/procurementcenter/listPage.do">继续采购&gt;</a>
    </section>
    <c:if test="${fn:length(list) == 0}">
      <section class="empty-shopcart">
        <div class="h2">您的采购单还是空的</div>
        <div class="h3">再去逛逛吧~</div>
        <a href="${root}/scms/procurementcenter/listPage.do" class="button">立即采购</a>
    </section>
    </c:if>
    <c:if test="${fn:length(list) != 0}">
    <section class="goods-list">
        <div class="list-head">
            <span class="w-goods-checkbox">
                <span class="checkbox selectAll" data-checkbox="false"></span>
            </span>
            <span style="float:left; width: 82px">全选</span>
            <span class="w-goods-name">商品</span>
            <span class="w-goods-price">价格</span>
            <span class="w-goods-number">数量</span>
            <span class="w-goods-price">小计</span>
            <span class="w-goods-price">操作</span>
        </div>
        <form action="" id="shoppingCartForm">
        <c:forEach items="${list}" var="item">
          <div class="list-body">
            <section class="list-box">
                <div class="list-box-title">
                    <b id="${item.brandId}">品牌：${item.brandName}</b>&nbsp;&nbsp;
                    <c:if test="${item.isShowLackNumMessage}">
                    <c:if test="${item.lackNum!=0}">
                    <span class="main-color error">起批量为${item.minimum}箱，还差${item.lackNum}箱</span>
                    </c:if>
                    </c:if>
                </div>
                <c:forEach items="${item.list}" var="childrenItem">
                <div class="list-box-item clearfix">
                    <span class="w-goods-checkbox">
                       <c:if test="${childrenItem.status==0}">
                        <span class="checkbox bodyCheckbox" data-checkbox="false"></span>
                       </c:if>
                       <c:if test="${childrenItem.status==1}">
                        <span class="checkbox active bodyCheckbox" data-checkbox="true"></span>
                       </c:if>
                    </span>
                    <c:choose>
                      <c:when test="${childrenItem.imgS!=''}">
                        <img src="${USER_FASTFDSPREURL}${childrenItem.imgS}" width="80" height="80" alt="" class="w-goods-pic" onerror="javascript:this.src='${USER_DEFAULTIMG_URL}'">
                      </c:when>
                      <c:otherwise>
                        <img src="${USER_DEFAULTIMG_URL}" width="80" height="80" alt="" class="w-goods-pic">
                      </c:otherwise>                    
                    </c:choose>
                    <span class="name w-goods-name">
                        <div class="text">
                         ${childrenItem.itemBaseName}<br>
                                                    规格：${childrenItem.spec}
                        </div>
                    </span>
                    <span class="price w-goods-price">￥${childrenItem.zjjfPrice}</span>
                    <span class="number w-goods-number">
                    <c:choose>
                     <c:when test="${childrenItem.num>1}">
                        <span class="box reduce J_reduce">-</span>
                     </c:when>
                     <c:otherwise>
                        <span class="box reduce active J_reduce">-</span>
                     </c:otherwise>
                    </c:choose>
                        <input type="text" value="${childrenItem.num}" class="box text cartNum" maxlength="4">
                        <input type="hidden" value="${childrenItem.num}">
                        <span class="box add J_add">+</span>
                    </span>
                    <span class="price totleprice w-goods-price">￥${childrenItem.totalPrice}</span>
                    <input type="hidden" value="${childrenItem.cart_id}">
                    <span class="operate w-goods-price delete">删除</span>
                </div>
                </c:forEach>
            </section>
        </div>
        </c:forEach>
        <div class="list-foot">
            <span class="w-goods-checkbox">
                <span class="checkbox selectAll" data-checkbox="false"></span>
            </span>
            <span style="float:left; width: 130px">
				全选&nbsp;&nbsp;&nbsp;
                <span class="deleteAll" id="deleteAll" style="cursor: pointer">删除选中的商品</span>
            </span>
            <div class="info">
            	已选择 <span class="main-color" id="totalProduct">${totalProduct}</span> 件商品&nbsp;&nbsp;&nbsp;&nbsp;
            	合计 <span class="price" id="totalPrice">￥${totalPrice}</span>&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" class="button-balance" value="去结算" id="toSettleAccounts">
            </div>
        </div>
        </form>
    </section>
    </c:if>
   <!--  <section class="del-goods">
        已删除商品，您可以重新购买：
        <div class="goods">
            <span class="info">风行红枣枸杞牛奶饮品 &nbsp;&nbsp; 规格：200ml*12盒/箱</span>
            <span class="price">￥10.00</span>
            <span class="number">1</span>
            <span class="re-buy">重新购买</span>
        </div>
    </section> -->
</main>
<%@ include file="../common/purchase-footer.jsp"%>
<script>
    $(function() {
    	//是否选中全选按钮
    	if($('span .bodyCheckbox.active').length==$('span .bodyCheckbox').length){
            $('.selectAll').addClass('active');
            $('.selectAll').attr('data-checkbox','true');
     	}
    	
    	
    	//去结算按钮
    	$('#toSettleAccounts').on('click',function(){
    		var error = parseInt('0');
    		$('span.error').each(function(){
    			if($(this).parent().siblings('div.list-box-item').children('span.w-goods-checkbox').children().hasClass('active')){
    				error=error+1;
    			}
    		});
    		if(error==0){
    			if($('span .bodyCheckbox.active').length==0){
    				layer.msg('请选择商品!',{time:1000});
    			}else{
    			location.href='${root}/scms/cart/toConfigOrderInfo.do';
    			}
    		}else{
    			layer.msg('选择的商品中还有没满足起批量要求的,不能结算!',{
    				time:1000
    			},function(){
    				location.href='${root}/scms/cart/getCartList.do';
    			});
    		}
    	});
    	
    	
    	//单选按钮
        $('.bodyCheckbox').on('click',function(){
        	$(this).toggleClass('active');
        	if($(this).hasClass('active')){
        		$(this).attr('data-checkbox','true');
        	}else{
        		$(this).attr('data-checkbox','false');
        	}
        	//是否勾选全选
        	if($('span .bodyCheckbox.active').length==$('span .bodyCheckbox').length){
               $('.selectAll').addClass('active');
               $('.selectAll').attr('data-checkbox','true');
        	}else{
        		$('.selectAll').removeClass('active');
        		$('.selectAll').attr('data-checkbox','false');
        	}
        	//页面计算商品价格和商品件数
        	/* var totalPrice = $('#totalPrice').html();
        	var totalProduct = $('#totalProduct').html();
        	totalPrice = parseFloat(totalPrice.substring(1,totalPrice.length));
        	totalProduct = parseInt(totalProduct);
        	var singlePrice = $(this).parent().parent().children().eq(5).html();
        	var singleNum = $(this).parent().parent().children().eq(4).children('input.cartNum').val()
        	singlePrice = parseFloat(singlePrice.substring(1,singlePrice.length));
        	singleNum = parseInt(singleNum);
        	if($(this).hasClass('active')){
               totalPrice+=singlePrice;
               totalProduct+=singleNum;
        	}else{
        		totalPrice-=singlePrice;
        		totalProduct-=singleNum;
        		check='uncheck';
        	}
        	$('#totalPrice').html('￥'+totalPrice.toFixed(2));
        	$('#totalProduct').html(totalProduct); */
        	var check='check';
        	if(!$(this).hasClass('active')){
                check='uncheck';
         	}
        	var cartIdStr = $(this).parent().parent().children().eq(6).val();
        	$.ajax({
    			type : "POST",
    			url : "${root}/scms/cart/asynUpdateCart.do",
    			data :{"type":check,'cartIdStr':cartIdStr},
    			success : function(da) {
    				if (da.success) {
    					$('#totalPrice').html('￥'+da.message.totalPrice.toFixed(2));
    					$('#totalProduct').html(da.message.totalProduct);
    					//解析起购量信息
    					$.each(da.message.scmsMinimumVoList,function(i,item){
    						$bran = $('#'+item.brandId+'');
    						$bran.next('span').empty();
    						if(item.isShowLackNumMessage){
    							if(item.lackNum>0){
    							var html = '&nbsp;&nbsp;<span class="main-color error">起批量为'+item.minimum+'箱，还差'+item.lackNum+'箱</span>';
    							$bran.after(html);
    							}
    						}
    					});
    				}else{
    					layer.msg(da.message,{time:1000});
    				}
    			},
    			error : function(da) {
    				layer.msg('失败的请求!',{time:1000});
    			}
    		});
        });
    	
    	//全选按钮
        $('.selectAll').on('click', function() {
        	var totalPrice = parseFloat(0);
        	var singlePrice = '';
            var cartIdStr = '';
            var cartId = '';
            var totalProduct = parseInt(0);
            var singleNum = '';
            $('.selectAll').toggleClass('active');
            if($(this).hasClass('active')){
            	$(this).attr('data-checkbox','true');
            	$('.bodyCheckbox').addClass('active');
            	//js 计算总价,选中商品数
                $('.bodyCheckbox').each(function(){
                	if($(this).hasClass('active')) {
                        $(this).attr('data-checkbox','true');
                        singlePrice = $(this).parent().parent().children().eq(5).html();
                        singlePrice = singlePrice.substring(1,singlePrice.length);
                        singleNum = $(this).parent().parent().children().eq(4).children('input.cartNum').val();
                        totalPrice += parseFloat(singlePrice);
                        totalProduct += parseInt(singleNum);
                        cartIdStr+=$(this).parent().parent().children().eq(6).val()+',';
                    }
                });
                //$('#totalPrice').html('￥'+totalPrice.toFixed(2));
                //$('#totalProduct').html(totalProduct);
                cartIdStr = cartIdStr.substring(0,cartIdStr.length-1);
                $.ajax({
        			type : "POST",
        			url : "${root}/scms/cart/asynUpdateCart.do",
        			data :{"type":"check",'cartIdStr':cartIdStr},
        			success : function(da) {
        				if (da.success) {
        					$('#totalPrice').html('￥'+da.message.totalPrice.toFixed(2));
        					$('#totalProduct').html(da.message.totalProduct);
        					//解析起购量信息
        					$.each(da.message.scmsMinimumVoList,function(i,item){
       						   $bran = $('#'+item.brandId+'');
       						   $bran.next('span').empty();
       						   if(item.isShowLackNumMessage){
       						   	  if(item.lackNum>0){
       						   	  var html = '&nbsp;&nbsp;<span class="main-color error">起批量为'+item.minimum+'箱，还差'+item.lackNum+'箱</span>';
       						   	  $bran.after(html);
       						   	}
       						   }
        					});
        				}else{
        					layer.msg(da.message,{time:1000});
        				}
        			},
        			error : function(da) {
        				layer.msg('失败的请求!',{time:1000})
        			}
        		});
            }else{
            	$(this).attr('data-checkbox','false');
            	$('.bodyCheckbox').removeClass('active');
            	$('#totalPrice').html('￥0.00');
            	$('#totalProduct').html('0');
            	$('.bodyCheckbox').each(function(){
                	if(!$(this).hasClass('active')) {
                        cartIdStr+=$(this).parent().parent().children().eq(6).val()+',';
                    }
                });
            	cartIdStr = cartIdStr.substring(0,cartIdStr.length-1);
            	$.ajax({
        			type : "POST",
        			url : "${root}/scms/cart/asynUpdateCart.do",
        			data :{"type":"uncheck",'cartIdStr':cartIdStr},
        			success : function(da) {
        				if (da.success) {
        					$('#totalPrice').html('￥'+da.message.totalPrice.toFixed(2));
        					//解析起购量信息
        					$.each(da.message.scmsMinimumVoList,function(i,item){
       						   $bran = $('#'+item.brandId+'');
       						   $bran.next('span').empty();
       						   if(item.isShowLackNumMessage){
       						   	  if(item.lackNum>0){
       						   	  var html = '&nbsp;&nbsp;<span class="main-color error">起批量为'+item.minimum+'箱，还差'+item.lackNum+'箱</span>';
       						   	  $bran.after(html);
       						   	}
       						   }
        					});
        				}else{
        					layer.msg(da.message,{time:1000})
        				}
        			},
        			error : function(da) {
        				layer.msg('失败的请求!',{time:1000})
        			}
        		});
            }
            
        });
        
    	
    	//在购物车输入框中输入事件
    	$('.cartNum').keypress(function(b){
    		var keyCode = b.keyCode ? b.keyCode : b.charCode;
    		if (keyCode != 0 && (keyCode < 48 || keyCode > 57) && keyCode != 8 && keyCode != 37 && keyCode != 39 && keyCode !=13) {
                return false;
            } else {
                return true;
            }
    	}).change(function(){
    		var numVal = parseInt($(this).val()) || 0;
            numVal = numVal < 1 ? 1 : numVal;
            if(numVal>9999){
            	layer.tips('商品数量超限',this, {
            	    time: 1000
            	});
            	$(this).val($(this).next().val());
            	return;
            }else{
            	$(this).val(numVal);
                $(this).next().val(numVal);	
            }
            var $this = $(this);
            var cartId=$(this).parent('span').siblings('input[type="hidden"]').val();
            var $singleTotalPrice = $(this).parent('span').next('span');
            $.ajax({
    			type : "POST",
    			url : "${root}/scms/cart/asynUpdateCart.do",
    			data :{"type":"add","cartId":cartId,"quantity":$(this).val()},
    			success : function(da) {
    				if (da.success) {
    					$singleTotalPrice.html('￥'+da.message.singleTotalPrice.toFixed(2));
    					$('#totalPrice').html('￥'+da.message.totalPrice.toFixed(2));
    					$('#totalProduct').html(da.message.totalProduct.toFixed(2));
    					var $span = $this.parent().parent().siblings('div:first');
						$span.children('span').remove();
						//解析起购量信息
    					$.each(da.message.scmsMinimumVoList,function(i,item){
   						   $bran = $('#'+item.brandId+'');
   						   $bran.next('span').empty();
   						   if(item.isShowLackNumMessage){
   						   	  if(item.lackNum>0){
   						   	  var html = '&nbsp;&nbsp;<span class="main-color error">起批量为'+item.minimum+'箱，还差'+item.lackNum+'箱</span>';
   						   	  $bran.after(html);
   						   	}
   						   }
    					});
    				}else{
    					if(da.message=='null'){
                            location.href='${root}/scms/cart/getCartList.do';    						
      					}else{
      					layer.msg(da.message,{time:1000})
      					}
    				}
    			},
    			error : function(da) {
    				layer.msg('失败的请求!',{time:1000})
    			}
    		});
    	});
        //加商品
        $('#shoppingCartForm').on('click','.J_add',function(){
        	var $this = $(this);
        	var i = parseInt($(this).parent().children('input.cartNum').val()) + 1;
        	if(parseInt($(this).parent().children('input.cartNum').val()) == 1){
        		i = 10;
        	}else{
        		i = parseInt($(this).parent().children('input.cartNum').val()) + 10
        	}
        	
        	$(this).parent().children('input.cartNum').val(i);
        	$(this).parent().children('input[type="hidden"]').val(i);
        	if($(this).parent().children('input.cartNum').val()>1){
        		$(this).siblings('span').removeClass('active');
        	}
        	var quantity = $(this).prev('input').val();
        	if(quantity>9999){
        		$(this).prev('input').prev().val(9999);
        		$(this).prev('input').val(9999);
        		layer.tips('商品数量超限',this, {
            	    time: 1000
            	});
        		return;
        	}
        	var cartId = $(this).parent('span').siblings('input[type="hidden"]').val();
        	var $singleTotalPrice = $(this).parent('span').next('span');
        	var $singlePrice = $(this).parent('span').prev('span');
        	$.ajax({
    			type : "POST",
    			url : "${root}/scms/cart/asynUpdateCart.do",
    			data :{"type":"add","cartId":cartId,"quantity":quantity},
    			success : function(da) {
    				if (da.success) {
    					$singleTotalPrice.html('￥'+da.message.singleTotalPrice.toFixed(2));
    					$('#totalPrice').html('￥'+da.message.totalPrice.toFixed(2));
    					$('#totalProduct').html(da.message.totalProduct);
    					$singlePrice.html('￥'+da.message.singlePrice.toFixed(2));
    					var $span = $this.parent().parent().siblings('div:first');
						$span.children('span').remove();
						//解析起购量信息
    					$.each(da.message.scmsMinimumVoList,function(i,item){
   						   $bran = $('#'+item.brandId+'');
   						   $bran.next('span').empty();
   						   if(item.isShowLackNumMessage){
   						   	  if(item.lackNum>0){
   						   	  var html = '&nbsp;&nbsp;<span class="main-color error">起批量为'+item.minimum+'箱，还差'+item.lackNum+'箱</span>';
   						   	  $bran.after(html);
   						   	}
   						   }
    					});
    				}else{
    					if(da.message=='null'){
                          location.href='${root}/scms/cart/getCartList.do';    						
    					}else{
    					layer.msg(da.message,{time:1000})
    					}
    				}
    			},
    			error : function(da) {
    				layer.msg('失败的请求!',{time:1000})
    			}
    		}); 
        });
        //减商品
        $('#shoppingCartForm').on('click','.J_reduce',function(){
        	var $this = $(this);
        	var i = parseInt($(this).next('input').val()) - 1;
        	if(i ==0 ){
        		$(this).addClass('active');
        		return;
        	} else if(i ==1 ){
        		$(this).addClass('active');
        		$(this).next('input').val(i);
        		$(this).parent().children('input[type="hidden"]').val(i);
        	} else {
        		$(this).next('input').val(i);
        		$(this).parent().children('input[type="hidden"]').val(i);
        	}
        	var cartId = $(this).parent('span').siblings('input[type="hidden"]').val();
        	var $singleTotalPrice = $(this).parent('span').next('span');
        	var $singlePrice = $(this).parent('span').prev('span');
        	$.ajax({
    			type : "POST",
    			url : "${root}/scms/cart/asynUpdateCart.do",
    			data :{"type":"add","cartId":cartId,"quantity":$(this).next('input').val()},
    			success : function(da) {
    				if (da.success) {
    					$singleTotalPrice.html('￥'+da.message.singleTotalPrice.toFixed(2));
    					$singlePrice.html('￥'+da.message.singlePrice.toFixed(2));
    					$('#totalPrice').html('￥'+da.message.totalPrice.toFixed(2));
    					$('#totalProduct').html(da.message.totalProduct);
    					var $span = $this.parent().parent().siblings('div:first');
						$span.children('span').remove();
						//解析起购量信息
    					$.each(da.message.scmsMinimumVoList,function(i,item){
   						   $bran = $('#'+item.brandId+'');
   						   $bran.next('span').empty();
   						   if(item.isShowLackNumMessage){
   						   	  if(item.lackNum>0){
   						   	  var html = '&nbsp;&nbsp;<span class="main-color error">起批量为'+item.minimum+'箱，还差'+item.lackNum+'箱</span>';
   						   	  $bran.after(html);
   						   	}
   						   }
    					});
    				}else{
    					layer.msg(da.message,{time:1000});
    				}
    			},
    			error : function(da) {
    				layer.msg('失败的请求!',{time:1000});
    			}
    		});
        });
        //删除商品
        $('#shoppingCartForm').on('click','.delete',function(){
        	var cartIdArr = $(this).prev('input').val();
        	var quantity = $(this).next('input').val();
        	layer.confirm('确定删除该商品?',function(index){
            	$.ajax({
        			type : "POST",
        			url : "${root}/scms/cart/asynUpdateCart.do",
        			data :{"type":"delete","cartIdArr":cartIdArr,"quantity":quantity},
        			success : function(da) {
        				if (da.success) {
        					location.href="${root}/scms/cart/getCartList.do";
        				}else{
        					if(da.message==null){
        						location.href="${root}/scms/cart/getCartList.do";	
        					}
        				}
        			},
        			error : function(da) {
        				layer.msg('失败的请求!',{time:1000});
        			}
        		}); 
        	});
        });
        //删除所有选中商品 #id="deleteAll"')
        $('#deleteAll').click(function() {
        	
        	if($('span .bodyCheckbox.active').length==0){
        		layer.msg('请选择需要删除的商品!',{time:2000});
        		return;
        	}
        	layer.confirm('确定要删除所有选中商品?',function(index){
        		var cartIdArr = '';
        		$('span .bodyCheckbox.active').each(function(){
	                cartIdArr+=$(this).parent().siblings('input').val()+',';
	        	});
	        	cartIdArr = cartIdArr.substring(0,cartIdArr.length-1);
	        	$.ajax({
	    			type : "POST",
	    			url : "${root}/scms/cart/asynUpdateCart.do",
	    			data :{"type":"delete","cartIdArr":cartIdArr,"quantity":$(this).next('input').val()},
	    			success : function(da) {
	    				if (da.success) {
	    					location.href="${root}/scms/cart/getCartList.do";
	    				}else{
	    					if(da.message==null){
	    						location.href="${root}/scms/cart/getCartList.do";	
	    					}
	    				}
	    			},
	    			error : function(da) {
	    				layer.msg('失败的请求!',{time:1000});
	    			}
	    		});
        	});
        });
    });
</script>
</body>
</html>