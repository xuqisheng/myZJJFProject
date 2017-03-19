<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>邀请配置</title>
<%@ include file="../common/head.jsp"%>
<script src="${root }/resources/js/comm.js"></script>
</head>
<body>
	<div class="wrap-bd">
		<div class="mb-default">
			<a class="crumb" href="../../keFu/systemConfig/getAllConfig.do">参数设置</a><a class="crumb crumb-active">邀请设置</a>
		</div>
		<fieldset class="wrap-bd">
			<div>
				<label class="label">手机URL：</label>
				<c:choose>
					<c:when test="${str=='E' }">
						<input class="input input-default" type="text" id="mobileUrl" value="${configShare.mobileUrl }" maxlength="500">
					</c:when>
					<c:otherwise>
						${configShare.mobileUrl }
					</c:otherwise>
				</c:choose>
			</div>
			<p>
				<label class="label">批发商URL：</label>
				<c:choose>
					<c:when test="${str=='E' }">
						<input class="input input-default" type="text" id="supplierUrl" value="${configShare.supplierUrl }" maxlength="500">
					</c:when>
					<c:otherwise>
						${configShare.supplierUrl }
					</c:otherwise>
				</c:choose>

			</p>
			<p>
				<label class="label">描述：</label>
				<c:choose>
					<c:when test="${str=='E' }">
						<input class="input input-default" type="text" id="appDescribe" value="${configShare.appDescribe }" maxlength="500">
					</c:when>
					<c:otherwise>
						${configShare.appDescribe }
					</c:otherwise>
				</c:choose>
			</p>
			<p>
				<label class="label">奖励类型：</label>
				<c:choose>
					<c:when test="${str=='E' }">
						<select class="select" id="awardType" value="${configShare.awardType }">
							<option value="0" <c:if test="${configShare.awardType==0 }">selected="selected"</c:if>>优惠券</option>
						</select>
					</c:when>
					<c:otherwise>
						优惠券
					</c:otherwise>
				</c:choose>

			</p>
			<div id="spVoucherDiv" class="condition">
				<input type="hidden" id="sendIdInput" value="245" name="sendId">
				<label class="label va-t">优惠券：</label>
				<div style="width: 800px; display: inline-block">
					<c:if test="${str=='E' }">
						<div>
							<span id="addSpVoucher" class="button button-operate">+选择优惠券</span>
						</div>
					</c:if>
					<input type="hidden" id="couponId" value="${configShare.couponId}">
					<input type="hidden" id="configId" value="${configId}">
					<input type="hidden" id="id" value="${configShare.id}">
					<table class="table-list table-border mt-small">
							<thead class="table-thead">
								<tr>
									<th width="280">名称</th>
									<th>面值</th>
									<th>有效期</th>
									<th>使用金额限制</th>
								</tr>
							</thead>
							<tbody class="table-tbody" id="aaa">
								<c:if test="${configShare.couponId != null }">
									<tr class="spVoucherTr">
										<td>${configShare.ruleName }</td>
										<td>&yen;${configShare.useMoney }</td>
										<td>${configShare.useDay }天</td>
										<td>${configShare.startPrice }</td>
									</tr>
								</c:if>
								<c:if test="${configShare.couponId == null }">
									<tr class="spVoucherTr"><td colspan="4">无数据</td></tr>
								</c:if>
							</tbody>
							
						<!-- <tbody id="confirmSpVoucherTbody" class="table-tbody"><input type="hidden" value="245"><tr><td>恭喜你有了一个优惠券</td><td>￥10</td><td>10天</td><td>1000</td></tr></tbody> -->
					</table>
				</div>
			</div>
			<p>
				<label class="label"></label>
				<c:choose>
					<c:when test="${str=='E' }">
						<input type="button" id="saveButton" value="确定" class="button button-ok">
						<input type="button" value="取消" class="button button-cancel" id="button-cancel">
					</c:when>
					<c:otherwise>
						<input type="button" value="取消" class="button button-cancel" id="button-cancel">
					</c:otherwise>
				</c:choose>
			</p>
		</fieldset>
		<div class="dialog hidden J_dialog" id="adboardDiv">
			<div class="dialog-head"></div>
			<div class="dialog-body" id="jpagination" style="overflow:hidden; float:none">
				<p>
					<input class="input input-search-text" type="text" id="ruleName" placeholder="优惠券名称" style="width: 600px">
					<input class="input input-search-button" type="button" value="搜索" id="search">
				</p>
				<table class="table-list table-border" style="width: 680px;">
					<thead class="table-thead">
						<tr>
							<th width="280">名称</th>
							<th>面值</th>
							<th>有效期</th>
							<th>使用金额限制</th>
						</tr>
					</thead>
					<tbody class="table-tbody" id="spVoucherTbody">
						<!-- <tr class="spVoucherTr">
							<td>优惠券名称名称名称名称名称名称名称名称名称</td>
							<td>&yen;10.00</td>
							<td>10天</td>
							<td>1000</td>
						</tr> -->
					</tbody>
				</table>
				 <%@ include file="../common/pagination.jsp"%>
			</div>
			<div class="dialog-foot">
				<button class="dialog-button dialog-ok">确定</button>
				<button class="dialog-button dialog-cancel">取消</button>
			</div>
		</div>
	</div>
	<div class="cover-all"></div>
	<script>
		$(function() {
			
			$('#addSpVoucher').on('click', function() {
				$('.J_dialog').fadeIn();
				$('.cover-all').fadeIn();
				$('.J_dialog .dialog-head').html("选择优惠券");
			});
			//异步加载优惠券列表
			$("#jpagination").pagination({
			    pageSize: 5,
			    remote: {
			        url: '${root}/keFu/systemConfigDetail/getAllSpVoucherTemp.do',
			        params: {ruleName:$("#ruleName").val()},
			        success: function(data) {
			            var html='';
			            $.each(data.list, function(i,item) {
			            	html+='<tr class="spVoucherTr">';
			            	html+='<input type="hidden" name="ruleId" value="'+item.id+'">';
			            	html+='<td>'+item.ruleName+'</td>';
			            	html+='<td>&yen;'+item.useMoney+'</td>';
			            	html+='<td>'+item.useDay+'</td>';
			            	html+='<td>'+item.startPrice+'</td>';
			            	html+='</tr>';
			            });
		                if(html == "") {
		                 	html = '<tr><td colspan="10" class="no-data">无数据</td></tr>';
		                }
			            $('#spVoucherTbody').html(html);
			            dialogPosCenter('.J_dialog');
			        },
			        totalName:'totalSize'
			    }
			});
			//搜索优惠券
			$("#search").on("click",function(){
				$("#jpagination").pagination('setParams', {ruleName:$("#ruleName").val()});
	      		$("#jpagination").pagination('setPageIndex', 0);
	      	 	$("#jpagination").pagination('remote');
			});
			var tr = '';
			var ruleId = '';
			//选取优惠券
			$('#spVoucherTbody').on('click', '.spVoucherTr', function() {
				$(this).siblings('.spVoucherTr').removeAttr('style');
				if ($(this).attr('style') == 'color: red') {
					$(this).removeAttr('style');
				} else {
					tr = $(this).html();
					ruleId = $(this).find('input[name="ruleId"]').val();
					$(this).attr('style', 'color: red');
				}
			});

			//确定按钮
			$('.J_dialog').on('click', '.dialog-ok', function() {
				$("#aaa").html(tr);
				$("#couponId").val(ruleId);
				$('.J_dialog').fadeOut();
				$('.cover-all').fadeOut();
			}).on('click', '.dialog-cancel', function() {
				$('.J_dialog').fadeOut();
				$('.cover-all').fadeOut();
			});

			//取消
		    $("#button-cancel").on("click",function(){
		    	location.href="${root}/keFu/systemConfig/getAllConfig.do";
		    })

			//保存
			$("#saveButton").on("click",function(){
				var id =  $.trim($("#id").val());
				var configId =  $.trim($("#configId").val());
				var mobileUrl = $.trim($("#mobileUrl").val());
				var supplierUrl = $.trim($("#supplierUrl").val());
				var appDescribe = $.trim($("#appDescribe").val());
				var awardType = $.trim($("#awardType").val());
				var couponId = $.trim($("#couponId").val());
				$.post("${root}/keFu/systemConfigDetail/updateConfigShare.do",{id:id,configId:configId,mobileUrl:mobileUrl,supplierUrl:supplierUrl,awardType:awardType,couponId:couponId,appDescribe:appDescribe},function(data){
					if(data.success){
						alert(data.message);
						location.href="${root}/keFu/systemConfig/getAllConfig.do";
					}else{
						alert(data.message);
					}
				},'json');
			})
		})
	</script>
</body>
</html>
