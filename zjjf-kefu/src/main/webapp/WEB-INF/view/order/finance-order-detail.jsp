<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>财务管理</title>
    <%@ include file="../common/head.jsp"%>
</head>
<body class="wrap-bd">
<input type="hidden" id="send-order-id-ss" name="orderid" value="${map.orderInfo.orderId }" />
<div>
    <a href="${root}/Customer/Finance/FinanceList.do?s=1" class="crumb">财务管理</a><a href="javascript:void(null)" onclick="history.go(-1);" class="crumb">批发商订单</a><a class="crumb crumb-active">订单详情</a>
</div>
<div class="wrap-bd bg mt-default">
	<span>订单编号：${map.orderInfo.orderId }</span>
	<c:if test="${send_agin==1 }">
	    <span>原批发商手机号为：${map.orderInfo.suppliertel}</span>
	</c:if>
	<c:if test="${map.orderInfo.status==1||send_agin==1 }">
		<div class="dis-ib">
			<span class="txt-warn" id="su-mobile"></span>
			<input type="hidden" id="send-order-id" name="orderid" value="${map.orderInfo.orderId }" />
			<select id="send-mobile"  name="supplierTel">
			    <option value="-1">请选择批发商手机号</option>
				<c:forEach var="s" items="${supplierList }">
				    <option value="${s.mobile }">${s.mobile }&nbsp;${s.suppliername }</option>
				</c:forEach>
			</select>
		</div>
	</c:if>
	<c:if test="${map.orderInfo.status!=1&&empty send_agin }">
		<span class="ml-default">批发商手机号：${map.orderInfo.supplierTel }</span>
	</c:if>
</div>
<div class="mt-default">
	<b class="txt-info">收货信息 </b>
	<div class="wrap-bd bg mt-small">
		<div>
		    <label class="label">店主：</label>${map.orderInfo.userName }
		</div>
		<p>
		    <label class="label">手机号：</label>${map.orderInfo.mobile }
		</p>
		<p>
		    <label class="label">店名：</label>${map.orderInfo.storeName }
		</p>
		<p>
		    <label class="label">店面地址：</label>${map.orderInfo.address }
		</p>
		<p>
		    <label class="label"> 配送时间：</label><fmt:formatDate value="${map.orderInfo.sendDate}" type="date" />
		</p>
		<p>
		    <label class="label">支付方式：</label>
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
			</c:choose>
		</p>
		<p>
		     <label class="label"> 固定电话：</label>${map.orderInfo.userTel }
		</p>
		<div>
			 <label class="label">备注：</label>${map.orderInfo.userRemark }
		</div>
	</div>
</div>
<div class="mt-default">
    <b class="txt-info">货物清单</b>
	<table class="table-list mt-small">
		<tr>
			<th>条码</th>
			<th>商品名称</th>
			<th>规格</th>
			<th>数量</th>
			<th>单价（元）</th>
			<th>金额（元）</th>
			<th>费用单价</th>
			<th>费用金额</th>
			<c:if test="${!empty  service}">
				<th>费用率</th>
			</c:if>
		</tr>
		<c:forEach var="order" items="${map.orderDetail }">
			<tr>
				<td>${order.barCode }</td>
				<td>${order.brand }${order.name }</td>
				<td>${order.spec }</td>
				<td>${order.quantity }</td>
				<td>${order.price }</td>
				<td>${order.totalPrice }</td>
				<td>${order.fee}</td>
				<td class="a">${order.fee*order.quantity}</td>
				<c:if test="${!empty  service}">
					<td>${(order.fee*order.quantity)/order.totalPrice}</td>
				</c:if>
			</tr>
		</c:forEach>
		<tfoot>
		<tr>
			<td colspan="3">金额合计：<span class="txt-warn" id="bigmoney">${map.total }</span></td>
			<td>总数量：${map.ordernum }</td>
			<td colspan="2">小写：<span class="txt-warn">${map.total }</span></td>
			<td colspan="3">费用金额：<span class="txt-warn" id="b">${map.orderInfo.zfee}</span></td>
		</tr>
		</tfoot>
	</table>
</div>
<div class="mt-default">
    <b class="txt-info">订单跟踪</b>
	<div class="wrap-bd bg mt-small">
		<div class="mb-small txt-success">提交订单时间：<fmt:formatDate value="${map.orderInfo.addTime}" type="both" /></div>
		<div class="mb-small <c:if test="${!empty map.orderInfo.gaveTime}">txt-success</c:if>">客服派单时间：<fmt:formatDate value="${map.orderInfo.gaveTime}" type="both" /></div>
		<div class="mb-small <c:if test="${!empty map.orderInfo.getOrderTime}">txt-success</c:if>">批发商提单时间：<fmt:formatDate value="${map.orderInfo.getOrderTime}" type="both" /></div>
		<div class="mb-small <c:if test="${!empty map.orderInfo.printTime}">txt-success</c:if>">批发商打印时间：<fmt:formatDate value="${map.orderInfo.printTime}" type="both" /></div>
		<div class="mb-small <c:if test="${!empty map.orderInfo.ackTime}">txt-success</c:if>">货物送达时间：<fmt:formatDate value="${map.orderInfo.ackTime}" type="both" /></div>
		<div class="mb-small txt-success">转角客服审核时间：<fmt:formatDate value="${map.orderInfo.kfCheckTime}" type="both" /></div>
		<div class="mb-small txt-success">转角客服审核备注：${map.orderInfo.kfnote}</div>
		<div class="mb-small txt-success">供应商审核时间：<fmt:formatDate value="${map.orderInfo.spCheckTime}" type="both" /></div>
		<div class="mb-small txt-success">供应商审核备注：${map.orderInfo.spRemark}</div>
		<div class="txt-success">转角财务审核备注：${map.orderInfo.acRemark}</div>
	</div>
    <div class="mt-default">
	    <c:if test="${!empty  service}">
	        <c:if test="${map.orderInfo.kfStatus==1}">
	        <a href="#" onclick="auditSpOrderInfo('${map.orderInfo.id}',2,'kf');"><button class="button button-ok">审核</button></a>
	        </c:if>
	        <c:if test="${map.orderInfo.kfStatus==2 && spOrderInfo.acStatus <3}">
	        <a href="#" onclick="auditSpOrderInfo('${map.orderInfo.id}',1,'kf');"><button class="button button-cancel">撤销审核</button></a>
	        </c:if>
	    </c:if>
	    <c:if test="${!empty  supplier}">
	        <c:if test="${map.orderInfo.spStatus==1}">
	        <a href="#" onclick="auditSpOrderInfo('${map.orderInfo.id}',2,'sp');"><button class="button button-ok">审核</button></a>
	        </c:if>
	        <c:if test="${map.orderInfo.spStatus==2 && spOrderInfo.acStatus <3}">
	        <a href="#" onclick="auditSpOrderInfo('${map.orderInfo.id}',1,'sp');"><button class="button button-cancel">撤销审核</button></a>
	        </c:if>
	    </c:if>
    </div>
</div>
<script>
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
                    $("#bg-overAll").removeClass("hidden");
                    $("#btn-send-cancel").on("click", function() {
                        $("#send-order-dialog").addClass("hidden");
                        $("#bg-overAll").addClass("hidden");
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


function auditSpOrderInfo(val,status,type){
	var  remark=prompt("您可以输入备注","");
	if(remark == null){
		return ;
	}
	var  date={};
	if(remark == ""){
		date={strIds:val,status:status,type:type};
	}else{
		date={strIds:val,status:status,type:type,note:remark};
	}
	$.ajax({
		type : "POST",
		url : "${root}/Customer/Finance/auditSpOrderInfo.do",
		async : true,
		data :date,
		success: function(da){
			alert(da.message);
			location.reload();
		},
		error: function(da){
			alert(da.message);
		}
	});
}
</script>
</body>
</html>