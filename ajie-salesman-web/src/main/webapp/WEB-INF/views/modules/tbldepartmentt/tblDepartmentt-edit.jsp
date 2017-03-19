<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%
	request.setAttribute("root", request.getContextPath());
%>
<html>
<head>
<title>部门信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(
			function() {
				//$("#name").focus();
				$("#inputForm")
						.validate(
								{
									submitHandler : function(form) {
										loading('正在提交，请稍等...');
										form.submit();
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#messageBox").text("输入有误，请先更正。");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
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
		<li class="active"><a
			href="${ctx}/tbldepartmentt/tblDepartmentt/form?id=${tblDepartmentt.id}">部门信息<shiro:hasPermission
					name="tbldepartmentt:tblDepartmentt:edit">${not empty tblDepartmentt.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="tbldepartmentt:tblDepartmentt:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="tblDepartmentt"
		action="${ctx}/tbldepartmentt/tblDepartmentt/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="form-group">
			<div>
				<label class="control-label" style="margin-left: 15px;">部门编号:</label>
			</div>
			<div class="col-sm-2">
				<form:input path="deptId" htmlEscape="false" maxlength="16"
					class="form-control " readonly="true" style="background:#CCCCCC" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<label class="control-label" style="margin-left: 15px;">部门名称:</label>
			</div>
			<div class="col-sm-2">
				<form:input path="deptName" htmlEscape="false" maxlength="16"
					class="form-control " />
			</div>
		</div>
		<div class="form-group">
			<div>
				<label class="control-label" style="margin-left: 15px;">上级部门:</label>
			</div>
			<div class="col-sm-2">
				<select name="pid" id="pid">
					<!--显示当前下拉框的值  -->
					<c:forEach items="${list}" var="pidName">
						<option value="${pidName.deptId}"
							<c:if test="${pidName.deptId==tblDepartmentt.pid}">selected
						</c:if>>${pidName.deptName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<div>
				<label class="control-label" style="margin-left: 15px;">备注:</label>
			</div>
			<div class="col-sm-2">
				<form:input path="remarks" htmlEscape="false" maxlength="20"
					class="form-control " />
			</div>
		</div>
		<table>
			<p>
				<label class="label">部门领导：</label> <a
					style="margin-left: 10px; cursor: hand;" id="openLeaderList"
					href="#">添加</a>
			<table id="xiaojg" class="table-list table-border" border="0" style="border:1px solid #DC143C;" cellpadding="0"cellspacing="1" >
				<thead class="table-thead">
					<tr class="table-header">
						<th width="150px;">序号</th>
						<th width="150px;">账号</th>
						<th width="150px;">姓名</th>
						<th width="150px;">部门</th>
						<th width="150px;">职位</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody class="table-tbody" id="departmentTbody">
					<c:forEach var="userinfo" items="${userinfo}" varStatus="status">
						<tr>

							<td>${status.index+1}</td>
							<td>${userinfo.mobile}</td>
							<td>${userinfo.userName}</td>
							<td>${userinfo.deptName}</td>
							<td>${userinfo.parentName}</td>
							<td><a onclick="deluser(this,'${userinfo.sid}');">删除</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</p>
		</table>
		<div class="form-actions">
			<shiro:hasPermission name="tbldepartmentt:tblDepartmentt:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="javascript :history.go(-1);" />
		</div>
	</form:form>
	<script type="text/javascript">
		$('#openLeaderList')
				.on(
						'click',
						function() {
							var deptId = $("#deptId").val();
							layer
									.open({
										type : 2,
										title : '',
										maxmin : true,
										shadeClose : true, //点击遮罩关闭层
										area : [ '900px', '620px' ],
										content : '${ctx}/tbldepartmentt/tblDepartmentt/userlist?deptId='
												+ deptId
									});
						});

		//选择用户列表回调页面
		function selectUserCallback(obj) {
			console.log(obj);
			$(".table-tbody").eq(0).append(obj);
			layer.closeAll('iframe'); //关闭所有的iframe层
			resetSeq();
		}
		//刷新序号
		function resetSeq() {
			$(".table-tbody").eq(0).find("tr").each(function(index) {
				$(this).find("td:eq(0)").html(index + 1);
			});
		}

		function deluser(obj, sid) {
			if (confirm("确定要删除吗？")){
				$.ajax({
					type : "post",
					url : ctx + "/tbldepartmentt/tblDepartmentt/deluser",
					data : "id=" + sid,
					dataType : "json",
					async : false,
					success : function(data) {
						if (data.success) {
							$.jBox.tip(data.msg, "操作成功", "success");
							$(obj).parent().parent().remove();
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
		}
		$('#submit').on('click', function() {
			//修改部门信息
			updateDeptData();
		});

		/**
		 * 修改部门信息
		 */
		function updateDeptData() {
			var pid = $("#pid").val();
			var deptId = $("#deptId").val();	
			var deptName = $("#deptName").val();
			var remarks = $("#remarks").val();

			if (deptId == "" || deptId == null) {
				alert("部门编码不能为空!");
				return;
			}
			if (deptName == "" || deptName == null) {
				alert("部门名称不能为空!");
				return;
			}

			var regex = /[^\u4e00-\u9fa5a-zA-Z0-9_]/ig;
			if (regex.test(deptName)) {
				alert("部门名称只能包含中英文、数字及下划线等字符！");
				return;
			}
			if (deptName.length > 15) {
				alert("部门名称长度不能超过15个字符！");
				return;
			}
			if (remarks.length > 200) {
				alert("备注长度不能超过200字符！");
				return;
			}
			var regexRmk = /[^\u4e00-\u9fa5a-zA-Z0-9_,.，;；。！!？?\\、%]/ig;
			if (regexRmk.test(remarks)) {
				alert("备注只能包含中英文、数字、下划线及常用标点符号等字符！");
				return;
			}
		}
	</script>
</body>
</html>