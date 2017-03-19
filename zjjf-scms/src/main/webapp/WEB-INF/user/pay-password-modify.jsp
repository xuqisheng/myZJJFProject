<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<%
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragrma","no-cache");
response.setDateHeader("Expires",0);
%> 
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<title>支付密码修改/设置</title>
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
        <a href="${root}/scms/password/toEditPassWord.do" class="crumb">密码管理</a><span class="crumb crumb-active">修改支付密码</span>
    </div>
	<div class="wrap-bd bg">
		<b>支付密码修改/设置</b>
		<p>
			<label class="label">新密码：</label>
			<input class="input-search-text" type="password" name="newpwd" placeholder="请输入6位新密码" id="newpwd">
			<span style="color: red;" id="newpwdmes"></span>
		</p>
		<p>
			<label class="label">确认新密码：</label>
			<input class="input-search-text" type="password" name="renewpwd" placeholder="请确认6位新密码" id="renewpwd">
			<span style="color: red;" id="renewpwdmes"></span>
		</p>
		<p>
			<label class="label"></label>
			<input class="button-save" type="button" name="save" value="确定" id="save">
			<input class="button-cancel ml-default" type="button" name="cancel" value="取消">
		</p>
	</div>
	<script type="text/javascript">
	  $(function(){
		  $('#newpwd').on('keyup',function(){
			  var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g"); 
		      var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g"); 
		      var enoughRegex = new RegExp("(?=.{6,}).*", "g");
		      if(false==enoughRegex.test($.trim($(this).val()))){
		    	  $('#newpwdmes').addClass('error');
		    	  $('#newpwdmes').text('密码长度至少为6位!');
		      }else if(strongRegex.test($.trim($(this).val()))){
		    	  $('#newpwdmes').removeClass('error');
		    	  $('#newpwdmes').html('<span class="bar-box"><span class="bar-i green"></span></span> 高');
		      }else if(mediumRegex.test($.trim($(this).val()))){
		    	  $('#newpwdmes').removeClass('error');
		    	  $('#newpwdmes').html('<span class="bar-box"><span class="bar-i yellow"></span></span> 中');
		      }else{
		    	  $('#newpwdmes').removeClass('error');
		    	  $('#newpwdmes').html('<span class="bar-box"><span class="bar-i red"></span></span> 低');
		      }
		      return true;
		  });
		  
		  $('#renewpwd').on('blur',function(){
			  if($.trim($('#renewpwd').val())!=$.trim($('#newpwd').val())){
				  $('#renewpwdmes').addClass('error');
				  $('#renewpwdmes').text('两次输入的密码不一样');
			  }else{
				  $('#renewpwdmes').removeClass('error');
				  $('#renewpwdmes').text('');
			  }
		  });
		  
		  
		  $('#save').on('click',function(){
			  if($('.error').length!=0){
                    return;				  
			  }
			  var password = $.trim($('#newpwd').val());
			  var renewpwd = $.trim($('#renewpwd').val());
			  var mess = '';
			  if($('#newpwdmes:contains("高")').length==1){
				  mess=2;
			  }else if($('#newpwdmes:contains("中")').length==1){
				  mess=1;
			  }else{
				  mess=0;
			  }
			  $.ajax({
	    			type : "POST",
	    			url : "${root}/scms/password/updatePayPassword.do",
	    			data :{"newpwd":password,"mess":mess,"renewpwd":renewpwd},
	    			success : function(da) {
	    				if (da.success) {
	    					location.href="${root}/scms/password/toEditPassWord.do";
	    				}else{
	    					if(da.message='501'){
	    						$('#renewpwdmes').addClass('error');
	    						$('#renewpwdmes').text('两次输入的密码不一样');
	    					}else if(da.message='502'){
	    						alert('缺少必要参数!')
	    					}else{
	    						console.log(da.message);
	    					}
	    				}
	    			},
	    			error : function(da) {
	    				alert("失败的请求!");
	    			}
	    	  });
		  });
			  
	  });
	</script>
</body>
</html>