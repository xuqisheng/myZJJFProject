<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>退款管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="../common/header.jsp"></jsp:include>
<script type="text/javascript">
	$(function(){
		var mibocrud = MiBoCRUD.creatNew({
			modelName:"refundMg",
			dialogTitle:'退款管理管理',
			listURL:'<c:url value="/admin/refundctl/List.do"/>',
			creatURL:'<c:url value="/admin/refundctl/Add.do"/>',
			updateURL:'<c:url value="/admin/refundctl/Update.do"/>',
			deleteURL:'<c:url value="/admin/refundctl/Delete.do"/>',
			findListFun:function(){
				$("#refundMgdg" ).datagrid('load',{
					orderNo: $("#orderNo").textbox('getValue'), 
					patientName: $("#patientName").textbox('getValue'),
					doctorName: $("#doctorName").textbox('getValue'), 
					isDelete:$("#refundMgIsDelete").combobox('getValue'),
					state:$("#state").combobox('getValue')
				})
			}
		}).init();
	});
	
	function formatState(val, row){
		if(val == 0){
			return "申请";
		}else if(val == 1){
			return "完成";
		}else{
			return "其它";
		}
	}
	
	function formatPayWay(val, row){
		if(val == 1){
			return "支付宝";
		}else if(val == 2){
			return "快钱";
		}else if(val == 3){
			return "微信";
		}else{
			return "其它";
		}
	}
	
	function formatOper(val,row,index){
		return '<a href="#" onclick="orderRefund(\''+row.id+'\',\''+row.orderId+'\','+row.state+','+row.payWay+')">微信退款</a>';
	}
	
	function orderRefund(id, orderId, state, payWay){
   		if(state == 1){
   			alertMsg("此申请已完成退款");
   			return;
   		}
   		if(payWay != 3){
   			alertMsg("暂时仅支持微信退款");
   			return;
   		}
   		$.messager.confirm('提示', "是否确认退款？", function(r){
			if(r){
				$.ajax({
					type: "POST",
					url: root+"/admin/refundctl/orderRefund.do",
					data: {id:id, orderId:orderId},
					success: function(msg){
						$("#refundMgdg" ).datagrid("reload");
					},
					error: function (XMLHttpRequest, textStatus, errorThrown) {
					 	// 通常 textStatus 和 errorThrown 之中
					 	// 只有一个会包含信息
					 	this; // 调用本次AJAX请求时传递的options参数
					 	$.messager.alert('抱歉', errorThrown, 'warning');
					}
				});
			}
		});
   	}
</script>
</head>
<body>
<!-- -------------------------------------------退款表------------------------------------------ -->
 <table id="refundMgdg" 
	idField="id"  sortName="refundApplyTime" sortOrder="desc"  pageSize =50
	toolbar="#refundMgtoolbar" pagination="true" fitColumns="true"  fit="true"
	data-options="rownumbers:true,singleSelect:true,method:'post',queryParams:{state:0}">
    <thead>
        <tr>
        	<th field="patientId" width="100" sortable="true" editor="text" hidden="true">患者信息</th>
        	<th field="orderId" width="100" sortable="true" editor="text" hidden="true">订单信息</th>
			<!-- <th field="aliNo" width="150" sortable="true" editor="text">支付宝帐号</th> -->
        	<th field="orderNo" width="150" sortable="true" editor="text">订单编号</th>
        	<th field="serviceName" width="80" sortable="true" editor="text">订单类型</th>
        	<th field="patientName" width="80" sortable="true" editor="text">患者名</th>
        	<th field="patientPhoneNum" width="100" sortable="true" editor="text">患者电话</th>
        	<th field="doctorName" width="80" sortable="true" editor="text">医生名</th>
        	<th field="doctorPhoneNum" width="100" sortable="true" editor="text">医生电话</th>
        	<th field="amount" width="80" sortable="true" editor="text">退款金额</th>
        	<th field="refundApplyTime" width="100" sortable="true" editor="text">退款申请时间</th>
        	<th field="refundGetTime" width="100" sortable="true" editor="text" >退款到达时间</th>
        	<th field="remark" width="200" sortable="true" editor="text" >退款缘由</th>
        	<th field="payWay" width="80" sortable="true" formatter="formatPayWay">支付方式</th>
        	<th field="state" width="80" sortable="true" formatter="formatState">状态</th>
        	<th field="isDelete" width="60" sortable="true" editor="text" formatter="publicFormatDel">是否删除</th>
        	<th data-options="field:'_operate',width:60,align:'center',formatter:formatOper">操作</th>
        </tr>
    </thead>
</table>
<div id="refundMgtoolbar">
    <!-- 订单ID:<input id="refundMgField1"class="easyui-textbox" style="width:140px;">
    患者ID:<input id="refundMgField2"class="easyui-textbox" style="width:140px;"> -->
    订单号:<input id="orderNo"class="easyui-textbox" style="width:140px;"/>
    患者名:<input id="patientName"class="easyui-textbox" style="width:140px;"/>
    医生名:<input id="doctorName"class="easyui-textbox" style="width:140px;"/>
    状态: 
    <select id="state" class="easyui-combobox" panelHeight="auto" style="width:100px" >
        <option value="0">申请</option>
        <option value="1">完成</option>
        <option value="" >全部</option>
    </select>
    是否删除: 
    <select id="refundMgIsDelete" class="easyui-combobox" panelHeight="auto" style="width:100px" >
        <option value="false" selected="selected">否</option>
        <option value="true">是</option>
        <option value="" >全部</option>
    </select>
    <a id="refundMgtoolbar-findlist"  class="easyui-linkbutton" iconCls="icon-search" plain="true"  >查询</a>
</div>
</body>
</html>
