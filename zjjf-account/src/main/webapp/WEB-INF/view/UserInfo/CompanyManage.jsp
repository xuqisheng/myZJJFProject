<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>企业信息管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="../common/header.jsp"></jsp:include>
<script type="text/javascript">
/**
 * 格式化附加信息
 */
function homeUrlFormatter(value, row) {
	var homeurl = '<a href="' + value + '" target="_blank">查看主页</a>';
	var space = "&nbsp;";
	return  space + homeurl + space  ;
}

/**
 * 格式化附加信息
 */
function zhizhaoPicFormatter(value, row) {
	var homeurl = '<a  href="#" src="' +$("#picPrefix").val()+ value + '" class="miboshowBigPic">查看照片</a>';
	var space = "&nbsp;";
	return  space + homeurl + space  ;
}


	$(function(){
		var mibocrud = MiBoCRUD.creatNew({
			modelName:"companyMg",
			dialogTitle:'企业信息管理',
			listURL:'<c:url value="/admin/companyctl/List.do"/>',
			creatURL:'<c:url value="/admin/companyctl/Add.do"/>',
			updateURL:'<c:url value="/admin/companyctl/Update.do"/>',
			deleteURL:'<c:url value="/admin/companyctl/Delete.do"/>',
			findListFun:function(){
				$("#companyMgdg" ).datagrid('load',{
					name:$("#companyMglikeName").textbox('getValue'),
					isDelete:$("#companyMgstate").combobox('getValue')
				})
			}
		}).init();
	});
</script>
</head>
<body>
<input id="picPrefix" type="hidden"  value="${picPrefix}" />
 <table id="companyMgdg" 
	idField="id" sortName="createTime" sortOrder="desc"  pageSize =50
	toolbar="#companyMgtoolbar" pagination="true" fitColumns="true"  fit="true"
	data-options="rownumbers:true,singleSelect:true,method:'post'">
    <thead>
        <tr>
        <th field="name"  sortable="true" editor="text">企业名称</th>
        <th field="loginName"  sortable="true" editor="text">企业登录名称</th>
        <th field="address" sortable="true" editor="text">企业地址</th>
        <th field="telNo" sortable="true" editor="text">联系电话</th>
        <th field="faxNo" sortable="true" editor="text">传真</th>
        <th field="homeUrl" sortable="true" editor="text"  formatter="homeUrlFormatter" >企业主页</th>
        <th field="type" sortable="true" editor="text" formatter="pulbicFormatCompanyType">企业类型</th>
        <th field="operatingLicensePic" sortable="true" editor="text" formatter="zhizhaoPicFormatter">许可证照片</th>
        <th field="operatingLicenseNo" sortable="true" editor="text">许可证号码</th>
        <th field="lawPerson" sortable="true" editor="text">法人代表</th>
        <th field="lawPersonIdCard" sortable="true" editor="text">法人身份号码</th>
        <th field="lawPersonTelNo" sortable="true" editor="text">法人电话</th>
        <th field="linkPersonName" sortable="true" editor="text">企业的联系人姓名</th>
        <th field="linkPersonIdCard" sortable="true" editor="text">企业的联系人身份证号码</th>
        <th field="linkPersonTelNo" sortable="true" editor="text">企业的联系人电话</th>
        <th field="settledDate" sortable="true" editor="text"  >入住时间</th>
        <th field="createTime" sortable="true" editor="text"  >创建时间</th>
        <th field="createUserName" sortable="true" editor="text"  >创建人名称</th>
        <th field="isDelete" sortable="true"  editor="text"  formatter="publicFormatDel">是否删除</th>
        </tr>
    </thead>
</table>
<div id="companyMgtoolbar">
    <a id="companyMgtoolbar-new"   class="easyui-linkbutton" iconCls="icon-add" plain="true"  >新建企业</a>
    <a id="companyMgtoolbar-edit"  class="easyui-linkbutton" iconCls="icon-edit" plain="true"  >编辑企业</a>
    <a id="companyMgtoolbar-delete"  class="easyui-linkbutton" iconCls="icon-remove" plain="true" >删除企业</a>
    企业名称:<input id="companyMglikeName"class="easyui-textbox" style="width:140px;">
   	是否删除: 
       <select id="companyMgstate" class="easyui-combobox" panelHeight="auto" style="width:100px" >
	        <option value="false"  selected="selected">否</option>
	        <option value="true">是</option>
	        <option value=""  >全部</option>
       </select>
    <a id="companyMgtoolbar-findlist"  class="easyui-linkbutton" iconCls="icon-search" plain="true"  >查询</a>
</div>

 <div id="companyMgdlg" class="easyui-dialog" modal="true" style="width:600px;padding:10px 20px;min-height:400px;" closed="true" buttons="#companyMgdlgbt">
    <div class="ftitle">企业信息编辑</div>
    <form id="companyMgfm" method="post" novalidate>
    <div class="fitem"><label>企业名称:</label><input name="name" class="easyui-textbox"  required="true" ></div>
    <div class="fitem"><label>企业登录名称:</label><input name="loginName" onkeyup="value=value.replace(/[\W]/g,'') "
    										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"></div>
    <div class="fitem"><label>企业主页:</label><input name="homeUrl" class="easyui-textbox"  style="width:420px;"></div>
    <div class="fitem"><label>企业地址:</label><input name="address" class="easyui-textbox"  required="true" style="width:420px;"></div>
    <div class="fitem"><label>联系电话:</label><input name="telNo" class="easyui-textbox"  required="true" ></div>
    <div class="fitem"><label>传真:</label><input name="faxNo" class="easyui-textbox"  required="true" ></div>
    <div class="fitem"><label>企业类型:</label><input name="type" class="easyui-combobox"  required="true"  data-options="data:miboCompanyType,valueField:'value',textField:'lable',panelHeight:'auto',editable:false"  >
    <!-- <label>企业类型:</label>
    	<select name="type" class="easyui-combobox"  required="true" >
    		<option value="0">医药企业</option>
    		<option value="1">医疗器械企业</option>
    		<option value="2">其他企业</option>
    	</select> -->
    </div>
    <!-- <div class="fitem"><label>许可证照片:</label> --><input name="operatingLicensePic" type="hidden"><!-- </div> -->
    <div class="fitem"><label>许可证号码:</label><input name="operatingLicenseNo" class="easyui-textbox"  required="true"  ></div>
    <div class="fitem"><label>法人代表:</label><input name="lawPerson" class="easyui-textbox"   ></div>
    <div class="fitem"><label>法人身份证:</label><input name="lawPersonIdCard" class="easyui-textbox"    ></div>
    <div class="fitem"><label>法人电话:</label><input name="lawPersonTelNo" class="easyui-textbox"   ></div>
    <div class="fitem"><label>联系人姓名:</label><input name="linkPersonName" class="easyui-textbox"  ></div>
    <div class="fitem"><label>联系人身份证:</label><input name="linkPersonIdCard" class="easyui-textbox" ></div>
    <div class="fitem"><label>联系人电话:</label><input name="linkPersonTelNo" class="easyui-textbox"   ></div>
    <input name="settledDate" type="hidden"></th>
    <input name="createTime" type="hidden"></th>
    <input name="userId" type="hidden"></th>
    <input name="isDelete" type="hidden"></th>
    </form>
</div>
<div id="companyMgdlgbt">
	<a id="companyMgdlgbt-save" class="easyui-linkbutton c6" iconCls="icon-ok"  style="width:90px">保存</a>
	<a id="companyMgdlgbt-cancel" class="easyui-linkbutton" iconCls="icon-cancel"  style="width:90px">取消</a>
</div>

</body>
</html>
