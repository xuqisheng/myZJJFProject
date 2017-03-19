<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>经纪人列表</title>
   	<jsp:include page="../common/header.jsp"></jsp:include>
</head>
<body class="easyui-layout">
	<div region="center" style="padding:0px;" border="false" header="false">  
	    <table border="false" fit="true" id="tt" class="easyui-datagrid" url="${root}/admin/brokerctl/BrokerList.do" 
	    		iconCls="icon-save" sortName="createTime" sortOrder="desc" singleSelect="true"
	            rownumbers="true" idField="id" fitColumns="true" pagination="true" pageSize="20"
	            data-options="rownumbers:true,method:'post',toolbar:'#tb', queryParams:{isDelete:0}">
	        <thead>
	            <tr>
	            	<th data-options="field:'ck',checkbox:true"></th>
	                <th field="id" width="200" sortable="true" hidden="true">经纪人id</th>
	                <th field="name" width="100" sortable="true" formatter="formatOp">名称</th>
	                <th field="certificateType" width="100" sortable="true" formatter="formatCertificateType">证件类型</th>
	                <th field="certificateNo" width="150" sortable="true">证件号码</th>
	                <th field="phoneNum" width="100" sortable="true">电话</th>
	                <th field="address" width="300" sortable="true">住址</th>
	                <th field="creatorName" width="100" align="center">创建人</th>
	                <th field="createTime" width="120" align="center">创建时间</th>
	                <th field="isDelete" width="80" sortable="true" formatter="formatIsDelete">是否删除</th>
	                <th data-options="field:'_operate', width:80, align:'center', formatter:formatOper">操作</th>
	            </tr>
	        </thead>
	    </table>
	    <div id="tb" style="padding:2px 5px;">
	    	<div style="margin-top: 2px;">
	    		<a href="javascript: addBroker();" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>    
         		<a href="javascript: delBroker();" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
         		名称:<input id="name"class="easyui-textbox" style="width:140px;"/>
         		电话:<input id="phoneNum"class="easyui-textbox" style="width:140px;"/>
	    		是否删除: 
		       	<select id="isDelete" class="easyui-combobox" panelHeight="auto" style="width:100px" >
			        <option value="false" selected="selected">否</option>
			        <option value="true">是</option>
			        <option value="" >全部</option>
		       	</select>
		       	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" 
		       		plain="true" onclick="javascript: brokerFind()">查询</a>
	       </div>
	    </div>
    </div>
</body>
<script type="text/javascript">
	
	// 经纪人搜索
	function brokerFind(){
		$('#tt').edatagrid('reload',{
			name: $("#name").textbox('getValue'),
			phoneNum: $("#phoneNum").textbox('getValue'),
			isDelete: $("#isDelete").combobox('getValue')
		});
	}

	// 格式化证件类型
	function formatCertificateType(val, row){
		if(val == 1){
			return "身份证";
		}else{
			return "其它";
		}
	}
	
	// 格式化是否在用
	function formatIsDelete(val, row){
		if(val == 0){
			return "否";
		}else if(val == 1){
			return "是";
		}else{
			return "其它";
		}
	}
	
	// 查看经纪人对应的医生
	function formatOper(val, row, index){
		return '<a href="#" onclick="doctorList(\'' + row.id + '\')">医生列表</a>';
	}
	
	// 经纪人对应的医生列表
	function doctorList(brokerId){
		var url = root+'/admin/brokerctl/DoctorListPage.do?brokerId='+brokerId;
	  	showWindow("医生列表", url, 650, 400, brokerFind);
	}
	
	// 编辑经纪人链接
	function formatOp(val, row){
		return '<a href="#" mce_href="#" onclick="editBroker(\''+ row.id + '\')">'+val+'</a>';
	}
	
	// 新增经纪人
	function addBroker(){
		var url = root+'/admin/brokerctl/EditPage.do';
	   	showWindow("经纪人编辑", url, 400, 320, brokerFind);
	}
	
	// 编辑经纪人
	function editBroker(id){
		var url = root+'/admin/brokerctl/EditPage.do?id='+id;
	   	showWindow("经纪人编辑", url, 400, 320, brokerFind);
	}
	
	// 删除经纪人
	function delBroker(){
		var ids=getSelections();
		if(ids.length==0){
		  	alertMsg('请选择一行进行删除！');
		   	return;
		 }
		 $.messager.confirm('提示', "是否确认删除？", function(r){
			if(r){
				$.ajax({
					type: "POST",
					url: root+"/admin/brokerctl/Delete.do",
					data: "ids="+ids.join(','),
					success: function(msg){
						brokerFind();
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