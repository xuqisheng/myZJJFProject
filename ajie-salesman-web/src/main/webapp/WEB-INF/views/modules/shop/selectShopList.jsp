<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户管理管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/salesman/selectShopList.js" type="text/javascript"></script>
</head>
<body>
<div class="wrapper wrapper-content animated fadeInRight">
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/shop/shop/getMyShopList">客户管理列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="shop" action="${ctx}/shop/shop/getMyShopList" method="post" class="form-inline" style="margin:10px 5px 10px 5px">
		<input id="divId" name="divId" type="hidden" value="${divId}">
		<input name="salesmanId" type="hidden" value="${shop.salesmanId}"/>
		<input name="shopIds" type="hidden" value="${shop.shopIds}"/>
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<div class="form-group">
			<label>商铺编号/名称：</label>
				<form:input path="shopNo" htmlEscape="false" maxlength="32" class="form-control" value="${shop.shopNo}"/>
			</div>
			<div class="form-group">
			<label>注册手机号：</label>
				<input type="text" name="registerTel" id="registerTel" class="form-control input-sm" value="${shop.registerTel}" style="width:165px;"/>
			</div>
			<div class="form-group">
				<label>详细地址：</label>
				<input type="text" name="shopAddress" id="shopAddress" class="form-control input-sm" value="${shop.shopAddress}" style="width:165px;"/>
			</div>
			<div class="form-group">
			<label>商铺类型：</label>
				<form:select path="shopType" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('aj_shop_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		<input id="btnSelCust" class="btn btn-primary" type="button" onclick="selectShop();" value="添加选中客户"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th><input type="checkbox" id="chk_all" name="chk_all"></th>
				<th>商铺编号</th>
				<th>商铺名称</th>
				<th>注册手机号</th>
				<th>固定电话</th>
				<th>详细地址</th>
				<th>商铺类型</th>
				<th>所属区域</th>
				<th>是否注册</th>
			</tr>
		</thead>
		<tbody class="table-tbody">
		<c:forEach items="${page.list}" var="shop">
			<tr>
				<td><input type="checkbox" onclick="setSelectAll();" name="chk_list" value="${shop.shopNo}"></td>
				<td><a href="${ctx}/shop/shop/form?shopNo=${shop.shopNo}">
					${shop.shopNo}
				</a></td>
				<td>
					${shop.shopName}
				</td>
				<td>
					${shop.registerTel}
				</td>
				<td>
					${shop.telephone}
				</td>
				<td>
					${shop.shopAddress}
				</td>
				<td>
					${fns:getDictLabel(shop.shopType, 'aj_shop_type', '')}
				</td>
				<td>
					${shop.province}
					${shop.city}
					${shop.area}
				</td>
				<td>
					${fns:getDictLabel(shop.isRegister, 'aj_shop_is_register', '')}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</div>
</body>
</html>