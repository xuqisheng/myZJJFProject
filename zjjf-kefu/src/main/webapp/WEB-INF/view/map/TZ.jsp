<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title></title>
    <%@ include file="../common/head.jsp"%>
    <script src="${root}/resources/vendor/jquery/ajaxfileupload.js"></script>
    <%@ include file="../common/autocomplete.jsp"%>
  
</head>
<body>
	<br>
	<div class="table">
		普通查询：
		<a href="${root }/customer/appStore/szMapPage.do">深圳市</a>
		<a href="${root }/customer/appStore/gzMapPage.do">广州市</a>
	</div>
	<br>
	<div class="table">
		按批发商查询：
		<input type="text" id="mobile">
		<a href="#" onclick="dhMapPage()">批发部地图</a>&nbsp;&nbsp;默认为道鸿批发部
	</div>
	<br>
	<div class="table">
		<form action="${root}/customer/appStore/readExcel.do" name="Form" id="Form" method="post" enctype="multipart/form-data">
		Excel导入查询：
			<input type="file" id="excel" name="file"  onchange="fileType(this)" />
			<a class="button button-white" onclick="save();">导入</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="${root }/customer/appStore/returnTempMap.do">查看地图</a>
		</form>
	</div>
</body>
<script>
    function save(){
		if($("#excel").val()==""){
			alert('请选择文件上传!');
			return false;
		}
		$(".cover-all").fadeIn();
		$("#Form").submit();
	}
    
    function dhMapPage(){
    	var mobile = $("#mobile").val();
    	if(mobile== null || mobile=="" || mobile=="null" ||mobile=="undefined"){
    		mobile="13560033077";
    	}
    	location.href="${root }/customer/appStore/dhMapPage.do?mobile="+mobile;
    }
    
</script>
</html>