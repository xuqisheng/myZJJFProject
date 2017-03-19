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
	        			<input type="hidden" value="${Model.userId}" name="userId"/>
	        			<input type="hidden" value="${Model.bankUserName}" name="bankUserName">
	        		</c:if>
		            <table cellpadding="5">
		                <tr>
		                    <td>交易流水号：</td>
		                    <td>
		                    	<input class="easyui-textbox" type="text" value="${Model.code}" name="code" style="width:300px"></input>
		                   	</td>
		            	</tr>
		            	<tr>
		            		<td>提现金额：</td>
		                    <td>
		                    	<input class="easyui-textbox" type="text" value="${Model.amount}" name="amount" readonly style="width:300px"></input>
		                   	</td>
		            	</tr>
		            	<tr>
		            		<td>账户余额：</td>
		                    <td>
		                    	<input class="easyui-textbox" type="text" value="${Model.overage}" name="overage" readonly style="width:300px"></input>
		                   	</td>
		            	</tr>
		            	<tr>
		            		<td>银行卡号：</td>
		                    <td>
		                    	<input class="easyui-textbox" type="text" value="${Model.bankNo}" name="bankNo" readonly style="width:300px"></input>
		                   	</td>
		            	</tr>
		            	<tr>
		                    <td>所属银行：</td> 
		                    <td>
		                        <select class="easyui-combobox" panelHeight="auto" name="issuingBank" readonly style="width:300px">
		                        	<option value="0" <c:if test="${Model.issuingBank==0}">selected="selected"</c:if>>支付宝</option>
		                        	<option value="1" <c:if test="${Model.issuingBank==1}">selected="selected"</c:if>>中国工商银行</option>
						            <option value="2" <c:if test="${Model.issuingBank==2}">selected="selected"</c:if>>中国建设银行</option>
						            <option value="3" <c:if test="${Model.issuingBank==3}">selected="selected"</c:if>>中国光大银行</option>
						            <option value="4" <c:if test="${Model.issuingBank==4}">selected="selected"</c:if>>中国交通银行</option>
						            <option value="${Model.issuingBank}" 
						            	<c:if test="${Model.issuingBank>4 || Model.issuingBank<0}">selected="selected"</c:if>>其它</option>
						        </select>
		                    </td>
		                </tr>
		                <!-- <tr>
		                	<td>创建时间：</td>
		                	<td>
		                		<input class="easyui-datetimebox" type="text" value="${Model.createTime}" readonly style="width:300px"></input>
		                	</td>
		                </tr> -->
		                <tr>
		                    <td>审核结果：</td>
		                    <td>
		                        <select id="state" class="easyui-combobox" panelHeight="auto" name="state" 
		                        		style="width:300px" data-options="required:true">
		                        	<option value="0" <c:if test="${Model.state==0}">selected="selected"</c:if>>未审核</option>
						            <option value="1" <c:if test="${Model.state==1}">selected="selected"</c:if>>审核通过</option>
						            <option value="2" <c:if test="${Model.state==2}">selected="selected"</c:if>>审核不通过</option>
						            <option value="3" <c:if test="${Model.state==3}">selected="selected"</c:if>>打款完成</option>
						        </select>
		                    </td>
		                </tr>
		                <tr>
		                    <td>描述：</td>
		                    <td>
		                    	<textarea name="remark" data-options="multiline:true" maxlength="50"
									style="width:300px; height:60px">${Model.remark}</textarea>
		                    	<!-- <input class="easyui-textbox" name="remark" data-options="multiline:true" 
		                    		style="width:300px; height:60px" maxlength="50" value="${Model.remark}"></input> -->
		                    </td>
		                </tr>
		            </table>
	        	</form>
	        </div>
	        <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
	        	<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" 
	        		onclick="javascript:submitForm()" style="width:80px">审核</a>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" 
					onclick="javascript:cancelSave()" style="width:80px">取消</a>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		$(function(){
			$('#ff').form({
				url:root+"/CustomerServer/CheckWithdrawals.do",	
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
			var oldState = "${Model.state}"; // 原先状态
			var newState = $("#state").combobox("getValue"); // 审核后的状态
			if(oldState == newState){
				alert("审核结果未变，没做处理");
				closeWindow(this);
				return;
			}
			$('#ff').submit();
		}
		
		function cancelSave(){
			closeWindow();
		}
	</script>
</html>