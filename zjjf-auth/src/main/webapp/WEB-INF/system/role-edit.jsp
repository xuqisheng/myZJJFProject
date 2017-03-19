<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>街坊店宝 - 角色管理</title>
<%@ include file="../common/head.jsp"%>
</head>
<body class="wrap-bd">
<div class="mt-small mb-default">
    <a href="${root }/auth/role/getRoleListPage.do">返回角色列表</a>
</div>
<form id="addEditParam">
<fieldset>
    <legend>${str }</legend>
    <p>
        <label class="label">所属系统：</label>
        <select class="select" name="appId">
            <option value="2" <c:if test="${appId == 2}">selected="selected"</c:if>>客服端</option>
            <option value="3" <c:if test="${appId == 3}">selected="selected"</c:if>>进销存端</option>
            <option value="4" <c:if test="${appId == 4}">selected="selected"</c:if>>财务</option>
        </select>
    </p>
    <p>
        <label class="label">角色编号</label>
        <input class="input input-default" type="text" name="roleNo" value="${role.roleNo }" id="roleNo" maxlength="50">
        <input type="hidden" id="roleNo1" value="${role.roleNo }">
    </p>
    <p>
        <label class="label required">角色名称</label>
        <input class="input input-default" type="text" name="roleName" value="${role.roleName }" id="rolename" maxlength="50">
    </p>
    <div class="clearfix">
        <label class="fl label required">权限</label>
        <div class="fl" style="width: 800px" id="AAA">
        	<input name="" class="J_selectAll" type="checkbox">&nbsp;全选
   				<c:forEach var="auth1" items="${authList1 }">
	        		<div class="J_div">
				        	<p><input class="J_checkbox J_checkboxOne" name="authIds" value="${auth1.id }" type="checkbox">&nbsp;${auth1.authName }</p>
						        <c:forEach var="auth2" items="${auth1.auths }">
						        	<p><input name="authIds" style="margin-left: 50px" class="J_checkbox J_checkboxTwo" value="${auth2.id }" type="checkbox">&nbsp;${auth2.authName }</p>
					    	    </c:forEach>
		    	    </div>
		        </c:forEach>
        </div>
    </div>
    <p>
       <label class="label">账号状态：</label>
       <c:choose>
       	<c:when test="${role.status==1 }">
       		<input type="radio" name="status" value="1" checked="checked"> 正常
	       <input class="ml-default" type="radio" name="status" value="0"> 停用
       	</c:when>
       	<c:when test="${role.status==0 }">
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
        <label class="label"></label>
        <input type="button" class="button button-ok" value="保存" onclick="saveInfo()" id="addAndEditButton">
        <input type="button" class="button button-cancel" value="取消" onclick="self.location=document.referrer;">
    </p>
</fieldset>
</form>
</body>
<script>
	$(function() {
		var id = "${role.id}";
		if(id != '' && id != null){
			var authList2 = $.parseJSON('${authList2 }');
			$.each(authList2,function(i,auth){
				$('input[value="'+auth.id+'"]').prop('checked', true);
				$.each(auth.auths,function(i,auth2){
					$('input[value="'+auth2.id+'"]').prop('checked', true);
				});
			});
		}
		$('#AAA').on('click', '.J_selectAll', function() {
			$('#AAA .J_checkbox').prop('checked', $(this).prop('checked'));
		});
		$('#AAA').on('click', '.J_checkbox', function() {
			var checkboxLength = $('#AAA .J_checkbox').length;
			if(checkboxLength != $('#AAA .J_checkbox:checked').length) {
				$('.J_selectAll').prop('checked', false);
			} else {
				$('.J_selectAll').prop('checked', true);
			}
		});
		$('#AAA').on('click', '.J_checkboxOne', function() {
			$(this).parent('p').parent('.J_div').find('.J_checkbox').prop('checked', $(this).prop('checked'));
		});
		$('#AAA').on('click', '.J_checkboxTwo', function() {
			$(this).parent('p').find('.J_checkboxThree').prop('checked', $(this).prop('checked'));
			var checkboxTwoLength = $(this).parent('p').parent('.J_div').find('.J_checkboxTwo:checked').length;
			if(checkboxTwoLength > 0) {
				$(this).parent('p').parent('.J_div').find('.J_checkboxOne').prop('checked', true);
			} else {
				$(this).parent('p').parent('.J_div').find('.J_checkboxOne').prop('checked', false);
			}
		});
		$('#AAA').on('click', '.J_checkboxThree', function() {
			var checkboxThreeLength = $(this).parent('p').find('.J_checkboxThree:checked').length;
			if(checkboxThreeLength > 0) {
				$(this).parent('p').find('.J_checkboxTwo').prop('checked', true);
				$(this).parent('p').parent('.J_div').find('.J_checkboxOne').prop('checked', true);
			} else {
				$(this).parent('p').find('.J_checkboxTwo').prop('checked', false);
			}
		});
		$('#roleNo').on('blur' , function(){
			var val = $.trim($(this).val());
			if(val == ''){
                layer.tips('请输入角色编号', this);
				$(this).focus();
			}else if(val != $.trim($("#roleNo1").val())){
				$.post("${root}/auth/role/checkRoleNo.do",{roleNo:val},function(data){
					if(!data.success){
                        layer.tips(data.message, $("#roleNo"));
						$("#roleNo").val("");
						$("#roleNo").focus();
					}
				},'json')
			}
		});
	});
	function saveInfo(){
		var roleNostr = /^[^\u4e00-\u9fa5]{0,}$/;
		if(!roleNostr.test($.trim($("#roleNo").val()))){
			alert("角色编号不能为中文！");
			return;
		}
		if($.trim($("#rolename").val())==""){
			alert("角色名不能为空！");
			return;
		}
		var authId = new Array();
		$("input[name=authIds]:checked").each(function(){
			authId.push($(this).val());
		})
		if(authId.length<=0){
			alert("请选择权限！");
			return;
		}
		$("#addAndEditButton").hide();
		var id = "${role.id}";
		if(id == '' || id == null){
			$.ajax({
     			type : "POST",
     			url : "${root }/auth/role/insertRole.do",
     			dataType : "json",
     			data:$('#addEditParam').serialize(),
     			success : function(data) {
     				if (data.success) {
	   					alert(data.message);
	   					$('.button-cancel').click();
	   				}else{
	   					alert(data.message);
	   				}
     			},
     			error : function() {
     				alert("请求失败！");
	   			}
     		});  
		}else{
			$.ajax({
     			type : "POST",
     			url : "${root }/auth/role/updateRole.do?id="+id,
     			dataType : "json",
     			data:$('#addEditParam').serialize(),
     			success : function(data) {
     				if (data.success) {
	   					alert(data.message);
	   					$('.button-cancel').click();
	   				}else{
	   					alert(data.message);
	   				}
     			},
     			error : function() {
     				alert("请求失败！");
	   			}
     		});  
		}
	}
</script>
</html>