<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>App日志监控管理</title>
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
		<li class="active"><a href="${ctx}/applog/applog/">App日志监控列表</a></li>
		<shiro:hasPermission name="applog:applog:edit"><li><a href="${ctx}/applog/applog/form">App日志监控添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="applog" action="${ctx}/applog/applog/" method="post" class="form-inline" style="margin:10px 5px 10px 5px">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<div class="form-group">
				<form:input path="logId" htmlEscape="false" maxlength="11" class="form-control"  placeholder="请输入用户账号"/>
			</div>
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		
		<a href="${ctx}/applog/applog/findErrorLog" onclick="return confirmx('确定要导出App日志数据吗？', this.href)">
			<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
		</a>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				    <th>账号</th>
					<th>姓名</th>
					<th>设备名称</th>
					<th>设备标识</th>
					<th>异常信息</th>
					<th>异常时间</th>
					<th>创建时间</th>
					<th>版本号</th>
				<shiro:hasPermission name="applog:applog:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="applog">
			<tr>
				<td><a href="${ctx}/applog/applog/form?id=${applog.id}">
					${applog.mobile}
				</a></td>
				<td>
					${applog.userName}
				</td>				
				<td>${applog.deviceUUID}</td>
				<td>${applog.deviceName}</td>
				<td>${applog.errorMsg}</td>
				<td>
					<fmt:formatDate value="${applog.errorTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${applog.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${applog.version}
				</td>
				<shiro:hasPermission name="applog:applog:edit"><td>
    				<a href="${ctx}/applog/applog/form?id=${applog.id}">修改</a>
					<a href="${ctx}/applog/applog/delete?id=${applog.id}" onclick="return confirmx('确认要删除该App日志监控吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</div>
<script type="text/javascript">
/* $("#btnExport").click(function(){
	 if(confirm("确定要导出日志数据吗？")){
		 
		 $.ajax({
				type : "post",
				url : ctx + "/applog/applog/findErrorLog",
				dataType : "json",
				async : true,
				success : function(data) {
					if (data.success) {
						$.jBox.tip(data.msg, "操作成功", "success");
						alert($(obj).parent());
						console.info($(obj).parent());
					} else {
						alert(data.message);
					}
				},
				error : function(data) {
				}
			});
		}
}); */
</script>
</body>
</html>