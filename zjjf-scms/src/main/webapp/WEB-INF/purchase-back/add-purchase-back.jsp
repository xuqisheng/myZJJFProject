<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="../../resources/css/base.css" />
		<link rel="stylesheet" href="../../resources/css/order-detail.css">
		<script src="../../resources/vendor/jquery/jquery-1.12.4.min.js"></script>
		<script src="../../resources/js/comm.js"></script>
		<script src="../../resources/vendor/My97DatePicker/WdatePicker.js"></script>
		<script src="../../resources/vendor/drag/drag.1.0-min.js"></script>
	</head>
	<style type="text/css">
		label {
			display: inline-block;
			width: 80px;
		}
	</style>

	<body>
		<div class="wrap-bd">
			<div class="mb-small">
				<div class="title">新增采购订单</div>
			</div>
			<div class="bg wrap-bd clearfix">
				<div class="mb-default">
					<label for="">供应商：</label>
					<input type="button" class="button button-default mr-default" name="" id="J_addSupplier" value="选择供应商" />
					<label for="" class="ml-default">退货时间：</label>
					<input type="text" class="input input-search-date" onclick="WdatePicker()" name="" id="" value="" />
				</div>
				<div class="mb-default">
					<label for="">备注：</label>
					<input type="" class="input input-text" name="" id="" value="" style="width: 576px;" />
				</div>
				<table class="table-list table-border" id="stock_info">
					<thead>
						<tr>
							<th>序号</th>
							<th>商品供应码</th>
							<th>箱码</th>
							<th>商品条形码</th>
							<th>商品名称</th>
							<th>规格</th>
							<th>退货数量</th>
							<th>备注</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody class="table-tbody">
						<tr>
							<td>1</td>
							<td>
								<input type="text" class="input input-search-date" name="" id="" value="" />
								<input type="button" class="button button-operate" name="" id="J_addNew" value="..." />
							</td>
							<td>1</td>
							<td>1</td>
							<td>1</td>
							<td>1</td>
							<td><input type="text" name="" class="input input-search-date" id="" value="" /></td>
							<td><input type="text" name="" class="input input-search-date" id="" value="" /></td>
							<td>
								<input type="button" name="" id="" class="button button-operate J_del" value="删除" />
							</td>
						</tr>
					</tbody>
				</table>
				<div class="cover-all">

				</div>
				<!--选择商品-->
				<div class="dialog hidden" id="J_dialogGoodsNew" style="width: 700px;">
					<div class="dialog-head" style="cursor: move;">
						选择商品
						<div id="" class="dialog-close">
						</div>
					</div>
					<div class="dialog-body dialog-padding">
						<input type="text" name="" id="" value="" placeholder="供应商编号/名称" class="input mr-default" style="width: 200px;" />
						<input type="text" name="" id="" value="" placeholder="商品名称/条形码" class="input mr-default" style="width: 200px;" />
						<input type="button" class="input-search-button" name="" id="" value="搜索" />
						<div class="mt-default" style="height: 250px;overflow: auto;">
							<table class="table-list table-border" id="">
								<thead>
									<tr>
										<th>商品供应码</th>
										<th>供应商名称</th>
										<th>商品条形码</th>
										<th>箱码</th>
										<th>商品名称</th>
										<th>规格</th>
										<th>单位</th>
									</tr>
								</thead>
								<tbody class="table-tbody">
									<tr>
										<td>1</td>
										<td>1</td>
										<td>1</td>
										<td>1</td>
										<td>1</td>
										<td>1</td>
										<td>1</td>
									</tr>
									<tr>
										<td>1</td>
										<td>1</td>
										<td>1</td>
										<td>1</td>
										<td>1</td>
										<td>1</td>
										<td>1</td>
									</tr>
									<tr>
										<td>1</td>
										<td>1</td>
										<td>1</td>
										<td>1</td>
										<td>1</td>
										<td>1</td>
										<td>1</td>
									</tr>
									<tr>
										<td>1</td>
										<td>1</td>
										<td>1</td>
										<td>1</td>
										<td>1</td>
										<td>1</td>
										<td>1</td>
									</tr>
									<tr>
										<td>1</td>
										<td>1</td>
										<td>1</td>
										<td>1</td>
										<td>1</td>
										<td>1</td>
										<td>1</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="dialog-foot">
						<input type="button" class="dialog-button dialog-ok" id="" value="确认">
						<input type="button" class="dialog-button dialog-cancel ml-default" value="取消">
					</div>
					</form>
				</div>

				<!--选择供应商-->
				<div class="dialog hidden" id="J_dialogSupplier" style="width: 700px;">
					<div class="dialog-head" style="cursor: move;">
						选择供应商
						<div id="" class="dialog-close">
						</div>
					</div>
					<div class="dialog-body dialog-padding">
						<input type="text" name="" id="" value="" placeholder="供应商名称" class="input input-default mr-default" />
						<input type="button" class="input-search-button" name="" id="" value="搜索" />
						<div class="mt-default" style="height: 250px;overflow: auto;">
							<table class="table-list table-border" id="">
								<thead>
									<tr>
										<th>序号</th>
										<th>供应商编号</th>
										<th>供应商名称</th>
									</tr>
								</thead>
								<tbody class="table-tbody">
									<tr>
										<td>1</td>
										<td>1</td>
										<td>1</td>
								</tbody>
							</table>
						</div>
					</div>
					<div class="dialog-foot">
						<input type="button" class="dialog-button dialog-ok" id="" value="确认">
						<input type="button" class="dialog-button dialog-cancel ml-default" value="取消">
					</div>
					</form>
				</div>
			</div>
			<div class="mb-default mt-default">
				<input type="button" class="button-save" name="" id="" value="确认" />
				<input type="button" class="button-save" name="" id="printBillinfo" value="打印" />
				<input type="button" class="button-cancel" name="" id="" value="取消" />
			</div>
		</div>

	</body>
	<script>
		var oDialog = document.getElementsByClassName("dialog");
		var oDialogHead = document.getElementsByClassName("dialog-head");
		//		var oDialogbody = document.getElementsByClassName("dialog-body")[0];
		startDrag(oDialogHead[1], oDialog[1]);

		$(function() {
			dialogPosCenter('#J_dialogGoodsNew');
			dialogPosCenter('#J_dialogSupplier');
			$('#J_addNew').on('click', function() {
				$('#J_dialogGoodsNew,.cover-all').fadeIn();
			});
			$('#J_addSupplier').on('click', function() {
				$('#J_dialogSupplier').fadeIn();
			});
			$('.dialog').on('click', '.dialog-cancel', function() {
				$('.dialog,.cover-all').fadeOut();
			})
			$('.dialog').on('click', '.dialog-close', function() {
				$('.dialog,.cover-all').fadeOut();
			})
			$('.table-list').on('click', 'td', function() {
				$(this).parent().css({
					'background': '#009dd9',
					'color': 'white'
				}).siblings().css({
					'background': '#fff',
					'color': 'black'
				});
				$_tr = $(this).parent();
			});
			$('tbody').on('click', '.J_del', function() {
				if(confirm('确认删除？')) {
					$(this).parent('td').parent('tr').remove();
				}
			})
		})
	</script>

</html>