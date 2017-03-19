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
		<table id="spWDSheetMgdg" idField="id"  sortName="createTime" sortOrder="desc"  pageSize="50"
			toolbar="#spWDSheetMgtoolbar" pagination="true" fitColumns="true"  fit="true"
			data-options="rownumbers:true,singleSelect:true,method:'post'">
		    <thead>
		        <tr>
		        <th field="sheetNum"  sortable="true" >汇总单编号</th>
		        <th field="sheetName">汇总单名称</th>
		        <th field="sheetRemark">汇总单备注</th>
		        <!-- <th field="createTime">创建时间</th>
		        <th field="createUser">创建用户</th> -->
		        <th field="sheetPath" formatter="FormatBillSheetDownload">汇总单下载</th>
		        <!-- <th field="id" formatter="inputBankbackBillinfo">回执录入</th>
		        <th field="payBankNum">银行支付流水</th>
		        <th field="payBankName">支付银行名称</th>
		        <th field="payTime">银行支付时间</th>
		        <th field="fillTime">录入时间</th>
		        <th field="fillUserid">录入用户</th>
		        <th field="fillRemark">录入备注</th>
		        <th field="xtype">汇总单类型</th> 
		        <th field="status">汇总单状态</th>
		        <th field="isdelete">是否删除</th> -->
		        </tr>
		    </thead>
		</table>
		<!--===================================================west-table-toolbar=================================================  -->
		<div id="spWDSheetMgtoolbar" style="padding:5px;">
			 <div>
			  	汇总单编号/银行流水号:<input id="spWDSheetMgField2" class="easyui-textbox" style="width:240px;">
		   		<a  id="spWDSheetMgtoolbar-findlist"  class="easyui-linkbutton"  iconCls="icon-search" plain="true">查询</a>
		    </div>
		</div>
	</div>
</div>


<script type="text/javascript">

var mibocrud = MiBoCRUD.creatNew({
	modelName:"spWDSheetMg",
	dialogTitle:"用户信息编辑",
	listURL:'<c:url value="/account/spwalletctl/WDSheetList.do"/>',
	creatURL:'<c:url value="/account/spwalletctl/Add.do"/>',
	updateURL:'<c:url value="/account/spwalletctl/Update.do"/>',
	deleteURL:'<c:url value="/account/spwalletctl/Delete.do"/>',
	findListFun:function(){
		$("#spWDSheetMgdg" ).datagrid('load',{
			keyword:$("#spWDSheetMgField2").textbox('getValue'),
			//xtype:2,
			status:1,
			isDelete:0
		})
	}
}).init();
</script>

