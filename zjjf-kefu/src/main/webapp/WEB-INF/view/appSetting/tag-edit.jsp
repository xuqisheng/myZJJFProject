<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>模块元素管理</title>
<%@ include file="../common/head.jsp"%>
<%@ include file="../common/autocomplete.jsp"%>
<script src="${root}/resources/vendor/jquery/ajaxfileupload.js"></script>
<script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
<script src="${root}/resources/js/comm.js"></script>
</head>
<body class="wrap-bd">
	<div class="mb-default">
		<span>当前位置：</span>
		<a class="crumb" href="${root}/kefu/appModule/getAllAppTag.do">app标签管理</a>
	    <c:choose>
			<c:when test="${appModuleDetail != null}">
        		<a class="crumb" href="#">编辑标签</a>
			</c:when>
			<c:otherwise>
        		<a class="crumb" href="#">添加标签</a>
			</c:otherwise>
		</c:choose>
    </div>
<div class="title mb-default">
	<c:choose>
		<c:when test="${appModuleDetail != null}">
			编辑模块元素
		</c:when>
		<c:otherwise>
			添加模块元素
		</c:otherwise>
	</c:choose>
</div>
<input type="hidden" value="${appModuleDetail.id }" name="id" id="id"><!-- 只有编辑时才有值 -->
<form id="form">
    <div class="wrap-bd bg table-border">
        <b>基础信息</b>
        <p class="required">
            <label class="label">元素名称：</label>
            <input type="text" name="name"  id="name" value="${appModuleDetail.name }" placeholder="" class="input" maxlength="50">
        </p>
        <p class="required">
            <label class="label">元素描述：</label>
            <input type="text" name="remark"  id="remark" value="${appModuleDetail.remark }" placeholder="" class="input" maxlength="50">
        </p>
        <p class="required">
            <label class="label">星期：</label>
            <select name="weekDay" id="weekDay" class="select">
            	<option value="">请选择</option>
            	<option value="1" <c:if test="${appModuleDetail.weekDay==1 }">selected="selected"</c:if>>1</option>
            	<option value="2" <c:if test="${appModuleDetail.weekDay==2 }">selected="selected"</c:if>>2</option>
            	<option value="3" <c:if test="${appModuleDetail.weekDay==3 }">selected="selected"</c:if>>3</option>
            	<option value="4" <c:if test="${appModuleDetail.weekDay==4 }">selected="selected"</c:if>>4</option>
            	<option value="5" <c:if test="${appModuleDetail.weekDay==5 }">selected="selected"</c:if>>5</option>
            	<option value="6" <c:if test="${appModuleDetail.weekDay==6 }">selected="selected"</c:if>>6</option>
            	<option value="7" <c:if test="${appModuleDetail.weekDay==7 }">selected="selected"</c:if>>7</option>
            </select>
        </p>
        <p class="required">
            <label class="label">时间：</label>
                                        开始：<input type="text" name="beginTime"  id="beginTime" value="<fmt:formatDate value="${appModuleDetail.beginTime }" pattern="HH:mm:ss"/>" placeholder="" class="input J_TIME_START" maxlength="50">
                                        结束：<input type="text" name="stopTime"  id="stopTime" value="<fmt:formatDate value="${appModuleDetail.stopTime }" pattern="HH:mm:ss"/>" placeholder="" class="input J_TIME_END" maxlength="50">
        </p>
        <input type="hidden" value="${appModuleId }" name="appModuleId" id="appModuleId">
        <p class="required">
            <label class="label">元素位置：</label>
            <input type="text" name="detailPosition"  id="detailPosition" value="${appModuleDetail.detailPosition }" placeholder="" class="input" maxlength="3">
        </p>
        <p class="required">
            <label class="label">是否可点击：</label>
            <input type="radio" name="canClick"  id="canClick" value="0" class="radio ml-default canClick" <c:if test="${appModuleDetail==null || appModuleDetail.canClick==false}">checked="checked"</c:if>> 是
            <input type="radio" name="canClick"  id="canClick" value="1" class="radio ml-default canClick" <c:if test="${appModuleDetail.canClick==true}">checked="checked"</c:if>> 否
        </p>
        <p >
            <label class="label">标贴1：</label>
            <select name="tagId1" id="tagId1" class="select">
                <option value="">请选择</option>
                <c:if test="${labelList != null }">
                	<c:forEach items="${labelList}" var="label">
                		<option value="${label.id }" <c:if test="${appModuleDetail.tagId1==label.id }">selected="selected"</c:if>>${label.name }</option>
                	</c:forEach>
                </c:if>
            </select>
        </p>
        <p >
            <label class="label">标贴2：</label>
            <select name="tagId2" id="tagId2" class="select" >
                <option value="">请选择</option>
                <c:if test="${labelList != null }">
                	<c:forEach items="${labelList}" var="label">
                		<option value="${label.id }" <c:if test="${appModuleDetail.tagId2==label.id }">selected="selected"</c:if>>${label.name }</option>
                	</c:forEach>
                </c:if>
            </select>
        </p>
        <p >
            <label class="label">标贴3：</label>
            <select name="tagId3" id="tagId3" class="select">
                <option value="">请选择</option>
                <c:if test="${labelList != null }">
                	<c:forEach items="${labelList }" var="label">
                		<option value="${label.id }" <c:if test="${appModuleDetail.tagId3==label.id }">selected="selected"</c:if>>${label.name }</option>
                	</c:forEach>
                </c:if>
            </select>
        </p> 
        <p >
            <label class="label">标题1：</label>
            <input type="text" name="title1"  id="title1" value="${appModuleDetail.title1 }" placeholder="" class="input" maxlength="50">
            &nbsp;&nbsp;&nbsp;&nbsp;
            <label class="label">颜色：</label>
            <input type="text" name="color1"  id="color1" value="${appModuleDetail.color1 }" placeholder="" class="input" maxlength="50">
        </p>
        <p >
           <label class="label">标题2：</label>
           <input type="text" name="title2"  id="title2" value="${appModuleDetail.title2 }" placeholder="" class="input" maxlength="50">
           &nbsp;&nbsp;&nbsp;&nbsp;
           <label class="label">颜色：</label>
           <input type="text" name="color2"  id="color2" value="${appModuleDetail.color2 }" placeholder="" class="input" maxlength="50">
        </p>
        <p >
           <label class="label">标题3：</label>
           <input type="text" name="title3"  id="title3" value="${appModuleDetail.title3 }" placeholder="" class="input" maxlength="50">
           &nbsp;&nbsp;&nbsp;&nbsp;
           <label class="label">颜色：</label>
           <input type="text" name="color3"  id="color3" value="${appModuleDetail.color3 }" placeholder="" class="input" maxlength="50">
        </p>
        <p>
            <label class="label">图片：</label>
            <img alt="" src="${USER_FASTFDSPREURL}${appModuleDetail.picUrl }" id="img" onerror="javascript:this.src='${USER_DEFAULTIMG_URL}'">
            <input type="file" id="file" name="file">
            <input type="text" name="picUrl"  id="picUrl" value="${appModuleDetail.picUrl }" placeholder="" class="input">
        </p>
        <p >
            <label class="label">跳转类型：</label>
            <input type="radio" name="skipType"  id="skipType" value="0" class="radio ml-default" <c:if test="${appModuleDetail==null || appModuleDetail.skipType==0}">checked="checked"</c:if>> 商品列表
            <input type="radio" name="skipType"  id="skipType" value="1" class="radio ml-default" <c:if test="${appModuleDetail.skipType==1}">checked="checked"</c:if>> 嵌入页面
        </p>
         <p >
            <label class="label">广告位：</label>
            <select name="adBoardId" id="adBoardId" class="select">
            	<option value="">请选择</option>
            	<c:forEach var="board" items="${boardList }">
            		<option value="${board.id }" <c:if test="${board.id==appModuleDetail.adBoardId }">selected="selected"</c:if> >${board.name }</option>
            	</c:forEach>
            </select>
        </p>
        <p class="required">
            <label class="label">排序：</label>
            <input type="text" name="orderId"  id="orderId" value="${appModuleDetail.orderId }" placeholder="" class="input" maxlength="3">
        </p>
    </div>
    <div class="mt-default">
        <input type="button" value="保存" class="button button-ok" id="okSave">
        <input type="button" value="取消" class="button button-cancel" id="okClose" >
    </div>
</form>
<script>

$(function(){
	dateRangeHms('.J_TIME_START', '.J_TIME_END');
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
					$("#picUrl").val(resultData);
				}
			},
			error : function(data, status, e) {
				alert('上传出错');
			}
		});
	}
	
	/* $(".canClick").on("click",function(){
		if($(this).val()==0){
			$("#p_skipType").show();
			$("#p_skipObject").show();
		}else{
			$("#p_skipType").hide();
			$("#p_skipObject").hide();
		}
	}) */
	/* 保存 */
	$("#okSave").on("click",function(){
		var id = $("#id").val();
		//参数校验

		//保存操作
		var url="";
		var $root = $(this);
		$root.attr("disabled","disabled");
		if(id == null || id=='undefined' || id==""){
			url="${root}/kefu/appModule/addModuleDetail.do";
		}else{
			url="${root}/kefu/appModule/updateModuleDetail.do?id="+id;
		}
		$.post(url,$("#form").serializeArray(),function(data){
			if(data.success){
				alert(data.message);
				location.href="${root }/kefu/appModule/getAllAppTag.do";
			}else{
				alert(data.message);
				$root.removeAttr("disabled");
			}
		},'json');
	});

	/* 取消 */
	$("#okClose").on("click",function(){
		location.href="${root }/kefu/appModule/getAllAppTag.do";
	});
});

</script>
</body>
</html>
