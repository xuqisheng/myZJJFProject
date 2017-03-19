var msgSocket;
$(function() {
	$.ajax({
		url : root + "/Common/GetImUrl.do",
		type : "GET",
		dataType : "jsonp",
		cache : false,
		async : false,
		success : function(data) {
			gotoConnection(data);
		}
	});
});

function gotoConnection(url) {
	msgSocket = io.connect(url + '/system_msg');
	msgSocket.on('connect', connectHandler);
	msgSocket.on('message', messageHandler);
	msgSocket.on('system', systemHandler);
	msgSocket.on('disconnect', disconnectHandler);
}

// 链接成功立马去认证
function connectHandler() {
	var userId = $("#userId").val();
	var token = $("#token").val();
	msgSocket.emit('login', {
		userId : userId,
		token : token
	});
}

function messageHandler(data) {
	dealOrder(data);
}

function systemHandler(data) {
	msgAlert('系统消息', data);
}

function disconnectHandler() {
	msgAlert('系统消息', '您已掉线！');
}

function msgAlert(title, msg) {
	$.messager.show({
		title : title,
		msg : msg,
		showType : 'show'
	});
}

function dealOrder(data) {
	
	// 有新聊天消息提醒
	if(data.msgType==4007){
		initChatMsgList(1);	// ./outlook2.js 自动刷新聊天列表
		msgAlert('系统消息', '新聊天消息');
		return;
	}
	
	// 有超时订单消息提醒
	if(data.msgType==1006){
		var url = root + "/admin/orderInfoctl/TimeOutListPage.do";
		msgAlert("系统消息", '<a  href="javascript:addTab(\'超时订单\', \''+url+'\', \'\');" >' + data.data + '</a>');
		return;
	}
	
	// 新增订单弹出窗口
	var flag = false; // 默认不需要推送
	if(data.msgType==1001 || data.msgType==1002 || data.msgType==1003 || data.msgType==1004 || data.msgType==1005){
		refreshUnReadCount();
		flag = true; // 以上几种类型需要推送
	}
	if(flag == false){
		return; // 不需要推送
	}
	
	var orderId = data.data;
	// var menuId = "40284b814a52405d014a525f00ba0014";
	var url = root+"/CustomerServer/MyOrderListPage.do";
	
	var msg = "";
	$.ajax({
		cache : false,
		type : "POST",
		url : root + "/CustomerServer/ShowOrderMsg.do?orderId="+orderId,
		data : null,
		dataType : "json",
		async : false,
		error : function(data) {
		},
		success : function(data) {
			if (data.success == true) {
				msg = data.message;
			}
		}
	});
	
	msgAlert("系统消息", '<a  href="javascript:addTab(\'客服订单列表\', \''+url+'\', \'\');" >' + msg + '</a>');
}

/**
 * 刷新未读消息数目
 */
function refreshUnReadCount(){
	$.ajax({
		cache : false,
		type : "POST",
		url : root + "/CustomerServer/getUnReadCount.do",
		data : null,
		dataType : "json",
		async : false,
		error : function(data) {
		},
		success : function(data) {
			if (data.success == true) {
				$("#unReadCount").val(data.message);
			}
		}
	});
}