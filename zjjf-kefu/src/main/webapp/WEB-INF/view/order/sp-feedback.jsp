<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title></title>
<%@ include file="../common/head.jsp"%>
<script src="${root}/resources/js/comm.js"></script>
</head>
<body>
	<div class="wrap-bd">
		<div class="mb-small">
			<form action="${root}/CornerV2/SpFeedblack/getAllSpFeedbackByParam.do" method="post">
				<input type="text" name="text" id="text" class="input input-search-text" value="${spFeedbackRo.text }" placeholder="店铺名称/手机号/反馈内容">
				<input type="submit" value="搜索" class="input input-search-button">
			</form>
		</div>
		<table class="table-list table-border">
		  <thead class="table-thead">
			<tr>
				<td>序号</td>
				<td width="300px">内容</td>
				<td>店铺名称</td>
				<td>手机号</td>
				<td>反馈时间</td>
				<td>最后回复时间</td>
				<td>状态</td>
				<td>操作</td>
			</tr>
		  </thead>
		  <tbody class="table-tbody">
			 <c:forEach var="spFeedback" items="${SpFeedbackList }" varStatus="status">
				<tr>
					<td>
						${status.index+1 }
					</td>
					<td>${spFeedback.content }</td>
					<td>
						${spFeedback.storeName }
						<input type="hidden" name="id" value="${spFeedback.id }">
					</td>
					<td>${spFeedback.storeMobile }</td>
					<td><fmt:formatDate value="${spFeedback.addTime }" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
					<td><fmt:formatDate value="${spFeedback.lastTime }" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
					<c:if test="${spFeedback.status==0 }">
						<td><font color="red">不可用</font></td>
					</c:if>
					<c:if test="${spFeedback.status==1 }">
						<td><font color="grad">可用</font></td>
					</c:if>
					<td>
						<a href="${root}/CornerV2/SpFeedblack/getAllFeedbackByStoreId.do?storeId=${spFeedback.storeId}">详情</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
		</table>
	</div>
<c:if test="${fn:length(SpFeedbackList)>0}">
	<%@ include file="../common/pagination-kk.jsp"%>
</c:if>
</body>
</html>