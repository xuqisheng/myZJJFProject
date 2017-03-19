<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>批发商库存预警详情</title>
<%@ include file="../common/head.jsp"%>
</head>
<body>
<div class="wrap-bd">
    <div class="wrap-bd bg table-border">
                   所属定格：${plantItemVo.spGroupName }
        <span class="ml-default"></span>批发商名称：${plantItemVo.supplierName }
        <span class="ml-default"></span>手机号码：${plantItemVo.mobile }
    </div> 
    <input type="hidden" value="${plantItemRo.spId }" id="spId" name="spId">
    <input type="hidden" value="${plantItemRo.spGroupId }" id="spGroupId" name="spGroupId">
    <div class="mt-small">
        <table class="table-list table-border">
            <thead class="table-thead">
            <tr class="table-header">
                <th>序号</th>
                <th>商品条形码</th>
                <th>商品名称</th>
                <th>规格</th>
                <th>单位</th>
                <th>库存下限</th>
                <th>库存数量</th>
            </tr>
            </thead>
            <tbody class="table-tbody">
            <c:choose>
            	<c:when test="${plantItemVoList==null || plantItemVoList.size()==0 }">
            		<tr>
		                <td colspan="6">无数据</td>
		            </tr>
            	</c:when>
            	<c:otherwise>
            		<c:forEach var="plantItem" items="${plantItemVoList}" varStatus="var">
            			 <tr>
			                <td>${(page.pageIndex-1)*10+(var.index+1) }</td>
			                <td>${plantItem.mdseId }</td>
			                <td>${plantItem.name }</td>
			                <td>${plantItem.spec }</td>
			                <td>${plantItem.pkg }</td>
			                <td>${plantItem.goodsLower }</td>
			                <td>${plantItem.goodsStock }</td>
			            </tr>
            		</c:forEach>
            	</c:otherwise>
            </c:choose>
            </tbody>
        </table>
    </div>
    <c:if test="${fn:length(plantItemVoList)>0}">
		<%@ include file="../common/pagination-kk.jsp"%>
	</c:if>
</div>
</body>
</html>