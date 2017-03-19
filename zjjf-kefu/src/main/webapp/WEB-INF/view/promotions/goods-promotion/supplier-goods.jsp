<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">

	<head>
		<meta charset="UTF-8">
		<title>参与批发商</title>
		<%@ include file="../../common/head.jsp"%>
		<link rel="stylesheet" type="text/css" href="${root }/resources/vendor/jquery/ztree/css/zTreeStyle/zTreeStyle.css" />
		<script src="${root }/resources/js/comm.js"></script>
		<script src="${root }/resources/vendor/jquery/ztree/js/jquery.ztree.all.js"></script>

	</head>

	<body>
		<div class="wrap-bd">
			<div class="mb-small">
				<a href="#" class="crumb">促销管理</a>
                <a href="${root }/keFu/SKUActive/getAllSKUActive.do" class="crumb">单品促销</a>
			</div>
			<div class="bg wrap-bd">
				<div class="mb-default clearfix">
					<label class="label va-t fl">
						参与批发商：
					</label>
				<form action="" method="post" id="fm">
				<div class="fl"  style="display: inline-block;width: 80%;">
				<table class="table-list mb-default " >
					<thead>
						<tr>
							<th>序号</th>
							<th>定格名称</th>
							<th>批发商名称</th>
							<th>商品条码</th>
							<th>商品名称</th>
							<th>活动价格</th>
							<th>商品限量</th>
							<th>总限量</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${plantItemList != null && plantItemList.size()>0 }">
								<c:forEach items="${plantItemList}" var="plantItem" varStatus="sta">
									<tr>
										<td>${sta.index+1 }
										    <input type="hidden" name="plantItemIds" value="${plantItem.id }"/>
										    <input type="hidden" name="brandIds" value="${plantItem.brandId}"/></td>
										<td>${plantItem.spGroupName }<input type="hidden" name="spGroupIds" value="${plantItem.spGroupId }"/></td>
										<td>${plantItem.supplierName }<input type="hidden" name="supplierIds" value="${plantItem.spId }"/></td>
										<td>${plantItem.mdseId }<input type="hidden" name="itemBaseId" value="${plantItem.itemBaseId }"/></td>
										<td>${plantItem.name }<input type="hidden" name="tagIds" value="${plantItem.tagLabelId1 }"/></td>
										<td><input type="text" class="input input-date skPri" name="activePrices" id="" value="${plantItem.SKUProPrice }" /></td>
										<td><input type="text" class="input input-date sinLim" name="buyLimitNums" value="${plantItem.SKUProLimitNum }" /></td>
										<td><input type="text" class="input input-date allLim" name="totalLimitNums" value="${plantItem.SKUProLimitNum }" /></td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="8">暂无数据</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
				</div>
				</div>
				<div class="mb-default">
					<a href="${root}/keFu/SKUActive/supplierGoodsBack.do" class="button button-ok">上一步</a>
					<input type="button" id="save" class="button button-ok ml-small" value="保存"/>
					<a href="${root }/keFu/SKUActive/getAllSKUActive.do" class="button button-cancel ml-small">取消</a>
				</div>
				</form>
			</div>
		</div>
	</body>
	<script>

		$('.allLim').on('blur',function(){
				var proLimitNum = $.trim($(this).val());
				var vala=$(this).parent().parent().find('.sinLim').val();
				if(proLimitNum.length != 0) {
					 var reg = /^\+?[0-9]\d*$/;
					var r = proLimitNum.match(reg);
					if(r == null){
						alert('限量数为不小于0的整数!');
						$(this).val('');
					}
				}
			})
		$('.sinLim').on('blur',function(){
				var proLimitNum = $.trim($(this).val());
				var vala=$(this).parent().parent().find('.allLim').val();

				if(proLimitNum.length != 0) {
					 var reg = /^\+?[0-9]\d*$/;
					var r = proLimitNum.match(reg);
					if(r == null){
						alert('限量数为不小于0的整数!');
						$(this).val('');
					}
				}
			})
		$('.skPri').on('blur',function(){
				var proLimitNum = $.trim($(this).val());
				if(proLimitNum.length != 0) {
					 var reg = /^\s*(\+|-)?((\d+([\.,]\d+)?)|([\.,]\d+))\s*$/;
					var r = proLimitNum.match(reg);
					if(r == null){
						alert('请输入大于0的数字!');
						$(this).val('');
						return;
					}
					if($(this).val()<=0){
					alert('售价必须大于0');
					$(this).val('');
					return;
				}
				}
			})
		$(function(){
			$("#save").on("click",function(){
				var plantItemIds = $("input[name='plantItemIds']");
				var brandIds = $("input[name='brandIds']");
				var spGroupIds = $("input[name='spGroupIds']");
				var supplierIds = $("input[name='plantItemIds']");
				var buyLimitNums = $("input[name='buyLimitNums']");
				var activePrices = $("input[name='activePrices']");
				var brandIds = $("input[name='brandIds']");
				var totalLimitNums = $("input[name='totalLimitNums']");

				if((plantItemIds==null || plantItemIds.lenght==0)||(spGroupIds==null || spGroupIds.length==0)||(supplierIds==null || supplierIds.length==0)){
					alert("无商品信息，请完善");
					return;
				}
				$.ajax({
					url:'${root}/keFu/SKUActive/addSKUActive.do',
					type:'post',
					data:$('#fm').serializeArray(),
					dataType:'json',
					success:function(d){
						if(d.success){
							alert(d.message);
							location.href="${root }/keFu/SKUActive/getAllSKUActive.do";
						}else{
							alert(d.message);
						}
					},
					error:function(e){

					}
				})
			})

		})
	</script>
</html>
