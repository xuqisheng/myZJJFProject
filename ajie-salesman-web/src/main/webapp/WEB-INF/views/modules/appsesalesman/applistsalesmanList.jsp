<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>账户信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
<div class="wrapper wrapper-content animated fadeInRight">
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/appsesalesman/applistsalesman/">账户信息列表</a></li>
		<shiro:hasPermission name="appsesalesman:applistsalesman:edit"><li><a href="${ctx}/appsesalesman/applistsalesman/seldept">账户信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="applistsalesman" action="${ctx}/appsesalesman/applistsalesman/" method="post" class="form-inline" style="margin:10px 5px 10px 5px">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<div class="form-group">
			<label>用户名：</label>
				<form:input path="userName" htmlEscape="false" maxlength="16" class="form-control"/>
			</div>
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户名</th>
				<th>手机号码</th>
				<th>最后登录时间</th>
				<th>部门名称</th>
				<th>职位名称</th>
				<th>版本号</th>
				<shiro:hasPermission name="appsesalesman:applistsalesman:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="applistsalesman">
			<tr>
				<td><a href="${ctx}/appsesalesman/applistsalesman/form?id=${applistsalesman.id}">
					${applistsalesman.userName}
				</a></td>
				<td>
					${applistsalesman.mobile}
				</td>
				<td>
					<fmt:formatDate value="${applistsalesman.lastTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>				
				 <td>
					${applistsalesman.names}
				</td>
				
				<td>
					${applistsalesman.labels}
				</td>
		
				<td>
					${applistsalesman.version}
				</td>
				
				<shiro:hasPermission name="appsesalesman:applistsalesman:edit"><td>
    				<a href="${ctx}/appsesalesman/applistsalesman/form?id=${applistsalesman.id}">修改</a>
					<a href="${ctx}/appsesalesman/applistsalesman/delete?id=${applistsalesman.id}" onclick="return confirmx('确认要删除该账户信息吗？', this.href)">删除</a>
				</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</div>
</body>
</html>
out.clear(); 
out=pageContext.pushBody();
