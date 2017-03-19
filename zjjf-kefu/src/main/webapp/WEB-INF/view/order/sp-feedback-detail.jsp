<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>反馈详情页</title>
<%@ include file="../common/head.jsp"%>
<script src="${root}/resources/js/comm.js"></script>
</head>
<body class="wrap-bd">
<div>
	<c:forEach items="${spFeedbackVoList }" var="spFeedback">
	<div class="table-border mb-small">
		<div class="wrap-bd clearfix">
			<c:if test="${spFeedback.sendUser==0 }">
				<div style="color:#ccc;font-size:12px">
					${spFeedback.storeName }
				</div>
			</c:if>
			<c:if test="${spFeedback.sendUser==1 }">
				<div style="color:#111;font-size:12px;">
					客服回复
				</div>
			</c:if>
			<p>${spFeedback.content }</p>
			<div class="fr" style="color:#ccc">
				<c:if test="${spFeedback.sendUser==1 }">
					${spFeedback.checkerNm }
				</c:if>
				&nbsp;&nbsp;
				<span>
					<fmt:formatDate value="${spFeedback.addTime }" pattern="yyyy-MM-dd HH:mm:ss"/> 
				</span>
			</div>
		</div>
	</div>
	</c:forEach>
</div>
<c:if test="${fn:length(spFeedbackVoList)>0}">
	<%@ include file="../common/pagination-kk.jsp"%>
</c:if>
<div>
	回复内容：
	<textarea class="textarea" id="content" style="width:100%" maxlength="1000"></textarea>
	<p>
		<input type="button" class="button button-ok J_ok" value="回复">
	</p>
</div>
<script>
$(function(){
	$(".J_ok").on('click', function() {
		var storeId = "${spFeedbackRo.storeId}";
		var content = $.trim($("#content").val());
		if(content == '') {
			alert('回复内容不能是空！');
		} else {
			$.post("${root}/CornerV2/SpFeedblack/saveCustomerServiceHuiFu.do", {storeId:storeId,content:content},function(data){
				if(data.success) {
					alert(data.message);
					location.href = "${root}/CornerV2/SpFeedblack/getAllFeedbackByStoreId.do?pageIndex=${page.pageIndex}&storeId=${spFeedbackRo.storeId}";
				}
			},"json");
		}
	});
})
</script>
</body>
</html>