<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>健康包</title>
   	<jsp:include page="../common/header.jsp"></jsp:include>
</head>
<body class="easyui-layout">
	<div region="center" style="padding:0px;" border="false" header="false">  
	    <table border="false" fit="true" id="tt" class="easyui-datagrid" 
	    		url="${root}/admin/healthPackageCtrl/HealthPackageList.do" 
	    		iconCls="icon-save" sortName="createTime" sortOrder="desc" singleSelect="true"
	            rownumbers="true" idField="id" fitColumns="true" pagination="true" pageSize="20"
	            data-options="rownumbers:true,method:'post',toolbar:'#tb', queryParams:{isDelete:0}">
	        <thead>
	            <tr>
	            	<th data-options="field:'ck',checkbox:true"></th>
					<th field="id" width="200" hidden="true">id</th>
					<th field="price" width="120" formatter="formatOp">价格</th>
					<th field="creatorName" width="120" align="center">创建人</th>
					<th field="createTime" width="120" align="center">创建时间</th>
					<th field="delete" width="60" sortable="true" align="center" formatter="formatDelete">是否删除</th>
					<th data-options="field:'_operate', width:80, align:'center', formatter:formatOper">操作</th>
	            </tr>
	        </thead>
	    </table>
	    <div id="tb" style="padding:2px 5px;">
	    	<div style="margin-top: 2px;">
	    		<a href="javascript: addPackage();" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>    
         		<a href="javascript: delPackage();" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
	    		删除:
    			<select id="isDelete" class="easyui-combobox" panelHeight="auto" style="width:100px">
		            <option value="0">否</option>
		           	<option value="1">是</option>
		           	<option value="">全部</option>
		      	</select>
	    		创建人:<input id="creatorName"class="easyui-textbox" style="width:140px;">
		       	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" 
		       		plain="true" onclick="javascript: packageFind()">查询</a>
	       </div>
	    </div>
    </div>
</body>
<script type="text/javascript">

	// 编辑健康包
	function formatOp(val, row){
		return '<a href="#" mce_href="#" onclick="editPackage(\''+ row.id + '\')">'+val+'</a>';
	}
	
	// 编辑健康包
	function editPackage(id){
		var url = root+'/admin/healthPackageCtrl/EditPackagePage.do?id='+id;
	   	showWindow("编辑健康包", url, 300, 200, packageFind);
	}
	
	//格式化 是否在用
	function formatDelete(val, row){
		if(val == 0){
			return "否";
		}else if(val == 1){
			return "是";
		}else{
			return "其它";
		}
	}

	// 健康包查询
	function packageFind(){
		$('#tt').edatagrid('load',{
			isDelete: $("#isDelete").combobox('getValue'),
			creatorName:$("#creatorName").textbox('getValue')
		});
	}
	
	// 新增健康包
	function addPackage(){
		var url = root+'/admin/healthPackageCtrl/EditPackagePage.do';
	   	showWindow("编辑健康包", url, 300, 200, packageFind);
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
					url: root+"/admin/healthPackageCtrl/DeletePackage.do",
					data: "ids="+ids.join(','),
					success: function(msg){
						packageFind();
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
	
	// 配置描述
	function formatOper(val, row, index){
		return '<a href="#" onclick="description(\'' + row.id + '\')">配置描述</a>';
	}
	
	// 子项列表
	function description(id){
		var url = root+'/admin/healthPackageCtrl/DescriptionListPage.do?id='+id;
	  	showWindow("描述列表", url, document.body.clientWidth*0.8, document.body.clientHeight*0.8, packageFind);
	}
	
</script>
</html>