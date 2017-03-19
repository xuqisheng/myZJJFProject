<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="reportTable">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>销售目标设置</title>
	<%@ include file="../../common/head.jsp"%>
    <%@ include file="../../common/datepicker.jsp"%>
    <%@ include file="../../common/pagination.jsp"%>
</head>
<body class="wrap-bd" ng-controller="tableController" data-ng-init="loadPage()">
	<div class="mb-default">
		<a href="" class="crumb">系统设置</a>
		<a ng-href="<%=request.getContextPath() %>/api/store/goalset/loadPage.do?menuId=40" class="crumb">目标设置</a>
		<a href="" class="crumb crumb-active">销售目标设置</a>
	</div>

	<div class="op-section">
		<select class="select" ng-model="selectCity" ng-options="v.code as v.name for v in citySelect" ng-change="changeCityOrArea(1)">
		    <option value="">全部城市</option>
		</select>
        <select class="select" ng-model="selectArea" ng-options="v.code as v.name for v in areaSelect" ng-change="changeCityOrArea(2)">
        	<option value="">全部区域</option>
		</select>
		<input type="text" placeholder="请输入合作商名称" class="input input-search-text" ng-model="supplierName"> 
		<input class="input input-search-button ml-default" value="搜索" type="button" ng-click="find()">
	</div>
	
	<form name="myForm">
		<table class="table-list table-border">
		<thead class="table-thead">
			<tr>
				<th ng-repeat="t in parentTitle" colspan="{{t.titleCount}}">{{a.parentTitle}}</th>
			</tr>
			<tr>
				<th>城市</th>
	            <th>区域</th>
	            <th>合作商</th>
	            <th>定格</th>
	            <th>月GMV目标</th>
	            <th>时间</th>
				<!-- <td ng-repeat="keys in cn_keys">{{keys}}</td> -->
			</tr>
		</thead>
		<tbody class="table-tbody">
			<tr ng-repeat="d_row in key_dataList">
				<td ng-hide="true" ng-model="hideId">{{d_row[0]}}</td>
				<td>{{d_row[1]}}</td>
				<td>{{d_row[2]}}</td>
				<td>{{d_row[3]}}</td>
				<td>{{d_row[4]}}</td>
				<td class="padding-zero">
                	<input name="in{{$index}}" ng-pattern="/^[0-9]*$/" min="0" type="number" class="input" ng-model="goalInput" ng-init="goalInput=d_row[5]" ng-change="changedGoal(d_row[0],goalInput)">
                	<span style="display:inline-block;height:auto;width:85px">
		                <span style="color:red" ng-show="myForm.in{{$index}}.$dirty && myForm.in{{$index}}.$invalid">请输入正整数</span>
                	</span>
				</td>
				<td>{{d_row[6]}}</td>
			</tr>
		</tbody>
	</table>
	<div class="mt-default">
        <button ng-click="save(myForm.$invalid)" class="button button-ok">保存</button>
        <button class="button button-cancel" onclick="window.history.go(-1)">取消</button>
    </div>
	</form>
	
    <div class="cover-loading" ng-show="isLoading"></div>
	<script>
		var root = '<%=request.getContextPath() %>';
		var report_base_url = root + '/api/spgroup/goal';
		var changeCity_url = root + '/api/base_core/getCityAreaGroupById.do';
	    var getQueryParam_url = root + '/api/sp_order/getQueryParam.do';
	    
		var querySaleGoal_url = report_base_url + '/querySaleGoalSet.do';
		var updateSaleGoal_url = report_base_url + '/updateSaleGoalSet.do';
		var app = angular.module('reportTable', ['My97Ext']);
		
		app.controller('tableController',['$scope','saleGoalSetService','spOrderSevrices',function($scope, saleGoalSetService, spOrderSevrices){
			$scope.loadPage = function() {
				$scope.isLoading = true;
				$scope.selectCity = '77';
				$scope.invalid = false;
				var data = getParam();
				spOrderSevrices.getQueryParam(data).success(function (result) {
					$scope.citySelect = result.optionList[0];
		    		$scope.areaSelect = result.optionList[1];
	            });
				saleGoalSetService.querySaleGoalSet(data).success(function(result){
					$scope.key_dataList = result.dataList;
					$scope.changeCityOrArea(1);
					$scope.isLoading = false;
				});
			}
			
			$scope.changedGoal = function(id, goalInput) {
				
				angular.forEach($scope.key_dataList, function(bean, i){
					if(bean && bean[0] == id){
						bean[5] = goalInput;
	   	    		}
	   			})
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
			
			$scope.save = function(invalid) {
				$scope.isLoading = true;
				if (invalid) {
				} else {
					if ($scope.key_dataList != null && $scope.key_dataList != '') {
						saleGoalSetService.updateSaleGoalSet($scope.key_dataList).success(function(result){
							console.log("更新条数:" + result.affected);
							$scope.find();
						});
					}
				}
				$scope.isLoading = false;
			}
		
			var getParam = function() {
				var param = {};
				param["menuId"] = <%=request.getAttribute("menuId")%>;
				var dayTime = <%=request.getAttribute("dayTime")%>;
				if (dayTime != null) {
					param["dayTime"] = dayTime || null;
				}
				param["cityId"] = $scope.selectCity || null;
				param["areaId"] = $scope.selectArea || null;
				param["supplierName"] = $scope.supplierName || null; 
				return param;			
			}
			
			$scope.find = function() {
				$scope.isLoading = true;
				var data = getParam();
				saleGoalSetService.querySaleGoalSet(data).success(function(result){
					$scope.key_dataList = result.dataList;
					$scope.isLoading = false;
				});
			}
			
		}]);
		
		app.service('saleGoalSetService',['$http',function($http){
			
			this.querySaleGoalSet = function(postData) {
				return $http.post(querySaleGoal_url, postData);
			}
			
			this.updateSaleGoalSet = function(postData) {
				return $http.post(updateSaleGoal_url, postData);
			}
		}]);
		
		app.service('spOrderSevrices', ['$http', function ($http) {
	    	
	        this.changeCity = function (postData) {
	        	return $http.post(changeCity_url, postData);
	        }
	        this.getQueryParam = function (postData) {
	        	return $http.post(getQueryParam_url, postData);
	        }
	    }]);
		
	</script>
</body>
</html>
