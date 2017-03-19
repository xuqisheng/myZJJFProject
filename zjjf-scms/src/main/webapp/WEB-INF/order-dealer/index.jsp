<%@page import="com.corner.core.config.Constants"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh" >
<head>
	<meta http-equiv="refresh" content="20">
    <meta charset="UTF-8">
    <title>街坊店宝</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <%@ include file="../common/head.jsp"%>
    <script src="${root}/resources/vendor/kkpager-1.3/kkpager.min.js"></script>
    <link rel="stylesheet" type="text/css"	href="${root}/resources/vendor/kkpager-1.3/kkpager_orange_custom.css">
</head>
<body>
<div class="wrap-bd">
    <div class="op-section mt-small mb-small clearfix">
        <div class="fl"  id="orderNav">
            <a href="${root}/scms/scOrder/getScOrderInfoList.do?"><span class="pills <c:if test="${maOrderInfoMgRo.queryStatus == null}">pills-active</c:if>">全部订单</span></a>
            <a href="${root}/scms/scOrder/getScOrderInfoList.do?queryStatus=1"><span class="pills <c:if test="${maOrderInfoMgRo.queryStatus ==1}">pills-active</c:if>">未配送</span></a>
            <a href="${root}/scms/scOrder/getScOrderInfoList.do?queryStatus=2"><span class="pills <c:if test="${maOrderInfoMgRo.queryStatus == 2}">pills-active</c:if>">配送中</span></a>
            <a href="${root}/scms/scOrder/getScOrderInfoList.do?queryStatus=3"><span class="pills <c:if test="${maOrderInfoMgRo.queryStatus == 3}">pills-active</c:if>">已送达</span></a>
            <input type="hidden" id="queryStatus" value="${maOrderInfoMgRo.queryStatus}">
        </div>
    </div>
    <c:choose>
    	<c:when test="${fn:length(maOrderInfoMgVoList) == 0}">
    		 <div class="no-data"></div>
    	</c:when>
    	<c:otherwise>
    		<c:forEach var="maOrderInfoMgVo" items="${maOrderInfoMgVoList }" varStatus="v">
		    <div class="mt-small" id="mt-small">
			   <table class="table-list table-border">
			         <thead class="table-thead">
			         	<c:if test="${v.index==0 }">
				            <tr>
				                <th width="600" style="border-bottom:2px solid #e7e8eb">商品</th>
				                <th width="175" style="border-bottom:2px solid #e7e8eb">价格</th>
				                <th width="175" style="border-bottom:2px solid #e7e8eb">订单数量</th>
				                <th width="175" style="border-bottom:2px solid #e7e8eb">小计（元）</th>
				            </tr>
			            </c:if>
				         <tr>
				             <th colspan="4" class="ta-l">
				                 <span class="mr-default">订单号：${maOrderInfoMgVo.orderId }</span>
				                 <span class="ml-default mr-default">总数量：${maOrderInfoMgVo.countNumber }</span>
				                 <span class="ml-default mr-default">总金额：<span class="orange">￥${maOrderInfoMgVo.outOfPrice }</span></span>
				                 <span class="ml-default mr-default orange">
			                    	<c:if test="${maOrderInfoMgVo.managerStatus == 1 }">未配送</c:if>
			                    	<c:if test="${maOrderInfoMgVo.managerStatus == 2 }">配送中</c:if>
			                    	<c:if test="${maOrderInfoMgVo.managerStatus == 3 }">已送达</c:if>
			                    </span>
			                     <span class="ml-default mr-default">付款状态：
		                    		<c:if test="${maOrderInfoMgVo.acStatus == 4 }">
		                    			已付款（付款时间：<fmt:formatDate value="${maOrderInfoMgVo.acPayTime }" pattern="yyyy-MM-dd HH:mm:ss"/>）
		                    		</c:if>
		                    		<c:if test="${maOrderInfoMgVo.acStatus == 1 || maOrderInfoMgVo.acStatus == 2 || maOrderInfoMgVo.acStatus == 3 }">
		                    			未付款
		                    		</c:if>
				                 </span>
				                 <a href="${root}/scms/scOrder/returnDetailsAndPrintPage.do?id=${maOrderInfoMgVo.id}" class="fr">订单详情</a>
				             </th>
				         </tr>
			         </thead>
			         <tbody class="table-tbody">
			         <c:forEach var="scOrderDetailVo" items="${maOrderInfoMgVo.scOrderDetailVoList }">
				          <tr>
				              <td width="600" class="ta-l">
				              <c:choose>
				              	<c:when test="${scOrderDetailVo.img != '' && scOrderDetailVo.img != null && scOrderDetailVo.img != 'null'}">
				              		<img class="fl" src="${USER_FASTFDSPREURL }${scOrderDetailVo.img }" width="90" height="90" alt="缩略图" onerror="javascript:this.src='${USER_DEFAULTIMG_URL}'">
				              	</c:when>
				              	<c:otherwise>
				              		<img class="fl" src="${USER_DEFAULTIMG_URL}" width="90" height="90" alt="缩略图">
				              	</c:otherwise>
				              </c:choose>
				                  
				                  <div class="fl ml-default" style="width:450px">
							       ${scOrderDetailVo.name }
							       <br>
							       ${scOrderDetailVo.spec }
				                  </div>
				              </td>
				              <td width="175">${scOrderDetailVo.areaPrice }</td>
				              <td width="175">${scOrderDetailVo.countNum }</td>
				              <td width="175" class="orange">${scOrderDetailVo.xiaoji }</td>
				          </tr>
			          </c:forEach>
			         </tbody>
			       </table>
		    </div>
	    	</c:forEach>
    	</c:otherwise>
    </c:choose>
</div>
<c:if test="${fn:length(maOrderInfoMgVoList)>0}">
  <%@ include file="../common/pagination-kk.jsp"%>
</c:if>
</body>

</html>