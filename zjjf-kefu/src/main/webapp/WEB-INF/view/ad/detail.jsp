<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>广告详情</title>
    <%@ include file="../common/head.jsp"%>
</head>
<body>
<div class="wrap-bd">
    <div>
        位置：<a class="crumb" href="javascript: void(0)">广告管理</a><a href="list.html" class="crumb">广告列表</a><a class="crumb crumb-active">广告详情</a>
    </div>
    <div class="wrap-bd bg border mt-small">
        <form action="">
            <div>
                <label class="label">广告名称：</label>
                广告名称广告名称
                <span class="label"></span>
                浏览量：1434
            </div>
            <p>
                <label class="label">广告描述：</label>
                农夫山泉有点甜
            </p>
            <p>
                <label class="label">有效时间：</label>
                2016-6-15 00:00:00 - 2016-6-18 23:59:59
            </p>
            <p>
                <label class="label">广告类型：</label>
                普通广告/商品广告/品牌广告
            </p>
            <div>
                <label class="label va-t">广告商品：</label>
                <div class="dis-ib" style="width: 800px;">
                    <table class="table-list mt-small">
                        <thead>
                            <tr>
                                <th>商品条码</th>
                                <th>商品名称</th>
                                <th>规格</th>
                                <th>价格</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>1234567891230</td>
                                <td>商品商品商品名称</td>
                                <td>24*300ml</td>
                                <td>110.23</td>
                                <td>删除</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div>
                <label class="label va-t">广告品牌：</label>
                <div class="dis-ib" style="width: 800px;">
                    <table class="table-list mt-small">
                        <thead>
                            <tr>
                                <th>商品编号</th>
                                <th>品牌名称</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>1234567891230</td>
                                <td>农夫山泉</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <p>
                <label class="label">广告位：</label>
                广告位置
            </p>
            <p>
                <label class="label">广告图：</label>
                图片地址
            </p>
            <p>
                <label class="label">点击效果：</label>
                点击无反应/打开功能/打开广告详情页
            </p>
            <p>
                <label class="label">打开功能：</label>
                品牌专区/促销专区/商品分类：碳酸饮料
            </p>
            <p>
                <label class="label">打开地址：</label>
                地址
            </p>
            <div>
                <label class="label va-t">广告内容：</label>
                <div class="dis-ib" style="width: 800px;">
                    <script id="ueditorContent" name="ueditorContent" type="text/plain"></script>
                    <script src="../../resources/vendor/ueditor/ueditor.config.js"></script>
                    <script src="../../resources/vendor/ueditor/ueditor.all.min.js"></script>
                    <script>
                        var ue = UE.getEditor('ueditorContent');
                        ue.addListener('ready', function () {
                            ue.setContent('${ad.content}');
                        });
                    </script>
                </div>
            </div>
            <div class="mt-default">
                <label class="label va-t">参与区域：</label>
                <div class="dis-ib" style="width: 800px;">
                    <div class="dis-ib clearfix">
                        <table class="table-list fl mt-small mr-small" style="width: 395px;">
                            <thead>
                                <tr>
                                    <th>定格名称</th>
                                    <th>定格分区</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>南山科苑片区</td>
                                    <td>南山区</td>
                                    <td>删除</td>
                                </tr>
                            </tbody>
                        </table>
                        <table class="table-list fl mt-small mr-small" style="width: 395px;">
                            <thead>
                                <tr>
                                    <th>定格名称</th>
                                    <th>定格分区</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>南山科苑片区</td>
                                    <td>南山区</td>
                                    <td>删除</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <p>
                <label class="label"></label>
                <input type="button" name="" value="返回" class="button button-cancel">
            </p>
        </form>
    </div>
</div>
</body>
</html>
