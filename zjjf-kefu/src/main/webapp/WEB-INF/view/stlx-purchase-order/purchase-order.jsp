<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setAttribute("root", request.getContextPath());%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>采购订单</title>
		<link rel="stylesheet" type="text/css" href="${root}/resources/css/base.css" />
		<link rel="stylesheet" href="${root}/resources/css/order-detail.css">
		<script src="${root}/resources/vendor/jquery/jquery-1.12.4.min.js"></script>
		<script src="${root}/resources/js/comm.js"></script>
		<script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
		<%@ include file="../common/head.jsp"%>
	</head>
	<style type="text/css">
		ul{
			list-style: none;margin: 0;padding: 0;
		}
		ul li{
			margin-bottom: 18px;
		}
	</style>

	<body>
		<div class="wrap-bd no-print">
			<div class="mb-small clearfix">
				<div class="fl">采购订单</div>
			</div>
			<div class="bg wrap-bd clearfix">
			    <input type="hidden" value="${pageIndex}" id="pageIndex" name="pageIndex">
				<form action="${root}/kefu/erpOrder/toErpOrderIndex.do" method="post">
				    <input type="hidden" name="type" value="${type}"> 
					<label id="">关键词：</label>
					<input type="text" name="whName" id="" value="${whName}" class="mr-default input input-default" placeholder="仓库名称/采购单号" />
					<label for="">审核状态：</label>
					<select name="checkStatus" class="mr-default select">
						<option value="-1" <c:if test="${checkStatus==-1}">selected</c:if>>全部</option>
						<option value="2" <c:if test="${checkStatus==2}">selected</c:if>>已审核</option>
						<option value="1" <c:if test="${checkStatus==1}">selected</c:if>>未审核</option>
					</select>
					<!-- <label for="">入库状态：</label>
					<select name="inKu" class="mr-default select">
						<option value="-1">全部</option>
						<option value="1">已入库</option>
						<option value="0">未入库</option>
					</select> -->
					<label for="">采购时间:</label>
					<input type="text" name="startTime" id="" value="<fmt:formatDate value="${startTime}" pattern="yyyy-MM-dd" />" class=" input input-search-date J_DS" /> &nbsp;至&nbsp;
					<input type="text" name="endTime" id="" value="<fmt:formatDate value="${endTime}" pattern="yyyy-MM-dd" />" class=" input input-search-date J_DE" />
					<input type="submit" class="input input-search-button" value="搜索" />
				</form>
			</div>
			<div class="mb-small clearfix mt-default">
				<span class="button button-operate mr-default" id="J_New">新建</span>
				<span class="button button-operate mr-default" id="J_edit">修改</span>
				<span class="button button-operate mr-default" id="J_del">删除</span>
				<span class="button button-operate mr-default" id="J_check">审核</span>
				<span class="button button-operate mr-default" id="J_look">查看</span>
				<span class="button button-operate mr-default" id="J_print">打印</span>
			</div>
			<table class="table-list table-border" id="stock_info">
				<thead>
					<tr>
						<th><input type="checkbox" class="checkbox" name="" id="J_selectAll" value="" /></th>
						<th>序号</th>
						<th>采购编号</th>
						<th>采购仓库</th>
						<th>供应商名称</th>
						<th>采购金额</th>
						<th>采购数量</th>
						<th>采购人</th>
						<th>采购时间</th>
						<th>当前状态</th>
						<th>是否审核</th>
						<th>审核人</th>
						<th>审核时间</th>
					</tr>
				</thead>
				<tbody class="table-tbody">
				 <c:forEach var="item" items="${list}" varStatus="status">
					<tr data-checkStatus="${item.checkStatus}">
						<td><input type="checkbox" class="checkbox J_chk" name="" id="" value="${item.id}" /></td>
						<td>${status.index+1}</td>
						<td>${item.orderId}</td>
						<td>${item.whName}</td>
						<td>${item.managerName}</td>
						<td>${item.itemPrice}</td>
						<td>${item.totalNum}</td>
						<td>${item.purchaseUserName}</td>
						<td><fmt:formatDate value="${item.addTime}" pattern="yyyy-MM-dd HH:mm:ss" /> </td>
						<td>
						  <c:if test="${item.status==0}">
						     未入库
						  </c:if>
						  <c:if test="${item.status==1}">
						     已入库
						  </c:if>
						</td>
						<td>
						  <c:if test="${item.checkStatus==1}">
						     未审核
						  </c:if>
						  <c:if test="${item.checkStatus==2}">
						     已审核
						  </c:if>
						</td>
						<td>
						  <c:choose>
						    <c:when test="${not empty item.checkUserName}">
						      ${item.checkUserName}
						    </c:when>
						    <c:otherwise>
						    </c:otherwise>
						  </c:choose>
						</td>
						<td>
						  <c:choose>
						    <c:when test="${not empty item.checkTime}">
						     <fmt:formatDate value="${item.checkTime}" pattern="yyyy-MM-dd HH:mm:ss" />
						    </c:when>
						    <c:otherwise>
						    </c:otherwise>
						  </c:choose>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:if test="${fn:length(list)>0}">
	     	<%@ include file="../common/pagination-kk.jsp"%>
		  </c:if>
		</div>
		
	
	<!--打印的内容-->
		<div class="order-print" id="realPrintDiv">
			<div class="print-order-title ta-c" style="font-size: 20px">转角街坊采购单</div>	
			<div class="clearfix mb-small mt-default">
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
	<script type="text/javascript">
		selectAll('#J_selectAll', '.J_chk');
		
		$('.J_chk').on('click',function(){
	    	if($(this).is('.isChk')){
	    		$(this).removeClass('isChk');
	    	}else{
	    		$(this).addClass('isChk');
	    	}
	    })
		dateRange('.J_DS', '.J_DE', 1);		
		$(function() {
			$('.table-list td').click(function() {
				$(this).parent().css({'background':'#009dd9','color':'white'}).siblings().css({'background':'#fff','color':'black'});
				$_tr = $(this).parent();		
			});
			
			
			//新建按钮
			$('#J_New').on('click',function(){
				location.href='${root}/kefu/erpOrder/toAddERPOrder.do';
			});
			//查看按钮
			$('#J_look').on('click',function(){
				var chk = 0;
				for(var i = 0; i < $('.J_chk').length; i++) {
					if($('.J_chk').eq(i).is(':checked')) {
						$('.J_chk').eq(i).addClass('isChk');
						chk++;
					}
				}
				if(chk == 1) {
					var id = $('.J_chk.isChk').eq(0).val();
					location.href='${root}/kefu/erpOrder/toEditERPOrderInfo.do?id='+id+'&action=watch';
				} else if(chk > 1) {
					layer.msg('只能查看其中一项',{time:2000});
				} else {
					layer.msg('请选择一项查看',{time:2000});
				}
			});
			
			
			
			//修改按钮
			$('#J_edit').on('click', function() {
				var chk = 0;
				for(var i = 0; i < $('.J_chk').length; i++) {
					if($('.J_chk').eq(i).is(':checked')) {
						$('.J_chk').eq(i).addClass('isChk');
						chk++;
					}
				}
				if(chk == 1) {
					var checkstatus = $('.J_chk.isChk').eq(0).parent('td').parent('tr').attr('data-checkstatus');
					if(checkstatus==2){
						layer.msg('该笔采购订单已审核通过,不能修改!');
						return;
					}
					//进行修改
					var id = $('.J_chk.isChk').eq(0).val();
					location.href='${root}/kefu/erpOrder/toEditERPOrderInfo.do?action=edit&id='+id;
				} else if(chk > 1) {
					layer.msg('只能修改其中一项',{time:2000});
				} else {
					layer.msg('请选择一项进行修改',{time:2000});
				}
			})
			//删除按钮
			$('#J_del').on('click', function() {
				var chk = 0;
				for(var i = 0; i < $('.J_chk').length; i++) {
					if($('.J_chk').eq(i).is(':checked')) {
						$('.J_chk').eq(i).addClass('isChk');
						chk++;
					}
				};
				
				if(chk==0){
					layser.msg('请选择要删除的订单!');
					return;
				}
				
				var errorNum = 0;
				var msg = '序号';
				var delManagerIdStr = '';
				$('.J_chk.isChk').each(function(){
					delManagerIdStr+=","+$(this).val();
					var checkstatus = $(this).parent('td').parent('tr').attr('data-checkstatus');
					if(checkstatus==2){
						errorNum++;
						var index = $(this).parent('td').next('td').html();
						msg+=index+'  ';
					}
				});
				
				if(errorNum>0){
					msg+='采购已审核通过,不能删除!';
					layer.msg(msg);
					return;
				}
				delManagerIdStr=delManagerIdStr.substring(1,delManagerIdStr.length);
				$.ajax({
					url: '${root}/kefu/erpOrder/delErpManagerOrderInfo.do',
					type: 'post',
					data: {'delManagerIdStr':delManagerIdStr},
					dataType: 'json',
					success: function(data) {
						if(data.success){
							layer.msg('删除成功,页面即将刷新!',{time:1000},function(){
								location.href="${root}/kefu/erpOrder/toErpOrderIndex.do";
							});
						}else{
							layer.msg('删除失败,页面即将刷新!',{time:1000},function(){
								location.href="${root}/kefu/erpOrder/toErpOrderIndex.do";
							});
						}
					},
					error: function(error) {
						layer.msg('请求失败');
					}
				});
			})
			
			//打印按钮
			$('#J_print').on('click', function() {
				var chk = 0;
				for(var i = 0; i < $('.J_chk').length; i++) {
					if($('.J_chk').eq(i).is(':checked')) {
						$('.J_chk').eq(i).addClass('isChk');
						chk++;
					}
				}
				if(chk == 1) {
					var id = $('.J_chk.isChk').eq(0).val();
					if(id==null||id==''){
						layer.msg('缺少订单信息!');
			            return;
					}
					
					$.ajax({
	 					type : "post",
	 					url : '${root}/kefu/erpOrder/getOrderInfoById.do?id='+id,
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
				} else if(chk > 1) {
					layer.msg('只能打印其中一项',{time:2000});
				} else {
					layer.msg('请选择一项进行打印',{time:2000});
				}
			});
			//审核按钮
			$('#J_check').on('click', function() {
				var chk = 0;
				for(var i = 0; i < $('.J_chk').length; i++) {
					if($('.J_chk').eq(i).is(':checked')) {
						$('.J_chk').eq(i).addClass('isChk');
						chk++;
					}
				}
				if(chk==0){
					layer.msg('请至少选择一项进行审核!');
					return;
				}
				
				var managerIdStr = '';
				var errorNum = 0;
				var msg = '序号';
				$('.J_chk.isChk').each(function(){
					var checkstatus = $(this).parent('td').parent('tr').attr('data-checkstatus');
					if(checkstatus==2){
						errorNum++;
						var index = $(this).parent('td').next('td').html();
						msg+=index+'  ';
					}
					managerIdStr+=","+$(this).val();
				});
				
				if(errorNum>0){
					msg+='采购已审核通过,不能再次审核!';
					layer.msg(msg);
					return;
				}
				
				managerIdStr=managerIdStr.substring(1,managerIdStr.length);
				$.ajax({
					url: '${root}/kefu/erpOrder/checkErpOrderInfo.do',
					type: 'post',
					data: {'managerIdStr':managerIdStr},
					dataType: 'json',
					success: function(data) {
						if(data.success){
							layer.msg('审核成功,页面即将刷新!',{time:1000},function(){
								location.href="${root}/kefu/erpOrder/toErpOrderIndex.do?pageIndex="+$('#pageIndex').val();
							});
						}else{
							layer.msg('审核失败,页面即将刷新!',{time:1000},function(){
								location.href="${root}/kefu/erpOrder/toErpOrderIndex.do?pageIndex="+$('#pageIndex').val();
							});
						}
					},
					error: function(error) {
						layer.msg('请求失败');
					}
				});
			});
			
		}) 
	</script>

</html>