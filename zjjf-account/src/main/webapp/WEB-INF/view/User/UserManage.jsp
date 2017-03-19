<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="easyui-layout"  fit="true">
	<div  region="west"  style="width:70%;" data-options="split:true">
		<!--=================================================west-table========================================================  -->
		<table id="userMgdg" 
			idField="id"  sortName="regtime" sortOrder="desc"  pageSize="50"
			toolbar="#userMgtoolbar" pagination="true" fitColumns="true"  fit="true"
			data-options="rownumbers:true,singleSelect:true,method:'post',onClickRow:getRolesByUserId">
		    <thead>
		        <tr>
		        <th data-options="field:'ck',checkbox:true"></th>
		        <th field="url"  sortable="true" >头像</th>
		        <th field="id"  sortable="true">会计ID</th>
		        <th field="username"  sortable="true" >登录名称</th>
		        <th field="nickname"  sortable="true" >会计昵称</th>
		        <th field="realname"  sortable="true" >真实姓名</th>
		        <th field="mobile"  sortable="true" >手机号</th>
		        <th field="gender"  sortable="true" >性别</th>
		        <th field="regtime"  sortable="true" >注册时间</th>
		        <th field="regip"  sortable="true" >注册IP</th>
		        <th field="lasttime"  sortable="true" >最后登录时间</th>
		        <th field="lastip"  sortable="true" >最后登录IP</th>
		        <th field="updatetime"  sortable="true" >资料更新时间</th>
		        <th field="birthday"  sortable="true" >生日</th>
		        <th field="password"  sortable="true" >密码</th>
		        <th field="intensity"  sortable="true" >密码强度</th>
		        <th field="email"  sortable="true" >邮箱</th>
		        <th field="useaddress"  sortable="true" >住址</th>
		        <th field="province"  sortable="true" >省</th>
		        <th field="city"  sortable="true" >市</th>
		        <th field="areaid"  sortable="true" >区</th>
		        <th field="idnum"  sortable="true" >身份证号码</th>
		        <th field="idpicurl0"  sortable="true" >身份证正面</th>
		        <th field="idpicurl1"  sortable="true" >身份证背面</th>
		        <th field="ismanager"  sortable="true" formatter="pulbicFormatIsManager">是否管理员</th>
		        <th field="status"  sortable="true" formatter="pulbicFormatState">状态</th>
		        <th field="isdelete"  sortable="true" formatter="publicFormatDel">是否删除</th>
		        </tr>
		    </thead>
		</table>
		<!--===================================================west-table-toolbar=================================================  -->
		<div id="userMgtoolbar" style="padding:5px;">
			 <div>
			  用户ID:<input id="userMgField1"class="easyui-textbox" style="width:140px;">
		    用户登录名:<input id="userMgField2"class="easyui-textbox" style="width:140px;">
		   <!--  性别: 
		    <select id="userMgSex" class="easyui-combobox" panelHeight="auto" style="width:100px" >
		        <option value="0" >男</option>
		        <option value="1">女</option>
		        <option value=""  selected="selected">全部</option>
		    </select> -->
		    状态: <select id ="userMgstate" class="easyui-combobox" panelHeight="auto" style="width:100px" >
				     <option value="0">可用</option>
				     <option value="1">不可用</option>
				     <option value="" selected="selected">全部</option>
			</select>
		    是否删除: 
		    <select id="userMgIsDelete" class="easyui-combobox" panelHeight="auto" style="width:100px" >
		        <option value="false" selected="selected">否</option>
		        <option value="true">是</option>
		        <option value=""  >全部</option>
		    </select>
		    <a  id="userMgtoolbar-findlist"  class="easyui-linkbutton"  iconCls="icon-search" plain="true"  >查询</a>
		    </div>
		   	<a id="userMgtoolbar-new"   class="easyui-linkbutton" iconCls="icon-add" plain="true"  >新建用户</a>
		    <a id="userMgtoolbar-edit"  class="easyui-linkbutton" iconCls="icon-edit" plain="true"  >编辑用户</a>
		    <a id="userMgtoolbar-delete" class="easyui-linkbutton" iconCls="icon-remove" plain="true"  >删除用户</a>
		</div>
		
		<!--===================================================west-table-modeifydlg=================================================  -->
		<div id="userMgdlg" class="easyui-dialog"  modal="true"  style="min-width:400px;padding:10px 20px;min-height:200px;" closed="true" buttons="#userMgdlgbt">
		    <div class="ftitle">用户信息编辑</div>
		    <form id="userMgfm" method="post">
		    	<div class="fitem">
		    		<label>登录名称:</label><input name="userName" class="easyui-textbox" required="true"/>
		    		<label>状态:</label><input name="status" class="easyui-combobox"  required="true"  data-options="data:miboState,valueField:'value',textField:'lable',panelHeight:'auto',editable:false"/>
		    	</div>
		    	<div class="fitem">
		    		<label>会计昵称:</label><input name="nickName" class="easyui-textbox"/>
		    		<label>真实姓名:</label><input name="realName" class="easyui-textbox"/>
		    	</div>
		    	<div class="fitem">
		    		<label>性别:</label><input name="gender" class="easyui-combobox" data-options="data:miboUserSex,valueField:'value',textField:'lable',panelHeight:'auto',editable:false" required/>
		    		<label>生日:</label><input name="birthday" class="easyui-datebox" data-options="editable:false"/>
		    	</div>
		    	<div class="fitem">
			    	<label>密码:</label><input name="password" class="easyui-textbox" required/>
			    	<label>手机号:</label><input name="mobile" class="easyui-numberbox" required/>
		    	</div>
		    	<div class="fitem">
		    		<label>邮箱:</label><input name="email" class="easyui-textbox" validType="email"/>
					<label>住址:</label><input name="useAddress" class="easyui-textbox"/>
				</div>
		    	<div class="fitem">
		    		<label>身份证号码:</label><input name="idNum" class="easyui-textbox"/>
					<label>是否管理员:</label><input name="isManager" class="easyui-combobox" data-options="data:miboIsManager,valueField:'value',textField:'lable',panelHeight:'auto',editable:false" required/>
				</div>
		    	<div class="fitem">
		    		<label>是否删除:</label><input name="isDelete" class="easyui-combobox" data-options="data:miboDeleteFlag,valueField:'value',textField:'lable',panelHeight:'auto',editable:false" required/>
				</div>
		    </form>
		</div>
		
		<!--===================================================west-table-modeifydlg-bt=================================================  -->
		<div id="userMgdlgbt">
			<a id="userMgdlgbt-save"  class="easyui-linkbutton c6" iconCls="icon-ok"   style="width:90px">保存</a>
			<a id="userMgdlgbt-cancel"   class="easyui-linkbutton" iconCls="icon-cancel"  style="width:90px">取消</a>
		</div>
	</div>
	<div  region="center"  split="true"  title="用户角色设置" >
		<div  class="easyui-layout"  fit="true" >
			<div region="center">
				<table id="userMgRoledg" 
					idField="id"  sortName="createtime" sortOrder="desc" 
					pagination="true" fitColumns="true"  fit="true"
					data-options="rownumbers:true,method:'post'">
				    <thead>
				        <tr>
				        <th data-options="field:'ck',checkbox:true"></th>
				        <th field="roleName"  sortable="true" editor="text" width="100">角色名称</th>
				        <th field="roleRemark" sortable="true" editor="text" width="100">角色描述</th>
				        <th field="createTime" sortable="true" editor="text" width="200">创建时间</th>
				        <th field="state" sortable="true" editor="text"  formatter="pulbicFormatState" width="80">审核</th>
				        <th field="isDelete" sortable="true" editor="text" formatter="publicFormatDel" width="80">是否删除</th>
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
var mibocrud = MiBoCRUD.creatNew({
	modelName:"userMg",
	dialogTitle:"用户信息编辑",
	listURL:'<c:url value="/account/userctl/List.do"/>',
	creatURL:'<c:url value="/account/userctl/Add.do"/>',
	updateURL:'<c:url value="/account/userctl/Update.do"/>',
	deleteURL:'<c:url value="/account/userctl/Delete.do"/>',
	findListFun:function(){
		$("#userMgdg" ).datagrid('load',{
			id:$("#userMgField1").textbox('getValue'),
			realName:$("#userMgField2").textbox('getValue'),
			//sex:$("#userMgSex").combobox('getValue'),
			status:$("#userMgstate").combobox('getValue'),
			isDelete:$("#userMgIsDelete").combobox('getValue')
		})
	}
}).init();

$('#userMgRoledg').datagrid({
	url: '<c:url value="/account/rolectl/List.do"/>'
});

function getRolesByUserId(rowIndex,rowData){
	$('#userMgRoledg').datagrid("clearSelections");
	currentUser = rowData;
	if(rowData){
		$.post( '<c:url value="/account/userctl/getRolesById.do"/>',{id:rowData.id},function(result){
			currentUserRoles=result;//留给刷新使用
			if(result.length>0){
				 $.each(result, function(){
					 $('#userMgRoledg').datagrid("selectRecord",this+"");
				 });   
			}
		},'json');	
	}
}


//刷新
function reloadAuthList(){
	$('#userMgRoledg').datagrid("reload");
	if(currentUserRoles.length>0){
		 $.each(currentUserRoles, function(){     
			 $('#userMgRoledg').datagrid("selectRecord",this+"");
		 });   
	}
}
//清除所有行clearSelections
function deSelectAll(rowIndex,rowData){
	$('#userMgRoledg').datagrid("clearSelections");
}
//选择所有行selectAll
function inSelectAll(rowIndex,rowData){
	$('#userMgRoledg').datagrid("selectAll");
}
//保存所选
function saveAuths(rowIndex,rowData){
	if(currentUser){//用户未选中任何角色
		$.post( '<c:url value="/account/userctl/saveRoles.do"/>',{
				userId:currentUser.id,
				roleIds:getSelections('userMgRoledg').join(',')
			},function(result){
				$.messager.alert("信息",""+result.message);
		},'json');
	}else{
		$.messager.alert("提示","请选择用户后提交");
	}
}
</script>

