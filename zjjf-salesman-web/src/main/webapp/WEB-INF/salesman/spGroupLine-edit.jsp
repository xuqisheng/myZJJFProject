<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>阿街足迹</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <%@ include file="../common/head.jsp"%>
	<script>
		var rootPath="${root}";
	</script>
    
<style>
	ul li{ list-style:none; height:38px; line-height:38px;float:left; /* 向左漂移，将竖排变为横排 */ }
	ul li input{ margin-right:5px;margin-left:30px;}
</style>
</head>
<body class="wrap-bd">
<div class="title mb-default" id="title">定格路线划分</div>
<div class="wrap-bd" style="background: #fff">
	<div>
	   <fieldset >
   		 <legend>基本信息</legend>
   		   <form id="addSpgLine">
   		     <input type="hidden" id="shopIds"/>
   		   	 <input type="hidden" id="lineSet" name="lineSet"/>
   		   	 <input type="hidden" id="opType" name="opType" value="Edit"/>
		  	 <input type="hidden" name="provinceId" value="${headerVO.provinceId}">
		  	 <input type="hidden" name="cityId" value="${headerVO.cityId}">
		  	 <input type="hidden" name="areaId" value="${headerVO.areaId}">
		  	 <input type="hidden" id="deptId" name="deptId" value="${headerVO.deptId}">
		  	 <input type="hidden" name="spGroupId" value="${headerVO.spGroupId}">
   		   	 <input type="hidden" id="spGroupName" name="spGroupName" />
  		   	 <!--  hisUserId历史的用户ID -->
   		   	 <input type="hidden" id="hisUserId" value="${headerVO.userIdSet}"/>
   		   	 <input type="hidden" id="userIdSet" name="userIdSet" value="${headerVO.userIdSet}"/>
		  	 <input type="hidden" id="tmpProvinceId" value="${headerVO.provinceId}">
		  	 <input type="hidden" id="tmpCityId" value="${headerVO.cityId}">
		  	 <input type="hidden" id="tmpAreaId" value="${headerVO.areaId}">
		  	 <input type="hidden" id="tmpDeptId" value="${headerVO.deptId}">
		  	 <input type="hidden" id="tmpSpGroupId" value="${headerVO.spGroupId}">
		  	 
	   		 <p>
		   		 <div style="margin-bottom: 20px;">
<!-- 		   		 	<div style="margin-bottom: 20px;"> -->
<!-- 	            	     定格下属客户数：156个注册客户，118个未注册客户  <a href="#" id="shopLinkList">添加未注册客户</a> -->
<!-- 	                </div> -->
	            	<label class="label" id="codeLabel">所辖区域：</label>
		            <select id="provinceSelect" style="width:209px;background-color: #e7e8eb;" onchange="getRegionInfo(this,'citySelect',0)" disabled="disabled" >
		                <option value="">省份</option>
		                <c:forEach var="provinceList" items="${provinceList}">
		              	  <option value="${provinceList.id}">${provinceList.name}</option>
		                </c:forEach>
		            </select>
		            <select id="citySelect" style="width:209px;background-color: #e7e8eb;" onchange="getRegionInfo(this,'areaSelect',0)" disabled="disabled">
		                <option value="">城市</option>
		            </select>
		            <select id="areaSelect" style="width:209px;background-color: #e7e8eb;" onchange="getSpGroupInfo(this,'spGroupSelect','0');" disabled="disabled">
		                <option value="">区域</option>
		            </select>
	            </div>
	            </p>
	            <p>
	            <label class="label" id="codeLabel">所属定格：</label>
	            <select id="spGroupSelect" style="width:422px;background-color: #e7e8eb;" disabled="disabled">
	                <option value="">请选择</option>
	            </select>
	            </p>
	            <p>
	            <label class="label" id="deptLabel">绑定部门：</label>
	               <select id="deptSelect" style="width:209px;background-color: #e7e8eb;" disabled="disabled">
		                <option value="">请选择</option>
		                <c:forEach var="deptList" items="${deptList}">
		              	  <option value="${deptList.deptId}">${deptList.deptName}</option>
		                </c:forEach>
		            </select>
	            <label class="" id="deptLabel"  style="margin-left:10px;">BD代表<span style="color: red;">*</span>：</label>
	            <span id="spSlsm">${headerVO.dbUsers}</span>
	            <a style="margin-left: 10px;" id="openUserList">添加</a>
	         </p>
         </form> 
       </fieldset>
        
	   <fieldset style="margin-top:40px;">
   		 <legend>规划线路</legend>
   		 <div id="lineContainer">
   		 	<c:forEach var="list" items="${lineList}">
   		 	<div class="mark">
		         <p>
		            <label class="label" id="codeLabel">路线<span style="color: red;">*</span>：</label>
		            <input type="text" name="lineNo" class="input-search-text" value="${list.line}">
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
         </div> 
         <div><button type="button" id="addLineBtn">>>继续添加线路</button></div>	
       </fieldset>
	
	</div>
	<div id="submitDiv" style="margin-top: 20px;">
		<button class="button-save" id="submitBtn" onclick="savaSpGroupLine();">确定</button>
		<button class="button-cancel ml-default" id="cancelButton" onclick="history.go(-1)">取消</button>
	</div>
</div>
<!-- 线路模板 start -->
<div id="bakDiv" style="display: none;">
	<div class="mark">
     <p>
        <label class="label" id="codeLabel">路线<span style="color: red;">*</span>：</label>
        <input type="text" name="lineNo" class="input-search-text">
     </p>
     <p>
        <label class="label" id="codeLabel">路线客户： </label>
        <input type="button" name="btnAddSelCust"  value="添加客户"     onclick="loadCustList(this);">
        <input type="button" name="btnDelLineCust"  value="删除选中客户" onclick="delLineCust(this);">
        <input type="button" name="btnDelLine"     value="删除线路"     onclick="delLineData(this);">
        <div class="chkBoxDiv" style="display: inline-block; width: 90%; height: 142px; background: #fff; overflow: auto;">
        	 <ul></ul>
        </div>
  </p>
 </div>
</div>
<!-- 线路模板 end -->
<script src="${root}/resources/vendor/jquery/jquery-1.12.0.min.js"></script>
<script src="${root}/resources/vendor/salesman/js/salesman.js"></script>
<script src="${root}/resources/vendor/salesman/js/spGroupLine.js"></script>
<script src="${root}/resources/vendor/salesman/js/spGroupLine-edit.js"></script>
</body>
</html>