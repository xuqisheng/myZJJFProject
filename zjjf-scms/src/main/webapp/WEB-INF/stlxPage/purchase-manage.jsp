<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>供应商管理</title>
		<link rel="stylesheet" type="text/css" href="../../resources/css/base.css" />
		<link rel="stylesheet" type="text/css" href="../../resources/vendor/jquery/ztree/css/zTreeStyle/zTreeStyle.css" />			
		<script src="../../resources/vendor/jquery/jquery-1.12.4.min.js"></script>
		<script src="../../resources/js/comm.js"></script>
		<script src="../../resources/vendor/jquery/ztree/js/jquery.ztree.all.js"></script>
		
	</head>

	<body>
		<div class="wrap-bd">
			<div class="mb-small">
				<div class="title">供应商管理</div>
			</div>
			<div class="bg wrap-bd">
				<div class="">
					<label id="" class="">
							关键词：
						</label>
					<input type="text" name="" id="" value="" class="input input-default mr-default" placeholder="供应商编号/名称" />
					<label for="" class="">状态：</label>
					<select name="" class="mr-default select">
						<option value="">全部</option>
						<option value="">引进中</option>
						<option value="">合作中</option>
						<option value="">停止合作</option>
					</select>
					<input type="button" name="" id="" value="搜索" class="input-search-button" />
				</div>
			</div>
				<div class="clearfix mt-default">
					<div id="" class="fl bg" style="width: 12%;overflow-x: auto;min-height: 600px;">
						<div class="ztree" id="ztree">
							<ul id="treeDemo" class="ztree">
							</ul>
						</div>
					</div>
					<table class="table-list mb-default fr" style="width: 86%;">
						<thead>
							<tr>
								<th>序号</th>
								<th>编号</th>
								<th>名称</th>
								<th>状态</th>
								<th>最后更新时间</th>
								<th>操作</th>

							</tr>
						</thead>
						<tbody class="table-tbody">
							<tr>
								<td>1</td>
								<td>164653213</td>
								<td>农夫山泉呃呃呃</td>
								<td>500ml</td>
								<td>50.32</td>						
								<td>
									<span class="button button-operate">查看</span>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
		</div>
	</body>
	<script type="text/javascript">
		//定格区域树
		
		var setting = {
			view: {
				showIcon: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},

		};

		var zNodes = [{
				name: "广东",
				open: true,
				children: [{
					name: "深圳",
					children: [{
						name: "南山"
					}, {
						name: "福田"
					}, {
						name: "宝安"
					}, {
						name: "罗湖"
					}]
				}, {
					name: "广州",
					children: [{
						name: "广州1"
					}, {
						name: "广州2"
					}, {
						name: "广州3"
					}, {
						name: "广州4"
					}]
				}, {
					name: "汕头",
					isParent: true
				}]
			}, {
				name: "北京",
				isParent: true
			}

		];

		$(document).ready(function() {
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			$.fn.zTree.init($("#treeDemo-1"), setting, zNodes);
		});
	</script>

</html>