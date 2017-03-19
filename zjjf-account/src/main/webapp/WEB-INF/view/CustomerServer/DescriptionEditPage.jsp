<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="../common/header.jsp"></jsp:include>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" style="padding: 5px;">
		<div style="padding: 10px 60px 20px 60px">
			<form id="ff" method="post" enctype="multipart/form-data">
				<c:if test="${Model!=null}">
					<input type="hidden" value="${Model.id}" name="id" />
					<input type="hidden" value="${Model.healthPackageId}" name="healthPackageId" />
				</c:if>
				<table cellpadding="5">
					<tr>
						<td>名称：</td>
						<td>
							<select id="item" class="easyui-combobox" name="item" style="width:200px" data-options="editable:false">  
							    <option value="0" <c:if test="${Model.item == 0}">selected="selected"</c:if>>健康习惯短信提醒</option>
							    <option value="1" <c:if test="${Model.item == 1}">selected="selected"</c:if>>家庭医生服务</option>
							    <option value="2" <c:if test="${Model.item == 2}">selected="selected"</c:if>>VIP门诊预约服务</option>
							    <option value="3" <c:if test="${Model.item == 3}">selected="selected"</c:if>>健康体检</option>
							    <option value="4" <c:if test="${Model.item == 4}">selected="selected"</c:if>>健康讲座</option>
							    <option value="5" <c:if test="${Model.item == 5}">selected="selected"</c:if>>健康辅助工具使用</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>内容：</td>
						<td><input class="easyui-textbox" name="description" 
								style="width:200px" value="${Model.description}"></input></td>
					</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
        	<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" 
        		onclick="javascript:submitForm()" style="width:80px">修改</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" 
				onclick="javascript:cancelSave()" style="width:80px">关闭</a>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		$('#ff').form({
			url : root + "/admin/healthPackageCtrl/EditDescription.do",
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data.success) {
					closeWindow(this);
				} else {
					alertMsg(data.message);
				}
			},
			error : function() {
				alertMsg("加载失败，请检查网络！");
			}
		});
	});
	
	function submitForm() {
		$('#ff').submit();
	}
	
	function cancelSave() {
		closeWindow();
	}
</script>
</html>