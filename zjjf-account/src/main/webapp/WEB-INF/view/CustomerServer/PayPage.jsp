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
	        			<input type="hidden" value="${Model.orderInfoId}" name="orderInfoId"/>
	        		</c:if>
		            <table cellpadding="5">
		                <%-- <tr>
		                    <td>支付状态：</td>
		                    <td>
		                        <select id="state" class="easyui-combobox" panelHeight="auto" name="state" 
		                        		style="width:300px" data-options="required:true, editable:false">
		                        	<option value="0" <c:if test="${Model.state==0}">selected="selected"</c:if>>未支付</option>
						            <option value="1" <c:if test="${Model.state==1}">selected="selected"</c:if>>已支付</option>
						        </select>
		                    </td>
		                </tr> --%>
		                <tr>
		                    <td>描述：</td>
		                    <td>
		                    	<input id="state" name="state" value="1" type="hidden"/>
		                    	<textarea name="remark" data-options="multiline:true" maxlength="100"
									style="width:300px; height:60px">${Model.remark}</textarea>
		                    	<!-- <input class="easyui-textbox" name="remark" data-options="multiline:true" 
		                    		style="width:300px; height:60px" maxlength="100"value="${Model.remark}"></input> -->
		                    </td>
		                </tr>
		            </table>
	        	</form>
	        </div>
	        <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
	        	<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" 
	        		onclick="javascript:submitForm()" style="width:80px">支付</a>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" 
					onclick="javascript:cancelSave()" style="width:80px">取消</a>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		$(function(){
			$('#ff').form({
				url:root+"/CustomerServer/DoctorPayed.do",	
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
			$('#ff').submit();
		}
		
		function cancelSave(){
			closeWindow();
		}
	</script>
</html>