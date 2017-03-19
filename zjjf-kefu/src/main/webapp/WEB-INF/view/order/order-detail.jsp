<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>转角店宝</title>
    <%@ include file="../common/head.jsp"%>
    <script src="${root }/resources/js/comm.js"></script>
</head>
<body class="wrap-bd">
<input type="hidden" id="send-order-id-ss" name="orderid" value="${map.orderInfo.orderId }" />
<div class="mb-default">
    <a class="crumb" href="${root}/Customer/SpOrderInfo/GetSpOrderInfos.do?status=1">商铺订单</a><a class="crumb crumb-active">订单详情</a>
</div>
<div class="wrap-bd bg">
	<div>
	           订单编号：${map.orderInfo.orderId } &nbsp;&nbsp;&nbsp;
		<c:if test="${!empty map.linkOrderNo}">
			客户订单号：${map.linkOrderNo}
		</c:if>
	</div>
	<div class="mt-small">
		<c:if test="${send_agin==1 }">
			<span class="mr-default">原批发商手机号为：${map.orderInfo.supplierTel}</span>
		</c:if>
		<c:if test="${map.orderInfo.status!=1 && empty send_agin}">
			<span class="mr-default">批发商手机号:${map.orderInfo.supplierTel }</span>
		</c:if>
		<c:if test="${map.orderInfo.level == 2}">
			<c:if test="${map.orderInfo.status==1||send_agin==1 }">
                <div class="dis-ib">
                    <span class="txt-warn" id="su-mobile"></span>
                    <input type="hidden" id="send-order-id" name="orderid" value="${map.orderInfo.orderId }" /> 
                    <select id="send-mobile"  name="supplierTel" class="select">
                            <option value="-1">请选择批发商</option>
                            <c:forEach var="s" items="${supplierList }">
                                <option value="${s.mobile }">${s.mobile }&nbsp;${s.supplierName }</option>
                            </c:forEach>
                    </select>
                    <button id="send-order-btn" type="button" class="button button-default">派单</button>
                </div>
			</c:if>
		</c:if>
    </div>
</div>
<div class="mt-default">
    <b class="txt-info">收货信息</b>
	<div class="wrap-bd bg mt-small">
		<div>
			<label class="label">店主：</label>
			<span>${map.orderInfo.userName }</span>
		</div>
		<p>
            <label class="label">手机号：</label>
            <span>${map.orderInfo.mobile }</span>
		</p>
		<p>
            <label class="label">店名：</label>
		    <span class="trade-imfor-dd">${map.orderInfo.storeName }</span>
		</p>
		<p>
            <label class="label">店面地址：</label>
			<span>${map.orderInfo.address }</span>
		</p>
		<p>
            <label class="label">配送时间：</label>
			<span><fmt:formatDate value="${map.orderInfo.sendDate}" type="date" /></span>
		</p>
		<p>
            <label class="label">支付方式：</label>
		    <span> 
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
		</p>
		<p>
			<span class="trade-imfor-dt">固定电话：</span>
			${map.orderInfo.userTel }
			<span class="trade-imfor-dd"></span>
		</p>
		<div>
		     <span class="trade-imfor-dt">备注：</span>
		     <span class="trade-imfor-dd">
			 ${map.orderInfo.userRemark }
		  </span>
		</div>
	</div>
</div>
<div class="mt-default">
    <b class="txt-info">货物清单</b>
	<table class="table-list mt-small">
	    <thead>
			<tr>
				<th>条码</th>
				<th>商品名称</th>
				<th>规格</th>
				<th>数量</th>
				<th>单价（元）</th>
				<th>金额（元）</th>
				<th></th> 
			</tr>
		</thead>
		<tbody>
		    <c:forEach var="order" items="${map.orderDetail }">
			<tr>
				<td>${order.barCode }</td>
				<td>${order.brand }${order.name }
					<c:if test="${!empty order.youHui}">
						(${order.youHui})
					</c:if>
				</td>
				<td>${order.spec }</td>
				<td>${order.quantity }</td>
				<td>${order.price }</td>
				<td>${order.totalPrice }</td>
				<td></td>
			</tr>
		    </c:forEach>
			<tr>
				<td>金额合计：<span class="txt-warn">${map.total }</span></td>
				<td>转角补贴：<span class="txt-warn">${map.orderInfo.rebate }</span></td>
				<td>优惠券补贴：<span class="txt-warn">${map.orderInfo.coupon }</span></td>
				<td>运费：<span class="txt-warn">${map.orderInfo.freight }</span></td>
				<td>用户实付（含运费）：<span class="txt-warn" id="bigmoney">${map.total-map.orderInfo.rebate-map.orderInfo.coupon+map.orderInfo.freight}</span></td>
				<td>小写：<span class="txt-warn">${map.total-map.orderInfo.rebate-map.orderInfo.coupon+map.orderInfo.freight}</span></td>
				<td>总数量：${map.ordernum }</td>
			</tr>
        </tbody>
	</table>
</div>
<div class="mt-default">
    <b class="txt-info">订单跟踪</b>
	<div class="wrap-bd bg mt-small">
		<div class="mb-small txt-success">提交订单时间：<fmt:formatDate value="${map.orderInfo.addTime}" type="both" /></div>
		<div class="mb-small <c:if test="${!empty map.orderInfo.supportTime}">txt-success</c:if>">在线支付时间：<fmt:formatDate value="${map.orderInfo.supportTime}" type="both" /></div>
		<div class="mb-small <c:if test="${!empty map.orderInfo.gaveTime}">txt-success</c:if>">客服派单时间：<fmt:formatDate value="${map.orderInfo.gaveTime}" type="both" /></div>
		<div class="mb-small <c:if test="${!empty map.orderInfo.getOrderTime}">txt-success</c:if>">批发商提单时间：<fmt:formatDate value="${map.orderInfo.getOrderTime}" type="both" /></div>
		<div class="mb-small <c:if test="${!empty map.orderInfo.printTime}">txt-success</c:if>">批发商打印时间：<fmt:formatDate value="${map.orderInfo.printTime}" type="both" /></div>
		<div class="<c:if test="${!empty map.orderInfo.ackTime}">txt-success</c:if>">货物送达时间：<fmt:formatDate value="${map.orderInfo.ackTime}" type="both" /></div>
	</div>
</div>
<div class="dialog hidden mt-default mb-small" id="send-order-dialog">
	<div class="dialog-head">派单确认</div>
	<div class="dialog-body" style="padding:20px 80px 0;">
		<b>您将把该订单推送给以下批发商</b>
		<p>
			<label class="label">手机号：</label>
			<span id="send-mobile-s"></span>
        <p>
        <p>
            <label class="label">供货商地址：</label>
			<span id="send-address-s">南山批发部</span>
		<p>
		<p>
            <label class="label">联系人姓名：</label>
			<span id="send-name-s"></span>
        </p>
        <p>
            <label class="label">供货区域：</label>
			<span id="send-address2-s"></span>
		</p>
		<p>
            <label class="label">供货商名称:</label>
			<span id="send-sname-s"></span>
		</p>
	</div>
    <div class="dialog-foot">
        <input type="button" id="btn-send-sure" class="dialog-button dialog-ok" value="确定">
        <input type="button" id="btn-send-cancel" class="dialog-button dialog-cancel" value="取消">
    </div>
</div>
<div id="bg-overAll" class="cover-all"></div>
<script>
	 $(function() {
		 dialogPosCenter('#send-order-dialog');
		 var dataPath = "/CornerV2/";
		 $(function() {
		     $("#bigmoney").html(ChinaCost($("#bigmoney").html()));
		     $("#send-order-btn").on("click", function() {
		         var mobile = $("#send-mobile").val();
		         if (mobile == -1) {
		             return;
		         }
		         if (mobile == "") {
		             $("#su-mobile").html("请输入正确的手机号");
		             return;
		         }
		         if (!/^1[3|4|5|8][0-9]\d{4,8}$/.test(mobile)) {
		             $("#su-mobile").html("请输入正确的手机号");
		             return;
		         }
		         $.ajax({
		             type : "get",
		             url : dataPath + "Customer/SpOrderInfo/getSupplier.do",
		             async : true,
		             data : {
		                 "mobile" : mobile
		             },
		             success : function(da) {
		                 if (!da.success) {
		                     $("#su-mobile").html(da.message);
		                 } else {
		                     $("#su-mobile").html("");
		                     $("#send-mobile-s").html(da.message.supplier.mobile);
		                     $("#send-address-s").html(da.message.supplier.supplieraddress);
		                     $("#send-name-s").html(da.message.supplier.managername);
		                     $("#send-address2-s").html(da.message.areaname);
		                     $("#send-sname-s").html(da.message.supplier.suppliername);
		                     $("#send-order-dialog").removeClass("hidden");
		                     $("#bg-overAll").show();
		                     $("#btn-send-cancel").on("click", function() {
		                         $("#send-order-dialog").addClass("hidden");
		                         $("#bg-overAll").hide();
		                     });
		                     $("#btn-send-sure").on("click", function() {
		                         $.ajax({
		                             type : "get",
		                             url : dataPath + "Customer/SpOrderInfo/sendGoods.do",
		                             async : true,
		                             data : {
		                                 "supplierTel" : $("#send-mobile-s").html(),
		                                 "orderid" : $("#send-order-id").val().trim()
		                             },
		                             success : function(da) {
		                                 if (da.success) {
		                                     $("#su-mobile").html("");
		                                     $("#send-order-dialog").addClass("hidden");
		                                     location.href = dataPath + "Customer/SpOrderInfo/GetSpOrderInfos.do?status=2";
		                                 } else {
		                                     $("#su-mobile").html(ta.message);
		                                 }
		                             },
		                             error : function(da) {
		                             }
		                         });
		                     });
		                 }
		             },
		             error : function(da) {
		             }
		         });

		     });

		     $("#exportExcel").on("click", function() {
		         console.log($("#send-order-id").val());
		         $.ajax({
		             type : "get",
		             url : dataPath + "Customer/SpOrderInfo/exportExcel.do",
		             async : true,
		             data : {
		                 "orderid":$("#send-order-id-ss").val()
		             },
		             success : function(data) {
		                 alert(data.message);
		             }
		         });
		     });
		 });
		 function ChinaCost(numberValue) {
		     var numberValue = new String(Math.round(numberValue * 100)); // 数字金额
		     var chineseValue = ""; // 转换后的汉字金额
		     var String1 = "零壹贰叁肆伍陆柒捌玖"; // 汉字数字
		     var String2 = "万仟佰拾亿仟佰拾万仟佰拾元角分"; // 对应单位
		     var len = numberValue.length; // numberValue 的字符串长度
		     var Ch1; // 数字的汉语读法
		     var Ch2; // 数字位的汉字读法
		     var nZero = 0; // 用来计算连续的零值的个数
		     var String3; // 指定位置的数值
		     if (len > 15) {
		         alert("超出计算范围");
		         return "";
		     }
		     if (numberValue == 0) {
		         chineseValue = "零元整";
		         return chineseValue;
		     }
		     String2 = String2.substr(String2.length - len, len); // 取出对应位数的STRING2的值
		     for (var i = 0; i < len; i++) {
		         String3 = parseInt(numberValue.substr(i, 1), 10); // 取出需转换的某一位的值
		         if (i != (len - 3) && i != (len - 7) && i != (len - 11) && i != (len - 15)) {
		             if (String3 == 0) {
		                 Ch1 = "";
		                 Ch2 = "";
		                 nZero = nZero + 1;
		             } else if (String3 != 0 && nZero != 0) {
		                 Ch1 = "零" + String1.substr(String3, 1);
		                 Ch2 = String2.substr(i, 1);
		                 nZero = 0;
		             } else {
		                 Ch1 = String1.substr(String3, 1);
		                 Ch2 = String2.substr(i, 1);
		                 nZero = 0;
		             }
		         } else { // 该位是万亿，亿，万，元位等关键位
		             if (String3 != 0 && nZero != 0) {
		                 Ch1 = "零" + String1.substr(String3, 1);
		                 Ch2 = String2.substr(i, 1);
		                 nZero = 0;
		             } else if (String3 != 0 && nZero == 0) {
		                 Ch1 = String1.substr(String3, 1);
		                 Ch2 = String2.substr(i, 1);
		                 nZero = 0;
		             } else if (String3 == 0 && nZero >= 3) {
		                 Ch1 = "";
		                 Ch2 = "";
		                 nZero = nZero + 1;
		             } else {
		                 Ch1 = "";
		                 Ch2 = String2.substr(i, 1);
		                 nZero = nZero + 1;
		             }
		             if (i == (len - 11) || i == (len - 3)) { // 如果该位是亿位或元位，则必须写上
		                 Ch2 = String2.substr(i, 1);
		             }
		         }
		         chineseValue = chineseValue + Ch1 + Ch2;
		     }
		     if (String3 == 0) { // 最后一位（分）为0时，加上“整”
		         chineseValue = chineseValue + "整";
		     }
		     return chineseValue;
		 }
	}); 
</script>
</body>
</html>