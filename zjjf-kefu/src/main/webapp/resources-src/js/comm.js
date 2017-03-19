// 日期范围  my97datepicker
function dateRange(start, end, fmt) {
    fmt || 0;  //0 yyyy-MM-dd HH:mm:ss  1 yyyy-MM-dd
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
function dateRangeHms(start, end, fmt) {
    fmt || 0;  //0 yyyy-MM-dd HH:mm:ss  1 yyyy-MM-dd
    var $time_start = $(start);
    var $time_end = $(end);
    $time_start.on('focus', function() {
        var idx = $(start).index(this);
        WdatePicker({
            dateFmt: fmt ? "yyyy-MM-dd" : "HH:mm:ss",
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
            dateFmt: fmt ? "yyyy-MM-dd" : "HH:mm:ss",
            isShowClear: false,
            isShowWeek: true,
            minDate: $(start).eq(idx).val()
        });
    });
};
// dialog位置居中
function dialogPosCenter(dialog) {
    var $winwidth = ($(window).width() > $(dialog).width()) ? $(window).width() : $(dialog).width();
    var $winheight = ($(window).height() > $(dialog).height()) ? $(window).height() : $(dialog).height();
    var dleft = ($winwidth - $(dialog).width())/2;
    var dtop = ($winheight - $(dialog).height())/2;
    $(dialog).css({
        'left': dleft,
        'top': dtop
    });
}
// 全选，全不选
function selectAll(btn, chk) {
    $(btn).on('click', function() {
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
    }else if(ev.keyCode == "9"){
        var index = $('input[data-shortcut="enter"]').index(this);
        var length = $('input[data-shortcut="enter"]').length;
        $('input[data-shortcut="enter"]').eq(index).focus();
    }
});
