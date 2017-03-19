<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>订单信息管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="../common/header.jsp"></jsp:include>
<script type="text/javascript">

/**
 *  打开iframe 
 */
function openIframeWindow(result,url){
	$("#report-deal-window").dialog("open").dialog('setTitle',"举报处理—定位举报对象");
	$("#reportdlgiframe").attr("src",url);
	var  infoString = "<span style='margin:2px 5px;color:yellow'>请拷贝右侧信息后查询举报对象</span>" ;
		   infoString += "<span style='margin:2px 5px;color:yellow'>举报对象ID:</span>"+result.id  ;
	       infoString += "<span style='margin:2px 5px;color:yellow'>举报对创建时间:</span>"+result.createTime;
	       infoString += "<span style='margin:2px 5px;color:yellow'>举报对象名称:</span>"+result.name;
	$("#report-dlg-toolbar").html(infoString);
}
/**
 * 添加面板
 */
function addTab(subtitle,url,icon){
	if(!window.top.$('#tabs').tabs('exists',subtitle)){
		window.top.$('#tabs').tabs('add',{
			title:subtitle,
			content:createFrame(url),
			closable:true,
			icon:icon
		});
	}else{
		window.top.$('#tabs').tabs('select',subtitle);
		window.top.$('#mm-tabupdate').click();
	}
	tabClose();
}
function createFrame(url)
{
	var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	return s;
}
/**
 * 格式化举报对象字段
 */
function reportObjectOfColoumFormatter(value, row) {
	var repobj = '<a href="##" onclick="getReportObject(\'' + value +'\',\'' + row.type+ '\',\'' + row.id+'\')">查看</a>';
	var space = "&nbsp;&nbsp;";
	return space + repobj + space ;
}
function getReportObject(id,type,rowid){
	$("#reportMgdg").datagrid("selectRecord",rowid);
	if(id){
		if(type == 0){
			$.getJSON('<c:url value="/admin/reportctl/getReportAirticle.do"/>',{id:id},function(result){
				openIframeWindow(result,'<c:url value="/admin/article/ArticleListPage.do"/>');
			});
		}else if( type == 1){
			$.getJSON('<c:url value="/admin/reportctl/getReportPhoto.do"/>',{id:id},function(result){
				openIframeWindow(result,'<c:url value="/admin/comment/AlbumsPhotoCommentListPage.do"/>');
			});
		}else if(type == 2){
			$.getJSON('<c:url value="/admin/reportctl/getReportAirticle.do"/>',{id:id},function(result){
				openIframeWindow(result,'<c:url value="/admin/article/ArticleListPage.do"/>');
			});
		}else {
			$.messager.alert("错误","找不到对应模块");
		}
	}
}

/**
 * 格式化举报人字段
 */
function reporterOfColoumFormatter(value, row) {
	var reporter = '<a href="#" onclick="getReporter(\'' + value + '\')">查看</a>';
	var space = "&nbsp;&nbsp;";
	return reporter;
}
function getReporter(id){
	if(id){
		 $('#reportMgReporterdg').datagrid({
			 url:'<c:url value="/admin/doctorctl/getUserInfo.do"/>',
			 queryParams:{ id:id}
		});
	}
}

/**
 * 格式化处理人字段
 */
function dealerOfColoumFormatter(value, row) {
	var reporter = '<a href="#" onclick="getDealer(\'' + value + '\')">查看</a>';
	var space = "&nbsp;&nbsp;";
	return reporter;
}
function getDealer(id){
	if(id){
		 $('#reportMgDealerdg').datagrid({
			 url:'<c:url value="/admin/doctorctl/getUserInfo.do"/>',
			 queryParams:{ id:id}
		});
	}
}

/**
 * 获取用户信息
 */
function getReportInfo(value, row){
	 $('#reportMgReporterdg').datagrid({
		 url:'<c:url value="/admin/doctorctl/getUserInfo.do"/>',
		 queryParams:{ id:row.reportUserId}
	});
	 $('#reportMgDealerdg').datagrid({
		 url:'<c:url value="/admin/doctorctl/getUserInfo.do"/>',
		 queryParams:{ id:row.dealerId}
	});
}

$(function(){
	$.ajaxSetup({cache:false});
	var mibocrud = MiBoCRUD.creatNew({
		modelName:"reportMg",
		dialogTitle:'订单信息管理',
		listURL:'<c:url value="/admin/reportctl/List.do"/>',
		creatURL:'<c:url value="/admin/reportctl/Add.do"/>',
		updateURL:'<c:url value="/admin/reportctl/Update.do"/>',
		deleteURL:'<c:url value="/admin/reportctl/Delete.do"/>',
		findListFun:function(){
			$("#reportMgdg" ).datagrid('load',{
				type:$("#reportMgType").combobox('getValue'),
				dealType:$("#reportMgDealType").combobox('getValue'),
				state:$("#reportMgState").combobox('getValue'),
				isDelete:$("#reportMgIsDelete").combobox('getValue')
			})
		}
	}).init();
});
</script>
</head>
<body>
<div class="easyui-layout"  fit="true"><!-- layout -->
<div  region="center" ><!-- layout center-->
 <table id="reportMgdg" 
	idField="id"  sortName="reportTime" sortOrder="DESC" 
	toolbar="#reportMgtoolbar" pagination="true" fitColumns="true"  fit="true"
	data-options="rownumbers:true,singleSelect:true,method:'post'">
    <thead>
        <tr>
        <th field="type" sortable="true" editor="text" formatter="publicFormatReportObjectType" width="100">举报类型</th>
        <th field="content" sortable="true" editor="text" width="100">举报内容</th>
        <th field="reportTime" sortable="true" editor="text" width="100">举报时间</th>
        <th field="dealType" sortable="true" editor="text" formatter="publicFormatReportDealType" width="100">处理方式</th>
        <th field="addword" sortable="true" editor="text" width="100">处理说明</th>
      	<th field="state" sortable="true" editor="text"  formatter="publicFormatReportDealState" width="100">处理状态</th>
      	<th field="dealTime" sortable="true" editor="text"  formatter="publicFormatReportDealState" width="100">处理时间</th>
        <th field="reportUserId" sortable="true" editor="text" formatter="reporterOfColoumFormatter" width="100">举报人</th>
        <th field="dealerId" sortable="true" editor="text" formatter="dealerOfColoumFormatter" width="100">处理人</th>
        <th field="reportId" sortable="true" editor="text" formatter="reportObjectOfColoumFormatter" width="100">举报对象</th>
        <th field="isDelete" sortable="true"  editor="text"  formatter="publicFormatDel" width="100">是否删除</th>
        </tr>
    </thead>
</table>
<div id="reportMgtoolbar">
    <!-- <a id="reportMgtoolbar-new"   class="easyui-linkbutton" iconCls="icon-add" plain="true"  >新建</a> -->
    <a id="reportMgtoolbar-edit"  class="easyui-linkbutton" iconCls="icon-edit" plain="true"  >处理举报</a>
    <a id="reportMgtoolbar-delete" class="easyui-linkbutton" iconCls="icon-remove" plain="true"  >删除举报</a>
   	举报对象: 
    <select id="reportMgType" class="easyui-combobox" panelHeight="auto" style="width:100px" >
        <option value="0">文章</option>
        <option value="1">图片</option>
        <option value="2">链接</option>
        <option value=""  selected="selected">全部</option>
    </select>
   	 处理方式: 
    <select id="reportMgDealType" class="easyui-combobox" panelHeight="auto" style="width:100px" >
        <option value="0" >不做处理</option>
        <option value="1">删除</option>
        <!-- <option value="2">通知用户</option>
        <option value="3">通知举报人</option> -->
        <option value=""  selected="selected">全部</option>
    </select>
   	 处理状态: 
    <select id="reportMgState" class="easyui-combobox" panelHeight="auto" style="width:100px" >
        <option value="0" >未处理</option>
        <option value="1">已处理</option>
        <option value=""  selected="selected">全部</option>
    </select>
   	 是否删除: 
    <select id="reportMgIsDelete" class="easyui-combobox" panelHeight="auto" style="width:100px" >
        <option value="false"  selected="selected">否</option>
        <option value="true">是</option>
        <option value="" >全部</option>
    </select>
    <a id="reportMgtoolbar-findlist"  class="easyui-linkbutton" iconCls="icon-search" plain="true"  >查询</a>
</div>
<!-- -------------------------------------------举报编辑区域------------------------------------------ -->
 <div id="reportMgdlg" class="easyui-dialog"  modal="true"  style="width:400px;padding:10px 20px;min-height:400px;" closed="true" buttons="#reportMgdlgbt">
    <div class="ftitle">举报审核</div>
    <form id="reportMgfm" method="post" novalidate>
    <div class="fitem">
    	<label>举报类型:</label>
    	<input name="type"  class="easyui-combobox"  data-options="data:miboReportObjectType,valueField:'value',textField:'lable',panelHeight:'auto'" readonly="true">
    </div>
    <div class="fitem">
    	<label>举报时间:</label>
    	<input name="reportTime" class="easyui-datetimebox" readonly="true">
    </div>
    <div class="fitem">
    	<label>举报内容:</label>
		<textarea  name="content" class="textbox"  style="height:100px;width:240px" readonly="true"></textarea>
	</div>
    <div class="fitem">
    	<label>处理方式:</label>
    	<input name="dealType"  class="easyui-combobox"  data-options="data:miboReportDealType,valueField:'value',textField:'lable',panelHeight:'auto'"   required>
    </div>
    <div class="fitem">
    	<label>处理说明:</label>
		<textarea  name="addword" class="textbox"  style="height:100px;width:240px" ></textarea>
	</div>
    <input name="reportId"  type="hidden">
    <input name="dealerId"  type="hidden">
    <input name="state"  type="hidden">
    <input name="isDelete"  type="hidden">
    </form>
</div>
<div id="reportMgdlgbt">
	<a id="reportMgdlgbt-save"  class="easyui-linkbutton c6" iconCls="icon-ok"   style="width:90px">保存</a>
	<a id="reportMgdlgbt-cancel"   class="easyui-linkbutton" iconCls="icon-cancel"  style="width:90px">取消</a>
</div>
</div><!-- layout center-->

<div region="south"  style="height: 180px;"><!-- layout south-->
	<div class="easyui-layout"  fit="true"><!-- layout south  layout -->
			<div region="east" style="width: 50%" title="处理人信息"><!-- layout south  layout east -->
				<table id="reportMgDealerdg"  fitColumns="true"  fit="true"  class="easyui-datagrid"   data-options="rownumbers:true,singleSelect:true,method:'post'">
	    			<thead><tr>
				        <th field="loginName" sortable="true" editor="text" width="100">登录名称</th>
				        <th field="nikeName" sortable="true" editor="text" width="100">昵称</th>
				        <th field="sex" sortable="true" editor="text" formatter="publicFormatSex" width="100">性别</th>
				        <th field="email" sortable="true" editor="text" width="100">Email</th>
				        <th field="callNum" sortable="true" editor="text" width="100">电话</th>
				        <th field="phoneNum" sortable="true" editor="text" width="100">手机</th>
				        <th field="departmentName" sortable="true" editor="text" width="100">部门名称</th>
				        <th field="state" sortable="true" editor="text"  formatter="pulbicFormatState" width="100">审核</th>
				        <th field="isDelete" sortable="true" editor="text" formatter="publicFormatDel" width="100">删除</th>
				        <th field="createTime" sortable="true" editor="text" width="100">创建时间</th>
				        <th field="lastLoginTime" sortable="true" editor="text" width="100">最后登录时间</th>
	       			</tr></thead>
	       		</table>
			</div><!-- layout south  layout east -->
			<div region="center" style="width: 50%" title="举报人信息"><!-- layout south  layout east -->
				<table  id="reportMgReporterdg"  fitColumns="true"  fit="true"  class="easyui-datagrid"   data-options="rownumbers:true,singleSelect:true,method:'post'">
	    			<thead><tr>
				        <th field="loginName" sortable="true" editor="text" width="100">登录名称</th>
				        <th field="nikeName" sortable="true" editor="text" width="100">昵称</th>
				        <th field="sex" sortable="true" editor="text" formatter="publicFormatSex" width="100">性别</th>
				        <th field="email" sortable="true" editor="text" width="100">Email</th>
				        <th field="callNum" sortable="true"  editor="text" width="100">电话</th>
				        <th field="phoneNum" sortable="true"  editor="text" width="100">手机</th>
				        <th field="departmentName" sortable="true" editor="text" width="100">部门名称</th>
				        <th field="state" sortable="true" editor="text"  formatter="pulbicFormatState" width="100">审核</th>
				        <th field="isDelete" sortable="true"  editor="text" formatter="publicFormatDel" width="100">删除</th>
				        <th field="createTime" sortable="true" editor="text" width="100">创建时间</th>
				        <th field="lastLoginTime" sortable="true" editor="text" width="100">最后登录时间</th>
	       			</tr></thead>
	       		</table>
			</div><!-- layout south  layout east -->
	</div><!-- layout south  layout-->
</div><!-- layout south-->

</div><!-- layout -->

<!-- deal window  begin-->
<div id="report-deal-window" class="easyui-dialog"  toolbar="#report-dlg-toolbar"   modal="true"  closed="true"  style="width:90%;padding:0;height:90%;">
	<iframe id="reportdlgiframe"  name="reportdlgiframe"  src="./admin/article/ArticleListPage.do"  style="width:100%;height:100%;border:none;"></iframe>
</div>
<div id="report-dlg-toolbar" style="padding:2px 0;background:#45A245;height:16px;padding-left:10px">
</div>
<!-- deal window end -->
</body>
</html>
