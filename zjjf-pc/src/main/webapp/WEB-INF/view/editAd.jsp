<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%	request.setAttribute("root", request.getContextPath());%>
<%@ include file="./taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>编辑广告</title>
    <link href="${root}/resources-admin/css/normalize.css" rel="stylesheet">
    <link href="${root}/resources-admin/css/comm.css" rel="stylesheet">
    <link href="${root}/resources-admin/css/ad-edit.css" rel="stylesheet">
    <script src="${root}/resources-admin/plugs/jquery/jquery-1.12.2.min.js"></script>
    <style>
    .main .input,.main .select{box-sizing:border-box;width:200px;height:28px;}
    .main .label{width:120px;height:28px;display:inline-block;}
    </style>
</head>
<body class="wrap-bd">
<div class="g-breadcrumb">
    主页 &raquo; <a class="active">广告编辑</a>
</div>
<div class="main">
	<form id="param">
		<p>
			<label class="label">排序：</label>
			<input type="text" id="orderId" name="orderId" value="${advertisement.orderId }" class="input">
		</p>
		<p>
			<label class="label">广告名称：</label>
			<input type="text" id="name" name="name" value="${advertisement.name }" class="input">
		</p>
		<p>
			<label class="label">广告位：</label>
			<select id="positionId" name="positionId" class="select">
				<option value="" selected="selected">请选择</option>
				<option value="0">首页广告</option>
			</select>
		</p>
		<p>
			<label class="label">广告图片：</label>
			<input type="text" id="adUrl" name="adUrl" value="${advertisement.adUrl }" class="input"><br>
			<label class="label"></label>
			<a href="http://www.izjjf.cn/${advertisement.adUrl }" target="_blank">
            	<img src="http://www.izjjf.cn/${advertisement.adUrl }" width="90" height="90" alt="缩略图" onerror="javascript:this.src='http://www.izjjf.cn/group1/M00/00/79/cEpChlbhNLKAPH1VAAAKDKR6XpI313.png'">
            </a>
		</p>
		<p>
			<label class="label">背景颜色：</label>
			<input type="text" id="backgroud" name="backgroud" value="${advertisement.backgroud }" class="input">
		</p>
		<p>
			<label class="label">图片对应的链接：</label>
			<input type="text" id="menuPage" name="menuPage" value="${advertisement.menuPage }" class="input">
		</p>
		<p>
			<label class="label">内容：</label>
			<script id="content" name="content" type="text/plain"></script>
			<script src="${root}/resources-admin/plugs/ueditor/ueditor.config.js"></script>
			<script src="${root}/resources-admin/plugs/ueditor/ueditor.all.min.js"></script>
			<script>
                   var ue = UE.getEditor('content',{
                   	initialFrameWidth:1000, //初始化宽度
                   	initialFrameHeight:500, //初始化高度
                   });
                   ue.addListener('ready', function () {
                       ue.setContent('${advertisement.content}');
                   });
            </script>
		</p>
		<p>
			<input type="button" value="提交" id="submit">
			<input type="button" value="取消" id="exit">
			<input type="hidden" id="id" name="id" value="${advertisement.id }">
		</p>
	</form>
</div>
<script type="text/javascript">
$(function(){
	$("#positionId").val('${advertisement.positionId }');
	
	$("#submit").on("click",function(){
		console.log(123)
		var id = $("#id").val();
		var orderId=$("#orderId").val();
		var name=$("#name").val();
		var positionId=$("#positionId").val();
		var adUrl=$("#adUrl").val();
		var content=$("#content").text();
		var backgroud = $("#backgroud").val();
		var menuPage = $("#menuPage").val();
		console.log(content);
		if(name ==""){
			alert("请输入广告名");
			return;
		}else if(positionId == ""){
			alert("请选择广告位");
			return;
		}else if(adUrl ==""){
			alert("请输入广告URL");
			return;
		}
		var param=null;
		var url="${root}/pc/advertisement/"
		if(id != null && id != '' && id != 'undefined'){
			url+="updateAdvertisement.do";
		}else{
			url+="addAdvertisement.do";
		}
		console.log(url)
		$.post(url,$("#param").serialize(),function(data){
			if(data.success){
				alert(data.message);
				location.href="${root}/pc/advertisement/gatAllAdvertisement.do";
			}else{
				alert(data.message);
			}
		},'json')
	})

	$("#exit").on("click",function(){
		location.href="${root}/pc/advertisement/gatAllAdvertisement.do";
	})
})
</script>
</body>
</html>