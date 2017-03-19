<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
		<a class="crumb" href="${root }/kefu/appModule/getAppModuleList.do">app模块管理</a>
	    <c:choose>
			<c:when test="${appModule != null}">
        		<a class="crumb" href="#">编辑模板</a>
			</c:when>
			<c:otherwise>
        		<a class="crumb" href="#">添加模板</a>
			</c:otherwise>
		</c:choose>
    </div>
<div class="title mb-default">
	<c:choose>
		<c:when test="${appModule != null}">
			编辑模板
		</c:when>
		<c:otherwise>
			添加模板
		</c:otherwise>
	</c:choose>
</div>
<input type="hidden" value="${appModule.id }" name="id" id="id"><!-- 只有编辑时才有值 -->
<form id="form">
    <div class="wrap-bd bg table-border">
        <b>基础信息</b>
        <p class="required">
            <label class="label">模块：</label>
            <select name="code" id="code" class="select" style="width: 200px">
            	<option value="">请选择</option> 
            	<option value="01">分类滚动列表</option> 
            	<option value="10">首页快捷按钮</option> 
            	<option value="11">首页导航</option>
            	<option value="12">首页搜索</option>
            	<option value="20">爆款推荐</option>
            	<option value="21">促销专区</option>
            	<option value="22">品牌促销</option>
            	<option value="23">品牌专区</option>
            	<option value="24">抢购专区</option>
            	<option value="30">滚动广告</option>
            	<option value="40">转角公告</option>
            </select>
        </p>
        <p class="required">
            <label class="label">模板名称：</label>
            <input type="text" name="name"  id="name" value="${appModule.name }" placeholder="" class="input" maxlength="50" style="width: 200px">
        </p>
        <p class="required">
            <label class="label">模板描述：</label>
            <input type="text" name="remark"  id="remark" value="${appModule.remark }" placeholder="" class="input" maxlength="50" style="width: 200px">
        </p>
        <p class="required">
            <label class="label">时间：</label>
                                       开始：<input type="text" name="beginTime"  id="beginTime" value="<fmt:formatDate value="${appModule.beginTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="" class="input J_TIME_START" maxlength="50">
                                       结束：<input type="text" name="stopTime"  id="stopTime" value="<fmt:formatDate value="${appModule.stopTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="" class="input J_TIME_END" maxlength="50">
        </p>
        <p>
            <label class="label">样板图片：</label>
            <img alt="" src="${USER_FASTFDSPREURL}${appModule.viewImg }" id="img" onerror="javascript:this.src='${USER_DEFAULTIMG_URL}'">
            <input type="file" id="file" name="file">
            <input type="text" name="viewImg"  id="viewImg" value="${appModule.viewImg }" placeholder="" class="input" style="width: 500px">
        </p>
        
        <p class="required">
            <label class="label">模板类型：</label>
            <select name="moduleType" id="moduleType" class="select" style="width: 200px">
            	<option value="">请选择</option>
            	<option value="1">商品标签</option>
            	<option value="2">商品列表</option>
            	<option value="3">广告模板</option>
            </select>
        </p>
        <p class="required">
            <label class="label">模板大小：</label>
                                        宽度：<input type="text" name="mdWidth"  id="mdWidth" value="${appModule.mdWidth }" placeholder="" class="input" maxlength="3">
                                        高度：<input type="text" name="mdHight"  id="mdHight" value="${appModule.mdHight }" placeholder="" class="input" maxlength="3">
        </p>
        <p class="required">
            <label class="label">调用链接：</label>
            <input type="text" name="cfgUrl"  id="cfgUrl" value="${appModule.cfgUrl }" placeholder="" class="input" maxlength="225">
        </p>
        <p class="required">
            <label class="label">模板数据：</label>
                                        起始页：<input type="text" name="cfgPageIndex"  id="cfgPageIndex" value="${appModule.cfgPageIndex }" placeholder="" class="input" maxlength="3">
                                        页面数量：<input type="text" name="cfgPageSize"  id="cfgPageSize" value="${appModule.cfgPageSize }" placeholder="" class="input" maxlength="3">
        </p>
        <p class="required">
            <label class="label">广告位：</label>
            <select name="cfgAdBoardId" id="cfgAdBoardId" class="select">
            	<option value="">请选择</option>
            	<c:forEach var="board" items="${boardList }">
            		<option value="${board.id }" <c:if test="${appModule.cfgAdBoardId == board.id}">selected="selected"</c:if>>${board.name }</option>
            	</c:forEach>
            </select>
        </p>
        <p class="required">
            <label class="label">是否可见：</label>
            <input type="radio" name="seeAble" value="0" class="radio ml-default canClick" <c:if test="${appModule==null || appModule.seeAble==false}">checked="checked"</c:if>> 否
            <input type="radio" name="seeAble" value="1" class="radio ml-default canClick" <c:if test="${appModule.seeAble==true}">checked="checked"</c:if>> 是
        </p>
        <p class="required">
            <label class="label">排序：</label>
            <input type="text" name="orderId"  id="orderId" value="${appModule.orderId }" placeholder="" class="input" maxlength="3">
        </p>
        <p class="required">
            <label class="label">AppItemTag：</label>
            &nbsp;&nbsp;&nbsp;
           	<c:forEach var="item" items="${tagList}">
           		<input type="checkbox" value="${item.id}" name="itemTagRadio">${item.name} 
           	</c:forEach>
            <%-- <input type="text" name="orderId"  id="orderId" value="${appModule.orderId }" placeholder="" class="input" maxlength="3"> --%>
        </p>
    </div>
    <div class="mt-default">
        <input type="button" value="保存" class="button button-ok" id="okSave">
        <input type="button" value="取消" class="button button-cancel" id="okClose" >
    </div>
</form>
<script>
$(function(){
	dateRange('.J_TIME_START', '.J_TIME_END');
	//回写
	var appModule = '${appModule}';
	if(appModule != null && appModule != ""){
		var jsonModuleStr = '${jsonModule}';
		var jsonModule = JSON.parse(jsonModuleStr);
		$('#code').val(jsonModule.code);
		$('#moduleType').val(jsonModule.moduleType);
		$('#cfgAdBoardId').val(jsonModule.cfgAdBoardId);
	}
	
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
					$("#viewImg").val(resultData);
				}
			},
			error : function(data, status, e) {
				alert('上传出错');
			}
		});
	}
	
	$("#okSave").on("click",function(){
		var id = $("#id").val();
		//参数校验

		//保存操作
		var url="";
		var $root = $(this);
		$root.attr("disabled","disabled");
		if(id == null || id=='undefined' || id==""){
			url="${root}/kefu/appModule/addModule.do";
		}else{
			url="${root}/kefu/appModule/updateModule/"+id+".do";
		}
		$.post(url,$("#form").serializeArray(),function(data){
			if(data.success){
				alert(data.message);
				location.href="${root }/kefu/appModule/getAppModuleList.do";
			}else{
				alert(data.message);
				$root.removeAttr("disabled");
			}
		},'json');
	});

	/* 取消 */
	$("#okClose").on("click",function(){
		location.href="${root }/kefu/appModule/getAppModuleList.do";
	});
	
	
	
	//显示AppItemTag选择
	var tagMapListJsonStr = '${tagMapListJsonStr}';
	
	if(tagMapListJsonStr!=''){
	    var tagMapListJson = JSON.parse(tagMapListJsonStr);
	    $(tagMapListJson).each(function(i,item){
	    	//console.log(item);
	    	$('input[type="checkbox"][name="itemTagRadio"]').each(function(){
	    		if(item.itemTagId==$(this).val()){
	    			$(this).attr('checked',true);
	    		}
	    	});
	    });
	}
});

</script>
</body>
</html>
