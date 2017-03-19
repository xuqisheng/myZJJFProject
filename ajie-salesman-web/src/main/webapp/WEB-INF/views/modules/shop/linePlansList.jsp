<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>线路规划管理</title>
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
		<li class="active"><a href="${ctx}/shop/linePlans/">线路规划列表</a></li>
		<shiro:hasPermission name="shop:linePlans:edit"><li><a href="${ctx}/shop/linePlans/queryLinePlans">线路规划添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="linePlans" action="${ctx}/shop/linePlans/" method="post" class="form-inline" style="margin:10px 5px 10px 5px">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<div class="form-group">
			<label>路线：</label>
				<form:input path="lineName" htmlEscape="false" maxlength="10" class="form-control"/>
			</div>
			<div class="form-group">
			<label>userId：</label>
				<form:input path="userId" htmlEscape="false" maxlength="32" class="form-control"/>
			</div>
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>线路ID</th>
				<th>路线</th>
				<th>部门ID</th>
				<th>userId</th>
				<th>shopTotal</th>
				<th>visitTotal</th>
				<th>week</th>
				<shiro:hasPermission name="shop:linePlans:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="linePlans">
			<tr>
				<td><a href="${ctx}/shop/linePlans/form?id=${linePlans.id}">
					${linePlans.lineId}
				</a></td>
				<td>
					${linePlans.lineName}
				</td>
				<td>
					${linePlans.deptId}
				</td>
				<td>
					${linePlans.userId}
				</td>
				<td>
					${linePlans.shopTotal}
				</td>
				<td>
					${linePlans.visitTotal}
				</td>
				<td>
					${linePlans.week}
				</td>
				<shiro:hasPermission name="shop:linePlans:edit"><td>
    				<a href="${ctx}/shop/linePlans/form?id=${linePlans.id}">修改</a>
					<a href="${ctx}/shop/linePlans/delete?id=${linePlans.id}" onclick="return confirmx('确认要删除该线路规划吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</div>
</body>
</html>