<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh" ng-app="managerPurchase">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>采购报表</title>
	<%@ include file="../../common/head.jsp"%>
    <%@ include file="../../common/datepicker.jsp"%>
    <%@ include file="../../common/pagination.jsp"%>
    <link rel="stylesheet" href="${root}/resources/css/purchase.css">
</head>
<body class="wrap-bd" ng-controller="tableController" data-ng-init="loadPage()">
    <div class="mb-default">
        <a href="" class="crumb">采购</a>
        <a href="" class="crumb crumb-active">采购报表</a>
    </div>
    <div class="op-section">

        <select class="select" ng-model="selectWhId" ng-options="v.code as v.name for v in whIdList">
                <option value="">全部仓库</option>
        </select>
        <input type="text" placeholder="采购单号/供应商名称" class="input input-search-text" ng-model="orderNoOrManagerName">
        <input type="text" placeholder="商品条码/商品名称" class="input input-search-text" ng-model="itemNameOrItemCode">
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
    <div class="purchase-total">
        <div>
            <div class="h2">{{dailyMummary.quantity}}</div>
            <div>当期采购总量</div>
        </div>
        <div>
            <div class="h2">{{dailyMummary.operateQuantity}}</div>
            <div>当期入库总量</div>
        </div>
        <div>
            <div class="h2">{{dailyMummary.areaPrice}}</div>
            <div>当期采购总成本</div>
        </div>
    </div>
<!--     <div class="custom-fields">
        <div class="custom-title fl">自定义报表字段</div>
        <ul class="fields-name fl">
            <li class="active">默认</li>
            <li>全选</li>
            <li>供应商编号</li>
            <li>供应商名称</li>
            <li>合作方式</li>
        </ul>
    </div> -->
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
    <br/>
 <!--    <div class="trend-chart">
        <div class="chart-header">
            近30天移动平均价走势
        </div>
        <div class="chart-content">
            <div class="chart-div">
                <div id="supplierPurchase-chart" style="height:400px;"></div>
            </div>
        </div>
    </div> -->
    <div class="cover-loading" ng-show="isLoading"></div>
    <script src="<%=request.getContextPath() %>/resources/vendor/echarts/echarts.js"></script>
    <%--<script src="<%=request.getContextPath() %>/resources/vendor/echarts/lineOption.js"></script>--%>
    <script src="<%=request.getContextPath() %>/resources/js/echartsOption/lineOption.js"></script>
    <script type="text/javascript">

	    var root = '<%=request.getContextPath() %>';
		var getErpManagerPurchase_url = root + '/api/supplier/purchase/getList.do';
	    var getQueryParam_url = root + '/api/supplier/purchase/getQueryParam.do';
	    var export_url = root + '/api/supplier/purchase/portExcel.do';
		var app = angular.module('managerPurchase', ['My97Ext', 'tm.pagination']); // 第二个参数定义了Module依赖
		
		app.controller('tableController', ['$scope','managerAnaService',function($scope, managerAnaService){
    		
    		$scope.loadPage = function(){
    			managerAnaService.getQueryParam({}).success(function (result) {
					$scope.whIdList = result.optionList[0];
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
		    		$scope.dailyMummary = result.dailyMummary;
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

    		$scope.excelExport = function(){
	    		var dayTimeBegin = $scope.dayTimeBegin || '';
	    		var dayTimeEnd = $scope.dayTimeEnd || '';
	    		var whId  = $scope.selectWhId || '';
	    		var orderNoOrManagerName = $scope.orderNoOrManagerName || '';
	    		var itemNameOrItemCode = $scope.itemNameOrItemCode || '';
	    
	    		var param = "dayTimeBegin=" + dayTimeBegin + "&dayTimeEnd=" + dayTimeEnd + "&whId=" + whId + "&orderNoOrManagerName=" + orderNoOrManagerName
    				+ "&itemNameOrItemCode=" + itemNameOrItemCode;
	    		console.log(param);
	    		location.href = export_url + "?" + param;
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
	    		paramMap["orderNoOrManagerName"] = $scope.orderNoOrManagerName || null;
	    		paramMap["itemNameOrItemCode"] = $scope.itemNameOrItemCode || null;
    			paramMap["pageNo"] = $scope.paginationConf.currentPage || null;
	    		paramMap["offset"] = $scope.paginationConf.itemsPerPage || null;
    			return paramMap;
    		}
    		
    		$scope.clearQuery = function(){
    			
	    		$scope.itemNameOrItemCode = "";
	    		$scope.orderNoOrManagerName = "";
	    		$scope.selectWhId = "";
	    		$scope.dayTimeBegin = "";
	    		$scope.dayTimeEnd = "";
                $scope.isYestertay = false;
                $scope.isWeek = false;
                $scope.isMonth = true;
                $scope.checkTab(-1);
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
    			return $http.post(getErpManagerPurchase_url, postData);
    		}
	        this.getQueryParam = function (postData) {
	        	return $http.post(getQueryParam_url, postData);
	        }
    	}]);
	
        // 基于准备好的dom，初始化echarts实例
      /*   var myChart = echarts.init(document.getElementById('supplierPurchase-chart'));
        myChart.showLoading();

        var option = optionOne;
        option.legend.data = ['移动平均价'],
        option.xAxis[0].data = [ "福田区22", "南山区22","龙岗区","坪山", "横岗","南山转角","龙岗转角", "南山区22","龙岗区","坪山", "横岗","南山转角","龙岗转角", "南山区22","龙岗区","坪山", "横岗","南山转角","龙岗转角", "南山区22","龙岗区","坪山", "横岗","南山转角","龙岗转角"],
        option.series[0].name = '移动平均价',
        option.series[0].data = [220, 182, 352, 234, 456, 265, 310, 182, 265, 123, 290, 330, 310, 182, 352, 234, 456, 265, 310, 182, 265, 123, 290, 330, 310]

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        myChart.hideLoading();
        window.onresize =myChart.resize; */
    </script>
</body>
</html>
