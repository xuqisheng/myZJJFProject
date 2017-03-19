<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>联合采购商品</title>
	<%@ include file="../common/head.jsp"%>
</head>
<body>
<div class="wrap-bd">
	<div class="mb-default">
	    <a class="crumb" href="${root}/scms/gorupItem/getGorupItemlistPage.do">联合采购商品</a><a class="crumb crumb-active">区域信息列表</a>
	</div>
    <div class="mb-small clearfix">
        <form class="fl" id="groupForm">
            <input class="input input-search-text" type="text" name="areaName" value="${areaName}" placeholder="请输入区域名称" />
            <input class="input input-search-button" value="搜索" type="button" onclick="getRadio()"/>
		</form>
    </div>
    <table class="table-list table-border">
        <thead class="table-thead">
        <tr class="table-header">
            <th>编号</th>
            <th>区域名称</th>
            <th>商品数</th>
            <th>批发商数</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody class="table-tbody" id="groupFromBody">
        	<c:forEach items="${list}" varStatus="i" var="group" >
		        <tr>
		            <td>${group.id }</td>
		            <td>${group.name}</td>
		            <td><c:if test="${group.totItem == null}">0</c:if><c:if test="${group.totItem != null}">${group.totItem}</c:if></td>
		            <td><c:if test="${group.totSupplier == null}">0</c:if><c:if test="${group.totSupplier != null}">${group.totSupplier}</c:if></td>
		            <td>
		                <a href="${root}/scms/gorupItem/toIndex.do?isUpdate=edit&groupId=${group.id}" class="button button-operate">管理</a>
		            </td>
		        </tr>
        	</c:forEach>
        </tbody>
    </table>
	<c:if test="${fn:length(list)>0}">
		  <%@ include file="../common/pagination-kk.jsp"%>
   	</c:if>
</div>
<script type="text/javascript">
function getRadio(){
	var name= $('input[name="areaName"]').val();
	var url="${root}/scms/gorupItem/getGorupItemlistPage.do";
	if(name != null &&name != undefined && name != ''){
		   url=url+"?areaName="+name;
	}
	window.location.href=url;
}
</script>
</body>
</html>