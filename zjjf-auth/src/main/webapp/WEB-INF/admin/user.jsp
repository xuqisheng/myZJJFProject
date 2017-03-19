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
    <form class="fl" id="findService" action="${root }/auth/admUser/getUserListPage.do">
        <input class="input input-search-text" type="text" name="userName" value="${userMgRo.userName }" placeholder="用户账号/姓名/角色名称" maxlength="16">
<%--         <input type="hidden" name="status" id="status" value="${userMgRo.status }" > --%>
        <input class="input input-search-button" value="搜索" type="submit">
    </form>
    <div class="fr"><a href="${root }/auth/admUser/goUserEdit.do"><button class="button button-default">添加系统用户</button></a></div>
</div>
<div id="J_pills">
	
<%--     <span class="pills <c:if test="${userMgRo.status == 1}">pills-active</c:if>" onclick="submitFind(1)">正常账号</span> 
		<span class="pills <c:if test="${userMgRo.status == 0 || userMgRo.status == null}">pills-active</c:if>" onclick="submitFind(0)">停用账号</span> --%>
</div>
<table class="table-list table-border">
    <thead class="table-thead">
    <tr>
        <th>账号</th>
<!--         <th>姓名</th> -->
        <th>部门</th>
        <th>岗位</th>
        <th>角色</th>
        <th>创建者</th>
        <th>最后编辑时间</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody class="table-tbody">
	    <c:forEach var="CustomerService" items="${userList }">
	        <tr>
	            <td>${CustomerService.userName}</td>
<%-- 	            <td>${CustomerService.nickName}</td> --%>
	            <td>${CustomerService.deptName}</td>
	            <td>${CustomerService.postName}</td>
	            <td>${CustomerService.roleName}</td>
	            <td>${CustomerService.createName}</td>
	            <td><fmt:formatDate value="${CustomerService.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	            <th>
	            	<c:if test="${CustomerService.status == 1}"><span class="txt-success">启用</span></c:if>
	            	<c:if test="${CustomerService.status == 0}"><span class="txt-warn">停用</span></c:if>
	            </th>
	            <td>
	                <a href="${root }/auth/admUser/goUserEdit.do?id=${CustomerService.id}" class="button button-operate J_editShopGroup" >编辑</a>
	                <c:if test="${CustomerService.status==2 }">
	                	<input type="button" value="停用" class="button button-operate" onclick="stopOrDel('${CustomerService.id}','${CustomerService.status}','status')">
	                </c:if>
	                <c:if test="${CustomerService.status==0 }">
	                	<input type="button" value="删除" class="button button-operate" onclick="stopOrDel('${CustomerService.id}','${CustomerService.isDelete}','isdelete')">
	                </c:if>
	            </td>
	        </tr>
	    </c:forEach>
    </tbody>
</table>
<c:if test="${fn:length(CustomerServices)>0}">
	<%@ include file="../common/pagination-kk.jsp"%>
</c:if>
<script>
	function submitFind(id){
		$('input[name="status"]').val(id);
		$("#findService").submit();
	}
	function stopOrDel(id,status,str){
		var url="${root}/auth/admUser/updateCustomerServiceStatusAndIsDelete.do";
		var pageStatus = $("#status").val();
		if(str=="status"){
			if(confirm("确定要停用吗？")){
				var status = 0;
				$.ajax({type:'POST',
					url:url,
					data:{customerServiceId:id,status:status},
					dataType:'json',
					success:function(data){
						if(data.success){
							alert("处理成功！");
							location.href="${root}/auth/admUser/getAllCustomerServiceByParam.do?initStatus="+pageStatus;
						}else{
							alert("处理失败！");
						}
					},
					error:function(){
						alert("请求失败！");
					}
				});
			}
		}else if(str=="isdelete"){
			if(confirm("确定要删除吗？")){
				var isDelete = 1;
				$.ajax({type:'POST',
					url:url,
					data:{customerServiceId:id,isDelete:isDelete},
					dataType:'json',
					success:function(data){
						if(data.success){
							alert("删除成功！");
							location.href="${root}/auth/admUser/getAllCustomerServiceByParam.do?initStatus="+pageStatus;
						}else{
							alert("删除失败！");
						}
					},
					error:function(){
						alert("请求失败！");
					}
				});
			}
		}
	}
</script>
</body>
</html>