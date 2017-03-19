<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="easyui-layout"  fit="true">
	<div region="center">
		<!--=================================================west-table========================================================  -->
		<table id="settleConfMgdg" idField="id"  sortName="id" sortOrder="desc"  pageSize="50"
			toolbar="#settleConfMgtoolbar" pagination="true" fitColumns="true"  fit="true"
			data-options="rownumbers:true,singleSelect:true,method:'post'">
		    <thead>
		        <tr>
		        <th field="id">主键</th>
		        <th field="type">类型</th>
		        <th field="content">内容</th>
		        <th field="remark">备注</th>
		        <th field="img">图片</th>
		        <th field="isDelete" formatter="publicFormatDel">删除</th>
		        </tr>
		    </thead>
		</table>
		<!--===================================================west-table-toolbar=================================================  -->
		<div id="settleConfMgtoolbar" style="padding:5px;">
			 <div>
			  	内容:<input id="settleConfMgField2" class="easyui-textbox" style="width:240px;">
		   		<a  id="settleConfMgtoolbar-findlist"  class="easyui-linkbutton"  iconCls="icon-search" plain="true">查询</a>
		    </div>
		</div>
	</div>
</div>
 <div id="settleConfMgdlg" class="easyui-dialog"  modal="true"  style="min-width:400px;padding:10px 20px;min-height:400px;" closed="true" buttons="#settleConfMgdlgbt">
    <div class="ftitle">信息编辑</div>
    <form id="settleConfMgfm" method="post" novalidate>
    <!-- <div class="fitem"><label>id:</label><input name="id" class="easyui-textbox"></div> -->
    <div class="fitem"><label>类型:</label><input name="type" class="easyui-textbox" ></div>
	<div class="fitem"><label>内容:</label><input name="content" class="easyui-textbox" ></div>
	<div class="fitem"><label>备注:</label><input name="remark" class="easyui-textbox" ></div>
	<div class="fitem"><label>图片:</label><input name="img" class="easyui-textbox" ></div>
	<div class="fitem"><label>删除:</label><input name="isDelete" class="easyui-textbox" ></div>
    </form>
</div>
<div id="settleConfMgdlgbt">
	<a id="settleConfMgdlgbt-save"  class="easyui-linkbutton c6" iconCls="icon-ok"   style="width:90px">保存</a>
	<a id="settleConfMgdlgbt-cancel"   class="easyui-linkbutton" iconCls="icon-cancel"  style="width:90px">取消</a>
	<!-- <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="javascript:settleConfMgsaveUser();" style="width:90px">保存</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#settleConfMgdlg').dialog('close');" style="width:90px">取消</a> -->
</div>

<script type="text/javascript">

var mibocrud = MiBoCRUD.creatNew({
	modelName:"settleConfMg",
	dialogTitle:"仓库结算汇单信息编辑",
	listURL:'<c:url value="/account/sysconfigctl/List.do"/>',
	creatURL:'<c:url value="/account/sysconfigctl/Add.do"/>',
	updateURL:'<c:url value="/account/sysconfigctl/Update.do"/>',
	deleteURL:'<c:url value="/account/sysconfigctl/Delete.do"/>',
	findListFun:function(){
		$("#settleConfMgdg" ).datagrid('load',{
			content:$("#settleConfMgField2").textbox('getValue')
		})
	}
}).init();
</script>

