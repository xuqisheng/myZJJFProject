<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>部门信息管理</title>
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
		<li><a href="${ctx}/tbldepartmentt/tblDepartmentt/">部门信息列表</a></li>
		<li class="active"><a href="${ctx}/tbldepartmentt/tblDepartmentt/form?id=${tblDepartmentt.id}">部门信息<shiro:hasPermission name="tbldepartmentt:tblDepartmentt:edit">${not empty tblDepartmentt.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="tbldepartmentt:tblDepartmentt:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tblDepartmentt" action="${ctx}/tbldepartmentt/tblDepartmentt/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">部门编号:</label></div>
			<div class="col-sm-2">
				<form:input id="deptId" path="deptId" htmlEscape="false" maxlength="16" class="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">部门名称:</label></div>`
			<div class="col-sm-2">
				<form:input path="deptName" htmlEscape="false" maxlength="16" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">上级部门:</label></div>
			<div class="col-sm-2">	
				<select name="pid" id="pid">
					<c:forEach items="${list}" var="pidName">
						<option value="${pidName.deptId}">${pidName.deptName}</option>
					</c:forEach>
				</select> 					
			</div> 
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">备注:</label></div>
			<div class="col-sm-2">
				<form:input path="remarks" htmlEscape="false" maxlength="20" class="form-control "/>
			</div>
		</div> 
		<div class="form-actions">
			<shiro:hasPermission name="tbldepartmentt:tblDepartmentt:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
<script type="text/javascript">
	$('#btnSubmit').on('click',function() {
		var pid = $("#pid").val();
		var deptId = $("#deptId").val();
		var deptName = $("#deptName").val();
		var remarks = $("#remarks").val();
		
		if(deptId =="" || deptId == null){
			alert("部门编码不能为空!");
			return;
		}
		if(deptName =="" ||deptName == null){
			alert("部门名称不能为空!");
			return;
		}
		
	    var regExId = /^[a-zA-Z0-9_]{1,}$/; 
	    if (!deptId.match(regExId)) { 
	            alert("部门编号只能包含字母、数字及下划线等字符!"); 
	            return false; 
	    } 
		
		var regex = /[^\u4e00-\u9fa5a-zA-Z0-9_]/ig;
	    if (regex.test(deptName))
	    {
	        alert("部门名称只能包含中英文、数字及下划线等字符！");
	        return;
	    }
		if(deptName.length>15){
		    alert("部门名称长度不能超过15个字符！");
		    return;
		}
		if(remarks.length>200){
			alert("备注长度不能超过200个字符！");
			return;
		}
		var regexRmk = /[^\u4e00-\u9fa5a-zA-Z0-9_,.，;；。！!？?\\、%]/ig;
	    if (regexRmk.test(remarks))
	    {
	    	alert("备注只能包含中英文、数字、下划线及常用标点符号等字符！");
	        return;
	    }
});
</script>
</body>
</html>