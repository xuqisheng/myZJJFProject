<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>个人信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
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
		<li class="active"><a href="${ctx}/sys/user/info">个人信息</a></li>
		<li><a href="${ctx}/sys/user/modifyPwd">修改密码</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="user" action="${ctx}/sys/user/info" method="post" class="form-horizontal">
	  <div class="form-group">
	    <label class="col-sm-1 control-label">归属公司</label>
	    <div class="col-sm-10">
	      <p class="form-control-static">${user.company.name}</p>
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-1 control-label">归属部门</label>
	    <div class="col-sm-10">
	      <p class="form-control-static">${user.office.name}</p>
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <label class="col-sm-1 control-label">姓名</label>
	    <div class="col-sm-2">
	      <form:input path="name" type="text" htmlEscape="false" maxlength="50" class="form-control" readonly="true"/>
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-1 control-label">邮箱</label>
	    <div class="col-sm-2">
	      <form:input path="email" htmlEscape="false" maxlength="50" type="email" class="form-control"/>
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <label for="inputPhone" class="col-sm-1 control-label">电话</label>
	    <div class="col-sm-2">
	      <form:input path="phone" type="text" htmlEscape="false" maxlength="50" class="form-control"/>
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <label for="inputMobile" class="col-sm-1 control-label">手机</label>
	    <div class="col-sm-2">
	      <form:input path="mobile" type="text" htmlEscape="false" maxlength="50" class="form-control"/>
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <label for="inputMobile" class="col-sm-1 control-label">备注</label>
	    <div class="col-sm-2">
	      <form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="form-control"/>
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <label class="col-sm-1 control-label">用户类型</label>
	    <div class="col-sm-10">
	      <p class="form-control-static">${fns:getDictLabel(user.userType, 'sys_user_type', '无')}</p>
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <label class="col-sm-1 control-label">用户角色</label>
	    <div class="col-sm-10">
	      <p class="form-control-static">${user.roleNames}</p>
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <label class="col-sm-1 control-label">上次登录</label>
	    <div class="col-sm-10">
	      <p class="form-control-static">IP: ${user.oldLoginIp}&nbsp;&nbsp;&nbsp;&nbsp;时间：<fmt:formatDate value="${user.oldLoginDate}" type="both" dateStyle="full"/></p>
	    </div>
	  </div>
	  <div class="form-group">
	    <div class="col-sm-offset-1 col-sm-10">
	      <button type="submit" class="btn btn-sm btn-primary pull-left m-t-n-xs">保 存</button>
	    </div>
	  </div>
	<!--   
	  <div class="form-group">
	    <label for="inputPassword" class="col-sm-1 control-label">Password</label>
	    <div class="col-sm-2">
	      <input type="password" class="form-control" id="inputPassword3" placeholder="Password" htmlEscape="false">
	    </div>
	  </div>
	  <div class="form-group">
	    <div class="col-sm-offset-1 col-sm-10">
	      <div class="checkbox">
	        <label>
	          <input type="checkbox"> Remember me
	        </label>
	      </div>
	    </div>
	  </div>
	 -->
	</form:form>
</body>
</html>