<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>GMV</title>
        <%@ include file="../../common/head.jsp"%>
        <%@ include file="../../common/datepicker.jsp"%>
        <%@ include file="../../common/pagination.jsp"%>
    <link rel="stylesheet" href="${root}/resources/css/zjdb-sale.css">
</head>
<body class="wrap-bd">
    <input type="hidden" name="" class="input input-default">
    <div class="mb-default">
        <a href="" class="crumb">订单</a>
        <a href="" class="crumb crumb-active">GMV</a>
    </div>
    <div class="op-section">
        <select class="select" ng-model="selectCity" ng-options="v.code as v.name for v in citySelect" ng-change="changeCityOrArea(1)">
                <option value="">全部城市</option>
        </select>
        <select class="select" ng-model="selectArea" ng-options="v.code as v.name for v in areaSelect" ng-change="changeCityOrArea(2)">
            <option value="">全部区域</option>
        </select>
        <select class="select" ng-model="selectSpGroupId" ng-options="v.code as v.name for v in spGroupIdSelect">
            <option value="">全部定格</option>
        </select>
        <input type="text" placeholder="合作商" class="input input-search-text" ng-model="supplierName">
    </div>
    <div class="op-section clearfix">
        <div class="fl">
            查询时间: &nbsp;&nbsp;<input type="text" class="input input-date" onchange="" date-picker ng-model="dayTime">
        </div>
        <div class="fr">
            <input type="button" value="查询" ng-click="queryOrders(1);" class="input input-search-button">
            <input type="button" value="重置" ng-click="clearQuery();" class="input input-search-button-white">
            <input type="button" value="导出" ng-click="excelExport();" class="input input-search-button-white">
        </div>
    </div>
    <div class="total-c clearfix">
        <div class="total" style="width: 100%;">
            <div class="total-top" style="border-bottom: 1px solid #dad9d9;margin-bottom: 12px;">
                <div class="SummaryDataShow-BlueTitle ta-l">&nbsp;&nbsp;订单交易额</div>
                <div class="fl ta-c width-half">
                    <div class="h2">123</div>
                    <div>今日交易额</div>
                </div>
                <div class="fr ta-c width-half">
                    <div class="h2">123</div>
                    <div>本月交易额</div>
                </div>
            </div>
            <div class="div">
                <div class="fl ta-c width-half">
                    <div class="div-item" style="padding-left: 200px;">
                        <b>123</b>
                        <br>昨日交易额
                    </div>
                    <div class="div-item" style="padding-right: 200px;">
                        <b>123</b>
                        <br>日增长率
                    </div>
                </div>
                <div class="fr ta-c width-half">
                    <div class="div-item" style="padding-left: 200px;">
                        <b>123</b>
                        <br>上月交易额
                    </div>
                    <div class="div-item" style="padding-right: 200px;">
                        <b>123</b>
                        <br>月增长率
                    </div>
                </div>
            </div>
        </div>
    </div>
    <br/>
    <div class="trend-chart">
        <div class="chart-header">
            近30天订单交易额走势
        </div>
        <div class="chart-content">
            <div class="chart-div">
                <div id="gmv-chart" style="height:400px;"></div>
            </div>
        </div>
    </div>

    <script src="<%=request.getContextPath() %>/resources/vendor/echarts/echarts.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/echartsOption/lineOption.js"></script>
    <script type="text/javascript">

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('gmv-chart'));
        myChart.showLoading();
        var option = optionTwo;

        option.legend.data = ['总交易额','自营额'],
        option.xAxis[0].data = [ "福田区22", "南山区22","龙岗区","坪山", "横岗","南山转角","龙岗转角"],
        option.series[0].name = '总交易额',
        option.series[0].data = [220, 182, 191, 234, 290, 330, 310]
        option.series[1].name = '自营额',
        option.series[1].data = [120, 132, 200, 134, 90, 230, 210]

        // 使用刚指定的配置项和数据显示图表。

        myChart.setOption(option);
        myChart.hideLoading();
        window.onresize =myChart.resize;
    </script>
</body>
</html>
