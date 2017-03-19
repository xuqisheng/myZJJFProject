<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>App运行日志管理</title>
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
		<li class="active"><a href="${ctx}/apprunlog/appRunLog/">App运行日志列表</a></li>
		<shiro:hasPermission name="apprunlog:appRunLog:edit"><li><a href="${ctx}/apprunlog/appRunLog/form">App运行日志添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="appRunLog" action="${ctx}/apprunlog/appRunLog/" method="post" class="form-inline" style="margin:10px 5px 10px 5px">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<div class="form-group">
				<form:input path="userName" htmlEscape="false" maxlength="11" class="form-control" placeholder="请输入用户账号"/>
			</div>
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		<a href="${ctx}/apprunlog/appRunLog/findAppRunLogList" onclick="return confirmx('确定要导出App运行日志数据吗？', this.href)">
			<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
		</a>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
					<th>用户</th>
					<th>设备类型</th>
					<th>设备名称</th>
					<th>是否活着</th>
					<th>上传状态</th>
					<th>网络</th>
					<th>GPS</th>
					<th>App时间</th>
					<th>服务器时间</th>
					<th>数据信息</th>
				<shiro:hasPermission name="apprunlog:appRunLog:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="appRunLog">
			<tr>
				<td><a href="${ctx}/apprunlog/appRunLog/form?id=${appRunLog.id}">
					${appRunLog.userName}
				</a></td>
				<td>
					${appRunLog.deviceType}
				</td>
				<td>
					${appRunLog.deviceName}
				</td>
				<td>
					${appRunLog.keepAlive}
				</td>
				<td>
					${appRunLog.uploadState}
				</td>
				<td>
					${appRunLog.network}
				</td>
				<td>
					${appRunLog.gps}
				</td>
				<td>
					<fmt:formatDate value="${appRunLog.appTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${appRunLog.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${appRunLog.dataInfo}
				</td>
				<shiro:hasPermission name="apprunlog:appRunLog:edit"><td>
    				<a href="${ctx}/apprunlog/appRunLog/form?id=${appRunLog.id}">修改</a>
					<a href="${ctx}/apprunlog/appRunLog/delete?id=${appRunLog.id}" onclick="return confirmx('确认要删除该App运行日志吗？', this.href)">删除</a>
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
				url : ctx + "/apprunlog/appRunLog/findAppRonLogList",
				dataType : "json",
				async : false,
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