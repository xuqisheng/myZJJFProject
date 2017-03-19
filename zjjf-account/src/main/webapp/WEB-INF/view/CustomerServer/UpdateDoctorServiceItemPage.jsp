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
	        			<input type="hidden" value="${Model.id}" name="id"/>
	        			<input type="hidden" value="${Model.doctorId}" name="doctorId"/>
	        		</c:if>
		            <table cellpadding="5">
		                <tr>
		                    <td>医生姓名:</td>
		                    <td>
		                    	<input type="text" name="dtName" value="${Model.dtName}" 
		                    		readonly style="border:none" data-options="required:true"></input>
		                    </td>
		            	</tr>
		            	<tr>
		                    <td>服务项目:</td>
		                    <td>
		                    	<input id="itName" name="type" data-options="required:true"></input>
		                    </td>
		                </tr>
		                <tr>
		                	<td>分成：</td>
		                	<td>
		                		<input class="easyui-numberbox" type="text" name="proportion" value="${Model.proportion}" 
		                			style="width:300px" data-options="required:true, min:0, precision:4"></input>
		                	</td>
		                </tr>
		                <tr>
		                	<td>价格：</td>
		                	<td>
		                		<input class="easyui-numberbox" type="text" name="price" value="${Model.price}" 
		                			style="width:300px" data-options="required:true, min:0, precision:2"></input>
		                	</td>
		                </tr>
		                <tr>
		                    <td>状态：</td>
		                    <td>
		                        <select id="isDelete" class="easyui-combobox" panelHeight="auto" name="isDelete" 
		                        		style="width:300px;" data-options="required:true, editable:false">
		                        	<c:if test="${Model!=null}">
	                        			<option value="0" <c:if test="${Model.isDelete==false}">selected="selected"</c:if>>在用</option>
	                        			<option value="1" <c:if test="${Model.isDelete==true}">selected="selected"</c:if>>删除</option>
		                        	</c:if>
						        </select>
		                    </td>
		                </tr>
		                <tr>
		                    <td>总分:</td>
		                    <td>
		                    	<input name="totalScore" value="${Model.totalScore}" readonly style="border:none"></input>
		                    </td>
		                </tr>
		                <tr>
		                    <td>总次数:</td>
		                    <td>
		                    	<input name="times" value="${Model.times}" readonly style="border:none"></input>
		                    </td>
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
		$(function(){
			$('#ff').form({
				url:root+"/admin/doctorctl/UpdateDoctorServiceItem.do?",	
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
			$("#itName").combobox({
				width: 300,
				editable: false,
				panelHeight: "auto",
				url: root+"/admin/systemconfig/ServiceItemList.do",    
				valueField: 'value',    
				textField: 'lable',
				onLoadSuccess:function(){
			 		$('#itName').combobox('setValue','${Model.type}').combobox('setText','${Model.itName}');
			    } 
			});		
		});
		
		function submitForm(){
			$('#ff').submit();
		}
		
		function cancelSave(){
			closeWindow();
		}
	</script>
</html>