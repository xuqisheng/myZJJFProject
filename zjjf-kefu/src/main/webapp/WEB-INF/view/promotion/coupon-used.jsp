<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>优惠券使用情况</title>
    <%@ include file="../common/head.jsp"%>
</head>
<body>
<div class="wrap-bd">
    <div class="mt-small mb-default clearfix">
        <form action="${root}/Customer/voucher/getSpVoucherUsedLog.do" name="" method="post" class="fl">
            <input class="input input-search-text" type="text" name="keyStr" value="${keyStr}" placeholder="订单编号/手机号/店铺名称" />
            <input type="hidden" name="id" value="${id}">
            <input class="input input-search-button" value="搜索" type="submit" />
        </form>
        <a class="fr" href="${root}/Customer/voucher/toIndex.do">返回优惠券列表</a>
    </div>
    <table class="table-list table-border">
        <thead class="table-thead">
        <tr>
            <th>订单编号</th>
            <th>订单金额</th>
            <th>店铺名称</th>
            <th>店主名称</th>
            <th>手机号</th>
            <th>面值</th>
            <th>状态</th>
            <th>使用时间</th>
        </tr>
        </thead>
        <tbody class="table-tbody">
        <c:forEach var="storevo" items="${list}">
        <tr>
            <td>${storevo.ordId}</td>
            <td>￥${storevo.amount}</td>
            <td>${storevo.name}</td>
            <td>${storevo.contact}</td>
            <td>${storevo.mobile}</td>
            <td>￥${storevo.voucherMoney}</td>
            <td>
             <c:choose>
               <c:when test="${storevo.type==2}">
                                      已锁定
               </c:when>
               <c:otherwise>
                                     已使用
               </c:otherwise>             
             </c:choose>
            </td>
            <td><fmt:formatDate value="${storevo.dateTime}" pattern="yyyy-MM-dd"/></td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
	<c:if test="${fn:length(list)>0}">
		<%@ include file="../common/pagination-kk.jsp"%>
	</c:if>
</body>
</html>