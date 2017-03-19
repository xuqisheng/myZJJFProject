<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>广告初始化</title>
    <%@ include file="../common/head.jsp"%>
</head>
<body>
<div class="wrap-bd">
<div style="padding: 40px 60px; width:600px; background: #ececec">
	<form id="ff" method="get" action="#">
		<p>
			<label class="label">源广告组：</label>
			<select class="select" id="startSelect">
			 <c:forEach var="allSp" items="${allSpList}">
			   <option value="${allSp.id}" name="allSpId">
			     ${allSp.name}
			   </option> 
			 </c:forEach>
			</select>
		</p>
		<p>
			<label class="label">目标广告组：</label>
			<select class="select" id="endSelect">
			<c:forEach var="noAdSp" items="${noAdSpList}">
			   <option value="${noAdSp.id}" name="noAdSpId">
			     ${noAdSp.name}
			   </option> 
			 </c:forEach>
			</select>
		</p>
		<p>
			<input type="submit" id="test" value="提交" class="button button-ok">
		</p>
	</form>
</div>
<script>
        $(function () {
        	$("#test").click(function(){
        		$.ajax({
    				type : "post",
    				url : "${root}/Customer/AdManage/copyAd.do?timeState="+new Date().getTime(),
    				dataType : "json",
    				data:{allSpId:$("#startSelect").val(),
    					 noAdSpId:$("#endSelect").val()
    					},
    				success : function(data) {
    					if(data.success){
    						alert("插入"+data.message);
    						location.href="${root}/Customer/AdManage/toAdCopy.do";
    					}else{
    						alert(data.message);
    					}
    				},
    				error : function(data) {
    				}
    			}); 
        	});
        });
  </script>
 </div>
</body>
</html>