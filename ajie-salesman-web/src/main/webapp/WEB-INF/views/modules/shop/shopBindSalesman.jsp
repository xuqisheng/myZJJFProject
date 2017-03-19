<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户管理管理</title>
	<meta name="decorator" content="default"/>
<%-- 	<script src="${ctxStatic}/common/salesman.js" type="text/javascript"></script> --%>
	<script src="${ctxStatic}/salesman/shopBindSalesman.js" type="text/javascript"></script>
</head>
<body>
	<ul class="nav nav-tabs">
	</ul><br/>
	<form:form id="inputForm" modelAttribute="shop" action="${ctx}/shop/shop/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="hidden" name="shopIds" id="shopIds">
		<sys:message content="${message}"/>		
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">部门:</label></div>
			<div class="col-sm-1">
				<sys:treeselect id="deptId" name="deptId" value="" labelName="deptName" labelValue="" title="部门" url="/sys/dept/treeData" cssClass="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">业务员:</label></div>
			<div class="col-sm-1">
				<input type="hidden" name="salesmanId" id="salesmanId"/>
				<input type="text" name="suggestionName" id="suggestionName" class="form-control" value="${linePlans.userName}"/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">手机号:</label></div>
			<div class="col-sm-1">
				<span id="spMobileId"></span>
			</div>
		</div>
	    <div class="hr-line-dashed"></div>
	    <input id="btnDistribute" class="btn btn-primary" type="button" value="批量绑定客户" onclick="bindShopFun();"/>
	</form:form>
</body>
</html>