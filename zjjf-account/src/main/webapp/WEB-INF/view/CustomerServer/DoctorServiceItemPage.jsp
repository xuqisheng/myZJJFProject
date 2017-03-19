<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="../common/header.jsp"></jsp:include>
	<title>医生服务项目编辑</title>
</head>
<body class="easyui-layout">
	<input type="hidden" name="doctorId" id="doctorId" value="${doctorId}"/>
	<div region="center" style="padding:0px;" border="false" header="false">  
	    <table border="false" fit="true" id="tt" class="easyui-datagrid" 
	    		url="${root}/admin/doctorctl/DoctorServiceItemList.do?doctorId=${doctorId}" 
	    		iconCls="icon-save" sortName="orderIndex" sortOrder="desc" singleSelect="true"
	            rownumbers="true" idField="id" fitColumns="true" pagination="true" pageSize="20"
	            data-options="rownumbers:true,method:'post',toolbar:'#tb'">
	        <thead>
	            <tr>
	            	<th data-options="field:'ck',checkbox:true"></th>
	            	<th field="id" width="200" sortable="true" hidden="true">医生服务项目id</th>
	                <th field="dtName" width="100">医生</th>
	                <th field="itName" width="100" sortable="true" formatter="formatOper">项目</th>
	                <th field="proportion" width="60" sortable="true" align="center">分成</th>
	                <th field="price" width="60" sortable="true" align="center">价格</th>
	                <th field="isDelete" width="60" sortable="true" align="center" formatter="formatDelete">状态</th>
	                <th field="createTime" width="140" align="center" sortable="true">创建时间</th>
	                <!-- <th field="creatorNikeName" width="80" align="center">创建人</th>
	                <th data-options="field:'_operate',width:80,align:'center',">操作</th> -->
	            </tr>
	        </thead>
	    </table>
	    <div id="tb" style="padding:2px 5px;">
	    	<div style="margin-top: 2px;">
	    		<a href="javascript: addItem();" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>    
         		<a href="javascript: delItem();" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
	       </div>
	    </div>
    </div>
</body>
<script type="text/javascript">

	//点击表单查询
	function doSearch(){
		//查询参数直接添加在queryParams中    
		var queryParams = $('#tt').datagrid('options').queryParams;  
		$('#tt').datagrid('options').queryParams=queryParams;        
		$("#tt").datagrid('reload'); 
	}

	// 格式化是否在用
	function formatDelete(val, row){
		if(val == 0){
			return "在用";
		}else if(val == 1){
			return "删除";
		}else{
			return "其它";
		}
	}
	
	// 编辑项目链接
	function formatOper(val, row, index){
		return '<a href="#" onclick="updateDoctorServiceItem(' + row.id + ')">' + val + '</a>';
	}
	
	// 编辑项目
	function updateDoctorServiceItem(doctorServiceItemId){
	  	var url = root+'/admin/doctorctl/UpdateDoctorServiceItemPage.do?doctorServiceItemId='+doctorServiceItemId;
	  	showWindow("编辑服务项目", url, 600, 300, doSearch);
  	}
	
	// 增加医生服务项目
	function addItem(){
		var url = root+'/admin/doctorctl/UpdateDoctorServiceItemPage.do?doctorId=${doctorId}';
	  	showWindow("编辑服务项目", url, 600, 300, doSearch);
	}
	
	// 删除医生服务项目
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
					url: root+"/admin/doctorctl/DeleteDoctorServiceItemPage.do",
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