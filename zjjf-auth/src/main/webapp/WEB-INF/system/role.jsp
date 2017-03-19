<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>系统角色管理</title>
<%@ include file="../common/head.jsp"%>
<script src="${root}/resources/js/layout.js"></script>
</head>
<body class="wrap-bd">
<div class="mb-small clearfix">
    <!-- <form class="fl" id="authForm" method="post" action="${root}/auth/auth/getAuthListPage.do">
        <label>所属系统：</label>
        <select class="select" name="appId">
        	<option value="" <c:if test="${authMgRo.appId == null || authMgRo.appId == ''}">selected="selected"</c:if>>全部</option>
            <option value="2" <c:if test="${authMgRo.appId == 2}">selected="selected"</c:if>>客服端</option>
            <option value="3" <c:if test="${authMgRo.appId == 3}">selected="selected"</c:if>>进销存端</option>
            <option value="4" <c:if test="${authMgRo.appId == 4}">selected="selected"</c:if>>财务</option>
        </select>
        <input class="input input-search-text" type="text" name="authName" value="${authMgRo.authName}" placeholder="权限名称">
        <input class="input input-search-button" value="搜索" type="submit">
    </form>
     -->
    <div class="fr">
 	    <input type="button" value="添加角色" class="button button-default J_add">
    </div>
</div>
<table class="table-list table-border">
    <thead class="table-thead">
    <tr>
        <th>角色编号</th>
        <th>角色名称</th>
        <th>用户数</th>
        <th>创建者</th>
        <th>最后编辑时间</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody class="table-tbody">
    <c:forEach var="role" items="${roleList }">
        <tr>
            <td>${role.roleNo}</td>
            <td>${role.roleName}</td>
            <td><a href="${root }/auth/user/getUserListPage.do?userName=${role.roleName}&status=2">${role.userNum}</a></td>
            <td>${role.createUser}</td>
            <td><fmt:formatDate value="${role.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
            <th>
            	<c:choose>
               		<c:when test="${role.status == 1}"><span class="txt-success">启用</span></c:when>
               		<c:otherwise><span class="txt-warn">不启用</span></c:otherwise>
               	</c:choose>
            </th>
            <td>
            	<a href="${root }/auth/role/goRoleEdit.do?id=${role.id}&appId=${role.appId}" class="button button-operate J_editShopGroup" >编辑</a>
<%--             	<a href="${root }/auth/role/deleteRole.do?id=${role.id}" class="button button-operate J_editShopGroup" >删除</a> --%>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:if test="${fn:length(roleList)>0}">
	<%@ include file="../common/pagination-kk.jsp"%>
</c:if>
<script type="text/javascript">
	$(function(){
		$('.J_add').on('click' , function(){
			layer.confirm('请选择该角色对应系统',{
				btn:['客服', '进销存' , '财务']
			},function(index){
				openEdit('2');
			},function(index){
				openEdit('3');
			},function(index){
				openEdit('4');
			});
		});
	});
	function openEdit(appid){
		location.href='${root }/auth/role/goRoleEdit.do?appId='+appid;
	}
</script>
</body>
</html>