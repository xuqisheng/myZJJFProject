<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
function FormatCornerAccountStatus(value, row) {
	var user = '<a href="#" onclick="getSpFeeShengHeDetail(\'' + row.supplierId + '\')">未审核('+value+')</a>';
	return user;
}

</script>

<div class="easyui-layout"  fit="true">
	<div region="center">
		<!--=================================================west-table========================================================  -->
		<table id="settleBillMgdg" idField="id"  sortName="areaId" sortOrder="asc"  pageSize="50"
			toolbar="#settleBillMgtoolbar" pagination="true" fitColumns="true"  fit="true"
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
		        <th field="yStatus">财务已审核</th>
		        <th field="noStatus" formatter="FormatCornerAccountStatus">财务未审核</th>
		        </tr>
		    </thead>
		</table>
		<!--===================================================west-table-toolbar=================================================  -->
		<div id="settleBillMgtoolbar" style="padding:5px;">
			 <div>
			 	核对区域：<select id="settleBillMgField1" class="easyui-combobox" panelHeight="auto" style="width:70px" >
			 			<option value=""  selected="selected">全部</option>
				 		<c:forEach var="m" items="${areaList}">
				 			<option value="${m.id}">${m.name}</option>
						</c:forEach>
					  </select>
			  	批发商名称或编码:<input id="settleBillMgField2" class="easyui-textbox" style="width:140px;">
			  	送达时间：    <input id="settleBillMgField3" class="easyui-datebox" style="width:150px">
			  	至：<input id="settleBillMgField4" class="easyui-datebox" style="width:150px">
		   		<a  id="settleBillMgtoolbar-findlist"  class="easyui-linkbutton"  iconCls="icon-search" plain="true">查询</a>
		    </div>
		</div>
	</div>
</div>


<script type="text/javascript">

var mibocrud = MiBoCRUD.creatNew({
	modelName:"settleBillMg",
	dialogTitle:"用户信息编辑",
	listURL:'<c:url value="/account/billsettlectl/List.do"/>',
	creatURL:'<c:url value="/account/billsettlectl/Add.do"/>',
	updateURL:'<c:url value="/account/billsettlectl/Update.do"/>',
	deleteURL:'<c:url value="/account/billsettlectl/Delete.do"/>',
	findListFun:function(){
		$("#settleBillMgdg" ).datagrid('load',{
			areaId:$("#settleBillMgField1").combobox('getValue'),
			spKeyword:$("#settleBillMgField2").textbox('getValue'),
			beginTime:$("#settleBillMgField3").datebox('getValue')+" 00:00:00",
			endTime:$("#settleBillMgField4").datebox('getValue')+" 23:59:59"
		})
	}
}).init();

function getSpFeeShengHeDetail(supperId){
	var url="/account/billsettlectl/SettleOrderListManagerPage.do?supplierId="+supperId;
	addTab("结算明细",url,"icon icon-sys");
}

</script>

