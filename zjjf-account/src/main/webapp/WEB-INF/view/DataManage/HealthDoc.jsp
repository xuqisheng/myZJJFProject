<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>健康档案管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="../common/header.jsp"></jsp:include>
<script type="text/javascript">

function getHealthDocInfo(value, row){
	getUserInfo(row.id);
}

/**
 * 获取用户信息
 */
function getUserInfo(id){
	 $('#healthDocUserdg').datagrid({
		 url:'<c:url value="/admin/doctorctl/getUserInfo.do"/>',
		 queryParams:{ id:id}
	});
}


	$(function(){
		
		//与本人关系
		$.getJSON( '<c:url value="/admin/fetchconfigctl/getRELATIONSHIP.do"/>',function(result){
			miboRELATIONSHIP = result;
		});
		
		var mibocrud = MiBoCRUD.creatNew({
			modelName:"healthDocMg",
			dialogTitle:'健康档案管理',
			listURL:'<c:url value="/admin/healthdocctl/List.do"/>',
			creatURL:'<c:url value="/admin/healthdocctl/Add.do"/>',
			updateURL:'<c:url value="/admin/healthdocctl/Update.do"/>',
			deleteURL:'<c:url value="/admin/healthdocctl/Delete.do"/>',
			findListFun:function(){
				$("#healthDocMgdg" ).datagrid('load',{
					name:$("#healthDocMglikeName").textbox('getValue'),
					isDelete:$("#healthDocMgstate").combobox('getValue')
				})
			}
		}).init();
	});
</script>
</head>
<body>
<div class="easyui-layout"  fit="true"><!-- bord div -->
<div region="center" ><!-- ceter div -->
 <table id="healthDocMgdg" 
	idField="id"  sortName="id" sortOrder="asc" 
	toolbar="#healthDocMgtoolbar" pagination="true" fitColumns="true"  fit="true"
	data-options="rownumbers:true,singleSelect:true,method:'post',onClickRow:getHealthDocInfo">
    <thead>
    	<tr>
	        <th field="userId"  sortable="true" editor="text" hidden="true">用户信息</th>
	        <th field="name" sortable="true" editor="text" width="100">档案人名称</th>
	        <th field="sex" sortable="true" editor="text" width="80" formatter="publicFormatSex">档案人性别</th>
	        <th field="birthday" sortable="true" editor="text" width="150">档案人生日</th>
	        <th field="idCard" sortable="true" editor="text" width="150">档案人身份证</th>
	        <th field="allergies" sortable="true" editor="text" width="200">患者的过敏历史记录</th>
	        <th field="chronicDiseases" sortable="true" editor="text" width="200">患者的过敏历史记录</th>
	        <th field="pastHistory" sortable="true" editor="text" width="200">既往史</th>
	        <th field="familyHistory" sortable="true" editor="text" width="200">家族史</th>
	        <th field="menstrualHistory" sortable="true" editor="text" width="100">女士月经史</th>
	        <th field="relationship" sortable="true" editor="text" formatter="publicFormatRELATIONSHIP" width="80">与本人关系</th>
	        <th field="createTime" sortable="true" editor="text" width="150">创建时间</th>
	        <th field="isDelete" sortable="true" editor="text"  formatter="publicFormatDel" width="80"> 是否删除</th>
        </tr>
	</thead>
</table>
<div id="healthDocMgtoolbar">
	<!-- 
    <a id="healthDocMgtoolbar-new"   class="easyui-linkbutton" iconCls="icon-add" plain="true"  >新建企业</a>
    <a id="healthDocMgtoolbar-edit"  class="easyui-linkbutton" iconCls="icon-edit" plain="true"  >编辑企业</a>
    <a id="healthDocMgtoolbar-delete"  class="easyui-linkbutton" iconCls="icon-remove" plain="true" >删除企业</a>
     -->
       档案人名称:<input id="healthDocMglikeName"class="easyui-textbox" style="width:140px;">
   	   是否删除: 
       <select id="healthDocMgstate" class="easyui-combobox" panelHeight="auto" style="width:100px" >
           <option value="0" selected="selected">可用</option>
           <option value="1">不可用</option>
           <option value="" >全部</option>
       </select>
    <a id="healthDocMgtoolbar-findlist"  class="easyui-linkbutton" iconCls="icon-search" plain="true"  >查询</a>
</div>
<!-- ------------------------------------------------------------------新建或者编辑区域----------------------------------------------------------------- 
 <div id="healthDocMgdlg" class="easyui-dialog" modal="true" style="width:600px;padding:10px 20px;min-height:400px;" closed="true" buttons="#healthDocMgdlgbt">
    <div class="ftitle">企业信息编辑</div>
    <form id="healthDocMgfm" method="post" novalidate>

    </form>
</div>
<div id="healthDocMgdlgbt">
	<a id="healthDocMgdlgbt-save" class="easyui-linkbutton c6" iconCls="icon-ok"  style="width:90px">保存</a>
	<a id="healthDocMgdlgbt-cancel" class="easyui-linkbutton" iconCls="icon-cancel"  style="width:90px">取消</a>
</div>
-->

</div><!-- ceter div -->
<!-- ------------------------------------------------------------------其他信息管理----------------------------------------------------------------- -->
<div region="south"  style="height:120px;background: #eee; overflow-y:hidden" >
	<table id="healthDocUserdg"  fitColumns="true"  fit="true"  class="easyui-datagrid"   data-options="rownumbers:true,singleSelect:true,method:'post'">
		<thead><tr>
	       <th field="loginName"  sortable="true" editor="text" width="100">登录名称</th>
	       <th field="nikeName" sortable="true" editor="text" width="100">昵称</th>
	       <th field="sex" sortable="true" editor="text"  formatter="publicFormatSex" width="50">性别</th>
	       <th field="email" sortable="true" editor="text" width="100">Email</th>
	       <th field="callNum" sortable="true"  editor="text" width="100">电话</th>
	       <th field="phoneNum" sortable="true"  editor="text" width="100">手机</th>
	       <th field="departmentName" sortable="true" editor="text" width="100">部门名称</th>
	       <th field="state" sortable="true" editor="text"  formatter="pulbicFormatState" width="80">审核</th>
	       <th field="isDelete" sortable="true"  editor="text" formatter="publicFormatDel" width="50">删除</th>
	       <th field="createTime" sortable="true" editor="text" width="120">创建时间</th>
	       <th field="lastLoginTime" sortable="true" editor="text" width="120">最后登录时间</th>
	       <!-- <th field="backupOne" sortable="true" editor="text">备用字段一</th>
	       <th field="backupTwo" sortable="true" editor="text">备用字段二</th> -->
	   	</tr></thead>
	</table>
</div><!-- south div -->
</div><!-- bord div -->
</body>
</html>
