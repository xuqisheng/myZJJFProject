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
	<style type="text/css">
		ul {
			list-style: none;
			margin: 0;
			padding: 0;
		}
		
		ul p {
			margin: 0;
		}
		input[type="radio"] {
			margin-left: 0;
		}
		li {
			margin-bottom: 18px;
			height: 35px;
		}
	</style>

	<body class="wrap-bd">
		<div class="mb-default">
			<span>当前位置：</span>
			<a class="crumb" href="${root }/ERPMa/toManageList.do">供应商管理</a>
			<c:choose>
				<c:when test="${manager != null}">
					<a class="crumb" href="#">编辑供应商</a>
				</c:when>
				<c:otherwise>
					<a class="crumb" href="#">新增供应商</a>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="title mb-default">
			<c:choose>
				<c:when test="${manager != null}">
					编辑供应商
				</c:when>
				<c:otherwise>
					新增供应商
				</c:otherwise>
			</c:choose>
		</div>
		<input type="hidden" value="${manager.id }" name="id" id="id">
		<!-- 只有编辑时才有值 -->
		<h5 class="mb-default">基础资料</h5>
		<form id="form">
			<div class="wrap-bd bg table-border">
				<div class="clearfix">
					<ul style="width: 32%;" class="fl">
						<li>
							<p class="required">
								<label class="label">供应商名称：</label>
								<input type="text" name="managerName" id="managerName" value="${manager.managerName }" placeholder="请输入供应商名称" class="input" maxlength="50">
							</p>
						</li>
						<li>
							<span class="required">
	            			<label class="label">固定电话：</label>
	            			<input type="text" name="tel"  id="tel" value="${manager.tel }" placeholder="请输入固定电话" class="input">
	        			    </span>
						</li>
						<li>
							<p>
								<label class="label">职位：</label>
								<input type="text" name="job" id="job" value="${manager.job }" placeholder="" class="input" maxlength="50">
							</p>
						</li>
						<li>
							<p>
								<label class="label">仓库联系人：</label>
								<input type="text" name="whBranderName" id="whBranderName" value="${manager.whBranderName }" placeholder="" class="input" maxlength="50">
							</p>
						</li>
					</ul>
					<ul style="width: 32%;" class="fl">
						<li style="padding-top: 10px;height: 25px;">
							<label class="label">状态：</label>
							<%-- <c:choose>
								<c:when test="${manager != null }">
									<input type="radio" name="status" id="status" value="0" placeholder="" class="radio ml-default" <c:if test="${manager.status==0}">checked="checked"</c:if>> 引进中
									<input type="radio" name="status" id="status" value="1" placeholder="" class="radio ml-default" <c:if test="${manager.status==1}">checked="checked"</c:if>> 合作中
									<input type="radio" name="status" id="status" value="2" placeholder="" class="radio ml-default" <c:if test="${manager.status==2}">checked="checked"</c:if>> 停止合作
								</c:when>
								<c:otherwise>
									<input type="radio" name="status" id="status" value="0" placeholder="" class="radio ml-default" checked="checked"> 引进中
									<input type="radio" name="status" id="status" value="1" placeholder="" class="radio ml-default"> 合作中
									<input type="radio" name="status" id="status" value="2" placeholder="" class="radio ml-default"> 停止合作
								</c:otherwise>
							</c:choose> --%>
							<select name="status" class="select">
							  <option value="0" <c:if test="${manager.status==0}">selected="selected"</c:if>>引进中</option>
							  <option value="1" <c:if test="${manager.status==1}">selected="selected"</c:if>>合作中</option>
							  <option value="2" <c:if test="${manager.status==2}">selected="selected"</c:if>>停止合作</option>
							</select>
						</li>
						<li>
							<p>
								<label class="label">传真：</label>
								<input type="text" name="fax" id="fax" value="${manager.fax }" placeholder="格式0755-8位数字" class="input">&nbsp;&nbsp;

							</p>
						</li>
						<li>
							<p class="required">
								<label class="label">手机号：</label>
								<input type="text" name="mobile" id="mobile" value="${manager.mobile }" placeholder="请输入手机号" class="input">
							</p>
						</li>
						<li>
							<!-- <p class="required"> -->
								<label class="label">仓库电话：</label>
								<input type="text" name="whTel" id="mobile" value="${manager.whTel}" placeholder="格式0755-8位数字" class="input">
							<!-- </p> -->
						</li>
					</ul>
					<ul style="width: 32%;" class="fl">
						<li style="padding-top: 10px;height: 25px;">
							<label class="label">合作方式：</label>
							<%-- <c:choose>
								<c:when test="${manager != null }">
									<input type="radio" name="cooperation" id="cooperation" value="0" placeholder="" class="radio ml-default" <c:if test="${manager.cooperation==0}">checked="checked"</c:if>> 购销
									<input type="radio" name="cooperation" id="cooperation" value="1" placeholder="" class="radio ml-default" <c:if test="${manager.cooperation==1}">checked="checked"</c:if>> 平台入住
								</c:when>
								<c:otherwise>
									<input type="radio" name="cooperation" id="cooperation" value="0" placeholder="" class="radio ml-default" checked="checked"> 购销
									<input type="radio" name="cooperation" id="cooperation" value="1" placeholder="" class="radio ml-default"> 平台入驻
								</c:otherwise>
							</c:choose> --%>
							<select name="cooperation" class="select">
							  <option value="0" <c:if test="${manager.cooperation==0}">selected="selected"</c:if>>购销</option>
							  <option value="1" <c:if test="${manager.cooperation==1}">selected="selected"</c:if>>平台入驻</option>
							</select>
						</li>
						<li>
							<p class="required">
								<label class="label">联系人：</label>
								<input type="text" name="branderName" id="branderName" value="${manager.branderName }" placeholder="请输入联系人" class="input" maxlength="50">
							</p>
						</li>
						<li>
							<p>
								<label class="label">Email：</label>
								<input type="text" name="email" id="email" value="${manager.email }" placeholder="" class="input">
							</p>
						</li>
						<li>
							<p>
								<label class="label">仓库手机号：</label>
								<input type="text" name="whMobile" id="whMobile" value="${manager.whMobile }" placeholder="" class="input">
							</p>
						</li>
					</ul>
				</div>
				<p class="required">
					<label class="label">供应商地址：</label>
					<select name="provinceId" id="provinceId" class="select" style="width: 120px">
						<option value="-1">省</option>
						<%-- <c:if test="${provinceList != null }">
							<c:forEach items="${provinceList}" var="province">
								<option value="${province.id }" <c:if test="${manager.provinceId==province.id }">selected="selected"</c:if>>${province.name }</option>
						</c:forEach>
						</c:if> --%>
					</select>
					<select name="cityId" id="cityId" style="width: 120px" class="select">
						<option value="-1">市</option>
						<%-- <c:if test="${cityList != null }">
							<c:forEach items="${cityList}" var="city">
								<option value="${city.id }" <c:if test="${manager.cityId==city.id }">selected="selected"</c:if>>${city.name }</option>
						</c:forEach>
						</c:if> --%>
					</select>
					<select name="areaId" id="areaId" style="width: 120px" class="select">
						<option value="-1">区</option>
						<%-- <c:if test="${areaList != null }">
							<c:forEach items="${areaList }" var="area">
								<option value="${area.id }" <c:if test="${manager.areaId==area.id }">selected="selected"</c:if>>${area.name }</option>
						</c:forEach>
						</c:if> --%>
					</select>
					<input type="text" name="address" id="address" value="${manager.address }" placeholder="请输入详细地址" class="input input-default" maxlength="50">
				</p>

				<!--<p>
					<label class="label">仓库地址：</label>
					<input type="text" name="whAddress" id="whAddress" value="${manager.whAddress }" placeholder="" class="input" maxlength="50">
				</p>-->
				<div>
					<label class="label">备注：</label>
					<textarea name="remark" id="remark" class="textarea" style="width: 570px;" maxlength="200">${manager.remark }</textarea>
				</div>
			</div>
			<h5>财务信息</h5>
			<div class="wrap-bd bg table-border mt-small">
				<div class="clearfix">
					<ul style="width: 32%;" class="fl">
						<li>
							<p class="required">
								<label class="label">开户人：</label>
								<input type="text" name="bankUserName" id="bankUserName" value="${manager.bankUserName }" placeholder="请输入开户名称" class="input" maxlength="50">
							</p>
						</li>
						<li>
							<div class="required">
								<label class="label">税号：</label>
								<input type="text" name="taxNumber" id="taxNumber" value="${manager.taxNumber }" placeholder="请输入税号" class="input" maxlength="50">
							</div>
						</li>
					</ul>
					<ul style="width: 32%;" class="fl">
						<li>
							<p class="required">
								<label class="label">开户银行：</label>
								<input type="text" name="bankName" id="bankName" value="${manager.bankName }" placeholder="请输入开户银行" class="input" maxlength="50">
							</p>
						</li>
						<li>
							<p class="required">
								<label class="label">税率：</label>
								<input type="text" name="taxRate" id="" value="${manager.taxRate}" placeholder="" class="input">%
							</p>
						</li>
					</ul>
					<ul style="width: 32%;" class="fl">
						<li>
							<p class="required">
								<label class="label">银行账号：</label>
								<input type="text" name="bankNum" id="bankNum" value="${manager.bankNum }" placeholder="请输入银行账号" class="input" maxlength="50">
							</p>
						</li>
						<li>
							<p class="required">
								<label class="label">结款方式：</label>
								
								<select name="cleaningDayStatus" id="cleaningDayStatus">
								  <option value="0" <c:if test="${manager.cleaningDayStatus == 0}">selected</c:if>>预付</option>
								  <option value="1" <c:if test="${manager.cleaningDayStatus == 1}">selected</c:if>>到付</option>
								  <option value="2" <c:if test="${manager.cleaningDayStatus == 2}">selected</c:if>>账期</option>
								</select>
								<input type="text" name="cleaningDay" id="text_cleaningDay" value="${manager.cleaningDay}" placeholder="" class="input-search-date <c:if test="${manager.cleaningDayStatus != 2}">hidden</c:if>" maxlength="3"> <span id="day" class="<c:if test="${manager.cleaningDayStatus != 2}">hidden</c:if>">天</span>
								<%-- <c:choose>
									<c:when test="${manager != null }">
										<input type="radio" name="cleaningDay" id="cleaningDay" value="-1" placeholder="" class="radio ml-default cleaningDay" <c:if test="${manager.cleaningDay==-1}">checked="checked"</c:if>> 预付
										<input type="radio" name="cleaningDay" id="cleaningDay" value="0" placeholder="" class="radio ml-default cleaningDay" <c:if test="${manager.cleaningDay==0}">checked="checked"</c:if>> 到付
										<input type="radio" name="cleaningDay" id="cleaningDay" value="1" placeholder="" class="radio ml-default cleaningDay" <c:if test="${manager.cleaningDay > 0}">checked="checked"</c:if>> 账期
										<input type="text" name="text_cleaningDay" id="text_cleaningDay" value="<c:if test=" ${manager.cleaningDay> 0}">${manager.cleaningDay}</c:if>" placeholder="" class="input-search-date" maxlength="3"> <span id="day">天</span>
									</c:when>
									<c:otherwise>
										<input type="radio" name="cleaningDay" id="cleaningDay" value="-1" placeholder="" class="radio ml-default cleaningDay" checked="checked"> 预付
										<input type="radio" name="cleaningDay" id="cleaningDay" value="0" placeholder="" class="radio ml-default cleaningDay"> 到付
										<input type="radio" name="cleaningDay" id="cleaningDay" value="1" placeholder="" class="radio ml-default cleaningDay"> 账期
										<input type="text" name="text_cleaningDay" id="text_cleaningDay" value="" placeholder="" class="input-search-date" maxlength="3"> <span id="day">天</span>
									</c:otherwise>
								</c:choose> --%>
							</p>
						</li>
					</ul>
				</div>
			</div>
			<div class="mt-default">
				<input type="button" value="保存并设置配送批发商" class="button button-ok" id="okSave">
				<input type="button" value="取消" class="button button-cancel" id="okClose">
			</div>
		</form>
		<script>
		    var regionObj;
			$(function() {
				
				//获取所有可用省市区
				$.ajax({
    			type : "POST",
    			url : "${root}/Corner/Region/getAllEnabledRegion.do",
    			dataType:'json',
    			async : false,
    			success : function(da) {
    				if(da.success){
    					regionObj = da.message;
    					//生成省select html 
    					var html = '<option value="-1">省</option>';
    					$(regionObj[0].regionList).each(function(i,item){
    						html+='<option value="'+item.id+'">'+item.name+'</option>';
    					});
    					$('#provinceId').html(html);
    				}else{
    					layer.msg(da.message);
    				}
    			},
    			error : function(da) {
    				layer.msg('失败的请求!');
    			}
    		 });
				
				
				var provinceId = '${manager.provinceId}';
				var cityId = '${manager.cityId}';
				var areaId = '${manager.areaId}';
				if(provinceId!=null&&provinceId!=''){
                    $('#provinceId').val(provinceId);
                    //生成市html  
                    var html = '<option value="-1">市</option>';
  					$(regionObj[0].regionList).each(function(i,item){//省
  						$(item.regionList).each(function(i,item){//市
  							if(item.pId == provinceId){
  								html+='<option value="'+item.id+'">'+item.name+'</option>'
  							}
  						});
  					});
  					$('#cityId').html(html).val(cityId);
  					
  					//生成区html
  					var html = '<option value="-1">区</option>';
					$(regionObj[0].regionList).each(function(i,item){//省
						$(item.regionList).each(function(i,item){//市
							$(item.regionList).each(function(i,item){//区
							   if(item.pId == cityId){
								   html+='<option value="'+item.id+'">'+item.name+'</option>';
							   }
							});
						});
					});
					$('#areaId').html(html).val(areaId);
				}
				
				//结算方式
				$('#cleaningDayStatus').on('change',function(){
					if($('#cleaningDayStatus').val()==2){
						$('#text_cleaningDay,#day').removeClass('hidden');
					}else{
						$('#text_cleaningDay,#day').addClass('hidden');
					}
				});
				
				/* 保存 */
				$("#okSave").on("click", function() {
					var id = $("#id").val();
					//参数校验
					var managerName = $.trim($("#managerName").val());
					if(managerName == "") {
						alert("供应商名称不能为空");
						return;
					}
					var provinceId = $.trim($("#provinceId").val());
					if(provinceId == -1) {
						alert("请选择省");
						return;
					}
					var cityId = $.trim($("#cityId").val());
					if(cityId == -1) {
						alert("请选择市");
						return;
					}
					var areaId = $.trim($("#areaId").val());
					if(areaId == -1) {
						alert("请选择区");
						return;
					}
					var address = $.trim($("#address").val());
					if(address == "") {
						alert("请输入详细地址");
						return;
					}
					var tel = $.trim($("#tel").val());
					var talChick = /^0\d{2,3}-\d{5,8}$/;
					if(tel == "") {
						alert("请输入固定电话");
						return;
					} else if(!talChick.test(tel)) {
						alert("固定电话格式不正确");
						return;
					}
					var fax = $.trim($("#fax").val());
					if(fax != "") {
						if(!talChick.test(fax)) {
							alert("传真号码格式不正确");
							return;
						}
					}
					var branderName = $.trim($("#branderName").val());
					if(branderName == "") {
						alert("请输入联系人");
						return;
					}
					var mobile = $.trim($("#mobile").val());
					var mobileChick = /^1[3|4|5|7|8][0-9]\d{4,8}$/;
					if(mobile == "") {
						alert("请输入手机号");
						return;
					} else if(mobile.length < 11) {
						alert("请输入11位手机号码");
						return;
					} else if(!mobileChick.test(mobile)) {
						alert("手机号码格式有误");
						return;
					}
					var email = $.trim($("#email").val());
					var emailChick = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
					if(email != "") {
						if(!emailChick.test(email)) {
							alert("邮箱格式有误");
							return;
						}
					}
					var remark = $.trim($("#remark").val());
					var whMobile = $.trim($("#whMobile").val());
					if(whMobile != "") {
						if(whMobile.length < 11) {
							alert("请输入11位仓库手机号码");
							return;
						} else if(!mobileChick.test(whMobile)) {
							alert("仓库手机号码格式有误");
							return;
						}
					}
					var cleaningDay = $("#cleaningDayStatus").val();
					var text_cleaningDay = $.trim($("#text_cleaningDay").val());
					var dayCheck = /^[0-9]{1,3}$/;
					if(cleaningDay == 2) {
						if(text_cleaningDay == "") {
							alert("请输入账期天数");
							return;
						} else if(!dayCheck.test(text_cleaningDay)) {
							alert("天数输入有误");
							return;
						}

					}
					var bankUserName = $.trim($("#bankUserName").val());
					if(bankUserName == "") {
						alert("请输入开户名称");
						return;
					}
					var bankName = $.trim($("#bankName").val());
					if(bankName == "") {
						alert("请输入开户银行");
						return;
					}
					var bankNum = $.trim($("#bankNum").val());
					if(bankNum == "") {
						alert("请输入银行卡账号");
						return;
					}
					var taxNumber = $.trim($("#taxNumber").val());
					if(taxNumber == "") {
						alert("请输入税号");
						return;
					}

					//保存操作
					var url = "";
					var $root = $(this);
					$root.attr("disabled", "disabled");
					if(id == null || id == 'undefined' || id == "") {
						url = "${root}/ERPMa/addERPManager.do";
					} else {
						url = "${root}/ERPMa/updateERPManager.do?id=" + id;
					}
					$.post(url, $("#form").serializeArray(), function(data) {
						if(data.success) {
							layer.msg('保存供应商成功,页面即将跳转', {
								time: 1000
							}, function() {
								location.href = '${root}/ERPMa/toSetDistribute.do?id=' + data.message + '';
							});
						} else {
							layer.msg(data.message);
							$root.removeAttr("disabled");
						}
					}, 'json');
				});

				/* 取消 */
				$("#okClose").on("click", function() {
					location.href = "${root }/ERPMa/toManageList.do";
				});
				
				
				/*******************************省市区联动begin********************************/
				//选择省份
				$('#provinceId').on('change',function(){
					var pId = $('#provinceId').val();//省id
					if(pId==-1){
						layer.msg('请选择省份!',{time:1500});
						$('#cityId').html('<option value="-1">市</option>');
						return;
					};
					//生成市 下拉框
					var html = '<option value="-1">市</option>';
					$(regionObj[0].regionList).each(function(i,item){//省
						$(item.regionList).each(function(i,item){//市
							if(item.pId == pId){
								html+='<option value="'+item.id+'">'+item.name+'</option>'
							}
						});
					});
					$('#cityId').html(html);
				});
				
				//选择市
				$('#cityId').on('change',function(i,item){
					
					var pId = $('#cityId').val();//市id
					if(pId==-1){
						layer.msg('请选择市!',{time:1500});
						$('#areaId').html('<option value="-1">区</option>');
						return;
					};
					
					var html = '<option value="-1">区</option>';
					$(regionObj[0].regionList).each(function(i,item){//省
						$(item.regionList).each(function(i,item){//市
							$(item.regionList).each(function(i,item){//区
							   if(item.pId == pId){
								   html+='<option value="'+item.id+'">'+item.name+'</option>';
							   }
							});
						});
					});
					$('#areaId').html(html);
				});
				/*******************************省市区联动end********************************/
			});

			/* 省市区级联 */
			/* function cascade(str) {
				var html = '';
				var param = null;
				if(str == 'p') {
					param = {
						pId: $("#provinceId").val()
					};
					html = '<option value="">市</option>';
					$("#areaId").html('<option value="">区</option>');
				} else if(str == 'c') {
					param = {
						pId: $("#cityId").val()
					};
					html = '<option value="">区</option>';
				}
				$.post("${root }/ERPMa/cascade.do", param, function(data) {
					if(data.success) {
						$.each(data.message, function(i, item) {
							html += '<option value="' + item.id + '">' + item.name + '</option>';
						});
					} else {
						alert(data.message);
					}
					if(str == 'p') {
						$("#cityId").html(html);
					} else if(str == 'c') {
						$("#areaId").html(html);
					}
				}, 'json');
			} */
		</script>
	</body>

</html>