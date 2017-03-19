<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>部门管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="../common/header.jsp"></jsp:include>
</head>

<body >
<!-- layout div -->
<div class="easyui-layout"  fit="true">

<div region="west"  split="true"  title="部门菜单"  style="width:300px;padding:5px;" toolbar="#westPanelTool" >
	<ul id="departmentMgtt"  fit="true"></ul>
</div>

<div region="center" >
	<div class="easyui-panel"  fit="true" style="padding:20px;"  title="编辑区域">
		    <div class="ftitle">部门信息编辑</div>
		    <form id="depMgfm" method="post" novalidate>
		    <div class="fitem"><label>部门名称:</label><input name="name" class="easyui-textbox" required></div>
		    <div class="fitem"><label>部门编号:</label><input name="code" class="easyui-textbox" required></div>
		    <div class="fitem"><label>部门描述:</label><input name="remark" class="easyui-textbox" ></div>
		    <div class="fitem"><label>部门领导:</label><input id="departmentMgLeaderId" name="leaderId"  required="true" ></div>
		    <div class="fitem"><label>上级部门:</label><input id="departmentMgUpDp" name="pId"  required="true" ></div>
    		<div class="fitem"><label>审核:</label><input name="state"  class="easyui-combobox"  required="true"  data-options="data:miboState,valueField:'value',textField:'lable',panelHeight:'auto',editable:false" ></div>
		    <div class="fitem"><label>排序:</label><input name="orderIndex" class="easyui-numberbox"  required></div>
		    <div class="fitem"><label>备用字段一:</label><input name="col1" class="easyui-textbox" ></div>
		    <div class="fitem"><label>备用字段二:</label><input name="col2" class="easyui-textbox" ></div>
		    <div class="fitem"><label>备用字段二:</label><input name="col3" class="easyui-textbox" ></div>
		    <input name="creatorId"  type="hidden">
		    <input name="createTime"   type="hidden">
		    <input name="isDelete"   type="hidden">
		    </form>
		    
		    <div id="depMgdlgbt">
				<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="javascript:depMgsaveDep();" style="width:90px">保存</a>
			</div>
	</div>
</div>

</div>
<!-- menu div -->
 <div id="mm" class="easyui-menu" style="width:120px;">
	<div onclick="append()" data-options="iconCls:'icon-add'">添加子节点</div>
	<div onclick="removeit()" data-options="iconCls:'icon-remove'">删除</div>
	<div class="menu-sep"></div>
	<div onclick="expand()">Expand</div>
	<div onclick="collapse()">Collapse</div>
</div>

<script type="text/javascript">

/*****************************public courent node******************************/
var  courentNode;

var depMgurl="";
 
 
 $('#departmentMgtt').tree({
	 	url: '<c:url value="/admin/departmentctl/getSubDepartment.do"/>',
		queryParams:{id:'DEPARTMENT_TOP_NODE'},
		method: 'post',
		onContextMenu: function(e,node){
			e.preventDefault();
			$(this).tree('select',node.target);
			$('#mm').menu('show',{
				left: e.pageX,
				top: e.pageY
			});
		},
		onClick:function(node){
			node.state = node.tempState;
			$('#depMgfm').form('load',node);
			//加载上级部门的信息
			var  departmentMgUpDpgd = $('#departmentMgUpDp').combogrid('grid');
			departmentMgUpDpgd.datagrid("reload",{id:node.pId,state:0,isDelete:0});
			$('#departmentMgUpDp').combogrid("setValue",node.pId);
			departmentMgUpDpgd.datagrid('options').queryParams = {state:0,isDelete:0} ;
			departmentMgUpDpgd.datagrid('options').onShowPanel = function(){
				alert("dd");
			} ;
			
			//当前部门的管理员
			var  departmentMgLeaderIddg = $('#departmentMgLeaderId').combogrid('grid');
			departmentMgLeaderIddg.datagrid("reload",{id:node.leaderId,state:0,isDelete:0});
			$('#departmentMgLeaderId').combogrid("setValue",node.leaderId);
			departmentMgLeaderIddg.datagrid('options').queryParams = {state:0,isDelete:0} ;
			
			depMgurl = '<c:url value="/admin/departmentctl/Update.do"/>?id='+node.id;
		}
 });
 /*****************************form combobox*************************************/
 $('#departmentMgUpDp').combogrid({
	 	url: '<c:url value="/admin/departmentctl/getAllDepartMents.do"/>',
		queryParams:{state:0,isDelete:0},
		fitColumns:true,
		pagination:true,
		panelWidth:450,
		idField:"id",
		textField:"name",
		mode: 'remote',
		editable: false,
		onShowPanel:function(){
			$('#departmentMgUpDp').combogrid("grid").datagrid("load");
		},
		//value:"",//default value
		columns:[[
		          {field:'name',title:'部门名称',width:160},
		          {field:'code',title:'部门编码',width:100},
		]]
 });
 
 $('#departmentMgLeaderId').combogrid({
	 	url: '<c:url value="/admin/userctl/List.do"/>',
		queryParams:{state:0, isDelete:0},
		fitColumns:true,
		pagination:true,
		panelWidth:450,
		idField:"id",
		textField:"loginName",
		mode: 'remote',
		onShowPanel:function(){
			$('#departmentMgLeaderId').combogrid("grid").datagrid("load");
		},
		//value:"",//default value
		columns:[[
		          {field:'loginName',title:'用户名称',width:160},
		          {field:'sex',title:'用户性别',width:100},
		          {field:'departmentName',title:'部门名称',width:100}
		]],
		// 点击刷新按钮有效果
		keyHandler:{
			query: function(keyword) {
				var  departmentMgLeaderIddg = $('#departmentMgLeaderId').combogrid('grid');
				departmentMgLeaderIddg.datagrid("load",{loginName:keyword,state:0,isDelete:0});
				departmentMgLeaderIddg.datagrid('options').queryParams = {state:0,isDelete:0} ;
				//设置查询参数
				//var queryParams = $('#departmentMgLeaderId').combogrid('grid').datagrid('options').queryParams;
				//queryParams.loginName = keyword;
				//('#departmentMgLeaderId').combogrid('grid').datagrid('options').queryParams = queryParams;
				//重新加载
				//$('#departmentMgLeaderId').combogrid('grid').datagrid('reload');
				//$('#departmentMgLeaderId').combogrid('setValue', keyword);
            }
		}
});
 
/*****************************menu funtion*************************************/
function append(){
	var t = $('#departmentMgtt');
	courentNode = t.tree('getSelected');
	depMgurl = '<c:url value="/admin/departmentctl/Add.do"/>';
	$('#depMgfm').form('clear');
	//load current depart ment for new node
	var  departmentMgUpDpgd = $('#departmentMgUpDp').combogrid('grid');
	departmentMgUpDpgd.datagrid("reload",{id:courentNode.id,state:0,isDelete:0});
	$('#departmentMgUpDp').combogrid("setValue",courentNode.id);
	departmentMgUpDpgd.datagrid('options').queryParams = {state:0,isDelete:0} ;
}
function removeit(){
	$.messager.confirm("提示","确定删除该部门吗?",function(r){
		if(r){
			var node = $('#departmentMgtt').tree('getSelected');
			if(node.children.length <= 0 ){
				if( node.id == "DEPARTMENT_TOP_NODE" ){
					$.messager.alert("提示","根节点部门不能删除！");
					return;
				}
				var temp = [];
				temp.push(node.id);
				$.getJSON( '<c:url value="/admin/departmentctl/Delete.do"/>',{
					ids:temp.join(",")
				},function(result){
					if(result.success){			
						$('#departmentMgtt').tree('remove', node.target);
					}else{
						$.messager.show({ // show error message
							title: 'Error',
							msg: result.message
						});
					}
				});
			}else{
				$.messager.show({ // show error message
					title: 'Error',
					msg: "非叶子节点部门不能删除"
				});
			}
		}
	});
}
function collapse(){
	var node = $('#departmentMgtt').tree('getSelected');
	$('#departmentMgtt').tree('collapse',node.target);
}
function expand(){
	var node = $('#departmentMgtt').tree('getSelected');
	$('#departmentMgtt').tree('expand',node.target);
}

function expandTo(){
	var node = $('#departmentMgtt').tree('find',113);
	$('#departmentMgtt').tree('expandTo', node.target).tree('select', node.target);
}

/*****************************tree control function*************************************/
function depMgsaveDep(){
	$('#depMgfm').form('submit',{
		url: depMgurl,
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
				 $('#departmentMgtt').tree("reload");
			}
		}
	});
}

</script>
</body>
</html>