<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setAttribute("root", request.getContextPath());%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="${root}/resources/css/base.css" />
		<link rel="stylesheet" href="${root}/resources/css/order-detail.css">
		<script src="${root}/resources/vendor/jquery/jquery-1.12.4.min.js"></script>
		<script src="${root}/resources/js/comm.js"></script>	
		<title>采购订单查看</title>
	</head>
	<style type="text/css">
		ul,
		ol,
		li {
			padding: 0;
			margin: 0;
			list-style: none;
		}
		
		li {
			margin-bottom: 18px;
		}
	</style>

	<body>
		<div class="wrap-bd no-print">
			<div class="mb-default">
				<a href="${root}/kefu/erpOrder/toErpOrderIndex.do?type=1" class="crumb">采购管理</a>
				<a href="${root}/kefu/erpOrder/toErpOrderIndex.do?type=1" class="crumb">采购订单管理</a>
				<a href="#" class="crumb">采购订单查看</a>
			</div>
			<div class="mb-small clearfix">
				<div class="fl">采购订单查看</div>
			</div>
			<div class="bg wrap-bd clearfix">
				<ul class="fl" style="width: 33%;">
					<li>
						<label class="label">
							采购单号：
					</label>
						<span>${orderInfo.orderId}</span>
					</li>
					<li>
						<label class="label">
							采购人：
						</label>
						<span>${orderInfo.purchaseUserName}</span>
					</li>
					<li>
						<label class="label">
							采购时间：
						</label>
						<span><fmt:formatDate value="${orderInfo.addTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
					</li>
					<li>
						<label class="label">
							审核人：
						</label>
						<span>${orderInfo.checkUserName}</span>
					</li>
					<li>
						<label class="label">备注：</label>
						<span>${orderInfo.remark}</span>
					</li>
				</ul>
				<ul class="fl" style="width: 33%;">
					<li>
						<label class="label">
							采购仓库：
						</label>
						<span>${orderInfo.whName}</span>
					</li>
					<li>
						<label class="label">
							采购金额：
						</label>
						<span>${orderInfo.itemPrice}</span>
					</li>
					<li>
						<label class="label">
							交货时间：
						</label>
						<span><fmt:formatDate value="${orderInfo.gaveTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
					</li>
					<li>
						<label class="label">
							审核时间：
						</label>
						<span><fmt:formatDate value="${orderInfo.checkTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
					</li>
				</ul>
				<ul class="fl" style="width: 33%;">
					<li>
						<label class="label">
							供应商名称：
						</label>
						<span>${orderInfo.managerName}</span>
					</li>
					<li>
						<label class="label">
							采购数量：
						</label>
						<span>${caiGou}</span>
					</li>
					<li>
						<label class="label">
							是否审核：
						</label>
						<span>
						  <c:if test="${orderInfo.checkStatus==1}">
						     未审核
						  </c:if>
						  <c:if test="${orderInfo.checkStatus==2}">
						     已审核
						  </c:if>
						</span>
					</li>
					<li>
						<label class="label">
							当前数量：
						</label>
						<span>${danQian}</span>
					</li>
				</ul>
				<table class="table-list table-border" id="stock_info">
					<thead>
						<tr>
							<th>序号</th>
							<th>商品供应码</th>
							<th>箱码</th>
							<th>商品条形码</th>
							<th>商品名称</th>
							<th>规格</th>
							<th>数量</th>
							<th>入库数量</th>
							<th>采购价</th>
						</tr>
					</thead>
					<tbody class="table-tbody">
					<c:forEach varStatus="status" var="item" items="${detailVos}">
						<tr>
							<td>${status.index+1}</td>
							<td>${item.itemCode}</td>
							<td>${item.barCode}</td>
							<td>${item.mdseId}</td>
							<td>${item.name}</td>
							<td>${item.spec}</td>
							<td>${item.quantity}</td>
							<td>${item.operateQuantity}</td>
							<td>${item.areaPrice}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<div class="mb-default mt-default">
					<input type="button" class="button button-ok goBack" name="" id="" value="确认" />
					<input type="button" class="button button-ok" name="" id="printBillinfo" value="重新打印" />
					<input type="button" class="button button-cancel goBack" name="" id="" value="取消" />
				</div>
			</div>
		</div>
		<!--打印的内容-->
		<div class="order-print" id="realPrintDiv">
			<div class="print-order-title ta-c mb-default" style="font-size: 20px">转角街坊采购单</div>
			<div class="clearfix mt-default">
				<ul style="width: 38%;" class="fl">
					<li>采购单号：<span id="orderInfoOrderId"></span></li>
					<li>供应商号：<span id="orderInfoManagerCode"></span></li>
					<li>制单时间：<span id="orderInfoAddTime"></span></li>
					<li>仓库地址：<span id="orderInfoERPWarehouseAddress"></span></li>
				</ul>
				<ul style="width: 30%;"  class="fl">
					<li>供应商：<span id="orderInfoManagerName"></span></li>
					<li>供应商合作方式：<span id="orderInfoCooperation"></span></li>
					<li>交货时间：<span id="orderInfogaveTime"></span></li>
				</ul>
				<ul style="width: 32%;" class="fl">
					<li>制单人：<span id="orderInfoPurchaseUserName"></span></li>
					<li>仓库：<span id="orderInfoERPWarehouse"></span></li>
				</ul>
			</div>
			
			<table class="table-print mt-small" border="1">
				<tr>
					<th>序号</th>
					<th>箱码</th>
					<th>商品条形码</th>
					<th>商品名称</th>
					<th>规格</th>
					<!-- <th>单位</th> -->
					<th>单价</th>
					<th>数量</th>
					<th>小计</th>
				</tr>
				<tbody id="printTbody">
				</tbody>
			</table>
			<div class="mt-default">
				<div class="clearfix mb-default">
					
					<span class="ta-l" style="display:inline-block;width: 200px;">备注：<span id="orderInfoRemark"></span></span>
					<span class=" ta-l" style="display:inline-block;width: 200px;">总数量：<span id="totalNum"></span></span>
					<span class=" ta-l" style="display:inline-block;width: 200px;">总计：<span id="orderInfoPrice"></span></span>
					
				</div>
				<div class="clearfix">
					<span class="ta-l" style="display:inline-block;width: 200px;">仓库预约电话：<span id="wareHouseMobile"></span></span>
					<span class=" ta-l" style="display:inline-block;width: 200px;">客服热线：400-664-3833</span>
				</div>
				
			</div>
		</div>
	</body>
	<script>
		$('.table-list td').click(function() {
				$(this).parent().css({'background':'#009dd9','color':'white'}).siblings().css({'background':'#fff','color':'black'});	
				$_tr = $(this).parent();
			});
			$(function() {
			$("#printBillinfo").on('click', function() {
				$.ajax({
 					type : "post",
 					url : '${root}/kefu/erpOrder/getOrderInfoById.do?id=${orderInfo.id}',
 					dataType : "json",
 					success : function(da) {
 						if(da.success){
 							//填充表单
 							$('#orderInfoOrderId').text(da.message.orderId);//采购单编号
 							$('#orderInfoManagerName').text(da.message.erpManager.managerName);//供应商名
 							$('#orderInfoManagerCode').text(da.message.erpManager.managerCode);//供应商编号
 							if(da.message.purchaseUserName!=null&&da.message.purchaseUserName!='null'&&da.message.purchaseUserName!=''){
 	 							  $('#orderInfoPurchaseUserName').text(da.message.purchaseUserName);//制单人
 	 						 }else{
 	 							  $('#orderInfoPurchaseUserName').text('');//制单人
 	 						 }
 							$('#orderInfoAddTime').text(da.message.addTimeStr);//制单时间
 							//供应商合作方式
 							var cooperation = da.message.erpManager.cooperation;
 							if(cooperation==0){
 							  $('#orderInfoCooperation').text('购销');//合作方式
 							}else{
 							  $('#orderInfoCooperation').text('平台入驻');//合作方式
 							}
 							$('#orderInfoERPWarehouse').text(da.message.erpWarehouse.name);//仓库
 							$('#orderInfoERPWarehouseAddress').text(da.message.erpWarehouse.address);//仓库地址
 							$('#orderInfogaveTime').text(da.message.gaveTimeStr);//交货时间
 							$('#orderInfoRemark').text(da.message.remark);//备注
 							$('#orderInfoPrice').text(da.message.itemPrice);//总计
 							$('#totalNum').text(da.message.totalNum);//总数量
 							$('#wareHouseMobile').text(da.message.erpWarehouse.mobile)//仓库电话
 							//循环表单
 							var html= '';
 							$(da.message.detailVos).each(function(i,item){
 								html+='<tr>'
 								     +'<td>'+(i+1)+'</td>'
 								     +'<td>'+item.barCode+'</td>'
 								     +'<td>'+item.mdseId+'</td>'
 								     +'<td>'+item.name+'</td>'
 								     +'<td>'+item.spec+'</td>'
 								     +'<td>'+item.areaPrice+'</td>'
 								     +'<td>'+item.quantity+'</td>'
 								     +'<td>'+item.totalPrice+'</td>'
 								     +'</tr>';
 							});
 							$('#printTbody').html(html);
 							$("#realPrintDiv").show();
 							window.print();
 							$("#realPrintDiv").hide();
 							if(confirm("是否已打印成功?") == true) {
 								return true;
 							}
 							return false;
 						}else{
 							
 						}
 					},
 					error : function(data) {
 						layer.msg('失败的请求!');
 					}
 				});
				/* $("#realPrintDiv").show();
				window.print();
				$("#realPrintDiv").hide();
				if(confirm("是否已打印成功?") == true) {
					return true;
				}
				return false; */
			});
			$('.table-list td').click(function() {
				$(this).parent().css({'background':'#009dd9','color':'white'}).siblings().css({'background':'#fff','color':'black'});	
				$_tr = $(this).parent();
			});
			
			$('.goBack').on('click',function(){
				location.href='${root}/kefu/erpOrder/toErpOrderIndex.do?type=1';
			});
		})
	</script>
</html>