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
					<input type="hidden" value="${Model.healthPlanId}" name="healthPlanId" />
					<input type="hidden" value="${Model.healthPlanItemId}" name="HealthPlanItemId" />
					<input type="hidden" value="${Model.type}" name="type" />
				</c:if>
				<table cellpadding="5">
					<tr>
						<td>星期：</td>
						<td>
							<select id="date" class="easyui-combobox" panelHeight="auto" 
									name="date" style="width:180px" data-options="required:true">
								<option value="1" <c:if test="${Model.date==1}">selected="selected"</c:if>>一</option>
								<option value="2" <c:if test="${Model.date==2}">selected="selected"</c:if>>二</option>
								<option value="3" <c:if test="${Model.date==3}">selected="selected"</c:if>>三</option>
								<option value="4" <c:if test="${Model.date==4}">selected="selected"</c:if>>四</option>
								<option value="5" <c:if test="${Model.date==5}">selected="selected"</c:if>>五</option>
								<option value="6" <c:if test="${Model.date==6}">selected="selected"</c:if>>六</option>
								<option value="7" <c:if test="${Model.date==7}">selected="selected"</c:if>>日</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>时间：</td>
						<td><input class="easyui-timespinner" name="time" style="width:180px" value="${Model.time}" 
									data-options="showSeconds:true, required:true"></input></td>
					</tr>
					<!-- <tr>
						<td>是否在用：</td>
						<td>
							<select id="delete" class="easyui-combobox" panelHeight="auto" 
									name="delete" style="width:180px" data-options="required:true">
								<option value="0" <c:if test="${Model.delete==false}">selected="selected"</c:if>>是</option>
								<option value="1" <c:if test="${Model.delete==true}">selected="selected"</c:if>>否</option>
							</select>
						</td>
					</tr> -->
				</table>
			</form>
		</div>
		<div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
        	<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" 
        		onclick="javascript:submitForm()" style="width:80px">保存</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" 
				onclick="javascript:cancelSave()" style="width:80px">关闭</a>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		$('#ff').form({
			url : root + "/admin/healthplanctl/EditItemTime.do?",
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