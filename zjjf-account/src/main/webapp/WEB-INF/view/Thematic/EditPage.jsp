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
        	<form id="ff" method="post" enctype="multipart/form-data">
        		<c:if test="${Model!=null}">
        			<input type="hidden" value="${Model.id}" name="id" id="id"/>
        		</c:if>
	            <table cellpadding="5">
	            	<tr>
	                    <td>名称：</td>
	                    <td><input class="easyui-textbox" type="text" name="name" value="${Model.name}" 
	                    		style="width: 155px;" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>热度：</td>
	                    <td><input class="easyui-numberbox" type="text" name="hotCount" value="${Model.hotCount}" 
	                    		style="width: 155px;" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>排序：</td>
	                    <td><input class="easyui-numberbox" type="text" name="orderIndex" value="${Model.orderIndex}" 
	                    		style="width: 155px;" data-options="required:true"></input></td>
	                </tr>
	                <!-- <tr>
	                    <td>是否删除：</td>
	                    <td>
	                        <select id="delete" class="easyui-combobox" panelHeight="auto" name="delete" style="width: 155px;" data-options="required:true">
	                        	<c:if test="${Model!=null}">
                        			<option value="0" <c:if test="${Model.delete==false}">selected="selected"</c:if>>否</option>
                        			<option value="1" <c:if test="${Model.delete==true}">selected="selected"</c:if>>是</option>
	                        	</c:if>
					        </select>
	                    </td>
	                </tr> -->
	                <tr>
	                    <td>状态：</td>
	                    <td>
	                        <select id="state" class="easyui-combobox" panelHeight="auto" name="state" style="width: 155px;" data-options="required:true">
	                        	<c:if test="${Model!=null}">
                        			<option value="0" <c:if test="${Model.state==0}">selected="selected"</c:if>>可用</option>
                        			<option value="1" <c:if test="${Model.state==1}">selected="selected"</c:if>>不可用</option>
	                        	</c:if>
					        </select>
	                    </td>
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
			url:root+"/admin/thematic/Add.do",	
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