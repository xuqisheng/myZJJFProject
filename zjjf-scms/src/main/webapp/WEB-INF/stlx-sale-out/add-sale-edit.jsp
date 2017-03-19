<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="${root}/resources/css/base.css" />
		<link rel="stylesheet" href="${root}/resources/css/order-detail.css">
		<script src="${root}/resources/vendor/jquery/jquery-1.12.4.min.js"></script>
		<script src="${root}/resources/js/comm.js"></script>
		<script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
        <%@ include file="../common/head.jsp"%>
	</head>
	<style type="text/css">
		ul {
			list-style: none;
			padding: 0;
			margin: 0;
		}

		li {
			margin-bottom: 18px;
		}
	</style>

	<body>
		<div class="wrap-bd">
			<div class="mb-small">
				<div class="title" id="title">编辑销售出库</div>
			</div>
			<form id="spOrDerForm">
			<input type="hidden" name="id" value="${stockInfoVo.id}" id="id">
			<div>
			  <select id="J_erpWarehouseSelect" name="whId">
			   <c:forEach items="${warhouseList}" var="item">
			     <option value="${item.id}">${item.name}</option>
			   </c:forEach>
			  </select>
			</div>

			<div class="bg wrap-bd mb-default">
				<div class="clearfix">
					<ul style="width: 33%;" class="fl">
						<li>订单编号：<span id="spOrderId">${stockInfoVo.orderId}</span></li>
						<li>配送时间：<span id="sendDate"><fmt:formatDate value="${stockInfoVo.sendDate}" pattern="yyyy-MM-dd"/></span></li>
						<li>店名：<span id="spStoreName">${stockInfoVo.storeName}</span></li>
						<li>备注：<span id="userRemark">${stockInfoVo.userRemark}</span></li>
					</ul>
					<ul style="width: 33%;" class="fl">
						<li>客户订单号：<span id="ziOrderId">${stockInfoVo.pOrderId}</span></li>
						<li>手机号：<span id="mobile">${stockInfoVo.mobile}</span></li>
						<li>固定电话：<span id="userTel">${stockInfoVo.userTel}</span></li>
					</ul>
					<ul style="width: 33%;" class="fl">
						<li>配送店主：<span id="supplierNam">${stockInfoVo.supplierName}</span></li>
						<li>支付方式：<span id="payStr">${stockInfoVo.supportmethoStr}</span></li>
						<li>店名地址：<span id="address">${stockInfoVo.address}</span></li>
					</ul>
				</div>
			</div>
			<table class="table-list table-border" id="stock_info">
				<thead>
					<tr>
						<th>序号</th>
						<th>商品条形码</th>
						<th>商品名称</th>
						<th>规格</th>
						<th>单价</th>
						<th>销售数量</th>
						<th>金额</th>
						<th>已出库数量</th>
						<th>库位</th>
						<th>出库数量</th>
					</tr>
				</thead>
				<tbody class="table-tbody" id="J_orderDetailTbody">
				<c:forEach var="item" items="${detailVos}" varStatus="status">
				 <tr data-yiChuKu="${item.spDetailOutStockNum}" data-xiaoShou="${item.quantity}">
				  <td>${status.index+1}</td>
				  <td>${item.barCode}</td>
				  <td>${item.name}</td>
				  <td>${item.spec}</td>
				  <td>${item.price}</td>
				  <td>${item.quantity}</td>
				  <td>${item.totalPrice}</td>
				  <td>${item.spDetailOutStockNum}</td>
				  <td>
				   <input type="text" class="input input-search-date" name="whName"  value="${item.wh3Name}">
				   <input type="button" class="button button-operate J_addNew"   value="...">
				   <input type="hidden"  name="wareHouseIdArr" value="${item.wh3Id}">
				  </td>
				  <td>
				  <input type="text" name="operateQuantityArr" class="input input-search-date operateQuantityArr"  value="${item.operateStock}">
				  <input type="hidden"  name="orderDetailIdArr" value="${item.pId}">
				  </td>
				 </tr>
				</c:forEach>
				</tbody>
			</table>
			</form>
			<div class="cover-all">

			</div>
			<!--选择库位-->
			<div class="dialog hidden" id="J_dialogGoodsNew" style="width: 700px;">
				<div class="dialog-head">
					选择库位
					<div id="J_wareHouseClose" class="dialog-close">
					</div>
				</div>
				<div class="dialog-body dialog-padding">
					<input type="text" id="whName"   placeholder="库位名称" class="input mr-default" style="width: 200px;" />
					<input type="button" class="input-search-button" id="whSearch"  value="搜索" />
					<div class="mt-default" style="height: 250px;overflow: auto;">
						<table class="table-list table-border" >
							<thead>
								<tr>
									<!-- <th><input type="checkbox" class="checkbox"  id="J_selectAll" /></th> -->
									<th>序号</th>
									<th>仓库名称</th>
									<th>库区名称</th>
									<th>库位名称</th>
								</tr>
							</thead>
							<tbody class="table-tbody" id="J_wareHouseTbody">
							</tbody>
						</table>
					</div>
				</div>
				<div class="dialog-foot">
					<input type="button" class="dialog-button dialog-ok" id="J_wareHouseOk" value="确认">
					<input type="button" class="dialog-button dialog-cancel ml-default" value="取消" id="J_wareHouseCancel">
				</div>
			</div>
			<div class="mb-default mt-default">
				<input type="button" class="button-save"  id="J_save" value="保存" />
				<input type="button" class="button-cancel"  id="J_cancel" value="取消" />
			</div>
		</div>

	</body>
	<script>
        drag('#J_dialogGoodsNew .dialog-head','#J_dialogGoodsNew');
        drag('#J_dialogSupplier .dialog-head','#J_dialogSupplier');
		$(function() {
		    dialogPosCenter('#J_dialogGoodsNew');
			dialogPosCenter('#J_dialogSupplier');
			function drag(dragSon,dragFather){
    		$(dragSon).bind("mousedown", function(event) {
					/* 获取需要拖动节点的坐标 */
					var offset_x = $(dragFather)[0].offsetLeft; //x坐标
					console.log(offset_x)
					var offset_y = $(dragFather)[0].offsetTop; //y坐标
					/* 获取当前鼠标的坐标 */
					var mouse_x = event.pageX;
					var mouse_y = event.pageY;
					/* 绑定拖动事件 */
					/* 由于拖动时，可能鼠标会移出元素，所以应该使用全局（document）元素 */
					$(document).bind("mousemove", function(ev) {
						/* 计算鼠标移动了的位置 */
						var _x = ev.pageX - mouse_x;
						var _y = ev.pageY - mouse_y;
						/* 设置移动后的元素坐标 */
						var now_x = (offset_x + _x) + "px";
						var now_y = (offset_y + _y) + "px";
						/* 改变目标元素的位置 */
						$(dragFather).css({
							top: now_y,
							left: now_x
						});
					});
				});
				/* 当鼠标左键松开，接触事件绑定 */
				$(document).bind("mouseup", function() {
					$(this).unbind("mousemove");
				});
    	}

			/*******************************选中库位begin*****************************************/
			$('#J_erpWarehouseSelect').on('change',function(){
				$('#J_orderDetailTbody').find('input[name="whName"]').val('');
				$('#J_orderDetailTbody').find('input[name="wareHouseIdArr"]').remove();
			});
            var wahouseIndex = 0;
            $('#whSearch').on('click' , function () {
                wahouseIndex = $('#J_orderDetailTbody .J_addNew').index(this);
                var wareHouseId = $('#J_erpWarehouseSelect').val();
                var whName = $('#whName').val();
                $.ajax({
                    type : "POST",
                    url : "${root}/scms/erpMarket/getWarehouseLevel3.do",
                    dataType:'json',
                    data:{'wareHouseId':wareHouseId , whName: whName},
                    async : true,
                    success : function(da) {
                        if(da.success){
                            var html = '';
                            $(da.message).each(function(i,item){
                                html+='<tr class="J_wareHouseTbodyTr" data-id="'+item.id+'">'
                                    +'<td>'+(i+1)+'</td>'
                                    +'<td>'+(item.whName)+'</td>'
                                    +'<td>'+(item.whAreaName)+'</td>'
                                    +'<td>'+(item.name)+'</td>'
                                    +'input type="hidden" name="orderDetailIdArr" value="'+item.pId+'">'
                                    +'</tr>';
                            });
                            $('#J_wareHouseTbody').html(html);
                        }else{
                            layer.msg(da.message);
                        }
                    },
                    error : function(da) {
                        layer.msg('失败的请求!');
                    }
                });
                $('#J_dialogGoodsNew').fadeIn();
            });

			//选择仓库按钮
			$('#J_orderDetailTbody').on('click','.J_addNew',function(){
                $('#whName').val('');
			    $('#whSearch').trigger('click');
			});
			//仓库点击事件
			$('#J_wareHouseTbody').on('click','.J_wareHouseTbodyTr',function(){
				$(this).css({'background':'#009dd9','color':'white'}).siblings().css({'background':'#fff','color':'black'});
				$(this).addClass('trSelected').siblings().removeClass('trSelected');
			});
			//选择仓库确认按钮
			$('#J_wareHouseOk').on('click',function(){
				var $selectedTr = $('#J_wareHouseTbody .trSelected');
				if($selectedTr==null||$selectedTr.length==0){
					layer.msg('请选择仓库!',{time:1000});
					return;
				}
				//设置仓库id
				var wareHouseId = $selectedTr.attr('data-id');
				var html = '<input type="hidden" value="'+wareHouseId+'" name="wareHouseIdArr">';
				$('#J_orderDetailTbody .J_addNew').eq(wahouseIndex).siblings('input[name="wareHouseIdArr"]').remove();
				$('#J_orderDetailTbody .J_addNew').eq(wahouseIndex).after(html);

				//设置仓库名字
				var whName = $selectedTr.children('td').eq(3).html();
				$('#J_orderDetailTbody .J_addNew').eq(wahouseIndex).siblings('input[name="whName"]').eq(0).val(whName);
				$('#J_dialogGoodsNew').fadeOut();
			});
			//仓库关闭 取消按钮
			$('#J_wareHouseClose,#J_wareHouseCancel').on('click',function(){
				$('#J_dialogGoodsNew').fadeOut();
			});
			/*******************************选中库位end*****************************************/

			/*******************************保存表单begin*****************************************/
			$('#J_save').on('click',function(){
				if($.trim($('#id').val())==''){
					layer.msg('缺少销售出库单id!');
                    return;
				}
				var errorNum = 0;
				//校验出库数量不能大于销售数量
				$('#J_orderDetailTbody').find('.operateQuantityArr').each(function(){
					var operateQuantity = parseInt($(this).val());//出库数量
					var yiChuKu = parseInt($(this).parent('td').parent('tr').attr('data-yichuku'));//已出库数量
					var xiaoShou = parseInt($(this).parent('td').parent('tr').attr('data-xiaoshou'));//销售数量

					if((operateQuantity+yiChuKu) > xiaoShou){
						errorNum++;
						layer.tips('出库数量和已出库数量之和不能大于销售数量!',this,{
							tipsMore:true,
                			tips:[4,'#E81818']
						});
						
					}
				});
				
				if(errorNum>0){
					return;
				}

				var whErrorNum = 0;
				//校验仓库
				$('#J_orderDetailTbody').find('input[name="whName"]').each(function(){
					if($.trim($(this).val())==''){
						layer.tips('请选择仓库!',this,{
							tipsMore:true,
                			tips:[4,'#E81818']
						});
						whErrorNum++;
					}
				});

				if(whErrorNum>0){
					return;
				}

				$.ajax({
	    			type : "POST",
	    			url : "${root}/scms/erpMarket/updateSaleOut.do",
	    			dataType:'json',
	    			async : false,
	    			data : $('#spOrDerForm').serialize(),
	    			success : function(da) {
	    				if(da.success){
	    					layer.msg('保存成功!' , 300 , function () {
                                $('#J_cancel').trigger('click');
                            });
	    				}else{
	    					layer.msg(da.message);
	    				}
	    			},
	    			error : function(da) {
	    				layer.msg('失败的请求!');
	    			}
	    		});
			});

			/*******************************保存表单end*****************************************/
			//取消按钮
			$('#J_cancel').on('click',function(){
				location.href='${root}/scms/erpMarket/toSaleOutIndex.do?pageIndex=1&level=1';
		    });
	     });
	</script>

</html>
