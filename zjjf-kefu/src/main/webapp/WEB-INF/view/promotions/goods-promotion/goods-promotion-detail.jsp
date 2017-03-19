<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">

	<head>
		<meta charset="UTF-8">
		<title>${skuActive.activeName }</title>
		<%@ include file="../../common/head.jsp"%>
		<link rel="stylesheet" type="text/css" href="${root }/resources/vendor/jquery/ztree/css/zTreeStyle/zTreeStyle.css" />
		<script src="${root }/resources/js/comm.js"></script>
		<script src="${root }/resources/vendor/jquery/ztree/js/jquery.ztree.all.js"></script>
        <script src="${root}/resources/js/promotion/goods-promotion.js"></script>
	</head>

	<body>
		<div class="wrap-bd">
			<div class="mb-small">
				<a href="#" class="crumb">促销管理</a>
                <a href="${root }/keFu/SKUActive/getAllSKUActive.do" class="crumb">单品促销</a>
			</div>

		<div class="bg wrap-bd">
			<div class="mb-default ">
				<label for="" class="label">活动名称：</label> ${skuActive.activeName }
			</div>
			<div class="mb-default ">
				<span class="label">活动说明：</span> ${skuActive.activeDesc }
			</div>
			<div class="mb-default ">
				<label for="" class="label">状态：</label>
				<c:if test="${skuActive.status==0}">
					新建
				</c:if>
				<c:if test="${skuActive.status==1}">
					已审核
				</c:if>
				<c:if test="${skuActive.status==2}">
					生效中
				</c:if>
				<c:if test="${skuActive.status==3}">
					自动结束
				</c:if>
				<c:if test="${skuActive.status==4}">
					手动结束
				</c:if>
			</div>

			<div class="mb-default ">
				<label for="" class="label"> 添加时间：</label>
				<fmt:formatDate value="${skuActive.addTime }" pattern="yyyy-MM-dd HH:mm:ss" />

			</div>
			<div class="mb-default">
				<label for="" class="label">  添加人：</label> ${skuActive.addUser }
			</div>

			<c:if test="${skuActive.status==2 ||  skuActive.status==3 || skuActive.status==4 }">

				<div class="mb-default ">
					<label for="" class="label"> 审核时间：</label>
					<fmt:formatDate value="${skuActive.confirmTime }" pattern="yyyy-MM-dd HH:mm:ss" />
					<label for="" class="label">  审核人：</label> ${skuActive.confirmUser }
				</div>

				<div class="mb-default ">
					<label for="" class="label"> 生效时间：</label>
					<fmt:formatDate value="${skuActive.effecTime }" pattern="yyyy-MM-dd HH:mm:ss" />
					<label for="" class="label"> 生效次数：</label> ${skuActive.effecCount }
					<label for="" class="label"> 最近生效人：</label> ${skuActive.effecUser }
				</div>
				<div class="mb-default ">
					<label for="" class="label"> 失效时间：</label>
					<fmt:formatDate value="${skuActive.invalidTime }" pattern="yyyy-MM-dd HH:mm:ss" />
					<label for="" class="label">  失效次数：</label> ${skuActive.invalidCount }
					<label for="" class="label">  最近失效人：</label> ${skuActive.invalidUser }
				</div>

			</c:if>

			<div class="mb-default">
				<label for="" class="label">活动时间：</label>
				<fmt:formatDate value="${skuActive.startTime }" pattern="yyyy-MM-dd HH:mm:ss" />
				<!--,dateFmt:'yyyyMMdd HH:mm:ss'控制是时间在分秒-->
				&nbsp;&nbsp; - &nbsp;&nbsp;
				<fmt:formatDate value="${skuActive.endTime }" pattern="yyyy-MM-dd HH:mm:ss" />
				<span class="txt-warn hidden" id="times"></span>
			</div>
			<div class="mb-default hidden">
				<label for="" class="label">展示时间：</label>
				<fmt:formatDate value="${skuActive.upTime }" pattern="yyyy-MM-dd HH:mm:ss" />
				&nbsp;&nbsp; - &nbsp;&nbsp;
				<fmt:formatDate value="${skuActive.downTime }" pattern="yyyy-MM-dd HH:mm:ss" />
				<span class="txt-warn hidden" id="times"></span>
			</div>
			<div class="mb-default">
				<label for="" class="label">购买时间：</label>
				<fmt:formatDate value="${skuActive.buyStartTime }" pattern="yyyy-MM-dd HH:mm:ss" />
				&nbsp;&nbsp; - &nbsp;&nbsp;
				<fmt:formatDate value="${skuActive.buyEndTime }" pattern="yyyy-MM-dd HH:mm:ss" />
				<span class="txt-warn hidden" id="times"></span>
			</div>
			<div class="mb-default">
				<label for="" class="label">发布区域：</label>
				<c:choose>
					<c:when test="${skuActivePublishTag != null && skuActivePublishTag.size()>0 }">
						<c:forEach items="${skuActivePublishTag}" var="item" varStatus="sta">
							<input type="checkbox" name="publish${item.id}" disabled="disabled" id="" value="${item.id}" class="checkbox" <c:if test="${item.checked == '1' }">checked="checked"</c:if> />&nbsp;${item.name}&nbsp;&nbsp;
						</c:forEach>
					</c:when>
					<c:otherwise>
						无发布区域
					</c:otherwise>
				</c:choose>
			</div>

			<div class="mb-default ">
				<div class="mb-default">
					参与批发商：
				</div>
				<form action="" method="post" id="fm">
					<div class="" style="display: inline-block;width: 80%;">
						<table class="table-list mb-default">
							<thead>
								<tr>
									<th>序列号</th>
									<th>定格名称</th>
									<th>批发商名称</th>
									<th>商品条码</th>
									<th>商品名称</th>
									<th>活动价格</th>
									<th>商品限量</th>
									<th>总限量</th>
									<th>标贴</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${listDetial != null && listDetial.size()>0 }">
										<c:forEach items="${listDetial}" var="item" varStatus="sta">
											<tr>
												<td>${sta.index+1 }<input type="hidden" name="skuActiveGoodsInfoIds" value="${item.id }" /></td>
												<td>${item.spGroupName }</td>
												<td>${item.supplierName }</td>
												<td>${item.mdseId }</td>
												<td>${item.itemBaseName }</td>
												<td>
													<c:if test="${opType == null}">
													      ${item.activePrice}
													</c:if>
													<c:if test="${opType == '1'}">
													    <input type="text" class="input input-date" name="activePrices" id="" onchange="editEffecSKUActive4updatePrice('${skuActive.id}','${item.plantItemId}',this)" value="${item.activePrice }" />
													</c:if>
												</td>
												<td>${item.buyLimitNum }</td>
												<td>${item.totalLimitNum }</td>
												<td>
														<c:forEach items="${tagList}" var="tag">
															<c:if test="${item.tagId == tag.id }">
																${tag.name}
															</c:if>
														</c:forEach>
												</td>
											</tr>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tr>
											<td colspan="9">暂无数据</td>
										</tr>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</div>
			</div>
			<div class="mb-default" style="overflow: hidden;">
				<a href="${root }/keFu/SKUActive/getAllSKUActive.do" class="button button-default fl">回列表</a>
			</div>
			</form>
		</div>
	</body>
	<script>
	</script>

</html>
