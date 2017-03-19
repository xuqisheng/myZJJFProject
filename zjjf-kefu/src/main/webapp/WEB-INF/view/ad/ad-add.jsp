<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>添加广告</title>
		<%@ include file="../common/head.jsp"%>
		<script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
		<%@ include file="../common/ztree.jsp"%>
		<script src="${root}/resources/js/comm.js"></script>
		<script src="${root}/resources/vendor/jquery/ajaxfileupload.js"></script>
		<script src="${root }/resources/js/ztree-search.js"></script>
	</head>
	<body>
		<div class="wrap-bd">
			<div class="mb-small">
				<a class="crumb" href="${root}/Customer/AdManage/toIdex.do">广告管理</a>
				<c:choose>
					<c:when test="${map.advertisement != null}">
						<a class="crumb crumb-active">编辑广告</a>
					</c:when>
					<c:otherwise>
						<a class="crumb crumb-active">添加广告</a>
					</c:otherwise>
				</c:choose>
			</div>
			<fieldset class="bg">
				<form action="" id="adForm">
					<input type="hidden" name="id" value="${map.advertisement.id}">
					<p>
						<label class="label">排序：</label>
						<input class="input input-default" type="text" value="${map.advertisement.ordId}" name="ordId" id="ordid" maxlength="3"/>
					</p>
					<p>
						<label class="label">名称：</label>
						<input class="input input-default" type="text" value="${map.advertisement.name}" name="name" id="name" maxlength="60"/>
						<span class="error-prompt" id="name_error"></span>
					</p>
					<p>
						<label class="label">描述：</label>
						<input class="input input-default" type="text" value="${map.advertisement.desc}" name="desc" id="desc" maxlength="60"/>
						<span class="error-prompt" id="desc_error"></span>
					</p>
					<p>
						<label class="label">有效时间：</label>
						
						<input type="text" class="input input-date J_TIME_START" name="startTime" id="" value="<fmt:formatDate value="${map.advertisement.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" /> 至
						<input type="text" class="input input-date J_TIME_END" name="endTime" id="" value="<fmt:formatDate value="${map.advertisement.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
						<%-- <fmt:formatDate value="${map[advertisement].endTime}" pattern=""/> --%>
					</p>
					<p>
						<label class="label">广告位：</label>
							
							<select class="select" name="boardId" id="adPosition">
								<option value="">请选择</option>
								<c:forEach var="adboard" items="${map.adboardList}">
									<option value="${adboard.id}" <c:if test="${adboard.id == map.adboard.id}"> selected="selected"</c:if>>${adboard.name}</option>
								</c:forEach>
							</select>
					</p>
					<p>
						<label class="label">广告图：</label>
						<input class="input" type="text" value="${map.advertisement.url}" name="url" id="iosImg" style="width:480px;" maxlength="225">
						<input type="hidden" name="" id="good_img" value="${ad.extimg}" />
						<span class="error-prompt" id="send-img"></span>
					</p>
					<p>
						<label class="label">广告详图：</label>
						<input class="input" type="text" value="${map.advertisement.extimg}" name="extimg" id="androidImg" style="width:480px;" maxlength="225">
					</p>
					<p>
						<label class="label">点击效果</label>
						<select name="isClick" class="select isClick">
							<option value="">请选择</option>
							<option value="0">不可点击</option>
							<option value="1">可点击</option>
						</select>
					</p>
					<p class="hidden gn">
						<label class="label">打开功能</label>
						<select name="clickType" class="select clickType">
							<option value="">请选择</option>
							<option value="1">广告详情</option>
							<option value="2">商品分类</option>
							<option value="3">商品标签</option>
							<option value="4">商品品牌</option>
						</select>
						<select name="col1" class="select hidden skipId">
							<option value="">请选择</option>
							<c:if test="${map.advertisement.isClick}">
								<c:if test="${map.advertisement.clickType==2}">
									<c:forEach var="catelog" items="${map.catelogList}">
										<option value="${catelog.id }" <c:if test="${itemTag.id ==map.advertisement.classId}"> selected="selected"</c:if>>${catelog.name }</option>
									</c:forEach>
								</c:if>
								<c:if test="${map.advertisement.clickType==3}">
									<c:forEach var="itemTag" items="${map.itemTagList}">
										<option value="${itemTag.id }" <c:if test="${itemTag.id ==map.advertisement.itemTagId}"> selected="selected"</c:if>>${itemTag.id }</option>
									</c:forEach>
								</c:if>
							</c:if>
						</select>
					</p>
					<p class="hidden linkUrl" id="hiddenText">
						<input class="input" type="text" style="width:500px;" value="zjjf-kefu/view/app/promotion/ad-detail.jsp" name="linkUrl" readonly="readonly" />
					</p>
					<p>
						<label class="label mb-small">内容：</label>
						<script id="ueditorContent" name="content" type="text/plain"></script>
						<script src="${root }/resources/vendor/ueditor/ueditor.config.js"></script>
						<script src="${root }/resources/vendor/ueditor/ueditor.all.min.js"></script>
						<script>
							var ue = UE.getEditor('ueditorContent');
							ue.addListener('ready', function() {
								ue.setContent('${map.advertisement.content}');
							});
						</script>
					</p>
					<p>
						<label class="label">参与区域</label>
						<select name="asDefault" class="select spGroup" id="J_spGroupSelect">
							<option value="">请选择</option>
							<option value="1">全部定格</option>
							<option value="0">指定定格</option>
						</select>
					</p>
					<p class="hidden a">
						<label for="" class="label">参与定格：</label>
						<input type="button" class="button button-operate" name="" id="J_addArea" value="增加定格区域" />
					</p>
						<table class="table-list mb-default hidden tab">
							<thead>
								<tr>
									<th>定格名称</th>
									<th>定格分区</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody class="table-tbody" id="spGroup_info">
								<c:choose>
									<c:when test="${map.spGroupList != null && map.spGroupList.size()>0 }">
										<c:forEach var="spGroup" items="${map.spGroupList}">
											<tr>
												<td>${spGroup.name }<input type="hidden" value="${spGroup.id }" class="spGroupId" name="spGroupId"></td>
												<td>${spGroup.areaName }</td>
												<td><input type="button" value="删除" class="s_del"></td>
											</tr>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tr><th colspan="3">暂无数据</th></tr>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
						<div class="cover-all">
							
						</div>
					<div class="dialog hidden J_dialog" id="J_dialogArea" style="width: 450px;">
						<form action="" method="post">
							<div class="dialog-head">
								选择定格区域
								<div id="" class="dialog-close">
								</div>
							</div>
							<div class="dialog-body">
								<form action="" method="post">
									<div class="mb-default ">
										<input type="text" name="" id="search-condition"  onkeydown="entersearch()" value="" class="input input-default" placeholder="定格名称" />
										<input type="button" name="" id="" onclick="search_ztree('treeDemo', 'search-condition')" value="搜索" class="input input-search-button" />
									</div>
								</form>
								<div class="ztree" style="height: 150px;overflow: auto;">
									<ul id="treeDemo" class="ztree" style="height:140px; overflow:auto"></ul>
								</div>
							</div>
							<div class="dialog-foot">
								<div class="">
									<span class="dialog-button dialog-ok ml-default" id="saveArea">保存</span>
									<span class="dialog-button ml-small  dialog-cancel">取消</span>
								</div>
							</div>
						</form>
					</div>
					<p>
						
						<c:choose>
							<c:when test="${map.str == 'Q' || map.advertisement.isDelete}">
								<input type="button" id="btn" class="button button-cancel" value="返回" onclick="okClose()"/>
							</c:when>
							<c:otherwise>
								<input type="button" id="btn" class="button button-ok" value="提交" onclick="checkForm()" />
								<input type="button" id="btn" class="button button-cancel" value="返回" onclick="okClose()"/>
							</c:otherwise>
						</c:choose>
					</p>
				</form>
			</fieldset>
			<script>
				function entersearch() {
			var event = window.event || arguments.callee.caller.arguments[0];
			if(event.keyCode == 13) {
				search_ztree('treeDemo', 'search-condition');
			}
		}
			$(function() {
				dialogPosCenter('#J_dialogArea');
				$('#J_addArea').on('click', function() {
					$('#J_dialogArea, .cover-all').fadeIn();
				});
				$('.J_dialog').on('click', '.dialog-cancel', function() {
					$('.J_dialog, .cover-all').fadeOut();
				})
				$('.dialog').on('click', '.dialog-close', function() {
					$('.J_dialog, .cover-all').fadeOut();
				})
				var zTreeObj=null;
				
				var setting = {view: {showLine: false},data: {key: {children: "regionList",},simpleData: {enable: true}}};
				var regionList='${map.regionList}';
				var zNodes = JSON.parse(regionList);
				$(document).ready(function() {
					zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
					zTreeObj.expandAll(true);
				});
				var spGroups = new Array();
				$('#saveArea').on('click',function(){
					var html= '';
					var trSpGroupIds = $(".spGroupId");
					$.each(zTreeObj.getSelectedNodes(),function(i,item){
						var chongfu = false;
						if(trSpGroupIds.length>0){
							trSpGroupIds.each(function(){
								if(item.id == $(this).val()){
									chongfu=true;
									return false;
								}
							});
						}
						if(chongfu==false){
							html+='<tr>';
							html+='<td>'+item.name+'<input type="hidden" class="spGroupId" value="'+item.id+'" name="spGroupId"></td>';
							html+='<td>'+item.getParentNode().name+'</td>';
							html+='<td><input type="button" value="删除" class="s_del"></td>';
							html+='</tr>';
						}
					});
					if(trSpGroupIds != null && trSpGroupIds.length > 0){
						$("#spGroup_info").append(html);
					}else{
						$("#spGroup_info").html(html);
					}
				});	
				});
				
				
				function checkForm() {
					
					if($('#J_spGroupSelect').val()==-1){
						layer.msg('请选择区域!');
						return;
					}
					
					$.ajax({
						type: "POST",
						url: "${root}/Customer/AdManage/saveAdvertisement.do",
						async: false,
						dataType: "json",
						data: $("#adForm").serialize(),
						success: function(da) {
							if(da.success) {
								alert(da.message);
								location.href = "${root}/Customer/AdManage/toIdex.do";
							} else {
								alert(da.message);
							}
						},
						error: function(da) {
							alert("请求失败");
						}
					});
				}
				function okClose(){
					location.href = "${root}/Customer/AdManage/toIdex.do";
				}
				$(function() {
					dateRange('.J_TIME_START', '.J_TIME_END');
					$('.isClick').on('change',function(){
						if($(this).val()==1){
							$('.gn').show();
						}else{
							$('.gn').hide();
						}
					});
					//加载skipId数据
					function getSkipIdData(url){
						var html='<option value="">请选择</option>';
						$.ajax({
							url:url,
							dataType:'json',
							type:'post',
							async: false,
							success:function(data){
								if(data.success){
									$.each(data.message,function(i,item){
										html+='<option value="'+item.id+'">'+item.name+'</option>';
									});
								}
							},
							error:function(error){
								alert("请求异常");
							}
						});
						return html;
					}
					$('.clickType').on('change',function(){
						if($(this).val()==1){
							$('.linkUrl').show();
							$('.skipId').hide();
						}else if($(this).val()==2){
							var url = '${root}/Customer/AdManage/getSecondCateList.do';
							$('.skipId').html(getSkipIdData(url));
							$('.skipId').show();
							$('.linkUrl').hide();
						}else if($(this).val()==3){
							var url = '${root}/kefu/appModule/getAllItemTag.do';
							$('.skipId').html(getSkipIdData(url));
							$('.skipId').show();
							$('.linkUrl').hide();
						}else if($(this).val()==4){
							$(this).val('');
							alert("请选择其它项");
						}else{
							$('.skipId').hide();
							$('.linkUrl').hide();
						}
					});
					$('.spGroup').on('change',function(){
						if($(this).val()==0){
							$('.a').show();
							$('.tab').show();
						}else{
							$('.a').hide();
							$('.tab').hide();
						}
					});

					//回显数据
					var advertisement = '${map.advertisement}'
					if(advertisement != null && advertisement != '') {
						var jsonAdStr = '${map.jsonAd}'
						var jsonAd = JSON.parse(jsonAdStr);
						if(jsonAd.isClick){
							$('.gn').show();
						}else{
							$('.gn').hide();
						}
						if(jsonAd.isClick){
							$('.isClick').val('1');
							$('.gn').show();
						}else{
							$('.isClick').val('0');
							$('.gn').hide();
						}
						if(jsonAd.isClick){
							if(jsonAd.clickType==1){
								$('.clickType').val('1');
								$('.linkUrl').show();
								$('.skipId').hide();
							}else if(jsonAd.clickType==2){	
								$('.clickType').val('2');
								var url = '${root}/Customer/AdManage/getSecondCateList.do';
								$('.skipId').html(getSkipIdData(url));
								$('.skipId').val(jsonAd.classId);
								$('.skipId').show();
								$('.linkUrl').hide();
							}else if(jsonAd.clickType==3){
								$('.clickType').val('3');
								var url = '${root}/kefu/appModule/getAllItemTag.do';
								$('.skipId').html(getSkipIdData(url));
								$('.skipId').val(jsonAd.itemTagId);
								$('.skipId').show();
								$('.linkUrl').hide();
							}else if(jsonAd.clickType==4){
								$('.clickType').val('4');
								$(this).val('');
								alert("请选择其它项");
							}else{
								$('.skipId').hide();
								$('.linkUrl').hide();
							}
						}
						if(!jsonAd.asDefault){
							$('.spGroup').val('0');
							$('.a').show();
							$('.tab').show();
						}else{
							$('.spGroup').val('1');
							$('.a').hide();
							$('.tab').hide();
						}
					}
					
					$('#spGroup_info').on('click','.s_del',function(){
						$(this).parent().parent().remove();
						if($('.spGroupId').length == 0 || $('.spGroupId') == ""){
							$("#spGroup_info").html('<tr><th colspan="3">暂无数据</th></tr>');
						}
					});
				});
			</script>
		</div>
	</body>

</html>