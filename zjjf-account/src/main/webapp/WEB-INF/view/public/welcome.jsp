<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- CSS for welcom.jsp  Page-->
<style type="text/css">
*{ margin:0; padding:0; font-size:12px;}
#mainTopPanel_logo{width:300px;height:50px;position:absolute;left:30px;bottom:5px ; }
#mainTopPanel_userInfo { width:600px;height:30px;position:absolute;right:20px;bottom:5px;}
#mainTopPanel_userInfo li{ float:right; list-style:none;}
.tree-node{ padding-top:6px; padding-bottom:6px;}
#panel_top{ background:#000}
</style>
<script>
WelcomPage={
	createNew:function(){
		var welcomePage = {};
		welcomePage.alertMsg=function(){
			alert("sdfasdfasdfa")
		}
		return welcomePage;
	}
};
var welcomPage = WelcomPage.createNew();
</script>
<table class="easyui-datagrid" title="Basic DataGrid" style="width:700px;height:250px"
data-options="singleSelect:true,collapsible:true,url:'datagrid_data1.json',method:'get'">
<thead>
<tr>
<th data-options="field:'itemid',width:80">Item ID</th>
<th data-options="field:'productid',width:100">Product</th>
<th data-options="field:'listprice',width:80,align:'right'">List Price</th>
<th data-options="field:'unitcost',width:80,align:'right'">Unit Cost</th>
<th data-options="field:'attr1',width:250">Attribute</th>
<th data-options="field:'status',width:60,align:'center'">Status</th>
</tr>
</thead>
</table>

