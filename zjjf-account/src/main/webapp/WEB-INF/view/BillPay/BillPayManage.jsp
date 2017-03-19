<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
function FormatAcToPay(value, row) {
	if(value<1) return '<span style="color:red">暂无</span>';
	return '<a href="#" style="color:red" onclick="getAcPayStatusDetail(\'toPay\',\'' + row.supplierId + '\')">待结算('+value+')</a>';
}
function FormatAcInSheet(value, row) {
	if(value<1) return '<span style="color:green">暂无</span>';
	return '<a href="#" style="color:green" onclick="getAcPayStatusDetail(\'inSheet\',\'' + row.supplierId + '\')">结算中('+value+')</a>';
}
function FormatAcAlreadyPay(value, row) {
	if(value<1) return '<span style="color:blue">暂无</span>';
	return '<a href="#" style="color:blue" onclick="getAcPayStatusDetail(\'alreadyPay\',\'' + row.supplierId + '\')">已结算('+value+')</a>';
}
</script>


<div class="easyui-layout"  fit="true">
	<div region="center">
		<!--=================================================west-table========================================================  -->
		<table id="billPayMgdg" idField="id"  sortName="areaId" sortOrder="asc"  pageSize="50"
			toolbar="#billPayMgtoolbar" pagination="true" fitColumns="true"  fit="true"
			data-options="rownumbers:true,singleSelect:true,method:'post'">
		    <thead>
		        <tr>
		        <th field="areaId"  sortable="true" >区域ID</th>
		        <th field="supplierCode"  sortable="true">批发商编号</th>
		        <th field="supplierName"  sortable="true" >批发商名称</th>
		        <th field="spOrderPrice"  sortable="true" >总交易金额</th>
		        <th field="spZmaoli"  sortable="true" >总毛利</th>
		        <th field="spZfee"  sortable="true" >总费用</th>
		        <th field="zfeeRate">费用率</th>
		        <th field="toPay" formatter="FormatAcToPay">待结算订单数</th>
		        <th field="inSheet" formatter="FormatAcInSheet">结算单已打订单数</th>
		        <th field="alReadyPay" formatter="FormatAcAlreadyPay">财务已结算</th>
		        </tr>
		    </thead>
		</table>
		<!--===================================================west-table-toolbar=================================================  -->
		<div id="billPayMgtoolbar" style="padding:5px;">
			 <div>
			 	核对区域：<select id="billPayMgField1" class="easyui-combobox" panelHeight="auto" style="width:70px" >
			 			<option value=""  selected="selected">全部</option>
				 		<c:forEach var="m" items="${areaList}">
				 			<option value="${m.id}">${m.name}</option>
						</c:forEach>
					  </select>
			  	批发商名称或编码:<input id="billPayMgField2" class="easyui-textbox" style="width:140px;">
			  	送达时间：    <input id="billPayMgField3" class="easyui-datebox" style="width:150px">
			  	至：<input id="billPayMgField4" class="easyui-datebox" style="width:150px">
		   		<a  id="billPayMgtoolbar-findlist"  class="easyui-linkbutton"  iconCls="icon-search" plain="true"  >查询</a>
		    </div>
		</div>
	</div>
</div>


<script type="text/javascript">

var mibocrud = MiBoCRUD.creatNew({
	modelName:"billPayMg",
	dialogTitle:"用户信息编辑",
	listURL:'<c:url value="/account/billpayctl/List.do"/>',
	creatURL:'<c:url value="/account/billpayctl/Add.do"/>',
	updateURL:'<c:url value="/account/billpayctl/Update.do"/>',
	deleteURL:'<c:url value="/account/billpayctl/Delete.do"/>',
	findListFun:function(){
		$("#billPayMgdg" ).datagrid('load',{
			areaId:$("#billPayMgField1").combobox('getValue'),
			spKeyword:$("#billPayMgField2").textbox('getValue'),
			beginTime:$("#billPayMgField3").datebox('getValue')+" 00:00:00",
			endTime:$("#billPayMgField4").datebox('getValue')+" 23:59:59"
		})
	}
}).init();

function getAcPayStatusDetail(type,supperId){
	var url="";
	if(type == "toPay"){
		url ="/account/billpayctl/BillPayOrderListManagerPage.do?type="+type;
		url +="&supplierId="+supperId;
		url +="&acStatus=2";//待付款
		addTab("待付款订单列表",url,"icon icon-sys");
	}else if(type == "inSheet"){
		url="/account/billpayctl/BillPayOrderListManagerPage.do?type="+type;
		url +="&supplierId="+supperId;
		url +="&acStatus=3";//付款中
		addTab("支付中订单列表",url,"icon icon-sys");
	}else if(type == "alreadyPay"){
		url="/account/billpayctl/BillPayOrderListManagerPage.do?type="+type;
		url +="&supplierId="+supperId;
		url +="&acStatus=4";//已付款
		addTab("已结算订单列表",url,"icon icon-sys");
	}else{
		return ;
	}
}

</script>

