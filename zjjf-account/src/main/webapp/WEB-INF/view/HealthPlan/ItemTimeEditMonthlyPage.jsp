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
						<td>每月：</td>
						<td>
							<select id="date" class="easyui-combobox" panelHeight="200" 
									name="date" style="width:180px" data-options="required:true">
								<option value="1" <c:if test="${Model.date==1}">selected="selected"</c:if>>1</option>
								<option value="2" <c:if test="${Model.date==2}">selected="selected"</c:if>>2</option>
								<option value="3" <c:if test="${Model.date==3}">selected="selected"</c:if>>3</option>
								<option value="4" <c:if test="${Model.date==4}">selected="selected"</c:if>>4</option>
								<option value="5" <c:if test="${Model.date==5}">selected="selected"</c:if>>5</option>
								<option value="6" <c:if test="${Model.date==6}">selected="selected"</c:if>>6</option>
								<option value="7" <c:if test="${Model.date==7}">selected="selected"</c:if>>7</option>
								<option value="8" <c:if test="${Model.date==7}">selected="selected"</c:if>>8</option>
								<option value="9" <c:if test="${Model.date==7}">selected="selected"</c:if>>9</option>
								<option value="10" <c:if test="${Model.date==7}">selected="selected"</c:if>>10</option>
								<option value="11" <c:if test="${Model.date==7}">selected="selected"</c:if>>11</option>
								<option value="12" <c:if test="${Model.date==7}">selected="selected"</c:if>>12</option>
								<option value="13" <c:if test="${Model.date==7}">selected="selected"</c:if>>13</option>
								<option value="14" <c:if test="${Model.date==7}">selected="selected"</c:if>>14</option>
								<option value="15" <c:if test="${Model.date==7}">selected="selected"</c:if>>15</option>
								<option value="16" <c:if test="${Model.date==7}">selected="selected"</c:if>>16</option>
								<option value="17" <c:if test="${Model.date==7}">selected="selected"</c:if>>17</option>
								<option value="18" <c:if test="${Model.date==7}">selected="selected"</c:if>>18</option>
								<option value="19" <c:if test="${Model.date==7}">selected="selected"</c:if>>19</option>
								<option value="20" <c:if test="${Model.date==7}">selected="selected"</c:if>>20</option>
								<option value="21" <c:if test="${Model.date==7}">selected="selected"</c:if>>21</option>
								<option value="22" <c:if test="${Model.date==7}">selected="selected"</c:if>>22</option>
								<option value="23" <c:if test="${Model.date==7}">selected="selected"</c:if>>23</option>
								<option value="24" <c:if test="${Model.date==7}">selected="selected"</c:if>>24</option>
								<option value="25" <c:if test="${Model.date==7}">selected="selected"</c:if>>25</option>
								<option value="26" <c:if test="${Model.date==7}">selected="selected"</c:if>>26</option>
								<option value="27" <c:if test="${Model.date==7}">selected="selected"</c:if>>27</option>
								<option value="28" <c:if test="${Model.date==7}">selected="selected"</c:if>>28</option>
								<option value="29" <c:if test="${Model.date==7}">selected="selected"</c:if>>29</option>
								<option value="30" <c:if test="${Model.date==7}">selected="selected"</c:if>>30</option>
								<option value="31" <c:if test="${Model.date==7}">selected="selected"</c:if>>31</option>
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