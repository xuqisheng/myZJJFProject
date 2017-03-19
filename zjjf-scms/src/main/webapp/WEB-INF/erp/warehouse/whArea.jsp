<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setAttribute("root", request.getContextPath());%>
<%@ include file="../../common/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>库区管理</title>
		<link rel="stylesheet" type="text/css" href="../../resources/css/base.css" />
		<script src="../../resources/vendor/jquery/jquery-1.12.4.min.js"></script>
		<script src="../../resources/js/comm.js"></script>
	</head>
	<style type="text/css">
		#stock_info th{
			padding-left: 4px !important;padding-right: 8px !important;
		}
	</style>

	<body>
		<div class="wrap-bd">
			<div class="mb-small clearfix">
				<div class="fl title">库区管理</div>
			</div>
			<div class="op-section clearfix">
				<form action="${root }/erp/warehouse/getWhareaBySupplierId.do" method="post">
					<label id="" class="label">关键词：</label>
					<input type="text" name="numAndName" id="numAndName" value="${warehouseRo.numAndName }" class="mr-default input input-default" placeholder="库区编号/名称" />
					<input type="submit" class="input-search-button ml-default" value="搜索" />
				</form>
			</div>
			<div class="mb-small clearfix mt-default">
				<span class="button button-operate" id="J_New">新建</span>
				<span class="button button-operate" id="J_edit">修改</span>
				<span class="button button-operate" id="J_del">删除</span>
			</div>
			<table class="table-list table-border" id="stock_info">
				<thead>
					<tr>
						<th>序号</th>
						<th>库区编号</th>
						<th>库区名称</th>
						<th>所属仓库</th>
						<th>库区面积(平米)</th>
						<th>创建人</th>
						<th>创建时间</th>
						<th>操作</th>
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
									<td>${warehouse.acreage }</td>
									<td>${warehouse.createName }</td>
									<td><fmt:formatDate value="${warehouse.addTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td>
										<span class="button button-operate whAreaEdit">编辑</span>
										<span class="button button-operate whAreaDlt">删除</span>
									</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr><td colspan="8" class="no-data"></td></tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<c:if test="${fn:length(warehouseList)>0}">
			  <%@ include file="../../common/pagination-kk.jsp"%>
		    </c:if>
			<div class="dialog hidden" id="J_dialogAddNew">
				<input type="hidden" name="id" id="id">
				<form action="" method="post" id="fm">
					<div class="dialog-head" style="cursor: move;">
						<div class="dialog-title" id="tit">管理库区</div>
						<div class="dialog-close"></div>
					</div>
					<div class="dialog-body dialog-padding clearfix">
						<div class="clearfix">
							<p class="fl mr-default">
								<label class="label">库区编号：</label>
								<input type="text" name="code" id="code" class="input" maxlength="50" placeholder="库区编号如:KQ01">
							</p>
							<p class="fl">
								<label class="label">库区名称：</label>
								<input type="text" name="name" id="name" class="input" maxlength="50" placeholder="请输入库区名称">
							</p>
						</div>
						<div class="clearfix">
							<p class="fl mr-default">
								<label class="label">库区面积：</label>
								<input type="text" name="acreage" id="acreage" class="input" maxlength="5" placeholder="请输入库区面积">
							</p>
							<p class="fr">
								<label class="label">最大存放量：</label>
								<input type="text" name="maxMum" id="maxMum" class="input" maxlength="5" placeholder="请输入库区存放量">
							</p>
						</div>
						<div id="sltUpId">
							<p class="fl">
								<label class="label">所属仓库：</label>
								<select id="upId" name="upId" class="select">
									<option value="">请选择</option>
								</select>
							</p>
						</div>
					</div>
					<div class="dialog-foot">
						<input type="button" id="okSave" class="dialog-button dialog-ok" value="保存">
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
		//级联
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
		//删除
		function whAreaDlt(id){
			$.ajax({
				url:'${root}/erp/warehouse/delWarehouse/2/'+id+'.do',
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
			});
		}

		//编辑时回写数据
		function showData(id){
			$.ajax({
				url:'${root}/erp/warehouse/getWarehouseById/'+id+'.do',
				type:'post',
				dataType:'json',
				async: false,
				success:function(data){
					if(data.success){
						$('#id').val(data.message.id);
						$('#name').val(data.message.name);
						$('#code').val(data.message.code);
						$('#acreage').val(data.message.acreage);
						$('#maxMum').val(data.message.maxMum);
						$('#upId').html(getNext(0));
						$('#upId').val(data.message.upId);
						$('#upId').attr("disabled","disabled");
					}
				},
				error:function(error){
					alert('请求失败');
				}
			});
		}
		
		$(function() {
			dialogPosCenter('#J_dialogAddNew');
			
			$('.whAreaDlt').on('click',function(){
				var id = $(this).parent().parent().find('.l_id').val();
				whAreaDlt(id);
			});
			
			$('#J_New').on('click', function() {
				$('#tit').text('添加库区');
				$('#upId').html(getNext(0));
				$('#J_dialogAddNew, .cover-all').fadeIn();
			});
			
			$('.whAreaEdit').on('click',function(){
				$('#tit').text('编辑库区');
				var id = $(this).parent('td').parent('tr').find('.l_id').val();
				$('#upId').html(getNext(0));
				showData(id);
				$('#J_dialogAddNew, .cover-all').fadeIn();
			});
			$('.dialog').on('click', '.dialog-cancel, .dialog-close', function() {
				$('.dialog, .cover-all').fadeOut();
				window.location.href="${root}/erp/warehouse/getWhareaBySupplierId.do";
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
					whAreaDlt($_tr.find('.l_id').val());
				}
			});
			
			//保存
			$('#okSave').on('click',function(){
				//校验
				var name = $.trim($('#name').val());
				if(name == ""){
					alert("库区名称不能为空");
					return;
				}
				var code = $.trim($('#code').val());
				if(code == ""){
					alert("库区编号不能为空");
					return;
				}
				var upId = $.trim($('#upId').val());
				
				var id = $('#id').val();
				var url = '${root}/erp/warehouse';
				var param = '';
				if(id == null || id == '' || id == 'null' || id == 'undefined'){
					if(upId == ""){
						alert("请选择所属仓库");
						return;
					}
					url+='/addWarehouse/'+2+'.do';
				}else{
					url+='/editWarehouse/'+2+'/'+id+'.do';
				}
				$.ajax({
					url:url,
					type:'post',
					data:$('#fm').serializeArray(),
					dataType:'json',
					success:function(data){
						alert(data.message);
						if(data.success){
							window.location.href="${root}/erp/warehouse/getWhareaBySupplierId.do";
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