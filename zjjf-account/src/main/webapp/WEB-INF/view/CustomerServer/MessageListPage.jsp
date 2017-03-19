<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>消息推送记录列表</title>
   	<jsp:include page="../common/header.jsp"></jsp:include>
</head>
<body class="easyui-layout">
	<div region="center" style="padding:0px;" border="false" header="false">  
	    <table border="false" fit="true" id="tt" class="easyui-datagrid" 
	    		url="${root}/admin/sysmonitorctl/MyPushMessageList.do" 
	    		iconCls="icon-save" sortName="createTime" sortOrder="desc" singleSelect="true"
	            rownumbers="true" idField="id" fitColumns="true" pagination="true" pageSize="20"
	            data-options="rownumbers:true,method:'post',toolbar:'#tb'">
	        <thead>
	            <tr>
	            	<th data-options="field:'ck',checkbox:true"></th>
	                <th field="id" width="60" sortable="true" hidden="true">记录id</th>
	                <th field="publishName" width="60" sortable="true">发送人</th>
	                <th field="publishPhoneNum" width="60" sortable="true">发送人号码</th>
	                <th field="msgTypeStr" width="80" sortable="true">消息类型</th> <!-- formatter="formatMsgType" -->
	                <th field="data" width="200" sortable="true" formatter="formatData">内容</th>
	                <th field="dataType" width="50" sortable="true" formatter="formatDataType">数据类型</th>
	                <th field="state" width="50" sortable="true" formatter="formatState">信息状态</th>
	                <th field="createTime" width="100" sortable="true">推送时间</th>
	            </tr>
	        </thead>
	    </table>
	    <div id="tb" style="padding:2px 5px;">
	    	<div style="margin-top: 2px;">
	    		发送人:<input id="publishName"class="easyui-textbox" style="width:140px;">
	    		信息状态: 
		        <select id="state" class="easyui-combobox" panelHeight="auto" style="width:100px">
		            <option value="">全部</option>
		            <option value="0">未读</option>
		            <option value="1">已读</option>
		        </select>
		       	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" 
		       		plain="true" onclick="javascript: pushMsgFind()">查询</a>
	       </div>
	    </div>
    </div>
</body>
<script type="text/javascript">

	// 消息推送记录搜索
	function pushMsgFind(){
		$('#tt').edatagrid('load',{
			publishName: $("#publishName").textbox('getValue'),
			state: $("#state").combobox('getValue')
		});
	}
	
	// 格式化 消息类型
	function formatMsgType(val, row){
		if(val == 1001){
			return "客服处理 预约咨询请求";
		}else if(val == 1002){
			return "客服处理 预约住院支付结束";
		}else if(val == 1003){
			return "";
		}else if(val == 1004){
			return "";
		}else if(val == 1005){
			return "";
		}else if(val == 2001){
			return "图文咨询处理结果发送给用户";
		}else if(val == 2002){
			return "线上随访处理结果发送给用户";
		}else if(val == 2003){
			return "图文咨询处理结果发送给用户";
		}else if(val == 2004){
			return "预约住院处理结果发送给用户";
		}else if(val == 3001){
			return "图文咨询处理结果发送给医生";
		}else if(val == 3002){
			return "线上随访处理结果发送给医生";
		}else if(val == 3003){
			return "图文咨询处理结果发送给医生";
		}else if(val == 3004){
			return "预约住院处理结果发送给医生";
		}else{
			return "其它";
		}
	}
	
	// 格式化 消息内容
	function formatData(val, row){
		return '<a href="#" mce_href="#" onclick="viewData(\'' + row.id + '\')">' + val + '</a>';
	}
	
	// 查看消息内容
	function viewData(id){
		var url = root + "/CustomerServer/ViewMsg.do?id="+id;
		showWindow("信息详情", url, 520, 360, pushMsgFind);
	}
	
	//格式化 数据类型
	function formatDataType(val, row){
		if(val == 0){
			return "文本";
		}else if(val == 1){
			return "数据库id";
		}else if(val == 2){
			return "病例";
		}else if(va == 3){
			return "聊天";
		}else{
			return "其它";
		}
	}
	
	// 格式化 消息状态
	function formatState(val, row){
		if(val == 0){
			return "未读";
		}else if(val == 1){
			return "已读";
		}else{
			return "其它";
		}
	}
</script>
</html>