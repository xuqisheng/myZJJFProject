var winArray=new Array()

//设置登录窗口
function openPwd() {
    $('#w').window({
        title: '修改密码',
        modal: true,
        shadow: true,
        closed: true,
        resizable:false
    });
}
//关闭登录窗口
function closePwd() {
    $('#w').window('close');
}

//修改密码
function serverLogin() {
    var $newpass = $('#txtNewPass');
    var $rePass = $('#txtRePass');

    if ($newpass.val() == '') {
        msgShow('系统提示', '请输入密码！', 'warning');
        return false;
    }
    if ($rePass.val() == '') {
        msgShow('系统提示', '请在一次输入密码！', 'warning');
        return false;
    }

    if ($newpass.val() != $rePass.val()) {
        msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
        return false;
    }
    
    if ($newpass.val().length < 6) {
        msgShow('系统提示', '密码长度不能低于6位', 'warning');
        return false;
    }

    $.post(root+'/account/authority/editpassword.do', {
    	newPassword:$newpass.val()
    },function(msg) {
    	if(msg.success){
            msgShow('系统提示', '恭喜，密码修改成功！<br/>您的新密码为：' + msg.message, 'info');
            $newpass.val('');
            $rePass.val('');
            closePwd();
    	}else{
    		msgShow('系统提示', '密码修改错误：' + msg.message, 'info');
    	}
    },"json")
    
}

$(function() {

    $('#editpass').click(function() {
        $('#w').window('open');
    });

    $('#btnEp').click(function() {
        serverLogin();
    })

	$('#btnCancel').click(function(){closePwd();})

    $('#loginOut').click(function() {
        $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {
            if (r) {
                location.href =root+'/account/authority/doLoginOut.do';
            }
        });
    });//  $('#loginOut').click
    
});

// $(function() {

//var tab = null;
//var accordion = null;
//var tree = null;
//$(function() {
//	// 布局
//	$("#layout1").ligerLayout({
//		leftWidth : 190,
//		height : '100%',
//		heightDiff : -34,
//		space : 4,
//		onHeightChanged : f_heightChanged
//	});
//
//	var height = $(".l-layout-center").height();
//
//	// Tab
//	$("#framecenter").ligerTab({
//		height : height
//	});
//
//	// 面板
//	$("#accordion1").ligerAccordion({
//		height : height - 24,
//		speed : null
//	});
//
//	$(".l-link").hover(function() {
//		$(this).addClass("l-link-over");
//	}, function() {
//		$(this).removeClass("l-link-over");
//	});
//	
//	$(".topMenu").each(function(){
//		var pId=$(this).attr("treeId");
//		// 树
//		$("#tree_"+pId).ligerTree({
//			//data : indexdata1,
//			url : root+'/Menu/GetUserMenu.do?pId='+pId+'&ranCode='+new Date().getTime(),
//			checkbox : false,
//			slide : true,
//			nodeWidth : 120,
//			attribute : [ 'nodename', 'url' ],
//			onSelect : function(node) {
//				if (!node.data.url)
//					return;
//				var tabid = $(node.target).attr("tabid");
//				if (!tabid) {
//					tabid = new Date().getTime();
//					$(node.target).attr("tabid", tabid);
//				}
//				f_addTab(tabid, node.data.text, root+node.data.url);
//			}
//		});
//	});
//	tab = $("#framecenter").ligerGetTabManager();
//	accordion = $("#accordion1").ligerGetAccordionManager();
//	tree = $("#tree1").ligerGetTreeManager();
//	$("#pageloading").hide();
//
//});
//function f_heightChanged(options) {
//	if (tab)
//		tab.addHeight(options.diff);
//	if (accordion && options.middleHeight - 24 > 0)
//		accordion.setHeight(options.middleHeight - 24);
//}
//function f_addTab(tabid, text, url) {
//	tab.addTabItem({
//		tabid : tabid,
//		text : text,
//		url : url
//	});
//}

