$(function(){
	// footer二维码
	$('#footer .part2').on('click', '.code', function(){
		$(this).addClass('curr').siblings('dd').removeClass('curr');
		var codesrc = $(this).attr('data-img');
		$('#footer .part2 img').attr('src',codesrc);
	})
	//返回顶部
	var $gotop = $('#gotop');
	var gotopLeft = ($(window).width() - 1000)/2 + 1006;
	$gotop.css("left", gotopLeft + "px");
	$gotop.on('click', function(){
		$('body,html').animate({scrollTop:0}, 600);
	});
	$(window).on('scroll', function(){
		$(this).scrollTop() > 325 ? $gotop.fadeIn() : $gotop.fadeOut();
	});
});