<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setAttribute("root", request.getContextPath());%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>客服后台-转角街坊</title>
		<link rel="stylesheet" type="text/css" href="${root}/resources/css/base.css" />
		<link rel="stylesheet" href="${root}/resources/css/order-detail.css">
		<script src="${root}/resources/vendor/jquery/jquery-1.12.4.min.js"></script>
		<script src="${root}/resources/js/comm.js"></script>
		<script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
		<%@ include file="../common/head.jsp"%>
	</head>
	<style type="text/css">
		label {
			display: inline-block;
			width: 80px;
		}
		.hidden{
		  display:none;
		}
	</style>

	<body>
		<div class="wrap-bd  noprint">
			<div class="mb-default">
				<a href="${root}/kefu/erpOrder/toErpOrderIndex.do?type=1" class="crumb">采购管理</a>
				<a href="${root}/kefu/erpOrder/toErpOrderIndex.do?type=1" class="crumb">采购订单管理</a>
				<c:choose>
				  <c:when test="${not empty orderInfo}">
				    <a href="#" class="crumb">编辑采购订单</a>
				  </c:when>
				  <c:otherwise>
				    <a href="#" class="crumb">新增采购订单</a> 
				  </c:otherwise>
				</c:choose>
			</div>
			<div class="mb-small clearfix">
			<c:choose>
				  <c:when test="${not empty orderInfo}">
				    <div class="fl">编辑采购订单</div>
				  </c:when>
				  <c:otherwise>
				    <div class="fl">新增采购订单</div> 
				  </c:otherwise>
				</c:choose>
			</div>
			
			<div class="bg wrap-bd clearfix">
			<form id="addPurchaseForm">
			<input name="id" type="hidden" value="${orderInfo.id}">
				<div class="mb-default">
					<label for="">采购仓：</label>
					<c:choose>
					  <c:when test="${not empty orderInfo}">
					    <input type="hidden" value="${orderInfo.whId}" id="whId" name="whId">
					    <span id="whName">${orderInfo.whName}</span>
					  </c:when>
					  <c:otherwise>
					  <input type="button" class="button button-default mr-default" name="" id="J_addwhouse" value="选择仓库" />
					  </c:otherwise>
					</c:choose>
					<label for="" class="ml-default">供应商：</label>
					<c:choose>
					  <c:when test="${not empty orderInfo}">
					    <input type="hidden" value="${orderInfo.managerId}" id="managerId" name="managerId">
					    <span id="spName">${orderInfo.managerName}</span>
					  </c:when>
					  <c:otherwise>
					    <input type="button" class="button button-default mr-default" name="" id="J_addSupplier" value="选择供应商" />
					  </c:otherwise>
					</c:choose>
					<label for="" class="ml-default">交货时间：</label>
					<input type="text" class="input input-search-date" name="gaveTime" onfocus="WdatePicker()" id="gaveTime" value="<fmt:formatDate value="${orderInfo.gaveTime}" pattern="yyyy-MM-dd" />" />
				</div>
				<div class="mb-default">
					<label for="">备注：</label>
					<input type="" class="input input-text" name="remark" id="" value="${orderInfo.remark}" style="width: 576px;" />
				</div>
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
							<th>采购价</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody class="table-tbody" id="purchaseTbody">
					<c:forEach var="item" varStatus="status" items="${detailVos}">
						<tr>
							<td>${status.index+1}</td>
							<td class="J_purchaseManagerId">
							    ${item.itemCode}
								<input type="text" class="input input-search-date hidden" name="" id="" value="" />
								<input type="button" class="button button-operate J_productAdd hidden" name="" id="J_addNew" value="..." />
								<input type="hidden" name="managerItemIdArr" value="${item.itemId}" style="display: none;">
							</td>
							<td class="J_purchaseXMdseId">${item.barCode}</td>
							<td class="J_purchaseMdseId">${item.mdseId}</td>
							<td class="J_purchaseName">${item.name}</td>
							<td class="J_spec">${item.spec}</td>
							<td><input type="text" name="managerItemNumArr" class="input input-search-date" id="" value="${item.quantity}" /></td>
							<td><input type="text" name="managerItemPriceArr" class="input input-search-date" id="" value="${item.areaPrice}" /></td>
							<td>
								<input type="button" name="" id="" class="button button-operate J_del" value="删除" />
							</td>
						</tr>
				   </c:forEach>
						<tr id="lastTr">
							<td colspan="9"><input type="button" name="" id="" value="增加一行" class="button button-operate"/></td>
						</tr>
					</tbody>
				</table>
				<div class="cover-all"></div>
				<div class="mb-default mt-default">
					<input type="button" class="button button-ok" name="" id="saveForm" value="确认" />
					<input type="button" class="button button-ok hidden" name="" id="printBillinfo" value="打印" />
					<input type="button" class="button button-cancel" name="" id="cancelForm" value="取消" />
				</div>
                </form>
				<!--选择仓库-->
				<div class="dialog hidden" id="J_dialogWhouse" style="width: 700px;">
					<div class="dialog-head">
						选择仓库
						<div id="J_wareHouseClase" class="dialog-close">
						</div>
					</div>
					<div class="dialog-body dialog-padding">
						<input type="text" name="keyStr" id="J_wareHouseKeyStr" value="" placeholder="仓库名称" class="input input-default mr-default" />
						<input type="button" class="input input-search-button" name="" id="J_wareHouseSearch" value="搜索" />
						<div class="mt-default" style="height: 250px;overflow: auto;">
							<table class="table-list table-border" id="">
								<thead>
									<tr>
										<th>序号</th>
										<th>仓库编号</th>
										<th>仓库名称</th>
										<th>批发商名称</th>
									</tr>
								</thead>
								<tbody class="table-tbody" id="wareHouseTbody">
								</tbody>
							</table>
							<div class="clearfix">
                              <div id="jpagination1"></div>
                            </div>
						</div>
					</div>
					<div class="dialog-foot">
						<input type="button" class="dialog-button dialog-ok" id="wareHouseOk" value="确认">
						<input type="button" class="dialog-button dialog-cancel ml-default" id="wareHouseCancel" value="取消">
					</div>
					</form>
				</div>

				<!--选择供应商-->
				<div class="dialog hidden" id="J_dialogSupplier" style="width: 700px;">
					<div class="dialog-head">
						选择供应商
						<div id="J_managerClose" class="dialog-close">
						</div>
					</div>
					<div class="dialog-body dialog-padding">
						<input type="text" name="keyStr" id="J_managerSearchKeyStr" value="" placeholder="供应商名称" class="input input-default mr-default" />
						<input type="button" class="input input-search-button" name="" id="J_managerSearch" value="搜索" />
						<div class="mt-default" style="height: 250px;overflow: auto;">
							<table class="table-list table-border" id="">
								<thead>
									<tr>
										<th>序号</th>
										<th>供应商编号</th>
										<th>供应商名称</th>
									</tr>
								</thead>
								<tbody class="table-tbody" id="managerTbody">
								</tbody>
							</table>
							<%@ include file="../common/pagination.jsp"%>
						</div>
					</div>
					<div class="dialog-foot">
						<input type="button" class="dialog-button dialog-ok" id="addSpOk" value="确认">
						<input type="button" class="dialog-button dialog-cancel ml-default" id="addSpCancel" value="取消">
					</div>
					</form>
				</div>

				<!--选择商品-->
				<div class="dialog hidden" id="J_dialogGoodsNew" style="width: 700px;">
					<div class="dialog-head">
						选择商品
						<div id="J_managerProduct" class="dialog-close">
						</div>
					</div>
					<div class="dialog-body dialog-padding">
						<!-- <input type="text" name="" id="" value="" placeholder="供应商编号/名称" class="input input-default mr-default" /> -->
						<input type="text" name="keyStr" id="keyStr" value="" placeholder="商品名称/条形码" class="input input-default mr-default" />
						<input type="button" class="input input-search-button" name="" id="J_managerItemSearch" value="搜索" />
						<div class="mt-default" style="height: 250px;overflow: auto;">
							<table class="table-list table-border" id="">
								<thead>
									<tr>
										<th>商品供应码</th>
										<th>供应商名称</th>
										<th>商品条形码</th>
										<th>箱码</th>
										<th>商品名称</th>
										<th>规格</th>
										<th>单位</th>
									</tr>
								</thead>
								<tbody class="table-tbody" id="managerItemTbody">
								</tbody>
							</table>
							<div class="clearfix">
                              <div id="jpagination3"></div>
                            </div>
						</div>
					</div>
					<div class="dialog-foot">
						<input type="button" class="dialog-button dialog-ok" id="itemOk" value="确认">
						<input type="button" class="dialog-button dialog-cancel ml-default" value="取消" id="J_itemCancel">
					</div>
					</form>
				</div>
			</div>
		</div>
		<!--打印的内容-->
		<div class="order-print" id="realPrintDiv">
			<div class="print-order-title ta-c" style="font-size: 20px">转角街坊采购单</div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" style="line-height:20px;font-size: 12px;" class="mb-default">
				<tr class="mb-small">
					<td>采购单编号：<span id="orderInfoOrderId"></span></td>
					<td>供应商：<span id="orderInfoManagerName"></span></td>
					<td>供应商编号：<span id="orderInfoManagerCode"></span></td>
				</tr>
				<tr class="mb-small">
					<td>制单人：<span id="orderInfoPurchaseUserName"></span></td>
					<td>制单时间：<span id="orderInfoAddTime"></span></td>
					<td>供应商合作方式：<span id="orderInfoCooperation"></span></td>
				</tr>
				<tr class="mb-small">
					<td>仓库：<span id="orderInfoERPWarehouse"></span></td>
					<td>仓库地址：<span id="orderInfoERPWarehouseAddress"></span></td>
					<td>交货时间：<span id="orderInfogaveTime"></span></td>
				</tr>
			</table>
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
		dialogPosCenter('#J_dialogGoodsNew');
		dialogPosCenter('#J_dialogWhouse');
		dialogPosCenter('#J_dialogSupplier');
		
		
		/* $('#J_addNew').on('click', function() {
			$('#J_dialogGoodsNew, .cover-all').fadeIn();
		}); */
		
		
		/* $('.dialog').on('click', '.dialog-cancel', function() {
			$('.dialog, .cover-all').fadeOut();
			$('#jpagination').pagination('destroy');
			$('#jpagination1').pagination('destroy');
		})
		
		
		$('.dialog').on('click', '.dialog-close', function() {
			$('.dialog, .cover-all').fadeOut();
			$('#jpagination').pagination('destroy');
			$('#jpagination1').pagination('destroy');
		}) */
		
		/* $('.table-list').on('click','td',function() {
			$(this).parent().css({'background':'#009dd9','color':'white'}).siblings().css({'background':'#fff','color':'black'});	
			$_tr = $(this).parent();
		}); */
		
		$(function() {
			
			/******************打印表单begin*******************************/
			$('#printBillinfo').on('click',function(){
				
				var id = $('#orderInfoId').val();
				if($.trim($('#orderInfoId').val())==null||$.trim($('#orderInfoId').val())==''||$.trim($('#orderInfoId').val())=='null'){
					layer.msg('请先保存订单!')
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
			});
			/******************打印表单end*******************************/
			
			/******************保存表单begin*******************************/
 			$('#saveForm').on('click',function(){
 				$('#saveForm').addClass('hidden');
 				
 				//参数校验
 				//仓库id
 				if($('#whId').val()==null||$('#whId').val()==''){
 					layer.msg('请选择仓库！');
 				   $('#saveForm').removeClass('hidden');
 					retrun;
 				}
 				//供应商id
 				if($('#managerId').val()==null||$('#managerId').val()==''){
 					layer.msg('请选择供应商！');
 				   $('#saveForm').removeClass('hidden');
 					retrun;
 				}
 				//交货时间
 				if($('#gaveTime').val()==''){
 					layer.msg('交货时间不能为空!');
 					$('#saveForm').removeClass('hidden');
 					return;
 				}
 				
 				//商品选择判断  校验
 				/* console.log($('.J_productTr').length);
 				if($('#purchaseTbody .J_productTr').length==0){
 					layer.msg('请选择供应商商品!');
 					$('#saveForm').removeClass('hidden');
 					return;
 				} */
 				
 				//数量判断
 				var numError = 0;
 				$('#purchaseTbody .J_num').each(function(){
 					var reg = /^[0-9]*[1-9][0-9]*$/;
 					if(!reg.test($(this).val())){
 						layer.tips('格式不正确!',this,{
							tipsMore:true,
                			tips:[4,'#E81818']
						});
 						numError++;
 					}
 				});
 				var priceError = 0;
 				//采购价判断
 				$('#purchaseTbody .J_price').each(function(){
 					var reg = /^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/;;
 					if(!reg.test($(this).val())){
 						layer.tips('格式不正确!',this,{
							tipsMore:true,
                			tips:[4,'#E81818']
						});
 						priceError++;
 					}
 				});
 				
 				if(numError>0){
 					$('#saveForm').removeClass('hidden');
 					return;
 				}
 				if(priceError>0){
 					$('#saveForm').removeClass('hidden');
 					return;
 				}
 				
 				$.ajax({
 					type : "post",
 					url : '${root}/kefu/erpOrder/addERPOrderInfo.do',
 					data:$('#addPurchaseForm').serialize(),
 					dataType : "json",
 					success : function(da) {
 						if(da.success){
 							layer.msg('保存订单成功!',{tiem:1000},function(){
 							    $('#printBillinfo').removeClass('hidden');
 							    $('#orderInfoId').remove();
 							    var html = '<input type="hidden" value="'+da.message+'" id="orderInfoId">';
 							   $('#saveForm').after(html);
 							});
 						}else{
 							layer.msg(da.message);
 							 $('#saveForm').removeClass('hidden');
 						}
 					},
 					error : function(data) {
 						layer.msg('失败的请求!');
 					   $('#saveForm').removeClass('hidden');
 					}
 				}); 			
			});
			
			//取消按钮
			$('#cancelForm').on('click',function(){
				location.href='${root}/kefu/erpOrder/toErpOrderIndex.do?type=1';
			});
			/******************保存表单end*******************************/
			
			
			
			/******************仓库选择begin*******************************/
			//选择仓库按钮  wareHouseOk
			$('#J_addwhouse').on('click', function() {
				$('#J_wareHouseKeyStr').val('');
				
				
				$("#jpagination1").pagination({
		    	    pageSize: 10,
		    	    remote: {
		    	        url: '${root}/ERPMa/getAllJsonErpWareHose.do',
		    	       // params: searchObject,
		    	        success: function(data) {
		    	        	if(data.flag){
		    	        		var html = '';
		    	        		$.each(data.list,function(i,item){
		    	        			html+='<tr class="J_wareHouseTr" data-id="'+item.id+'">'
		    	        			    +'<td>'+(i+1)+'</td>'
		    	        			    +'<td>'+item.code+'</td>'
		    	        			    +'<td>'+item.name+'</td>'
		    	        			    +'<td>'+item.supplierName+'</td>'
		    	        			    +'</tr>';
		    	        		});
		    	        		$('#wareHouseTbody').html(html);
		    	        	}
		    	        },
		    	        totalName:'totalSize'
		    	    }
		    	});
				$('#J_dialogWhouse, .cover-all').fadeIn();
			});
			$('#wareHouseOk').on('click',function(){
				$('#J_dialogWhouse, .cover-all').fadeOut();
				$("#jpagination1").pagination('destroy');
				$('#whId,#whName').remove();
				//生成仓库id
				var warehouseId = $('#wareHouseTbody').find('tr.J_warehouseSelected').eq(0).attr('data-id');
				var whName =  $('#wareHouseTbody').find('tr.J_warehouseSelected').eq(0).children('td').eq(2).html();
				var html = '<span id="whName">'+whName+'</span><input type="hidden" name="whId" value="'+warehouseId+'" id="whId">';
				$('#J_addwhouse').after(html);
			});
			//仓库选择取消,关闭按钮
			$('#wareHouseCancel,#J_wareHouseClase').on('click',function(){
				$('#J_dialogWhouse, .cover-all').fadeOut();
				$("#jpagination1").pagination('destroy');
			});
			$('#wareHouseTbody').on('click','.J_wareHouseTr',function(){
				$(this).css({'background':'#009dd9','color':'white'}).siblings().css({'background':'#fff','color':'black'});
				$(this).addClass('J_warehouseSelected').siblings().removeClass('J_warehouseSelected');
			});
			//仓库搜索按钮
			$('#J_wareHouseSearch').on('click',function(){
				var searchObject = {"keyStr":$.trim($('#J_wareHouseKeyStr').val())};
            	$("#jpagination1").pagination('setParams',searchObject);
            	$("#jpagination1").pagination('setPageIndex', 0);
            	$("#jpagination1").pagination('remote');				
			});
			/******************仓库选择end*******************************/
			
			
			/******************供应商选择begin*******************************/
			//选择供应商按钮
			$('#J_addSupplier').on('click', function() {
				$('#J_managerSearchKeyStr').val('');
				//判断是否选中了仓库
				if($('#whId').length==0){
					layer.msg('请选择仓库!',{time:2000});
                    return; 					
				}
				var searchObject={'whId':$('#whId').val()};
				$("#jpagination").pagination({
		    	    pageSize: 10,
		    	    remote: {
		    	        url: '${root}/ERPMa/getERPManagerByWhId.do',
		    	       params: searchObject,
		    	        success: function(data) {
		    	        	if(data.flag){
		     	        		var html = '';
		    	        		$.each(data.list,function(i,item){
		    	        			html+='<tr class="J_addSpTr" data-id="'+item.id+'">'
		    	        			    +'<td>'+(i+1)+'</td>'
		    	        			    +'<td>'+item.managerCode+'</td>'
		    	        			    +'<td>'+item.managerName+'</td>'
		    	        			    +'</tr>';
		    	        		});
		    	        		$('#managerTbody').html(html);
		    	        	}
		    	        },
		    	        totalName:'totalSize'
		    	    }
		    	});
				$('#J_dialogSupplier, .cover-all').fadeIn();
			});
			//确定按钮
			$('#addSpOk').on('click',function(){
				$('#J_dialogSupplier, .cover-all').fadeOut();
				$("#jpagination").pagination('destroy');
				$('#managerId,#spName').remove();
				var spId = $('#managerTbody').find('tr.J_spSelected').eq(0).attr('data-id');
				var spName = $('#managerTbody').find('tr.J_spSelected').eq(0).children('td').eq(2).html();
				var html = '<span id="spName">'+spName+'</span><input type="hidden" name="managerId" id="managerId" class="input input-default" value="'+spId+'">';
				$('#J_addSupplier').after(html);
				//清空商品列表
				$('#lastTr').siblings().remove();
			});
			//取消按钮 关闭按钮
			$('#addSpCancel,#J_managerClose').on('click',function(){
				$('#J_dialogSupplier, .cover-all').fadeOut();
				$("#jpagination").pagination('destroy');
			});
			//选中供应商
			$('#managerTbody').on('click','.J_addSpTr',function(){
				$(this).css({'background':'#009dd9','color':'white'}).siblings().css({'background':'#fff','color':'black'});
				$(this).addClass('J_spSelected').siblings().removeClass('J_spSelected');
			});
			//搜索按钮
			$('#J_managerSearch').on('click',function(){
				var searchObject = {"keyStr":$.trim($('#J_managerSearchKeyStr').val()),"whId":$('#whId').val()};
            	$("#jpagination").pagination('setParams',searchObject);
            	$("#jpagination").pagination('setPageIndex', 0);
            	$("#jpagination").pagination('remote');
			});
			
			/******************供应商选择end*******************************/
			
			/******************供应商商品选择begin*******************************/
			var purchaseIndex ; 
			//选择供应商商品按钮
			$('#purchaseTbody').on('click','.J_productAdd',function(){
				$('#keyStr').val('');
				if($('#managerId').val()==null||$('#managerId').val()==''){
					layer.msg('请选择供应商!');
					return;
				}
				var searchObject = {"managerId":$('#managerId').val()};
				var $parentDom = $(this).parent().parent();
				purchaseIndex = $('#purchaseTbody .J_productAdd').index(this);
				$("#jpagination3").pagination({
		    	    pageSize: 10,
		    	    remote: {
		    	        url: '${root}/ERPMa/getJsonErpManagerItem.do',
		    	        params: searchObject,
		    	        success: function(data) {
		    	        	if(data.flag){
		    	        		var html = '';
		    	        		$.each(data.list,function(i,item){
		    	        			html+='<tr class="J_itemTr" data-id="'+item.id+'">'
		    	        			    +'<td class="J_itemCode">'+item.itemCode+'</td>'
		    	        			    +'<td>'+item.managerName+'</td>'
		    	        			    +'<td class="J_xmdseId">'+item.mdseId+'</td>'
		    	        			    +'<td class="J_mdseId">'+item.mdseId+'</td>'
		    	        			    +'<td class="J_itemBaseName">'+item.itemBaseName+'</td>'
		    	        			    +'<td class="J_spec">'+item.spec+'</td>'
		    	        			    +'<td class="J_pkg">'+item.pkg+'</td>'
		    	        			    +'</tr>';
		    	        		});
		    	        		$('#managerItemTbody').html(html);
		    	        		
		    	        	}
		    	        },
		    	        totalName:'totalSize'
		    	    }
		    	});
				$('#J_dialogGoodsNew, .cover-all').fadeIn();
			});
			//选中商品
			$('#managerItemTbody').on('click',' .J_itemTr',function(){
				$(this).css({'background':'#009dd9','color':'white'}).siblings().css({'background':'#fff','color':'black'});
				$(this).addClass('J_itemSelected').siblings().removeClass('J_itemSelected');
			});
			//选中商品的确认按钮
			$('#itemOk').on('click',function(){
				//判断是否有选中的商品
				if($('#managerItemTbody .J_itemSelected').length==0){
					layer.msg('请选择商品!',{time:1000});
					return;
				}
				var managerItemDom = $('#managerItemTbody').find('tr.J_itemSelected').eq(0);
				var managerItemId = managerItemDom.attr('data-id');
				var itemCode = managerItemDom.find('td.J_itemCode').html();
				//判断商品是否重复
				if($('#purchaseTbody').find('input[name="managerItemIdArr"][value="'+managerItemId+'"]').length>0){
					layer.msg('商品已存在!',{time:1000});
					return;
				}
				
				
				
				
				//设置managerItem id
				var html = '<input type="hidden" name="managerItemIdArr" value="'+managerItemId+'">';
				//var html = '<span>'+itemCode+'<span>';
				
				//改变选中之后的样式		
				$('#purchaseTbody tr').eq(purchaseIndex).find('td.J_purchaseManagerId').append(itemCode).append(html).find('input').hide();
				//$('#purchaseTbody tr').eq(purchaseIndex).find('td.J_purchaseManagerId').after(html).empty().html(itemCode);
				
				//设置箱码
				$('#purchaseTbody tr').eq(purchaseIndex).find('td.J_purchaseXMdseId').html(managerItemDom.find('td.J_xmdseId').html());
				//设置条形码
				$('#purchaseTbody tr').eq(purchaseIndex).find('td.J_purchaseMdseId').html(managerItemDom.find('td.J_mdseId').html());
				//设置商品名称
				$('#purchaseTbody tr').eq(purchaseIndex).find('td.J_purchaseName').html(managerItemDom.find('td.J_itemBaseName').html());
				//设置规格
				$('#purchaseTbody tr').eq(purchaseIndex).find('td.J_spec').html(managerItemDom.find('td.J_spec').html());
				
				$('#J_dialogGoodsNew, .cover-all').fadeOut();
				$("#jpagination3").pagination('destroy');
			});
			//供应商品选择关闭按钮  取消按钮
			$('#J_managerProduct,#J_itemCancel').on('click',function(){
				$('#J_dialogGoodsNew, .cover-all').fadeOut();
				$("#jpagination3").pagination('destroy');
			});
			
			//供应商商品搜索
            $('#J_managerItemSearch').on('click',function(){
            	var searchObject = {"managerId":$('#managerId').val(),"keyStr":$.trim($('#keyStr').val())};
            	$("#jpagination3").pagination('setParams',searchObject);
            	$("#jpagination3").pagination('setPageIndex', 0);
            	$("#jpagination3").pagination('remote');
            });			
			/******************供应商商品选择end*******************************/
			
			
			
			var html='';
			$('#lastTr').click(function(){
				html=
				'<tr calss="J_productTr">'+
							'<td></td>'+
							'<td class="J_purchaseManagerId">'+
								'<input type="text" class="input" name="" id="" value=""/>'+
								'<input type="button" class="button button-operate J_productAdd" style="height:34px" name="" id="J_addNew" value="..." />'+
							'</td>'+
							'<td class="J_purchaseXMdseId"></td>'+
							'<td class="J_purchaseMdseId"></td>'+
							'<td class="J_purchaseName"></td>'+
							'<td class="J_spec"></td>'+
							'<td><input type="text" name="managerItemNumArr" class="input input-search-date J_num" id="" value="" /></td>'+
							'<td><input type="text" name="managerItemPriceArr" class="input input-search-date J_price" id="" value="" /></td>'+
							'<td>'+
								'<input type="button" name="" id="" class="button button-operate J_del" value="删除" />'+
							'</td>'+
						'</tr>';			
						$(this).before(html)
			});
			$('tbody').on('click', '.J_del', function() {
				if(confirm('确认删除？')) {
					$(this).parent('td').parent('tr').remove();
				}
			});
			
		})
	</script>
</html>