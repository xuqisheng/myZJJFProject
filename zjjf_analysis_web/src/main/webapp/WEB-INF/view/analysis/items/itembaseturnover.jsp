<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh" ng-app="orderTable">
<head>
    <meta charset="UTF-8">
    <title>魔方 - 商品分析</title>
    <%@ include file="../../common/head.jsp"%>
    <%@ include file="../../common/datepicker.jsp"%>
    <%@ include file="../../common/pagination.jsp"%>
    <script src="<%=request.getContextPath() %>/resources/vendor/echarts/echarts.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/echartsOption/barOption.js"></script>
    <%--<script src="<%=request.getContextPath() %>/resources/js/echartsOption/lineOption.js"></script>--%>
</head>
<body class="wrap-bd" ng-controller="tableController" data-ng-init="loadPage()">
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
            商品交易额对比分析
        </div>
        <div class="chart-content">
            <div class="chart-div">
                <div id="transaction-chart" style="height:400px;"></div>
            </div>
        </div>
    </div>
    <div class="trend-chart">
        <div class="chart-header">
            商品订单量对比分析
        </div>
        <div class="chart-content">
            <div class="chart-div">
                <div id="orders-chart" style="height:400px;"></div>
            </div>
        </div>
    </div>
    <br/>
	<div class="table-contain">
	    <pre>
	    <table class="table-list">
	        <thead class="table-thead">
	 	        <tr><th colspan="{{a.titleCount}}" ng-repeat="a in parentTitle">{{a.parentTitle}}</th></tr>
		        <tr><td ng-repeat="a in cn_keys">{{a}}</td> </tr>
	        </thead>
	        <tbody class="table-tbody">
	       		<tr ng-repeat="d_row in key_dataList"><td ng-repeat="b in d_row track by $index">{{b}}</td></tr>
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

	    var tableController_url = root + '/api/items/spOrderList.do';
	    var export_url = root + '/api/items/portExcel.do';
	    var changeCity_url = root + '/api/base_core/getCityAreaGroupById.do';
	    var getQueryParam_url = root + '/api/items/getQueryParam.do';

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
		    		$scope.classifiOneSelect = result.optionList[3];
		    		$scope.classifiTwoSelect = result.optionList[4];
	            });
				$scope.selectDate(-1);
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
		    		$scope.paginationConf.totalItems = result.totalCount;
		    		loadEchart(result.data1.xdata, result.data1.ydata, result.data2.ydata);
		    		$scope.isLoading = false;
	            });
	    	}

			var loadEchart = function(xdata, y1data, y2data) {
				var transaction = echarts.init(document.getElementById('transaction-chart'));
		        var orders = echarts.init(document.getElementById('orders-chart'));
		        transaction.showLoading();
		        orders.showLoading();

		        var transactionOption = optionFirst;
		        var ordersOption = optionSecond;
		        /*var transactionOption = optionOne;
		        var ordersOption = optionOneSecond;*/

		        transactionOption.legend.data = ['交易额'],
		        transactionOption.xAxis[0].data = xdata,
                transactionOption.xAxis[0].axisLabel.formatter=function(c){
                    for(i in c){
                        if(c.length>5){
                            var a=c.substring(0,4)
                            return a+="..."
                        }else{
                            return c
                        }
                    }
                },
                transactionOption.series[0].name = '交易额',
		        transactionOption.series[0].data = y1data;

		        ordersOption.legend.data = ['总订单量'],
		        ordersOption.xAxis[0].data = xdata,
                ordersOption.xAxis[0].axisLabel.formatter=function(c){
                    for(i in c){
                        if(c.length>5){
                            var a=c.substring(0,4)
                            return a+="..."
                        }else{
                            return c
                        }
                    }
                },
		        ordersOption.series[0].name = '总订单量',
		        ordersOption.series[0].data = y2data;

		        // 使用刚指定的配置项和数据显示图表。
		        transaction.setOption(transactionOption);
		        orders.setOption(ordersOption);

		        transaction.hideLoading();
		        orders.hideLoading();

		        window.onresize =function(){
		            transaction.resize();
		            orders.resize();
		        }
			}

	    	$scope.excelExport = function(){
	    		var areaId = $scope.selectArea || '';
	    		var cityId = $scope.selectCity || '';
	    		var spGroupId = $scope.selectSpGroupId || '';
	    		var selectClassifiOne = $scope.selectClassifiOne || '';
	    		var selectClassifiTwo =  $scope.selectClassifiTwo || '';
	    		var averageTurnoverMax =  $scope.averageTurnoverMax || '';
	    		var averageTurnoverMin =  $scope.averageTurnoverMin || '';
	    		var supplierName = $scope.supplierName || '';
	    		var nameOrbarCode = $scope.nameOrbarCode || '';
	    		var addTimeBegin = $scope.addTimeBegin || '';
	    		var addTimeEnd = $scope.addTimeEnd || '';

	    		var param = "cityId=" + cityId + "&areaId=" + areaId + "&spGroupId=" + spGroupId + "&selectClassifiOne=" + selectClassifiOne
    				+ "&selectClassifiTwo=" + selectClassifiTwo + "&averageTurnoverMax=" + averageTurnoverMax + "&averageTurnoverMin=" + averageTurnoverMin + "&supplierName=" + supplierName
    				+ "&nameOrbarCode=" + nameOrbarCode + "&addTimeBegin=" + addTimeBegin + "&addTimeEnd=" + addTimeEnd;
	    		console.log(param);
	    		location.href = export_url + "?" + param;
	    	}

	    	$scope.changeCityOrArea = function(type){
	    		var data = {};
	    		data["type"] = type;
	    		data["cityId"] = ($scope.selectCity || null);
	    		data["areaId"] = ($scope.selectArea || "");
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
	    		$scope.selectClassifiOne = "";
	    		$scope.selectClassifiTwo = "";
	    		$scope.averageTurnoverMax = "";
	    		$scope.averageTurnoverMin = "";
	    		$scope.supplierName = "";
	    		$scope.nameOrbarCode = "";
	    		$scope.addTimeBegin = "";
	    		$scope.addTimeEnd = "";
                $scope.isYestertay = false;
                $scope.isWeek = false;
                $scope.isMonth = true;
                $scope.checkTab(-30);
	    	}

	    	var getParam = function() {
	    		var paramMap = {};
	    		paramMap["cityId"] = $scope.selectCity || null;
	    		paramMap["areaId"] = $scope.selectArea || null;
	    		paramMap["spGroupId"] = $scope.selectSpGroupId || null;
	    		paramMap["selectClassifiOne"] = $scope.selectClassifiOne || null;
	    		paramMap["selectClassifiTwo"] = $scope.selectClassifiTwo || null;
	    		paramMap["averageTurnoverMax"] = $scope.averageTurnoverMax || null;
	    		paramMap["averageTurnoverMin"] = $scope.averageTurnoverMin || null;
	    		paramMap["supplierName"] = $scope.supplierName || null;
	    		paramMap["nameOrbarCode"] = $scope.nameOrbarCode || null;
	    		paramMap["addTimeBegin"] = $scope.addTimeBegin || null;
	    		paramMap["addTimeEnd"] = $scope.addTimeEnd || null;
	    		paramMap["pageNo"] = $scope.paginationConf.currentPage || null;
	    		paramMap["offset"] = $scope.paginationConf.itemsPerPage || null;
	    		return paramMap;
	    	}

	    	$scope.checkTab = function(addDayCount) {
	    		$scope.selectDate(addDayCount);
	    	    $scope.queryOrders(1);
	    	}

	    	$scope.selectDate = function(addDayCount) {
	            if(-1 == addDayCount) {
	                $scope.isYestertay = true;
	                $scope.isWeek = false;
	                $scope.isMonth = false;
	            } else if(-7 == addDayCount) {
	                $scope.isYestertay = false;
	                $scope.isWeek = true;
	                $scope.isMonth = false;
	            } else if(-30 == addDayCount) {
	                $scope.isYestertay = false;
	                $scope.isWeek = false;
	                $scope.isMonth = true;
	            }
	            var today = new Date();
	            var year = today.getFullYear();
	            var month = today.getMonth() + 1;
	            if(month < 10) {month = '0' + month;}
	            var date = today.getDate() - 1;
	            if(date < 10) {date = '0' + date;}
	            $scope.addTimeEnd = year + "-" + month + "-" + date;
	            today.setDate(today.getDate() + addDayCount);
	            year = today.getFullYear();
	            month = today.getMonth() + 1;
	            if(month < 10) {month = '0' + month;}
	            date = today.getDate();
	            if(date < 10) {date = '0' + date;}
	            $scope.addTimeBegin = year + "-" + month + "-" + date;
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
