<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add sorting to DataGrid - jQuery EasyUI Demo</title>
   	<jsp:include page="../common/header.jsp"></jsp:include>
</head>
<body class="easyui-layout">
<div region="west"  title="省"  style="padding:0px;width: 360px;" border="true" header="false">  
    <table border="false" fit="true" id="tt"   url="${root }/admin/city/ListProvince.do"  iconCls="icon-save"
            sortName="orderIndex" sortOrder="desc"
            rownumbers="true"  idField="provinceID"  fitColumns="false" pagination="false"  pageSize="50"
            data-options="singleSelect:true,rownumbers:true,method:'post',toolbar:'#tb',onClickRow:selectProvince">
        <thead>
            <tr>
            	<th data-options="field:'ck',checkbox:true"></th>
                <th field="provinceName" width="150" editor="{type:'validatebox',options:{required:true}}">名称</th>
                <th field="orderIndex" width="60"  sortable="true" align="center" editor="{type:'numberbox',options:{required:true}}">排序</th>
            </tr>
        </thead>
    </table>
    <div id="tb" style="padding:2px 5px;">
       <div style="margin-top: 5px;">
       	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:$('#tt').edatagrid('addRow')">新增</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:$('#tt').edatagrid('destroyRow')">删除</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:$('#tt').edatagrid('saveRow')">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="javascript:$('#tt').edatagrid('cancelRow')">取消</a>   
        </div>
    </div>
</div>
	<div region="center"  split="true"  title="市"  style="padding:0px;width: 360px;" border="true" header="false">  
    <table border="false" fit="true" id="ttt" class="easyui-datagrid"  url=""  iconCls="icon-save"
            sortName="orderIndex" sortOrder="desc"
            rownumbers="true"  idField="cityID"  fitColumns="false" pagination="false"  pageSize="50"
            data-options="singleSelect:true,rownumbers:true,method:'post',toolbar:'#tb1',onClickRow:selectCity">
        <thead>
            <tr>
            	<th data-options="field:'ck',checkbox:true"></th>
                <th field="cityName" width="150" editor="{type:'validatebox',options:{required:true}}">名称</th>
                <th field="zipCode" width="150" editor="{type:'validatebox',options:{required:true}}">编码</th>
                <th field="orderIndex" width="60" sortable="true" align="center" editor="{type:'numberbox',options:{required:true}}">排序</th>
            </tr>
        </thead>
    </table>
	    <div id="tb1" style="padding:2px 5px;">
	       <div style="margin-top: 5px;">
         	 <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:$('#ttt').edatagrid('addRow')">新增</a>
		     <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:$('#ttt').edatagrid('destroyRow')">删除</a>
		     <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:$('#ttt').edatagrid('saveRow')">保存</a>
		     <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="javascript:$('#ttt').edatagrid('cancelRow')">取消</a>   
	        </div>
	    </div>
    </div>
     <div region="east"  title="区" split="true" style="width:400px;" border="true" >
     	<table border="false" fit="true" id=tbDistrict class="easyui-datagrid"  iconCls="icon-save"
            sortName="orderIndex" sortOrder="desc"
            rownumbers="true"  idField="districtID" fitColumns="false" pagination="false"  pageSize="50" data-options="rownumbers:true,method:'post',toolbar:'#tb2'">
	        <thead>
	            <tr>
	            	<th data-options="field:'ck',checkbox:true"></th>
	                <th field="districtName" width="150" editor="{type:'validatebox',options:{required:true}}">名称</th>
	                <th field="orderIndex" width="60" sortable="true" align="center" editor="{type:'numberbox',options:{required:true}}">排序</th>
	            </tr>
	        </thead>
    	</table>
	    <div id="tb2" style="padding:2px 5px;">
	       <div style="margin-top: 5px;">
	          <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:$('#tbDistrict').edatagrid('addRow')">新增</a>
		      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:$('#tbDistrict').edatagrid('destroyRow')">删除</a>
		      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:$('#tbDistrict').edatagrid('saveRow')">保存</a>
		      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="javascript:$('#tbDistrict').edatagrid('cancelRow')">取消</a>   
	        </div>
	    </div>
     </div>  
</body>
<script type="text/javascript">
	$(function(){
	    $('#tt').edatagrid({
	        saveUrl: root+'/admin/city/AddProvince.do',
	        updateUrl: root+'/admin/city/UpdateProvince.do',
	        destroyUrl: root+'/admin/city/DeleteProvince.do',
	    });
	    
	    $('#ttt').edatagrid({
	        saveUrl: root+'/admin/city/AddCity.do',
	        updateUrl: root+'/admin/city/UpdateCity.do',
	        destroyUrl: root+'/admin/city/DeleteCity.do',
	        addRow:function(index){
	        	$('#ttt').edatagrid('addRow',{
	        	    index: 2,
	        	    row:{
	        	        name:'name1',
	        	        addr:'addr1'
	        	    }
	        	});
	        }
	    });
	    
	    $('#tbDistrict').edatagrid({
	        saveUrl: root+'/admin/city/AddDistrict.do',
	        updateUrl: root+'/admin/city/UpdateDistrict.do',
	        destroyUrl: root+'/admin/city/DeleteDistrict.do',
	    });
	    
	    
	});
	function selectProvince(rowIndex,rowData){
		loadCity(rowData.provinceID);
	}
	
	function selectCity(rowIndex,rowData){
		loadDistrict(rowData.cityID);
	}
	
	//加载城市列表
	function loadCity(id){
	  	var queryParams = $('#ttt').datagrid('options').queryParams;  
        queryParams.provinceID =id;
        $('#ttt').datagrid('options').url=root + "/admin/city/ListCity.do";
        $('#ttt').datagrid('options').queryParams=queryParams;        
        $("#ttt").datagrid('reload'); 
	}
	
	function loadDistrict(id){
		var queryParams = $('#tbDistrict').datagrid('options').queryParams;  
        queryParams.cityID =id;
        queryParams.provinceID=-1;
        $('#tbDistrict').datagrid('options').url=root + "/admin/city/ListDistrict.do";
        $('#tbDistrict').datagrid('options').queryParams=queryParams;        
        $("#tbDistrict").datagrid('reload'); 
	}
	
	function getSelections(){
	    var ss = [];
	    var rows = $('#tt').datagrid('getSelections');
	    for(var i=0; i<rows.length; i++){
	        var row = rows[i];
	        ss.push(row.id);
	    }
	    return ss;
	}
	
	function formatPrice(val,row){
	      if (val==0){
	          return '可用';
	      } else {
	          return '不可用';
	      }
 	 }
	  
	 function formatOp(val,row){
		  return '<a href="#" mce_href="#" onclick="view(\''+ row.id + '\')">'+val+'</a> ';  
 	 }
	 
	 function formatOper(val,row,index){
			return '<a href="#" onclick="viewItemList('+row.id+')">子项配置</a>';
	}
	 
	 var url;
     function newUser(){
    	  url = root+'/admin/city/Add.do';
    	  showWindow("数据字典新增",url,500,300,doSearch);
      }
     
     function view(id){
   	  url = root+'/admin/city/Add.do?id='+id;
   	  showWindow("数据字典编辑",url,500,300,doSearch);
     }
     
     function viewItemList(id){
      	  url = root+'/admin/systemconfig/ItemList.do?configId='+id;
      	  showWindow("数据字典值维护",url,650,450,doSearch);
        }
      
      function editUser(){
          var row = $('#dg').datagrid('getSelected');
          if (row){
              $('#dlg').dialog('open').dialog('setTitle','Edit User');
              $('#fm').form('load',row);
              url =  root+'/admin/city/Update.do?id='+row.id;
          }
      }
      
	  
	  function del(){
		  var ids=getSelections();
		  if(ids.length==0){
			  	alertMsg('请选择一行进行删除！');
		    	return;
		  }
		  alert(ids);
		  $.messager.confirm('提示', "是否确认删除？", function(r){
				if(r){
					$.ajax({
						   type: "POST",
						   url: root+"/admin/city/Delete.do",
						   data: "ids="+ids.join(','),
						   success: function(msg){
						     doSearch("");
						   },
						   error: function (XMLHttpRequest, textStatus, errorThrown) {
							    // 通常 textStatus 和 errorThrown 之中
							    // 只有一个会包含信息
							    this; // 调用本次AJAX请求时传递的options参数
							    $.messager.alert('抱歉',errorThrown,'warning');
							}
					});
				}
			});
	  }
</script>
</html>