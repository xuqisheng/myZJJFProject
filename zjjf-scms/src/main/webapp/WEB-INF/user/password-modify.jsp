<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<title>修改密码</title>
	<%@ include file="../common/head.jsp"%>
    <style>
        .bar-box {
            position: relative;
            width: 90px;
            height: 6px;
            display: inline-block;
            background: #ccc;
            vertical-align: middle;
        }
        .bar-box .bar-i {
            position: absolute;
            top: 0;
            left: 0;
            height: 6px;
            display: inline-block;
        }
        .bar-box .red {
            width: 33.33%;
            background: #ed4b1c;
        }
        .bar-box .yellow {
            width: 66.66%;
            background: #f4a925;
        }
        .bar-box .green {
            width: 100%;
            background: #62b618;
        }
    </style>
</head>
<body class="wrap-bd">
    <div class="mb-small">
        <span style="font-size: 14px;">当前位置：</span>
        <a href="${root}/scms/password/toEditPassWord.do" class="crumb">密码管理</a>
        <span class="crumb crumb-active">修改登录密码</span>
    </div>
	<div class="wrap-bd bg">
		<b>修改密码</b>
		<p>
			<label class="label">原密码：</label>
			<input class="input-search-text" type="password" name="oldpwd" placeholder="请输入原密码" id="usedPassword" onblur="chickPassword(this,1)" maxlength="20" autocomplete="off">
			<label style="color: red;" id="usedpwdmes"></label>
		</p>
		<p>
			<label class="label">新密码：</label>
			<input class="input-search-text" type="password" name="newpwd" placeholder="请输入新密码" id="newPassword" onblur="chickPassword(this,2)" maxlength="20" autocomplete="off">
			<label style="color: red;" id="newpwdmes1"></label>
			<label style="color: red;" id="newpwdmes"></label>
		</p>
		<p>
			<label class="label">确认新密码：</label>
			<input class="input-search-text" type="password" name="renewpwd" placeholder="请确认新密码" id="okPassword" onblur="chickPassword(this,3)" maxlength="20" autocomplete="off">
			<label style="color: red;" id="okpwdmes"></label>
		</p>
		<p>
			<label class="label"></label>
			<input class="button-save" type="button" name="save" value="确定" id="updatePassword" onclick="updatePassword()">
			<input class="button-cancel ml-default" type="button" name="cancel" value="清除" onclick="clearInput()">
        </p>
	</div>
</body>
<script type="text/javascript">
    var str = "${str}";
	function clearInput(){
		$("#usedPassword").val("");
		$("#newPassword").val("");
		$("#okPassword").val("");
		$("#newpwdmes1").text("");
		$("#usedpwdmes").text("");
		$("#okpwdmes").text("");
	}
	
	function chickPassword(e,num){
		var password = /^[^\u4e00-\u9fa5]{0,}$/;
		if(num==1){
			if($.trim(e.value)==""){
				$("#usedpwdmes").text("原密码不能为空！");
				return;
			}
			$.post("${root}/scms/password/chickPasswordIsOk.do",{usedPassword:e.value,str:str},function(data){
				if(!data.success){
					$("#usedpwdmes").text(data.message);
					return;
				}
			})
		}else if(num==2){
			if($.trim(e.value)==""){
				$("#newpwdmes1").text("新密码不能为空！");
				return;
			}else if($.trim(e.value).length<6){
		    	$("#newpwdmes1").text("密码长度不能小于6位！");
			}
		}else if(num==3){
			if($.trim(e.value)==""){
				$("#okpwdmes").text("确认密码不能为空！");
				return;
			}else if($.trim(e.value).length<6){
				$("#okpwdmes").text("密码长度不能小于6位！");
				return;
			}else if($.trim(e.value) != $.trim($("#newPassword").val())){
				$("#okpwdmes").text("两次输入的新密码不一致！");
				return;
			}
		}
	} 
	$('#usedPassword').on('keyup',function(){
		$("#usedpwdmes").text("");
	});
	$('#okPassword').on('keyup',function(){
		$("#okpwdmes").text("");
	});
	$('#newPassword').on('keyup',function(){
		 $("#newpwdmes1").text("");
		var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g"); 
	      var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g"); 
	      var enoughRegex = new RegExp("(?=.{6,}).*", "g");
	      $('#newpwdmes').css('color','green');
	      if($.trim($(this).val())== ""){
	    	  $("#newpwdmes").text("");
	    	  $("#newpwdmes1").text("");
	      }else if(strongRegex.test($.trim($(this).val()))){
	    	  $('#newpwdmes').html('<span class="bar-box"><span class="bar-i green"></span></span> 高');
	      }else if(mediumRegex.test($.trim($(this).val()))){
	    	  $('#newpwdmes').html('<span class="bar-box"><span class="bar-i yellow"></span></span> 中');
	      }else{
	    	  $('#newpwdmes').html('<span class="bar-box"><span class="bar-i red"></span></span> 低');
	      }
	      return true;
	  });
	
	function updatePassword(){
		var usedPassword = $.trim($("#usedPassword").val());
		var newPassword = $.trim($("#newPassword").val());
		var okPassword = $.trim($("#okPassword").val());
		var password = /^[^\u4e00-\u9fa5]{0,}$/;
		/* $.post("${root}/scms/password/chickPasswordIsOk.do",{usedPassword:usedPassword,str:str},function(data){
			if(!data.success){
				layer.tips(data.message,"#usedPassword");
				return false;
			}
		}) */
		if(usedPassword==""){
			$("#usedpwdmes").text("原密码不能为空！");
			$("#usedPassword").focus();
			return;
		}else if(newPassword==""){
			$("#newpwdmes1").text("新密码不能为空！");
			$("#newPassword").focus();
			return;
		}else if(newPassword.length < 6){
			$("#newpwdmes1").text("密码长度不能小于6位！");
			$("#newPassword").focus();
			return;
		}else if(okPassword==""){
			$("#okpwdmes").text("确认密码不能为空！");
			$("#okPassword").focus();
			return;
		}else if(okPassword.length < 6){
			$("#okpwdmes").text("密码长度不能小于6位！");
			$("#okPassword").focus();
			return;
		}else if(newPassword != okPassword){
			$("#okpwdmes").text("两次输入的新密码不一致！");
			$("#okPassword").focus();
			return;
		}
		
		$("#updatePassword").hide();
		var mess = '';
		  if($('#newpwdmes:contains("高")').length==1){
			  mess=2;
		  }else if($('#newpwdmes:contains("中")').length==1){
			  mess=1;
		  }else{
			  mess=0;
		  }
		var url = "${root}";
		var param = {newPassword:newPassword,usedPassword:usedPassword};
		url+="/scms/password/updateLoginPassword.do?col2="+mess;
		$.post(url,param,function(data){
			if(data.success){
				layer.msg(data.message)
				$("#usedPassword").val("");
				$("#newPassword").val("");
                   $("#okPassword").val("");
                   $("#newpwdmes").empty();
			}else{
				layer.msg(data.message)
			}
			$("#updatePassword").show();
		})
			
	}
</script>
</html>