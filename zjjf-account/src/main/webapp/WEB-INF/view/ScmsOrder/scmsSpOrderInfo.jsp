<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
function detail(value,row){
	if(row.col1 == null || row.col1 == ''){
		return '<a href="#" style="color:red" onclick="toUpdate(\'' + value + '\')">审核</a>';
	}else{
		return '<a href="#" style="color:blue" onclick="toDetail(\'' + value + '\' , \'' + row.acRemark + '\' , \'' + row.col2 + '\' )">详情</a>';
	}
}
function fkStatus(value,row){
	if(value == null || value == ''){
		return '未付款';
	}else{
		return '已付款';
	}
}
function status(value,row){
	return "已送达";
}
</script>
<div class="easyui-layout"  fit="true" id="scmsSpOrderInfoMgdgLayout">
	<div region="center">
		<!--=================================================west-table========================================================  -->
		<table id="scmsSpOrderInfoMgdg" idField="id"  sortName="addTime" sortOrder="desc"  pageSize="50"
			toolbar="#scmsSpOrderInfoMgtoolbar" pagination="true" fitColumns="true"  fit="true"
			data-options="rownumbers:true,singleSelect:true,method:'post'">
		    <thead>
		        <tr>
					<th field="orderId"  sortable="true" >订单号</th>
					<th field="storeName">批发商名称</th>
					<th field="mobile">批发商手机号</th>
					<th field="supplierNam">仓库名称</th>
					<th field="supplierTel">仓库手机号</th>
					<th field="status" formatter="status" sortable="true">状态</th>
					<th field="col1" formatter="fkStatus" sortable="true">付款状态</th>
					<th field="orderPrice">订单金额</th>
					<th field="id" formatter="detail">操作</th>
		        </tr>
		    </thead>
		</table>
		<!--===================================================west-table-toolbar=================================================  -->
		<div id="scmsSpOrderInfoMgtoolbar" style="padding:5px;">
			 <div>
			  	仓库名称/手机号/订单号:<input id="queryOrderId" value="${command.orderId}" class="easyui-textbox" style="width:240px;" >
			  	订单状态：<select id="queryStatus" class="easyui-combobox"  panelHeight="auto" style="width:70px" >
			  		<option value="0" selected="selected">全部</option>
			  		<option value="1" >未付款</option>
			  		<option value="2" >已付款</option>
			  	</select>
		   		<a id="scmsSpOrderInfoMgtoolbar-findlist"  class="easyui-linkbutton"  iconCls="icon-search" plain="true">查询</a>
		    </div>
		</div>
	</div>
</div>
<!--===================================================west-table-modeifydlg=================================================  -->
<div id="scmsOrderDetaildlg" class="easyui-dialog"  modal="true"  style="min-width:400px;padding:10px 20px;min-height:200px;" closed="true" buttons="#scmsOrderDetaildlgbt">
	<form id="scmsOrderDetailfm" method="post">
		<div class="fitem" id="remark">
			<label>备注:</label><input name="remark" style='width:320px;' class="easyui-textbox" required="true"/>
		</div>
		<div class="fitem" id="updateTime"></div>
		<input name="id" type="hidden" >
	</form>
</div>
<div id="scmsOrderDetaildlgbt">
	<a id="scmsOrderDetaildlgbt-save"  class="easyui-linkbutton c6" iconCls="icon-ok"   style="width:90px">保存</a>
	<a id="scmsOrderDetaildlgbt-cancel"   class="easyui-linkbutton" iconCls="icon-cancel"  style="width:90px">取消</a>
</div>

<script type="text/javascript">
function toUpdate(id){
	$('input[name="id"]').val(id);
	$('#remark').html('<label>备注:</label><input name="remark" style="width:320px;" class="easyui-textbox" required="true"/>');
	$('#scmsOrderDetaildlgbt-save').show();
	$('#updateTime').hide();
	showDetail('审核');
}
function toDetail(id ,acRemark , col2){
	$('input[name="id"]').val(id);
	$('#remark').html('<label>备注:'+acRemark+'</label>');
	$('#updateTime').html('<label>操作时间:'+col2+'</label>');
	$('#updateTime').show();
	$('#scmsOrderDetaildlgbt-save').hide();

	showDetail('详情');
}
var mibocrud = MiBoCRUD.creatNew({
	modelName:"scmsSpOrderInfoMg",
	dialogTitle:"用户信息编辑",
	queryParams:{orderId:'${command.orderId}'},
	listURL:'<c:url value="/account/sporderinfoctl/scmsListJson.do"/>',
	findListFun:function(){
		$("#scmsSpOrderInfoMgdg").datagrid('load',{
			orderId:$("#queryOrderId").textbox('getValue'),
			status:$("#queryStatus").combobox('getValue')
		})
	}
}).init();



//===========================================================================
function showDetail(title){
	var id = $('#id').val();
	var remark = $('input[name="remark"]').val();
	$('#scmsOrderDetaildlg').dialog('open').dialog('setTitle',title);
}
//对话框保存按钮
var currentWDSheetIds;
$("#scmsOrderDetaildlgbt-save").off("click").on("click",function(){
	$("#spId").val(currentWDSheetIds);
	$("#scmsOrderDetailfm").form('submit',{
		url: '<c:url value="/account/sporderinfoctl/updateSpOrderCol1.do"/>',
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
				$("#scmsOrderDetaildlg").dialog('close');
				$("#scmsSpOrderInfoMgdg").datagrid('reload');
			}
		}
	})
});
//对话框保存按钮
$("#scmsOrderDetaildlgbt-cancel").off("click").on("click",function(){
	$('#scmsOrderDetaildlg').dialog('close');
});
</script>

