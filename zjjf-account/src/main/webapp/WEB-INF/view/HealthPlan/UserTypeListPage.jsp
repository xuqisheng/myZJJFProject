<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户健康计划类型</title>
   	<jsp:include page="../common/header.jsp"></jsp:include>
</head>
<body class="easyui-layout">
	<div region="center" style="padding:0px;" border="false" header="false">  
	    <table border="false" fit="true" id="tt" class="easyui-datagrid" url="${root}/admin/healthplanctl/UserInTypeList.do" 
	    		iconCls="icon-save" sortName="createTime" sortOrder="desc" singleSelect="true"
	            rownumbers="true" idField="id" fitColumns="true" pagination="true" pageSize="20"
	            data-options="rownumbers:true,method:'post',toolbar:'#tb'">
	        <thead>
	            <tr>
	            	<th data-options="field:'ck',checkbox:true"></th>
	            	<th field="id" hidden="true">用户id</th>
					<th field="nikeName" width="100">用户名</th>
					<th field="phoneNum" width="100">电话</th>
					<th field="sex" width="60" sortable="true" align="center" formatter="formatSex">性别</th>
					<th field="state" width="60" sortable="true" align="center" formatter="formatState">状态</th>
					<th data-options="field:'_operate', width:80, align:'center', formatter:formatOper">操作</th>
	            </tr>
	        </thead>
	    </table>
	    <div id="tb" style="padding:2px 5px;">
	    	<div style="margin-top: 2px;">
	    		用户名:<input id="creatorName" class="easyui-textbox" style="width:140px;">
	    		<!-- 
	    		类型名:<input id="name" class="easyui-textbox" style="width:140px;">
	    		是否完成:
	    		<select id="finishStatus" class="easyui-combobox" panelHeight="auto" style="width:100px">
		            <option value="">全部</option>
		            <option value="0">否</option>
		            <option value="1">是</option>
		        </select>
	    		是否在用:
    			<select id="isDelete" class="easyui-combobox" panelHeight="auto" style="width:100px">
		            <option value="">全部</option>
		            <option value="0">是</option>
		            <option value="1">否</option>
		      	</select>
			    -->
		       	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" 
		       		plain="true" onclick="javascript: configFind()">查询</a>
	       </div>
	    </div>
    </div>
</body>
<script type="text/javascript">

	//格式化 用户性别
	function formatSex(val, row){
		if(val == 0){
			return "男";
		}else if(val == 1){
			return "女";
		}else{
			return "其它";
		}
	}
	
	//格式化 用户状态
	function formatState(val, row){
		if(val == 0){
			return "可用";
		}else if(val == 1){
			return "禁用";
		}else{
			return "其它";
		}
	}

	// 用户健康计划类型查询
	function configFind(){
		$('#tt').edatagrid('load',{
			/** name: $("#name").textbox('getValue'),
			finishStatus: $("#finishStatus").combobox('getValue'),
			isDelete: $("#isDelete").combobox('getValue'), */
			creatorName:$("#creatorName").textbox('getValue')
		});
	}
	
	// 查看子项
	function formatOper(val, row, index){
		return '<a href="#" onclick="userItemList(\'' + row.id + '\')">查看项目</a>';
	}
	
	// 子项列表
	function userItemList(userId){
		var url = root+'/admin/healthplanctl/UserItemListPage.do?userId='+userId;
	  	showWindow("项目列表", url, 600, 450, configFind);
	}
</script>
</html>