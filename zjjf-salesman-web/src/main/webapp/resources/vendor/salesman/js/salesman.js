$(document).ready(function(){
  // 在这里写你的代码...
});

function getRegionInfo(obj,objId,opType){
	//根据操作类型决定取值
	var pid = opType==0?$(obj).val():obj;
	$.ajax({
		type: "post",
		url: rootPath+"/base/queryRegionList.do",
		data: 'pId='+pid,
		dataType: "json",
		async: false,
		success: function(reObj) {
			//console.log(obj);
			if(reObj.success) {
				var selHtml = '';
				if(objId == 'citySelect'){
					selHtml = '<option value="">城市</option>';
				}else if(objId == 'areaSelect'){
					selHtml = '<option value="">区域</option>';
				} 
				$.each(reObj.list, function(i,item) {
	            	selHtml+='<option value="'+item.id+'">'+item.name+'</option>';
	            });
	            $("#"+objId).empty();
	            $("#"+objId).append(selHtml); 
	            
			} else {
				alert(reObj.msg);
			} 
		},
		error : function(data) {
		}
	});
}

/**
 * 获取区域对应定格数据
 */
function getSpGroupInfo(obj,objId,opType){
	//根据操作类型决定取值
	var areaId = opType==0?$(obj).val():obj;
	
	$.ajax({
		type: "post",
		url: rootPath+"/base/querySpGroupList.do",
		data: 'areaId='+areaId,
		dataType: "json",
		async: false,
		success: function(reObj) {
			//console.log(obj);
			if(reObj.success) {
				var selHtml = '<option value="">请选择</option>';
				$.each(reObj.list, function(i,item) {
	            	selHtml+='<option value="'+item.id+'">'+item.name+'</option>';
	            });
	            $("#"+objId).empty();
	            $("#"+objId).append(selHtml); 
	            
			} else {
				alert(reObj.msg);
			} 
		},
		error : function(data) {
		}
	});
}

/**
 * 获取线路定格数据
 */
function getLineSpGroup(obj,objId,opType){
	
	//根据操作类型决定取值
	var areaId = opType==0?$(obj).val():obj;
	$.ajax({
		type: "post",
		url: rootPath+"/base/getLineSpGroup.do",
		data: 'areaId='+areaId,
		dataType: "json",
		async: false,
		success: function(reObj) {
			//console.log(obj);
			if(reObj.success) {
				var selHtml = '<option value="">请选择</option>';
				$.each(reObj.list, function(i,item) {
	            	selHtml+='<option value="'+item.spGroupId+'">'+item.spGroupName+'</option>';
	            });
	            $("#"+objId).empty();
	            $("#"+objId).append(selHtml); 
	            
			} else {
				alert(reObj.msg);
			} 
		},
		error : function(data) {
		}
	});
}

/**
 * 获取线路定格数据
 */
function getLineList(obj,objId,opType){
	
	//根据操作类型决定取值
	var spGroupId = opType==0?$(obj).val():obj;
	$.ajax({
		type: "post",
		url: rootPath+"/base/getLineBySpGroupList.do",
		data: 'spGroupId='+spGroupId,
		dataType: "json",
		async: false,
		success: function(reObj) {
			//console.log(obj);
			if(reObj.success) {
				var selHtml = '<option value="">请选择</option>';
				$.each(reObj.list, function(i,item) {
	            	selHtml+='<option value="'+item.lineId+'">'+item.lineName+'</option>';
	            });
	            $("#"+objId).empty();
	            $("#"+objId).append(selHtml); 
	            
			} else {
				alert(reObj.msg);
			} 
		},
		error : function(data) {
		}
	});
}

/**
 * 固定电话校验
 * @param str
 * @returns {Boolean}
 */
function checkTelephone(str){
   var re = /^0\d{2,3}-?\d{7,8}$/;
   if(!re.test(str)){
       //alert("固定电话输入有误！");
       return true;
   }
   return false;
}

/**
 * 验证手机号是否正确
 */
function checkMobile(mobile)
{
	var regex = /^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/;
    if (!regex.test(mobile))
    {
        //alert( "您输入的手机号不合法" );
        return true;
    }
    return false;
}

//checkTelephone("0955-1234567T");//调用
/**
 * 校验金额
 */
function checkMoney(num){
	//var exp = /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/;
	var exp=/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/;
	//var exp = /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/;
	if(!exp.test(num)){
		//alert('注意：金额字段只支持后两位小数点');
		return true;
    }
    return false;	
}

/**
 * 校验数字
 * @param value
 * @returns {Boolean}
 */
function checkNumber(value) { 
	if (isNaN(value)) { 
		//alert("请输入数字"); 
		return true;
	} 
	return false;
}

function isNotNullObj(obj){
	for(var i in obj){
		if(obj.hasOwnProperty(i)){
			return true;
		}
	}
	return false;
}

//复选框的事件  
$("#chk_all").click(function(){
	//alert($("#chk_all").prop("checked"));
	if($("#chk_all").prop("checked")){
		$("input[name='chk_list']").prop("checked",true);  
	}else{
		$("input[name='chk_list']").prop("checked",false);  
	}
	
});

//子复选框的事件  
function setSelectAll(){  
    //当没有选中某个子复选框时，SelectAll取消选中  
    if (!$("#chk_list").checked) {  
        $("#chk_all").prop("checked", false);  
    }  
    var chsub = $("input[type='checkbox'][name='chk_list']").length; //获取subcheck的个数  
    var checkedsub = $("input[type='checkbox'][name='chk_list']:checked").length; //获取选中的subcheck的个数
    if (checkedsub == chsub) {  
        $("#chk_all").prop("checked", true);  
    }  
}  

