<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	var rootPath="${root}";
</script>
<script src="${root}/resources/vendor/jquery/jquery-1.12.0.min.js"></script>
<script type="text/javascript" src="${root}/resources/vendor/webuploader/js/webuploader.min.js"></script>
<script type="text/javascript" src="${root}/resources/vendor/webuploader/js/upload.js"></script>
<link rel="stylesheet" type="text/css" href="${root}/resources/vendor/webuploader/css/style.css"/> 
<link rel="stylesheet" type="text/css" href="${root}/resources/vendor/webuploader/css/webuploader.css"/> 
</head>
<body>
	<input type="hidden" id="picUrl" name="picUrl"/>
	<!-- editShopPic 该值是为了标识修改客户信息图片上传页面所用 -->
	<input type="hidden" id="pageMark" value="editShopPic">
    <div id="wrapper">
        <div id="container">
            <!--头部，相册选择和格式选择-->
            <div id="uploader">
                <div class="queueList">
                    <div id="dndArea" class="placeholder">
                        <div id="filePicker"></div>
                        <p>或将照片拖到这里，单次最多可选300张</p>
                    </div>
                </div>
                <div class="statusBar" style="display:none;">
                    <div class="progress">
                        <span class="text">0%</span>
                        <span class="percentage"></span>
                    </div><div class="info"></div>
                    <div class="btns">
                        <div id="filePicker2"></div><div class="uploadBtn">开始上传</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>