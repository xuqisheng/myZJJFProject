<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>医院列表</title>
   	<jsp:include page="../common/header.jsp"></jsp:include>
</head>
<body class="easyui-layout">
	<div region="center" style="padding:0px;" border="false" header="false">  
	    <table border="false" fit="true" id="tt" class="easyui-datagrid" url="${root}/admin/hospital/HospitalList.do" 
	    		iconCls="icon-save" sortName="createTime" sortOrder="desc" singleSelect="true"
	            rownumbers="true" idField="id" fitColumns="true" pagination="true" pageSize="20"
	            data-options="rownumbers:true,method:'post',toolbar:'#tb'">
	        <thead>
	            <tr>
	            	<th data-options="field:'ck',checkbox:true"></th>
	                <th field="id" width="200" sortable="true" hidden="true">医院id</th>
	                <th field="name" width="200" sortable="true" formatter="formatOp">医院名称</th>
	                <th field="provinceName" width="100">所属省份</th>
	                <th field="cityName" width="100">所属城市</th>
	                <th field="districtName" width="100">所属县区</th>
	                <th field="rank" width="100" sortable="true">等级</th>
	                <th field="orderIndex" width="100">排序</th>
	                <th field="creatorName" width="100" align="center">创建人</th>
	                <th field="createTime" width="120" align="center">创建时间</th>
	                <th field="isDelete" width="80" formatter="formatIsDelete">是否删除</th>
	            </tr>
	        </thead>
	    </table>
	    <div id="tb" style="padding:2px 5px;">
	    	<div style="margin-top: 2px;">
	    		<a href="javascript: addHospital();" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>    
         		<a href="javascript: delHospital();" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
         		省份:<input id="provinceName"class="easyui-textbox" style="width:140px;"/>
         		城市:<input id="cityName"class="easyui-textbox" style="width:140px;"/>
         		县区:<input id="districtName"class="easyui-textbox" style="width:140px;"/>
         		名称:<input id="name"class="easyui-textbox" style="width:140px;"/>
	    		等级:<input id="rank"class="easyui-textbox" style="width:140px;"/>
	    		删除:
	    		<select id="isDelete" class="easyui-combobox" panelHeight="auto" style="width:100px">
		           	<option value="0">否</option>
		           	<option value="1">是</option>
		           	<option value="-1">全部</option>
		       	</select>
		       	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" 
		       		plain="true" onclick="javascript:hospitalFind()">查询</a>
	       </div>
	    </div>
    </div>
</body>
<script type="text/javascript">
	//格式化是否在用
	function formatIsDelete(val, row){
		if(val == 0){
			return "否";
		}else if(val == 1){
			return "是";
		}else{
			return "其它";
		}
	}
	
	// 医院搜索
	function hospitalFind(){
		$('#tt').edatagrid('reload',{
			provinceName: $("#provinceName").textbox('getValue'),
			cityName: $("#cityName").textbox('getValue'),
			districtName: $("#districtName").textbox('getValue'),
			name: $("#name").textbox('getValue'),
			rank: $("#rank").textbox('getValue'),
			isDelete: $("#isDelete").combobox('getValue')
		});
	}
	
	// 编辑医院链接
	function formatOp(val, row){
		return '<a href="#" mce_href="#" onclick="editHospital(\''+ row.id + '\')">'+val+'</a>';
	}
	
	// 编辑医院
	function editHospital(id){
		var url = root+'/admin/hospital/EditPage.do?id='+id;
	   	showWindow("医院编辑", url, 400, 320, hospitalFind);
	}
	
	// 新增医院
	function addHospital(){
		var url = root+'/admin/hospital/EditPage.do';
	   	showWindow("医院编辑", url, 400, 320, hospitalFind);
	}

	// 删除医院
	function delHospital(){
		var ids=getSelections();
		if(ids.length==0){
		  	alertMsg('请选择一行进行删除！');
		   	return;
		 }
		 $.messager.confirm('提示', "是否确认删除？", function(r){
			if(r){
				$.ajax({
					type: "POST",
					url: root+"/admin/hospital/Delete.do",
					data: "ids="+ids.join(','),
					success: function(msg){
						hospitalFind();
					},
					error: function (XMLHttpRequest, textStatus, errorThrown) {
					 	// 通常 textStatus 和 errorThrown 之中
					 	// 只有一个会包含信息
					 	this; // 调用本次AJAX请求时传递的options参数
					 	$.messager.alert('抱歉', errorThrown, 'warning');
					}
				});
			}
		});
	}
	
	// 获取选中项
	function getSelections(){
	    var ss = [];
	    var rows = $('#tt').datagrid('getSelections');
	    for(var i=0; i<rows.length; i++){
	        var row = rows[i];
	        ss.push(row.id);
	    }
	    return ss;
	}	
</script>
</html>