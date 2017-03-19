<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>商品交易分析</title>
	<%@ include file="../../common/head.jsp"%>
    <%@ include file="../../common/datepicker.jsp"%>
    <%@ include file="../../common/pagination.jsp"%>
</head>
<body class="wrap-bd">
    <div class="mb-default">
        <a href="" class="crumb">商品分析</a>
        <a href="" class="crumb crumb-active">商品交易分析</a>
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
        <input type="text" placeholder="请输入合作商名称" class="input input-search-text" ng-model="supplierName">
        <input type="text" placeholder="请输入商品名称/条形码" class="input input-search-text" ng-model="nameOrbarCode">
    </div>
    <div class="op-section">
        <span>均价区间</span>
        <input type="text" class="input" ng-model="averageTurnoverMin"> 至
        <input type="text" class="input" ng-model="averageTurnoverMax">
        <select class="select ml-default" ng-model="selectClassifiOne" ng-options="v.code as v.name for v in classifiOneSelect">
            <option value="">请选择一级分类</option>
        </select>
        <select class="select" ng-model="selectClassifiTwo" ng-options="v.code as v.name for v in classifiTwoSelect">
            <option value="">请选择二级分类</option>
        </select>

    </div>
    <div class="op-section clearfix">
        <div class="fl">
            下单时间
            <input type="text" class="input input-date" onchange="" date-picker ng-model="addTimeBegin"> 至
            <input type="text" class="input input-date" onchange="" date-picker ng-model="addTimeEnd">
            <input type="hidden" ng-model="recentDay">
            <span class="pills ml-default" ng-class="{'pills-active':isYestertay}" ng-click="checkTab(-1);">昨天</span>
            <span class="pills" ng-class="{'pills-active':isWeek}" ng-click="checkTab(-7);">最近7天</span>
            <span class="pills" ng-class="{'pills-active':isMonth}" ng-click="checkTab(-30);">最近30天</span>
        </div>
        <div class="fr">
            <input type="button" value="查询" ng-click="queryOrders(1);" class="input input-search-button">
            <input type="button" value="重置" ng-click="clearQuery();" class="input input-search-button-white">
            <input type="button" value="导出" ng-click="excelExport();" class="input input-search-button-white">
        </div>
    </div>
    <br/>
    <div class="trend-chart">
        <div class="chart-header">
            近30天订单交易额走势
        </div>
        <div class="chart-content">
            <div class="chart-content">
                <div class="chart-div">
                    <div id="transaction-chart" style="height:500px;"></div>
                </div>
            </div>
            <div class="chart-content">
                <div class="chart-div">
                    <div id="orders-chart" style="height:500px;"></div>
                </div>
            </div>
        </div>
    </div>

    <script src="<%=request.getContextPath() %>/resources/vendor/echarts/echarts.js"></script>
   <%-- <script src="<%=request.getContextPath() %>/resources/js/echartsOption/barOption.js"></script>--%>
    <script src="<%=request.getContextPath() %>/resources/js/echartsOption/lineOption.js"></script>
    <script type="text/javascript">

        // 基于准备好的dom，初始化echarts实例
        var transaction = echarts.init(document.getElementById('transaction-chart'));
        var orders = echarts.init(document.getElementById('orders-chart'));
        transaction.showLoading();
        orders.showLoading();

        /*var transactionOption = optionFirst;
        var ordersOption = optionSecond;*/
        var transactionOption = optionOne;
        var ordersOption = optionOneSecond;

        transactionOption.legend.data = ['交易额'],
        transactionOption.xAxis[0].data = [ "福", "南","龙","坪", "横","南","龙", "南","龙","坪", "横岗","南山转角","龙岗转角", "南山区22","龙岗区","坪山", "横岗","南山转角","龙岗转角", "南山区22","龙岗区","坪山", "横岗","南山转角","龙岗转角"]
        transactionOption.series[0].name = '交易额',
        transactionOption.series[0].data = [300, 400, 100, 234, 290, 330, 310, 182, 100, 234, 290, 330, 310, 400, 100, 234, 290, 330, 310, 182, 100, 234, 290, 330, 310]

        ordersOption.legend.data = ['总订单量'],
        ordersOption.xAxis[0].data = [ "31", "645","4564","46", "46","南山转角","龙岗转角", "46","龙岗区","坪山", "横岗","南山转角","龙岗转角", "南山区22","龙岗区","坪山", "横岗","南山转角","龙岗转角", "南山区22","龙岗区","坪山", "横岗","南山转角","龙岗转角"]
        ordersOption.series[0].name = '总订单量',
        ordersOption.series[0].data = [500, 200, 100, 234, 290, 330, 310, 182, 100, 234, 290, 330, 310, 200, 100, 234, 290, 330, 310, 182, 100, 234, 290, 330, 310]

        // 使用刚指定的配置项和数据显示图表。
        transaction.setOption(transactionOption);
        orders.setOption(ordersOption);

        transaction.hideLoading();
        orders.hideLoading();

        window.onresize =function(){
            transaction.resize();
            orders.resize();
        }
    </script>
</body>
</html>
