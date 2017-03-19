$(document).ready(function(){
  // 在这里写你的代码...
});

function getRegionInfo(obj,objId,opType,fillVal){
	//根据操作类型决定取值
	//debugger;
	var pid = opType==0?$(obj).val():obj;
	
	if(pid != null && pid != ""){
		$.ajax({
			type: "post",
			url: ctx+"/base/queryRegionList.do",
			data: 'pId='+pid,
			dataType: "json",
			async: false,
			success: function(reObj) {
				//console.log(obj);
				if(reObj.success) {
					var selHtml = '';
					if(objId == 'cityId'){
						selHtml = '<option value="">城市</option>';
						//当省份下拉框的值改变的时候，将城市和区域下拉框值清空
						$("#cityId").select2("val", "");
						$("#areaId").select2("val", "");
					}else if(objId == 'areaId'){
						selHtml = '<option value="">区域</option>';
						//当城市下拉框的值改变的时候，将区域下拉框值清空
						$("#areaId").select2("val", "");
					} 
					$.each(reObj.list, function(i,item) {
		            	selHtml+='<option value="'+item.id+'">'+item.name+'</option>';
		            });
		            $("#"+objId).empty();
		            $("#"+objId).append(selHtml); 
		            //界面回填值
		            if(opType =="1"){
		            	$("#"+objId).select2("val", fillVal);
		            }
		            
				} else {
					alert(reObj.msg);
				} 
			},
			error : function(data) {
			}
		});
	}else{
		//如果区域切换上一级置空时，相应下级也需要置空处理
		if(objId == 'cityId'){
			$("#cityId").select2("val", "");
			$("#areaId").select2("val", "");
		}else if(objId == 'areaId'){
			$("#areaId").select2("val", "");
		}
	}
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

function closeLayer() {
	//当你在iframe页面关闭自身时
	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(index); //再执行关闭  
}

