<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="../common/header.jsp"></jsp:include>
	<title>健康包描述</title>
</head>
<body class="easyui-layout">
	<div region="center" style="padding:0px;" border="true" header="false">  
	    <table border="false" fit="true" id="tt" class="easyui-datagrid" 
	    		url="${root}/admin/healthPackageCtrl/DescriptionList.do?healthPackageId=${healthPackageId}" 
	    		iconCls="icon-save" sortName="createTime" sortOrder="desc" singleSelect="true"
	            rownumbers="true" idField="id" fitColumns="true" pagination="true" pageSize="20"
	            data-options="rownumbers:true,method:'post',toolbar:'#tb',queryParams:{isDelete:0}">
	        <thead>
	            <tr>
	            	<th data-options="field:'ck',checkbox:true"></th>
	            	<th field="id" width="200" sortable="true" hidden="true">子项目id</th>
	                <th field="item" width="100" formatter="formatOp">名称</th>
	                <th field="description" width="100" sortable="true">内容</th>
	                <th field="createTime" width="120" align="center" sortable="true">创建时间</th>
	                <th field="creatorName" width="120" align="center">创建人</th>
	                <th field="delete" width="80" align="center" formatter="formatDelete">是否删除</th>
	                <th field="price" width="200" sortable="true" hidden="true">价格</th>
	            </tr>
	        </thead>
	    </table>
	    <div id="tb" style="padding:2px 5px;">
	    	<div style="margin-top: 2px;">
	    		<a href="javascript: addDescription();" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>    
         		<a href="javascript: delDescription();" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
         		删除:
    			<select id="isDelete" class="easyui-combobox" panelHeight="auto" 
    					style="width:100px" data-options="onSelect:doSearch">
		            <option value="0">否</option>
		           	<option value="1">是</option>
		           	<option value="">全部</option>
		      	</select>
	       </div>
	    </div>
    </div>
</body>
<script type="text/javascript">

	// 编辑子项链接
	function formatOp(val, row){
		var name = "";
		if(val == 0){
			name = "健康习惯短信提醒";
		}else if(val == 1){
			name = "家庭医生服务";
		}else if(val == 2){
			name = "VIP门诊预约服务";
		}else if(val == 3){
			name = "健康体检";
		}else if(val == 4){
			name = "健康讲座";
		}else if(val == 5){
			name = "健康辅助工具使用";
		}else{
			name = "其它";
		}
		return '<a href="#" mce_href="#" onclick="editDescription(\''+ row.id + '\')">'+name+'</a>';
	}
	
	// 编辑子项链接
	function editDescription(id){
		var url = root+'/admin/healthPackageCtrl/EditDescriptionPage.do?id='+id;
	   	showWindow("编辑健康包描述", url, 400, 300, doSearch);
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
	
	// 点击表单查询
	function doSearch(){
		 //查询参数直接添加在queryParams中    
	     var queryParams = $('#tt').datagrid('options').queryParams;
	     queryParams.isDelete=getComboboxVal("isDelete");
	     $('#tt').datagrid('options').queryParams=queryParams;
	     $("#tt").datagrid('reload'); 
	}
	
	// 增加健康计划包
	function addDescription(){
		var url = root+'/admin/healthPackageCtrl/EditDescriptionPage.do?healthPackageId=${healthPackageId}';
	   	showWindow("编辑健康包描述", url, 400, 300, doSearch);
	}
	
	// 删除健康计划包
	function delDescription(){
		var ids=getSelections();
		if(ids.length==0){
		  	alertMsg('请选择一行进行删除！');
		   	return;
		 }
		 $.messager.confirm('提示', "是否确认删除？", function(r){
			if(r){
				$.ajax({
					type: "POST",
					url: root+"/admin/healthPackageCtrl/DeleteDescription.do",
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