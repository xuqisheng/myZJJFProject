<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>系统用户管理</title>
<%@ include file="../common/head.jsp"%>
</head>
<body class="wrap-bd">
<div class="mt-small mb-default clearfix">
    <form class="fl" id="findService" action="${root }/auth/user/getUserListPage.do">
        <input class="input input-search-text" type="text" name="userName" value="${userMgRo.userName }" placeholder="用户账号/姓名/角色名称" maxlength="16">
        <input type="hidden" name="status" id="status" value="${userMgRo.status }" >
        <input class="input input-search-button" value="搜索" type="submit">
    </form>
    <div class="fr"><a href="${root }/auth/user/goUserEdit.do"><button class="button button-default">添加系统用户</button></a></div>
</div>
<div id="J_pills">
    <span class="pills <c:if test="${userMgRo.status == null}">pills-active</c:if>" onclick="submitFind('')">全部</span>
    <span class="pills <c:if test="${userMgRo.status == 2}">pills-active</c:if>" onclick="submitFind(2)">正常账号</span>
    <span class="pills <c:if test="${userMgRo.status == 0}">pills-active</c:if>" onclick="submitFind(0)">停用账号</span>
</div>
<table class="table-list table-border">
    <thead class="table-thead">
    <tr>
        <th>账号</th>
        <th>姓名</th>
        <th>部门</th>
        <th>岗位</th>
        <th>角色</th>
        <th>创建者</th>
        <th>最后编辑时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody class="table-tbody">
	    <c:forEach var="CustomerService" items="${userList }">
	        <tr>
	            <td>${CustomerService.userName}</td>
	            <td>${CustomerService.nickName}</td>
	            <td>${CustomerService.deptName}</td>
	            <td>${CustomerService.postName}</td>
	            <td>${CustomerService.roleName}</td>
	            <td>${CustomerService.createName}</td>
	            <td><fmt:formatDate value="${CustomerService.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	            <td>
	            	<c:choose>
	            		<c:when test="${CustomerService.isDelete == true }"><font color="red">已删除</font></c:when>
	            		<c:otherwise>
	            			<a href="${root }/auth/user/goUserEdit.do?id=${CustomerService.id}" class="button button-operate J_editShopGroup" >编辑</a>
			                <c:if test="${CustomerService.status==2 }">
			                	<input type="button" value="停用" class="button button-operate eideStatus" date-val="${CustomerService.id}">
			                </c:if>
			                <c:if test="${CustomerService.status==0 }">
			                	<input type="button" value="删除" class="button button-operate eideDelete" date-val="${CustomerService.id}">
			                </c:if>
	            		</c:otherwise>
	            	</c:choose>
	            </td>
	        </tr>
	    </c:forEach>
    </tbody>
</table>
<c:if test="${fn:length(userList)>0}">
	<%@ include file="../common/pagination-kk.jsp"%>
</c:if>
<script>
	var url = '${root }/auth/user/updateUser.do';
	$(function(){
		$('.eideStatus').on('click' , function(){
			var id = $(this).attr('date-val');
			edit({id:id , status:0});
		});
		$('.eideDelete').on('click' , function(){
			var id = $(this).attr('date-val');
			edit({id:id , isDelete:true});
		});
	});
	function edit( dataIn ){
		$.ajax({
 			type : "POST",
 			url : url,
 			dataType : "json",
 			data:dataIn,
 			success : function(data) {
 				if (data.success) {
 					alert(data.message);
 					location.reload();
   				}else{
   					alert(data.message);
   				}
 			},
 			error : function() {
 				alert("请求失败！");
   			}
 		});
	}
	function submitFind(id){
		$('input[name="status"]').val(id);
		$("#findService").submit();
	}
</script>
</body>
</html>