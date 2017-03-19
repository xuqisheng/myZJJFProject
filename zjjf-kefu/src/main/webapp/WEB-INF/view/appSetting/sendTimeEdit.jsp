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
<%@ include file="../common/ztree.jsp"%>
<script src="${root}/resources/js/comm.js"></script>
<script src="${root }/resources/js/ztree-search.js"></script>
</head>
<body class="wrap-bd">
	<div class="mb-default">
		<span>当前位置：</span>
		<a class="crumb" href="${root}/keFu/systemConfig/toListPage.do">送货时间配置</a>
	    <c:choose>
			<c:when test="${sendTimeConfig != null}">
        		<a class="crumb" href="#">编辑</a>
			</c:when>
			<c:otherwise>
        		<a class="crumb" href="#">添加</a>
			</c:otherwise>
		</c:choose>
    </div>
<div class="title mb-default">
	<c:choose>
		<c:when test="${sendTimeConfig != null}">
			编辑
		</c:when>
		<c:otherwise>
			添加
		</c:otherwise>
	</c:choose>
</div>
<input type="hidden" value="${sendTimeConfig.id }" name="id" id="id"><!-- 只有编辑时才有值 -->
<form id="form">
    <div class="wrap-bd bg table-border">
        <b>基础信息</b>
        <p class="required">
            <label class="label">名称：</label>
            <input type="text" name="name"  id="name" value="${sendTimeConfig.name }" placeholder="" class="input" maxlength="100" >
        </p>
        <p >
            <label class="label">备注：</label>
            <input type="text" name="remark"  id="remark" value="${sendTimeConfig.remark }" placeholder="" class="input" maxlength="100" style="width: 400px">
        </p >
        <p >
            <label class="label">时间：</label>
            <input type="text" name="budgetHour"  id="budgetHour" value="${sendTimeConfig.budgetHour }" placeholder="" class="input" maxlength="5" style="width: 50px">小时
        	<label>预计xxx时间送货</label>
        </p >
        <p>
            <label class="label">排序：</label>
            <input type="text" name="ordId"  id="ordId" value="${sendTimeConfig.ordId }" placeholder="" class="input" maxlength="5" style="width: 50px">
        </p>
        <p >
            <label class="label">状态：</label>
            <input type="radio" name="status" value="1" <c:if test="${sendTimeConfig == null || sendTimeConfig.status==1 }">checked="checked"</c:if> >正常
            &nbsp;&nbsp;&nbsp;&nbsp;
            <input type="radio" name="status" value="0" <c:if test="${sendTimeConfig.status==0 }">checked="checked"</c:if> >停用
        </p>
    </div>
    <div class="mt-default">
        <input type="button" value="保存" class="button button-ok" id="okSave">
        <input type="button" value="取消" class="button button-cancel" id="okClose" >
    </div>
</form>
<script>


$(function(){
	/* 保存 */
	$("#okSave").on("click",function(){
		var name = $('#name').val();
		if(name == ""){
			alert("名称不能为空");
			return;
		}
		var budgetHour = $('#budgetHour').val();
		
		if(budgetHour == ""){
			alert("时间不能为空");
			return;
		}
		var id = $("#id").val();
		//保存操作
		var url="";
		var $root = $(this);
		$root.attr("disabled","disabled");
		if(id == null || id=='undefined' || id==""){
			url="${root}/keFu/systemConfig/addSendTimeConfig.do";
		}else{
			url="${root}/keFu/systemConfig/updateSendTimeConfig.do?id="+id;
		}
		$.post(url,$("#form").serializeArray(),function(data){
			if(data.success){
				alert(data.message);
				location.href="${root}/keFu/systemConfig/toListPage.do";
			}else{
				alert(data.message);
				$root.removeAttr("disabled");
			}
		},'json');
	});

	/* 取消 */
	$("#okClose").on("click",function(){
		location.href="${root}/keFu/systemConfig/toListPage.do";
	});
});

</script>
</body>
</html>
