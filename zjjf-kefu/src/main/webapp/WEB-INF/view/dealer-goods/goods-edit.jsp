﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>经销商商品管理 - 经销商商品编辑</title>
    <%@ include file="../common/head.jsp"%>
    <%@ include file="../common/autocomplete.jsp"%>
</head>
<body>
<div class="wrap-bd">
    <div class="mb-default clearfix">
        <a class="crumb" href="${root}/scms/manager/listPage.do">经销商商品管理</a><a class="crumb crumb-active">商品编辑</a>
        <a class="fr" href="javascript:location.href=document.referrer;">返回</a>
    </div>
    <div class="mb-default">
        <b>【${scmsManager.managerName}】经销商</b>
    </div>
    <div>
    	<form id="itemForm">
    		<input type="hidden" name="managerId" value="${scmsManager.id}">
	        <p class="required">
	            <label class="label">商品条形码：</label>
	            <input class="input input-default" type="text" name="mdseId" maxlength="20">
	        </p>
	        <p class="required">
	            <label class="label">商品名称：</label>
	            <input class="input input-default" type="text" name="goodName" maxlength="60">
	        </p>
	        <p id="itemBaseId">
	        </p>
	        <p>
	            <label class="label">生产日期：</label>
	            <select class="select" name="year">
	            </select>
	            <select class="select" name="month">
	            </select>
	        </p>
	        <div>
	            <label class="label">商品详情：</label>
	            <textarea class="textarea textarea-default" rows="5" name="mark" style="width: 800px; height: 20%" maxlength="250"></textarea>
	        </div>
	        <div class="mt-default clearfix">
	            <label class="label fl">价格：</label>
	            <table class="table-list table-border fl" style="width: 800px">
	                <thead class="table-thead">
	                <tr>
	                    <th></th>
	                    <th>经销区域</th>
	                    <th>采购价</th>
	                    <th>转角售价</th>
	                    <th>市场价</th>
	                </tr>
	                </thead>
	                <tbody id="itemTbody">
	                	<c:forEach items="${scmsGroups}" varStatus="i" var="scmsGroup" >
			                <tr>
			               		<td><input type="hidden" value="${scmsGroup.id}" name="groupIds"><input type="hidden" value="" name="ids">${i.count}</td>
			               		<td><input type="hidden" value="${scmsGroup.name}" name="areaNames">${scmsGroup.name}</td>
			                    <td>
			                        <input class="input input-full" type="text" name="areaPrices" maxlength="10">
			                    </td>
			                    <td>
			                        <input class="input input-full" type="text" name="zjjfPrices" maxlength="10">
			                    </td>
			                    <td>
			                        <input class="input input-full" type="text" name="marketPrices" maxlength="10">
			                    </td>
			                </tr>
						</c:forEach>
	                </tbody>
	            </table>
	        </div>
	        <p>
	            <label class="label">上架状态：</label>
	            <input type="radio" value="1" name="status"> 上架
	            <input type="radio" value="0" name="status" class="ml-default"> 下架
	        </p>
        </form>
        <p>
            <label class="label"></label>
            <input type="button" class="button button-ok" value="保存" id="submit">
            <input type="button" class="button button-cancel" value="取消" id="return"  onclick="javascript:location.href=document.referrer;">
        </p>
    </div>
</div>
<script>
	$(function(){
		var nowDate = new Date();
		var nowYear = nowDate.getFullYear();
		var nowMonth = nowDate.getMonth(); 
		$(function(){
			var itembaseId = '';
			$.post("${root}/scms/item/findByItemId.do",{id : '${scmsItemMgRo.id}'},function(data){
				if(data.success){
					var yearHtml = '';
					yearHtml += '<option value="0" selected="selected">不限</option>';
					for (var i = nowYear; i >= 1900; i--) {
						yearHtml += '<option value="'+i+'">'+i+'</option>'
					}
					$('select[name="year"]').html(yearHtml);
					var monthHtml = '';
					monthHtml += '<option value="0" selected="selected">不限</option>';
					for (var j = 1; j < 13; j++) {
						monthHtml += '<option value="'+j+'">'+j+'</option>'
					}
					$('select[name="month"]').html(monthHtml);
					var pricesHtml = '';
					$.each(data.message , function(i,scmsItem){
						itembaseId = scmsItem.itemBaseId;
						$('select[name="month"]').val(scmsItem.month);
						$('select[name="year"]').val(scmsItem.year);
						$('input[name="status"][value="'+scmsItem.status+'"]').prop("checked","checked"); 
						$('input[name="mdseId"]').val(scmsItem.mdseId);
						$('input[name="goodName"]').val(scmsItem.goodName);
						$('textarea[name="mark"]').val(scmsItem.mark);
						$('#itemTbody').find('tr').each(function(){
							if($(this).find('input[name="groupIds"]').val() == scmsItem.groupId){
								
								$(this).find('input[name="ids"]').val(scmsItem.id);
								$(this).find('input[name="areaPrices"]').val(scmsItem.areaPrice);
								$(this).find('input[name="zjjfPrices"]').val(scmsItem.zjjfPrice);
								$(this).find('input[name="marketPrices"]').val(scmsItem.marketPrice);
							}
						});
					});
					$.post("${root}/scms/item/findByMdseId.do",$('#itemForm').serializeArray(),function(data){
						if(data.success){
							var html = '<label class="label">规格：</label>';
							$.each(data.message , function(i,itembase){
								html += '<input type="radio" name="itemBaseId" value="'+itembase.id+'"> '+itembase.spec;
							});
							$('#itemBaseId').html(html);
							$('input[type="radio"][value="'+itembaseId+'"]').prop("checked","checked"); 
						}
					},'json');
				}else{
					layer.msg('页面信息有误请刷新！', {icon: 5});
				}
			},'json');
		});
		function findByMdseId(mdseId){
			$.post("${root}/scms/item/findByMdseId.do",{mdseId:mdseId},function(data){
				if(data.success){
					var html = '<label class="label">规格：</label>';
					$.each(data.message , function(i,itembase){
						$('input[name="goodName"]').val(itembase.name);
						html += '<input type="radio" name="itemBaseId" value="'+itembase.id+'" checked="checked"> '+itembase.spec;
					});
					$('#itemBaseId').html(html);
					$('textarea[name="mark"]').focus();
				}
			},'json');
		}
			$('input[name="goodName"]').on("keyup" , function(e){
				if(e.keyCode!=13) {
					$('input[name="mdseId"]').val('');
					$('#itemBaseId').html('');
	            }
			});
			$('input[name="mdseId"]').on("keyup" , function(e){
				if(e.keyCode!=13) {
					$('input[name="goodName"]').val('');
					$('#itemBaseId').html('');
				}
			});
	   	 	$('input[name="mdseId"]').autocomplete({
	               serviceUrl: "${root}/scms/item/getItemBaseList.do",
	               params: {mdseId:$('input[name="mdseId"]').val()},
	               paramName: 'mdseId',
	               transformResult: function(response) {
	                   var res = JSON.parse(response);
	                   
	                   if(res.message != null) {
	                	   return {
		                       suggestions: $.map(res.message, function(value, key) {
		                       	return { value: value.mdseId+'('+value.name+')', data: value };
		                       })
		                   };
	    	            }else{
	    	            	return {
	    		            	suggestions: [{ value: "无数据"}]
	    		            };
	    	            }
	               },
	               onSelect: function(dd) {
	            	   var itembase = dd.data;
	            	   $('input[name="mdseId"]').val(itembase.mdseId)
	            	   $('input[name="goodName"]').val(itembase.name);
	            	   findByMdseId(itembase.mdseId);
	               }
	        });
	   	 	$('input[name="goodName"]').autocomplete({
               serviceUrl: "${root}/scms/item/getItemBaseList.do",
               params: {name:$('input[name="goodName"]').val()},
               paramName: 'name',
               transformResult: function(response) {
                   var res = JSON.parse(response);
                   if(res.message != null) {
                	   return {
	                       suggestions: $.map(res.message, function(value, key) {
	                       	return { value: value.name, data: value };
	                       })
	                   };
    	            }else{
    	            	return {
    		            	suggestions: [{ value: "无数据"}]
    		            };
    	            }
               },
               onSelect: function(dd) {
            	   var itembase = dd.data;
            	   $('input[name="mdseId"]').val(itembase.mdseId)
            	   $('input[name="goodName"]').val(itembase.name);
            	   findByMdseId(itembase.mdseId);
               }
        });
		$('#submit').on('click' , function(){
			layer.load(2);
			var isSubmit = true;
			if($.trim($('input[name="mdseId"]').val()) == ''){
				layer.closeAll('loading');
				$('input[name="mdseId"]').focus();
				layer.tips('商品条形码不能为空', $('input[name="mdseId"]'));
				isSubmit = false;
			}else if($.trim($('input[name="goodName"]').val()) == ''){
				layer.closeAll('loading');
				$('input[name="goodName"]').focus();
				layer.tips('商品名称不能为空', $('input[name="goodName"]'));
				isSubmit = false;
			}else if($('input[type="radio"][name="itemBaseId"]').val() == ''){
				layer.closeAll('loading');
				$('input[name="mdseId"]').val('');
				$('input[name="mdseId"]').focus();
				layer.tips('无规格信息请重新录入条形码', $('input[name="mdseId"]'));
				isSubmit = false;
			}else{
				$('#itemTbody').find('tr').each(function(){
					var testPriceCheck = /^\d{1,5}(\.\d{1,2})?$/;
					var areaPrices = $.trim($(this).find('input[name="areaPrices"]').val());
					var zjjfPrices = $.trim($(this).find('input[name="zjjfPrices"]').val());
					var marketPrices = $.trim($(this).find('input[name="marketPrices"]').val());
					if(areaPrices == ''){
						layer.closeAll('loading');
						$(this).find('input[name="areaPrices"]').focus();
						layer.tips('采购价不能为空', $(this).find('input[name="areaPrices"]'));
						isSubmit = false;
						return false;
					}else if(!testPriceCheck.test(areaPrices)){
						layer.closeAll('loading');
						$(this).find('input[name="areaPrices"]').focus();
						layer.tips('采购价格式格式有误', $(this).find('input[name="areaPrices"]'));
						isSubmit = false;
						return false;
					}else if(zjjfPrices == ''){
						layer.closeAll('loading');
						$(this).find('input[name="zjjfPrices"]').focus();
						layer.tips('转角售价不能为空', $(this).find('input[name="zjjfPrices"]'));
						isSubmit = false;
						return false;
					}else if(!testPriceCheck.test(zjjfPrices)){
						layer.closeAll('loading');
						$(this).find('input[name="zjjfPrices"]').focus();
						layer.tips('转角售价格式有误', $(this).find('input[name="zjjfPrices"]'));
						isSubmit = false;
						return false;
					}else if(marketPrices == ''){
						layer.closeAll('loading');
						$(this).find('input[name="marketPrices"]').focus();
						layer.tips('市场价不能为空', $(this).find('input[name="marketPrices"]'));
						isSubmit = false;
						return false;
					}else if(!testPriceCheck.test(marketPrices)){
						layer.closeAll('loading');
						$(this).find('input[name="marketPrices"]').focus();
						layer.tips('市场价格式有误', $(this).find('input[name="marketPrices"]'));
						isSubmit = false;
						return false;
					}
				});
			}
			if(isSubmit){
				$.post("${root}/scms/item/updateScmsItem.do",$('#itemForm').serializeArray(),function(data){
					if(data.success){
						layer.closeAll('loading');
						layer.msg("修改成功", {
						    icon: 1,
						    time: 2000 //2秒关闭（如果不配置，默认是3秒）
						}, function(){
							$('#return').click();
						});
					}else{
						layer.closeAll('loading');
						layer.msg(data.message, {icon: 5});
					}
				},'json');
			}
		});
	});
</script>
</body>
</html>