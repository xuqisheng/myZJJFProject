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
</head>
<body>
<h3 class="ta-c" style="color:#333;font-size: 20px">JFUN转角&nbsp;入库单</h3>
<table border="0" cellpadding="0" cellspacing="0" width="100%" style="line-height: 16px;font-size:12px">
    <tbody>
    <tr>
        <td>采购单编号：${info.orderId}</td>
        <td>仓库：${info.whName}</td>
        <td>地址：${warehouse.address}</td>
    </tr>
    <tr>
                <td>入库单编号：${lists.orderId}</td>
                <td>供应商：${info.managerName}</td>
                <td>制单时间：<fmt:formatDate value="${lists.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
    </tr>
    </tbody>
</table>
<table class="table-print mt-small">
    <thead>
    <tr>
        <th>序号</th>
        <th>箱码</th>
        <th>商品条形码</th>
        <th>商品名称</th>
        <th>规格</th>
        <th>单位</th>
        <th>单价</th>
        <th>采购量</th>
        <th>入库量</th>
        <th>小计</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${lists.details}" var="ele" varStatus="eleStat">
        <tr>
            <td>${eleStat.count}</td>
            <td>${ele.barCode}</td>
            <td>${ele.mdseId}</td>
            <td>${ele.name}</td>
            <td>${ele.spec}</td>
            <td>${ele.pkg}</td>
            <td>￥${ele.areaPrice}</td>
            <td>${ele.quantity}</td>
            <td>${ele.operateStock}</td>
            <td>￥${ele.totalPrice}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<table border="0" cellpadding="0" cellspacing="0" width="100%" class="mt-small" style="line-height: 16px;font-size:12px">
    <tbody>
    <tr>
        <td>客服热线：400-664-3833</td>
        <td class="ta-r">
            总数量：${lists.itemQuantity}箱
            <span class="ml-default">总计：￥${lists.itemPrice}</span>
        </td>
    </tr>
    <tr style="margin-top: 10px;">
        <td colspan="2" class="ta-r">
                供应商签名：<span class="label"></span>
                收货人签名（盖章）：<span class="label"></span>
        </td>
    </tr>
    </tbody>
</table>
<script>
    $(function() {
        window.print();
        setTimeout(function() {
            window.close();
        }, 500);
    });
</script>
</body>
</html>
