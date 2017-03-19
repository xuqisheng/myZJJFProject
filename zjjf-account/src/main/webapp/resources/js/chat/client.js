var pictureSource;
var destinationType;
var file = null;
var temoVoiceFile = "myrecording.mp3";

(function() {
	var d = document, w = window, p = parseInt, dd = d.documentElement, db = d.body, dc = d.compatMode == 'CSS1Compat', dx = dc ? dd : db, ec = encodeURIComponent;

	w.CHAT = {
		msgObj : d.getElementById("message"),
		screenheight : w.innerHeight ? w.innerHeight : dx.clientHeight,
		senderId : null,
		chatId : null,
		type : null,
		acceptId : null,
		socket : null,
		showUrl : 'http://192.168.3.124:3001/upload/show.html',
		showVoiceUrl : 'http://192.168.3.124:3001/upload/showvoice.html',
		socketUrl : '',
		uploadUrl : 'http://192.168.3.124:3001/upload/uploadnew.html',
		uploadVoiceUrl : 'http://192.168.3.124:3001/upload/uploadvoice.html',
		mediaRec : null,
		mediaTimer : null,
		myTextDiv : '<li id="#id"><div class="message"><div class="msgHead floatRight"><img src="#photo#" width="50px" height="50px" class="circle"/></div><div class="dialogRight">#content#</div></div></li>',
		otherTestDiv : '<li id="#id"><div class="message"><div class="msgHead floatLeft"><img src="#photo#" width="50px" height="50px" class="circle"/></div><div class="dialogLeft">#content#</div></div></li>',
		myPicDiv : '<li id="#id"><div class="message"><div class="msgHead floatRight"><img src="#photo#" width="50px" height="50px" class="circle"/></div><div class="dialogRight"><img src="#pic#" width="50px" height="50px" /></div></div></li>',
		otherPicDiv : '<li id="#id"><div class="message"><div class="msgHead floatLeft"><img src="#photo#" width="50px" height="50px" class="circle" /></div><div class="dialogLeft"><img src="#pic#" width="50px" height="50px" /></div></div></li>',
		myVoiceDiv : '<li id="#id"><div class="message"><div class="msgHead floatRight"><img src="#photo#" width="50px" height="50px" class="circle"/></div><div class="dialogRight"><img onclick="CHAT.playAudio(\'#voiceUrl#\');" class="circle" width="30px" src="#pic#" alt="" /></div></div></li>',
		otherVoiceDiv : '<li id="#id"><div class="message"><div class="msgHead floatLeft"><img src="#photo#" width="50px" height="50px" class="circle" /></div><div class="dialogLeft"><img class="circle" onclick="CHAT.playAudio("#voiceUrl#");" width="30px" src="#pic#" alt="" /></div></div></li>',
		pageIndex : 1,
		pageSize : 10,
		lastTime : new Date(),

		// 让浏览器滚动条保持在最低部
		scrollToBottom : function() {
			w.scrollTo(0, this.msgObj.clientHeight);
		},

		// 退出，本例只是一个简单的刷新
		logout : function() {
			// this.socket.disconnect();
			// location.reload();
			this.socket.emit('leave_chat', {
				sendId : this.senderId,
				chatId : this.chatId
			});
			window.top.onChat = null;
		},
		// 提交聊天消息内容
		submit : function() {
			var content = d.getElementById("content").value;
			if (content != '') {
				var obj = {
					senderId : this.senderId,
					chatId : this.chatId,
					type : 0,
					acceptId : this.acceptId,
					content : content
				};
				this.socket.emit('message', obj);
				d.getElementById("content").value = '';
			}
			return false;
		},
		genUid : function() {
			return new Date().getTime() + "" + Math.floor(Math.random() * 899 + 100);
		},
		// 更新系统消息，本例中在用户加入、退出的时候调用
		updateSysMsg : function(o, action) {
			// 当前在线用户列表
			var onlineUsers = o.onlineUsers;
			// 当前在线人数
			var onlineCount = o.onlineCount;
			// 新加入用户的信息
			var user = o.user;

			// 更新在线人数
			var userhtml = '';
			var separator = '';
			for (key in onlineUsers) {
				if (onlineUsers.hasOwnProperty(key)) {
					userhtml += separator + onlineUsers[key];
					separator = '、';
				}
			}
			// d.getElementById("onlinecount").innerHTML = '当前共有 '+onlineCount+'
			// 人在线，在线列表：'+userhtml;

			// 添加系统消息
			var html = '';
			html += '<div class="msg-system">';
			html += user.sendId;
			html += (action == 'login') ? ' 加入了聊天室' : ' 退出了聊天室';
			html += '</div>';
			var section = d.createElement('section');
			section.className = 'system J-mjrlinkWrap J-cutMsg';
			section.innerHTML = html;
			this.msgObj.appendChild(section);
			this.scrollToBottom();
		},
		// 第一个界面用户提交用户名
		usernameSubmit : function(sendId, chatId, type) {
			if (sendId != "" && chatId != "") {
				d.getElementById("chatbox").style.display = 'block';
				this.init(sendId, chatId, type);
			}
			return false;
		},
		login : function() {
			this.socket.emit('join_chat', {
				senderId : this.senderId,
				chatId : this.chatId
			});
		},
		getUserPic : function(userId) {
			var pic;
			$.ajax({
				url : root + "/Common/GetUserPic.do",
				type : "GET",
				dataType : "jsonp",
				cache : false,
				data : "id=" + userId,
				async : false,
				jsonpCallback : "jsonpCallback",
				success : function(data) {
					pic = data;
				}
			});
			return pic;
		},
		addLoadMsg : function(obj) {
			var isme = (obj.senderId == CHAT.senderId) ? true : false;
			var contentDiv = '<div>' + obj.content + '</div>';
			var usernameDiv = '<span>' + obj.senderId + '</span>';
			// 图片
			if (obj.type == 1) {
				try {
					var picUrl = this.showUrl + '?id=' + obj.id;
					contentDiv = '<div><img  style="max-width: 200px;" src="' + picUrl + '" alt="" /></div>';
				} catch (e) {
					alert(e);
				}
			}
			// 语音
			if (obj.type == 2) {
				try {
					var picUrl = $("#voicePng").attr('src');
					var voiceUrl = this.showVoiceUrl + '?id=' + obj.id;
					contentDiv = '<div><img onclick="CHAT.playAudio(\'' + voiceUrl + '\');" width="30px" src="' + picUrl + '" alt="" /></div>';
				} catch (e) {
					alert(e);
				}
			}
			var urlPic;
			if (!$("#" + obj.senderId).attr("src")) {
				// 获取该发送人的头像
				urlPic = /*root +*/ CHAT.getUserPic(obj.senderId);
				$("#imgDiv").append('<img id="' + obj.senderId + '" src="' + urlPic + '" with="40px" height="40px" />');
			} else {
				urlPic = $("#" + obj.senderId).attr("src");
			}
			// 加入到头像列表
			usernameDiv = '<img id="' + obj.senderId + '" src="' + urlPic + '" with="40px" height="40px" />';
			var section = d.createElement('section');
			if (isme) {
				section.className = 'user';
				section.innerHTML = contentDiv + usernameDiv;
			} else {
				section.className = 'service';
				section.innerHTML = usernameDiv + contentDiv;
			}
			CHAT.msgObj.appendChild(section);
			// 滚动到最后
			this.scrollToBottom();
		},
		addMsg : function(obj) {
			var isme = (obj.senderId == CHAT.senderId) ? true : false;
			var contentDiv = '<div>' + obj.content + '</div>';
			var usernameDiv = '<span>' + obj.senderId + '</span>';
			// 图片
			if (obj.type == 1) {
				try {
					var picUrl = this.showUrl + '?id=' + obj.id;
					contentDiv = '<div><img  style="max-width: 200px;" src="' + picUrl + '" alt="" /></div>';
				} catch (e) {
					alert(e);
				}
			}
			// 语音
			if (obj.type == 2) {
				try {
					var picUrl = $("#voicePng").attr('src');
					var voiceUrl = this.showVoiceUrl + '?id=' + obj.id;
					contentDiv = '<div><img onclick="CHAT.playAudio(\'' + voiceUrl + '\');" width="30px" src="' + picUrl + '" alt="" /></div>';
				} catch (e) {
					alert(e);
				}
			}
			var urlPic;
			if (!$("#" + obj.senderId).attr("src")) {
				// 获取该发送人的头像
				urlPic = root + CHAT.getUserPic(obj.senderId);
				$("#imgDiv").append('<img id="' + obj.senderId + '" src="' + urlPic + '" with="40px" height="40px" />');
			} else {
				urlPic = $("#" + obj.senderId).attr("src");
			}
			// 加入到头像列表
			usernameDiv = '<img id="' + obj.senderId + '" src="' + urlPic + '" with="40px" height="40px" />';
			var section = d.createElement('section');
			if (isme) {
				section.className = 'user';
				section.innerHTML = contentDiv + usernameDiv;
			} else {
				section.className = 'service';
				section.innerHTML = usernameDiv + contentDiv;
			}
			CHAT.msgObj.appendChild(section);
			// 滚动到最后
			this.scrollToBottom();
		},
		loadNoReadMsg : function(msg) {
			// alert('未读：'+msg.length);
			for (var i = msg.length-1; i >= 0; i--) {
				CHAT.addLoadMsg(msg[i]);
				CHAT.lastTime = msg[i].sendTime;
			}
		},
		goReadMsg : function() {
			// 拉取未读消息
			this.socket.emit('loadMessage', {
				senderId : this.senderId,
				chatId : this.chatId,
				pageIndex : this.pageIndex,
				pageSize : this.pageSize,
				date : CHAT.lastTime
			});
			this.pageIndex++;
		},
		init : function(sendId, chatId, type) {
			d.getElementById("showusername").innerHTML = this.senderId;
			this.msgObj.style.minHeight = (this.screenheight - db.clientHeight + this.msgObj.clientHeight) + "px";
			// 滚动到最后
			this.scrollToBottom();

			this.senderId = sendId;
			this.chatId = chatId;
			this.type = type;

			// 连接websocket后端服务器
			this.socket = io.connect(this.socketUrl);

			this.socket.on('connect', function(socket) {
				CHAT.login();
			});

			// 监听新用户登录
			this.socket.on(this.senderId + '_join_' + this.chatId, function(o) {
				if (o.code) {
					CHAT.goReadMsg(new Date());
				} else {
					alert(o.msg);
					CHAT.logout();
				}
			});

			// 监听拉取未读消息
			this.socket.on(this.senderId + "_loadMsg", function(o) {
				CHAT.loadNoReadMsg(o);
			});

			// 监听用户退出
			this.socket.on('logout', function(o) {
				CHAT.updateSysMsg(o, 'logout');
			});

			// 监听消息发送
			this.socket.on(this.chatId, function(obj) {
				CHAT.addMsg(obj);
			});

			// 监听审核消息
			this.socket.on(this.senderId + '_' + this.chatId, function(obj) {
				alert('这个是学生的审核消息，暂时不处理!');
			});

			this.uploadUrl = root + "/Chat/Upload.do";
			this.uploadVoiceUrl = root + "/Chat/Upload.do";

		}
	};
	// 通过“回车”提交信息
	d.getElementById("content").onkeydown = function(e) {
		e = e || event;
		if (e.keyCode === 13) {
			CHAT.submit();
		}
	};
})();