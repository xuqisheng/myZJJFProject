<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>促销管理</title>
<%@ include file="../common/head.jsp"%>

<script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
<script src="${root}/resources/js/comm.js?vvv"></script>
</head>
<body>
	<div class="wrap-bd">
		<div>
			<span style="font-size: 14px;">当前位置：</span> <a class="crumb">促销管理</a><span
				class="crumb crumb-active">活动列表</span>
		</div>
		<div class="title mb-small">转角活动列表</div>
		<!-- <div class="op-section mb-small">
        <form method="post" action="#" id="formconditon">
            <label>活动时间：</label>
            <input type="text" name="ruleStart" value="" class="input-search-date J_timeS"> -
            <input type="text" name="ruleEnd" value="" class="input-search-date J_timeE">
            <label class="ml-default">活动类型：</label>
            <select id="ruleTypeSelect" name="ruleType">
            <option value="">全部</option>
			<option  value="1">注册送优惠劵</option>
			<option value="2">满送优惠劵</option>
			<option value="3">满减</option>
			<option value="9">满送优惠劵+商品</option>
			<option value="10">满减金额+商品</option>
			<option value="11">满送商品</option>
			<option value="12">满购商品送商品</option>
			</select>
            <input type="text" name="ruleName"  value="" placeholder="活动名称" class="input-search-text ml-default" onkeydown="return banInputSapce(event);">
            <input value="搜索" type="button"  class="input-search-button ml-default" id='sub'>
        </form>
    </div> -->
		<table class="table-list table-border">
			<thead class="table-thead">
				<tr>
					<th rowspan="2">编号</th>
					<th rowspan="2" width="200">名称</th>
					<th rowspan="2">类型</th>
					<th rowspan="2">状态</th>
					<th colspan="2">费用分摊比例</th>
					<th rowspan="2">使用金额限制</th>
					<th rowspan="2">开始时间</th>
					<th rowspan="2">结束时间</th>
					<th rowspan="2">操作</th>
				</tr>
				<tr>
					<th>转角支出</th>
					<th>批发商支出</th>
				</tr>
			</thead>
			<tbody class="table-tbody" id="J_tableBody">

			</tbody>
		</table>
		<%@ include file="../common/pagination.jsp"%>
	</div>
	<!-- 参加活动客户dialog -->
	<form action="#" id="formId">
		<input type="hidden" value="" name="spVoucherActiveId"
			id="spVoucherActiveId">
		<div class="dialog hidden" id="J_dialogJoinCustomer"
			style="min-width: 600px;">
			<div class="dialog-head">
				参加活动客户 <span class="dialog-close"></span>
			</div>
			<div class="dialog-body">

				<div id="J_zssp">
					<label class="label va-t">赠送商品</label>
					<div style="display: inline-block; width: 600px">
						<div class="mb-default">
							<span class="button-operate" id="J_selectGoods">+选择商品</span>
						</div>
						<div style="max-height: 220px; overflow: auto;" id="product">


						</div>
					</div>
				</div>


				<!--
        <div class="mt-default">
            <label class="label">参与客户</label>
             <input type="radio" name="c" data-groupname="aa" data-tab="item"> 全部客户
            <input type="radio" name="c" data-groupname="aa" data-tab="item" class="ml-default"> 部分客户
        </div>
        <div data-groupname="aa" data-tab="content"></div>
        -->
				<div data-groupname="aa" data-tab="content">
					<label class="label"></label>
					<div
						style="display: inline-block; width: 600px; max-height: 220px; overflow: auto;"
						id="group1">
						<!-- <p>指定分组（2 / 5）</p> -->
						<!--  <p>
                    <input type="checkbox"> 分组名1
                    <input type="checkbox" class="ml-default" > 分组名2
                    <input type="checkbox" class="ml-default"> 分组名3
                    <input type="checkbox" class="ml-default"> 分组名4
                    <input type="checkbox" class="ml-default"> 分组名5
                </p>
                -->
					</div>
				</div>
			</div>
			<div class="dialog-foot">
				<input type="button" class="dialog-button dialog-ok" value="确定" id="sureAdd">
				<input type="button" class="dialog-button dialog-cancel ml-default"
					value="取消">
			</div>
		</div>
	</form>
	<!--
dialog -->
	<div class="dialog hidden" id="J_dialogSelectGoods">
		<div class="dialog-head">
			选择商品 <span class="dialog-close"></span>
		</div>
		<div class="dialog-body">
			<div>
				<input type="text" id='ruleName' class="input-search-text"
					style="width: 500px;" placeholder="商品名称"
					onkeydown="return banInputSapce(event);"> <input
					type="button" onclick="search()" class="input-search-button"
					value="搜索">
			</div>
			<div style="height: 350px; overflow: auto">
				<table class="table-list table-border mt-default">
					<thead class="table-thead">
						<tr>
							<th>商品条码</th>
							<th>商品名称</th>
							<th>规格</th>
							<!--   <th>单位</th> -->
						</tr>
					</thead>
					<tbody class="table-tbody" id="productG">
					</tbody>
				</table>
			</div>
		</div>
		<div class="dialog-foot">
			<input type="button" class="dialog-button dialog-ok" value="确定">
			<input type="button" class="dialog-button dialog-cancel ml-default"
				value="取消">
		</div>
	</div>
	<div class="cover-all"></div>
	<script>
    $(function() {
        dialogPosCenter('#J_dialogJoinCustomer');
        dialogPosCenter('#J_dialogSelectGoods');
        //tab('aa');
        dateRange('.J_timeS', '.J_timeE', 1);
        // 参加活动客户
        $('#J_tableBody').on('click', '.J_join', function() {
        	//
        	$('#product').html('');
        	var cyid = $(this).attr('data-id');
        	$("#spVoucherActiveId").val(cyid);
        	$.post('${root}/scms/promotion/groupcheck.do',{id:cyid}, function (data) {

    			if(data.success){
    				if(!data.message.product){
    					$('#J_zssp').hide();
    				}else{
    					$('#J_zssp').show();
    				}
    				var html="<br/>未参与活动的分组：";
    				 $.each(data.message.all, function(i,item) {
    					 if((i+1)%4==0){
    						 html+="<br/>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp";
    					 }
    					 html+='<input type="checkbox" value="'+item.id+'" class="checkbox ml-default"  name="ids"> '+item.name;
    				})
    				 $('#group1').html(html);
    				$('#J_dialogJoinCustomer, .cover-all').show();
    			}
    	 	},"json");

        });
        $('#J_dialogJoinCustomer').on('click', '.dialog-close, .dialog-cancel', function() {
            $('#J_dialogJoinCustomer, .cover-all').hide();
        });
        $('#J_dialogJoinCustomer').on('click', '.dialog-ok', function() {
        	 $("#sureAdd").prop('disabled', 'disabled');
            //提交数据
            var data=$("#formId").serialize();

        	$.post('${root}/scms/promotion/addpromotion.do',data, function (data) {
        		if(data.success){
        			layer.msg(data.message, {
		        		  icon: 1,
		        		  time: 2000 //2秒关闭（如果不配置，默认是3秒）
		        		}, function(){
		        			$("#sureAdd").prop('disabled', '');
		        			window.location.reload();

		        		});
        		}else{
        			layer.msg(data.message);
        			$("#sureAdd").prop('disabled', '');
        		}

    	 	},"json");
        });

        // 选择商品
        $('#J_selectGoods').on('click', function() {
        	var len = $("#product p").length;
        	var spVoucherActiveId=$("#spVoucherActiveId").val();
        	if(len <= 0){
        		$.post('${root}/scms/promotion/findproduct.do',{"id":spVoucherActiveId}, function (data) {
	    			if(data.success){
	    				var html='';
	    				 $.each(data.message, function(i,item) {
	    					 html+='<tr class="J_tr"><td>'+item.mdseId+'</td>';
	    					 html+='<td class="J_name"><input type="hidden" name="scmsItemId" value="'+item.scmsid+'">'+item.name+'</td>';
	    					 html+='<td>'+item.spec+'</td></tr>';
	    				 })
	    				$('#productG').html(html);
	    				 dialogPosCenter('#J_dialogSelectGoods');
	    				$('#J_dialogJoinCustomer').hide();
	           		 	$('#J_dialogSelectGoods').show();
	    			}
    	 	},"json");


         }else{
        	 layer.msg("只能选择一种商品");
         }

        });
        $('#J_dialogSelectGoods').on('click', '.dialog-close, .dialog-cancel', function() {
            $('#J_dialogSelectGoods').hide();
            $('#J_dialogJoinCustomer').show();
        });
        $('#J_dialogSelectGoods').on('click', '.J_tr', function() {
            $(this).addClass('J_select').css('background', '#ccc').siblings().removeClass('J_select').css('background', '');
        });
        $('#J_dialogSelectGoods').on('click', '.dialog-ok', function() {
            $('#J_dialogSelectGoods').hide();
            $('#J_dialogJoinCustomer').show();
            //var itemId = $('#J_dialogSelectGoods .J_select').find('input[name="scmsItemId"]').val();

            var nameHtml = $('#J_dialogSelectGoods .J_select').find('.J_name').html();
            var nametext = $('#J_dialogSelectGoods .J_select').find('.J_name').text();
            if(nametext==undefined||nametext==''||nametext==null){
            	alert("请选择商品");
            	return;
            }
            var html =   '<p>'
                + '<span class="mr-default">' + nameHtml + '</span>'
                + '数量：<input type="text" class="input-search-date mr-default" name="number">'
            	+ '赠送总量：<input type="text" class="input-search-date" name="count"><input type="hidden" value="'+nametext+'" name="itemBaseName">'
            	+ '<span onclick="removeproduct(this)" class="button-operate ml-default">删除</span>'
            	+ '</p>'

            $('#product').append(html);

        });
        aab();
    });

    $('#sub').on('click', function(e) {
    	aab();
   });

    function search(){
    	var ruleName=$("#ruleName").val();
    	var spVoucherActiveId=$("#spVoucherActiveId").val();
    	var data={"id":spVoucherActiveId,"ruleName":ruleName};
    	$.post('${root}/scms/promotion/findproduct.do',data, function (data) {
			if(data.success){
				$('#productG').html('');
				var html='';
				 $.each(data.message, function(i,item) {
					 html+='<tr class="J_tr"><td>'+item.mdseId+'</td>';
					 html+='<td class="J_name"><input type="hidden" name="scmsItemId" value="'+item.scmsid+'">'+item.name+'</td>';
					 html+='<td>'+item.spec+'</td></tr>';
				 })
				$('#productG').html(html);
				dialogPosCenter('#J_dialogSelectGoods');
			}
 		},"json");
    }

    function removeproduct(obj){
    	$(obj).parent().remove();
    }


    function aab(){
    	 $("#jpagination").pagination({
    	        pageSize: 10,
    	        remote: {
    	            url: '${root}/scms/promotion/morelist.do',
    	            success: function(data) {
    	            	 var html='';
    					 $.each(data.list, function(i,item) {
    						 html+='<tr><td>'+(i+1)+'</td>';
    						 html+='<td><a href="${root}/scms/promotion/watchActive.do?id='+item.id+'">'+item.ruleName+'</a></td>';
    						 if(item.ruleType==1){html+='<td>注册送优惠劵</td>';
    						 }else if(item.ruleType==2){html+='<td>满送优惠劵</td>';
    						 }else if(item.ruleType==3){html+='<td>满减</td>';
    						 }else if(item.ruleType==9){html+='<td>满送优惠劵+商品</td>';}else if(item.ruleType==10){html+='<td>满减金额+商品</td>';
    						 }else if(item.ruleType==11){html+='<td>满送商品</td>';}else if(item.ruleType==12){html+='<td>满购商品送商品</td>';
                             }else if(item.ruleType==13){html+='<td>提前订货送优惠劵</td>';}else{html+='<td>其他</td>';}
    						 if(item.status==0){
    	 						 html+='<td>已停止</td>';
    	 						 }
    	 					 else if(item.status==1){
    	 						 html+='<td>进行中</td>';
    	 					 }else if(item.status==2){
    	 						 html+='<td>待开始</td>';
    	 					 }

    						 if(item.plantHalve==100){
    		 						html+='<td></td>';
    		 						html+='<td></td>';
    		 					}else{
    		 						html+='<td>'+item.plantHalveStr+'%</td>';
    		 						html+='<td>'+item.spHalveStr+'%</td>';
    		 					}
    						 html+='<td>'+item.ruleStartPrice+'</td>';

    						 html+='<td>'+item.ruleStart+'</td>';
    						 html+='<td>'+item.ruleEnd+'</td>';
    						 html+='<td><a href="javascript: void(0)" class="J_join" data-id="'+item.id+'">参与</a></td>';
    					 });
    					 if(html == "") {
    						 html = '<tr><td colspan="10" class="no-data"></td></tr>';
    	                 }
    					 $('#J_tableBody').html(html);
    	            },
    	            totalName:'totalSize'
    	        }
    	 });


   }


    /**
    * 禁止空格输入
    * @param e
    * @returns {Boolean}
    */
    function banInputSapce(e)
    {
    var keynum;
    if(window.event) // IE
    {
    keynum = e.keyCode
    }
    else if(e.which) // Netscape/Firefox/Opera
    {
    keynum = e.which
    }
    if(keynum == 32){
    return false;
    }
    return true;
    }
</script>
</body>
</html>
