<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setAttribute("root", request.getContextPath());%>
<%@ include file="../../common/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><c:choose><c:when test="${warehouse!= null }">编辑仓库</c:when><c:otherwise>添加仓库</c:otherwise></c:choose></title>
		<link rel="stylesheet" type="text/css" href="../../../resources/css/base.css" />
		<link rel="stylesheet" type="text/css" href="../../../resources/vendor/jquery/ztree/css/zTreeStyle/zTreeStyle.css" />
		<script src="../../../resources/vendor/jquery/jquery-1.12.4.min.js"></script>
		<script src="../../../resources/js/comm.js"></script>
		<script src="../../../resources/vendor/jquery/ztree/js/jquery.ztree.all.js"></script>
	</head>

	<body>
		<div class="wrap-bd">
			<div class="mb-small clearfix">
				<div class="fl title"><c:choose><c:when test="${warehouse!= null }">编辑仓库</c:when><c:otherwise>添加仓库</c:otherwise></c:choose></div>
			</div>
			<div class="op-section clearfix">
				<div class="mb-default">
					<label for="" class="label">仓库名称:</label>
					<input type="text" value="${warehouse.name }" name="name" id="name" class="input-default" style="width: 300px;" />
				</div>
				<div class="mb-default">
					<label for="" class="label">仓库编号:</label>
					<input type="text" value="${warehouse.code }" name="code" id="code" class="input-default" />
				</div>
				<div class="mb-default">
					<label for="" class="label">仓库简称:</label>
					<input type="text" value="${warehouse.abbreviation }" name="abbreviation" id="abbreviation" class="input-default" />
				</div>
				<div class="mb-default">
					<label for="" class="label">联系人:</label>
					<input type="text" value="${warehouse.contacts }" name="contacts" id="contacts" class="input-default" />
				</div>
				<div class="mb-default">
					<label for="" class="label">联系电话:</label>
					<input type="text" value="${warehouse.mobile }" name="mobile" id="mobile" class="input-default" />
				</div>
				<div class="mb-default">
					<label for="" class="label">仓库地址:</label>
					<input type="text" value="${warehouse.address }" name="address" id="address" class="input-default" style="width: 400px;" />
				</div>
				<!-- <div class="">
					<label for="" class="label">配送区域:</label>
					<input type="button" name="" id="J_addArea" value="选择定格区域" class="button-operate" />
				</div>
				<div class="mb-default clearfix">
					<label for="" class="label fl">&nbsp;</label>
					<table class="table-list table-border mt-default fl " style="width: 80%;">
						<thead>
							<tr>
								<th>序号</th>
								<th>定格名称</th>
								<th>定格区域</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody class="table-tbody" id="freezeArea">
							<tr>
								<td>1</td>
								<td>库区1</td>
								<td>库位1</td>
								<td>
									<a href="#">删除</a>
								</td>
							</tr>
						</tbody>
					</table>
				</div> -->
				<div id="">
					<button class="button-save">保存</button>
					<button class="button-cancel">取消</button>
				</div>
			</div>

		</div>
		<!-- <div class="dialog hidden J_dialog" id="J_dialogArea">
			<form action="" method="post">
				<div class="dialog-head" style="cursor: move;">选择定格区域
					<div id="" class="dialog-close">
					</div>
				</div>
				<div class="dialog-body clearfix">
					<div class="">
						<input type="text" name="" id="" value="" class="input input-default" placeholder="定格名称" style="width: 360px;" />
						<input type="button" name="" id="" value="搜索" class="input-search-button va-t" />
					</div>
					<div class="limit-height" style="height:260px;width: 400px;">
						<ul id="treeDemo" class="ztree">
						</ul>
					</div>
				</div>
				<div class="dialog-foot">
					<div class="">
						<span class="dialog-button dialog-ok ml-default" id="isOk">确认</span>
						<span class="dialog-button ml-small  dialog-cancel">取消</span>
					</div>
				</div>
			</form>
		</div> -->
	</body>
	<script>
		/* $(function() {
			//树
			var setting = {
			        view: {
			            showLine: false
			        },
			        data: {
			        	key: {
			        		children: "sub",
			        	},
			            simpleData: {
			                enable: true
			            }
			        }
				};
			var regionList = '${sub}';
			var zNodes = JSON.parse(regionList);
			var zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
			
			dialogPosCenter('#J_dialogArea');
			$('#J_addArea').on('click', function() {
				$('#J_dialogArea, .cover-all').fadeIn();
			});
			$('.J_dialog').on('click', '.dialog-cancel,.dialog-close', function() {
					$('.J_dialog').fadeOut();
			});
			
			$('#isOk').on('click',function(){
				var html = '';
				var spGroupId = $('.spGroupId');
				var indexs = $('.index');
				var index = 0;
				if(indexs.length >0){
					index = indexs.length;
				}
				$.each(zTreeObj.getSelectedNodes(),function(i,item){
					var flag = true;
					if(spGroupId.length >0){
						spGroupId.each(function(){
							if(item.id == $(this).val()){
								flag = false;
								return false;
							}
						})
					}
					if(flag){
						index +=1;
						html += '<tr class="g_tr">' +
						'<td class="index">'+index+'</td>' +
						'<td>'+item.name+'<input type="hidden" value="'+item.id+'" name="spGroupId" class="spGroupId"/></td>' +
						'<td>'+item.getParentNode().name+'</td>' +
						'<td><span class="icon-op icon-op-del J_del" title="删除 "></span></td>' +
					 '</tr>';
					}
				});
				$('#freezeArea').append(html);
				$('.J_dialog, .cover-all').fadeOut();
			});
			
			$('#freezeArea').on('click', '.J_del', function() {
				if(confirm('确认删除？')) {
					$(this).parent('td').parent('tr').remove();
				}
			});
			//拖拽
			var oDialog = document.getElementsByClassName("dialog")[0];
			startDrag(oDialog, oDialog);
		
		}); */
	</script>

</html>