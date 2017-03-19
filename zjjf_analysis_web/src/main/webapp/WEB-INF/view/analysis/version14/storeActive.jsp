<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>终端活跃</title>
	<%@ include file="../../common/head.jsp"%>
    <%@ include file="../../common/datepicker.jsp"%>
    <%@ include file="../../common/pagination.jsp"%>
</head>
<body class="wrap-bd">
    <div class="mb-default">
        <a href="" class="crumb">终端分析</a>
        <a href="" class="crumb crumb-active">终端分析</a>
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
        <input type="text" class="input input-date" onfocus="WdatePicker()" onchange="" date-picker ng-model="addTimeBegin"> 至
        <input type="text" class="input input-date" onfocus="WdatePicker()" onchange="" date-picker ng-model="addTimeEnd">
    </div>
    <div class="op-section clearfix">
        <div class="fr">
            <input type="button" value="查询" ng-click="queryOrders(1);" class="input input-search-button">
            <input type="button" value="重置" ng-click="clearQuery();" class="input input-search-button-white">
            <input type="button" value="导出" ng-click="excelExport();" class="input input-search-button-white">
        </div>
    </div>
    <br/>
    <div class="trend-chart">
        <div class="chart-header">
            活跃终端分布图
        </div>
        <div class="chart-content">
            <div class="chart-div">
                <div class="half-div">
                    <div id="order-chart" style="height:400px;"></div>
                </div>
                <div class="half-div">
                    <div id="cumulative-chart" style="height:400px;"></div>
                </div>
            </div>
        </div>
    </div>

    <script src="<%=request.getContextPath() %>/resources/vendor/echarts/echarts.js"></script>
    <%--<script src="<%=request.getContextPath() %>/resources/js/echartsOption/barOption.js"></script>--%>
    <script src="<%=request.getContextPath() %>/resources/js/echartsOption/lineOption.js"></script>
    <script type="text/javascript">

        // 基于准备好的dom，初始化echarts实例
        var order = echarts.init(document.getElementById('order-chart'));
        var cumulative = echarts.init(document.getElementById('cumulative-chart'));
        order.showLoading();
        cumulative.showLoading();

        /*var orderOption = optionFirst;
        var cumulativeOption = optionSecond;*/
        var orderOption = optionOne;
        var cumulativeOption = optionOneSecond;

        orderOption.legend.data = ['终端数'],
        orderOption.xAxis[0].data = [ "123", "6546","465","46", "46","46","46", "南山区22","龙岗区","坪山", "横岗","南山转角","龙岗转角"]
        orderOption.series[0].name = '终端数',
        orderOption.series[0].data = [100, 182, 500, 234, 290, 330, 310, 182, 500, 234, 290, 330, 310]
        orderOption.legend.right = '9.5%',

        cumulativeOption.legend.data = ['终端数'],
        cumulativeOption.xAxis[0].data = [ "福田区", "南山区22","龙岗区","坪山", "横岗","南山转角","龙岗转角", "南山区22","龙岗区","坪山", "横岗","南山转角","龙岗转角"]
        cumulativeOption.series[0].name = '终端数',
        cumulativeOption.series[0].data = [220, 182, 290, 234, 290, 330, 310, 182, 290, 234, 290, 330, 310]
        cumulativeOption.legend.right = '9.5%',

        // 使用刚指定的配置项和数据显示图表。
        order.setOption(orderOption);
        cumulative.setOption(cumulativeOption);

        order.hideLoading();
        cumulative.hideLoading();

        window.onresize =function(){
            order.resize();
            cumulative.resize();
        }
    </script>
</body>
</html>
