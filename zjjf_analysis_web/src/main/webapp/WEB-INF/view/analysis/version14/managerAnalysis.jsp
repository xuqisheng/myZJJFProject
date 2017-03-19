<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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

        <select class="select" ng-model="selectWarehouse" ng-options="v.id as v.name for v in warehouseSelect" ng-change="changeCityOrArea(1)">
                <option value="">全部仓库</option>
        </select>
        <select class="select" ng-model="selectManagerStatus" ng-options="v.code as v.name for v in managerStatusSelect" ng-change="changeCityOrArea(2)">
            <option value="">供应商状态</option>
        </select>
        <select class="select" ng-model="selectCoopWay" ng-options="v.code as v.name for v in coopWaySelect">
            <option value="">合作方式</option>
        </select>
        <input type="text" placeholder="供应商编号/供应商名称" class="input input-search-text" ng-model="idOrnameOfManager">
        <input type="text" placeholder="商品条码/商品名称" class="input input-search-text" ng-model="codeOrnameOfGoods">
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
    <div class="custom-fields">
        <div class="custom-title fl">自定义报表字段</div>
        <ul class="fields-name fl">
            <li class="active">默认</li>
            <li>全选</li>
            <li>供应商编号</li>
            <li>供应商名称</li>
            <li>合作方式</li>
        </ul>
    </div>
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
    
    <br/>
    <div class="trend-chart">
        <div class="chart-header">
            近30天订单交易额走势
        </div>
        <div class="chart-content">
            <div class="chart-div">
                <div id="supplier-chart" style="height:500px;"></div>
            </div>
        </div>
        <div class="chart-content">
            <div class="chart-div">
                <div id="goods-chart" style="height:500px;"></div>
            </div>
        </div>
    </div>

    <script src="<%=request.getContextPath() %>/resources/vendor/echarts/echarts.js"></script>
    <%--<script src="<%=request.getContextPath() %>/resources/js/echartsOption/barOption.js"></script>--%>
    <script src="<%=request.getContextPath() %>/resources/js/echartsOption/lineOption.js"></script>

    <script type="text/javascript">
		
    	var root = '<%=request.getContextPath() %>';
    	var getErpManagerAnalysis_url = root + '/api/supplier/analysis/getList.do';
    	var app = angular.module('report', ['My97Ext']);
    	
    	app.controller('ctrl',['$scope','managerAnaService',function($scope, managerAnaService){
    		
    		$scope.loadPage = function(){
    			var data = getParam();
    			managerAnaService.getErpManagerAnalysis(data).success(function(result){
    				$scope.cn_keys = result.key_cn;
    				$scope.key_dataList = result.dataList;
    				$scope.paginationConf.totalItems = result.totalCount;
    				$scope.selectWarehouse = result.warehouseSelect;
    				alert('hello');
    			});
    		}
    		
    		$scope.paginationConf = {
	            currentPage: 1,
	            itemsPerPage: 10,
    		};
    		
    		var getParam = function() {
    			var paramMap = {};
    			paramMap["pageNo"] = $scope.paginationConf.currentPage || null;
	    		paramMap["offset"] = $scope.paginationConf.itemsPerPage || null;
    			return paramMap;
    		}
    		
    		
    	}]);
    	
    	app.service('managerAnaService',['$http',function($http){
    		this.getErpManagerAnalysis = function(postData) {
    			return $http.post(getErpManagerAnalysis_url, postData);
    		}
    	}]);
    	
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
        supplierOption.xAxis[0].data = [ "6345", "434","435","434", "横岗","43","龙岗转角", "43","龙岗区","坪山", "横岗","南山转角","龙岗转角", "南山区22","龙岗区","坪山", "横岗","南山转角","龙岗转角", "南山区22","龙岗区","坪山", "横岗","南山转角","龙岗转角"]
        supplierOption.series[0].name = '毛利率',
        supplierOption.series[0].data = [300, 400, 100, 234, 290, 330, 310, 182, 100, 234, 290, 330, 310, 400, 100, 234, 290, 330, 310, 182, 100, 234, 290, 330, 310]

        goodsOption.legend.data = ['毛利率'],
        goodsOption.xAxis[0].data = [ "福田区", "南山区22","龙岗区","坪山", "横岗","南山转角","龙岗转角", "南山区22","龙岗区","坪山", "横岗","南山转角","龙岗转角", "南山区22","龙岗区","坪山", "横岗","南山转角","龙岗转角", "南山区22","龙岗区","坪山", "横岗","南山转角","龙岗转角"]
        goodsOption.series[0].name = '毛利率',
        goodsOption.series[0].data = [150, 200, 100, 234, 290, 330, 310, 182, 100, 234, 290, 330, 310, 400, 100, 234, 290, 330, 310, 182, 100, 234, 290, 330, 310]

        // 使用刚指定的配置项和数据显示图表。
        supplier.setOption(supplierOption);
        goods.setOption(goodsOption);

        supplier.hideLoading();
        goods.hideLoading();

        window.onresize =function(){
            supplier.resize();
            goods.resize();
        }
    </script>
</body>
</html>
