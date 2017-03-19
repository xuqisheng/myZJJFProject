<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>数据字典配置</title>
   	<jsp:include page="../common/header.jsp"></jsp:include>
</head>
<body class="easyui-layout">
<div region="center" style="padding:0px;" border="false" header="false">  
    <table border="false" fit="true" id="tt" class="easyui-datagrid"  url="${root }/admin/systemconfig/List.do"  iconCls="icon-save"
            sortName="createTime" sortOrder="desc" singleSelect="true"
            rownumbers="true"  idField="id"  fitColumns="true" pagination="true"  pageSize="20"
            data-options="rownumbers:true,method:'post',toolbar:'#tb',queryParams:{state: 0}">
        <thead>
            <tr>
            	<th data-options="field:'ck',checkbox:true"></th>
                <th field="name" width="200"  formatter="formatOp">名称</th>
                <th field="code" width="200" >代码</th>
                <th field="state" width="60"  sortable="true"   align="center" formatter="formatPrice">状态</th>
                <th field="createTime" width="120" align="center" sortable="true"   >创建时间</th>
                <th field="creatorName" width="120" align="center" >创建人</th>
                <th field="remark"  width="260" align="right" sortable="true" >备注</th>
                <th data-options="field:'_operate',width:80,align:'center',formatter:formatOper">操作</th>  
            </tr>
        </thead>
    </table>
    <div id="tb" style="padding:2px 5px;">
    	<div style="margin-top: 2px;">
	       <input id="tbLikeName" class="easyui-searchbox" data-options="prompt:'请输入名称进行查询',searcher:doSearch" style="width:300px"></input>
	       状态: 
	        <select id="state" class="easyui-combobox" panelHeight="auto" style="width:100px" data-options="onSelect:doSearch">
	            <option value="0">可用</option>
	            <option value="1">不可用</option>
	            <option value="-1">全部</option>
	        </select>
       </div>
       <div style="margin-top: 5px;">
         	<a href="javascript:newUser();" class="easyui-linkbutton" iconCls="icon-add" plain="true" ></a>    
         	<a href="javascript:del();" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>    
        </div>
    </div>
    </div>
     
     <div id="dlg"  data-options="modal:true,closed:true,iconCls:'icon-save'" class="easyui-dialog" style="width:500px;height:330px;padding:10px 20px" closed="true" buttons="#dlg-buttons">
     </div>
</body>
<script type="text/javascript">
	function getSelections(){
	    var ss = [];
	    var rows = $('#tt').datagrid('getSelections');
	    for(var i=0; i<rows.length; i++){
	        var row = rows[i];
	        ss.push(row.id);
	    }
	    return ss;
	    //$.messager.alert('Info', ss.join('<br/>'));
	}
	
	//点击表单查询
	function doSearch(asdasd){
		 //查询参数直接添加在queryParams中    
         var queryParams = $('#tt').datagrid('options').queryParams;  
         queryParams.likeName =$("#tbLikeName").val();
         queryParams.state=getComboboxVal("state");
         $('#tt').datagrid('options').queryParams=queryParams;        
         $("#tt").datagrid('reload'); 
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
    	  url = root+'/admin/systemconfig/Add.do';
    	  showWindow("数据字典新增",url,500,300,doSearch);
      }
     
     function view(id){
   	  url = root+'/admin/systemconfig/Add.do?id='+id;
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
              url =  root+'/admin/systemconfig/Update.do?id='+row.id;
          }
      }
      
      function saveUser(){
          $('#fm').form('submit',{
              url: url,
              onSubmit: function(){
                  return $(this).form('validate');
              },
              success: function(result){
                  var result = eval('('+result+')');
                  if (result.errorMsg){
                      $.messager.show({
                          title: 'Error',
                          msg: result.errorMsg
                      });
                  } else {
                      $('#dlg').dialog('close');        // close the dialog
                      $('#dg').datagrid('reload');    // reload the user data
                  }
              }
          });
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
						   url: root+"/admin/systemconfig/Delete.do",
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