<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>街坊店宝 - 权限管理</title>
	<%@ include file="../common/head.jsp"%>
</head>
<body>
<div class="wrap-bd">
    <div class="mb-small clearfix">
        <form class="fl" id="authForm" method="post" action="${root}/auth/admAuth/getAuthListPage.do">
            <input class="input input-search-text" type="text" name="authName" value="${authMgRo.authName}" placeholder="权限名称">
            <input class="input input-search-button" value="搜索" type="submit">
        </form>
        <div class="fr">
        	<a href="${root }/auth/admAuth/goAuthEdit.do" class="button button-default" >新增权限</a>
        </div>
    </div>
    <div>
        <table class="table-list table-border">
            <thead class="table-thead">
            <tr class="table-header">
            	<th width="10%">权限层级</th>
            	<th width="10%">权限类型</th>
                <th width="10%">权限名称</th>
                <th width="16%">权限描述</th>
                <th width="16%">权限action</th>
                <th width="10%">父权限</th>
                <th width="10%">是否启用</th>
                <th width="10%">创建时间</th>
                <th width="8%">操作</th>
            </tr>
            </thead>
            <tbody class="table-tbody">
            <c:forEach items="${authList}" var="auth">
            <tr>
            	<td>${auth.level}级权限</td>
            	<td>
            		<c:if test="${auth.type == 1}">菜单</c:if>
            		<c:if test="${auth.type == 2}">功能</c:if>
            	</td>
                <td>${auth.authName}</td>
                <td>${auth.roleRemark}</td>
                <td>${auth.action}</td>
                <td>${auth.upId}</td>
            	<td>
                	<c:choose>
                		<c:when test="${auth.status == 1}"><span class="txt-success">启用</span></c:when>
                		<c:otherwise><span class="txt-warn">不启用</span></c:otherwise>
                	</c:choose>
                </td>
                <td><fmt:formatDate value="${auth.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            
            
<%--            <td>${auth.ordId}</td> 
                <td>${auth.createUser}</td>
                <td><c:if test="${auth.isDelete}">是</c:if><c:if test="${!auth.isDelete}">否</c:if></td> --%>
                <td>
                	<a href="${root }/auth/admAuth/goAuthEdit.do?id=${auth.id}" class="button button-operate J_editShopGroup" >编辑</a>
                </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <c:if test="${fn:length(authList)>0}">
		<%@ include file="../common/pagination-kk.jsp"%>
	</c:if>
</div>
<div class="cover-all"></div>
</body>
</html>