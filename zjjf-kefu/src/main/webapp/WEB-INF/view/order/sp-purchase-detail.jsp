<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>批发商采购单详情</title>
	<%@ include file="../common/head.jsp"%>
</head>
<body class="wrap-bd">
<div class="mb-default">
    <a class="crumb" href="${root}/scms/purchase/listPage.do">批发商采购订单</a><a class="crumb crumb-active">明细</a>
</div>
<div class="wrap-bd bg table-border">
    <dl>
        <dt><b>采购单信息</b></dt>
        <dd><p>订单号：${vo.orderId}</p></dd>
        <dd><p>提交时间：<fmt:formatDate value="${vo.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p></dd>
        <dd><p>状态：<c:choose>
        					<c:when test="${vo.status==1}">已下单</c:when>
        					<c:when test="${vo.status==2}">未配送</c:when>
        					<c:when test="${vo.status==3}">配送中</c:when>
        					<c:when test="${vo.status==4}">已完成</c:when>
        					<c:when test="${vo.status==5}">已取消</c:when>
        					</c:choose></p></dd>
          <dd><p>配送方式：<c:choose>
        					<c:when test="${vo.ordertype==1}">货到付款</c:when>
        					<c:when test="${vo.ordertype==2}">自提</c:when>
        					<c:otherwise>送货上门</c:otherwise>
        					</c:choose></p></dd>
    </dl>
    <dl>
        <dt><b>收货人</b></dt>
        <dd>
            <p>
               
                <span class="mr-default ml-default">${vo.consignee}</span>
                <span class="mr-default ml-default">${vo.mobile}</span>
                 <span class="mr-default ml-default">${vo.address}</span>
            </p>
        </dd>
    </dl>
    <div class="mb-small clearfix">
        <div class="fl">
            <b>采购清单</b>
        </div>
        <div class="fr">
        	实付总额：<b>￥${vo.orderPrice}</b>
        </div>
    </div>
    <table class="table-list table-border">
        <thead class="table-thead">
        <tr>
            <td width="320">商品</td>
            <td>商品条形码</td>
            <td>单位</td>
            <td>价格</td>
            <td>数量</td>
            <td>小计</td>
        </tr>
        </thead>
        <tbody class="table-tbody">
        	 <c:forEach items="${list}" varStatus="i" var="item" > 
        	 <tr>
		     	<td class="ta-l">
                 	<c:choose>
	            		<c:when test="${item.imgS != '' && item.imgS != null && detail.imgS != 'null'}">
	                		<img class="fl" src="${USER_FASTFDSPREURL }${item.imgS}" width="90" height="90" alt="缩略图" onerror="javascript:this.src='${USER_DEFAULTIMG_URL}'"/>
	                	</c:when>
	                	<c:otherwise>
	                		<img class="fl" src="${USER_DEFAULTIMG_URL}" width="90" height="90" alt="缩略图"/>
	                	</c:otherwise>
	            	</c:choose>
	                <div class="fl ml-default mt-small" style="width:200px;height:100px;line-height:16px;overflow:hidden">
	                    ${item.name}<br>
						规格：${item.spec}
	                </div>
	            </td>
	            <td>${item.mdseId}</td>
	            <td>${item.pkg}</td>
	            <td>￥${item.price}</td>
	            <td>${item.quantity}</td>
	            <td>￥${item.totalPrice}</td>
			</tr>
		    </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>