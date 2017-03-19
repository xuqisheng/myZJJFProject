<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
function FormatCornerSpOrderOprStatusOpr(value, row) {
	var result ;
	if(value == 1){
		result = '<a href="#" onclick="dofeeAccountSpShengAction(2,\'' + row.spOrderUUID + '\')">审核</a>';
	}else if(value == 2){
		result = '<a href="#" onclick="dofeeAccountSpShengAction(1,\'' + row.spOrderUUID + '\')">取消审核</a>';
	}else if(value > 2){
		result ="<span style='color:blue'>不可操作</span>";
	}else{
		result ="<span style='color:red'>状态异常</span>";
	}
	result +=' | <a href="#" onclick="getfeeSpShengSpOrderInfoDetail(\'' + row.spOrderId + '\')">详情</a>';
	return result;
}
function FormatAcFeeShengheStatus(value, row) {
	var result ;
	if(value == 2){
		result = "<span style='color:green'>已审核</span>"
	}else if(value == 1){
		result ="<span style='color:red'>未审核</span>";
	}else if(value == 3){
		result = "<span style='color:blue'>结算中</span>"
	}else if(value == 4){
		result = "<span style='color:blue'>已结算</span>"
	}else{
		result ="<span style='color:red'>状态异常</span>";
	}
	return result;
}

</script>

<div class="easyui-layout"  fit="true" id="settleOrderListMgdgLayout">
	<div region="center">
		<!--=================================================west-table========================================================  -->
		<table id="settleOrderListMgdg" idField="spOrderUUID"  sortName="ackTime" sortOrder="desc"  pageSize="50"
			toolbar="#settleOrderListMgtoolbar" pagination="true" fitColumns="true"  fit="true"
			data-options="rownumbers:true,singleSelect:false,method:'post'">
		    <thead>
		        <tr>
		        <th data-options="field:'ck',checkbox:true"></th>
		        <th field="areaId"  sortable="true" >区域ID</th>
		        <th field="supplierCode"  sortable="true">批发商编号</th>
		        <th field="supplierName"  sortable="true" >批发商名称</th>
		        <th field="storeId"  sortable="true" >小店 ID</th>
		        <th field="storeName"  sortable="true" >小店名称</th>
		        <th field="spOrderId"  sortable="true" >订单编号</th>
		        <th field="spGoodsPrice"  sortable="true" >订单市场金额</th>
		        <th field="spOrderPrice"  sortable="true" >订单交易金额</th>
		        <th field="spZmaoli"  sortable="true" >订单总毛利</th>
		        <th field="spZfee"  sortable="true" >订单总费用</th>
		        <th field="zfeeRate">订单费用率</th>
		        <th field="spKFStatus" formatter="publicCornerShengheStatus">客服审核状态</th>
		        <th field="spSPStatus" formatter="publicCornerShengheStatus">供应商审核状态</th>
		        <th field="spAcStatus" formatter="FormatAcFeeShengheStatus">财务审核状态</th>
		        <th field="optAcStatus" formatter="FormatCornerSpOrderOprStatusOpr">操作</th>
		        </tr>
		    </thead>
		</table>
		<!--===================================================west-table-toolbar=================================================  -->
		<div id="settleOrderListMgtoolbar" style="padding:5px;">
			 <div>
		 		 订单状态：<select id="settleOrderListMgField1" class="easyui-combobox" panelHeight="auto" style="width:70px" >
		 			<option value="-1"  selected="selected">全部</option>
		 			<option value="1">未审核</option>
		 			<option value="2">已审核</option>
				  </select>
			  	小店名或订单编号:<input id="settleOrderListMgField2" class="easyui-textbox" style="width:140px;">
			  	送达时间：    <input id="settleOrderListMgField3" class="easyui-datebox" style="width:150px">
			  	至：<input id="settleOrderListMgField4" class="easyui-datebox" style="width:150px">
		   		<a  id="settleOrderListMgtoolbar-findlist"  class="easyui-linkbutton"  iconCls="icon-search">查询</a>
		   		<a  onclick="dofeeAccountSpShengActionBatch(2);" class="easyui-linkbutton">批量审核</a>
		   		<a  onclick="dofeeAccountSpShengActionBatch(1);" class="easyui-linkbutton">批量撤审</a>
		   		<a  id="settleOrderListMgtoolbar-fullscreen"  class="easyui-linkbutton">全屏</a>
		    </div>
		</div>
	</div>
	<div id="settleOrderListMgLayoutEast"data-options="region:'east',collapsed:true,split:true" title="订单明细" style="width:50%;">
		<!-- -->
	</div>
</div>


<script type="text/javascript">

var mibocrud = MiBoCRUD.creatNew({
	modelName:"settleOrderListMg",
	dialogTitle:"用户信息编辑",
	queryParams:{'supplierId':'${supplierId}',acStatus:-1},
	listURL:'<c:url value="/account/billsettlectl/SpList.do"/>',
	creatURL:'<c:url value="/account/billsettlectl/SpAdd.do"/>',
	updateURL:'<c:url value="/account/billsettlectl/SpUpdate.do"/>',
	deleteURL:'<c:url value="/account/billsettlectl/SpDelete.do"/>',
	findListFun:function(){
		$("#settleOrderListMgdg" ).datagrid('load',{
			supplierId:'${supplierId}',
			acStatus:$("#settleOrderListMgField1").combobox('getValue'),
			spKeyword:$("#settleOrderListMgField2").textbox('getValue'),
			beginTime:$("#settleOrderListMgField3").datebox('getValue')+" 00:00:00",
			endTime:$("#settleOrderListMgField4").datebox('getValue')+" 23:59:59"
		})
	}
}).init();

//全屏
var feeScreenFlag=true;
$("#settleOrderListMgtoolbar-fullscreen").off("click").on("click",function(){
	if(feeScreenFlag){
		$('#publicLayout').layout('collapse','north');
		$('#publicLayout').layout('collapse','west');
		$('#settleOrderListMgdgLayout').layout('collapse','east');
		$(this).linkbutton({text:"退出全屏"});
		feeScreenFlag=false;
	}else{
		$('#publicLayout').layout('expand','north');
		$('#publicLayout').layout('expand','west');s
		$('#settleOrderListMgdgLayout').layout('expand','east');
		$(this).linkbutton({text:"全屏"});
		feeScreenFlag=true;
	}
});

//审核-取消审核
function dofeeAccountSpShengAction(status,spOrderId){
	if(spOrderId && spOrderId.length > 0 && status >0){
		gofeeAccountSpShengAction(status,spOrderId);
	}else{
		$.messager.alert("信息","请选择订单");
	}
	
}

//选择多列
function getSettleSelections(id) {
	var ss = [];
	var rows = $('#' + id).datagrid('getSelections');
	for (var i = 0; i < rows.length; i++) {
		var row = rows[i];
		ss.push(row.spOrderUUID);
	}
	return ss;
}

//批量处理
function dofeeAccountSpShengActionBatch(status){
	var spOrderIds = getSettleSelections('settleOrderListMgdg').join(',');
	if( spOrderIds && spOrderIds.length > 0 && status >0){
		gofeeAccountSpShengAction(status,spOrderIds);
	}else{
		$.messager.alert("信息","请选择订单");
	}
}

//核心方法
function gofeeAccountSpShengAction(acStatus,spOrderIds){
	$.messager.prompt('审核通过', '请输入审核备注（选填）:', function(r){
		if (r){
			$.post( '<c:url value="/account/checkbillctl/UpdateAcStatusPass.do"/>',{
				acRemark:r,
				acStatus:acStatus,
				spOrderIds:spOrderIds
			},function(result){
				$.messager.alert("信息",""+result.message);
				$('#settleOrderListMgdg').datagrid('reload');
			},'json');
		}
	});
}

//获取订单详情
function getfeeSpShengSpOrderInfoDetail(spOrderId){
	$('#settleOrderListMgdgLayout').layout('expand','east');
	$('#settleOrderListMgLayoutEast').panel('open').panel('refresh',
	root+'/account/billsettlectl/SettleOrderInfoManagerPage.do?spOrderId='+spOrderId);
}
</script>

