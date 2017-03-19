<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>商品库管理 - 出库管理详情</title>
    <%@ include file="../../common/head.jsp"%>
    <style type="text/css">
        .info-cont {
            padding: 0 12px;
            width: 320px;
            height: 34px;
            line-height: 34px;
            display: inline-block;
            vertical-align: middle;
            overflow: hidden;
        }
    </style>
</head>
<body>
<div class="wrap-bd no-print">
    <div class="wrap-bd bg">
        <b class="mr-default">订单编号：${map.orderInfo.orderId} </b>
        <b class="ml-default">客户订单号：${map.linkOrderNo}</b>
    </div>
    <div class="title mt-default">
        收货信息
    </div>
    <div class="wrap-bd bg">
        <div>
            <label class="label">配送店主：</label><span class="info-cont">${map.orderInfo.supplierNam }</span>
            <label class="label">配送时间：</label><span class="info-cont"><fmt:formatDate value="${map.orderInfo.sendDate}" type="date" /></span>
        </div>
        <div>
            <label class="label">手机号：   </label><span class="info-cont">${map.orderInfo.mobile }</span>
            <label class="label">支付方式：</label>
            <span class="info-cont">
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
            </span>
        </div>
        <div>
            <label class="label">店名：</label><span class="info-cont">${map.orderInfo.storeName }</span>
            <label class="label">固定电话：</label><span class="info-cont">${map.orderInfo.userTel }</span>
        </div>
        <div>
            <label class="label">店面地址：</label><span class="info-cont">${map.orderInfo.address }</span>
            <label class="label">备注：</label><span class="info-cont">${map.orderInfo.userRemark }</span>
        </div>
    </div>
    <table class="table-list mt-default">
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
        </tbody>
        <tfoot>
        <tr>
            <td>合计</td>
            <td colspan="6" class="ta-r">
                数量合计：${map.ordernum }
                <span class="ml-default">金额合计：${map.total }</span>
            </td>
        </tr>
        </tfoot>
    </table>
    <div class="mt-default ta-c">
        <c:if test="${map.orderInfo.status==2}">
            <a href="${root}/scms/ERPOrderInfo/updateStatusATime.do?status=3&orderid=${map.orderInfo.orderId}" onclick="javascript:location.href='${root}/scms/ERPOrderInfo/updateStatusATime.do?status=3&orderid=${map.orderInfo.orderId}'"><button class="button-save">提单</button></a>
        </c:if>
        <c:if test="${map.orderInfo.status==3}">
            <a href="${root}/scms/ERPOrderInfo/updateStatusATime.do?status=4&orderid=${map.orderInfo.orderId}" onclick="javascript:location.href='${root}/scms/ERPOrderInfo/updateStatusATime.do?status=4&orderid=${map.orderInfo.orderId}'"><button id="printBillinfo" class="button-save">打印</button></a>
        </c:if>
        <c:if test="${map.orderInfo.status > 3}">
            <input type="button" id="printBillinfo" class="button-save" value="重新打印"/>
        </c:if>
        <input type="button" name="" value="取消" class="button-cancel" onclick="window.close()">
    </div>
</div>
<!------打印-------->
<div id="realPrintDiv">
    <h3 class="ta-c" style="color:#333;font-size: 20px">JFUN转角&nbsp;送货单</h3>
    <table border="0" cellpadding="0" cellspacing="0" width="100%" style="line-height: 16px;font-size:12px">
        <tbody>
        <tr>
            <td>订单编号：${map.orderInfo.orderId}</td>
            <td>配送时间：<fmt:formatDate value="${map.orderInfo.sendDate}" type="date" /></td>
            <td>
                <c:choose>
                    <c:when test="${ERP_WAREHOUSE_SESSION_KEY == null}">
                        <c:forEach var="warehouse" items="${map.warehouses}" varStatus="eleStat">
                            <c:if test="${eleStat.first}">仓库：${warehouse.name}</c:if>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        仓库：${ERP_WAREHOUSE_SESSION_KEY.name}
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td>收货人：${map.orderInfo.storeName }</td>
            <td>地址：${map.orderInfo.address }</td>
            <td>电话：${map.orderInfo.mobile }</td>
        </tr>
        </tbody>
    </table>
    <table class="table-print mt-small">
        <thead>
        <tr>
            <th>序号</th>
            <th>商品条码</th>
            <th>商品名称</th>
            <th>规格</th>
            <th>数量</th>
            <th>单价（元）</th>
            <th>金额（元）</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="order" items="${map.orderDetail }" varStatus="eleStat">
            <tr>
                <td>${eleStat.count }</td>
                <td>${order.barCode }</td>
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
                <td>${order.spec }</td>
                <td>${order.quantity }</td>
                <td>￥${order.price }</td>
                <td>￥${order.totalPrice }</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <table border="0" cellpadding="0" cellspacing="0" width="100%" class="mt-small" style="line-height: 16px;font-size:12px">
        <tbody>
        <tr>
            <td>备注：${map.orderInfo.userRemark }</td>
            <td class="ta-r">
                总数量：${map.ordernum }箱
                <span class="ml-default">总计：￥${map.total }</span>
            </td>
        </tr>
        <tr>
            <td>客服热线：400-664-3833</td>
            <td class="ta-r">运费：${map.orderInfo.freight }</td>
        </tr>
        <tr>
            <td colspan="2" class="ta-r">
                优惠：-￥${map.orderInfo.rebate + map.orderInfo.coupon}
            </td>
        </tr>
        <tr>
            <td colspan="2" class="ta-r">

                <span class="mr-default">付款状态：${map.orderInfo.supportStatus == 1 ? '已付款' : '未付款'}</span>
                <c:if test="${map.orderInfo.supportmetho==2 }">
                    <span class="mr-default" style="border:1px solid #333;padding: 2px 5px">
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
                    </span>
                </c:if>
                总金额：￥${map.total-map.orderInfo.rebate-map.orderInfo.coupon+map.orderInfo.freight}元
            </td>
        </tr>
        <tr>
            <td colspan="2" class="ta-r">
                收货人签名：<span class="label"></span>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<script>
    $(function() {
        $("#realPrintDiv").hide();
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
    });
</script>
</body>
</html>
