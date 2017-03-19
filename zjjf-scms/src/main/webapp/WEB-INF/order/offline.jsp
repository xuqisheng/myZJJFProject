<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<%
	request.setAttribute("root", request.getContextPath());
%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<title>快销宝 - 转角街坊</title>
<meta name="description" content="">
<meta name="keywords" content="">
<%@ include file="../common/head.jsp"%>
<script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
<script src="${root}/resources/js/comm.js?vvv"></script>
<%@ include file="../common/autocomplete.jsp"%>
</head>
<body onload="onload()">
	<div class="wrap-bd noprint">
		<div class="mb-small clearfix">
			<div class="fl title">全部订单</div>
			<div class="fr">
				<a href="${root}/scms/orderctl/listPage.do?staging=1" target="_blank"><button class="button">打单出货</button></a>
			</div>
		</div>
		<div class="op-section clearfix">
			<form action="" name="orderid" method="post" id="orderForm">
				<input class="input-search-text" type="text" name="orderId" placeholder="订单号/店铺名称/客户手机号">
				<label class="ml-default">时间：</label>
				<input type="text" name="getOrderTimeStart" class="input-search-date J_DATE_START" placeholder="选择时间" /> -
				<input type="text" name="getOrderTimeEnd" class="input-search-date mr-small J_DATE_END" placeholder="选择时间" />
				<label class="ml-default">结算方式：</label>
				<select name="jsType" class="select">
					<option value="" selected="selected">全部</option>
					<option value="1">现结</option>
					<option value="2">批结</option>
					<option value="3">月结</option>
				</select>
				<label class="ml-default">付款状态：</label>
				<select name="supportStatus" class="select">
						<option value="" selected="selected">全部</option>
						<option value="1" >已收款</option>
						<option value="0">未收款</option>
				</select>
				<label class="ml-default">打印标志：</label>
				<select name="status" class="select">
					<option value="" selected="selected">全部</option>
					<option value="4">已打印</option>
					<option value="1">未打印</option>
				</select>
				<input type="button" class="input-search-button ml-default" value="搜索" id="orderFormSubmit" />
			</form>
		</div>
		<div>
			<table class="table-list table-border">
				<thead class="table-thead">
					<tr class="table-header">
						<th>订单编号</th>
						<th>店铺名称</th>
						<th>手机号</th>
						<th>金额合计</th>
						<th>结算方式</th>
						<th>打印状态</th>
						<th>付款状态</th>
						<th>订单时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody class="table-tbody" id="orderFromTbody">
				</tbody>
			</table>
		</div>
		<%@ include file="../common/pagination.jsp"%>
	</div>
	<div class="noprint">
		<div class="dialog hidden" id="J_dialogEditOrder">
			<form action="${root}/scms/orderctl/update.do" name="orderid" method="post" id="J_editOrder" onsubmit="return isSubmit()">
				<div class="dialog-head">
					<div class="dialog-title">订单详情</div>
					<div class="dialog-close"></div>
				</div>
				<div class="dialog-body dialog-padding">
					<div>
						<b class="mr-default">订单号：<label id="editOrderId"></label><input type="hidden" name="orderId" id="editOrderIdhidden" /></b>
						<b class="ml-default">总计：<span style="color: red" id="editOrderPrice"></span></b>
					</div>
					<div class="mt-default">
						<label class="label">店铺名称：</label>
						<input type="text" class="input mr-default" placeholder="" name="contact" placeholder="" id="editContact" maxlength="64">
						<span class="ml-default mr-default"></span>
						<label class="label ml-default">联系人：</label>
						<input type="text" name="consignee" id="editConsignee" class="input" placeholder="" maxlength="12">
					</div>
					<div>
						<label class="label">手机号：</label>
						<input type="text" class="input mr-default" id="editMobile" name="mobile" placeholder="" maxlength="11">
						<span class="ml-default mr-default"></span>
						<label class="label ml-default">收货地址：</label>
						<input type="text" class="input" id="editAddress" name="address" placeholder="" maxlength="200">
					</div>
					<div>
						<label class="label">客户类型：</label>
						<span style="display: inline-block; width: 200px;" class="mr-default">
							<input type="radio" name="editStoreType" value="1" checked="checked"> 便利店 <input class="ml-default" type="radio" name="editStoreType" value="2"> 餐饮店 <!-- <input type="radio" name="editStoreType" value="3"> 其他 -->
						</span>
						<span class="ml-default mr-default"></span>
						<label class="label ml-default">结算方式：</label>
						<span style="display: inline-block; width: 200px;">
							<input type="radio" name="editJsType" value="1" checked="checked"> 现结 <input class="ml-default" type="radio" name="editJsType" value="2"> 批结 <input class="ml-default" type="radio" name="editJsType" value="3"> 月结
						</span>
					</div>
					<div class="mb-small">
						<label class="label">收款状态：</label>
						<span style="display: inline-block; width: 200px;" class="mr-default">
							<input type="radio" name="editSupportStatus" value="0"> 未收款 <input class="ml-default" type="radio" name="editSupportStatus" value="1"> 已收款
						</span>
						<span class="ml-default mr-default"></span>
						<label class="label ml-default">备注：</label> <input type="text" id="editSpRemark" name="spRemark" class="input">
					</div>
					<div style="max-width: 686px; max-height: 130px; overflow: auto; border: 1px solid #dce0e8">
						<table class="table-list table-border" id="editScmsOrderDetails">
							<thead class="table-thead">
								<tr class="table-header">
									<th>商品编号</th>
									<th>商品名称</th>
									<th width="120">规格</th>
									<th width="30">单位</th>
									<th width="60">数量</th>
									<th width="80">单价（元）</th>
									<th width="80">小计(元)</th>
								</tr>
							</thead>
							<tbody class="table-tbody" id="editScmsOrder">
							</tbody>
						</table>
                    </div>
					<div class="ta-l mt-small" style="color: #aaa; font-size: 13px;">
						订单提交时间：<span id="editAddTime"></span><br> 订单打印时间：<span
							id="editPrintTime"></span><br> 最后编辑时间：<span
							id="editGaveTime"></span>
					</div>
				</div>
				<div class="dialog-foot">
					<input type="button" class="dialog-button dialog-ok" value="重新打印" id="editOrderPrint">
					<input type="button" class="dialog-button dialog-ok ml-default" value="保存订单" id="editOrderSave">
					<input type="button" class="dialog-button dialog-cancel ml-default" value="取消">
				</div>
			</form>
		</div>
	</div>
	<div class="cover-all hidden noprint"></div>
	<style>
		.print-order {
			font-size: 14px;
			display: none
		}

		.print-order table th {
			font-size: 14px;
			font-weight: normal;
		}

		.print-order .print-order-title {
			position: relative;
			line-height: 32px;
			text-align: center;
			font-size: 16px;
			font-weight: bold
		}

		.print-order .re-print {
			position: absolute;
			top: 0;
			right: 10px;
			font-size: 14px;
			font-weight: normal
		}

		@media print {
			.noprint {
				display: none
			}
			.print-order {
				display: block
			}
		}
	</style>
	<div class="print-order" id="realPrintDiv">
        <div class="print-order-title ta-c" style="font-size: 20px">JFUN转角 线下订单 <span class="re-print"><span id="afreshPrint"></span></span></div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" style="line-height:14px" id="print-order">
			<tr>
				<td>配送店主：</td>
				<td></td>
				<td>&nbsp;</td>
				<td>店铺手机：</td>
				<td></td>
				<td>&nbsp;</td>
				<td>店铺固话：</td>
				<td></td>
			</tr>
			<tr>
				<td>店铺名称：</td>
				<td></td>
				<td>&nbsp;</td>
				<td>订单编号：</td>
				<td></td>
				<td>&nbsp;</td>
				<td>结算方式：
				<td>
				<td></td>
			</tr>
			<tr>
				<td>店铺地址：</td>
				<td></td>
				<td>&nbsp;</td>
				<td>备注信息：</td>
				<td></td>
			</tr>
		</table>
		<table class="table-print mt-small mb-small" id="print-order-orderDetail">
		</table>
		<div style="line-height:14px">
			金额合计：￥<span id="print-order-orderDetail-orderPrice"></span>
			&nbsp;&nbsp;&nbsp;&nbsp;总数量：<span
				id="print-order-orderDetail-totalQuantity"></span>
		</div>
	</div>
	<script>
	var isOk = '1';
    $(function () {
		$('.table-list').on('click', 'td', function() {
            $(this).parent().css({
                'background': '#009dd9',
                'color': 'white'
            }).siblings().css({
                'background': '#fff',
                'color': 'black'
            })
        });
    	var num = 1;
    	 //获取商品列表
        $("#jpagination").pagination({
            pageSize: 10,
            showJump: true,
            remote: {
                url: '${root}/scms/orderctl/list.do',
                params: $('#orderForm').serializeArray(),
                success: function(data) {
                	var html = "";
   				 $.each(data.list,function(i,item){
   					 html+='<tr><td>'+item.orderId+'</td><td>'+item.storeName+'</td><td>'+item.mobile+'</td><td class="txt-warn">'+item.orderPrice+'</td>';
   					 switch (item.jsType) {
						case 1:
							html+='<td>现结</td>';
							break;
						case 2:
							html+='<td>批结</td>';
							break;
						case 3:
							html+='<td>月结</td>';
							break;
						default:
							html+='<td>其他</td>';
							break;
					}
   					 switch (item.status) {
						case 4:
							html+='<td class="txt-success">已打印</td>';
							break;
						case 1:
							html+='<td class="txt-log">未打印</td>';
							break;
						default:
							html+='<td>其他</td>';
							break;
					}
   					 switch (item.supportStatus) {
						case 1:
							html+='<td class="txt-success">已付款</td>';
							break;
						case 0:
							html+='<td class="txt-warn">未付款</td>';
							break;
						default:
							html+='<td>其他</td>';
							break;
					}
   					html += '<td>'+ item.getOrderTime+'</td>';
   					html+='<td><a href="#" onclick="editOrder(\''+ item.orderId +' \')">详情</a>&nbsp;&nbsp;<a href="#" onclick="deleteOrder(\''+ item.id +' \')">删除</a></td></tr>';
   				 });
                 if(html == "") {
                 	html = '<tr><td colspan="10" class="no-data"></td></tr>';
                 }
   				 $("#orderFromTbody").html(html);
                },
                totalName:'totalSize'
            }
        });
        //搜索按钮
        $('#orderFormSubmit').on('click', function(e) {

       	 $("#jpagination").pagination('setParams', $('#orderForm').serializeArray());
       	 $("#jpagination").pagination('setPageIndex', 0);
       	 $("#jpagination").pagination('remote');
       });

        dateRange('.J_DATE_START', '.J_DATE_END', 1);

        $('input[type="text"]').keyup(function(e) {
        	if($.trim($(this).val()).length >= 60){
        		$(this).focus();
        		return false;
        	}
        });
        $('input[type="text"]').keydown(function(e) {
        	if($.trim($(this).val()).length >= 60){
        		$(this).focus();
        		if(!isNumber(e.keyCode))
        			return false;
        	}
        });
        $('.J_editOrder').on('click', function() {
            $('#J_dialogEditOrder').fadeIn();
        });

        $('.dialog').on('click', '.dialog-cancel, .dialog-close', function() {
        	$('.cover-all').fadeOut();
            $('.dialog').fadeOut();
            $('.dialog .dialog-body input[type!="radio"]').val('');
            $("#goodsPrice").html("");
    	    $("#total").html("");
        });
        var submitPrint = '0';
	     $("#editOrderSave").on("click",function(){
	     	submitPrint = '0';
	     	if(isSubmit())
        		$("#J_editOrder").submit();
	     });
	     $("#editOrderPrint").on("click",function(){
      		$('.cover-all').hide();
	     	submitPrint = '1';
	     	if(isSubmit())
        		$("#J_editOrder").submit();
	     });
   		$('#J_editOrder').on('submit', function(){
   			isOk = '0';
            ajaxSubmit(this, function(data){
           		if(data.success){
           			if(submitPrint == '1'){
           				if(data.message.status == "4"){
           					$("#afreshPrint").html("重新打印");
           				}else {
                            $("#afreshPrint").html('');
                        }
           				$("#print-order tr:eq(0) td:eq(1)").html(data.message.supplierNam);
           				$("#print-order tr:eq(0) td:eq(4)").html(data.message.mobile);
           				$("#print-order tr:eq(0) td:eq(7)").html(data.message.userTel);
           				$("#print-order tr:eq(1) td:eq(1)").html(data.message.storeName);
           				$("#print-order tr:eq(1) td:eq(4)").html(data.message.orderId);
           				if(data.message.jsType == 1)
           					$("#print-order tr:eq(1) td:eq(7)").html("现结");
           				else if(data.message.jsType == 2)
           					$("#print-order tr:eq(1) td:eq(7)").html("批结");
           				else if(data.message.jsType == 3)
           					$("#print-order tr:eq(1) td:eq(7)").html("月结");
           				else
           					$("#print-order tr:eq(1) td:eq(7)").html("其他");

           				$("#print-order tr:eq(2) td:eq(1)").html(data.message.address);
           				$("#print-order tr:eq(2) td:eq(4)").html(data.message.spRemark);


           				var totalQuantity = 0;
           				var orderDetailTable = '<tr><th>商品条码</th><th>商品名称</th><th>规格</th><th>数量</th><th>单价（元）</th><th>金额（元）</th></tr>';


           				for (var i = 0; i < data.message.scmsOrderDetails.length; i++) {
							var orderDetail = data.message.scmsOrderDetails[i];
							orderDetailTable += '<tr><td>'+orderDetail.barCode+'</td>';
							orderDetailTable += '<td>'+orderDetail.name+'</td>';
           					orderDetailTable += '<td>'+orderDetail.spec+'</td>';
           					orderDetailTable += '<td>'+orderDetail.quantity+'</td>';
           					orderDetailTable += '<td>'+orderDetail.price+'</td>';
           					orderDetailTable += '<td>'+orderDetail.totalPrice+'</td></tr>';
           					totalQuantity += orderDetail.quantity;
						}
           				$("#print-order-orderDetail").html(orderDetailTable);

           				$("#print-order-orderDetail-orderPrice").html(data.message.orderPrice);
           				$("#print-order-orderDetail-totalQuantity").html(totalQuantity);

                 		$('.cover-all').fadeOut();
                 		$('.dialog').fadeOut();
                 		$('.dialog .dialog-body input[type!="radio"]').val('');
                  		window.print();
                  		if(confirm("是否已打印成功?") == true) {
                  			$.ajax({
                    			type : "POST",
                    			url : "${root}/scms/orderctl/update.do?orderId="+data.message.orderId+"&status=4",
                    			dataType : "json",
                    			success : function(data) {
                    				if (data.success) {
                    					$("#orderFormSubmit").trigger("click");
                    					isOk = '1';
                    				}
                    			}
                    		});
                  			return true;
                  		}
                  		isOk = '1';
                  		return false;


           			}else{
           				isOk = '1';
           				alert("订单修改成功");
                 		$('.cover-all').fadeOut();
           				$('.dialog').fadeOut();
                	    $('.dialog .dialog-body input[type!="radio"]').val('');
                	    $("#orderFormSubmit").trigger("click");
           			}
               	}else{
               		alert(data.message);
               	}
            });
            return false;
        });
    });
    //将form中的值转换为键值对。
    function getFormJson(frm) {
        var o = {};
        var a = $(frm).serializeArray();
        $.each(a, function () {
            if (o[this.name] !== undefined) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });

        return o;
    }
    function isNumber(keyCode) {
        // 数字
        //if (keyCode >= 48 && keyCode <= 57 ) return true
        // 小数字键盘
        //if (keyCode >= 96 && keyCode <= 105) return true
        // Backspace键
        if (keyCode == 8) return true
        return false
    }
  	//将form转为AJAX提交
    function ajaxSubmit(frm, fn) {
        var dataPara = getFormJson(frm);
        $.ajax({
            url: frm.action,
            type: frm.method,
            data: dataPara,
            success: fn
        });
    }
    function deleteOrder(orderId){
    	if(confirm("确认删除该信息?") == true) {
    		$.post("${root}/scms/orderctl/delete.do",{id:orderId},function(data){
				if(data.success){
					alert(data.message);
					$("#orderFormSubmit").trigger("click");
				}else{
					alert(data.message);
				}
			},'json');
    	}
    }
    function editOrder(orderId){
    	$('.cover-all').fadeIn();
        $('#J_dialogEditOrder').fadeIn();
    	$.ajax({
			type : "POST",
			url : "${root}/scms/orderctl/selectByOrderId.do?orderId="+orderId,
			dataType : "json",
			success : function(data) {
				if (data.success) {
					document.getElementById("editOrderId").innerHTML  = (data.message.orderId);
					document.getElementById("editOrderIdhidden").value  = data.message.orderId;
					document.getElementById("editOrderPrice").innerHTML  = ("￥"+data.message.goodsPrice);
					document.getElementById("editConsignee").value = data.message.consignee;
					document.getElementById("editContact").value = data.message.storeName;
					document.getElementById("editMobile").value = data.message.mobile;
					document.getElementById("editAddress").value = data.message.address;
					document.getElementById("editAddTime").innerHTML = data.message.addTime;
                    var printTime = data.message.printTime;
                    if(printTime != null || printTime != '' || printTime != 'null')
					    document.getElementById("editPrintTime").innerHTML = printTime;
                    else
                        document.getElementById("editPrintTime").innerHTML = '';
					document.getElementById("editGaveTime").innerHTML = data.message.gaveTime;
					if(data.message.status == "1") {
						document.getElementById("editOrderPrint").value = "打印";
					} else {
						document.getElementById("editOrderPrint").value = "重新打印";
					}
					$('input[name="editStoreType"][value='+data.message.storeType+']').prop("checked","checked");
					$('input[name="editJsType"][value='+data.message.jsType+']').prop("checked","checked");
					$('input[name="editSupportStatus"][value='+data.message.supportStatus+']').prop("checked","checked");
					document.getElementById("editSpRemark").value = data.message.spRemark;
					var scmsOrderDetails = data.message.scmsOrderDetails;
					var editScmsOrderDetail =  document.getElementById("editScmsOrderDetails");
					var html = '';
					for (var i = 0; i < scmsOrderDetails.length; i++) {
						var scmsOrderDetail = scmsOrderDetails[i];
 						html += '<tr>'
						+ '<td>' + scmsOrderDetail.barCode + '</td>'
						+ '<td>' + scmsOrderDetail.name + '</td>'
						+ '<td>' + scmsOrderDetail.spec + '</td>'
						+ '<td>箱</td>'
						+ '<td>' + scmsOrderDetail.quantity + '</td>'
						+ '<td>' + scmsOrderDetail.price + '</td>'
						+ '<td>' + scmsOrderDetail.totalPrice + '</td>'
						+ '</tr>';
					}
					$('#editScmsOrder').html(html);
					dialogPosCenter('#J_dialogEditOrder');
				}
			},
			error : function(data) {
			}
		});
    };
    function isSubmit(){
 		var isSubmit = true;
 		var mobile = $("#editMobile").val(); //获取手机号
		var telReg = mobile.match(/^(0|86|17951)?(1[3-9])[0-9]{9}$/);
		var testPriceCheck = /^\d{1,5}(\.\d{1,2})?$/;
		var xnumCheck = /^[0-9]{1,4}$/;

		if($("#editContact").val() == ""){
			layer.tips('请填入店铺名称', '#editContact');
			$("#editContact").focus();
			return false;
		}
		if($.trim(mobile) == ""){
			layer.tips('请填入手机号', '#editMobile');
			$("#editMobile").focus();
			return false;
		}
		if($.trim(mobile)!='' && !telReg){
			layer.tips('请填入正确的手机号', '#editMobile');
			$("#editMobile").focus();
			return false;
		}
		if($("#editAddress").val() == ""){
			layer.tips('请填入地址', '#editAddress');
			$("#editAddress").focus();
			return false;
		}
 		if(isOk == '0'){
 			return false;
 		}
 		return isSubmit;
 	}
</script>
</body>
</html>
