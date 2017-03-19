<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>联合采购商品 - 管理商品/批发商</title>
	<%@ include file="../common/head.jsp"%>

    <script src="${root}/resources/js/comm.js"></script>
    <%@ include file="../common/autocomplete.jsp"%>
</head>
<body class="wrap-bd">
<div class="mb-default">
	<a class="crumb" href="${root }/scms/gorupItem/getGorupItemlistPage.do">联合采购商品</a>
	<a class="crumb crumb-active">管理商品&amp;批发商</a>
</div>
<div class="wrap-bd mb-default" style="background: #f1f1f1">
    <span class="mr-default">区域编号：${group.id}</span>
    <span class="ml-default mr-default">区域名称：${group.name}</span>
    <span class="ml-default">分类：${group.name2}</span>
</div>
<div id="J_tab">
    <span data-groupname="tab" data-tab="item" class="pills pills-active">商品列表</span>
    <span data-groupname="tab" data-tab="item" class="pills">批发商列表</span>
</div>
<div>
    <div data-groupname="tab" data-tab="content">
        <div class="mt-small mb-small clearfix">
            <form class="fl" id="itemForm">
            	<input type="hidden" value="${group.id}" name="groupId"/>
                <input class="input input-search-text" type="text" name="name" placeholder="请输入物流编号/名称" />
                <input class="input input-search-button" value="搜索" type="button" id="submit"/>
            </form>
            <div class="fr"><span class="button button-default" id="J_addGoods">添加商品</span></div>
        </div>
        <table class="table-list table-border">
            <thead class="table-thead">
	        <tr>
	            <th>物流编号</th>
	            <th>商品条形码</th>
	            <th>商品名称</th>
	            <th>规格</th>
	            <th>年份</th>
	            <th>月份</th>
	            <th>转角售价</th>
	            <th>上架状态</th>
	            <th>操作</th>
	        </tr>
	        </thead>
	        <tbody class="table-tbody" id="itemFromBody">
	        </tbody>
        </table>
        <%@ include file="../common/pagination.jsp"%>
    </div>
    <div data-groupname="tab" data-tab="content">
        <div class="mt-small mb-small clearfix">
            <form class="fl" id="supplierForm">
                <input type="hidden" name="bsCircleId" value="${group.id}"/>
                <input class="input input-search-text" type="text" name="supplierCode" placeholder="请输入批发商编号/名称" />
                <input class="input input-search-button" value="搜索" type="button" id="submit_sp"/>
            </form>
            <div class="fr"><a href="${root}/scms/manager/addSupplierPageList.do?groupId=${group.id}"><button class="button button-default">添加批发商</button></a></div>
        </div>
        <table class="table-list table-border">
            <thead class="table-thead">
            <tr>
                <th>批发商编号</th>
                <th>批发商名称</th>
                <th>联系方式</th>
                <th>地址</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody class="table-tbody" id="supplierFormTbody">
            <!-- <tr>
                <td>00975852</td>
                <td>鸿业兴批发部</td>
                <td>15017877766</td>
                <td>桃园南新路12号</td>
                <td>
                    <input type="button" value="删除" class="button button-operate" >
                </td>
            </tr> -->
            </tbody>
        </table>
        <div id="page_sp" class="fr clearfix"></div>
    </div>
</div>
<div class="dialog hidden" id="J_dialog">
    <div class="dialog-head"></div>
    <div class="dialog-body">
    	<form id="scmsItemForm">
	        <table class="table-list table-border">
	            <thead class="table-thead">
	            <tr>
	                <th></th>
	                <th width="280">商品名称</th>
	                <th>规格</th>
	                <th>年</th>
	                <th>月</th>
	                <th>经销商</th>
	                <th width="60">采购价</th>
	                <th width="60">转角售价</th>
	                <th width="60">市场价</th>
	            </tr>
	            </thead>
	            <tbody class="table-tbody">
	            <tr>
	                <td><input type="hidden" id="scmsItemId">
	                <input type="hidden" name="groupId" value="${group.id}">
	                <input type="hidden" name="areaName" value="${group.name}">
	                <input type="hidden" name="status" value="1">
	                <input type="hidden" name="managerId">
	                <input type="hidden" name="itemBaseId">
	                1
	                </td>
	                <td><input type="text" class="input input-full" name="goodName" id="scmsItemName"></td>
	                <td id="scmsItemSpec"></td>
	                <td>
	                <label class="label">生产日期：</label>
		            <select class="select"  id="selectYear" name="selectYear" disabled="disabled">
		            </select>
	                	<input type="hidden" id="year" name="year" maxlength="4" readonly="readonly">
	            	</td>
	                <td>
		                <select class="select" id="selectMonth" name="selectMonth"  disabled="disabled">
			            </select>
		                <input type="hidden" id="month" name="month" maxlength="2" readonly="readonly">
	                </td>
	                <td id="scmsItemManagerName"></td>
	                <td><input type="text" class="input input-full" name="areaPrice" id="scmsItemAreaPrices"></td>
	                <td><input type="text" class="input input-full" name="zjjfPrice" id="scmsItemZjjfPrices"></td>
	                <td><input type="text" class="input input-full" name="marketPrice" id="scmsItemMarketPrices"></td>
	            </tr>
	            </tbody>
	        </table>
        </form>
    </div>
    <div class="dialog-foot">
        <input type="button" class="dialog-button dialog-ok" value="确定" id="submitGoods">
        <input type="button" class="dialog-button dialog-cancel" value="取消">
    </div>
</div>
<div class="cover-all"></div>
<script>
    $(function() {
    	$('#scmsItemForm').on('keydown', function() {
	   	 	$('#scmsItemName').autocomplete({
	               serviceUrl: "${root}/scms/item/findItemByName.do",
	               params: {groupId:$('input[name="groupId"]').val(),name:$('#scmsItemName').val()},
	               transformResult: function(response) {
	                   var res = JSON.parse(response);
	                   return {
	                       suggestions: $.map(res.message, function(value, key) {
	                       	return { value: value.goodName+'('+value.managerName+')', data: value };
	                       })
	                   };
	               },
	               onSelect: function(dd) {
	            	   $('#scmsItemName').val(dd.data.goodName);
	            	   $('input[name="managerId"]').val(dd.data.managerId);
	            	   $('#scmsItemSpec').html(dd.data.spec);
	            	   $('#year').val(dd.data.year);
	            	   $('#month').val(dd.data.month);
	            	   $('#selectYear').html('<option>'+dd.data.year+'</option>');
	            	   if(dd.data.year == 0)
	            		   $('#selectYear').html('<option>不限</option>');
	       			   $('#selectMonth').html('<option>'+dd.data.month+'</option>');
	            	   if(dd.data.month == 0)
	            		   $('#selectMonth').html('<option>不限</option>');
	            	   $('#scmsItemManagerName').html(dd.data.managerName);
	            	   $('input[name="itemBaseId"]').val(dd.data.itemBaseId);
	            	   $('#scmsItemAreaPrices').val(dd.data.areaPrice);
	            	   $('#scmsItemZjjfPrices').val(dd.data.zjjfPrice);
	            	   $('#scmsItemMarketPrices').val(dd.data.marketPrice);
	               }
	        });
        });

        tab('tab');
        $('#J_tab').on('click', '.pills', function() {
            $(this).addClass('pills-active').siblings('.pills').removeClass('pills-active');
        });
        
        dialogPosCenter('#J_dialog');
        var $dialog = $('#J_dialog');
        var $cover = $('.cover-all');
        $('#J_addGoods').on('click', function() {
        	$('#year').val('');
			$('#year').attr('readonly' , true);
			$('#month').val('');
			$('#selectYear').html('');
			$('#selectMonth').html('');
			$('#month').attr('readonly' , true);
			$('#scmsItemId').val('');
			$('#scmsItemName').val('');
			$('#scmsItemName').attr('readonly' , false);
			$('#scmsItemAreaPrices').val('');
			$('#scmsItemZjjfPrices').val('');
			$('#scmsItemMarketPrices').val('');
			$('#scmsItemManagerName').html('');
			$('#scmsItemSpec').html('');
        	
            $dialog.find('.dialog-head').text('添加');
            $dialog.show();
            $cover.show();
//            layer.open({
//	    	    type: 1,
//	    	    area: ['1200px', '300px'],
//	    	    content: $('#J_dialog').html() //数组第二项即吸附元素选择器或者DOM
//	    	}); 
        });
        $('.J_editGoods').on('click', function() {
            $dialog.find('.dialog-head').text('编辑');
            $dialog.show();
            $cover.show();
        });
        $dialog.on('click', '.dialog-ok', function() {
            $cover.hide();
        }).on('click', '.dialog-cancel', function() {
            $dialog.hide();
            $cover.hide();
        });
        $("#page_sp").pagination({
        	pageSize: 10,
            showJump: true,
            remote: {
                url: '${root}/scms/gorupItem/getSupplierPageList.do',
                params: $('#supplierForm').serializeArray(),
                success: function(data) {
                var html = '';
   				 $.each(data.list,function(i,supplier){
   					html += '<tr><td>' + supplier.supplierCode + '</td>';
   					html += '<td>' + supplier.supplierName + '</td>';
   					html += '<td>' + supplier.mobile + '</td>';
   					html += '<td>' + supplier.supplierAddress + '</td>';
   					html += '<td><input type="button" value="删除" class="button button-operate" onclick="updateSupplier(\' '+supplier.id+' \')"></td></tr>';
   				 });
   				 if(html == '') {
                 	html = '<tr><td colspan="5" class="ta-c">暂无数据</td></tr>';
                 }
   				 $("#supplierFormTbody").html(html);
                },
                totalName:'totalSize',
                pageParams: function (data) {
                   	return {
                   			pageIndex: data.pageIndex + 1,
                   			pageSize: data.pageSize
                   		}
                },
            }
        });
        $('#supplierForm').keydown(function(e){
			if (e.keyCode == 13) {
				e.preventDefault();
				$("#page_sp").pagination('setParams', $('#supplierForm').serializeArray());
		       	$("#page_sp").pagination('setPageIndex', 0);
		       	$("#page_sp").pagination('remote');
			}
		});
        //搜索按钮
        $('#submit_sp').on('click', function(e) {
       	 $("#page_sp").pagination('setParams', $('#supplierForm').serializeArray());
       	 $("#page_sp").pagination('setPageIndex', 0);
       	 $("#page_sp").pagination('remote');
       });
        $("#jpagination").pagination({
            pageSize: 10,
            showJump: true,
            remote: {
                url: '${root}/scms/gorupItem/listPage.do',
                params: $('#itemForm').serializeArray(),
                success: function(data) {
                	var html = "";
   				 $.each(data.list,function(i,item){
   					 html += '<tr><td>'+item.wuliu+'</td>'
   					 html +='<td>'+item.mdseId+'</td>';
   			         html +='<td>'+item.goodName+'</td>';
   			         html +='<td>' + item.spec + '</td>';
   			         if(item.year != 0){
   			        	html +='<td>'+item.year+'</td>';
   			         }else{
   			         	html +='<td>不限</td>';
   			         }
   			         if(item.month != 0){
   			         	html +='<td>'+item.month+'</td>';
   			         }else{
   			         	html +='<td>不限</td>';
   			         }
   			         html +='<td>'+item.zjjfPrice+'</td>';
   			         if(item.status == '0'){
   			        	html +='<td>下架</td>';
   			        	html +='<td><input type="button" value="上架" onclick="updateItem(\''+item.id+' \' , \' 1 \')" class="button button-operate"> ';
   			         }else{
   			        	html += '<td>上架</td>';
   			        	html += '<td><input type="button" value="下架" onclick="updateItem(\''+item.id+' \' , \' 0 \')" class="button button-operate"> ';
   			         }
   			      	 html += '<input type="button" value="编辑" onclick="editItem(\''+item.id+' \' , \' 1 \')" class="button button-operate J_editGoods"> ';
   			      	 html += '<input type="button" value="删除" onclick="updateItem(\''+item.id+' \' , \' 2 \')" class="button button-operate"> ';
   			      	 html += '</td></tr>';
   				 });
                 if(html == '') {
                 	html = '<tr><td colspan="9" class="ta-c">暂无数据</td></tr>';
                 }
   				 $("#itemFromBody").html(html);
                },
                totalName:'totalSize',
                pageParams: function (data) {
                   	return {
                   			pageIndex: data.pageIndex + 1,
                   			pageSize: data.pageSize
                   		}
                },

            }
        });
        $('#itemForm').keydown(function(e){
			if (e.keyCode == 13) {
				e.preventDefault();
				$("#jpagination").pagination('setParams', $('#itemForm').serializeArray());
		       	$("#jpagination").pagination('setPageIndex', 0);
		       	$("#jpagination").pagination('remote');
			}
		});
        //搜索按钮
        $('#submit').on('click', function(e) {
       	 $("#jpagination").pagination('setParams', $('#itemForm').serializeArray());
       	 $("#jpagination").pagination('setPageIndex', 0);
       	 $("#jpagination").pagination('remote');
       });
       $('#submitGoods').on('click', function(e) {
    	   if($('#J_dialog').find('.dialog-head').text() == '编辑'){
    		   var scmsItemId = $('#scmsItemId').val();
    		   $.post("${root}/scms/item/updateById.do",{
    			   id:scmsItemId,
    			   areaPrice:$('#scmsItemAreaPrices').val(),
    			   marketPrice:$('#scmsItemMarketPrices').val(),
    			   zjjfPrice:$('#scmsItemZjjfPrices').val(),
    			   },function(data){
					if(data.success){
						layer.msg('修改成功', {
						    icon: 1,
						    time: 2000 //2秒关闭（如果不配置，默认是3秒）
						}, function(){
							$dialog.hide();
				            $cover.hide();
							$("#submit").trigger("click");
						}); 
					}else{
						layer.msg(data.message, {icon: 5});
					}
				 },'json');
    	   }else{
    		   var testPriceCheck = /^\d{1,5}(\.\d{1,2})?$/;
    		   
    		   if($.trim($('#year').val()) == ''){
    			   $('#year').val('0');
    		   }
    		   if($.trim($('#month').val()) == ''){
    			   $('#month').val('0');
    		   }
    		   
    		   if($.trim($('input[name="managerId"]').val()) == ''){
    			   layer.tips('商品信息无效，请重新选择！', $('#scmsItemName'));
    			   $('#scmsItemName').focus();
    		   }else if($.trim($('#scmsItemName').val()) == ''){
    			   layer.tips('商品信息无效，请重新选择！', $('#scmsItemName'));
    			   $('#scmsItemName').focus();
    		   }else if(($.trim($('#year').val()).length != 4)&&($.trim($('#year').val()) != '0')){
    			   console.log($('#year').val());
    			   layer.tips('商品信息无效，请重新选择！', $('#scmsItemName'));
    			   $('#scmsItemName').focus();
    		   }else if($.trim($('#month').val()) > 12 || $.trim($('#month').val()) < 0){
    			   layer.tips('商品信息无效，请重新选择！', $('#scmsItemName'));
    			   $('#scmsItemName').focus();
    		   }else if($.trim($('#scmsItemAreaPrices').val()) ==''){
    			   layer.tips('采购价不能为空', $('#scmsItemAreaPrices'));
    			   $('#scmsItemAreaPrices').focus();
    		   }else if(!testPriceCheck.test($.trim($('#scmsItemAreaPrices').val()))){
    			   layer.tips('采购价格式格式有误', $('#scmsItemAreaPrices'));
    			   $('#scmsItemAreaPrices').focus();
    		   }else if($.trim($('#scmsItemZjjfPrices').val())  ==''){
    			   layer.tips('转角售价不能为空', $('#scmsItemZjjfPrices'));
    			   $('#scmsItemZjjfPrices').focus();
    		   }else if(!testPriceCheck.test($.trim($('#scmsItemZjjfPrices').val()))){
    			   layer.tips('转角售价格式有误', $('#scmsItemZjjfPrices'));
    			   $('#scmsItemZjjfPrices').focus();
    		   }else if($.trim($('#scmsItemMarketPrices').val()) == ''){
    			   layer.tips('市场价不能为空', $('#scmsItemMarketPrices'));
    			   $('#scmsItemMarketPrices').focus();
    		   }else if(!testPriceCheck.test($.trim($('#scmsItemMarketPrices').val()))){
    			   layer.tips('市场价格式有误', $('#scmsItemMarketPrices'));
    			   $('#scmsItemMarketPrices').focus();
    		   }else{
	    		   $.post("${root}/scms/item/addScmsItem.do",
	    				   $('#scmsItemForm').serialize()
	    				   ,function(data){
						if(data.success){
							layer.msg('添加成功', {
							    icon: 1,
							    time: 2000 //2秒关闭（如果不配置，默认是3秒）
							}, function(){
								$dialog.hide();
					            $cover.hide();
				            	$("#submit").trigger("click");
							}); 
						}else{
							layer.msg(data.message, {icon: 5});
						}
					 },'json');
    		   }
    	   }
       });
});
function updateItem(id , status){
	if(status == 2){
		layer.msg('确认删除？', {
			time: 0, //20s后自动关闭
			btn: ['删除', '取消'],
			yes: function(index){
				layer.close(index);
				$.post("${root}/scms/item/updateById.do",{id:id,isDelete : 1},function(data){
					if(data.success){
						layer.msg('删除成功', {
						    icon: 1,
						    time: 2000 //2秒关闭（如果不配置，默认是3秒）
						}, function(){
			            	$("#submit").trigger("click");
						}); 
					}else{
						layer.msg(data.message, {icon: 5});
					}
				 },'json');
			}
		});
	}else{
		$.post("${root}/scms/item/updateById.do",{id:id,status : status},function(data){
			if(data.success){
				layer.msg('修改成功', {
				    icon: 1,
				    time: 2000 //2秒关闭（如果不配置，默认是3秒）
				}, function(){
	            	$("#submit").trigger("click");
				}); 
			}else{
				layer.msg(data.message, {icon: 5});
			}
		 },'json');
	}   
}
function editItem(id , status){
	$.post("${root}/scms/item/findById.do",{id:id},function(data){
		if(data.success){
			$('#J_dialog').find('.dialog-head').text('编辑');
        	$('#J_dialog, .cover-all').show();
        	
			var item = data.message;
			$('#year').val(item.year);
			$('#selectYear').html('<option>'+item.year+'</option>');
			if(item.year == 0)
     			$('#selectYear').html('<option>不限</option>');
			$('#year').attr('readonly' , true);
			$('#month').val(item.month);
			$('#selectMonth').html('<option>'+item.month+'</option>');
			if(item.month == 0)
	     		$('#selectMonth').html('<option>不限</option>');
			$('#month').attr('readonly' , true);
			$('#scmsItemId').val(item.id);
			$('#scmsItemName').val(item.goodName);
			$('#scmsItemName').attr('readonly' , true);
			$('#scmsItemAreaPrices').val(item.areaPrice);
			$('#scmsItemZjjfPrices').val(item.zjjfPrice);
			$('#scmsItemMarketPrices').val(item.marketPrice);
			$('#scmsItemManagerName').html(item.managerName);
			$('#scmsItemSpec').html(item.spec);
		}else{
			$('#year').val('');
			$('#year').attr('readonly' , false);
			$('#month').val('');
			$('#month').attr('readonly' , false);
			$('#scmsItemId').val('');
			$('#scmsItemName').val('');
			$('#scmsItemName').attr('readonly' , false);
			$('#scmsItemAreaPrices').val('');
			$('#scmsItemZjjfPrices').val('');
			$('#scmsItemMarketPrices').val('');
			$('#scmsItemManagerName').html('');
			$('#scmsItemSpec').html('');
		}
	},'json');
}
function updateSupplier(id){
	layer.msg('确认删除？', {
		time: 0, //20s后自动关闭
		btn: ['删除', '取消'],
		yes: function(index){
			layer.close(index);
			$.post("${root}/scms/manager/updateSupplier.do",{supplierId:id , addOrDel:'0'},function(data){
				if(data.success){
					layer.msg("删除成功", {
					    icon: 1,
					    time: 2000 //2秒关闭（如果不配置，默认是3秒）
					}, function(){
						$("#submit_sp").trigger("click");
					}); 
				}else{
					layer.msg(data.message, {icon: 5});
				}
			},'json');
		}
	});
}
</script>
</body>
</html>