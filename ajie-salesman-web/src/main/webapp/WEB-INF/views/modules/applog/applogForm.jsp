<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>App日志监控管理</title>
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
		<li><a href="${ctx}/applog/applog/">App日志监控列表</a></li>
		<li class="active"><a href="${ctx}/applog/applog/form?id=${applog.id}">App日志监控<shiro:hasPermission name="applog:applog:edit">${not empty applog.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="applog:applog:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="applog" action="${ctx}/applog/applog/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">日志ID:</label></div>
			<div class="col-sm-2">
				<form:input path="logId" htmlEscape="false" maxlength="11" class="form-control required"/>
				<span class="help-inline" style="line-height:40px;"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">错误信息:</label></div>
			<div class="col-sm-2">
				<form:input path="errorMsg" htmlEscape="false" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">异常发生时间:</label></div>
			<div class="col-sm-2">
				<input name="errorTime" type="text" readonly="readonly" maxlength="20" class="form-control Wdate "
					value="<fmt:formatDate value="${applog.errorTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">设备类型:</label></div>
			<div class="col-sm-2">
				<form:input path="deviceType" htmlEscape="false" maxlength="1" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">version:</label></div>
			<div class="col-sm-2">
				<form:input path="version" htmlEscape="false" maxlength="10" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">创建时间:</label></div>
			<div class="col-sm-2">
				<input name="createTime" type="text" readonly="readonly" maxlength="20" class="form-control Wdate "
					value="<fmt:formatDate value="${applog.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="applog:applog:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>