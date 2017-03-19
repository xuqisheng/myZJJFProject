<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>财务管理</title>
<%@ include file="../common/head.jsp"%>
</head>
<body>
<div class="wrap-bd">
	<div class="mb-default">
    <a class="crumb" href="#">订单管理</a>
    <a class="crumb crumb-active">财务管理</a>
</div>
	<div class="mb-small">
	<form action="${root }/Customer/Finance/FinanceList.do" name="orderid" method="post">
		<span>区域：</span>
		<select class="select" name="province" id="sheng" onchange="getArea(1)">
			<option value="-1">全部</option>
			<c:forEach var="sheng" items="${shengList }">
				<option <c:if test="${spOrderInfoRo.province==sheng.id }">selected="selected"</c:if> value="${sheng.id }">${sheng.name }</option>
			</c:forEach>
		</select>
		<select class="select" name="city" id="shi" onchange="getArea(2)">
			<option value="-1">全部</option>
			<c:forEach var="shi" items="${shiList }">
				<option <c:if test="${spOrderInfoRo.city==shi.id }">selected="selected"</c:if> value="${shi.id }">${shi.name }</option>
			</c:forEach>
		</select>
		<select class="select" id="area" name="areaid">
			<option value="-1">全部</option>
			<c:forEach var="area" items="${areaList }">
				<option <c:if test="${spOrderInfoRo.areaId==area.id }">selected="selected"</c:if> value="${area.id }">${area.name }</option>
			</c:forEach>
		</select>
		<input class="input input-search-text ml-default" type="text" name="spKeyword" placeholder="批发商编码/批发商名称" value="${spOrderInfoRo.spKeyword}">
		<input type="submit" class="input input-search-button ml-default" value="搜索">
	</form>
	</div>
	<table class="table-list mt-small">
		<thead>
			<tr>
				<th>区域编号</th>
				<th>批发商编码</th>
				<th>批发商名称</th>
				<th>交易金额</th>
				<th>总毛利</th>
				<th>总费用</th>
				<th>费用率</th>
				<th>未审核单数</th>
				<th>已审核单数</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody class="table-tbody">
			<c:forEach var="spOrderInfo" items="${spOrderInfos}">
				<tr>
					<td>${spOrderInfo.areaid }</td>
					<td>${spOrderInfo.supplierCode }</td>
					<td>${spOrderInfo.supplierName }</td>
					<td class="txt-warn">${spOrderInfo.spOrderPrice }</td>
					<td class="txt-warn">${spOrderInfo.spZmaoli }</td>
					<td class="txt-success">${spOrderInfo.spZfee }</td>
					<td>${spOrderInfo.zfeeRate }</td>
					<td>${spOrderInfo.noStatus }</td>
					<td>${spOrderInfo.yStatus }</td>
					<td><a href="${root }/Customer/Finance/getFinanceSpOrderInfo.do?supplierId=${spOrderInfo.supplierId}">查看</a>
					<!--   <c:if test="${!empty  service &&spOrderInfo.status==2}">
							&nbsp;|&nbsp;
							<a href="${root }/Customer/Finance/getSpOrderInfo.do?orderid=${spOrderInfo.orderid}&send_agin=1">重新派送</a>
						</c:if>-->
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:if test="${fn:length(spOrderInfos)>0}">
		<%@ include file="../common/pagination-kk.jsp"%>
	</c:if>
</div>
<script>
function getArea(num){
	var pId = 0
	if(num=='1'){
		$("#shi").html('<option value="-1">全部</option>');
		$("#area").html('<option value="-1">全部</option>');
		pId = $("#sheng").val();
		$.post("${root}/Corner/Region/getRegionByPidOrRegionLevel.do",{pId:pId},function(data){
			if(data.success){
				var html = '<option value="-1">全部</option>';
				$.each(data.message,function(i,item){
					html += '<option value="'+item.id+'">'+item.name+'</option>';
				})
				$("#shi").html(html);
			}else{
				$("#shi").html('<option value="-1">全部</option>');
			}
		},"json");
	}else if(num=='2'){
		$("#area").html('<option value="-1">全部</option>');
		pId = $("#shi").val();
		$.post("${root}/Corner/Region/getRegionByPidOrRegionLevel.do",{pId:pId},function(data){
			if(data.success){
				var html = '<option value="-1">全部</option>';
				$.each(data.message,function(i,item){
					html += '<option value="'+item.id+'">'+item.name+'</option>';
				})
				$("#area").html(html);
			}else{
				$("#area").html('<option value="-1">全部</option>');
			}
		},"json");
	}
}



	function go(status) {
		location.href = "/CornerV2/Customer/Finance/GetSpOrderInfos.do?status=" + status + "&pageIndex=" + $("#kkpager_btn_go_input").val().trim();
	};
</script>
</body>
</html>