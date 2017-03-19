<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>优惠券详情发布对象</title>
    <%@ include file="../common/head.jsp"%>
</head>
<body>
<div>
    <table class="table-list">
        <thead class="table-thead">
        <tr>
            <th colspan="8">
                <div class="fl ml-default">
                    <input type="checkbox" name="coupon" id="J_selectAll">&nbsp;全选
                    <span class="button-operate ml-default" id="J_delSelect">移除</span>                    
                    <!-- 指定用户才有以下按钮 -->
                    <a class="button-operate ml-small" href="${root}/Customer/voucher/toDetailAdd.do?tagId=${tagId}">添加</a>
                    <form style="display: inline-block" action="${root}/Customer/voucher/readExcel.do" name="Form" id="Form" method="post" enctype="multipart/form-data">
	                    <span class="button-operate ml-default" onclick="save();">Excel导入</span>
	                    <input type="file" id="excel" name="excel" onchange="fileType(this)" />
                    </form>
                </div>
                <form action="#" method="post" class="fr">
                    <input class="input-search-text" type="text" name="name" value="${keyStr}" placeholder="请输入商铺编号/名称/店主姓名/手机号" />
                    <input class="input-search-button" value="搜索" type="submit" />
                </form>
            </th>
        </tr>
        <tr>
            <th>&nbsp;</th>
            <th>商铺编号</th>
            <th>商铺名称</th>
            <th>店主姓名</th>
            <th>手机号</th>
            <th>所属定格</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody class="table-tbody">
        <c:forEach var="storeVo" items="${list}">
        <tr>
            <td><input class="ml-small J_checkbox" type="checkbox" name="storeId" value="<%-- ${storeVo.storeId} --%>"></td>
            <td>${storeVo.suppliercode}</td>
            <td>${storeVo.name}</td>
            <td>${storeVo.contact}</td>
            <td>${storeVo.mobile}</td>
            <td>${storeVo.spGropName}</td>
            <td>
                <a href="${root}/Customer/voucher/removeStore.do?id=${storeVo.id}&tagId=${tagId}">移除</a>
            </td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${fn:length(list)>0}">
		<%@ include file="../common/pagination-kk.jsp"%>
	</c:if>
    <script>
  //保存
	function save(){
		if($("#excel").val()=="" || document.getElementById("excel").files[0] =='请选择xls格式的文件'){
			alert("请选择文件");
			return false;
		}
		$("#Form").submit();
	}
    
    
    //校验上传文件格式
    function fileType(obj){
		var fileType=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();//获得文件后缀名
	    if(fileType != '.xls'){
	    	alert("请上传xls格式的文件");
	    	$("#excel").val('');
	    	//document.getElementById("excel").files[0] = '请选择xls格式的文件';
	    }
	}
        $(function () {
            $('#J_selectAll').on('click', function() {
                $('.J_checkbox').prop('checked', $(this).prop('checked'));
            });
            $('.J_checkbox').on('click', function() {
                if($('.J_checkbox').length == $('.J_checkbox:checked').length) {
                    $('#J_selectAll').prop('checked', 'checked');
                } else {
                    $('#J_selectAll').prop('checked', '');
                }
            });
            $('#J_delSelect').on('click', function() {
                console.log($('.J_checkbox:checked'));
            });
            
            /* $('.J_del').on('click', function() {
            	if(window.confirm("确定移除?")){
            		 $.ajax({
    	    			type : "POST",
    	    			url : "${root}/Customer/SpVoucherController/removeStore.do",
    	    			async : false,
    	    			data : {"vouId":$("#vouId").val(),
    	    				    "id":$(this).prev().val()
    	    			       },
    	    			success : function(da) {
    	    				alert(da.message);
    	    				if(da.success){
    	    					//location.href="${root}/Customer/SpVoucherController/toSpVoucherObject.do?pageIndex="+$("#currPageIndex").val()+"&vouId="+$("#vouId").val()+"&name=${keyStr}";
    	    				}
    	    			},
    	    			error : function(da) {
    	    				alert("请求失败!");
    	    			}
    	    		}); 
                }
            }); */
        })
    </script>
</div>
</body>
</html>