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
<div class="title mb-default" id="title">添加/编辑用户</div>
<div class="wrap-bd" style="background: #fff">
	<div>
	  <form id="addSalesmanForm">
		<b>基本信息</b>
        <p>
            <label class="label" id="mobileLabel">账号 <span style="color: red;">*</span>：</label>
            <input type="text" id="mobile" name="mobile" class="input-search-text">
        </p>
        <p>
            <label class="label" id="userNameLabel">姓名<span style="color: red;">*</span> ：</label>
            <input type="text" id="userName" name="userName" class="input-search-text">
        </p>
        <p>
            <label class="label" id="genderLabel">性别<span style="color: red;">*</span>：</label>
			<input type="radio" value="1" name="gender" checked="checked">男
            <input type="radio" value="2" name="gender"  class="ml-default">女
        </p>
        <p>
            <label class="label" id="deptLabel">部门<span style="color: red;">*</span>：</label>
            <select id="deptSelect" name="deptId" style="width:209px;">
                <option value="">请选择</option>
                <c:forEach var="deptList" items="${deptList}">
              	  <option value="${deptList.deptId}">${deptList.deptName}</option>
                </c:forEach>
            </select>
        </p>
        <p>
            <label class="label" id="postLabel">岗位<span style="color: red;">*</span>：</label>
            <select id="postSelect" name="postType" style="width:209px;">
                <option value="">请选择</option>
                <c:forEach var="dictList" items="${dictList}">
              	  <option value="${dictList.value}">${dictList.label}</option>
                </c:forEach>
            </select>
        </p>
        <p>
            <label class="label">Email：</label>
            <input type="text" id="email" name="email" class="input-search-text">
        </p>
        <p>
            <label class="label">密码<span style="color: red;">*</span>：</label>
            <input type="text" id="password" name="password" class="input-search-text">
        </p>
        <p>
            <label class="label">账号状态<span style="color: red;">*</span>：</label>
			<input type="radio" value="0" name="isDelete" checked="checked">正常
            <input type="radio" value="1" name="isDelete">停用
        </p>
        <p>
            <label class="label">角色：</label>
            <input type="radio" value="0" name="userType" checked="checked">员工角色
            <input type="radio" value="1" name="userType">管理员角色
        </p>
	   </form>  		
	</div>
	<div id="submitDiv">
		<button class="button-save" id="submit">确定</button>
		<button class="button-cancel ml-default" id="cancelButton">取消</button>
	</div>
</div>
<%-- <script src="${root}/resources/js/product-add.js"></script> --%>
<script>
$('#submit').on('click',function() {
	var deptSelect = $("#deptSelect").val();
	var postSelect = $("#postSelect").val();
	var userName = $("#userName").val();
	var mobile = $("#mobile").val();
	var password = $("#password").val();
	var email = $("#email").val();
	
	if(mobile =="" || mobile == null){
		alert("注册账号不能为空!");
		return;
	}
	if(userName =="" ||userName == null){
		alert("用户名称不能为空!");
		return;
	}
	if(password=="" || password == null){
		alert("密码不能为空!");
		return;
	}
	if(userName.length>15){
	    alert("用户名称长度不能超过15个字符！");
	    return;
	}
	if(password.length<6){
	    alert("密码至少大于等于6位");
	    return;
	}
	var regex = /^[0-9A-Za-z_!@#$%^&*]+$/
	if(!regex.test(password)){
		alert("密码只能由字母、数字、下划线及@#$%^&*等组成\n！"); 
		return;
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
	/* if(!/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i.test(mobile)){
		alert( "您输入的手机号不合法" );
	    return false;
	} */
    
	 $.ajax({
			type: "post",
			url: "${root}/account/addSalesman.do",
			data: $('#addSalesmanForm').serialize(),
			dataType: "json",
			async: false,
			success: function(data) {
			 if(data.success) {
					alert("添加成功!");
					location.href="${root}/account/index.do";
				} else {
					alert(data.message);
				} 
			},
			error : function(data) {
			}
		});
   
});

$('#cancelButton').on('click',function() {
	location.href="${root}/account/index.do";
});


//弹出一个iframe层
$('#openLeaderList').on('click', function(){
	var url = '${root}/account/toLeaderPage.do';
    layer.open({
        type: 2,
        title: '',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['800px' , '520px'],
        content: '${root}/account/toLeaderPage.do'
    });
});

$(document).ready(function(){
    //解决浏览器cookie帐号名称密码回填的问题
	$('#password').mouseenter(function(){
        $("#password").attr("type","password");
    });
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
        window.alert( "您输入的电子邮件地址不合法" );
        return false;
    }
}
//验证手机号是否正确
function checkMobile(mobile)
{
	var regex = /^(13[0-9]|14[0-9]|15[0-9]|16[0-9]|17[0-9]|18[0-9])\d{8}$/;
	//var regex = /^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/;
// 	if(!/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i.test(mobile))
    if (regex.test(mobile))
    {
//         window.alert(mobile);
        return true;
    }
    else
    {
//         window.alert( "您输入的手机号不合法" );
        return false;
    }
}

function checkPwd(pwd)
{
    var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\W).*$", "g");
    var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
    var enoughRegex = new RegExp("(?=.{6,}).*", "g");
    if (false == enoughRegex.test($(this).val()))
    {
        //$('#passstrength').html('More Characters');
        alert('More Characters!');
    }
    else if (strongRegex.test($(this).val()))
    {
//         $('#passstrength').className = 'ok';
//         $('#passstrength').html('强!');
        alert('强!');
    }
    else if (mediumRegex.test($(this).val()))
    {
        alert('中!');
    }
    else
    {
        alert('弱!');
    }
    return true;
}


</script>
</body>
</html>