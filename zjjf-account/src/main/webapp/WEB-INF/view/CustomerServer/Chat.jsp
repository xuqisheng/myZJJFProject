<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="../common/header.jsp"></jsp:include>
	
	<!--[if lt IE 8]>
    <script src="${root}/resources/js/chat/json3.min.js" type="text/javascript"></script><![endif]-->
    <script src="${root}/resources/js/chat/socket.io.js" type="text/javascript"></script>
    
    
    <link rel="stylesheet" type="text/css" href="${root}/resources/css/chat/style.css"/>
</head>
<body>
	<div style="display: none;" id="imgDiv">
		<img id="voicePng" alt="XX" src="${root}/resources/images/chat/voice.png" />
		<img id="${userId }" alt="我的头像" src="${root}/resources/images/chat/chat_admin_logo.png" />
		<input id="chatId" value="${chatId }" type="hidden" />
		<input id="userId" value="${userId }" type="hidden" />
		<input id="type" value="${type }" type="hidden" />
	</div>
	<div id="chatbox">
	    <div id="fix" style="background:#3d3d3d;height: 28px; width: 100%;font-size:12px;display: none;">
	        <div style="line-height: 28px;color:#fff;">
	            <span style="text-align:left;margin-left:10px;">
	            	<a href="##" id="back_a">返回</a>
	           	</span>
	            <span style="float:right; margin-right:10px;"><span id="showusername"></span> | 
				<a href="javascript:;" onclick="CHAT.logout()" style="color:#fff;">标志会话结束</a></span>
	        </div>
	    </div>
	    <div id="doc">
	        <div id="chat">
	            <div id="message" class="message" style="margin-bottom: 50px;">
	            </div>
	            <div style="margin-top:50px;width:100%;position:fixed;bottom:0px;padding-top: 5px; padding-bottom: 5px; background-color: #ffffff;">
	                <table width="100%" cellspacing="0" cellpadding="0">
	                    <tr>
	                        <td width="60px" style="display: none;">
	                            <div style="width: 30px; float: left;">
	                            	<a id="selectVoice">
	                                	<img style=" width: 30px; height: 30px" src="${root }/resources/images/chat/msc.png" alt="添加图标">
	                               </a>
	                            </div>
	                            <div style="width: 30px; float: left;">
	                            	<a id="selectPic">
	                                	<img style="width: 30px; height: 30px" src="${root }/resources/images/chat/upload_img.png" alt="添加图标">
	                                </a>
	                            </div>
	                        </td>
	                        <td>
	                            <div class="input" style="float: left;  width: 100%;">
	                                <input type="text" maxlength="140" placeholder="请输入聊天内容，按Ctrl提交" id="content" name="content">
	                            </div>
	                        </td>
	                        <td width="80px">
	                            <div class="action" style="width: 80px; float: right;">
	                                <button type="button" id="mjr_send" onclick="CHAT.submit();">提交</button>
	                            </div>
	                        </td>
	                    </tr>
	                </table>
	                <div style="clear: both;"></div>
	            </div>
	        </div>
	    </div>
	</div>
</body>
<script src="${root}/resources/js/chat/client.js" type="text/javascript"></script>
<script type="text/javascript">
	window.onload=function(){
    	$.ajax({
    		url : root + "/Common/GetImUrl.do",
    		type : "GET",
    		dataType : "jsonp",
    		cache : false,
    		async : false,
    		jsonpCallback : "jsonpCallback",
    		success : function(url) {
    			gotoChat(url);
    		}
    	});
	};
	
	function gotoChat(url){
		$("#selfPic").attr("src","");
	    var userId=$("#userId").val();
	    var chatId=$("#chatId").val();
	    var back_a="";
	    var type=$("#type").val();
	    
	    
		CHAT.socketUrl=url+"/message";
		CHAT.usernameSubmit(userId,chatId,type);
	}
</script>
</html>