<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<script src="${ctxStatic}/salesman/linePlansForm.js" type="text/javascript"></script>
</head>
<body>
	<div id="lineContainer">
		<c:choose>
			<c:when test="${!empty lineList}">
				<c:forEach var="list" items="${lineList}">
		 		 	<div class="mark">
				         <p>
				            <label class="label" id="codeLabel">路线<span style="color: red;">*</span>：</label>
				            <input type="text" name="lineName" class="input-search-text" value="${list.lineName}">
				            <input type="hidden" name="lineId" class="input-search-text" value="${list.lineId}">
				         </p>
				         <p>
				            <label class="label" id="codeLabel">路线客户： </label>
				            <input type="button" name="btnAddSelCust"   value="添加客户"    onclick="loadCustList(this);">
				            <input type="button" name="btnDelLineCust"  value="删除选中客户" onclick="delLineCust(this);">
				            <input type="button" name="btnDelLine"      value="删除本线路"   onclick="delLineData(this);">
				            <div class="chkBoxDiv" style="display: inline-block; width: 90%; height: 142px; background: #fff; overflow: auto;">
				            	 <ul>
				            	 	<c:forEach var="shopVo" items="${list.shop}">
				            	 		<li style="width: 250px;">
				            	 			<input type="checkbox" name="chk_list" value="${shopVo.shopId}">${shopVo.shopName}
				            	 		</li>
				            	 	</c:forEach>
				            	 </ul>
				            </div>
				         </p>
		        	</div>
		        </c:forEach>
			</c:when>
			<c:otherwise>
				<div class="mark">
				     <p>
				        <label class="label" id="codeLabel">路线<span style="color: red;">*</span>：</label>
				        <input type="text" name="lineName" class="input-search-text">
				     </p>
				     <p>
				        <label class="label" id="codeLabel">路线客户： </label>
				        <input type="button" name="btnAddSelCust"  value="添加客户"     onclick="loadCustList(this);">
				        <input type="button" name="btnDelLineCust"  value="删除选中客户" onclick="delLineCust(this);">
				        <input type="button" name="btnDelLine"     value="删除线路"     onclick="delLineData(this);">
				        <div class="chkBoxDiv" style="display: inline-block; width: 90%; height: 142px; background: #fff; overflow: auto;">
				        </div>
				  	</p>
				</div>
			</c:otherwise>
		</c:choose>
		</div> 
		
</body>
</html>