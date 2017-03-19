<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>配送方式</title>
		<link rel="stylesheet" type="text/css" href="../../resources/css/base.css" />
		<link rel="stylesheet" type="text/css" href="../../resources/vendor/jquery/ztree/css/zTreeStyle/zTreeStyle.css" />
		<script src="../../resources/vendor/jquery/jquery-1.12.4.min.js"></script>
		<script src="../../resources/vendor/jquery/ztree/js/jquery.ztree.all.js"></script>
		<script src="../../resources/js/comm.js"></script>
	</head>

	<body>
		<div class="wrap-bd">
			<div class="mb-default">
				<a href="#" class="crumb">参数设置</a>
				<a href="#" class="crumb">配送方式</a>
			</div>
			<div class="clearfix mb-small">
				<div class="fl">配送方式</div>
				<a href="#" class="button button-default fr">自提</a>
			</div>
			<form id="addForm">
				<div class="bg wrap-bd clearfix">
					<div class="mb-default">
						<input type="radio" name="scopType" id="" value="0" checked="checked" /><span class="mr-default va-t">全部定格</span>
						<input type="radio" name="scopType" id="" value="1" class="ml-default" /><span class="mr-default va-t">指定定格</span>
						<input type="radio" name="scopType" id="" value="2" class="ml-default" /><span class="va-t">排除定格</span>
					</div>
					<div class="mb-default" id="youhui">
						优惠比例:<input type="text" value="" class="input input-date ml-default" name="discount">
					</div>

					<div class="mb-default hidden" id="J_addDiv">
						<input type="button" class="button button-operate" name="" id="J_addArea" value="增加定格区域" />
					</div>
					<div class="cover-all">
					</div>
					<div class="dialog hidden J_dialog" id="J_area" style="width: 500px;">
						<form action="" method="post">
							<div class="dialog-head">
								选择定格区域
								<div id="" class="dialog-close">
								</div>
							</div>
							<div class="dialog-body">
								<div class="mb-default">
									<input type="text" name="" id="" value="" class="input input-default" placeholder="定格名称" style="width: 350px;" />
									<input type="button" name="" class="input input-search-button" id="" value="搜索" />
								</div>
								<div class="ztree" id="" style="height: 200px;overflow: auto;">
									<!-- <ul id="treeDemo" class="ztree">
								</ul> -->
									<%@ include file="../common/ztree.jsp"%>
									<script src="${root}/resources/js/ztree-search.js"></script>
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
					<table class="table-list mb-default hidden" id="addSpGroupTable">
						<thead>
							<tr>
								<td></td>
								<th>定格名称</th>
								<th>定格分区</th>
								<th>优惠比例/单</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody class="table-tbody" id="addSpGroupTbody">
							<!-- <tr>
							<td><input type="checkbox" class="checkbox J_chk" name="" value="" /></td>
							<td>南山</td>
							<td>南山区</td>
							<td><input type="text" class="input input-date" name="" id="" value="" />%</td>
							<td>
								<input type="button" name="" id="" value="删除" class="button button-operate J_del" />
							</td>
						</tr>
						<tr>
							<td><input type="checkbox" class="checkbox J_chk" name="" value="" /></td>
							<td>南山</td>
							<td>南山区</td>
							<td><input type="text" class="input input-date" name="" id="" value="" />%</td>
							<td>
								<input type="button" name="" id="" value="删除" class="button button-operate J_del" />
							</td>
						</tr>
						<tr>
							<td><input type="checkbox" class="checkbox" name="" id="J_selectAll" value="" />全选</td>
							<td>
								<input type="button" name="" id="allDel" value="批量删除" class="button button-operate" />
							</td>
							<td colspan="3"></td>
						</tr> -->
						</tbody>
					</table>
					<div>
						<input type="button" class="button button-ok mr-small" value="确认" id="save">
						<input type="button" class="button button-cancel" value="取消" id="to-cancel">
					</div>
				</div>
			</form>
		</div>
	</body>
	<script>
		var zTreeObj;
		var zSetting = {
			view: {
				showLine: false,
				showIcon: showIconForTree,
				fontCss: setFontCss_ztree
			},
			check: {
				enable: true
			},
			data: {
				key: {
					children: "regionList",
				},
				simpleData: {
					enable: true
				}
			},
			callback: {
				onCheck: onCheck
			}
		};
		var zNodes = '';

		function showIconForTree(treeId, treeNode) {
			return !treeNode.isParent;
		};

		function onCheck(e, treeId, treeNode) {
			if(treeNode.checked) {
				if($('#ztree').find('input[id="' + treeNode.id + '"]').length == 0) {
					var html = '<input type="hidden" class="J_spGroupId" id = "' + treeNode.id + '" value="' + treeNode.name + ',' + treeNode.id + ',' + treeNode.getParentNode().name + '">'
					$('#ztree').append(html);
				}
			} else {
				$('#ztree').find('input[id="' + treeNode.id + '"]').remove();
			}
		}

		$(function() {
			selectAll('#J_selectAll', '.J_chk');
			dialogPosCenter('#J_area');

			//保存按钮
			$('#save').on('click', function() {
				$.ajax({
					type: "POST",
					url: "${root}/Customer/SpGroup/getAcTiveSpGroupList.do",
					dataType: 'json',
					async: true,
					//data : $('#addForm').serialize(),
					success: function(da) {
						if(da.success) {
							zNodes = da.message;
						} else {
							layer.msg(da.message);
						}
					},
					error: function(da) {
						alert('失败的请求!');
					}
				});
			})
			
			$('#to-cancel').click(function(){
				window.location.href="";
			})
			//radio选择
			$('input[type="radio"][name="scopType"]').on('click', function() {
				var radioValue = $(this).val(); //0 全部定格  1指定定格 2排除定格
				if(radioValue == 0) {
					$('#youhui').removeClass('hidden');
					$('#J_addDiv').addClass('hidden');
					$('#addSpGroupTable').addClass('hidden');
				} else if(radioValue == 1) {
					$('#youhui').addClass('hidden');
					$('#J_addDiv').removeClass('hidden');
					$('#addSpGroupTable').removeClass('hidden');
				} else {
					$('#youhui').removeClass('hidden');
					$('#J_addDiv').removeClass('hidden');
					$('#addSpGroupTable').removeClass('hidden');
				}

			})

			$('.dialog').on('click', '.dialog-close', function() {
				$('.J_dialog, .cover-all').fadeOut();
			})
			$('.dialog').on('click', '.dialog-cancel', function() {
					$('.J_dialog, .cover-all').fadeOut();
				})
				//删除功能
			$('#allDel').click(function() {
				if($('#J_selectAll').is(":checked")) {
					if(confirm('确认删除全部定格？')) {
						$(this).parent().parent().parent().find('tr').remove()
					}
				} else if($('.J_chk').is(":checked")) {
					$('input[type="checkbox"]:checked').each(function() { //找到所有选中的 复选框
						if(confirm('确认删除？')) {
							$(this).parent().parent().remove(); //复选框上一层
						}
					});;
				} else {
					alert('请选择定格');
				}
			})
			$('table').on('click', '.J_del', function() {
				if(confirm('确认删除？')) {
					$(this).parent('td').parent('tr').remove();
				}
			});

			//获取定格列表
			$.ajax({
				type: "POST",
				url: "${root}/Customer/SpGroup/getAcTiveSpGroupList.do",
				dataType: 'json',
				async: true,
				//data : $('#addForm').serialize(),
				success: function(da) {
					if(da.success) {
						zNodes = da.message;
					} else {
						layer.msg(da.message);
					}
				},
				error: function(da) {
					alert('失败的请求!');
				}
			});

			//增加定格区域按钮
			$('#J_addArea').on('click', function() {
				$('#J_area, .cover-all').fadeIn();
				$(zNodes).each(function(i, item) { //省
					$(item.regionList).each(function(i, item) { //市
						$(item.regionList).each(function(i, item) { //区
							$(item.regionList).each(function(i, item) { //定格
								item.nocheck = false;
								item.checked = false;
							});
						});
					});
				});
				var html = '';
				//判断是否要根据addSpGroupTbody下已经选中的定格勾上zTree中的checkbox
				if($('#addSpGroupTbody').find('tr [name="spGroupIdArr"]').length != 0) {
					$('#addSpGroupTbody').find('tr [name="spGroupIdArr"]').each(function(i, item) {
						var id = $(this).val();
						$(zNodes).each(function(i, item) { //省
							$(item.regionList).each(function(i, item) { //市
								$(item.regionList).each(function(i, item) { //区
									var parantName = item.name;
									$(item.regionList).each(function(i, item) { //定格
										if(item.id == id) {
											item.checked = true;
											html += '<input type="hidden" class="J_spGroupId" id = "' + item.id + '" value="' + item.name + ',' + item.id + ',' + parantName + '">';
										}
									});
								});
							});
						});
					});
					//onCheck(e, treeId, treeNode);
				}
				zTreeObj = $.fn.zTree.init($("#ztree"), zSetting, zNodes);
				zTreeObj.expandAll(true);
				$('#ztree').append(html);
				dialogPosCenter('#addSpGroupDiv');
			});

			//定格选中确定按钮 dialog-cancel
			$('.J_dialog').on('click', '.dialog-ok', function() {
				var html = '';
				var spGrouphtml = $('#ztree').find('input[class="J_spGroupId"]');
				$(spGrouphtml).each(function(i, item) {
					var arr = new Array();
					arr = item.value.split(",");
					//if($('#addSpGroupTbody').find('tr input[value="'+arr[1]+'"]').length==0){
					html += '<tr><input type="hidden" name="spGroupIdArr" value="' + arr[1] + '">' +
						'<td><input type="checkbox" class="checkbox J_chk" name="" value=""></td>' +
						'<td>' + arr[0] + '</td>' +
						'<td>' + arr[2] + '</td>' +
						'<td><input type="text" name="discountArr"></td>' +
						'<td><span class="button button-operate J_spGroupDelete">删除</span></td>' +
						'</tr>';
					//}
				});
				$('#addSpGroupTbody').html(html);
				$('.J_dialog, .cover-all').fadeOut();
			});

		});
		/* var setting = {
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
		}];
		$(document).ready(function() {
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			$.fn.zTree.init($("#treeDemo-1"), setting, zNodes);
		}); */
	</script>

</html>