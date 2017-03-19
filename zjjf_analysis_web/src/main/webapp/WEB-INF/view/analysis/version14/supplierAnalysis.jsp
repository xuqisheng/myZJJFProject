<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>供应商分析</title>
    <%@ include file="../../common/head.jsp"%>
    <%@ include file="../../common/datepicker.jsp"%>
    <%@ include file="../../common/pagination.jsp"%>
    <link rel="stylesheet" href="${root}/resources/css/purchase.css">
</head>
<body class="wrap-bd">
    <div class="mb-default">
        <a href="" class="crumb">采购</a>
        <a href="" class="crumb crumb-active">供应商分析</a>
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
        <select class="select" ng-model="selectSupportmetho" ng-options="v.code as v.name for v in supportmethoSelect">
            <option value="">全部支付方式</option>
        </select>
        <select class="select" ng-model="selectsupportStatus" ng-options="v.code as v.name for v in supportStatusSelect">
            <option value="">全部支付状态</option>
        </select>
        <select class="select" ng-model="selectStatus" ng-options="v.code as v.name for v in statusSelect">
            <option value="">全部配送状态</option>
        </select>
    </div>
    <div class="op-section">
        <input type="text" placeholder="订单号/子订单号" class="input input-search-text" ng-model="orderNos">
        <input type="text" placeholder="便利店名称" class="input input-search-text" ng-model="storeName">
        <input type="text" placeholder="配送商" class="input input-search-text" ng-model="supplierName">
    </div>
    <div class="op-section clearfix">
        <div class="fl">
            下单时间
            <input type="text" class="input input-date" onchange="" date-picker ng-model="dayTimeBegin"> 至
            <input type="text" class="input input-date" onchange="" date-picker ng-model="dayTimeEnd">
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
    <div class="custom-fields">
        <div class="custom-title fl">自定义报表字段</div>
        <ul class="fields-name fl">
            <li class="active">默认</li>
            <li>全选</li>
            <li>供应商编号</li>
            <li>供应商名称</li>
            <li>合作方式</li>
        </ul>
    </div>
    <br/>
    <div class="trend-chart">
        <div class="chart-header">
            近30天订单交易额走势
        </div>
        <div class="chart-content">
            <div class="chart-div">
                <div id="supplier-chart" style="height:500px;"></div>
            </div>
        </div>
        <div class="chart-content">
            <div class="chart-div">
                <div id="goods-chart" style="height:500px;"></div>
            </div>
        </div>
    </div>

    <script src="<%=request.getContextPath() %>/resources/vendor/echarts/echarts.js"></script>
    <%--<script src="<%=request.getContextPath() %>/resources/js/echartsOption/barOption.js"></script>--%>
    <script src="<%=request.getContextPath() %>/resources/js/echartsOption/lineOption.js"></script>

    <script type="text/javascript">

        // 基于准备好的dom，初始化echarts实例
        var supplier = echarts.init(document.getElementById('supplier-chart'));
        var goods = echarts.init(document.getElementById('goods-chart'));
        supplier.showLoading();
        goods.showLoading();

        /*var supplierOption = optionFirst;
        var goodsOption = optionSecond;*/
        var supplierOption = optionOne;
        var goodsOption = optionOneSecond;

        supplierOption.legend.data = ['毛利率'],
        supplierOption.xAxis[0].data = [ "6345", "434","435","434", "横岗","43","龙岗转角", "43","龙岗区","坪山", "横岗","南山转角","龙岗转角", "南山区22","龙岗区","坪山", "横岗","南山转角","龙岗转角", "南山区22","龙岗区","坪山", "横岗","南山转角","龙岗转角"]
        supplierOption.series[0].name = '毛利率',
        supplierOption.series[0].data = [300, 400, 100, 234, 290, 330, 310, 182, 100, 234, 290, 330, 310, 400, 100, 234, 290, 330, 310, 182, 100, 234, 290, 330, 310]

        goodsOption.legend.data = ['毛利率'],
        goodsOption.xAxis[0].data = [ "福田区", "南山区22","龙岗区","坪山", "横岗","南山转角","龙岗转角", "南山区22","龙岗区","坪山", "横岗","南山转角","龙岗转角", "南山区22","龙岗区","坪山", "横岗","南山转角","龙岗转角", "南山区22","龙岗区","坪山", "横岗","南山转角","龙岗转角"]
        goodsOption.series[0].name = '毛利率',
        goodsOption.series[0].data = [150, 200, 100, 234, 290, 330, 310, 182, 100, 234, 290, 330, 310, 400, 100, 234, 290, 330, 310, 182, 100, 234, 290, 330, 310]

        // 使用刚指定的配置项和数据显示图表。
        supplier.setOption(supplierOption);
        goods.setOption(goodsOption);

        supplier.hideLoading();
        goods.hideLoading();

        window.onresize =function(){
            supplier.resize();
            goods.resize();
        }
    </script>
</body>
</html>
