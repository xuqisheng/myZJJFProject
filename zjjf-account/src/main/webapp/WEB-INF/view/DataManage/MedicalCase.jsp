<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>病例信息管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="../common/header.jsp"></jsp:include>
<script type="text/javascript">

function getMedicalCaseInfo(value, row){
	getUserInfo(row.id);
	getAttachInfo(row.id);
}

/**
 * 获取用户信息
 */
function getUserInfo(id){
	 $('#medicalCaseUserdg').datagrid({
		 url:'<c:url value="/admin/doctorctl/getUserInfo.do"/>',
		 queryParams:{ id:id}
	});
}
/**
 * 获取附件信息
 */
function getAttachInfo(id){
	 $('#medicalCaseAttachdg').datagrid({
		 url:'<c:url value="/admin/medicalcasectl/getAttachInfo.do"/>',
		 queryParams:{ dbId:id}
	});
}


	$(function(){
		var mibocrud = MiBoCRUD.creatNew({
			modelName:"medicalCaseMg",
			dialogTitle:'病例信息管理',
			listURL:'<c:url value="/admin/medicalcasectl/List.do"/>',
			creatURL:'<c:url value="/admin/medicalcasectl/Add.do"/>',
			updateURL:'<c:url value="/admin/medicalcasectl/Update.do"/>',
			deleteURL:'<c:url value="/admin/medicalcasectl/Delete.do"/>',
			findListFun:function(){
				$("#medicalCaseMgdg" ).datagrid('load',{
					disease:$("#medicalCaseField1").textbox('getValue'),
					description:$("#medicalCaseField2").textbox('getValue'),
					isDelete:$("#medicalCaseMgstate").combobox('getValue')
				})
			}
		}).init();
	});
</script>
</head>
<body>
<div class="easyui-layout"  fit="true"><!-- bord div -->
<div region="center" ><!-- ceter div -->
 <table id="medicalCaseMgdg" 
	idField="id"  sortName="createTime" sortOrder="desc" 
	toolbar="#medicalCaseMgtoolbar" pagination="true" fitColumns="true"  fit="true"
	data-options="rownumbers:true,singleSelect:true,method:'post',onClickRow:getMedicalCaseInfo">
    <thead>
        <tr>
        <th field="userId"  sortable="true" editor="text" hidden="true">用户ID</th>
        <th field="healthDocId" sortable="true" editor="text" hidden="true">健康档案ID</th>
        <th field="disease" sortable="true" editor="text" width="100">疾病名称</th>
        <th field="description" sortable="true" editor="text" width="100">病情描述</th>
        <th field="history" sortable="true" editor="text" width="100">发病时间</th>
        <th field="help" sortable="true" editor="text" width="100">希望得到什么帮助</th>
        <th field="createTime" sortable="true" editor="text" width="100">创建时间</th>
        <th field="isDelete" sortable="true"  editor="text" formatter="publicFormatDel" width="50">删除</th>
        </tr>
    </thead>
</table>
<div id="medicalCaseMgtoolbar">
	<!--
    <a id="medicalCaseMgtoolbar-new"   class="easyui-linkbutton" iconCls="icon-add" plain="true"  >新建企业</a>
    <a id="medicalCaseMgtoolbar-edit"  class="easyui-linkbutton" iconCls="icon-edit" plain="true"  >编辑企业</a>
    <a id="medicalCaseMgtoolbar-delete"  class="easyui-linkbutton" iconCls="icon-remove" plain="true" >删除企业</a>
    -->
       疾病名称:<input id="medicalCaseField1"class="easyui-textbox" style="width:140px;">
       病情描述:<input id="medicalCaseField2"class="easyui-textbox" style="width:240px;">
   	   是否删除: 
       <select id="medicalCaseMgstate" class="easyui-combobox" panelHeight="auto" style="width:100px" >
           <option value="0" selected="selected">可用</option>
           <option value="1">不可用</option>
           <option value="" >全部</option>
       </select>
    <a id="medicalCaseMgtoolbar-findlist"  class="easyui-linkbutton" iconCls="icon-search" plain="true"  >查询</a>
</div>
<!-- ------------------------------------------------------------------新建或者编辑区域----------------------------------------------------------------- 
 <div id="medicalCaseMgdlg" class="easyui-dialog" modal="true" style="width:600px;padding:10px 20px;min-height:400px;" closed="true" buttons="#medicalCaseMgdlgbt">
    <div class="ftitle">企业信息编辑</div>
    <form id="medicalCaseMgfm" method="post" novalidate>
    </form>
</div>
<div id="medicalCaseMgdlgbt">
	<a id="medicalCaseMgdlgbt-save" class="easyui-linkbutton c6" iconCls="icon-ok"  style="width:90px">保存</a>
	<a id="medicalCaseMgdlgbt-cancel" class="easyui-linkbutton" iconCls="icon-cancel"  style="width:90px">取消</a>
</div>
-->

</div><!-- ceter div -->
<!-- ---------------------------------------------------------------------------其他信息管理------------------------------------------------------------------------------------------- -->
<div region="south"  style="height:120px;background: #eee; overflow-y:hidden" >
	<table id="medicalCaseUserdg"  fitColumns="true"  fit="true"  class="easyui-datagrid"   data-options="rownumbers:true,singleSelect:true,method:'post'">
		<thead><tr>
	       <th field="loginName"  sortable="true" editor="text" width="100">登录名称</th>
	       <th field="nikeName" sortable="true" editor="text" width="100">昵称</th>
	       <th field="sex" sortable="true" editor="text" formatter="publicFormatSex" width="50">性别</th>
	       <th field="email" sortable="true" editor="text" width="100">Email</th>
	       <th field="callNum" sortable="true"  editor="text" width="100">电话</th>
	       <th field="phoneNum" sortable="true"  editor="text" width="100">手机</th>
	       <th field="departmentName" sortable="true" editor="text" width="100">部门名称</th>
	       <th field="state" sortable="true" editor="text"  formatter="pulbicFormatState" width="50">审核</th>
	       <th field="isDelete" sortable="true"  editor="text" formatter="publicFormatDel" width="50">删除</th>
	       <th field="createTime" sortable="true" editor="text" width="150">创建时间</th>
	       <th field="lastLoginTime" sortable="true" editor="text" width="150">最后登录时间</th>
	       <!-- <th field="backupOne" sortable="true" editor="text">备用字段一</th>
	       <th field="backupTwo" sortable="true" editor="text">备用字段二</th> -->
	   	</tr></thead>
	</table>
</div><!-- south div -->
<div region="east"  style="width:50%">
	<table id="medicalCaseAttachdg"  fitColumns="true"  pagination="true"   fit="true"  class="easyui-datagrid"   data-options="rownumbers:true,singleSelect:true,method:'post'">
		<thead><tr>
	       <th field="name"  sortable="true" editor="text" width="200">文件名称</th>
	       <th field="type" sortable="true" editor="text" width="100" hidden="true">文件类型</th>
	       <th field="url" sortable="true" editor="text" width="200">文件地址</th>
	       <th field="createTime" sortable="true" editor="text" width="100">创建时间</th>
	       <th field="isDelete" sortable="true" editor="text" formatter="publicFormatDel" width="50">删除</th>
	   	</tr></thead>
	</table>
</div><!-- east div -->

</div><!-- bord div -->
</body>
</html>
