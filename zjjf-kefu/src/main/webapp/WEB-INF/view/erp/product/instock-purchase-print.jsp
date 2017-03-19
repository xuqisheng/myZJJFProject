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
    <h3 class="ta-c" style="color:#333;font-size: 20px">JFUN转角&nbsp;采购单</h3>
    <table border="0" cellpadding="0" cellspacing="0" width="100%" style="line-height: 16px;font-size:12px">
        <tbody>
        <tr>
            <td>采购单编号：${info.orderId}</td>
            <td>供应商：${info.managerName}</td>
            <td>供应商编号：${info.managerCode}</td>
        </tr>
        <tr>
            <td>制单人：${SUPPLY_SESSION_KEY.supplierName}</td>
            <td>制单时间：<fmt:formatDate value="${info.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td>供应商合作方式：${cooperationName}</td>
        </tr>
        <tr>
            <td>仓库：${info.whName}</td>
            <td>仓库地址：${info.whAddress}</td>
            <td>交货时间：<fmt:formatDate value="${info.gaveTime}" pattern="yyyy年MM月dd日"/></td>
        </tr>
        </tbody>
    </table>
    <table class="table-print mt-small mb-small">
        <thead>
        <tr>
            <th>序号</th>
            <th>箱码</th>
            <th>商品条形码</th>
            <th>商品名称</th>
            <th>规格</th>
            <th>单位</th>
            <th>单价</th>
            <th>数量</th>
            <th>小计</th>
        </tr>
        </thead>
        <tbody>
            <c:set var="totalQuantity" value="0"></c:set>
            <c:set var="totalAreaPrice" value="0"></c:set>
            <c:forEach items="${info.details}" var="ele" varStatus="eleStat">
                <tr>
                    <c:set var="totalQuantity" value="${totalQuantity + ele.quantity}"></c:set>
                    <c:set var="totalAreaPrice" value="${totalAreaPrice + (ele.quantity * ele.areaPrice)}"></c:set>
                    <td>${eleStat.count}</td>
                    <td>${ele.wuliu}</td>
                    <td>${ele.mdseId}</td>
                    <td>${ele.name}</td>
                    <td>${ele.spec}</td>
                    <td>${ele.pkg}</td>
                    <td>${ele.areaPrice}</td>
                    <th>${ele.quantity}</th>
                    <th>${ele.quantity * ele.areaPrice}</th>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <table border="0" cellpadding="0" cellspacing="0" width="100%" class="mt-small" style="line-height:16px;font-size:12px">
        <tbody>
        <tr>
            <td>备注：${info.remark}</td>
            <td class="ta-r">
                总数量：${totalQuantity}箱
                <span class="ml-default">总计：￥${totalAreaPrice}</span>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                仓库预约电话：${info.whMobile}
                <span class="ml-default">客服热线：400-664-3833</span>
            </td>
        </tr>
        </tbody>
    </table>
    <script>
        $(function() {
            window.print();
            // chrome新窗口立即关闭处理
            setTimeout(function() {
                window.close();
            }, 500);
        });
    </script>
</body>
</html>
