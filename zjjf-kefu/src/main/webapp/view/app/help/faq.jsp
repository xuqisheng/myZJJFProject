<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<title>帮助中心 - 帮助中心</title>
<script src="${root}/resources/vendor/jquery/jquery-3.1.0.min.js"></script>
<style>
	* {
		-webkit-tap-highlight-color: rgba(0,0,0,0) /* 默认点击蓝底 */
	}
    body {
        margin: 0;
        background: #e6e6e6
    }
    .help-faq {
        margin-top: 12px;
        min-width: 320px;
        height: auto;
        background: #fff;
        word-wrap: break-word
    }
    .help-faq-item {
        border-bottom: 1px solid #ededed
    }
    .help-faq-item:last-child {
        border-bottom: 0 none
    }
    .help-faq-title {
        position: relative;
        padding: 10px 16px;
        line-height: 22px;
        font-size: 16px;
        color: #333;
        overflow: hidden;
        cursor: pointer
    }
    .help-faq-title .i {
        margin-right: 8px;
        width: 10px;
        height: 10px;
        display: inline-block;
        background: #ed4b1c;
        border-radius: 50%
    }
    .help-faq-title .dir {
        float: right;
        right: 16px;
        top: 0;
        width: 19px;
        height: 19px;
        background: url(../../resources/images/help_arr_x.png) center no-repeat
    }
    .help-faq-content {
        padding: 0px 34px 8px;
        display: none;
        line-height: 20px;
        font-size: 14px;
        color: #aaa;
    }
    .help-faq-item-active .help-faq-content {
        display: block;
    }
    .help-faq-item-active .help-faq-title .dir {
        background: url(../../resources/images/help_arr_s.png) center no-repeat;
    }
</style>
</head>
<body>
<div class="help-faq"></div>
<script>
	$(function() {
		$.ajax({
			url: '${root}/Pc/SystemInfo/appGetAllHelp.do',
		    type: 'get',
		    success: function(data) {
		    	if(data.success){
					var html="";
					$.each(data.message,function(i,item){
						html += "<div class='help-faq-item'>"
					        	+ "<div class='help-faq-title'>"
					        	+ "<span class='i'></span>"+item.title+"<span class='dir'></span>"
					        	+ "</div>"
					        	+ "<div class='help-faq-content'>"+item.solution+"</div>"
					    	    + "</div>";
					});
					$('.help-faq').append(html);
					$('.help-faq').on('click', '.help-faq-title', function() {
						$(this).parent('.help-faq-item').toggleClass('help-faq-item-active');
					});
				}
			}
		});
    });
</script>
</body>
</html>