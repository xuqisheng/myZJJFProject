<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>经销商商品管理 - 经销商列表</title>
    <%@ include file="../common/head.jsp"%>
</head>
<body>
<div class="wrap-bd">
    <div class="mb-default">
        <a class="crumb" href="${root}/scms/manager/listPage.do">经销商商品管理</a><a class="crumb crumb-active">经销商列表</a>
    </div>
    <div class="mb-small">
        <form method="get">
            <input class="input input-search-text" type="text" name="managerName" value="${scmsManager.managerName}" placeholder="请输入经销商名称">
            <input class="input input-search-button" value="搜索" type="button" onclick="getRadio()"/>
        </form>
    </div>
    <div>
    	<table class="table-list table-border">
	        <thead class="table-thead">
	        <tr>
	            <th>编号</th>
	            <th>经销商名称</th>
	            <th>编辑时间</th>
	            <th>操作</th>
	        </tr>
	        </thead>
	        <tbody class="table-tbody" id="itemFromBody">
	        	<c:forEach items="${scmsManagers}" varStatus="i" var="scmsManager" >
		        	<tr>
			            <td>${scmsManager.managerCode}</td>
			            <td>${scmsManager.managerName}</td>
			            <td><fmt:formatDate value="${scmsManager.regTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			            <td>
			            	<a class="button button-operate" href="${root}/scms/item/listPage.do?managerId=${scmsManager.id}">管理</a>
			            </td>
		            </tr>
	            </c:forEach>
	        </tbody>
	    </table>
	    <c:if test="${fn:length(scmsManagers)>0}">
		  <%@ include file="../common/pagination-kk.jsp"%>
    	</c:if>
    </div>
</div>
<script type="text/javascript">
function getRadio(){
	var url="${root}/scms/manager/listPage.do";
	var managerName= $('input[name="managerName"]').val();
	if(managerName != null &&managerName != undefined && managerName != ''){
		   url=url+"?managerName="+managerName;
	}
	location.href=url;
}
function editManager(id){
	window.location.href = '${root}/scms/manager/addOrUpdate.do?id='+id;
}
</script>
</body>
</html>