<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
function FormatSpWalletLog(value, row) {
	if(value == 5)
		return '<span style="color:grey">货款</span>';
	else if(value == 12)
		return '<span style="color:grey">保证金</span>';
	else if(value == 13)
		return '<span style="color:grey">平台服务费</span>';
	else if(value == 14)
		return '<span style="color:grey">转角罚款</span>';
	else
		return '<span style="color:grey">其他</span>';
}
</script>

<div class="easyui-layout"  fit="true" id="SpWalletSetLogMgdgLayout">
	<div region="center">
		<!--=================================================west-table========================================================  -->
		<table id="SpWalletSetLogMgdg" idField="id"  sortName="actionTime" sortOrder="desc"  pageSize="50" pageList="[50,500,1000,2000]"
			toolbar="#SpWalletSetLogMgtoolbar" pagination="true" fitColumns="true"  fit="true"
			data-options="rownumbers:true,singleSelect:true,method:'post'">
		    <thead>
		        <tr>
		        <th field="id"  sortable="true">申请编号</th>
		        <th field="areaId"  sortable="true">区域ID</th>
		        <th field="supplierName">批发商名称</th>
		        <th field="supplierCode">批发商编号</th>
		        <th field="balance">钱包</th>
		        <th field="money">操作金额</th>
		        <th field="actionTime">操作时间</th>
		        <th field="optType" formatter="FormatSpWalletLog">操作类型</th>
		        </tr>
		    </thead>
		</table>
		<!--===================================================west-table-toolbar=================================================  -->
		<div id="SpWalletSetLogMgtoolbar" style="padding:5px;">
			 <div>
				<label>类型:</label>
	    		<select id="SpWalletSetLogMgField1" class="easyui-combobox" panelHeight="auto" style="width:70px" required="true">
			 		<option value="5">货款</option>
			 		<option value="12">保证金</option>
			 		<option value="13">平台服务费</option>
			 		<option value="14">转角罚款</option>
				</select>
			  	申请时间：<input id="SpWalletSetLogMgField3" class="easyui-datebox" style="width:150px">
 			  	至：<input id="SpWalletSetLogMgField4" class="easyui-datebox" style="width:150px">
		   		<a  id="SpWalletSetLogMgtoolbar-findlist"  class="easyui-linkbutton"  iconCls="icon-search" plain="true">查询</a>
		    </div>
		</div>
	</div>
</div>

<script type="text/javascript">
var initXType = ${command.xtype};
if(initXType == 0){initXType = null;}
$("#SpWalletSetLogMgField1").val(initXType);

var mibocrud = MiBoCRUD.creatNew({
	modelName:"SpWalletSetLogMg",
	dialogTitle:"信息编辑",
	queryParams:{'supplierId':'${command.supplierId}',xtype:initXType},
	listURL:'<c:url value="/account/spwalletctl/SpWalletLogList.do"/>',
	findListFun:function(){
		$("#SpWalletSetLogMgdg" ).datagrid('load',{
			supplierId : '${command.supplierId}',
			xtype : $("#SpWalletSetLogMgField1").combobox('getValue'),
			beginTime : $("#SpWalletSetLogMgField3").datebox('getValue')+" 00:00:00",
			endTime : $("#SpWalletSetLogMgField4").datebox('getValue')+" 23:59:59"
		})
	}
}).init();
</script>

