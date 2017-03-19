<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户管理管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/salesman/shopList.js" type="text/javascript"></script>

</head>
<body>
<div class="wrapper wrapper-content animated fadeInRight">
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/shop/shop/">客户管理列表</a></li>
		<shiro:hasPermission name="shop:shop:edit"><li><a href="${ctx}/shop/shop/form">客户管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="shop" action="${ctx}/shop/shop/" method="post"  style="margin:10px 5px 10px 5px">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="tmpCityId" type="hidden" value="${shop.cityId}"/>
		<input id="tmpAreaId" type="hidden" value="${shop.areaId}"/>
			<div class="form-inline" style="margin-top: 10px;">
				<div class="form-group">
					<label>所属区域 ：</label>
					<form:select path="provinceId" class="input-medium" onchange="getRegionInfo(this,'cityId','0','')">
						<form:option value="" label="省份"/>
						<form:options items="${provinceList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
					</form:select>
				</div>
				<div class="form-group">
					<form:select  path="cityId" style="width:120px;height:29px;" onchange="getRegionInfo(this,'areaId','0','')">
		                <form:option value="" label="城市"/>
		            </form:select>
				</div>
				<div class="form-group">
					<form:select  path="areaId" style="width:120px;height:29px;">
		                <form:option value="" label="区域"/>
		            </form:select>
				</div>
			</div>
			<div class="form-inline" style="margin-top: 10px;">
				<div class="form-group">
					<label>商铺类型 ：</label>
					<form:select path="shopType" class="input-medium">
						<form:option value="" label="请选择"/>
						<form:options items="${fns:getDictList('aj_shop_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</div>
				<div class="form-group" style="margin-left: 15px;">
					<label>是否分配：</label>
					<form:select path="isAllot" class="input-medium">
						<form:option value="ALL" label="请选择"/>
						<form:option value="N" label="未分配"/>
						<form:option value="Y" label="已分配"/>
					</form:select>
				</div>
				<div class="form-group" style="margin-left: 20px;">
					<label>注册手机号：</label>
					<input type="text" name="registerTel" id="registerTel" class="form-control input-sm" value="${shop.registerTel}" style="width:165px;"/>
				</div>
			</div>
			<div class="form-inline" style="margin-top: 10px;">
				<div class="form-group">
					<label>编号/名称：</label>
						<form:input path="shopNo" htmlEscape="false" maxlength="32" class="form-control input-sm" style="width:165px;"/>
					</div>
					<div class="form-group" style="margin-left: 20px;">
						<label>业务员：</label>
						<input type="text" name="salesmanName" id="salesmanName" class="form-control input-sm" value="${shop.salesmanName}" style="width:165px;"/>
					</div>
					<div class="form-group" style="margin-left: 36px;">
						<label>详细地址：</label>
						<input type="text" name="shopAddress" id="shopAddress" class="form-control input-sm" value="${shop.shopAddress}" style="width:165px;"/>
					</div>
				<input id="btnSubmit" class="btn btn-primary btn-sm" type="submit" value="查询" style="margin-left: 10px;"/>
				<input id="btnClear"  class="btn btn-primary btn-sm" type="button" value="重置" onclick="clearFun();"/>
				<input id="btnDistribute" class="btn btn-primary btn-sm" type="button" value="分配业务员" onclick="distributeUser();"/>
			</div>

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
				<th>业务员</th>
				<th>详细地址</th>
				<th>商铺类型</th>
				<th>所属区域</th>
				<th>是否注册</th>
				<shiro:hasPermission name="shop:shop:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="shop">
			<tr>
				<td>
					<input type="checkbox" onclick="setSelectAll();" name="chk_list" value="${shop.shopNo}">
				</td>
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
					${shop.salesmanName}
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
				<%-- <td style="background-color:${shop.isRegister eq '0'?'red':''};"> --%>
				<td>
					${fns:getDictLabel(shop.isRegister, 'aj_shop_is_register', '')}
				</td>
				<shiro:hasPermission name="shop:shop:edit"><td>
    				<a href="${ctx}/shop/shop/form?shopNo=${shop.shopNo}">修改</a>
    				<c:if test="${shop.isRegister eq '0'}">
					<a href="${ctx}/shop/shop/delete?shopNo=${shop.shopNo}" onclick="return confirmx('确认要删除该客户管理吗？', this.href)">删除</a>
					</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</div>
</body>
</html>