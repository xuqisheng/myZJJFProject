<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>支付信息管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="../common/header.jsp"></jsp:include>
<script type="text/javascript">

/**
 * 格式化订单id
 */
function payMgFormatOrderInfo(value, row){
	return '<a href="#" onclick="getOrderInfoByNo(\'' + value + '\')">'+value+'</a>';
}
/**
 * 订单查看事件
 */
 function getOrderInfoByNo(rowIndex,rowData){
	 if (rowData){
		 $('#orderInfoMgdg').datagrid({
			 url:'<c:url value="/admin/orderInfoctl/List.do"/>',
			 queryParams:{ orderNo:rowData.orderNo}
		});
			//$.post('<c:url value="/admin/payctl/getOrderInfoByNo.do"/>',{
			//	no:rowData.orderNo
			//},function(result){
			//	if( result ){	
			//		$('#orderInfodlg').dialog('open').dialog('setTitle','订单信息');
			//		$('#orderInfodlgfm').form('load',result);
			//	}
			//},'json');
	}
}
	
	
$(function(){
	var mibocrud = MiBoCRUD.creatNew({
		modelName:"payMg",
		dialogTitle:'付款信息管理',
		listURL:'<c:url value="/admin/payctl/List.do"/>',
		creatURL:'<c:url value="/admin/payctl/Add.do"/>',
		updateURL:'<c:url value="/admin/payctl/Update.do"/>',
		deleteURL:'<c:url value="/admin/payctl/Delete.do"/>',
		findListFun:function(){
			$("#payMgdg" ).datagrid('load',{
				orderNo:$("#payMgField1").textbox('getValue'),
				amount:$("#payMgField2").textbox('getValue'),
				payWay:$("#payMgPayWay").combobox('getValue'),
				state:$("#payMgState").combobox('getValue'),
				isDelete:$("#payMgIsDelete").combobox('getValue')
			})
		}
	}).init();
});
</script>
<style type="text/css">
#orderInfodlgfm input{width:260px;}
#orderInfodlgfm label{text-align: right}
</style>
</head>
<body>
<div class="easyui-layout"  fit="true"><!-- bord div -->
<div region="center" ><!-- ceter div -->
<!-- -------------------------------------------支付表------------------------------------------ -->
 <table id="payMgdg" 
	idField="id"  sortName="id" sortOrder="asc"  pageSize =50
	toolbar="#payMgtoolbar" pagination="true" fitColumns="true"  fit="true"
	data-options="rownumbers:true,singleSelect:true,method:'post',onClickRow:getOrderInfoByNo">
    <thead>
        <tr>
	        <th field="orderNo" sortable="true" editor="text" width="160">订单编号</th>
	        <th field="orderAmount" sortable="true" editor="text" width="80">订单金额</th>
	        <th field="payWay" sortable="true" editor="text" formatter="publicFormatmiboUserPayType" width="80">支付渠道</th>
	        <th field="bankId" sortable="true" editor="text" width="100">银行流水号</th>
	        <th field="payNo" sortable="true" editor="text" width="100">支付流水号</th>
	        <th field="payAmount" sortable="true" editor="text" width="80">实际支付金额</th>
	        <th field="fee" sortable="true" editor="text" width="100">支付平台手续费</th>
	        <th field="quantity" sortable="true" editor="text" width="100">商品数量</th>
	        <th field="singPrice" sortable="true" editor="text" width="100">商品单价</th>
	        <th field="payType" sortable="true" editor="text" width="100">用户支付途径</th>
	        <th field="dealState" sortable="true" editor="text" width="100">交易状态</th>
	        <th field="dealCreatTime" sortable="true" editor="text" width="150">用户下单时间</th>
	        <th field="dealPayTime" sortable="true" editor="text" width="150">用户支付时间</th>
	        <th field="createTime" sortable="true" editor="text" width="150">通知时间</th>
	        <th field="remark" sortable="true" editor="text" width="100">备注</th>
	        <th field="state" sortable="true" editor="text" width="100">状态</th>
	        <th field="isDelete" sortable="true" editor="text" formatter="publicFormatDel" width="80">是否删除</th>
        </tr>
    </thead>
</table>
<div id="payMgtoolbar">
	订单编号:<input id="payMgField1" class="easyui-textbox" style="width:140px;">
    金额:<input id="payMgField2" class="easyui-numberbox"   precision="2"  maxlength="16"   style="width:140px;">
    支付方式: 
    <select id="payMgPayWay" class="easyui-combobox" panelHeight="auto" style="width:100px" >
        <option value="0">支付宝</option>
        <option value="1" >快钱支付</option>
        <option value=""  selected="selected">全部</option>
    </select>
    支付状态: 
    <select id="payMgState" class="easyui-combobox" panelHeight="auto" style="width:100px" >
        <option value="0" >未支付</option>
        <option value="1">已支付</option>
        <option value=""  selected="selected">全部</option>
    </select>
    是否删除: 
    <select id="payMgIsDelete" class="easyui-combobox" panelHeight="auto" style="width:100px" >
        <option value="false" selected="selected">否</option>
        <option value="true">是</option>
        <option value="" >全部</option>
    </select>
    <a id="payMgtoolbar-findlist"  class="easyui-linkbutton" iconCls="icon-search" plain="true"  >查询</a>
</div>

</div><!-- ceter div -->

<!-- -------------------------------------------原订单信息------------------------------------------ -->
<div region="south" style="height:120px;background: #eee;" >

<div  id="orderInfodlg" class="easyui-dialog"  modal="true" style="min-width:420px;min-height:400px;padding:20px;" closed="true"  >
	<form id="orderInfodlgfm" method="post" >
		<div class="fitem"><label>订单编号:</label><input name="orderNo" class="easyui-textbox"  readonly="true"></div>
		<div class="fitem"><label>医生:</label><input name="doctorId" class="easyui-textbox"  readonly="true"></div>
		<div class="fitem"><label>医生服务类型:</label><input name="serviceType" class="easyui-textbox"  readonly="true"></div>
		<div class="fitem"><label>价格:</label><input name="price" class="easyui-textbox"  readonly="true"></div>
		<div class="fitem"><label>患者:</label><input name="patientId" class="easyui-textbox"  readonly="true"></div>
		<div class="fitem"><label>患者病例:</label><input name="medicalCaseId" class="easyui-textbox"  readonly="true"></div>
		<div class="fitem"><label>患者期望时间:</label><input name="hopeTime" class="easyui-textbox"  readonly="true"></div>
		<div class="fitem"><label>客服确定时间:</label><input name="sureTime" class="easyui-textbox"  readonly="true"></div>
		<div class="fitem"><label>患者号码:</label><input name="telNo" class="easyui-textbox"  readonly="true"></div>
		<div class="fitem"><label>创建时间:</label><input name="createTime" class="easyui-textbox"  readonly="true"></div>
		<div class="fitem"><label>状态:</label><input name="state" class="easyui-textbox"  readonly="true"></div>
    </form>
</div>
<table id="orderInfoMgdg"  fitColumns="true"  fit="true"  class="easyui-datagrid"   data-options="rownumbers:true,singleSelect:true,method:'post'">
    <thead>
        <tr>
        <th field="orderNo" sortable="true" editor="text" width="100">订单编号</th>
        <th field="serviceType" sortable="true" editor="text" width="100">医生服务类型</th>
        <th field="price" sortable="true" editor="text" width="100">价格</th>
        <th field="hopeTime" sortable="true" editor="text" width="100">患者期望时间</th>
        <th field="sureTime" sortable="true" editor="text" width="100">客服确定时间</th>
        <th field="telNo" sortable="true" editor="text" width="100">患者号码</th>
        <th field="createTime" sortable="true" editor="text" width="100">创建时间</th>
         <th field="state" sortable="true" editor="text" formatter="pulbicFormatmiboOrderInfoState" width="100">状态</th>
        <th field="isDelete" sortable="true" editor="text" formatter="publicFormatDel" width="100">是否删除</th>
        </tr>
    </thead>
</table>


</div><!-- east div -->
</div><!-- bord div -->
</body>
</html>
