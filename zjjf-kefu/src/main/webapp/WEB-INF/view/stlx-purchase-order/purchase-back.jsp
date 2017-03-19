<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setAttribute("root", request.getContextPath());%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>采购退货</title>
		<link rel="stylesheet" type="text/css" href="${root}/resources/css/base.css" />
		<script src="${root}/resources/vendor/jquery/jquery-1.12.4.min.js"></script>
		<script src="${root}/resources/js/comm.js"></script>
		<script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
		<%@ include file="../common/head.jsp"%>
	</head>

	<body>
		<div class="wrap-bd">
			<div class="mb-small">
				<a href="#" class="crumb">采购管理</a>
				<a href="#" class="crumb">采购退货管理</a>
			</div>
			<div class="mb-small clearfix">
				<div class="fl mt-default">采购退货管理</div>
			</div>
			<div class="bg wrap-bd mb-default">
			<input type="hidden" value="${pageIndex}" id="pageIndex" name="pageIndex">
			  <form action="${root}/kefu/erpOrder/toPurchaseBaceOrder.do" method="post">
				<div class="">
					<label for="">关键词：</label>
					<input type="text" class="input input-default mr-default" name="keyStr" id="" value="${keyStr}" placeholder="退货单号/仓库名称" />
					<label for="">审核状态：</label>
					<select name="checkStatus" class="select mr-default">
						<option value="-1" <c:if test="${checkStatus==-1}">selected</c:if>>全部</option>
						<option value="2" <c:if test="${checkStatus==2}">selected</c:if>>已审核</option>
						<option value="1" <c:if test="${checkStatus==1}">selected</c:if>>未审核</option>
					</select>
					<label for="">退货时间：</label>
					<input type="text" class="input input-date J_timeS" name="startTime" id="" value="<fmt:formatDate value="${startTime}" pattern="yyyy-MM-dd" />" />&nbsp;至&nbsp;
					<input type="text" class="input input-date mr-default J_timeE" name="endTime" id="" value="<fmt:formatDate value="${endTime}" pattern="yyyy-MM-dd" />" />
					<input type="submit" class="input input-search-button" name="" id="" value="搜索" />
				</div>
				</form>
			</div>
			<div class="mb-default">
				<input type="button" class="button button-operate" name="" id="J_check" value="审核" />
				<input type="button" class="button button-operate" name="" id="J_watch" value="查看" />
				<!-- <input type="button" class="button button-operate" name="" id="" value="删除" />
				<input type="button" class="button button-operate" name="" id="" value="打印" /> -->
			</div>
			<table class="table-list mb-default">
				<thead>
					<tr>
						<th><input type="checkbox" class="checkbox" name="" id="J_selectAll" value="" /></th>
						<th>序号</th>
						<th>退货单号</th>
						<th>退货仓库</th>
						<th>供应商名称</th>
						<th>退货金额</th>
						<th>退货数量</th>
						<th>退货人</th>
						<th>退货时间</th>
						<th>是否审核</th>
					</tr>
				</thead>
				<tbody class="table-tbody">
					<c:forEach var="item" items="${list}" varStatus="status">
					<tr data-checkStatus="${item.checkStatus}">
						<td><input type="checkbox" class="checkbox J_chk" name="" id="" value="${item.id}" /></td>
						<td>${status.index+1}</td>
						<td>${item.orderId}</td>
						<td>${item.whName}</td>
						<td>${item.managerName}</td>
						<td>${item.itemPrice}</td>
						<td>${item.totalNum}</td>
						<td>${item.addUserName}</td>
						<td><fmt:formatDate value="${item.taskTime}" pattern="yyyy-MM-dd" /> </td>
						<td>
						  <c:if test="${item.checkStatus==1}">
						     未审核
						  </c:if>
						  <c:if test="${item.checkStatus==2}">
						     已审核
						  </c:if>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<c:if test="${fn:length(list)>0}">
		<%@ include file="../common/pagination-kk.jsp"%>
		</c:if>
	</body>
	<script>
	    selectAll('#J_selectAll', '.J_chk');

	    $('.J_chk').on('click',function(){
	    	if($(this).is('.isChk')){
	    		$(this).removeClass('isChk');
	    	}else{
	    		$(this).addClass('isChk');
	    	}
	    })


		$(function(){
    	dateRange('.J_timeS', '.J_timeE', 1);



    	//查看按钮
		$('#J_watch').on('click',function(){
			var chk = 0;
			for(var i = 0; i < $('.J_chk').length; i++) {
				if($('.J_chk').eq(i).is(':checked')) {
					$('.J_chk').eq(i).addClass('isChk');
					chk++;
				}
			}
			if(chk == 1) {
				var id = $('.J_chk.isChk').eq(0).val();
				location.href='${root}/kefu/erpOrder/toWatchPurchaseBackOrder.do?id='+id;
			} else if(chk > 1) {
				layer.msg('只能查看其中一项',{time:2000});
			} else {
				layer.msg('请选择一项查看',{time:2000});
			}
		});

    	//审核按钮
		$('#J_check').on('click', function() {
			var chk = 0;
			for(var i = 0; i < $('.J_chk').length; i++) {
				if($('.J_chk').eq(i).is(':checked')) {
					$('.J_chk').eq(i).addClass('isChk');
					chk++;
				}
			}
			if(chk==0){
				layer.msg('请至少选择一项进行审核!');
				return;
			}

			var managerIdStr = '';
			var errorNum = 0;
			var msg = '序号';
			$('.J_chk.isChk').each(function(){
				var checkstatus = $(this).parent('td').parent('tr').attr('data-checkstatus');
				if(checkstatus==2){
					errorNum++;
					var index = $(this).parent('td').next('td').html();
					msg+=index+'  ';
				}
				managerIdStr+=","+$(this).val();
			});

			if(errorNum>0){
				msg+='退货单已审核通过,不能再次审核!';
				layer.msg(msg);
				return;
			}
			managerIdStr=managerIdStr.substring(1,managerIdStr.length);
			$.ajax({
				url: '${root}/kefu/erpOrder/updatePurchaseOrder.do',
				type: 'post',
				data: {'managerIdStr':managerIdStr},
				dataType: 'json',
				success: function(data) {
					if(data.success){
						layer.msg('审核成功,页面即将刷新!',{time:1000},function(){
							location.href="${root}/kefu/erpOrder/toPurchaseBaceOrder.do?pageIndex="+$('#pageIndex').val();
						});
					}else{
						layer.msg(data.message,function(){
							location.href="${root}/kefu/erpOrder/toPurchaseBaceOrder.do?pageIndex="+$('#pageIndex').val();
						});
					}
				},
				error: function(error) {
					layer.msg('请求失败');
				}
			});
		});
		})
	</script>
</html>
