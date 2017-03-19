<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>优惠券详发给了谁</title>
    <%@ include file="../common/head.jsp"%>
</head>
<body>
<div class="wrap-bd">
<a class="fl" href="${root}/Customer/voucher/toIndex.do">返回优惠券列表</a>
    <table class="table-list table-border">
        <thead class="table-thead">
        <tr>
            <th colspan="8">
                <!-- <div class="fl ml-default">
                    <input type="checkbox" name="coupon" id="J_selectAll">&nbsp;全选
                    <span class="button button-operate ml-default" id="J_addSelect">添加</span>
                </div> -->
                
                <form action="${root}/Customer/voucher/toSpVoucherSendWho.do" method="post" class="fr" id="searchForm">
                    <input class="input input-search-text" type="text" name="keyStr" value="${keyStr}" placeholder="" />
                    <input type="hidden" name="id" value="${ruleId}">
                    <input class="input input-search-button" value="搜索" type="button" id="searchButton"/>
                </form>
            </th>
        </tr>
        <tr>
            <!-- <th>&nbsp;</th> -->
            <th>订单号</th>
            <th>商铺编号</th>
            <th>商铺名称</th>
            <th>店主姓名</th>
            <th>手机号</th>
            <th>所属定格</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody class="table-tbody" id="storeTbody">
        <c:forEach var="storeVo" items="${list}">
        <tr>
            <!-- <td><input class="ml-small J_checkbox" type="checkbox" name="coupon"></td> -->
            <td>
            <c:if test="${not empty storeVo.orderId}"></c:if>
            </td>
            <td>${storeVo.suppliercode}</td>
            <td>${storeVo.name}</td>
            <td>${storeVo.contact}</td>
            <td>${storeVo.mobile}</td>
            <td>${storeVo.spGropName}</td>
            <td>
               <input type="hidden" value="${ruleId}"><!-- 优惠劵id-->
               <input type="hidden" value="${storeVo.id}"><!--店铺id  -->
               <input type="hidden" value="${storeVo.spVoucherId}"><!--SpVoucher id  -->
               <input class="button button-operate J_del" type="button" value="删除">
            </td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
    <div>
    </div>
    <c:if test="${fn:length(list)>0}">
		<%@ include file="../common/pagination-kk.jsp"%>
	</c:if>
    <script>
        $(function () {
            $('#J_selectAll').on('click', function() {
                $('.J_checkbox').prop('checked',$(this).prop('checked'));
            });
            $('.J_checkbox').on('click', function() {
                if($('.J_checkbox').length == $('.J_checkbox:checked').length) {
                    $('#J_selectAll').prop('checked', 'checked');
                } else {
                    $('#J_selectAll').prop('checked', '');
                }
            });

            $('#J_addSelect').on('click', function() {
                console.log($('.J_checkbox:checked'));
            });
            
            //搜索按钮
            $('#searchButton').click(function(){
            	$('#searchForm').submit();
            });
            //删除按钮
            $('.J_del').on('click', function() {
                if(confirm("确定移除？")){
                	 $.ajax({
      	    			type : "POST",
      	    			url : "${root}/Customer/voucher/removeStore.do",
      	    			dataType:'json',
      	    			async : false,
      	    			data : {'ruleId':$(this).prev().prev().prev().val(),
      	    				    'id':$(this).prev().prev().val(),
      	    				    'spVoucherId':$(this).prev().val()
      	    			       },
      	    			success : function(da) {
      	    			if(da.success){
      	    				    alert('删除成功!');
      	    					location.href="${root}/Customer/voucher/toSpVoucherSendWho.do?id=${ruleId}";
      	    				}else{
      	    					alert(da.message);
      	    					location.href="${root}/Customer/voucher/toSpVoucherSendWho.do?id=${ruleId}";
      	    				} 
      	    			},
      	    			error : function(da) {
      	    				alert("请求失败!");
      	    			}
      	    		});                	
                };
            });
        })
    </script>
</div>
</body>
</html>