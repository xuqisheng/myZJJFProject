<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Excel导入</title>
<%@ include file="../common/head.jsp"%>
<script src="${root}/resources/vendor/jquery/jquery-1.12.0.min.js"></script>
<script src="${root}/resources/vendor/jquery/jquery.form.js"></script>
 <script>
 	var rootPath="${root}";
 </script>
</head>
<body style="background-color: #fff;">
	<div style="margin-top: 50px;margin-left:80px;">
		<form id="importForm" method="POST" enctype="multipart/form-data">
			<div>
				<input id="importFile" type="file" name="file"/>  
				<a id="downloadTmpl" style="float: right: 0px;" href="${root}/shop/download.do?fileName=shopTemplete.xlsx">下载数据模板</a>
	   		</div>
	   		<div style="margin-top: 10px;">
	   			<input type="button" class="input-search-button" value="导入" onclick="importExcel();"/>  
	   			<input type="button" class="input-search-button" value="取消" onclick="parent.closeCallback();"/>  
	   		</div>
		</form>
		
	</div>
</body>
<script type="text/javascript">
 
function importExcel(){
	
	var importFile = $("#importFile").val();
	
	if(importFile == null || importFile ==''){
		alert("请选择导入Excel文件");
		return;
	}
	
	var a=importFile.lastIndexOf("."); 
	var str=importFile.substring(a+1);
	var imgArr = [ "xlsx","xls"];
    if($.inArray(str, imgArr)=='-1'){
       alert('请选择正确的图片格式');
       return;
    } 
	
	
    var options = {  	
	    	 url: rootPath+"/shop/importExcel.do",       
			 dataType: 'json', 
			 contentType: "application/json; charset=utf-8",    
			 success: function(data) { 
				// 如果图片上传成功则保存表单注册数据  
				 if(data.success){
			    	 alert(data.message);
			    	 parent.closeCallback();//成功关闭弹出窗口
			     }else{
			    	 alert(data.message);
			     }
	         }   
	    };    
	    // 提交表单   
	    $('#importForm').ajaxSubmit(options);
}

</script>
</html>