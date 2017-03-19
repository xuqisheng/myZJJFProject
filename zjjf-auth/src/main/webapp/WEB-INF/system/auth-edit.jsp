<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>街坊店宝 - 权限管理</title>
    <%@ include file="../common/head.jsp"%>
</head>
<body>
<div class="wrap-bd">
    <div class="wrap-bd table-border">
    	<form
    	<c:choose>
    		<c:when test="${auth.id == null || auth.id == ''}">action="${root }/auth/auth/insertAuth.do"</c:when>
    		<c:otherwise>action="${root }/auth/auth/updateAuth.do"</c:otherwise>
    	</c:choose>
    	>
    		<input type="hidden" name="id" value="${auth.id }">
	        <div>
	            <label class="label">所属系统：</label>
	            <select class="select" name="appId">
	                <option value="2" <c:if test="${auth.appId == 2}">selected="selected"</c:if>>客服端</option>
	                <option value="3" <c:if test="${auth.appId == 3}">selected="selected"</c:if>>进销存端</option>
	                <option value="4" <c:if test="${auth.appId == 4}">selected="selected"</c:if>>财务</option>
	                <option value="6" <c:if test="${auth.appId == 6}">selected="selected"</c:if>>经销商</option>
                	<option value="7" <c:if test="${auth.appId == 7}">selected="selected"</c:if>>仓库</option>
	            </select>
	        </div>
	        <p>
	            <label class="label">权限名称：</label>
	            <input class="input input-default" type="text" name="authName" value="${auth.authName }">
	        </p>
	        <p>
	            <label class="label">权限描述：</label>
	            <textarea class="textarea textarea-default" name="roleRemark">${auth.roleRemark }</textarea>
	        </p>
	        <p>
	            <label class="label">图标class：</label>
	            <input class="input input-default" type="text" name="icon" value="${auth.icon }">
	        </p>
	        <p>
	            <label class="label">父权限：</label>
	            <select class="select" name="upId">
                	<option value="" selected="selected" data-lay="0">请选择</option>
                	<c:forEach items="${authList}" varStatus="i" var="auth1" >
                        <option value="${auth1.id}" data-lay="${auth1.level}" <c:if test="${auth1.id == auth.upId}">selected="selected"</c:if>>${auth1.authName}(${auth1.level}级菜单)</option> 
					</c:forEach>
                </select>
	            <span class="txt-warn">
	            	<c:if test="${auth.level != 0 && auth.level != null}">（层级：<spen id="level">${auth.level }</spen>级）</c:if>
	            	<input type="hidden" value="${auth.level}" name="level">
	            </span>
	        </p>
	        <p>
	        	<label class="label">菜单类型：</label>
	            <input type="radio" value="1" name="type" checked="checked">菜单
	            <input type="radio" value="2" name="type" <c:if test="${auth.type == 2}">checked="checked"</c:if>>功能
	            
	        </p>
	        <p>
	            <label class="label">权限action：</label>
	            <input class="input input-default" type="text" name="action" value="${auth.action }" <c:if test="${auth.type == 1}">readonly="readonly"</c:if>>
	            <span class="txt-warn">（填写action，示例：/kefu/authority/scmsLoginPage.do）</span>
	        </p>
	        <p>
	            <label class="label">排序：</label>
	            <input class="input input-default" type="text" name="ordId" value="${auth.ordId }">
	        </p>
	        <p>
	            <label class="label">是否启用：</label>
	            <input type="radio" name="status" value="1" checked="checked" <c:if test="${auth.status == 1}">checked="checked"</c:if>> 是
	            <input type="radio" name="status" value="0" class="ml-default" <c:if test="${auth.status == 0}">checked="checked"</c:if>> 否
	        </p>
	        <p>
		        <input type="submit" class="button button-ok" value="保存">
		        <input type="button" class="button button-cancel" value="取消" onclick="self.location=document.referrer;">
		    </p>
	    </form>
    </div>
</div>
<script type="text/javascript">
$(function(){
	$('input[name="type"]').on('click' , function(){
		if($(this).val() == '1'){
			$('input[name="action"]').val('#');
			$('input[name="action"]').attr('readonly' , true);
		}
		else if($(this).val() == '2'){
			$('input[name="action"]').val('');
			$('input[name="action"]').attr('readonly' , false);
			$('input[name="action"]').focus();
		} 
	});
	$('select[name="appId"]').on('change' , function(){
		$.post("${root }/auth/auth/getAuthByAppId.do",{appId:$('select[name="appId"]').val(),type:1},function(data){
			var html = '<option value="" data-lay="0">请选择</option>';
			$('select[name="upId"]').html(html);
			if(data.success){
				$.each(data.message,function(i,auth){
					html+='<option value="'+auth.id+'" data-lay="'+auth.level+'">'+auth.authName+'('+auth.level+'级菜单)'+'</option>'
				})
			}
			$('select[name="upId"]').html(html);
		},'json');
	});

	$('select[name="upId"]').on('change' , function() {
		var level = $(this).find('option:selected').attr('data-lay');
		$('#level').text(level);
		$('input[name="level"]').val(parseInt(level)+1);
	});
	
	$('select[name="upId"]').trigger('change');
});
</script>
</body>
</html>
