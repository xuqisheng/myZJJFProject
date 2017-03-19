<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
function FormatCornerSpOrderOprStatusOpr(value, row) {
	var result ;
	if(value == 1){
		result = '<a href="#" onclick="dofeeAccountSpShengAction(2,\'' + row.spOrderUUID + '\')">审核</a>';
	}else if(value == 2){
		result = '<a href="#" onclick="dofeeAccountSpShengAction(1,\'' + row.spOrderUUID + '\')">取消审核</a>';
	}else if(value > 2){
		result ="<span style='color:blue'>不可操作</span>";
	}else{
		result ="<span style='color:red'>状态异常</span>";
	}
	result +=' | <a href="#" onclick="getfeeSpShengSpOrderInfoDetail(\'' + row.spOrderId + '\')">详情</a>';
	return result;
}
function FormatMaWithDrawPayStatus(value, row){
	var result ;
	if(value == 0){
		result = "<span style='color:green'>未支付</span>"
	}else if(value == 1){
		result ="<span style='color:red'>支付成功</span>";
	}else{
		result ="<span style='color:red'>状态异常</span>";
	}
	return result;
}
function FormatWhWithDrawStatusX(value, row) {
	var result ;
	if(value == 2){
		result = "<span style='color:green'>已审核</span>"
	}else if(value == 1){
		result ="<span style='color:red'>未审核</span>";
	}else if(value == 3){
		result = "<span style='color:yellow'>已拒绝</span>"
	}else if(value == 4){
		result = "<span style='color:blue'>结算中</span>"
	}else if(value == 5){
		result = "<span style='color:blue'>已打款</span>"
	}else if(value == 6){
		result ="<span style='color:red'>已退款</span>";
	}else{
		result ="<span style='color:red'>无记录</span>";
	}
	return result;
}

function inputSpWDBankbackBillinfo(value,row){
	return '<a href="#" style="color:blue" onclick="doInputBankbackBillinfo(\'' + value + '\')">点击录入</a>';
}
</script>

<div class="easyui-layout"  fit="true" id="whWithDrawListMgdgLayout">
	<div region="center">
		<!--=================================================west-table========================================================  -->
		<table id="whWithDrawListMgdg" idField="id"  sortName="applyTime" sortOrder="desc"  pageSize="50" pageList="[50,500,1000,2000]"
			toolbar="#whWithDrawListMgtoolbar" pagination="true" fitColumns="true"  fit="true"
			data-options="rownumbers:true,singleSelect:false,method:'post'">
		    <thead>
		        <tr>
		        <th data-options="field:'ck',checkbox:true"></th>
		        <th field="county"  sortable="true">区域ID</th>
		        <th field="name">仓库名称</th>
		        <th field="houseCode">仓库编号</th>
		        <th field="wallet">钱包余额</th>
		        <th field="id"  sortable="true">提现编号</th>
		        <th field="amount">提现金额</th>
		        <th field="cardBankName">收款开户行</th>
		        <th field="cardUserName">收款户名</th>
		        <th field="cardNo">收款卡号</th>
		        <th field="status" formatter="FormatWhWithDrawStatusX">申请状态</th>
		        <th field="applyTime">申请时间</th> 
		        <th field="applyRemark">申请备注</th> 
		        <th field="checkTime">审核时间</th>
		        <th field="checkRemark">审核备注</th>
		        <th field="denyTime">退审时间</th>
		        <th field="denyRemark">退审原因</th>
		        <th field="payTime">付款时间</th>
		        <th field="payRemark">付款备注</th>		        
		        <th field="payState" formatter="FormatMaWithDrawPayStatus">付款状态</th>
		        <th field="payment">付款金额</th>
		        <th field="bankcode">付款银行名</th>
		        <th field="bankDealNo">付款行交易流水</th>
		        <th field="tradePlant">第三方支付名称</th>
		        <th field="tradeNo">第三方支付流水</th>
		        <!-- <th field="walletAddTime">创建时间</th>
		        <th field="walletUpdateTime">最后更新时间</th> -->
		        </tr>
		    </thead>
		</table>
		<!--===================================================west-table-toolbar=================================================  -->
		<div id="whWithDrawListMgtoolbar" style="padding:5px;">
			 <div>
		 		 订单状态：<select id="whWithDrawListMgField1" class="easyui-combobox" panelHeight="auto" style="width:70px" >
		 			<option value=""  selected="selected">全部</option>
		 			<option value="1">未审核</option>
		 			<option value="2">已审核</option>
		 			<option value="3">已拒绝</option>
		 			<option value="4">支付中</option>
		 			<option value="5">已支付</option>
				  </select>
			  	仓库名：<input id="whWithDrawListMgField2" class="easyui-textbox" style="width:140px;">
			  	申请时间：<input id="whWithDrawListMgField3" class="easyui-datebox" style="width:150px">
			  	至：<input id="whWithDrawListMgField4" class="easyui-datebox" style="width:150px">
		   		<a  id="whWithDrawListMgtoolbar-findlist"  class="easyui-linkbutton"  iconCls="icon-search">查询</a>
		   		<a  onclick="doWithDrawPassActionBatch(2);" class="easyui-linkbutton">批量审核</a>
		   		<a  onclick="doWithDrawPassActionBatch(1);" class="easyui-linkbutton">批量撤审</a>
		   		<a  onclick="doWithDrawPassActionBatch(3);" class="easyui-linkbutton">批量否决</a>
		   		<a  onclick="toBuildWithDrawSheet();" class="easyui-linkbutton" style="color:red">生成结算单</a>
		   		<a  onclick="inputSheetReBack();" class="easyui-linkbutton" style="color:red">录入回执</a>
		   		<a  onclick="clearAllSelectMaWithDraw();" class="easyui-linkbutton">清除选择</a>
		    </div>
		</div>
	</div>
	<!-- <div id="whWithDrawListMgLayoutEast" data-options="region:'east',collapsed:true,split:true" title="订单明细" style="width:50%;">
		
	</div> -->
</div>
<!--===================================================west-table-modeifydlg=================================================  -->
<div id="toPayWithDrawListMgdlg" class="easyui-dialog"  modal="true"  style="min-width:500px;padding:10px 20px;min-height:200px;" closed="true" buttons="#toPayWithDrawListMgdlgbt">
    <form id="toPayWithDrawListMgfm" method="post">
    	<div class="fitem">
    		<label style="width:130px">申请汇总单名称:</label><input name="sheetName" style='width:320px;' class="easyui-textbox" required="true"/>
    	</div>
    	<div class="fitem">
    		<label style="width:130px">申请汇总单周期:</label><input name="sheetRemark" style='width:320px;' class="easyui-textbox"/>
    	</div>
		<input id="fmspWithDrawIds" type="hidden" name="withDrawIds" >
		<input id="fmWithDrawxtype" type="hidden" name="xtype">
	</form>
</div>
<div id="toPayWithDrawListMgdlgbt">
	<a id="toPayWithDrawListMgdlgbt-save"  class="easyui-linkbutton c6" iconCls="icon-ok"   style="width:90px">保存</a>
	<a id="toPayWithDrawListMgdlgbt-cancel"   class="easyui-linkbutton" iconCls="icon-cancel"  style="width:90px">取消</a>
</div>	

<!--===================================================west-table-modeifydlg=================================================  -->
<div id="whWDSheetMgdlg" class="easyui-dialog"  modal="true"  style="min-width:400px;padding:10px 20px;min-height:200px;" closed="true" buttons="#whWDSheetMgdlgbt">
    <div class="ftitle">信息录入</div>
    <form id="whWDSheetMgfm" method="post">
    	<div class="fitem">
    		<label>银行流水:</label><input name="bankDealNo" style='width:320px;' class="easyui-numberbox" required="true"/>
    	</div>
    	<div class="fitem">
    		<label>银行名称:</label><input name="bankcode" style='width:320px;' class="easyui-textbox" required="true"/>
    	</div>
    	<div class="fitem">
    		<label>第三方平台名称:</label><input name="tradePlant" style='width:320px;' class="easyui-textbox"/>
    	</div>
    	<div class="fitem">
    		<label>第三方平台流水:</label><input name="tradeNo" style='width:320px;' class="easyui-textbox"/>
    	</div>
    	<div class="fitem">
    		<label>付款金额:</label><input name="payment" style='width:320px;' class="easyui-numberbox" 
    		min="0.01" value="1" max="100000000" precision="2" type="text"
    		required="true"/>
    	</div>
    	<div class="fitem">
    		<label>付款时间:</label><input name="payTime" style='width:320px;' class="easyui-datetimebox" editable="false" required="true"/>
    	</div>
    	<div class="fitem">
    		<label>回执备注:</label><input name="payRemark" style='width:320px;' class="easyui-textbox"/>
    	</div>
		<input id="fmWdSheetIds" type="hidden" name="id" >
	</form>
</div>
<div id="whWDSheetMgdlgbt">
	<a id="whWDSheetMgdlgbt-save"  class="easyui-linkbutton c6" iconCls="icon-ok"   style="width:90px">保存</a>
	<a id="whWDSheetMgdlgbt-cancel"   class="easyui-linkbutton" iconCls="icon-cancel"  style="width:90px">取消</a>
</div>


<script type="text/javascript">
var initStatus = ${status};
if(initStatus == 0){initStatus = null;}
$("#whWithDrawListMgField1").val(initStatus);

var mibocrud = MiBoCRUD.creatNew({
	modelName:"whWithDrawListMg",
	dialogTitle:"信息编辑",
	queryParams:{'whId':'${whId}',status:initStatus},
	listURL:'<c:url value="/account/whwalletctl/DetailList.do"/>',
	creatURL:'<c:url value="/account/whwalletctl/SpAdd.do"/>',
	updateURL:'<c:url value="/account/whwalletctl/SpUpdate.do"/>',
	deleteURL:'<c:url value="/account/whwalletctl/SpDelete.do"/>',
	findListFun:function(){
		$("#whWithDrawListMgdg" ).datagrid('load',{
			whId:'${whId}',
			status:$("#whWithDrawListMgField1").combobox('getValue'),
			spKeyword:$("#whWithDrawListMgField2").textbox('getValue'),
			beginTime:$("#whWithDrawListMgField3").datebox('getValue')+" 00:00:00",
			endTime:$("#whWithDrawListMgField4").datebox('getValue')+" 23:59:59"
		})
	}
}).init();

//全屏
var feeScreenFlag=true;
$("#whWithDrawListMgtoolbar-fullscreen").off("click").on("click",function(){
	if(feeScreenFlag){
		$('#publicLayout').layout('collapse','north');
		$('#publicLayout').layout('collapse','west');
		$('#whWithDrawListMgdgLayout').layout('collapse','east');
		$(this).linkbutton({text:"退出全屏"});
		feeScreenFlag=false;
	}else{
		$('#publicLayout').layout('expand','north');
		$('#publicLayout').layout('expand','west');s
		$('#whWithDrawListMgdgLayout').layout('expand','east');
		$(this).linkbutton({text:"全屏"});
		feeScreenFlag=true;
	}
});

//批量处理
function doWithDrawPassActionBatch(status){
	var withDrawIds = [];
	var rows = $('#whWithDrawListMgdg').datagrid('getSelections');
	for (var i = 0; i < rows.length; i++) {
		var row = rows[i];
		withDrawIds.push(row.id);
		if(row.status >= 4){
			alert("列表中包含已支付申请，请从新选择申请状态")
			return;
		}
	}
	//alert(withDrawIds);
	if( withDrawIds && withDrawIds.length > 0 && status >0){
		goWithDrawSpShengAction(status,withDrawIds);
	}else{
		$.messager.alert("信息","请选择订单");
	}
}

//核心方法
function goWithDrawSpShengAction(status,withDrawIds){
	$.messager.prompt('审核通过', '请输入备注（选填）:', function(r){
		if (r){
			$.post( '<c:url value="/account/whwalletctl/UpdateWithDrawPass.do"/>',{
				userRemark:r,
				status:status,
				withDrawIds:withDrawIds.join(",")
			},function(result){
				$.messager.alert("信息",""+result.message);
				$('#whWithDrawListMgdg').datagrid('reload');
			},'json');
		}
	});
}

//获取订单详情
function getfeeSpShengSpOrderInfoDetail(spOrderId){
	$('#whWithDrawListMgdgLayout').layout('expand','east');
	$('#whWithDrawListMgLayoutEast').panel('open').panel('refresh',
	root+'/account/whwalletctl/SettleOrderInfoManagerPage.do?spOrderId='+spOrderId);
}

//生成对账单
function toBuildWithDrawSheet(){
	var rows = $('#whWithDrawListMgdg').datagrid('getSelections');
	if(rows.length<=0){
		alert("请选择申请！")
		return ;
	}
	for (var i = 0; i < rows.length; i++) {
		var row = rows[i];
		if(row.status != 2){
			alert("列表中包含未审核申请，请重新选择申请记录")
			return;
		}
	}
	$("#toPayWithDrawListMgdlg").dialog("open").dialog('setTitle',"新建提现申请汇总结算单");
	$("#toPayWithDrawListMgfm").form('clear');
}

//对话框保存按钮
$("#toPayWithDrawListMgdlgbt-save").off("click").on("click",function(){
	var withDrawIds = [];
	var rows = $('#whWithDrawListMgdg').datagrid('getSelections');
	for (var i = 0; i < rows.length; i++) {
		var row = rows[i];
		withDrawIds.push(row.id);
		if(row.status != 2){
			alert("列表中包含未审核申请，请从新选择申请状态")
			return;
		}
	}
	$("#fmspWithDrawIds").val(withDrawIds.join(','));
	$("#fmWithDrawxtype").val(2);
	//$("#fmsupplierId").val('${whId}');
	$("#toPayWithDrawListMgfm").form('submit',{
		url: '<c:url value="/account/whwalletctl/CreatDWSheet.do"/>',
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
				var retMessage="汇总单号："+result.message.sheetNum+"<br/>";
				retMessage+="汇总单名称："+result.message.sheetName+"<br/>";
				retMessage+="汇总单备注："+result.message.sheetRemark+"<br/>";
				//retMessage+='对账下载：<a href="#" style="color:blue" onclick="downloadBillSheet(\'' + result.message.sheetpath + '\')">点击下载</a>';
				retMessage+='汇总单下载：<a target="blank" href="http://www.izjjf.cn/'+result.message.sheetPath+'">点击下载</a>';
			    $.messager.alert("操作成功",retMessage,"info",function(){
					$("#toPayWithDrawListMgdlg").dialog('close'); // close the dialog
					$("#whWithDrawListMgdg").datagrid('reload'); // reload the user data
			    });
			}
		}
	})
});

//对话框保存按钮
$("#toPayWithDrawListMgdlgbt-cancel").off("click").on("click",function(){
	$('#toPayWithDrawListMgdlg').dialog('close');
});


//=================================回执录入==========================================
function inputSheetReBack(){
	var rows = $('#whWithDrawListMgdg').datagrid('getSelections');
	var confirmWDIds=[];
	var allmoney=0;
	if(rows.length>0){
		for (var i = 0; i < rows.length; i++) {
			if(rows[i].status < 4){
				alert("该申请不是结算状态"+rows[i].status);
				return;
			}
			allmoney += rows[i].amount;
			confirmWDIds.push(rows[i].id);
		}
		$('#whWDSheetMgdlg').dialog('open').dialog('setTitle','录入银行流水,共'+rows.length+'条,金额：'+allmoney+'元');
		$('#whWDSheetMgfm').form('load',rows[0]);
		currentWDSheetIds=confirmWDIds.join(",");
	}else{
		alert("请选择一条申请");
	}
}
//=================================回执录入==========================================
function clearAllSelectMaWithDraw(){
	$('#whWithDrawListMgdg').datagrid("clearSelections");
}
//对话框保存按钮
$("#whWDSheetMgdlgbt-cancel").off("click").on("click",function(){
	$('#whWDSheetMgdlg').dialog('close');
});
//对话框保存按钮
var currentWDSheetIds;
$("#whWDSheetMgdlgbt-save").off("click").on("click",function(){
	$("#fmWdSheetIds").val(currentWDSheetIds);
	$("#whWDSheetMgfm").form('submit',{
		url: '<c:url value="/account/whwalletctl/FillWDBack.do"/>',
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
				$("#whWDSheetMgdlg").dialog('close');
				$("#whWithDrawListMgdg").datagrid('reload');
			}
		}
	})
});

</script>

