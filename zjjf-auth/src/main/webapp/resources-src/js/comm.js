// 日期范围  my97datepicker
function dateRange(start, end) {
    var $time_start = $(start);
    var $time_end = $(end);
    $time_start.on('focus', function() {
        var idx = $(start).index(this);
        WdatePicker({
            dateFmt: "yyyy-MM-dd HH:mm:ss",
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
            dateFmt: "yyyy-MM-dd HH:mm:ss",
            isShowClear: false,
            isShowWeek: true,
            minDate: $(start).eq(idx).val()
        });
    });
}
function dateRangeSimple(start, end) {
    var $time_start = $(start);
    var $time_end = $(end);
    $time_start.on('focus', function() {
        var idx = $(start).index(this);
        WdatePicker({
            dateFmt: "yyyy-MM-dd",
            isShowWeek: true,
            onpicked: function() {
                $(end).eq(idx).focus();
            }
        });
    });
    $time_end.on('focus', function() {
        var idx = $(end).index(this);
        WdatePicker({
            dateFmt: "yyyy-MM-dd",
            isShowWeek: true,
            minDate: $(start).eq(idx).val()
        });
    });
}
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
    $btn = $(btn);
    $chk = $(chk);
    $btn.on('click', function() {
        if($(this).is(':checked')) {
            $chk.prop('checked', 'checked');
        } else {
            $chk.prop('checked', '');
        }
    });
    $chk.on('click', function() {
        var chkArray = [];
        $chk.each(function() {
            if($(this).is(':checked')) {
                chkArray.push($(this));
            }
        });
        if($chk.length == chkArray.length) {
            $btn.prop('checked', 'checked');
        } else {
            $btn.prop('checked', '');
        }
    });
}
// tab
function tab(groupname) {
    var $content = $('[data-groupname="' + groupname + '"][data-tab="content"]');
    $content.not(':first').hide();
    $('html, body').on('click', '[data-groupname="' + groupname + '"][data-tab="item"]', function() {
        var index = $('[data-groupname="' + groupname + '"][data-tab="item"]').index(this);
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
