<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>转角店宝</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <%@ include file="../common/head.jsp"%>
    <script src="${root}/resources/js/comm.js"></script>
    <%@ include file="../common/autocomplete.jsp"%>
    <script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
	<script src="${root}/resources/js/comm.js?vvv"></script>
    <style type="text/css">
    	.commodityName {
    		background-color:#FFFFFF;
    		border:none;
    		outline:medium;
    	}
    </style>
</head>
<body>
<!-- 库存列表begin -->
<div class="wrap-bd">
    <div class="mb-small clearfix">
        <div class="fl title">库存列表</div>
        <c:if test="${ERP_WAREHOUSE_SESSION_KEY == null}">
            <div class="fr">
                <button class="button" id="J_addStock">商品入库</button>
            </div>
        </c:if>
    </div>
    <div class="op-section clearfix">
       <form action="" name="orderid" method="post" id="fm">
        <input class="input-search-text" type="text" name="nameAndMdseId" id="nameAndMdseId" value="${nameAndMdseId }" maxlength="50" value="" placeholder="商品编号/名称">
        <label class="ml-default">库存：</label>
        <input type="text" maxlength="5" name="beginStock" id="beginGoodsStock" value="" class="input-search-date" placeholder="" /> -
        <input type="text" maxlength="5" name="endStock" id="endGoodsStock" value="" class="input-search-date" placeholder="" />
        <input type="button" class="input-search-button ml-default"  id="queryButton" value="搜索" />
       </form>
    </div>
    <div>
       <table class="table-list table-border" id="abc">
         <thead>
          <tr>
              <th>商品编号</th>
              <th>商品名称</th>
              <th>仓库</th>
              <!-- <th>仓库类型</th> -->
              <th>可售量</th>
              <th>锁定</th>
              <th>待发货</th>
              <th>在途</th>
              <th>规格</th>
              <th>单位</th>
              <th>操作</th>
          </tr>
         </thead>
         <tbody class="table-tbody">

         </tbody>
       </table>
    </div>
    <%@ include file="../common/pagination.jsp"%>
</div>
<script>
	$("#jpagination").pagination({
	    pageSize: 10,
	    remote: {
	        url: '${root}/ERPStockManager/getSupplierStock.do',
	        params: $('#fm').serializeArray(),
	        success: function(data) {
	            var html='';
	            $.each(data.list, function(i,item) {
	            	html+='<tr>'
	            		html+='<td><span class="J_mdseId">'+item.mdseId+'</span><input type="hidden" class="J_cid" value="'+item.itemBaseId+'" ></td>'
	            		html+='<td><span class="J_name">'+item.goodsName+'</span></td>'
		                html+='<td>'+item.whName+'<input type="hidden" class="J_whId" value="'+item.warehouseId+'" ></td>';
		                /* html+='<td>'+item.typeMgName+'<input type="hidden" class="J_typeMg" value="'+item.typeMg+'" ></td>'; */
		                html+='<td>'+item.stockNum+'</td>';
		                html+='<td>'+item.lockStock+'</td>';
		                html+='<td>'+item.examineStock+'</td>';
		                html+='<td>'+item.transportStock+'</td>';
		                html+='<td>'+item.spec+'</td>';
		                if(item.pkg==null || item.pkg=='' || item.pkg=='null'){
		                	html+='<td>箱</td>';
		                }else{
		                	html+='<td>'+item.pkg+'</td>';
		                }
                        html+='<td>';
                        html+='<a href="javascript:void(0)" class="button button-operate kJ_editStock">库存修正</a>&nbsp;&nbsp;';
                        html+='<a class="button button-operate"  href="${root}/ERPStockManager/goGoodsStockPage/1.do?goodsName='+item.goodsName+'&mdseId='+item.mdseId+'&spec='+item.spec+'&pkg='+item.pkg+'&warehouseId='+item.warehouseId+'&itemBaseId='+item.itemBaseId+'">库存明细</a>&nbsp;&nbsp;';
                        html+='<a class="button button-operate"  href="${root}/ERPStockManager/goGoodsStockPage/2.do?goodsName='+item.goodsName+'&mdseId='+item.mdseId+'&spec='+item.spec+'&pkg='+item.pkg+'&warehouseId='+item.warehouseId+'&itemBaseId='+item.itemBaseId+'">操作明细</a>&nbsp;&nbsp;';
                        html+='</td></tr>';
	            });
                if(html == "") {
                 	html = '<tr><td colspan="10" class="no-data"></td></tr>';
                }
	            $('#abc .table-tbody').html(html);
	        },
	        totalName:'totalSize'
	    }
	});
	//条件验证
	$('#queryButton').on('click', function(e) {
 		var nameAndMdseId = $("#nameAndMdseId").val();
		var beginGoodsStock = $("#beginGoodsStock").val();
		var endGoodsStock = $("#endGoodsStock").val();

		var beginGoodsStockCheck = /^-?[0-9]\d*$/;
		if(!beginGoodsStockCheck.test(beginGoodsStock)){
			$("#beginGoodsStock").val("");
		}
 		var endGoodsStockCheck = /^-?[0-9]\d*$/;
		if(!endGoodsStockCheck.test(endGoodsStock)){
			$("#endGoodsStock").val("");
		}

      	 $("#jpagination").pagination('setParams', $('#fm').serializeArray());
      		$("#jpagination").pagination('setPageIndex', 0);
      	 $("#jpagination").pagination('remote');
     });

</script>
<!-- 库存列表end -->
<!-- 库存修正begin -->
<div class="cover-all"></div>
<div class="dialog hidden" id="J_dialogEditStock">
    <form id="e_fm">
        <div class="dialog-head"><div class="dialog-title">库存修正</div><div class="dialog-close"></div></div>
        <div class="dialog-body dialog-padding">
            <p>
                <label class="label">商品名称：</label>
                <!-- <input type="text" maxlength="50" class="input" style="width:360px"> -->
                <span id="name"></span>
                <input type="hidden" id="X_mdseId"/>
                <input type="hidden" name="itemBaseId" id="itemBaseId"/>
            </p>
            <p>
                <label class="label">修正类型：</label>
                <input type="radio" value="3" name="status" checked="checked"> 入库
                <input type="radio" value="2" name="status" class="ml-default"> 出库
            </p>
            <p>
                <label class="label">选择仓库：</label>
                <select id="whId" style="width: 180px;overflow: auto;">
                	<option value="">请选择</option>
                </select>

                <select id="whArId" name=""  style="width: 180px;overflow: auto;">
                	<option value="">请选择</option>
                </select>
                <select id="whPoId" name="warehouseId" style="width: 180px;overflow: auto;">
                	<option value="">请选择</option>
                </select>
                <!-- <input type="hidden" name="typeMg" id="typeMg"> -->
            </p>
            <p>
                <label class="label">数量：</label>
                <input type="text" name="operateQuantity" id="operateQuantity" class="input" style="width:360px">
            </p>
            <p>
                <label class="label">生产日期：</label>
                <input type="text" name="productionDate" id="productionDate" class="input-search-date J_DATE_START">
            </p>
            <p>
                <label class="label va-t">备注：</label>
                <textarea class="textarea" name="remark" id="remark" maxlength="225" cols="22" rows="2" style="width:360px"></textarea>
            </p>
        </div>
        <div class="dialog-foot">
            <input type="button" class="dialog-button dialog-ok" id="okSubmit" value="确定"">
            <span class="ml-default"></span>
            <input type="button" class="dialog-button dialog-cancel" value="取消" >
            <input type="reset" style="display:none;" />
        </div>
    </form>
</div>
<script type="text/javascript">
$(function(){
/* 		//库存修正时查出所有的商品名称
        $('#commodityName').autocomplete({
            serviceUrl: '${root}/scms/plantItem/getCommodityName.do',
            paramName: 'commodityName',
            transformResult: function(response) {
	            var res = JSON.parse(response)
	            if(res.message != null) {
		           	return {
		            	suggestions: $.map(res.message, function(value, key) {
		                 	return { value: value.name+"--"+value.spec, data: value };
		            	})
		            };
	            } else {
	            	return {
		            	suggestions: [{ value: "无数据"}]
		            };
	            }
            },
            onSelect: function(dd) {
            	if(dd.value==="无数据") {
               		$(this).val("");
                	return;
            	}
				$("#xitemBaseId").val(dd.data.itemBaseId);
            }
       });
	})
 */
 dateRange('.J_DATE_START',1);
//修正商品库存后将操作记录插入数据库
	$("#okSubmit").on('click',function(){
		if($("#itemBaseId").val()==null || $("#itemBaseId").val()==""){
			//layer.tips('此商品不存在！', '#commodityName');
			alert("商品不存在！");
			return ;
		}
		if($("#whPoId").val() == ''){
			alert("请选择仓库！");
			return ;
		}
		var xnumCheck = /^[0-9]{1,5}$/;
		if($.trim($("#operateQuantity").val())==""){
			//layer.tips('请输入修改数量！', '#xnum');
			alert("请输入修改数量！");
			return ;
		}else if(!xnumCheck.test($.trim($("#operateQuantity").val())) || $.trim($("#operateQuantity").val())==0){
			//layer.tips('修正时数量为1-99999的整数！', '#xnum');
			alert("修正时数量为1-99999的整数！");
			return;
		}
		if($.trim($("#remark").val()).length>225){
			//layer.tips('备注不能超过225个字符!', '#xremark');
			alert("备注不能超过225个字符!");
			return;
		}

		$(this).attr({ "disabled": "disabled" });
	 	$.post("${root}/ERPStockManager/updateStock.do",
				$("#e_fm").serializeArray(),
				function(date){
					if(date.success){
						alert(date.message);
						location.href = "${root}/ERPStockManager/stockPage.do?nameAndMdseId="+$("#X_mdseId").val();
					}else{
						//layer.msg('修正失败！', {icon: 5});
						alert(date.message);
					}
			});
	});
});
</script>
<!-- 库存修正end -->
<%-- <!-- 单商品入库begin -->
<div class="dialog hidden" id="J_singleGoodsAddStock">
     <form action="${root}/scms/plantItem/CommodityRuKu.do" method="post" id="kj_rFormParam">
        <div class="dialog-head"><div class="dialog-title">商品入库</div><div class="dialog-close"></div></div>
        <div class="dialog-body dialog-padding">
            <p>
                <label class="label">商品名称：</label>
                <span id="kj_rname"></span>
            </p>
            <p>
                <label class="label">入库数量：</label>
                <input type="text" value="" name="inputGoodsStock" id="kj_inputGoodsStock" class="input" style="width:360px" maxlength="5">
            </p>
            <p>
                <label class="label">进货价：</label>
                <input type="text" value="" name="inputPlantDisPrice" id="kj_inputPlantDisPrice" class="input" style="width:360px">
            </p>
        </div>
        <div class="dialog-foot">
            <input type="hidden" name="ritemBaseId" value="" id="kj_ritemBaseId">
            <input type="button" class="dialog-button dialog-ok" id="kj_okSubmit" value="确定">
            <input type="button" class="dialog-button dialog-cancel ml-default" value="取消">
            <input type="reset" class="hidden" />
        </div>
    </form>
</div> --%>

<script>
//级联
function caseCode(upId){
	var html = '<option value="">请选择</option>';
	$.ajax({
		url:'${root}/erp/warehouse/getWhpositionBySupplierId/'+upId+'.do',
		type:'post',
		dataType:'json',
		async: false,
		success:function(data){
			if(data.success){
				$.each(data.message,function(i,item){
					html += '<option value="'+item.id+'">'+item.name+'</option>';
				});
			}
		},
		error:function(error){
			alert('请求异常');
		}
	});
	return html;
}


$(function() {
		$('.table-list').eq(0).on('click', 'td', function() {
            $(this).parent().css({
                'background': '#009dd9',
                'color': 'white'
            }).siblings().css({
                'background': '#fff',
                'color': 'black'
            })
        });
	dialogPosCenter('#J_singleGoodsAddStock');
	dialogPosCenter('#J_dialogEditStock');
	$('.dialog').on('click', '.dialog-cancel, .dialog-close', function() {
    	$("#whId").removeAttr("disabled");
        $('.dialog, .cover-all').fadeOut();
		$("input[type=reset]").trigger("click");//触发reset按钮
		$('#J_autoComplete tr:not(:first):not(:last)').remove();
	});

    $('#abc').on('click','.kJ_editStock', function() {
    	var $tr = $(this).parent("td").parent("tr");
    	//仓库select
    	var html = caseCode(0);
    	$('#whId').html(html);
    	$('#whId').val($tr.find(".J_whId").val());
    	$('#whId').attr("disabled","disabled");
    	//拉取库区
    	var html = caseCode($tr.find(".J_whId").val());
    	$('#whArId').html(html);

    	$("#itemBaseId").val($tr.find(".J_cid").val());
    	$("#name").text($tr.find(".J_name").text());
    	$("#X_mdseId").val($tr.find(".J_mdseId").text());
        $('#J_dialogEditStock, .cover-all').fadeIn();
    });
   /*  $('#abc').on('click','.kJ_addStock', function() {
    	var $tr = $(this).parent("td").parent("tr");
    	$("#kj_ritemBaseId").val($tr.find(".J_cid").val());
    	$("#kj_rname").text($tr.find(".kj_name").text());
    	$('#J_singleGoodsAddStock, .cover-all').fadeIn();
    }); */
   /*  $('#J_editStock').on('click', function() {
    	//仓库select
    	var html = caseCode(0);
    	$('#whId').html(html);
        $('#J_dialogAddStock').fadeOut();
        $('#J_dialogEditStock, .cover-all').fadeIn();
    }); */
    /* 级联 */
    $('#whId').on('change',function(){
    	if($(this).val()==''){
    		$('#whPoId').html('<option value="">请选择</option>');
    	}
    	var html = caseCode($(this).val());
    	$('#whArId').html(html);
    });
    $('#whArId').on('change',function(){
    	var html = caseCode($(this).val());
    	$('#whPoId').html(html);
    });

});
    /* 级联 */
    //快捷添加库存
    /* $('#kj_rFormParam').on('click', '#kj_okSubmit', function () {
    	var kj_ritemBaseId = $("#kj_ritemBaseId").val();
    	if(kj_ritemBaseId==""){
    		alert("商品部存在");
    		return;
    	}
    	var kj_inputGoodsStock = $("#kj_inputGoodsStock").val();
    	var stockCheck = /^[0-9]{1,5}$/;
    	if(kj_inputGoodsStock==""){
    		alert("请输入入库数量");
    		return;
    	}else if(!stockCheck.test(kj_inputGoodsStock) || kj_inputGoodsStock==0){
    		alert("入库数量为1-99999的整数！");
    		return;
    	}
    	var kj_inputPlantDisPrice = $("#kj_inputPlantDisPrice").val();
    	var priceCheck =  /^\d{1,5}(\.\d{1,2})?$/;
    	if(kj_inputPlantDisPrice==""){
    		alert("请输入进货价");
    		return;
    	}else if(!priceCheck.test(kj_inputPlantDisPrice)){
    		alert("请输入正确的进货价");
    		return;
    	}

    	$('#kj_rFormParam').submit();
    }); */



</script>
<!-- 单商品入库end -->
<!-- 商品入库begin -->
<div class="dialog hidden" id="J_dialogAddStock">
    <form id="rFormParam">
        <div class="dialog-head"><div class="dialog-title">商品入库</div><div class="dialog-close"></div></div>
        <div class="dialog-body dialog-padding">
	        <div style="max-height: 350px;overflow:auto;">
	           <table class="table-list table-border">
	             <thead class="table-thead">
	              <tr class="table-header">
	                  <th width="60">序号</th>
	                  <th class="ta-l" width="350" style="text-align:center">商品</th>
	                  <th width="90">入库数量</th>
	                  <th width="90">进货价</th>
	                  <th width="80">操作</th>
	              </tr>
	             </thead>
	             <tbody class="table-tbody" id="J_autoComplete">
	                <tr>
	                    <td>1</td>
	                    <td class="ta-l">
	                    	<input type="text" class="input J_rcommodityName" style="width: 350px" maxlength="50" name="rcommodityName" placeholder="请输入商品关键字" data-shortcut="enter">
	                    	<input type="hidden" name="erpPlantItemLogs[0].itemBaseId" class="p_itemBaseId" value=""/>
	                    	<select class="p_whId" id="p_whId"><!-- <option value="">请选择</option> --></select>
	                    	<select class="p_whArId" id="p_whArId"><!-- <option value="">请选择</option> --></select>
	                    	<select class="p_whPoId" id="p_whPoId" name="erpPlantItemLogs[0].warehouseId"><!-- <option value="">请选择</option> --></select>
	                    </td>
	                    <td><input type="text" class="input p_operateQuantity" name="erpPlantItemLogs[0].operateQuantity" maxlength="5" style="width: 50px" data-shortcut="enter"></td>
	                    <td><input type="text" class="input p_areaPrice" name="erpPlantItemLogs[0].areaPrice" style="width: 50px" data-shortcut="enter" placeholder=""></td>
	                    <td class="J_delInput cur-p">删除</td>
	                </tr>
	                <tr>
	                    <td colspan="5" class="ta-l"><img id="J_addInput" src="${root}/resources/images/add-input.png" alt="" class="ml-default cur-p"></td>
	                </tr>
	             </tbody>
	           </table>
	        </div>
        </div>
        <div class="dialog-foot">
                <input type="button" class="dialog-button dialog-ok" value="确定" id="okDialog">
                <input type="button" class="dialog-button dialog-cancel ml-default" value="取消">
                <input type="reset" class="hidden">
        </div>
    </form>
</div>
<div class="cover-all"></div>
<script>
	// 拦截form表单
    $('#rFormParam').on('click', '#okDialog', function () {
		  var xBreak=true;
			$(".p_itemBaseId").each(function(){
				if($(this).val()==null || $(this).val()=="" || $(this).siblings(".J_rcommodityName").val()==null ||$(this).siblings(".J_rcommodityName").val()==""){
					//layer.tips('此商品不存在！', $(this).siblings("input[name=rcommodityName]"));
					alert("商品不存在！");
					xBreak = false;
					return false;
				}
			});
			if(xBreak==false){
				return;
			}
			var xBreakA=true;
			var inputGoodsStock = /^[0-9]{1,5}$/;
			$(".p_operateQuantity").each(function(){
				if($(this).val()==null || $(this).val()=="" || !inputGoodsStock.test($(this).val()) ||$(this).val()==0){
					//layer.tips('入库数量为1-99999的整数！',this);
					alert("入库数量为1-99999的整数！");
					xBreakA = false;
					return false;
				}
			})
			if(xBreakA == false){
				return;
			}
			var xBreakB = true;
			var inputPlantDisPrice =  /^\d{1,5}(\.\d{1,2})?$/;
			$(".p_areaPrice").each(function(){
				if($(this).val()==null || $(this).val()=="" || !inputPlantDisPrice.test($(this).val())){
					//layer.tips('进货价可能没输,或者进货价格式不正确！',this);
					alert("进货价可能没输,或者进货价格式不正确！");
					xBreakA = false;
					return false;
				}
			})
			if(xBreakA == false){
				return;
			}
		    if(xBreak==true && xBreakA==true &&xBreakB==true){
		    	var $tr = $(this);
		    	$tr.attr({ "disabled": "disabled" });
				$.ajax({
					url:'${root}/ERPStockManager/goosInputStock.do',
					type:'post',
					dataType:'json',
					data:$("#rFormParam").serializeArray(),
					success:function(data){
						alert(data.message);
						if(data.success){
							location.href="${root}/ERPStockManager/stockPage.do";
						}
						$tr.removeAttr("disabled");
					},
					error:function(error){
						alert("请求失败");
					}
				});
		    }
    })

    function caseCode1(upId){
    		var html = '';
    		$.ajax({
				url:'${root}/erp/warehouse/getWhpositionBySupplierId/'+upId+'.do',
				type:'post',
				dataType:'json',
				async: false,
				success:function(data){
					if(data.success){
						$.each(data.message,function(i,item){
							if(i == 0){
								html += '<option selected="selected" value="'+item.id+'">'+item.name+'</option>';
							}else{
								html += '<option value="'+item.id+'">'+item.name+'</option>';
							}
						});
					}
				},
				error:function(error){
					alert('请求异常');
				}
			});
    		return html;
    	}

    $(function() {
        dialogPosCenter('#J_dialogEditStock');
        dialogPosCenter('#J_dialogAddStock');
        var num = 1;
        $('#J_addInput').on('click', function() {
        	num++
            $(this).parent().parent().before('<tr class="J_rtr">'
                + '<td class="num">'+num+'</td>'
                + '<td class="ta-l">'
                + '<input type="text" class="input J_rcommodityName" style="width: 350px" maxlength="50" name="rcommodityName" placeholder="请输入商品关键字" data-shortcut="enter">'
                + '<input type="hidden" name="erpPlantItemLogs['+(num-1)+'].itemBaseId" class="p_itemBaseId"/>'
                + '<select class="p_whId" ><!-- <option value="">请选择</option> --></select>'
                + '<select class="p_whArId" ><!-- <option value="">请选择</option> --></select>'
                + '<select class="p_whPoId" name="erpPlantItemLogs['+(num-1)+'].warehouseId"><!-- <option value="">请选择</option> --></select>'
                + '<td><input type="text" class="input p_operateQuantity" name="erpPlantItemLogs['+(num-1)+'].operateQuantity" maxlength="5" style="width: 50px" data-shortcut="enter"></td>'
                + '<td><input type="text" class="input p_areaPrice" name="erpPlantItemLogs['+(num-1)+'].areaPrice" style="width: 50px" data-shortcut="enter" placeholder=""></td>'
                + '<td class="J_delInput cur-p">删除</td>'
                + '</tr>');
        	var $tr = $(this).parent().parent().prev();
        	$tr.children('td').eq(1).focus();
        	$tr.find('.p_whId').html(caseCode1(0));
        	$tr.find('.p_whId').hide();
        	$tr.find('.p_whArId').html(caseCode1($tr.find('.p_whId').val()));
        	$tr.find('.p_whArId').hide();
        	$tr.find('.p_whPoId').html(caseCode1($tr.find('.p_whArId').val()));
        	$tr.find('.p_whPoId').hide();

        	dialogPosCenter('#J_dialogAddStock');
        });
        $('#J_dialogAddStock').on('click', '.J_delInput', function() {
        	var sort = [];
        	sort = $(this).parent('tr').nextAll('tr').children('.num');
        	$.each(sort, function(i, n) {
        		n.innerHTML = parseInt(n.innerHTML) - 1;
        	});
        	num = $(this).parent('tr').siblings('tr').children('.num').last().text();
        	$(this).parent().remove();
        	dialogPosCenter('#J_dialogAddStock');
        });

        $('#J_addStock').on('click', function() {
        	$('#p_whId').html(caseCode1(0));
        	$('#p_whId').hide();
        	$('#p_whArId').html(caseCode1($('#p_whId').val()));
        	$('#p_whArId').hide();
        	$('#p_whPoId').html(caseCode1($('#p_whArId').val()));
        	$('#p_whPoId').hide();
            $('#J_dialogEditStock').fadeOut();
            $('#J_dialogAddStock, .cover-all').fadeIn();
            $(".J_rcommodityName").focus();
        });

        $('.dialog').on('click', '.dialog-cancel, .dialog-close', function() {
        	/* $("#commodityName").removeAttr("disabled");
        	$("#commodityName").removeClass("commodityName")
            $('.dialog, .cover-all').fadeOut();
			$("input[type=reset]").trigger("click");//触发reset按钮
			$('#J_autoComplete tr:not(:first):not(:last)').remove(); */
			//走链接

			num=1 //删除用的
		});

        /* 商品入库时查出所有的商品名称 */
		$('#J_autoComplete').on('keydown', function() {
			var itemBaseId = "";
	        $('.J_rcommodityName').autocomplete({
	            serviceUrl: '${root}/scms/plantItem/getAllCommodityNameAndItemBaseId.do',
	            paramName: 'rcommodityName',
	            transformResult: function(response) {
	                var res = JSON.parse(response)
	                if(res.message!=null){
			           	 return {
			                    suggestions: $.map(res.message, function(value, key) {
			                    	return { value: value.name+"--"+value.spec, data: value };
			                    })
			                };
	                }else{
	                	return{
	                		suggestions:[{value:"无数据"}]
	                	};
	                }
	            },
	            onSelect: function(dd) {
	            	if(dd.value=="无数据"){
	            		$(this).val("");
	            		return;
	            	}
					$(this).siblings('.p_itemBaseId').val(dd.data.itemBaseId);
					$(this).parent('td').next('td').find('.p_operateQuantity').focus();
					//$(this).parent('td').next('td').find('input[name="inputGoodsStock"]').val("1");
					 $(".J_rcommodityName").each(function(){
						 if($(this).val()==null || $(this).val()==""){
							 $(this).siblings('.p_itemBaseId"]').val("");
						 }
					 })

					if($(this).siblings('.p_itemBaseId').val()!=null || $(this).siblings('.p_itemBaseId').val()!=""){
						$(this).siblings('.p_itemBaseId').val("");
					}
					var flag = true;
					 $(".p_itemBaseId").each(function(){
		            	if($(this).val()==dd.data.itemBaseId){
		            		//layer.msg("此商品有重复");
		            		alert("此商品已存在！");
		            		flag = false;
		        			return false;
		            	}
		            });
					 if(flag == false){
						 $(this).val("");
						 $(this).focus();
						 $(this).siblings('.p_itemBaseId').val("");
					 }else{
						 $(this).siblings('.p_itemBaseId').val(dd.data.itemBaseId);
					 }
	            }
	       });

		});
    })

   $('html, body').on('keydown', 'input[data-shortcut="enter"]:last', function(ev) {
      if(ev.keyCode == "13" || ev.keyCode == "9") {
          $('#J_addInput').trigger('click');
      }
  });
</script>
<!-- 商品入库end -->
</body>
</html>
