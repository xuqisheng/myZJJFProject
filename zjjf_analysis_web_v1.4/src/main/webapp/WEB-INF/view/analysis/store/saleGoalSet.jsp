<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="goalSetApp">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>终端目标设置</title>
    <%@ include file="../../common/head.jsp"%>
    <%@ include file="../../common/datepicker.jsp"%>
</head>
<body class="wrap-bd" ng-controller="goalSetController" ng-init="loadPage()">
<div class="mb-default">
	<a href="" class="crumb">系统设置</a>
	<a ng-href="<%=request.getContextPath() %>/api/store/goalset/loadPage.do?menuId=40" class="crumb">目标设置</a>
    <a href="" class="crumb crumb-active">终端目标设置</a>
    <div ng-view></div>
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
		<input class="input input-search-button ml-default" value="搜索" type="button" ng-click="find()">
</div>
<form name="myForm">
	<table class="table-list table-border">
    <thead class="table-thead">
    <tr>
        <th>城市</th>
        <th>区域</th>
        <th>定格</th>
        <th>新增注册终端月目标</th>
        <th>活跃用户月目标</th>
        <th>时间</th>
    </tr>
    </thead>
    <tbody class="table-tbody">
    <tr ng-repeat="d_row in key_dataList">
    	<td ng-hide="true" ng-model="hideId">{{d_row[0]}}</td>
        <td>{{d_row[1]}}</td>
        <td>{{d_row[2]}}</td>
        <td>{{d_row[3]}}</td>
        <td class="padding-zero">
            <input name="in1{{$index}}" ng-pattern="/^[0-9]*$/" min=0 type="number" class="input" ng-model="regGoalInput" ng-init="regGoalInput=d_row[4]" ng-change="changeRegGoal(d_row[0],regGoalInput)">
        	<span style="display:inline-block;height:auto;width:85px">
               <span style="color:red" ng-show="myForm.in1{{$index}}.$dirty && myForm.in1{{$index}}.$invalid">请输入正整数</span>
           	</span>
        </td>
        <td class="padding-zero">
            <input name="in2{{$index}}" ng-pattern="/^[0-9]*$/" min=0 type="number" class="input" ng-model="actGoalInput" ng-init="actGoalInput=d_row[5]" ng-change="changeActGoal(d_row[0],actGoalInput)">
            <span style="display:inline-block;height:auto;width:85px">
               <span style="color:red" ng-show="myForm.in2{{$index}}.$dirty && myForm.in2{{$index}}.$invalid">请输入正整数</span>
           	</span>
        </td>
        <td>{{d_row[6]}}</td>
    </tr>
    </tbody>
</table>
<div class="mt-default">
    <button class="button button-ok" ng-click="save(myForm.$invalid)">保存</button>
    <button class="button button-cancel" onclick="window.history.go(-1)">取消</button>
</div>
</form>
<div class="cover-loading" ng-show="isLoading"></div>

<script type="text/javascript">
	var root = '<%=request.getContextPath()%>';
	var base_url = root + '/api/store/goal';
	var queryTerminalGoal_url = base_url + '/queryTerminalGoalSet.do';
	var updateTerminalGoal_url = base_url + '/updateTerminalGoalSet.do';
	
	var changeCity_url = root + '/api/base_core/getCityAreaGroupById.do';
    var getQueryParam_url = root + '/api/sp_order/getQueryParam.do';
	var app = angular.module('goalSetApp',[]);
	app.controller('goalSetController',['$scope','goalSetService','spOrderSevrices',function($scope, goalSetService, spOrderSevrices){
		
		$scope.loadPage = function() {
			$scope.isLoading = true;
			$scope.selectCity='77'
			var data = getParam();
			spOrderSevrices.getQueryParam(data).success(function (result) {
				$scope.citySelect = result.optionList[0];
	    		$scope.areaSelect = result.optionList[1];
	    		$scope.spGroupIdSelect = result.optionList[2];
            });
			goalSetService.queryTerminalGoalSet(data).success(function(result){
				$scope.key_dataList = result.dataList;
				$scope.cn_keys = result.cn_keys;
				$scope.changeCityOrArea(1);
				$scope.isLoading = false;
			});
		}
		
		$scope.changeRegGoal = function(id, goalInput) {
			
			angular.forEach($scope.key_dataList, function(bean, i){
				if(bean && bean[0] == id){
					bean[4] = goalInput;
   	    		}
   			})
		}
		
		$scope.changeActGoal = function(id, goalInput) {
			
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
		
		var getParam = function() {
			var param = {};
			param["menuId"] = <%=request.getAttribute("menuId")%>;
			var dayTime = <%=request.getAttribute("dayTime")%>;
			if (dayTime != null) {
				param["dayTime"] = dayTime || null;
			}
			param["cityId"] = $scope.selectCity || null;
			param["areaId"] = $scope.selectArea || null;
			param["spGroupId"] = $scope.selectSpGroupId || null;
			console.log(param);
			return param;
		}
		
		$scope.save = function(invalid) {
			$scope.isLoading = true;
			if (!invalid) {
				if ($scope.key_dataList != null && $scope.key_dataList != '') {
					goalSetService.updateTerminalGoalSet($scope.key_dataList).success(function(result){
						console.log("更新条数" + result.affected);
						$scope.find();
					});
				}
			}
			$scope.isLoading = false;
		}
		
		$scope.find = function() {
			$scope.isLoading = true;
			var data = getParam();
			goalSetService.queryTerminalGoalSet(data).success(function(result){
				$scope.key_dataList = result.dataList;
				$scope.isLoading = false;
			});
		}
	}]);
	
	app.service('goalSetService',['$http',function($http){
		
		this.queryTerminalGoalSet = function(postData) {
			return $http.post(queryTerminalGoal_url, postData);
		}
		
		this.updateTerminalGoalSet = function(postData) {
			return $http.post(updateTerminalGoal_url, postData);
		}
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