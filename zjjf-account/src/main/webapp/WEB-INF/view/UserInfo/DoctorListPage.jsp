<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="../common/header.jsp"></jsp:include>
	<title>经纪人对应的医生列表</title>
</head>
<body class="easyui-layout">
	<div region="center" style="padding:0px;" border="true" header="false">  
	    <table border="false" fit="true" id="tt" class="easyui-datagrid" 
	    		url="${root}/admin/brokerctl/DoctorList.do?brokerId=${brokerId}" 
	    		iconCls="icon-save" sortName="createTime" sortOrder="desc" singleSelect="true"
	            rownumbers="true" idField="id" fitColumns="true" pagination="true" pageSize="20"
	            data-options="rownumbers:true,method:'post',toolbar:'#tb'">
	        <thead>
	            <tr>
	            	<th data-options="field:'ck',checkbox:true"></th>
	            	<th field="id" width="200" sortable="true" hidden="true">医生id</th>
	                <th field="hpName" width="100" align="center" sortable="true">医院</th>
	                <th field="ocName" width="100" align="center" sortable="true">科室</th>
	                <th field="name" width="120" align="center" sortable="true">医生</th>
	                <th field="telNo" width="120" align="center" sortable="true">电话</th>
	                <th field="rank" width="80" align="center" sortable="true">等级</th>
	            </tr>
	        </thead>
	    </table>
	    <div id="tb" style="padding:2px 5px;">
	    	<div style="margin-top: 2px;">
	    		医院:<input id="hpName"class="easyui-textbox" style="width:140px;"/>
	    		科室:<input id="ocName"class="easyui-textbox" style="width:140px;"/>
	    		医生:<input id="name"class="easyui-textbox" style="width:140px;"/>
		       	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" 
		       		plain="true" onclick="javascript: configFind()">查询</a>
	       </div>
    </div>
</body>
<script type="text/javascript">
	// 医生列表查询
	function configFind(){
		$('#tt').edatagrid('load',{
			hpName: $("#hpName").textbox('getValue'),
			ocName: $("#ocName").textbox('getValue'),
			name: $("#name").textbox('getValue')
		});
	}
</script>
</html>