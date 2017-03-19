
var tRoot = "/zjjf-kefu";
/**
 * 设置页面变量到js文件里面。
 * @param pRoot
 */
function setRoot(pRoot){
	tRoot = pRoot;
}

/**
 * 查询顶级分类
 * @returns {String}
 */
function queryTopItemCate(){
	$.ajaxSetup({
        async : false  
    });
	var html = '';
	$.post(tRoot+"/customer/itemCatelog/queryTopItemCate.do",function(data){
		if(data.success){
			$.each(data.bData,function(i,item){
				html+='<option value="'+item.id+'">'+item.name+'</option>'
			});
		}
	},'json');
	return html;
}

/**
 * 通过一级获取二级
 */
function selectErJiByYiJi(){
	var html = '<option value="" class="select">二级分类</option>';
	var id = $("#yiji").val();
	if(id != null && id != ""){
		html += queryItemCateByPid(id);
	}
	
	$("#erji").html(html);
}
/**
 * 根据父分类id，查询子分类集合
 * @param pid
 * @returns {String}
 */
function queryItemCateByPid(pid){
	$.ajaxSetup({   
        async : false  
    });
	var html = '';
	$.post(tRoot+"/customer/itemCatelog/getAllItemCateByPid.do",{pid:pid},function(data){
		if(data.success){
			$.each(data.message,function(i,item){
				html+='<option value="'+item.id+'">'+item.name+'</option>'
			});
		}
	},'json');
	return html;
}

function ajaxAsync(url,param){
	$.ajaxSetup({   
        async : false  
    });
	var dataObj = '';
	$.post(tRoot + url,param,function(data){
		if(data.success){
			dataObj = data.message;
		}
	},'json');
	return dataObj;
}

/**
 * 根据品牌名称查询
 * @param name
 */
function getBrandByName(name){
	var dataObj = ajaxAsync("/keFu/SKUActive/getBrandByName.do", "{brandName:"+name+",str:brand}");
	var html = "";
	$.each(dataObj,function(i,item){
		html+='<option value="'+item.id+'">'+item.name+'</option>'
	});
}

/**
 * 确认单品促销活动
 * @param id
 * @returns {String}
 */
function confirmOne(id){
	$.ajaxSetup({
        async : false  
    });
	$.post(tRoot+"/keFu/SKUActive/confirmSKUActive.do",{id:id},function(data){
		layer.msg(data.desc,{tiem:1000},function(){
			loadSKUActiveList();
		});
	},'json');
}

/**
 * 停止单品促销活动
 * @param id
 * @returns {String}
 */
function stopSKUActive(id){
	$.ajaxSetup({
        async : false  
    });
	$.post(tRoot+"/keFu/SKUActive/stopSKUActive.do",{id:id},function(data){
		layer.msg(data.desc,{tiem:1000},function(){
			loadSKUActiveList();
		});
	},'json');
}
/**
 * 生效到设置数据
 * @param id
 */
function effecSKUActive(id){
	$.ajaxSetup({
        async : false  
    });
	
	$.post(tRoot+"/keFu/SKUActive/effecSKUActive.do",{id:id},function(data){
		layer.msg(data.desc,{tiem:1000},function(){
			loadSKUActiveList();
		});
	},'json');
}
/**
 * 失效设置数据
 */
function invalidSKUActive(id){
	$.ajaxSetup({
        async : false  
    });
	$.post(tRoot+"/keFu/SKUActive/invalidSKUActive.do",{id:id},function(data){
		layer.msg(data.desc,{tiem:1000},function(){
			loadSKUActiveList();
		});
	},'json');
}

/**
 * 添加新的单品促销内容，并刷新列表
 * @param activeid
 * @param zTreeObj
 */
function editSKUActive4addPlantItem(activeid,zTreeObj){
	$.ajaxSetup({
        async : false  
    });
	var spGroupIds =queryspGroupIdsFromZtree(zTreeObj);
	var trs = $("input[name='J_chk']:checked").parent().parent();
	var itemBaseIds = new Array();
	trs.each(function() {
		var $trThis = $(this);
		var id = $trThis.find(".id").val();
		itemBaseIds.push(id);
	});
	var param = "id="+activeid+"&spGroupIds="+spGroupIds.toString()+"&itemBaseIds=" + itemBaseIds.toString();
	$.post(tRoot+"/keFu/SKUActive/editSKUActive4addPlantItem.do",param,function(data){
		layer.msg(data.desc,{tiem:100},function(){
			location.href = tRoot + "/keFu/SKUActive/editSKUActive.do?step=1&id="+activeid;
		});
	},'json');
}
/**
 * 修改tag标签
 * @param activeid
 * @param plantItemId
 * @param tagId
 */
function editSKUActive4updateTag(activeid,plantItemId,ele){
	var param = "id="+activeid+"&plantItemId="+plantItemId+"&tagId=" + ele.value;
	$.post(tRoot+"/keFu/SKUActive/editSKUActive4updateTag.do",param,function(data){
		layer.msg(data.desc,{tiem:100},function(){
			location.href = tRoot + "/keFu/SKUActive/editSKUActive.do?step=1&id="+activeid;
		});
	},'json');
}

function editSKUActive4updatePrice(activeid,plantItemId,ele){
	var param = "id="+activeid+"&plantItemId="+plantItemId+"&price=" + ele.value;
	$.post(tRoot+"/keFu/SKUActive/editSKUActive4updatePrice.do",param,function(data){
		layer.msg(data.desc,{tiem:100},function(){
			location.href = tRoot + "/keFu/SKUActive/editSKUActive.do?step=1&id="+activeid;
		});
	},'json');
}

function editEffecSKUActive4updatePrice(activeid,plantItemId,ele){
	var param = "id="+activeid+"&plantItemId="+plantItemId+"&price=" + ele.value;
	$.post(tRoot+"/keFu/SKUActive/editEffecSKUActive4updatePrice.do",param,function(data){
		layer.msg(data.desc,{tiem:100},function(){
			location.href = tRoot + "/keFu/SKUActive/querySKUActive.do?id="+activeid + "&opType=1";
		});
	},'json');
}

function editSKUActive4updateNum(activeid,plantItemId,ele){
	if(parseInt(ele.value)<0){
		alert('单品限量不能为负数');
		ele.value="";
		return false;		
	}
	var param = "id="+activeid+"&plantItemId="+plantItemId+"&num=" + ele.value;
	$.post(tRoot+"/keFu/SKUActive/editSKUActive4updateNum.do",param,function(data){
		layer.msg(data.desc,{tiem:100},function(){
			location.href = tRoot + "/keFu/SKUActive/editSKUActive.do?step=1&id="+activeid;
		});
	},'json');
}
function editSKUActive4updateTotalNum(activeid,plantItemId,ele){
	if(parseInt(ele.value)<0){
		alert('总限量不能为负数');
		ele.value="";
		return false;		
	}
	var param = "id="+activeid+"&plantItemId="+plantItemId+"&totalNum=" + ele.value;
	$.post(tRoot+"/keFu/SKUActive/editSKUActive4updateTotalNum.do",param,function(data){
		layer.msg(data.desc,{tiem:100},function(){
			location.href = tRoot + "/keFu/SKUActive/editSKUActive.do?step=1&id="+activeid;
		});
	},'json');
}


/**
 * 删除
 * @param activeid
 * @param plantItemId
 */
function delSKUActiveGoodsInfo(activeid,plantItemId){
	var param = "id="+activeid+"&plantItemId="+plantItemId;
	$.post(tRoot+"/keFu/SKUActive/delSKUActiveGoodsInfo.do",param,function(data){
		layer.msg(data.desc,{tiem:100},function(){
			location.href = tRoot + "/keFu/SKUActive/editSKUActive.do?step=1&id="+activeid;
		});
	},'json');
}


/**
 * 保存修改
 * @param activeid
 */
function saveEditSKUActive(activeid){
	var param = "id="+activeid;
	$.post(tRoot+"/keFu/SKUActive/saveEditSKUActive.do",param,function(data){
		layer.msg(data.desc,{tiem:1000},function(){
			location.href = tRoot + "/keFu/SKUActive/editSKUActive.do?step=1&id="+activeid;
		});
	},'json');
}

/**
 * 单品促销活动，加载活动列表
 */
function loadSKUActiveList(){
	var param = $('#allSKUActive').serialize();
//	var pageIndex = $('#kkpager_btn_go_input').val();
	var pageIndex = $('#curpageIndex').val();
	param = param + "&pageIndex=" + pageIndex;
	location.href = tRoot + "/keFu/SKUActive/getAllSKUActive.do?"+param;
}

function querySpGroupList(){
	 //获取定格列表
	var zNodes = "";
    $.ajax({
		type : "POST",
		url : tRoot + "/Customer/SpGroup/getAcTiveSpGroupList.do",
		dataType:'json',
		async : true,
		data : $('#addForm').serialize(),
		success : function(da) {
			if(da.success){
				zNodes = da.message;
			}else{
				layer.msg(da.message);
			}
		},
		error : function(da) {
			alert('失败的请求!');
		}
	});
    return zNodes;
}
/**
 * 从属性图获取定格id
 * @param zTreeObj
 * @returns {Array}
 */
function queryspGroupIdsFromZtree(zTreeObj){
	var spGroupIds = new Array();
	$.each(zTreeObj.getSelectedNodes(),function(i,item){
		var chongfu = false;
		var trSpGroupIds = $("input[name='spGroupIds']");
		//console.log(trSpGroupIds);
		if(trSpGroupIds.length>0){
			trSpGroupIds.each(function(){
				if(item.id == $(this).val()){
					chongfu=true;
					return false;
				}
			});
		}
		if(chongfu == false){
			spGroupIds.push(item.id);
		}
	});
	return spGroupIds;
}

function querySuppliers(zTreeObj){
	var spGroupIds = new Array();
	$.each(zTreeObj.getSelectedNodes(),function(i,item){
		spGroupIds.push(item.id);
	});
	if(spGroupIds.length>0){
		var indexs = $(".index");
		var index = 0;
		if(indexs.length>0){
			index = indexs.length+1;
		}else{
			index = 1;
		}
		var html="";
		$.ajax({
			url:tRoot+'/keFu/SKUActive/spGroupOk.do',
			type:'post',
			data:{spGroupIds:spGroupIds},
			dataType:'json',
			success:function(d){
				if(d.success){
//					joinSuppliers(d.message);
					index = 1;
					$.each(d.message,function(i,item){
						html+=
							'<tr>'
								+'<td><span class="index">'+(i+index)+'</span></td>'
								+'<td>'+ item.provinceName + '/' + item.cityName + '/' + item.areaName 
								       + '<input type="hidden" name="provinceNames" value="'+item.provinceName+'"/>' 
								       + '<input type="hidden" name="cityNames" value="'+item.cityName+'"/>' 
								       + '<input type="hidden" name="areaNames" value="'+item.areaName+'"/>' 
								+'</td>'
								+'<td>'+item.groupName+'<input type="hidden" name="groupNames" value="'+item.groupName+'"/>'
								+'<input type="hidden" name="spGroupIds" value="'+item.spGroupId+'"/></td>'
								+'<td>'+item.supplierCode+'<input type="hidden" name="supplierCodes" value="'+item.supplierCode+'"/>' 
								       + '<input type="hidden" name="supplierIds" value="'+item.id+'"/></td>'
								+'<td>'+item.supplierName+'<input type="hidden" name="supplierNames" value="'+item.supplierName+'"/></td>'
								+'<td>'+item.mobile+'<input type="hidden" name="mobiles" value="'+item.mobile+'"/></td>'
								+'<td><span class="icon-op icon-op-del J_del" title="删除" ></span></td>'
							+'</tr>';
					});
					$('#supplier_info').html(html);
					//$('#supplier_info').innerHTML=html;
				}
			},
			error:function(e){
				alert("请求有误，请重试");
			}
		});
	}
	$('.J_dialog, .cover-all').fadeOut();
}

function joinSuppliers(list){
	var html = "";
	$.each(list,function(i,item){
		html+=
			'<tr>'
				+'<td><span class="index">'+(i+index)+'</span></td>'
				+'<td>'+item.provinceName+'/'+item.cityName+'/'+item.areaName+'<input type="hidden" name="provinceNames" value="'+item.provinceName+'"/><input type="hidden" name="cityNames" value="'+item.cityName+'"/><input type="hidden" name="areaNames" value="'+item.areaName+'"/></td>'
				+'<td>'+item.groupName+'<input type="hidden" name="groupNames" value="'+item.groupName+'"/><input type="hidden" name="spGroupIds" value="'+item.spGroupId+'"/></td>'
				+'<td>'+item.supplierCode+'<input type="hidden" name="supplierCodes" value="'+item.supplierCode+'"/><input type="hidden" name="supplierIds" value="'+item.id+'"/></td>'
				+'<td>'+item.supplierName+'<input type="hidden" name="supplierNames" value="'+item.supplierName+'"/></td>'
				+'<td>'+item.mobile+'<input type="hidden" name="mobiles" value="'+item.mobile+'"/></td>'
				+'<td><span class="icon-op icon-op-del J_del" title="删除" onclick="delSupplier('+item.id+')"></span></td>'
			+'</tr>';
	});
	return html;
}

function delSupplier(id){
	var flg = false;
	$.ajaxSetup({
        async : false  
    });
	var index = 1;
	$.post(tRoot+"/keFu/SKUActive/delSupplier.do",{supplierId:id},function(data){
		flg = data.success;
		var array = data.message;
	},'json');
	return flg;
}

