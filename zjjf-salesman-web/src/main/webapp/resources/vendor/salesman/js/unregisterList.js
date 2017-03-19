	$(function() {
		 $("#jpagination").pagination({
             pageSize: 10,
             remote: {
                 url: rootPath+'/shop/listPage.do',
                 params: $('#shopForm').serializeArray(),
                 success: function(data) {
                     var html='';
                     $.each(data.list, function(i,item) {
                    	 
                    	html+='<tr><td><input type="checkbox" onclick="setSelectAll();" name="chk_list" value="'+item.shopNo+'"></td>';
                     	html+='<td>'+item.shopNo+'</td>';
                     	html+='<td>'+item.shopName+'</td>';
   	                	html+='<td>'+item.area+'</td>';
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
    	                	html+='<td>未注册</td>';
    	                }else if(item.isRegister == '1'){
    	                	html+='<td>已注册</td>';
    	                }
    	                
    	                html+='</tr>';
    	                
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
        	 $("#jpagination").pagination('setParams', $('#shopForm').serializeArray());
        	 $("#jpagination").pagination('setPageIndex', 0);
        	 $("#jpagination").pagination('remote');
        });
		 
		 
	});
	
	//将选中的客户信息绑定到定格上
	function bindSpGroup(){
		
		var shopNos = "";
		var spGroupId = $("#spGroupSelect", window.parent.document).val();
		var spGroupName = $("#spGroupSelect", window.parent.document).find("option:selected").text();
		//alert("spGroupId="+spGroupId+",spGroupName="+spGroupName);
		
		$("input[name='chk_list']:checked").each(function() { // 遍历选中的checkbox
			n = $(this).parents("tr").index();  // 获取checkbox所在行的顺序
			//$(".table-tbody").find("tr:eq("+n+")").remove();
			var trObj = $(".table-tbody").find("tr:eq("+n+")");
			var shopNo = $(trObj).find("td:eq(1)").text()
			
			if(shopNos != null && shopNos !=''){
				shopNos+=",'"+shopNo+"'";
			}else{
				shopNos="'"+shopNo+"'";
			}
			
		});	
		
		//alert(shopNos);
		parent.addBindShopCallback(spGroupId,spGroupName,shopNos); 
	}
	
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
	
	function delShopInfo(obj){
		var shopId =  $(obj).data("id");
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
	