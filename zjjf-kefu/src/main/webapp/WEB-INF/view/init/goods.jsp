<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>初始化商品</title>
<%@ include file="../common/head.jsp"%>
<script src="${root}/resources/vendor/jquery/ajaxfileupload.js"></script>
</head>
<body class="wrap-bd">
<div class="mt-default table-border" style="width:800px;">
	<div id="ads" style="padding: 6px 20px; background: #ccc; font-size:18px;">系统图片上传界面</div>
	<form style="padding:40px; background:#e1e1e1" id="ff" >
		<div style=" padding:10px;text-align:center" >
			<p id="p1">
				<span>图片上传：</span>
				<input type="file" name="uploadify1" id="uploadify1"/>
			</p>
			<p id="p2">
				<!-- <span>图片URL：</span>
				<span id="picUrl"></span> -->
			</p>
			<p id="p3">
				<!-- <span>图片预览：</span>
				<img id="picPreview"> -->
			</p>
		</div>
		<div style="padding:10px; text-align:center" >
			<button type="button" class="button button-ok" id="picUpload">提交</button>
		</div>
	</form>
</div>

<div class="mt-default table-border" style="width:800px;">
	<div id="" style="padding: 6px 20px; background: #ccc; font-size:18px;">商品初始化</div>
	<form style="padding:40px; background:#e1e1e1" id="fff">
	<div style="padding:10px;text-align:center" >
		<table>
			<tr><td align="right">源组：</td><td align="left"><select id="oldSpGroupId" name="oldSpGroupId" onchange="change(1)" class="select"><option value="-1">请选择</option></select><!-- <input type="text" name="oldSpGroupId"/> --></td></tr>
			<tr><td align="right">源组内批发商：</td><td align="left"><select id="oldSpId" name="oldSpId" class="select"></select><!-- <input type="text" name="oldSpId" style="width:300px;"/> --></td></tr>
			<tr>
				<td align="right">分类：</td>
				<td>
					1:<select onchange="selectErJiByYiJi()" id="yiJi">
					<option value="-1">请选择</option>
						<c:forEach var="yiJi" items="${itemCatelogYiJiList }">
							<option value="${yiJi.id }">${yiJi.name }</option>
						</c:forEach>
					</select>
					2:<select name="cateId" id="erJi">
					</select>
				</td>
			</tr>
			<tr><td align="right">状态：</td><td align="left"><input type="text" name="status" value="1" class="input input-default"></td></tr>
			<tr><td align="right">目标组：</td><td align="left"><select id="spGroupId" name="spGroupId" onchange="change(2)" class="select"><option value="-1">请选择</option></select><!-- <input type="text" name="spGroupId"/> --></td></tr>
			<tr><td align="right">目标组批发商：</td><td align="left"><select id="spId" name="spId" class="select"></select><!-- <input type="text" name="spId" style="width:300px;"/> --></td></tr>
		</table>
	</div>
	<div  style="padding:10px; text-align:center" ><button type="button" class="button button-ok" id="initOk">提交</button></div>
	</form>
</div>

<!-- <div class="mt-default table-border" style="width:800px;">
	<div id="ads" style="padding: 6px 20px; background: #DEDEDE; font-size:18px;">二维码生成界面</div>
	<div style="margin:20px">
	<p>
        <label for="storeId">店铺编码:</label>
        <input type="text" id="storeId" class="input input-default">
	</p>
	<p>
        <label for="storeName">店铺名称:</label>
        <input type="text" id="storeName" maxlength="300" size="40" class="input input-default">
	</p>
	<p>
        <button id="getqrcodeBut" class="button button-ok">生成数据</button>
	</p>
    </div>
    <div id="storeQRResult" style="margin:20px">
    	
    </div>
</div> -->

<script>
$(function() {
	//加载定格下拉框
	$.post("${root}/Customer/SpGroup/getAllSpGroup1.do",function(data){
		if(data.success){
			var html='<option value="-1">请选择</option>';
			$.each(data.message,function(i,item){
				html+='<option value="'+item.id+'">'+item.name+'</option>'
			});
			$("#oldSpGroupId").html(html);
			$("#spGroupId").html(html);
		}
	},'json');
});


//上传图片功能
$("#picUpload").on("click", function() {
	
	if ($("#uploadify1").val() == null || $("#uploadify1").val() == "") {
		alert("请先选择上传图片");
		return;
	}
	var f = checkFileType();
	if (f) {
		ajaxFileUpload();
	}
});
function checkFileType() {
	var myFile = document.getElementById("uploadify1");
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
		fileElementId : 'uploadify1',
		dataType : 'text',
		success : function(data, status) {
			if (data == "错误") {
				return;
			}
			alert("上传成功");
			var resultData = data.substring(1, data.length-1);
			$("#p2").html('<span>图片URL：</span><span id="picUrl">'+resultData+'</span>');
			$("#p3").html('<span>图片预览：</span><img id="picPreview" src='+fileServiceName + resultData+'>');
		},
		error : function(data, status, e) {
			alert('上传出错');
		}
	});
}




//根据一级商品查出二级商品
function selectErJiByYiJi(){
	var pid = $("#yiJi").val();
	$.post("${root}/customer/itemCatelog/getAllItemCateByPid.do",{pid:pid},function(data){
		if(data.success){
			var html = '';
			$.each(data.message,function(i,item){
				html+='<option value="'+item.id+'">'+item.name+'</option>'
			})
			$("#erJi").html(html);
			
		}
		
	},'json')
}

//选择定格查出下面的批发商
function change(num){
	var oldSpGroupId = $("#oldSpGroupId").val();
	var spGroupId = $("#spGroupId").val();
	var groupId = 0;
	if(num==1){
		groupId = oldSpGroupId;
	} else if(num == 2) {
		groupId = spGroupId;
	}

	$.post("${root}/Customer/SpGroup/getSupplierIdByGroupId.do",{groupId:groupId},function(data){
		if(data.success){
			var html='';
			$.each(data.message,function(i,item){
				console.log(item.suppliername);
				html+='<option value="'+item.id+'">'+item.supplierName+'</option>'
			});
			if(num==1){
				$("#oldSpId").html(html);
			}else if(num==2){
				$("#spId").html(html);
			}
		}else{
			if(num==1){
				$("#oldSpId").html("");
			}else if(num==2){
				$("#spId").html("");
			}
		}
	},'json');
}


//初始化商品数据
$("#initOk").click(function(){
	layer.msg('加载中', {icon: 16});
	$.ajax({
			type : "POST",
			url : "${root}/data/initSpPlantItem.do",
			dataType : "json",
			data:$('#fff').serialize(),
			success : function(data) {
				if (data.success) {
					alert(data.message);
					location.reload();
				}else{
					alert(data.message);
				}
			},
			error : function() {
				alert("请求失败！");
			}
		});  
})

/* $("#getqrcodeBut").click(function(){
	var storeId =  $("#storeId").val().trim();
	var storeName =  $("#storeName").val().trim();
	if(storeId == ''){
		alert("编码不能为空");
		return;
	}
	$("#getqrcodeBut").attr("disabled",false);
	$.ajax({
		url: "/CornerV2/Mobile/Code/anno/getStoreQrcode.do",
		data:{'storeId':storeId,'storeName':encodeURIComponent(storeName)},
		type: "POST",
		dataType:'jsonp',
		success:function(date){
			if(date.success){
				$("#getqrcodeBut").attr("disabled",true);
				var newValue =date.message.storeId +"</br>"+date.message.storeName+"</br>"+date.message.qrcodeUrl
				$("#storeQRResult").html(newValue);	
			}else{
				alert(date.message);
			}
		},
		error:function(er){
			alert("请求异常");
		},
		complete:function(){
			$("#getqrcodeBut").attr("disabled",false);				
		}
	});
}) */
</script>

</body>
</html>
