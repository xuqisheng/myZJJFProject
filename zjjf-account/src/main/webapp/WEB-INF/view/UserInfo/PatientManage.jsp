<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>患者信息管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta name="viewport" content="width=device-width" />
<jsp:include page="../common/header.jsp"></jsp:include>

</head>
<body>
<input id="picPrefix" type="hidden"  value="${picPrefix}" />
<table id="patientMgdg" 
	idField="id"  sortName="createTime" sortOrder="desc" pageSize =50
	toolbar="#patientMgtoolbar" pagination="true" fitColumns="true"  fit="true"
	data-options="rownumbers:true,singleSelect:true,method:'post',onDblClickRow:patientMgeditUser">
    <thead>
        <tr>
        <!-- <th field="userName" sortable="true" editor="text">用户名称</th> -->
        <th field="face" sortable="true" editor="text" formatter="publicPicFormatter">患者头像</th>
        <th field="loginName"  sortable="true" editor="text" width="100">登录名称</th>
        <th field="name"  sortable="true" editor="text" width="100">患者名称</th>
        <th field="nickName" sortable="true" editor="text" width="100">患者昵称</th>
        <th field="sex" sortable="true" editor="text" formatter="publicFormatSex" width="60">患者性别</th>
        <th field="phoneNum" sortable="true" editor="text" width="120">手机号码</th>
        <th field="birthday" sortable="true" editor="text"  formatter="publicFormatDate" width="80">患者生日</th>
        <th field="idCard" sortable="true" editor="text" width="120">身份证号码</th>
        <th field="marriage" sortable="true" editor="text" formatter="publicFormatMarry" width="60">婚姻状况</th>
        <th field="createTime" sortable="true" editor="text" width="150">创建时间</th>
        <th field="createUserName" sortable="true" editor="text" width="100">创建人</th>
        <th field="isDelete" sortable="true"  editor="text"  formatter="publicFormatDel" width="80">是否删除</th>
        </tr>
    </thead>
</table>
<div id="patientMgtoolbar">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:patientMgnewUser();">新建患者</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:patientMgeditUser()">编辑患者</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:patientMgdestroyUser(); ">删除患者</a>
            患者名称:<input id="patientMglikeName"class="easyui-textbox" style="width:140px;">
			是否删除: <select id="patientMgstate" class="easyui-combobox" panelHeight="auto" style="width:100px" onchange="doSearch();">
				        <option value="false" selected="selected">否</option>
				        <option value="true">是</option>
				        <option value=""  >全部</option>
			       </select>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="javascript:patientMgfind()">查询</a>
</div>

 <div id="patientMgdlg" class="easyui-dialog" style="min-width:400px;padding:10px 20px;min-height:400px;" closed="true" buttons="#patientMgdlgbt">
    <div class="ftitle">患者信息编辑</div>
    <form id="patientMgfm" method="post" novalidate>
    <!-- <div class="fitem"><label>用户Id</label><input name="userId" class="easyui-textbox" ></div>
    <div class="fitem"><label>用户名称:</label><input name="userName" class="easyui-textbox" editable="false"></div>
    <div class="fitem"><label>患者头像:</label> --><input name="face" style="width: 420px;" type="hidden"><!-- class="easyui-textbox" </div> -->
    <div class="fitem"><label>患者名称:</label><input name="name" class="easyui-textbox" ></div>
    <div class="fitem"><label>患者登录名称:</label><input name="loginName" onkeyup="value=value.replace(/[\W]/g,'') "
    										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/></div>
    <div class="fitem"><label>患者昵称:</label><input name="nickName" class="easyui-textbox" ></div>
    <div class="fitem"><label>患者性别:</label><input name="sex"  class="easyui-combobox"  data-options="data:miboUserSex,valueField:'value',textField:'lable',panelHeight:'auto',editable:false"   required>
    <!--<label>患者性别:</label>
    	<select name="sex" class="easyui-combobox" style="width:158px;" panelHeight="auto">
    		<option value="0">男</option>
    		<option value="1">女</option>
    	</select>-->
    </div>  
    <div class="fitem"><label>患者生日:</label><input name="birthday" class="easyui-datebox" data-options="editable:false"></div>
    <div class="fitem"><label>身份证号码:</label><input id="idCard_validate" name="idCard" class="easyui-textbox" ></div>
    <div class="fitem"><label>婚姻状况:</label><input name="marriage" class="easyui-combobox"  required="true"  data-options="data:miboMarryState,valueField:'value',textField:'lable',panelHeight:'auto',editable:false" >
    <div class="fitem"><label>电子邮箱:</label><input id="email_validate" name="email" class="easyui-textbox">
    <!-- <label>婚姻状况:</label>
    	<select name="marriage" class="easyui-combobox" style="width:158px;" panelHeight="auto">
    		 <option value="0">未婚</option>
    		 <option value="1">已婚</option>
    		 <option value="2">保密</option>
    	</select> -->
    </div>
     <input name="creatorId"  type="hidden">
     <input name="createTime"  type="hidden">
     <input name="isDelete"  type="hidden">
    </form>
</div>
<div id="patientMgdlgbt">
	<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="javascript:patientMgsaveUser();" style="width:90px">保存</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#patientMgdlg').dialog('close');" style="width:90px">取消</a>
</div>

<script type="text/javascript">

$(function(){
	$("input",$("#idCard_validate").next("span")).blur(function(){  
   	 	var re = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
   	    if(!re.test($("#idCard_validate").val())){
   	    	$('#idCard_validate').textbox('setValue', '');
   	    	alert("身份证号码验证错误");
   	    }
   	});
	
	$("input",$("#email_validate").next("span")).blur(function(){  
   	 	var re = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
   	    if(!re.test($("#email_validate").val())){
   	    	$('#email_validate').textbox('setValue', '');
   	    	alert("电子邮箱验证错误");
   	    }
   	});
	
	$('#patientMgdg').datagrid({
		url: '<c:url value="/admin/patientctl/List.do"/>',
		queryParams:{isDelete:0}
	});
});

function patientMgfind(){
	 $('#patientMgdg').edatagrid('load',{
		name:$("#patientMglikeName").textbox('getValue'),
		isDelete:$("#patientMgstate").combobox('getValue')
	 });
}

var patientMgurl ="";
function patientMgnewUser(){
	$('#patientMgdlg').dialog('open').dialog('setTitle','患者信息编辑');
	$('#patientMgfm').form('clear');
	patientMgurl = '<c:url value="/admin/patientctl/Add.do"/>';
}

function patientMgeditUser(){
	var row = $('#patientMgdg').datagrid('getSelected');
	if (row){
		$('#patientMgdlg').dialog('open').dialog('setTitle','编辑用户');
		$('#patientMgfm').form('load',row);
		patientMgurl = '<c:url value="/admin/patientctl/Update.do"/>?id='+row.id;
	}
}
function patientMgsaveUser(){
	$('#patientMgfm').form('submit',{
		url: patientMgurl,
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
				$('#patientMgdlg').dialog('close'); // close the dialog
				$('#patientMgdg').datagrid('reload'); // reload the user data
			}
		}
	});
}

/*
function patientMggetSelections(){
    var ss = [];
    var rows = $('#patientMgdg').datagrid('getSelections');
    for(var i=0; i<rows.length; i++){
        var row = rows[i];
        ss.push(row.id);
    }
    return ss;
}*/

function patientMgdestroyUser(){
	var ids=getSelections("patientMgdg");
	var row = $('#patientMgdg').datagrid('getSelected');
	if (row){
		$.messager.confirm('Confirm','确定删除?',function(r){
			if (r){
				$.post('<c:url value="/admin/patientctl/Delete.do"/>',{ids:ids.join(',')},function(result){
					if (result.success){
						$('#patientMgdg').datagrid('reload'); // reload the user data
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
</body>
</html>