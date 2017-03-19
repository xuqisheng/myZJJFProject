<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh">

	<head>
		<meta charset="UTF-8">
		<title>快销宝 - 转角街坊</title>
		<meta name="description" content="">
		<meta name="keywords" content="">
		<%@ include file="../common/head.jsp"%>
		<link rel="stylesheet" href="${root}/resources/css/order-detail.css?v1">
		<script src="${root}/resources/js/china-cost.js"></script>
	</head>
	<style type="text/css">
		.info-ct li {
			height: 28px
		}
		.o-prog li{
			height: 28px;
		}
	</style>

	<body>
		<header class="header wrap-padding noprint">
			<section class="wrap">
				<div class="fl"><img src="${root}/resources/images/logo-other.png" alt="logo图片"></div>
				<div class="fr info">
					<span class="username mr-small">您好&nbsp;&nbsp;${logInVo.userName}</span> |
					<span class="ml-small close" onclick="window.close();">关闭</span></div>
			</section>
		</header>
		<input id="groupId" value="" type="hidden" />
		<div class="wrap-padding noprint">
			<div class="mt-default mb-default">
				<span>当前位置：</span>
				<span class="crumb">转角订单
        </span><span class="crumb crumb-active">订单详情</span>
			</div>
			<div class="op-section">
				<b>
                            订单编号：${map.orderInfo.orderId}
                            &nbsp;
                            &nbsp;
                            &nbsp;
		        客户订单号：${map.linkOrderNo}
	    </b>
			</div>
			<div class="mt-default clearfix">
				<div class="title mb-small">收货信息</div>
				<div>
					<ul class="info-ct fl">
						<li class="table-list"><span class="trade-imfor-dt">配送店主：
				</span> <span class="trade-imfor-dd">
						<p>${map.orderInfo.supplierNam }
						</p>
				</span></li>
						<li class="table-list"><span class="trade-imfor-dt">手机号：
				</span> <span class="trade-imfor-dd">
						<p>${map.orderInfo.mobile }
						</p>
				</span>
						</li>

						<li class="table-list">
							<span class="trade-imfor-dt">店名：</span>
							<span class="trade-imfor-dd">
						<p>${map.orderInfo.storeName }
						</p>
				</span>
						</li>
						<li class="table-list">
							<span class="trade-imfor-dt">店面地址：</span>
							<span class="trade-imfor-dd">${map.orderInfo.address }</span> &nbsp;&nbsp;&nbsp;
						</li>
						<li class="table-list">
							<span class="trade-imfor-dt">地址备注：</span>
							<span class="trade-imfor-dd">
						<input type="text" value="${map.orderInfo.addressRemark }" id="addressRemark" class="hidden input input-default">
						<span class="icon-op icon-op-edit J_edit"></span>
							</span>
						</li>
					</ul>
					<ul class="info-ct fl">
						<li class="table-list"><span class="trade-imfor-dt">配送时间：</span>
							<span class="trade-imfor-dd">
						<p>
							<fmt:formatDate value="${map.orderInfo.sendDate}" type="date" />
						</p>
				</span>
						</li>
						<li class="table-list noprint">
							<span class="trade-imfor-dt">支付方式：
					</span>
							<span class="trade-imfor-dd">
					<c:choose>
						<c:when test="${map.orderInfo.supportmetho==1 }">
							银行卡支付
						</c:when>
						<c:when test="${map.orderInfo.supportmetho==2 }">
							货到付款
						</c:when>
						<c:when test="${map.orderInfo.supportmetho==3 }">
							支付宝支付
						</c:when>
						<c:when test="${map.orderInfo.supportmetho==4 }">
							微信支付
						</c:when>
						<c:when test="${map.orderInfo.supportmetho==5 }">
							钱包支付
						</c:when>
					</c:choose>
					</span>
						</li>
						<li class="table-list">
							<span class="trade-imfor-dt">固定电话：
				</span>${map.orderInfo.userTel }
							<span class="trade-imfor-dd">
						<p>
						</p>
				</span>
						</li>
						<li class="table-list">
							<span class="trade-imfor-dt">备注：</span>
							<span class="trade-imfor-dd"><p>${map.orderInfo.userRemark }</p></span>
						</li>
						<li class="table-list">
							<span class="trade-imfor-dt"></span>
							<span class="trade-imfor-dd"></span>
						</li>
					</ul>
				</div>
			</div>
			<div class="title mt-default mb-small">货物清单
			</div>
			<div class="s-list">
				<table class="list table-border">
					<tr>
						<th>条码
						</th>
						<th>商品名称
						</th>
						<th>规格
						</th>
						<th>数量
						</th>
						<th>单价（元）
						</th>
						<th>金额（元）
						</th>
						<th></th>
					</tr>
					<c:forEach var="order" items="${map.orderDetail }">
						<tr>
							<td>${order.barCode }
							</td>
							<td>${order.name }
								<c:if test="${!empty order.youHui}">
									(${order.youHui})
								</c:if>
								<c:if test="${order.goodsType==1}">
									(平台赠品)
								</c:if>
								<c:if test="${order.goodsType==2}">
									(批发商赠品)
								</c:if>
							</td>
							<td>${order.spec }
							</td>
							<td>${order.quantity }
							</td>
							<td>${order.price }
							</td>
							<td>${order.totalPrice }
							</td>
							<td></td>
						</tr>
					</c:forEach>
					<tr class="t-footer">
						<td class="tsum">金额合计：<span class="orange">${map.total }</span>
						</td>
						<td class="tsum">满立减补贴：
							<span class="orange">${map.orderInfo.rebateSp2Sp+map.orderInfo.rebatePlat+map.orderInfo.rebateSp2Plat}
				</span>
						</td>
						<td class="tsum">优惠券补贴：
							<span class="orange">${map.orderInfo.spCoupon+map.orderInfo.platCoupon}
				</span>
						</td>
						<td class="tsum">运费：
							<span class="orange">${map.orderInfo.freight }
				</span>
						</td>
						<td class="tsum">用户实付（含运费）：
							<span class="orange" id="bigmoney">${map.total-map.orderInfo.rebate-map.orderInfo.coupon+map.orderInfo.freight}
				</span>
						</td>
						<td>小写：
							<span class="orange">${map.total-map.orderInfo.rebate-map.orderInfo.coupon+map.orderInfo.freight}</span>
						</td>
						<td>总数量：${map.ordernum }
						</td>
					</tr>
				</table>
			</div>
			<div class="title mt-default mb-small">订单跟踪</div>
			<div class="s-list">
				<div class="o-prog">
					<ul>
						<li class="curr"><span class="p-n">提交订单时间</span><span><fmt:formatDate value="${map.orderInfo.addTime}" type="both" /></span></li>
						<li class="curr"><span class="p-n">在线支付时间</span><span><fmt:formatDate value="${map.orderInfo.supportTime}" type="both" /></span></li>
						<li class="curr"><span class="p-n">客服派单时间</span><span><fmt:formatDate value="${map.orderInfo.gaveTime}" type="both" /></span></li>
						<li class="curr"><span class="p-n">批发商提单时间</span><span><fmt:formatDate value="${map.orderInfo.getOrderTime}" type="both" /></span></li>
						<li class="curr"><span class="p-n">批发商打印时间</span><span><fmt:formatDate value="${map.orderInfo.printTime}" type="both" /></span></li>
						<li class="curr"><span class="p-n">批发商发货时间</span><span><fmt:formatDate value="${map.orderInfo.whSendTime}" type="both" /></span></li>
						<li class="curr"><span class="p-n">货物送达时间</span><span><fmt:formatDate value="${map.orderInfo.whAckTime}" type="both" /></span></li>
					</ul>
					<c:if test="${map.orderInfo.status==2}">
						<a href="#" onclick="javascript:location.href='${root}/scms/orderctl/updateStatusATime.do?status=3&orderid=${map.orderInfo.orderId}'">
							<button class="complete-btn">提单</button>
						</a>
					</c:if>
					<c:if test="${map.orderInfo.status==3}">
                        <c:choose>
                            <c:when test="${SUPPLY_SESSION_KEY.supplierType == 2}">
                                <a href="#" onclick="javascript:location.href='${root }/scms/ERPSpOrderOwner/toAddERPSpOrderOwner/${map.orderInfo.orderId}.do'">
                                    <button class="complete-btn" style="right: 120px;">附属订单</button>
                                </a>
                                <a href="${root}/scms/erpMarket/toSaveSaleOutOrder.do?id=${map.orderInfo.id}" class="complete-btn">出库</a>
                            </c:when>
                            <c:otherwise>
                                <a href="#" onclick="javascript:location.href='${root}/scms/orderctl/updateStatusATime.do?status=4&orderid=${map.orderInfo.orderId}'">
                                    <button id="printBillinfo" class="complete-btn">打印</button>
                                </a>
                            </c:otherwise>
                        </c:choose>
					</c:if>
					<c:if test="${map.orderInfo.status==4}">
                        <button id="printBillinfo" class="complete-btn">重新打印</button>
						<%--<c:choose>
                            <c:when test="${SUPPLY_SESSION_KEY.supplierType == 2 && map.orderInfo.logisticsStatus != 5}">
                            </c:when>
							<c:when test="${map.orderInfo.logisticsStatus == 1}">
                                <button id="printBillinfo" class="complete-btn" style="right: 150px;">重新打印</button>
								<a href="#" onclick="javascript:location.href='${root}/scms/orderctl/updateStatusATime.do?logisticsStatus=1&orderid=${map.orderInfo.orderId}'">
									<button id="fahuo" class="complete-btn">确认发货</button>
								</a>
							</c:when>
							<c:when test="${map.orderInfo.logisticsStatus == 4}">
                                <button id="printBillinfo" class="complete-btn" style="right: 150px;">重新打印</button>
								<a href="#" onclick="javascript:location.href='${root}/scms/orderctl/updateStatusATime.do?logisticsStatus=4&orderid=${map.orderInfo.orderId}'">
									<button id="complete-btn" class="complete-btn">确认送达</button>
								</a>
							</c:when>
							<c:when test="${map.orderInfo.logisticsStatus == 5}">
								<a href="#" onclick="javascript:location.href='${root }/scms/ERPSpOrderOwner/toAddERPSpOrderOwner/${map.orderInfo.orderId}.do'">
									<button class="complete-btn">附属订单</button>
								</a>
							</c:when>
						</c:choose>--%>
					</c:if>
				</div>
			</div>
		</div>
		<!-- 打印 -->
		<div class="order-print" id="realPrintDiv">
			<div class="print-order-title ta-c" style="font-size: 20px">JFUN转角 订单</div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" style="line-height:14px">
				<tr>
					<td>配送店主：${map.orderInfo.supplierNam}</td>
					<td>店铺手机：${map.orderInfo.mobile}</td>
					<td>配送时间：
						<fmt:formatDate value="${map.orderInfo.sendDate}" type="date" />
					</td>
				</tr>
				<tr>
					<td>店铺名称：${map.orderInfo.storeName}</td>
					<td>订单编号：${map.orderInfo.orderId}</td>
					<td>支付方式：
						<c:choose>
							<c:when test="${map.orderInfo.supportmetho==1 }">
								银行卡支付
							</c:when>
							<c:when test="${map.orderInfo.supportmetho==2 }">
								货到付款
							</c:when>
							<c:when test="${map.orderInfo.supportmetho==3 }">
								支付宝支付
							</c:when>
							<c:when test="${map.orderInfo.supportmetho==4 }">
								微信支付
							</c:when>
							<c:when test="${map.orderInfo.supportmetho==5 }">
								钱包支付
							</c:when>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td>下单时间：
						<fmt:formatDate value="${map.orderInfo.addTime}" pattern="yyyy-MM-dd HH:mm:ss" />
					</td>
					<td colspan="2">店铺地址：${map.orderInfo.address}
						<c:if test="${!empty map.orderInfo.addressRemark}">
							(${map.orderInfo.addressRemark})
						</c:if>
					</td>
				</tr>
				<tr>
					<td>客户订单：${map.linkOrderNo}</td>
					<td colspan="2">备注信息： ${map.orderInfo.userRemark}</td>
				</tr>
			</table>
			<table class="table-print mt-small" border="1">
				<tr>
					<th>序号</th>
					<th>商品分类</th>
					<th>商品名称</th>
					<th>规格</th>
					<th>数量</th>
					<th>单价（元）</th>
					<th>金额（元）</th>
				</tr>

				<c:forEach var="order" items="${map.orderDetail }" varStatus="var">
					<c:set value="${var.count}" var="count" />
					<tr>
						<td>${count}</td>
						<td>${order.cateName }</td>
						<td class="ta-l" style="padding: 2px 20px;">${order.name }
							<c:if test="${!empty order.youHui}">
								<br/> (${order.youHui})
							</c:if>
							<c:if test="${order.goodsType==1}">
								(平台赠品)
							</c:if>
							<c:if test="${order.goodsType==2}">
								(批发商赠品)
							</c:if>
						</td>
						<td>${order.spec }</td>
						<td>${order.quantity }</td>
						<td>${order.price }</td>
						<td>${order.totalPrice }</td>
					</tr>
				</c:forEach>
				<c:set var="ownerPrice" value="0" />
				<c:forEach var="ele" items="${ownerDetail }" varStatus="eleStat">
					<c:choose>
						<c:when test="${ele.goodsType == 1}">
							<c:set value="${count + 1}" var="count" />
							<tr>
								<td>${count}</td>
								<td>${ele.cateName }</td>
								<td class="ta-l" style="padding: 2px 20px;">${ele.name }(兑换)</td>
								<td>${ele.spec }</td>
								<td>${ele.quantity }</td>
								<td>0</td>
								<td>0</td>
							</tr>
						</c:when>
						<c:when test="${ele.price != null || ele.price != ''}">
							<c:set var="ownerPrice" value="${ele.price + ownerPrice}" />
						</c:when>
					</c:choose>
				</c:forEach>
			</table>
			<div style="line-height: 14px">
				<div>
					金额合计：${map.total }
					<span class="ml-default">满立减补贴：${map.orderInfo.rebate }</span>
					<span class="ml-default">优惠券补贴：${map.orderInfo.coupon }</span>
					<span class="ml-default">运费：${map.orderInfo.freight }</span>
					<c:if test="${ownerPrice != 0}"><span class="ml-default">扣减金额：${ownerPrice}(兑换)</span></c:if>

				</div>
				<div>
					<span>用户实付（含运费）：
                        <span id="bigmoneyPrint">${map.total-map.orderInfo.rebate-ownerPrice-map.orderInfo.coupon+map.orderInfo.freight}</span>
					<span class="ml-default">小写：${map.total-map.orderInfo.rebate-ownerPrice-map.orderInfo.coupon+map.orderInfo.freight}元</span>
					<span class="ml-default">总数量：${map.ordernum }</span>
					</span>
				</div>
				<div>
					<span>捡货人：</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<span>验货人：</span>
				</div>
			</div>
		</div>
		<script>
			$(function() {
				$("#addressRemark").on("blur", function() {
					var storeId = "${map.orderInfo.storeId}";
					var addressRemark = $("#addressRemark").val();
					$.post("${root}/scms/orderctl/addAddressRemark.do", {
						storeId: storeId,
						addressRemark: addressRemark
					}, function(data) {
						if(data.success) {
							location.reload();
						}
					}, 'json');
				})

				$("#printBillinfo").on('click', function() {
					$("#realPrintDiv").show();
					window.print();
					$("#realPrintDiv").hide();
					if(confirm("是否已打印成功?") == true) {
						return true;
					}
					return false;
				});
				$("#bigmoney").html(ChinaCost($("#bigmoney").html()));
				$("#bigmoneyPrint").html(ChinaCost($("#bigmoneyPrint").html()));

				var map = {};
				var array = new Array();
				$(".J_edit").on("click", function() {
					var input = $(this).prev();
					var id = input.attr("addressRemark");
					var real = input.prev();
					input.show();
					input.focus();
					input.on("blur", function() {
						$(this).hide();
						real.show();
					});
					input.change(function() {
						array.push(id);
						real.text($(this).val());
						map[id] = $(this).val();
					});
				});
			});
		</script>
	</body>

</html>
