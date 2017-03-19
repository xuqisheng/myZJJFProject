<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>编辑问题</title>
<%@ include file="../common/head.jsp"%>
</head>
<body class="wrap-bd">
<div class="mb-default">
    <a href="${root}/customer/spHelp/getAllHelp.do">返回问题列表</a>
</div>
<fieldset class="bg">
    <legend>编辑问题</legend>
    <p>
        <label class="label">问题：</label>
        <textarea class="textarea" id="title" name="title" maxlength="225" cols="60" rows="5">${title}</textarea><label id="wenti" style="color:red"></label>
    </p>
    <p>
        <label class="label">答案：</label>
        <textarea class="textarea" name="solution" id="solution" maxlength="225" cols="60" rows="10">${solution}</textarea><label style="vertical-align:top;color:red" id="daan"></label>
    </p>
    <p>
        <label class="label"></label>
        <button class="button button-ok" onclick="editHelp()">保存</button>
        <button class="button button-cancel" onclick="exit()">取消</button>
        <input type="hidden" name="id" value="${id}" id="id"/>
    </p>
</fieldset>
<script>
    function editHelp(){
    	var id = $("#id").val();
    	var title = $("#title").val();
    	var solution = $("#solution").val();
    	
    	if($.trim(title) == ""){
			$("#wenti").html("问题不能为空！");
		}else if($.trim(title).length>225){
			$("#wenti").html("问题长度不能超过225个字符！");
		}
		if($.trim(solution) == ""){
			$("#daan").html("答案不能为空！");
		}else if($.trim(solution).length>225){
			$("#daan").html("答案长度不能超过225个字符！");
		}
    	
		if($.trim(title) == "" || $.trim(solution) == "" ||$.trim(solution).length>225 || $.trim(title).length>225){
			return;
		}else{
    	
    	$.post("${root}/customer/spHelp/updateHelp.do",
    			{id:id,
    			title:title,
    			solution:solution},
    			function(data){
    				if(data.success){
    					alert(data.message);
    					location.href = "${root}/customer/spHelp/getAllHelp.do";
    				}else{
    					alert(data.message);
    				}
    			},'json');
		}
    }
    
    function exit(){
		location.href = "${root}/customer/spHelp/getAllHelp.do";
	}
</script>
</body>
</html>