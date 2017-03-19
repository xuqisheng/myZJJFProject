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
$(function() {
    var $nav = $('#J-gNav');
    $nav.on('mouseover', '.J-navLi', function() {
        var index = $nav.find('.J-navLi').index(this);
        $nav.find('.J-subNav').show();
        $nav.find('.J-navLiSub').eq(index).removeClass('hidden').siblings('.J-navLiSub').addClass('hidden');
    }).on('mouseout', '.J-navLi', function() {
        $nav.find('.J-subNav').hide();
    });
    $nav.on('mouseover', '.J-subNav', function() {
        $(this).show();
    }).on('mouseout', '.J-subNav', function() {
        $(this).hide();
    });

    /*返回顶部
    var $gotop = $('#gotop');
    var gotopLeft = ($(window).width() - 1200)/2 + 1206;
    $gotop.css("left", gotopLeft + "px");
    $gotop.on('click', function(){
        $('body,html').animate({scrollTop:0}, 600);
    });
    $(window).on('scroll', function(){
        $(this).scrollTop() > 325 ? $gotop.fadeIn() : $gotop.fadeOut();
    });*/

    $('.goTop').click(function(){
        $("html,body").animate({scrollTop:"0px"},400);
    });

    $('.goTop').hover(function(){
        $(this).css('background','#fff url("../resources/images/goTop-active.png") no-repeat center')
    },function(){
        $(this).css('background','#fff url("../resources/images/goTop.png") no-repeat center')
    });
    
    $('.phone').hover(function(){
        $(this).css('background','#fff url("../resources/images/phone-active.png") no-repeat center');
        $('.fix-left').css({
            'display':'block',
            'background':'#fff url("../resources/images/downloadApp.png") no-repeat center'
        });
    },function(){
        $(this).css('background','#fff url("../resources/images/phone.png") no-repeat center');
        $('.fix-left').css('display','none');
    });

    $('.weixin').hover(function(){
        $(this).css('background','#fff url("../resources/images/weixin-active.png") no-repeat center');
        $('.fix-left').css({
            'display':'block',
            'background':'#fff url("../resources/images/watchUs.png") no-repeat center'
        });
    },function(){
        $(this).css('background','#fff url("../resources/images/weixin.png") no-repeat center');
        $('.fix-left').css('display','none');
    });
});
