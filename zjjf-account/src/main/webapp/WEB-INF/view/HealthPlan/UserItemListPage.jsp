<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户健康计划子项</title>
   	<jsp:include page="../common/header.jsp"></jsp:include>
</head>
<body class="easyui-layout">
	<div region="center" style="padding:0px;" border="false" header="false">  
	    <table border="false" fit="true" id="tt" class="easyui-datagrid" 
	    		url="${root}/admin/healthplanctl/UserItemList.do?userId=${userId}" 
	    		iconCls="icon-save" sortName="createTime" sortOrder="desc" singleSelect="true"
	            rownumbers="true" idField="id" fitColumns="true" pagination="true" pageSize="20"
	            data-options="rownumbers:true,method:'post',toolbar:'#tb'">
	        <thead>
	            <tr>
	            	<th data-options="field:'ck',checkbox:true"></th>
	            	<th field="typeName" width="100">类型名</th>
					<th field="itemName" width="100">子项名</th>
					<th field="finishStatus" width="100" sortable="true" align="center" formatter="formatFinishStatus">是否完成</th>
					<th field="createTime" width="100" align="center">创建时间</th>
	            </tr>
	        </thead>
	    </table>
	    <div id="tb" style="padding:2px 5px;">
	    	<div style="margin-top: 2px;">
	    		是否完成:
	    		<select id="finishStatus" class="easyui-combobox" panelHeight="auto" 
	    				style="width:100px" data-options="onSelect:doSearch">
		            <option value="">全部</option>
		            <option value="0">否</option>
		            <option value="1">是</option>
		        </select>
	       </div>
	    </div>
    </div>
</body>
<script type="text/javascript">

	//用户健康计划类型查询
	function doSearch(){
		 //查询参数直接添加在queryParams中    
	     var queryParams = $('#tt').datagrid('options').queryParams;
	     queryParams.finishStatus=getComboboxVal("finishStatus");
	     $('#tt').datagrid('options').queryParams=queryParams;        
	     $("#tt").datagrid('reload'); 
	}
	
	//格式化 是否完成
	function formatFinishStatus(val, row){
		if(val == 0){
			return "否";
		}else if(val == 1){
			return "是";
		}else{
			return "其它";
		}
	}
	
</script>
</html>