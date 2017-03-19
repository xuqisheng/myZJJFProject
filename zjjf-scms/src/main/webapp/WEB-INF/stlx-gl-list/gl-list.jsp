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
</head>

<body>
	<div class="wrap-bd">
		<div class="">
			<div class="mb-default title">损益单列表</div>
		</div>
		<div class="bg wrap-bd">
			<label for="">损益单：</label>
			<select name="" class="select mr-default">
				<option value="">报损单</option>
				<option value="">报溢单</option>
			</select>
			<label for="" class="ml-default">审核状态：</label>
			<select name="" class="select mr-default">
				<option value="">全部</option>
				<option value="">未审核</option>
				<option value="">已审核</option>
			</select>
			<label for="" class="ml-default">选择日期：</label>
			<input type="text" class="input input-search-date J_ds" name="" id="" value="" />&nbsp;至&nbsp;
			<input type="text" class="input input-search-date mr-default J_de" name="" id="" value="" />
			<input type="button" class="input-search-button" name="" id="" value="搜索" />
		</div>
		<div class="mb-default mt-default">
			<input type="button" class="button button-operate mr-small" name="" id="" value="新建" />
			<input type="button" class="button button-operate mr-small" name="" id="" value="修改" />
			<input type="button" class="button button-operate mr-small" name="" id="" value="删除" />
			<input type="button" class="button button-operate mr-small" name="" id="" value="审核" />
			<input type="button" class="button button-operate mr-small" name="" id="" value="查看" />
		</div>
		<table class="table-list table-border" id="stock_info">
			<thead>
				<tr>
					<th><input type="checkbox" class="checkbox" name="" id="J_selectAll" value="" /></th>
					<th>单据编号</th>
					<th>单据类型</th>
					<th>损益数量</th>
					<th>损益金额</th>
					<th>审核状态</th>
					<th>创建人</th>
					<th>创建时间</th>
					<th>备注</th>
				</tr>
			</thead>
			<tbody class="table-tbody">
				<tr>
					<td><input type="checkbox" class="checkbox J_chk" name="" id="" value="" /></td>
					<td>124324324234</td>
					<td>dds</td>
					<td>1fdsfs</td>
					<td>11243245</td>
					<td>13434</td>
					<td>14343</td>
					<td>23524</td>
					<td>35454543543</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
<script>
	$(function() {
		dateRange('.J_ds', '.J_de', 1);
		selectAll('#J_selectAll', '.J_chk');
		$('.table-list td').click(function() {
			$(this).parent().css({
				'background': '#009dd9',
				'color': 'white'
			}).siblings().css({
				'background': '#fff',
				'color': 'black'
			});
		});
	})
</script>

</html>