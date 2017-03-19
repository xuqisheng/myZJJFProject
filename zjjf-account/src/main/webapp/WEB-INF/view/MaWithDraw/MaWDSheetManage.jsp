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
		<table id="maWDSheetMgdg" idField="id"  sortName="createTime" sortOrder="desc"  pageSize="50"
			toolbar="#maWDSheetMgtoolbar" pagination="true" fitColumns="true"  fit="true"
			data-options="rownumbers:true,singleSelect:true,method:'post'">
		    <thead>
		        <tr>
		        <th field="sheetNum"  sortable="true" >经销商结算汇总单编号</th>
		        <th field="sheetName">经销商汇总单名称</th>
		        <th field="sheetRemark">经销商汇总单备注</th>
		        <th field="sheetPath" formatter="FormatBillSheetDownload">经销商汇总单下载</th>
		        </tr>
		    </thead>
		</table>
		<!--===================================================west-table-toolbar=================================================  -->
		<div id="maWDSheetMgtoolbar" style="padding:5px;">
			 <div>
			  	汇总单编号/银行流水号:<input id="maWDSheetMgField2" class="easyui-textbox" style="width:240px;">
		   		<a  id="maWDSheetMgtoolbar-findlist"  class="easyui-linkbutton"  iconCls="icon-search" plain="true">查询</a>
		    </div>
		</div>
	</div>
</div>


<script type="text/javascript">

var mibocrud = MiBoCRUD.creatNew({
	modelName:"maWDSheetMg",
	dialogTitle:"用户信息编辑",
	listURL:'<c:url value="/account/mawalletctl/WDSheetList.do"/>',
	creatURL:'<c:url value="/account/mawalletctl/Add.do"/>',
	updateURL:'<c:url value="/account/mawalletctl/Update.do"/>',
	deleteURL:'<c:url value="/account/mawalletctl/Delete.do"/>',
	findListFun:function(){
		$("#maWDSheetMgdg" ).datagrid('load',{
			keyword:$("#maWDSheetMgField2").textbox('getValue'),
			//xtype:2,
			status:1,
			isDelete:0
		})
	}
}).init();
</script>

