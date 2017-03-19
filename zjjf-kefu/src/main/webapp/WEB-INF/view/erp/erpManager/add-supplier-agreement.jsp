<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setAttribute("root", request.getContextPath());%>
<%@ include file="../../common/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>供应商合同管理</title>
		<link rel="stylesheet" type="text/css" href="${root}/resources/css/base.css" />
		<script src="${root}/resources/vendor/jquery/jquery-1.12.4.min.js"></script>
		<script src="${root}/resources/js/comm.js"></script>
		<script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
		<%@ include file="../../common/head.jsp"%>
	</head>
	<style type="text/css">
		ul,
		ol,
		li {
			padding: 0;
			margin: 0;
			list-style: none;
		}
		
		li {
			margin-bottom: 18px;
		}
		
		ul li select {
			width: 200px;
		}
		.label{
			width: 150px;
		}
		input[type="text"] {
			width: 200px;
		}
		.J_managerSelected {
		  background:#de0e0e;
		}
	</style>

	<body>
		<div class="wrap-bd">
		<form id="contractForm">
		<input type="hidden" name="id" value="${contract.id}" id="">
		<input type="hidden" name="erpManagerId" value="${manager.id}" id="erpManagerId">
			<div class="mb-default">
				<a href="${root}/maContract/toContractIndex.do" class="crumb">供应商合同管理</a>
				<c:if test="${not empty contract.id}">
				 <a href="#" class="crumb">编辑合同</a>
				</c:if>
				<c:if test="${empty contract.id}">
				 <a href="#" class="crumb">新增合同</a>
				</c:if>
				
			</div>
			<div class="">
			<c:if test="${not empty contract.id}">
				<div class="mb-small">编辑合同</div>
			</c:if>
			<c:if test="${empty contract.id}">
				<div class="mb-small">新增合同</div>
			</c:if>
			</div>
			<div class="bg wrap-bd">
				<div class="mb-default clearfix">
					<div class="fl" style="width: 33%;">
						<label for="" class="label">供应商编号：</label>
						<span class="mr-default" id="contractCode">${manager.managerCode}</span>
						<span class="button button-operate  ml-default" id="J_chsSup">选择供应商</span>
					</div>
					<div class="fl">
						<label for="" class="label">供应商名称：</label>
						<span class="mr-default" id="managerName">${manager.managerName}</span>
					</div>
				</div>
				<div class="mb-default clearfix">
					<div class="fl" style="width: 33%;">
						<label for="" class="label">合同编号：</label>
						<input type="text" name="code" class="input" id="code" value="${contract.code}" style="width: 200px;" />
						<span class="txt-warn">&nbsp;*</span>
					</div>
					<div class="fl">
						<label for="" class="label">合同有效期：</label>
						<input type="text" class="input J_timeS" name="startTime" id="startTime" value="<fmt:formatDate value="${contract.startTime}" pattern="yyyy-MM-dd" />" /> &nbsp;至&nbsp;
						<input type="text" class="input J_timeE" name="endTime" id="endTime" value="<fmt:formatDate value="${contract.endTime}" pattern="yyyy-MM-dd" />" />
						<span class="txt-warn">&nbsp;*</span>
					</div>
				</div>
				<div class="cover-all">

				</div>
				<div class="dialog hidden J_dialog" id="J_choseSupplier" style="width: 500px;">
					<form action="" method="post">
						<div class="dialog-head">
							选择供应商
							<div id="" class="dialog-close">
							</div>
						</div>
						<div class="dialog-body">
							<div class="mb-default">
								<input type="text" name="keyStr" id="J_keyStr" value="" class="input input-default" placeholder="供应商名称" />
								<input type="button" name="" class="input input-search-button" id="J_search" value="搜索" />
							</div>
							<div style="height: 200px;overflow: auto;">
								<table class="table-list">
									<thead>
										<tr>
											<!-- <th>序号</th> -->
											<th>供应商编号</th>
											<th>供应商名称</th>
										</tr>
									</thead>
									<tbody id="managerTbody">
									</tbody>
								</table>
								<%@ include file="../../common/pagination.jsp"%>
							</div>
						</div>
						<div class="dialog-foot">
							<div class="mt-default">
								<input type="button" class="dialog-button dialog-ok" name="" id="" value="确认" />
								<input type="button" class="dialog-button dialog-cancel" name="" id="" value="取消" />
							</div>
						</div>
					</form>
				</div>
				<div class="clearfix">
					<ul class="fl" style="width: 33%;">
						<li>
							<label class="label">
							结算天数：
						</label>
							<input type="text" name="dayNum" id="dayNum" value="${contract.dayNum}" class="input" />
							<span class="txt-warn">&nbsp;*</span>
						</li>
						<li>
							<label class="label">
							推广/促消费：
						</label>
							<input type="text" name="generalizeFee" id="" value="${contract.generalizeFee}" class="input" />
						</li>
						<li>
							<label class="label">
							违约金：
						</label>
							<input type="text" name="penalty" id="" value="${contract.penalty}" class="input" />&nbsp;元/每次
						</li>
					</ul>
					<ul class="fl" style="width: 33%;">
						<li>
							<label class="label">
							平台使用年费：
						</label>
							<input type="text" name="usePlatFormFee" id="" value="${contract.usePlatFormFee}" class="input" />
						</li>
						<li>
							<label class="label">
							仓储物流费：
						</label>
							<input type="text" name="logisticsFee" id="" value="${contract.logisticsFee}" class="input" />&nbsp;元/每月
						</li>
					</ul>
					<ul class="fl" style="width: 33%;">
						<li>
							<label class="label">
							技术服务费：
						</label>
							<input type="text" name="useTecFee" id="useTecFee" onblur="allLes()" value="${contract.useTecFee}" class="input" />&nbsp;%
							<span class="txt-warn">&nbsp;*</span>
						</li>
						<li>
							<label class="label">
							保证金：
						</label>
							<input type="text" name="margin" id="" value="${contract.margin}" class="input" />
						</li>
					</ul>
				</div>
				<div class="">
					<label for="" class="label">备注：</label>
					<textarea name="remark" class="textarea" style="width: 500px;" rows="" cols="">${contract.remark}</textarea>
				</div>
			</div>
			<div class="mt-default">
				<input type="button" name="" id="save" value="保存" class="button button-ok" />
				<span id="cancel" class="button button-cancel">
					取消
				</span>
			</div>
			</form>
		</div>
	</body>
	<script type="text/javascript">
		dateRange('.J_timeS', '.J_timeE', 1);
		
		//技术服务费的校验
		function allLes(){
			if($('#useTecFee').val()>100){
				alert('请输入0到100的数字')
				$('#useTecFee').val('')
			}
			if($('#useTecFee').val()<0){
				alert('请输入0到100的数字')
				$('#useTecFee').val('')
			}
			if($('#useTecFee').val().length != 0) {
					 var reg = /^\s*(\+|-)?((\d+([\.,]\d+)?)|([\.,]\d+))\s*$/;
					 var proLimitNum = $.trim($('#useTecFee').val())
					var r = proLimitNum.match(reg);
					if(r == null){
						alert('请输入大于0的数字!');
						$('#useTecFee').val('');
						return;
					}
					
				}
		}
		$(function() {
			dialogPosCenter('#J_choseSupplier');
			//选择供应商按钮
			$('#J_chsSup').on('click', function() {
				$('#J_choseSupplier, .cover-all').fadeIn();
				//列表查询
			    $("#jpagination").pagination({
			    	    pageSize: 10,
			    	    remote: {
			    	        url: '${root}/ERPMa/getAllERPManager.do',
			    	        success: function(data) {
			    	           var html = '';
			                   if(data.flag){
			                	   $.each(data.list,function(i,item){
			                		   html+='<tr class="J_manager" data-id="'+item.id+'">'
			                		       +'<td>'+item.managerCode+'</td>'
			                		       +'<td>'+item.managerName+'</td>'
			                		       +'</tr>';
			                	   });
			                	   $('#managerTbody').html(html);
			                   }
			    	        },
			    	        totalName:'totalSize'
			    	    }
			    	});
			});
			//选中供应商
			$('#managerTbody').on('click','.J_manager td',function(){
				$(this).parent().addClass('J_managerSelected').siblings().removeClass('J_managerSelected');
				$(this).parent().css({'background':'#009dd9','color':'white'}).siblings().css({'background':'#fff','color':'black'});
			});
			//选供应商确认按钮
			$('.J_dialog').on('click','.dialog-ok',function(){
				if($('#managerTbody').find('tr.J_managerSelected').length==0){
                    layer.msg('请选择供应商!',{tiem:2000});					
				};
				$('#managerTbody').find('tr.J_managerSelected').each(function(){
					    var contractCode= $(this).children().eq(0).html();
					    var managerName= $(this).children().eq(1).html();
                    	$('#contractCode').text(contractCode);	
                    	$('#managerName').text(managerName);
                    	$('#erpManagerId').val($(this).attr('data-id'));
				});
				$('.J_dialog, .cover-all').fadeOut();
			});
			
			
			//供应商搜索按钮
			$('#J_search').on('click',function(){
				var searchObject = {'keyStr':$.trim($('#J_keyStr').val())};
				$("#jpagination").pagination('setParams',searchObject);
            	$("#jpagination").pagination('setPageIndex', 0);
            	$("#jpagination").pagination('remote');
			});
			
			$('.J_dialog').on('click', '.dialog-cancel', function() {

				$('.J_dialog, .cover-all').fadeOut();
			})
			$('.dialog').on('click', '.dialog-close', function() {

				$('.J_dialog, .cover-all').fadeOut();
			});
			
			
			//保存按钮
			$('#save').on('click',function(){
				
				//参数校验
				if($.trim($('#code').val())==''){
					layer.msg('合同编号不能为空!',{time:2000});
					return;
				}
				if($.trim($('#startTime').val())==''||$.trim($('#endTime').val())==''){
					layer.msg('合同有效期不能为空!',{time:2000});
					return;
				}
				if($.trim($('#dayNum').val())==''){
					layer.msg('结算天数不能为空!',{time:2000});
					return;
				}
				if($.trim($('#useTecFee').val())==''){
					layer.msg('技术服务费不能为空!',{time:2000});
					return;
				}
				$.ajax({
					type : "post",
					url : '${root}/maContract/save.do',
					data:$('#contractForm').serialize(),
					dataType : "json",
					success : function(da) {
						if(da.success){
							layer.msg('保存成功!',{time:1000},function(){
								location.href="${root}/maContract/toContractIndex.do";
							});
						}
					},
					error : function(data) {
					}
				});
			});
			
			//取消按钮
			$('#cancel').on('click',function(){
				location.href="${root}/maContract/toContractIndex.do";
			});
			
			
		})
	</script>
</html>
