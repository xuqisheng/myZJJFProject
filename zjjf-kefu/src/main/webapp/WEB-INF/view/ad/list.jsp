<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>广告管理</title>
    <%@ include file="../common/head.jsp"%>
    <script src="../../resources/vendor/My97DatePicker/WdatePicker.js"></script>
    <script src="../../resources/js/comm.js"></script>
</head>
<body>
<div class="wrap-bd">
    <div>
        <a class="crumb" href="javascript: void(0)">广告管理</a><a class="crumb crumb-active">广告列表</a>
    </div>
    <div class="mt-default mb-small clearfix">
        <div class="fl">
            <label for="J_dataS">有效时间：</label>
            <input type="text" name="" id="J_dataS" class="input input-date"> -
            <input type="text" name="" id="J_dataE" class="input input-date">
            <label class="ml-default">广告位：</label>
            <select class="select">
                <option selected="selected">全部</option>
            </select>
            <input type="text" name="adName" id="adName" placeholder="广告名称" class="input input-search-text ml-default">
            <input type="button" value="搜索" class="input input-search-button ml-default">
        </div>
        <a href="edit.html" id="J_addAd" class="fr button button-default">新增广告</a>
    </div>
    <table class="table-list">
        <thead>
            <tr>
                <th>序号</th>
                <th>活动名称</th>
                <th>排序</th>
                <th>广告位</th>
                <th>浏览量</th>
                <th>有效时间</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>1</td>
                <td>转角520活动</td>
                <td>1</td>
                <td>APP启动页</td>
                <td>1325</td>
                <td>2015/11/10 12:00:00 到<br>2015/11/11 18:00:00</td>
                <td>已发布</td>
                <td>
                    <a href="detail.html">查看</a>
                    <a href="edit.html">编辑</a>
                    <a href="javascript: void(0)" class="J_delete">删除</a>
                </td>
            </tr>
        </tbody>
    </table>
    <script>
        $(function () {
            dateRange('#J_dataS', '#J_dataE', 1);
            $('.J_delete').on('click', function() {
                alert("确定删除？");
            });
        })
    </script>
</div>
</body>
</html>
