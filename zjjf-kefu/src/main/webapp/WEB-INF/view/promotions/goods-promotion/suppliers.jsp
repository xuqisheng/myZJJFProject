<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">

	<head>
		<meta charset="UTF-8">
		<title>参与批发商</title>
		<%@ include file="../../common/head.jsp"%>
		<link rel="stylesheet" type="text/css" href="${root}/resources/vendor/jquery/ztree/css/zTreeStyle/zTreeStyle.css" />
		<script src="${root}/resources/js/comm.js"></script>
		<script src="${root}/resources/vendor/jquery/ztree/js/jquery.ztree.all.js"></script>
		<script src="${root}/resources/vendor/jquery/ztree/js/jquery.ztree.excheck.js"></script>
		<script src="${root}/resources/js/promotion/goods-promotion.js"></script>
		<script src="${root}/resources/js/ztree-search.js"></script>
	</head>

	<body>
		<div class="wrap-bd">
			<div class="mb-small">
				<a href="#" class="crumb">促销管理</a>
                <a href="${root }/keFu/SKUActive/getAllSKUActive.do" class="crumb">单品促销</a>
			</div>
			<div class="bg wrap-bd">
				<div class="mb-default ">
					<label class="label">
						参与批发商：
					</label>
					<button class="button button-operate mr-default" id="J_dialog-addSupplier" type="button">增加批发商</button>
				</div>
				<form action="${root }/keFu/SKUActive/spGroupNext.do" method="post" id="fm">
					<table class="table-list mb-default">
						<thead>
							<tr>
								<th>序号</th>
								<th>所属区域</th>
								<th>所属定格</th>
								<th>批发商编号</th>
								<th>批发商名称</th>
								<th>手机号</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody class="table-tbody" id="supplier_info">
						</tbody>
					</table>
					<div class="mb-default">
						<a href="${root }/keFu/SKUActive/returnAddOrEditPage.do?action=2" class="button button-ok">上一步</a>
						<input type="button" class="button button-ok ml-small" id="okNext" value="下一步" />
						<a href="${root }/keFu/SKUActive/getAllSKUActive.do" class="button button-cancel ml-small">取消</a>
					</div>
				</form>
				<div class="dialog hidden J_dialog" id="J_dialogSupplier" style="width: 600px;">
					<form action="" method="post" onsubmit="return false">
						<div class="dialog-head">

							选择批发商

							<div id="" class="dialog-close">

							</div>
						</div>
						<div class="dialog-body">
							<form action="" method="post"  onsubmit="return false">
								<div class="mb-default">
									<input type="text" name="" id="search-condition" onkeydown="entersearch()" value="" class="input input-default" placeholder="定格名称" style="width:400px ;" />
									<input type="button" name="" id="" onclick="search_ztree('treeDemo', 'search-condition')" value="搜索" class="button button-default" />
								</div>
							</form>
							<div class="ztree" id="ztree">
								<ul id="treeDemo" class="ztree" style="height:200px; overflow:auto">
								</ul>
							</div>
						</div>
						<div class="dialog-foot">
							<div class="">
								<span class="dialog-button dialog-ok ml-default" id="saveBtn">确认</span>
								<span class="dialog-button ml-small  dialog-cancel">取消</span>
							</div>
						</div>
					</form>
				</div>
				<div class="cover-all">

				</div>
			</div>
		</div>
	</body>
	<script>
		function goback(){
			history.go(-1);
		}
		function entersearch() {
			var event = window.event || arguments.callee.caller.arguments[0];
			if(event.keyCode == 13) {
				search_ztree('treeDemo', 'search-condition');
			}
		}
		$(function() {
			var zTreeObj = null;
			var setting = {
				view: {
					showLine: false
				},
				data: {
					key: {
						children: "regionList",
					},
					simpleData: {
						enable: true
					}
				}
			};
			var regionList = '${regionList}';
			var zNodes = JSON.parse(regionList);
			$(document).ready(function() {
				zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
			})
			dialogPosCenter('#J_dialogSupplier');
			$('#J_dialog-addSupplier').on('click', function() {
				$('#J_dialogSupplier, .cover-all').fadeIn();
			});
			$('.J_dialog').on('click', '.dialog-cancel', function() {

				$('.J_dialog, .cover-all').fadeOut();
			})
			$('.dialog').on('click', '.dialog-close', function() {
				$('.J_dialog, .cover-all').fadeOut();
			})

			var suppliers = '${suppliers}';
			if(suppliers != null && suppliers != '') {
				var supJson = JSON.parse(suppliers);
				var html = '';
				$.each(supJson, function(i, item) {
					html +=
						'<tr>' +
						'<td><span class="index">' + (i + 1) + '</span></td>' +
						'<td>' + item.provinceName + '/' + item.cityName + '/' + item.areaName
						       + '<input type="hidden" name="provinceNames" value="' + item.provinceName + '"/>'
						       + '<input type="hidden" name="cityNames" value="' + item.cityName + '"/>'
						       + '<input type="hidden" name="areaNames" value="' + item.areaName + '"/>'
					           + '</td>' +
						'<td>' + item.groupName + '<input type="hidden" name="groupNames" value="' + item.groupName + '"/>'
						       + '<input type="hidden" name="spGroupIds" value="' + item.spGroupId + '"/>'
						       + '</td>' +
						'<td>' + item.supplierCode + '<input type="hidden" name="supplierCodes" value="' + item.supplierCode + '"/>'
						       + '<input type="hidden" name="supplierIds" value="' + item.id + '"/>'
						       + '</td>' +
						'<td>' + item.supplierName
						       + '<input type="hidden" name="supplierNames" value="' + item.supplierName + '"/>'
						       + '</td>' +
						'<td>' + item.mobile + '<input type="hidden" name="mobiles" value="' + item.mobile + '"/></td>' +
						'<td><span class="icon-op icon-op-del J_del" title="删除"></span></td>' +
						'</tr>';
				});
				$('#supplier_info').html(html);
				//$('#supplier_info').innerHTML=html;
			}

			$('#saveBtn').click(function() {
				querySuppliers(zTreeObj);
			})
			$('.table-list').on('click', '.J_del', function() {

				if(confirm('确认删除？')) {
					var supId=$(this).parent().parent().find('input[name="supplierIds"]').val();
					console.log(supId)
					delSupplier(supId);
					$(this).parent('td').parent('tr').remove();
				}
			})

			$("#okNext").on("click", function() {
				var spGroupIds = $("input[name='spGroupIds']");
				var supplierIds = $("input[name='supplierIds']");
				if((spGroupIds == null || spGroupIds.length == 0) || (supplierIds == null || supplierIds.length == 0)) {
					alert("请选择批发商");
					return;
				}
				$("#fm").submit();
			})
		})
	</script>

</html>
