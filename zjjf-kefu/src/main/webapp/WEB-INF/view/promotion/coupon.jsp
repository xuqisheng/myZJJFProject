<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>优惠券列表</title>
	<%@ include file="../common/head.jsp"%>
	
    <script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
    <script src="${root}/resources/js/comm.js"></script>
</head>
<body>
<div class="wrap-bd">
    <div class="mt-small mb-default clearfix">
        <form action="${root}/Customer/voucher/toIndex.do"  method="post" class="fl">
        
                              生成时间：
            <input type="text" name="startDate" value="${startDateStr}" class="input input-date J_timeS"> -
            <input type="text" name="endDate" value="${endDateStr}" class="input input-date mr-default J_timeE">
                              面值：
            <input type="text" name="useMoneyStr" value="${useMoneyStr}" class="input input-search-text mr-default">
                             名称：
            <input type="text" name="ruleName" value="${keyStr}"  class="input input-search-text">
            <input type="submit" value="搜索" class="input input-search-button">
        </form>
        <div class="fr">
            <a class="button button-default" href="${root}/Customer/voucher/toCouponAdd.do?pageIndex=${pageIndex}">添加优惠券</a>
        </div>
    </div>
    <table class="table-list table-border">
        <thead class="table-thead">
        <tr>
            <th>编号</th>
            <th>优惠券名称</th>
            <th>面值</th>
            <th>发放数</th>
            <th>已使用</th>
            <th>有效期</th>
            <th>使用金额限制</th>
            <th>生成时间</th>
            <th width="130">备注</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody class="table-tbody">
        <c:forEach var="spVoucherRule" items="${list}" varStatus="status">
        <tr>
            <input type="hidden" value="${spVoucherRule.id}"><!--${spVoucherRule.id}  -->
            <td>${spVoucherRule.id}</td>
            <td>${spVoucherRule.ruleName}</td>
            <td>￥${spVoucherRule.useMoney}</td>
            <td>
            <a href="${root}/Customer/voucher/toSpVoucherSendWho.do?id=${spVoucherRule.id}" class="J_detail">${spVoucherRule.grantCount}</a>
            </td>
            <td>
            <a href="${root}/Customer/voucher/getSpVoucherUsedLog.do?id=${spVoucherRule.id}" class="J_used">${spVoucherRule.usedCount}</a>
            </td>
            <td>${spVoucherRule.useDay}</td>
            <td>
	            <c:choose>
		            <c:when test="${spVoucherRule.startPrice=='0.00'}">
		            </c:when>
		            <c:otherwise>
		              ${spVoucherRule.startPrice}
		            </c:otherwise>
	            </c:choose>
            </td>
            <td><fmt:formatDate value="${spVoucherRule.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td>${spVoucherRule.remark}</td>
            <td>
                <a href="${root}/Customer/voucher/toSpVoucherObject.do?id=${spVoucherRule.id}&pageIndex=${pageIndex}">发送</a>
                
                <a href="${root}/Customer/voucher/toEditspVoucherRule.do?id=${spVoucherRule.id}&pageIndex=${pageIndex}">编辑</a>
                
                <a href="${root}/Customer/voucher/deletSpVoucher.do?id=${spVoucherRule.id}&pageIndex=${pageIndex}" onclick="if(confirm('确定删除?')==false)return false;">删除</a>
            </td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
    <input type="hidden" id="currPageIndex" value="${pageIndex}">
    <c:if test="${fn:length(list)>0}">
		<%@ include file="../common/pagination-kk.jsp"%>
	</c:if>
    <script>
        $(function () {
        	dateRange('.J_timeS', '.J_timeE', 1);
            //删除按钮
            $('.J_del').on('click', function() {
                if(window.confirm("确定删除？")){
                	 $.ajax({
     	    			type : "POST",
     	    			url : "${root}/Customer/SpVoucherController/deletspVoucherRule.do",
     	    			async : false,
     	    			data : {"id":$(this).parent().parent().children('input').val()
     	    			       },
     	    			success : function(da) {
     	    				alert(da.message);
     	    			if(da.success){
     	    					location.href="${root}/Customer/SpVoucherController/toIndex.do?pageIndex="+$("#currPageIndex").val()+"&name=${keyStr}";
     	    				} 
     	    			},
     	    			error : function(da) {
     	    				alert("请求失败!");
     	    			}
     	    		});
                }
            });
        })
    </script>
</div>
</body>
</html>