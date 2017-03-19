<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
function FormatSpWithDrawStatus1(value, row) {
	if(value == 0){
		return '<span style="color:grey">暂无</span>';
	}
	return '<a href="#" style="color:red" onclick="getSpWithDrawDetail(\'' + row.id + '\',1)">未审核('+value+')</a>';
}
function FormatSpWithDrawStatus2(value, row) {
	if(value == 0){
		return '<span style="color:grey">暂无</span>';
	}
	return '<a href="#" style="color:green" onclick="getSpWithDrawDetail(\'' + row.id + '\',2)">已审核('+value+')</a>';
}
function FormatSpWithDrawStatus3(value, row) {
	if(value == 0){
		return '<span style="color:grey">暂无</span>';
	}
	return '<a href="#" style="color:yellow" onclick="getSpWithDrawDetail(\'' + row.id + '\',3)">已否决('+value+')</a>';
}
function FormatSpWithDrawStatus4(value, row) {
	if(value == 0){
		return '<span style="color:grey">暂无</span>';
	}
	return '<a href="#" style="color:blue" onclick="getSpWithDrawDetail(\'' + row.id + '\',4)">结算中('+value+')</a>';
}
function FormatSpWithDrawStatus5(value, row) {
	if(value == 0){
		return '<span style="color:grey">暂无</span>';
	}
	return '<a href="#" style="color:blue" onclick="getSpWithDrawDetail(\'' + row.id + '\',5)">已打款('+value+')</a>';
}
</script>

<div class="easyui-layout"  fit="true">
	<div region="center">
		<!--=================================================west-table========================================================  -->
		<table id="spWithDrawMgdg" idField="id"  sortName="id" sortOrder="asc"  pageSize="50"
			toolbar="#spWithDrawMgtoolbar" pagination="true" fitColumns="true"  fit="true"
			data-options="rownumbers:true,singleSelect:true,method:'post'">
		    <thead>
		        <tr>
		        <th field="supplierName"  sortable="true">批发商名称</th>
		        <th field="supplierCode"  sortable="true">批发商编号</th>
		        <th field="areaId"  sortable="true">批发商区域</th>
				<th field="inCount" formatter="FormatSpWithDrawStatus1">未审核</th>
		        <th field="alreadyCount" formatter="FormatSpWithDrawStatus2">已审核</th>
		        <th field="denyCount" formatter="FormatSpWithDrawStatus3">已否决</th>
		        <th field="inPay" formatter="FormatSpWithDrawStatus4">结算中</th>
		        <th field="payCount" formatter="FormatSpWithDrawStatus5">已付款</th>
		        </tr>
		    </thead>
		</table>
		<!--===================================================west-table-toolbar=================================================  -->
		<div id="spWithDrawMgtoolbar" style="padding:5px;">
			 <div>
				<%--核对区域：<select id="spWithDrawMgField1" class="easyui-combobox" panelHeight="auto" style="width:70px" >
			 			<option value=""  selected="selected">全部</option>
				 		<c:forEach var="m" items="${areaList}">
				 			<option value="${m.id}">${m.name}</option>
						</c:forEach>
					  </select> --%>
			  	批发商名称或编码:<input id="spWithDrawMgField2" class="easyui-textbox" style="width:140px;">
			  	<!-- 送达时间：    <input id="spWithDrawMgField3" class="easyui-datebox" style="width:150px">
			  	至：<input id="spWithDrawMgField4" class="easyui-datebox" style="width:150px"> -->
		   		<a  id="spWithDrawMgtoolbar-findlist"  class="easyui-linkbutton"  iconCls="icon-search" plain="true">查询</a>
		    </div>
		</div>
	</div>
</div>


<script type="text/javascript">

var mibocrud = MiBoCRUD.creatNew({
	modelName:"spWithDrawMg",
	dialogTitle:"用户信息编辑",
	listURL:'<c:url value="/account/spwalletctl/List.do"/>',
	creatURL:'<c:url value="/account/spwalletctl/Add.do"/>',
	updateURL:'<c:url value="/account/spwalletctl/Update.do"/>',
	deleteURL:'<c:url value="/account/spwalletctl/Delete.do"/>',
	findListFun:function(){
		$("#spWithDrawMgdg" ).datagrid('load',{
			//areaId:$("#spWithDrawMgField1").combobox('getValue'),
			spKeyword:$("#spWithDrawMgField2").textbox('getValue')
			//beginTime:$("#spWithDrawMgField3").datebox('getValue')+" 00:00:00",
			//endTime:$("#spWithDrawMgField4").datebox('getValue')+" 23:59:59"
		})
	}
}).init();

function getSpWithDrawDetail(supperId,status){
	var url="/account/spwalletctl/SpWDListManagerPage.do?supplierId="+supperId+"&status="+status;
	addTab("结算生成",url,"icon icon-sys");
}

</script>

