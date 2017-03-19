<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>终端累计</title>
    <%@ include file="../../common/head.jsp"%>
    <%@ include file="../../common/datepicker.jsp"%>
    <%@ include file="../../common/pagination.jsp"%>
    <link rel="stylesheet" href="${root}/resources/css/daily.css">
</head>
<body class="wrap-bd">
    <div class="mb-default">
        <a href="" class="crumb">终端</a>
        <a href="" class="crumb crumb-active">终端累计</a>
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
        &nbsp;&nbsp;&nbsp;&nbsp;下单时间&nbsp;&nbsp;
        <input type="text" class="input input-date" onchange="" date-picker ng-model="addTime">
    </div>
    <div class="op-section clearfix">
        <div class="fr">
            <input type="button" value="查询" ng-click="queryOrders(1);" class="input input-search-button">
            <input type="button" value="重置" ng-click="clearQuery();" class="input input-search-button-white">
            <input type="button" value="导出" ng-click="excelExport();" class="input input-search-button-white">
        </div>
    </div>
    <div class="total-c clearfix">
        <div class="total">
            <div class="total-top">
                <div class="SummaryDataShow-BlueTitle ta-l dailyTitleT">注册终端</div>
                <div class="h2">123</div>
                <div>今日新增</div>
            </div>
            <div class="div">
                <div class="div-item">
                    <b>123</b>
                    <br>昨日新增
                </div>
                <div class="div-item">
                    <b>123</b>
                    <br>本月新增
                </div>
            </div>
        </div>
        <div class="total">
            <div class="total-top">
                <div class="SummaryDataShow-RedTitle ta-l dailyTitleT">活跃终端</div>
                <div class="h2">123</div>
                <div>今日新增</div>
            </div>
            <div class="div">
                <div class="div-item">
                    <b>123</b>
                    <br>昨日新增
                </div>
                <div class="div-item">
                    <b>123</b>
                    <br>本月新增
                </div>
            </div>
        </div>
        <div class="total">
            <div>
                <div class="SummaryDataShow-GreenTitle ta-l dailyTitleT">累计终端</div>
            </div>
            <div class="div">
                <div class="div-item mt-default">
                    <div class="h2">123</div>
                    <br>未下单终端
                </div>
                <div class="div-item mt-default">
                    <div class="h2">123</div>
                    <br>终端总数
                </div>
            </div>
        </div>
    </div>
    <br/>
    <div class="trend-chart">
        <div class="chart-header">
            近30天终端累计曲线图
        </div>
        <div class="chart-content">
            <div class="chart-div">
                <div id="storeAddup-chart" style="height:400px;"></div>
            </div>
        </div>
    </div>

    <script src="<%=request.getContextPath() %>/resources/vendor/echarts/echarts.js"></script>
    <%--<script src="<%=request.getContextPath() %>/resources/vendor/echarts/lineOption.js"></script>--%>
    <script src="<%=request.getContextPath() %>/resources/js/echartsOption/lineOption.js"></script>
    <script type="text/javascript">

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('storeAddup-chart'));
        myChart.showLoading();

        var option = optionTwo;
        option.legend.data = ['终端总数','活跃终端'],
        option.xAxis[0].data = [ "福田区22", "南山区22","龙岗区","坪山", "横岗","南山转角","龙岗转角", "南山区22","龙岗区","坪山", "横岗","南山转角","龙岗转角"],
        option.series[0].name = '终端总数',
        option.series[0].data = [220, 182, 100, 234, 290, 330, 310, 182, 100, 234, 290, 330, 310]
        option.series[1].name = '活跃终端',
        option.series[1].data = [120, 132, 200, 134, 90, 230, 210, 132, 200, 134, 90, 230, 210]

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        myChart.hideLoading();
        window.onresize =myChart.resize;
    </script>
</body>
</html>
