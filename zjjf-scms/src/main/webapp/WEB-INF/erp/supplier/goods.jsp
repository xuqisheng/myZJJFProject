<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setAttribute("root", request.getContextPath());%>
<%@ include file="../../common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>供应商商品列表</title>
    <%@ include file="../../common/head.jsp"%>
</head>
<body>
<div class="wrap-bd">
	<div class="mb-default">
        <span>当前位置：</span>
        <a class="crumb" href="${root }/scms/ERPMa/getAllERPManager.do">供应商管理</a>
        <a class="crumb" href="#">商品管理<span id="J_bbbbb">（${manager.managerName }）</span></a>
    </div>
    <div class="mt-small mb-default clearfix">
        <form class="fl" method="post" action="${root }/scms/ERPMaItem/getAllManagerItem.do">
        	<input type="hidden" value="${managerItemRo.managerId }" name="managerId" id="managerId">
            <input class="input-search-text" type="text" value="${managerItemRo.numOrName }" name="numOrName" placeholder="商品编号/名称" maxlength="25"/>
            <input value="搜索" type="submit" class="input-search-button">
        </form>
        <div class="fr hidden">
            <a href="${root }/scms/ERPMaItem/returnAddOrUpdatePage.do?action=1&managerId=${managerItemRo.managerId }" class="button">新增商品</a>
        </div>
    </div>
    <table class="table-list">
        <thead>
        <tr>
            <th>商品供应码</th>
            <th>箱码</th>
            <th>商品条形码</th>
            <th>商品名称</th>
            <th>规格</th>
            <th>单位</th>
            <th>采购价</th>
            <th>税率</th>
            <th class="hidden">操作</th>
        </tr>
        </thead>
        <tbody>
	        <c:choose>
	        	<c:when test="${managerItemList != null && managerItemList.size()>0 }">
	        		<c:forEach items="${managerItemList}" var="managerItem">
	        			<tr>
				            <td>${managerItem.itemCode }</td>
				            <td>${managerItem.mdseId }</td>
				            <td>${managerItem.mdseIdX }</td>
				            <th>${managerItem.name }</th>
				            <td>${managerItem.spec }</td>
				            <td>${managerItem.pkg }</td>
				            <td>${managerItem.areaPrice }</td>
				            <td>${managerItem.taxRate }%</td>
				            <td class="hidden">
				                <a href="${root }/scms/ERPMaItem/returnAddOrUpdatePage.do?action=2&managerId=${managerItemRo.managerId }&id=${managerItem.id }">编辑</a>&nbsp;&nbsp;
				                <a href="javascript:void(0)" onclick="deleteItem('${managerItem.id }')">删除</a>
				            </td>
				        </tr>
	        		</c:forEach>
	        	</c:when>
	        	<c:otherwise>
	        		<tr>
			            <tr><td colspan="9" class="no-data"></td></tr>
			        </tr>
	        	</c:otherwise>
	        </c:choose>
        </tbody>
    </table>
     <c:if test="${fn:length(managerItemList)>0}">
		  <%@ include file="../../common/pagination-kk.jsp"%>
    </c:if>
</div>
<script type="text/javascript">
function deleteItem(id){
	if(id != null && id != "" && id !="null" && id != "undefined"){
		if(confirm("确定删除吗？")){
			$.post("${root}/scms/ERPMaItem/deleteERPManagerItem.do",{id:id},function(data){
				if(data.success){
					location.reload();
				}else{
					alert(data.message);
				}
			},'json')
		}
	}
}
</script>
</body>
</html>
