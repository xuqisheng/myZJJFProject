<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN" ng-app="user_manager">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>系统用户管理</title>
    <%@ include file="../../common/head.jsp"%>
    <%@ include file="../../common/datepicker.jsp"%>
    <%@ include file="../../common/pagination.jsp"%>
</head>
<body class="wrap-bd" ng-controller="userTableController">
<div class="mb-default">
    <a href="" class="crumb">权限设置</a>
    <a href="" class="crumb crumb-active">用户管理</a>
</div>
<div class="op-section mb-small clearfix">
    <form class="fl">
        <select class="select" ng-model="selectRole" ng-options="row_map.id as row_map.roleName for row_map in roleList">
		    <option value="">全部角色</option>
		</select>
        <input class="input input-search-text" type="text" name="" value="" placeholder="用户名" ng-model="userName">
        <input class="input input-search-button ml-default" value="搜索" type="button" ng-click="queryUsers();">
        <input type="button" value="重置查询条件" ng-click="clearQuery();" class="input input-search-button-white">
    </form>
    <div class="fr">
        <a href="<%=request.getContextPath() %>/api/authority/user/user_edit.do" class="button button-white">创建用户</a>
    </div>
</div>
<table class="table-list table-border">
    <thead class="table-thead">
    <tr>
        <th>用户名</th>
        <th>角色名</th>
        <th>创建时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody class="table-tbody">
    	<tr ng-repeat="d_row in userList">
    		<td>{{d_row.userName}}</td>
    		<td>{{d_row.roleName}}</td>
    		<td>{{d_row.createTime}}</td>
    		<td class="table-op">
    			<a ng-if="d_row.userName != 'anaAdmin'" ng-href="<%=request.getContextPath() %>/api/authority/user/user_edit.do?id={{d_row.id}}"><i class="icon-op icon-op-edit"></i> 编辑 </a>
                <a ng-if="d_row.userName != 'anaAdmin'" href="#" ng-click="deleteUser(d_row.id)" ng-confirm-click="确定要删除这个用户么?" class="ml-default"><i class="icon-op icon-op-del"></i> 删除</a>
            </td>
    	</tr>
        <tr>
            <td colspan="4" class="padding-zero">
                <tm-pagination conf="paginationConf"></tm-pagination>
            </td>
        </tr>
    </tbody>
</table>
<%--<tm-pagination conf="paginationConf"></tm-pagination>--%>
<script>

    var root = '<%=request.getContextPath() %>';
    var loadUserController_url = root + '/api/authority/user/getUserList.do';
    var deleteUserController_url = root + '/api/authority/user/deleteUser.do'
    var app = angular.module('user_manager', ['My97Ext', 'tm.pagination']); // 第二个参数定义了Module依赖

    app.config(function($httpProvider) {
        $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
        $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
        $httpProvider.defaults.transformRequest = [function(data) {
            var param = function(obj) {
                var query = '';
                var name, value, fullSubName, subName, subValue, innerObj, i;
                for (name in obj) {
                    value = obj[name];
                    if (value instanceof Array) {
                        for (i = 0; i < value.length; ++i) {
                            subValue = value[i];
                            fullSubName = name + '[' + i + ']';
                            innerObj = {};
                            innerObj[fullSubName] = subValue;
                            query += param(innerObj) + '&';
                        }
                    } else if (value instanceof Object) {
                        for (subName in value) {
                            subValue = value[subName];
                            fullSubName = name + '[' + subName + ']';
                            innerObj = {};
                            innerObj[fullSubName] = subValue;
                            query += param(innerObj) + '&';
                        }
                    } else if (value !== undefined && value !== null) {
                        query += encodeURIComponent(name) + '='
                                + encodeURIComponent(value) + '&';
                    }
                }
                return query.length ? query.substr(0, query.length - 1) : query;
            };
            return angular.isObject(data) && String(data) !== '[object File]'
                    ? param(data)
                    : data;
        }];
    });

    app.directive('ngConfirmClick', [
        function(){
            return {
                priority: 1,
                terminal: true,
                link: function (scope, element, attr) {
                    var msg = attr.ngConfirmClick || "Are you sure?";
                    var clickAction = attr.ngClick;
                    element.bind('click',function (event) {
                        if ( window.confirm(msg) ) {
                            scope.$eval(clickAction)
                        }
                    });
                }
            };
    	}]
    )

    app.controller('userTableController', ['$scope', 'loadUserService', 'deleteUserService',  function ($scope, loadUserService, deleteUserService) {

		var loadPage = function(){
			$scope.queryUsers();
    	}
		//配置分页基本参数
	    $scope.paginationConf = {
            currentPage: 1,
            itemsPerPage: 5,
            /* onChange: function(){
            	//$scope.queryOrders();
            } */
        };

		$scope.queryUsers = function(day) {

			var data = getParam();
			loadUserService.list(data).success(function (result) {
				$scope.roleList = result.roleList;
	    		$scope.userList = result.userList;
	    		$scope.paginationConf.totalItems = result.totalCount;
            });
		}
		$scope.deleteUser = function(id) {
			var data = {"id" : id};
			deleteUserService.deleteUser(data).success(function (result) {
	    		alert("删除成功！");
	    		loadPage();
            });
		}

		$scope.clearQuery = function(){

    		$scope.userName = "";
    		$scope.selectRole = "";
    		$scope.createTimeBegin = "";
    		$scope.createTimeEnd = "";
    	}


    	// 获取参数
    	var getParam = function() {
    		var paramMap = {};
    		paramMap["userName"] = $scope.userName || null;
    		paramMap["roleId"] = $scope.selectRole || null;
    		paramMap["createTimeBegin"] = $scope.createTimeBegin || null;
    		paramMap["createTimeEnd"] = $scope.createTimeEnd || null;
    		paramMap["currentPage"] = $scope.paginationConf.currentPage || null;
    		paramMap["itemsPerPage"] = $scope.paginationConf.itemsPerPage || null;

    		return paramMap;
    	}

		$scope.$watch('paginationConf.currentPage + paginationConf.itemsPerPage', loadPage);
    }]);

    app.factory('loadUserService', ['$http', function ($http) {
        var list = function (postData) {
            return $http.post(loadUserController_url, postData);
        }
        return {
            list: function (postData) {
                return list(postData);
            }
        }
    }]);

    app.factory('deleteUserService', ['$http', function ($http) {
        var deleteUser = function (postData) {
            return $http.post(deleteUserController_url, postData);
        }
        return {
        	deleteUser: function (postData) {
                return deleteUser(postData);
            }
        }
    }]);
</script>
</body>
</html>
