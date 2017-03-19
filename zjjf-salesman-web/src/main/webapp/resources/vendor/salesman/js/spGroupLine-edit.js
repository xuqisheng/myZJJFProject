$(document).ready(function(){
	// 在这里写你的代码...
	$("#addLineBtn").bind("click", function(){
		var bakLineHtml = $("#bakDiv").html();
	    $("#lineContainer").append(bakLineHtml);
	});
	
	//弹出一个iframe层
	$('#openUserList').on('click', function(){
		var deptId = $("#deptSelect").val();
		var provinceId = $("#tmpProvinceId").val();
		var cityId = $("#tmpCityId").val();
		var areaId = $("#tmpAreaId").val();
		var userIds = $("#hisUserId").val();
		//alert("province="+provinceId+"&city="+cityId+"&area="+areaId);
		
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
	        		+"&province="+provinceId+"&city="+cityId+"&area="+areaId+"&userIds="+userIds
	    });
	});
	
	//初始化区域信息
	initRegion();
});