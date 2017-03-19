<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>账户信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/appsesalesman/applistsalesman/">账户信息列表</a></li>
		<li class="active"><a href="${ctx}/appsesalesman/applistsalesman/form?id=${applistsalesman.id}">账户信息<shiro:hasPermission name="appsesalesman:applistsalesman:edit">${not empty applistsalesman.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="appsesalesman:applistsalesman:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="applistsalesman" action="${ctx}/appsesalesman/applistsalesman/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">用户名:</label></div>
			<div class="col-sm-2">
				<form:input path="userName" htmlEscape="false" maxlength="16" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">手机号码:</label></div>
			<div class="col-sm-2">
				<form:input path="mobile" htmlEscape="false" maxlength="15" class="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">最后登录时间:</label></div>
			<div class="col-sm-2">
				<input name="lastTime" type="text" readonly="readonly" maxlength="20" class="form-control Wdate "
					value="<fmt:formatDate value="${applistsalesman.lastTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>	
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">部门名称:</label></div>
			<div class="col-sm-2">	
				<select name="deptId" id="deptId">
					<c:forEach items="${list}" var="seldeptName">
						<option value="${seldeptName.deptId}">${seldeptName.names}</option>
					</c:forEach>
				</select> 					
			</div>
		</div>	
		<div class="form-group">
		<div><label class="control-label" style="margin-left:15px;">职位名称:</label></div>
			<div class="col-sm-2">
				<select name="labels" id="labels">
					<c:forEach items="${listlab}" var="label">
						<option value="${label.id}">${label.labels}</option>
					</c:forEach> 
				</select> 			
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">版本号:</label></div>
			<div class="col-sm-2">
				<form:input path="version" htmlEscape="false" maxlength="10" class="form-control "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="appsesalesman:applistsalesman:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>