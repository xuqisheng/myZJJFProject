<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>街坊店宝 - 商铺用户组管理用户管理</title>
	<%@ include file="../common/head.jsp"%>
    <script src="${root}/resources/js/admin/comm.js"></script>
</head>
<body>
<div class="wrap-bd">
    <div class="mb-small clearfix">
        <div class="fl">
            <input type="checkbox" id="J_selectAll"> 全选
            <input type="button" value="添加" class="button button-white ml-small" id="J_addSelectUser">
        </div>
        <form class="fr" id="searchForm" method="post" action="${root}/Customer/store/getStoreList.do">
            <input class="input input-search-text" type="text" name="keyStr" value="${keyStr}" placeholder="店铺名称/手机号/定格名称">
            <input class="input input-search-button" value="搜索" type="submit">
             <input type="hidden" value="${id}" name="id"> 
        </form>
    </div>
    <div>
        <table class="table-list table-border">
            <thead class="table-thead">
            <tr class="table-header">
                <th></th>
                <th>商铺编号</th>
                <th>商铺名称</th>
                <th>店主名</th>
                <th>手机号</th>
                <th>定格名称</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody class="table-tbody">
            <form id="addStoreForm" action="${root}/Customer/store/addStoreIntoSpACGroup.do" method="post">
            <c:forEach items="${list}" var="item">
            <tr>
                <td>
                    <input type="checkbox" value="${item.id}" class="J_checkbox" name="storeIdStr">
                </td>
                <td>${item.suppliercode}</td>
                <td>${item.name}</td>
                <td>${item.contact}</td>
                <td>${item.mobile}</td>
                <td>${item.spGropName}</td>
                <td>
                    <input type="hidden" value="${item.acGroupId}" name="acGroupId">
                    <input type="hidden" value="${item.id}">
                    <input class="button button-operate J_addStore" type="button" value="添加">
                </td>
            </tr>
            </c:forEach>
            <input type="hidden" value="${id}" name="acSpGroupId" id= "acSpGroupId"> 
            </form>
            </tbody>
        </table>
    </div>
    <c:if test="${fn:length(list)>0}">
		<%@ include file="../common/pagination-kk.jsp"%>
	</c:if>
</div>
<script>
$(function() {
	selectAll('#J_selectAll', '.J_checkbox');
	//多个添加
	$('#J_addSelectUser').on('click',function(){
		var storeIdStr = '';
       $('.J_checkbox:checked').each(function() {
    	   storeIdStr +=$(this).val()+",";
		});
       storeIdStr = storeIdStr.substring(0,storeIdStr.lastIndexOf(","));
       if(storeIdStr.length==0){
   		alert('请选择要添加用户!')
   		return;
   	}
       $.ajax({
			type : "POST",
			url : "${root}/Customer/store/addStoreIntoSpACGroup.do",
			async : false,
			data : {'spAcGroupId':$('#acSpGroupId').val(),'storeIdStr':storeIdStr},
			success : function(da) {
				if (da.success) {
					alert("添加用户成功!");
		            location.href='${root}/Customer/store/getStoreList.do?id='+$('#acSpGroupId').val();
				}else{
					alert(da.message);
				}
			},
			error : function(da) {
			}
		});
	});
	
	//单个添加
	$('.J_addStore').on('click',function(){
		var acGroupId = $(this).prev().prev().val();
		var storeId = $(this).prev().val();
		if(acGroupId!=''){
			if(confirm('该店铺已经存在于其他用户组')){
				$.ajax({
        			type : "POST",
        			url : "${root}/Customer/store/addStoreIntoSpACGroup.do",
        			async : false,
        			data : {'spAcGroupId':$('#acSpGroupId').val(),'storeIdStr':storeId},
        			success : function(da) {
        				if (da.success) {
        					alert("添加用户成功!");
        		            location.href='${root}/Customer/store/getStoreList.do?id='+$('#acSpGroupId').val();
        				}else{
        					alert(da.message);
        				}
        			},
        			error : function(da) {
        			}
        		});
			}
		}else{
			$.ajax({
    			type : "POST",
    			url : "${root}/Customer/store/addStoreIntoSpACGroup.do",
    			async : false,
    			data : {'spAcGroupId':$('#acSpGroupId').val(),'storeIdStr':storeId},
    			success : function(da) {
    				if (da.success) {
    					alert("添加用户成功!");
    		            location.href='${root}/Customer/store/getStoreList.do?id='+$('#acSpGroupId').val();
    				}else{
    					alert(da.message);
    				}
    			},
    			error : function(da) {
    			}
    		});
		}
	});
});
</script>

</body>
</html>