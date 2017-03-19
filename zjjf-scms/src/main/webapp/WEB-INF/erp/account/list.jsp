<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>账户中心 - 账户列表</title>
    <%@ include file="../../common/head.jsp"%>
    <script src="${root}/resources/js/comm.js"></script>
</head>
<body>
<div class="wrap-bd">
    <div class="op-section clearfix">
        <a href="${root}/erp/warehouse/toEdit.do" class="fr button">新增用户</a>
    </div>
    <table class="table-list">
        <thead>
            <tr>
                <th>序号</th>
                <th>用户名</th>
                <th>姓名</th>
                <th>仓库</th>
                <th>添加时间</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="ele" varStatus="eleStat">
            <tr>
                <td>${eleStat.count}</td>
                <td>${ele.userName}</td>
                <td>${ele.name}</td>
                <td>${ele.whName}</td>
                <td><fmt:formatDate value="${ele.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>
                    <a href="${root}/erp/warehouse/toEdit.do?id=${ele.id}">编辑</a>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${fn:length(list) == 0}">
            <tr><td colspan="6"  class="no-data"></td></tr>
        </c:if>
        </tbody>
    </table>
    <c:if test="${fn:length(list)>0}">
        <%@ include file="../../common/pagination-kk.jsp"%>
    </c:if>
</div>
<script>
    $(function() {
        $('#sub').on('click', function(e) {
            e.preventDefault();
            $('#form').submit();
        });
        $('.pills').on('click' , function () {
            $('input[name="status"]').val($(this).attr('data-status'));
            $('#sub').trigger('click');
        })
    });
</script>
</body>
</html>
