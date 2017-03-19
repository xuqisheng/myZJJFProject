$(document).ready(function() {
	initRegion();
	//触发用户信息加载（载入页面时加载）
	 $('#salesmanName').bind("input.autocomplete", function () {
		 //alert($('#salesmanName').val());
         $(this).trigger('keydown.autocomplete');
     });
	
	  $("#salesmanName").autocomplete(ctx+'/sys/user/getUserDynamicList', {
          matchContains: true,
          minChars: 1,
          extraParams: {
        	  userName:function(){return $('#salesmanName').val();},
        	  deptId:function(){return $('#deptIdId').val();}
          }, 
          /*width: 200,
          max: 20,
          scrollHeight: 300,
          matchCase: false,*/
          dataType: "json",//数据格式  
          mustMatch:false,
          parse: function(data) {
              return $.map(data, function(row) {
                  return {
                      data: row,
                      value: row.userId,
                      result: row.userName
                  }
              });
          },
          formatItem: function(item) {
             return "<font color=green>" + item.userName + "</font>";//提示的内容显示内容及格式设置
          }
      }).result(function(e, item) {
    	  //console.log(item);
    	  $("#salesmanId").val(item.userId);
      });
});

function page(n,s){
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	$("#searchForm").submit();
	return false;
}
//打开分开业务员界面
function distributeUser(){
    layer.open({
        type: 2,
        title: '',
        //shade: 0.5,//shade为弹出层透明度设置默认为0.3，如果为0表示不会有屏蔽父页面效果（不屏蔽则可以继续操作父页面）
        maxmin: true,
        shadeClose: false, //点击遮罩关闭层(如果为true表示点击则关闭弹出层，如果为false点击其他区域则不能关闭)
        area : ['500px' , '600px'],
        content: ctx+"/shop/shop/toBindUserPage.do"
    }); 
}

/**
 * 初始化区域信息
 */
function initRegion(){
	var provinceId = $("#provinceId").val();
	var cityId = $("#tmpCityId").val();
	var areaId = $("#tmpAreaId").val();
	//alert("provinceId="+provinceId+",cityId="+cityId);
	//如果省份ID不为空，则查询对应省份对应城市
	if(provinceId != null && provinceId !=""){
		//alert("provinceId="+provinceId);
		getRegionInfo(provinceId,"cityId",'1',cityId);
		//$("#citySelect").val(cityId);
	}
	//如果城市ID不为空，则查询对应省份对应城市
	if(cityId != null && cityId !=""){
		//alert("cityId="+cityId);
		getRegionInfo(cityId,"areaId",'1',areaId);
		//$("#areaSelect").val(areaId);
	}
}

//打开分开业务员界面
function clearFun(){
	$("#cityId").select2("val", "");
	$("#areaId").select2("val", "");
	$("#provinceId").val(null).trigger("change");
	$("#shopType").val(null).trigger("change");
	$("#shopNo").val("");
	$("#salesmanName").val("");
	$("#isAllot").select2("val", "ALL");
	$("#registerTel").val("");
	
}