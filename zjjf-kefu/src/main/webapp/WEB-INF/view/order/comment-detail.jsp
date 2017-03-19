<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta charset="UTF-8">
	<title>订单评论详情</title>
	<%@ include file="../common/head.jsp"%>
</head>
<body class="wrap-bd">
<div class="mb-default">
    <a class="crumb" href="${root}/Customer/SpComment/getAllCommentByParameter.do">订单评论</a>
    <a class="crumb crumb-active">评论详情</a>
</div>
<div class="wrap-bd bg">
	<p>
	    <label class="label">订单编号：</label>
	    ${comment.orderNo2 }
	    <a class="ml-default mr-default" href="${root}/Customer/SpOrderInfo/getSpOrderInfo.do?orderid=${comment.orderNo2}" target="_blank">订单详情</a>
	
	    <label class="ml-default">评论人：</label>
	    ${comment.storeNm }
	
	    <label class="ml-default">批发商名称：</label>
	    ${comment.spNm }
	</p>
	<p>
	    <label class="label">综合评分：</label>
	   	<c:if test="${comment.unionFen==1 }">
	   		<img src="${root}/resources/images/star.png" alt="*">
	   	</c:if>
	   	<c:if test="${comment.unionFen==2 }">
	   		<img src="${root}/resources/images/star.png" alt="*">
	   		<img src="${root}/resources/images/star.png" alt="*">
	   	</c:if>
	   	<c:if test="${comment.unionFen==3 }">
	   		<img src="${root}/resources/images/star.png" alt="*">
	   		<img src="${root}/resources/images/star.png" alt="*">
	   		<img src="${root}/resources/images/star.png" alt="*">
	   	</c:if>
	   	<c:if test="${comment.unionFen==4 }">
	   		<img src="${root}/resources/images/star.png" alt="*">
	   		<img src="${root}/resources/images/star.png" alt="*">
	   		<img src="${root}/resources/images/star.png" alt="*">
	   		<img src="${root}/resources/images/star.png" alt="*">
	   	</c:if>
	   	<c:if test="${comment.unionFen==5 }">
	   		<img src="${root}/resources/images/star.png" alt="*">
	   		<img src="${root}/resources/images/star.png" alt="*">
	   		<img src="${root}/resources/images/star.png" alt="*">
	   		<img src="${root}/resources/images/star.png" alt="*">
	   		<img src="${root}/resources/images/star.png" alt="*">
	   	</c:if>
	</p>
	<div class="mb-default clearfix">
		<label class="fl label">评论标签：</label>
	    <div class="fl" style="width:800px">
		    <button class="button button-white">服务很热情</button>
		    <button class="button button-white">缺货/货品不全</button>
		    <button class="button button-white">送货速度很快</button>
		    <button class="button button-white">软件操作麻烦</button>
		    <button class="button button-white">价格很优惠</button>
		    <button class="button button-white">微信支付不能退款</button><br>
	    </div>
	</div>
	<div class="clearfix">
	    <label class="fl label">评论内容：</label>
	    <div class="fl" style="width:800px">
		    ${comment.info }
	    </div>
	</div>
	<p>
	    <label class="label">评论时间：</label>
	    <fmt:formatDate value="${comment.addTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
	    
	</p>
	<p>
	    <label class="label">处理状态：</label>
	   	<c:if test="${comment.csDealstat == 0 }">
	   		<input type="radio" name="status" id="csDealstat0" value="0" checked="checked"> 未处理
	   		<input class="ml-default" type="radio" name="status" id="csDealstat2" value="2"> 已处理
	   	</c:if>
	     <c:if test="${comment.csDealstat == 2 }">
	     	<input type="radio" name="status" id="csDealstat0" value="0"> 未处理
	    	<input class="ml-default" type="radio" name="status" id="csDealstat2" value="2" checked="checked"> 已处理
	    </c:if>
	</p>
	<p>
	    <label class="label">客服回复：</label>
	    <textarea class="textarea" name="csDealInfo" id="csDealInfo" cols="60" rows="4">${comment.csDealInfo}</textarea>
	</p>
	<p>
	    <label class="label"></label>
	    <input type="hidden" id="csId" value="${service.id }" />
	    <input type="hidden" id="csNm" value="${service.username }" />
	    <button class="button button-ok" onclick="saveComment('${id}')">保存</button>
	    <button class="button button-cancel" onclick="quxiao()">取消</button>
	</p>
</div>
<script>
    function saveComment(id){
        var csDealstat = $('input[name="status"]:checked').val();
        var csDealInfo = $("#csDealInfo").val();
        var csId=$("#csId").val();
        var csNm=$("#csNm").val();
        $.post("${root}/Customer/SpComment/updateCommentById.do",
                {id:id,
            csDealstat:csDealstat,
            csDealInfo:csDealInfo,
            csId:csId,
            csNm:csNm},
            function(date){
                if(date.success){
                    alert(date.message);
                    location.href = "${root}/Customer/SpComment/getAllCommentByParameter.do?sort=addTime&order=desc"
                }else{
                    alert(date.message);
                }
            },'json')
    }
    function quxiao(){
        location.href = "${root}/Customer/SpComment/getAllCommentByParameter.do?sort=addTime&order=desc"
    }
    
</script>
</body>
</html>