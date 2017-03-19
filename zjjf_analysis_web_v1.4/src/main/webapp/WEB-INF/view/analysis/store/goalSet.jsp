<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="goalsetApp">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>目标设置</title>
    <%@ include file="../../common/head.jsp"%>
    <%@ include file="../../common/datepicker.jsp"%>
    <%@ include file="../../common/pagination.jsp"%>
    <script src="<%=request.getContextPath() %>/resources/vendor/jquery/jquery-1.12.3.min.js"></script>
</head>
<body class="wrap-bd" ng-controller="goalsetController" ng-init="loadPage()">
    <div class="mb-default">
    	<a href="" class="crumb">系统设置</a>
        <a href="" class="crumb">目标设置</a>
    </div>
    <div class="op-section mb-small clearfix">
        <div class="fr">
            <a class="create-user button button-white">创建目标</a>
        </div>
    </div>
    <table class="table-list table-border">
        <thead class="table-thead">
            <tr>
                <th>序号</th>
                <th>年</th>
                <th>月</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody class="table-tbody">
            <tr ng-repeat="data in dataList">
            	<td>{{$index+1}}</td>
            	<td>{{data[1]}}</td>
            	<td>{{data[2]}}</td>
                <td>
                    <a href="" ng-click="toSpGoalUrl(data[1],data[2])">销售目标设置</a>
                    <a href="" class="ml-default" ng-click="toStoreGoalUrl(data[1],data[2])">终端目标设置</a>
                </td>
            </tr>
        </tbody>
    </table>

    <%--目标设置-弹框--%>
    <div class="dialog-div">
        <div class="cover-all"></div>
        <div class="dialog dialog-center">
            <div class="dialog-head">
                <div class="dialog-head-content">
                    <p>创建目标</p>
                </div>
                <div class="dialog-close fr">
                    <img  src="${root}/resources/images/dialog-closeBg.png"/>
                </div>
            </div>
            <div class="dialog-body">
                <div class="mb-default">
                    年：<select class="select" ng-model="selectedYear" ng-options="a.code as a.name for a in yearList">
              </select>
                </div>
                <div>
                    月：<select class="select" ng-model="selectedMonth" ng-options="b.id as b.name for b in monthList">
             </select>
                </div>
            </div>
            <div class="dialog-foot">
                <input type="button" id="choose" value="确定" class="dialog-button dialog-ok" ng-click="createGoal(selectedYear,selectedMonth)"/>
                <input type="button" id="notchoose" value="取消" class="dialog-button dialog-cancel"/>
            </div>
        </div>
    </div>
    <div class="cover-loading" ng-show="isLoading"></div>
    <tm-pagination conf="paginationConf"></tm-pagination>
    <!-- 对话弹框 -->
    <script type="text/javascript">
        $(function(){
            $('body,html').on('click','.create-user',function(){
                $('.cover-all,.dialog').fadeIn();
            });
            $('body,html').on('click','.dialog-close,.dialog-cancel',function(){
                $('.cover-all,.dialog').fadeOut();
            })
        })
        
        var root = '<%=request.getContextPath() %>';
		var queryGoalSet_url = root + '/api/store/goalset/getList.do';
		var insertGoalSet_url = root + '/api/store/goalset/addGoal.do';
		var querySaleGoal_url = root + '/api/spgroup/goal/querySaleGoalSet.do';
        var app = angular.module('goalsetApp',['tm.pagination']);
        app.controller('goalsetController',['$scope','goalSetService',function($scope,goalSetService,saleGoalSetService){
        	
        	$scope.loadPage = function() {
        		$scope.isLoading = true;
        		$scope.yearList = [{code:2016,name:'2016年'},{code:2017,name:'2017年'},{code:2018,name:'2018年'},{code:2019,name:'2019年'},{code:2020,name:'2020年'}];
        		$scope.monthList = [{id:1,name:'1月'},{id:2,name:'2月'},{id:3,name:'3月'},{id:4,name:'4月'},{id:5,name:'5月'},{id:6,name:'6月'},
        		             {id:7,name:'7月'},{id:8,name:'8月'},{id:9,name:'9月'},{id:10,name:'10月'},{id:11,name:'11月'},{id:12,name:'12月'}];
        		var param = getParam();
        		goalSetService.getList(param).success(function(result){
        			$scope.dataList = result.dataList;
        			$scope.paginationConf.totalItems = result.totalCount;
        		});
        		$scope.isLoading = false;
        	}
        	
        	$scope.paginationConf = {
    	            currentPage: 1,
    	            itemsPerPage: 10,
    	    };
        	
        	var getParam = function() {
        		$scope.menuId = <%=request.getAttribute("menuId")%>
        		var param = {};
        		param["menuId"] = $scope.menuId || null;
        		param["year"] = $scope.selectedYear || null;
				param["month"] = $scope.selectedMonth || null;
				param["pageNo"] = $scope.paginationConf.currentPage || null;
	    		param["offset"] = $scope.paginationConf.itemsPerPage || null;
        		return param;
        	}
        	
        	$scope.createGoal = function(year,month) {
        		if (year != null && month != null) {
	        		var param = getParam();
	        		goalSetService.insertGoal(param).success(function(result){
	        			if(!result) {
	        				alert('目标已存在，请重新设置');
	        			}
	        		});
        		} else {
        			alert('请选择年月');
        		}
        	}
        	
        	$scope.toSpGoalUrl = function(year,month) {
        		if (month < 10) {
        			var m = '0' + month.toString();
        		} else {
        			m = month.toString();
        		}
        		var dayTime = year.toString() + m;
        		window.location='<%=request.getContextPath() %>/api/spgroup/goal/loadPage.do?menuId=38&dayTime=' + dayTime;
        	}
        	
        	$scope.toStoreGoalUrl = function(year,month) {
        		var m = '';
        		if (month < 10) {
        			m = '0' + month.toString();
        		} else {
        			m = month.toString();
        		}
        		var dayTime = year.toString() + m;
        		window.location='<%=request.getContextPath() %>/api/store/goal/loadPage.do?menuId=39&dayTime=' + dayTime;
        	}
        	
        	var pagenationQuery = function() {
	        	$scope.loadPage();
	        }
        	 
        	$scope.$watch('paginationConf.currentPage + paginationConf.itemsPerPage', pagenationQuery);
        	
        }]);
        
        app.service('goalSetService',['$http',function($http){
        	
        	this.getList = function(postData) {
        		return $http.post(queryGoalSet_url, postData);
        	}
        	
        	this.insertGoal = function(postData) {
        		return $http.post(insertGoalSet_url, postData);
        	}
        }]);
        
        
    </script>
   
</body>
</html>
