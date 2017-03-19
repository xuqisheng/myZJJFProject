<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setAttribute("root", request.getContextPath());%>
<%@ include file="../../common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>供应商列表</title>
    <link href="../../resources/images/favicon.png" rel="icon" type="image/x-icon" />
    <%@ include file="../../common/head.jsp"%>
</head>
<body class="wrap-bd">
	<div class="mb-default">
		<span>当前位置：</span>
		<a class="crumb" href="${root }/scms/ERPMa/getAllERPManager.do">供应商管理</a>
	    <c:choose>
			<c:when test="${manager != null}">
        		<a class="crumb" href="#">编辑供应商</a>
			</c:when>
			<c:otherwise>
        		<a class="crumb" href="#">添加供应商</a>
			</c:otherwise>
		</c:choose>
    </div>
<div class="title mb-default">
	<c:choose>
		<c:when test="${manager != null}">
			编辑供应商
		</c:when>
		<c:otherwise>
			添加供应商
		</c:otherwise>
	</c:choose>
</div>
<input type="hidden" value="${manager.id }" name="id" id="id"><!-- 只有编辑时才有值 -->
<form id="form">
    <div class="wrap-bd bg table-border">
        <b>基础信息</b>
        <p class="required">
            <label class="label">供应商名称：</label>
            <input type="text" name="managerName"  id="managerName" value="${manager.managerName }" placeholder="请输入供应商名称" class="input" maxlength="50">
        </p>
        <p class="required">
            <label class="label">联系地址：</label>
            <select name="provinceId" id="provinceId" class="select" onchange="cascade('p')">
                <option value="">省</option>
                <c:if test="${provinceList != null }">
                	<c:forEach items="${provinceList}" var="province">
                		<option value="${province.id }" <c:if test="${manager.provinceId==province.id }">selected="selected"</c:if>>${province.name }</option>
                	</c:forEach>
                </c:if>
            </select>
            <select name="cityId" id="cityId" class="select" onchange="cascade('c')">
                <option value="">市</option>
                <c:if test="${cityList != null }">
                	<c:forEach items="${cityList}" var="city">
                		<option value="${city.id }" <c:if test="${manager.cityId==city.id }">selected="selected"</c:if>>${city.name }</option>
                	</c:forEach>
                </c:if>
            </select>
            <select name="areaId" id="areaId" class="select">
                <option value="">区</option>
                <c:if test="${areaList != null }">
                	<c:forEach items="${areaList }" var="area">
                		<option value="${area.id }" <c:if test="${manager.areaId==area.id }">selected="selected"</c:if>>${area.name }</option>
                	</c:forEach>
                </c:if>
            </select>
            <input type="text" name="address"  id="address" value="${manager.address }" placeholder="请输入详细地址" class="input" maxlength="50">
        </p>
        <div>
	        <span class="required">
	            <label class="label">固定电话：</label>
	            <input type="text" name="tel"  id="tel" value="${manager.tel }" placeholder="请输入固定电话" class="input">
	        </span>
            <span class="txt-log">如:020-0000000/0755-0000000</span>
        </div>
        <p>
            <label class="label">传真：</label>
            <input type="text" name="fax"  id="fax" value="${manager.fax }" placeholder="" class="input">&nbsp;&nbsp;
            <span class="txt-log">如:020-0000000/0755-0000000</span>
        </p>
        <p class="required">
            <label class="label">联系人：</label>
            <input type="text" name="branderName"  id="branderName" value="${manager.branderName }" placeholder="请输入联系人" class="input" maxlength="50">
        </p>
        <p>
            <label class="label">职位：</label>
            <input type="text" name="job"  id="job" value="${manager.job }" placeholder="" class="input" maxlength="50">
        </p>
        <p class="required">
            <label class="label">手机号：</label>
            <input type="text" name="mobile"  id="mobile" value="${manager.mobile }" placeholder="请输入手机号" class="input">
        </p>
        <p>
            <label class="label">Email：</label>
            <input type="text" name="email"  id="email" value="${manager.email }" placeholder="" class="input">
        </p>
        <p>
            <label class="label">仓库地址：</label>
            <input type="text" name="whAddress"  id="whAddress" value="${manager.whAddress }" placeholder="" class="input" maxlength="50">
        </p>
        <p>
            <label class="label">仓库联系人：</label>
            <input type="text" name="whBranderName"  id="whBranderName" value="${manager.whBranderName }" placeholder="" class="input" maxlength="50">
        </p>
        <p>
            <label class="label">仓库手机号：</label>
            <input type="text" name="whMobile" id="whMobile" value="${manager.whMobile }" placeholder="" class="input">
        </p>
        <div>
            <label class="label">备注：</label>
            <textarea name="remark" id="remark" class="textarea" maxlength="200">${manager.remark }</textarea>
        </div>
    </div>
    <div class="wrap-bd bg table-border mt-small">
        <b>财务信息</b>
        <p class="required">
            <label class="label">结款方式：</label>
            <c:choose>
            	<c:when test="${manager != null }">
           			<input type="radio" name="cleaningDay"  id="cleaningDay" value="-1" placeholder="" class="radio ml-default cleaningDay" <c:if test="${manager.cleaningDay==-1}">checked="checked"</c:if>> 预付
		            <input type="radio" name="cleaningDay"  id="cleaningDay" value="0" placeholder="" class="radio ml-default cleaningDay" <c:if test="${manager.cleaningDay==0}">checked="checked"</c:if>> 到付
		            <input type="radio" name="cleaningDay"  id="cleaningDay" value="1" placeholder="" class="radio ml-default cleaningDay" <c:if test="${manager.cleaningDay > 0}">checked="checked"</c:if>> 账期
		            <input type="text" name="text_cleaningDay"  id="text_cleaningDay" value="<c:if test="${manager.cleaningDay > 0}">${manager.cleaningDay}</c:if>" placeholder="" class="input-search-date" maxlength="3"> <span id="day">天</span>
            	</c:when>
            	<c:otherwise>
            		<input type="radio" name="cleaningDay"  id="cleaningDay" value="-1" placeholder="" class="radio ml-default cleaningDay" checked="checked"> 预付
		            <input type="radio" name="cleaningDay"  id="cleaningDay" value="0" placeholder="" class="radio ml-default cleaningDay"> 到付
		            <input type="radio" name="cleaningDay"  id="cleaningDay" value="1" placeholder="" class="radio ml-default cleaningDay"> 账期
		            <input type="text" name="text_cleaningDay"  id="text_cleaningDay" value="" placeholder="" class="input-search-date" maxlength="3"> <span id="day">天</span>
            	</c:otherwise>
            </c:choose>
        </p>
        <p class="required">
            <label class="label">开户名称：</label>
            <input type="text" name="bankUserName"  id="bankUserName" value="${manager.bankUserName }" placeholder="请输入开户名称" class="input" maxlength="50">
        </p>
        <p class="required">
            <label class="label">开户银行：</label>
            <input type="text" name="bankName"  id="bankName" value="${manager.bankName }" placeholder="请输入开户银行" class="input" maxlength="50">
        </p>
        <p class="required">
            <label class="label">银行账号：</label>
            <input type="text" name="bankNum"  id="bankNum" value="${manager.bankNum }" placeholder="请输入银行账号" class="input" maxlength="50">
        </p>
        <div class="required">
            <label class="label">税号：</label>
            <input type="text" name="taxNumber"  id="taxNumber" value="${manager.taxNumber }" placeholder="请输入税号" class="input" maxlength="50">
        </div>
    </div>
    <div class="wrap-bd bg table-border mt-small">
        <b>供应商状态</b>
        <div>
            <label class="label">状态：</label>
            <c:choose>
            	<c:when test="${manager != null }">
           			<input type="radio" name="status"  id="status" value="0" placeholder="" class="radio ml-default" <c:if test="${manager.status==0}">checked="checked"</c:if>> 引进中
		            <input type="radio" name="status"  id="status" value="1" placeholder="" class="radio ml-default" <c:if test="${manager.status==1}">checked="checked"</c:if>> 合作中
		            <input type="radio" name="status"  id="status" value="2" placeholder="" class="radio ml-default" <c:if test="${manager.status==2}">checked="checked"</c:if>> 停止合作
            	</c:when>
            	<c:otherwise>
            		<input type="radio" name="status"  id="status" value="0" placeholder="" class="radio ml-default" checked="checked"> 引进中
		            <input type="radio" name="status"  id="status" value="1" placeholder="" class="radio ml-default"> 合作中
		            <input type="radio" name="status"  id="status" value="2" placeholder="" class="radio ml-default"> 停止合作
            	</c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="wrap-bd bg table-border mt-small">
        <b>合作方式</b>
        <div>
            <label class="label">方式：</label>
            <c:choose>
            	<c:when test="${manager != null }">
           			<input type="radio" name="cooperation"  id="cooperation" value="0" placeholder="" class="radio ml-default" <c:if test="${manager.cooperation==0}">checked="checked"</c:if>> 购销
		            <input type="radio" name="cooperation"  id="cooperation" value="1" placeholder="" class="radio ml-default" <c:if test="${manager.cooperation==1}">checked="checked"</c:if>> 平台入住
            	</c:when>
            	<c:otherwise>
            		<input type="radio" name="cooperation"  id="cooperation" value="0" placeholder="" class="radio ml-default" checked="checked"> 购销
		            <input type="radio" name="cooperation"  id="cooperation" value="1" placeholder="" class="radio ml-default"> 平台入驻
            	</c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="mt-default">
        <input type="button" value="保存" class="button-save" id="okSave">
        <input type="button" value="取消" class="button-cancel" id="okClose">
    </div>
</form>
<script>
$(function(){
	//默认隐藏
	var rcleaningDay = "${manager.cleaningDay}";
	if(rcleaningDay == null || rcleaningDay == "" || rcleaningDay<1){
		$("#text_cleaningDay").hide();
		$("#day").hide();
	}

	$(".cleaningDay").on("click",function(){
		console.log($(this).val());
		if($(this).val()==1){
			$("#text_cleaningDay").show();
			$("#day").show();
		}else{
			$("#text_cleaningDay").hide();
			$("#day").hide();
		}
	})
	/* 保存 */
	$("#okSave").on("click",function(){
		var id = $("#id").val();
		//参数校验
		var managerName = $.trim($("#managerName").val());
		if(managerName==""){
			alert("供应商名称不能为空");
			return;
		}
		var provinceId = $.trim($("#provinceId").val());
		if(provinceId==""){
			alert("请选择省");
			return;
		}
		var cityId = $.trim($("#cityId").val());
		if(cityId==""){
			alert("请选择市");
			return;
		}
		var areaId = $.trim($("#areaId").val());
		if(areaId==""){
			alert("请选择区");
			return;
		}
		var address = $.trim($("#address").val());
		if(address==""){
			alert("请输入详细地址");
			return;
		}
		var tel = $.trim($("#tel").val());
		var talChick = /^0\d{2,3}-\d{5,8}$/;
		if(tel==""){
			alert("请输入固定电话");
			return;
		}else if(!talChick.test(tel)){
			alert("固定电话格式不正确");
			return;
		}
		var fax = $.trim($("#fax").val());
		if(fax != ""){
			if(!talChick.test(fax)){
				alert("传真号码格式不正确");
				return;
			}
		}
		var branderName = $.trim($("#branderName").val());
		if(branderName == ""){
			alert("请输入联系人");
			return;
		}
		var mobile = $.trim($("#mobile").val());
		var mobileChick = /^1[3|4|5|7|8][0-9]\d{4,8}$/;
		if(mobile == ""){
			alert("请输入手机号");
			return;
		}else if(mobile.length<11){
			alert("请输入11位手机号码");
			return;
		}else if(!mobileChick.test(mobile)){
			alert("手机号码格式有误");
			return;
		}
		var email = $.trim($("#email").val());
		var emailChick =  /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if(email != ""){
			if(!emailChick.test(email)){
				alert("邮箱格式有误");
				return;
			}
		}
		var remark = $.trim($("#remark").val());
		var whMobile = $.trim($("#whMobile").val());
		if(whMobile != ""){
			if(whMobile.length<11){
				alert("请输入11位仓库手机号码");
				return;
			}else if(!mobileChick.test(whMobile)){
				alert("仓库手机号码格式有误");
				return;
			}
		}
		var cleaningDay = $("input[name=cleaningDay]:checked").val();
		var text_cleaningDay = $.trim($("#text_cleaningDay").val());
		var dayCheck = /^[0-9]{1,3}$/;
		if(cleaningDay==1){
			if(text_cleaningDay==""){
				alert("请输入账期天数");
				return;
			}else if(!dayCheck.test(text_cleaningDay)){
				alert("天数输入有误");
				return;
			}

		}
		var bankUserName = $.trim($("#bankUserName").val());
		if(bankUserName==""){
			alert("请输入开户名称");
			return;
		}
		var bankName = $.trim($("#bankName").val());
		if(bankName==""){
			alert("请输入开户银行");
			return;
		}
		var bankNum = $.trim($("#bankNum").val());
		if(bankNum==""){
			alert("请输入银行卡账号");
			return;
		}
		var taxNumber = $.trim($("#taxNumber").val());
		if(taxNumber==""){
			alert("请输入税号");
			return;
		}

		//保存操作
		var url="";
		var $root = $(this);
		$root.attr("disabled","disabled");
		if(id == null || id=='undefined' || id==""){
			url="${root}/scms/ERPMa/addERPManager.do";
		}else{
			url="${root}/scms/ERPMa/updateERPManager.do?id="+id;
		}
		$.post(url,$("#form").serializeArray(),function(data){
			if(data.success){
				alert(data.message);
				location.href="${root }/scms/ERPMa/getAllERPManager.do";
			}else{
				alert(data.message);
				$root.removeAttr("disabled");
			}
		},'json');
	});

	/* 取消 */
	$("#okClose").on("click",function(){
		location.href="${root }/scms/ERPMa/getAllERPManager.do";
	});
});



/* 省市区级联 */
	function cascade(str){
		var html = '';
		var param = null;
		if(str=='p'){
			param={pId:$("#provinceId").val()};
			html = '<option value="">市</option>';
			$("#areaId").html('<option value="">区</option>');
		}else if(str=='c'){
			param={pId:$("#cityId").val()};
			html = '<option value="">区</option>';
		}
		console.log(param)
		$.post("${root }/scms/ERPMa/cascade.do",param,function(data){
			if(data.success){
				$.each(data.message,function(i,item){
					html += '<option value="'+item.id+'">'+item.name+'</option>';
				});
			}else{
				alert(data.message);
			}
			if(str=='p'){
    			$("#cityId").html(html);
    		}else if(str=='c'){
    			$("#areaId").html(html);
    		}
		},'json');
	}

</script>
</body>
</html>
