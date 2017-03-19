<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>广告管理</title>
<%@ include file="../common/head.jsp"%>
<%@ include file="../common/autocomplete.jsp"%>
<script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
<script src="${root}/resources/js/comm.js"></script>
</head>
<body>
	<div class="wrap-bd">
		<div class="mb-default">
			<a class="crumb" href="${root}/Customer/AdManage/toIdex.do">广告管理</a><a
				class="crumb crumb-active">广告列表</a>
		</div>
		<div class="mb-small clearfix">
		<form action="">
			<label class="ml-default">广告名称：</label>
			<input type="text" id="name" name="name" value="${name }" placeholder="请输入广告名称" class="input input-default">
			<label class="ml-default">有效时间：</label>
			<input type="text" id="startTime" name="startTime" value="<fmt:formatDate value="${startTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" class="input input-default J_TIME_START">&nbsp;至&nbsp;
			<input type="text" id="endTime" name="endTime" value="<fmt:formatDate value="${endTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" class="input input-default J_TIME_END">
			<label class="ml-default">广告位：</label>
			<select name="boardId">
			<option value="">全部</option>
			<c:forEach items="${adboards}" var="item">
			  <option value="${item.id}" <c:if test="${boardId==item.id}">selected</c:if>>${item.name}</option>
			</c:forEach>
			</select>
			<input type="submit" value="查询" class="button button-default ml-small">
		</form>
		<a href="${root}/Customer/AdManage/toEditAdvertisement/A.do"><button class="fr button button-default">添加广告</button></a>
		</div>
		<table class="table-list table-border">
			<!-- 按广告添加时间倒叙排序 -->
			<thead class="table-thead">
				<tr>
					<th>ID</th>
					<th>广告名称</th>
					<th>排序</th>
					<th>广告位</th>
					<th>有效期</th>
					<th>发布区域</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody class="table-tbody">
			<c:choose>
				<c:when test="${list != null && list.size() >0}">
					<c:forEach var="ad" items="${list}" varStatus="sta">
						<tr>
							<td>${ad.id}</td>
							<td>${ad.name}</td>
							<td>${ad.ordId}</td>
							<td>${ad.adboardName}</td>
							<td><fmt:formatDate value="${ad.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;至&nbsp;<fmt:formatDate value="${ad.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<%-- <td><fmt:formatDate value="${ad.addTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td> --%>
							<td>
								<c:if test="${ad.asDefault}"><span style="color:red;">全部定格</span></c:if>
								<c:if test="${!ad.asDefault}">指定定格</c:if>
							</td>
							<td>${ad.staName}</td>
							<td>
								<a href="${root}/Customer/AdManage/toEditAdvertisement/Q.do?id=${ad.id}">查看</a>
								<c:choose>
									<c:when test="${ad.isDelete}">
										<span class="button button-operate txt-warn">已删除</span>
										<span class="button button-operate">编辑</span> 
									</c:when>
									<c:otherwise>
										<span class="button button-operate J_delete">删除<input type="hidden" class="adId1" value="${ad.id}"></span>
										<span class="button button-operate J_edit">编辑<input type="hidden" class="adId" value="${ad.id}"></span>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr><td colspan="8">暂无数据</td></tr>
				</c:otherwise>
			</c:choose>
			</tbody>
		</table>
		<c:if test="${fn:length(list)>0}">
			<%@ include file="../common/pagination-kk.jsp"%>
		</c:if>
		<script>
    //搜索广告 
    function searchAd(){
 	   location.href="${root}/Customer/AdManage/toIdex.do?spGroupId="+$("#spSelect").val()+"&spGroupName="+$("#spGroupName").val();
    }
    function allAd(){
    	$("#spSelect").val("");
    	if($("#spGroupName").val() != ""){
    		$("#spGroupName").val("");
    	}
    	searchAd();
    }
	function defultAd(){
		$("#spSelect").val("-1");
		if($("#spGroupName").val() != ""){
			$("#spGroupName").val("");
    	}
		searchAd();
    } 
   	$(function () {
   		
   		dateRange('.J_TIME_START', '.J_TIME_END');
         //回显定格列表
         /* $("#spSelect").val("${spGroupId}"); */
         
	   $('#spGroupName').autocomplete({
	          serviceUrl: '${root}/Customer/AdManage/getSpGroupBySpGroupName.do',
	          paramName: 'spGroupName',
	          transformResult: function(response) {
	        	var res = null;
	        	if(response != null){
	        		res = JSON.parse(response);
	        	}
	            if(res.message != null) {
		           	return {
		            	suggestions: $.map(res.message, function(value, key) {
		                 	return { value: value.name, data: value };
		            	})
		            };
	            } else {
	            	return {
		            	suggestions: [{ value: "无数据"}]
		            };
	            }
	          },
	          onSelect: function(dd) {
	          	if(dd.value==="无数据") {
	             		$(this).val("");
	              	return;
	          	}
				$("#spSelect").val(dd.data.id);
				searchAd();
	          }
	     });
     	//批量下架
     	 $('.p_edit').on('click', function() {
     		 var flag = false;
     		 var id = $(this).children("input[name=id1]").val()
     		 var status = $(this).children("input[name=status]").val()
     		 if(status==0){
     			 if(confirm("确定批量上架？")){
     				 status=1;
     				 flag=true;
     			 }else{
     				 return;
     			 }
     		 }else if(status==1){
     			 if(confirm("确定批量下架？")){
     				 status=0;
     				 flag=true;
     			 }else{
     				 return;
     			 }
     		 }
     		 if(flag){
     			 $.ajax({
  					type : "post",
  					url : "${root}/Customer/AdManage/batchUpdateAdstatus.do?id="+id+"&status="+status,
  					dataType : "json",
  					success : function(data) {
  						if (data.success) {
  							alert(data.message);
  							window.location.reload();
  						} else {
  							alert(data.message);
  						}
  					},
  					error : function(data) {
  					}
  				}); 
     		 }
          });
     	//批量删除
     	 $('.p_delete').on('click', function() {
              if(confirm("确定要批量删除？")){
              	$.ajax({
				type : "post",
				url : "${root}/Customer/AdManage/batchDeleteAd.do?id="+$(this).children("input").val(),
				dataType : "json",
				success : function(data) {
					if (data.success) {
						alert(data.message);
						window.location.reload();
					} else {
						alert(data.message);
					}
				},
				error : function(data) {
				}
			});         	
              }
          });
     	
     	
     	
         $('.J_edit').on('click', function(){
             location.href = "${root}/Customer/AdManage/toEditAdvertisement/E.do?id="+$(this).find('.adId').val();
         });
         $('.J_delete').on('click', function() {
             if(confirm("确定删除？")){
             	$.ajax({
			type : "post",
			url : "${root}/Customer/AdManage/deleteAd.do?id="+$(this).children("input").val(),
			dataType : "json",
			success : function(data) {
				if (data.success) {
					alert(data.message);
					window.location.reload();
				} else {
					alert(data.message);
				}
			},
			error : function(data) {
			}
		});         	
             }
         });
         //排序
        /*  $('.J_orderid').on('click', 'span', function() {
         	$(this).hide();
         	$(this).next().show().val($(this).text()).focus().on('blur', function() {
         		var $thisspan = $(this);
         		$.ajax({
			type : "post",
			url : "${root}/Customer/AdManage/updateOrderId.do?id="+$(this).next().val(),
			dataType : "json",
			data:{ordid:$(this).val()},
			success : function(data) {
				if (data.success) {
					$thisspan.hide();
					$thisspan.siblings('span').text(data.message.ordid).show();
					window.location.reload();
				} else {
					alert(data.message);
				}
			},
			error : function(data) {
				
				$thisspan.siblings('span').text(data.message.ordid).show();
				window.location.reload();
			}
		});
             });
         }); */
     });
    </script>
	</div>
</body>
</html>