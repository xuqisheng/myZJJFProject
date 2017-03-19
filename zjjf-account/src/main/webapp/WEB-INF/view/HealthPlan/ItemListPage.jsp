<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="../common/header.jsp"></jsp:include>
	<title>健康计划子项列表</title>
</head>
<body class="easyui-layout">
	<div region="center" style="padding:0px;" border="true" header="false">  
	    <table border="false" fit="true" id="tt" class="easyui-datagrid" 
	    		url="${root}/admin/healthplanctl/ItemList.do?healthPlanId=${healthPlanId}" 
	    		iconCls="icon-save" sortName="createTime" sortOrder="desc" singleSelect="true"
	            rownumbers="true" idField="id" fitColumns="true" pagination="true" pageSize="20"
	            data-options="rownumbers:true,method:'post',toolbar:'#tb'">
	        <thead>
	            <tr>
	            	<th data-options="field:'ck',checkbox:true"></th>
	            	<th field="id" width="200" sortable="true" hidden="true">子项目id</th>
	                <th field="name" width="100" formatter="formatOp">名称</th>
	                <th field="content" width="100" sortable="true">内容</th>
	                <th field="createTime" width="120" align="center" sortable="true">创建时间</th>
	                <th field="creatorName" width="120" align="center">创建人</th>
	                <th field="orderIndex" width="80" align="center">排序</th>
	                <th field="delete" width="80" align="center" formatter="formatDelete">是否删除</th>
	                <!-- <th data-options="field:'_operate', width:80, align:'center', formatter:formatOper">操作</th> -->
	            </tr>
	        </thead>
	    </table>
	    <div id="tb" style="padding:2px 5px;">
	    	<div style="margin-top: 2px;">
	    		<a href="javascript: addItem();" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>    
         		<a href="javascript: delItem();" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
	    		删除:
    			<select id="isDelete" class="easyui-combobox" panelHeight="auto" 
    					style="width:100px" data-options="onSelect:doSearch">
		            <option value="0">否</option>
		           	<option value="1">是</option>
		           	<option value="-1">全部</option>
		      	</select>
	       </div>
	    </div>
    </div>

    <div region="south" style="padding:0px; height:50%" border="true" header="false">  
	    <table border="false" fit="true" id="tt_time" class="easyui-datagrid" 
	    		iconCls="icon-save" sortName="createTime" sortOrder="desc" singleSelect="true"
	            rownumbers="true" idField="id" fitColumns="true" pagination="true" pageSize="20"
	            data-options="rownumbers:true,method:'post',toolbar:'#tb_time'">
	        <thead>
	            <tr>
	            	<th data-options="field:'ck',checkbox:true"></th>
	            	<th field="id" width="200" sortable="true" hidden="true">执行时间id</th>
	                <th field="healthPlanId" width="100" hidden="true">计划id</th>
	                <th field="healthPlanItemId" width="100" hidden="true">子项id</th>
	                <th field="type" width="120" align="center" formatter="formatType">类型</th>
	                <th field="date" width="120" align="center">执行日期</th>
	                <th field="time" width="120" align="center" formatter="formatTime">执行时间</th>
	                <th field="createTime" width="120" align="center">创建时间</th>
	                <th field="creatorName" width="120" align="center">创建人</th>
	                <!-- <th field="delete" width="120" align="center" formatter="formatDelete">是否删除</th> -->
	                <!-- <th data-options="field:'_operate', width:80, align:'center', formatter:formatOper">操作</th> -->
	            </tr>
	        </thead>
	    </table>
	    <div id="tb_time" style="padding:2px 5px;">
	    	<div style="margin-top: 2px;">
	    		<a href="javascript: addTime(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true">每日</a>
	    		<a href="javascript: addTime(1);" class="easyui-linkbutton" iconCls="icon-add" plain="true">每周</a>
	    		<a href="javascript: addTime(2);" class="easyui-linkbutton" iconCls="icon-add" plain="true">每月</a>
	    		<a href="javascript: addTime(3);" class="easyui-linkbutton" iconCls="icon-add" plain="true">每年</a>    
	    		<a href="javascript: addTime(4);" class="easyui-linkbutton" iconCls="icon-add" plain="true">仅一次</a>
         		<a href="javascript: delTime();" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
	       </div>
	    </div>
    </div>
</body>
<script type="text/javascript">
	// 健康计划id
	var healthPlanId = "${healthPlanId}";
	
	// 健康计划子项id
	var healthPlanItemId = "";
	
	$(function(){
		$("#tt").datagrid({
			onClickRow: function(rowIndex, rowData){
				healthPlanItemId = rowData.id;
				$("#tt_time").datagrid({url:"${root}/admin/healthplanctl/ItemTimeList.do?healthPlanItemId="+healthPlanItemId});
			}
		});
	});

	// 增加子项
	function addItem(){
		var url = root+'/admin/healthplanctl/EditItemPage.do?healthPlanId=${healthPlanId}';
	   	showWindow("编辑健康计划子项", url, 400, 300, doSearch);
	}
	
	// 点击表单查询
	function doSearch(){
		 //查询参数直接添加在queryParams中    
	     var queryParams = $('#tt').datagrid('options').queryParams;
	     queryParams.isDelete=getComboboxVal("isDelete");
	     $('#tt').datagrid('options').queryParams=queryParams;
	     $("#tt").datagrid('reload'); 
	}
	
	// 格式化 是否在用
	function formatDelete(val, row){
		if(val == 0){
			return "否";
		}else if(val == 1){
			return "是";
		}else{
			return "其它";
		}
	}
	
	// 编辑子项链接
	function formatOp(val, row){
		return '<a href="#" mce_href="#" onclick="editItem(\''+ row.id + '\')">'+val+'</a>';
	}
	
	// 编辑子项链接
	function editItem(id){
		var url = root+'/admin/healthplanctl/EditItemPage.do?id='+id;
	   	showWindow("编辑健康计划子项", url, 400, 300, doSearch);
	}
	
	// 删除子项
	function delItem(){
		var ids=getSelections();
		if(ids.length==0){
		  	alertMsg('请选择一行进行删除！');
		   	return;
		 }
		 $.messager.confirm('提示', "是否确认删除？", function(r){
			if(r){
				$.ajax({
					type: "POST",
					url: root+"/admin/healthplanctl/DeleteItem.do",
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
	
	// 格式化 执行频率
	function formatType(val, row){
		if(val == 0){
			return "每日";
		}else if(val == 1){
			return "每周";
		}else if(val == 2){
			return "每月";
		}else if(val == 3){
			return "每年";
		}else if(val == 4){
			return "仅一次";
		}else{
			return "其它";
		}
	}
	
	// 编辑子项时间链接
	function formatTime(val, row){
		return '<a href="#" mce_href="#" onclick="editItemTime(\''+ row.id + '\')">'+val+'</a>';
	}
	
	// 编辑子项时间方法
	function editItemTime(id){
		var url = root+'/admin/healthplanctl/EditItemTimePage.do?id='+id;
	   	showWindow("编辑健康计划子项", url, 400, 300, doSearch_time);
	}

	// 增加子项执行时间
	function addTime(type){
		if(healthPlanItemId == ""){
			alert("请 点击 选择一条子项");
			return;
		}
		var url = root+'/admin/healthplanctl/EditItemTimePage.do?healthPlanId='+healthPlanId+'&healthPlanItemId='+healthPlanItemId+'&type='+type;
	   	showWindow("编辑健康计划子项执行时间", url, 400, 300, doSearch_time);
	}
	
	// 编辑子项时间后，重新加载表格
	function doSearch_time(){
		$("#tt_time").datagrid({url:"${root}/admin/healthplanctl/ItemTimeList.do?healthPlanItemId="+healthPlanItemId});
	}
	
	// 删除子项执行时间
	function delTime(){
		var ids=getSelections_time();
		if(ids.length==0){
		  	alertMsg('请选择一行进行删除！');
		   	return;
		 }
		 $.messager.confirm('提示', "是否确认删除？", function(r){
			if(r){
				$.ajax({
					type: "POST",
					url: root+"/admin/healthplanctl/DeleteItemTime.do",
					data: "ids="+ids.join(','),
					success: function(msg){
						doSearch_time();
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
	function getSelections_time(){
	    var ss = [];
	    var rows = $('#tt_time').datagrid('getSelections');
	    for(var i=0; i<rows.length; i++){
	        var row = rows[i];
	        ss.push(row.id);
	    }
	    return ss;
	}
</script>
</html>