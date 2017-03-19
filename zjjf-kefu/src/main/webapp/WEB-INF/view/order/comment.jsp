<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>订单评论列表</title>
<%@ include file="../common/head.jsp"%>
<script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
<script src="${root}/resources/js/comm.js"></script>
</head>
<body class="wrap-bd">
	<div class="mb-default">
	    <a class="crumb" href="#">订单管理</a>
	    <a class="crumb crumb-active">订单评论</a>
	</div>
<fieldset class="mb-small bg">
    <form action="${root}/Customer/SpComment/getAllCommentByParameter.do?sortName=addTime&sortOrder=desc" method="post">
        <p>
            <label>批发商名称：</label>
            <input type="text" name="spNm" value="${spcRo.spNm }" class="input input-search-text" placeholder="" id="spNm"/>
            <label class="ml-default">时间：</label>
          	<input type="text" name="beginTime1" value="${beginTime1 }" class="input input-date J_TIME_START" placeholder="" id="beginTime1" /> 至 
            <input type="text" name="endTime1" value="${endTime1 }" class="input input-date J_TIME_END" placeholder="" id="endTime1" /> 
            <label class="ml-default">内容：</label>
            <input type="text" name="info" value="${spcRo.info }" class="input input-search-text" placeholder="评论内容" id="info" />
	        <input value="搜索" type="submit" class="input input-search-button">
        </p>
        <p id="J_star">
            <label>综合评分：</label>
            <c:if test="${spcRo.unionFenA !=null }"><input type="checkbox" name="unionFenA" class="checkbox" value="1" checked="checked" id="unionFenA">&nbsp;1星</c:if>
            <c:if test="${spcRo.unionFenA ==null }"><input type="checkbox" name="unionFenA" class="checkbox" value="1" id="unionFenA">&nbsp;1星</c:if>
           
            <c:if test="${spcRo.unionFenB !=null }"><input type="checkbox" name="unionFenB" class="checkbox ml-default" value="2" checked="checked" id="unionFenB">&nbsp;2星</c:if>
            <c:if test="${spcRo.unionFenB ==null }"><input type="checkbox" name="unionFenB" class="checkbox ml-default" value="2" id="unionFenB">&nbsp;2星</c:if>
            
            <c:if test="${spcRo.unionFenC !=null }"><input type="checkbox" name="unionFenC" class="checkbox ml-default" value="3" checked="checked" id="unionFenC">&nbsp;3星</c:if>
            <c:if test="${spcRo.unionFenC ==null }"><input type="checkbox" name="unionFenC" class="checkbox ml-default" value="3" id="unionFenC">&nbsp;3星</c:if>
            
            <c:if test="${spcRo.unionFenD !=null }"><input type="checkbox" name="unionFenD" class="checkbox ml-default" value="4" checked="checked" id="unionFenD">&nbsp;4星</c:if>
            <c:if test="${spcRo.unionFenD ==null }"><input type="checkbox" name="unionFenD" class="checkbox ml-default" value="4" id="unionFenD">&nbsp;4星</c:if>
            
            <c:if test="${spcRo.unionFenE !=null }"><input type="checkbox" name="unionFenE" class="checkbox ml-default" value="5" checked="checked" id="unionFenE">&nbsp;5星     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
            <c:if test="${spcRo.unionFenE ==null }"><input type="checkbox" name="unionFenE" class="checkbox ml-default" value="5" id="unionFenE">&nbsp;5星     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
            <label class="ml-default">处理状态：</label>
            <select class="select" name="csDealstat" id="csDealstat">
            	<option value="">全部</option>
                <option value="0">未处理</option>
                <option value="2">已处理</option>
            </select>
        </p>
    </form>
</fieldset>
<table class="table-list table-border">
    <thead class="table-thead">
    <tr>
        <th width="130">综合评分</th>
        <th width="150">订单编号</th>
        <th width="80">评论人</th>
        <th width="100">批发商名称</th>
        <th>评论内容</th>
        <th width="80">评论时间</th>
        <th width="60">处理状态</th>
        <th width="30">操作</th>
    </tr>
    </thead>
    <tbody class="table-tbody">
    <c:forEach var="comment" items="${commentList }">
	    <tr>
	    	<td>
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
	    	</td>
	        <td>${comment.orderNo2 }</td>
	        <td>${comment.storeNm }</td>
	        <td>${comment.spNm }</td>
	        <td class="ta-l">${comment.info }</td>
	        <td>
	        	<fmt:formatDate value="${comment.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
	        </td>
	        <c:if test="${comment.csDealstat==0 }">
	    		<td class="txt-warn">未处理</td>
	    	</c:if>
	    	<c:if test="${comment.csDealstat==1 }">
	    		<td>处理中</td>
	    	</c:if>
	    	<c:if test="${comment.csDealstat==2 }">
	    		<td class="txt-success">已处理</td>
	    	</c:if>
	    	
	        <td>
	        	<a href="${root}/Customer/SpComment/getCommentById.do?id=${comment.id}">详情</a>
	        </td>
	    </tr>
     </c:forEach>
    </tbody>
</table>
<c:if test="${fn:length(commentList)>0}">
	<%@ include file="../common/pagination-kk.jsp"%>
</c:if>
<script>
	$(function() {
		dateRange('.J_TIME_START', '.J_TIME_END');
		if("${spcRo.csDealstat}"!=""){
			$("#csDealstat").val("${spcRo.csDealstat}");
		}
		
		/*处理状态*/
		$("#csDealstat").on('change', function() {
			dynamicStateSelect();
		});
		
		
		/* checkBox */
		$('input[type="checkbox"]').on('click',function(){
			dynamicStateSelect();
		});
		
	});
	
	function dynamicStateSelect(){
		var spNm = $("#spNm").val();
		var beginTime1 = $("#beginTime1").val();
		var endTime1 = $("#endTime1").val();
		var info = $("#info").val();
		//判断5次
		var unionHtml='';
	    if($('#unionFenA').prop('checked')==true){
		    unionHtml+="&unionFenA=1"
		}
	    if($('#unionFenB').prop('checked')==true){
		    unionHtml+="&unionFenB=2"
		} 
	    if($('#unionFenC').prop('checked')==true){
		    unionHtml+="&unionFenC=3"
		} 
	    if($('#unionFenD').prop('checked')==true){
		    unionHtml+="&unionFenD=4"
		} 
	    if($('#unionFenE').prop('checked')==true){
		    unionHtml+="&unionFenE=5"
		} 
	    var csDealstat = $("#csDealstat").val();
		location.href = "${root}/Customer/SpComment/getAllCommentByParameter.do?sortName=addTime&sortOrder=desc&spNm="+spNm+"&beginTime1="+beginTime1+"&endTime1="+endTime1+unionHtml+"&csDealstat="+csDealstat;
	}
	
</script>
</body>
</html>