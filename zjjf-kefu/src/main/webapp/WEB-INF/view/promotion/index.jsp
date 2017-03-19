<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>满减促销管理</title>
	<%@ include file="../common/head.jsp"%>
</head>
<body>
<div class="wrap-bd">
    <div class="mb-small clearfix">
        <a href="${root}/Customer/proManage/toAuth.do?st=1"><button class="button button-default fr">新建满减</button></a>
    </div>
    <table class="table-list table-border"><!-- 按addTime 降序排序  -->
        <thead class="table-thead">
        <tr>
            <th>编号</th>
            <th>名称</th>
            <th>添加时间</th>
            <th>管理操作</th>
        </tr>
        </thead>
        <tbody class="table-tbody">
        <c:forEach var="plantRebate" items="${plantRebateList}">
        <tr>
            <td>${plantRebate.id}</td>
            <td>${plantRebate.rebatename}</td>
            <td><fmt:formatDate value="${plantRebate.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td>
            	<input type="hidden" value="${plantRebate.id}">
                <span class="button button-operate J_edit">编辑</span>
                <c:if test="${plantRebate.status==1}">
                <span class="button button-operate J_status">关闭</span>
                </c:if>
                <c:if test="${plantRebate.status==0}">
                <span class="button button-operate J_status">开启</span>
                </c:if>
            </td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
    <script>
        $(function () {
        	//编辑按钮
            $('.J_edit').on('click', function(){
                location.href = "${root}/Customer/proManage/toAuth.do?id="+$(this).prev().val()+"&st=2";
            });
            
            
            //开启or关闭按钮
            $('.J_status').on('click', function() {
                if($(this).text()=="开启"){
                	if(confirm("确定开启活动?")){
                		$.ajax({
        	    			type : "POST",
        	    			url : "${root}/Customer/proManage/startOrStopPromotion.do",
        	    			async : false,
        	    			data : {"id":$(this).prev().prev().val(),
        	    				    "status":"1"
        	    			       },
        	    			success : function(da) {
        	    				if (da.success) {
        	    				  alert("开启活动成功!");
        	    				  location.href="${root}/Customer/proManage/toIdex.do";
        	    				}else{
        	    					alert(da.message);
        	    				}
        	    			},
        	    			error : function(da) {
        	    			}
        	    		});
                	}
                }else{
                	if(confirm("确定结束活动?")){
                		$.ajax({
        	    			type : "POST",
        	    			url : "${root}/Customer/proManage/startOrStopPromotion.do",
        	    			async : false,
        	    			data : {"id":$(this).prev().prev().val(),
        	    				    "status":"0"
        	    			       },
        	    			success : function(da) {
        	    				if (da.success) {
        	    				  alert("结束活动成功!");	
        	    				  location.href="${root}/Customer/proManage/toIdex.do";
        	    				}else{
        	    					alert(da.message);
        	    				}
        	    			},
        	    			error : function(da) {
        	    			}
        	    		});
                	}
                }
            });
        })
    </script>
</div>
</body>
</html>