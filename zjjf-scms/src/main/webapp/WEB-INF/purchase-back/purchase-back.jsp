<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>采购退货</title>
		<link rel="stylesheet" type="text/css" href="../../resources/css/base.css" />
		<link rel="stylesheet" href="../../resources/css/order-detail.css">
		<script src="../../resources/vendor/jquery/jquery-1.12.4.min.js"></script>
		<script src="../../resources/js/comm.js"></script>
		<script src="../../resources/vendor/My97DatePicker/WdatePicker.js"></script>
	</head>

	<body>
		<div class="wrap-bd noprint">
			<div class="mb-small clearfix">
				<div class="title">采购退货管理</div>
			</div>
			<div class="bg wrap-bd mb-default">
				<div class="">
					<label for="">关键词：</label>
					<input type="text" class="input input-default mr-default" name="" id="" value="" placeholder="仓库名称" />
					<label for="">审核状态：</label>
					<select name="" class="select mr-default">
						<option value="">全部</option>
					</select>
					<label for="">退货时间：</label>
					<input type="text" class="input input-date J_timeS" name="" id="" value="" />&nbsp;至&nbsp;
					<input type="text" class="input input-date mr-default J_timeE" name="" id="" value="" />
					<input type="button" class="input-search-button" name="" id="" value="搜索" />
				</div>
			</div>
			<div class="mb-default">
				<input type="button" class="button button-operate mr-small" name="" id="" value="新建" />
				<input type="button" class="button button-operate mr-small" name="" id="" value="删除" />
				<input type="button" class="button button-operate mr-small" name="" id="" value="查看" />
				<input type="button" class="button button-operate mr-small" name="" id="printBillinfo" value="打印" />
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
						<th>审核人</th>
						<th>审核时间</th>
					</tr>
				</thead>
				<tbody class="table-tbody">
					<tr>
						<th><input type="checkbox" class="checkbox J_chk" name="" id="" value="" /></th>
						<td>1</td>
						<td>64354435435</td>
						<td>维他豆奶</td>
						<td>维他美</td>
						<td>饮料</td>
						<td>330ml</td>
						<td>瓶</td>
						<td>550/3</td>
						<td>正常</td>
						<td>1</td>
						<td>2</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!--打印的内容-->
		<div class="order-print" id="realPrintDiv">
			<div class="print-order-title ta-c" style="font-size: 20px">JFUN转角 退货单</div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" style="line-height:20px;font-size: 12px;" class="mb-default">
				<tr class="mb-small">
					<td>退货单编号：</td>
					<td>采购单编号：</td>
					<td>仓库：</td>
				</tr>
				<tr class="mb-small">
					<td>供应商编号：</td>
					<td>供应商名称：</td>
					<td>制单时间：</td>
				</tr>
				<tr class="mb-small">
					<td>退货时间：</td>
					<td>退货原因：</td>
				</tr>
			</table>
			<table class="table-print mt-small" border="1">
				<tr>
					<th>序号</th>
					<th>箱码</th>
					<th>商品条形码</th>
					<th>商品名称</th>
					<th>规格</th>
					<th>单位</th>
					<th>单价</th>
					<th>数量</th>
					<th>小计</th>
				</tr>
				<tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>
					<td>9</td>
				</tr>
				<tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>
					<td>9</td>
				</tr>
			</table>
			<div class="mt-default">
				<div class="mb-default">				
					<span class="ta-l" style="display:inline-block;width: 30%;">仓库联系方式：0755-23456789</span>
					<span class="ta-l" style="display:inline-block;width: 30%;">客服热线：400-644-3833</span>
					<span class="ta-l" style="display:inline-block;width: 15%;">退货数量：</span>
					<span class="ta-l" style="display:inline-block;width: 15%;">总计：</span>			
				</div>
				<div class="clearfix">
					<span class="ta-l" style="display:inline-block;width: 30%;">财务签名：</span>	
					<span class="ta-l" style="display:inline-block;width: 36%;">供应商签名：</span>
					<span class="ta-l" style="display:inline-block;width: 33%;">仓库签名(盖章)：</span>
				</div>
				
			</div>
		</div>
	</body>
	<script>
		dateRange('.J_timeS', '.J_timeE', 1);
		selectAll('#J_selectAll', '.J_chk');
		$(function(){
			$("#printBillinfo").on('click', function() {
				$("#realPrintDiv").show();
				window.print();
				$("#realPrintDiv").hide();
				if(confirm("是否已打印成功?") == true) {
					return true;
				}
				return false;
			});
			$('.table-list td').click(function() {
				$(this).parent().css({'background':'#009dd9','color':'white'}).siblings().css({'background':'#fff','color':'black'});	
				$_tr = $(this).parent();
			});
		})
	</script>
</html>