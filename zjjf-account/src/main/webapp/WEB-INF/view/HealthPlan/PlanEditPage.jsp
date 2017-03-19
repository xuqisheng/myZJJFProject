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
	       	<div style="padding:10px 10px 10px 60px">
	        	<form id="ff" method="post" enctype="multipart/form-data">
	        		<c:if test="${Model!=null}">
	        			<input type="hidden" value="${Model.id}" name="id"/>
	        		</c:if>
		            <table cellpadding="5">
		            	<tr>
		            		<td></td>
		            		<td>
		            			<img id="pic" name="pic" src="${Model.pic}" style="width:120px; height:110px"/>
		            			<br/>
		            			<input type="file" name="uploadify" id="uploadify" data-options="required:true"/>
		            		</td>
		            	</tr>
		                <tr>
		                    <td>名称：</td>
		                    <td>
		                    	<input class="easyui-textbox" type="text" name="name" value="${Model.name}" 
		                    		style="width:180px" data-options="required:true"></input>
		                   	</td>
		            	</tr>
		            	<tr>
		                    <td>关注次数：</td>
		                    <td>
		                    	<input class="easyui-numberbox" type="text" name="count" value="${Model.count}" 
		                    		readonly style="width:180px"></input>
		                   	</td>
		            	</tr>
		            	<tr>
		                    <td>是否推荐：</td>
		                    <td>
		                        <select id="isRecommend" class="easyui-combobox" panelHeight="auto" name="isRecommend" 
		                        		style="width:180px" data-options="required:true">
	                        	   	<option value="0" <c:if test="${Model.isRecommend==0}">selected="selected"</c:if>>否</option>
					            	<option value="1" <c:if test="${Model.isRecommend==1}">selected="selected"</c:if>>是</option>
						        </select>
		                    </td>
		                </tr>
		                <!-- <tr>
		                    <td>是否在用：</td>
		                    <td>
		                        <select id="delete" class="easyui-combobox" panelHeight="auto" name="delete" 
		                        		style="width:180px" data-options="required:true">
	                        	   	<option value="0" <c:if test="${Model.delete==false}">selected="selected"</c:if>>是</option>
					            	<option value="1" <c:if test="${Model.delete==true}">selected="selected"</c:if>>否</option>
						        </select>
		                    </td>
		                </tr> -->
		                <tr>
		                    <td>描述：</td>
		                    <td>
		                    	<textarea name="description" data-options="multiline:true" maxlength="50"
									id="description" style="width:180px; height:60px">${Model.description}</textarea>
		                    	<!-- <input class="easyui-textbox" name="description" data-options="multiline:true" 
		                    		style="width:180px; height:60px" value="${Model.description}"></input> -->
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
				url:root+"/admin/healthplanctl/EditPlan.do?",	
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
		});
		
		function submitForm(){
			if($.trim($("#description").val()).length == 0){
				alert("描述必填");
				return;
			}
			$('#ff').submit();
		}
		
		function cancelSave(){
			closeWindow();
		}
	</script>
</html>