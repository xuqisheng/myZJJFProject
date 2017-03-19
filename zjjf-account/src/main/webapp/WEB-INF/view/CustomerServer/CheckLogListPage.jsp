<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>审核历史记录</title>
   	<jsp:include page="../common/header.jsp"></jsp:include>
</head>
<body class="easyui-layout">
	<div region="center" style="padding:0px;" border="false" header="false">  
	    <table border="false" fit="true" id="tt" class="easyui-datagrid" 
	    		url="${root}/CustomerServer/CheckLogList.do?recordId=${recordId}" 
	    		iconCls="icon-save" sortName="checkTime" sortOrder="desc" singleSelect="true"
	            rownumbers="true" idField="id" fitColumns="true" pagination="true" pageSize="20"
	            data-options="rownumbers:true,method:'post'">
	        <thead>
	            <tr>
	            	<th data-options="field:'ck',checkbox:true"></th>
	            	<th field="id" width="100" sortable="true" hidden="true">id</th>
	                <th field="userName" width="100" sortable="true">用户名</th>
	                <th field="state" width="80" align="center" formatter="formatState">审核状态</th>
	                <th field="checkTime" width="120" align="center">审核时间</th>
	            </tr>
	        </thead>
	    </table>
    </div>
</body>
<script type="text/javascript">

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
	
</script>
</html>