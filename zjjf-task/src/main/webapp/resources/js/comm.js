function dateRange(t,e){var n=$(t),i=$(e);n.on("focus",function(){var n=$(t).index(this);WdatePicker({dateFmt:"yyyy-MM-dd HH:mm:ss",isShowClear:!1,isShowWeek:!0,onpicked:function(){$(e).eq(n).focus()}})}),i.on("focus",function(){var n=$(e).index(this);WdatePicker({dateFmt:"yyyy-MM-dd HH:mm:ss",isShowClear:!1,isShowWeek:!0,minDate:$(t).eq(n).val()})})}function dateRangeSimple(t,e){var n=$(t),i=$(e);n.on("focus",function(){var n=$(t).index(this);WdatePicker({dateFmt:"yyyy-MM-dd",isShowWeek:!0,onpicked:function(){$(e).eq(n).focus()}})}),i.on("focus",function(){var n=$(e).index(this);WdatePicker({dateFmt:"yyyy-MM-dd",isShowWeek:!0,minDate:$(t).eq(n).val()})})}function dialogPosCenter(t){var e=$(window).width()>$(t).width()?$(window).width():$(t).width(),n=$(window).height()>$(t).height()?$(window).height():$(t).height(),i=(e-$(t).width())/2,c=(n-$(t).height())/2;$(t).css({left:i,top:c})}function selectAll(t,e){$btn=$(t),$chk=$(e),$btn.on("click",function(){$(this).is(":checked")?$chk.prop("checked","checked"):$chk.prop("checked","")}),$chk.on("click",function(){var t=[];$chk.each(function(){$(this).is(":checked")&&t.push($(this))}),$chk.length==t.length?$btn.prop("checked","checked"):$btn.prop("checked","")})}function tab(t){var e=$('[data-groupname="'+t+'"][data-tab="content"]');e.not(":first").hide(),$("html, body").on("click",'[data-groupname="'+t+'"][data-tab="item"]',function(){var n=$('[data-groupname="'+t+'"][data-tab="item"]').index(this);e.eq(n).show(),e.not(e.eq(n)).hide()})}$("html, body").on("keypress",'input[data-shortcut="enter"]',function(t){if("13"==t.keyCode){var e=$('input[data-shortcut="enter"]').index(this);$('input[data-shortcut="enter"]').length;$('input[data-shortcut="enter"]').eq(e+1).focus()}else if("9"==t.keyCode){var e=$('input[data-shortcut="enter"]').index(this);$('input[data-shortcut="enter"]').length;$('input[data-shortcut="enter"]').eq(e).focus()}});