<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
   	<jsp:include page="../common/header.jsp"></jsp:include>
</head>
<body class="easyui-layout">
	<div region="center" style="padding:0px;" border="false" header="false">  
		<table border="false" fit="true" id="tt" iconCls="icon-save" singleSelect="true"
				sortName="createTime" sortOrder="desc" rownumbers="true"  
				idField="id" fitColumns="true" pagination="true" pageSize="20"
				data-options="rownumbers:true,method:'post',toolbar:'#tb'">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th field="name" width="200"  formatter="formatOp" editor="{type:'validatebox',options:{required:true}}">名称</th>
					<th field="hotCount" width="100" sortable="true" editor="{type:'numberbox',options:{required:true}}">热度</th>
					<th field="state" width="60" sortable="true" align="center" formatter="formatState" 
						editor="{type:'combobox',options:{required:true,editable:false,data:[{'id':'0','text':'可用'},{'id':'1','text':'不可用'}],valueField:'id',textField:'text'}}">状态</th>
					<th field="orderIndex"  width="100" align="right" sortable="true" editor="{type:'numberbox',options:{required:true}}">排序</th>
					<th field="createTime" width="120" align="center" sortable="true">创建时间</th>
					<th field="creatorName" width="120" align="center" >创建人</th>
				</tr>
			</thead>
		</table>  
		<div id="tb" style="padding:2px 5px;">
			<div style="margin-top: 2px;">
				<input id="tbLikeName" class="easyui-searchbox" data-options="prompt:'请输入名称进行查询',searcher:doSearch" style="width:300px"></input>
				状态: 
				<select id="state" class="easyui-combobox" panelHeight="auto" style="width:100px" data-options="onSelect:doSearch">
					<option value="-1">全部</option>
					<option value="0">可用</option>
					<option value="1">不可用</option>
				</select>
			</div>
			<div style="margin-top: 5px;">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:$('#tt').edatagrid('addRow')">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:$('#tt').edatagrid('destroyRow')">删除</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:$('#tt').edatagrid('saveRow')">保存</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="javascript:$('#tt').edatagrid('cancelRow')">取消</a>   
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
	    $('#tt').edatagrid({
	    	url : root+"/admin/thematic/List.do",
	        saveUrl: root+'/admin/thematic/Add.do',
	        updateUrl: root+'/admin/thematic/Update.do',
	        destroyUrl: root+'/admin/thematic/Delete.do',
	        queryParams:{state: 0}
	    });
	});
	
	// 点击表单查询
	function doSearch(asdasd){
		//查询参数直接添加在queryParams中    
        var queryParams = $('#tt').datagrid('options').queryParams;  
        queryParams.likeName =$("#tbLikeName").val();
        queryParams.state=getComboboxVal("state");
        $('#tt').datagrid('options').queryParams=queryParams;        
        $("#tt").datagrid('reload'); 
	}
	
	// 格式化状态
	function formatState(val,row){
		if (val == 0){
			return '可用';
		} else {
			return '不可用';
		}
 	}
	 
	// 查看专题链接
	function formatOp(val,row){
		return '<a href="#" mce_href="#" onclick="edit('+ row.id + ')">'+val+'</a> ';  
 	}
	 
	// 查看专题
    function edit(id){
		var url = root+'/admin/thematic/EditPage.do?id='+id;
		showWindow("专题编辑", url, 500, 300, doSearch);
    }
</script>
</html>