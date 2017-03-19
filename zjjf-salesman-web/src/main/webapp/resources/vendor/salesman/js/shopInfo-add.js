$(document).ready(function(){
	$('#submit').on('click',function() {
		 //修改店铺信息
		addShopData()
	});
});

function addShopData() {
	var provinceId = $("#provinceId").val();
	var cityId = $("#citySelect").val();
	var areaId = $("#areaSelect").val();
	var shopType = $("#shopType").val();
	
	var shopName  = $("#shopName").val();
	var telephone = $("#telephone").val();
	var bossName  = $("#bossName").val();
	var bossTel   = $("#bossTel").val();
	var shopAddress = $("#shopAddress").val();
	var remarks = $("#remarks").val();
	
	
	var shopArea  = $("#shopArea").val();
	var staffNum  = $("#staffNum").val();
	var distributionNum  = $("#distributionNum").val();
	var dcShop    = $("#dcShop").val();
	
	var mainProduct = $("#mainProduct").val();
	var saleRatio   = $("#saleRatio").val();
	var sku         = $("#sku").val();
	var turnover    = $("#turnover").val();
	
	var regex = /[^\u4e00-\u9fa5a-zA-Z0-9_]/ig;
	
	if(provinceId =="" || provinceId == null){
		alert("所属省份不能为空!");
		return;
	}
	if(cityId =="" ||cityId == null){
		alert("所属城市不能为空!");
		return;
	}
	if(areaId =="" ||areaId == null){
		alert("所属区域不能为空!");
		return;
	}
	
	if(shopType =="" ||shopType == null){
		alert("客户类型不能为空!");
		return;
	}
	if(shopName =="" ||shopName == null){
		alert("客户名称不能为空!");
		return;
	}
	
    if (regex.test(shopName))
    {
        alert("客户名称只能包含中英文、数字及下划线等字符！");
        return;
    }
	
	if(telephone =="" ||telephone == null){
		alert("固定电话不能为空!");
		return;
	}
	if(checkTelephone(telephone)){
		alert("固定电话输入有误！");
        return;
	}
	
	if(bossName =="" ||bossName == null){
		alert("店铺负责人不能为空!");
		return;
	}
	if (regex.test(bossName))
    {
        alert("客户名称只能包含中英文、数字及下划线等字符！");
        return;
    }
    
	if(bossTel =="" ||bossTel == null){
		alert("联系方式不能为空!");
		return;
	}
	
	if(checkMobile(bossTel)){
		alert("您输入的手机号不合法" );
        return;
	}
	
	
	if(shopAddress =="" ||shopAddress == null){
		alert("详细地址不能为空!");
		return;
	}
	/*if (regex.test(shopAddress))
    {
        alert("详细地址只能包含中英文、数字及下划线等字符！");
        return;
    }*/
	
	if(shopArea !="" && shopArea != null && checkNumber(shopArea)){
		alert("店铺面积只能为数字！"); 
		return;
	}
	if(staffNum !="" && staffNum != null && checkNumber(staffNum)){
		alert("人员数量只能为数字！"); 
		return;
	}
	if(distributionNum !="" && distributionNum != null && checkNumber(distributionNum)){
		alert("配送员数量只能为数字！"); 
		return;
	}
	/*if(saleRatio !="" && saleRatio != null && checkMoney(saleRatio)){
		alert("主营商品占比只能为数字或小数(保留两位小数)！"); 
		return;
	}*/
	var regex1 = /[^0-9.%]/ig;
	
	if(regex1.test(saleRatio)){
		alert("主营商品占比只能为数字、小数点及百分号等字符"); 
		return;
	}
	if(sku !="" && sku != null && checkNumber(sku)){
		alert("SKU数只能为数字！"); 
		return;
	}
	if(turnover != 0){
		if(turnover !="" && turnover != null && checkMoney(turnover)){
			alert("日均营业额必须大于零（小数保留两位小数）！"); 
			return;
		}
	}
	if(dcShop !="" && dcShop != null){
		
	    if (regex.test(dcShop))
	    {
	        alert("配送合作商只能包含中英文、数字及下划线等字符！");
	        return;
	    }
	}
	if(mainProduct !="" && mainProduct != null){
		
	    if (regex.test(mainProduct))
	    {
	        alert("主营商品只能包含中英文、数字及下划线等字符！");
	        return;
	    }
	}
	
	if(remarks.length>200){
		alert("备注长度不能超过200个字符！");
		return;
	}
	/*var regexRmk = /[^\u4e00-\u9fa5a-zA-Z0-9_,.，;；。！!？?\\、%]/ig;
    if (regexRmk.test(remarks))
    {
    	alert("备注只能包含中英文、数字、下划线及常用标点符号等字符！");
        return;
    }*/
    
    //上传图片限制
    var picSize = $(".picUl").find("img").size();
    if(picSize>4){
    	alert( '注意: 最多可以上传4张图片！');
    	return;
    }
    //重新准备图片URL到隐藏字段中
    initPicUrl();
    //=====================将区域相关名称保存表中=======================
	var province = $("#provinceId option:selected").text();
	var city = $("#citySelect option:selected").text();
	var area = $("#areaSelect option:selected").text();
	$("#province").val(province);
	$("#city").val(city);
	$("#area").val(area);
	//=====================将区域相关名称保存表中=======================
	 $.ajax({
			type: "post",
			url: rootPath+"/shop/addShopInfo.do",
			data: $('#addShopForm').serialize(),
			dataType: "json",
			async: false,
			success: function(data) {
			 if(data.success) {
					alert("添加成功!");
					location.href=rootPath+"/shop/index.do";
				} else {
					alert(data.message);
				} 
			},
			error : function(data) {
			}
		});
}


