<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
function FormatAcFeeShengheStatus(value, row) {
	var result ;
	if(value == 3){
		result = "<span style='color:green'>已审核</span>"
	}else if(value == 4){
		result = "<span style='color:blue'>已打款</span>"
	}else if(value == 2){
		result ="<span style='color:red'>待付款</span>";
	}else{
		result ="<span style='color:red'>状态异常</span>";
	}
	return result;
}

</script>
<div class="easyui-layout"  fit="true" id="toPayOrderListMgdgLayout">
	<div region="center">
		<!--=================================================west-table========================================================  -->
		<table id="toPayOrderListMgdg" idField="spOrderUUID"  sortName="ackTime" sortOrder="desc"  pageSize="2000" pageList="[50,500,1000,2000]"
			toolbar="#toPayOrderListMgtoolbar" pagination="true" fitColumns="true"  fit="true"
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
		        <th field="spAcStatus" formatter="FormatAcFeeShengheStatus">财务审核状态</th>
		        <!-- <th field="spKFStatus" formatter="publicCornerShengheStatus">客服审核状态</th> -->
		        <!-- <th field="spSPStatus" formatter="publicCornerShengheStatus">供应商审核状态</th> -->
				<!-- <th field="optAcStatus" formatter="FormatCornerSpOrderOprStatusOpr">操作</th> -->
		        </tr>
		    </thead>
		</table>
		<!--===================================================west-table-toolbar=================================================  -->
		<div id="toPayOrderListMgtoolbar" style="padding:5px;">
			<div>
			<!--订单状态：<select id="toPayOrderListMgField1" class="easyui-combobox" panelHeight="auto" style="width:70px" >
		 			<option value=""  selected="selected">全部</option>
		 			<option value="1">未审核</option>
		 			<option value="2">已审核</option>
				  </select> -->
			  	小店名或订单编号:<input id="toPayOrderListMgField2" class="easyui-textbox" style="width:140px;">
			  	送达时间：    <input id="toPayOrderListMgField3" class="easyui-datebox" style="width:150px">
			  	至：<input id="toPayOrderListMgField4" class="easyui-datebox" style="width:150px">
		   		<a  id="toPayOrderListMgtoolbar-findlist"  class="easyui-linkbutton"  iconCls="icon-search">查询</a>
				<a  onclick="toBuildOrderInfoSheet();" class="easyui-linkbutton">生成结算单</a>
		   		<!--<a  onclick="doAccountSpShengActionBatch(1);" class="easyui-linkbutton">批量审核</a>
		   		<a  id="toPayOrderListMgtoolbar-fullscreen"  class="easyui-linkbutton">全屏</a> -->
		    </div>
		</div>
	</div>
</div>
<!--===================================================west-table-modeifydlg=================================================  -->
<div id="toPayOrderListMgdlg" class="easyui-dialog"  modal="true"  style="min-width:400px;padding:10px 20px;min-height:200px;" closed="true" buttons="#toPayOrderListMgdlgbt">
    <div class="ftitle">信息录入</div>
    <form id="toPayOrderListMgfm" method="post">
    	<div class="fitem">
    		<label>结算单名称:</label><input name="sheetname" style='width:320px;' class="easyui-textbox" required="true"/>
    	</div>
    	<div class="fitem">
    		<label>结算周期:</label><input name="sheetremark" style='width:320px;' class="easyui-textbox"/>
    	</div>
		<input id="fmspOrderIds" type="hidden" name="spOrderIds" >
		<input id="fmxtype" type="hidden" name="xtype">
		<input id="fmsupplierId" type="hidden" name="supplierId">
	</form>
</div>
<div id="toPayOrderListMgdlgbt">
	<a id="toPayOrderListMgdlgbt-save"  class="easyui-linkbutton c6" iconCls="icon-ok"   style="width:90px">保存</a>
	<a id="toPayOrderListMgdlgbt-cancel"   class="easyui-linkbutton" iconCls="icon-cancel"  style="width:90px">取消</a>
</div>	

<script type="text/javascript">

var mibocrud = MiBoCRUD.creatNew({
	modelName:"toPayOrderListMg",
	dialogTitle:"用户信息编辑",
	queryParams:{'supplierId':'${supplierId}','acStatus':'${acStatus}',spStatus:2,kfStatus:2},
	listURL:'<c:url value="/account/billpayctl/PerStatusList.do"/>',
	creatURL:'<c:url value="/account/billpayctl/Add.do"/>',
	updateURL:'<c:url value="/account/billpayctl/Update.do"/>',
	deleteURL:'<c:url value="/account/billpayctl/Delete.do"/>',
	findListFun:function(){
		$("#toPayOrderListMgdg" ).datagrid('load',{
			supplierId:'${supplierId}',acStatus:'${acStatus}',spStatus:2,kfStatus:2,
			//areaId:$("#toPayOrderListMgField1").textbox('getValue'),
			spKeyword:$("#toPayOrderListMgField2").textbox('getValue'),
			beginTime:$("#toPayOrderListMgField3").datebox('getValue')+" 00:00:00",
			endTime:$("#toPayOrderListMgField4").datebox('getValue')+" 23:59:59"
		})
	}
}).init();

//选择ids
function getAcSheetSelections() {
	var ss = [];
	var rows = $('#toPayOrderListMgdg').datagrid('getChecked');
	for (var i = 0; i < rows.length; i++) {
		var row = rows[i];
		ss.push(row.spOrderUUID);
	}
	return ss;
}

//对话框保存按钮
$("#toPayOrderListMgdlgbt-save").off("click").on("click",function(){
	var idsArray=getAcSheetSelections();
	$("#fmspOrderIds").val(idsArray.join(','));
	$("#fmxtype").val(1);
	$("#fmsupplierId").val('${supplierId}');
	$("#toPayOrderListMgfm").form('submit',{
		url: '<c:url value="/account/billsheetctl/CreatAcSheet.do"/>',
		onSubmit: function(){
			return $(this).form('validate');
		},
		success: function(result){
			var result = eval('('+result+')');
			if (!result.success){
				$.messager.show({
					title: 'Error',
					msg: result.message
				});
			} else {
				var retMessage="对账单号："+result.message.sheetnum+"<br/>";
				retMessage+="对账名称："+result.message.sheetname+"<br/>";
				retMessage+="对账备注："+result.message.sheetremark+"<br/>";
				//retMessage+='对账下载：<a href="#" style="color:blue" onclick="downloadBillSheet(\'' + result.message.sheetpath + '\')">点击下载</a>';
				retMessage+='对账下载：<a target="blank" href="http://www.izjjf.cn/'+result.message.sheetpath+'">点击下载</a>';
			    $.messager.alert("操作成功",retMessage,"info",function(){
					$("#toPayOrderListMgdlg").dialog('close'); // close the dialog
					$("#toPayOrderListMgdg").datagrid('reload'); // reload the user data
			    });
			}
		}
	})
});

//对话框保存按钮
$("#toPayOrderListMgdlgbt-cancel").off("click").on("click",function(){
	$('#toPayOrderListMgdlg').dialog('close');
});
//生成对账单
function toBuildOrderInfoSheet(){
	if(getAcSheetSelections().length<1){
	    $.messager.alert("操作成功","请先选择要进行支付的订单");
	    return ;
	}
	$("#toPayOrderListMgdlg").dialog("open").dialog('setTitle',"新建对账单");
	$("#toPayOrderListMgfm").form('clear');
}
</script>

