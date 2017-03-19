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
		            <table cellpadding="5">
		                <tr>
		                    <td>发送人:</td>
		                    <td>
		                    	<input  class="easyui-textbox" type="text" style="width:300px" value="${Model.publishName}"></input>
		                   	</td>
		            	</tr>
		            	<tr>
		                	<td>发送人号码：</td>
		                	<td>
		                		<input readonly class="easyui-textbox" type="text" value="${Model.publishPhoneNum}" style="width:145px"></input>
		                	</td>
		                </tr>
		                <tr>
		                    <td>内容:</td>
		                    <td>
		                    	<input readonly class="easyui-textbox" value="${Model.data}"
		                    		data-options="multiline:true" style="width:300px; height:60px"></input>
		                    </td>
		                </tr>
		                
		                <tr>
		                	<td>发送时间：</td>
		                	<td>
		                		<input readonly class="easyui-datetimebox" type="text" value="${Model.createTime}" style="width:300px"></input>
		                	</td>
		                </tr>
		            </table>
	        	</form>
	        </div>
		</div>
	</body>
</html>