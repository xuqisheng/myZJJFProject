<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="easyui-layout"  fit="true">
	<div region="center">
		<table class="datagrid-btable" >
			<tr>
				<td class="datagrid-cell">订单编号：</td><td colspan="3">${spOrderInfo.orderid}</td>
			</tr>
			<tr>
				<td class="datagrid-cell">店主：</td><td>${spOrderInfo.username}</td>
				<td class="datagrid-cell">配送时间：</td><td>
					<fmt:formatDate value="${spOrderInfo.senddate}" type="date" />
				</td>
			</tr>
			<tr>
				<td class="datagrid-cell">手机号：</td><td>${spOrderInfo.mobile}</td>
				<td class="datagrid-cell">支付方式：</td><td>
					<c:choose>
						<c:when test="${spOrderInfo.supportmetho==1 }">
							银行卡支付
						</c:when>
						<c:when test="${spOrderInfo.supportmetho==2 }">
							货到付款
						</c:when>
						<c:when test="${spOrderInfo.supportmetho==3 }">
							支付宝支付
						</c:when>
						<c:when test="${spOrderInfo.supportmetho==4 }">
							微信支付
						</c:when>
					</c:choose>
				</td></tr>
			<tr>
				<td class="datagrid-cell">店名：</td><td>${spOrderInfo.storename}</td>
				<td class="datagrid-cell">固定电话：</td><td>${spOrderInfo.usertel}</td></tr>
			<tr>
				<td class="datagrid-cell">店面地址：</td><td>${spOrderInfo.address}</td>
				<td class="datagrid-cell">备注：</td><td>${spOrderInfo.userremark}</td>
			</tr>
			<tr>
				<td class="datagrid-cell">批发商名称：</td><td>${spOrderInfo.suppliernam}</td>
				<td class="datagrid-cell">批发商电话：</td><td>${spOrderInfo.suppliertel}</td>
			</tr>
		</table>
		<div class="wrap s-list noprint">
			<table class="list" border="1" cellspacing="0" cellpadding="2">
				<tr>
					<th>条码</th>
					<th>商品名称</th>
					<th>规格</th>
					<th>数量</th>
					<!-- <th>市价（元）</th> -->
					<!-- <th>总市价（市价*数量）</th> -->
					<th>出货价</th>
					<th>总出货价（出货价*数量）</th>
					<th>进货价</th>
					<!-- <th>总进货价（进货价*数量）</th> -->
					<th>毛利</th>
					<!-- <th>总毛利（毛利*数量）</th> -->
					<th>费用</th>
					<th>总费用（费用*数量）</th>
				</tr>
				<c:forEach var="order" items="${listDetail}">
					<tr>
						<td>${order.barcode }</td>
						<td>${order.brand }${order.name }</td>
						<td>${order.spec }</td>
						<td>${order.quantity }</td>
						<td>${order.price }</td>
						<td>${order.totalprice }</td>
						<td>${order.plantdisprice }</td>
						<td>${order.maoli }</td>
						<td>${order.fee }</td>
						<td>${order.fee*order.quantity}</td>
					</tr>
				</c:forEach>
				<tr class="t-footer">
					<td colspan="5">金额合计：<i class="orange" id="bigmoney">${bigTotalMoney}</i></td>
					<td colspan="5">小写：<i class="orange">${spOrderInfo.orderprice }</i></td>
				</tr>
			</table>
			<div>
				<ul>
					<li><span class="p-n">提交订单时间：</span><span><fmt:formatDate value="${spOrderInfo.addtime}" type="both" /></span></li>
					<li><span class="p-n">客服派单时间：</span><span><fmt:formatDate value="${spOrderInfo.gavetime}" type="both" /></span></li>
					<li><span class="p-n">批发商提单时间：</span><span><fmt:formatDate value="${spOrderInfo.getordertime}" type="both" /></span></li>
					<li><span class="p-n">批发商打印时间：</span><span><fmt:formatDate value="${spOrderInfo.printtime}" type="both" /></span></li>
					<li><span class="p-n">货物送达时间：</span><span><fmt:formatDate value="${spOrderInfo.acktime}" type="both" /></span></li>
				</ul>
			</div>
			<div style="color:#4FE835">
				<span>客服审核：</span>
				<span>
					<c:choose>
						<c:when test="${spOrderInfo.kfstatus==1 }">
							客服未审核
						</c:when>
						<c:when test="${spOrderInfo.kfstatus==2 }">
							客服已审核
						</c:when>
					</c:choose>
				</span><br />
				<span>客服备注：</span><span>${spOrderInfo.kfnote}</span><br />
				<span>客服审核时间：</span><span><fmt:formatDate value="${spOrderInfo.kfchecktime}" type="both" /></span>
			</div>
			<div style="color:#0884E5">
				<span>供应商审核：</span><span>
					<c:choose>
						<c:when test="${spOrderInfo.spstatus==1 }">
							供应商未审核
						</c:when>
						<c:when test="${spOrderInfo.spstatus==2 }">
							供应商已审核
						</c:when>
					</c:choose>
				</span><br />
				<span>供应商备注：</span><span>${spOrderInfo.spremark}</span><br />
				<span>供应商审核时间：</span><span>
				<fmt:formatDate value="${spOrderInfo.spchecktime}" type="both" />
				</span>
			</div>
			<div style="color:#FD9B27">
				<span>财务审核：</span><span>
					<c:choose>
						<c:when test="${spOrderInfo.acstatus==1 }">
							未审核
						</c:when>
						<c:when test="${spOrderInfo.acstatus==2 }">
							已审核
						</c:when>
						<c:when test="${spOrderInfo.acstatus==3 }">
							结算中
						</c:when>
						<c:when test="${spOrderInfo.acstatus==3 }">
							已结算
						</c:when>
					</c:choose>
				</span><br />
				<span>财务备注：</span><span>${spOrderInfo.acremark}</span><br />
				<span>财务对账时间：</span><span>
				<fmt:formatDate value="${spOrderInfo.acchecktime}" type="both" />
				</span><br />
				<span>财务结算时间：</span><span>
				<fmt:formatDate value="${spOrderInfo.acsettletime}" type="both" />
				</span><br />
				<span>财务付款时间：</span><span>
				<fmt:formatDate value="${spOrderInfo.acpaytime}" type="both" />
				</span>
			</div>
			<div>
				<c:choose>
						<c:when test="${spOrderInfo.acstatus==1 }">
							<a id="checkBillPassOrder" onclick="dofeeAccountSpShengAction(2,'${spOrderInfo.orderid}')" class="easyui-linkbutton">审核通过</a>
						</c:when>
						<c:when test="${spOrderInfo.acstatus==2 }">
							<a id="checkBillPassOrder" onclick="dofeeAccountSpShengAction(1,'${spOrderInfo.orderid}')" class="easyui-linkbutton">取消审核</a>
						</c:when>
				</c:choose>
			</div>
		</div>
	</div>
</div>

