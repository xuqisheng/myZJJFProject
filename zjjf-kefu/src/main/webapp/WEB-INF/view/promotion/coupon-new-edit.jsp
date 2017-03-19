<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>编辑优惠券</title>
	<%@ include file="../common/head.jsp"%>
     <%@ include file="../common/autocomplete.jsp"%>
    <script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
    <script src="${root}/resources/js/comm.js"></script>
</head>
<body>
<div class="wrap-bd">
    <div class="mb-small clearfix">
        <b class="fl">编辑优惠券</b>
        <a class="fr" href="${root}/Customer/voucher/toIndex.do">返回优惠券列表</a>
    </div>
    <div>
    <form id="addForm">
    <div>
        <p class="required">
            <label class="label">名称：</label>
            <input class="input input-default" type="text" name="ruleName" value="${spVoucherRule.ruleName}" id="ruleName" maxlength="10">
            <span id="ruleNameError" class="" style="color: red"></span>
            <span class="" style="color: green;">最多10个字</span>
        </p>
        <p class="required">
            <label class="label">面值：</label>
            <input class="input input-default" type="text" name="useMoney" value="${useMoney}" id="useMoney"> 元
            <span id="useMoneyError" class="" style="color: red"></span>
        </p>
        <p class="required">
            <label class="label">有效期：</label>
            <input class="input input-default" type="text" name="useDay" value="${spVoucherRule.useDay}" id="useDay"> 天
            <span id="useDayError" class="" style="color: red"></span>
        </p>
        <p>
	        <label class="label">短信内容：</label>
	       <textarea class="textarea" name="ruleMsg" id="ruleMsg" cols="60" rows="3" maxlength="60">${spVoucherRule.ruleMsg}</textarea>
	               还可以输入<span id="ruleMsgError" style="color: red"></span>字
	    </p>
        <p>
            <label class="label">说明：</label>
            <textarea class="textarea" name="ruleRemark" id="ruleRemark" cols="30" rows="3" maxlength="12">${spVoucherRule.ruleRemark}</textarea>
                         还可以输入<span id="ruleRemarkError" style="color: red"></span>字
        </p>
        <p>
            <label class="label">金额限制：</label>
            <input type="radio" value="0" name="startPriceRadio" checked="checked">
            单笔满
            <input class="input input-default" type="text" style="width: 80px" name="startPrice" value="${startPrice}" id="startPrice">
            元使用
            <span style="color: red" id="startPriceError" calss=""></span>
            <input class="ml-default" type="radio" value="1" name="startPriceRadio"> 不限制
        </p>
        <p>
            <label class="label">支付方式：</label>
            <input type="checkbox" value="4" name="payTypeMethod"> 微信支付
            <input class="ml-default" type="checkbox" value="3" name="payTypeMethod"> 支付宝支付
            <!-- <input class="ml-default" type="checkbox" value="2" name="payTypeMethod"> 货到付款 -->
            <span style="color: red" id="payTypeMethodError" calss=""></span>
        </p>
        <div>
            <label class="label">商品/类别：</label>
            <input type="radio" value="0" name="useItemFlag" checked="checked" />&nbsp;所有商品&nbsp;
            <input class="ml-default" type="radio" value="2" name="useItemFlag" />&nbsp;排除类别/商品&nbsp;
            <input class="ml-default" type="radio" value="1" name="useItemFlag" />&nbsp;指定类别/商品&nbsp;
            <!-- 排除 -->
            <div id="excludeDiv"></div>
            <table class="table-list table-border mt-default mb-default hidden" style="width: 90%; margin-left: 100px" id="excludeProduct">
                <thead class="table-thead">
                <tr>
                    <th width="80">类型</th>
                    <th colspan="2">类别/商品</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody class="table-tbody" id="excludeProductTbody">
                <tr>
                    <td>
                        <select class="select">
                            <option value="0">类别</option>
                            <option value="1">商品</option>
                        </select>
                    </td>
                    <td>
                        <input class="input input-full J_autocomplete" type="text">
                    </td>
                    <td width="80">不参与</td>
                    <td width="180">
                        <span class="button button-operate exclude-del">删除</span>
                        <span class="button button-operate exclude-add">增加</span>
                    </td>
                </tr>
                </tbody>
            </table>
            <!-- 指定 -->
            <div id="assignDiv"></div>
            <table class="table-list table-border mt-default mb-default hidden" style="width: 90%; margin-left: 100px" id="assignProduct">
                <thead class="table-thead">
                <tr>
                    <th width="80">类型</th>
                    <th colspan="2">类别/商品</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody class="table-tbody" id="assignTbody">
                <tr>
                    <td>
                        <select class="select J_select">
                            <option value="0">类别</option>
                            <option value="1">商品</option>
                        </select>
                    </td>
                    <td>
                        <input class="input input-full J_autocomplete type" type="text">
                    </td>
                    <td width="80">参与</td>
                    <td width="180">
                        <span class="button button-operate J_removeGoods">排除商品</span>
                        <span class="button button-operate J_delTrs">删除</span>
                    </td>
                </tr>
                <tr>
                    <td colspan="5" class="tal">
                        <span class="button button-operate ml-default J_addTr">增加</span>
                    </td>
                </tr>
                </tbody>
            </table>
			<p>
	        	<span style="color:red; font-size:12px; margin-left:100px">1、如果某一行不需要，请务必点击删除按钮删除。</span><br>
	        	<span style="color:red; font-size:12px; margin-left:100px">2、指定商品或排除商品，在输入框搜索后，按空格键搜索。</span>
			</p>
        </div>
    </div>
    </form>
    <p>
        <label class="label"></label>
        <c:choose>
          <c:when test="${isEdit==1}">
           <button class="button button-ok" id="saveButton">确定</button>
          </c:when>
          <c:otherwise>
            <span style="color: red;">该优惠劵已经有被用户使用,不能编辑</span>
          </c:otherwise>
        </c:choose>
        <button class="button button-cancel" id="cancel">取消</button>
    </p>
    </div>
    <script>
    var isSubmit=true;//防止重复提交
        $(function(){
            dateRange('.J_TIME_START', '.J_TIME_END');
            /* $('.J_selectAll').on('click', function() {
                $('.J_checkbox').prop('checked',$(this).prop('checked'));
            });
            $('.J_checkbox').on('click', function() {
                if($('.J_checkbox').length == $('.J_checkbox:checked').length) {
                    $('.J_selectAll').prop('checked', 'checked');
                } else {
                    $('.J_selectAll').prop('checked', '');
                }
            }); */
            
            
          //短信字数限制
            $('#ruleMsg').on('keyup',function(){
            	var limtNum=60;
            	var limit= limtNum-$('#ruleMsg').val().length;
            	$('#ruleMsgError').html(limit);
            });
            $('#ruleMsgError').html(60-$('#ruleMsg').val().length);//回显
            //说明字数限制
            $('#ruleRemark').on('keyup',function(){
            	var limtNum=12;
            	var limit= limtNum-$('#ruleRemark').val().length;
            	$('#ruleRemarkError').html(limit);
            });
            $('#ruleRemarkError').html(12-$('#ruleRemark').val().length);
            
            
            //取消按钮
            $('#cancel').click(function(){
            	location.href='${root}/Customer/voucher/toIndex.do?pageIndex=${pageIndex}';
            });
            
            
          //校验名字
            $('#ruleName').blur(function(){
            	if($.trim($('#ruleName').val())==""){
            		$('#ruleNameError').addClass("error");
                	$('#ruleNameError').text('名字不能为空!');
                }else{
                	$('#ruleNameError').removeClass('error');
                	$('#ruleNameError').text('');
                }	
            });
            //校验面值
            $('#useMoney').blur(function(){
            	var userMoney = $('#useMoney').val();
            	if($.trim(userMoney)==''){
            		$('#useMoneyError').addClass('error');
                	$('#useMoneyError').text('金额不能为空!');
                }else{
                	$('#useMoneyError').removeClass('error');
                	$('#useMoneyError').text('');
                }
            	var isNum = /^[1-9]*[1-9][0-9]{0,6}$/;
            	if(!isNum.test(userMoney)){
            		$('#useMoneyError').addClass('error');
                	$('#useMoneyError').text('金额只能为从1开始的正整数!');
                }else if(userMoney.length>7){
                	$('#useMoneyError').addClass('error');
                	$('#useMoneyError').text('金额长度不能超过7位有效数!');
                }else{
                	$('#useMoneyError').removeClass('error');
                	$('#useMoneyError').text('');
                }
            });
          //校验优惠劵有效期 长度的校验
            $('#useDay').blur(function(){
            	var useDay = $('#useDay').val();
            	if($.trim(useDay)==''){
            		$('#useDayError').addClass('error');
                	$('#useDayError').text('优惠劵有效期不能为空!');
                }else{
                	$('#useDayError').removeClass('error');
                	$('#useDayError').text('');
                }
            	var isNum = /^[1-9][0-9]{0,2}$/;
            	if(!isNum.test(useDay)){
            		$('#useDayError').addClass('error');
                	$('#useDayError').text('有效期只能为正整数');
                }else if(useDay>127){
                	$('#useDayError').addClass('error');
                	$('#useDayError').text('最大值只能为127天!');
                }else{
                	$('#useDayError').removeClass('error');
                	$('#useDayError').text('');
                }
            });
            //校验起送金额 长度的校验
            $('#startPrice').blur(function(){
            	var $startPrice = $(this).val();
            	var $startPriceError = $('#startPriceError');
            	if($.trim($startPrice)==''){
            		$startPriceError.addClass('error');
            		$startPriceError.text('订单金额不能为空!');
                }else{
                	$startPriceError.removeClass('error');
                	$startPriceError.text('');
                }
            	var isNum = /^[1-9]*[1-9][0-9]*$/;
            	if(!isNum.test($startPrice)){
            		$startPriceError.addClass('error');
            		$startPriceError.text('订单金额只能为从1开始的正整数!');
                }else{
                	$startPriceError.removeClass('error');
                	$startPriceError.text('');
                }
            	//优惠劵面额不能大于起送金额
            	if(parseInt($('#useMoney').val()) > parseInt($startPrice)){
            		$startPriceError.addClass('error');
            		$startPriceError.text('订单金额不能小于优惠劵面值');
            	}else{
            		$startPriceError.removeClass('error');
            		$startPriceError.text('');
            	}
            });
            //支付方式
            $('input[type="checkbox"][name="payTypeMethod"][value="0"]').click(function(){
            	if($('input[type="checkbox"][name="payTypeMethod"][value="0"]').is(':checked')){
            		$('input[type="checkbox"][name="payTypeMethod"][value!="0"]').prop('checked','');
            		$('#payTypeMethodError').removeClass('error');
                	$('#payTypeMethodError').text('');
            	}
            });
            $('input[type="checkbox"][name="payTypeMethod"][value!="0"]').click(function(){
            	$('input[type="checkbox"][name="payTypeMethod"][value="0"]').prop('checked','');
            	if($(this).is(':checked')){
            		$('#payTypeMethodError').removeClass('error');
                	$('#payTypeMethodError').text('');
            	}
            });
            
          //编辑优惠劵 确定按钮
            $('#saveButton').click(function(){
                //表单校验
                $('#name').trigger('blur');
                $('#useMoney').trigger('blur');
                $('#useDay').trigger('blur');
                
              //优惠劵面值不能大于订单金额限制
                if(parseInt($('#useMoney').val())>parseInt($('#startPrice').val())){
                	$('#startPriceError').addClass('error');
                	$('#startPriceError').text('订单金额不能小于优惠劵面值');
                }else{
                	$('#startPriceError').removeClass('error');
                	$('#startPriceError').text('');
                }
                
                //校验金额限制
                if($('input[type="radio"][name="startPriceRadio"][value="0"]').is(':checked')){
                	$('#startPrice').trigger('blur');
                }
                //校验支付方式
                if($('input[type="checkbox"][name="payTypeMethod"]:checked').length==0){
                	$('#payTypeMethodError').addClass('error');
                	$('#payTypeMethodError').text('请选择至少一种支付方式!');
                }
                if($('span.error').length>0){
                	return;
                }
                $('#saveButton').hide();
            	$.ajax({
        			type : "POST",
        			url : "${root}/Customer/voucher/updateSpVoucherRule.do?id=${id}",
        			data : $("#addForm").serialize(),
        			success : function(da) {
        				if (da.success) {
        				  alert("保存成功!");
        				  location.href='${root}/Customer/voucher/toIndex.do?pageIndex=${pageIndex}';
        				}else{
        					alert(da.message);
        					$('#saveButton').show();
        				}
        			},
        			error : function(da) {
        				alert("失败的请求!");
        				$('#saveButton').show();
        			}
        		});
            });
            
            
            //回显数据
            //回显支付方式
            var payType = "${payType}";
            var payStr = "${payStr}";
            if(payType==0){
               $('input[type="checkbox"][name="payTypeMethod"][value="0"]').prop('checked','checked');
            }else{
              $('input[type="checkbox"][name="payTypeMethod"][value="0"]').prop('checked','');
              var payStrArr = new Array();
              payStrArr =payStr.substring(1,payStr.length-1);
              for(var i=0;i<payStrArr.length;i++){
               $('input[type="checkbox"][name="payTypeMethod"][value="'+payStrArr[i]+'"]').prop('checked','checked');
              }
            };
            //回显金额限制
            var startPriceStr = '${startPriceStr}';
            if(startPriceStr==0){
            	$('input[type="radio"][name="startPriceRadio"][value="0"]').prop('checked','checked');
            }else{
            	$('input[type="radio"][name="startPriceRadio"][value="1"]').prop('checked','checked');
            }
            //回显商品类别
            /*回显商品类别选项 begin*/
            var goodsType = "${goodsType}";
            $('input[type="radio"][name="useItemFlag"][value="'+goodsType+'"]').prop('checked','checked');
            //排除商品回显
            var mgItems = "${mgItems}";
            console.log(mgItems);
          	 var arr = new Array();
          	 arr = mgItems.split(";");
          	 var html = '';
          	 var hiddenHtml='';
            if(goodsType==2){
           	 $('#excludeProduct').show();
           	 for(i=0;i<arr.length;i++){
           		 var tempArr = new Array();
           		 tempArr=arr[i].split("@");
           		 //hiddenHtml+='<input type="hidden" name="itemArr" value="'+arr[i]+'">';
           		 html+='<tr>'
      			         +'<td>'
      			         +'<select class="select">';
           		 if(tempArr[0]=="item"){
           			 html+='<option value="1" selected="selected">商品</option>'
           			      +'<option value="0">类别</option>';
           		 }else{
           			 html+='<option value="0" selected="selected">类别</option>'
           			      +'<option value="1">商品</option>';
           		 }
           		 html+='</select>'
           		      +'</td>'
           		      +'<td>'
           		      +'<input class="input input-full J_autocomplete" type="text" value="'+tempArr[2]+'">'
           		      +'<input type="hidden" name="itemArr" value="'+arr[i]+'">'
                         +'</td>'
                         +'<td width="80">不参与</td>'
                         +'<td width="180">'
                         +'<span class="button button-operate exclude-del">删除</span>'
                         +'<span class="button button-operate exclude-add">增加</span>'
                         +'</td>'
                         +'</tr>'
           		      ;     
           	 }
           	 $('#excludeProductTbody').html(html);
           	 //$('#excludeDiv').html(hiddenHtml);
            }else if(goodsType==1){
           	 $('#assignProduct').show();
           	 for(i=0;i<arr.length;i++){
           		 var tempArr = new Array();
           		 tempArr=arr[i].split("@");
           		 //hiddenHtml+='<input type="hidden" name="productArr" value="'+arr[i]+'">';
           	     if(tempArr[0]=="cat"){
           	    	 html+='<tr>'
           	    	      +'<td>'
           	    	      +'<select class="select">'
           	    	      +  '<option value="0" selected="selected">类别</option>'
           	    	      +  '<option value="1">商品</option>'
           	    	      +  '</select>'
           	    	      +'</td>'
           	    	      +'<td>'
           	    	      +'<input class="input input-full J_autocomplete type" type="text" style="width: 100%" value="'+tempArr[2]+'">'
           	    	      +'<input type="hidden" name="productArr" value="'+arr[i]+'">'
           	    	      +'</td>'
           	    	      +'<td width="80">参与</td>'
           	    	      +'<td width="160">'
           	    	      +'<span class="button button-operate J_removeGoods">排除商品</span>&nbsp;&nbsp;'
           	    	      +'<span class="button button-operate J_delTrs">删除</span>'
           	    	      +'</td>'
           	    	      +'</tr>';
           	     }else{
           	    	 if(tempArr[3]==0){
           	    		 html+='<tr>'
              	    	      +'<td>'
              	    	      +'<select class="select">'
              	    	      +  '<option value="1" selected="selected">商品</option>'
              	    	      +  '<option value="0">类别</option>'
              	    	      +  '</select>'
              	    	      +'</td>'
              	    	      +'<td>'
              	    	      +'<input class="input input-full J_autocomplete type" type="text" style="width: 100%" value="'+tempArr[2]+'">'
              	    	      +'<input type="hidden" name="productArr" value="'+arr[i]+'">'
              	    	      +'</td>'
              	    	      +'<td width="80">参与</td>'
              	    	      +'<td width="160">'
              	    	      +'<span class="button button-operate J_removeGoods">排除商品</span>&nbsp;&nbsp;'
              	    	      +'<span class="button button-operate J_delTrs">删除</span>'
              	    	      +'</td>'
              	    	      +'</tr>';
           	    	 }else{
           	    		 html+='<tr>'
                 	    	      +'<td> </td>'
                 	    	      +'<td>'
                 	    	      +'<input class="input input-full J_autocomplete J_autocomplete" type="text" style="width: 100%" value="'+tempArr[2]+'">'
                 	    	      +'<input type="hidden" name="productArr" value="'+arr[i]+'">'
                 	    	      +'</td>'
                 	    	      +'<td width="80">不参与</td>'
                 	    	      +'<td width="160">'
                 	    	      +'<span class="button button-operate J_delTr">删除</span>&nbsp;&nbsp;'
                 	    	      +'<span class="button button-operate J_removeGoods">增加</span>'
                 	    	      +'</td>'
                 	    	      +'</tr>';
           	    	 }
           	     }
           	 }
           	 html+='<tr>'
                 +'<td class="tal" colspan="5">'
                 +'<span class="button button-operate ml-default J_addTr">增加</span>'
                 +'</td>'
                 +'</tr>'
           	 $('#assignTbody').html(html);
           	 //$('#assignDiv').html(hiddenHtml);
            }
           
           /*回显商品类别选项 end*/
           
            
            /* 商品类别begin */
            //商品类别切换
            var $useItemFlagRadio = $('input[type="radio"][name="useItemFlag"]');
            $useItemFlagRadio.click(function(){
                 if($(this).val()==0){
                	 $('#excludeProduct').hide();//排除商品
                     $('#assignProduct').hide();//指定商品
                 }else if($(this).val()==2){
                	 $('#assignProduct').hide();//指定商品
                	 $('#excludeProduct').show();//排除商品
                 }else{
                	 $('#excludeProduct').hide();//排除商品
                	 $('#assignProduct').show();//指定商品
                 }
            });
           
            //排除商品-删除按钮           
            $('#excludeProduct').on('click','.exclude-del',function(){
            	//if($('#excludeProductTbody tr').length>1){
            	$(this).parent().parent().remove();
            	//}
            });
            //排除商品-增加按钮           
            $('#excludeProduct').on('click','.exclude-add',function(){
            	var html='<tr>'
         	       +'<td><select class="select"><option value="0">类别</option><option value="1">商品</option></select></td>'
         	       +'<td><input class="input-search-text J_autocomplete" type="text" style="width: 100%"></td>'
         	       +'<td width="80">不参与</td>'
         	       +'<td width="160"><span class="button button-operate exclude-del">删除</span> <span class="button button-operate exclude-add">增加</span></td>'
         	       +'</tr>';
         	   $(this).parent().parent().after(html);
            })
            //排除商品,自动填充
	        $('#excludeProduct').on('keydown', '.J_autocomplete', function() {
	        	//selectid=0选中的是类别,1选中的是商品
	        	var selectid = $(this).parent().prev().children('select').val();
	        	var url = '';
	        	if(selectid==0){
	        		url='${root}/Customer/ItemBases/getItemCate.do';
	        	}else{
	        		url='${root}/Customer/ItemBases/getBTypeItemBase.do';
	        	}
	            $(this).autocomplete({
	                serviceUrl: url,
	                paramName: 'name',
	                autoSelectFirst:true,
	                transformResult: function(response) {
	    	            var res = JSON.parse(response)
	    	            if(res.message != null) {
	    		           	return {
	    		            	suggestions: $.map(res.message, function(value, key) {
	    		                 	return { value: value.name, data: value };
	    		            	})
	    		            };
	    	            } else {
	    	            	return {
	    		            	suggestions: [{ value: "无数据"}]
	    		            };
	    	            }
	                },
	                onSelect: function(dd) {
	                	var selecid = $(this).parent().prev().children('select').val();
	                	var html = '';
	                	if(selecid==0){
	                		html='<input type="hidden" name="itemArr" value="cat@'+dd.data.id+'@'+dd.data.name+'">';
	                	}else{
	                		html='<input type="hidden" name="itemArr" value="item@'+dd.data.id+'@'+dd.data.name+'">';
	                	}
	                	$(this).parent().find('input:hidden').remove();
	                	$(this).parent().append(html);
	                }
	           });
	        }
    	)
            /* 商品类别end */
            
            /* 指定商品begin */
            // 选择商品时，排除商品按钮操作显示隐藏
            $('#assignProduct').on('change', '.J_select', function() {
                if($(this).val() == 1) {
                	$(this).parent('td').parent('tr').find('.J_removeGoods').hide();
                } else {
                	$(this).parent('td').parent('tr').find('.J_removeGoods').show();                	
                }
            });
            // 添加类别/商品tr
            var htmlAddTr = '<tr>'
                + '<td>'
                + '<select class="select J_select">'
                    + '<option value="0">类别</option>'
                    + '<option value="1">商品</option>'
                    + '</select>'
                + '</td>'
                + '<td>'
                    + '<input class="input-search-text J_autocomplete type" type="text" style="width: 100%">'
                + '</td>'
                + '<td width="80">参与</td>'
                + '<td width="160">'
                    + '<span class="button button-operate J_removeGoods">排除商品</span>&nbsp;&nbsp;'
                    + '<span class="button button-operate J_delTrs">删除</span>'
                    + '</td>'
                + '</tr>';
            $('#assignProduct').on('click', '.J_addTr', function() {
                $(this).parent('td').parent('tr').before(htmlAddTr);
            });
            // 排除商品tr添加/删除
            var htmlRemoveGoods = '<tr class="J_goods">'
                + '<td>&nbsp;</td>'
                + '<td>'
                + '<input class="input-search-text J_autocomplete" type="text" style="width: 100%">'
                + '</td>'
                + '<td width="80">不参与</td>'
                + '<td width="160">'
                + '<span class="button button-operate J_delTr">删除</span>&nbsp;&nbsp;'
                + '<span class="button button-operate J_removeGoods">增加</span>'
                + '</td>'
                + '</tr>';
            $('#assignProduct').on('click', '.J_removeGoods', function() {
                $(this).parent('td').parent('tr').after(htmlRemoveGoods);
            }).on('click', '.J_delTr', function() {
            	var removeObj = $(this).parent().prev().prev().children('input').val();
            	$('input[name="productArr"]').each(function(){
            		var temp = $(this).val();
            		var arr = new Array();
            		arr = temp.split("@");
            		if(arr[2]==removeObj){
            			$(this).remove();
            		}
            	});
                $(this).parent('td').parent('tr').remove();
            });
            // 删除商品tr/删除类别及排除商品tr
            var flag = false;
            $('#assignProduct').on('click', '.J_delTrs', function() {
                flag = $(this).parent('td').parent('tr').next().hasClass("J_goods");
                if(!flag) {
                	var removeObj = $(this).parent().prev().prev().children('input').val();
                	$('input[name="productArr"]').each(function(){
                		var temp = $(this).val();
                		var arr = new Array();
                		arr = temp.split("@");
                		if(arr[2]==removeObj){
                			$(this).remove();
                		}
                	});
                    $(this).parent('td').parent('tr').remove();
                } else {
                    alert('请先删除排除商品！');
                }
            });
            
	       //指定商品,自动填充
	        $('#assignProduct').on('keydown', '.J_autocomplete', function() {
	        	var url = '';
	        	if($(this).hasClass('type')){
	        	  var selectid = $(this).parent().prev().children('select').val();
	        	  if(selectid==0){
		        		url='${root}/Customer/ItemBases/getItemCate.do';
		        	}else{
		        		url='${root}/Customer/ItemBases/getBTypeItemBase.do';
		        	}
	        	}else{
	        		url='${root}/Customer/ItemBases/getBTypeItemBase.do';
	        	}
	            $(this).autocomplete({
	                serviceUrl: url,
	                paramName: 'name',
	                autoSelectFirst:true,
	                transformResult: function(response) {
	    	            var res = JSON.parse(response)
	    	            if(res.message != null) {
	    		           	return {
	    		            	suggestions: $.map(res.message, function(value, key) {
	    		                 	return { value: value.name, data: value };
	    		            	})
	    		            };
	    	            } else {
	    	            	return {
	    		            	suggestions: [{ value: "无数据"}]
	    		            };
	    	            }
	                },
	                onSelect: function(dd) {
	                	var html = '';
	                	if($(this).hasClass('type')){
	                	var selecid = $(this).parent().prev().children('select').val();
	                	if(selecid==0){
	                		html='<input type="hidden" name="productArr" value="cat@'+dd.data.id+'@'+dd.data.name+'@0">';//0表示参与
	                	}else{
	                		html='<input type="hidden" name="productArr" value="item@'+dd.data.id+'@'+dd.data.name+'@0">';
	                	}
	                	}else{
	                		html='<input type="hidden" name="productArr" value="item@'+dd.data.id+'@'+dd.data.name+'@1">';//1表示不参与
	                	}
	                	$(this).parent().find('input:hidden').remove();
	                	$(this).parent().append(html);
	                }
	           });
	        })
            /* 指定商品end */
        });
    </script>
</body>
</html>