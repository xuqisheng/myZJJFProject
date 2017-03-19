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
    <script src="${root}/resources/js/comm.js"></script>
    <%@ include file="../common/autocomplete.jsp"%>
    <script>
    	var rootPath="${root}";
    </script>
	<script src="${root}/resources/vendor/jquery/jquery-1.12.0.min.js"></script>
	<script src="${root}/resources/vendor/salesman/js/salesman.js"></script>
	<script src="${root}/resources/vendor/salesman/js/shopInfo.js"></script>
	<script src="${root}/resources/vendor/salesman/js/shopInfo-edit.js"></script>
	<script type="text/javascript" src="${root}/resources/vendor/webuploader/js/webuploader.min.js"></script>
	<script type="text/javascript" src="${root}/resources/vendor/webuploader/js/upload.js"></script>
	<link rel="stylesheet" type="text/css" href="${root}/resources/vendor/webuploader/css/style.css"/> 
	<link rel="stylesheet" type="text/css" href="${root}/resources/vendor/webuploader/css/webuploader.css"/> 
    <style type="text/css">
    	.picUl li{float:left;margin-right:10px;}
    </style>
</head>
<body class="wrap-bd">
<div class="title mb-default" id="title">编辑客户</div>
<div class="wrap-bd" style="background: #fff">
	<div>
	  <form id="editShopForm">
	 	<input type="hidden" name="shopId" value="${shopInfo.shopId}">
	  	<input type="hidden" id="province" name="province"/>
	  	<input type="hidden" id="city" name="city"/>
	  	<input type="hidden" id="area" name="area"/>
	  	<input type="hidden" id="picUrl" name="picUrl"/>
	  	<input type="hidden" id="spGroupName" name="spGroupName" />
	  	<input type="hidden" id="isRegister" name="isRegister" value="${shopInfo.isRegister}"/>
	    <input type="hidden" id="tmpStartShopHours" value="${shopInfo.startShopHours}"/>
	  	<input type="hidden" id="tmpEndShopHours"   value="${shopInfo.endShopHours}"/>
	  	<input type="hidden" id="tmpShopType"       value="${shopInfo.shopType}"/>
	  	<input type="hidden" id="tmpLineId"         value="${shopInfo.lineId}"/>
	  
	  	<input type="hidden" id="tmpProvinceId" name="tmpProvinceId" value="${shopInfo.provinceId}"/>
	  	<input type="hidden" id="tmpCityId"     name="tmpCityId"     value="${shopInfo.cityId}">
	  	<input type="hidden" id="tmpAreaId"     name="tmpAreaId"     value="${shopInfo.areaId}">
	  	<input type="hidden" id="tmpSpGroupId"  name="tmpSpGroupId"  value="${shopInfo.spGroupId}"/>
	  	
	  	<input type="hidden" id="tmpShopName"    name="tmpShopName"     value="${shopInfo.shopName}">
	  	<input type="hidden" id="tmpBossTel"     name="tmpBossTel"      value="${shopInfo.bossTel}">
	  	<input type="hidden" id="tmpBossName"    name="tmpBossName"     value="${shopInfo.bossName}">
	  	<input type="hidden" id="tmpShopAddress" name="tmpShopAddress"  value="${shopInfo.shopAddress}">
	  	
	  <fieldset>
   		<legend>基础信息</legend>
        <p>
        	<span>
        	     <label class="label" id="codeLabel" style="width: 155px;">所属区域<span style="color: red;">*</span>：</label>
           		 <select id="provinceId" name="provinceId" value="${shopInfo.provinceId}" style="width:120px;height:29px;" onchange="getRegionInfo(this,'citySelect','0')">
	                <option value="">省份</option>
	                <c:forEach var="provinceList" items="${provinceList}">
	              	  <option value="${provinceList.id}">${provinceList.name}</option>
	                </c:forEach>
	            </select>
	            <select id="citySelect" name="cityId" value="${shopInfo.cityId}" style="width:120px;height:29px;" onchange="getRegionInfo(this,'areaSelect','0')">
	                <option value="">城市</option>
	            </select>
	            <select id="areaSelect" name="areaId" value="${shopInfo.areaId}" style="width:120px;height:29px;" onchange="getLineSpGroup(this,'spGroupSelect','0');">
	                <option value="">区域</option>
	            </select>
        	</span>
        </p>
   		
        <p>
        	<span>
        	     <label class="label" id="codeLabel" style="width: 155px;">挂靠定格/线路：</label>
           		 <select id="spGroupSelect" name="spGroupId" value="${shopInfo.spGroupId}" onchange="getLineList(this,'lineSelect','0')">
           		 	<option value="">请选择</option>
           		 </select>
           		 <span> - </span>
           		 <select id="lineSelect" name="lineId" style="height:29px;width:110px;">
           		 	<option value="">请选择</option>
           		 </select>
        	</span>
        </p>
        
        <p>
        	<span>
        	    <label class="label" id="codeLabel" style="width: 155px;">商铺类型<span style="color: red;">*</span>：</label>
				<select id="shopType" name="shopType" value="${shopInfo.shopType}" style="width:150px;height:29px;">
	                <c:forEach var="shopTypeList" items="${shopTypeList}">
	              	  <option value="${shopTypeList.value}">${shopTypeList.label}</option>
	                </c:forEach>
				</select>
        	</span>
        </p>
        <p>
        	<span>
        	     <label class="label" id="codeLabel" style="width: 155px;">商铺名称<span style="color: red;">*</span>：</label>
           		<input type="text" id="shopName" name="shopName" value="${shopInfo.shopName}" class="input-search-text">
        	</span>
        	<span>
        	    <label class="label" id="codeLabel" style="width: 155px;margin-left: 20px;">固定电话 <span style="color: red;">*</span>：</label>
           		<input type="text" id="telephone" name="telephone" value="${shopInfo.telephone}" class="input-search-text">
        	</span>
        </p>
        <p>
        	<span>
        	     <label class="label" id="codeLabel" style="width: 155px;">店铺负责人<span style="color: red;">*</span>：</label>
           		<input type="text" id="bossName" name="bossName" value="${shopInfo.bossName}" class="input-search-text">
        	</span>
        	<span>
        	    <label class="label" id="codeLabel" style="width: 155px;margin-left: 20px;">联系方式<span style="color: red;">*</span>：</label>
           		<input type="text" id="bossTel" name="bossTel" value="${shopInfo.bossTel}" class="input-search-text">
        	</span>

        </p>
        <p>
        	<span>
        	     <label class="label" id="codeLabel" style="width: 155px;">详细地址<span style="color: red;">*</span>：</label>
           		<input type="text" id="shopAddress" name="shopAddress" value="${shopInfo.shopAddress}" class="input-search-text" style="width:590px;">
        	</span>
        </p>
      </fieldset>
        
        
	  <fieldset style="margin-top:40px;">
   		<legend>详情信息</legend>
        <p>
        	<span>
        	    <label class="label" id="codeLabel" style="width: 155px;">店铺面积：</label>
           		<input type="text" id="shopArea" name="shopArea" value="${shopInfo.shopArea}" class="input-search-text" style="width:180px;"> ㎡
        	</span>
        	<span>
        	     <label class="label" id="codeLabel" style="width: 155px;margin-left: 20px;">营业时间：</label>
        	     <input type="text" id="startShopHours" name="startShopHours" value="${shopInfo.startShopHours}" class="input-search-text" style="width:70px;">
           		 <!-- <select id="startShopHours" name="startShopHours">
           		 	<option value="00:00" selected="selected">00:00</option>
           		 	<option value="01:00">01:00</option>
           		 	<option value="02:00">02:00</option>
           		 	<option value="03:00">03:00</option>
           		 	<option value="04:00">04:00</option>
           		 	<option value="05:00">05:00</option>
           		 	<option value="06:00">06:00</option>
           		 	<option value="07:00">07:00</option>
           		 	<option value="08:00">08:00</option>
           		 	<option value="09:00">09:00</option>
           		 	<option value="10:00">10:00</option>
           		 	<option value="11:00">11:00</option>
           		 	<option value="12:00">12:00</option>
           		 	<option value="13:00">13:00</option>
           		 	<option value="14:00">14:00</option>
           		 	<option value="15:00">15:00</option>
           		 	<option value="16:00">16:00</option>
           		 	<option value="17:00">17:00</option>
           		 	<option value="18:00">18:00</option>
           		 	<option value="19:00">19:00</option>
           		 	<option value="20:00">20:00</option>
           		 	<option value="21:00">21:00</option>
           		 	<option value="22:00">22:00</option>
           		 	<option value="23:00">23:00</option>
           		 	<option value="24:00">24:00</option>
           		 </select> -->
           		 <span> - </span>
           		 <input type="text" id="endShopHours" name="endShopHours" value="${shopInfo.endShopHours}" class="input-search-text" style="width:70px;">
           		 
           		 <!-- <select id="endShopHours" name="endShopHours">
           		 	<option value="00:00">00:00</option>
           		 	<option value="01:00">01:00</option>
           		 	<option value="02:00">02:00</option>
           		 	<option value="03:00">03:00</option>
           		 	<option value="04:00">04:00</option>
           		 	<option value="05:00">05:00</option>
           		 	<option value="06:00">06:00</option>
           		 	<option value="07:00">07:00</option>
           		 	<option value="08:00">08:00</option>
           		 	<option value="09:00">09:00</option>
           		 	<option value="10:00">10:00</option>
           		 	<option value="11:00">11:00</option>
           		 	<option value="12:00">12:00</option>
           		 	<option value="13:00">13:00</option>
           		 	<option value="14:00">14:00</option>
           		 	<option value="15:00">15:00</option>
           		 	<option value="16:00">16:00</option>
           		 	<option value="17:00">17:00</option>
           		 	<option value="18:00">18:00</option>
           		 	<option value="19:00">19:00</option>
           		 	<option value="20:00">20:00</option>
           		 	<option value="21:00">21:00</option>
           		 	<option value="22:00">22:00</option>
           		 	<option value="23:00">23:00</option>
           		 	<option value="24:00" selected="selected">24:00</option>
           		 </select> -->
        	</span>
        	<span>
        	     <label class="label" id="codeLabel" style="width: 155px;margin-left: 20px;">人员数量：</label>
           		<input type="text" id="staffNum" name="staffNum" value="${shopInfo.staffNum}" class="input-search-text">
        	</span>
        </p>
        <p>
        	<span>
        	    <label class="label" id="codeLabel" style="width: 155px;">配送员数量 ：</label>
           		<input type="text" id="distributionNum" name="distributionNum" value="${shopInfo.distributionNum}" class="input-search-text">
        	</span>
        	<span>
        	     <label class="label" id="codeLabel" style="width: 155px;margin-left: 20px;">配送合作商：</label>
           		<input type="text" id="dcShop" name="dcShop" value="${shopInfo.dcShop}" class="input-search-text">
        	</span>
        	<span>
        	     <label class="label" id="codeLabel" style="width: 155px;margin-left: 20px;">主营商品：</label>
            	 <!--<input type="text" id="mainProduct" name="mainProduct" class="input-search-text"> -->
           		 <input type="checkbox" name="mainProduct" value="酒水" class="ml-default" <c:if test="${fn:contains(shopInfo.mainProduct, '酒水')}">checked="checked"</c:if>> 酒水
           		 <input type="checkbox" name="mainProduct" value="烟"   class="ml-default" <c:if test="${fn:contains(shopInfo.mainProduct, '烟')}">checked="checked"</c:if>> 烟
           		 <input type="checkbox" name="mainProduct" value="饮料" class="ml-default" <c:if test="${fn:contains(shopInfo.mainProduct, '饮料')}">checked="checked"</c:if>> 饮料
           		 <input type="checkbox" name="mainProduct" value="零食" class="ml-default" <c:if test="${fn:contains(shopInfo.mainProduct, '零食')}">checked="checked"</c:if>> 零食
           		 <input type="checkbox" name="mainProduct" value="文具" class="ml-default" <c:if test="${fn:contains(shopInfo.mainProduct, '文具')}">checked="checked"</c:if>> 文具
        	</span>
        </p>
        
        <p>
        	<span>
        	    <label class="label" id="codeLabel" style="width: 155px;">主营商品占比 ：</label>
           		<input type="text" id="saleRatio" name="saleRatio" value="${shopInfo.saleRatio}" class="input-search-text" >
        	</span>
        	<span>
        	     <label class="label" id="codeLabel" style="width: 155px;margin-left: 20px;">SKU数：</label>
           		<input type="text" id="sku" name="sku" value="${shopInfo.sku}" class="input-search-text">
        	</span>
        	<span>
        	     <label class="label" id="codeLabel" style="width: 155px;margin-left: 20px;">日均营业额：</label>
           		<input type="text" id="turnover" name="turnover" value="${shopInfo.turnover}" class="input-search-text">
        	</span>
        </p>
        
        <p>
        	<span>
        	    <label class="label" id="codeLabel" style="width: 155px;">烟草许可证 ：</label>
<%--            		 <input type="radio" name="baccyLicence" value="0" class="ml-default" checked="${shopInfo.baccyLicence == 0?checked:''}">无  --%>
           		 <input type="radio" name="baccyLicence" value="0" class="ml-default" <c:if test="${shopInfo.baccyLicence eq '0'}">checked="checked"</c:if> >无
           		 <input type="radio" name="baccyLicence" value="1" class="ml-default" <c:if test="${shopInfo.baccyLicence eq '1'}">checked="checked"</c:if> >有
        	</span>
        	<span>
        	     <label class="label" id="codeLabel" style="width: 155px;margin-left: 50px;">是否连锁：</label>
           		 <input type="radio" name="isMultipleShop" value="0" class="ml-default" <c:if test="${shopInfo.isMultipleShop eq '0'}">checked="checked"</c:if>>无 
           		 <input type="radio" name="isMultipleShop" value="1" class="ml-default" <c:if test="${shopInfo.isMultipleShop eq '1'}">checked="checked"</c:if>>有
        	</span>
        	<span>
        	     <label class="label" id="codeLabel" style="width: 155px;margin-left: 50px;">有无POS机：</label>
           		 <input type="radio" name="isPos" value="0" class="ml-default" <c:if test="${shopInfo.isPos eq '0'}">checked="checked"</c:if>>无 
           		 <input type="radio" name="isPos" value="1" class="ml-default" <c:if test="${shopInfo.isPos eq '1'}">checked="checked"</c:if>>有
        	</span>
        </p>
        
        
        <p>
        	<span>
        	    <label class="label" id="codeLabel" style="width: 155px;">有无电脑 ：</label>
           		<input type="radio" name="isComputer" value="0" class="ml-default" <c:if test="${shopInfo.isComputer eq '0'}">checked="checked"</c:if>>无 
           		<input type="radio" name="isComputer" value="1" class="ml-default" <c:if test="${shopInfo.isComputer eq '1'}">checked="checked"</c:if>>有
        	</span>
        	<span>
        	     <label class="label" id="codeLabel" style="width: 155px;margin-left: 50px;">有无WIFI：</label>
           		 <input type="radio" name="isWifi" value="0" class="ml-default" <c:if test="${shopInfo.isWifi eq '0'}">checked="checked"</c:if>>无 
           		 <input type="radio" name="isWifi" value="1" class="ml-default" <c:if test="${shopInfo.isWifi eq '1'}">checked="checked"</c:if>>有
        	</span>
        	<span>
        	     <label class="label" id="codeLabel" style="width: 155px;margin-left: 50px;">其它合作平台：</label>
           		 <input type="radio" name="otherPatform" value="0" class="ml-default" <c:if test="${shopInfo.otherPatform eq '0'}">checked="checked"</c:if>>无 
           		 <input type="radio" name="otherPatform" value="1" class="ml-default" <c:if test="${shopInfo.otherPatform eq '1'}">checked="checked"</c:if>>有
        	</span>
        </p>
        <p>
        	<span>
        	    <label class="label" id="codeLabel" style="width: 155px;">支付平台：</label>
           		 <input type="checkbox" name="ipay" value="微信"   class="ml-default" <c:if test="${fn:contains(shopInfo.ipay, '微信')}">checked="checked"</c:if>>微信 
           		 <input type="checkbox" name="ipay" value="支付宝" class="ml-default" <c:if test="${fn:contains(shopInfo.ipay, '支付宝')}">checked="checked"</c:if>>支付宝
           		 <input type="checkbox" name="ipay" value="银联"   class="ml-default" <c:if test="${fn:contains(shopInfo.ipay, '银联')}">checked="checked"</c:if>>银联
        	</span>
        </p>
        <p>
            <label class="label" style="width: 155px;">备注：</label>
			<textarea rows="5" cols="125" id="remarks" name="remarks" value="${shopInfo.remarks}">${shopInfo.remarks}</textarea>
        </p>
        </fieldset>
	  <fieldset style="margin-top:40px;">
   		<legend>店铺图片</legend>
   		<div class="webuploader-pick" onclick="uploadPicFunc();">点击选择图片</div>
   		<div>
   			<ul class="picUl">
   				<c:forEach var="picList" items="${shopInfo.picList}">
   					<li>
   						<img src="${picList}" width="185px;" height="135px;"/>
   						</br>
   						<a onclick="delPic(this);">删除</a>
   					</li>
   				</c:forEach>
   			</ul>
   		</div>
   	  </fieldset>

	   </form>  		
	</div>
	<div id="submitDiv" style="margin-top: 20px;">
		<button class="button-save" id="submit">确定</button>
		<button class="button-cancel ml-default" id="cancelButton" onclick="history.go(-1)">取消</button>
	</div>
</div>

</body>
</html>