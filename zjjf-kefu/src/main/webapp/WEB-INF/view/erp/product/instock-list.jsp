<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>商品库管理 - 入库管理列表</title>
    <%@ include file="../../common/head.jsp"%>
    <script src="${root}/resources/js/comm.js"></script>
</head>
<body>
<div class="wrap-bd">
    <div class="mb-small title">入库管理</div>
    <div class="op-section clearfix">
        <div class="fl mr-default">
            <span class="pills <c:if test="${commod.status == 0}">pills-active</c:if>" data-status="0">待处理</span>
            <span class="pills <c:if test="${commod.status == 1}">pills-active</c:if>" data-status="1">已完成</span>
        </div>
        <form class="fl ml-default" action="${root}/scms/ERPOrderInfo.do" id="form">
            <input class="input-search-text" type="text" name="orderId" value="${commod.orderId}" placeholder="采购编号/供应商名称" />
            <input type="hidden" value="${commod.status}" name="status">
            <input value="搜索" type="button" class="input-search-button" id="sub">
        </form>
        <c:if test="${ERP_WAREHOUSE_SESSION_KEY == null}"><a href="${root}/scms/ERPOrderInfo/toAdd.do" class="fr button">新增采购单</a></c:if>
    </div>
    <div class="table-contain">
        <pre>
        <c:if test="${commod.status == 0}">
            <table class="table-list">
                <thead>
                <tr>
                    <th>采购单号</th>
                    <th>类型</th>
                    <th>供应商</th>
                    <th>入库数量</th>
                    <th>已入库</th>
                    <th>制单时间</th>
                    <th>制单人</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${list}" var="ele">
                        <tr>
                            <td>${ele.orderId}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${ele.type == 1}">采购入库</c:when>
                                    <c:otherwise>其他</c:otherwise>
                                </c:choose>
                            </td>
                            <td>${ele.managerName}</td>
                            <td>${ele.total1}</td>
                            <td>${ele.total2}</td>
                            <td><fmt:formatDate value="${ele.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td>${ele.supplierName}</td>
                            <td>
                                <a href="${root}/scms/ERPOrderInfo/detail/1.do?orderId=${ele.orderId}">编辑入库</a>&nbsp;&nbsp;
                                <c:if test="${ERP_WAREHOUSE_SESSION_KEY == null}">
                                    <a href="${root}/scms/ERPOrderInfo/detail/2.do?orderId=${ele.orderId}" target="_blank">打印采购单</a>
                                </c:if>
                            </td>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${fn:length(list) == 0}">
                        <tr><td colspan="8"  class="no-data"></td></tr>
                    </c:if>
                </tbody>
            </table>
        </c:if>
        </pre>
    </div>
    <div class="table-contain">
        <pre>
        <c:if test="${commod.status == 1}">
            <table class="table-list">
                <thead>
                <tr>
                    <th>采购单号</th>
                    <th>类型</th>
                    <th>供应商</th>
                    <th>入库数量</th>
                    <th>制单人</th>
                    <th>制单时间</th>
                    <th>入库人</th>
                    <th>入库时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="ele">
                    <tr>
                        <td>${ele.orderId}</td>
                        <td>
                            <c:choose>
                                <c:when test="${ele.type == 1}">采购入库</c:when>
                                <c:otherwise>其他</c:otherwise>
                            </c:choose>
                        </td>
                        <td>${ele.managerName}</td>
                        <td>${ele.total2}</td>
                        <td>${ele.supplierName}</td>
                        <td><fmt:formatDate value="${ele.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>${ele.actionUserName}</td>
                        <td><fmt:formatDate value="${ele.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>
                            <a href="${root}/scms/ERPOrderInfo/detail/1.do?orderId=${ele.orderId}">详情</a>&nbsp;&nbsp;
                            <c:if test="${ERP_WAREHOUSE_SESSION_KEY == null}">
                            <a href="${root}/scms/ERPOrderInfo/detail/2.do?orderId=${ele.orderId}" target="_blank">打印采购单</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${fn:length(list) == 0}">
                    <tr><td colspan="11"  class="no-data"></td></tr>
                </c:if>
                </tbody>
            </table>
        </c:if>
        </pre>
    </div>
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
