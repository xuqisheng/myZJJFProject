/**
 * 商品库管理__商品管理
 */

//回车键
document.onkeydown = function(event) {
	e = event ? event :(window.event ? window.event : null);
	if(e.keyCode==13){
		$("#jpagination").pagination('setParams', {'keyStr':$('#keyStr').val(),'catId':$('#secondSelect').val()});
	   	$("#jpagination").pagination('setPageIndex', 0);
		$("#jpagination").pagination('remote');
	}
}
var map="";
var map2="";
$(function() {
        //添加/编辑商品 确定按钮
        $('#J_dialogGoods').on('click', '.dialog-ok', function() {
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
        	var testPriceCheck = /[0-9]{1,5}(\.[0-9]{2})?/;
        	if(!priceCheck.test($('#storePrice').val()) || $('#storePrice').val()==0){
        		alert("便利店出货价格式不正确!");
        		return;
        	}
        	if(!priceCheck.test($('#resPrice').val())){
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
      					alert(data.message);
      					$('#itemBaseId').val('');
      					$("#jpagination").pagination('remote');
      					 $('#J_dialogGoods').fadeOut();
      				} else {
      					alert(data.message);
      				}
      			},
      			error : function(data) {
      			}
      		});

        })
        //取消按钮,关闭按钮
        $('#J_dialogGoods').on('click', '.dialog-cancle, .dialog-close', function() {
        	$("input[type=reset]").trigger("click");
        	$('#productName').removeAttr('disabled');
        	$('#productName').val('');
        	$('#mdseId').val('');
        	$('#spec').val('');
        	$('#itemBaseId').val('');
        	$('#J_dialogGoods').fadeOut();
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
				 var html = '';
				   $.each(eval('('+map+')'),function(i,item){
					   html+='<option value="'+i+'">'+item+'</option>';
				   });
				   $('#firstSelect').append(html);
				   var html2='';
				   $.each(eval('('+map2+')'),function(i,item){
					   if(i==$('#firstSelect').val()){
						   $.each(item,function(j,item2){
							   if(item!=null){
							   html2+='<option value="'+j+'">'+item2+'</option>';
							   }else{
								   html2+='<option value="'+j+'"></option>';
							   }
						   });
					   }
				   });
				   $('#secondSelect').append(html2);
				} else {
					alert(data.message);
				}
			},
			error : function(data) {
			}
		});

        //二级联动
        $('#firstSelect').on('change', function() {
        	//清空二级下拉框
        	$('#secondSelect').empty();
        	var html2='';
        	html2+='<option value="-1">全部</option>';
			   $.each(eval('('+map2+')'),function(i,item){
				   if(i==$('#firstSelect').val()){
					   $.each(item,function(j,item2){
						   html2+='<option value="'+j+'">'+item2+'</option>';
					   });
				   }
			   });
			   $('#secondSelect').html(html2);
        });
        layer.load(2);
        //获取商品列表
        $("#jpagination").pagination({
        	pageSize: 10,
            remote: {
                url: rootPath+'/scms/plantItem/getPlantItem.do',
                success: function(data) {
                	layer.closeAll('loading');
                	var html = "";
					$.each(data.list, function(i,item) {

   						html += '<tr><td>' + item.mdseId + '</td><td>' + item.name + '</td><td>' + item.cateNameO + '/'+item.cateNameT+'</td><td>' + item.spec + '</td>';
   						if(item.pkg==null||item.pkg==""||item.pkg=="null"){
   							html+='<td>箱</td>';
   						}else{
   							html+='<td>' + item.pkg + '</td>';
   						}
   						/*if(item.areaPrice!=null){
   							html += '<td>'+item.areaPrice+'</td>';
   						}else{
   							html += '<td>--</td>';
   						}*/

   						if(item.status != null && (item.status == 0 || item.status == 1)){
   							html += '<td>店宝商品</td>';
   						}else if(item.status != null && item.status == 3){
   							html += '<td>其它商品</td>';
   						}

   						/*if(item.status != null && item.status == 0){
   							html += '<td class="txt-warn">已下架</td>';
   						}else if(item.status != null && item.status == 1){
   							html += '<td class="txt-success">已上架</td>';
   						}else{
   							html += '<td>--</td>';
   						}*/
   						/*if(item.userTypes !=0) {
	   						var blprice="";
	   						$.each(item.userTypes, function(i2, item2) {
	   							if(item2.name == "便利店" && item2.salePrice != null) {
	      							blprice = item2.salePrice;
	      							return false;
	   							}
	      					});
	      					if(blprice!=""){
	      						html+='<td>'+blprice+'</td>'
	      					}else{
	      						html+='<td>--</td>'
	      					}
	   						var cyprice="";
	      					$.each(item.userTypes, function(i2, item2) {
	   							if(item2.name == "餐饮店" && item2.salePrice != null) {
	   								cyprice=item2.salePrice;
	      							return false;
	   							}
	      					});
	      					if(cyprice!=""){
	      						html+='<td>'+cyprice+'</td>'
	      					}else{
	      						html+='<td>--</td>'
	      					}
	   					} else {
	   						html += '<td>--</td><td>--</td>';
	   					}*/
	                 	html += '<td><input type="hidden" value="' + item.itemBaseId + '"><a class="button button-operate" href="'+rootPath+'/scms/plantItem/toProductAdd.do?itemBaseId='+item.itemBaseId+'&pageIndex='+data.map.pageIndex+'">编辑</a></td></tr>';
   				 	});
                    if(html == "") {
                     	html = '<tr><td colspan="9" class="no-data"></td></tr>';
                    }
   					$('.table-tbody').html(html);
                },
                totalName:'totalSize'
            }
        });
        //搜索按钮
        $('#formSub').on('click', function(e) {
			$("#jpagination").pagination('setParams', {'keyStr':$('#keyStr').val(),'catId':$('#secondSelect').val(),'pCatId':$('#firstSelect').val(),status:$("#status").val()});
			$("#jpagination").pagination('setPageIndex', 0);
			$("#jpagination").pagination('remote');


       	});

        //商品上架或下架
        function sxj(num,id){

        }

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
            	$('#measure').val(dd.data.measure);
                $('#pic').attr("src", "http://www.izjjf.cn/" + dd.data.imgS);
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
         			type : "post",
         			url : rootPath+"/scms/plantItem/getSpItmeBaseAndPrice.do",
         			data:{'itemBaseId':dd.data.id},
         			dataType : "json",
         			async:true,
         			success : function(data) {
         			 if (data.success) {
         				  if(data.message!=null){
         				  $('#plantItemId').val(data.message.id);
         				  alert("已经有该商品了,可以编辑出货价")
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
         			 }else {
         					alert(data.message);
         				}
         			},
         			error : function(data) {
         				alert("参数有误,请联系客服")
         			}
         		})
            }
       });
        })
