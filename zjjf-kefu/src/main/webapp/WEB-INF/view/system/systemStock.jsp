<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>设置库存</title>
<%@ include file="../common/head.jsp"%>
<script src="${root }/resources/js/comm.js"></script>
</head>
<body>
	<div class="wrap-bd">
		<div class="mb-default">
			<a class="crumb" href="../../keFu/systemConfig/getAllConfig.do">参数设置</a><a class="crumb crumb-active">设置库存</a>
		</div>
		<fieldset class="wrap-bd">
			<div>
				<label class="label">增加库存：</label>
				<input class="input input-default" type="text" id="stockNum" maxlength="4">
			</div>
			<p>
				<label class="label"></label>
				<input type="button" id="saveButton" value="确定" class="button button-ok">
				<input type="button" value="取消" class="button button-cancel" id="button-cancel">
			</p>
		</fieldset>
	</div>
	<div class="cover-all"></div>
</body>
<script>
	$("#saveButton").on("click",function(){
		if(confirm("此功能会修改所有批发商的库存,确定要修改吗？")){
			var stockNumChick = /^[0-9]{0,4}$/;
			var stockNum = $("#stockNum").val();
			if(stockNum==null || stockNum=="" || stockNum=='null' || stockNum=='undefiend'){
				stockNum=0;
			}
			if(!stockNumChick.test(stockNum)){
				alert("数量输入有误！");
				return;
			}
			$.post("${root}/keFu/systemConfigDetail/updateAllGoodsStock.do",{stockNum:stockNum},function(data){
				if(data.success){
					alert(data.message);
					location.href="${root}/keFu/systemConfig/getAllConfig.do";
				}else{
					alert(data.message);
				}
			},'json');
		}
	})
	
	//取消
    $("#button-cancel").on("click",function(){
    	location.href="${root}/keFu/systemConfig/getAllConfig.do";
    })
	
	
</script>
</html>
