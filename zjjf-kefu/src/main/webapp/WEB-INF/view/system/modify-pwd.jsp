<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<title>修改密码</title>
	<%@ include file="../common/head.jsp"%>
</head>
<body class="wrap-bd">
	<div class="wrap-bd bg">
		<b>修改密码</b>
		<p class="required">
			<label class="label">原密码：</label>
			<input class="input input-search-text" type="password" name="oldpwd" placeholder="请输入原密码" id="usedPassword" onblur="chickPassword(this,1)">
		</p>
		<p class="required">
			<label class="label">新密码：</label>
			<input class="input input-search-text" type="password" name="newpwd" placeholder="请输入新密码" id="newPassword" onblur="chickPassword(this,2)">
		</p>
		<p class="required">
			<label class="label">确认新密码：</label>
			<input class="input input-search-text" type="password" name="renewpwd" placeholder="请确认新密码" id="okPassword" onblur="chickPassword(this,3)">
		</p>
		<p>
			<label class="label"></label>
			<input class="button button-ok" type="button" name="save" value="确定" id="updatePassword" onclick="updatePassword()">
			<input class="button button-cancel" type="button" name="cancel" value="清除" onclick="clearInput()">
		</p>
	</div>
</body>
<script type="text/javascript">
	function clearInput(){
		$("#usedPassword").val("");
		$("#newPassword").val("");
		$("#okPassword").val("");
	}

	/* function chickPassword(e,num){
		var usedPassword = $("#usedPassword").val();
		var newPassword = $("#newPassword").val();
		var okPassword = $("#okPassword").val();
		var password = /^[^\u4e00-\u9fa5]{0,}$/;
		if(num==1){
			if(e.value==""){
				alert("原密码不能为空！")
				return;
			}
			
		}else if(num==2){
			if(e.value==""){
				alert("新密码不能为空！")
				return;
			}else if(e.value.length<6){
				alert("新密码长度不能小于6位！")
				return;
			}else if(!(e.value).test(newPassword)){
				alert("新密码不能输入中文汉字！")
				return;
			}
		}else if(num==3){
			if(e.value==""){
				alert("确认密码不能为空！")
				return;
			}else if(e.value.length<6){
				alert("确认密码长度不能小于6位！")
				return;
			}else if(!(e.value).test(okPassword)){
				alert("确认密码不能输入中文汉字！")
				return;
			}
			
		}
	} */

	function updatePassword(){
		
		var usedPassword = $("#usedPassword").val();
		var newPassword = $("#newPassword").val();
		var okPassword = $("#okPassword").val();
		var password = /^[^\u4e00-\u9fa5]{0,}$/;
		
		if(usedPassword==""){
			alert("原密码不能为空！")
			return;
		}
	
		if(newPassword==""){
			alert("新密码不能为空！")
			return;
		}else if(newPassword.length<6){
			alert("新密码长度不能小于6位！")
			return;
		}else if(!password.test(newPassword)){
			alert("新密码不能输入中文汉字！")
			return;
		}
	
		if(okPassword==""){
			alert("确认密码不能为空！")
			return;
		}else if(okPassword.length<6){
			alert("确认密码长度不能小于6位！")
			return;
		}else if(!password.test(okPassword)){
			alert("确认密码不能输入中文汉字！")
			return;
		}
		if(newPassword != okPassword){
			alert("两次输入的新密码不一致");
			return;
		}
		$("#updatePassword").hide()
		$.post("${root}/userManager/customerService/updateLoginPassword.do",{newPassword:newPassword,usedPassword:usedPassword},function(data){
			if(data.success){
				alert(data.message)
				$("#usedPassword").val("");
				$("#newPassword").val("");
				$("#okPassword").val("");
			}else{
				alert(data.message)
			}
			$("#updatePassword").show()
		},'json')
	}
</script>
</html>