<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="report">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>供应商分析</title>
    <%@ include file="../../common/head.jsp"%>
    <%@ include file="../../common/datepicker.jsp"%>
    <%@ include file="../../common/pagination.jsp"%>
    <link rel="stylesheet" href="${root}/resources/css/purchase.css">
</head>
<body class="wrap-bd" ng-controller="ctrl" ng-init="loadPage()">
    <div class="mb-default">
        <a href="" class="crumb">采购</a>
        <a href="" class="crumb crumb-active">供应商分析</a>
    </div>
    <div class="op-section">

        <select class="select" ng-model="selectWhId" ng-options="v.code as v.name for v in whIdList">
                <option value="">全部仓库</option>
        </select>
        <select class="select" ng-model="selectManagerStatus" ng-options="v.code as v.name for v in managerStatusSelect">
            <option value="">供应商状态</option>
        </select>
        <select class="select" ng-model="selectCoopWay" ng-options="v.code as v.name for v in coopWaySelect">
            <option value="">合作方式</option>
        </select>
        <input type="text" placeholder="供应商编号/供应商名称" class="input input-search-text" ng-model="managerCodeOrmanageName">
        <input type="text" placeholder="商品条码/商品名称" class="input input-search-text" ng-model="itemNameOrItemCode">
	</div>
    </div>
    <div class="op-section clearfix">
        <div class="fl">
            下单时间
            <input type="text" class="input input-date" onchange="" date-picker ng-model="dayTimeBegin"> 至
            <input type="text" class="input input-date" onchange="" date-picker ng-model="dayTimeEnd">
            <input type="hidden" ng-model="recentDay">
        </div>
        <div class="fr">
            <input type="button" value="查询" ng-click="queryOrders(1);" class="input input-search-button">
            <input type="button" value="重置" ng-click="clearQuery();" class="input input-search-button-white">
            <input type="button" value="导出" ng-click="excelExport();" class="input input-search-button-white">
        </div>
    </div>
    <!-- <div class="custom-fields">
        <div class="custom-title fl">自定义报表字段</div>
        <ul class="fields-name fl">
            <li class="active">默认</li>
            <li>全选</li>
            <li>供应商编号</li>
            <li>供应商名称</li>
            <li>合作方式</li>
        </ul>
    </div> -->
    <br/>
    <div class="trend-chart">
        <div class="chart-header">
            同一供应商，不同商品的毛利率对比
        </div>
        <div class="chart-content">
            <div class="chart-div">
                <div id="supplier-chart" style="height:400px;"></div>
            </div>
        </div>
    </div>
    <div class="trend-chart">
        <div class="chart-header">
            同一商品，不同供应商的毛利率对比
        </div>
        <div class="chart-content">
            <div class="chart-div">
                <div id="goods-chart" style="height:400px;"></div>
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
                        <div class="cover-loading" ng-show="isLoading"></div>
                    </td>
                </tr>
	        </tbody>
	    </table>
        </pre>
	</div>

    <div class="cover-loading" ng-show="isLoading"></div>
    <script src="<%=request.getContextPath() %>/resources/vendor/echarts/echarts.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/echartsOption/lineOption.js"></script>

    <script type="text/javascript">

    	var root = '<%=request.getContextPath() %>';
    	var getErpManagerAnalysis_url = root + '/api/supplier/analysis/getList.do';
    	var getQueryParam_url = root + '/api/supplier/analysis/getQueryParam.do';
    	var export_url = root + '/api/supplier/analysis/portExcel.do';
    	var app = angular.module('report', ['My97Ext', 'tm.pagination']); // 第二个参数定义了Module依赖

    	app.controller('ctrl',['$scope','managerAnaService',function($scope, managerAnaService){

    		$scope.loadPage = function(){
    			managerAnaService.getQueryParam({}).success(function (result) {
					$scope.whIdList = result.optionList[0];
					$scope.managerStatusSelect = result.optionList[1];
					$scope.coopWaySelect = result.optionList[2];
	            });
				$scope.selectDate(-1);
    		}

    		$scope.queryOrders = function(flag){
	        	if(flag && flag ==1){
	        		$scope.paginationConf.currentPage = 1;
	        	}
				$scope.isLoading = true;
				var data = getParam();
				managerAnaService.getErpManagerAnalysis(data).success(function(result){
    				$scope.cn_keys = result.key_cn;
    				$scope.key_dataList = result.dataList;
    				$scope.paginationConf.totalItems = result.totalCount;
    				loadEchart(result.data.xList, result.data.ydataList, result.data.x1List, result.data.y1dataList);
    				$scope.isLoading = false;
    			});
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
	            $scope.dayTimeEnd = year + "-" + month + "-" + date;
	            today.setDate(today.getDate() + addDayCount);
	            year = today.getFullYear();
	            month = today.getMonth() + 1;
	            if(month < 10) {month = '0' + month;}
	            date = today.getDate();
	            if(date < 10) {date = '0' + date;}
	            $scope.dayTimeBegin = year + "-" + month + "-" + date;
	        }

    		$scope.paginationConf = {
	            currentPage: 1,
	            itemsPerPage: 10,
    		};

    		var getParam = function() {
    			var paramMap = {};
    			paramMap["dayTimeBegin"] = $scope.dayTimeBegin || null;
	    		paramMap["dayTimeEnd"] = $scope.dayTimeEnd || null;
	    		paramMap["whId"] = $scope.selectWhId || null;
	    		paramMap["managerStatusSelect"] = $scope.selectManagerStatus || null;
	    		paramMap["coopWaySelect"] = $scope.selectCoopWay || null;
	    		paramMap["managerCodeOrmanageName"] = $scope.managerCodeOrmanageName || null;
	    		paramMap["itemNameOrItemCode"] = $scope.itemNameOrItemCode || null;
    			paramMap["pageNo"] = $scope.paginationConf.currentPage || null;
	    		paramMap["offset"] = $scope.paginationConf.itemsPerPage || null;
    			return paramMap;
    		}

    		$scope.excelExport = function(){
	    		var dayTimeBegin = $scope.dayTimeBegin || '';
	    		var dayTimeEnd = $scope.dayTimeEnd || '';
	    		var whId = $scope.selectWhId || '';
	    		var managerStatusSelect = $scope.selectManagerStatus || '';
	    		var coopWaySelect =  $scope.selectCoopWay || '';
	    		var managerCodeOrmanageName =  $scope.managerCodeOrmanageName || '';
	    		var itemNameOrItemCode =  $scope.itemNameOrItemCode || '';

	    		var param = "dayTimeBegin=" + dayTimeBegin + "&dayTimeEnd=" + dayTimeEnd + "&whId=" + whId + "&managerStatusSelect=" + managerStatusSelect
    				+ "&coopWaySelect=" + coopWaySelect + "&managerCodeOrmanageName=" + managerCodeOrmanageName + "&itemNameOrItemCode=" + itemNameOrItemCode ;
	    		console.log(param);
	    		location.href = export_url + "?" + param;
	    	}

    		$scope.clearQuery = function(){

	    		$scope.managerCodeOrmanageName = "";
	    		$scope.itemNameOrItemCode = "";
	    		$scope.selectWhId = "";
	    		$scope.selectManagerStatus = "";
	    		$scope.coopWaySelect = "";
	    		$scope.dayTimeBegin = "";
	    		$scope.dayTimeEnd = "";
                $scope.isYestertay = false;
                $scope.isWeek = false;
                $scope.isMonth = true;
                $scope.checkTab(-1);
	    	}

    		var loadEchart = function(xdata, ydata, x1data, y1data) {

    			console.log(xdata)

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
    	        supplierOption.xAxis[0].data = xdata,
                supplierOption.xAxis[0].axisLabel.formatter=function(c){
                    for(i in c){
                        if(c.length>5){
                            var a=c.substring(0,4)
                            return a+="..."
                        }else{
                            return c
                        }
                    }
                },
    	        supplierOption.series[0].name = '毛利率',
    	        supplierOption.series[0].data = ydata,

    	        goodsOption.legend.data = ['毛利率'],
    	        goodsOption.xAxis[0].data = x1data,
                goodsOption.xAxis[0].axisLabel.formatter=function(c){
                    for(i in c){
                        if(c.length>5){
                            var a=c.substring(0,4)
                            return a+="..."
                        }else{
                            return c
                        }
                    }
                },
    	        goodsOption.series[0].name = '毛利率',
    	        goodsOption.series[0].data = y1data,

    	        // 使用刚指定的配置项和数据显示图表。
    	        supplier.setOption(supplierOption);
    	        goods.setOption(goodsOption);

    	        supplier.hideLoading();
    	        goods.hideLoading();

    	        window.onresize =function(){
    	            supplier.resize();
    	            goods.resize();
    	        }
	        }

	    	var pagenationQuery = function() {
	        	$scope.queryOrders(0);
	        }
	        /***************************************************************
			        当页码和页面记录数发生变化时监控后台查询; 如果把currentPage和itemsPerPage分开监控的话则会触发两次后台事件。
	        ***************************************************************/
	        $scope.$watch('paginationConf.currentPage + paginationConf.itemsPerPage', pagenationQuery);

    	}]);

    	app.service('managerAnaService',['$http',function($http){
    		this.getErpManagerAnalysis = function(postData) {
    			return $http.post(getErpManagerAnalysis_url, postData);
    		}
    		this.getQueryParam = function (postData) {
 	        	return $http.post(getQueryParam_url, postData);
 	        }
    	}]);

    </script>
</body>
</html>
