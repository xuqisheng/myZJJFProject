// 日期范围  my97datepicker
function dateRange(start, end, fmt) {
	fmt || 0; //0 yyyy-MM-dd HH:mm:ss  1 yyyy-MM-dd
	var $time_start = $(start);
	var $time_end = $(end);
	$time_start.on('focus', function() {
		var idx = $(start).index(this);
		WdatePicker({
			dateFmt: fmt ? "yyyy-MM-dd" : "yyyy-MM-dd HH:mm:ss",
			isShowClear: false,
			isShowWeek: true,
			onpicked: function() {
				$(end).eq(idx).focus();
			}
		});
	});
	$time_end.on('focus', function() {
		var idx = $(end).index(this);
		WdatePicker({
			dateFmt: fmt ? "yyyy-MM-dd" : "yyyy-MM-dd HH:mm:ss",
			isShowClear: false,
			isShowWeek: true,
			minDate: $(start).eq(idx).val()
		});
	});
}

//拖拽
function drag(dragSon,dragFather){
    		$(dragSon).bind("mousedown", function(event) {
					/* 获取需要拖动节点的坐标 */
					var offset_x = $(dragFather)[0].offsetLeft; //x坐标
					console.log(offset_x)
					var offset_y = $(dragFather)[0].offsetTop; //y坐标
					/* 获取当前鼠标的坐标 */
					var mouse_x = event.pageX;
					var mouse_y = event.pageY;
					/* 绑定拖动事件 */
					/* 由于拖动时，可能鼠标会移出元素，所以应该使用全局（document）元素 */
					$(document).bind("mousemove", function(ev) {
						/* 计算鼠标移动了的位置 */
						var _x = ev.pageX - mouse_x;
						var _y = ev.pageY - mouse_y;
						/* 设置移动后的元素坐标 */
						var now_x = (offset_x + _x) + "px";
						var now_y = (offset_y + _y) + "px";
						/* 改变目标元素的位置 */
						$(dragFather).css({
							top: now_y,
							left: now_x
						});
					});
				});
				/* 当鼠标左键松开，接触事件绑定 */
				$(document).bind("mouseup", function() {
					$(this).unbind("mousemove");
				});
    	}


//拖拽

// dialog位置居中
function dialogPosCenter(dialog) {
	var $winwidth = ($(window).width() > $(dialog).width()) ? $(window).width() : $(dialog).width();
	var $winheight = ($(window).height() > $(dialog).height()) ? $(window).height() : $(dialog).height();
	var dleft = ($winwidth - $(dialog).width()) / 2;
	var dtop = ($winheight - $(dialog).height()) / 2;
	$(dialog).css({
		'left': dleft,
		'top': dtop
	});
}
// 全选，全不选
function selectAll(btn, chk) {
	$(btn).on('click', function() {
		//更改背景色-J
		var attrs = $(this).prop("checked");
		if(attrs) {
			$("tbody tr").has("input[type='checkbox']").css({
				"background": "#009dd9",
				"color": "#fff"
			});
		} else {
			$("tbody tr").has("input[type='checkbox']").css({
				"background": "#fff",
				"color": "black"
			});
		}

		if($(this).is(':checked')) {
			$(chk).prop('checked', 'checked');
		} else {
			$(chk).prop('checked', '');
		}
	});
	$('html, body').on('click', chk, function() {
		var chkArray = [];
		$(chk).each(function() {
			if($(this).is(':checked')) {
				chkArray.push($(this));
			}
		});
		if($(chk).length == chkArray.length) {
			$(btn).prop('checked', 'checked');
		} else {
			$(btn).prop('checked', '');
		}
	});
}
// table 单选 tr -J
function selectTr(table, tr) {
	$(table).on('click', tr, function(event) {
		var event = event || window.event;
		var target = event.target;
		event.stopPropagation();
		var inp = $(this).find("input[type='checkbox']");
		var attr1 = inp.prop("checked");
		var inpnum = $(this).find("input[type='checkbox']").length;
		if(target.localName == "input") {
			if(attr1) {
				$(this).css({
					"background": "#009dd9",
					"color": "#fff"
				});
			} else {
				$(this).css({
					"background": "#fff",
					"color": "black"
				});
			}
		} else if(inpnum == 1) {
			if(attr1) {
				inp.prop("checked", false);
				$(this).has("input[type='checkbox']").css({
					"background": "#fff",
					"color": "black"
				});
			} else {
				inp.prop("checked", true);
				$(this).has("input[type='checkbox']").css({
					"background": "#009dd9",
					"color": "#fff"
				});
			}
		}
	})
}
// tab
function tab(groupname, mode, activeClass) {
	var $item = $('[data-groupname="' + groupname + '"][data-tab="item"]');
	var $content = $('[data-groupname="' + groupname + '"][data-tab="content"]');
	var $itemActive = $item.filter('.' + (activeClass || 'active'));
	if($itemActive.length == 1) {
		var index = $item.index($itemActive);
		$content.not($content.eq(index)).hide();
	} else {
		$content.not(':first').hide();
	}
	$('html, body').on(mode || 'click', '[data-groupname="' + groupname + '"][data-tab="item"]', function() {
		var index = $item.index(this);
		$(this).addClass(activeClass || 'active');
		$item.not($item.eq(index)).removeClass(activeClass || 'active');
		$content.eq(index).show();
		$content.not($content.eq(index)).hide();
	});
}
// input enter
$('html, body').on('keypress', 'input[data-shortcut="enter"]', function(ev) {
	if(ev.keyCode == "13") {
		var index = $('input[data-shortcut="enter"]').index(this);
		var length = $('input[data-shortcut="enter"]').length;
		$('input[data-shortcut="enter"]').eq(index + 1).focus();
	} else if(ev.keyCode == "9") {
		var index = $('input[data-shortcut="enter"]').index(this);
		var length = $('input[data-shortcut="enter"]').length;
		$('input[data-shortcut="enter"]').eq(index).focus();
	}
});
//加载AJAX请求，操作类请求
function load(url , param) {
    layer.load(2, {time: 5*1000});
    $.post(url,param,function (data) {
        if(data.success){
            layer.msg('操作成功,页面即将刷新!',{time:1000},function(){
                location.reload();
            });
        }else{
            layer.msg(data.message,{time:2000},function () {
                location.reload();
            });
        }
    },'json');
}
