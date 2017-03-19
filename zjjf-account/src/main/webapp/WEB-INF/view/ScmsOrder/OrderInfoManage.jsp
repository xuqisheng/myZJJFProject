<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
function detail(value,row){
	return '<a href="#" style="color:blue" onclick="toMaOrderInfoDetail(\'' + value + '\')">详情</a>';
}
function whPayStatus(value,row){
	if(value == false)
		return "<span style='color:red'>未支付</span>";
	else
		return "<span style='color:green'>已支付</span>";
}
function whPayMetho(value,row){
	if(value == 5)
		return "钱包支付";
	else
		return "对公转账";
}
function WhFreight(value,row){
	if(value == 0 || value == null)
		return 0;
	else
		return value;
}
</script>
<div class="easyui-layout"  fit="true" id="orderInfoMgdgLayout">
	<div region="center">
		<!--=================================================west-table========================================================  -->
		<table id="orderInfoMgdg" idField="id"  sortName="createTime" sortOrder="desc"  pageSize="50"
			toolbar="#orderInfoMgtoolbar" pagination="true" fitColumns="true"  fit="true"
			data-options="rownumbers:true,singleSelect:true,method:'post'">
		    <thead>
		        <tr>
		        <th field="orderId"  sortable="true" >采购单号</th>
		        <th field="warehouseName">仓库</th>
		        <th field="whPayMetho" formatter="whPayMetho">支付方式</th>
		        <th field="whPayStatus" formatter="whPayStatus">状态</th>
		        <th field="totQuantity">数量</th>
		        <th field="orderPrice">货款金额</th>
		        <th field="outOfPrice">采购成本</th>
		        <th field="whFreight" formatter="WhFreight">配送成本</th>
		        <th field="id" formatter="detail">操作</th>
		        </tr>
		    </thead>
		</table>
		<!--===================================================west-table-toolbar=================================================  -->
		<div id="orderInfoMgtoolbar" style="padding:5px;">
			 <div>
			  	采购单号:<input id="orderId" class="easyui-textbox" style="width:240px;" >
			  	订单状态：<select id="queryStatus"  class="easyui-combobox"  panelHeight="auto" style="width:70px" >
			  		<option value="1" selected="selected">全部</option>
			  		<option value="2" >未接单</option>
			  		<option value="3" >未入库</option>
			  		<option value="4" >已入库</option>
			  	</select>
		   		<a id="orderInfoMgtoolbar-findlist"  class="easyui-linkbutton"  iconCls="icon-search" plain="true">查询</a>
		    </div>
		</div>
	</div>
</div>


<script type="text/javascript">
function toMaOrderInfoDetail(id){
	var url="/account/scmsorderinfoctl/toMaOrderInfoDetail.do?id="+id;
	addTab("详情页",url,"icon icon-sys");
}
var mibocrud = MiBoCRUD.creatNew({
	modelName:"orderInfoMg",
	dialogTitle:"用户信息编辑",
	listURL:'<c:url value="/account/scmsorderinfoctl/toMaOrderInfoPageList.do"/>',
	findListFun:function(){
		$("#orderInfoMgdg").datagrid('load',{
			orderId:$("#orderId").textbox('getValue'),
			queryStatus:$("#queryStatus").combobox('getValue')
		})
	}
}).init();
</script>

