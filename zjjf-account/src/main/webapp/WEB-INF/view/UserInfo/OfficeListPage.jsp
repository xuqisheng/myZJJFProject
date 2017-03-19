<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>科室列表</title>
   	<jsp:include page="../common/header.jsp"></jsp:include>
</head>
<body class="easyui-layout">
	<div region="center" style="padding:0px;" border="false" header="false">  
	    <table border="false" fit="true" id="tt" class="easyui-datagrid" url="${root}/admin/office/OfficeList.do" 
	    		iconCls="icon-save" sortName="createTime" sortOrder="desc" singleSelect="true"
	            rownumbers="true" idField="id" fitColumns="true" pagination="true" pageSize="20"
	            data-options="rownumbers:true,method:'post',toolbar:'#tb'">
	        <thead>
	            <tr>
	            	<th data-options="field:'ck',checkbox:true"></th>
	                <th field="id" width="200" sortable="true" hidden="true">科室id</th>
	                <th field="name" width="400" sortable="true" formatter="formatOp">科室名称</th>
	                <th field="creatorId" width="200" hidden="true">创建人id</th>
	                <th field="creatorName" width="200">创建人</th>
	                <th field="createTime" width="200" align="center" sortable="true">创建时间</th>
	                <th field="isDelete" width="150" formatter="formatIsDelete">是否删除</th>
	            </tr>
	        </thead>
	    </table>
	    <div id="tb" style="padding:2px 5px;">
	    	<div style="margin-top: 2px;">
	    		<a href="javascript: addOffice();" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>    
         		<a href="javascript: delOffice();" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
	    		名称:<input id="name"class="easyui-textbox" style="width:140px;">
			       删除:
	    		<select id="isDelete" class="easyui-combobox" panelHeight="auto" style="width:100px">
		           	<option value="0">否</option>
		           	<option value="1">是</option>
		           	<option value="-1">全部</option>
		       	</select>
		       	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" 
		       		plain="true" onclick="javascript:officeFind()">查询</a>
	       </div>
	    </div>
    </div>
</body>
<script type="text/javascript">
	//格式化是否在用
	function formatIsDelete(val, row){
		if(val == 0){
			return "否";
		}else if(val == 1){
			return "是";
		}else{
			return "其它";
		}
	}
	
	// 科室搜索
	function officeFind(){
		$('#tt').edatagrid('reload',{
			name: $("#name").textbox('getValue'),
			isDelete: $("#isDelete").combobox('getValue')
		});
	}
	
	// 编辑科室链接
	function formatOp(val, row){
		return '<a href="#" mce_href="#" onclick="editOffice(\''+ row.id + '\')">'+val+'</a> ';
	}
	
	// 编辑科室
	function editOffice(id){
		var url = root+'/admin/office/EditPage.do?id='+id;
	   	showWindow("科室编辑", url, 400, 160, officeFind);
	}
	
	// 新增科室
	function addOffice(){
		var url = root+'/admin/office/EditPage.do';
	   	showWindow("科室编辑", url, 400, 160, officeFind);
	}
	
	// 删除科室
	function delOffice(){
		var ids=getSelections();
		if(ids.length==0){
		  	alertMsg('请选择一行进行删除！');
		   	return;
		 }
		 $.messager.confirm('提示', "是否确认删除？", function(r){
			if(r){
				$.ajax({
					type: "POST",
					url: root+"/admin/office/Delete.do",
					data: "ids="+ids.join(','),
					success: function(msg){
						officeFind();
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