<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- layout div -->
<div class="easyui-layout"  fit="true">
<div  region="west"  style="width:60%;padding:5px;"  data-options="split:true">
<table id="roleMgdg" 
	idField="id"  sortName="createtime" sortOrder="desc" pageSize="50"
	toolbar="#roleMgtoolbar" pagination="true" fitColumns="true"  fit="true"
	data-options="rownumbers:true,singleSelect:true,method:'post',onClickRow:getAuthsByRoleId,onDblClickRow:roleMgeditUser">
    <thead>
   		<th data-options="field:'ck',checkbox:true"></th>
   		<th field="id"  sortable="true">角色ID</th>
        <th field="roleName"  sortable="true">角色名称</th>
        <th field="roleRemark" sortable="true">角色描述</th>
        <th field="createTime" sortable="true">创建时间</th>
        <th field="createUser" sortable="true">创建人</th>
        <th field="status" sortable="true" formatter="pulbicFormatState">审核</th>
        <th field="isDelete" sortable="true"formatter="publicFormatDel">是否删除</th>
        <th field="ordId" sortable="true">排序</th>
        </tr>
    </thead>
</table>
<div id="roleMgtoolbar">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:roleMgnewUser();">新建角色</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:roleMgeditUser();">编辑角色</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:roleMgdestroyUser();">删除角色</a>
    角色名称:<input id="roleMglikeName"class="easyui-textbox" style="width:140px;">
	审核状态:
	<select id="roleMgstate" class="easyui-combobox" panelHeight="auto" style="width:100px" onchange="doSearch();">
         <option value="0">可用</option>
         <option value="1">不可用</option>
         <option value=""  selected="selected">全部</option>
	</select>
	是否删除: 
    <select id="roleMgIsDelete" class="easyui-combobox" panelHeight="auto" style="width:100px" >
        <option value="false" selected="selected" >否</option>
        <option value="true">是</option>
        <option value="" >全部</option>
    </select>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="javascript:roleMgfind()">查询</a>
</div>

 <div id="roleMgdlg" class="easyui-dialog"  modal="true"  style="width:400px;padding:10px 20px;min-height:200px;" closed="true" buttons="#roleMgdlgbt">
    <div class="ftitle">角色信息编辑</div>
    <form id="roleMgfm" method="post" novalidate>
    <div class="fitem"><label>角色名称:</label><input name="roleName" class="easyui-textbox"  required="true" ></div>
    <div class="fitem"><label>角色描述:</label><input name="roleRemark" class="easyui-textbox" ></div>
    <div class="fitem"><label>排序:</label><input name="ordId" class="easyui-numberbox" ></div>
    <div class="fitem"><label>审核:</label><input name="status"  class="easyui-combobox"  required="true"  data-options="data:miboState,valueField:'value',textField:'lable',panelHeight:'auto',editable:false" ></div>
    </form>
</div>
<div id="roleMgdlgbt">
	<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="javascript:roleMgsaveUser();" style="width:90px">保存</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#roleMgdlg').dialog('close');" style="width:90px">取消</a>
</div>
</div>
<div  region="center"  split="true"  title="角色权限设置" >
		<div  class="easyui-layout"  fit="true" >
			<div region="center">
				<table id="roleMgAuthdg" pageSize="50"
					idField="id"  sortName="createtime" sortOrder="asc"  
					pagination="true" fitColumns="true"
					data-options="rownumbers:true,method:'post'">
				    <thead>
				        <tr>
				        <th data-options="field:'ck',checkbox:true"></th>
				        <th field="id"  sortable="true">权限ID</th>
				        <th field="authString"  sortable="true">权限字符串</th>
				        <th field="authName" sortable="true" >权限名称</th>
				        <th field="action" sortable="true" >权限URL</th>
				        <th field="roleRemark" sortable="true">权限说明</th>
				        <th field="ordId" sortable="true">排序</th>
				        <th field="status" sortable="true"  formatter="pulbicFormatState">审核</th>
				        </tr>
				    </thead>
				</table>
			</div>
			<div region="south" style="height:40px; padding:5px ;background:#E6F0FF">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="javascript:reloadAuthList();" style="width:90px">刷新</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-redo" onclick="javascript:inSelectAll();" style="width:90px">全部选择</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:deSelectAll();" style="width:90px">全部取消</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="javascript:saveAuths();" style="width:90px">保存</a>
			</div>
		</div>
</div>
</div>


<script type="text/javascript">

$('#roleMgdg').datagrid({
	url: '<c:url value="/account/rolectl/List.do"/>',
	queryParams:{isDelete:0}
});

$('#roleMgAuthdg').datagrid({
	url: '<c:url value="/account/authctl/List.do"/>'
});
//取得当前角色行对应的权限
var  currentRole;
var  currentRoleAuths;
function getAuthsByRoleId(rowIndex,rowData){
	$('#roleMgAuthdg').datagrid("clearSelections");
	currentRole = rowData;
	if(rowData){
		$.post( '<c:url value="/account/rolectl/getAuths.do"/>',{id:rowData.id},function(result){
			currentRoleAuths=result;//留给刷新使用
			if(result.length>0){
				 $.each(result, function(){     
					 $('#roleMgAuthdg').datagrid("selectRecord",this+"");
				 });   
			}
		},'json');	
	}
}
//刷新
function reloadAuthList(){
	$('#roleMgAuthdg').datagrid("reload");
	if(currentRoleAuths.length>0){
		 $.each(currentRoleAuths, function(){     
			 $('#roleMgAuthdg').datagrid("selectRecord",this+"");
		 });   
	}
}
//清除所有行clearSelections
function deSelectAll(rowIndex,rowData){
	$('#roleMgAuthdg').datagrid("clearSelections");
}
//选择所有行selectAll
function inSelectAll(rowIndex,rowData){
	$('#roleMgAuthdg').datagrid("selectAll");
}
//保存所选
function saveAuths(rowIndex,rowData){
	if(currentRole){//用户未选中任何角色
		$.post( '<c:url value="/account/rolectl/saveAuths.do"/>',{
				roleId:currentRole.id,
				authIds:getSelections('roleMgAuthdg').join(',')
			},function(result){
				$.messager.alert("信息",""+result.message);
		},'json');	
	}else{
		$.messager.alert("提示","请选择角色后提交");
	}
}

function roleMgfind(){
	 $('#roleMgdg').edatagrid('load',{
		roleName:$("#roleMglikeName").textbox('getValue'),
		state:$("#roleMgstate").combobox('getValue'),
		isDelete:$("#roleMgIsDelete").combobox('getValue')
	 });
}

var roleMgurl ="";
function roleMgnewUser(){
	$('#roleMgdlg').dialog('open').dialog('setTitle','角色信息编辑');
	$('#roleMgfm').form('clear');
	roleMgurl = '<c:url value="/account/rolectl/Add.do"/>';
}

function roleMgeditUser(){
	var row = $('#roleMgdg').datagrid('getSelected');
	if (row){
		$('#roleMgdlg').dialog('open').dialog('setTitle','编辑用户');
		$('#roleMgfm').form('load',row);
		roleMgurl = '<c:url value="/account/rolectl/Update.do"/>?id='+row.id;
	}
}
function roleMgsaveUser(){
	$('#roleMgfm').form('submit',{
		url: roleMgurl,
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
				$('#roleMgdlg').dialog('close'); // close the dialog
				$('#roleMgdg').datagrid('reload'); // reload the user data
			}
		}
	});
}


function roleMggetSelections(){
    var ss = [];
    var rows = $('#roleMgdg').datagrid('getSelections');
    for(var i=0; i<rows.length; i++){
        var row = rows[i];
        ss.push(row.id);
    }
    return ss;
}

function roleMgdestroyUser(){
	var ids=roleMggetSelections();
	var row = $('#roleMgdg').datagrid('getSelected');
	if (row){
		$.messager.confirm('Confirm','确定删除?',function(r){
			if (r){
				$.post('<c:url value="/account/rolectl/Delete.do"/>',{ids:ids.join(',')},function(result){
					if (result.success){
						$('#roleMgdg').datagrid('reload'); // reload the user data
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


</script>
