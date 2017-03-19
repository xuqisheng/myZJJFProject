<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
function FormatMaWithDrawStatus1(value, row) {
	if(value == 0){
		return '<span style="color:grey">暂无</span>';
	}
	return '<a href="#" style="color:red" onclick="getMaWithDrawDetail(\'' + row.id + '\',1)">未审核('+value+')</a>';
}
function FormatMaWithDrawStatus2(value, row) {
	if(value == 0){
		return '<span style="color:grey">暂无</span>';
	}
	return '<a href="#" style="color:green" onclick="getMaWithDrawDetail(\'' + row.id + '\',2)">已审核('+value+')</a>';
}
function FormatMaWithDrawStatus3(value, row) {
	if(value == 0){
		return '<span style="color:grey">暂无</span>';
	}
	return '<a href="#" style="color:yellow" onclick="getMaWithDrawDetail(\'' + row.id + '\',3)">已否决('+value+')</a>';
}
function FormatMaWithDrawStatus4(value, row) {
	if(value == 0){
		return '<span style="color:grey">暂无</span>';
	}
	return '<a href="#" style="color:blue" onclick="getMaWithDrawDetail(\'' + row.id + '\',4)">结算中('+value+')</a>';
}
function FormatMaWithDrawStatus5(value, row) {
	if(value == 0){
		return '<span style="color:grey">暂无</span>';
	}
	return '<a href="#" style="color:blue" onclick="getMaWithDrawDetail(\'' + row.id + '\',5)">已打款('+value+')</a>';
}
</script>

<div class="easyui-layout"  fit="true">
	<div region="center">
		<!--=================================================west-table========================================================  -->
		<table id="maWithDrawMgdg" idField="id"  sortName="id" sortOrder="asc"  pageSize="50"
			toolbar="#maWithDrawMgtoolbar" pagination="true" fitColumns="true"  fit="true"
			data-options="rownumbers:true,singleSelect:true,method:'post'">
		    <thead>
		        <tr>
		        <th field="managerName"  sortable="true">产商名称</th>
		        <th field="managerCode"  sortable="true">产商编号</th>
		        <th field="areaId"  sortable="true">产商区域</th>
				<th field="inCount" formatter="FormatMaWithDrawStatus1">未审核</th>
		        <th field="alreadyCount" formatter="FormatMaWithDrawStatus2">已审核</th>
		        <th field="denyCount" formatter="FormatMaWithDrawStatus3">已否决</th>
		        <th field="inPay" formatter="FormatMaWithDrawStatus4">结算中</th>
		        <th field="payCount" formatter="FormatMaWithDrawStatus5">已付款</th>
		        </tr>
		    </thead>
		</table>
		<!--===================================================west-table-toolbar=================================================  -->
		<div id="maWithDrawMgtoolbar" style="padding:5px;">
			 <div>
				<%--核对区域：<select id="maWithDrawMgField1" class="easyui-combobox" panelHeight="auto" style="width:70px" >
			 			<option value=""  selected="selected">全部</option>
				 		<c:forEach var="m" items="${areaList}">
				 			<option value="${m.id}">${m.name}</option>
						</c:forEach>
					  </select> --%>
			  	经销商商名称或编码:<input id="maWithDrawMgField2" class="easyui-textbox" style="width:140px;">
			  	<!-- 送达时间：    <input id="maWithDrawMgField3" class="easyui-datebox" style="width:150px">
			  	至：<input id="maWithDrawMgField4" class="easyui-datebox" style="width:150px"> -->
		   		<a  id="maWithDrawMgtoolbar-findlist"  class="easyui-linkbutton"  iconCls="icon-search" plain="true">查询</a>
		    </div>
		</div>
	</div>
</div>


<script type="text/javascript">

var mibocrud = MiBoCRUD.creatNew({
	modelName:"maWithDrawMg",
	dialogTitle:"用户信息编辑",
	listURL:'<c:url value="/account/mawalletctl/List.do"/>',
	creatURL:'<c:url value="/account/mawalletctl/Add.do"/>',
	updateURL:'<c:url value="/account/mawalletctl/Update.do"/>',
	deleteURL:'<c:url value="/account/mawalletctl/Delete.do"/>',
	findListFun:function(){
		$("#maWithDrawMgdg" ).datagrid('load',{
			//areaId:$("#maWithDrawMgField1").combobox('getValue'),
			spKeyword:$("#maWithDrawMgField2").textbox('getValue')
			//beginTime:$("#maWithDrawMgField3").datebox('getValue')+" 00:00:00",
			//endTime:$("#maWithDrawMgField4").datebox('getValue')+" 23:59:59"
		})
	}
}).init();

function getMaWithDrawDetail(maId,status){
	var url="/account/mawalletctl/MaWDListManagerPage.do?maId="+maId+"&status="+status;
	addTab("结算生成",url,"icon icon-sys");
}

</script>

