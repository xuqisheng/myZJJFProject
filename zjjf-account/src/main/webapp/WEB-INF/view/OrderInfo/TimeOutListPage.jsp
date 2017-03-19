<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>超时订单</title>
   	<jsp:include page="../common/header.jsp"></jsp:include>
</head>
<body class="easyui-layout">
	<div region="center" style="padding:0px;" border="false" header="false">  
	    <table border="false" fit="true" id="tt" class="easyui-datagrid" 
	    		url="${root}/admin/orderInfoctl/TimeOutList.do" 
	    		iconCls="icon-save" sortName="createTime" sortOrder="desc" singleSelect="true"
	            rownumbers="true" idField="id" fitColumns="true" pagination="true" pageSize="20"
	            data-options="rownumbers:true,method:'post',toolbar:'#tb'">
	        <thead>
	            <tr>
	            	<th data-options="field:'ck',checkbox:true"></th>
					<th field="id" width="200" hidden="true">id</th>
					<th field="orderNo" width="200" align="center">订单编号</th>
					<th field="doctorId" width="120" hidden="true">医生id</th>
					<th field="doctorName" width="100" align="center">医生姓名</th>
					<th field="doctorTelNo" width="120" align="center">医生电话</th>
					<th field="patientId" width="120" hidden="true">患者id</th>
					<th field="patientName" width="100" align="center">患者姓名</th>
					<th field="patientTelNo" width="120" align="center">患者电话</th>
					<th field="lable" width="120" align="center">服务名称</th>
					<th field="price" width="100" align="center">价格</th>
					<th field="createTime" width="120" align="center">创建时间</th>
	            </tr>
	        </thead>
	    </table>
	    <div id="tb" style="padding:2px 5px;">
	    	<div style="margin-top: 2px;">
	    		订单号:<input id="orderNo"class="easyui-textbox" style="width:140px;"/>
	    		医生姓名:<input id="doctorName"class="easyui-textbox" style="width:140px;"/>
	    		患者姓名:<input id="patientName"class="easyui-textbox" style="width:140px;"/>
		       	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" 
		       		plain="true" onclick="javascript: orderListFind()">查询</a>
	       </div>
	    </div>
    </div>
</body>
<script type="text/javascript">

	// 超时订单查询
	function orderListFind(){
		$('#tt').edatagrid('load',{
			orderNo:$("#orderNo").textbox('getValue'),
			doctorName:$("#doctorName").textbox('getValue'),
			patientName:$("#patientName").textbox('getValue')
		});
	}

</script>
</html>