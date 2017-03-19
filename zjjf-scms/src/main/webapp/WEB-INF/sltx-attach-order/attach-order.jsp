<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
	<meta charset="UTF-8">
	<title>供应商管理</title>
	<link rel="stylesheet" type="text/css" href="../../resources/css/base.css" />
	<script src="../../resources/vendor/jquery/jquery-1.12.4.min.js"></script>
	<script src="../../resources/js/comm.js"></script>
	<script src="../../resources/vendor/drag/drag.1.0-min.js"></script>
</head>

<body>
	<div class="wrap-bd">
		<div class="">
			<div class="mb-default title">附属订单</div>
		</div>
		<div class="">
			<table class="table-list mb-default">
				<thead>
					<tr>
						<th>类型</th>
						<th>兑换对象</th>
						<th>商品条码</th>
						<th>商品名称</th>
						<th>数量</th>
						<th>兑换商品数量</th>
						<th>抵扣金额</th>
						<th>回收数量</th>
						<th>确认兑换数量</th>
						<th>确认抵扣金额</th>
						<th>备注</th>
					</tr>
				</thead>
				<tbody class="table-tbody">
					<tr>
						<td>
							<select name="" class="select">
								<option value="">兑换商品</option>
								<option value="">抵扣金额</option>
							</select>
						</td>
						<td>
							<select name="" class="select">
								<option value="">瓶盖</option>
								<option value="">瓶身</option>
								<option value="">纸箱</option>
							</select>
						</td>
						<td>
							<input type="text" class="input input-search-date" name="" id="" value="" />
							<input type="button" class="button button-operate" name="" id="J_addNew" value="..." />
						</td>
						<td>好多好多瓶</td>
						<td><input type="text" class="input input-search-date" name="" id="" value="" /></td>
						<td><input type="text" class="input input-search-date" name="" id="" value="" /></td>
						<td><input type="text" class="input input-search-date" name="" id="" value="" /></td>
						<td><input type="text" class="input input-search-date" name="" id="" value="" /></td>
						<td><input type="text" class="input input-search-date" name="" id="" value="" /></td>
						<td><input type="text" class="input input-search-date" name="" id="" value="" /></td>
						<td><input type="text" class="input input-default" name="" id="" value="" /></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="dialog hidden" id="J_dialogGoodsNew" style="width: 700px;">
			<div class="dialog-head" style="cursor: move;">
				选择商品
				<div id="" class="dialog-close">
				</div>
			</div>
			<div class="dialog-body dialog-padding">
				<input type="text" name="" id="" value="" placeholder="商品名称" class="input mr-default input-default" />
				<input type="button" class="input-search-button" name="" id="" value="搜索" />
				<div class="mt-default" style="height: 250px;overflow: auto;">
					<table class="table-list table-border" id="">
						<thead>
							<tr>
								<th>条码</th>
								<th>商品名称</th>
								<th>规格</th>
							</tr>
						</thead>
						<tbody class="table-tbody">
							<tr>
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
		</div>
		<div class="mt-default">
			<input type="button" class="button button-save" name="" id="" value="确认" />
			<input type="button" class="button-cancel" name="" id="" value="取消" />
		</div>
	</div>
</body>
<script type="text/javascript">
	var oDialog = document.getElementsByClassName("dialog");
	var oDialogHead = document.getElementsByClassName("dialog-head");
	//		var oDialogbody = document.getElementsByClassName("dialog-body")[0];
	startDrag(oDialogHead[0], oDialog[0]);
	dialogPosCenter('#J_dialogGoodsNew');
	$('#J_addNew').on('click', function() {
		$('#J_dialogGoodsNew').fadeIn();
	});
	$('.dialog').on('click', '.dialog-cancel', function() {
		$('.dialog').fadeOut();
	})
	$('.dialog').on('click', '.dialog-close', function() {
		$('.dialog').fadeOut();
	})
</script>

</html>