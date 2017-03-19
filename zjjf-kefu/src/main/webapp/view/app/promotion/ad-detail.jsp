<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<title>活动详情</title>
<script src="${root}/resources/vendor/jquery/jquery-3.1.0.min.js"></script>
<style>
    body{margin:0;}
    p{margin:0;padding: 0}
    img{vertical-align:bottom;}
</style>
</head>
<body>
<div>
    <img id="J_banner" src="" width="100%" alt=""/>
</div>
<div style="padding:5px 12px 50px; word-wrap: break-word;line-height: 26px" id="J_content"></div>
<script>
	$(function() {
		$.ajax({
		     url: '${root}/Customer/AdManage/getLinkContent.do',
		     type: 'get',
		     data: {'id':<%=request.getParameter("id")%>},
		     dataType:'json',
		     success: function(data) {
		         if(data.success){
		        	var urlImg = 'http://www.izjjf.cn/'+data.message.extimg;
			        var dd = data.message.content;
			        $('#J_banner').attr('src', urlImg);
			        $('#J_content').html(dd);
		         }
		     }
		});
	});
</script>
</body>
</html>
