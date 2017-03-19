/**
 * @author luke at 2014年11月26日下午2:14:06
 * @Email luke@mibodoctor.com
 * @Desc 主要存放管理模块的公共js函数，包括面向对象声明，公共库函数
 */

/**
 * 弹出alert
 */
function alertMsg(msg) {
	$.messager.alert('提示', msg);
}

/**
 * 弹出alert
 * $.messager.alert(title, msg, icon, fn)
 * icon四种设置："error"、"info"、"question"、"warning"
 */
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}

function getSelections(id) {
	var ss = [];
	var rows = $('#' + id).datagrid('getSelections');
	for (var i = 0; i < rows.length; i++) {
		var row = rows[i];
		ss.push(row.id);
	}
	return ss;
}

/**
 * 获取
 * 
 * @param id
 * @returns
 */
function getComboboxVal(id) {
	return $('#' + id).combobox('getValue');
}

/**
 * 关闭当前弹出窗口
 */
function closeWindow() {
	var id = window.top.winArray.pop();
	// 获取当前页面父页面的 div ID
	parent.$('#' + id).window('close');
}

/**
 * 打开弹出框
 * 
 * @param title
 *            弹出框标题
 * @param url
 * @param width
 * @param height
 * @param callBack
 */
function showWindow(title, url, width, height, callBack) {
	var id = new Date().getTime();
	window.top.winArray.push(id);
	var _dialog = window.top
			.$(
					"<div id='"
							+ id
							+ "' style='height:100px;width:100px;'><iframe style='width:100%;height:100%;border:0;' src='"
							+ url + "'></iframe></div>").appendTo(
					window.top.document.body);
	_dialog.dialog({
		title : title,
		width : width,
		height : height,
		iconCls : "icon-save",
		modal : true,
		onClose : function() {
			callBack();
			$('#' + id).html("");
			_dialog.dialog("destroy");
		}
	});
}

/**
 * 打开弹出框
 * 
 * @param title
 *            弹出框标题
 * @param url
 * @param width
 * @param height
 * @param callBack
 */
function showWindow2( title, url) {
	var id = new Date().getTime();
	window.top.winArray.push(id);
	var _dialog = window.top
	.$( "<div id='" +id+ "' style='width:90%;height:90%'><img src='"+ url + "'></img></div>")
	.appendTo(window.top.document.body);
	_dialog.dialog({
		title : title,
		iconCls : "icon-save",
		modal : true,
		onClose : function() {
			callBack();
			$('#' + id).html("");
			_dialog.dialog("destroy");
		}
	});
}

/**
 * creat by lukehua
 * 对页面的所有带有 miboshowBigPic 属性的 img 放大
 * 
 */
//$(".miboshowBigPic").live("click",function(){
//	showWindow2("大图",$(this).attr("src"));
//})

/**
 * 公共状态 formater by lukehua
 * 
 * @param title
 *           格式化状态：0-可用，1-不可用
 * @param url
 * @param width
 * @param height
 * @param callBack
 */
var miboState = new Array({value:0,lable:"不可用"},{value:1,lable:"可用"});
function pulbicFormatState(val, row) {
	if (val == 1) {
		return '可用';
	} else {
		return '不可用';
	}
}

/**
 * 公共状态 formater by lukehua
 * 
 * @param title
 *           格式化状态：0-可用，1-不可用
 * @param url
 * @param width
 * @param height
 * @param callBack
 */
var miboIsManager = new Array({value:0,lable:"否"},{value:1,lable:"是"});
function pulbicFormatIsManager(value, row) {
    for(var i=0; i<miboIsManager.length; i++){  
        if (miboIsManager[i].value == value) return miboIsManager[i].lable;  
    }  
    return value;	
}

/**
 * 公共状态 formater by lukehua
 * 
 * @param title
 *             格式化性别：0-男，1-女
 * @param url
 * @param width
 * @param height
 * @param callBack
 */
var miboUserSex = new Array({value:0,lable:"男"},{value:1,lable:"女"});
function publicFormatSex(value, row) {
	if (value == 0) {
		return "男";
	} else if (value == 1) {
		return "女";
	} else {
		return "<span style='color:red'>未知</span>";
	}
}

/**
 * 公共状态 formater by lukehua
 * 
 * @param title
 *            格式化删除状态：0-否，1-是
 * @param url
 * @param width
 * @param height
 * @param callBack
 */
var miboDeleteFlag = new Array({value:0,lable:"否"},{value:1,lable:"是"});
function publicFormatDel(value, row) {
	if (value == 0) {
		return "否";
	} else if (value == 1) {
		return "<span style='color:red'>是</span>";
	} else {
		return "<span style='color:red'>错误</span>";
	}
}
/**
 * 公共状态 formater by lukehua
 * 
 * @param title
 *            格式化文章类型：0-文章，1-连接
 * @param url
 * @param width
 * @param height
 * @param callBack
 */
var miboArticleType = new Array({value:0,lable:"文章"},{value:1,lable:"连接"});
function publicFormatArticleType(value, row) {
	if (value == 0) {
		return "文章";
	} else if (value == 1) {
		return "连接";
	} else {
		return "<span style='color:red'>错误</span>";
	}
}
/**
 * 公共状态 formater by lukehua
 * 
 * @param title
 *            格式化文章发布类型类型：0-医生文章，1-觅博文章
 * @param url
 * @param width
 * @param height
 * @param callBack
 */
var miboArticlePubType = new Array({value:0,lable:"医生文章"},{value:1,lable:"觅博文章"});
function publicFormatArticlePubType(value, row) {
	if (value == 0) {
		return "医生文章";
	} else if (value == 1) {
		return "觅博文章";
	} else {
		return "<span style='color:red'>错误</span>";
	}
}
/**
 * 公共状态 formater by lukehua
 * 
 * @param title
 *            格式化文章是否置顶：0-否，1-是
 * @param url
 * @param width
 * @param height
 * @param callBack
 */
var miboArticleIsTop = new Array({value:0,lable:"否"},{value:1,lable:"是"});
function publicFormatArticleIsTop(value, row) {
	if (value == 0) {
		return "否";
	} else if (value == 1) {
		return "是";
	} else {
		return "<span style='color:red'>错误</span>";
	}
}

/**
 * 公共状态 formater by lukehua
 * 
 * @param title
 *            格式化举报对象：0-文章，1-图片，2-链接
 * @param url
 * @param width
 * @param height
 * @param callBack
 */
var miboReportObjectType = new Array({value:0,lable:"文章"},{value:1,lable:"图片"},{value:1,lable:"链接"});
function publicFormatReportObjectType(value, row) {
    for(var i=0; i<miboReportObjectType.length; i++){  
        if (miboReportObjectType[i].value == value) return miboReportObjectType[i].lable;  
    }  
    return value;	
}

/**
 * 公共状态 formater by lukehua
 * 
 * @param title
 *            格式化举报处理方式：0-不做处理，1-删除
 * @param url
 * @param width
 * @param height
 * @param callBack
 */
var miboReportDealType = new Array({value:0,lable:"不做处理"},{value:1,lable:"删除"});
function publicFormatReportDealType(value, row) {
    for(var i=0; i<miboReportDealType.length; i++){  
        if (miboReportDealType[i].value == value) return miboReportDealType[i].lable;  
    }  
    return value;	
}
/**
 * 公共状态 formater by lukehua
 * 
 * @param title
 *            格式化举报处理方式：0-支付宝，1-快钱支付
 * @param url
 * @param width
 * @param height
 * @param callBack
 */
var miboUserPayType = new Array({value:1,lable:"支付宝"},{value:2,lable:"快钱支付"});
function publicFormatmiboUserPayType(value, row) {
    for(var i=0; i<miboUserPayType.length; i++){  
        if (miboUserPayType[i].value == value) return miboUserPayType[i].lable;  
    }  
    return value;	
}

/**
 * 公共状态 formater by lukehua
 * 
 * @param title
 *            格式化举报处理状态：0-未处理，1-已处理
 * @param url
 * @param width
 * @param height
 * @param callBack
 */
var miboReportDealState = new Array({value:0,lable:"未处理"},{value:1,lable:"已处理"});
function publicFormatReportDealState(value, row) {
	for(var i=0; i<miboReportDealState.length; i++){  
		if (miboReportDealState[i].value == value) return miboReportDealState[i].lable;  
	}  
	return value;	
}

/**
 * 公共状态 formater by lukehua
 * 
 * @param title
 *            格式化与本人关系：
 * @param url
 * @param width
 * @param height
 * @param callBack
 */
var miboRELATIONSHIP = new Array();
function publicFormatRELATIONSHIP(value, row) {
    for(var i=0; i<miboRELATIONSHIP.length; i++){  
        if (miboRELATIONSHIP[i].value == value) return miboRELATIONSHIP[i].lable;  
    }  
    return value;	
}
/**
 * 公共状态 formater by lukehua
 * 
 * @param title
 *            格式化文章内容,默认20个
 * @param url
 * @param width
 * @param height
 * @param callBack
 */
var miboArticleViewLength = 20;//如果有变化从新赋值
function publicFormatArticleViewLength(value, row) {
	if (value.length > miboArticleViewLength) {
		return value.substring(0,20)+"...";
	}
	return value;
}


/**
 * 公共状态 formater by lukehua
 * 
 * @param title
 *            格式化删除状态：false-否，true-是
 * @param url
 * @param width
 * @param height
 * @param callBack
 */
function publicFormatDel2(value, row) {
	if (value) {
		return "是";
	} else{
		return "否";
	}
}


/**
 * 公共状态 formater by lukehua
 * 
 * @param title
 *            格式化婚姻状态：0-未婚，1-已婚，2-保密
 * @param url
 * @param width
 * @param height
 * @param callBack
 */
var  miboMarryState = new Array({value:0,lable:"未婚"},{value:1,lable:"已婚"},{value:2,lable:"保密"},{value:3,lable:"其他"});
function publicFormatMarry(value, row) {
    for(var i=0; i<miboMarryState.length; i++){
        if (miboMarryState[i].value == value) return miboMarryState[i].lable;  
    }  
    return value;  
}



/**
 * 公共状态 formater by lukehua
 * 
 * @param title
 *            格式化日期：from  2014-12-02 00:00:00 to 2014-12-02
 * @param url
 * @param width
 * @param height
 * @param callBack
 */
function publicFormatDate(value, row) {
	if (value == null || value == "") {
		return "";
	}else{
		return value.substring(0,10);
	}
}

/**
 * 公共状态 formater by lukehua
 * 
 * @param title
 *            格式化医生类型：from  2014-12-02 00:00:00 to 2014-12-02
 * @param url
 * @param width
 * @param height
 * @param callBack
 */
var miboDoctorType = new Array({value:0,lable:"普通医生"},{value:1,lable:"学生"},{value:2,lable:"签约医生"},{value:3,lable:"签约顾问"},{value:4,lable:"觅博医生"},{value:5,lable:"统计医生"});
function publicFormatDoctorType(value, row) {
    for(var i=0; i<miboDoctorType.length; i++){
        if (miboDoctorType[i].value == value) return miboDoctorType[i].lable;  
    }  
    return value;  
}

/**
 * 公共状态 formater by lukehua
 * 
 * @param title
 *            格式化医生职称：from  2014-12-02 00:00:00 to 2014-12-02
 * @param url
 * @param width
 * @param height
 * @param callBack
 */
var miboDoctorProType = new Array();
function publicFormatDoctorPType(value, row) {
    for(var i=0; i<miboDoctorProType.length; i++){  
        if (miboDoctorProType[i].value == value) return miboDoctorProType[i].lable;  
    }  
    return value;  
}

/**
 * 公共状态 formater by lukehua
 * 
 * @param title
 *            格式化企业类型：from  2014-12-02 00:00:00 to 2014-12-02
 * @param url
 * @param width
 * @param height
 * @param callBack
 */
var  miboCompanyType =  new Array({value:0,lable:"药品企业"},{value:1,lable:"医疗器械企业"},{value:2,lable:"建筑企业"},{value:3,lable:"其他企业"});
function pulbicFormatCompanyType(value, row) {
    for(var i=0; i<miboCompanyType.length; i++){  
        if (miboCompanyType[i].value == value) return miboCompanyType[i].lable;  
    }  
    return value;
}

/**
 * 公共状态 formater by lukehua
 * 
 * @param title
 *            格式化图片
 * @param url
 * @param width
 * @param height
 * @param callBack
 */
function publicPicFormatter(value, row) {
	var picPrefix = $("#picPrefix").val();
	var pichtm = "<img src='"+picPrefix+value+"'  style='width:28px;height:28px;' />";
	return pichtm;
}
/**
 * 公共状态 formater by lukehua
 * 
 * @param title
 *            格式化订单类型：from  2014-12-02 00:00:00 to 2014-12-02
 * @param url
 * @param width
 * @param height
 * @param callBack
 */
var  miboOrderInfoState =  new Array({value:0,lable:"未支付"},{value:1,lable:"支付完成"},{value:2,lable:"资料已提交"},
							{value:3,lable:"申请退款"},{value:4,lable:"已完结"},{value:5,lable:"已确认"},{value:6,lable:"已取消"});
function pulbicFormatmiboOrderInfoState(value, row) {
    for(var i=0; i<miboOrderInfoState.length; i++){  
        if (miboOrderInfoState[i].value == value) return miboOrderInfoState[i].lable;  
    }  
    return value;
}

/**
 * 公共状态 formater by lukehua
 * 
 * @param title
 *            格式化审核状态：1-未审核，2-审核，其他-异常
 * @param url
 * @param width
 * @param height
 * @param callBack
 */
function publicCornerShengheStatus(value, row) {
	var result ;
	if(value == 1){
		result = "<span style='color:red'>未审核</span>"
	}else if(value == 2){
		result = "<span style='color:green'>已审核</span>"
	}else{
		result ="<span style='color:red'>状态异常</span>";
	}
	return result;
}

/***
*
*creat by lukehua
*div标签命名限制：
*     列表展示table：模块名称+dg
*	  弹出编辑框：模块名称+dlg
*     提交表单命名：模块名称+fm
* 参数json格式，{  modelName:**,
* 							listURL:***,
* 							creatURL:**,
* 							updateURL:**,
* 							deleteURL:**,
* 							dialogTitle:**,
* 							findListFun:fun
*						}
*/

MiBoCRUD = {
	creatNew:function(CRUDURL){
		var MiBoCRUDinstance={};
		var instanceURL="";
		var model_dg = CRUDURL.modelName+"dg";//table
		var model_dlg = CRUDURL.modelName+"dlg";//dialog
		var model_fm = CRUDURL.modelName+"fm";//form
		var model_newbt = CRUDURL.modelName+"toolbar-new";//create
		var model_editbt = CRUDURL.modelName+"toolbar-edit";//edit
		var model_deletebt = CRUDURL.modelName+"toolbar-delete";//delete
		var model_findListbt = CRUDURL.modelName+"toolbar-findlist";//findlist
		var model_savebt = CRUDURL.modelName+"dlgbt-save";//save
		var model_canelbt = CRUDURL.modelName+"dlgbt-cancel";//cancel
		var model_queryParams = CRUDURL.queryParams?CRUDURL.queryParams:{isDelete:0};

		//创建对象
		MiBoCRUDinstance.newObject=function(){
			$("#"+ model_dlg ).dialog("open").dialog('setTitle',CRUDURL.dialogTitle);
			$( "#"+model_fm ).form('clear');
			instanceURL = CRUDURL.creatURL;
		};
		//编辑对象
		MiBoCRUDinstance.editObject=function(){
			var row = $("#"+model_dg).datagrid('getSelected');
			if (row){
				$("#"+model_dlg).dialog('open').dialog('setTitle',CRUDURL.dialogTitle);
				$("#"+model_fm).form('load',row);
				instanceURL = CRUDURL.updateURL + '?id='+row.id;
			}
		};
		//保存对象
		MiBoCRUDinstance.saveObject = function(){
			$("#"+model_fm).form('submit',{
				url: instanceURL ,
				onSubmit: function(){
					return $(this).form('validate');
				},
				success: function(result){
					var result = eval('('+result+')');
					if (!result.success){
						$.messager.show({
							title: 'Error',
							msg: result.message
						});
					} else {
						$("#"+model_dlg).dialog('close'); // close the dialog
						$("#"+model_dg).datagrid('reload'); // reload the user data
					}
				}
			})
		};
		//取消
		MiBoCRUDinstance.optCancel = function(){
			$('#'+model_dlg).dialog('close');
		}
		//删除对象
		MiBoCRUDinstance.deleteObject = function(){
			var row = $("#"+model_dg).datagrid('getSelected');
			if (row){
				$.messager.confirm('Confirm','确定删除?',function(r){
					if (r){
						$.post( CRUDURL.deleteURL ,{ids:getSelections(model_dg).join(",")},function(result){
							if (result.success){
								$("#"+model_dg).datagrid('reload'); // reload the user data
							} else {
								$.messager.show({ // show error message
									title: 'Error',
									msg: result.message
								});
							}
						},'json');
					}
				});
			}
		};
		//初始化函数
		MiBoCRUDinstance.init=function(){
			//初始化datagride
			$("#"+model_dg).datagrid({
				url:CRUDURL.listURL,
				onDblClickRow:MiBoCRUDinstance.editObject,
				queryParams:model_queryParams
			});
			//事件注册
			$("#"+model_newbt).on("click",MiBoCRUDinstance.newObject);
			$("#"+model_editbt).on("click",MiBoCRUDinstance.editObject);
			$("#"+model_deletebt).on("click",MiBoCRUDinstance.deleteObject);
			$("#"+model_savebt).on("click",MiBoCRUDinstance.saveObject);
			$("#"+model_canelbt).on("click",MiBoCRUDinstance.optCancel);
			$("#"+model_findListbt).on("click",CRUDURL.findListFun);//查询事件
		}
		
		return MiBoCRUDinstance;
	}//creatNew

}





