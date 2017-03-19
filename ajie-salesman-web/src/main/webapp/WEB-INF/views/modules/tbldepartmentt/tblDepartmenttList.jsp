<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>部门信息管理</title>
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
		<li class="active"><a href="${ctx}/tbldepartmentt/tblDepartmentt/">部门信息列表</a></li>
		<shiro:hasPermission name="tbldepartmentt:tblDepartmentt:edit"><li><a href="${ctx}/tbldepartmentt/tblDepartmentt/form">部门信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tblDepartmentt" action="${ctx}/tbldepartmentt/tblDepartmentt/" method="post" class="form-inline" style="margin:10px 5px 10px 5px">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<div class="form-group">
			<label>部门编码：</label>
				<form:input path="deptId" htmlEscape="false" maxlength="20" class="form-control" placeholder="请输入部门编号"/>
			</div>
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>部门编码</th>
				<th>部门名称</th>
				<th>上级部门</th>
				<th>部门负责人</th>
				<th>备注</th>
				<shiro:hasPermission name="tbldepartmentt:tblDepartmentt:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
	<tbody class="table-tbody">
		<c:forEach items="${page.list}" var="tblDepartmentt">
			<tr>
				<td>
					${tblDepartmentt.deptId}
				</td>
				<td>
					${tblDepartmentt.deptName}
				</td>
				<td>
					${tblDepartmentt.parentName}
				</td>
				<td>
					${tblDepartmentt.userName}
				</td>
				<td>
					${tblDepartmentt.remarks}
				</td>
				<shiro:hasPermission name="tbldepartmentt:tblDepartmentt:edit"><td>
    				<a href="${ctx}/tbldepartmentt/tblDepartmentt/forms?id=${tblDepartmentt.id}">修改</a>
					<a href="${ctx}/tbldepartmentt/tblDepartmentt/delete?id=${tblDepartmentt.id}" onclick="return confirmx('确认要删除该部门信息吗？', this.href)">删除</a>
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