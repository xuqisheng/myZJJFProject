<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>商品打印单</title>
    <%@ include file="../../common/head.jsp"%>
    <script src="${root}/resources/js/china-cost.js"></script>
</head>
<style type="text/css">
    .todo{
        float: left;
    }
    ul,li{
        list-style: none;margin-bottom: 18px;
    }

    .lr li{
        float: left;width: 33%;margin-bottom: 0;
    }
    .table-print td{
        line-height: 20px;
    }
    .tableOne td{
    	line-height: 16px;
    }
</style>
<body>
<!-- 打印 -->
<div class="print-order-title ta-c mb-default" style="font-size: 20px">JFUN转角 订单</div>
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="line-height:14px" class="mb-default tableOne">
    <tr>
        <td>配送店主：${map.orderInfo.supplierNam}</td>
        <td>店铺手机：${map.orderInfo.mobile}</td>
        <td>配送时间：
            <fmt:formatDate value="${map.orderInfo.sendDate}" type="date" />
        </td>
    </tr>
    <tr>
        <td>店铺名称：${map.orderInfo.storeName}</td>
        <td>出库单号：${info.orderId}</td>
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
        <%--<td>销售单号：${map.orderInfo.orderId}</td>--%>
        <td>客户订单：${map.linkOrderNo}</td>
        <td colspan="2">备注信息： ${map.orderInfo.userRemark}</td>
    </tr>
</table>
<table class="table-print mb-default" border="1">
    <tr>
        <th>序号</th>
        <th>商品分类</th>
        <th>商品名称</th>
        <th>规格</th>
        <th>数量</th>
        <th>单价（元）</th>
        <th>金额（元）</th>
        <%--<th>备注</th>--%>
    </tr>
    <c:set var="quantity" value="0" />
    <c:set var="totMoney" value="0" />
    <c:forEach var="order" items="${map.orderDetail }" varStatus="var">
        <c:set value="${quantity + order.quantity}" var="quantity" />
        <c:set value="${totMoney + order.totalPrice}" var="totMoney" />
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
            <%--<td>${order.remark }</td>--%>
        </tr>
    </c:forEach>
    <c:set var="ownerPrice" value="0" />
    <c:forEach var="ele" items="${ownerDetail }" varStatus="eleStat">
        <c:choose>
            <c:when test="${ele.goodsType == 1}">
            </c:when>
            <c:when test="${ele.price != null || ele.price != ''}">
                <c:set var="ownerPrice" value="${ele.price + ownerPrice}" />
            </c:when>
        </c:choose>
    </c:forEach>
</table>
<div style="line-height: 14px">
    <div>
        	金额合计：${totMoney }
        <span class="ml-default mr-default">满立减补贴：${map.orderInfo.rebate }</span>
        <span class="ml-default mr-default">优惠券补贴：${map.orderInfo.coupon }</span>
        <span class="ml-default mr-default">运费：${map.orderInfo.freight }</span>
        <c:if test="${ownerPrice != 0}"><span class="ml-default">扣减金额：${ownerPrice}(兑换)</span></c:if>

    </div>
    <div>
					<span>用户实付（含运费）：
                        <span id="bigmoneyPrint" class="mr-default">${info.itemPrice}</span>
					<span class="ml-default mr-default">小写：${info.itemPrice}元</span>
					<span class="ml-default">总数量：${quantity }</span>
					</span>
    </div>
    <div>
        <span>捡货人：</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <span>验货人：</span>
    </div>
</div>
<script>
    $(function() {
        $("#bigmoneyPrint").html(ChinaCost($("#bigmoneyPrint").html()));
        window.print();
        // chrome新窗口立即关闭处理
        setTimeout(function() {
            window.close();
        }, 500);
    });
</script>
</body>
</html>
