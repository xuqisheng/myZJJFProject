/**
 * 商品库管理__商品管理_新增/编辑
 */
var map="";
var map2="";

var supplierId = SUPPLYSESSION;
var supplierMobile = MOBILESESSION;
var supplierIds = supplierIdsArr;

$(function() {
	yesOrNo();
	//判断批发商是否可以修改价格
	function yesOrNo(){
		if(supplierIds != null){
			var flag = false;
			for (var i = 0; i < supplierIds.length; i++) {
				if(supplierId==supplierIds[i] || supplierMobile==supplierIds[i]){
					flag = true;
					break;
				}
			}
			if(flag){
				return true;
			}else{
				var $areaPrices = $(".areaPrices");
				if($areaPrices != null && $areaPrices != ""){
					$areaPrices.each(function(){
						$(this).attr('disabled','disabled');
					});
				}
				var $statuss = $(".statuss")
				if($statuss != null && $statuss != ""){
					$statuss.each(function(){
						$(this).attr('disabled','disabled');
					});
				}
				return false;
			}
		}else{
			return false;
		}
	}
	
	//判断是否编辑入口
	if($('#flag').val()=="true"){
 		 $('.J_title').html('编辑商品');
		 $('#productName').hide();
		 $('#productNameLabel').after( $('#productNameHidden').val());//商品名
		 $('#mdseId').hide();
		 $('#mdseIdLabel').after($('#mdseIdHidden').val());//条形码
		 $('#spec').hide();
		 $('#specLabel').after($('#specHidden').val());//规格
		 //measure
		 $('#measure').hide();
		 $('#measureLabel').after($('#measureHidden').val());
	}else{
		$('.J_title').html('添加商品');
	}
	
	$('.areaPrices').on('focus',function(){
		$(this).parent('td').parent('tr').find('.msg').text("");
	});
	
	//添加/编辑商品 确定按钮
        $('#submit').on('click',function() {
        	//市场价
        	var testPriceCheck = /^\d{1,5}(\.\d{1,2})?$/;
        	var status = $("#status").val();
        	//从session里面获取supplierId
        	if(supplierId != null && yesOrNo()){
	        	if(status != null && status != "" && (status == 0 || status == 1)){
	        		var $areaPrices = $('.areaPrices');
	        		var $tr = null;
	        		var count = 0;
	        		//市场价
	        		var plantMemPrice = 0;
	        		var jiage = 0;
	        		if($areaPrices != null && $areaPrices != ""){
		        		$areaPrices.each(function(){
		        			$tr = $(this).parent('td').parent('tr');
		        			plantMemPrice = $tr.find(".plantMemPrice").val();
		        			if(plantMemPrice == null || plantMemPrice == '' || plantMemPrice == 'null'){
		                		plantMemPrice=0;
		                	}
		        			jiage = parseFloat(plantMemPrice)+parseFloat(plantMemPrice*0.1);
		        			if(isNaN(jiage)){
		        				count ++;
			        			return false;
			        		}
		        			if($(this).val()==null || $(this).val()==""){
		        				$tr.find('.msg').html('<span style="color:red">售价不能为空</span>');
		        				count ++;
		        			}else if($(this).val() > (Math.floor(jiage*100)/100)){
		        				$tr.find('.msg').html('<span style="color:red">您的价格已经高于市场价（'+plantMemPrice+'）的10%，如需调整请联系客服  400-664-3833</span>');
		        				count ++;
		        			}else if($(this).val()<0){
		        				$tr.find('.msg').html('<span style="color:red">售价不能小于0</span>');
		        				count ++;
		        			}else if(!testPriceCheck.test($(this).val())){
		        				$tr.find('.msg').html('<span style="color:red">售价格式不正确</span>');
		        				count ++;
		        			}
		        		});
	        		}
	        		if(count >0){
	        			return false;
	        		}
	        		
	            	/*
	        		if($('#areaPrice').val()=="" || $('#areaPrice').val()==null){
	        			alert("转角售价不能为空！");
	        			return;
	        		}else if($('#areaPrice').val() > (Math.floor(jiage*100)/100)){
	            		$("#label").html('<span style="color:red">您的价格已经高于市场价（'+plantMemPrice+'）的10%，如需调整请联系客服  400-664-3833</span>');
	            		return;
	            	}else if($('#areaPrice').val() < 0){
	            		$("#label").html('<span style="color:red">店宝售价不能小于0！</span>');
	            		return;
	            	}else if(!testPriceCheck.test($('#areaPrice').val())){
	            		alert("转角售价格式不正确！");
	            		return;
	            	}*/
	        	}
        	}
        	
        	
        	if($('#itemBaseId').val()=="" || $('#itemBaseId').val() == null){
        		alert("商品库没有该商品,无法新增!");
        		return;
        	}
        	if($.trim($('#storePrice').val())==""){
        		alert("便利店出货价不能为空!");
        		return;
        	}
        	if($.trim($('#resPrice').val())==""){
        		alert("餐饮店出货价不能为空!");
        		return;
        	}
        	
        	if(!testPriceCheck.test($('#storePrice').val()) && !testPriceCheck.test($('#storePrice').val()) ){
        		alert("便利店出货价格式不正确!");
        		return;
        	}
        	if(!testPriceCheck.test($('#resPrice').val()) && !testPriceCheck.test($('#resPrice').val())){
        		alert("餐饮店出货价格式不正确!");
        		return;
        	}
        	 $.ajax({
      			type: "post",
      			url: rootPath+"/scms/plantItem/updatePlantItemPrice.do",
      			data: $('#priceForm').serialize(),
      			dataType: "json",
      			async: false,
      			success: function(data) {
      			 if(data.success) {
      					alert("操作成功!");
      					//TODO 跳转到列表
      					location.href=rootPath+"/scms/plantItem/toProductIndex.do";
      				} else {
      					alert(data.message);
      				} 
      			},
      			error : function(data) {
      			}
      		});
           
        });
        //取消按钮,关闭按钮
        $('#cancelButton').on('click', function() {
        	//回到列表
        	location.href=rootPath+"/scms/plantItem/toProductIndex.do";
        });
        
        //获取商品类别
        $.ajax({
			type: "post",
			url: rootPath+"/scms/plantItem/getItemCatlog.do",
			dataType: "json",
			async: true,
			success: function(data) {
			 if(data.success) {
				 map = data.message.map;
				 map2 = data.message.map2;
				 
				 var pId = '';
				   $.each(eval('('+map2+')'),function(i,item){
					   $.each(item,function(j,item2){
						  if(j==$('#catId').val()){
							  pId = i;
							  return false;
						  }
					   });
				   });
				   var html = '';
				   $.each(eval('('+map+')'),function(i,item){
					   html+='<option value="'+i+'">'+item+'</option>';
				   });
				   $('#productFirstSelect').html(html);
				   $('#productFirstSelect').val(pId);
				   var html2='';
				   $.each(eval('('+map2+')'),function(i,item){
					   if(i==$('#productFirstSelect').val()){
						   $.each(item,function(j,item2){
							   html2+='<option value="'+j+'">'+item2+'</option>';
						   });
					   }					   
				   });
				   $('#productSecondSelect').html(html2);
				   $('#productSecondSelect').val($('#catId').val());
				} else {
					alert(data.message);
				} 
			},
			error : function(data) {
			}
		});        
        
        //自动填充
        $('#productName').autocomplete({
            serviceUrl: rootPath+'/scms/plantItem/getPlantItemName.do',
            paramName: 'productName',
            transformResult: function(response) {
                var res = JSON.parse(response)
                if(res.message!=null){
                	return {
                        suggestions: $.map(res.message, function(value, key) {
                        	return { value: value.name+'--'+value.spec, data: value };
                        })
                    };
                }else{
                	return {
		            	suggestions: [{ value: "数据异常,请联系客服"}]
		            };
                }
            },
            onSelect: function(dd) {
            	//回显页面
            	$('#mdseId').html("");
            	$('#spec').html("");
            	$('#itemBaseId').html("");
            	$('#mdseId').val(dd.data.mdseId);
            	$('#spec').val(dd.data.spec);
            	if(dd.data.pkg==null||dd.data.pkg==""){
            		$('#measure').val("无");
            	}else{
            		$('#measure').val(dd.data.pkg);
            	}
            	
            	if(dd.data.imgS==null||dd.data.imgS==""){
            		$('#pic').attr("src", $("#DEFAULTIMGURL").val());
            	}else{
            		$('#pic').attr("src", $("#FASTFDSPREURL").val()+dd.data.imgS);
            	}
                $('#itemBaseId').val(dd.data.id);
                var catId = dd.data.cateId;
                var parentId = '';
                //回显下拉列表 begin
                $('#productFirstSelect').empty();
                 $.each(eval('('+map2+')'),function(i,item){
				   $.each(item,function(j,item2){
					   if(j==catId){
						   parentId = i;
					   }
				   });
				 });
                 var html = '';
				 $.each(eval('('+map+')'),function(i,item){
					 html+='<option value="'+i+'">'+item+'</option>';
				 });
				 $('#productFirstSelect').append(html);
				 $('#productFirstSelect').val(parentId);
				 $('#productSecondSelect').empty();
				 var html2='';
				 $.each(eval('('+map2+')'),function(i,item){
					   if(i==$('#productFirstSelect').val()){
						   $.each(item,function(j,item2){
							   html2+='<option value="'+j+'">'+item2+'</option>';
						   });
					   }					   
				   });
				   $('#productSecondSelect').html(html2);
				   $('#productSecondSelect').val(catId);
				//回显下拉列表 end
                $.ajax({
         			type: "post",
         			url: rootPath+"/scms/plantItem/getSpItmeBaseAndPrice.do",
         			data: {'itemBaseId':dd.data.id},
         			dataType : "json",
         			async: true,
         			success: function(data) {
         			 if(data.success) {
         				  if(data.message!=null){
         				  $('#plantItemId').val(data.message.id);
         				  $('#productName').next('span').text("已经有该商品了,可以编辑出货价");
       					  $.each(data.message.userTypes,function(i,item){
       						  if(item.name=='便利店'){
       							  $('#storePriceId').val(item.id); 
       							 $('#storePrice').val(item.salePrice);         							  
       						  }else if(item.name=='餐饮店'){
       							  $('#resPriceId').val(item.id);   
       							 $('#resPrice').val(item.salePrice);  
       						  }
       					  });
         				}
         			 } else {
         					alert(data.message);
         				} 
         			},
         			error: function(data) {
         				alert("参数有误,请联系客服")
         			}
         		})
            }
	});
})