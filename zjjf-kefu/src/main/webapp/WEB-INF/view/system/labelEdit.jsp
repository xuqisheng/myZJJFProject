<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>标贴管理</title>
<%@ include file="../common/head.jsp"%>
<%@ include file="../common/autocomplete.jsp"%>
<script src="${root}/resources/vendor/jquery/ajaxfileupload.js"></script>
</head>
<body class="wrap-bd">
	<div class="mb-default">
		<span>当前位置：</span>
		<a class="crumb" href="${root}/kefu/appItemLabel/toList.do">标贴管理</a>
	    <c:choose>
			<c:when test="${itemLabel != null}">
        		<a class="crumb" href="#">编辑标贴</a>
			</c:when>
			<c:otherwise>
        		<a class="crumb" href="#">添加标贴</a>
			</c:otherwise>
		</c:choose>
    </div>
<div class="title mb-default">
	<c:choose>
		<c:when test="${itemLabel != null}">
			编辑标贴
		</c:when>
		<c:otherwise>
			添加标贴
		</c:otherwise>
	</c:choose>
</div>
<input type="hidden" value="${itemLabel.id }" name="id" id="id"><!-- 只有编辑时才有值 -->
<form id="form">
    <div class="wrap-bd bg table-border">
        <b>基础信息</b>
        <p class="required">
            <label class="label">标贴名称：</label>
            <input type="text" name="name"  id="name" value="${itemLabel.name }" placeholder="" class="input" maxlength="50">
        </p>
        <p class="required">
            <label class="label">标贴描述：</label>
            <input type="text" name="remark"  id="remark" value="${itemLabel.remark }" placeholder="" class="input" maxlength="50">
        </p>
        <p>
            <label class="label">标贴图片：</label>
            <img alt="" src="${USER_FASTFDSPREURL}${itemLabel.tagImg }" id="img" onerror="javascript:this.src='${USER_DEFAULTIMG_URL}'">
            <input type="file" id="file" name="file">
            <input type="text" name="tagImg"  id="tagImg" value="${itemLabel.tagImg }" placeholder="" class="input">
        </p>
    </div>
    <div class="mt-default">
        <input type="button" value="保存" class="button button-ok" id="okSave">
        <input type="button" value="取消" class="button button-cancel" id="okClose" >
    </div>
</form>
<script>
$(function(){
	//上传图片功能
	$("#file").on("change", function() {
		if ($("#file").val() == null || $("#file").val() == "") {
			alert("请先选择上传图片");
			return;
		}
		var f = checkFileType();
		if (f) {
			ajaxFileUpload();
		}
	});
	function checkFileType() {
		var myFile = document.getElementById("file");
		var filePath = myFile.value;
		var dotNdx = filePath.lastIndexOf('.');
		var exetendName = filePath.slice(dotNdx + 1).toLowerCase();
		if ((exetendName == "jpg" || exetendName == "jpeg" || exetendName == "gif" || exetendName == "png")) {
			return true;
		}
		alert("请选择正确的图片格式");
		return false;
	} 

	function ajaxFileUpload() {
		var fileServiceName = '${USER_FASTFDSPREURL }';
		$.ajaxFileUpload({
			url : "${root}/customer/itemBases/upload.do",
			secureuri : false,
			fileElementId : 'file',
			dataType : 'text',
			success : function(data, status) {
				if (data == "错误") {
					alert("上传出错");
					return;
				}else{
					var resultData = data.substring(1, data.length-1);
					$("#img").attr("src",fileServiceName+resultData);
					$("#tagImg").val(resultData);
				}
			},
			error : function(data, status, e) {
				alert('上传出错');
			}
		});
	}
	
	/* 保存 */
	$("#okSave").on("click",function(){
		var id = $("#id").val();
		//保存操作
		var url="";
		var $root = $(this);
		$root.attr("disabled","disabled");
		if(id == null || id=='undefined' || id==""){
			url="${root}/kefu/appItemLabel/addAppItemLabel.do";
		}else{
			url="${root}/kefu/appItemLabel/updateAppItemLabel.do?id="+id;
		}
		$.post(url,$("#form").serializeArray(),function(data){
			if(data.success){
				alert(data.message);
				location.href="${root}/kefu/appItemLabel/toList.do";
			}else{
				alert(data.message);
				$root.removeAttr("disabled");
			}
		},'json');
	});

	/* 取消 */
	$("#okClose").on("click",function(){
		location.href="${root}/kefu/appItemLabel/toList.do";
	});
});

</script>
</body>
</html>
