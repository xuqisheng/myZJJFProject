<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<jsp:include page="../common/header.jsp"></jsp:include>
	</head>
	<body>
		<div class="easyui-layout" data-options="fit:true" style="padding:5px;">
	       	<div style="padding:10px 60px 20px 60px">
	        	<form id="ff" method="post">
	        		<c:if test="${Model!=null}">
	        			<!-- 订单id -->
	        			<input type="hidden" value="${Model.id}" name="orderId"/>
	        			<input type="hidden" value="${Model.serviceType}" name="serviceType"/>
	        			<input type="hidden" value="${Model.customerServerWorkRecordId}" name="customerServerWorkRecordId"/>
	        		</c:if>
		            <table cellpadding="5">
		                <tr>
		                    <td>订单编号：</td>
		                    <td>
		                    	<input class="easyui-textbox" type="text" name="orderNo" value="${Model.orderNo}" 
		                    		readonly style="width:300px" data-options="required:true"></input>
		                   	</td>
		            	</tr>
		            	<tr>
		            		<td>服务类型：</td>
		            		<td>
		            			<select class="easyui-combobox" panelHeight="auto" style="width:300px" 
		            					name="serviceType" readonly data-options="required:true">
						            <option value="1" <c:if test="${serviceType==1}">selected="selected"</c:if>>预约咨询</option>
						            <option value="2" <c:if test="${serviceType==2}">selected="selected"</c:if>>预约住院</option>
						        </select>
		            		</td>
		            	</tr>
		            	<tr>
		            		<td>医生：</td>
		            		<td>
		            			<input id="doctorId" name="doctorId" style="width:300px" required="true"/>
		            		</td>
		            	</tr>
		                <tr>
		                	<td>期望时间：</td>
		                	<td>
		                		<input class="easyui-datetimebox" type="text" value="${Model.hopeTime}" readonly style="width:300px"></input>
		                	</td>
		                </tr>
		                <tr>
		                	<td>确定时间：</td>
		                	<td>
		                		<input class="easyui-datetimebox" type="text" name="newSureTime" value="${Model.sureTime}" 
		                			 style="width:300px" data-options="required:true"></input>
		                	</td>
		                </tr>
		                <tr>
		                    <td>描述：</td>
		                    <td>
		                    	<textarea id="remark" name="remark" data-options="multiline:true" maxlength="1000"
									style="width:300px; height:180px">${Model.remark}</textarea>
		                    	<!-- <input class="easyui-textbox" name="remark" data-options="multiline:true" 
		                    		style="width:300px; height:60px" maxlength="100" value="${Model.remark}"></input> -->
		                    </td>
		                </tr>
		            </table>
	        	</form>
	        </div>
	        <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
	        	<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" 
	        		onclick="javascript:submitForm()" style="width:80px">确认就诊</a>
				<!-- <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" 
					onclick="javascript:cancelSave()" style="width:80px">调剂失败</a> -->
			</div>
		</div>
	</body>
	<script type="text/javascript">
		$(function(){
			$('#ff').form({
				url:root+"/CustomerServer/SaveOrderOprLog.do?",	
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');
				},
				success:function(data){
					var data = eval('(' + data + ')');
					if (data.success){
						closeWindow(this);
					}else{
						alertMsg(data.message);
					}
				},
				error:function(){
					alertMsg("加载失败，请检查网络！");
				}
			});
			
			$('#doctorId').combogrid({
			 	url: root+'/admin/doctorctl/List.do',
				queryParams:{isDelete:0},
				fitColumns:true,
				pagination:true,
				panelWidth:300,
				idField:"id",
				textField:"name",
				mode: 'remote',
				onShowPanel:function(){
					$('#doctorId').combogrid("grid").datagrid("load");
				},
				columns:[[
					{field:'id',title:'id',width:160},
				 	{field:'name',title:'医生',width:160},
				 	{field:'telNo',title:'电话',width:160},
				 	{field:'rank',title:'等级',width:160}
				]],
				onLoadSuccess: function(){
					$('#doctorId').combogrid('setValue', '${Model.doctorId}').combogrid('setText', '${Model.doctorName}'); 
				},
				// 点击刷新按钮有效果
				keyHandler:{
					query: function(keyword) {
						//设置查询参数
						var queryParams = $('#doctorId').combogrid('grid').datagrid('options').queryParams;
						queryParams.name = keyword;
						('#doctorId').combogrid('grid').datagrid('options').queryParams = queryParams;
						//重新加载
						$('#doctorId').combogrid('grid').datagrid('reload');
						$('#doctorId').combogrid('setValue', keyword);
		            }
				}
			});
			
		});
		
		function submitForm(){
			$('#ff').submit();
		}
		
		// 暂时已弃用
		function cancelSave(){
			$.ajax({
				type: "POST",
				url: root+"/CustomerServer/Unadjustable.do",
				data: {remark:$("#remark").val()},
				success: function(msg){
				},
				error: function (XMLHttpRequest, textStatus, errorThrown) {
				 	this; // 调用本次AJAX请求时传递的options参数
				 	$.messager.alert('抱歉', errorThrown, 'warning');
				}
			});
			closeWindow();
		}
	</script>
</html>