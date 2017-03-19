<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>专区图片管理</title>
<%@ include file="../common/head.jsp"%>
<%@ include file="../common/autocomplete.jsp"%>
<script src="${root}/resources/vendor/jquery/ajaxfileupload.js"></script>
</head>
<body>
	<div>
		<div >
		<form action="" id="form">
			<c:forEach var="detail" items="${moduleDetailList }" varStatus="var">
				<div style="float: left">
					<input type="hidden" value="${detail.id }" class="ids" name='ids'>
					<span>${detail.name }</span>
					<div>
						<img style="display:block;width:100px;height:100px; alt="" src="${USER_FASTFDSPREURL}${detail.picUrl }" class="imgs" onerror="javascript:this.src='${USER_DEFAULTIMG_URL}'">
					</div>
					<input type="file" class="picUpload" id="picUpload${var.index+1 }" name="picUpload${var.index+1 }">
					<input type="hidden" value="${detail.picUrl }" class="picUrls" name="picUrls">
				</div>
			</c:forEach>
		</form>
		</div>
	</div>
	<div>
		<input class="button button-ok" type="button" value="确认" id="okImage">
		<input class="button button-cancel" type="button" value="取消" id="exit">
	</div>
</body>
<script type="text/javascript">
$("#okImage").on("click",function(){
	$.post("${root}/appModule/updateImage.do",$("#form").serializeArray(),function(data){
		if(data.success){
			alert(data.message);
			location.href="${root}/keFu/systemConfig/getAllConfig.do";
		}else{
			alert(data.message);
		}
	},'json');
});
$("#exit").on("click",function(){
	location.href="${root}/keFu/systemConfig/getAllConfig.do";
})

//上传图片功能
$(".picUpload").on("change", function() {
	var $element = $(this);
	if ($element.val() == null || $element.val() == "") {
		alert("请先选择上传图片");
		return;
	}
	var f = checkFileType($element);
	if (f) {
		ajaxFileUpload($element);
	}
});
function checkFileType(element) {
	var myFile = element;
	var filePath = myFile.val();
	var dotNdx = filePath.lastIndexOf('.');
	var exetendName = filePath.slice(dotNdx + 1).toLowerCase();
	if ((exetendName == "jpg" || exetendName == "jpeg" || exetendName == "gif" || exetendName == "png")) {
		return true;
	}
	alert("请选择正确的图片格式");
	return false;
} 

function ajaxFileUpload(element) {
	var $div = element.parent("div");
	var fileServiceName = '${USER_FASTFDSPREURL }';
	$.ajaxFileUpload({
		url : "${root}/customer/itemBases/upload.do",
		secureuri : false,
		fileElementId : element.attr("id"),
		dataType : 'text',
		success : function(data, status) {
			if (data == "错误") {
				alert("上传出错");
				return;
			}else{
				var resultData = data.substring(1, data.length-1);
				$div.find(".picUrls").val(resultData);
				$div.find(".imgs").attr('src',fileServiceName+resultData);
			}
		},
		error : function(data, status, e) {
			alert('上传出错');
		}
	});
}

</script>
</html>