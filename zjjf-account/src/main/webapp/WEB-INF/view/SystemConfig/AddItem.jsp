<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        		<input type="hidden" value="${configId==null ? Model.configId : configId }" name="configId" />
        		<c:if test="${Model!=null }">
        			<input type="hidden" value="${Model.id }" name="id" />
        		</c:if>
	            <table cellpadding="5">
	                <tr>
	                    <td>标签:</td>
	                    <td><input class="easyui-textbox" type="text" name="lable"  value="${Model.lable }" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>值:</td>
	                    <td>
	                    	<input class="easyui-textbox" type="text" name="value"   value="${Model.value }" data-options="required:true"></input>
	                   	</td>
	                </tr>
	                 <tr>
	                    <td>状态:</td>
	                    <td>
	                        <select id="state" class="easyui-combobox" panelHeight="auto"  name="state" style="width: 150px;" data-options="required:true">
	                        	<c:if test="${Model!=null }">
	                        		<c:if test="${Model.state==0 }">
	                        			<option value="0" selected="selected">可用</option>
	                        			<option value="1">不可用</option>
	                        		</c:if>
	                        		<c:if test="${Model.state==1 }">
	                        			<option value="0" >可用</option>
	                        			<option value="1" selected="selected">不可用</option>
	                        		</c:if>
	                        	</c:if>
	                        	<c:if test="${Model==null }">
	                        	   <option value="0">可用</option>
					            	<option value="1">不可用</option>
	                        	</c:if>
					        </select>
	                    </td>
	                </tr>
	                 <tr>
	                    <td>排序:</td>
	                    <td>
	                    	<input class="easyui-textbox" type="text" name="orderIndex"   value="${Model.orderIndex }" data-options="required:true"></input>
	                   	</td>
	                </tr>
	            </table>
        	</form>
        </div>
         <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:submitForm()" style="width:80px">Ok</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:cancelSave()" style="width:80px">Cancel</a>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		$('#ff').form({
			url:root+"/admin/systemconfig/AddItem.do?random="+new Date().getTime(),	
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success:function(data){
				var data = eval('(' + data + ')');
				if (data.success){
					closeWindow();
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
	
	function clearForm(){
		$('#ff').form('clear');
	}
</script>
</html>