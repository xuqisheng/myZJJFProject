	$(function() {
		 $("#jpagination").pagination({
             pageSize: 10,
             remote: {
                 url: rootPath+'/shop/listPage.do',
                 params: $('#shopForm').serializeArray(),
                 success: function(data) {
                     var html='';
                     $.each(data.list, function(i,item) {
                    	 var spGroupId = item.spGroupId;
                    	 var province = item.province;
                    	 var city = item.city;
                    	 var area = item.area;
                    	 
                     	html+='<tr><td>'+item.shopNo+'</td><td>'+item.shopName+'</td>';
   	                	html+='<td>'+province+city+area+'</td>';
    	                if(item.shopAddress == null){
    	                	html+='<td></td>';
    	                }else{
    	                	html+='<td>'+item.shopAddress+'</td>';
    	                }
    	                if(item.telephone == null){
    	                	html+='<td></td>';
    	                }else{
    	                	html+='<td>'+item.telephone+'</td>';
    	                }
    	                html+='<td>'+item.shopType+'</td>';
    	                
    	                if(item.isRegister == null || item.isRegister == '0'){
    	                	
    	                	if(spGroupId != null && spGroupId !=''){
    	                		html+='<td>未注册</td>';
                        	}else{
                        		html+='<td style="background-color:red;">未注册</td>';
                        	}
    	                }else if(item.isRegister == '1'){
    	                	html+='<td>已注册</td>';
    	                }
    	                
    	                if(item.isRegister == '1'){
    	                	html+='<td><a href="'+rootPath+'/shop/toEditPage.do?shopId='+item.shopId+'" style="margin-right:40px;">编辑</a>';
    	                }else{
    	                	html+='<td><a href="'+rootPath+'/shop/toEditPage.do?shopId='+item.shopId+'" style="margin-right:10px;">编辑</a>';
    	                	html+='<a data-id="'+item.shopId+'" data-shopName="'+item.shopName+'" onclick="delShopInfo(this);">删除</a>';
    	                }
    	                html+='</td></tr>';
    	                
                     });
                     if(html == "") {
                     	html = '<tr><td colspan="10" class="no-data"></td></tr>';
                     }
                     $('.table-tbody').html(html);
                 },
                 totalName:'totalSize',
                 pageParams: function (data) {
                    	return {
                    			pageIndex: data.pageIndex + 1,
                    			pageSize: data.pageSize
                    		}
                 }
             }
         });
		 
		 $('#sub').on('click', function(e) {
			 
			 $("#tmpProvinceId").val($("#provinceId").val());
			 $("#tmpCityId").val($("#citySelect").val());
			 $("#tmpAreaId").val($("#areaSelect").val());
			 $("#tmpShopType").val($("#shopTypeTmp").val());
			 
        	 $("#jpagination").pagination('setParams', $('#shopForm').serializeArray());
        	 $("#jpagination").pagination('setPageIndex', 0);
        	 $("#jpagination").pagination('remote');
        });
		 
		// 如果身份数据改变的时候清空城市和区域的值
		$("#provinceId").change( function() {
			$("#citySelect").val("");
			$("#areaSelect").val("");
		});
		
		//初始化区域信息
		initRegion();
	});
	
	//重置查询条件
	function resetCondition() {
		$("#shopNo").val("");
		$("#shopTypeTmp").val("");
		$("#provinceId").val("");
		$("#citySelect").val("");
		$("#areaSelect").val("");
    }
	
	//弹出一个iframe层,供选择部门管理者
	$('#btnImport').on('click', function(){
	    layer.open({
	        type: 2,
	        title: '',
	        maxmin: true,
	        shadeClose: true, //点击遮罩关闭层
	        area : ['600px' , '135px'],
	        content: rootPath+'/shop/toFileImportPage.do'
	    });
	});
	
	//弹出一个iframe层,供选择部门管理者
	$('#btnSyncShop').on('click', function(){
		 $.ajax({
			 type: "post",
			 url: rootPath+"/shop/syncShopData.do",
			 dataType: "json",
			 async: false,
			 success: function(data) {
				// console.info(data);
			  if(data.success) {
				 alert(data.message);
			  }else{
				 alert(data.message);
			  } 
			},
			error : function(data) {
				alert("同步失败")
			}
		});
	});
	
	function delShopInfo(obj){
		var shopId =  $(obj).data("id");
		var shopName = $(obj).data("shopname");
		if(window.confirm('你确定要删除名称为【'+shopName+'】的客户吗？')){
		 $.ajax({
				type: "post",
				url: rootPath+"/shop/delShopInfo.do",
				data: "shopId="+$.trim(shopId)+"&isDelete=1",
				dataType: "json",
				async: false,
				success: function(data) {
				 if(data.success) {
						alert("操作成功!");
						location.href=rootPath+"/shop/index.do";
					} else {
						alert(data.message);
					} 
				},
				error : function(data) {
				}
			});
		}
	}
	
	function check() {  
		 var excel_file = $("#importFile").val();  
	     if (excel_file == "" || excel_file.length == 0) {  
	         alert("请选择文件路径！");  
	         return false;  
	     } else {  
	        return true;  
	    }  
	} 
	
	//选择用户列表回调页面
	function closeCallback(){
		layer.closeAll('iframe'); //关闭所有的iframe层
	}
	

	/**
	 * 初始化区域信息
	 */
	function initRegion(){
		var provinceId = $("#tmpProvinceId").val();
		var cityId = $("#tmpCityId").val();
		var areaId = $("#tmpAreaId").val();
		
		$("#provinceId").val(provinceId);
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
	}
	