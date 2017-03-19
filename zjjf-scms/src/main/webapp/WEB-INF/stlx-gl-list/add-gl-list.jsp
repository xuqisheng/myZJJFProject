<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
	<meta charset="UTF-8">
	<title>供应商管理</title>
	<link rel="stylesheet" type="text/css" href="../../resources/css/base.css" />
	<script src="../../resources/vendor/jquery/jquery-1.12.4.min.js"></script>
	<script src="../../resources/js/comm.js"></script>
	<script src="../../resources/vendor/My97DatePicker/WdatePicker.js"></script>
	<script src="../../resources/vendor/drag/drag.1.0-min.js"></script>
	<script src="../../resources-src/js/fm.validator.js"></script>
</head>
<style type="text/css">
	.validator-error {
		color: red
	}
</style>
<body>
	<div class="wrap-bd">
		<div class="">
			<div class="mb-default title">新增损益单</div>
		</div>
		<form action="" method="post" class="validator">
			<div class="bg wrap-bd">
				<div>
					<label class="label">单据类型：</label>
					<select name="" class="select mr-default">
						<option value="">报损单</option>
						<option value="">报溢单</option>
					</select>
					<label for="" class="ml-default label">选择日期：</label>
					<input type="text" data-required class="input input-search-date mr-default" name="" onFocus="WdatePicker()" value="" />
					<span class="" style="padding:0 20px;margin:0 50px;">仓库：1111111</span>
					<span class="ml-default">单号：111111</span>
				</div>
				<div class="mt-default">
					<label for="" class="label">备注：</label>
					<input type="text" data-required style="width: 400px;" class="input mr-default" name="" value="" />
				</div>
			</div>
			<table class="table-list table-border">
				<thead>
					<tr>
						<th>商品条形码</th>
						<th>商品名称</th>
						<th>规格</th>
						<th>单位</th>
						<th>数量</th>
						<th>备注</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody class="table-tbody">
					<tr>
						<td>124324324234</td>
						<td>dds</td>
						<td>1fdsfs</td>
						<td>
							<input type="text" class="input input-search-date mr-default" name="" value=""  data-required/>
						</td>
						<td>
							<input data-required type="text" class="input input-default mr-default" class="input- input-default" data-type="number" name="" value="" />
						</td>
						<td>14343</td>
						<td>
							<a href="#" class="J_del">删除</a>
						</td>
					</tr>
					<tr>
						<td colspan="7">
							<span class="button button-operate" id="J_addNew">添加商品</span>
						</td>
					</tr>
				</tbody>
			</table>
			<!--选择商品-->
			<div class="dialog hidden" id="J_dialogGoodsNew" style="width: 700px;">
				<div class="dialog-head" style="cursor: move;">
					选择商品
					<div class="dialog-close">
					</div>
				</div>
				<div class="dialog-body dialog-padding">
					<input type="text" name="" value="" placeholder="商品名称" class="input mr-default" style="width: 200px;" />
					<input type="button" class="input-search-button" name="" value="搜索" />
					<div class="mt-default" style="height: 250px;overflow: auto;">
						<table class="table-list table-border" id="">
							<thead>
								<tr>
									<th>商品条码</th>
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
								</tr>
								<tr>
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
					<input type="button" class="dialog-button dialog-ok" value="确认">
					<input type="button" class="dialog-button dialog-cancel ml-default" value="取消">
				</div>
			</div>
			<div class="mt-default">
				<input type="submit" class="button-save" value="确认" />
				<input type="button" class="button-cancel" value="取消" />
			</div>
		</form>
	</div>
</body>
<script>
	var oDialog = document.getElementsByClassName("dialog");
	var oDialogHead = document.getElementsByClassName("dialog-head");
	startDrag(oDialogHead[0], oDialog[0]);
	$(function() {
		Validator.language = 'en';
		dialogPosCenter('#J_dialogGoodsNew');
		$('#J_addNew').on('click', function() {
			$('#J_dialogGoodsNew,.cover-all').fadeIn();
		});
		$('.dialog').on('click', '.dialog-cancel', function() {
			$('.dialog,.cover-all').fadeOut();
		})
		$('.dialog').on('click', '.dialog-close', function() {
			$('.dialog,.cover-all').fadeOut();
		})
		$('tbody').on('click', '.J_del', function() {
			if(confirm('确认删除？')) {
				$(this).parent('td').parent('tr').remove();
			}
		})
	})
</script>

</html>
