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
<script src="${root }/resources/vendor/My97DatePicker/WdatePicker.js"></script>
<script src="${root }/resources/js/comm.js"></script>
</head>
<body>
<div class="wrap-bd">
	<div class="mb-small">
	    <a href="${root}/Customer/Finance/FinanceList.do?s=1" class="crumb">财务管理</a><a class="crumb crumb-active">批发商订单</a>
	</div>
	<div>
		<form action="${root }/Customer/Finance/getFinanceSpOrderInfo.do" name="orderid" id="frm" method="post">
			状态：
			<select name="kfStatus" class="select mr-default">
				<option value="">全部</option>
				<option	<c:if test="${spOrderInfoRo.kfStatus == 1}">selected="selected"</c:if> value="1">未审核</option>
				<option	<c:if test="${spOrderInfoRo.kfStatus == 2}">selected="selected"</c:if> value="2">已审核</option>
			</select>
			送达时间：
			<input id="starDate" class="input input-default" type="text" name="beginTime" value="<fmt:formatDate value='${spOrderInfoRo.beginTime}' type="both"/>"> 至 
			<input id="endDate" class="input input-default" type="text"  name="endTime" value="<fmt:formatDate value='${spOrderInfoRo.endTime}' type="both"/>">
            <input type="hidden" value="${spOrderInfoRo.supplierId}" name="supplierId"> 
            <input type="hidden" value="${spOrderInfoRo.supplierCode}" name="supplierCode"> 
            <input class="input input-search-text ml-default" type="text" name="spKeyword" placeholder="便利店名称/订单编号" value="${spOrderInfoRo.spKeyword}"> 
			<input type="submit" class="input input-search-button ml-default" value="搜索">
		</form>
	</div>
	<table class="table-list mt-small">
		<thead>
			<tr>
				<th><input type="checkbox" value="${spOrderInfo.orderid }" id="selAll" onclick='checkAll()'></th>
				<th>批发商名称</th>
				<th>小店名称</th>
				<th>订单编号</th>
				<th>收货时间</th>
				<th>订单交易金额</th>
				<th>订单总毛利</th>
				<th>订单总费用</th>
				<th>订单费用率</th>
				<th>审核状态</th>
				<th>结算状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="spOrderInfo" items="${spOrderInfos}">
				<tr>
					<td width="15px"><input type="checkbox"
						<c:if test="${spOrderInfo.kfStatus == 2}">disabled="disabled" </c:if>
						value="${spOrderInfo.spOrderInfoId}" name="ches"></td>
					<td>${spOrderInfo.supplierName }</td>
					<td>${spOrderInfo.storeName }</td>
					<td>${spOrderInfo.spOrderId }</td>
					<td><fmt:formatDate value='${spOrderInfo.ackTime}' type="both"/></td>
					<td>${spOrderInfo.spOrderPrice }</td>
					<td>${spOrderInfo.spZmaoli }</td>
					<td>${spOrderInfo.spZfee }</td>
				
						<td>${spOrderInfo.zfeeRate }</td>
				
					
						<td><c:if test="${spOrderInfo.kfStatus == 1}"><span style='color:#ED4A1C'>未审核</span></c:if>
							<c:if test="${spOrderInfo.kfStatus == 2}">已审核</c:if></td>
						<td>
							<c:if test="${spOrderInfo.acStatus == 1}"><span style='color:#ED4A1C'>未审核</span></c:if>
							<c:if test="${spOrderInfo.acStatus == 2}"><span style='color:#ED4A1C'>已审核</span></c:if>
							<c:if test="${spOrderInfo.acStatus == 3}"><span style='color:#ED4A1C'>结算中</span></c:if>
							<c:if test="${spOrderInfo.acStatus == 4}"><span style='color:#06ADA3'>已结算</span></c:if>
						</td>
						<td><c:if test="${spOrderInfo.kfStatus == 1}">
								<a href="javascript:;"
									onclick="auditSpOrderInfo('${spOrderInfo.spOrderInfoId}',2,'kf');"
									names="${spOrderInfo.spOrderId}">审核</a>
							</c:if> <c:if test="${spOrderInfo.kfStatus == 2 && spOrderInfo.acStatus <3}">
								<a href="javascript:;"
									onclick="auditSpOrderInfo('${spOrderInfo.spOrderInfoId}',1,'kf');">撤销审核</a>
							&nbsp;|&nbsp;</c:if>
							<a href="${root }/Customer/Finance/getSpOrderInfoFinance.do?orderid=${spOrderInfo.spOrderId}">详情</a>
						</td>
					
				</tr>
			</c:forEach>
			<tr>
				<td colspan="6"></td>
				<td colspan="3">交易总额：${allPrice}</td>
				<td colspan="3">费用总额：${allFei}</td>
			</tr>
		</tbody>
	</table>
	<p>
		<input type="button" class="button button-ok" value="批量审核" onclick="auditSpOrderInfos(2,'kf')">
	</p>
</div>
<script>
    dateRange('#starDate', '#endDate');
    
	function go(status) {
		location.href = "/CornerV2/Customer/Finance/GetSpOrderInfos.do?status=" + status + "&pageIndex=" + $("#kkpager_btn_go_input").val().trim();
	};

	function auditSpOrderInfo(val, status, type) {
		var remark = prompt("您可以输入备注", "");
		if(remark == null){
			return ;
		}
		var date = {};
		if (remark == "") {
			date = {
				strIds : val,
				status : status,
				type : type
			};
		} else {
			date = {
				strIds : val,
				status : status,
				type : type,
				note : remark
			};
		}
		$.ajax({
			type : "POST",
			url : "${root}/Customer/Finance/auditSpOrderInfo.do",
			async : true,
			data : date,
			dataType:'json',
			success : function(da) {
				alert(da.message);
				location.reload();
			},
			error : function(da) {
				alert(da.message);
			}
		});
	}

	function checkAll() {//全选与不全选
		var selAll = document.getElementById("selAll");
		var checkboxs = document.getElementsByName("ches");
		if (document.getElementById("selAll").checked == true) {
			for (var i = 0; i < checkboxs.length; i++) {
				if (checkboxs[i].type == "checkbox")
					checkboxs[i].checked = true;
			}
		} else {
			for (var i = 0; i < checkboxs.length; i++) {
				if (checkboxs[i].type == "checkbox")
					checkboxs[i].checked = false;
			}
		}
	}

	function auditSpOrderInfos(status,type) {
		var strIds = "";
		var ck = false;
		var checkboxs = document.getElementsByName("ches");
		for (var i = 0; i < checkboxs.length; i++) {
			if (checkboxs[i].checked == true) {
				strIds += checkboxs[i].value + ",";
				ck = true;
			}
		}
		if (ck == false) {
			alert("请至少选中一个！");
			return;
		}
		var remark = prompt("您可以输入备注", "");
		if(remark == null){
			return ;
		}
		var date = {};
		if (remark == "") {
			date = {
				strIds : strIds,
				status : status,
				type : type
			};
		} else {
			date = {
				strIds : strIds,
				status : status,
				type : type,
				note : remark
			};
		}
		$.ajax({
			type: "POST",
			url: "${root}/Customer/Finance/auditSpOrderInfo.do",
			async : true,
			data :date,
			dataType:'json',
			success : function(da) {
				alert(da.message);
				$("#frm").submit();
			},
			error : function(da) {
				alert(da.message);
			}
		});
	}

	function addRemark(val, obj) {
		var str = obj.value;
		var dataPath = "/CornerV2/";
		$.ajax({
			type : "POST",
			url : dataPath + "Customer/Finance/updateRemark.do",
			async : true,
			data : 'orderid=' + val + '&mark=' + str,
			dataType:'json',
			success : function(da) {
				alert(da.message);
				$("#frm").submit();
			},
			error : function(da) {
				alert(da.message);
			}
		});
	}
	function daochu() {
		var form = $("#frm").serialize();
		var dataPath = "/CornerV2/";
		$.ajax({
			type : "POST",
			url : dataPath + "Customer/Finance/exportExcelInfo.do",
			async : true,
			data : form,
			dataType:'json',
			success : function(da) {
				alert(da.message);
			},
			error : function(da) {
				alert(da.message);
			}
		});
	}
</script>
</body>
</html>