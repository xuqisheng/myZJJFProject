<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh" ng-app="orderTable">
<head>
    <meta charset="UTF-8">
    <title>街坊店宝</title>
    <%@ include file="../../common/head.jsp"%>
    <%@ include file="../../common/datepicker.jsp"%>
    <%@ include file="../../common/pagination.jsp"%>
    <script src="<%=request.getContextPath() %>/resources/vendor/echarts/echarts.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/echartsOption/lineOption.js"></script>
    <link rel="stylesheet" href="${root}/resources/css/zjdb-sale.css">
</head>
<body class="wrap-bd" ng-controller="tableController" data-ng-init="loadPage()">
<input type="hidden" name="menuId" class="input input-default" value=${menuId } id="menuId">
	<div class="mb-default">
	    <a href="" class="crumb">订单数据分析</a>
	    <a href="" class="crumb crumb-active">交易额分析</a>
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
	    <div class="total">
	        <div class="total-top">
	            <div class="SummaryDataShow-BlueTitle ta-l">日交易额汇总</div>
	            <div class="h2" ng-cloak>{{dailyMummary.todayTurnoverSum}}</div>
                <div>今日交易额</div>
	        </div>
            <div class="div">
                <div class="div-item">
                    <b ng-cloak>{{dailyMummary.yesTodayTurnoverSum}}</b>
                    <br>昨日交易额
                </div>
                <div class="div-item">
                    <b ng-class="{true: 'txt-success', false: 'txt-warn'}[dailyMummary.dailyIncrease >= 0]" ng-cloak>{{dailyMummary.dailyIncrease}}%</b>
                    <br>日增长率
                </div>
            </div>
	    </div>
	    <div class="total">
            <div class="total-top">
	            <div class="SummaryDataShow-RedTitle ta-l">月交易额汇总</div>
	            <div class="h2" ng-cloak>{{monthMummary.thisMonthTurnoverSum}}</div>
                <div>本月交易额</div>
	        </div>
            <div class="div">
                <div class="div-item">
                    <b ng-cloak>{{monthMummary.lastMonthTurnoverSum}}</b>
                    <br>上月交易额
                </div>
                <div class="div-item">
                    <b ng-class="{true: 'txt-success', false: 'txt-warn'}[monthMummary.monthIncrease >= 0]" ng-cloak>{{monthMummary.monthIncrease}}%</b>
                    <br>月增长率
                </div>
            </div>
	    </div>
	</div>
    </br>
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
	<br/>
	<div class="table-contain">
	    <pre>
	    <table class="table-list">
	        <thead class="table-thead">
	 	        <tr><th colspan="{{a.titleCount}}" ng-repeat="a in parentTitle" ng-cloak>{{a.parentTitle}}</th></tr>
		        <tr><td ng-repeat="a in cn_keys track by $index" ng-cloak>{{a}}</td> </tr>

	        </thead>
	        <tbody class="table-tbody">
	       		<tr ng-repeat="d_row in key_dataList">
		       		<td ng-repeat="b in d_row track by $index">
		       			<b ng-if="$index == '8' || $index == '15'" ng-class="{true: 'txt-success', false: 'txt-warn'}[b >= 0]" ng-cloak>{{b}}</b>
		       			<span ng-if="!($index == '8' || $index == '15')" ng-cloak>
		       				{{b}}
		       			</span>
		       		</td>
	       		</tr>
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

	    var tableController_url = root + '/api/sp_order/turnover/spOrderTurnoverList.do';
	    var export_url = root + '/api/sp_order/turnover/portExcel.do';
	    var changeCity_url = root + '/api/base_core/getCityAreaGroupById.do';
	    var getQueryParam_url = root + '/api/sp_order/turnover/getQueryParam.do';

	    var app = angular.module('orderTable', ['My97Ext', 'tm.pagination']); // 第二个参数定义了Module依赖

	    app.controller('tableController', ['$scope', 'spOrderSevrices', function ($scope, spOrderSevrices) {

	    	$scope.items = [{'code':"yestoday", 'name':'昨天'}, {'code':"week", 'name':'7天前'}, {'code':"month", 'name':'30天前'},];

	    	$scope.loadPage = function () {
	        	$scope.isLoading = true;
				var data = getParam();
				spOrderSevrices.getQueryParam(data).success(function (result) {
					$scope.citySelect = result.optionList[0];
		    		$scope.areaSelect = result.optionList[1];
		    		$scope.spGroupIdSelect = result.optionList[2];
	            });
				defaultDayTime();
	        }

			$scope.queryOrders = function(flag){
	        	if(flag && flag ==1){
	        		$scope.paginationConf.currentPage = 1;
	        	}
				$scope.isLoading = true;
				var data = getParam();
				spOrderSevrices.getOrderList(data).success(function (result) {
		    		$scope.cn_keys = result.key_cn;
		    		$scope.key_dataList = result.dataList;
		    		$scope.parentTitle = result.parentTitle;
		    		$scope.dailyMummary = result.dailyMummary;
		    		$scope.monthMummary = result.monthMummary;
		    		$scope.paginationConf.totalItems = result.totalCount;

		    		loadEchart(result.data.xdata,result.data.ydata,result.data.y1data);
		    		$scope.isLoading = false;
	            });
	    	}

	    	$scope.excelExport = function(){
	    		var areaId = $scope.selectArea || '';
	    		var cityId = $scope.selectCity || '';
	    		var spGroupId = $scope.selectSpGroupId || '';
	    		var supplierName = $scope.supplierName || '';
	    		var dayTime = $scope.dayTime || '';
	    		var param = "cityId=" + cityId + "&areaId=" + areaId + "&spGroupId=" + spGroupId + "&supplierName=" + supplierName + "&dayTime=" + dayTime;
	    		console.log(param);
	    		location.href = export_url + "?" + param;
	    	}

	    	$scope.changeCityOrArea = function(type){
	    		var data = {};
	    		data["type"] = type;
	    		data["cityId"] = ($scope.selectCity || null);
	    		data["areaId"] = ($scope.selectArea || "");
	    		console.log(data);
	    		spOrderSevrices.changeCity(data).success(function (result) {
	    			if(type == 1){
	    				$scope.areaSelect = result.areaSelect;
	    			}
	    			$scope.spGroupIdSelect = result.spGroupIdSelect;
	            });
	    	}

	    	$scope.clearQuery = function(){
	    		$scope.selectCity = "";
	    		$scope.selectArea = "";
	    		$scope.selectSpGroupId = "";
	    		$scope.supplierName = "";
	    		//$scope.dayTime = "";
	    		defaultDayTime();
	    	}

	    	var getParam = function() {

	    		var paramMap = {};
	    		paramMap["cityId"] = $scope.selectCity || null;
	    		paramMap["areaId"] = $scope.selectArea || null;
	    		paramMap["spGroupId"] = $scope.selectSpGroupId || null;
	    		paramMap["supplierName"] = $scope.supplierName || null;
	    		paramMap["dayTime"] = $scope.dayTime || null;
	    		paramMap["limit"] = $scope.paginationConf.itemsPerPage || null;
	    		if($scope.paginationConf.itemsPerPage != null && $scope.paginationConf.currentPage != null){
	    			paramMap["offset"] = $scope.paginationConf.itemsPerPage * ($scope.paginationConf.currentPage - 1);
	    		}else{
	    			paramMap["offset"] = 0;
	    		}
	    		return paramMap;
	    	}


	    	$scope.checkTab = function(addDayCount) {
	    		$scope.selectDate(addDayCount);
	    	    $scope.queryOrders(1);
	    	}

	    	var defaultDayTime = function() {
	            var today = new Date();
	            var year = today.getFullYear();
	            var month = today.getMonth() + 1;
	            if(month < 10) {month = '0' + month;}
	            var date = today.getDate() - 1;
	            if(date < 10) {date = '0' + date;}
	            $scope.dayTime = year + "-" + month + "-" + date;
	        }

	    	var loadEchart = function(xdata,ydata,y1data) {

	            //--- 折柱 ---
	                //基于准备好的dom,初始化echart图表
	            var myChart = echarts.init(document.getElementById('gmv-chart'));
	            myChart.showLoading();
	            //定义图表option
	            var option = optionTwo;
                option.legend.data = ['总交易额','自营额'],
	            option.xAxis[0].data = xdata;
	            option.series[0].name = '总交易额';
	            option.series[0].data = ydata;
	            option.series[1].name = '自营额';
	            option.series[1].data = y1data;
	            //为echarts对象加载数据
	            myChart.setOption(option);
	            myChart.hideLoading();
	            window.onresize =myChart.resize;
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

	    app.service('spOrderSevrices', ['$http', function ($http) {

	        this.changeCity = function (postData) {
	        	return $http.post(changeCity_url, postData);
	        }
	        this.getQueryParam = function (postData) {
	        	return $http.post(getQueryParam_url, postData);
	        }
	        this.getOrderList = function (postData) {
	            return $http.post(tableController_url, postData);
	        }
	    }]);
	</script>
</body>
</html>
