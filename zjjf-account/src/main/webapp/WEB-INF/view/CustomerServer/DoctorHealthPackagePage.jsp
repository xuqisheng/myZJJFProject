<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="../common/header.jsp"></jsp:include>
	<title>健康包配置</title>
</head>
<body class="easyui-layout">
	<input type="hidden" name="doctorId" id="doctorId" value="${doctorId}"/>
	<div region="center" style="padding:0px;" border="false" header="false">  
	    <table border="false" fit="true" id="tt" class="easyui-datagrid" 
	    		url="${root}/admin/doctorctl/DoctorHealthPackageList.do?doctorId=${doctorId}" 
	    		iconCls="icon-save" sortName="createTime" sortOrder="desc" singleSelect="true"
	            rownumbers="true" idField="id" fitColumns="true" pagination="true" pageSize="20"
	            data-options="rownumbers:true,method:'post',toolbar:'#tb'">
	        <thead>
	            <tr>
	            	<th data-options="field:'ck',checkbox:true"></th>
					<th field="id" width="200" hidden="true">健康包id</th>
					<th field="price" width="120">价格</th>
					<th field="creatorName" width="120" align="center">创建人</th>
					<th field="createTime" width="120" align="center">创建时间</th>
	            </tr>
	        </thead>
	    </table>
	    <div id="tb" style="padding:2px 5px;">
	    	<div style="margin-top: 2px;">
	    		<a href="javascript: addPackage();" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>    
         		<a href="javascript: delPackage();" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
	       </div>
	    </div>
    </div>
</body>
<script type="text/javascript">

	// 增加健康包
	function addPackage(){
		var url = root+'/admin/doctorctl/AddHealthPackagePage.do?doctorId=${doctorId}';
	   	showWindow("健康包配置", url, 400, 200, doSearch);
	}
	
	// 点击表单查询
	function doSearch(){
		//查询参数直接添加在queryParams中    
		var queryParams = $('#tt').datagrid('options').queryParams;
		$('#tt').datagrid('options').queryParams=queryParams;
	   	$("#tt").datagrid('reload'); 
	}
	
	// 删除健康包
	function delPackage(){
		var ids=getSelections();
		if(ids.length==0){
		  	alertMsg('请选择一行进行删除！');
		   	return;
		 }
		 $.messager.confirm('提示', "是否确认删除？", function(r){
			if(r){
				$.ajax({
					type: "POST",
					url: root+"/admin/doctorctl/DeleteHealthPackage.do?doctorId=${doctorId}",
					data: "ids="+ids.join(','),
					success: function(msg){
						doSearch();
					},
					error: function (XMLHttpRequest, textStatus, errorThrown) {
					 	// 通常 textStatus 和 errorThrown 之中
					 	// 只有一个会包含信息
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
</script>
</html>