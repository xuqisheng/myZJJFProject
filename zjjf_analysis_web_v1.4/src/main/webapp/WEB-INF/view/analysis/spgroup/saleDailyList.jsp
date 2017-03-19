<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh" ng-app="orderTable">
<head>
    <meta charset="UTF-8">
    <title>街坊店宝</title>
    <%@ include file="../../common/head.jsp"%>
    <%@ include file="../../common/datepicker.jsp"%>
    <%@ include file="../../common/pagination.jsp"%>
</head>
<body class="wrap-bd" ng-controller="tableController" data-ng-init="loadPage()">
	<div class="mb-default">
	    <a href="" class="crumb">销售报表</a>
	    <a href="" class="crumb crumb-active">销售日报</a>
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
	</div>
	<div class="op-section clearfix">
        <div class="fl">
            查询时间: &nbsp;&nbsp;<input type="text" class="input input-date" onchange="" date-picker ng-model="addTime">
        </div>
	    <div class="fr">
	        <input type="button" value="查询" ng-click="queryOrders(1);" class="input input-search-button">
	        <input type="button" value="重置" ng-click="clearQuery();" class="input input-search-button-white">
	        <input type="button" value="导出" ng-click="excelExport();" class="input input-search-button-white">
	    </div>
	</div>
	<div class="table-contain">
	    <pre>
	    <table class="table-list">
	        <thead class="table-thead">
	 	        <tr><th colspan="{{a.titleCount}}" ng-repeat="a in parentTitle">{{a.parentTitle}}</th></tr>
		        <tr><td ng-repeat="a in cn_keys track by $index">{{a}}</td> </tr>
	        </thead>
	        <tbody class="table-tbody">
	       		<tr ng-repeat="d_row in key_dataList "><td ng-repeat="b in d_row track by $index">{{b}}</td></tr>
	        </tbody>
	    </table>
        </pre>
	</div>
    <tm-pagination conf="paginationConf"></tm-pagination>
    <div class="cover-loading" ng-show="isLoading"></div>
	<script>

	    var root = '<%=request.getContextPath() %>';

	    var tableController_url = root + '/api/sale/daily/list.do';
	    var export_url = root + '/api/sale/daily/portExcel.do';
	    var changeCity_url = root + '/api/base_core/getCityAreaGroupById.do';
	    var getQueryParam_url = root + '/api/sale/daily/getQueryParam.do';

	    var app = angular.module('orderTable', ['My97Ext', 'tm.pagination']); // 第二个参数定义了Module依赖

	    app.controller('tableController', ['$scope', 'service', function ($scope, service) {

	    	$scope.loadPage = function () {
	        	$scope.isLoading = true;
				var data = getParam();
				service.getQueryParam(data).success(function (result) {
					$scope.citySelect = result.optionList[0];
		    		$scope.areaSelect = result.optionList[1];
		    		$scope.spGroupIdSelect = result.optionList[2];
	            });
				defaultaddTime();
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
		    		$scope.isLoading = false;
	            });
	    	}

	    	$scope.excelExport = function(){
	    		var areaId = $scope.selectArea || '';
	    		var cityId = $scope.selectCity || '';
	    		var spGroupId = $scope.selectSpGroupId || '';
	    		var addTime = $scope.addTime || '';
	    		var supplierName = $scope.supplierName || '';
	    		var param = "cityId=" + cityId + "&areaId=" + areaId + "&spGroupId=" + spGroupId + "&addTime=" + addTime + "&supplierName=" + supplierName;
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

	    	var defaultaddTime = function() {
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
	    		$scope.supplierName = "";
	    		defaultaddTime();
	    	}

	    	var getParam = function() {
	    		var paramMap = {};
	    		paramMap["cityId"] = $scope.selectCity || null;
	    		paramMap["areaId"] = $scope.selectArea || null;
	    		paramMap["spGroupId"] = $scope.selectSpGroupId || null;
	    		paramMap["addTime"] = $scope.addTime || null;
	    		paramMap["supplierName"] = $scope.supplierName || null;
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

