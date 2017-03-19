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
<link rel="stylesheet" href="${root}/resources/css/order-add.css?v">
<script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
<script src="${root}/resources/js/comm.js"></script>
<%@ include file="../common/autocomplete.jsp"%>
</head>
<body>
	<header class="header noprint">
	    <section class="wrap">
	        <div class="fl"><img src="${root}/resources/images/logo-other.png" alt="logo图片"></div>
	        <div class="fr info"><span class="username mr-small">您好&nbsp;&nbsp;${logInVo.userName}</span> | <span class="ml-small close" onclick="window.close();">关闭</span></div>
	    </section>
	</header>
	<section class="wrap content noprint">
		<div id="J_dialogAddOrder">
			<form action="${root}/scms/orderctl/insert.do" name="orderid" id="J_diaLogAddOrderForm" method="post"  onsubmit="return isSubmit()">

				<div class="customer-info clearfix">
					<div class="title mb-default">打单出货</div>
					<div class="row clearfix">
		                <div class="row-item required">
							<label class="label">店铺名称：</label><input type="text" class="input" value="" name="storeName" id="storeName" maxlength="64" width="100%" placeholder="">
							<input type="hidden" name="storeId" placeholder="必填" id="storeId" value="">
		                </div>
		                <div class="row-item">
							<label class="label">联系人：</label><input type="text" name="consignee" class="input" placeholder="" id="consignee" maxlength="12" width="100%">
						</div>
					</div>
					<div class="row clearfix">
		                <div class="row-item required">
							<label class="label">手机号：</label><input type="text" id="mobile" name="mobile" class="input" placeholder="" maxlength="11"  width="100%">
						</div>
		                <div class="row-item required">
							<label class="label">收货地址：</label><input type="text" name="address" class="input" placeholder="" id="address" maxlength="200"  width="100%">
						</div>
					</div>
					<div class="row clearfix">
		                <div class="row-item">
							<label class="label">客户类型：</label><span style="display: inline-block; width: 200px;"><input type="radio" name="storeType" value="1" checked="checked"> 便利店 <input class="ml-default" type="radio" name="storeType" value="2"> 餐饮店 <!-- <input type="radio" name="storeType" value="3">其他 --></span>
						</div>
		                <div class="row-item">
							<label class="label">结算方式：</label><span style="display: inline-block; width: 200px;"><input type="radio" name="jsType" value="1" checked="checked"> 现结 <input class="ml-default" type="radio" name="jsType" value="2"> 批结 <input class="ml-default" type="radio" name="jsType" value="3"> 月结 </span>
						</div>
					</div>
					<div class="row clearfix">
		                <div class="row-item">
							<label class="label">收款状态：</label><span style="display: inline-block; width: 200px;"><input type="radio" name="supportStatus" value="0" checked="checked"> 未收款 <input class="ml-default" type="radio" name="supportStatus" value="1"> 已收款 </span>
						</div>
		                <div class="row-item">
							<label class="label">备注：</label><input type="text" name="spRemark" class="input" maxlength="100" width="100%">
						</div>
					</div>
				</div>
				<table class="table-list table-border" id="J_addItemBaseTable">
					<thead class="table-thead">
						<tr class="table-header">
							<th width="10%">序号</th>
							<th class="ta-l" width="40%">商品</th>
							<th width="20%">价格（元）</th>
							<th width="20%">数量（箱）</th>
							<th width="10%">操作</th>
						</tr>
					</thead>
					<tbody class="table-tbody" id="J_autoComplete">
						<tr id="row1">
							<td>1</td>
							<td class="ta-l">
								<input type="hidden" name="itemBaseId" id="itemBaseId">
								<input type="text"  data-shortcut="enter" class="input J_itemBaseName" style="width:100%" placeholder="添加商品，输入商品关键字" name="itemBaseName">
							</td>
							<td>
								<input type="text" class="input" data-shortcut="enter" name="price" width="100%" maxlength="8">
							</td>
							<td>
								<input type="text" name="quantity" data-shortcut="enter"  class="input" width="100%" maxlength="4">
							</td>
							<td class="J_delInput cur-p"><a>删除</a></td>
						</tr>
						<tr>
							<td class="ta-l">
								<img id="J_addInput" src="${root}/resources/images/add-input.png" alt="" class="ml-default cur-p">
							</td>
							<td></td>
							<td style="font-size: 14px;">总金额：<span style="color: red" id="goodsPrice">￥0</span>
								<input name="goodsPrice" id="goodsPriceHidden" type="hidden" value="" />
							</td>
							<td style="font-size: 14px;">总数量：<span style="color: red" id="total">0</span></td>
							<td></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</section>
	<section class="wrap section-operate noprint">
		<input type="button" class="button-save mr-default" value="打印" id="J_insertPrint">
		<input type="button" class="button-save mr-default" value="保存订单" id="J_insertOrder">
		<input type="button" class="button-cancel" value="重置" id="J_insertResult">
	</section>
	<style>
		.print-order {
			font-size: 14px;
			display: none
		}

		.print-order table th {
			font-size: 14px;
			font-weight: normal;
		}

		.print-order .title {
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
        <div class="title" style="font-size: 20px">JFUN转角 线下订单<span class="re-print"><span id="afreshPrint"></span></span></div>
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
    	$('#storeName').focus();
    	var num = 1;
    	$('html, body').on('keydown', 'input[data-shortcut="enter"]:last', function(ev) {
    	    if(ev.keyCode == "13"||ev.keyCode == "9") {
    	        $('#J_addInput').trigger('click');
    	    }
    	});
    	$('#J_insertResult').on('click' , function(){
            $('#J_insertOrder').show();
            $('#J_insertPrint').show();

    		num = 1;
    		$('input[type!="radio"][type!="submit"][type!="button"]').val('');
       	    $("#orderFormSubmit").trigger("click");
       	    $("#goodsPrice").html("￥0");
       	    $("#total").html("");
       		addCollect(addDetail('', num, null), null);

       		$("#print-order-orderDetail").html("");
 			$("#print-order-orderDetail-orderPrice").html("");
 			$("#print-order-orderDetail-totalQuantity").html("");
 			$('input[name="storeType"][value="1"]').prop("checked","checked");
 			$('#storeName').focus();
    	});
        $('#J_addItemBaseTable').on('click', '#J_addInput', function() {
        	num += 1;
            $(this).parent().parent().before(addDetail('', num, null));
        });
        $('#J_dialogAddOrder').on('click', '.J_delInput', function() {
        	if(num == 1){
        		alert("最少保留一条商品信息");
        	}else{
	        	num-=1;
		        $(this).parent().empty();
		        chengeStoreType("1");
        	}
	     });

        $('#J_dialogAddOrder').on('keydown', function() {
	   	 	$('#storeName').autocomplete({
	               serviceUrl: "${root}/scms/storectl/getListByContact.do",
	               paramName: 'contact',
	               transformResult: function(response) {
	                   var res = JSON.parse(response);
	                   return {
	                       suggestions: $.map(res.message, function(value, key) {
	                       	return { value: value.nameAddress, data: value };
	                       })
	                   };
	               },
	               onSelect: function(dd) {
	            	   $('#mobile').val(dd.data.mobile);
	            	   $('#storeId').val(dd.data.id);
	            	   $('#consignee').val(dd.data.contact);
	            	   $('#address').val(dd.data.address);
	            	   $('input[name="storeType"][value='+dd.data.suType+']').prop("checked","checked");
	            	   isSubmitNoLayer();
	            	   $('#storeName').val(dd.data.name);
	               }
	        });
        });
        var countOrder = 1;
        $('#J_autoComplete').on('keydown', function() {
        	$('.J_itemBaseName').autocomplete({
                serviceUrl: "${root}/scms/storectl/getItemBaseListByName.do?storeType="+$("input[name='storeType']:checked").val(),
                paramName: 'name',
                transformResult: function(response) {
                    var res = JSON.parse(response);
    	            if(res.message != null) {
	               	 	return {
	                        suggestions: $.map(res.message, function(value, key) {
	                        	return { value: value.name + "--" + value.spec, data: value };
	                        })
	                    };
    	            }else{
    	            	return {
    		            	suggestions: [{ value: "无数据"}]
    		            };
    	            }
                },
                onSelect: function(dd) {
                	var itemBaseName = $(this);
                	if(dd.value=="无数据") {
                		layer.tips('请选择商品信息', this);
                   		$(this).val("");
                   		$(this).focus();
                	}else{
                		var isItemBaseId = true;
                		$('input[name="itemBaseId"]').each(function(){
                			if($(this).val() == dd.data.id){
								layer.tips('商品信息选择重复请重新选择', itemBaseName);
								itemBaseName.val("");
								itemBaseName.focus();
		                   		isItemBaseId = false;
		                   		return false;
    		            	}
                		});
                		if(isItemBaseId){
	                		$(this).siblings('input[name="itemBaseId"]').val(dd.data.id);
	    	            	$(this).parent().next().find('input[name="price"]').val(dd.data.price);
		                	$(this).parent().next().find('input[name="price"]').focus();
                		}
                	}
                }
           });
        	$('input[type="text"][name="price"]').on("keyup" , function(e){
        		if(countOrder == 1){
        			countOrder = 0;
	        		addQuantityChange();
        		}
        	});
        	$('input[type="text"][name="quantity"]').on("keyup" , function(e){
        		if(countOrder == 1){
        			countOrder = 0;
        			addQuantityChange();
        		}
        	});
        	$('input[type="text"][name="itemBaseName"]').on("keyup" , function(e){
        		$(this).siblings('input[name="itemBaseId"]').val("");
        	});
        });
        function addQuantityChange(){
       		var totalPrice = 0;
       		var totalQuantity = 0;
       		$('#J_autoComplete').find('tr').each(function(){
       			var price = $(this).find('input[name="price"]').val();
       			var quantity = $(this).find('input[name="quantity"]').val();
       			totalQuantity = (Number(totalQuantity) + Number(quantity));
       			try{
       				totalPrice = parseFloat(totalPrice)+(parseFloat(price*quantity));
       				if(isNaN(totalQuantity)){
       					return false;}
       			}catch(e){
       				console.log(e);
       			}finally{
       				countOrder = 1;
       			}
      			$("#goodsPrice").html("￥" + Math.floor(totalPrice*100)/100);
				$("#goodsPriceHidden").val(Math.floor(totalPrice*100)/100);
				$("#total").html(totalQuantity);
       		});
        	/**
       		$.ajax({
       			type : "POST",
       			url : "${root}/scms/orderctl/countOrder.do",
       			dataType : "json",
       			data:$('#J_diaLogAddOrderForm').serialize(),
       			success : function(data) {
       				countOrder = 1;
       				if (data.success) {
       					$("#goodsPrice").html("￥" + data.message.totalPrice);
       					$("#goodsPriceHidden").val(data.message.totalPrice);
       					$("#total").html(data.message.total);
       				}else{
       					$("#goodsPrice").html("￥0");
       	   				$("#goodsPriceHidden").val("0");
       	   				$("#total").html("0");
       				}
       			},
       			error : function(data) {
       				countOrder = 1;
       				$("#goodsPrice").html("￥0");
       				$("#goodsPriceHidden").val("0");
       				$("#total").html("0");
       			}
       		});**/
        }
        $('input[type="radio"][name="storeType"]').on("click" , function(){
        	chengeStoreType("0");
        });
        var submitPrint = '0';
        $("#J_insertOrder").on("click",function(){
            $('#J_insertOrder').hide();
        	submitPrint = '0';
        	if(isSubmit())
        		$("#J_diaLogAddOrderForm").submit();
        });
        $("#J_insertPrint").on("click",function(){
            $('#J_insertPrint').hide();
        	submitPrint = '1';
        	if(isSubmit())
        		$("#J_diaLogAddOrderForm").submit();
        });
   		$('#J_diaLogAddOrderForm').on('submit', function(){
			$.ajax({
      			type : "post",
      			url : "${root}/scms/orderctl/insert.do",
      			data:$('#J_diaLogAddOrderForm').serialize(),
      			dataType : "json",
      			success : function(data) {
      				layer.closeAll('loading');
               		if(data.success){
               			if(submitPrint == '1'){
               				$("#afreshPrint").html("");
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

                      		window.print();
               				$('#J_insertResult').trigger("click");
                      		if(confirm("是否已打印成功?") == true) {
                      			$.ajax({
                        			type : "POST",
                        			url : "${root}/scms/orderctl/update.do?orderId="+data.message.orderId+"&status=4",
                        			dataType : "json",
                        			success : function(data) {
                        				//打印成功后的操作
                        				isOk = '1';
                        			}
                        		});
                      			return true;
                      		}
                      		return false;
               			}else{
               				alert("订单添加成功");
               				$('#J_insertResult').trigger("click");
               				isOk = '1';
               			}
                   	}else{
                   		//交易报错信息
                   		isOk = '1';
                   	}
      			},
      			error : function(data) {
      				 isOk = '1';
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
 	function addDetail(html , num , detail){
 		html += '<tr id="row'+num+'"><td>'+num+'</td>';
 		if(detail == null || detail.lenght == 0){
 			html += '<td class="ta-l"><input type="hidden" name="itemBaseId" id="itemBaseId">';
 			html += '<input type="text"	class="input-search-text J_itemBaseName" data-shortcut="enter"  style="width:100%" placeholder="添加商品，输入商品关键字" name="itemBaseName"></td>';
 			html += '<td><input type="text" class="input" name="price" data-shortcut="enter"  width="100%" maxlength="8"></td>';
 			html += '<td><input type="text" name="quantity" class="input" width="100%" data-shortcut="enter"  maxlength="4"></td>';
 		}else{
 			html += '<td class="ta-l"><input type="hidden" name="itemBaseId" id="itemBaseId" value="'+ detail.itemBaseId +'">';
 			html += '<input type="text"	class="input-search-text J_itemBaseName" style="width:100%" data-shortcut="enter"  placeholder="添加商品，输入商品关键字" name="itemBaseName" value="'+ detail.itemBaseName +'"></td>';
 			html += '<td><input type="text" class="input" name="price" width="100%" maxlength="8" data-shortcut="enter"  value="'+detail.price+'"></td>';
 			html += '<td><input type="text" name="quantity" class="input" width="100%" maxlength="4" data-shortcut="enter" value="'+ detail.quantity +'" ></td>';
 		}
 		html += '<td class="J_delInput cur-p"><a>删除</a></td></tr>';
		return html;
 	}
 	function addCollect(html , data){
		html += '<tr><td class="ta-l"><img id="J_addInput" src="${root}/resources/images/add-input.png" alt="" class="ml-default cur-p"></td><td></td>';
		html += '<td style="font-size: 14px;">总金额：<span style="color: red" id="goodsPrice">';
 		if(data == null || data.lenght == 0){
 			html += '￥0</span>';
 			html += '<input name="goodsPrice" id="goodsPriceHidden" type="hidden" value="0" /></td>';
 			html += '<td style="font-size: 14px;">总数量：<span style="color: red" id="total">0</span></td>';
 		}else{
 			html += '￥'+data.message.goodsPrice+'</span>';
 			html += '<input name="goodsPrice" id="goodsPriceHidden" type="hidden"  value="'+data.message.goodsPrice+'" /></td>';
 			html += '<td style="font-size: 14px;">总数量：<span style="color: red" id="total">'+data.message.total+'</span></td>';
 		}
 		html += '<td></td></tr>';
 		$("#J_autoComplete").html(html);
 	}
 	function isSubmit(){
 		var isSubmit = true;
 		var mobile = $("#mobile").val(); //获取手机号
		var telReg = mobile.match(/^(0|86|17951)?(1[3-9])[0-9]{9}$/);
		var testPriceCheck = /^\d{1,5}(\.\d{1,2})?$/;
		var xnumCheck = /^[0-9]{1,4}$/;

		if($("#storeName").val() == ""){
			layer.tips('请填入店铺名称', '#storeName');
			$("#storeName").focus();
			return false;
		}
		if($.trim(mobile) == ""){
			layer.tips('请填入手机号', '#mobile');
			$("#mobile").focus();
			return false;
		}
		if($.trim(mobile)!='' && !telReg){
			layer.tips('请填入正确的手机号', '#mobile');
			$("#mobile").focus();
			return false;
		}
		if($("#address").val() == ""){
			layer.tips('请填入地址', '#address');
			$("#address").focus();
			return false;
		}
 		$('#J_autoComplete').find('tr').each(function(){
 			if($(this).find('input[name="itemBaseId"]').val() == undefined){
 				return false;
 			}else if($(this).find('input[name="itemBaseId"]').val() == ""){
				layer.tips('商品信息有误请重新选择', $(this).find('input[name="itemBaseName"]'));
				$(this).find('input[name="itemBaseName"]').val("");
				$(this).find('input[name="itemBaseName"]').focus();
				isSubmit = false;
				return false;
			}else if($(this).find('input[name="itemBaseName"]').val() == ""){
				layer.tips('请选择商品', $(this).find('input[name="itemBaseName"]'));
				$(this).find('input[name="itemBaseName"]').focus();
				isSubmit = false;
				return false;
			}else if($(this).find('input[name="price"]').val() == ""){
				layer.tips('请输入商品金额', $(this).find('input[name="price"]'));
				$(this).find('input[name="price"]').focus();
				isSubmit = false;
				return false;
			}else if(!testPriceCheck.test($.trim($(this).find('input[name="price"]').val()))){
				layer.tips('输入商品价格有误，请修改', $(this).find('input[name="price"]'));
				$(this).find('input[name="price"]').focus();
				isSubmit = false;
				return false;
			}else if($.trim($(this).find('input[name="price"]').val()) < 0){
				layer.tips('输入商品价格有误，请修改', $(this).find('input[name="price"]'));
				$(this).find('input[name="price"]').focus();
				isSubmit = false;
				return false;
			}else if($(this).find('input[name="quantity"]').val() == ""){
				layer.tips('请输入商品数量', $(this).find('input[name="quantity"]'));
				$(this).find('input[name="quantity"]').focus();
				isSubmit = false;
				return false;
			}else if(!xnumCheck.test($.trim($(this).find('input[name="quantity"]').val()))){
				layer.tips('输入商品数量有误', $(this).find('input[name="quantity"]'));
				$(this).find('input[name="quantity"]').focus();
				isSubmit = false;
				return false;
			}
		});
 		return isSubmit;
 	}
 	function isSubmitNoLayer(){
 		var isSubmit = true;
 		var mobile = $("#mobile").val(); //获取手机号
		if($("#storeName").val() == ""){
			$("#storeName").focus();
			return false;
		}
		if($.trim(mobile) == ""){
			$("#mobile").focus();
			return false;
		}
		if($("#address").val() == ""){
			$("#address").focus();
			return false;
		}
 		$('#J_autoComplete').find('tr').each(function(){
 			if($(this).find('input[name="itemBaseId"]').val() == ""){
				$(this).find('input[name="itemBaseName"]').val("");
				$(this).find('input[name="itemBaseName"]').focus();
				return false;
			}else if($(this).find('input[name="itemBaseName"]').val() == ""){
				$(this).find('input[name="itemBaseName"]').focus();
				return false;
			}else if($(this).find('input[name="price"]').val() == ""){
				$(this).find('input[name="price"]').focus();
				return false;
			}else if($(this).find('input[name="quantity"]').val() == ""){
				$(this).find('input[name="quantity"]').focus();
				return false;
			}
		});
 		return isSubmit;
 	}
 	function chengeStoreType(isDelete){
 		num = 0;
   		$.ajax({
      			type : "POST",
      			url : "${root}/scms/orderctl/updateStoreType.do?isDelete="+isDelete,
      			dataType : "json",
      			data:$('#J_diaLogAddOrderForm').serialize(),
      			success : function(data) {
      				if (data.success) {
      		         	$(this).parent().empty();
      					var details = data.message.details;
      					var html = '';
      					if(details.length == 0){
      						num = 1;
      						html = addDetail(html, num, null);
      						addCollect(html , null);
      					}else{
      						for (var i = 0; i < details.length; i++) {
								var detail = details[i];
								num = i+1;
								html = addDetail(html, num, detail);
							}
      						addCollect(html ,data);
      					}
      				}else{
      					$("#total").html("0");
          				$("#goodsPrice").html("￥0");
          				$("#goodsPriceHidden").val("0");
      				}
      			},
      			error : function(data) {
      				$("#total").html("0");
      				$("#goodsPrice").html("￥0");
      				$("#goodsPriceHidden").val("0");
      			}
      	});
 	}
</script>
</body>
</html>
