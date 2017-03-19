<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="utf-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <title>发送优惠券</title>
	<%@ include file="../common/head.jsp"%>
    <%@ include file="../common/autocomplete.jsp"%>
    <script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
    <script src="${root}/resources/js/comm.js"></script>
</head>
<body class="wrap-bd">
<div class="mb-default">
    <a class="crumb" href="${root}/Customer/voucher/toIndex.do">优惠券管理</a><a class="crumb crumb-active">发送优惠券</a>
</div>
<div class="wrap-bd bg">
    <div>
    	<b>条件：</b>
	    <form id="addForm" method="post" action="${root}/Customer/voucher/toConfig.do">
	       <input type="hidden" name="id" value="${id}">
		    <div>
		        <p class="ml-default">
		            <input type="radio" value="0" name="condition" checked="checked" class="selectSp">&nbsp;不限制&nbsp;
		        </p>
		        <p class="ml-default">
		            <input type="radio" value="1" name="condition" class="selectSp">&nbsp;从未下单用户&nbsp;
		        </p>
		        <p class="ml-default">
		            <input type="radio" value="2" name="condition" class="selectSp">&nbsp;<input type="number" style="width: 80px" name="customerNum" id="customerNum">&nbsp;个月内没下单用户&nbsp;
		            <span class="" style="color: red" id="numberError"></span>
		        </p>
		        <p class="ml-default">
		            <input type="radio" value="3" name="condition">&nbsp;excel导入&nbsp;
		        </p>
		        <p class="ml-default">
		            <input type="radio" value="4" name="condition">&nbsp;指定用户&nbsp;
		        </p>
		        <!-- <p>
		            <b>区域：</b>
		        </p> -->
		        <p id="spGroupP" class="">
		            <input class="ml-default" type="radio" value="0" name="spgroupRadio" checked="checked">&nbsp;所有定格区域&nbsp;
		            <input class="ml-default" type="radio" value="1" name="spgroupRadio">&nbsp;指定定格区域&nbsp;
		        </p>
		        <div id="saveSpGroup"></div>
		        <div id="saveStore"></div>
		     </form>
		        <table class="table-list ml-default hidden" id="spGroupTable">
		            <thead>
		            <tr>
		                <th colspan="8">
		                    <div class="fl ml-default">
		                        <input class="J_selectAll" type="checkbox" name="coupon" id="spGroupCheckAll">&nbsp;全选
		                        <span class="button button-operate ml-default" id="J_addSelect">添加</span>
		                    </div>
		                    <form method="post" class="fr">
		                        <select class="select" id="spGroupSelect" name="spGroupSelect">
		                            <option value="0">定格名称</option>
		                            <option value="1">定格区域</option>
		                        </select>
		                        <input class="input input-search-text" type="text" name="name" value="" placeholder="" id="spGroupKeyStr"/>
		                        <input style="display:none;"/> 
		                        <input class="input input-search-button" value="搜索" type="button" id="spGroupSearch"/>
		                    </form>
		                </th>
		            </tr>
		            <tr>
		                <th width="60">&nbsp;</th>
		                <th>定格名称</th>
		                <th width="220">分类</th>
		                <th width="150">操作</th>
		            </tr>
		            </thead>
		            <tbody id="spGroupTbody">
		            </tbody>
		            <tfoot>
		            	<tr>
		            		<td colspan="7"><%@ include file="../common/pagination.jsp"%></td>
		            	</tr>
		            </tfoot>
		        </table>
		    </div>
	    <table class="table-list hidden" id="storeTable">
	        <thead>
	        <tr>
	            <th width="50"><input class="J_selectAll" type="checkbox" id="storeCheckAll">&nbsp;全选</th>
	            <th style="text-align:left"><span class="button button-operate" id="storeAddAll">添加</span></th>
	            <th colspan="5" style="text-align:right">
	            	<input type="text" class="input input-search-text" name="" value="" id="storeKeyStr">
	            	<input type="button" class="input input-search-button" name="" value="搜索" id="storeSearch">
	            </th>
	        </tr>
	        <tr>
	            <th></th>
	            <th>商铺编号</th>
	            <th>商铺名称</th>
	            <th>店主名</th>
	            <th>手机号</th>
	            <th>定格名称</th>
	            <th>管理操作</th>
	        </tr>
	        </thead>
	        <tbody id="storeTbody">
	        </tbody>
	        <tfoot>
		    	<tr>
		        	<td colspan="7">
                    	<div id="jpagination_se"></div>
		        	</td>
		    	</tr>
		    </tfoot>
	    </table>
	    <p id="nextP">
	        <input class="button button-ok" type="button" id="next" value="下一步">
	        <input class="button button-cancel" type="button" id="cancel" value="取消">
	    </p>
	    <div class="table-list hidden" id="excelDiv">
	      <form action="${root}/Customer/voucher/readExcel.do?id=${id}" name="Form" id="Form" method="post" enctype="multipart/form-data">
		<table style="width:95%;" >
			<tr>
				<td style="padding-top:20px;"><input type="file" id="excel" name="excel" style="width:50px;" onchange="fileType(this)" /></td>
			</tr>
			<tr>
				<td>
					<a class="button button-white" onclick="save();">导入</a>
					<!-- <a class="button button-white" onclick="window.location.href='/user/downExcel.do'">下载模版</a> -->
				</td>
			</tr>
		</table>
	</form>
    </div>
</div>
<div class="cover-all"></div>
<script>
    function save(){
		if($("#excel").val()==""){
			alert('请选择文件上传!');
			return false;
		}
		$(".cover-all").fadeIn();
		$("#Form").submit();
	}
    
    function fileType(obj){
		var fileType=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();//获得文件后缀名
	    if(fileType != '.xls' && fileType != '.xlsx'){
	    	alert('请上传xls或xlsx格式的文件');
	    	$("#excel").val('');
	    }
	}
        $(function(){
            
           //定格列表显示
           $('#spGroupP input[type="radio"][name="spgroupRadio"]').on('click',function(){
        	  if($(this).val()==0){
        		  $('#spGroupTable').addClass('hidden');
        	  }else{
        		  $('#spGroupTable').removeClass('hidden');
        	  } 
           });
            
            //取消按钮
            $('#cancel').click(function(){
            	location.href='${root}/Customer/voucher/toIndex.do?pageIndex=${pageIndex}';
            });
            //下一步按钮
            $('#next').click(function(){
            	//指定用户，至少添加一个用户
            	if($('input[type="radio"][name="condition"][value="4"]').prop('checked')){
            		if($('#saveStore [name="storeArr"]').length==0){
            			layer.msg('请选择用户!');
            			return;
            		}
            	}
              //不限制，从未下单用户，几个月内未下单用户，并且是指定定格，至少添加一个定格
              if($('.selectSp:checked').length==1){
            	  if($('input[type="radio"][name="spgroupRadio"][value="1"]').prop('checked')){
            		  if($('#saveSpGroup [name="spGroupArr"]').length==0){
            			  layer.msg('请选择定格!');
            			  return;
            		  }
            	  }
              }              
              
            	var html='<span style="color: red">请求时间可能较长，请耐心等待</span>';
            	$('#customerNum').trigger('blur');
            	if($('#addForm span.error').length>0){
            		return;
            	}
            	$(this).before(html);
            	$(this).hide();
            	$('#addForm').submit();
            });
            
            //几个月内未下单用户
            $('#customerNum').blur(function(){
            	if($('input[type="radio"][name="condition"][value="2"]').prop('checked')){
            		if($('#customerNum').val()==''){
            			$('#numberError').addClass('error');
            			$('#numberError').text('请填写几个月未下单');
            		}else{
            			$('#numberError').removeClass('error');
            			$('#numberError').text('');
            		}
            		var isNum = /^[1-9]+$/;
            		var num = $('#customerNum').val();
            		if(!isNum.test(num)){
            			$('#numberError').addClass('error');
            			$('#numberError').text('必须为从1开始的正整数');
            		}else{
            			$('#numberError').removeClass('error');
            			$('#numberError').text('');
            		}
            	}
            	
            });
            
            //几个月未下单
            $('#customerNum').on('change',function(){
            	$('.selectSp[value="2"]').prop('checked','checked');
                if($('#customerNum').val()<1) {
            	$('#numberError').text('月份不能小于0');
            	$('#numberError').addClass('error');
                }else{
                	$('#numberError').text('');
                	$('#numberError').removeClass('error');
                }
                $('#storeTable').addClass('hidden');
                $('#spGroupP [type="radio"][name="spgroupRadio"][value="0"]').prop('checked','checked');
            });
            
            
            //单选按钮
            $('input[type="radio"][name="condition"]').click(function(){
            	var radioVar = $(this).val();
            	if(radioVar=='0'||radioVar=='1'||radioVar=='2'){
            		$('#storeTable').addClass('hidden');
            		$('#spGroupTable').removeClass('hidden');
            		$('#spGroupP').removeClass('hidden');
            		$('#excelDiv').addClass('hidden');
            		$('#spGroupP [type="radio"][name="spgroupRadio"][value="0"]').prop('checked','checked');
            		$('#spGroupTable').addClass('hidden');
            		$('#nextP').removeClass('hidden');
            	}else if(radioVar=='3'){
            		$('#storeTable').addClass('hidden');
            		$('#spGroupTable').addClass('hidden');
            		$('#spGroupP').addClass('hidden');
            		$('#excelDiv').removeClass('hidden');
            		$('#nextP').addClass('hidden');
            	}else{
            		$('#spGroupTable').addClass('hidden');
            		$('#spGroupP').addClass('hidden');
            		$('#excelDiv').addClass('hidden');
            		$('#storeTable').removeClass('hidden');
            		$('#spGroupP [type="radio"][name="spgroupRadio"][value="0"]').prop('checked','checked');
            		$('#spGroupTable').addClass('hidden');
            		$('#nextP').removeClass('hidden');
            	}
            });
            
           /* 店铺begin */
            //指定用户列表
            $("#jpagination_se").pagination({
                pageSize: 10,
                remote: {
                    url: '${root}/Customer/voucher/getStoreList.do?id=${id}',
                    params: {'keyStr': $("#status").val()},
                    success: function(data) {
                    	if(!data.flag){
                    		layer.msg('获取指定用户列表出错!')
                    		console.log(data.message);
                    		return;
                    	}
                    	var html = '';
                        $.each(data.list,function(i,item){
                        	html+='<tr>'
                        	    +'<td><input class="ml-small J_checkbox" type="checkbox" name="store" value="'+item.id+'"></td>'
                        	    +'<td>'+item.suppliercode+'</td>'
                        	    +'<td>'+item.name+'</td>'
                        	    +'<td>'+item.contact+'</td>'
                        	    +'<td>'+item.mobile+'</td>'
                        	    +'<td>'+item.spGropName+'</td>'
                        	    +'<td>'
                        	    +'<input type="button" value="添加" class="button button-operate store_add">'
                        	    +'<span class="hidden J_itemId">'+item.id+'</span>'
                        	    +'</td>'
                        	    +'</tr>';
                        });
                       $('#storeTbody').html(html);
                       // 翻页saveSpGroup区数据回显
						$('#saveStore input').each(function() {
							var ipt = $(this).val();
							$('.J_itemId').each(function() {
								if(ipt === $.trim($(this).text())) {
									$(this).prev().val('移除');
									$(this).prev().removeClass('store_add');
									$(this).prev().addClass('store_del');
									$(this).parent('td').siblings('td').first().find('.J_checkbox').prop('checked', 'checked');
								};
							});
						});
                    },
                    totalName: 'totalSize'
                }
            });
            
           //店铺搜索按钮
           $('#storeSearch').on('click',function() {
   			$("#jpagination_se").pagination('setParams', {'keyStr':$('#storeKeyStr').val()});
   			$("#jpagination_se").pagination('setPageIndex', 0);
           	$("#jpagination_se").pagination('remote');
           });
           
            //店铺 添加按钮(单个添加)
            $('#storeTbody').on('click','.store_add',function(){
               var html = '';
			   var va = $(this).parent('td').parent('tr').children('td').first().children('input').attr('value');
               html='<input type="hidden" name="storeArr" value="'+va+'">'
               $(this).val('移除');
               $(this).removeClass('store_add');
               $(this).addClass('store_del');
               $('#saveStore').append(html);
               var $ChecxBox= $(this).parent('td').parent('tr').children('td').first().children('input');
               $ChecxBox.prop('checked','checked');
            })
            
            //店铺全选(checkbox)
            $('#storeCheckAll').click(function(){
            	var $StoreChecxBox=$('input[type="checkbox"][name="store"]');
            	if($(this).prop('checked')){
            		$StoreChecxBox.prop('checked','checked');
            	}else{
            		$StoreChecxBox.prop('checked','');
            	};
            });
            
            //店铺 多个添加
            $('#storeAddAll').click(function(){
            	var $StoreChecxBox=$('input[type="checkbox"][name="store"]');
            	$.each($StoreChecxBox,function(i,item){
            	    if($(this).prop('checked')){
            	    	$('#saveStore input[name="storeArr"][value="'+$(this).val()+'"]').remove();
            	    	var html = '<input type="hidden" name="storeArr" value="'+$(this).val()+'">';
            	    	$('#saveStore').append(html);
            	    	var $LastTdInput = $(this).parent().parent().children().eq(6).children('input');
            	    	$($LastTdInput).val('移除');
                        $($LastTdInput).removeClass('store_add');
                        $($LastTdInput).addClass('store_del');
            	    }
            	});
            });
            
            //店铺  移除按钮
            $('#storeTbody').on('click','.store_del',function(){
			   var va = $(this).parent('td').parent('tr').children('td').first().children('input').attr('value');
               $(this).val('添加');
               $(this).removeClass('store_del');
               $(this).addClass('store_add');
               $('#saveStore input[name="storeArr"][value="'+va+'"]').remove();
               var $ChecxBox= $(this).parent('td').parent('tr').children('td').first().children('input');
               $ChecxBox.prop('checked','');
               $('#storeCheckAll').prop('checked','');
            })
            
            /* 店铺end */
            
            /* 定格区域begin */
            //读取定格列表
            $("#jpagination").pagination({
                pageSize: 10,
                remote: {
                    url: '${root}/Customer/SpGroup/getVoucherSpGroupList.do',
                    params: {'name': $('#spGroupKeyStr').val(),'spGroupSelect':$('#spGroupSelect').val()},
                    success: function(data) {
                    	var html = '';
						$.each(data.list, function(i, item) {
							html += '<tr>'
							    + '<td><input class="ml-small J_checkbox" type="checkbox" name="coupon"></td>'
							    + '<td>' + item.name + '</td>'
							    + '<td>' + item.areaName + '</td>'
							    + '<td><span class="button button-operate J_addSpGroup">添加</span>'
							    + '<span class="hidden J_itemId">' + item.id + '</span>'
							    + '</td>';
						    	+ '</tr>';
						});
						$('#spGroupTbody').html(html);
			            // 翻页saveSpGroup区数据回显
						$('#saveSpGroup input').each(function() {
							var ipt = $(this).val();
							$('.J_itemId').each(function() {
								if(ipt === $.trim($(this).text())) {
									addOrDel($(this).prev('span'), 'J_delSpGroup', 'J_addSpGroup');
									$(this).parent('td').siblings('td').first().find('.J_checkbox').prop('checked', 'checked');
								};
							});
						});
                    },
                    totalName: 'totalSize'
                }
            });
            $('#spGroupSearch').on('click',function() {
    			$("#jpagination").pagination('setParams', {name: $('#spGroupKeyStr').val(),spGroupSelect:$('#spGroupSelect').val(),search:'1'});
    			$("#jpagination").pagination('setPageIndex', 0);
            	$("#jpagination").pagination('remote');
            });
            
          //定格全选(checkbox)
            $('#spGroupCheckAll').click(function(){
            	var $SpGroupChecxBox=$('input[type="checkbox"][name="coupon"]');
            	if($(this).prop('checked')){
            		$SpGroupChecxBox.prop('checked','checked');
            	}else{
            		$SpGroupChecxBox.prop('checked','');
            	};
            });
            
            
            // 单选添加/移除
            function addOrDel($obj, addclass, removeclass) {
            	$obj.addClass(addclass).removeClass(removeclass);
            	if($.trim($obj.text()) === '添加') {
            		$obj.text('移除');
            	} else {
            		$obj.text('添加');
            	}
            }
            
            //定格添加按钮(单个添加)
            $('#spGroupTable').on('click', '.J_addSpGroup', function() {
            	//addOrDel($(this), 'J_delSpGroup', 'J_addSpGroup');
            	$(this).parent('td').siblings('td').first().find('.J_checkbox').prop('checked', 'checked');
            	$(this).text('移除');
            	$(this).removeClass('J_addSpGroup');
            	$(this).addClass('J_delSpGroup');
            	var hinput = '<input type="hidden" name="spGroupArr" value="' + $.trim($(this).next('.J_itemId').text()) + '">';
            	$('#saveSpGroup').append(hinput);
            	$('#spGroupP [type="radio"][name="spgroupRadio"][value="1"]').prop('checked','checked');
            });
			//定格删除按钮(单个删除)
            $('#spGroupTable').on('click', '.J_delSpGroup', function() {
            	//addOrDel($(this), 'J_addSpGroup', 'J_delSpGroup');
            	$(this).text('添加');
            	$(this).removeClass('J_delSpGroup');
            	$(this).addClass('J_addSpGroup');
            	$(this).parent('td').siblings('td').first().find('.J_checkbox').prop('checked', '');
            	var removeInput = '#saveSpGroup input[value="' + $.trim($(this).next('.J_itemId').text()) + '"]';
            	$(removeInput).remove();
            });      
            // 多选添加
            $('#J_addSelect').on('click', function() {
				$('.J_itemId').each(function() {
					if($(this).parent('td').siblings('td').first().find('.J_checkbox').is(':checked')) {
						$('#saveSpGroup input[name="spGroupArr"][value="'+$(this).text()+'"]').remove();
						$(this).prev('span').text('删除');
		            	$(this).prev('span').removeClass('J_addSpGroup');
		            	$(this).prev('span').addClass('J_delSpGroup');
		            	var hinput = '<input type="hidden" name="spGroupArr" value="' + $.trim($(this).text()) + '">';
		            	$('#saveSpGroup').append(hinput);
					}
				});
            });
            /* 定格区域end */
        });
</script>
</body>
</html>