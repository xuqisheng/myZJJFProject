<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
function FormatBillSheetDownload(value,row){
	return '<a style="color:blue" target="blank" href="http://www.izjjf.cn/'+value+'">点击下载</a>';
}
function inputBankbackBillinfo(value,row){
	return '<a href="#" style="color:blue" onclick="doInputBankbackBillinfo(\'' + value + '\')">点击录入</a>';
}

</script>
<div class="easyui-layout"  fit="true">
	<div region="center">
		<!--=================================================west-table========================================================  -->
		<table id="whWDSheetMgdg" idField="id"  sortName="createTime" sortOrder="desc"  pageSize="50"
			toolbar="#whWDSheetMgtoolbar" pagination="true" fitColumns="true"  fit="true"
			data-options="rownumbers:true,singleSelect:true,method:'post'">
		    <thead>
		        <tr>
		        <th field="sheetNum"  sortable="true" >仓库结算汇总单编号</th>
		        <th field="sheetName">仓库结算汇总单名称</th>
		        <th field="sheetRemark">仓库结算汇总单备注</th>
		        <th field="sheetPath" formatter="FormatBillSheetDownload">仓库结算汇总单下载</th>
		        </tr>
		    </thead>
		</table>
		<!--===================================================west-table-toolbar=================================================  -->
		<div id="whWDSheetMgtoolbar" style="padding:5px;">
			 <div>
			  	汇总单编号/银行流水号:<input id="whWDSheetMgField2" class="easyui-textbox" style="width:240px;">
		   		<a  id="whWDSheetMgtoolbar-findlist"  class="easyui-linkbutton"  iconCls="icon-search" plain="true">查询</a>
		    </div>
		</div>
	</div>
</div>


<script type="text/javascript">

var mibocrud = MiBoCRUD.creatNew({
	modelName:"whWDSheetMg",
	dialogTitle:"仓库结算汇单信息编辑",
	listURL:'<c:url value="/account/whwalletctl/WhSheetList.do"/>',
	creatURL:'<c:url value="/account/whwalletctl/Add.do"/>',
	updateURL:'<c:url value="/account/whwalletctl/Update.do"/>',
	deleteURL:'<c:url value="/account/whwalletctl/Delete.do"/>',
	findListFun:function(){
		$("#whWDSheetMgdg" ).datagrid('load',{
			keyword:$("#whWDSheetMgField2").textbox('getValue'),
			//xtype:2,
			status:1,
			isDelete:0
		})
	}
}).init();
</script>

