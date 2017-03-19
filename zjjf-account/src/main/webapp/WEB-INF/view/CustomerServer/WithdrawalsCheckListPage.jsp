<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>提款审核列表</title>
   	<jsp:include page="../common/header.jsp"></jsp:include>
</head>
<body class="easyui-layout">
	<div region="center" style="padding:0px;" border="false" header="false">  
	    <table border="false" fit="true" id="tt" class="easyui-datagrid" url="${root}/CustomerServer/WithdrawalsCheckList.do" 
	    		iconCls="icon-save" sortName="createTime" sortOrder="desc" singleSelect="true"
	            rownumbers="true" idField="id" fitColumns="true" pagination="true" pageSize="20"
	            data-options="rownumbers:true,method:'post',toolbar:'#tb',queryParams:{isDelete:0}">
	        <thead>
	            <tr>
	            	<th data-options="field:'ck',checkbox:true"></th>
	            	<th field="id" width="100" sortable="true">id</th>
	                <th field="code" width="200" sortable="true">交易流水号</th>
	                <th field="userName" width="100" sortable="true">用户名</th>
	              	<th field="phoneNum" width="120" sortable="true">用户电话</th>
	                <th field="amount" width="100" sortable="true">提现金额</th>
	                <th field="overage" width="100" sortable="true">账户余额</th>
	                <th field="bankNo" width="200" sortable="true">银行卡号/支付宝账号</th>
	                <th field="issuingBank" width="120" align="center" formatter="formatBankType">所属银行/支付宝</th>
	                <th field="bankUserName" width="100" align="center">开户名</th>
	                <th field="isDelete" width="80" align="center" formatter="formatIsDelete">是否删除</th>
	                <th field="state" width="80" align="center" formatter="formatState">审核状态</th>
	                <th field="createTime" width="120" align="center">创建时间</th>
	                <th data-options="field:'_operate',width:120,align:'center',formatter:formatOper">操作</th>
	            </tr>
	        </thead>
	    </table>
	    <div id="tb" style="padding:2px 5px;">
	    	<div style="margin-top: 2px;">
		       	流水号:<input id="code"class="easyui-textbox" style="width:140px"/>
		       	用户名:<input id="userName"class="easyui-textbox" style="width:140px"/>
		       	审核状态:
		        <select id="state" class="easyui-combobox" panelHeight="auto" style="width:100px">
		            <option value="" >全部</option>
		            <option value="0">未审核</option>
		            <option value="1">审核通过</option>
		            <option value="2">审核不通过</option>
		            <option value="3">打款完成</option>
		        </select>
				银行名:
				<select id="issuingBank" class="easyui-combobox" panelHeight="auto" style="width:100px">
		            <option value="" >全部</option>
		            <option value="0">支付宝</option>
		            <option value="1">中国工商银行</option>
		            <option value="2">中国建设银行</option>
		            <option value="3">中国光大银行</option>
		            <option value="4">中国交通银行</option>
		        </select>
		       	是否删除: 
		       	<select id="isDelete" class="easyui-combobox" panelHeight="auto" style="width:100px" >
			        <option value="false" selected="selected">否</option>
			        <option value="true">是</option>
			        <option value="" >全部</option>
		       	</select>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" 
		       		plain="true" onclick="javascript:withdrawalsCheckFind()">查询</a>
		       	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-excel" 
		       		plain="true" onclick="javascript:withdrawalsExcel()">导出excel</a>
	       </div>
	    </div>
    </div>
</body>
<script type="text/javascript">

	// 格式化 所属银行
	function formatBankType(val, row){
		if (val == 0) {
			return "支付宝";
		}else if(val == 1){
			return "中国工商银行";
		}else if(val == 2){
			return "中国建设银行";
		}else if(val == 3){
			return "中国光大银行";
		}else if(val == 4){
			return "中国交通银行";
		}else{
			return "其它";
		}
	}
	
	// 格式化 是否在用
	function formatIsDelete(val, row){
		if(val == 0){
			return "否";
		}else if(val == 1){
			return "是";
		}else{
			return "其它";
		}
	}
	
	// 格式化 处理状态
	function formatState(val, row){
		if(val == 0){
			return "未审核";
		}else if(val == 1){
			return "审核通过";
		}else if(val == 2){
			return "审核不通过";
		}else if(val == 3){
			return "打款完成";
		}else{
			return "其它";
		}
	}
	
	// 点击表单查询
	function withdrawalsCheckFind(){
		 //查询参数直接添加在queryParams中    
	     var queryParams = $('#tt').datagrid('options').queryParams;  
	     queryParams.code = $("#code").val();
	     queryParams.userName = $("#userName").val();
	     queryParams.state = getComboboxVal("state");
	     queryParams.issuingBank = getComboboxVal("issuingBank");
	     queryParams.isDelete = getComboboxVal("isDelete");
	     $('#tt').datagrid('options').queryParams=queryParams;        
	     $("#tt").datagrid('reload'); 
	}
	
	// 导出excel
	function withdrawalsExcel(){
		var code = $("#code").val();
		var userName = $("#userName").val();
		var state = getComboboxVal("state");
		var issuingBank = getComboboxVal("issuingBank");
		userName = encodeURIComponent(encodeURIComponent(userName));
		window.location.href = "${root}/CustomerServer/WithdrawalsCheckExcel.do"
			+ "?code="+code+"&userName="+userName+"&state="+state+"&issuingBank="+issuingBank;
	}
	
	// 处理申请
	function formatOper(val, row, index){
		return '<a href="#" onclick="check('+row.id+')">审核</a>'
			+'&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="log('+row.id+')">历史</a>';
	}
	
	// 审核 提款申请
	function check(id){
		var url = root+"/CustomerServer/CheckWithdrawalsPage.do?id="+id;
		showWindow("审核", url, 600, 450, withdrawalsCheckFind);
	}
	
	// 查看审核历史记录
	function log(id){
		var url = root+"/CustomerServer/CheckLogListPage.do?id="+id;
		showWindow("历史", url, 600, 450, withdrawalsCheckFind);
	}
</script>
</html>