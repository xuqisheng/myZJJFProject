<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>App运行日志管理</title>
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
		<li><a href="${ctx}/apprunlog/appRunLog/">App运行日志列表</a></li>
		<li class="active"><a href="${ctx}/apprunlog/appRunLog/form?id=${appRunLog.id}">App运行日志<shiro:hasPermission name="apprunlog:appRunLog:edit">${not empty appRunLog.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="apprunlog:appRunLog:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="appRunLog" action="${ctx}/apprunlog/appRunLog/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">是否活着(1、活着；2、杀死；):</label></div>
			<div class="col-sm-2">
				<form:input path="keepAlive" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">上传状态(1、上传成功；2、上传失败；3、批量上传成功；4、批量上传失败；):</label></div>
			<div class="col-sm-2">
				<form:input path="uploadState" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">网络（1、无网络；2、手机网络；3、无线网络；）:</label></div>
			<div class="col-sm-2">
				<form:input path="network" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">GPS(1、打开；2、关闭；):</label></div>
			<div class="col-sm-2">
				<form:input path="gps" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">上传的数据信息:</label></div>
			<div class="col-sm-2">
				<form:input path="dataInfo" htmlEscape="false" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">手机时间:</label></div>
			<div class="col-sm-2">
				<input name="appTime" type="text" readonly="readonly" maxlength="20" class="form-control Wdate "
					value="<fmt:formatDate value="${appRunLog.appTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">备注:</label></div>
			<div class="col-sm-2">
				<form:input path="remark" htmlEscape="false" maxlength="500" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">创建时间:</label></div>
			<div class="col-sm-2">
				<input name="createTime" type="text" readonly="readonly" maxlength="20" class="form-control Wdate "
					value="<fmt:formatDate value="${appRunLog.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">设备类型:</label></div>
			<div class="col-sm-2">
				<form:input path="deviceType" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="apprunlog:appRunLog:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>