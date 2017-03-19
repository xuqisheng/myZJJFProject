<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>消息列表</title>
<%@ include file="../common/head.jsp"%>
</head>
<body class="wrap-bd">
<div class="mb-small clearfix">
    <form class="fl" action="${root}/customer/messageManage/toIndex.do" method="post">
        <input class="input input-search-text" type="text" name="msgTitle" value="${keyStr}" placeholder="请输入消息关键词" />
        <input class="input input-search-button" value="搜索" type="submit" />
    </form>
    <div class="fr"><a href="${root}/customer/messageManage/toEdit.do"><button class="button button-default" id="newSpGroup">添加消息</button></a></div>
</div>
<table class="table-list table-border">
    <thead class="table-thead">
    <tr>
        <th width="200">消息标题</th>
        <th width="400">消息内容</th>
        <th>编辑时间</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody class="table-tbody">
     <c:forEach var="spPushMsg" items="${list}">
        <tr>
            <td class="J_orderid">${spPushMsg.msgTitle}</td>
            <td class="ta-l">${spPushMsg.content}</td>
            <td>
                <fmt:formatDate value="${spPushMsg.publishTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
	            <c:if test="${spPushMsg.status==0}">
	               <span class="txt-warn">未发送</span>
	            </c:if>
	            <c:if test="${spPushMsg.status==1}">
                   <span class="txt-success">已发送</span>
	            </c:if>
            </td>
            <td>
                <input type="hidden" value="${spPushMsg.id}">
                <c:if test="${spPushMsg.status==1}">
                   <input type="button" value="查看" class="button button-operate J_edit">
                </c:if>
                <c:if test="${spPushMsg.status==0}">
                   <input type="button" value="编辑" class="button button-operate J_edit">
                </c:if>
            </td>
        </tr>
     </c:forEach>
    </tbody>
</table>
<c:if test="${fn:length(list)>0}">
		<%@ include file="../common/pagination-kk.jsp"%>
</c:if>
<script>
    $(function() {
        // 编辑
        $('.J_edit').on('click', function(){
            location.href = "${root}/customer/messageManage/toEdit.do?id="+$(this).prev().val()+"&pageIndex=${pageIndex}";
        });
    });
</script>
</body>
</html>