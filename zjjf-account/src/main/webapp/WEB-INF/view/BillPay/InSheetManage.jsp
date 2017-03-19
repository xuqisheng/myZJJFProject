<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
function FormatAcFeeShengheStatus(value, row) {
	var result ;
	if(value == 3){
		result = "<span style='color:green'>已审核</span>"
	}else if(value == 4){
		result = "<span style='color:blue'>已打款</span>"
	}else if(value <= 2){
		result ="<span style='color:red'>未审核</span>";
	}else{
		result ="<span style='color:red'>状态异常</span>";
	}
	return result;
}

</script>
<div class="easyui-layout"  fit="true" id="inSheetOrderListMgdgLayout">
	<div region="center">
		<!--=================================================west-table========================================================  -->
		<table id="inSheetOrderListMgdg" idField="id"  sortName="d.sheetNum" sortOrder="desc"  pageSize="50"
			toolbar="#inSheetOrderListMgtoolbar" pagination="true" fitColumns="true"  fit="true"
			data-options="rownumbers:true,method:'post'">
		    <thead>
		        <tr>
		        <th data-options="field:'ck',checkbox:true"></th>
		        <th field="sheetNum"  sortable="true" >结算单编号</th>
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
		        <th field="spAcStatus" formatter="FormatAcFeeShengheStatus">财务审核状态</th>
		        <!-- <th field="spKFStatus" formatter="publicCornerShengheStatus">客服审核状态</th> -->
		        <!-- <th field="spSPStatus" formatter="publicCornerShengheStatus">供应商审核状态</th> -->
				<!-- <th field="optAcStatus" formatter="FormatCornerSpOrderOprStatusOpr">操作</th> -->
		        </tr>
		    </thead>
		</table>
		<!--===================================================west-table-toolbar=================================================  -->
		<div id="inSheetOrderListMgtoolbar" style="padding:5px;">
			<div>
			  	结算单编号:<input id="inSheetOrderListMgField2" class="easyui-textbox" style="width:140px;">
				订单编号/店铺名:<input id="inSheetOrderListMgField1" class="easyui-textbox" style="width:140px;">
			  	送达时间：    <input id="inSheetOrderListMgField3" class="easyui-datebox" style="width:150px">
			  	至：<input id="inSheetOrderListMgField4" class="easyui-datebox" style="width:150px">
		   		<a  id="inSheetOrderListMgtoolbar-findlist"  class="easyui-linkbutton"  iconCls="icon-search">查询</a>
				<!--<a  onclick="toBuildOrderInfoSheet();" class="easyui-linkbutton">生成结算单</a>
		   		<a  onclick="doAccountSpShengActionBatch(1);" class="easyui-linkbutton">批量审核</a>
		   		<a  id="inSheetOrderListMgtoolbar-fullscreen"  class="easyui-linkbutton">全屏</a> -->
		    </div>
		</div>
	</div>
</div>


<script type="text/javascript">

var mibocrud = MiBoCRUD.creatNew({
	modelName:"inSheetOrderListMg",
	dialogTitle:"用户信息编辑",
	queryParams:{'supplierId':'${supplierId}','acStatus':'${acStatus}',spStatus:2,kfStatus:2},
	listURL:'<c:url value="/account/billpayctl/PerStatusList.do"/>',
	creatURL:'<c:url value="/account/billpayctl/Add.do"/>',
	updateURL:'<c:url value="/account/billpayctl/Update.do"/>',
	deleteURL:'<c:url value="/account/billpayctl/Delete.do"/>',
	findListFun:function(){
		$("#inSheetOrderListMgdg" ).datagrid('load',{
			supplierId:'${supplierId}',acStatus:'${acStatus}',spStatus:2,kfStatus:2,
			spKeyword:$("#inSheetOrderListMgField1").textbox('getValue'),
			sheetNum:$("#inSheetOrderListMgField2").textbox('getValue'),
			beginTime:$("#inSheetOrderListMgField3").datebox('getValue')+" 00:00:00",
			endTime:$("#inSheetOrderListMgField4").datebox('getValue')+" 23:59:59"
		})
	}
}).init();

</script>

