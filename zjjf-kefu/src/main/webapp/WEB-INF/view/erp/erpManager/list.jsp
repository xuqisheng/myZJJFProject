<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setAttribute("root", request.getContextPath());%>
<%@ include file="../../common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>供应商列表</title>
		<%@ include file="../../common/head.jsp"%>
	</head>

	<body>
		<div class="wrap-bd">
			<div class="mb-small">
				<a href="#" class="crumb">供应商管理</a>
				<a href="#" class="crumb">供应商管理</a>
			</div>
			<div class="mb-small clearfix">
				<div class="fl" style="padding-top: 10px;">供应商管理</div>
				<div class="fr">
					<a href="${root}/ERPMa/returnAddOrEditPage.do?action=1" class="button button-default">添加供应商</a>
				</div>
			</div>
			<div class="mt-small mb-default clearfix bg wrap-bd">
				<form class="fl" method="post">
					<label for="">关键词：</label>
					<input class="input input-default mr-default" type="text" name="codeOrName" id="codeOrName" value="" placeholder="供应商编号/名称" maxlength="25" />
					<label for="" class="ml-default">状态：</label>
					<select name="status" id="status" class="select mr-default">
						<option value="-1">全部</option>
						<option value="0">引进中</option>
						<option value="1">合作中</option>
						<option value="2">停止合作</option>
					</select>
					<input value="搜索" type="button" class="input input-search-button ml-default" id="searchmanager">
				</form>

			</div>
			<div class="clearfix">
				<div class="fl bg border" style="width:15%;height: 500px;overflow: auto;">
					<%@ include file="../../common/ztree.jsp"%>
				</div>
				<table class="table-list fr" style="width: 80%;">
					<thead>
						<tr>
							<th>编号</th>
							<th>名称</th>
							<th>状态</th>
							<th>编辑时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="managerBody">
						<%-- <c:choose>
        	<c:when test="${managerList != null && managerList.size()>0 }">
        		<c:forEach items="${managerList }" var="manager">
        			<tr>
			            <td>${manager.managerCode }</td>
			            <td>${manager.managerName }</td>
			            <c:if test="${manager.status==0 }"><td class="txt-log">引进中</td></c:if>
			            <c:if test="${manager.status==1 }"><td class="txt-success">合作中</td></c:if>
			            <c:if test="${manager.status==2 }"><td class="txt-warn">停止合作</td></c:if>
			            <td><fmt:formatDate value="${manager.updateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			            <td>
			                <a href="${root }/scms/ERPMaItem/getAllManagerItem.do?managerId=${manager.id}">管理商品</a>&nbsp;&nbsp;
			                <a href="${root }/scms/ERPMa/returnAddOrEditPage.do?action=2&id=${manager.id}">编辑</a>&nbsp;&nbsp;
			                <a href="javascript:void(0)" onclick="deleteManager('${manager.id}')">删除</a>
			            </td>
			        </tr>
        		</c:forEach>
        	</c:when>
        	<c:otherwise>
        		<tr>
		            <tr><td colspan="5" class="no-data"></td></tr>
		        </tr>
        	</c:otherwise>
        </c:choose> --%>
					</tbody>
				</table>
				<%@ include file="../../common/pagination.jsp"%>
			</div>

		</div>
		<script type="text/javascript">
			/***********************************************区域树形图begin***********************************************/
			var searchObject={};
			var zTreeObj;
			var zSetting = {
				view: {
					showLine: false,
					showIcon: showIconForTree
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
					onClick: onClick
				}
			};
			var zNodes = '';

			function showIconForTree(treeId, treeNode) {
				return !treeNode.isParent;
			};

			function onClick(e, treeId, treeNode) {
				//console.log(treeNode.regionLevel);
				$('#regionLevel').val(treeNode.regionLevel);
				$('#regionId').val(treeNode.id);
				//if(treeNode.regionLevel!=0){
				/* searchObject = {
					'regionLevel': treeNode.regionLevel,
					'regionId': treeNode.id
				} */
				searchObject.regionLevel=treeNode.regionLevel;
				searchObject.regionId=treeNode.id;
				console.log(searchObject);
				$("#jpagination").pagination('setParams', searchObject);
				$("#jpagination").pagination('setPageIndex', 0);
				$("#jpagination").pagination('remote');
				//}
				var html = '<option value="-1" selected="selected">请选择</option>';
				if(treeNode.regionLevel != 1) {
					if(treeNode.regionLevel == 2) { //省
						//生成省html;
						$.each(zNodes, function(i, item) {
							$.each(item.regionList, function(i, item) { //省
								html += '<option value="' + item.id + '">' + item.name + '</option>';
							});
						});
						$('#regionsPId1').html(html);
						$('#regionsPId1').val(treeNode.id);
						html = '<option value="-1" selected="selected">请选择</option>';
						$.each(zNodes, function(i, item) {
							$.each(item.regionList, function(i, item) { //省
								$.each(item.regionList, function(i, item) { //市
									if(item.pId == treeNode.id) {
										html += '<option value="' + item.id + '">' + item.name + '</option>';
									}
								});
							});
						});
						$('#regionsPId2').html(html);
					} else if(treeNode.regionLevel == 3) { //市
						var provincceId = treeNode.getParentNode().id;
						$.each(zNodes, function(i, item) {
							//生成省html
							$.each(item.regionList, function(i, item) { //省
								html += '<option value="' + item.id + '">' + item.name + '</option>';
							});
						});
						$('#regionsPId1').html(html);
						$('#regionsPId1').val(provincceId);

						html = '<option value="-1" selected="selected">请选择</option>';
						//生成市html
						$.each(zNodes, function(i, item) {
							$.each(item.regionList, function(i, item) { //省
								$.each(item.regionList, function(i, item) { //市
									if(item.pId == provincceId) {
										html += '<option value="' + item.id + '">' + item.name + '</option>';
									}
								});
							});
						});
						$('#regionsPId2').html(html);
						$('#regionsPId2').val(treeNode.id);

						//生成区html
						html = '<option value="-1" selected="selected">请选择</option>';
						$.each(zNodes, function(i, item) {
							$.each(item.regionList, function(i, item) { //省
								$.each(item.regionList, function(i, item) { //市
									$.each(item.regionList, function(i, item) { //区
										if(item.pId == treeNode.id) {
											html += '<option value="' + item.id + '">' + item.name + '</option>';
										}
									});
								});
							});
						});
						$('#regionsPId3').html(html);
					} else if(treeNode.regionLevel == 4) { //区
						var provincceId = treeNode.getParentNode().getParentNode().id; //省id
						var cityId = treeNode.getParentNode().id; //市id

						//生成省html
						$.each(zNodes, function(i, item) {
							$.each(item.regionList, function(i, item) { //省
								html += '<option value="' + item.id + '">' + item.name + '</option>';
							});
						});
						$('#regionsPId1').html(html);
						$('#regionsPId1').val(provincceId);

						html = '<option value="-1" selected="selected">请选择</option>';
						//生成市html
						$.each(zNodes, function(i, item) {
							$.each(item.regionList, function(i, item) { //省
								$.each(item.regionList, function(i, item) { //市
									if(item.pId == provincceId) {
										html += '<option value="' + item.id + '">' + item.name + '</option>';
									}
								});
							});
						});
						$('#regionsPId2').html(html);
						$('#regionsPId2').val(cityId);

						//生成区html
						html = '<option value="-1" selected="selected">请选择</option>';
						$.each(zNodes, function(i, item) {
							$.each(item.regionList, function(i, item) { //省
								$.each(item.regionList, function(i, item) { //市
									$.each(item.regionList, function(i, item) { //区
										if(item.pId == cityId) {
											html += '<option value="' + item.id + '">' + item.name + '</option>';
										}
									});
								});
							});
						});
						$('#regionsPId3').html(html);
						$('#regionsPId3').val(treeNode.id);
					}
				}
			}
			/***********************************************区域树形图end***********************************************/

			$(function() {
				
				
				//搜索 
				$('#searchmanager').on('click',function(){
					 
					
					searchObject.codeOrName=$.trim($('#codeOrName').val());
					searchObject.status=$('#status').val();
					console.log(searchObject);
					
					$("#jpagination").pagination('setParams', searchObject);
					$("#jpagination").pagination('setPageIndex', 0);
					$("#jpagination").pagination('remote');
				});
				
				
				//删除
				$('#managerBody').on('click','.J_managerDel',function(){
					var id = $(this).parent('td').parent('tr').attr('data-id');
					if(id==null||id==''){
						layer.msg('缺少id数据!');
						return;
					}
					
					$.ajax({
						type: "POST",
						url: "${root}/ERPMa/deleteERPManager.do",
						data:{"id":id},
						dataType: 'json',
						async: true,
						success: function(da) {
							if(da.success) {
								layer.msg('删除成功,页面即将刷新',{time:1000},function(){
									location.href="${root}/ERPMa/toManageList.do";
								});
							} else {
								layer.msg(da.message);
							}
						},
						error: function(da) {
							layer.msg('失败的请求!');
						}
					});
				});
				
				//查询所有启用的区域
				$.ajax({
					type: "POST",
					url: "${root}/Corner/Region/getAllEnabledRegion.do",
					dataType: 'json',
					async: true,
					success: function(da) {
						if(da.success) {
							zNodes = da.message;
							zTreeObj = $.fn.zTree.init($("#ztree"), zSetting, zNodes);
							zTreeObj.expandAll(true);
						} else {
							layer.msg(da.message);
						}
					},
					error: function(da) {
						layer.msg('失败的请求!');
					}
				});

				/***************************************列表查询begin************************************************/
				//列表查询
				$("#jpagination").pagination({
					pageSize: 10,
					remote: {
						url: '${root}/ERPMa/getAllERPManager.do',
						params:searchObject,
						success: function(data) {
							var html = '';
							if(data.flag) {
								$.each(data.list, function(i, item) {
									html += '<tr data-id="'+item.id+'">' +
										'<td>' + item.managerCode + '</td>' +
										'<td>' + item.managerName + '</td>';
									if(item.status == 0) {
										html += '<td>引进中</td>';
									} else if(item.status == 1) {
										html += '<td>合作中</td>';
									} else if(item.status == 2) {
										html += '<td>停止合作</td>';
									}
									html += '<td>'+item.updateDateStr+'</td>'
									     +'<td>'
									     +'<a href="${root}/ERPMa/toSetDistribute.do?id='+item.id+'">配送区域</a>   '
									     +'<a href="${root}/kefu/ERPMaItem/getAllManagerItem.do?managerId='+item.id+'">管理商品</a>   '
									     +'<a href="${root}/ERPMa/returnAddOrEditPage.do?id='+item.id+'&action=2">编辑</a>  '
									     +'<a href="#" class="J_managerDel">删除</a>  '
									     +'</td>'		 
									     +'</tr>';
								});
								$('#managerBody').html(html);
							}
						},
						totalName: 'totalSize'
					}
				});
				/***************************************列表查询end************************************************/

			});

			function deleteManager(id) {
				if(id != null && id != '' && id != 'null' && id != 'undefined') {
					if(confirm("确定要删除吗？")) {
						$.post("${root}/scms/ERPMa/deleteERPManager.do", {
							id: id
						}, function(data) {
							if(data.success) {
								location.reload();
							} else {
								alert(data.message);
							}
						}, 'json');
					}
				}
			}
		</script>
	</body>

</html>