<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>商品库 - 商品品牌</title>
    <%@ include file="../common/head.jsp"%>
    <%@ include file="../common/autocomplete.jsp"%>
    <script src="${root}/resources/vendor/jquery/ajaxfileupload.js"></script>
    <script src="${root}/resources/js/comm.js"></script>
</head>
<body class="wrap-bd">
<div>
    位置：<a class="crumb" href="javascript: void(0)">品牌管理</a><a href="${root}/customer/brand/getAllBrandByParam.do?pageIndex=${pageIndex}" class="crumb">品牌列表</a><a class="crumb crumb-active">新增品牌</a>
</div>
<div class="wrap-bd bg mt-default">
    <form method="post" encType="multipart/form-data">
    <div>
        <label for="number" class="label">品牌编号：</label>
        <input type="text" name="brandNo" value="${brand.brandNo }" class="input input-default" id="brandNo" placeholder="" maxlength="10">
        <input type="hidden" name="id" value="${brand.id }" id="id">
        <input type="hidden" name="brandNoA" id="brandNoA" value="${brand.brandNo }">
    </div>
    <p>
        <label for="name" class="label">品牌名称：</label>
        <input type="text" name="name" value="${brand.name }" class="input input-default" id="name" maxlength="25">
    </p>
    <p>
        <label for="manufacturer" class="label">品牌商：</label>
        <input class="input input-default" type="text" value="${brand.s_name }" id="brandName" >
        <input type="hidden" id ="upId" name="upId" value="${brand.upId }">
    </p>
    <script type="text/javascript">
        $('#brandName').on('keydown' , function(){
        	$("#upId").val("");
        });
        $('#brandName').autocomplete({
            serviceUrl: '${root}/customer/brand/getBrandByName.do?str=branding',
            paramName: 'brandName',
            transformResult: function(response) {
 	            var res = JSON.parse(response)
 	            if(res.message != null) {
 		           	return {
 		            	suggestions: $.map(res.message, function(value, key) {
 		                 	return { value: value.name, data: value };
 		            	})
 		            };
 	            } else {
 	            	return {
 		            	suggestions: [{ value: "无数据"}]
 		            };
 		            
 	            }
            },
            onSelect: function(dd) {
            	if(dd.value==="无数据") {
               		$(this).val("");
                	return;
            	}
 				$("#upId").val(dd.data.id);
            }
       });
        </script>
    <div>
        <label class="label va-t">品牌logo：</label>
        <div class="dis-ib mt-default">
        	<img alt="" src="${USER_FASTFDSPREURL}${brand.logo}" id="logoImg" onerror="javascript:this.src='${USER_DEFAULTIMG_URL}'" height="145" width="145">
        </div>
        <input type="file" class="fileClass" n="1" id="file1" name="file1">
        <input type="hidden" name="logo" value="${brand.logo}" class="input input-default" id="logo" maxlength="25">
    </div>
    <div class="mt-small">
        <label class="label va-t">广告图：</label>
         <div class="dis-ib mt-default">
         	<img alt="" src="${USER_FASTFDSPREURL}${brand.imgB}" id="imgBImg" onerror="javascript:this.src='${USER_DEFAULTIMG_URL}'" height="145" width="145">
         </div>
         <input type="file" class="fileClass" n="2" id="file2" name="file2">
         <input type="hidden" name="imgB" value="${brand.imgB}" class="input input-default" id="imgB" maxlength="25">
    </div>
    <p>
        <label for="demo" class="label">备注：</label>
        <input type="text" name="remark" value="${brand.remark}" class="input input-default" id="remark" maxlength="50">
    </p>
    <p>
        <label for="demo" class="label">排序：</label>
        <input type="text" name="ordId" value="${brand.ordId}" class="input input-default" id="ordId" maxlength="50">
    </p>
    <div class="dialog-foot">
        <input type="button" value="确定" class="button button-ok">
        <input type="button" value="取消" class="button button-cancel">
    </div>
    </form>
</div>
</body>
<script >
var pageIndex = "${pageIndex}";
//图片上传
$(".fileClass").on("change",function(){
	var n = $(this).attr("n");
	console.log(n);
	if ($(this).val() == null || $(this).val() == "") {
		alert("请先选择上传图片");
		return;
	}
	var f = checkFileType($(this));
	if (f) {
		ajaxFileUpload($(this),n);
	} 
});
function checkFileType(ele) {
	var myFile = ele;
	var filePath = myFile.val();
	var dotNdx = filePath.lastIndexOf('.');
	var exetendName = filePath.slice(dotNdx + 1).toLowerCase();
	if ((exetendName == "jpg" || exetendName == "jpeg" || exetendName == "gif" || exetendName == "png")) {
		return true;
	}
	alert("请选择正确的图片格式");
	return false;
} 

function ajaxFileUpload(ele,n) {
	var fileServiceName = '${USER_FASTFDSPREURL }';
	$.ajaxFileUpload({
		url : "${root}/customer/itemBases/upload.do",
		secureuri : false,
		fileElementId : ele.attr("id"),
		dataType : 'text',
		success : function(data, status) {
			if (data == "错误") {
				alert("上传出错");
				return;
			}else{
				var resultData = data.substring(1, data.length-1);
				if(n=="1"){
					$("#logoImg").attr("src",fileServiceName+resultData);
					$("#logo").val(resultData);
				}else if(n=="2"){
					$("#imgBImg").attr("src",fileServiceName+resultData);
					$("#imgB").val(resultData);
				}
				
			}
		},
		error : function(data, status, e) {
			alert('上传出错');
		}
	}) 
}

function submit(){
	var id=$("#id").val();
	var brandNo = $.trim($("#brandNo").val());
	var remark = $.trim($("#remark").val());
	var upId = $("#upId").val();
	var name=$.trim($("#name").val());
	var logo = $.trim($("#logo").val());
	var imgB = $.trim($("#imgB").val());
	var ordId = $.trim($("#ordId").val());
	var str = /^[0-9a-zA-Z]{1,10}$/;
	if(!str.test(brandNo)){
		alert("品牌编号为10位的数字或字母");
		$("#brandNo").focus();
		return;
	}else if(name==""){
		alert("品牌名称不能为空！");
		$("#name").focus();
		return;
	}else if(upId=="" || upId==null){
		alert("请输入品牌商！");
		return;
	}
	
	var url="${root}/customer/brand/";
	var param=null;
	if($("#id").val()=="" || $("#id").val()==null){
		url+="addBrand.do";
		param = {name:name,brandNo:brandNo,remark:remark,upId:upId,logo:logo,imgB:imgB,ordId:ordId};
	}else{
		url+="updateBrand.do";
		param = {id:id,name:name,brandNo:brandNo,remark:remark,upId:upId,logo:logo,imgB:imgB,ordId:ordId};
	}
	
	$.post(url,param,function(data){
		if(data.success){
			alert(data.message);
			location.href="${root}/customer/brand/getAllBrandByParam.do?pageIndex="+pageIndex;
		}else{
			alert(data.message);
		}
	},'json');
}

$(".button-ok").click(function(){
	var id=$("#id").val();
	var brandNo = $.trim($("#brandNo").val());
	var remark = $.trim($("#remark").val());
	var upId = $("#upId").val();
	var name=$.trim($("#name").val());
	var str = /^[^\u4e00-\u9fa5]{0,}$/;
	if(brandNo==""){
		alert("品牌编号不能为空！");
		return;
	}
	//验证编号唯一性
	var flag = true;
	if($.trim(brandNo)!=$.trim($("#brandNoA").val())&&$.trim(brandNo)!=""){
		$.post("${root}/customer/brand/chickBrandNo.do",{brandNo:brandNo},function(data){
			if(data.success){
				alert(data.message);
				$("#brandNo").val("");
				$("#brandNo").focus();
				return false;
			}else{
				submit();
			}
		},'json');
		
	}else{
		submit();
	}
}); 

$(".button-cancel").on("click",function(){
	location.href="${root}/customer/brand/getAllBrandByParam.do?pageIndex="+pageIndex;
})
</script>
</html>
