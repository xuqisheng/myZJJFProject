<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="category" id="J_category">
    <ul class="nav" id="J_nav">
    	<c:forEach items="${items}" varStatus="i" var="item" >  
		<li onclick="searchByItemId('${item.cateId}')"><a href="javascript:void(0)" ><i class="icon ${item.col3 }"></i>${item.cateName}</a></li>
		</c:forEach>
    </ul>
    <div id="J_subNav" class="sub-category-cont">
    <c:forEach items="${items}" varStatus="i" var="items">	 
    	<div class="sub-category">
    		<ul>
    			<c:forEach items="${items.cate2}" varStatus="i" var="cate2" >  
    				<li onclick="searchByItemid('${cate2.cateId2}')"><a href="javascript:void(0)">${cate2.cateName2}</a></li>
    			</c:forEach>
             </ul>
    	</div>
    </c:forEach>
    </div>
 </div>