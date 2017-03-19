;(function($) {
    var tab = function(groupname, activeClass) {
        var $item = $('[data-groupname="' + groupname + '"][data-tab="item"]');
        var $content = $('[data-groupname="' + groupname + '"][data-tab="content"]');
        var $itemActive = $item.filter('.' + (activeClass || 'active'));
        if($itemActive.length == 1) {
            var index = $item.index($itemActive);
            $content.not($content.eq(index)).hide();
        } else {
            $content.not(':first').hide();
        }
        $('html, body').on('click', '[data-groupname="' + groupname + '"][data-tab="item"]', function() {
            var index = $item.index(this);
            $(this).addClass(activeClass || 'active');
            $item.not($item.eq(index)).removeClass(activeClass || 'active');
            $content.eq(index).show();
            $content.not($content.eq(index)).hide();
        });
    }
    var dialogPosCenter = function(dialog) {
        var $winwidth = ($(window).width() > $(dialog).width()) ? $(window).width() : $(dialog).width();
        var $winheight = ($(window).height() > $(dialog).height()) ? $(window).height() : $(dialog).height();
        var dleft = ($winwidth - $(dialog).width())/2;
        var dtop = ($winheight - $(dialog).height())/2;
        $(dialog).css({
            'left': dleft,
            'top': dtop
        });
    }
    $.fn.cascader = function(option) {
        var $root = $(this);
        var data = {};
        var tpl = '<div class="cascader J_cascader">'
                    +    '<div class="cascader-selected">'
                    +     '<span class="text">选择区域</span><i class="select-close J_close"></i>'
                    +   '</div>'
                    +   '<div class="cascader-picker J_picker">'
                    +     '<div  class="cascader-picker-tit"> '
                    +       '<span class="select-box active J_provinceItem" data-groupname="' + option.name + '" data-tab="item">'
                    +         '<span class="text">省</span><i class="select-arrow"></i>'
                    +       '</span> '
                    +       '<span class="select-box J_cityItem" data-groupname="' + option.name + '" data-tab="item">'
                    +         '<span class="text">市</span><i class="select-arrow"></i>'
                    +       '</span> '
                    +       '<span class="select-box J_areaItem" data-groupname="' + option.name + '" data-tab="item">'
                    +         '<span class="text">区</span><i class="select-arrow"></i>'
                    +       '</span> '
                    +     '</div> '
                    +     '<div class="cascader-picker-cont J_province" data-groupname="' + option.name + '" data-tab="content"></div>'
                    +     '<div class="cascader-picker-cont J_city" data-groupname="' + option.name + '" data-tab="content"></div>'
                    +     '<div class="cascader-picker-cont J_area" data-groupname="' + option.name + '" data-tab="content"></div>'
                    +   '</div>'
                    +'</div>'
                    +'<div class="cascader-cover J_cascaderCover"></div>';
        $root.html(tpl);
        tab(option.name);

        var flag = true;
        $.ajax({
            url: option.url,
            type: 'GET',
            dataType: 'json',
            cache: false
        }).done(function(respdata) {
            data = respdata;
            var phtml = '';
            phtml += '<ul class="ul">'
            $.each(data, function(index, element) {
                phtml += '<li class="li" data-value="' + element.id + '">' + element.name + '</li>';
            });
            phtml += '</ul>';
            $root.find('.J_province').html(phtml);
        })

        if(flag) {
            flag = false;
            $(this).addClass('active');
            $('.J_cascaderCover').show();
            $root.find('.J_cascader').show();
        } else {
            flag = true;
            $(this).removeClass('active');
            $('.J_cascaderCover').hide();
            $root.find('.J_cascader').hide();
        };

        dialogPosCenter('.J_cascader');
        $root.on('click', '.J_close', function() {
            $('.J_cascaderCover').hide();
            $root.find('.J_cascader').hide();
        });

        var province = '';
        var city= '';
        var area = '';
        var provinceTxt = '';
        var cityTxt = '';
        var areaTxt = '';
        var txt = '';
        $root.find('.J_province').on('click', '.li', function() {
            province = $(this).attr('data-value');
            provinceTxt = $(this).text();
            $root.find('.J_provinceItem .text').text($(this).text());

            var cityhtml = '';
            cityhtml += '<ul class="ul">'
            $.each(data, function(index, element) {
                if(data[index].id == province) {
                    $.each(element.sub, function(index, element) {
                        cityhtml += '<li class="li" data-value="' + element.id + '">' + element.name + '</li>';
                    });
                }
            });
            cityhtml += '</ul>';
            $root.find('.J_city').html(cityhtml);
            $root.find('.J_cityItem').trigger('click');

            $root.find('.J_cityItem .text').text('市');
            $root.find('.J_areaItem .text').text('区');
            $root.find('.J_area').empty();
            city = '';
            area = '';
            cityTxt = '';
            areaTxt = '';
            txt = provinceTxt;
        });

        $root.find('.J_city').on('click', '.li', function() {
            city = $(this).attr('data-value');
            cityTxt = $(this).text();
            $root.find('.J_cityItem .text').text($(this).text());

            var areahtml = '';
            areahtml += '<ul class="ul">'
            $.each(data, function(index, element) {
                $.each(element.sub, function(index, element) {
                    if(element.id == city) {
                        $.each(element.sub, function(index, element) {
                            areahtml += '<li class="li" data-value="' + element.id + '">' + element.name + '</li>';
                        });
                    }
                });
            });
            areahtml += '</ul>';
            $root.find('.J_area').html(areahtml);
            $root.find('.J_areaItem').trigger('click');

            $root.find('.J_areaItem .text').text('区');
            $root.find('.J_town').empty();
            area = '';
            areaTxt = '';
            txt = provinceTxt + '/' +cityTxt;
            v = province + ',' +city;
        });

        $root.find('.J_area').on('click', '.li', function() {
            area = $(this).attr('data-value');
            areaTxt = $(this).text();
            $root.find('.J_areaItem .text').text($(this).text());
            txt = provinceTxt + '/' +cityTxt + '/' +areaTxt;
            v = province + ',' +city + ',' +area;
            option.complete(txt, v);
            flag = true;
            $('.J_cascaderCover').hide();
            $root.find('.J_cascader').hide();
        });
    }
})(jQuery);
