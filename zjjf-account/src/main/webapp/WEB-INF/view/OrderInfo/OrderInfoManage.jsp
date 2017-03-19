<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>订单信息管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="../common/header.jsp"></jsp:include>
<script type="text/javascript">
function publicFormatAlink_operate(value, row){
	return '<a href="#" onclick="getDrawalList(\'' + value + '\')">查看评分</a>&nbsp;&nbsp;&nbsp;&nbsp;' +  '<a href="#" onclick="getOperateList(\'' + value + '\')">查看操作记录</a>';;
}

function  getDrawalList(id){
	if (id){
		$('#drawalListdlg').dialog('open').dialog('setTitle','订单评分记录');
		 $('#drawalListdlgtb').datagrid({
			 url:'<c:url value="/admin/orderInfoctl/getDrawalList.do"/>',
			 queryParams:{id:id}
		 });
	}
}
function  getOperateList(id){
	if (id){
		$('#operateListdlg').dialog('open').dialog('setTitle','订单操作记录');
		 $('#operateListdlgtb').datagrid({
			 url:'<c:url value="/admin/orderInfoctl/getOperateList.do"/>',
			 queryParams:{id:id}
		 });
	}
}
function publicFormatAlink_Doctor(value, row){
	return '<a href="#" onclick="getDoctorInfo(\'' + value + '\')">查看医生</a>';
}
function  getDoctorInfo(id){
	if (id){
		 $("#doctorMgDetailtb").tabs("select",0);
		 $('#doctorMgExtenddg').datagrid({
			 url:'<c:url value="/admin/doctorctl/List.do"/>',
			 queryParams:{ id:id}
		});
	}
}

function publicFormatAlink_Patient(value, row){
	return '<a href="#" onclick="getPtientInfo(\'' + value + '\')">查看患者</a>';
}
function  getPtientInfo(id){
	if (id){
		 $("#doctorMgDetailtb").tabs("select",1);
		 $('#doctorMgUserdg').datagrid({
			 url:'<c:url value="/admin/patientctl/List.do"/>',
			 queryParams:{ id:id}
		});
	}
}

function publicFormatAlink_MedicalCase(value, row){
	return '<a href="#" onclick="getMedicalCaseInfo(\'' + value + '\')">查看病例</a>';
}
function  getMedicalCaseInfo(id){
	if (id){
		$.post('<c:url value="/admin/orderInfoctl/getMedicalCaseInfo.do"/>',{id:id},function(result){
			if (result.success){
				$('#medicalInfodlgfm').form('clear');
				$('#medicalInfodlg').dialog('open').dialog('setTitle','订单关联患者病例信息');
				$('#medicalInfodlgfm').form('load',result.message);
			} else {
				$.messager.show({ // show error message
					title: 'Error',
					msg: result.message
				});
			}
		},'json');
	}
}

$(function(){
	var mibocrud = MiBoCRUD.creatNew({
		modelName:"orderInfoMg",
		dialogTitle:'订单信息管理',
		listURL:'<c:url value="/admin/orderInfoctl/List.do"/>',
		creatURL:'<c:url value="/admin/orderInfoctl/Add.do"/>',
		updateURL:'<c:url value="/admin/orderInfoctl/Update.do"/>',
		deleteURL:'<c:url value="/admin/orderInfoctl/Delete.do"/>',
		findListFun:function(){
			$("#orderInfoMgdg" ).datagrid('load',{
				orderNo:$("#orderInfoMgField1").textbox('getValue'),
				doctorName:$("#doctorName").textbox('getValue'),
				patientName:$("#patientName").textbox('getValue'),
				price:$("#orderInfoMgField2").textbox('getValue'),
				state:$("#state").combobox('getValue'),
				isDelete:$("#orderInfoMgIsDelete").combobox('getValue')
			})
		}
	}).init();
	$.ajaxSetup({cache:false});
	
	$(".patient-info").on("click",function(){
		alert("dd");
	});
});
</script>
</head>
<body>
<div class="easyui-layout"  fit="true"><!-- bord div -->
<div region="center" ><!-- ceter div -->
 <table id="orderInfoMgdg" 
	idField="id" sortName="createTime" sortOrder="desc"  pageSize =50
	toolbar="#orderInfoMgtoolbar" pagination="true" fitColumns="true"  fit="true"
	data-options="rownumbers:true,singleSelect:true,method:'post'">
    <thead>
        <tr>
        <th field="orderNo" sortable="true" editor="text" width="120">订单编号</th>
        <th field="doctorName" sortable="true" editor="text" width="80">医生姓名</th>
        <th field="doctorId"  sortable="true" editor="text" formatter="publicFormatAlink_Doctor" width="60">医生</th>
        <th field="serviceType"  sortable="true" editor="text" hidden="true" width="60">医生服务类型</th>
        <th field="lable" sortable="true" editor="text" width="60">服务项目</th>
        <th field="price" sortable="true" editor="text" width="60">价格</th>
        <th field="patientName" sortable="true" editor="text" width="80">患者姓名</th>
        <th field="patientId"  sortable="true" editor="text" formatter="publicFormatAlink_Patient" width="60">患者</th>
        <th field="medicalCaseId"  sortable="true" editor="text" formatter="publicFormatAlink_MedicalCase" width="60">患者病例</th>
        <th field="hopeTime"  sortable="true" editor="text" width="120">患者期望时间</th>
        <th field="sureTime"  sortable="true" editor="text" width="120">客服确定时间</th>
        <th field="telNo"  sortable="true" editor="text" width="100">患者号码</th>
        <th field="createTime" sortable="true" editor="text" width="120">创建时间</th>
      	<th field="state" sortable="true" editor="text" formatter="pulbicFormatmiboOrderInfoState" width="60">状态</th>
        <th field="isDelete" sortable="true"  editor="text"  formatter="publicFormatDel" width="50">是否删除</th>
        <th field="id"  sortable="true" editor="text"  formatter="publicFormatAlink_operate" width="160">查看订单相关记录</th>
        </tr>
    </thead>
</table>
<div id="orderInfoMgtoolbar">
   	订单号：<input id="orderInfoMgField1"class="easyui-textbox" style="width:140px;">
   	医生姓名：<input id="doctorName"class="easyui-textbox" style="width:140px;">
   	患者姓名：<input id="patientName"class="easyui-textbox" style="width:140px;">
	价格: <input id="orderInfoMgField2"  class="easyui-numberbox"   precision="2"  maxlength="16"   style="width:140px;">
	订单状态：
	<select id="state" class="easyui-combobox" panelHeight="auto" style="width:100px" >
		<option value="">全部</option>
        <option value="0">未支付</option>
        <option value="1">支付完成</option>
        <option value="2">资料已提交</option>
        <option value="3">申请退款</option>
        <option value="4">已确认</option>
        <option value="5">已完结</option>
        <option value="6">已取消</option>
    </select>
   	是否删除: 
    <select id="orderInfoMgIsDelete" class="easyui-combobox" panelHeight="auto" style="width:100px" >
        <option value="false" selected="selected">否</option>
        <option value="true">是</option>
        <option value="" >全部</option>
    </select>
    <a id="orderInfoMgtoolbar-findlist"  class="easyui-linkbutton" iconCls="icon-search" plain="true"  >查询</a>
</div>
</div><!-- ceter div -->
<div region="south"  style="height:120px;background: #eee; overflow-y:hidden" >
    <div id="doctorMgDetailtb" class="easyui-tabs"  fit="true" border="false" >
   		<!-- 医生扩展信息面板 -->
		<div title="医生信息"  >
			<table id="doctorMgExtenddg"  fitColumns="false"  fit="true"  class="easyui-datagrid" data-options="rownumbers:true,singleSelect:true,method:'post'">
    			<thead><tr>
			        <th field="loginName" sortable="true" editor="text" width="100">登录名称</th>
			        <th field="hName" sortable="true" editor="text" width="100">所属医院</th>
			        <th field="kName"  sortable="true" editor="text" width="100">所属科室</th>
			        <th field="settledDate"  sortable="true" editor="text" width="130">入驻时间</th>
			        <th field="type"  sortable="true" editor="text"  formatter="publicFormatDoctorType" width="100">医生类型</th>
			        <th field="professional"  sortable="true" editor="combobox"  formatter="publicFormatDoctorPType" width="100">医生职称</th>
			        <th field="name"  sortable="true" editor="text" width="100">医生名称</th>
			        <th field="telNo" sortable="true" editor="text" width="100">联系电话</th>
			        <th field="rank" sortable="true" editor="text" width="50">职称</th>
			        <th field="summary" sortable="true" editor="text" width="200">医生描述</th>
			        <th field="motto" sortable="true" editor="text" width="200">医生座右铭</th>
			        <th field="introduction" sortable="true" editor="text" width="200">专业技能</th>
			        <th field="qualificationCard" sortable="true" editor="text" width="120">资历证书号</th>
			        <th field="idCard" sortable="true" editor="text" width="120">身份证号</th>
			        <th field="sex" sortable="true" editor="text"  formatter="publicFormatSex" width="50">性别</th>
			        <th field="birthday" sortable="true" editor="text"  formatter="publicFormatDate" width="100">生日</th>
			        <th field="createTime" sortable="true" editor="text" width="130">创建时间</th>
			        <th field="creatorName" sortable="true" editor="text" width="100">创建人</th>
       			</tr></thead>
       		</table>
		</div>
		<!-- 关联用户信息面板 -->
		<div title="患者信息" >
			<table id="doctorMgUserdg"  fitColumns="true"  fit="true"  class="easyui-datagrid"   data-options="rownumbers:true,singleSelect:true,method:'post'">
    			<thead><tr>
			        <th field="loginName" sortable="true" editor="text" width="100">登录名称</th>
			        <th field="name"  sortable="true" editor="text" width="100">患者名称</th>
			        <th field="nickname" sortable="true" editor="text" width="100">患者昵称</th>
			        <th field="sex" sortable="true" editor="text" formatter="publicFormatSex" width="100">患者性别</th>
			        <th field="birthday" sortable="true" editor="text"  formatter="publicFormatDate" width="100">患者生日</th>
			        <th field="idCard" sortable="true" editor="text" width="100">身份证号码</th>
			        <th field="marriage" sortable="true" editor="text" formatter="publicFormatMarry" width="100">婚姻状况</th>
			        <th field="createTime" sortable="true" editor="text" width="100">创建时间</th>
			        <th field="createUserName" sortable="true" editor="text" width="100">创建用户</th>
			        <th field="isDelete" sortable="true"  editor="text"  formatter="publicFormatDel" width="100">是否删除</th>
       			</tr></thead>
       		</table>
		</div>
		
	</div>
</div><!-- south div -->
</div><!-- bord div -->

<!-- -------------------------------------------患者信息------------------------------------------ 
<div  id="patientInfodlg" class="easyui-dialog"  modal="true" style="min-width:420px;min-height:400px;padding:20px;" closed="true"  >
    <div class="ftitle">患者信息</div>
    <form id="patientInfodlgfm" method="post" novalidate>
    <div class="fitem"><label>用户Id</label><input name="id" class="easyui-textbox"  readonly="true"></div>
    <div class="fitem"><label>用户名称:</label><input name="userName" class="easyui-textbox" readonly="true"></div>
    <div class="fitem"><label>患者头像:</label><input name="face" class="easyui-textbox"  readonly="true"></div>
    <div class="fitem"><label>患者名称:</label><input name="name" class="easyui-textbox"   readonly="true"></div>
    <div class="fitem"><label>患者昵称:</label><input name="nickname" class="easyui-textbox"   readonly="true"></div>
    <div class="fitem">
    	<label>患者性别:</label>
    	<select name="sex" class="easyui-combobox" style="width:158px;" panelHeight="auto"  readonly="true">
    		<option value="0">男</option>
    		<option value="1">女</option>
    	</select>
    </div>
    <div class="fitem"><label>患者生日:</label><input name="birthday" class="easyui-datebox"   readonly="true"></div>
    <div class="fitem"><label>身份证号码:</label><input name="idCard" class="easyui-textbox"   readonly="true"></div>
    <div class="fitem">
    	<label>婚姻状况:</label>
    	<select name="marriage" class="easyui-combobox" style="width:158px;" panelHeight="auto"  readonly="true">
    		 <option value="0">未婚</option>
    		 <option value="1">已婚</option>
    		 <option value="2">保密</option>
    	</select>
    </div>
    </form>
</div>
-->
<!-- -----------------------------------------医生信息-------------------------------------------- 
<div  id="doctorInfodlg" class="easyui-dialog"  modal="true" style="width:600px;min-height:400px;padding:20px;" closed="true"  >
    <div class="ftitle">医生信息</div>
    <form id="doctorInfodlgfm" method="post" novalidate>
    <div class="fitem"><label>头像:</label><input name="face" class="easyui-textbox"   readonly="true">
     						 	  <label>登录名称:</label><input name="userId" class="easyui-textbox"   readonly="true"></div>
    <div class="fitem"><label>所属医院:</label><input name="hospitalId" class="easyui-textbox"     readonly="true">
  								<label>所属科室:</label><input name="hospitalOfficeId" class="easyui-textbox"   readonly="true" ></div>
    <div class="fitem"><label>入住时间:</label><input name="settledDate" class="easyui-datetimebox"  readonly="true">
 
    	<label>医生类型:</label>
    	<select name="type" class="easyui-combobox" style="width:158px;" panelHeight="auto"  readonly="true">
    		<option value="1">签约医生</option>
    		<option value="2" selected>普通医生</option>
    		<option value="3">学生助手</option>
    	</select>
    </div>
    <div class="fitem"><label>医生名称:</label><input name="name" class="easyui-textbox"  readonly="true">
    							  <label>联系电话:</label><input name="telNo" class="easyui-textbox"   readonly="true"></div>
    <div class="fitem"><label>职称:</label><input name="rank" class="easyui-textbox"   readonly="true"></div>
    <div class="fitem"><label>医生描述:</label><textarea  name="summary" class="textbox"  style="height:40px;width:420px"  readonly="true"></textarea></div>
    <div class="fitem"><label>医生座右铭:</label><input name="motto" class="easyui-textbox"  style="width:420px"  readonly="true"></div>
    <div class="fitem"><label>专业技能:</label><textarea name="introduction" class="textbox"  style="height:40px;width:420px"  readonly="true"></textarea></div>
    <div class="fitem"><label>资历证书号:</label><input name="qualificationCard" class="easyui-textbox"   readonly="true">
    							  <label>身份证号:</label><input name="idCard" class="easyui-textbox"  readonly="true"></div>
    <div class="fitem">
    	<label>性别:</label>
    	<select name="sex" class="easyui-combobox" style="width:158px;" panelHeight="auto"  readonly="true">
    		<option value="0" selected>男</option>
    		<option value="1">女</option>
    	</select>
    <label>生日:</label><input name="birthday" class="easyui-datebox"   readonly="true"></div>
    <div class="fitem"><label>创建用户:</label><input name="creatorId" class="easyui-textbox"   readonly="true">
     							  <label>创建时间:</label><input name="createTime" class="easyui-datetimebox"   readonly="true"></div>
    </form>
</div>
-->
<!-- ------------------------------------------病例信息------------------------------------------- -->
<div  id="medicalInfodlg" class="easyui-dialog"  modal="true" style="min-width:420px;min-height:400px;padding:20px;" closed="true"  >
  <div class="ftitle">医生信息</div>
  <form id="medicalInfodlgfm" method="post" novalidate>
	<div class="fitem"><label>疾病名称:</label><input name="disease" class="easyui-textbox"  readonly="true"></div>
    <div class="fitem"><label>病情描述:</label><input name="description" class="easyui-textbox" readonly="true"></div>
    <div class="fitem"><label>历史:</label><input name="history" class="easyui-textbox"  readonly="true"></div>
    <div class="fitem"><label>帮助:</label><input name="help" class="easyui-textbox"   readonly="true"></div>
     </form>
</div>

<!-- ------------------------------------------评分信息列表------------------------------------------- -->
<div  id="drawalListdlg" class="easyui-dialog"  modal="true" style="width:80%;height:400px;" closed="true"  >
 	<table id="drawalListdlgtb"  idField="id"  sortName="id"  sortOrder="asc"  fitColumns="true"  fit="true" class="easyui-datagrid" buttons="#orderInfoMgdlgbt"
		data-options="rownumbers:true,singleSelect:true,method:'post'">
    <thead>
        <tr>
        <th field="id"  sortable="true" editor="text" width="100">评分ID</th>
        <th field="orderId"  sortable="true" editor="text" width="100">订单ID</th>
        <th field="patientId"  sortable="true" editor="text" width="100">患者ID</th>
        <th field="doctorId"  sortable="true" editor="text" width="100">医生ID</th>
        <th field="item"  sortable="true" editor="text" width="100">评分项目</th>
        <th field="score"  sortable="true" editor="text" width="100">分数</th>
        <th field="createTime"  sortable="true" editor="text" width="100">创建时间</th>
        <th field="isDelete"  sortable="true" editor="text" formatter="publicFormatDel" width="100">记录是否删除</th>
        </tr>
    </thead>
</table>
</div>
<!-- ------------------------------------------操作信息列表------------------------------------------- -->
<div  id="operateListdlg" class="easyui-dialog"  modal="true" style="width:80%;height:400px;" closed="true"  >
 	<table id="operateListdlgtb"  idField="id"  sortName="id"  sortOrder="asc"  fitColumns="true"  fit="true" 
		data-options="rownumbers:true,singleSelect:true,method:'post'">
    <thead>
        <tr>
        <th field="id"  sortable="true" editor="text" width="100">操作ID</th>
        <th field="orderId"  sortable="true" editor="text" width="100">变更订单的id</th>
        <th field="orderNo"  sortable="true" editor="text" width="100">订单的编号</th>
        <th field="oldSureTime"  sortable="true" editor="text" width="100">变更订单的原先确定时间</th>
        <th field="newSureTime"  sortable="true" editor="text" width="100">变更订单的重新确定时间</th>
        <th field="userId"  sortable="true" editor="text" width="100">客服id</th>
        <th field="createTime"  sortable="true" editor="text" width="100">创建时间</th>
        <th field="isDelete"  sortable="true" editor="text" formatter="publicFormatDel" width="100">记录是否删除</th>
        <th field="remark"  sortable="true" editor="text" width="100">备注信息</th>
        </tr>
    </thead>
</table>
</div>


</body>
</html>
