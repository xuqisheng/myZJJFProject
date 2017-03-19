<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>crm访问记录列表</title>
   	<jsp:include page="../common/header.jsp"></jsp:include>
</head>
<body class="easyui-layout">
	<div region="center" style="padding:0px;" border="false" header="false">  
	    <table border="false" fit="true" id="tt" class="easyui-datagrid" 
	    		url="${root}/admin/sysmonitorctl/VisitLogList.do" 
	    		iconCls="icon-save" sortName="visitTime" sortOrder="desc" singleSelect="true"
	            rownumbers="true" idField="id" fitColumns="true" pagination="true" pageSize="20"
	            data-options="rownumbers:true,method:'post',toolbar:'#tb'">
	        <thead>
	            <tr>
	            	<th data-options="field:'ck',checkbox:true"></th> <!-- sortable="true" -->
	                <th field="id" width="200" hidden="true">记录id</th>
	                <th field="ip" width="100" >用户ip</th>
	                <th field="userId" width="150" hidden="true">用户名id</th>
	                <th field="userName" width="100">用户名</th>
	                <th field="url" width="200">访问地址</th>
	                <th field="authName" width="200">地址名称</th>
	                <th field="visitTime" width="100">访问时间</th>
	            </tr>
	        </thead>
	    </table>
	    <div id="tb" style="padding:2px 5px;">
	    	<div style="margin-top: 2px;">
	    		用户ip：<input id="ip"class="easyui-textbox" style="width:140px;">
	    		用户名：<input id="userName"class="easyui-textbox" style="width:140px;">
		       	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" 
		       		plain="true" onclick="javascript: vistLogFind()">查询</a>
	       </div>
	    </div>
    </div>
</body>
<script type="text/javascript">

	// 访问记录搜索
	function vistLogFind(){
		$('#tt').edatagrid('load',{
			ip: $("#ip").textbox('getValue'),
			userName: $("#userName").textbox('getValue')
		});
	}

</script>
</html>