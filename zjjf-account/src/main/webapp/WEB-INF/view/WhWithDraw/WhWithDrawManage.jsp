<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
function FormatWhWithDrawStatus1(value, row) {
	if(value == 0){
		return '<span style="color:grey">暂无</span>';
	}
	return '<a href="#" style="color:red" onclick="getWhWithDrawDetail(\'' + row.id + '\',1)">未审核('+value+')</a>';
}
function FormatWhWithDrawStatus2(value, row) {
	if(value == 0){
		return '<span style="color:grey">暂无</span>';
	}
	return '<a href="#" style="color:green" onclick="getWhWithDrawDetail(\'' + row.id + '\',2)">已审核('+value+')</a>';
}
function FormatWhWithDrawStatus3(value, row) {
	if(value == 0){
		return '<span style="color:grey">暂无</span>';
	}
	return '<a href="#" style="color:yellow" onclick="getWhWithDrawDetail(\'' + row.id + '\',3)">已否决('+value+')</a>';
}
function FormatWhWithDrawStatus4(value, row) {
	if(value == 0){
		return '<span style="color:grey">暂无</span>';
	}
	return '<a href="#" style="color:blue" onclick="getWhWithDrawDetail(\'' + row.id + '\',4)">结算中('+value+')</a>';
}
function FormatWhWithDrawStatus5(value, row) {
	if(value == 0){
		return '<span style="color:grey">暂无</span>';
	}
	return '<a href="#" style="color:blue" onclick="getWhWithDrawDetail(\'' + row.id + '\',5)">已打款('+value+')</a>';
}
</script>

<div class="easyui-layout"  fit="true">
	<div region="center">
		<!--=================================================west-table========================================================  -->
		<table id="whWithDrawMgdg" idField="id"  sortName="id" sortOrder="asc"  pageSize="50"
			toolbar="#whWithDrawMgtoolbar" pagination="true" fitColumns="true"  fit="true"
			data-options="rownumbers:true,singleSelect:true,method:'post'">
		    <thead>
		        <tr>
		        <th field="county"  sortable="true">仓库区域</th>
		        <th field="name"  sortable="true">仓库名称</th>
		        <th field="houseCode"  sortable="true">仓库编号</th>
				<th field="inCount" formatter="FormatWhWithDrawStatus1">未审核</th>
		        <th field="alreadyCount" formatter="FormatWhWithDrawStatus2">已审核</th>
		        <th field="denyCount" formatter="FormatWhWithDrawStatus3">已否决</th>
		        <th field="inPay" formatter="FormatWhWithDrawStatus4">结算中</th>
		        <th field="payCount" formatter="FormatWhWithDrawStatus5">已付款</th>
		        </tr>
		    </thead>
		</table>
		<!--===================================================west-table-toolbar=================================================  -->
		<div id="whWithDrawMgtoolbar" style="padding:5px;">
			 <div>
				<%--核对区域：<select id="whWithDrawMgField1" class="easyui-combobox" panelHeight="auto" style="width:70px" >
			 			<option value=""  selected="selected">全部</option>
				 		<c:forEach var="m" items="${areaList}">
				 			<option value="${m.id}">${m.name}</option>
						</c:forEach>
					  </select> --%>
			  	经销商商名称或编码:<input id="whWithDrawMgField2" class="easyui-textbox" style="width:140px;">
			  	<!-- 送达时间：    <input id="whWithDrawMgField3" class="easyui-datebox" style="width:150px">
			  	至：<input id="whWithDrawMgField4" class="easyui-datebox" style="width:150px"> -->
		   		<a  id="whWithDrawMgtoolbar-findlist"  class="easyui-linkbutton"  iconCls="icon-search" plain="true">查询</a>
		    </div>
		</div>
	</div>
</div>


<script type="text/javascript">

var mibocrud = MiBoCRUD.creatNew({
	modelName:"whWithDrawMg",
	dialogTitle:"用户信息编辑",
	listURL:'<c:url value="/account/whwalletctl/List.do"/>',
	creatURL:'<c:url value="/account/whwalletctl/Add.do"/>',
	updateURL:'<c:url value="/account/whwalletctl/Update.do"/>',
	deleteURL:'<c:url value="/account/whwalletctl/Delete.do"/>',
	findListFun:function(){
		$("#whWithDrawMgdg" ).datagrid('load',{
			//areaId:$("#whWithDrawMgField1").combobox('getValue'),
			spKeyword:$("#whWithDrawMgField2").textbox('getValue')
			//beginTime:$("#whWithDrawMgField3").datebox('getValue')+" 00:00:00",
			//endTime:$("#whWithDrawMgField4").datebox('getValue')+" 23:59:59"
		})
	}
}).init();

function getWhWithDrawDetail(whId,status){
	var url="/account/whwalletctl/WhWDListManagerPage.do?whId="+whId+"&status="+status;
	addTab("结算生成",url,"icon icon-sys");
}

</script>

