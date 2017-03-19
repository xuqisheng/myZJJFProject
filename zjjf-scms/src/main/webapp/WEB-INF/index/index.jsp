<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<title>转角店宝</title>
<meta name="description" content="">
<meta name="keywords" content="">
<%@ include file="../common/head.jsp"%>
<link href="${root }/resources/css/home.css?v20160608" rel="stylesheet">
<script src="${root }/resources/vendor/slick/slick.min.js"></script>
</head>
<body>
	<div class="wrap-bd">
		<div class="home-top clearfix">
			<div class="overview">
				<h1>
					转角订单总额：<span class="orange mr-default" id="orderTotalPrice"><span style="font-size:22px">￥</span>0</span>
					<span class="ml-default mr-default"></span>
					线下订单总额：<span class="orange" id="scmsOrderTotalPrice"><span style="font-size:22px">￥</span>0</span>
				</h1>
				<span class="mr-default">今日订单总数：<span class="orange" id="orderCollect">0</span></span>
				<span class="ml-default mr-default">转角订单总数：<span class="orange" id="orderTotal">0</span></span>
				<span class="ml-default mr-default">线下订单总数：<span class="orange" id="scmsOrderTotal">0</span></span>
			</div>
			<div class="shortcut">
				<a href="${root}/scms/orderctl/GetSpOrderInfos.do?status=2" id="J_myZjOrder"><img src="${root }/resources/images/shortcut-order.png" alt=""><br>转角订单</a>
				<a href="${root}/scms/orderctl/listPage.do?staging=1" target="_blank"><img src="${root }/resources/images/shortcut-order-offline.png" alt=""><br>线下订单</a>
				<!--<a href="${root}/scms/procurementcenter/listPage.do" target="_blank"><img src="${root }/resources/images/shortcut-myorder.png" alt=""><br>我要进货</a>-->
	            <a href="${root}/scms/sp/toSpWalletIndex.do" id="J_myWallet"><img src="${root }/resources/images/shortcut-wallet.png" alt=""><br>我的钱包</a>
			    <script>
			        // 暂时解决点击快捷键，左侧菜单显示优化
	                $('#J_myZjOrder').on('click', function() {
	                	$('.icon-home', parent.document).parents('.category').removeClass('active');
	                    $('.icon-order', parent.document).parents('.category').attr('data-direction','up').siblings('.subcategory').show().find('a').eq(0).addClass('active');
	                });
	                $('#J_myWallet').on('click', function() {
                        $('.icon-home', parent.document).parents('.category').removeClass('active');
	                    $('.icon-account', parent.document).parents('.category').attr('data-direction','up').siblings('.subcategory').show().find('a').eq(0).addClass('active');
	                });
			    </script>
			</div>
		</div>
		<!-- ———————————————————————————————————————————————————— -->
        <c:if test="${SUPPLY_SESSION_KEY.supplierType == 2}">
            <div>
                <div class="title mt-default mb-small">
                    订单预警
                </div>
                <div class="clearfix">
                    <table class="table-list table-border" id="orderTable">
                        <thead>
                        <tr>
                            <th>未处理时间</th>
                            <th>订单编号</th>
                            <th>店名</th>
                            <th>联系人</th>
                            <th>手机号</th>
                            <th>订单金额</th>
                            <th>支付方式</th>
                            <th>订单状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody id="orderCautionList">
                        </tbody>
                    </table>
                </div>
                <script type="text/javascript">
                    A();
                    setInterval("A()", 900000);
                    function A(){
                        $.post("${root}/scms/orderctl/getCautionOrder.do",function(data){
                            var html = '';
                            if(data.success){
                                $.each(data.message,function(i,item){
                                    html+='<tr>';
                                    if(item.timeNum >= 12 && item.timeNum < 24){
                                        html+='<td style="background-color: yellow">'+item.timeNum+'小时</td>';
                                    }else if(item.timeNum >= 24){
                                        html+='<td style="background-color: red">'+item.timeNum+'小时</td>';
                                    }else{
                                        html+='<td>'+item.timeNum+'小时</td>';
                                    }
                                    html+='<td>'+item.orderId+'</td>';
                                    html+='<td>'+item.storeName+'</td>';
                                    html+='<td>'+item.consignee+'</td>';
                                    html+='<td>'+item.mobile+'</td>';
                                    html+='<td>'+item.orderPrice+'</td>';
                                    html+='<td>'+item.supportName+'</td>';
                                    if (item.status == 4 && item.logisticsStatus == 1){
                                        html+='<td>已打印</td>';
                                    }else if (item.status == 4 && item.logisticsStatus == 4){
                                        html+='<td>已发货</td>';
                                    }else if (item.status == 4 && item.logisticsStatus == 5){
                                        html+='<td>已送达</td>';
                                    }else if (item.status == 5){
                                        html+='<td>已完成</td>';
                                    }else{
                                        html+='<td>'+item.statusName+'</td>';
                                    }

                                    html+='<td><a href="${root }/scms/orderctl/GetSpOrderInfo.do?storeid='+item.orderId+'" target="_blank">去处理</a></td>';
                                    html+='</tr>';
                                });
                            }
                            if(html == ''){
                                html = '<tr><td colspan="9" class="no-data"></td></tr>';
                            }
                            $("#orderCautionList").html(html);
                        },'json');
                    }
                </script>
            </div>
        </c:if>
		<!-- —————————————————————— -->
		<div class="title mt-default mb-small">
	                    待处理订单
	    <!-- <a href="" class="more">更多&gt;&gt;</a> -->
	    </div>
		<div class="op-section clearfix">
			<ul class="fl" id="orderNav">
				<span class="pills pills-active" data-status="10">催单</span>
				<span class="pills" data-status="2">已派单</span>
				<span class="pills" data-status="3">已提单</span>
				<span class="pills" data-status="4">已打印</span>
				<span class="pills" data-status="40">已发货</span>
				<span class="pills" data-status="50">已送达</span>
				<span class="pills" data-status="5">已完成</span>
			</ul>
			<!-- <form action="" name="orderid" method="" class="fr">
				<input class="input-search-text" type="text" id="orderid" placeholder="订单号/店名" value="">
				<input type="hidden" value="10" id="status">
				<input type="button" class="input-search-button" value="搜索" id="sub" />
			</form> -->
		</div>
		<div class="clearfix">
			<table class="table-list table-border" id="orderTable">
				<thead>
                    <tr>
                        <th>订单编号</th>
                        <th>店名</th>
                        <th>联系人</th>
                        <th>手机号</th>
                        <th>订单金额</th>
                        <th>支付方式</th>
                        <th>订单状态</th>
                        <th>操作</th>
                    </tr>
				</thead>
				<tbody id="orderList">
				</tbody>
			</table>
            <%@ include file="../common/pagination.jsp"%>
		</div>
	    <div class="clearfix">
	        <div class="stock" style="width: 100% !important;">
	            <div class="title mb-small">
	                库存预警
	                <a href="${root }/scms/plantItem/returnStockCautionPage.do" class="more">更多&gt;&gt;</a>
	            </div>
	            <div class="table">
	                <table class="table-list table-border">
	                    <thead class="table-thead">
	                    <tr>
	                        <th class="ta-l" width="150">条形码</th>
	                        <th class="ta-l" width="200">商品名称</th>
	                        <th class="ta-l" width="200">规格</th>
	                        <th width="100">剩余库存</th>
	                    </tr>
	                    </thead>
	                    <tbody id="stock">
                        <!-- <tr>
                            <td>
                                <a href="">6547284551</a>
                            </td>
                            <td>青岛啤酒纯生罐装500ml</td>
                            <td>500ml x 12瓶</td>
                            <td class="txt-warn">1</td>
                        </tr> -->
	                    </tbody>
	                </table>
	            </div>
	        </div>

	        <!--屏蔽掉的热卖推荐轮播-->
	        <!--<div class="hot">
	            <div class="title mb-small">
	                热卖推荐
	                <a href="${root}/scms/procurementcenter/listPage.do?setOrderNum=1" target="_blank" class="more">更多&gt;&gt;</a>
	            </div>
	            <div class="slide" id="slide">


	            </div>
	        </div>-->
	    </div>
	</div>
<!-- <div class="cover-all loading"></div> -->
<script>
	function getNowFormatDate() {
	    var date = new Date();
	    var seperator1 = "-";
	    var seperator2 = ":";
	    var month = date.getMonth() + 1;
	    var strDate = date.getDate();
	    if(month >= 1 && month <= 9) {
	        month = "0" + month;
	    }
	    if(strDate >= 0 && strDate <= 9) {
	        strDate = "0" + strDate;
	    }
	    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
	    return currentdate;
	}

	$(function() {
		//错误图片路径
		var errorUrl = "${USER_DEFAULTIMG_URL}";
		//正确图片路径
		var okUrl = "${USER_FASTFDSPREURL }";
		$.ajax({
			type : "POST",
			url : "${root}/scms/orderctl/toDayAll.do",
			dataType : "json",
			success : function(data) {
				if (data.success) {
					$("#orderTotalPrice").html('<span style="font-size:22px">￥</span>' + data.message.orderTotalPrice);
					$("#scmsOrderTotalPrice").html('<span style="font-size:22px">￥</span>' + data.message.scmsOrderTotalPrice);
					$("#orderCollect").html(data.message.orderCollect);
					$("#orderTotal").html(data.message.orderTotal);
					$("#scmsOrderTotal").html(data.message.scmsOrderTotal);
				}
			},
			error : function(data) {
			}
		});

		//库存预警
		$.ajax({
			type : "POST",
			url : "${root}/scms/plantItem/getSupplierStockDetailBysSpId.do?pageSize=4",
			dataType : "json",
			success : function(data) {
				var html = '';
				if (data.totalSize) {
					$.each(data.list,function(i,item){
						html+='<tr>';
						html+='<td class="ta-l">';
						html+='<a href="${root}/scms/plantItem/plantItemPage.do?commodityIdAndName='+item.mdseId+'">'+item.mdseId+'</a>';
						html+='</td>';
						html+='<td class="ta-l">'+item.name+'</td>';
						html+='<td class="ta-l">'+item.spec+'</td>';
						html+='<td class="txt-warn">'+item.goodsStock+'</td>';
						html+='</tr>';
					});
				}
				if(html==''){
					html = '<tr><td colspan="4" class="nodata"></td></tr>';
				}
				$("#stock").html(html);
			},
			error : function(data) {

			}
		});
		//加载热销页面
//		$.ajax({
//			type : "POST",
//			url : "${root}/scms/procurementcenter/listPage1.do",
//			dataType : "json",
//			success : function(data) {
//				if (data.success) {
//					var html = '';
//					$.each(data.message,function(i,item){
//						html+='<div class="slide-item">';
//						if(item.imgs == null || item.imgs == ''){
//							html+='<a href="${root}/scms/procurementcenter/productDetail.do?id='+item.scmsid+'" class="p-img" target="_blank"><img src="'+errorUrl+'" width="148" height="148" alt=""></a>';
//						}else{
//							html+='<a href="${root}/scms/procurementcenter/productDetail.do?id='+item.scmsid+'" class="p-img" target="_blank"><img src="'+okUrl+item.imgS+'" width="148" height="148" onerror="javascript:this.src='+errorUrl+'" alt=""></a>';
//						}
//						html+='<div class="p-name">'+item.name+'</div>';
//                      html+='<div class="clearfix">';
//                      html+='<span class="p-price">￥'+item.zjjfPrice+'</span>';
//                      html+='<a href="${root}/scms/procurementcenter/productDetail.do?id='+item.scmsid+'" class="p-buy entrance" target="_blank">立即购买</a>';
//                      html+='<input type="hidden" value="'+item.scmsid+'">';
//                      html+='</div>';
//                      html+='</div>';
//					});
//					$("#slide").html(html);
//					$('#slide').slick({
//			            slidesToShow: 2,
//			            autoplay: true,
//			            speed: 500,
//			            arrows: true,
//			            dots: false,
//			            infinite: false,
//                      responsive: [
//                      {
//                          breakpoint: 2560,
//                              settings: {
//                                  slidesToShow: 4,
//                                  infinite: true,
//                                  dots: false
//                              }
//                      },
//                      {
//                          breakpoint: 1920,
//                          settings: {
//                              slidesToShow: 3,
//                              infinite: true,
//                              dots: false
//                          }
//                      },
//                      {
//                          breakpoint: 1440,
//                          settings: {
//                              slidesToShow: 2,
//                              infinite: true,
//                              dots: false
//                          }
//                      },
//                      {
//                          breakpoint: 1280,
//                          settings: {
//                              slidesToShow: 2,
//                              infinite: true,
//                              dots: false
//                          }
//                      },
//                      {
//                          breakpoint: 480,
//                          settings: {
//                              slidesToShow: 1,
//                              slidesToScroll: 1
//                          }
//                      }
//                      ]
//			        });
//				}
//				if(html==''){
//					$("#slide").replaceWith('<div class="slide"><div class="nodata"></div></div>');
//				}
//
//			},
//			error : function(data) {
//
//			}
//		});

		//加载催单页面
		$("#jpagination").pagination({
			pageSize: 10,
            remote: {
                url: '${root}/scms/orderctl/GetSpOrderInfoslist.do',
                params: {'status': 10, "date": getNowFormatDate()},
                success: function(data) {
                	var html = '';
                     $.each(data.list, function(i,item) {
                     	html+='<tr><td>'+item.orderId+'</td>';
                     	html+='<td>'+item.storeName+'</td>';
                     	 html+='<td>'+item.consignee+'</td>';
                     	 if(item.mobile !=null && item.mobile!=''){
                     		html+='<td>'+item.mobile+'</td>';
                     	 }else{
                     		html+='<td></td>';
                     	 }
                     	html+='<td class="txt-warn">'+item.orderPrice+'</td>';
                     	if(item.supportmetho==1){
   	                	 html+='<td class="txt-success">银行卡支付</td>';
	   	                }else if(item.supportmetho==2){
	   	                	 html+='<td class="txt-warn">货到付款</td>';
	   	                }else if(item.supportmetho==3){
	   	                	 html+='<td class="txt-success">支付宝支付</td>';
	   	                }else if(item.supportmetho==4){
                             html+='<td class="txt-success">微信支付</td>';
                        }else if(item.supportmetho==5){
                             html+='<td class="txt-success">钱包支付</td>';
                        }else{
                             html+='<td></td>';
                        }
                     	if(item.status==2){
                     		html+='<td>已派单</td>';
                     	}else if(item.status==3){
                     		html+='<td>已提单</td>';
                     	}else if(item.status==4){
                     		if(item.logisticsStatus==4){
                     			html+='<td>配送中</td>';
                     		}else if(item.logisticsStatus==5){
                     			html+='<td class="txt-success">已送达</td>';
                     		}else{
                     			html+='<td>已打印</td>';
                     		}
                     	}else if(item.status==5){
                     		html+='<td class="txt-success">已送达</td>';
                     	}else if(item.status==6){
                     		html+='<td>已取消</td>';
                     	}
    	                if(item.supportmetho==2){
    	                	 html+='<td><a href="${root }/scms/orderctl/GetSpOrderInfo.do?storeid='+item.orderId+'" target="_blank">详情</a></td></tr>';
    	                }else{
    	                	if(item.supportStatus == 0) {
    	                		html+='<td><span style="color:red">未支付</span></td></tr>';
    	                	}
    	                	else if(item.supportStatus == 1) {
    	                		html+='<td><a href="${root }/scms/orderctl/GetSpOrderInfo.do?storeid='+item.orderId+'" target="_blank">详情</a></td></tr>';
    	                	}
    	                }
                     });
                     if(html == "") {
                     	html = '<tr><td colspan="10" class="no-data"></td></tr>';
                     }
                     $('#orderList').html(html);
                     //$('.cover-all.loading').hide();
                 },
                totalName:'totalSize'
            }
        });

		$

		$('#orderNav').on('click', '.pills', function() {
            // $('.cover-all.loading').show();
			var orderStatus = $(this).attr('data-status');
			$(this).addClass('pills-active').siblings().removeClass('pills-active');
			$('#orderid').val("");
			$("#status").val(orderStatus);
			$("#jpagination").pagination('setParams', {'status': orderStatus,"date": getNowFormatDate()});
			$("#jpagination").pagination('setPageIndex', 0);
			$("#jpagination").pagination('remote');
		});
	})
</script>
</body>
</html>
