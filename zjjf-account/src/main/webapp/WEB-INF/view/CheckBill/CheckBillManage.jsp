<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
function FormatCornerAccountStatus(value, row) {
	var user = '<a href="#" onclick="getSpShengHeDetail(\'' + row.supplierId + '\')">未审核('+value+')</a>';
	return user;
}

</script>

<div class="easyui-layout"  fit="true">
	<div region="center">
		<!--=================================================west-table========================================================  -->
		<table id="checkBillMgdg" idField="id"  sortName="areaId" sortOrder="asc"  pageSize="50"
			toolbar="#checkBillMgtoolbar" pagination="true" fitColumns="true"  fit="true"
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
		<div id="checkBillMgtoolbar" style="padding:5px;">
			 <div>
			 	核对区域：<select id="checkBillMgField1" class="easyui-combobox" panelHeight="auto" style="width:70px" >
			 			<option value=""  selected="selected">全部</option>
				 		<c:forEach var="m" items="${areaList}">
				 			<option value="${m.id}">${m.name}</option>
						</c:forEach>
					  </select>
			  	批发商名称或编码:<input id="checkBillMgField2" class="easyui-textbox" style="width:140px;">
			  	送达时间：    <input id="checkBillMgField3" class="easyui-datebox" style="width:150px">
			  	至：<input id="checkBillMgField4" class="easyui-datebox" style="width:150px">
		   		<a  id="checkBillMgtoolbar-findlist"  class="easyui-linkbutton"  iconCls="icon-search" plain="true"  >查询</a>
		    </div>
		</div>
	</div>
</div>


<script type="text/javascript">

var mibocrud = MiBoCRUD.creatNew({
	modelName:"checkBillMg",
	dialogTitle:"用户信息编辑",
	listURL:'<c:url value="/account/checkbillctl/List.do"/>',
	creatURL:'<c:url value="/account/checkbillctl/Add.do"/>',
	updateURL:'<c:url value="/account/checkbillctl/Update.do"/>',
	deleteURL:'<c:url value="/account/checkbillctl/Delete.do"/>',
	findListFun:function(){
		$("#checkBillMgdg" ).datagrid('load',{
			areaId:$("#checkBillMgField1").combobox('getValue'),
			spKeyword:$("#checkBillMgField2").textbox('getValue'),
			beginTime:$("#checkBillMgField3").datetimebox('getValue'),
			endTime:$("#checkBillMgField4").datetimebox('getValue')
		})
	}
}).init();

function getSpShengHeDetail(supperId){
	var url="/account/checkbillctl/CheckBillDetailManagerPage.do?supplierId="+supperId;
	addTab("对账明细",url,"icon icon-sys");
}

</script>

