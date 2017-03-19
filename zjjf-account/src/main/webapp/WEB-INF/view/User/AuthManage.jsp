<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table id="authMgdg" 
	idField="id"  sortName="createtime" sortOrder="desc" pageSize =50
	toolbar="#authMgtoolbar" pagination="true" fitColumns="true" fit="true"
	data-options="rownumbers:true,singleSelect:true,method:'post',onDblClickRow:authMgeditUser">
    <thead>
        <tr>
        <th field="authString"  sortable="true">权限字符串</th>
        <th field="authName" sortable="true">权限名称</th>
        <th field="action" sortable="true">权限URL</th>
        <th field="roleRemark" sortable="true">权限说明</th>
        <th field="upId" sortable="true" formatter="authMgUpIdFormatter">父权限</th>
        <th field="createTime" sortable="true" >创建时间</th>
        <th field="createUser" sortable="true" >创建人</th>
        <th field="icon" sortable="true">菜单图标</th>
        <th field="status" sortable="true" formatter="pulbicFormatState">审核</th>
        <th field="isDelete" sortable="true" formatter="publicFormatDel">是否删除</th>
        <th field="ordId" sortable="true">排序</th>
        </tr>
    </thead>
</table>
<div id="authMgtoolbar">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:authMgnewUser();">新建权限</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:authMgeditUser();">编辑权限</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:authMgdestroyUser();">删除权限</a>
            权限名称:<input id="authMgField1"class="easyui-textbox" style="width:140px;">
    Action名称:<input id="authMgField2"class="easyui-textbox" style="width:140px;">
    <!-- 
            创建时间（开始）：<input id="authMgbegindate" class="easyui-datetimebox" style="width:120px">
            创建时间（结束）：<input id="authMgenddate" class="easyui-datetimebox" style="width:120px">
     -->
   	   状态: 
       <select id="authMgstate" class="easyui-combobox" panelHeight="auto" style="width:100px" onchange="doSearch();">
           <option value="0">可用</option>
           <option value="1">不可用</option>
           <option value=""  selected="selected">全部</option>
       </select>
       	是否删除: 
	    <select id="authMgIsDelete" class="easyui-combobox" panelHeight="auto" style="width:100px" >
	        <option value="false"  selected="selected">否</option>
	        <option value="true">是</option>
	        <option value="" >全部</option>
	    </select>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="javascript:authMgfind()">查询</a>
</div>

 <div id="authMgdlg" class="easyui-dialog"  modal="true"  style="min-width:400px;padding:10px 20px;min-height:400px;" closed="true" buttons="#authMgdlgbt">
    <div class="ftitle">权限信息编辑</div>
    <form id="authMgfm" method="post" novalidate>
    <div class="fitem"><label>权限字符串:</label><input name="authString" class="easyui-textbox"  required="true"  style="width:400px;"></div>
    <div class="fitem"><label>权限名称:</label><input name="authName" class="easyui-textbox" ></div>
    <div class="fitem"><label>权限URL:</label><input name="action" class="easyui-textbox"  style="width:400px;"></div>
    <div class="fitem"><label>权限描述:</label><input name="roleRemark" class="easyui-textbox" ></div>
    <div class="fitem"><label>父权限:</label><input id="authMgUpId" name="upId"></div>
    <div class="fitem"><label>图标:</label><input name="icon"  class="easyui-textbox" ></div>
    <div class="fitem"><label>审核:</label><input name="status"  class="easyui-combobox"  required="true"  data-options="data:miboState,valueField:'value',textField:'lable',panelHeight:'auto',editable:false" ></div>
    <div class="fitem"><label>排序:</label><input name="ordId" class="easyui-numberbox" ></div>
    <input name="position" value="4" type="hidden" />
    </form>
</div>
<div id="authMgdlgbt">
	<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="javascript:authMgsaveUser();" style="width:90px">保存</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#authMgdlg').dialog('close');" style="width:90px">取消</a>
</div>

<script type="text/javascript">

var authMgUpIdData = new Array();

function authMgfind(){
	 $('#authMgdg').edatagrid('load',{
		authName:$("#authMgField1").textbox('getValue'),
		action:$("#authMgField2").textbox('getValue'),
		state:$("#authMgstate").combobox('getValue'),
		isDelete:$("#authMgIsDelete").combobox('getValue')
	 });
}

//reload auths 
function reloadAuth(){
	//获取菜单数据字典
	$.getJSON('<c:url value="/account/authctl/getAllMenus.do"/>',function(result){
		authMgUpIdData = result;
		//初始化上级菜单
		$("#authMgUpId").combobox({
			data:authMgUpIdData,
			editable:false,
			valueField:"id",
			textField:"authName",
			panelHeight:"auto"
		});
	});
}


function authMgUpIdFormatter(value, row){
    for(var i=0; i<authMgUpIdData.length; i++){
       if (authMgUpIdData[i].id == value) return authMgUpIdData[i].authname;  
    }  
    return value;	
}

function authMgnewUser(){
	$('#authMgdlg').dialog('open').dialog('setTitle','权限信息编辑');
	$('#authMgfm').form('clear');
	authMgurl = '<c:url value="/account/authctl/Add.do"/>';
}

function authMgeditUser(){
	var row = $('#authMgdg').datagrid('getSelected');
	if (row){
		$('#authMgdlg').dialog('open').dialog('setTitle','编辑用户');
		$('#authMgfm').form('load',row);
		authMgurl = '<c:url value="/account/authctl/Update.do"/>?id='+row.id;
	}
}
function authMgsaveUser(){
	$('#authMgfm').form('submit',{
		url: authMgurl,
		onSubmit: function(){
			return $(this).form('validate');
		},
		success: function(result){
			var result = eval('('+result+')');
			if (!result.success){
				$.messager.show({
					title: 'Error',
					msg: result.message
				});
			} else {
				$('#authMgdlg').dialog('close'); // close the dialog
				$('#authMgdg').datagrid('reload'); // reload the user data
				reloadAuth();// reload the auth
			}
		}
	});
}


function authMggetSelections(){
    var ss = [];
    var rows = $('#authMgdg').datagrid('getSelections');
    for(var i=0; i<rows.length; i++){
        var row = rows[i];
        ss.push(row.id);
    }
    return ss;
}

function authMgdestroyUser(){
	var ids=authMggetSelections();
	var row = $('#authMgdg').datagrid('getSelected');
	if (row){
		$.messager.confirm('Confirm','确定删除?',function(r){
			if (r){
				$.post('<c:url value="/account/authctl/Delete.do"/>',{ids:ids.join(',')},function(result){
					if (result.success){
						$('#authMgdg').datagrid('reload'); // reload the user data
						reloadAuth();// reload the auth data
					} else {
						$.messager.show({ // show error message
							title: 'Error',
							msg: result.message
						});
					}
				},'json');
			}
		});
	}
}
$(function(){
	$('#authMgdg').datagrid({
		url: '<c:url value="/account/authctl/List.do"/>',
		queryParams:{isDelete:0}
	});
	
	//获取菜单数据字典
	$.getJSON('<c:url value="/account/authctl/getAllMenus.do"/>',function(result){
		authMgUpIdData = result;
		//初始化上级菜单
		$("#authMgUpId").combobox({
			data:authMgUpIdData,
			editable:false,
			valueField:"id",
			textField:"authName",
			panelHeight:"auto"
		});
	});

});
</script>
