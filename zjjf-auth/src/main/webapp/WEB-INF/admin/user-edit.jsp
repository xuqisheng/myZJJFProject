<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<%
	request.setAttribute("root", request.getContextPath());
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>系统用户管理</title>
<%@ include file="../common/head.jsp"%>
</head>
<body class="wrap-bd">
<div class="mt-small mb-default">
    <a href="${root }/auth/admUser/getUserListPage.do">返回系统用户列表</a>
    <input type="hidden" name="status" id="status" value="${status }">
</div>
<form id = "addEditParam">
	<fieldset>
	    <legend id="legend">${str}</legend>
	    <input type="hidden" name="id" id="id" value="${user.id }">
	   <p class="required">
	       <label class="label">账号/工号：</label>
	       <input class="input input-default" type="text" name="userName" id="userName" value="${user.userName }" maxlength="16">
	       <input type="hidden" id="username1" value="${user.userName }">
	   </p>
	   <p class="required">
	       <label class="label">email：</label>
	       <input class="input input-default" type="text" name="email" id="email" value="${user.email }" maxlength="40">
	       <input type="hidden" id="email" value="${user.email }">
	   </p>
	    <p class="required">
	       <label class="label">密码：</label>
	       <input class="input input-default" type="text" name="password" maxlength="20" placeholder="">
	   </p>
	   <p>
	       <label class="label">部门：</label>
	       <%-- <input type="text" name="deptId" id="deptId" value="${user.deptId }"> --%>
	       <select class="select" id="deptId" name="deptId">
		       <option value="0">请选择</option>
		       <c:forEach var="department" items="${departmentList}">
		       		<option value="${department.id }">${department.name }</option>
		       </c:forEach>
	       </select>
	   </p>
	   <p>
	       <label class="label">岗位：</label>
	       <select class="select" id="postId" name="postId" >
		       <option value="0">请选择</option>
	       </select>
	   </p>
	   <div class="clearfix">
	       <label class="fl label">角色：</label>
	       <div class="fl" style="width: 800px">
	        <c:forEach var="role" items="${roleList1 }">
	            <span style="display: inline-block; width: 150px; height: 20px; overflow:hidden;">
	            	<input type="checkbox" name="roleIds" value="${role.id }"> ${role.roleName }
	            </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	        </c:forEach>
	       </div>
	   </div>
	  
	   <p>
	       <label class="label">账号状态：</label>
	       <c:choose>
	       	<c:when test="${user.status==1 }">
	       		<input type="radio" name="status" value="1" checked="checked"> 正常
		       <input class="ml-default" type="radio" name="status" value="0"> 停用
	       	</c:when>
	       	<c:when test="${user.status==0 }">
	       		<input type="radio" name="status" value="1"> 正常
		       <input class="ml-default" type="radio" name="status" value="0" checked="checked"> 停用
	       	</c:when>
	       	<c:otherwise>
		       	<input type="radio" name="status" value="1" checked="checked"> 正常
		      	<input class="ml-default" type="radio" name="status" value="0" > 停用
	       	</c:otherwise>
	       </c:choose>
	   </p>
	   <p>
	       <input type="button" class="button button-ok" id="addAndEditButton" value="保存">
	       <input type="button" class="button button-cancel" value="取消" onclick="javascript:location.href=document.referrer;" >
	   </p>
	</fieldset>
	</form>
<script>
	var url = '${root }/auth/admUser/getUserListPage.do';
	var addEditStr = "${addEditStr}";
	$(function(){
		$('input[name="password"]').on('focus' , function(){
			$('input[name="password"]').attr('type' , 'password');
			$('input[name="password"]').val('');
		});
		$('select[name="deptId"]').on('change' , function(){
			var deptId = $("#deptId").val();
			var html = '';
			if(deptId == 0){
				html += '<option value="0">请选择</option>';
			}else{
				$.ajax({
	     			type : "POST",
	     			url : "${root}/auth/public/getPositionListByDeptId.do",
	     			dataType : "json",
	     			async:false,
	     			data:{deptId:deptId},
	     			success : function(data) {
	     				if(data.success){
							$.each(data.message,function(i,item){
								html += '<option value="'+item.id+'">'+item.name+'</option>';
							})
						}else{
							html += '<option value="0">请选择</option>';
						}
						$('select[name="postId"]').html(html);
	     			}
	     		});  
			}
		});
		
		//	初始化数据
		var id = "${user.id}";
		if(id != '' && id != null){
			var roleList2 = $.parseJSON('${roleList2 }');
			$.each(roleList2,function(i,role2){
				$('input[value="'+role2.id+'"]').prop('checked', true);
			});
			
			$('select[name="deptId"]').val('${user.deptId}');
			$('select[name="deptId"]').trigger('change');
			$('select[name="postId"]').val('${user.postId}');
		}
		
		$('#addAndEditButton').on('click' , function(){
			var num = 0;
			
	 		var username = $("#username").val();
	 		var usernamestr = /^[^\u4e00-\u9fa5]{0,}$/;
	 		var usernamestri = /^[0-9]*$/; 
	 		if(username==""){
	 			alert("账号/工号不能为空!");
	 			return;
	 		}else if(!usernamestr.test(username)){
	 			alert("账号/工号不能为中文！");
	 			return;
	 		}
	 		var nickname = $("#nickname").val();
	 		if(nickname==""){
	 			alert("姓名不能为空!");
	 			return;
	 		}
	 		var deptId = $("#deptId").val();
	 		if(deptId==0){
	 			alert("请选择部门！");
	 			return;
	 		}
	 		var postId = $("#postId").val();
	 		if(postId==0){
	 			alert("请选择职位！");
	 			return;
	 		}
	 		/* var gender = $("input[name='gender']:checked").val();
	 		console.log(gender); */
	 		/* var status = $("input[name='status']:checked").val();
	 		console.log(status); */
	 		var roleId = new Array();
	 		$("input[name='roleIds']:checked").each(function(){
	 			roleId.push($(this).val());
	 		});
	 		if(roleId.length<=0){
	 			alert("请选择角色！");
	 			return;
	 		}
	 		$("#addAndEditButton").hide();
			if(id == '' || id == null){
			 var password = $.trim($('input[name="password"]').val());
		 		if(password == ''){
		 			alert("密码不能为空!");
		 			return;
		 		}else{
					$.ajax({
		     			type : "POST",
		     			url : "${root }/auth/admUser/insertUser.do",
		     			dataType : "json",
		     			data:$('#addEditParam').serialize(),
		     			success : function(data) {
		     				if (data.success) {
			   					alert(data.message);
			   					$("#addAndEditButton").disabled=false;
			   					location.href=url;
			   				}else{
			   					alert(data.message);
			   				}
		     			},
		     			error : function() {
		     				alert("请求失败！");
			   			}
		     		});  
		 		}
			}else{
				var status = '${status }';
				$.ajax({
	     			type : "POST",
	     			url : "${root }/auth/admUser/updateUser.do",
	     			dataType : "json",
	     			data:$('#addEditParam').serialize(),
	     			success : function(data) {
	     				if (data.success) {
		   					alert(data.message);
		   					$("#addAndEditButton").disabled=false
		   					location.href=url;
		   				}else{
		   					alert(data.message);
		   				}
	     			},
	     			error : function() {
	     				alert("请求失败！");
		   			}
	     		});  
			}
		});
	});
</script>
</body>
</html>