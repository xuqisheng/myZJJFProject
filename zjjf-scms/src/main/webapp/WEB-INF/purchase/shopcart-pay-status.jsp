<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>快销宝 - 支付状态</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <%@ include file="../common/purchase-head.jsp"%>
    <link rel="stylesheet" href="${root}/resources/purchase/css/shopcart-pay-status.css">
</head>
<body>
<%@ include file="../common/purchase-nav.jsp"%>
<header>
    <a href="${root}/scms/procurementcenter/listPage.do"><div class="logo"></div></a>
    <div class="search-box">
        <input type="text" placeholder="搜搜您需要的商品" class="search-text">
        <input type="button" value="搜索" class="search-button">
    </div>
</header>
<main class="w clearfix">
    <section class="pay-status">
    <c:choose>
      <c:when test="${status}">
        <div class="info">
            <img src="${root}/resources/purchase/images/ok.png" alt=""> &nbsp;&nbsp;
            您已成功付款 <span class="main-color">${outOfPrice}</span> 元！
        </div>
        <div class="tips">
            您可以 &nbsp;&nbsp;
            <a href="${root}/scms/scOrder/scorderdetail.do?orderId=${orderId}">查看订单</a> &nbsp;&nbsp;&nbsp;&nbsp;
            <a href="${root}/scms/procurementcenter/listPage.do">继续购物</a>
        </div>
     </c:when>
     <c:otherwise>
        <div class="info">
            <img src="${root}/resources/purchase/images/warn.png" alt=""> &nbsp;&nbsp;
            抱歉，您的付款失败！
        </div>
       </c:otherwise>
      </c:choose>
        <div class="tips">
            您可以去
            <a href="${root}/scms/procurementcenter/listPage.do">采购中心</a>
            逛逛
        </div>
        <!-- <hr class="hr">
        <b>特别提示：</b>您购买的商品，将会由以下品牌分别送达，敬请留意！
        <ul class="ul">
            <li>蒙牛</li>
            <li>王老吉</li>
        </ul> -->
    </section>
    <section class="me">
        <img src="${root}/resources/purchase/images/me.gif" alt="">
    </section>
</main>
<%@ include file="../common/purchase-footer.jsp"%>
</body>
</html>