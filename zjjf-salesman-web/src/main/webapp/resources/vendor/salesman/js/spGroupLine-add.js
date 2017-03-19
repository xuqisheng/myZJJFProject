$(document).ready(function(){
	// 在这里写你的代码...
	$("#addLineBtn").bind("click", function(){
		var bakLineHtml = $("#bakDiv").html();
	    $("#lineContainer").append(bakLineHtml);
	});
	
	//弹出一个iframe层
	$('#openUserList').on('click', function(){
		var deptId = $("#deptSelect").val();
		if(deptId == null || deptId ==''){
			alert("部门信息不能为空！");
			return;
		}
	    layer.open({
	        type: 2,
	        title: '',
	        maxmin: true,
	        shadeClose: true, //点击遮罩关闭层
	        area : ['900px' , '350px'],
	        content: rootPath+"/account/toSalesmanPage.do?deptId="+deptId
	    });
	});
	
	/*$('#shopLinkList').on('click', function(){
	    layer.open({
	        type: 2,
	        title: '',
	        maxmin: true,
	        shadeClose: true, //点击遮罩关闭层
	        area : ['1300px' , '850px'],
	        content: rootPath+"/shop/toUnregisterList.do"
	    });
	});*/
	
	//初始化区域信息
	initRegion();
});


/*function addBindShopCallback(spGroupId,spGroupName,shopNos){
	
	$.ajax({
		type : "POST",
		url : rootPath+"/shop/toDayAll.do",
		data:"spGroupId="+spGroupId+"&spGroupName="+spGroupName+"&shopNo="+shopNos,
		dataType : "json",
		success : function(data) {
			if (data.success) {
				layer.closeAll('iframe'); //关闭所有的iframe层
			}
		},
		error : function(data) {
		}
	});
	
	layer.closeAll('iframe'); //关闭所有的iframe层
}*/