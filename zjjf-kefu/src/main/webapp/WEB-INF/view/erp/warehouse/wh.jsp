<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setAttribute("root", request.getContextPath());%>
<%@ include file="../../common/taglib.jsp"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>仓库管理</title>
		<link rel="stylesheet" type="text/css" href="../../resources/css/base.css" />
		<script src="../../resources/vendor/jquery/jquery-1.12.4.min.js"></script>
		<script src="../../resources/js/comm.js"></script>
		<script src="../../resources/vendor/drag/drag.1.0-min.js"></script>
	</head>
	<style type="text/css">

	</style>

	<body>
		<div class="wrap-bd">
			<div class="mb-small clearfix">
				<div class="fl title">仓库管理</div>
			</div>
			<div class="op-section clearfix">
				<form action="${root }/erp/warehouse/getWarehouseBySupplierId.do" method="post">
					<label id="" class="label">关键词：</label>
					<input type="text" name="numAndName" id="numAndName" value="${warehouseRo.numAndName }" class="mr-default input input-default" placeholder="仓库编号/名称" />
					<input type="submit" class="input-search-button ml-default" value="搜索" />
				</form>
			</div>
			<div class="mb-small clearfix mt-default">
				<span class="button button-operate" id="J_New">新建</span>
				<span class="button button-operate" id="J_edit">修改</span>
				<span class="button button-operate" id="J_del">删除</span>
			</div>
			<table class="table-list table-border" id="stock_info">
				<thead>
					<tr>
						<th>序号</th>
						<th>仓库编号</th>
						<th>仓库名称</th>
						<th>仓库简称</th>
						<th>仓库地址</th>
						<th>创建人</th>
						<th>创建时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody class="table-tbody">
					<c:choose>
						<c:when test="${warehouseList != null && warehouseList.size()>0 }">
							<c:forEach var="warehouse" items="${warehouseList }" varStatus="sta">
								<tr>
									<td>${sta.index+1 }<input type="hidden" value="${warehouse.id }" class="l_id"></td>
									<td>${warehouse.code }</td>
									<td>${warehouse.name }</td>
									<td>${warehouse.abbreviation }</td>
									<td>${warehouse.provinceName }${warehouse.cityName }${warehouse.areaName }${warehouse.address }</td>
									<td>${warehouse.createName }</td>
									<td><fmt:formatDate value="${warehouse.addTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td>
										<!-- <span class="button button-operate whExit">分配销售区域</span> -->
										<span class="button button-operate whExit">编辑</span>
										<span class="button button-operate whDlt">删除</span>
									</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr><td colspan="8" class="no-data"></td></tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<c:if test="${fn:length(warehouseList)>0}">
			  <%@ include file="../../common/pagination-kk.jsp"%>
		    </c:if>
			<div class="dialog hidden" id="J_dialogAddNew">
				<input type="hidden" name="id" id="id">
				<form action="" method="post" id="fm">
					<div class="dialog-head" style="cursor: move;">
						<div class="dialog-title" id="tit">管理仓库</div>
						<div class="dialog-close"></div>
					</div>
					<div class="dialog-body dialog-padding">
						<p>
							<label class="label">仓库名称：</label>
							<input type="text" name="name" id="name" class="input" style="width:360px" maxlength="50" placeholder="请输入仓库名称">
						</p>
						<div class="clearfix">
							<p class="fl">
								<label class="label">仓库编号：</label>
								<input type="text" name="code" id="code" class="input" style="width:120px" maxlength="50" placeholder="编号如：CK01">
							</p>
							<p class="fr">
								<label class="label">仓库简称：</label>
								<input type="text" name="abbreviation" id="abbreviation" class="input" style="width:120px" maxlength="50">
							</p>
						</div>
						<div class="clearfix">
							<p class="fl">
								<label class="label">联系人：</label>
								<input type="text" name="contacts" id="contacts" class="input" style="width:120px" maxlength="50" placeholder="请输入联系人">
							</p>
							<p class="fr">
								<label class="label">联系电话：</label>
								<input type="text" name="mobile" id="mobile" class="input" style="width:120px" maxlength="13" placeholder="请输入联系电话">
							</p>
						</div>
						<p class="clearfix">
							<label class="label">仓库地址：</label>
							<input type="text" name="address" id="address" class="input" style="width:360px" maxlength="50" placeholder="请输入仓库地址">
						</p>
					</div>
					<div class="dialog-foot">
						<input type="button" class="dialog-button dialog-ok" id="okSave" value="保存">
						<input type="button" class="dialog-button dialog-cancel ml-default" value="取消">
						<input type="reset" class="hidden" />
					</div>
				</form>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		var oDialog = document.getElementsByClassName("dialog")[0];
		var oDialogHead = document.getElementsByClassName("dialog-head")[0];
//		var oDialogbody = document.getElementsByClassName("dialog-body")[0];
		startDrag(oDialogHead, oDialog); 

		//删除
		function whDlt(id){
			$.ajax({
				url:'${root}/erp/warehouse/delWarehouse/1/'+id+'.do',
				type:'post',
				dataType:'json',
				success:function(data){
					if(data.success){
						window.location.reload();
					}else{
						alert(data.message);
					}
				},
				error:function(error){
					alert("请求失败");
				}
			});
		}
		
		//编辑时回写数据
		function showData(id){
			$.ajax({
				url:'${root}/erp/warehouse/getWarehouseById/'+id+'.do',
				type:'post',
				dataType:'json',
				async: false,
				success:function(data){
					if(data.success){
						$('#id').val(data.message.id);
						$('#name').val(data.message.name);
						$('#code').val(data.message.code);
						$('#abbreviation').val(data.message.abbreviation);
						$('#contacts').val(data.message.contacts);
						$('#mobile').val(data.message.mobile);
						$('#address').val(data.message.address);
					}
				},
				error:function(error){
					alert('请求失败');
				}
			});
		}
		
		$(function() {
			dialogPosCenter('#J_dialogAddNew');
			$('.whDlt').on('click',function(){
				var id = $(this).parent().parent().find('.l_id').val();
				whDlt(id);
			});
			
			//添加页面
			$('#J_New').on('click', function() {
				$('#tit').text('添加仓库');
				$('#J_dialogAddNew, .cover-all').fadeIn();
				/* location.href="${root}/erp/warehouse/returnWarehousePage/1.do"; */
				
			});
			//编辑页面
			$('.whExit').on('click',function(){
				$('#tit').text('编辑仓库');
				var id = $(this).parent().parent().find('.l_id').val();
				/* location.href="${root}/erp/warehouse/returnWarehousePage/2.do?id="+id; */
				showData(id);
				$('#J_dialogAddNew, .cover-all').fadeIn();
			});
			$('.dialog').on('click', '.dialog-cancel,.dialog-close', function() {
				$('.dialog, .cover-all').fadeOut();
				location.href="${root}/erp/warehouse/getWarehouseBySupplierId.do";
			});
			var $_tr = null;
			$('.table-list td').click(function() {
				$(this).parent().css({'background':'#009dd9','color':'white'}).siblings().css({'background':'#fff','color':'black'});	
				$_tr = $(this).parent();
			});
			$('#J_edit').on('click',function(){
				if($_tr != null){
					showData($_tr.find('.l_id').val());
					$('#J_dialogAddNew, .cover-all').fadeIn();
				}
			});
			$('#J_del').on('click',function(){
				if($_tr != null){
					whDlt($_tr.find('.l_id').val());
				}
			});
			
			//保存
			$('#okSave').on('click',function(){
				//校验
				var name = $.trim($('#name').val());
				if(name == ""){
					alert("仓库名称不能为空");
					return;
				}
				var code = $.trim($('#code').val());
				if(code == ""){
					alert("仓库编号不能为空");
					return;
				}
				var contacts = $.trim($('#contacts').val());
				if(contacts == ""){
					alert("联系人不能为空");
					return;
				}
				var mobile = $.trim($('#mobile').val());
				if(mobile == ""){
					alert("联系电话不能为空");
					return;
				}
				var address = $.trim($('#address').val());
				if(address == ""){
					alert("仓库地址不能为空");
					return;
				}
				var id = $('#id').val();
				var url = '${root}/erp/warehouse';
				var param = '';
				if(id == null || id == '' || id == 'null' || id == 'undefined'){
					url+='/addWarehouse/'+1+'.do';
				}else{
					url+='/editWarehouse/'+1+'/'+id+'.do';
				}
				$.ajax({
					url:url,
					type:'post',
					data:$('#fm').serializeArray(),
					dataType:'json',
					success:function(data){
						alert(data.message);
						if(data.success){
							window.location.href="${root}/erp/warehouse/getWarehouseBySupplierId.do";
						}
					},
					error:function(error){
						alert('请求失败');
					}
				});
			});
		})
	</script>

</html>