<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setAttribute("root", request.getContextPath());%>
<%@ include file="../../common/taglib.jsp"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>库位管理</title>
		<link rel="stylesheet" type="text/css" href="../../resources/css/base.css" />
		<link rel="stylesheet" type="text/css" href="../../resources/vendor/jquery/ztree/css/zTreeStyle/zTreeStyle.css" />
		<script src="../../resources/vendor/jquery/jquery-1.12.4.min.js"></script>
		<script src="../../resources/js/comm.js"></script>
		<script src="../../resources/vendor/jquery/ztree/js/jquery.ztree.all.js"></script>
	</head>
	<style type="text/css">
		#stock_info{
			padding-left: 0;padding-right: 0;
		}
	</style>

	<body>
		<div class="wrap-bd">
			<div class="mb-small clearfix">
				<div class="fl title">库位管理</div>
			</div>
			<div class="op-section clearfix">
				<form action="${root }/erp/warehouse/getWhpositionBySupplierId.do" method="post" id="s_fm">
					<label id="" class="label">关键词：</label>
					<input type="hidden" name="upId" id="s_upId">
					<input type="text" name="numAndName" id="numAndName" value="${warehouseRo.numAndName }" class="mr-default input input-default" placeholder="库位编号/名称" />
					<input type="submit" class="input-search-button ml-default" value="搜索" />
				</form>
			</div>
			<div class="mb-small clearfix mt-default">
				<span class="button button-operate" id="J_New">新建</span>
				<span class="button button-operate" id="J_edit">修改</span>
				<span class="button button-operate" id="J_del">删除</span>
			</div>
			<div class="clearfix op-section">
				<div class="fl" style="height:400px;width: 10%;overflow-x:auto;overflow-y:auto">
					<ul id="treeDemo" class="ztree">
					</ul>
				</div>			
			<table class="table-list table-border fr" id="stock_info" style="width:88%">
				<thead>
					<tr>
						<th style="width: 4%;">序号</th>
						<th style="width: 8.6%;">库位编号</th>
						<th style="width: 8.6%;">库位名称</th>
						<th style="width: 8.6%;">所属仓库</th>
						<th style="width: 8.6%;">所属库区</th>
						<th style="width: 14.5%;">库位面积(平米)</th>
						<th style="width: 9%;">最大存放量</th>
						<th style="width: 10%">创建人</th>
						<th style="width: 14%">创建时间</th>
						<th style="width: 14%;">操作</th>
					</tr>
				</thead>
				<tbody class="table-tbody">
					<c:choose>
						<c:when test="${warehouseList != null && warehouseList.size()>0 }">
							<c:forEach var="warehouse" items="${warehouseList }" varStatus="sta">
								<tr>
									<td>${sta.index+1 }<input type="hidden" value="${warehouse.id }" class="l_id"></td>
									<td>${warehouse.code }</td>
									<td>${warehouse.name }</td>
									<td>${warehouse.whName }</td>
									<td>${warehouse.whAreaName }</td>
									<td>${warehouse.acreage }</td>
									<td>${warehouse.maxMum }</td>
									<td>${warehouse.createName }</td>
									<td><fmt:formatDate value="${warehouse.addTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td>
										<span class="button button-operate whPositionEdit">编辑</span>
										<span class="button button-operate whPositionDlt">删除</span>
									</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr><td colspan="10" class="no-data"></td></tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<c:if test="${fn:length(warehouseList)>0}">
			  <%@ include file="../../common/pagination-kk.jsp"%>
		    </c:if>
			</div>
			<div class="dialog hidden" id="J_dialogAddNew">
				<input type="hidden" name="id" id="id">
				<form action="" method="post" id="fm">
					<div class="dialog-head" style="cursor: move;">
						<div class="dialog-title" id="tit">管理库位</div>
						<div class="dialog-close"></div>
					</div>
					<div class="dialog-body dialog-padding clearfix">
						<div class="clearfix">
							<p class="fl mr-default">
								<label class="label">库位编号：</label>
								<input type="text" name="code" id="code" class="input" maxlength="50" placeholder="库位编号如:KW01">
							</p>
							<p class="fl">
								<label class="label">库位名称：</label>
								<input type="text" name="name" id="name" class="input" maxlength="50" placeholder="请输入库位名称">
							</p>
						</div>
						<div id="sltUpId" class="clearfix">
							<p class="fl" style="margin-right:38px">
								<label class="label">所属仓库：</label>
								<select id="whId" name="whId" class="select">
									<option value="">请选择</option>
								</select>
							</p>
							<p class="fl">
								<label class="label">所属库区：</label>
								<select id="upId" name="upId" class="select">
									<option value="">请选择</option>
								</select>
							</p>
						</div>
						<div class="clearfix">
							<p class="fl mr-default">
								<label class="label">库位面积：</label>
								<input type="text" name="acreage" id="acreage" class="input" maxlength="5" placeholder="请输入库位面积">
							</p>
							<p class="fr">
								<label class="label">最大存放量：</label>
								<input type="text" name="maxMum" id="maxMum" class="input" maxlength="5" placeholder="请输入库位最大存放量">
							</p>
						</div>
					</div>
					<div class="dialog-foot">
						<input type="hidden" name="" value="" id="">
						<input type="button" class="dialog-button dialog-ok" id="okSave" value="保存">
						<input type="button" class="dialog-button dialog-cancel ml-default" value="取消">
						<input type="reset" class="hidden" />
					</div>

				</form>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		drag('#J_dialogAddNew .dialog-head','#J_dialogAddNew');	
		function drag(dragSon,dragFather){
    		$(dragSon).bind("mousedown", function(event) {
					/* 获取需要拖动节点的坐标 */
					var offset_x = $(dragFather)[0].offsetLeft; //x坐标
					console.log(offset_x)
					var offset_y = $(dragFather)[0].offsetTop; //y坐标
					/* 获取当前鼠标的坐标 */
					var mouse_x = event.pageX;
					var mouse_y = event.pageY;
					/* 绑定拖动事件 */
					/* 由于拖动时，可能鼠标会移出元素，所以应该使用全局（document）元素 */
					$(document).bind("mousemove", function(ev) {
						/* 计算鼠标移动了的位置 */
						var _x = ev.pageX - mouse_x;
						var _y = ev.pageY - mouse_y;
						/* 设置移动后的元素坐标 */
						var now_x = (offset_x + _x) + "px";
						var now_y = (offset_y + _y) + "px";
						/* 改变目标元素的位置 */
						$(dragFather).css({
							top: now_y,
							left: now_x
						});
					});
				});
				/* 当鼠标左键松开，接触事件绑定 */
				$(document).bind("mouseup", function() {
					$(this).unbind("mousemove");
				});
    	}
		//树
		var setting = {view: {showLine: false},data: {key: {children: "treeList",},simpleData: {enable: true}},callback: {onClick: zTreeOnClick}};
		var treeList = '${treeList}';
		var zNodes = JSON.parse(treeList);
		var zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
		zTreeObj.expandAll(true);
		function zTreeOnClick(event, treeId, treeNode) {
			if(treeNode.level == 1){
				$('#s_upId').val(treeNode.id);
				$('#s_fm').submit();
			}
		};
		
		/* var upId = '${warehouseRo.upId }';
		if(upId != null && upId != ''){
			var nodes = treeObj.getNodes();
			alert($(this).id)
			nodes.each(function(){
				alert($(this).id)
				if($(this).id == upId){
					$(this).checked;
				}
			});
		} */
		
		function getNext(upId){
			var html = '<option value="">请选择</option>';
			$.ajax({
				url:'${root}/erp/warehouse/getWhpositionBySupplierId/'+upId+'.do',
				type:'post',
				dataType:'json',
				async: false,
				success:function(data){
					if(data.success){
						$.each(data.message,function(i,item){
							html += '<option value="'+item.id+'">'+item.name+'</option>';
						});
					}
				},
				error:function(error){
					alert('请求异常');
				}
			});
			return html;
		}
		
		function getUpId(id){
			var resultData = null;
			$.ajax({
				url:'${root}/erp/warehouse/getWarehouseById/'+id+'.do',
				type:'post',
				dataType:'json',
				async: false,
				success:function(data){
					if(data.success){
						resultData = data.message;
					}
				},
				error:function(error){
					alert('请求异常');
				}
			});
			return resultData;
		}
		
		//删除
		function whPositionDlt(id){
			$.ajax({
				url:'${root}/erp/warehouse/delWarehouse/3/'+id+'.do',
				type:'post',
				dataType:'json',
				success:function(data){
					if(data.success){
						window.location.reload();
					}else{
						alert(data.message);
					}
				},
				error:function(error){
					alert("请求失败");
				}
			})
		};
		
		//编辑时回写数据
		function showData(id){
			$.ajax({
				url:'${root}/erp/warehouse/getWarehouseById/'+id+'.do',
				type:'post',
				dataType:'json',
				success:function(data){
					if(data.success){
						var whId = "";
						var whArId = "";
						$('#id').val(data.message.id);
						$('#name').val(data.message.name);
						$('#code').val(data.message.code);
						$('#acreage').val(data.message.acreage);
						$('#maxMum').val(data.message.maxMum);
						whArId = data.message.upId;
						whId = getUpId(whArId).upId;
						//仓库下拉框
						$("#whId").html(getNext(0));
						$("#whId").val(whId);
						$('#whId').attr("disabled","disabled");
						//库区下拉框
						$("#upId").html(getNext(whId));
						$("#upId").val(whArId);
					}
				},
				error:function(error){
					alert('请求失败');
				}
			});
		}
		$(function() {
			dialogPosCenter('#J_dialogAddNew');
			$('.whPositionDlt').on('click',function(){
				var id = $(this).parent().parent().find('.l_id').val();
				whPositionDlt(id);
			});
			
			$('#whId').on('change',function(){
				$('#upId').html(getNext($(this).val()));
			});
			
			$('#J_New').on('click', function() {
				$('#tit').text('添加库位');
				$('#whId').html(getNext(0));
				$('#J_dialogAddNew, .cover-all').fadeIn();
			});
			$('.whPositionEdit').on('click',function(){
				$('#tit').text('编辑库位');
				var id = $(this).parent('td').parent('tr').find('.l_id').val();
				showData(id);
				$('#J_dialogAddNew, .cover-all').fadeIn();
				
			});
			$('.dialog').on('click', '.dialog-cancel, .dialog-close', function() {
				$('.dialog, .cover-all').fadeOut();
				window.location.href="${root}/erp/warehouse/getWhpositionBySupplierId.do";
			});
			var $_tr = null;
			$('.table-list td').click(function() {
				$(this).parent().css({'background':'#009dd9','color':'white'}).siblings().css({'background':'#fff','color':'black'});
				$_tr = $(this).parent();				
			});
			$('#J_edit').on('click',function(){
				if($_tr != null){
					showData($_tr.find('.l_id').val());
					$('#J_dialogAddNew, .cover-all').fadeIn();
				}
			});
			$('#J_del').on('click',function(){
				if($_tr != null){
					whPositionDlt($_tr.find('.l_id').val());
				}
			});
			
			//保存
			$('#okSave').on('click',function(){
				//校验
				var name = $.trim($('#name').val());
				if(name == ""){
					alert("库位名称不能为空");
					return;
				}
				var code = $.trim($('#code').val());
				if(code == ""){
					alert("库位编号不能为空");
					return;
				}
				var upId = $.trim($('#upId').val());
				if(upId == ""){
					alert("请选择所属库区");
					return;
				}
				var id = $('#id').val();
				var url = '${root}/erp/warehouse';
				var param = '';
				if(id == null || id == '' || id == 'null' || id == 'undefined'){
					url+='/addWarehouse/'+3+'.do';
				}else{
					url+='/editWarehouse/'+3+'/'+id+'.do';
				}
				$.ajax({
					url:url,
					type:'post',
					data:$('#fm').serializeArray(),
					dataType:'json',
					success:function(data){
						alert(data.message);
						if(data.success){
							window.location.href="${root}/erp/warehouse/getWhpositionBySupplierId.do";
						}
					},
					error:function(error){
						alert('请求失败');
					}
				});
			});
		})
	</script>

</html>