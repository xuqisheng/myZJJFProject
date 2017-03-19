<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>字典管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
		
		function updateDelFlag(id,delFlag,warnMsg){
			if(confirm(warnMsg)) {
				 //提交表单
				 loading('正在提交，请稍等...');
				 $.ajax({
						type: "post",
						url: ctx+"/sys/dict/updateDelFlag",
						data: "id="+id+"&delFlag="+delFlag,
						dataType: "json",
						async: false,//使用同步的方式,true为异步方式
						success: function(data) {
						 closeTip();//隐藏遮罩 
						 if(data.success) {
							 $("#searchForm").submit();
						 }else{
							$.jBox.tip(data.msg,"操作失败","error");
						 } 
					}
				});
			}
		}
	</script>
</head>
<body>
<div class="wrapper wrapper-content animated fadeInRight">
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/dict/">字典列表</a></li>
		<shiro:hasPermission name="sys:dict:edit"><li><a href="${ctx}/sys/dict/form?sort=10">字典添加</a></li></shiro:hasPermission>
	</ul>
	
	<form:form id="searchForm" modelAttribute="dict" action="${ctx}/sys/dict/" method="post" class="form-inline" style="margin:10px 5px 10px 5px">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div class="form-group">
			<label>描述:</label>
			<form:input path="description" htmlEscape="false" maxlength="50" class="form-control input-sm"/>
		</div>
		<div class="form-group">
		    <label for="exampleInputName2">类型:</label>
			<form:select id="type" path="type"  class="input-medium" style="height:20px;">
				<form:option value="" label="请选择"/>
				<form:options items="${typeList}" htmlEscape="false"/>
			</form:select>
		</div>
		<div class="form-group">
		    <label for="exampleInputName2">类型:</label>
			<form:select id="delFlag" path="delFlag"  class="input-medium" style="height:20px;">
				<form:option value="" label="请选择"/>
				<form:options items="${fns:getDictList('del_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
		</div>
		<div class="form-group" style="margin-left:10px;">
			<button type="submit" class="btn btn-primary m-t-n-xs" style="line-height: 18px;">查询</button>
		</div>
	</form:form>
	
	
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>键值</th><th>标签</th><th>类型</th><th>描述</th><th>排序</th><shiro:hasPermission name="sys:dict:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="dict">
			<tr>
				<td>${dict.value}</td>
				<td><a href="${ctx}/sys/dict/form?id=${dict.id}">${dict.label}</a></td>
				<td><a href="javascript:" onclick="$('#type').val('${dict.type}');$('#searchForm').submit();return false;">${dict.type}</a></td>
				<td>${dict.description}</td>
				<td>${dict.sort}</td>
				<shiro:hasPermission name="sys:dict:edit"><td>
    				<a href="${ctx}/sys/dict/form?id=${dict.id}">修改</a>
    				<c:if test="${dict.delFlag eq '0'}">
						<a href="javascript:void(0);" onclick="updateDelFlag('${dict.id}','1','确认要删除该字典吗？');">删除</a>
					</c:if>
    				<c:if test="${dict.delFlag eq '1'}">
						<a href="javascript:void(0);" onclick="updateDelFlag('${dict.id}','0','您确定要启用该条数据信息吗？');">启用</a>
					</c:if>
    				<a href="<c:url value='${fns:getAdminPath()}/sys/dict/form?type=${dict.type}&sort=${dict.sort+10}'><c:param name='description' value='${dict.description}'/></c:url>">添加键值</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="row">${page}</div>
</div>
</body>
</html>