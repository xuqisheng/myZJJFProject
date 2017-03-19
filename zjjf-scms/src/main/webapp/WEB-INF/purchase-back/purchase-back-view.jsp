<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>采购退货</title>
		<link rel="stylesheet" type="text/css" href="../../resources/css/base.css" />
		<script src="../../resources/vendor/jquery/jquery-1.12.4.min.js"></script>
		<script src="../../resources/js/comm.js"></script>
	</head>
	<style type="text/css">
		ul {
			list-style: none;
			padding: 0;
			margin: 0;
		}
		
		li {
			margin-bottom: 18px;
		}
	</style>

	<body>
		<div class="wrap-bd">

			<div class="mb-small">
				<div class="mt-default title">采购退货管理</div>
			</div>
			<div class="bg wrap-bd mb-default">
				<div class="clearfix">
					<ul style="width: 33%;" class="fl">
						<li>退货单号：</li>
						<li>退货人：</li>
						<li>退货时间：</li>
						<li>审核时间：</li>
					</ul>
					<ul style="width: 33%;" class="fl">
						<li>退货仓库：</li>
						<li>退货金额：</li>
						<li>是否审核：</li>
						<li>备注：</li>
					</ul>
					<ul style="width: 33%;" class="fl">
						<li>供应商名称：</li>
						<li>退货数量：</li>
						<li>审核人：</li>
					</ul>
				</div>
			</div>

			<table class="table-list mb-default">
				<thead>
					<tr>
						<th>序号</th>
						<th>商品供应码</th>
						<th>箱码</th>
						<th>商品条形码</th>
						<th>商品名称</th>
						<th>规格</th>
						<th>入库数量</th>
						<th>退货数量</th>
						<th>备注</th>
					</tr>
				</thead>
				<tbody class="table-tbody">
					<tr>
						<td>1</td>
						<td>64354435435</td>
						<td>维他豆奶</td>
						<td>维他美</td>
						<td>饮料</td>
						<td>330ml</td>
						<td>瓶</td>
						<td>550/3</td>
						<td>正常</td>
					</tr>
				</tbody>
			</table>
			<div class="mb-default">
				<input type="button" class="button-save" name="" id="" value="确认" />
				<input type="button" class="button-cancel" name="" id="" value="取消" />
			</div>
		</div>
	</body>
	<script>
	</script>

</html>