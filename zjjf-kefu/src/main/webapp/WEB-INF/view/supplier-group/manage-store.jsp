<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>定格管理</title>
<%@ include file="../common/head.jsp"%>
<script src="${root}/resources/js/comm.js"></script>
</head>
<body>
<div class="wrap-bd">
	<div>
		<div>
			定格管理 &gt; <span class="bule1" id="relevance">关联便利店</span>
			<div class="fr">
				定格名称：<b>${spGroupVo.name}</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				定格编号：<b>${spGroupVo.id}</b>
			</div>
		</div>
		<div class="mt-small">
			<span class="pills">组内</span>
			<span class="pills">组外</span>
			<!-- <span class="pills">全部</span> -->
			<input type="hidden" id="status" value="${status}"/>
		</div>
		<form action="${root}/Customer/SpGroup/getStoreOutList.do" id="searchForm" method="post" class="mb-small">
			<input class="input input-search-text" type="text" name="name" value="${searchName}" placeholder="请输入商铺编码/名称/店主名/手机号进行查询" />
			<input type="hidden" name="id" value="${spGroupVo.id}">
			<input class="input input-search-button" value="搜索" type="submit" id="search"/>
		</form>
	</div>
	<div>
		<table class="table-list table-border">
			<thead class="table-thead">
			<tr class="table-header">
				<th width="50">选择</th>
				<th>商铺编码</th>
				<th>商铺全称</th>
				<th>店主</th>
				<th>手机号</th>
				<th>店面地址</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody class="table-tbody">
			<c:forEach items="${spGroupVo.storeList}" var="su">
			<tr>
				<td><input type="checkbox" name="chk_list" value="${su.id}" /></td>
				<td>${su.id}</td>
				<td>${su.name}</td>
				<td>${su.contact}</td>
				<td>${su.mobile}</td>
				<td>${su.address}</td>
				<c:if test="${status == 1}">
				<td class="bule1">
				<a href="#" class="J_removeStore">删除</a>
				<input type="hidden" value="${spGroupVo.id}"/><!-- 现在的定格id -->
				<input type="hidden" value="${su.id}"/><!--店铺id  -->
				</td>
				</c:if>
				<c:if test="${status == 2}">
				<td class="bule1"><a href="#" class="addTest">添加</a><input type="hidden" value="${su.spGroupId}"/><!-- 定格id -->
				<input type="hidden" value="${spGroupVo.id}"/><!-- 现在的定格id -->
				<input type="hidden" value="${su.id}"/><!--店铺id  -->
				</td>
				</c:if>
			</tr>
			</c:forEach>
			<tr style="background:#f0f0f0">
				<td>
					<input type="checkbox" name="chk_all" class="J_chkAll" />&nbsp;全选
				</td>
				<td>
				   <c:if test="${status==1}">
					<span class="bule1"><a href="#" id="batchDelete">批量删除</a></span>
				   </c:if>
				   <c:if test="${status==2}">
					<span class="bule1"><a href="#" id="batchAdd">批量添加</a></span>
				   </c:if>
				</td>
				<td colspan="6">&nbsp;</td>
			</tr>
			</tbody>
		</table>
		
		<script>
			$(function(){
				selectAll('.J_chkAll', 'input[name="chk_list"]');
				
				
				
				//从组内删除店铺
				$('.J_removeStore').on('click',function(){
					var spGroupId = $(this).next().val();//定格id
					var storeId = $(this).next().next().val();//店铺id
                    if(spGroupId==''||storeId==''){
                    	layer.msg('程序出错，缺少必要参数!');
                    	return;
                    }
                    $.ajax({
						type : "post",
						url : "${root}/Customer/SpGroup/removeStore.do",
						dataType : "json",
						data :{'stId':storeId},
						success : function(data) {
							if (data.success) {
								location.href = "${root}/Customer/SpGroup/toStoreList.do?id="+spGroupId;
							} else {
								if(data.url=='1'){
									var msg = data.message;
									var msgStr = '';
									for(var i = 0;i<msg.length;i++){
										msgStr +=msg[i]+" ";
									}
									
									layer.msg('店铺编号:'+msgStr+' 有已经支付，但还没有确认收货的订单,无法切换定格!',{
										time:4000
									});
								}else{
									layer.msg(data.message);
								}
							}
						},
						error : function(data) {
						}
					});
				});
				
				//从组外增加
				/*${root}/Customer/SpGroup/addStore.do?id=${spGroupVo.id}&stId=${su.id}  */
				$(".addTest").on('click',function(){
					//alert($(this).next('input').val());
					var spId = $(this).next().next().val();//定格id
				    var suid = $(this).next().next().next().val();//店铺id
				    if(spId==''||suid==''){
                    	layer.msg('程序出错，缺少必要参数!');
                    	return;
                    }
				    if($(this).next('input').val()!=""){
				    	layer.confirm('此商铺已在其它组内,是否继续添加?',{
				    		btn:['是','否']
				    	},function(){
				    		$.ajax({
								type : "post",
								url : "${root}/Customer/SpGroup/addStore.do",
								dataType : "json",
								data :{'id':spId,'stId':suid},
								success : function(data) {
									if (data.success) {
										location.href = "${root}/Customer/SpGroup/getStoreOutList.do?id="+spId;
									} else {
										if(data.url=='1'){
											var msg = data.message;
											var msgStr = '';
											for(var i = 0;i<msg.length;i++){
												msgStr +=msg[i]+" ";
											}
											
											layer.msg('店铺编号:'+msgStr+' 有已经支付，但还没有确认收货的订单,无法切换定格!',{
												time:4000
											});
										}else{
											layer.msg(data.message);
										}
									}
								},
								error : function(data) {
								}
							});
				    	},function(){
				    	});
				    }else{
				    	$.ajax({
							type : "post",
							url : "${root}/Customer/SpGroup/addStore.do",
							dataType : "json",
							data :{'id':spId,'stId':suid},
							success : function(data) {
								if (data.success) {
									location.href = "${root}/Customer/SpGroup/getStoreOutList.do?id="+spId;
								} else {
									if(data.url=='1'){
										var msg = data.message;
										var msgStr = '';
										for(var i = 0;i<msg.length;i++){
											msgStr +=msg[i]+" ";
										}
										layer.msg('店铺编号:'+msgStr+' 有已经支付，但还没有确认收货的订单,无法切换定格!',{
											time:4000
										});
									}else{
										layer.msg(data.message);
									}
								}
							},
							error : function(data) {
							}
						});
				    }
				});
				
				//批量添加功能
				$("#batchAdd").click(function(e){
					e.preventDefault();
					var chkList="";	
					if($("input[name='chk_list']").size()==0){
						return ;
	                }else{
	                	$("input[name='chk_list']").each(function(){
                            if(true == $(this).prop("checked")){
                            	chkList = chkList + $(this).attr("value")+",";
                            }		
    					});
	                	if(chkList==""){
    						layer.msg('请选择要添加的商铺');
    						return;
    					}
	                	$.ajax({
    						type : "post",
    						url : "${root}/Customer/SpGroup/batchAddStore.do?id=${spGroupVo.id}",
    						dataType : "json",
    						data :{chkList:chkList},
    						success : function(data) {
    							if(data.success){
    								location.href = "${root}/Customer/SpGroup/getStoreOutList.do?id="+data.message;
    							}else{
    								if(data.url=='1'){
    									var msg = data.message;
    									var msgStr = '';
    									for(var i = 0;i<msg.length;i++){
    										msgStr +=msg[i]+" ";
    									}
    									layer.msg('店铺编号:'+msgStr+' 有已经支付，但还没有确认收货的订单,无法切换定格!',{
    										time:4000
    									});
    								}else{
    									layer.msg(data.message);
    								}
    							}
    						},
    						error : function(data) {
    						}
    					}); 
	                }
				});
				
				
				//批量删除功能 
			    $("#batchDelete").click(function(e){
					e.preventDefault();
                    var chkList="";					
					//如果列表没有值,点击按钮不反应
                    if($("input[name='chk_list']").size()==0){
					return ;
                    }else{
                    	//如果列表有值,但没选择复选框,提示选择
    					$("input[name='chk_list']").each(function(){
                            if(true == $(this).prop("checked")){
                            	chkList = chkList + $(this).attr("value")+",";
                            }		
    					});
    					if(chkList==""){
    						layer.msg('请选择要删除的店铺');
    						return;
    					}
    					$.ajax({
    						type : "post",
    						url : "${root}/Customer/SpGroup/batchRemoveStore.do?id=${spGroupVo.id}",
    						dataType : "json",
    						data :{chkList:chkList},
    						success : function(data) {
    							if (data.success) {
    								location.href = "${root}/Customer/SpGroup/toStoreList.do?id="+data.message;
    							} else {
    								if(data.url=='1'){
    									var msg = data.message;
    									var msgStr = '';
    									for(var i = 0;i<msg.length;i++){
    										msgStr +=msg[i]+" ";
    									}
    									layer.msg('店铺编号:'+msgStr+' 有已经支付，但还没有确认收货的订单,无法切换定格!',{
    										time:4000
    									});
    								}else{
    									layer.msg(data.message);
    								}
    							}
    						},
    						error : function(data) {
    						}
    					}); 
                    }
				});
				
				//搜索
				$("#search").click(function(e){
					e.preventDefault();
					if($("#status").val()==1){
						$("#searchForm").attr("action","${root}/Customer/SpGroup/toStoreList.do?pageIndex=1");
					}else if($("#status").val()==2){
						$("#searchForm").attr("action","${root}/Customer/SpGroup/getStoreOutList.do?pageIndex=1");
					}
					$("#searchForm").submit();
				});
				
				
				$(".pills").click(function(){
					if($(this).html()=="组内"){
						location.href="${root}/Customer/SpGroup/toStoreList.do?pageIndex=1&id=${spGroupVo.id}";
					}
					if($(this).html()=="组外"){
						location.href="${root}/Customer/SpGroup/getStoreOutList.do?pageIndex=1&id=${spGroupVo.id}";
					}
				});
				
				//前台样式显示控制
				if($("#status").val()==1){
					$(".pills").each(function(){
						if($(this).html()=="组内"){
	                    $(this).addClass("pills-active");						
						}
					 });					
				}else if($("#status").val()==2){
					$(".pills").each(function(){
						if($(this).html()=="组外"){
	                    $(this).addClass("pills-active");						
						}
					 });
				}
				
			});
		</script>
	</div>
	<c:if test="${fn:length(spGroupVo.storeList)>0}">
		  <%@ include file="../common/pagination-kk.jsp"%>
    </c:if>
	<script>
		function go() {
			location.href = "/CornerV2/SpGroup/toSupplierList.do?pageIndex=" + $("#kkpager_btn_go_input").val().trim();
		};
	</script>
</div>
</body>
</html>