<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">

	<head>
		<meta charset="UTF-8">
		<title>增加单品促销</title>
		<%@ include file="../../common/head.jsp"%>
		<%@ include file="../../common/autocomplete.jsp"%>
		<script src="${root }/resources/vendor/My97DatePicker/WdatePicker.js"></script>
		<script src="${root }/resources/js/promotion/goods-promotion.js"></script>
		<script src="${root }/resources/js/comm.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<style type="text/css">

	</style>

	<body>
		<div class="wrap-bd">
			<div class="mb-default">
				<a href="#" class="crumb">促销管理</a>
				<a href="${root }/keFu/SKUActive/getAllSKUActive.do" class="crumb">单品促销</a>
				<a href="${root }/keFu/SKUActive/returnAddOrEditPage.do?action=1" class="crumb">新增单品促销</a>
			</div>
			<div class="bg wrap-bd clearfix">
				<form action="${root}/keFu/SKUActive/skuActiveNext.do" method="post" id="fm">
					<div class="mb-default ">
						<label for="" class="label">活动名称：</label>
						<input type="text" name="activeName" value="${sessionObject.activeName}" class="input input-default mr-default" />
						<span class="txt-warn hidden" id="active-name-warn"></span>
					</div>
					<div class="mb-default ">
						<span class="label">活动说明：</span>
						<input type="text" name="activeDesc" id="active-desc" value="${sessionObject.activeDesc}" class="input input-default mr-default" />
						<span class="txt-warn hidden" id="active-desc-warn"></span>
					</div>
					<div class="mb-default">
						<label for="" class="label">活动时间：</label>
						<input type="text" name="activeStartTime"  onblur="kong(),getTime(),be()" value="<fmt:formatDate value="${sessionObject.startTime }" pattern="yyyy-MM-dd HH:mm:ss" />" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d'})" class="input input-default J_timeS" />
						<!--,dateFmt:'yyyyMMdd HH:mm:ss'控制是时间在分秒
						  <c:if test="${sessionObject.startTime} != null"> value="<fmt:formatDate value="${sessionObject.endTime }" pattern="yyyy-MM-dd HH:mm" />"</c:if>
						-->
						-
						<input type="text" name="activeEndTime" onblur="time(),getTime(),be()" value="<fmt:formatDate value="${sessionObject.endTime }" pattern="yyyy-MM-dd HH:mm:ss" />" id="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="input input-default J_timeE mr-default" />
						<span class="txt-warn" id="times"></span>
					</div>
					<%--<div class="mb-default">
						<label for="" class="label">展示时间：</label>
						<input type="text" name="upTime"  onblur="" value="<fmt:formatDate value="${sessionObject.upTime }" pattern="yyyy-MM-dd HH:mm:ss" />" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d'})" class="input input-default J_timeS" />
						-
						<input type="text" name="downTime" onblur="" value="<fmt:formatDate value="${sessionObject.downTime }" pattern="yyyy-MM-dd HH:mm:ss" />" id="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="input input-default J_timeE mr-default" />
						<span class="txt-warn" id="times"></span>
					</div>--%>
                    <div class="mb-default hidden">
                        <label for="" class="label">展示时间：</label>
                        <input type="text" name="upTime"  onblur="" value="<fmt:formatDate value="${sessionObject.upTime }" pattern="yyyy-MM-dd HH:mm:ss" />" readonly="readonly" class="input input-default J_timeS" />
                        -
                        <input type="text" name="downTime" onblur="" value="<fmt:formatDate value="${sessionObject.downTime }" pattern="yyyy-MM-dd HH:mm:ss" />" readonly="readonly" class="input input-default J_timeE mr-default" />
                        <span class="txt-warn" id="times"></span>
                    </div>
					<div class="mb-default">
						<label for="" class="label">购买时间：</label>
						<input type="text" name="buyStartTime"  onblur="be()" value="<fmt:formatDate value="${sessionObject.buyStartTime }" pattern="yyyy-MM-dd HH:mm:ss" />" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d'})" class="input input-default J_timeS" />
						-
						<input type="text" name="buyEndTime" onblur="time(),be()" value="<fmt:formatDate value="${sessionObject.buyEndTime }" pattern="yyyy-MM-dd HH:mm:ss" />" id="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="input input-default J_timeE mr-default" />
						<span class="txt-warn" id="times"></span>
					</div>
					<div class="mb-default" id="releaseArea">
						<label for="" class="label">发布区域：</label>
						<c:choose>
							<c:when test="${skuActivePublishTag != null && skuActivePublishTag.size()>0 }">
								<c:forEach items="${skuActivePublishTag}" var="item" varStatus="sta">
									<input type="checkbox" name="publishTag" id="${item.id}" value="${item.id}" class="checkbox" <c:if test="${item.checked == '1' }">checked="checked"</c:if> />&nbsp;${item.name}&nbsp;&nbsp;
								</c:forEach>
							</c:when>
						</c:choose>
					</div>
					<div class="mb-default clearfix">
						<div class="label va-t">商品列表：</div>
						<div style="display:inline-block;width: 80%;">
							<table class="table-list">
								<thead>
									<tr>
										<th>商品条码</th>
										<th>商品名称</th>
										<th>规格</th>
										<th>原价格</th>
										<th>活动售价</th>
										<th>单个ID限购</th>
										<th>商品标签</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody id="par">
									<tr id="J_addTr">
										<td colspan="9">
											<span class="button button-operate" id="J_dialog-addGoods">
												新增商品
											</span>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="">
						<input type="button" id="okNext" class="button button-ok" value="下一步">
						<a href="${root }/keFu/SKUActive/getAllSKUActive.do" class="button button-cancel ml-small">取消</a>
					</div>
				</form>
			</div>
			<div class="cover-all">

			</div>
			<div class="dialog hidden J_dialog" id="J_dialogArea" style="width: 800px;">
				<div class="dialog-head">
					选择商品
					<div id="" class="dialog-close">
					</div>
				</div>
				<div class="dialog-body clearfix">
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
				<div class="dialog-foot mb-default mt-default">
					<div class="">
						<span class="dialog-button dialog-ok ml-default" id="add">增加</span>
						<span class="dialog-button ml-small  dialog-cancel">关闭</span>
					</div>
				</div>
				</form>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		$(function() {
			setRoot('${root}');
			var tagList = '${skuActiveLabel}';
			var tagJson = JSON.parse(tagList);
			var action = '${action}';
			//var sessionObject = JSON.parse('${sessionObject}');
			$("#okNext").on("click", function() {

				var activeName = $.trim($("input[name='activeName']").val());
				if(!an(activeName)) {
					console.log(123);
					return;
				}
				var activeDesc = $.trim($("input[name='activeDesc']").val());
				if(!ad(activeDesc)) {
					return;
				}
				var startTime = $.trim($("input[name='activeStartTime']").val());
				if(!ti(startTime, 0)) {
					return;
				}
				var endTime = $.trim($("input[name='activeEndTime']").val());
				if(!ti(endTime, 1)) {
					return;
				}
				var checkboxs = $("input[type='checkbox']:checked");
				console.log()
				if(checkboxs == null || checkboxs.length == 0) {
					alert("请选择发布区域");
					return;
				}
				var itemBaseIds = $("input[name='itemBaseIds']");
				if(itemBaseIds == null || itemBaseIds.length == 0) {
					alert("请选择商品");
					return;
				}

                if($('#releaseArea').find('input[type="checkbox"]:checked').length==0){
                    alert("请选择发布区域");
                    return;
                }

				$("#fm").submit();
			});

			//正整数校验
			$('#par').on('blur','.intNum',function(){
				var proLimitNum = $(this).val();
				if(proLimitNum.length != 0) {
					 var reg = /^\+?[0-9]\d*$/;
					var r = proLimitNum.match(reg);
					if(r == null){
						alert('商品数量不可存在空格且必须为整数!');
						$(this).val('');//请将“日期”改成你需要验证的属性名称!
					}
				}
			})


			//数字校验
			$('#par').on('blur','.priNum',function(){
				var proLimitNum = $.trim($(this).val());
				if(proLimitNum.length != 0) {
					 var reg = /^\s*(\+|-)?((\d+([\.,]\d+)?)|([\.,]\d+))\s*$/;
					var r = proLimitNum.match(reg);
					if(r == null){
						alert('请输入数字!');
						$(this).val('');//请将“日期”改成你需要验证的属性名称!
						return;
					}
					if($(this).val()<=0){
					alert('售价必须大于0');
					$(this).val('');
					return;
				}
				}

			})

			function an(namelen) {
				if(namelen == "") {
					$('#active-name-warn').text("活动名称不能为空")
					$('#active-name-warn').show()
					return false;
				} else {
					if(namelen.length > 20) {
						$('#active-name-warn').text("最长20个字，最短4个字。")
						$('#active-name-warn').show()
						return false;
					} else if(namelen.length < 4) {
						$('#active-name-warn').text("最长20个字，最短4个字。")
						$('#active-name-warn').show()
						return false;
					} else {
						$('#active-name-warn').text("")
						$('#active-name-warn').hide()
						return true;
					}
				}
			};

			function ad(desclen) {
				if(desclen.length > 20) {
					$('#active-desc-warn').text("最长20个字。");
					$('#active-desc-warn').show()
					return false;
				} else {
					console.log(123);
					$('#active-desc-warn').text("");
					$('#active-desc-warn').hide()
					return true;
				}
			};

			function ti(len, n) {
				var str = "";
				if(len == "") {
					if(n == 0) {
						str = "开始时间不能为空";
					} else {
						str = "结束时间不能为空";
					}
					$("#times").text(str);
					$('#times').show()
					return false;
				} else if(!len == "") {
					str = "";
					$("#times").text(str);
					$('#times').show()
				}
				return true;
			};
			//回写
			var itemBases = '${itemBases}';
			if(itemBases != null && itemBases != '') {
				var objJson = JSON.parse(itemBases);

				var html = '';
				$.each(objJson, function(i, item) {
					html += '<tr>';
					html += '<td><input type="hidden" name="brandIds" value="' + item.brandId + '"/><input type="hidden" name="itemBaseIds" value="' + item.id + '"/>' + item.mdseId + '<input type="hidden" name="mdseIds" value="' + item.mdseId + '"/></td>';
					html += '<td>' + item.name + '<input type="hidden" name="names" value="' + item.name + '"/></td>';
					html += '<td>' + item.spec + '<input type="hidden" name="specs" value="' + item.spec + '"/></td>';
					html += '<td>' + item.pfPrice + '<input type="hidden" name="pfPrices" value="' + item.pfPrice + '" /></td>';
					html += '<td><input type="" name="proPrices" id="" class="input input-date priNum" value="';
					if(item.proPrice != null) {
						html += item.proPrice
					}
					html += '"/></td>';
					html += '<td><input type="" name="proLimitNums" id="" class="input input-date intNum" value="';
					if(item.proLimitNum != null) {
						html += item.proLimitNum
					}
					html += '" /></td>';
					html += '<td>';
					html += '<select name="tagIds" class="select">';
					$.each(tagJson, function(i, item1) {
						html += '<option value="' + item1.id + '"';
						if(item.tagId == item1.id) {
							html += 'selected="selected"';
						}
						html += '>' + item1.name + '</option>';
					})
					html += '</select>';
					html += '</td>';
					html += '<td>';
					html += '<span class="icon-op icon-op-del J_del" title="删除"></span>';
					html += '</td>';
					html += '</tr>';
				});
				$('#J_addTr').before(html);
			}

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
								html += '<td><input type="hidden" class="brandId" name="brandId" value="' + item.brandId + '"/><spen class="brandName">';
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

			if('' == 1) {
				$('#isVoluntary').attr('checked', true);
			} else {
				$('#isVoluntary').attr('checked', false);
			}
			$('#ruleType').val('-1');
			dialogPosCenter('#J_dialogArea');
			$('#J_dialog-addGoods').on('click', function() {
				//1.拉出分类，2.弹出商品内容
				var html = '<option value="" class="select">一级分类</option>';
				html += queryItemCateByPid(0);
				$("#yiji").html(html);
				$('#J_dialogArea, .cover-all').fadeIn();

			});
			$('.J_dialog').on('click', '.dialog-cancel', function() {

				$('.J_dialog, .cover-all').fadeOut();
			})
			$('.dialog').on('click', '.dialog-close', function() {

				$('.J_dialog, .cover-all').fadeOut();
			})
			selectAll('#J_selectAll', '.J_chk');
			$('.J_chkone').click(function() {
				$(this).attr("checked", true).siblings().attr("checked", false)
			})

			//添加元素
			function innerhtml($trThis) {
				var html_new = '';
				html_new += '<tr>';
				html_new += '<td><input type="hidden" name="brandIds" value="' + $trThis.find(".brandId").val() + '"/><input type="hidden" name="itemBaseIds" value="' + $trThis.find(".id").val() + '"/>' + $trThis.find(".mdseId").text() + '<input type="hidden" name="mdseIds" value="' + $trThis.find(".mdseId").text() + '"/></td>';
				html_new += '<td>' + $trThis.find(".name").text() + '<input type="hidden" name="names" value="' + $trThis.find(".name").text() + '"/></td>';
				html_new += '<td>' + $trThis.find(".spec").text() + '<input type="hidden" name="specs" value="' + $trThis.find(".spec").text() + '"/></td>';
				html_new += '<td>' + $trThis.find(".pfPrice").text() + '<input type="hidden" name="pfPrices" value="' + $trThis.find(".pfPrice").text() + '"/></td>';
				html_new += '<td><input type="" name="proPrices" id="" value="" class="input input-date priNum"/></td>';
				html_new += '<td><input type="" name="proLimitNums"  id="" value="" class="input input-date intNum"/></td>';
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

			$('#add').click(function() {
				var trs = $("input[name='J_chk']:checked").parent().parent();
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
				$('#J_addTr').before(html);
			})
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

		function kong() {
			if('${action}' === '1'){
				$('.J_timeE').val('');
			}else if('${action}' === '2'){
				var strE = new Date('${sessionObject.endTime }');
				var strS = new Date('${sessionObject.startTime }');
				htmlTimes(strS,strE);
			}

		}

		function time() {
			if($('.J_timeS').val() == "") {
				alert('请先选择开始时间');
				return;
			}
			var strS = $('.J_timeS').val();
			var strE = $('.J_timeE').val();
			var arrS = strS.split(" ");
			var arrE = strE.split(" ");
			var h1 = arrS[1].split(":");
			var h2 = arrE[1].split(":");
			var h = h2[0] - h1[0];
			var m = h2[1] - h1[1];
			console.log()
			var aDate = arrE[0].split("-");
			var oDate1 = new Date(aDate[1] + '/' + aDate[2] + '/' + aDate[0]); //转换为MM-dd-yyyy格式
			var aDate = arrS[0].split("-");
			var oDate2 = new Date(aDate[1] + '/' + aDate[2] + '/' + aDate[0]);
			var timeSpan = {};
			var TotalMilliseconds = oDate1 - oDate2; //相差的毫秒数
			timeSpan.Days = parseInt(TotalMilliseconds / 1000 / 60 / 60 / 24);
			timeSpan.TotalHours = parseInt(TotalMilliseconds / 1000 / 60 / 60);
			timeSpan.Hours = timeSpan.TotalHours % 24;
			timeSpan.TotalMinutes = parseInt(TotalMilliseconds / 1000 / 60);
			timeSpan.Minutes = timeSpan.TotalMinutes % 60;
			timeSpan.TotalSeconds = parseInt(TotalMilliseconds / 1000);
			timeSpan.Seconds = timeSpan.TotalSeconds % 60;
			timeSpan.Milliseconds = TotalMilliseconds;
			var milliseconds = TotalMilliseconds % 1000;
			if(TotalMilliseconds < 0) {
				alert('该时间必须大于开始时间，请重新选择');
				$('.J_timeE').val("");
				$('#times').html('');

			} else if(TotalMilliseconds == 0) {
				if(h<0){
						alert('该时间必须大于开始时间，请重新选择');
				}
				else if(h == 0) {
					if(m <= 0) {
						alert('该时间必须大于开始时间，请重新选择');
						$('.J_timeE').val("");
						$('#times').html('')
					} else {
						$('#times').html('活动持续时间为 ' + 0 + ' 天 ' + h + '小时' + m + ' 分 ');
					}
				} else {
					if(m < 0) {
						m = m + 60;
						h = h - 1;
						$('#times').html('活动持续时间为 ' + 0 + ' 天 ' + h + ' 小时 ' + m + ' 分 ');
					}
					$('#times').html('活动持续时间为 ' + 0 + ' 天 ' + h + ' 小时 ' + m + ' 分 ');
				}
			} else {
				if(m < 0) {
					m = m + 60;
					h = h - 1;
					if(h < 0) {
						h = h + 24;
						timeSpan.Days = timeSpan.Days - 1;
					}
					$('#times').html('活动持续时间为 ' + timeSpan.Days + ' 天 ' + h + ' 小时 ' + m + ' 分 ');
				} else if(h < 0) {
					h = h + 24;
					timeSpan.Days = timeSpan.Days - 1;
					$('#times').html('活动持续时间为 ' + timeSpan.Days + ' 天 ' + h + ' 小时 ' + m + ' 分 ')
				} else {
					$('#times').html('活动持续时间为 ' + timeSpan.Days + ' 天 ' + h + ' 小时 ' + m + ' 分 ');
				}
			}
		}

		function htmlTimes(strS,strE){
			var arrS = strS.split(" ");
			var arrE = strE.split(" ");
			var h1 = arrS[1].split(":");
			var h2 = arrE[1].split(":");
			var h = h2[0] - h1[0];
			var m = h2[1] - h1[1];
			console.log()
			var aDate = arrE[0].split("-");
			var oDate1 = new Date(aDate[1] + '/' + aDate[2] + '/' + aDate[0]); //转换为MM-dd-yyyy格式
			var aDate = arrS[0].split("-");
			var oDate2 = new Date(aDate[1] + '/' + aDate[2] + '/' + aDate[0]);
			var timeSpan = {};
			var TotalMilliseconds = oDate1 - oDate2; //相差的毫秒数
			timeSpan.Days = parseInt(TotalMilliseconds / 1000 / 60 / 60 / 24);
			timeSpan.TotalHours = parseInt(TotalMilliseconds / 1000 / 60 / 60);
			timeSpan.Hours = timeSpan.TotalHours % 24;
			timeSpan.TotalMinutes = parseInt(TotalMilliseconds / 1000 / 60);
			timeSpan.Minutes = timeSpan.TotalMinutes % 60;
			timeSpan.TotalSeconds = parseInt(TotalMilliseconds / 1000);
			timeSpan.Seconds = timeSpan.TotalSeconds % 60;
			timeSpan.Milliseconds = TotalMilliseconds;
			var milliseconds = TotalMilliseconds % 1000;
			if(TotalMilliseconds < 0) {
				alert('该时间必须大于开始时间，请重新选择');
				$('.J_timeE').val("");
				$('#times').html('')
			} else if(TotalMilliseconds == 0) {
				if(h <= 0) {
					if(m <= 0) {
						alert('该时间必须大于开始时间，请重新选择');
						$('.J_timeE').val("");
						$('#times').html('')
					} else {
						$('#times').html('活动持续时间为 ' + 0 + ' 天 ' + h + '小时' + m + ' 分 ');
					}
				} else {
					if(m < 0) {
						m = m + 60;
						h = h - 1;
						$('#times').html('活动持续时间为 ' + 0 + ' 天 ' + h + ' 小时 ' + m + ' 分 ');
					}
					$('#times').html('活动持续时间为 ' + 0 + ' 天 ' + h + ' 小时 ' + m + ' 分 ');
				}
			} else {
				if(m < 0) {
					m = m + 60;
					h = h - 1;
					if(h < 0) {
						h = h + 24;
						timeSpan.Days = timeSpan.Days - 1;
					}
					$('#times').html('活动持续时间为 ' + timeSpan.Days + ' 天 ' + h + ' 小时 ' + m + ' 分 ');
				} else if(h < 0) {
					h = h + 24;
					timeSpan.Days = timeSpan.Days - 1;
					$('#times').html('活动持续时间为 ' + timeSpan.Days + ' 天 ' + h + ' 小时 ' + m + ' 分 ')
				} else {
					$('#times').html('活动持续时间为 ' + timeSpan.Days + ' 天 ' + h + ' 小时 ' + m + ' 分 ');
				}
			}
		}
		/*获取时间*/
		function getTime(){
            var actSTime=$('input[name="activeStartTime"]').val();
            var actETime=$('input[name="activeEndTime"]').val();

            $('input[name="upTime"]').val(actSTime);
            $('input[name="downTime"]').val(actETime)
        }
        //比较活动时间和购买时间差
        function be(){
            var date1=$('input[name="activeStartTime"]').val();
            var date2=$('input[name="buyStartTime"]').val();
            var date3=$('input[name="activeEndTime"]').val();
            var date4=$('input[name="buyEndTime"]').val();
            var d1=new Date(date1);
            var d2=new Date(date2);
            var d3=new Date(date3);
            var d4=new Date(date4);
            if(d1>d2){
                alert('购买开始时间须大于活动开始时间');
                $('input[name="buyStartTime"]').val('');
                $('input[name="buyEndTime"]').val('');
            }else if(d2>d3){
                alert('购买开始时间须小于活动结束时间');
                $('input[name="buyStartTime"]').val('');
            }
            if(d3<d4){
                alert('购买结束时间须小于活动结束时间');
                $('input[name="buyEndTime"]').val('')
            }
            if(d2>d4){
                alert('购买结束时间须大于购买开始时间');
                $('input[name="buyEndTime"]').val('')
            }
        }
	</script>

</html>
