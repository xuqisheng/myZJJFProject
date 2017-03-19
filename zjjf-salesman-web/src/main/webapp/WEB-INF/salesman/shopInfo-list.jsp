<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>阿街足迹</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <%@ include file="../common/head.jsp"%>
    <script>
    	var rootPath="${root}";
    </script>
</head>
<body>
<div class="wrap-bd">
	<div class="mb-default title">客户管理</div>
	<div class="op-section clearfix">
	    <input type="hidden" id="tmpProvinceId" >
		<input type="hidden" id="tmpCityId" >
	  	<input type="hidden" id="tmpAreaId" >
	
		<form action="" id="shopForm" method="" class="fl">
			<input class="input-search-text" type="text" id="shopNo" name="shopNo" placeholder="商铺编号/名称">
			<select id="shopTypeTmp"  name="shopTypeTmp" style="width:150px;height:29px;">
                <c:forEach var="shopTypeList" items="${shopTypeList}">
              	  <option value="${shopTypeList.value}">${shopTypeList.label}</option>
                </c:forEach>
			</select>
			
			<span style="margin-left:10px;">所属区域：</span>
	            <select id="provinceId" name="provinceId" style="width:120px;height:29px;" onchange="getRegionInfo(this,'citySelect','0')">
	                <option value="">省份</option>
	                <c:forEach var="provinceList" items="${provinceList}">
	              	  <option value="${provinceList.id}">${provinceList.name}</option>
	                </c:forEach>
	            </select>
	            <select id="citySelect" name="cityId" style="width:120px;height:29px;" onchange="getRegionInfo(this,'areaSelect','0')">
	                <option value="">城市</option>
	            </select>
	            <select id="areaSelect" name="areaId" style="width:120px;height:29px;">
	                <option value="">区域</option>
	            </select>
			<input type="button" class="input-search-button" value="搜索"  id="sub"/>
			<input type="button" class="input-search-button" value="重置"  onclick="resetCondition();"/>
			<a style="margin-left: 50px;" href="${root}/shop/toAddPage.do">添加客户</a>
			<a style="margin-left: 10px;" id="btnImport">批量导入</a>
			<a style="margin-left: 10px;" id="btnSyncShop">同步店宝客户</a>
		</form>
	</div>
	<div>
		<table class="table-list table-border">
			<thead class="table-thead">
				<tr class="table-header">
					<th>商铺编号</th>
					<th>商铺名称</th>
					<th>所属区域</th>
					<th>详细地址</th>
					<th>固定电话</th>
					<th>商铺类型</th>
					<th>是否注册</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody class="table-tbody"></tbody>
		</table>
	</div>
	
</div>
<%@ include file="../common/pagination.jsp"%>
<script src="${root}/resources/vendor/salesman/js/salesman.js"></script>
<script src="${root}/resources/vendor/salesman/js/shopInfo-list.js"></script>
</body>
</html>