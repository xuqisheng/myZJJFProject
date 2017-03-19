function layout() {
    var $headerHeight = $('#header').outerHeight();
    var $navHeight = $(window).outerHeight();
    $('#nav').css({"height": $navHeight + 'px'});
    $('#mainiframe').css({"height": $navHeight - $headerHeight - 5 + 'px'});
}
$(function() {
    layout();
    $(window).on('resize', function() {
        layout();
    });
    $('#nav').on('click', '.category', function() {
        $(this).addClass('active').parents('li').siblings().children('.category').removeClass('active');
        $('.subcategory a').removeClass('active');
        if($(this).attr('data-direction') != undefined) {
            if($(this).attr('data-direction') == "down") {
                $(this).siblings('.subcategory').slideDown();
                $(this).attr("data-direction", "up");
            } else {
                $(this).siblings('.subcategory').slideUp();
                $(this).attr("data-direction", "down");
            }
        }
        $(this).parents('li').siblings().children('.subcategory').slideUp();
        var $othercategory = $(this).parents('li').siblings().children('.category');
        $othercategory.each(function(i, n) {
            if($(n).attr('data-direction') != undefined) {
                $(n).attr('data-direction', 'down');
            }
        });
    });
    $('#nav .subcategory').on('click', 'a', function() {
        $('#nav .category').removeClass('active');
        $(this).addClass('active').siblings('a').removeClass('active').parents('.subcategory').parents('li').siblings('li').find('.subcategory a').removeClass('active');
    });
});
