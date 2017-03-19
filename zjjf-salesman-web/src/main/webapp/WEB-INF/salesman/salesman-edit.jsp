<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>阿街足迹</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <%@ include file="../common/head.jsp"%>
    <script src="${root}/resources/js/comm.js"></script>
    <script>
    	var rootPath="${root}";
    </script>
</head>
<body class="wrap-bd">
<div class="title mb-default" id="title">编辑用户</div>
<div class="wrap-bd" style="background: #fff;">
	<div>
	  <form id="editSalesmanForm">
	  	<input type="hidden" name="id" id="userId" value="${salesman.id}"/>
	  	<input type="hidden" name="token" value="${salesman.token}"/>
	  	<input type="hidden" name="oldDeptId" id="oldDeptId" value="${salesman.deptId}"/>
	  	<input type="hidden" name="oldIsDelete" value="${salesman.isDelete}"/>
	  	<input type="hidden" name="oldUserType" value="${salesman.userType}"/>
		<input type="hidden" name="oldMobile" value="${salesman.mobile}"/>
	  	<input type="hidden" name="oldUserName" value="${salesman.userName}"/>
	  	<input type="hidden" name="oldGender" value="${salesman.gender}"/>
	  	<input type="hidden" name="oldPostType" value="${salesman.postType}"/>
		<b>基本信息</b>
        <p>
            <label class="label" id="mobileLabel">账号 <span style="color: red;">*</span>：</label>
            <input type="text" value="${salesman.mobile}" id="mobile" name="mobile" class="input-search-text">
        </p>
        <p>
            <label class="label" id="userNameLabel">姓名<span style="color: red;">*</span> ：</label>
            <input type="text" value="${salesman.userName}" id="userName" name="userName" class="input-search-text">
        </p>
        <p>
            <label class="label" id="genderLabel">性别<span style="color: red;">*</span>：</label>
			<input type="radio" value="1" name="gender" <c:if test="${salesman.gender eq 1}">checked="checked"</c:if>>男
            <input type="radio" value="2" name="gender" <c:if test="${salesman.gender eq 2}">checked="checked"</c:if>>女
        </p>
        <p>
            <label class="label" id="deptLabel">部门<span style="color: red;">*</span>：</label>
            <select id="deptSelect" name="deptId" style="width:209px;">
                <option value="">请选择</option>
                <c:forEach var="deptList" items="${deptList}">
              	  <option value="${deptList.deptId}" <c:if test="${deptList.deptId eq salesman.deptId}">selected="selected"</c:if> >${deptList.deptName}</option>
                </c:forEach>
            </select>
        </p>
        <p>
            <label class="label" id="postLabel">岗位<span style="color: red;">*</span>：</label>
            <select id="postSelect" name="postType" style="width:209px;">
            	<option value="">请选择</option>
            	<c:forEach var="dictList" items="${dictList}">
            	  <option value="${dictList.value}" <c:if test="${salesman.postType eq dictList.value}">selected="selected"</c:if>>${dictList.label}</option>
                </c:forEach>
            </select>
        </p>
        <p>
            <label class="label">Email：</label>
            <input type="text" id="email" name="email" value="${salesman.email}" class="input-search-text">
            
        </p>
        <p>
            <label class="label">密码：</label>
            <input type="text" id="password" name="password" value="" class="input-search-text">
        </p>
        <p>
            <label class="label">账号状态<span style="color: red;">*</span>：</label>
			<input type="radio" value="0" name="isDelete" <c:if test="${salesman.isDelete==0}">checked="checked"</c:if>>正常
            <input type="radio" value="1" name="isDelete" <c:if test="${salesman.isDelete==1}">checked="checked"</c:if>>停用
        </p>
        <p>
            <label class="label">角色：</label>
            <input type="radio" value="0" name="userType" <c:if test="${salesman.userType==0}">checked="checked"</c:if>>员工角色
            <input type="radio" value="1" name="userType" <c:if test="${salesman.userType==1}">checked="checked"</c:if>>管理员角色
        </p>

	   </form>  		
	</div>
	<div id="submitDiv">
		<button class="button-save" id="submit">确定</button>
		<button class="button-cancel ml-default" id="cancelButton">取消</button>
	</div>
</div>
<script>
$('#submit').on('click',function() {
	var deptSelect = $("#deptSelect").val();
	var postSelect = $("#postSelect").val();
	var userName = $("#userName").val();
	var mobile = $("#mobile").val();
	var password = $("#password").val();
	var email = $("#email").val();
	var userId = $("#userId").val();
	var oldDeptId = $("#oldDeptId").val();

	if(mobile =="" || mobile == null){
		alert("注册账号不能为空!");
		return;
	}
	if(userName =="" ||userName == null){
		alert("用户名称不能为空!");
		return;
	}
	if(userName.length>15){
		alert("用户名称长度不能超过15个字符！");
	    return;
	}
	if(password != null && password!=""){
		if(password.length<6){
		    alert("密码至少大于等于6位");
		    return;
		}
		var regex = /^[0-9A-Za-z_!@#$%^&*]+$/
		if(!regex.test(password)){
			alert("密码只能由字母、数字、下划线及@#$%^&*等组成\n！"); 
			return;
		}
	}
	
	if(deptSelect =="" ||deptSelect == null){
		alert("部门不能为空!");
		return;
	}
	if(postSelect=="" || postSelect == null){
		alert("岗位不能为空!");
		return;
	}
	
	if(mobile.length>11){
		alert( "注册账号为手机号,长度不能超过11位数！" );
	    return ;
	}
	
	var boolMobile = checkMobile(mobile);
	if(!boolMobile){
		alert( "注册账号为手机号！" );
       return false;
    }
	
	if(email!="" && email != null){
		var boolEmail = checkEmail(email);
		if(!boolEmail){
	    	alert( "您输入的电子邮件地址不合法" );
	        return false;
	    }
	}
	
	if(userId=="" || userId == null){
		alert("用户ID不能为空!");
		return;
	}
	
	//当部门切换的时候需要检查当前用户是否为其他部门的管理者
	if(deptSelect != oldDeptId){
		var boolCheckDept = checkDeptRelation(userId);
		if(!boolCheckDept){
			return;
		}
	}

	 $.ajax({
			type: "post",
			url: "${root}/account/updateSalesman.do",
			data: $('#editSalesmanForm').serialize(),
			dataType: "json",
			//async: false,//当async:true时是异步请求
			success: function(data) {
			 if(data.success) {
					alert(data.message);
					location.href="${root}/account/index.do";
				} else {
					alert(data.message);
				} 
			},
			error : function(data) {
			}
		});
   
});

function checkDeptRelation(userId){
	var bool = true;
	 $.ajax({
			type: "post",
			url: "${root}/account/checkDeptRelation.do",
			data: {"userId":userId},
			dataType: "json",
			async: false,//当async:true时是异步请求
			success: function(obj) {
				//debugger;
			    if(obj.success) {
			    	var data = obj.data;
					if(data!=null && data.trim()!=""){
						bool = false;
						alert("当前用户是【"+data+"】的管理者，切换部门必须先解除原部门管理者的关系！");
					}
				} else {
					alert(obj.message);
					bool = false;
				} 
			}
		});
	 return bool;
}

$('#cancelButton').on('click',function() {
	location.href="${root}/account/index.do";
});

//验证电子邮件地址是否合法
function checkEmail(email_address)
{
    var regex = /^([0-9A-Za-z\-_\.]+)@([0-9a-z]+\.[a-z]{2,3}(\.[a-z]{2})?)$/g;
    if ( regex.test(email_address))
    {
        return true;
    }
    else
    {
//         window.alert( "您输入的电子邮件地址不合法" );
        return false;
    }
}
//验证手机号是否正确
function checkMobile(mobile)
{
	var regex = /^(13[0-9]|14[0-9]|15[0-9]|16[0-9]|17[0-9]|18[0-9])\d{8}$/;
	//var regex = /^1((3\d)|(4[57])|(5[01256789])|(7\d)|(8\d))\d{8}$/;
    if (regex.test(mobile))
    {
//         window.alert(mobile);
        return true;
    }
    else
    {
        //window.alert( "您输入的手机号不合法" );
        return false;
    }
}
var sTime = new Date().getTime();
$(document).ready(function(){
	//解决浏览器cookie帐号名称密码回填的问题
	$('#password').mouseenter(function(){
        $("#password").attr("type","password");
    });
});


  

</script>
</body>
</html>