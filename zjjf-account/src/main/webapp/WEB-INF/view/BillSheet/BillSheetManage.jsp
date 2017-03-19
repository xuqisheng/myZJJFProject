<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
function FormatBillSheetDownload(value,row){
	return '<a style="color:blue" target="blank" href="http://www.izjjf.cn/'+value+'">点击下载</a>';
}
function FormatBillSheetSettleStatus(value,row){
	if(value == 2){
		return '<span style="color:green">回执已录入</span>';
	}
	return '<span style="color:red">未录入</span>';
}
function inputBankbackBillinfo(value,row){
	return '<a href="#" style="color:blue" onclick="doInputBankbackBillinfo(\'' + value + '\')">点击录入</a>';
}

</script>
<div class="easyui-layout"  fit="true">
	<div region="center">
		<!--=================================================west-table========================================================  -->
		<table id="billSheetMgdg" idField="id"  sortName="createTime" sortOrder="desc"  pageSize="50"
			toolbar="#billSheetMgtoolbar" pagination="true" fitColumns="true"  fit="true"
			data-options="rownumbers:true,singleSelect:true,method:'post'">
		    <thead>
		        <tr>
		        <th field="sheetnum"  sortable="true" >结算单编号</th>
		        <th field="sheetname">结算单名称</th>
		        <th field="sheetremark">结算单备注</th>
		        <th field="createtime">创建时间</th>
		        <!-- <th field="createuser">创建用户</th> -->
		        <th field="sheetpath" formatter="FormatBillSheetDownload">结算单下载</th>
		        <th field="id" formatter="inputBankbackBillinfo">回执录入</th>
		        <th field="status" formatter="FormatBillSheetSettleStatus">结算状态</th>
		        <th field="paybanknum">银行支付流水</th>
		        <th field="paybankname">支付银行名称</th>
		        <th field="paytime">银行支付时间</th>
		        <th field="filltime">录入时间</th>
		        <!-- <th field="filluserid">录入用户</th> -->
		        <th field="fillremark">录入备注</th>
		        <!-- <th field="xtype">结算单类型</th> -->
		        <!-- <th field="isdelete">是否删除</th> -->
		        </tr>
		    </thead>
		</table>
		<!--===================================================west-table-toolbar=================================================  -->
		<div id="billSheetMgtoolbar" style="padding:5px;">
			 <div>
			 	<%-- 核对区域：<select id="billSheetMgField1" class="easyui-combobox" panelHeight="auto" style="width:70px" >
			 			<option value=""  selected="selected">全部</option>
				 		<c:forEach var="m" items="${areaList}">
				 			<option value="${m.id}">${m.name}</option>
						</c:forEach>
					  </select> --%>
			  	结算单编号/银行流水号:<input id="billSheetMgField2" class="easyui-textbox" style="width:240px;">
			  	<!-- 送达时间：    <input id="billSheetMgField3" class="easyui-datetimebox" style="width:150px">
			  	至：<input id="billSheetMgField4" class="easyui-datetimebox" style="width:150px"> -->
		   		<a  id="billSheetMgtoolbar-findlist"  class="easyui-linkbutton"  iconCls="icon-search" plain="true">查询</a>
		    </div>
		</div>
	</div>
</div>
<!--===================================================west-table-modeifydlg=================================================  -->
<div id="billSheetMgdlg" class="easyui-dialog"  modal="true"  style="min-width:400px;padding:10px 20px;min-height:200px;" closed="true" buttons="#billSheetMgdlgbt">
    <div class="ftitle">信息录入</div>
    <form id="billSheetMgfm" method="post">
    	<div class="fitem">
    		<label>银行流水:</label><input name="paybanknum" style='width:320px;' class="easyui-numberbox" required="true"/>
    	</div>
    	<div class="fitem">
    		<label>银行名称:</label><input name="paybankname" style='width:320px;' class="easyui-textbox" required="true"/>
    	</div>
    	<div class="fitem">
    		<label>付款时间:</label><input name="paytime" style='width:320px;' class="easyui-datetimebox" editable="false" required="true"/>
    	</div>
    	<div class="fitem">
    		<label>回执备注:</label><input name="fillremark" style='width:320px;' class="easyui-textbox"/>
    	</div>
		<input id="fmAcSheetId" type="hidden" name="id" >
	</form>
</div>
<div id="billSheetMgdlgbt">
	<a id="billSheetMgdlgbt-save"  class="easyui-linkbutton c6" iconCls="icon-ok"   style="width:90px">保存</a>
	<a id="billSheetMgdlgbt-cancel"   class="easyui-linkbutton" iconCls="icon-cancel"  style="width:90px">取消</a>
</div>

<script type="text/javascript">

var mibocrud = MiBoCRUD.creatNew({
	modelName:"billSheetMg",
	dialogTitle:"用户信息编辑",
	listURL:'<c:url value="/account/billsheetctl/List.do"/>',
	creatURL:'<c:url value="/account/billsheetctl/Add.do"/>',
	updateURL:'<c:url value="/account/billsheetctl/Update.do"/>',
	deleteURL:'<c:url value="/account/billsheetctl/Delete.do"/>',
	findListFun:function(){
		$("#billSheetMgdg" ).datagrid('load',{
			keyword:$("#billSheetMgField2").textbox('getValue'),
			//xtype:2,
			status:1,
			isDelete:0
		})
	}
}).init();

//对话框保存按钮
var currentAcSheetId;
$("#billSheetMgdlgbt-save").off("click").on("click",function(){
	$("#fmAcSheetId").val(currentAcSheetId);
	$("#billSheetMgfm").form('submit',{
		url: '<c:url value="/account/billsheetctl/FillBack.do"/>',
		onSubmit: function(){
			return $(this).form('validate');
		},
		success: function(result){
			var result = eval('('+result+')');
			if (!result.success){
				$.messager.show({
					title: 'Error',
					msg: result.message
				});
			} else {
				var message="对账单号："+result.message.sheetnum+"<br/>";
				message+="对账名称："+result.message.sheetname+"<br/>";
				message+="对账备注："+result.message.sheetremark+"<br/>";
				message+='对账下载：<a href="#" style="color:blue" onclick="downloadBillSheet(\'' + result.message.sheetpath + '\')">点击下载</a>';
			    $.messager.alert("操作提示", "操作成功！","info",function(){
					$("#billSheetMgdlg").dialog('close'); // close the dialog
					$("#billSheetMgdg").datagrid('reload'); // reload the user data
			    });
			}
		}
	})
});

//对话框保存按钮
$("#billSheetMgdlgbt-cancel").off("click").on("click",function(){
	$('#billSheetMgdlg').dialog('close');
});
//生成对账单
function doInputBankbackBillinfo(value){
	currentAcSheetId=value;
	$("#billSheetMgdlg").dialog("open").dialog('setTitle',"新建对账单");
	$("#billSheetMgfm").form('clear');
}

</script>

