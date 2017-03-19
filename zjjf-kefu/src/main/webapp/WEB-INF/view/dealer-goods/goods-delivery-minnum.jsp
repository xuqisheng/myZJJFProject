<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>经销商商品管理 - 设置起送量</title>
    <%@ include file="../common/head.jsp"%>
    <script src="${root}/resources/js/comm.js"></script>
</head>
<body>
<div class="wrap-bd">
    <div class="mb-default">
        <span class="crumb">经销商商品管理</span>
        <span class="crumb crumb-active">设置起送量</span>
    </div>
    <div class="wrap-bd bg">
    	<form id="minimumForm">
	        <b><input type="hidden" value="${scmsManager.id}" name="managerId">【${scmsManager.managerName}】经销商</b>
	        <c:forEach items="${scmsMinimums}" varStatus="i" var="scmsMinimum" >
            <p>
        		<input type="hidden" value="${scmsMinimum.id}" name="ids">
	            <label class="label">${scmsMinimum.brandName}</label>
	            <input type="text" class="input input-default" value="${scmsMinimum.minimum}" name="minimums" maxlength="4"  data-shortcut="enter">
	        </p>
	        </c:forEach>
        </form>
        <br>
        <div>
            <input type="button" class="button button-ok" id="submit" value="确定">
            <input type="button" class="button button-cancel" value="取消" id="return" onclick="window.location.href = '${root}/scms/item/listPage.do?managerId=${scmsManager.id}'">
        </div>
    </div>
</div>
<script>
	$(function(){
		$('#submit').on('click' , function(){
			var submit = true;
			if($.trim($('input[name="managerId"]').val()) == ''){
				submit = false;
				layer.msg('页面信息有误，请重新打开界面');
			}
			$('#minimumForm').find('input[name="ids"]').each(function(){
				if($.trim($(this).val()) == ''){
					submit = false;
					layer.msg('页面信息有误，请重新打开界面');
					return false;
				}
			});
			$('#minimumForm').find('input[name="minimums"]').each(function(){
				var xnumCheck = /^[1-9]{1,4}$/;
				if($.trim($(this).val()) == ''){
					$(this).focus();
					submit = false;
					layer.tips('请录入起购量', this);
					return false;
				}else if(!xnumCheck.test($(this).val())){
					$(this).focus();
					submit = false;
					layer.tips('请输入正确的数量', this);
					return false;
				}
			});
			if(submit){
				$.post("${root}/scms/minimum/updateScmsMinimum.do",$('#minimumForm').serializeArray(),function(data){
					if(data.success){
						layer.msg(data.message, {
						    icon: 1,
						    time: 2000 //2秒关闭（如果不配置，默认是3秒）
						}, function(){
							$('#return').click();
						});  
					}else{
						layer.msg(data.message, {icon: 5});
					}
				},'json');
			}
		});
	});
</script>
</body>
</html>