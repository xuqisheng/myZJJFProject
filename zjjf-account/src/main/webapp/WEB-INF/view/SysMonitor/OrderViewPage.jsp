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
	        			<input type="hidden" value="${Model.id}" name="id" />	
	        		</c:if>
		            <table cellpadding="5">
		                <tr>
		                    <td>订单编号:</td>
		                    <td>
		                    	<input  class="easyui-textbox" type="text" style="width:300px" value="${Model.orderNo}"></input>
		                   	</td>
		            	</tr>
		            	<tr>
		                	<td>医生：</td>
		                	<td>
		                		<input readonly class="easyui-textbox" type="text" value="${Model.doctorName}" style="width:145px"></input>
		                		<input readonly class="easyui-textbox" type="text" value="${Model.doctorTelNo}" style="width:145px"></input>
		                	</td>
		                </tr>
		                <tr>
		                	<td>患者：</td>
		                	<td>
		                		<input readonly class="easyui-textbox" type="text" value="${Model.patientName}" style="width:145px"></input>
		                		<input readonly class="easyui-textbox" type="text" value="${Model.patientTelNo}" style="width:145px"></input>
		                	</td>
		                </tr>
		                <tr>
		                	<td>订单类型：</td>
		                	<td>
		                		<input readonly class="easyui-textbox" type="text" value="${Model.lable}" style="width:300px"></input>
		                	</td>
		                </tr>
		                <tr>
		                	<td>单价：</td>
		                	<td>
		                		<input readonly class="easyui-textbox" type="text" value="${Model.price}" style="width:300px"></input>
		                	</td>
		                </tr>
		                <tr>
		                	<td>期望时间：</td>
		                	<td>
		                		<input readonly class="easyui-datetimebox" type="text" value="${Model.hopeTime}" style="width:300px"></input>
		                	</td>
		                </tr>
		                <tr>
		                	<td>实际时间：</td>
		                	<td>
		                		<input readonly class="easyui-datetimebox" type="text" value="${Model.sureTime}" style="width:300px"></input>
		                	</td>
		                </tr>
		            	<tr>
		                    <td>订单状态:</td>
		                    <td>
		                        <select readonly class="easyui-combobox" panelHeight="auto" style="width:300px">
                        			<option value="0" <c:if test="${Model.state==0}">selected="selected"</c:if>>未服务</option>
                        			<option value="1" <c:if test="${Model.state==1}">selected="selected"</c:if>>支付完成</option>
                        			<option value="2" <c:if test="${Model.state==2}">selected="selected"</c:if>>资料已提交</option>
                        			<option value="3" <c:if test="${Model.state==3}">selected="selected"</c:if>>申请退款</option>
                        			<option value="4" <c:if test="${Model.state==4}">selected="selected"</c:if>>已完结</option>
                        			<option value="5" <c:if test="${Model.state==5}">selected="selected"</c:if>>已确认</option>
						        </select>
		                    </td>
		                </tr>
		                <tr>
		                    <td>描述:</td>
		                    <td>
		                    	<input readonly class="easyui-textbox" value="${Model.remark}"
		                    		data-options="multiline:true" style="width:300px; height:60px"></input>
		                    </td>
		                </tr>
		            </table>
	        	</form>
	        </div>
		</div>
	</body>
</html>