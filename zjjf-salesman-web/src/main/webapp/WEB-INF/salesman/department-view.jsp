<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>阿街足迹</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <%@ include file="../common/head.jsp"%>
    <script src="${root}/resources/js/comm.js"></script>
    <%@ include file="../common/autocomplete.jsp"%>
    <script>
    	var rootPath="${root}";
    </script>
</head>
<body class="wrap-bd">
<div class="title mb-default" id="title">添加/编辑用户</div>
<div class="wrap-bd" style="background: #fff">
	<div>
	  <form id="editDeptForm">
	  	<input type="hidden" name="id" value="${dept.id}">
		<b>基本信息</b>
        <p>
            <label class="label" id="codeLabel">编号 <span style="color: red;">*</span>：</label>
            <input type="text" id="deptId" name="deptId" value="${dept.deptId}" class="input-search-text" readonly="readonly">
        </p>
        <p>
            <label class="label" id="nameLabel">名称<span style="color: red;">*</span> ：</label>
            <input type="text" id="deptName" name="deptName" value="${dept.deptName}" class="input-search-text" readonly="readonly">
        </p>
        <p>
            <label class="label" id="deptLabel">上级部门<span style="color: red;">*</span>：</label>
            <select id="deptSelect" name="pid" style="width:209px;" disabled="disabled">
                <option value="">请选择</option>
                <c:forEach var="deptList" items="${deptList}">
              	  <option value="${deptList.deptId}" <c:if test="${deptList.deptId==dept.pid}">selected="selected"</c:if> >${deptList.deptName}</option>
                </c:forEach>
            </select>
        </p>
        <p>
            <label class="label">备注：</label>
			<textarea rows="5" cols="50" name="remarks" value="${dept.remarks}" readonly="readonly">${dept.remarks}</textarea>
        </p>
       <p>
			<label class="label">部门领导：</label>
			<table class="table-list table-border">
				<thead class="table-thead">
					<tr class="table-header">
	            		<th>序号</th>
	            		<th>账号</th>
	            		<th>姓名</th>
	            		<th>部门</th>
	            		<th>职位</th>
					</tr>
				</thead>
				<tbody class="table-tbody">
					<c:forEach var="list" items="${leaderList}" varStatus="status">
						<tr>
							<td>
								${status.index+1}
							</td>
							<td>
								${list.mobile}
								<input type="hidden" name="leaderId" value="${list.id}"/>
							</td>
							<td>${list.userName}</td>
							<td>${list.deptName}</td>
							<td>${list.postName}</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
        </p> 

	   </form>  		
	</div>
	<div id="submitDiv" style="margin-top: 20px;">
		<button class="button-cancel ml-default" id="cancelButton">返回</button>
	</div>
</div>
<script>
$('#cancelButton').on('click',function() {
	location.href="${root}/dept/index.do";
});
</script>
</body>
</html>