<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="./common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>快销宝 - 转角街坊</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <%@ include file="./common/head.jsp"%>
    <link rel="stylesheet" href="${root}/resources/css/layout.css?v20160707">
	<script src="${root}/resources/js/layout.js?v20160707"></script>
	<script src="${root}/resources/js/comm.js?v20160707"></script>
</head>
<body>
<%@ include file="./common/nav.jsp"%>
<main id="main">
	<%@ include file="./common/header.jsp"%>
    <article id="article">
        <c:choose>
            <c:when test="${ERP_WAREHOUSE_SESSION_KEY != null}"><!--ERP仓库菜单权限-->
                <iframe id="mainiframe" name="mainiframe" src="${root}/scms/ERPOrderInfo/outstockList.do" width="100%" height="100%" frameborder="0"></iframe>
            </c:when>
            <c:otherwise>
                <c:forEach items="${ScmsAuthVoList }" var="ScmsAuthVo" varStatus="status">
                    <c:if test="${status.index==0 }">
                        <c:if test="${ScmsAuthVo.type == 1 }">
                            <c:forEach items="${ScmsAuthVo.auths }" var="auth"  varStatus="authStatus">
                                <c:if test="${status.index==0 }">
                                    <iframe id="mainiframe" name="mainiframe" src="${root}${auth.action}" width="100%" height="100%" frameborder="0"></iframe>
                                </c:if>
                            </c:forEach>
                        </c:if>
                        <c:if test="${ScmsAuthVo.type == 2 }">
                            <iframe id="mainiframe" name="mainiframe" src="${root}${ScmsAuthVo.action}" width="100%" height="100%" frameborder="0"></iframe>
                        </c:if>
                    </c:if>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </article>
</main>
<div class="dialog hidden" id="J_protocol">
    <div class="dialog-head ta-c" id="title">
        ${plantProtocol.title}
    </div>
    <div class="dialog-body" id="body">
        <div style="margin-top:20px;padding:8px 16px;width:600px;height:420px;overflow:auto;border:1px solid #ccc;" id="content">
            ${plantProtocol.content}
        </div>
    </div>
    <div class="dialog-foot" id="foot">
        <div class="ta-l">
            <input type="checkbox" id="J_checkbox" checked="checked"> 我同意并遵守《${plantProtocol.title}》<br>
            <span class="hidden" style="color: red;" id="J_tips">勾选同意此协议后，可正常使用系统</span>
        </div>
        <p>
            <input type="submit" value="确定" class="dialog-button dialog-ok" id="J_submit">
        </p>
    </div>
</div>
<div class="cover-all" id="shadow"></div>
<script>
    $(function() {

    	if("true"=="${forget}"){//表示从忘记密码处跳过来
    		var $parentDiv = $('.icon-account').parent('div');
    		$parentDiv.attr('data-direction','up');
    		var $nextDiv = $parentDiv.next('div');
    		$nextDiv.attr('style','display: block;');
    		$nextDiv.children('a').eq(1).addClass('active');
    		/* $('#mainiframe').attr('src','${root}/scms/password/toEditPassWord.do?str=supply'); */
    		$('#mainiframe').attr('src','${root}/scms/password/toUpdatePayPassword.do');
    	}

    	dialogPosCenter('#J_protocol');
        $(window).on('resize', function() {
        	dialogPosCenter('#J_protocol');
        });
        $('#J_checkbox').on('click', function() {
            if($('#J_checkbox').is(':checked')) {
                $('#J_submit').removeClass('dialog-disable').addClass('dialog-ok').prop('disabled','');
                $('#J_tips').hide();
            } else {
                $('#J_submit').removeClass('dialog-ok').addClass('dialog-disable').prop('disabled','disabled');
                $('#J_tips').show();
            };
        });
        if("${noSign}"=="true") {
        	$('#J_protocol').removeClass('hidden');
        	$('#shadow').show();
        } else {
        	$('#J_protocol').addClass('hidden');
        	$('#shadow').hide();
        }
        $('#J_submit').on('click',function(){
        	var pr = "${plantProtocol.id}";
        	if($('#J_checkbox').is(':checked')) {
        		$.ajax({
					type: "POST",
					url: "${root}/scms/sign/signResult.do",
					dataType: "json",
					data: {pr:"${plantProtocol.id}"},
					success: function(data) {
						if (data.success) {
							$('#J_protocol').addClass('hidden');
				        	$('#shadow').removeClass('show');
				        	$('#shadow').hide();
						} else {
							alert(data.message);
						}
					},
					error: function(data) {
					}
				});
        	}
        });
    })
</script>
</body>
</html>
