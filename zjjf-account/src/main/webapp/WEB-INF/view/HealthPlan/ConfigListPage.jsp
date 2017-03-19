<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>健康计划配置</title>
   	<jsp:include page="../common/header.jsp"></jsp:include>
</head>
<body class="easyui-layout">
	<div region="center" style="padding:0px;" border="false" header="false">  
	    <table border="false" fit="true" id="tt" class="easyui-datagrid" url="${root}/admin/healthplanctl/ConfigList.do" 
	    		iconCls="icon-save" sortName="createTime" sortOrder="desc" singleSelect="true"
	            rownumbers="true" idField="id" fitColumns="true" pagination="true" pageSize="20"
	            data-options="rownumbers:true,method:'post',toolbar:'#tb'">
	        <thead>
	            <tr>
	            	<th data-options="field:'ck',checkbox:true"></th>
					<th field="name" width="200" formatter="formatOp">名称</th>
					<th field="description" width="200">描述</th>
					<th field="isRecommend" width="60" sortable="true" align="center" formatter="formatRecommend">是否推荐</th>
					<th field="count" width="120" align="center" sortable="true">关注次数</th>
					<th field="creatorName" width="120" align="center">创建人</th>
					<th field="createTime" width="120" align="center">创建时间</th>
					<th field="delete" width="60" sortable="true" align="center" formatter="formatDelete">是否删除</th>
					<th data-options="field:'_operate', width:80, align:'center', formatter:formatOper">操作</th>
	            </tr>
	        </thead>
	    </table>
	    <div id="tb" style="padding:2px 5px;">
	    	<div style="margin-top: 2px;">
	    		<a href="javascript: addPlan();" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>    
         		<a href="javascript: delPlan();" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
	    		计划名称:<input id="name"class="easyui-textbox" style="width:140px;">
	    		是否推荐:
	    		<select id="isRecommend" class="easyui-combobox" panelHeight="auto" style="width:100px">
		            <option value="">全部</option>
		            <option value="0">否</option>
		            <option value="1">是</option>
		        </select>
	    		删除:
    			<select id="isDelete" class="easyui-combobox" panelHeight="auto" style="width:100px">
		            <option value="0">否</option>
		           	<option value="1">是</option>
		           	<option value="-1">全部</option>
		      	</select>
	    		创建人:<input id="creatorName"class="easyui-textbox" style="width:140px;">
		       	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" 
		       		plain="true" onclick="javascript: configFind()">查询</a>
	       </div>
	    </div>
    </div>
</body>
<script type="text/javascript">
	// 编辑健康计划链接
	function formatOp(val, row){
		return '<a href="#" mce_href="#" onclick="editPlan(\''+ row.id + '\')">'+val+'</a>';
	}
	
	// 编辑健康计划
	function editPlan(id){
		var url = root+'/admin/healthplanctl/EditPlanPage.do?id='+id;
	   	showWindow("编辑健康计划", url, 460, 420, configFind);
	}

	// 格式化 是否推荐
	function formatRecommend(val, row){
		if(val == 0){
			return "否";
		}else if(val == 1){
			return "是";
		}else{
			return "其它";
		}
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
	
	// 配置子项
	function formatOper(val, row, index){
		return '<a href="#" onclick="itemList(\'' + row.id + '\')">配置子项</a>';
	}
	
	// 子项列表
	function itemList(id){
		var url = root+'/admin/healthplanctl/ItemListPage.do?id='+id;
	  	showWindow("子项列表", url, document.body.clientWidth*0.8, document.body.clientHeight*0.8, configFind);
	}

	// 健康计划查询
	function configFind(){
		$('#tt').edatagrid('load',{
			name: $("#name").textbox('getValue'),
			isRecommend: $("#isRecommend").combobox('getValue'),
			isDelete: $("#isDelete").combobox('getValue'),
			creatorName:$("#creatorName").textbox('getValue')
		});
	}

	// 新增健康计划
	function addPlan(){
		var url = root+'/admin/healthplanctl/EditPlanPage.do';
	   	showWindow("编辑健康计划", url, 460, 420, configFind);
	}
	
	// 删除健康计划
	function delPlan(){
		var ids=getSelections();
		if(ids.length==0){
		  	alertMsg('请选择一行进行删除！');
		   	return;
		 }
		 $.messager.confirm('提示', "是否确认删除？", function(r){
			if(r){
				$.ajax({
					type: "POST",
					url: root+"/admin/healthplanctl/DeletePlan.do",
					data: "ids="+ids.join(','),
					success: function(msg){
						configFind();
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