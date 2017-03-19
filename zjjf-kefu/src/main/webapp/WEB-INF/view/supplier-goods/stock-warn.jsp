<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>批发商库存预警</title>
<%@ include file="../common/head.jsp"%>
</head>
<body>
<div class="wrap-bd">
    <div class="mb-small">
        <form id="searchForm" method="post" action="${root}/keFu/plantItem/getSupplierAllStock.do">
            <label>所属定格：</label>
            <select class="select" name="spGroupId">
                <option value="">全部</option>
                <c:forEach var="spGroup" items="${spGroupList }" >
                	<option <c:if test="${spGroup.id == plantItemRo.spGroupId }">selected="selected"</c:if> value="${spGroup.id }" >${spGroup.name }</option>
                </c:forEach>
            </select>
            <input type="text" name="nameAndMobile" value="${plantItemRo.nameAndMobile }" placeholder="批发商名称/手机号码" class="input input-search-text ml-default">
            <input type="submit" id="search" value="搜索" class="input input-search-button ml-default">
        </form>
    </div>
    <div>
        <table class="table-list table-border">
            <thead class="table-thead">
            <tr class="table-header">
                <th>序号</th>
                <th>定格名称</th>
                <th>批发商名称</th>
                <th>手机号码</th>
                <th>预警商品数</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody class="table-tbody">
            <c:choose>
            	<c:when test="${plantItemVoList==null || plantItemVoList.size() ==0}">
            		<tr>
		                <td colspan="6">无数据</td>
		            </tr>
            	</c:when>
            	<c:otherwise>
		            <c:forEach var="plantItemVo" items="${plantItemVoList }" varStatus="var"> 
		            <tr>
		                <td>${(page.pageIndex-1)*10+(var.index+1) }</td>
		                <td>${plantItemVo.spGroupName }</td>
		                <td>${plantItemVo.supplierName }</td>
		                <td>${plantItemVo.mobile }</td>
		                <td>${plantItemVo.goodsCount }</td>
		                <td>
		                    <a href="${root }/keFu/plantItem/getSupplierStockDetail.do?spId=${plantItemVo.spId}&spGroupId=${plantItemVo.spGroupId}">查看</a>
		                </td>
		            </tr>
		            </c:forEach>
           		</c:otherwise>
             </c:choose>
            </tbody>
        </table>
        <c:if test="${fn:length(plantItemVoList)>0}">
          <%@ include file="../common/pagination-kk.jsp"%>
   		</c:if>
    </div>
</div>
</body>
</html>