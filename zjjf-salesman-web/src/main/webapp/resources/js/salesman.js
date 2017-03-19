$(document).ready(function(){
  // 在这里写你的代码...
});

function getRegionInfo(obj,objId){
	$.ajax({
		type: "post",
		url: rootPath+"/base/queryRegionList.do",
		data: 'pId='+$(obj).val(),
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
		url: "${root}/base/querySpGroupList.do",
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