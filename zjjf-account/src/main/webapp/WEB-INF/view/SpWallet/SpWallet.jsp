<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
function FormatSpWalletLog5(value, row) {
	if(value == 0){
		return '<span style="color:grey">暂无</span>';
	}
	return '<a href="#" style="color:red" onclick="getSpWalletLog(\'' + row.id + '\',5)">'+value+'</a>';
}
function FormatSpWalletLog12(value, row) {
	if(value == 0){
		return '<span style="color:grey">暂无</span>';
	}
	return '<a href="#" style="color:red" onclick="getSpWalletLog(\'' + row.id + '\',12)">'+value+'</a>';
}
function FormatSpWalletLog13(value, row) {
	if(value == 0){
		return '<span style="color:grey">暂无</span>';
	}
	return '<a href="#" style="color:red" onclick="getSpWalletLog(\'' + row.id + '\',13)">'+value+'</a>';
}
function FormatSpWalletLog14(value, row) {
	if(value == 0){
		return '<span style="color:grey">暂无</span>';
	}
	return '<a href="#" style="color:red" onclick="getSpWalletLog(\'' + row.id + '\',14)">'+value+'</a>';
}
</script>

<div class="easyui-layout"  fit="true">
	<div region="center">
		<!--=================================================west-table========================================================  -->
		<table id="SpWalletMgdg" idField="id" pageSize="50" toolbar="#SpWalletMgtoolbar" pagination="true" fitColumns="true"  fit="true"
			data-options="rownumbers:true,singleSelect:false,method:'post'">
		    <thead>
		        <tr>
		        <th data-options="field:'ck',checkbox:true"></th>
		        <th field="supplierName"  sortable="true">批发商名称</th>
		        <th field="supplierCode"  sortable="true">批发商编号</th>
		        <th field="areaId"  sortable="true">批发商区域</th>
		        <th field="wallet"  sortable="true">钱包余额</th>
		        <th field="total5"  sortable="true" formatter="FormatSpWalletLog5">货款笔数</th>
		        <th field="total12"  sortable="true" formatter="FormatSpWalletLog12">保证金笔数</th>
		        <th field="total13"  sortable="true" formatter="FormatSpWalletLog13">平台服务费笔数</th>
		        <th field="total14"  sortable="true" formatter="FormatSpWalletLog14">转角罚款笔数</th>
		        </tr>
		    </thead>
		</table>
		<!--===================================================west-table-toolbar=================================================  -->
		<div id="SpWalletMgtoolbar" style="padding:5px;">
			 <div>
				<%--核对区域：<select id="SpWalletMgField1" class="easyui-combobox" panelHeight="auto" style="width:70px" >
			 			<option value=""  selected="selected">全部</option>
				 		<c:forEach var="m" items="${areaList}">
				 			<option value="${m.id}">${m.name}</option>
						</c:forEach>
					  </select> --%>
			  	批发商名称或编码:<input id="SpWalletMgField2" class="easyui-textbox" style="width:140px;">
			  	<!-- 送达时间：    <input id="SpWalletMgField3" class="easyui-datebox" style="width:150px">
			  	至：<input id="SpWalletMgField4" class="easyui-datebox" style="width:150px"> -->
		   		<a  id="SpWalletMgtoolbar-findlist"  class="easyui-linkbutton"  iconCls="icon-search" plain="true">查询</a>
		   		
		   		<a onclick="inputSheetReBack();" class="easyui-linkbutton">扣款</a>
		    </div>
		</div>
	</div>
</div>
<!--===================================================west-table-modeifydlg=================================================  -->
<div id="SpWalletMgdlg" class="easyui-dialog"  modal="true"  style="min-width:400px;padding:10px 20px;min-height:200px;" closed="true" buttons="#SpWalletMgdlgbt">
    <div class="ftitle">信息录入</div>
    <form id="SpWalletMgfm" method="post">
    	<div class="fitem">
    		<label>金额:</label><input name="money" style='width:320px;' class="easyui-numberbox"  precision="2" size="8" maxlength="8" max="99999.99" required="true"/>
    	</div>
    	<div class="fitem">
    		<label>类型:</label>
    		<select name="optType" class="easyui-combobox" panelHeight="auto" style="width:70px" required="true">
		 		<option value="5">货款</option>
		 		<option value="12">保证金</option>
		 		<option value="13">平台服务费</option>
		 		<option value="14">转角罚款</option>
			  </select>
    	</div>
    	<div class="fitem">
    		<label>备注:</label><input name="remark" style='width:320px;' class="easyui-textbox" required="true"/>
    	</div>
		<input id="spId" name="spId" type="hidden" >
	</form>
</div>
<div id="SpWalletMgdlgbt">
	<a id="SpWalletMgdlgbt-save"  class="easyui-linkbutton c6" iconCls="icon-ok"   style="width:90px">保存</a>
	<a id="SpWalletMgdlgbt-cancel"   class="easyui-linkbutton" iconCls="icon-cancel"  style="width:90px">取消</a>
</div>

<script type="text/javascript">

var mibocrud = MiBoCRUD.creatNew({
	modelName:"SpWalletMg",
	dialogTitle:"用户信息编辑",
	listURL:'<c:url value="/account/spwalletctl/SpWalletList.do"/>',
	findListFun:function(){
		$("#SpWalletMgdg" ).datagrid('load',{
			//areaId:$("#SpWalletMgField1").combobox('getValue'),
			spKeyword:$("#SpWalletMgField2").textbox('getValue')
			//beginTime:$("#SpWalletMgField3").datebox('getValue')+" 00:00:00",
			//endTime:$("#SpWalletMgField4").datebox('getValue')+" 23:59:59"
		})
	}
}).init();

function getSpWalletLog(supperId,xType){
	var url="/account/spwalletctl/SpWalletLog.do?supplierId="+supperId+"&xtype="+xType;
	addTab("钱包日志",url,"icon icon-sys");
}


//=================================扣款==========================================
function inputSheetReBack(){
	$('input[name="money"]').val('');
	$('input[name="remark"]').val('');
	
	var rows = $('#SpWalletMgdg').datagrid('getSelections');
	var confirmWDIds=[];
	var wallet=0;
	if(rows.length>0){
		for (var i = 0; i < rows.length; i++) {
			wallet += rows[i].wallet;
			confirmWDIds.push(rows[i].id);
		}
		$('#SpWalletMgdlg').dialog('open').dialog('setTitle','录入扣款信息,共'+rows.length+'条,金额：'+wallet+'元');
		$('#SpWalletMgfm').form('load',rows[0]);
		currentWDSheetIds=confirmWDIds.join(",");
	}else{
		alert("请选择一条申请");
	}
}
//对话框保存按钮
var currentWDSheetIds;
$("#SpWalletMgdlgbt-save").off("click").on("click",function(){
	if($('input[name="money"]').val() > 0){
		$("#spId").val(currentWDSheetIds);
		$("#SpWalletMgfm").form('submit',{
			url: '<c:url value="/account/spwalletctl/updateSpWallet.do"/>',
			onSubmit: function(){
				return $(this).form('validate');
			},
			success: function(result){
				var result = eval('('+result+')');
				alert(result.message);
				if (!result.success){
					$.messager.show({
						title: 'Error',
						msg: result.message
					});
				} else {
					$("#SpWalletMgdlg").dialog('close');
					$("#SpWalletMgdg").datagrid('reload');
				}
			}
		})
	}else{
		alert("请输入大于0的金额");
	}
});
function clearAllSelectSpWithDraw(){
	$('#SpWalletMgdg').datagrid("clearSelections");
}
//对话框保存按钮
$("#SpWalletMgdlgbt-cancel").off("click").on("click",function(){
	$('#SpWalletMgdlg').dialog('close');
});

</script>

