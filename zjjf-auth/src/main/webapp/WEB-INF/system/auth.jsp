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
        <form class="fl" id="authForm" method="post" action="${root}/auth/auth/getAuthListPage.do">
            <label>所属系统：</label>
            <select class="select" name="appId">
            	<option value="" <c:if test="${authMgRo.appId == null || authMgRo.appId == ''}">selected="selected"</c:if>>全部</option>
                <option value="2" <c:if test="${authMgRo.appId == 2}">selected="selected"</c:if>>客服端</option>
                <option value="3" <c:if test="${authMgRo.appId == 3}">selected="selected"</c:if>>进销存端</option>
                <option value="4" <c:if test="${authMgRo.appId == 4}">selected="selected"</c:if>>财务</option>
                <option value="6" <c:if test="${authMgRo.appId == 6}">selected="selected"</c:if>>经销商</option>
                <option value="7" <c:if test="${authMgRo.appId == 7}">selected="selected"</c:if>>仓库</option>
            </select>
            <select class="select" name="type">
            	<option value="" <c:if test="${authMgRo.type == null || authMgRo.type == ''}">selected="selected"</c:if>>全部</option>
                <option value="1" <c:if test="${authMgRo.type == 1}">selected="selected"</c:if>>菜单</option>
                <option value="2" <c:if test="${authMgRo.type == 2}">selected="selected"</c:if>>功能</option>
            </select>
            <select class="select" name="level">
            	<option value="" <c:if test="${authMgRo.level == null || authMgRo.level == ''}">selected="selected"</c:if>>全部</option>
                <option value="1" <c:if test="${authMgRo.level == 1}">selected="selected"</c:if>>一级权限</option>
                <option value="2" <c:if test="${authMgRo.level == 2}">selected="selected"</c:if>>二级权限</option>
            </select>
            <input class="input input-search-text" type="text" name="authName" value="${authMgRo.authName}" placeholder="权限名称/父权限名称">
            <input class="input input-search-button" value="搜索" type="submit">
        </form>
        <div class="fr">
        	<a href="${root }/auth/auth/goAuthEdit.do" class="button button-default" >新增权限</a>
        </div>
    </div>
    <div>
        <table class="table-list table-border">
            <thead class="table-thead">
            <tr class="table-header">
            	<th width="8%">权限层级</th>
            	<th width="8%">权限类型</th>
            	<th width="8%">所属系统</th>
                <th width="10%">权限名称</th>
                <th width="16%">权限描述</th>
                <th width="16%">权限action</th>
                <th width="6%">父权限</th>
                <th width="6%">是否启用</th>
                <th width="4%">排序</th>
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
            	<td>
                	<c:choose>
                		<c:when test="${auth.appId == 1}">街坊</c:when>
                		<c:when test="${auth.appId == 2}">客服端</c:when>
                		<c:when test="${auth.appId == 3}">进销存端</c:when>
                		<c:when test="${auth.appId == 4}">财务</c:when>
                		<c:when test="${auth.appId == 5}">收银机</c:when>
                		<c:when test="${auth.appId == 6}">经销商</c:when>
                		<c:when test="${auth.appId == 7}">仓库</c:when>
                		<c:otherwise>其他系统</c:otherwise>
                	</c:choose>
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
                <td>${auth.ordId}</td>
                <td><fmt:formatDate value="${auth.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            
            
<%--            <td>${auth.ordId}</td> 
                <td>${auth.createUser}</td>
                <td><c:if test="${auth.isDelete}">是</c:if><c:if test="${!auth.isDelete}">否</c:if></td> --%>
                <td>
                	
                	<c:choose>
	            		<c:when test="${auth.isDelete == true }"><font color="red">已删除</font></c:when>
	            		<c:otherwise>
	            			<a href="${root }/auth/auth/goAuthEdit.do?id=${auth.id}" class="button button-operate J_editShopGroup" >编辑</a>
			                <c:if test="${auth.status==1 }">
			                	<input type="button" value="停用" class="button button-operate eideStatus" date-val="${auth.id}">
			                </c:if>
			                <c:if test="${auth.status==0 }">
			                	<input type="button" value="删除" class="button button-operate eideDelete" date-val="${auth.id}">
			                </c:if>
	            		</c:otherwise>
	            	</c:choose>
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
<script type="text/javascript">
	var url = '${root }/auth/auth/updateAuth.do';
	$(function(){
		$('.eideStatus').on('click' , function(){
			var id = $(this).attr('date-val');
			location.href= url + '?id='+id+'&status=0';
		});
		$('.eideDelete').on('click' , function(){
			var id = $(this).attr('date-val');
			location.href= url + '?id='+id+'&isDelete=1';
		});
		$('.select').on('change' , function(){
			$('#authForm').submit();
		});
	});
</script>
</body>
</html>