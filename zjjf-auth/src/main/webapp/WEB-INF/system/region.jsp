<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>地区管理</title>
<%@ include file="../common/head.jsp"%>
</head>
<body class="wrap-bd">
<div class="mt-small mb-default clearfix">
    <form class="fl" id="findService" action="${root }/auth/region/getRegionListPage.do">
    	<label>地区层级：</label>
        <select class="select" name="regionLevel">
        	<option value="" <c:if test="${regionMgRo.regionLevel == null || regionMgRo.regionLevel == ''}">selected="selected"</c:if>>全部</option>
            <option value="1" <c:if test="${regionMgRo.regionLevel == 1}">selected="selected"</c:if>>国</option>
            <option value="2" <c:if test="${regionMgRo.regionLevel == 2}">selected="selected"</c:if>>省</option>
            <option value="3" <c:if test="${regionMgRo.regionLevel == 3}">selected="selected"</c:if>>市</option>
            <option value="4" <c:if test="${regionMgRo.regionLevel == 4}">selected="selected"</c:if>>区(县)</option>
        </select>
        <input class="input input-search-text" type="text" name="name" value="${regionMgRo.name }" placeholder="地区名称/父名称" maxlength="16">
        <input type="hidden" name="status" id="status" value="${regionMgRo.status }" >
        <input class="input input-search-button" value="搜索" type="submit">
    </form>
</div>
<div id="J_pills">
    <span class="pills <c:if test="${regionMgRo.status == null}">pills-active</c:if>" data-val='' onclick="submitFind('')">全部</span>
    <span class="pills <c:if test="${regionMgRo.status == 1}">pills-active</c:if>" data-val='1' onclick="submitFind('1')">启用地区</span>
    <span class="pills <c:if test="${regionMgRo.status == 0}">pills-active</c:if>" data-val='0' onclick="submitFind('0')">停用地区</span>
</div>
<table class="table-list table-border">
    <thead class="table-thead">
    <tr>
        <th>编号</th>
        <th>地区</th>
        <th>父地区</th>
        <th>状态</th>
        <th>层级</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody class="table-tbody">
	    <c:forEach var="region" items="${regionList }">
	        <tr>
	            <td>${region.id}</td>
	            <td>${region.name}</td>
	            <td>${region.pName}</td>
	            <td>
					<c:choose>
	            		<c:when test="${region.status == 0 }"><span class="txt-warn">停用</span></c:when>
	            		<c:when test="${region.status == 1 }"><span class="txt-success">启用</span></c:when>
	            	</c:choose>
				</td>
	            <td>
	            	<c:choose>
	            		<c:when test="${region.regionLevel == 1 }">国</c:when>
	            		<c:when test="${region.regionLevel == 2 }">省</c:when>
	            		<c:when test="${region.regionLevel == 3 }">市</c:when>
	            		<c:when test="${region.regionLevel == 4 }">区/县</c:when>
	            	</c:choose>
	            </td>
	            <td>
	            	<c:choose>
	            		<c:when test="${region.isDelete == true }"><font color="red">已删除</font></c:when>
	            		<c:otherwise>
			                <c:if test="${region.status==1 }">
			                	<input type="button" value="停用" class="button button-operate eideStatus" date-id="${region.id}" date-status ="0">
			                </c:if>
			                <c:if test="${region.status==0 }">
			                	<input type="button" value="启用" class="button button-operate eideStatus" date-id="${region.id}" date-status ="1">
			                </c:if>
	            		</c:otherwise>
	            	</c:choose>
	            </td>
	        </tr>
	    </c:forEach>
    </tbody>
</table>
<c:if test="${fn:length(regionList)>0}">
	<%@ include file="../common/pagination-kk.jsp"%>
</c:if>
<script>
	var url = '${root }/auth/region/updateRegion.do';
	$(function(){
		$('.eideStatus').on('click' , function(){
			var id = $(this).attr('date-id');
			var status = $(this).attr('date-status');
			$.ajax({
	 			type : "POST",
	 			url : url,
	 			dataType : "json",
	 			data:{id:id , status:status},
	 			success : function(data) {
	 				if (data.success) {
	 					alert(data.message);
	 					location.reload();
	   				}else{
	   					alert(data.message);
	   				}
	 			},
	 			error : function() {
	 				alert("请求失败！");
	   			}
	 		});
		});
		$('.select').on('change' , function(){
			$('#findService').submit();
		});
		$('.pills').on('click' , function(){
			$('input[name="status"]').val($(this).attr('data-val'));
			$("#findService").submit();
		});
	});
</script>
</body>
</html>