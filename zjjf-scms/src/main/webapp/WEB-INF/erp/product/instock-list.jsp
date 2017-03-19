<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>商品库管理 - 采购订单列表</title>
    <%@ include file="../../common/head.jsp"%>
    <script src="${root}/resources/js/comm.js"></script>
</head>
<body>
<div class="wrap-bd">
    <div class="mb-small title">采购订单</div>
    <div class="op-section clearfix">
        <div class="fl mr-default">
            <span class="pills <c:if test="${commod.status == 0}">pills-active</c:if>" data-status="0">未入库</span>
            <span class="pills <c:if test="${commod.status == 1}">pills-active</c:if>" data-status="1">已入库</span>
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
            <table class="table-list">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>采购单号</th>
                    <th>采购仓库</th>
                    <th>供应商名称</th>
                    <th>采购金额</th>
                    <th>状态</th>
                    <th>审核状态</th>
                    <th>审核人</th>
                    <th>审核时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${list}" var="ele" varStatus="eleStat">
                        <tr>
                            <td>${eleStat.count}</td>
                            <td>${ele.orderId}</td>
                            <td>${ele.whName}</td>
                            <td>${ele.managerName}</td>
                            <td>${ele.itemPrice}</td>
                            <td>
                                <c:if test="${ele.status == 0}">未入库</c:if>
                                <c:if test="${ele.status == 1}">已入库</c:if>
                            </td>
                            <td>${ele.checkStatusStr}</td>
                            <td>${ele.checkUserName}</td>
                            <td><fmt:formatDate value="${ele.checkTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td>
                                <a class="button button-operate" href="${root}/scms/ERPOrderInfo/detail/1.do?orderId=${ele.orderId}">编辑入库</a>&nbsp;&nbsp;
                                <c:if test="${ERP_WAREHOUSE_SESSION_KEY == null}">
                                    <a class="button button-operate" href="${root}/scms/ERPOrderInfo/detail/2.do?orderId=${ele.orderId}" target="_blank">打印采购单</a>
                                </c:if>
                            </td>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${fn:length(list) == 0}">
                        <tr><td colspan="10"  class="no-data"></td></tr>
                    </c:if>
                </tbody>
            </table>
        </pre>
    </div>
    <c:if test="${fn:length(list)>0}">
        <%@ include file="../../common/pagination-kk.jsp"%>
    </c:if>
</div>
<script>
    $(function() {
    	$('.table-list').on('click', 'td', function() {
            $(this).parent().css({
                'background': '#009dd9',
                'color': 'white'
            }).siblings().css({
                'background': '#fff',
                'color': 'black'
            });
        });
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
