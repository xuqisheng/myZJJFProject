$(document).ready(function(){
	
	$('#shopLinkList').on('click', function(){
		var provinceId = $("#tmpProvinceId").val();
		var cityId = $("#tmpCityId").val();
		var areaId = $("#tmpAreaId").val();
		var spGroupId = $("#tmpSpGroupId").val();
		
	    layer.open({
	        type: 2,
	        title: '',
	        maxmin: true,
	        shadeClose: true, //点击遮罩关闭层
	        area : ['1300px' , '850px'],
	        content: rootPath+"/shop/toUnregisterList.do?provinceId="+provinceId
	                 +"&cityId="+cityId+"&areaId="+areaId+"&spGroupId="+spGroupId
	    });
	});
	
});

function addBindShopCallback(spGroupId,spGroupName,shopNos){
	
	$.ajax({
		type : "POST",
		url : rootPath+"/shop/updateBindShop.do",
		data:"spGroupId="+spGroupId+"&spGroupName="+spGroupName+"&shopNo="+shopNos+"&isRegister=1",
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
}

function initRegion(){
	var provinceId = $("#tmpProvinceId").val();
	var cityId = $("#tmpCityId").val();
	var areaId = $("#tmpAreaId").val();
	var deptId = $("#tmpDeptId").val();
	var spGroupId = $("#tmpSpGroupId").val();
	
	
	$("#deptSelect").val(deptId);
	$("#provinceSelect").val(provinceId);
	//如果省份ID不为空，则查询对应省份对应城市
	if(provinceId != null && provinceId !=""){
		//alert("provinceId="+provinceId);
		getRegionInfo(provinceId,"citySelect",1);
		$("#citySelect").val(cityId);
	}
	//如果城市ID不为空，则查询对应省份对应城市
	if(cityId != null && cityId !=""){
		//alert("cityId="+cityId);
		getRegionInfo(cityId,"areaSelect",1);
		$("#areaSelect").val(areaId);
	}
	//如果区域ID不为空，则查询对应省份对应城市
	if(areaId != null && areaId !=""){
		//alert("areaId="+areaId);
		getSpGroupInfo(areaId,"spGroupSelect",1);
		$("#spGroupSelect").val(spGroupId);
	}
}

function checkLineData(){
	
	var regex = /[^\u4e00-\u9fa5a-zA-Z0-9_-]/ig;
	var obj = $("#lineContainer").find("input[name=lineNo]");
	var lineTotal = $(obj).length;
    var errorLineNameIsNull = false, errorLineNameLength = false, errorLineNameRule = false;
	var arryLine = new Array();
	
	
	$(obj).each(function(i){
		var lineName = $(this).val();
		lineName = $.trim(lineName);
		
		if(lineName ==null || lineName ==""){
			errorLineNameIsNull = true;
		}
		
		if(lineName.length>10){
			errorLineNameLength = true;
		}
		
		if (regex.test(lineName)){
	        errorLineNameRule = true;
	    }
		
		if (arryLine.indexOf(lineName) == -1){
			arryLine.push(lineName);
		}
		
	});
	
	if(errorLineNameIsNull){
		return "error_line_name_is_null";//线路名为空
	}
	
	if(errorLineNameLength){
		return "error_line_name_overlength";//线路名称超长
	}
	
	if(errorLineNameRule){
		return "error_line_name_rule";//命名规则错误
	}
	
	if(arryLine.length < lineTotal){
		//当名称在数组中去重后跟路线总数比对，如果小于总的路线，则说明重复命名
		return "error_line_name_redo";
	}
	
	if(lineTotal == 0){
		//当名称在数组中去重后跟路线总数比对，如果小于总的路线，则说明重复命名
		return "error_line_required";
	}
	
}

//初始化线路数据
function initLineData(){
	
	var lineDataAll = ""; 
	
	$("#lineContainer").find("div.mark").each(function() { 
		 //alert($(this).html());
		 var lineVal = $(this).find("input[name=lineNo]").val();
		 var lineId = $(this).find("input[name=lineId]").val();
		 
		 var shopIdVal = null;
		 $(this).find(".chkBoxDiv ul li input[type=checkbox]").each(function(){
		    if(shopIdVal != null && shopIdVal !=""){
		    	shopIdVal=shopIdVal+","+$(this).val();
		    }else{
		    	shopIdVal=$(this).val();
		    }
		});  
		//将所以路线客户都放到lineDataAll中存储
		if(lineId != null && lineId !="" && lineId != "undefined"){
			lineDataAll= lineDataAll+lineVal+","+lineId+"##"+shopIdVal+"@@";
		}else{
			lineDataAll= lineDataAll+lineVal+"##"+shopIdVal+"@@";
		}
	});
	
	//将汇总信息设置到表单属性中
	$("#lineSet").val(lineDataAll);
}

/**
 * 保存定格路线函数
 */
function savaSpGroupLine(){
	var provinceId = $("#provinceSelect").val();
	var cityId = $("#citySelect").val();
	var areaId = $("#areaSelect").val();
	var spGroupId = $("#spGroupSelect").val();
	var deptId = $("#deptId").val();
	var userIdSet = $("#userIdSet").val();
	
	
	//alert("provinceId="+provinceId+", cityId="+cityId+", areaId="+areaId);
	 if((provinceId == null || provinceId =='') || (cityId == null || cityId =='') 
		||(areaId == null || areaId =='') ||(spGroupId == null || spGroupId =='')){
		alert("区域 及 定格 信息不能为空！");
		return;
	 }
	 
	 if(userIdSet == null || userIdSet ==""){
		 alert("DB代表不能为空！");
		 return;
	 }
	
	 var spGroupName = $("#spGroupSelect option:selected").text();
     $("#spGroupName").val(spGroupName);
     
     //保存前校验
 	 var status = checkLineData();
	 if(status == "error_line_name_is_null"){
		alert("所有的线路名称不能为空!");
		return;
	 }else if(status == "error_line_name_overlength"){
		alert("线路名称长度不能超过10个字符!");
		return;
	 }else if(status == "error_line_name_rule"){
		alert("线路名称只能包含中英文、数字及下划线等字符！");
		return;
	 }else if(status == "error_line_name_redo"){
		alert("线路名称不能重复!");
		return;
	 }else if(status == "error_line_required"){
		alert("保存时必须添加一条线路!");
		return;
	 }
	 //初始化线路数据
	 initLineData();
	 //提交表单
	 $.ajax({
			type: "post",
			url: rootPath+"/spgLine/addSpGroupData.do",
			data: $('#addSpgLine').serialize(),
			dataType: "json",
			async: false,
			success: function(data) {
			 if(data.success) {
					alert("添加成功!");
					location.href=rootPath+"/dept/toEditPage.do?deptId="+deptId+"&provinceId="+provinceId
						+"&cityId="+ cityId + "&areaId="+areaId;
				} else {
					alert(data.message);
				} 
			},
			error : function(data) {
			}
	});
}


function delLineData(obj){
// 	alert($(obj).parent().parent().html());
	if(confirm("您确定要删除该路线吗?")) {
		$(obj).parent().parent().remove();
	}
}

//打开选择客户列表
function loadCustList(obj){
	var shopIds = "";
	$("#lineContainer").find("input[type=checkbox]").each(function(i){
		shopIds+=$(this).val()+",";
	});  
	
	//解决layer按照get提交参数超长的问题，将值收集放入隐藏域中，等layer打开页面后，再回去父页面隐藏域中的值
	$("#shopIds").val(shopIds);
	//return;
	//定位到当前按钮对应的区域
	var htmlObj = $(obj).parent().parent().find(".chkBoxDiv");
	var timestamp=new Date().getTime();
	var idStr = "line"+timestamp;
	//对触发区域的div进行ID绑定，同时将该ID传到选择列表页面（提供数据回写使用）
	$(htmlObj).attr("id",idStr);
	
	var provinceId = $("#provinceSelect").val();
	var cityId = $("#citySelect").val();
	var areaId = $("#areaSelect").val();
	var spGroupId = $("#spGroupSelect").val();
	
	//alert("provinceId="+provinceId+", cityId="+cityId+", areaId="+areaId);
	if((provinceId == null || provinceId =='') || (cityId == null || cityId =='') 
		||(areaId == null || areaId =='') ||(spGroupId == null || spGroupId =='')){
		alert("区域 及 定格 信息不能为空！");
		return;
	}
	
    layer.open({
        type: 2,
        title: '',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['1300px' , '620px'],
        content: rootPath+"/shop/toShopListPage.do?divId="+idStr
        +"&provinceId="+provinceId
        +"&cityId="+cityId+"&areaId="+areaId
        +"&spGroupId="+spGroupId
        
        //屏蔽shopIds的原因是拼接数据过多导致get请求超过最大长度失败，因此屏蔽该参数，改用子页面获取父页面的值,如：$("#shopIds", window.parent.document).val();
        //+"&shopIds="+shopIds
    }); 
}

function selectShopCallback(divId,html){
	$("#"+divId).children().append(html);
	layer.closeAll('iframe'); //关闭所有的iframe层
}

function delLineCust(obj){
	$(obj).parent().parent().find("input[name='chk_list']:checked").each(function() { // 遍历选中的checkbox
		$(this).parent().remove();
	});	
}

function selectUserCallback(userIds,userNames){
	$("#userIdSet").val(userIds);
	$("#spSlsm").html(userNames);
	layer.closeAll('iframe'); //关闭所有的iframe层
}

//提供给原来打开页面使用
function layerClose(){
	layer.closeAll('iframe'); //关闭所有的iframe层
}