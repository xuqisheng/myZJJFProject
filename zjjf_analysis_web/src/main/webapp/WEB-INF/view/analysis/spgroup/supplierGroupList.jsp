<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>终端日报</title>
    <%@ include file="../../common/head.jsp"%>
    <%@ include file="../../common/datepicker.jsp"%>
    <%@ include file="../../common/pagination.jsp"%>
    <link rel="stylesheet" href="${root}/resources/css/daily.css">
</head>
<body class="wrap-bd">
    <div class="mb-default">
        <a href="" class="crumb">日报</a>
        <a href="" class="crumb crumb-active">终端日报</a>
    </div>
    <div class="op-section">

        <select class="select">
                <option value="">全部城市</option>
        </select>
        <select class="select">
            <option value="">全部区域</option>
        </select>
        <select class="select">
            <option value="">全部定格</option>
        </select>
        &nbsp;&nbsp;&nbsp;&nbsp;
        查询时间: &nbsp;&nbsp;<input type="text" class="input input-date">
    </div>
    <div class="op-section clearfix">
        <div class="fr">
            <input type="button" value="查询" class="input input-search-button">
            <input type="button" value="重置" class="input input-search-button-white">
            <input type="button" value="导出" class="input input-search-button-white">
        </div>
    </div>
    <div class="total-c clearfix">
        <div class="total">
            <div class="total-top">
                <div class="SummaryDataShow-BlueTitle ta-l dailyTitleF">新增注册终端汇总</div>
                <div class="h2">17</div>
                <div>今日新增</div>
            </div>
            <div class="div">
                <div class="div-item">
                    <b>17</b>
                    <br>昨日新增
                </div>
                <div class="div-item">
                    <b>230</b>
                    <br>本月累计
                </div>
            </div>
        </div>
        <div class="total">
            <div class="total-top">
                <div class="SummaryDataShow-RedTitle ta-l">活跃终端汇总</div>
                <div class="h2">17</div>
                <div>今日活跃</div>
            </div>
            <div class="div">
                <div class="div-item">
                    <b>17</b>
                    <br>昨日活跃
                </div>
                <div class="div-item">
                    <b>230</b>
                    <br>本月累计活跃
                </div>
            </div>
        </div>
        <div class="total">
            <div>
                <div class="SummaryDataShow-GreenTitle ta-l dailyTitleT">累计终端</div>
            </div>
            <div class="div">
                <div class="div-item mt-default">
                    <div class="h2">17</div>
                    <br>未下单终端
                </div>
                <div class="div-item mt-default">
                    <div class="h2">1700</div>
                    <br>终端总数
                </div>
            </div>
        </div>
    </div>
    <br/>
</body>
</html>
