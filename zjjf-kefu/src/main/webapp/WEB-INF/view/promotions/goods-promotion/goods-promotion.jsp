<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>单品促销</title>
		<%@ include file="../../common/head.jsp"%>
		<script src="${root }/resources/js/comm.js" type="text/javascript" charset="utf-8"></script>
		<script src="${root }/resources/vendor/My97DatePicker/WdatePicker.js"></script>
		<script src="${root }/resources/js/promotion/goods-promotion.js"></script>
	</head>

	<body>
		<div class="wrap-bd">
			<div class="mb-small">
				<a href="#" class="crumb">促销管理</a>
                <a href="${root }/keFu/SKUActive/getAllSKUActive.do" class="crumb">单品促销</a>
			</div>
			<div class="mb-small clearfix">
				<a href="${root }/keFu/SKUActive/returnAddOrEditPage.do?action=1" class="button button-default fr">新增促销</a>
			</div>
			<div class="bg wrap-bd clearfix">
				<div class="fl mb-default">
					<form action="${root }/keFu/SKUActive/getAllSKUActive.do" id="allSKUActive" name ="allSKUActive" method="post">
						<input type="hidden" name="curpageIndex" id="curpageIndex" value="${page.pageIndex}">
						<label class="label">
							活动时间：
							</label>
							<input type="text" name="beginTime" value="<fmt:formatDate value="${beginTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" class="input input-date J_timeS" />
							至
							<input type="text" name="endTime" value="<fmt:formatDate value="${endTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" class="input input-date J_timeE mr-default" />

						<label class="">
							活动名称：
							<input type="text" name="activeName" value="${activeName }" class="input input-search-text"/>
						    <input type="submit" value="搜索" class="input input-search-button ml-default" id="search" />
						</label>
					</form>
				</div>
				<table class="table-list table-border">
					<thead class="table-thead">
						<tr>
							<th>序号</th>
							<th>添加时间</th>
							<th>活动名称</th>
							<th>活动时间</th>
							<th>活动说明</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody class="table-tbody">
						<c:choose>
							<c:when test="${activeList != null && activeList.size()>0 }">
								<c:forEach items="${activeList}" var="active" varStatus="status">
									<tr>
										<td>${status.index+1 }</td>
										<td><fmt:formatDate value="${active.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
										<td>${active.activeName}</td>
										<td>
											<fmt:formatDate value="${active.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
											至
											<fmt:formatDate value="${active.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
										</td>
										<td>${active.activeDesc}</td>
										<td>
                                            <c:choose>
                                                <c:when test="${active.status==0}">新建</c:when>
                                                <c:when test="${active.status==1}">已审核</c:when>
                                                <c:when test="${active.status==2}">生效中</c:when>
                                                <c:when test="${active.status==3}">自动结束</c:when>
                                                <c:when test="${active.status==4}">手动结束</c:when>
                                            </c:choose>
										</td>
										<td>
                                            <a href="${root }/keFu/SKUActive/querySKUActive.do?id=${active.id}" class="button button-operate">查看</a>
											<c:if test="${active.status==0}">
												<input type="button" name="confirm_${active.id}" class="button button-operate" id="confirm_${active.id}" onclick="confirmOne('${active.id}')" value="审核" />
												<a href="${root }/keFu/SKUActive/editSKUActive.do?step=1&id=${active.id}" class="button button-operate">编辑</a>
												<input type="button" name="stop_${active.id}" class="button button-operate" id="stop_${active.id}" onclick="stopSKUActive('${active.id}')"  value="停用" />
											</c:if>
											<c:if test="${active.status==1 || active.status==2}">
												<input type="button" name="stop_${active.id}" class="button button-operate" id="stop_${active.id}" onclick="stopSKUActive('${active.id}')"  value="停用" />
												<%--
												<input type="button" name="effec_${active.id}" class="button button-operate" id="effec_${active.id}" onclick="effecSKUActive('${active.id}')"  value="立即生效" />
												<input type="button" name="invalid_${active.id}" class="button button-operate" id="invalid_${active.id}" onclick="invalidSKUActive('${active.id}')"  value="立即失效" />
												--%>
												<a href="${root }/keFu/SKUActive/querySKUActive.do?id=${active.id}&opType=1" class="button button-operate">改价</a>
											</c:if>
											<c:if test="${active.status==3}">
											    <%--
											    <input type="button" name="effec_${active.id}" class="button button-operate" id="effec_${active.id}" onclick="effecSKUActive('${active.id}')"  value="立即生效" />
											     --%>
											</c:if>
											<c:if test="${active.status==4}">
											    <%--
											    <input type="button" name="effec_${active.id}" class="button button-operate" id="effec_${active.id}" onclick="effecSKUActive('${active.id}')"  value="立即生效" />
											     --%>
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr><td colspan="7">暂无数据</td></tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>

								<c:if test="${fn:length(activeList)>0}">
							          <%@ include file="../../common/pagination-kk.jsp"%>
							   </c:if>
			</div>
		</div>
	</body>
<script type="text/javascript">

    $(function() {
    	setRoot('${root}');
    	dateRange('.J_timeS', '.J_timeE', 1);
    	if(''==1){
    		$('#isVoluntary').attr('checked',true);
    	}else{
    		$('#isVoluntary').attr('checked',false);
    	}
    	$('#ruleType').val('-1');
});
</script>
</html>
