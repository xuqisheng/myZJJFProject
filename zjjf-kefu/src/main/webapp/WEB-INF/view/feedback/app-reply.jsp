<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>对APP意见反馈 - 查看回复</title>
    <%@ include file="../common/head.jsp"%>
</head>
<body class="wrap-bd">
    <fieldset class="wrap-bd bg">
        <legend class="txt-info">反馈信息</legend>
        <div>

            <div>
                <label class="label">版本号：</label>
                ${spFeedbackVo.appVersion }
            </div>
            <p>
                <label class="label">反馈账号：</label>
               ${spFeedbackVo.storeMobile }
            </p>
            <p>
                <label class="label">联系方式：</label>
                ${spFeedbackVo.storeMobile }
            </p>
            <div>
                <label class="label va-t">反馈内容：</label>
                <div style="width: 800px;display: inline-block;">
                    <div>
                     ${spFeedbackVo.content }
                    </div>
                    <div>
                        
                       	<c:choose>
                       		<c:when test="${spFeedbackVo.pic1 != null && spFeedbackVo.pic1 != '' }">
                        		<a href="${USER_FASTFDSPREURL }${spFeedbackVo.pic1 }" target="_blank">
                        			<img src="${USER_FASTFDSPREURL }${spFeedbackVo.pic1 }" width="90" height="90" alt="缩略图" onerror="javascript:this.src='${USER_DEFAULTIMG_URL}'">
                        		</a>
                       		</c:when>
                       		<c:otherwise>
                        		<a href="${USER_DEFAULTIMG_URL}" target="_blank">
                        			<img src="${USER_DEFAULTIMG_URL}" width="90" height="90" alt="缩略图" >
                        		</a>
                       		</c:otherwise>
                       	</c:choose>
                   		<c:if test="${spFeedbackVo.pic2 != null && spFeedbackVo.pic2 != '' }">
	                  		<a href="${USER_FASTFDSPREURL }${spFeedbackVo.pic2 }" target="_blank">
	                  			<img src="${USER_FASTFDSPREURL }${spFeedbackVo.pic2 }" width="90" height="90" alt="缩略图" onerror="javascript:this.src='${USER_DEFAULTIMG_URL}'"> 
	                  		</a>
                   		</c:if>
                        <c:if test="${spFeedbackVo.pic3 != null && spFeedbackVo.pic3 != '' }">
	                        <a href="${USER_FASTFDSPREURL }${spFeedbackVo.pic3 }" target="_blank">
	                       		<img src="${USER_FASTFDSPREURL }${spFeedbackVo.pic3 }" width="90" height="90" alt="缩略图" onerror="javascript:this.src='${USER_DEFAULTIMG_URL}'">
	                        </a>
                        </c:if>
                    </div>
                </div>
            </div>
            <p>
                <label class="label">反馈时间：</label>
                <fmt:formatDate value="${spFeedbackVo.addTime }" pattern="yyyy-MM-dd HH:mm:ss"/> 
            </p>
        </div>
    </fieldset>
    <fieldset class="wrap-bd bg mt-default">
        <legend class="txt-info">反馈处理</legend>
        <div>
            <div>
            <input type="hidden" value="${spFeedbackVo.id }" id="id">
                <label class="label">反馈处理：</label>
                <c:if test="${spFeedbackVo.revertStatus==0 }">
                	<input type="radio" name="revertStatus" value="1" class="radio"> 已处理
                	<input type="radio" name="revertStatus" value="0" checked="checked" class="radio ml-default"> 未处理
                </c:if>
                <c:if test="${spFeedbackVo.revertStatus==1 }">
                	<input type="radio" name="revertStatus" value="1" checked="checked" class="radio"> 已处理
                	<input type="radio" name="revertStatus" value="0" class="radio ml-default"> 未处理
                </c:if>
            </div>
            <p>
                <label class="label">处理信息：</label>
                <textarea class="textarea" id="textarea" cols="56" rows="6"></textarea>
            </p>
            <p>
                <label class="label"></label>
                <input type="button" value="确认" onclick="edit()" class="button button-ok">
                <input type="button" value="取消" onclick="exit()" class="button button-cancel">
            </p>
        </div>
    </fieldset>
</body>
<script type="text/javascript">
$("#textarea").val('${spFeedbackVo.kfContent}');
function edit(){
	var revertStatus = $("input[name='revertStatus']:checked").val();
	var kfContent = $("#textarea").val();
	var id = $("#id").val();
	$.post("${root}/CornerV2/SpFeedblack/saveCustomerServiceHuiFu.do",{revertStatus:revertStatus,kfContent:kfContent,id:id},function(data){
		if(data.success){
			alert(data.message);
			location.href = "${root}/CornerV2/SpFeedblack/getAllSpFeedbackByParam.do";
		}else{
			alert(data.message);
		}
	},'json');
}
function exit(){
	location.href = "${root}/CornerV2/SpFeedblack/getAllSpFeedbackByParam.do";
}

	
</script>
</html>
