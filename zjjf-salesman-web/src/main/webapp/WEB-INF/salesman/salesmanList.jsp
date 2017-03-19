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
<style>
	ul li{ list-style:none; height:38px; line-height:38px;float:left; /* 向左漂移，将竖排变为横排 */ }
	ul li input{ margin-right:5px;margin-left:30px;}
</style>
</head>
<body>
<div class="wrap-bd" style="background: #fff;">
<!-- 	<div class="mb-default title">部门领导选择列表</div> -->
     <div class="chkBoxDiv" style="display: inline-block; background: #fff; overflow: auto;">
     	 <ul>
     	 	<c:forEach var="list" items="${list}">
     	 		<li style="width: 180px;">
     	 			<input type="checkbox" name="chk_list" value="${list.id}" data-name="${list.userName}" <c:if test="${list.isCheck eq '1'}">checked="checked"</c:if>>${list.userName}
     	 		</li>
     	 	</c:forEach>
     	 </ul>
     </div>
	<div class="op-section clearfix">
		<input type="button" class="input-search-button" value="确定"  id="btnConfirm" onclick="selectUser();"/>
		<input type="button" class="input-search-button" value="取消"  id="btnCencel" onclick="layerClose();"/>
	</div>
</div>
<script>
	
	//确定选择用户信息
	function selectUser(){	
		var chkTotal = $("input[name='chk_list']:checked").length;
		var userIds = new Array(); 
		var userNames = new Array(); 
		$("input[name='chk_list']:checked").each(function(i) {
			userIds.push($(this).val());
			userNames.push($(this).data("name"));
		}); 
		parent.selectUserCallback(userIds.toString(),userNames.toString()); 
	}
	
	function layerClose(){
		parent.layerClose();//关闭所有的iframe层
	}
	
</script>
</body>
</html>