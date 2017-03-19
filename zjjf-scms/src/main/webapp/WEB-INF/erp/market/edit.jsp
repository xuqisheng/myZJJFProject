<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="${root }/resources/css/base.css" />
		<script src="${root }/resources/vendor/jquery/jquery-1.12.4.min.js"></script>
		<script src="${root }/resources/js/comm.js"></script>
		<script src="${root }/resources-src/js/fm.validator.js"></script>
		<%@ include file="../../common/head.jsp"%>
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

		.validator-error {
			color: red
		}
	</style>

	<body>
		<div class="wrap-bd">
			<form method="post" id="saleBackForm" action="${root}/scms/ERPMarketStock/save.do" class="validator" onkeypress="if(event.keyCode==13||event.which==13){return false;}">
				<div class="mb-small">
					<div class="title">销售退货</div>
				</div>
				<div class="bg wrap-bd mb-default">
					<label for="J_addSaleOut">出库单：</label>
					<input type="button" class="button button-default mr-default" id="J_addSaleOut" value="选择出库单" />
                    <label class="label">仓库：</label>
                    <select name="whId"  class="select mr-default">
                        <c:forEach items="${warehouses}" var="warehouse">
                            <option value="${warehouse.id}" <c:if test="${info2.whId == warehouse.id}" >selected="selected"</c:if>>${warehouse.name}</option>
                        </c:forEach>
                    </select>
				</div>
				<div class="bg wrap-bd mb-default">
                    <input type="hidden" value="${info2.orderId}" name="orderId">
                    <input type="hidden" value="${info2.id}" name="id">

                    <input type="hidden" value="${info.id}" name="pId">
                    <input type="hidden" value="${info.orderId}" name="pOrderId">

					<div class="clearfix">
						<ul style="width: 33%;" class="fl">
							<li>出库单号：<span id="J_saleOutOrderId">${info.orderId}</span></li>
							<li>联系人：<span id="J_supplierName">${info.supplierName}</span></li>
						</ul>
						<ul style="width: 33%;" class="fl">
							<li>销售单号：<span id="J_spOrDerId">${info.pOrderId}</span></li>
						</ul>
						<ul style="width: 33%;" class="fl">
							<li>店名：<span id="J_storeName">${info.storeName}</span></li>
							<li>订单金额：<span id="J_itemPrice">${info.itemPrice}</span></li>
						</ul>
					</div>
					<div>
						<label class="label">退货备注:</label>
						<input type="text" name="remark" class="input"  style="width: 400px;" value="${info2.remark}"/>
					</div>
				</div>
				<table class="table-list table-border" id="stock_info">
					<thead>
						<tr>
							<th>序号</th>
							<th>商品条形码</th>
							<th>商品名称</th>
							<th>规格</th>
							<th>出库数量</th>
							<th>退货数量</th>
							<th>退货原因</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody class="table-tbody" id="J_marketDetailTbody">
                        <c:choose>
                            <c:when test="${info2 == null}">
                                <c:forEach var="ele" items="${detail}" varStatus="eleStat">
                                    <tr>
                                        <td>${eleStat.count}
                                            <input type="hidden" name="ids" value="">
                                            <input type="hidden" name="detailPIds" value="${ele.id}">
                                        </td>
                                        <td>${ele.barCode}</td>
                                        <td>${ele.name}</td>
                                        <td>${ele.spec}</td>
                                        <td>${ele.operateStock}</td>
                                        <td>
                                            <input type="text" class="input input-search-date mr-default" name="quantitys" value="0" maxlength="4" data-required/>
                                        </td>
                                        <td>
                                            <input type="text" class="input input-default mr-default" name="remarks" maxlength="50"/>
                                        </td>
                                        <td>
                                            <input type="button" class="button button-operate J_del" value="删除" />
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="ele" items="${detail2}" varStatus="eleStat">
                                    <tr>
                                        <td>${eleStat.count}
                                            <input type="hidden" name="ids" value="${ele.id}">
                                            <input type="hidden" name="detailPIds" value="${ele.pId}">
                                        </td>
                                        <td>${ele.barCode}</td>
                                        <td>${ele.name}</td>
                                        <td>${ele.spec}</td>
                                        <td>${ele.quantity}</td>
                                        <td>
                                            <input type="text" class="input input-search-date mr-default" name="quantitys" value="0" maxlength="4" data-required/>
                                        </td>
                                        <td>
                                            <input type="text" class="input input-default mr-default" name="remarks" value="${ele.remark}" maxlength="50" />
                                        </td>
                                        <td>
                                            <input type="button" class="button button-operate J_del" value="删除" />
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
					</tbody>
				</table>
				<div class="mb-default mt-default">
					<input type="button" class="button-save" id="btn-save" value="确认" />
					<input type="button" class="button-cancel" value="取消"/>
				</div>
			</form>
            <!--选择出库单-->
            <div class="dialog hidden" id="J_dialogSupplier" style="width: 700px;">
                <div class="dialog-head" style="cursor: move;">
                    		选择出库单
                    <div id="J_saleBackClose" class="dialog-close">
                    </div>
                </div>
                <div class="dialog-body dialog-padding">
                    <input type="text" id="orderId"  placeholder="出库单号" class="input input-default mr-default" />
                    <input type="button" class="input-search-button search" value="搜索"/>
                    <div class="mt-default" style="height: 250px;overflow: auto;">
                        <table class="table-list table-border">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>出库单订单编号</th>
                                <th>店名</th>
                                <!-- <th>手机号</th> -->
                            </tr>
                            </thead>
                            <tbody class="table-tbody" id="J_marketStockTbody">
                            <!-- <tr>
                                <td>1</td>
                                <td>1</td>
                                <td>1</td>
                                <td>1</td> -->
                            </tbody>
                        </table>
                        <%@ include file="../../common/pagination.jsp"%>
                    </div>
                </div>
                <div class="dialog-foot">
                    <input type="button" class="dialog-button dialog-ok" id="J_saleBackOk" value="确认">
                    <input type="button" class="dialog-button dialog-cancel ml-default" value="取消">
                </div>
            </div>
		</div>
	</body>
	<script>
		selectAll('#J_selectAll', '.J_chk');
		/*******************************选择销售出库单begin***********************************/
		$(function(){
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
			drag('#J_dialogSupplier .dialog-head','#J_dialogSupplier');
            $("#jpagination").pagination({
                pageSize: 10,
                remote: {
                    url: '${root}/scms/erpMarket/getErpMarketStockInfos.do',
                    // params: searchObject,
                    success: function(data) {
                        if(data.flag){
                            var html = '';
                            $.each(data.list,function(i,item){
                                html+='<tr class="J_marketOrderInfoTr" data-id="'+item.id+'">'
                                    +'<td>'+(i+1)+'</td>'
                                    +'<td>'+item.orderId+'</td>'
                                    +'<td>'+item.storeName+'</td>'
                                    /* +'<td>手机号</td>' */
                                    +'<input type="hidden" value="'+item.pOrderId+'" name="pOrderId">'
                                    +'<input type="hidden" value="'+item.itemPrice+'" name="itemPrice">'
                                    +'<input type="hidden" value="'+item.orderId+'" name="orderId">'
                                    +'<input type="hidden" value="'+item.supplierName+'" name="supplierName">'
                                    +'</tr>';
                            });
                            $('#J_marketStockTbody').html(html);
                        }
                    },
                    totalName:'totalSize'
                }
            });
            $('#J_dialogSupplier').on('click' , '.search' , function(){
                var orderId = $('#orderId').val();
                $("#jpagination").pagination('setParams', {orderId:orderId});
                $("#jpagination").pagination('remote');
            }).on('click' , '.dialog-cancel' , function(){
                //选择销售出库单取消按钮
                $('#J_dialogSupplier').fadeOut();
            }).on('click' , '.dialog-close' , function(){
                //选中销售出库关闭按钮
                $('#J_dialogSupplier').fadeOut();
            }).on('click' , '.dialog-ok' , function(){
                //选择销售出库单确认按钮
                var $selectedTr = $('#J_marketStockTbody .J_marketOrderInfoTr.selectedTr').eq(0);
                //出库单号
                var pId = $selectedTr.attr('data-id');
                var id = $('input[name="id"]').val();
                //当存在被选中内容时才能确认提交
                for(var i=0;i<$('.J_marketOrderInfoTr').length;i++){
                	if($('.J_marketOrderInfoTr').eq(i).hasClass('selectedTr')){
             		window.location.href = '${root}/scms/ERPMarketStock/edit/'+pId+'/'+id+'.do';
                	}
                }
            }).on('click','.J_marketOrderInfoTr',function(){
                $(this).css({
                    'background': '#009dd9',
                    'color': 'white'
                }).addClass('selectedTr').siblings().css({
                    'background': '#fff',
                    'color': 'black'
                }).removeClass('selectedTr');
            });
            $('#J_addSaleOut').on('click',function(){
                $('#J_dialogSupplier').fadeIn();
                $('#J_dialogSupplier .search').trigger('click');
            });

			//当存在表内容时才能够进行提交
			$('#btn-save').click(function(){
				if($('#J_marketDetailTbody tr').length!=0){
                    var count = 0;
                    var checknum = /^[0-9]\d*$/;
                    $('input[name="quantitys"]').each(function(){
                        if($(this).val() == ''){
                            count ++;
                            layer.tips('数量不能为空!',this,{
                                tipsMore:true,
                                tips:[4,'#E81818']
                            });
                        }else if($(this).val() < 0){
                            count ++;
                            layer.tips('数量不能小于0!',this,{
                                tipsMore:true,
                                tips:[4,'#E81818']
                            });
                        }else if(!checknum.test($(this).val())){
                            count ++;
                            layer.tips('数量格式不正确!',this,{
                                tipsMore:true,
                                tips:[4,'#E81818']
                            });
                        }
                    })
                    if(count == 0){
                        $('saleBackForm').submit();
                    }
				}
			})

            //选中销售出库单
            /*******************************选择销售出库单end***********************************/
            Validator.language = 'en';
            dialogPosCenter('#J_dialogSupplier');

            /* $('.table-list').on('click', 'td', function() {
                $(this).parent().css({
                    'background': '#009dd9',
                    'color': 'white'
                }).siblings().css({
                    'background': '#fff',
                    'color': 'black'
                });
                $_tr = $(this).parent();
            }); */
            $('tbody').on('click', '.J_del', function() {
                if(confirm('确认删除？')) {
                    $(this).parent('td').parent('tr').remove();
                }
            });
            $('.button-cancel').on('click', function() {
                window.location.href = '${root}/scms/ERPMarketStock/list/2.do';
            });
        });
	</script>

</html>
