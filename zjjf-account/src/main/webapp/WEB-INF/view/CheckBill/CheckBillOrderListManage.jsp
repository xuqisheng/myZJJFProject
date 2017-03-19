<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
function FormatCornerSpOrderOprStatusOpr(value, row) {
	var result ;
	if(value == 1){
		result = '<a href="#" onclick="doAccountSpShengAction(2,\'' + row.spOrderId + '\')">审核</a>';
	}else if(value == 2){
		result = '<a href="#" onclick="doAccountSpShengAction(1,\'' + row.spOrderId + '\')">取消审核</a>';
	}else{
		result ="<span style='color:red'>状态异常</span>";
	}
	result +=' | <a href="#" onclick="getSpShengSpOrderInfoDetail(\'' + row.spOrderId + '\')">详情</a>';
	return result;
}
function FormatCornerSpOrderOprStatus(value, row) {
	var result ;
	if(value == 1){
		result = "<span style='color:red'>未审核</span>"
	}else if(value == 2){
		result = "<span style='color:green'>已审核</span>"
	}else{
		result ="<span style='color:red'>状态异常</span>";
	}
	return result;
}
</script>

<div class="easyui-layout"  fit="true" id="checkBillDetailMgdgLayout">
	<div region="center">
		<!--=================================================west-table========================================================  -->
		<table id="checkBillDetailMgdg" idField="id"  sortName="ackTime" sortOrder="desc"  pageSize="50"
			toolbar="#checkBillDetailMgtoolbar" pagination="true" fitColumns="true"  fit="true"
			data-options="rownumbers:true,method:'post'">
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
		        <th field="spAcStatus" formatter="FormatCornerSpOrderOprStatus">财务审核状态</th>
		        <th field="optAcStatus" formatter="FormatCornerSpOrderOprStatusOpr">操作</th>
		        </tr>
		    </thead>
		</table>
		<!--===================================================west-table-toolbar=================================================  -->
		<div id="checkBillDetailMgtoolbar" style="padding:5px;">
			 <div>
		 		 订单状态：<select id="checkBillDetailMgField1" class="easyui-combobox" panelHeight="auto" style="width:70px" >
		 			<option value=""  selected="selected">全部</option>
		 			<option value="1">未审核</option>
		 			<option value="2">已审核</option>
				  </select>
			  	小店名或订单编号:<input id="checkBillDetailMgField2" class="easyui-textbox" style="width:140px;">
			  	送达时间：    <input id="checkBillDetailMgField3" class="easyui-datebox" style="width:150px">
			  	至：<input id="checkBillDetailMgField4" class="easyui-datebox" style="width:150px">
		   		<a  id="checkBillDetailMgtoolbar-findlist"  class="easyui-linkbutton"  iconCls="icon-search">查询</a>
		   		<a  onclick="doAccountSpShengActionBatch(2);" class="easyui-linkbutton">批量审核</a>
		   		<a  onclick="doAccountSpShengActionBatch(1);" class="easyui-linkbutton">批量审核</a>
		   		<a  id="checkBillDetailMgtoolbar-fullscreen"  class="easyui-linkbutton">全屏</a>
		    </div>
		</div>
	</div>
	<div id="checkBillDetailMgLayoutEast"data-options="region:'east',collapsed:true,split:true" title="订单明细" style="width:50%;">
		<!-- -->
	</div>
</div>


<script type="text/javascript">

var mibocrud = MiBoCRUD.creatNew({
	modelName:"checkBillDetailMg",
	dialogTitle:"用户信息编辑",
	queryParams:{'supplierId':'${supplierId}'},
	listURL:'<c:url value="/account/checkbillctl/SpList.do"/>',
	creatURL:'<c:url value="/account/checkbillctl/SpAdd.do"/>',
	updateURL:'<c:url value="/account/checkbillctl/SpUpdate.do"/>',
	deleteURL:'<c:url value="/account/checkbillctl/SpDelete.do"/>',
	findListFun:function(){
		$("#checkBillDetailMgdg" ).datagrid('load',{
			areaId:$("#checkBillDetailMgField1").combobox('getValue'),
			spKeyword:$("#checkBillDetailMgField2").textbox('getValue'),
			beginTime:$("#checkBillDetailMgField3").datetimebox('getValue'),
			endTime:$("#checkBillDetailMgField4").datetimebox('getValue')
		})
	}
}).init();
//全屏
var screenFlag=true;
$("#checkBillDetailMgtoolbar-fullscreen").on("click",function(){
	if(screenFlag){
		$('#publicLayout').layout('collapse','north');
		$('#publicLayout').layout('collapse','west');
		$('#checkBillDetailMgdgLayout').layout('collapse','east');
		$(this).linkbutton({text:"退出全屏"});
		screenFlag=false;
	}else{
		$('#publicLayout').layout('expand','north');
		$('#publicLayout').layout('expand','west');s
		$('#checkBillDetailMgdgLayout').layout('expand','east');
		$(this).linkbutton({text:"全屏"});
		screenFlag=true;
	}
});

//审核-取消审核
function doAccountSpShengAction(status,spOrderId){
	if(spOrderId && spOrderId.length > 0 && status >0){
		goAccountSpShengAction(status,spOrderId);
	}else{
		$.messager.alert("信息","请选择订单");
	}
	
}
//批量处理
function doAccountSpShengActionBatch(status){
	var rows = $('#checkBillDetailMgdg').datagrid('getSelections');
	var spOrderIds = getSelections('checkBillDetailMgdg').join(',');
	if( spOrderIds && spOrderIds.length > 0 && status >0){
		goAccountSpShengAction(status,spOrderIds);
	}else{
		$.messager.alert("信息","请选择订单");
	}
}

//核心方法
function goAccountSpShengAction(acStatus,spOrderIds){
	$.messager.prompt('审核通过', '请输入审核备注（选填）:', function(r){
		if (r){
			$.post( '<c:url value="/account/checkbillctl/UpdateAcStatusPass.do"/>',{
				userRemark:r,
				acStatus:acStatus,
				spOrderIds:spOrderIds
			},function(result){
				$.messager.alert("信息",""+result.message);
				$('#checkBillDetailMgdg').datagrid('reload');
			},'json');
		}
	});
}

//获取订单详情
function getSpShengSpOrderInfoDetail(spOrderId){
	$('#checkBillDetailMgdgLayout').layout('expand','east');
	$('#checkBillDetailMgLayoutEast').panel('open').panel('refresh',
			root+'/account/checkbillctl/CheckBillOrderInfoManagerPage.do?spOrderId='+spOrderId);
}
</script>

