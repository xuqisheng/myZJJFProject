<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>支付医生列表</title>
   	<jsp:include page="../common/header.jsp"></jsp:include>
</head>
<body class="easyui-layout">
	<div region="center" style="padding:0px;" border="false" header="false">  
	    <table border="false" fit="true" id="tt" class="easyui-datagrid" url="${root}/CustomerServer/PayDoctorList.do" 
	    		iconCls="icon-save" sortName="createTime" sortOrder="desc" 
	            rownumbers="true" idField="id" fitColumns="true" pagination="true" pageSize="20"
	            data-options="rownumbers:true,method:'post',toolbar:'#tb'">
	        <thead>
	            <tr>
	            	<th data-options="field:'ck',checkbox:true"></th>
	            	<th field="id" width="100" sortable="true" hidden="true">id</th>
	            	<th field="pdId" width="100" sortable="true" hidden="true">pdId</th>
	                <th field="orderNo" width="150" sortable="true">订单号</th>
	              	<th field="hpName" width="120" sortable="true">医院</th>
	                <th field="ofName" width="80" sortable="true">科室</th>
	                <th field="doctorName" width="80" sortable="true">医生</th>
	                <th field="doctorTelNo" width="100" sortable="true">医生电话</th>
	                <th field="lable" width="80" align="center">服务名称</th>
	                <th field="price" width="80" sortable="true">服务价格</th>
	                <th field="createTime" width="100" align="center">订单创建时间</th>
	                <th field="payDoctorState" width="80" align="center" formatter="formatState">支付状态</th>
	                <th field="nikename" width="80" align="center">支付人</th>
	                <th field="payTime" width="100" align="center">支付时间</th>
	                <th data-options="field:'_operate',width:80,align:'center',formatter:formatOper">操作</th>
	            </tr>
	        </thead>
	    </table>
	    <div id="tb" style="padding:2px 5px;">
	    	<div style="margin-top: 2px;">
	    		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" 
		       		plain="true" onclick="javascript:payAll()">批量支付</a>
	    		订单编号:<input id="orderNo" class="easyui-textbox" style="width:140px"/>
		       	医院名称:<input id="hpName" class="easyui-textbox" style="width:140px"/>
		       	医生名称:<input id="doctorName" class="easyui-textbox" style="width:140px"/>
		       	支付状态:
		        <select id="payDoctorState" class="easyui-combobox" panelHeight="auto" style="width:100px">
		            <option value="">全部</option>
		            <option value="0">未支付</option>
		            <option value="1">已支付</option>
		            <option value="-1">待处理</option>
		        </select>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" 
		       		plain="true" onclick="javascript:payFind()">查询</a>
		       	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-excel" 
		       		plain="true" onclick="javascript:payExcel()">导出excel</a>
	       </div>
	    </div>
    </div>
</body>
<script type="text/javascript">

	// 格式化 支付状态
	function formatState(val, row){
		if(val == 0){
			return "未支付";
		}else if(val == 1){
			return "已支付";
		}else{
			return "待处理";
		}
	}
	
	// 批量支付
	function payAll(){
		var ids=getSelections();
		if(ids.length==0){
		  	alertMsg('请选择一行进行支付！');
		   	return;
		 }
		 $.messager.confirm('提示', "是否确认批量支付？", function(r){
			if(r){
				$.ajax({
					type: "POST",
					url: root+"/CustomerServer/BatchPay.do",
					data: "ids="+ids.join(','),
					success: function(msg){
						payFind();
					},
					error: function (XMLHttpRequest, textStatus, errorThrown) {
					 	this; // 调用本次AJAX请求时传递的options参数
					 	$.messager.alert('抱歉', errorThrown, 'warning');
					}
				});
			}
		});
	}
	
	// 获取选中项
	function getSelections(){
	    var ss = [];
	    var rows = $('#tt').datagrid('getSelections');
	    for(var i=0; i<rows.length; i++){
	        var row = rows[i];
	        ss.push(row.id);
	    }
	    return ss;
	}

	// 点击表单查询
	function payFind(){
		//查询参数直接添加在queryParams中    
	    var queryParams = $('#tt').datagrid('options').queryParams;  
	    queryParams.orderNo = $("#orderNo").val();
	    queryParams.hpName = $("#hpName").val();
	    queryParams.doctorName = $("#doctorName").val();
	    queryParams.payDoctorState = getComboboxVal("payDoctorState");
	    $('#tt').datagrid('options').queryParams=queryParams;        
	    $("#tt").datagrid('reload'); 
	}
	
	// 处理支付
	function formatOper(val, row, index){
		return '<a href="#" onclick="pay(\''+row.id+'\', \''+row.pdId+'\')">标记为已打款</a>';
	}
	
	// 进入 处理支付 页面
	function pay(orderId, pdId){
		var url = root+"/CustomerServer/PayPage.do?orderId="+orderId+"&pdId="+pdId;
		showWindow("支付", url, 520, 200, payFind);
	}

	// 导出excel
	function payExcel(){
		var orderNo = $("#orderNo").val();
		var hpName = $("#hpName").val();
		var doctorName = $("#doctorName").val();
		var payDoctorState = getComboboxVal("payDoctorState");
		hpName = encodeURIComponent(encodeURIComponent(hpName));
		doctorName = encodeURIComponent(encodeURIComponent(doctorName));
		window.location.href = "${root}/CustomerServer/PayExcel.do"
			+ "?orderNo="+orderNo+"&hpName="+hpName+"&doctorName="+doctorName+"&payDoctorState="+payDoctorState;
	}
</script>
</html>