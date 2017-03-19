<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>添加优惠券</title>
    <%@ include file="../common/head.jsp"%>
    
    <script src="${root}/resources/js/comm.js"></script>
    <style>
        .box-goods {
            max-width:598px;
        }
        .goods {
            float: left;
        }
        .goods-op {
            float: left;
            padding-top: 160px;
            width: 60px;
            text-align: center;
        }
        .goods-selected {
            float: left;
        }
        .box {
            box-sizing: border-box;
            border: 1px solid #ccc;
            padding: 8px;
            width: 252px;
            height: 350px;
            overflow: auto;
        }
        .box-item {
            line-height: 22px;
        }
    </style>
</head>
<body class="wrap-bd">
    <div class="mb-default clearfix">
        <div>
	    <a class="crumb" href="#">优惠券管理</a>
	    <a class="crumb crumb-active">添加优惠券</a>
	</div>
        <a class="fr" href="coupon.html">返回优惠券列表</a>
    </div>
    <div>
        <div class="wrap-bd table-border">
            <form action="">
                <div class="required">
                    <label class="label">名称：</label>
                    <input type="text" name="name" class="input input-default" placeholder="最长不超过10个字">
                </div>
                <p class="required">
                    <label class="label">面值：</label>
                    <input type="text" name="name" class="input input-default"> 元
                </p>
                <p class="required">
                    <label class="label">有效期：</label>
                    <input type="text" name="name" class="input input-default"> 天
                </p>
                <p>
                    <label class="label">短信内容：</label>
                    <textarea name="" id="" cols="43" rows="3" class="textarea textarea-default"></textarea>
                    还可以输入<span class="txt-warn">60</span>字
                </p>
                <p class="required">
                    <label class="label">说明：</label>
                    <input type="text" name="name" class="input input-default">
                    还可以输入<span class="txt-warn">12</span>字
                </p>
                <p>
                    <label class="label">支付方式：</label>
                    <input type="checkbox" value="1" name="payment"> 货到付款
                    <input class="ml-default" type="checkbox" value="2" name="payment"> 微信支付
                </p>
                <p>
                    <label class="label">使用条件：</label>
                    <input type="radio" value="0" name="money" checked="checked">
                    单笔满
                    <input type="text" style="width: 80px">
                    元使用
                    <input class="ml-default" type="radio" value="1" name="money"> 不限制
                </p>
                <div>
                    <label class="label">商品/类别：</label>
                    <input type="radio" value="0" name="goods" checked="checked" data-groupname="tab" data-tab="item">&nbsp;所有商品&nbsp;
                    <input type="radio" value="1" name="goods" data-groupname="tab" data-tab="item" class="ml-default">&nbsp;排除类别/商品&nbsp;
                    <input type="radio" value="2" name="goods" data-groupname="tab" data-tab="item" class="ml-default">&nbsp;指定类别/商品&nbsp;
                </div>
                <div class="mt-default">
                    <div data-groupname="tab" data-tab="content"></div>
                    <!-- 排除 -->
                    <div data-groupname="tab" data-tab="content" class="clearfix">
                        <label class="label va-t fl">排除指定商品：</label>
                        <div class="fl" style="width: 90%;">
                            <table class="table-list table-border">
                                <thead class="table-thead">
                                <tr>
                                    <th width="80">类型</th>
                                    <th>类别/商品</th>
                                    <th width="80"></th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody class="table-tbody">
                                <tr>
                                    <td>类别/商品</td>
                                    <td>饮料/碳酸饮料</td>
                                    <td width="80">不参与</td>
                                    <td width="160">
                                        <span class="button button-operate">删除</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="4" class="ta-l">
                                        <span class="button button-operate ml-default">增加</span>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- 指定 -->
                    <div data-groupname="tab" data-tab="content" class="clearfix">
                        <label class="label va-t fl">指定购买商品：</label>
                        <div class="fl" style="width: 90%;">
                            <table class="table-list table-border">
                                <thead class="table-thead">
                                <tr>
                                    <th width="80">类型</th>
                                    <th>类别/商品</th>
                                    <th width="80"></th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody class="table-tbody">
                                <tr>
                                    <td>类别/商品</td>
                                    <td>饮料/碳酸饮料</td>
                                    <td width="80">参与</td>
                                    <td width="160">
                                        <span class="button button-operate">排除商品</span>
                                        <span class="button button-operate">删除</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td>可口可乐</td>
                                    <td width="80">不参与</td>
                                    <td width="160">
                                        <span class="button button-operate">添加</span>
                                        <span class="button button-operate">删除</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="5" class="ta-l">
                                        <span class="button button-operate ml-default">增加</span>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <p>
                    <label class="label">备注：</label>
                    <input type="text" name="" class="input input-default">
                </p>
                <p>
                    <label class="label"></label>
                    <input type="button" class="button button-ok" value="确定">
                    <input type="button" class="button button-cancel" value="取消">
                </p>
            </form>
        </div>
    </div>
    <!-- 选择类别/商品 -->
    <div class="dialog hidden" id="J_dialogAssignProduct">
        <div class="dialog-head">选择类别/商品</div>
        <div class="dialog-body">
            <div class="mb-default">
                <input type="text" id="search_condition" placeholder="商品类别/名称" class="input input-search-text" style="width:520px;">
                <input type="button" class="input input-search-button" onclick="search_ztree('ztree', 'search_condition')" value="搜索">
            </div>
            <div class="box-goods clearfix">
                <div class="goods">
                    <div class="mb-small">商品库</div>
                    <div class="box">
                        <%@ include file="../common/ztree.jsp"%>
                        <script src="${root}/resources/js/ztree-search.js"></script>
                    </div>
                </div>
                <div class="goods-op">
                    <input type="button" value="&gt;" class="button button-white">
                    <input type="button" value="&lt;" class="button button-white mt-default">
                </div>
                <div class="goods-selected">
                    <div class="mb-small">已选参与指定购买商品</div>
                    <div class="box">
                        <div class="box-item">饮料/碳酸饮料</div>
                        <div class="box-item">可口可乐</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="dialog-foot">
            <input type="button" class="dialog-button dialog-ok" value="确定">
            <input type="button" class="dialog-button dialog-cancel" value="取消">
        </div>
    </div>
    <!-- 排除商品 -->
    <div class="dialog hidden" id="J_dialogRemoveGoods">
        <div class="dialog-head">排除商品</div>
        <div class="dialog-body">
            <div class="mb-default">
                <input type="text" class="input input-search-text" style="width:520px;">
                <input type="button" class="input input-search-button" value="搜索">
            </div>
            <div style="max-width:598px;">
                <table class="table-list table-border">
                    <thead class="table-thead">
                    <tr>
                        <th width="50">选择</th>
                        <th width="120">商品条码</th>
                        <th>商品名称</th>
                        <th width="50">规格</th>
                        <th width="50">单位</th>
                    </tr>
                    </thead>
                    <tbody class="table-tbody">
                    <tr>
                        <td>
                            <input type="checkbox">
                        </td>
                        <td>1234567890</td>
                        <td>百事可乐百事可乐百事可乐百事可乐百事可乐百事可乐百事可乐百事可乐百事可乐百事可乐</td>
                        <td>320ml</td>
                        <td>瓶</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="dialog-foot">
            <input type="button" class="dialog-button dialog-ok" value="确定">
            <input type="button" class="dialog-button dialog-cancel" value="取消">
        </div>
    </div>
    <script>
        var zTreeObj;
        var setting = {
            view: {
                showLine: false,
                showIcon: showIconForTree,
                fontCss: setFontCss_ztree
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onCheck: onCheck
            }
        };
        var zNodes = [
            {id:1, pId:0, name: "转角街坊", open: true},
            {id:2, pId:1, name: "子节点1"},
            {id:3, pId:2, name: "子节点11子节点节点11子节点11子节点节点11子节点11子节点节点11"},
            {id:12, pId:1, name: "子节点2"}
        ];
        function showIconForTree(treeId, treeNode) {
            return !treeNode.isParent;
        };
        function onCheck(e, treeId, treeNode) {
            console.log(treeNode.name);
        }
        $(function() {
            zTreeObj = $.fn.zTree.init($("#ztree"), setting, zNodes);

            tab('tab');
            dialogPosCenter('#J_dialogAssignProduct');
            dialogPosCenter('#J_dialogRemoveGoods');


	        //短信字数限制
	        $('#ruleMsg').on('keyup',function(){
	        	var limtNum=60;
	        	var limit= limtNum-$('#ruleMsg').val().length;
	        	$('#ruleMsgError').html(limit);
	        });
	        //说明字数限制
	        $('#ruleRemark').on('keyup',function(){
	        	var limtNum=12;
	        	var limit= limtNum-$('#ruleRemark').val().length;
	        	$('#ruleRemarkError').html(limit);
	        });
	        
	        //取消按钮
	        $('#cancel').click(function(){
	        	location.href='${root}/Customer/voucher/toIndex.do?pageIndex=${pageIndex}';
	        });
	        
	        //金额限制--不限制功能按钮 
	        $('input[type="radio"][name="startPriceRadio"][value="1"]').click(function(){
	        	$('#startPriceError').removeClass('error');
	        	$('#startPriceError').text('');
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
	            	return;
	            }else{
	            	$('#useMoneyError').removeClass('error');
	            	$('#useMoneyError').text('');
	            }
	        	var isNum = /^[1-9]*[1-9][0-9]{0,6}$/;
	        	if(!isNum.test(userMoney)){
	        		$('#useMoneyError').addClass('error');
	            	$('#useMoneyError').text('金额只能为从1开始的正整数!');
	            	return;
	            }else if(userMoney.length>7){
	            	$('#useMoneyError').addClass('error');
	            	$('#useMoneyError').text('金额长度不能超过7位有效数!');
	            	return;
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
	            	return;
	            }else{
	            	$('#useDayError').removeClass('error');
	            	$('#useDayError').text('');
	            }
	        	var isNum = /^[1-9][0-9]{0,2}$/;
	        	if(!isNum.test(useDay)){
	        		$('#useDayError').addClass('error');
	            	$('#useDayError').text('有效期只能为正整数');
	            	return;
	            }else if(useDay>127){
	            	$('#useDayError').addClass('error');
	            	$('#useDayError').text('最大值只能为127天!');
	            	return;
	            }else{
	            	$('#useDayError').removeClass('error');
	            	$('#useDayError').text('');
	            }
	        });
	        //校验起送金额 长度的校验
	        $('#startPrice').blur(function(){
	        	var startPrice = $('#startPrice').val();
	        	if($.trim(startPrice)==''){
	        		$('#startPriceError').addClass('error');
	            	$('#startPriceError').text('订单金额不能为空!');
	            	return;
	            }else{
	            	$('#startPriceError').removeClass('error');
	            	$('#startPriceError').text('');
	            }
	        	var isNum = /^[1-9]*[1-9][0-9]*$/;
	        	if(!isNum.test(startPrice)){
	        		$('#startPriceError').addClass('error');
	            	$('#startPriceError').text('订单金额只能为从1开始的正整数!');
	            	return;
	            }else{
	            	$('#startPriceError').removeClass('error');
	            	$('#startPriceError').text('');
	            }
	        	//优惠劵面额不能大于起送金额
	        	if(parseInt($('#useMoney').val())>parseInt($('#startPrice').val())){
	        		$('#startPriceError').addClass('error');
	            	$('#startPriceError').text('订单金额不能小于优惠劵面值');
	            	return;
	        	}else{
	        		$('#startPriceError').removeClass('error');
	            	$('#startPriceError').text('');
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
	        
	        //新增优惠劵 确定按钮
	        $('#saveButton').click(function(){
	            //表单校验
	            $('#ruleName').trigger('blur');
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
	            if($('input[type="radio"][name="startPriceRadio"][value="1"]').is(':checked')){
	            	$('#startPriceError').removeClass('error');
	            	$('#startPriceError').text('');
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
	    			url : "${root}/Customer/voucher/addSpVoucher.do?timestamp="+(new Date()).valueOf(),
	    			data : $("#addForm").serialize(),
	    			success : function(da) {
	    				if (da.success) {
	    				  alert("保存成功!");
	    				  location.href='${root}/Customer/voucher/toIndex.do';
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

        });
    </script>
</body>
</html>
