<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>商品库 - 商品品牌列表</title>
    <%@ include file="../common/head.jsp"%>
    <script src="${root}/resources/js/comm.js"></script>
    <%@ include file="../common/autocomplete.jsp"%>
    <script src="${root}/resources/vendor/jquery/ajaxfileupload.js"></script>
</head>
<body class="wrap-bd">
<div class="mb-small clearfix">
    <form class="fl">
        <input class="input input-search-text" type="text" name="noAndName" value="${brandRo.noAndName }" placeholder="品牌编号/品牌名称">
        <input class="input input-search-button" value="搜索" type="submit">
    </form>
    <div class="fr"><button class="button button-default" id="J_addAd">新增品牌</button></div>
</div>
<table class="table-list">
    <thead>
        <tr>
        	<th>ID</th>
            <th>排序</th>
            <th>品牌编号</th>
            <th>品牌名称</th>
            <th>品牌商</th>
            <th>修改时间</th>
            <th>状态</th>
            <th width="120">操作</th>
        </tr>
    </thead>
    <tbody class="table-tbody">
    <c:forEach var="brand" items="${brandList }">
        <tr>
        	<td>${brand.id }</td>
            <td>${brand.ordId }</td>
            <td>${brand.brandNo }</td>
            <td>${brand.name }</td>
            <td>${brand.s_name }</td>
            <td><fmt:formatDate value="${brand.updateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <c:if test="${brand.status==0 }">
            	 <td class="txt-warn">已下架</td>
            </c:if>
            <c:if test="${brand.status==1 }">
            	 <td class="txt-success">已上架</td>
            </c:if>
            <td>
               
                <c:if test="${brand.isDelete==false }">
                 	<span class="button button-operate J_edit">编辑<input type="hidden" name="h_id" value="${brand.id }"></span>
                	<span class="button button-operate J_delete">&nbsp;删除&nbsp;<input type="hidden" name="h_id" value="${brand.id }"></span>
                </c:if>
                <c:if test="${brand.isDelete==true }">
                 	<span class="button button-operate J_edit">编辑</span>
                	<span class="button button-operate disabled">已删除</span>
                </c:if>
            </td>
        </tr>
    </c:forEach>    
    </tbody>
</table>
<c:if test="${fn:length(brandList)>0}">
	<%@ include file="../common/pagination-kk.jsp"%>
</c:if>
<script>
    $(function () {
    	var pageIndex = ${page.pageIndex};
    	
        $('#J_addAd').on('click', function() {
            location.href="${root}/customer/brand/returnBrandAddOrEditPage.do?action=1&pageIndex="+pageIndex;
        });
        $('.J_edit').on('click', function(){
            var id = $(this).find('input[name=h_id]').val();
            console.log(id)
            if(id == null || id == ""){
            	alert("请求有误");
            	 return;
            }
            location.href="${root}/customer/brand/returnBrandAddOrEditPage.do?action=2&pageIndex="+pageIndex+"&id="+id;
        });
     
        $('.J_delete').on('click', function() {
        	if(confirm("确定删除？")){
        		 var id = $(this).find('input[name=h_id]').val();
        		 $.post("${root}/customer/brand/deleteBrand.do",{id:id},function(data){
        			 if(data.success){
        				 alert(data.message)
        				 location.href="${root}/customer/brand/getAllBrandByParam.do";
        			 }else{
        				 alert(data.message)
        			 }
        		 },'json');
        	}
        });
    });
</script>
</body>
</html>