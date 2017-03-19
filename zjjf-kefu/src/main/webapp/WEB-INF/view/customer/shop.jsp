<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>商铺管理 - 审核记录表</title>
<%@ include file="../common/head.jsp"%>
<script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
<script src="${root}/resources/js/comm.js"></script>
</head>
<body class="wrap-bd">
    <div class="mb-default">
    	<a onclick="statusQuery('')" class="pills <c:if test="${storeRo.status=='' || storeRo.status==null}">pills-active</c:if>">全部</a>
    	<a onclick="statusQuery(1)" class="pills <c:if test="${storeRo.status==2}">pills-active</c:if>">待审核</a>
        <a onclick="statusQuery(2)" class="pills <c:if test="${storeRo.status==1}">pills-active</c:if>">已通过</a>
        <a onclick="statusQuery(3)" class="pills <c:if test="${storeRo.status==3}">pills-active</c:if>">已拒绝</a>
    </div>
	<div class="mt-default">
		<form action="${root }/customer/appStore/appStores.do" id="sesrch_form" method="post">
		    <label>更新时间：</label>
            <input type="text" value="<fmt:formatDate value="${storeRo.beginTime }"/>" name="beginTime" class="input input-date J_timeS"> -
            <input type="text" value="<fmt:formatDate value="${storeRo.endTime }"/>" name="endTime" class="input input-date J_timeE">
            <label class="ml-default">审核人：</label>
            <select class="select" name="verifyadminId">
                <option value="">请选择</option>
                <c:forEach var="customerService" items="${customerServiceList }">
                	<option <c:if test="${storeRo.verifyadminId==customerService.id }">selected="selected"</c:if> value="${customerService.id }">${customerService.userName }</option>
                </c:forEach>
            </select>
			<input type="text" value="${storeRo.keywords}" id="keywords" name="keywords" placeholder="商铺名/手机号" class="input input-search-text ml-default">
			<input type="hidden" name="status" id="status" value="${storeRo.status}"> 
			<input type="button" id="search_btn" value="搜索" class="input input-search-button ml-default">
		</form>
	</div>
	<div class="mt-small">
		<table class="table-list table-border">
			<thead class="table-thead">
				<tr>
					<th>序号</th>
					<th>店铺编号</th>
					<th>店铺名称</th>
					<th>手机号码</th>
					<th>审核人</th>
					<th>注册时间</th>
					<th>更新时间</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody class="table-tbody">
			<c:choose>
				<c:when test="${applyStores != null && applyStores.size()>0}">
					<c:forEach var="store" items="${applyStores}" varStatus="sta">
						<tr>
							<td>${(page.pageIndex-1)*10+(sta.index+1)}</td>
							<td>${store.suppliercode}</td>
							<td>${store.name}</td>
							<td>${store.mobile}</td>
							<td>${store.verifyAdminNm}</td>
							<td><fmt:formatDate value="${store.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td><fmt:formatDate value="${store.actionTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td>
		                        <c:if test="${store.status==1}"><span class="txt-success">通过</span></c:if>
		                        <c:if test="${store.status==2}"><span class="txt-warn">待审核</span></c:if>
		                        <c:if test="${store.status==3}"><span class="txt-warn">拒绝</span></c:if>
							</td>
							<td>
								<c:if test="${store.status==2}">
									<a href="${root}/customer/appStore/getStoreById.do?id=${store.storeId}">审核</a>
									<a href="${root}/customer/appStore/deleteAppStoreById.do?id=${store.storeId}&mobile=${applyStore.mobile}" onclick="return confirm('确定删除该条记录?')" class="delete">删除</a>
								</c:if>
								<c:if test="${store.status==1 || store.status==3}">
									<a href="${root}/customer/appStore/getStoreById.do?id=${store.storeId}">详情</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
			            <td colspan="9">无数据</td>
			        </tr>
				</c:otherwise>
			</c:choose>
			</tbody>
		</table>
	</div>
	<c:if test="${fn:length(applyStores)>0}">
		<%@ include file="../common/pagination-kk.jsp"%>
	</c:if>
</div>
<script>
	$(function(){
		dateRange('.J_timeS', '.J_timeE', 1);
		$("#search_btn").click(function(){
			$("#sesrch_form").submit();
		});
		
		$("#sendBtn").on("click",function(){
			var val = $("#id").val();
			location.href="appStores.do?id="+val;
		});
	});
	
	function statusQuery(num){
		if(num==1){
			$("#status").val(2);
		}else if(num==2){
			$("#status").val(1);
		}else if(num==3){
			$("#status").val(3);
		}else{
			$("#status").val('');
		}
		$("#sesrch_form").submit();
	}
</script>
</body>
</html>