<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>觅博医生</title>
   	<jsp:include page="../common/header.jsp"></jsp:include>
</head>
<body class="easyui-layout">
	<div region="center" style="padding:0px;" border="false" header="false">  
	    <table border="false" fit="true" id="tt" class="easyui-datagrid"  url="${root}/admin/doctorctl/DoctorList.do" 
	    		iconCls="icon-save" sortName="createTime" sortOrder="desc" singleSelect="true"
	            rownumbers="true" idField="id" fitColumns="true" pagination="true" pageSize="20"
	            data-options="rownumbers:true,method:'post',toolbar:'#tb',queryParams:{state: 0}">
	        <thead>
	            <tr>
	            	<th data-options="field:'ck',checkbox:true"></th>
	            	<th field="id" width="200" sortable="true" hidden="true">医生id</th>
	            	<th field="name" width="120" sortable="true">医生姓名</th>
	                <th field="hpName" width="200" sortable="true">所属医院</th>
	                <th field="ocName" width="150" sortable="true">所属科室</th>
	                <th field="telNo" width="150" sortable="true">医生电话</th>
	                <th field="serviceTimes" width=100" sortable="true">总服务次数</th>
	                <th data-options="field:'_operate',width:200,align:'center',formatter:formatOper">操作</th>
	            </tr>
	        </thead>
	    </table>
	    <div id="tb" style="padding:2px 5px;">
	    	<div style="margin-top: 2px;">
	    		医院名称:<input id="hpName"class="easyui-textbox" style="width:140px;">
	    		科室名称:<input id="ocName"class="easyui-textbox" style="width:140px;">
	    		医生姓名:<input id="name"class="easyui-textbox" style="width:140px;">
		       	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" 
		       		plain="true" onclick="javascript:authMgfind()">查询</a>
	       </div>
	    </div>
    </div>
</body>
<script type="text/javascript">
	function authMgfind(){
		$('#tt').edatagrid('load',{
			hpName:$("#hpName").textbox('getValue'),
			ocName:$("#ocName").textbox('getValue'),
			name:$("#name").textbox('getValue')
		});
	}

	//点击表单查询
	function doSearch(){
		 window.top.onChat=null;
		 //查询参数直接添加在queryParams中    
         var queryParams = $('#tt').datagrid('options').queryParams;  
         queryParams.hpName =$("#hpName").val();
         queryParams.ocName =$("#ocName").val();
         queryParams.name =$("#name").val();
         $('#tt').datagrid('options').queryParams=queryParams;        
         $("#tt").datagrid('reload'); 
	}
	
	// 客服与医生聊天，查看医生服务项目 链接
	function formatOper(val, row, index){
		return '<a href="#" onclick="chatDoctor(\'' + row.id + '\')">与医生对话</a>&nbsp;&nbsp;'
			+ '<a href="#" onclick="doctorServiceItem(\'' + row.id + '\')">编辑项目</a>&nbsp;&nbsp;'
			+ '<a href="#" onclick="doctorHealthPackage(\'' + row.id + '\')">健康包配置</a>';
	}
	
	// 客服与医生聊天
	function chatDoctor(doctorId){
		var url = root+'/CustomerServer/chatDoctor.do?doctorId='+doctorId;
	  	showWindow("客服医生聊天", url, 700, 450, doSearch);
	}
    
	// 医生服务项目
   	function doctorServiceItem(doctorId){
	  	var url = root+'/admin/doctorctl/DoctorServiceItemPage.do?doctorId='+doctorId;
	  	showWindow("服务项目", url, 600, 450, doSearch);
  	}
	
	// 医生的预约住院健康包配置
	function doctorHealthPackage(doctorId){
		var url = root+'/admin/doctorctl/DoctorHealthPackagePage.do?doctorId='+doctorId;
		showWindow("健康包配置", url, 600, 450, doSearch);
	}
</script>
</html>