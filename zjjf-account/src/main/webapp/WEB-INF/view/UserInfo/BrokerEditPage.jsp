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
        	<form id="ff" method="post">
        		<c:if test="${Model!=null}">
        			<input type="hidden" value="${Model.id}" name="id"/>
        		</c:if>
	            <table cellpadding="5">
	            	<tr>
	                    <td>名称：</td>
	                    <td><input class="easyui-textbox" type="text" name="name" value="${Model.name}" 
	                    		style="width: 200px;" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>证件类型：</td>
	                    <td>
	                        <select id="certificateType" class="easyui-combobox" panelHeight="auto" name="certificateType" style="width: 200px;" data-options="required:true, editable:false">
                       			<option value="1" <c:if test="${Model.certificateType==1}">selected="selected"</c:if>>身份证</option>
					        </select>
	                    </td>
	                </tr>
	                <tr>
	                    <td>证件号码：</td>
	                    <td><input class="easyui-textbox" type="text" name="certificateNo" value="${Model.certificateNo}" 
	                    		style="width: 200px;" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>电话：</td>
	                    <td><input class="easyui-numberbox" type="text" name="phoneNum" value="${Model.phoneNum}" 
	                    		style="width: 200px;" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>地址：</td>
	                    <td><input class="easyui-textbox" type="text" name="address" value="${Model.address}" style="width: 200px;"></input></td>
	                </tr>
	            </table>
        	</form>
        </div>
      	<div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:submitForm()" style="width:80px">确认</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:cancelSave()" style="width:80px">取消</a>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		$('#ff').form({
			url:root+"/admin/brokerctl/EditBroker.do",	
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