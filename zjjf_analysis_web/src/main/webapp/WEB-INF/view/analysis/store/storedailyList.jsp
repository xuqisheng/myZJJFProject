<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh" ng-app="orderTable">
<head>
    <meta charset="UTF-8">
    <title>终端日报</title>
    <%@ include file="../../common/head.jsp"%>
    <%@ include file="../../common/datepicker.jsp"%>
    <%@ include file="../../common/pagination.jsp"%>
    <link rel="stylesheet" href="${root}/resources/css/daily.css">
    <script src="<%=request.getContextPath() %>/resources/vendor/echarts/echarts.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/echartsOption/lineOption.js"></script>
</head>
<body class="wrap-bd" ng-controller="tableController" data-ng-init="loadPage()">
<div class="mb-default">
    <a href="" class="crumb">销售报表</a>
    <a href="" class="crumb crumb-active">终端日报2</a>
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
            <div class="h2" ng-cloak>{{bannerData.todayAddReg}}</div>
            <div>今日新增</div>
        </div>
        <div class="div">
            <div class="div-item">
                <b ng-cloak>{{bannerData.yestodayAddReg}}</b>
                <br>昨日新增
            </div>
            <div class="div-item">
                <b ng-cloak>{{bannerData.monthReg}}</b>
                <br>本月新增
            </div>
        </div>
    </div>
    <div class="total">
        <div class="total-top">
            <div class="SummaryDataShow-RedTitle ta-l dailyTitleT">活跃终端</div>
            <div class="h2" ng-cloak>{{bannerData.todayAddActive}}</div>
            <div>今日新增</div>
        </div>
        <div class="div">
            <div class="div-item">
                <b ng-cloak>{{bannerData.yesTodayAddActive}}</b>
                <br>昨日新增
            </div>
            <div class="div-item">
                <b ng-cloak>{{bannerData.monthAddActive}}</b>
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
                <div class="h2" ng-cloak>{{bannerData.unorderStore}}</div>
                <br>未下单终端
            </div>
            <div class="div-item mt-default">
                <div class="h2" ng-cloak>{{bannerData.totalStore}}</div>
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
<br/>
<div class="table-contain">
	    <pre>
	    <table class="table-list">
	        <thead class="table-thead">
	 	        <tr><th colspan="{{a.titleCount}}" ng-repeat="a in parentTitle" ng-cloak>{{a.parentTitle}}</th></tr>
		        <tr><td ng-repeat="a in cn_keys track by $index" ng-cloak>{{a}}</td> </tr>
	        </thead>
	        <tbody class="table-tbody">
	       		<tr ng-repeat="d_row in key_dataList "><td ng-repeat="b in d_row track by $index" ng-cloak>{{b}}</td></tr>
                <tr>
                    <td colspan="{{cn_keys.length}}" class="padding-zero">
                        <tm-pagination conf="paginationConf"></tm-pagination>
                    </td>
                </tr>
	        </tbody>
	    </table>
        </pre>
</div>

<%--<tm-pagination conf="paginationConf"></tm-pagination>--%>
<div class="cover-loading" ng-show="isLoading"></div>


<script>

    var root = '<%=request.getContextPath() %>';

    var tableController_url = root + '/api/store/daily/list.do';
    var export_url = root + '/api/store/daily/portExcel.do';
    var changeCity_url = root + '/api/base_core/getCityAreaGroupById.do';
    var getQueryParam_url = root + '/api/store/daily/getQueryParam.do';

    var app = angular.module('orderTable', ['My97Ext', 'tm.pagination']); // 第二个参数定义了Module依赖

    app.controller('tableController', ['$scope', 'service', function ($scope, service) {

        $scope.loadPage = function () {
            $scope.isLoading = true;
            var data = getParam();
            service.getQueryParam(data).success(function (result) {
                $scope.citySelect = result.optionList[0];
                $scope.areaSelect = result.optionList[1];
                $scope.spGroupIdSelect = result.optionList[2];
                $scope.isLoading = false;
            });
            defaultDayTime();
        }

        $scope.queryOrders = function(flag){
            if(flag && flag ==1){
                $scope.paginationConf.currentPage = 1;
            }
            $scope.isLoading = true;
            var data = getParam();
            service.getList(data).success(function (result) {
                $scope.cn_keys = result.key_cn;
                $scope.key_dataList = result.dataList;
                $scope.parentTitle = result.parentTitle;
                $scope.paginationConf.totalItems = result.totalCount;
                $scope.bannerData = result.bannerData;
                loadEchart(result.data.xdata,result.data.ydata,result.data.y1data);
                $scope.isLoading = false;
            });
        }

        var loadEchart = function(xdata,ydata,y1data) {

            //--- 折柱 ---
            //基于准备好的dom,初始化echart图表
            var myChart = echarts.init(document.getElementById('storeAddup-chart'));
            myChart.showLoading();
            //定义图表option
            var option = optionTwo;
            option.legend.data = ['终端总数','活跃终端'],
            option.xAxis[0].data = xdata;
            option.series[0].name = '终端总数';
            option.series[0].data = ydata;
            option.series[1].name = '活跃终端';
            option.series[1].data = y1data;
            //为echarts对象加载数据
            myChart.setOption(option);
            myChart.hideLoading();
            window.onresize =myChart.resize;
        }

        $scope.excelExport = function(){
            var areaId = $scope.selectArea || '';
            var cityId = $scope.selectCity || '';
            var spGroupId = $scope.selectSpGroupId || '';
            var addTime = $scope.addTime || '';
            var param = "cityId=" + cityId + "&areaId=" + areaId + "&spGroupId=" + spGroupId + "&addTime=" + addTime;
            console.log(param);
            location.href = export_url + "?" + param;
        }

        $scope.changeCityOrArea = function(type){
            var data = {};
            data["type"] = type;
            data["cityId"] = ($scope.selectCity || null);
            data["areaId"] = ($scope.selectArea || "");
            service.changeCity(data).success(function (result) {
                if(type == 1){
                    $scope.areaSelect = result.areaSelect;
                }
                $scope.spGroupIdSelect = result.spGroupIdSelect;
            });
        }

        var defaultDayTime = function() {
            var today = new Date();
            var year = today.getFullYear();
            var month = today.getMonth() + 1;
            if(month < 10) {month = '0' + month;}
            var date = today.getDate() - 1;
            if(date < 10) {date = '0' + date;}
            $scope.addTime = year + "-" + month + "-" + date;
        }

        $scope.clearQuery = function(){
            $scope.selectCity = "";
            $scope.selectArea = "";
            $scope.selectSpGroupId = "";
            $scope.addTime = "";
            defaultDayTime();
        }

        var getParam = function() {
            var paramMap = {};
            paramMap["cityId"] = $scope.selectCity || null;
            paramMap["areaId"] = $scope.selectArea || null;
            paramMap["spGroupId"] = $scope.selectSpGroupId || null;
            paramMap["addTime"] = $scope.addTime || null;
            paramMap["pageNo"] = $scope.paginationConf.currentPage || null;
            paramMap["offset"] = $scope.paginationConf.itemsPerPage || null;
            return paramMap;
        }

        $scope.paginationConf = {
            currentPage: 1,
            itemsPerPage: 10,
        };

        var pagenationQuery = function() {
            $scope.queryOrders(0);
        }
        /***************************************************************
         当页码和页面记录数发生变化时监控后台查询; 如果把currentPage和itemsPerPage分开监控的话则会触发两次后台事件。
         ***************************************************************/
        $scope.$watch('paginationConf.currentPage + paginationConf.itemsPerPage', pagenationQuery);
    }]);

    app.service('service', ['$http', function ($http) {

        this.changeCity = function (postData) {
            return $http.post(changeCity_url, postData);
        }
        this.getQueryParam = function (postData) {
            return $http.post(getQueryParam_url, postData);
        }
        this.getList = function (postData) {
            return $http.post(tableController_url, postData);
        }
    }]);
</script>
</body>
</html>
