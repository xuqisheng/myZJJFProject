<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户管理管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/common/salesman-panel.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/shop/shop/">客户管理列表</a></li>
		<li class="active"><a href="${ctx}/shop/shop/form?id=${shop.id}">客户管理<shiro:hasPermission name="shop:shop:edit">${not empty shop.shopNo?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="shop:shop:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="shop" action="${ctx}/shop/shop/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		

		<div class="form-group">
		   <div class="col-sm-7">
			   <label class="col-sm-1 control-label" for="ds_host">主机名</label>
			   <div class="col-sm-2">
			      <input class="form-control" id="ds_host" type="text" placeholder="192.168.1.161"/>
			   </div>
			   <label class="col-sm-1 control-label" for="ds_name">数据库名</label>
			   <div class="col-sm-2">
			      <input class="form-control" id="ds_name" type="text" placeholder="msh"/>
			   </div>
		   </div>
		</div>
		<div class="form-group">
		   <label class="col-sm-2 control-label" for="ds_username">用户名</label>
		   <div class="col-sm-4">
		      <input class="form-control" id="ds_username" type="text" placeholder="root"/>
		   </div>
		   <label class="col-sm-2 control-label" for="ds_password">密码</label>
		   <div class="col-sm-4">
		      <input class="form-control" id="ds_password" type="password" placeholder="123456"/>
		   </div>
		</div>

		
		
		
		
			
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">商铺ID:</label></div>
			<div class="col-sm-2">
				<form:input path="shopId" htmlEscape="false" maxlength="32" class="form-control required"/>
				<span class="help-inline" style="line-height:40px;"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">groupId:</label></div>
			<div class="col-sm-2">
				<form:input path="groupId" htmlEscape="false" maxlength="20" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">商铺编号:</label></div>
			<div class="col-sm-2">
				<form:input path="shopNo" htmlEscape="false" maxlength="32" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">商铺名称:</label></div>
			<div class="col-sm-2">
				<form:input path="shopName" htmlEscape="false" maxlength="100" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">固定电话:</label></div>
			<div class="col-sm-2">
				<form:input path="telephone" htmlEscape="false" maxlength="20" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">详细地址:</label></div>
			<div class="col-sm-2">
				<form:input path="shopAddress" htmlEscape="false" maxlength="200" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">shopLicense:</label></div>
			<div class="col-sm-2">
				<form:input path="shopLicense" htmlEscape="false" maxlength="32" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">商铺类型:</label></div>
			<div class="col-sm-2">
				<form:select path="shopType" class="input-medium ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('aj_shop_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">isMultipleShop:</label></div>
			<div class="col-sm-2">
				<form:input path="isMultipleShop" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">shopArea:</label></div>
			<div class="col-sm-2">
				<form:input path="shopArea" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">sku:</label></div>
			<div class="col-sm-2">
				<form:input path="sku" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">staffNum:</label></div>
			<div class="col-sm-2">
				<form:input path="staffNum" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">DistributionNum:</label></div>
			<div class="col-sm-2">
				<form:input path="DistributionNum" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">turnover:</label></div>
			<div class="col-sm-2">
				<form:input path="turnover" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">mainProduct:</label></div>
			<div class="col-sm-2">
				<form:input path="mainProduct" htmlEscape="false" maxlength="200" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">saleRatio:</label></div>
			<div class="col-sm-2">
				<form:input path="saleRatio" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">dcShop:</label></div>
			<div class="col-sm-2">
				<form:input path="dcShop" htmlEscape="false" maxlength="100" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">0：表示没有；1：表示有；:</label></div>
			<div class="col-sm-2">
				<form:input path="baccyLicence" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">0：表示没有；1：表示有；:</label></div>
			<div class="col-sm-2">
				<form:input path="isPos" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">0：表示没有；1：表示有；:</label></div>
			<div class="col-sm-2">
				<form:input path="isComputer" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">0：表示没有；1：表示有；:</label></div>
			<div class="col-sm-2">
				<form:input path="isWifi" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">ipay:</label></div>
			<div class="col-sm-2">
				<form:input path="ipay" htmlEscape="false" maxlength="200" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">otherPatform:</label></div>
			<div class="col-sm-2">
				<form:input path="otherPatform" htmlEscape="false" maxlength="200" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">remarks:</label></div>
			<div class="col-sm-2">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="2000" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">userId:</label></div>
			<div class="col-sm-2">
				<form:input path="userId" htmlEscape="false" maxlength="32" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">userName:</label></div>
			<div class="col-sm-2">
				<form:input path="userName" htmlEscape="false" maxlength="32" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">startShopHours:</label></div>
			<div class="col-sm-2">
				<form:input path="startShopHours" htmlEscape="false" maxlength="10" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">endShopHours:</label></div>
			<div class="col-sm-2">
				<form:input path="endShopHours" htmlEscape="false" maxlength="10" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">isDelete:</label></div>
			<div class="col-sm-2">
				<form:input path="isDelete" htmlEscape="false" maxlength="1" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">createTime:</label></div>
			<div class="col-sm-2">
				<input name="createTime" type="text" readonly="readonly" maxlength="20" class="form-control Wdate "
					value="<fmt:formatDate value="${shop.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">updateTime:</label></div>
			<div class="col-sm-2">
				<input name="updateTime" type="text" readonly="readonly" maxlength="20" class="form-control Wdate "
					value="<fmt:formatDate value="${shop.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">省份:</label></div>
			<div class="col-sm-2">
				<form:input path="province" htmlEscape="false" maxlength="100" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">城市:</label></div>
			<div class="col-sm-2">
				<form:input path="city" htmlEscape="false" maxlength="100" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">区域:</label></div>
			<div class="col-sm-2">
				<form:input path="area" htmlEscape="false" maxlength="100" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">longitude:</label></div>
			<div class="col-sm-2">
				<form:input path="longitude" htmlEscape="false" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">latitude:</label></div>
			<div class="col-sm-2">
				<form:input path="latitude" htmlEscape="false" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">salesmanId:</label></div>
			<div class="col-sm-2">
				<form:input path="salesmanId" htmlEscape="false" maxlength="32" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">salesmanName:</label></div>
			<div class="col-sm-2">
				<form:input path="salesmanName" htmlEscape="false" maxlength="50" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">picUrl:</label></div>
			<div class="col-sm-2">
				<form:input path="picUrl" htmlEscape="false" maxlength="1000" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">定格ID:</label></div>
			<div class="col-sm-2">
				<form:input path="spGroupId" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">定格名称:</label></div>
			<div class="col-sm-2">
				<form:input path="spGroupName" htmlEscape="false" maxlength="50" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">联系人:</label></div>
			<div class="col-sm-2">
				<form:input path="bossName" htmlEscape="false" maxlength="32" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">负责人电话:</label></div>
			<div class="col-sm-2">
				<form:input path="bossTel" htmlEscape="false" maxlength="15" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">注册电话:</label></div>
			<div class="col-sm-2">
				<form:input path="registerTel" htmlEscape="false" maxlength="15" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">是否注册:</label></div>
			<div class="col-sm-2">
				<form:input path="isRegister" htmlEscape="false" maxlength="1" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">标记路线:</label></div>
			<div class="col-sm-2">
				<form:input path="markLine" htmlEscape="false" maxlength="50" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">shopAdderss:</label></div>
			<div class="col-sm-2">
				<form:input path="shopAdderss" htmlEscape="false" maxlength="200" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">provinceId:</label></div>
			<div class="col-sm-2">
				<form:input path="provinceId" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">cityId:</label></div>
			<div class="col-sm-2">
				<form:input path="cityId" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div><label class="control-label" style="margin-left:15px;">areaId:</label></div>
			<div class="col-sm-2">
				<form:input path="areaId" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="shop:shop:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>