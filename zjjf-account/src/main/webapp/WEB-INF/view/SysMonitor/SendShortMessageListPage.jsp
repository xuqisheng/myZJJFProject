<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>短信发送记录列表</title>
   	<jsp:include page="../common/header.jsp"></jsp:include>
</head>
<body class="easyui-layout">
	<div region="center" style="padding:0px;" border="false" header="false">  
	    <table border="false" fit="true" id="tt" class="easyui-datagrid" 
	    		url="${root}/admin/sysmonitorctl/SendShortMessageList.do" 
	    		iconCls="icon-save" sortName="msgSendTime" sortOrder="desc" singleSelect="true"
	            rownumbers="true" idField="id" fitColumns="true" pagination="true" pageSize="20"
	            data-options="rownumbers:true,method:'post',toolbar:'#tb'">
	        <thead>
	            <tr>
	            	<th data-options="field:'ck',checkbox:true"></th>
	                <th field="id" width="200" sortable="true" hidden="true">记录id</th>
	                <th field="userName" width="150" sortable="true">用户名</th>
	                <th field="sendTelnumber" width="120" sortable="true">发送号码</th>
	              	<th field="msgSendTime" width="200" sortable="true">发送时间</th>
	                <th field="msgVerifyCode" width="100" sortable="true">验证码</th>
	                <th field="msgType" width="100" sortable="true" formatter="formatMsgType">短信发送类型</th>
	                <th field="revTelnumber" width="120" sortable="true">接收号码</th>
	                <th field="status" width="120" sortable="true" formatter="formatStatus">发送状态</th>
	            </tr>
	        </thead>
	    </table>
	    <div id="tb" style="padding:2px 5px;">
	    	<div style="margin-top: 2px;">
	    		用户名:<input id="userName"class="easyui-textbox" style="width:140px;">
	    		发送号码：<input id="sendTelnumber"class="easyui-textbox" style="width:140px;">
	    		短信类型: 
		        <select id="msgType" class="easyui-combobox" panelHeight="auto" style="width:100px">
		            <option value="">全部</option>
		            <option value="0">用户注册</option>
		            <option value="1">找回密码</option>
		        </select>
		       	接收号码：<input id="revTelnumber"class="easyui-textbox" style="width:140px;">
		       	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" 
		       		plain="true" onclick="javascript: msgFind()">查询</a>
	       </div>
	    </div>
    </div>
</body>
<script type="text/javascript">

	// 短信发送记录搜索
	function msgFind(){
		$('#tt').edatagrid('load',{
			userName: $("#userName").textbox('getValue'),
			sendTelnumber: $("#sendTelnumber").textbox('getValue'),
			msgType: $("#msgType").combobox('getValue'),
			revTelnumber: $("#revTelnumber").textbox('getValue')
		});
	}
	
	// 格式化 短信类型
	function formatMsgType(val, row){
		if(val == 0){
			return "用户注册";
		}else if(val == 1){
			return "找回密码";
		}else{
			return "其它";
		}
	}
	
	// 格式化 发送状态
	function formatStatus(val, row){
		if(val == "success:msgid"){
			return "提交成功";
		}else if(val == "error:msgid"){
			return "提交失败";
		}else if(val == "error:Missing username"){
			return "用户名为空";
		}else if(val == "error:Missing password"){
			return "密码为空";
		}else if(val == "error:Missing apikey"){
			return "APIKEY为空";
		}else if(val == "error:Missing recipient"){
			return "手机号码为空";
		}else if(val == "error:Missing message content"){
			return "短信内容为空";
		}else if(val == "error:Account is blocked"){
			return "帐号被禁用";
		}else if(val == "error:Unrecognized encoding"){
			return "编码未能识别";
		}else if(val == "error:APIKEY or password error"){
			return "APIKEY 或密码错误";
		}else if(val == "error:Unauthorized IP address"){
			return "未授权 IP 地址";
		}else if(val == "error:Account balance is insufficient"){
			return "余额不足";
		}else if(val == "error:Black keywords is:党中央"){
			return "屏蔽词";
		}else{
			return "其它";
		}
	}
</script>
</html>