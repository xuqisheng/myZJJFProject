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
    <script src="../../resources/js/comm.js"></script>
</head>
<body>
<div class="wrap-bd">
    <div class="mb-small clearfix">
        <div class="fl">
            <input type="checkbox" id="J_selectAll"> 全选
            <input type="button" value="删除" class="button button-white ml-small" id="batchDelete">
            <input type="button" value="新增用户" class="button button-default ml-default" id="addStore">
            <input type="button" value="用户导入" class="button button-default" id="J_importBtn">
        </div>
        <form class="fr" id="searchForm" method="post" action="${root}/Customer/store/getSpAcGroupWithStoreList.do">
            <input class="input input-search-text" type="text" name="keyStr" value="${keyStr}" placeholder="店铺名称/手机号/定格名称">
            <input class="input input-search-button" value="搜索" type="submit">
            <input type="hidden" name="id" value="${id}">
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
            <c:forEach items="${list}" var="item">
            <tr>
                <td>
                    <input type="checkbox" class="J_checkbox" value="${item.id}">
                </td>
                <td>${item.suppliercode}</td>
                <td>${item.name}</td>
                <td>${item.contact}</td>
                <td>${item.mobile}</td>
                <td>${item.spGropName}</td>
                <td>
                    <input type="hidden" value="${item.id}">
                    <input class="button button-operate J_deleteStore" type="button" value="删除">
                </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
        <input type="hidden" name="spAcGroupId" value="${id}" id="spAcGroupId">
    </div>
    <c:if test="${fn:length(list)>0}">
		<%@ include file="../common/pagination-kk.jsp"%>
	</c:if>
</div>
<div class="dialog hidden" id="J_importExcel">
    <div class="dialog-body">
        <form action="${root}/Customer/store/readExcel.do" name="excelForm" id="excelForm" method="post" enctype="multipart/form-data">
            <p>
                <label>选择文件：</label>
                <input class="input input-default" type="file" value="" name="excel" id="excel" onchange="fileType(this)">
            </p>
            <div class="dialog-foot">
                <button type="button" class="dialog-button dialog-ok" id="test">确定</button>
                <button type="button" class="dialog-button dialog-cancel">取消</button>
            </div>
            <input type="hidden" name="spAcGroupId" value="${id}">
        </form>
    </div>
</div>
<div class="cover-all"></div>
<script>
    $(function() {
    	selectAll('#J_selectAll', '.J_checkbox');
        dialogPosCenter('#J_importExcel');
        var $importExcel = $('#J_importExcel');
        // 用户导入
        $('#J_importBtn').on('click', function() {
            $(".cover-all").show();
            $importExcel.show();
        });
        // 确定
        $importExcel.find('.dialog-ok').on('click', function() {
        	$('#excelForm').submit();
            $importExcel.hide();
            $(".cover-all").hide();
        });
        // 取消
        $importExcel.find('.dialog-cancel').on('click', function() {
            $importExcel.hide();
            $(".cover-all").hide();
        });
        
        var id = '${id}';
        //新增用户
        $('#addStore').on('click',function(){
        	location.href="${root}/Customer/store/getStoreList.do?id="+id;
        });
        
        
        //单个删除
        $('.J_deleteStore').on('click',function(){
        	var storeIdStr = $(this).prev().val();
        	$.ajax({
    			type : "POST",
    			url : "${root}/Customer/store/deleteStoreFromSpACGroup.do",
    			async : false,
    			data : {'spAcGroupId':$('#spAcGroupId').val(),'storeIdStr':storeIdStr},
    			success : function(da) {
    				if (da.success) {
    					alert("删除用户成功!");
    		            location.href='${root}/Customer/store/getSpAcGroupWithStoreList.do?id='+$('#spAcGroupId').val();
    				}else{
    					alert(da.message);
    				}
    			},
    			error : function(da) {
    			}
    		});
        });
        
        //批量删除
        $('#batchDelete').on('click',function(){
        	var storeIdStr = '';
        	$('.J_checkbox:checked').each(function(){
        	  storeIdStr+=$(this).val()+",";
        	});
        	storeIdStr = storeIdStr.substring(0,storeIdStr.lastIndexOf(",")); 
        	if(storeIdStr.length==0){
        		alert('请选择要删除用户!')
        		return;
        	}
        	$.ajax({
    			type : "POST",
    			url : "${root}/Customer/store/deleteStoreFromSpACGroup.do",
    			async : false,
    			data : {'spAcGroupId':$('#spAcGroupId').val(),'storeIdStr':storeIdStr},
    			success : function(da) {
    				if (da.success) {
    					alert("删除用户成功!");
    		            location.href='${root}/Customer/store/getSpAcGroupWithStoreList.do?id='+$('#spAcGroupId').val();
    				}else{
    					alert(da.message);
    				}
    			},
    			error : function(da) {
    			}
    		});
        });
    });
    function fileType(obj){
		var fileType=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();//获得文件后缀名
	    if(fileType != '.xls'){
	    	alert('请使用03版本的excel导入,03版本的excel后缀名为.xls');
	    	$("#excel").val('');
	    }
	}
</script>
</body>
</html>