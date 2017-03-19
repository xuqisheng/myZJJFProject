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
		<%@ include file="../../common/autocomplete.jsp"%>
		<script src="${root}/resources/vendor/jquery/ztree/js/jquery.ztree.all.js"></script>
		<script src="${root}/resources/vendor/jquery/ztree/js/jquery.ztree.excheck.js"></script>
		<script src="${root}/resources/js/promotion/goods-promotion.js"></script>
		<script src="${root}/resources/js/ztree-search.js"></script>

	</head>

	<body>
		<div class="wrap-bd">
			<div class="">
				<a href="#" class="crumb">促销管理</a>
                <a href="${root }/keFu/SKUActive/getAllSKUActive.do" class="crumb">单品促销</a>
			</div>
		</div>
		<div class="bg wrap-bd">
			<div class="mb-default ">
				<label for="" class="label">活动名称：</label> ${skuActive.activeName }
			</div>
			<div class="mb-default ">
				<span class="label">活动说明：</span> ${skuActive.activeDesc }
			</div>
			<div class="mb-default ">
				<label for="" class="label">状态：</label>
				<c:if test="${skuActive.status==0}">
					新建
				</c:if>
				<c:if test="${skuActive.status==1}">
					已审核
				</c:if>
				<c:if test="${skuActive.status==2}">
					生效中
				</c:if>
				<c:if test="${skuActive.status==3}">
					自动结束
				</c:if>
				<c:if test="${skuActive.status==4}">
					手动结束
				</c:if>
			</div>

			<div class="mb-default ">
				<label for="" class="label"> 添加时间：</label>
				<fmt:formatDate value="${skuActive.addTime }" pattern="yyyy-MM-dd HH:mm:ss" />

			</div>
			<div class="mb-default">
				<label for="" class="label">  添加人：</label> ${skuActive.addUser }
			</div>
			<c:if test="${skuActive.status==2 ||  skuActive.status==3 || skuActive.status==4 }">

				<div class="mb-default ">
					<label for="" class="label"> 审核时间：</label>
					<fmt:formatDate value="${skuActive.confirmTime }" pattern="yyyy-MM-dd HH:mm:ss" />
					<label for="" class="label">  审核人：</label> ${skuActive.confirmUser }
				</div>

				<div class="mb-default ">
					<label for="" class="label"> 生效时间：</label>
					<fmt:formatDate value="${skuActive.effecTime }" pattern="yyyy-MM-dd HH:mm:ss" />
					<label for="" class="label"> 生效次数：</label> ${skuActive.effecCount }
					<label for="" class="label"> 最近生效人：</label> ${skuActive.effecUser }
				</div>
				<div class="mb-default ">
					<label for="" class="label"> 失效时间：</label>
					<fmt:formatDate value="${skuActive.invalidTime }" pattern="yyyy-MM-dd HH:mm:ss" />
					<label for="" class="label">  失效次数：</label> ${skuActive.invalidCount }
					<label for="" class="label">  最近失效人：</label> ${skuActive.invalidUser }
				</div>

			</c:if>

			<div class="mb-default">
				<label for="" class="label">活动时间：</label>
				<fmt:formatDate value="${skuActive.startTime }" pattern="yyyy-MM-dd HH:mm:ss" />
				<!--,dateFmt:'yyyyMMdd HH:mm:ss'控制是时间在分秒-->
				&nbsp;&nbsp; - &nbsp;&nbsp;
				<fmt:formatDate value="${skuActive.endTime }" pattern="yyyy-MM-dd HH:mm:ss" />
				<span class="txt-warn hidden" id="times"></span>
			</div>
			<div class="mb-default">
				<label for="" class="label">展示时间：</label>
				<fmt:formatDate value="${skuActive.upTime }" pattern="yyyy-MM-dd HH:mm:ss" />
				&nbsp;&nbsp; - &nbsp;&nbsp;
				<fmt:formatDate value="${skuActive.downTime }" pattern="yyyy-MM-dd HH:mm:ss" />
				<span class="txt-warn hidden" id="times"></span>
			</div>
			<div class="mb-default">
				<label for="" class="label">购买时间：</label>
				<fmt:formatDate value="${skuActive.buyStartTime }" pattern="yyyy-MM-dd HH:mm:ss" />
				&nbsp;&nbsp; - &nbsp;&nbsp;
				<fmt:formatDate value="${skuActive.buyEndTime }" pattern="yyyy-MM-dd HH:mm:ss" />
				<span class="txt-warn hidden" id="times"></span>
			</div>
			<div class="mb-default">
				<label for="" class="label">发布区域：</label>
				<c:choose>
					<c:when test="${skuActivePublishTag != null && skuActivePublishTag.size()>0 }">
						<c:forEach items="${skuActivePublishTag}" var="item" varStatus="sta">
							<input type="checkbox" name="publish${item.id}" disabled="disabled" id="" value="${item.id}" class="checkbox" <c:if test="${item.checked == '1' }">checked="checked"</c:if> />&nbsp;${item.name}&nbsp;&nbsp;
						</c:forEach>
					</c:when>
					<c:otherwise>
						无发布区域
					</c:otherwise>
				</c:choose>
			</div>
			<div class="mb-default">
				<label class="label">进行编辑：</label>
				<input type="button" name="" id="J_edit" value="添加单品促销商品" class="button button-operate" />
			</div>
			<div class="dialog hidden J_dialog" id="J_dialogEdit" style="width: 800px;">
				<div class="dialog-head">
					选择商品
					<div id="" class="dialog-close">
					</div>
				</div>
				<div>

				</div>
				<div class="dialog-body lastChose">
					<form action="" id="fm_add">
						<div class="mb-default clearfix">
							<div class="fl" style="width: 50%;">
								<label for="">商品分类:
								<select name="yiJiId" style="width: 100px;" class="select ta-c" id="yiji" onchange="selectErJiByYiJi()">
									<option value="" class="select" >一级分类</option>
								</select>
								<select name="erJiId" style="width: 100px;" class="select ta-c" id="erji">
									<option value="" class="select">二级分类</option>
								</select>
							</label>
							</div>
							<div class="fr ta-r" style="width: 50%;">
								<label for="">商品品牌:
								<input type="text" name="brandName" id="brandName" value="" class="input input-default"/>
							</label>
							</div>
						</div>
						<div class="mb-default clearfix">
							<div class="fl" style="widows: 50%;">
								<label for="">商品条码:
								<input type="text" name="mdseId" id="mdseId" value="" class="input input-default mr-small"/>

							</label>
							</div>
							<div class="fr ta-r" style="width: 50%;">
								<label for="">商品名称:
								<input type="text" name="itemBaseName" id="itemBaseName" value="" class="input input-default"/>
							</label>
							</div>
						</div>
						<div class="fr mb-default">
							<span class="dialog-button dialog-ok ml-default" id="query">查询</span>
							<span class="dialog-button ml-small button-ok border" id="resize">重置</span>
						</div>
						<div class="" style="width:100%;height: 250px;overflow: auto;">
							<table class="table-list" id="goods_chose">
								<thead>
									<tr>
										<th>
											<input type="checkbox" name="" id="J_selectAll" value="" />
										</th>
										<th>商品品牌</th>
										<th>商品条码</th>
										<th>商品名称</th>
										<th>规格</th>
										<th>价格</th>
									</tr>
								</thead>
								<tbody id="J_dialog_tbody" class="table-tbody">
									<tr>
										<td colspan="6">暂无数据</td>
									</tr>
								</tbody>
							</table>
						</div>
						<%@ include file="../../common/pagination.jsp"%>
					</form>
				</div>
				<div class="dialog-body hidden nextChose">
					<form action="" method="post" onsubmit="return false">
						<div class="mb-default ">
							<input type="text" name="" id="search-condition" onkeydown="entersearch()" value="" class="input input-default" placeholder="定格名称" style="width:400px ;" />
							<input type="button" name="" id="" onclick="search_ztree('treeDemo', 'search-condition')" value="搜索" class="button button-default" />
						</div>
					</form>
					<div class="ztree" id="ztree">
						<ul id="treeDemo" class="ztree" style="height:140px; overflow:auto">
						</ul>
					</div>
				</div>

				<div class="dialog-foot mb-default mt-default last_df">
					<div class="">
						<span class="dialog-button dialog-ok ml-default" id="next_db">下一步</span>
						<span class="dialog-button ml-small  dialog-cancel">关闭</span>
					</div>
				</div>
				<div class="dialog-foot mb-default mt-default hidden next_df">
					<div class="">
						<span class="dialog-button dialog-ok " id="last_db">上一步</span>
						<span class="dialog-button dialog-ok ml-small" id="add">确认</span>
						<span class="dialog-button ml-small  dialog-cancel">关闭</span>
					</div>
				</div>
				</form>
			</div>
			<div class="cover-all">

			</div>
			<div class="mb-default ">
				<div class="mb-default">
					参与批发商：
				</div>
				<form action="" method="post" id="fm">
					<div class="" style="display: inline-block;width: 80%;">
						<table class="table-list mb-default">
							<thead>
								<tr>
									<th>序列号</th>
									<th>定格名称</th>
									<th>批发商名称</th>
									<th>商品条码</th>
									<th>商品名称</th>
									<th>活动价格</th>
									<th>商品限量</th>
									<th>总限量</th>
									<th>标贴</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${listDetial != null && listDetial.size()>0 }">
										<c:forEach items="${listDetial}" var="item" varStatus="sta">
											<tr>
												<td>${sta.index+1 }<input type="hidden" name="plantItemIds" value="${item.id }" /></td>
												<td>${item.spGroupName }</td>
												<td>${item.supplierName }</td>
												<td>${item.mdseId }</td>
												<td>${item.itemBaseName }</td>
												<td><input type="text" class="input input-date" name="activePrices" id="" onchange="editSKUActive4updatePrice('${skuActive.id}','${item.plantItemId}',this)" value="${item.activePrice }" /></td>
												<td><input type="text" class="input input-date" name="buyLimitNums" id="" onchange="editSKUActive4updateNum('${skuActive.id}','${item.plantItemId}',this)" value="${item.buyLimitNum }" /></td>
												<td><input type="text" class="input input-date" name="totalLimitNum" id="" onchange="editSKUActive4updateTotalNum('${skuActive.id}','${item.plantItemId}',this)" value="${item.totalLimitNum }" /></td>
												<td>
													<select name="tagIds" class="select" onchange="editSKUActive4updateTag('${skuActive.id}','${item.plantItemId}',this)">
														<option value="0">请选择</option>
														<c:forEach items="${tagList}" var="tag">
															<option value="${tag.id}" <c:if test="${item.tagId == tag.id }"> selected="selected" </c:if>> ${tag.name}
															</option>
														</c:forEach>
													</select>
												</td>
												<td><input type="button" name="del_${plantItem.id}" class="button button-operate" id="del_${plantItem.id}" onclick="delSKUActiveGoodsInfo('${skuActive.id}','${item.plantItemId}')" value="删除此项" /></td>
											</tr>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tr>
											<td colspan="10">暂无数据</td>
										</tr>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</div>
			</div>
			<div class="mb-default" style="overflow: hidden;">
				<a href="${root }/keFu/SKUActive/getAllSKUActive.do" class="button button-default fl">返回列表</a> &nbsp;&nbsp;&nbsp;&nbsp;
				<%-- <a href="${root }/keFu/SKUActive/editSKUActive.do?step=1&id=${skuActive.id}" class="button button-default fl">还原</a>
				<a href="#" class="button button-default fl" onclick="saveEditSKUActive('${skuActive.id}')">保存修改设置</a> --%>
			</div>
			</form>
		</div>
	</body>
	<script>
		function entersearch() {
			var event = window.event || arguments.callee.caller.arguments[0];
			if(event.keyCode == 13) {
				search_ztree('treeDemo', 'search-condition');
			}
		}
		$(function() {
			var zTreeObj = null;
			var area = "";
			$.ajax({
				url: '${root}/keFu/SKUActive/queryRegionList.do',
				type: 'GET',
				dataType: 'json',
				success: function(res) {
					console.log(res.responseJson)
					area = res.message;
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
					var zNodes = area;
					$(document).ready(function() {
						zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
					})
				}
			})

			dialogPosCenter('#J_dialogEdit');
			$('#J_edit').on('click', function() {
				//1.拉出分类，2.弹出商品内容
				$('#J_dialogEdit, .cover-all').fadeIn();

			});
			$('#J_edit').on('click', function() {
				//1.拉出分类，2.弹出商品内容
				var html = '<option value="" class="select">一级分类</option>';
				html += queryItemCateByPid(0);
				$("#yiji").html(html);
				$('#J_dialogArea, .cover-all').fadeIn();

			});
			$('.dialog').on('click', '.dialog-cancel', function() {

				$('.J_dialog, .cover-all').fadeOut();
			})
			$('.dialog').on('click', '.dialog-close', function() {

				$('.J_dialog, .cover-all').fadeOut();
			})

			$('#next_db').click(function() {
				$(this).parent().parent().hide();
				$('.next_df').show();
				$('.lastChose').hide();
				$('.nextChose').show();
			})
			$('#last_db').click(function() {
				$(this).parent().parent().hide();
				$('.last_df').show();
				$('.nextChose').hide();
				$('.lastChose').show();
			})
			setRoot('${root}');

			//回写

			//查询商品
			$("#jpagination").pagination({
				pageSize: 5,
				remote: {
					url: '${root}/keFu/SKUActive/getGoodsList.do',
					params: $('#fm_add').serializeArray(),
					success: function(data) {
						var html = '';
						if(data.totalSize > 0) {
							$.each(data.list, function(i, item) {
								html += '<tr>';
								html += '<td>';
								html += '<input type="checkbox" name="J_chk" id="" value="" class="checkbox J_chk"/>';
								html += '<input type="hidden" id="" value="' + item.id + '" class="id" />';
								html += '</td>+';
								html += '<td><spen >';
								if(item.brandName != null && item.brandName != "") {
									html += item.brandName
								} else {
									html += '<spen style="font-style: red">无品牌</spen>';
								}
								html += '</spen></td>';
								html += '<td><spen class="mdseId">' + item.mdseId + '</spen></td>';
								html += '<td><spen class="name">' + item.name + '</spen></td>';
								html += '<td><spen class="spec">' + item.spec + '</spen></td>';
								if(item.pfPrice == null) {
									html += '<td><spen class="pfPrice">0</spen></td>';
								} else {
									html += '<td><spen class="pfPrice">' + item.pfPrice + '</spen></td>';
								}
								html += '</tr>';
							});
						} else {
							html = '<tr><td colspan="6">暂无数据</td></tr>';
						}
						$('#J_dialog_tbody').html(html);
					},
					totalName: 'totalSize'
				}
			});
			selectAll('#J_selectAll', '.J_chk');
			$('.J_chkone').click(function() {
				$(this).attr("checked", true).siblings().attr("checked", false)
			})

			//添加元素
			function innerhtml($trThis) {
				var html_new = '';
				var tagList = '${tagList}';
				var tagJson = JSON.parse(tagList);
				html_new += '<tr>';
				html_new += '<td><input type="hidden" name="itemBaseIds" value="' + $trThis.find(".id").val() + '"/>' + $trThis.find(".mdseId").text() + '<input type="hidden" name="mdseIds" value="' + $trThis.find(".mdseId").text() + '"/></td>';
				html_new += '<td>' + $trThis.find(".name").text() + '<input type="hidden" name="names" value="' + $trThis.find(".name").text() + '"/></td>';
				html_new += '<td>' + $trThis.find(".spec").text() + '<input type="hidden" name="specs" value="' + $trThis.find(".spec").text() + '"/></td>';
				html_new += '<td>' + $trThis.find(".pfPrice").text() + '<input type="hidden" name="pfPrices" value="' + $trThis.find(".pfPrice").text() + '"/></td>';
				html_new += '<td><input type="" name="proPrices" id="" value="" class="input input-date"/></td>';
				html_new += '<td><input type="" name="proLimitNums" id="" value="" class="input input-date"/></td>';
				html_new += '<td>';
				html_new += '<select name="tagIds" class="select">';
				$.each(tagJson, function(i, item) {
					html_new += '<option value="' + item.id + '">' + item.name + '</option>';
				})
				html_new += '</select>';
				html_new += '</td>';
				html_new += '<td>';
				html_new += '<span class="icon-op icon-op-del J_del" title="删除"></span>';
				html_new += '</td>';
				html_new += '</tr>';
				return html_new;
			}
			//根据itembaseId 定格，筛选所有的定格批发商商品
			$('#add').click(function() {
				$('.nextChose').hide();
				$('.J_dialog').hide();
				editSKUActive4addPlantItem('${skuActive.id}', zTreeObj);

				/* var trs = $("input[name='J_chk']:checked").parent().parent();
				var html = '';
				var id = 0;
				var itemBaseIds = null;
				trs.each(function() {
					var chongfu = false;
					var $trThis = $(this);
					id = $trThis.find(".id").val();
					itemBaseIds = $("input[name='itemBaseIds']");
					if(itemBaseIds.length > 0) {
						itemBaseIds.each(function() {
							if($(this).val() == id) {
								chongfu = true;
								return false;
							}
						});
					}
					if(chongfu == false) {
						html += innerhtml($trThis);
					}
				});
				$('#J_addTr').before(html); */
			});

			$('#par').on('click', '.J_del', function() {
				if(confirm('确认删除？')) {
					$(this).parent('td').parent('tr').remove();
				}
			})

			$('#query').click(function() {
				$('#J_dialog_tbody').html('');
				$("#jpagination").pagination('setParams', $('#fm_add').serializeArray());
				$("#jpagination").pagination('setPageIndex', 0);
				$("#jpagination").pagination('remote');
			})
			$('#resize').click(function() {
				$('#J_dialog_tbody').html('')
			})

			//新增商品，模糊品牌
			$('#brandName').autocomplete({
				serviceUrl: '${root}/keFu/SKUActive/getBrandByName.do?str=brand',
				paramName: 'brandName',
				transformResult: function(response) {
					var res = JSON.parse(response)
					if(res.message != null) {
						return {
							suggestions: $.map(res.message, function(value, key) {
								return {
									value: value.name,
									data: value
								};
							})
						};
					} else {
						return {
							suggestions: [{
								value: "无数据"
							}]
						};
					}
				},
				onSelect: function(dd) {
					if(dd.value === "无数据") {
						$(this).val("");
						return;
					}
					$("#upId").val(dd.data.id);
				}
			});

			//新增商品，模糊商品名称
			$('#itemBaseName').autocomplete({
				serviceUrl: '${root}/keFu/SKUActive/getItemBaseByName.do?',
				paramName: 'itemBaseName',
				transformResult: function(response) {
					var res = JSON.parse(response)
					if(res.message != null) {
						return {
							suggestions: $.map(res.message, function(value, key) {
								return {
									value: value.name,
									data: value
								};
							})
						};
					} else {
						return {
							suggestions: [{
								value: "无数据"
							}]
						};
					}
				},
				onSelect: function(dd) {
					if(dd.value === "无数据") {
						$(this).val("");
						return;
					}
					$("#upId").val(dd.data.id);
				}
			});
		})
	</script>

</html>
