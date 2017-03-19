<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>线路规划管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/salesman/linePlansForm.js" type="text/javascript"></script>
</head>
<body>
	<ul class="nav nav-tabs">
<%-- 		<li><a href="${ctx}/shop/linePlans/">线路规划列表</a></li> --%>
		<li class="active"><a href="${ctx}/shop/linePlans/queryLinePlans">线路规划</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="linePlans" action="${ctx}/shop/linePlans/saveLinePlans" method="post" class="form-horizontal">
		<input type="hidden" id="shopIds"/>
   		<input type="hidden" id="lineStr" name="lineStr"/>
   		<input type="hidden" id="userId" name="userId"/>
		<sys:message content="${message}"/>	
	<div class="form-group">
	    <div class="col-sm-10">
	        <div class="row">
	            <label class="col-sm-1 control-label">部门：</label>
	            <div class="col-md-2">
	                <sys:treeselect id="deptId" name="deptId" value="${linePlans.deptId}" labelName="deptName" labelValue="${linePlans.deptName}" title="部门" url="/sys/dept/treeData" cssClass="form-control required"/>
	            </div>
	            <label class="col-sm-1 control-label">业务员：</label>
	            <div class="col-md-2">
	                <input type="hidden" name="salesmanId" id="salesmanId" value="${linePlans.salesmanId}"/>
					<input type="text" name="suggestionName" id="suggestionName" class="form-control" value="${linePlans.userName}"/>
	            </div>
	        </div>
	    </div>
	</div>
	<div class="hr-line-dashed"></div>
	
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
				            <div class="chkBoxDiv" style="display: inline-block; width: 100%; height: 142px; background: #fff; overflow: auto;">
			            	 	<c:forEach var="shopList" items="${list.shopList}">
			            	 		<label class="checkbox-inline" style="width:246px;">
			            	 			<input type="checkbox" name="chk_list" value="${shopList.shopNo}">${shopList.shopName}
			            	 		</label>
			            	 	</c:forEach>
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
				        <div class="chkBoxDiv" style="display: inline-block; width: 100%; height: 142px; background: #fff; overflow: auto;">
				        </div>
				  	</p>
				</div>
			</c:otherwise>
		</c:choose>
		</div> 
		<div>
		 	<button type="button" id="addLineBtn" class="btn btn-primary">>>继续添加线路</button>
		 	<button type="button" class="btn btn-primary" onclick="savaLinePlans();">保存路线信息</button>
		</div>	
	    <div class="hr-line-dashed"></div>
		<!-- 线路模板 start -->
		<div id="bakDiv" style="display: none;">
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
			        <div class="chkBoxDiv" style="display: inline-block; width: 100%; height: 142px; background: #fff; overflow: auto;">
			        </div>
			  	</p>
			</div>
		</div>
	</form:form>
</body>
</html>