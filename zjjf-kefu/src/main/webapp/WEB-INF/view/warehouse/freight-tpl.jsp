<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>仓库管理 - 运费模板</title>
	<%@ include file="../common/head.jsp"%>
</head>
<body>
<div class="wrap-bd">
	<div>
	    <a class="crumb" href="${root}/scms/templet/listpage.do">运费模板管理</a><a class="crumb crumb-active">列表</a>
	</div>
    <div class="mb-small clearfix">
        <a class="button button-default fr" href="${root}/scms/templet/editObject.do">新增模板</a>
    </div>
    <table class="table-list">
        <thead>
        <tr>
        	<th>编号</th>
            <th>模板名称</th>
            <th>模板说明</th>
            <th>添加时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${list}" varStatus="i" var="item">
            <tr>
        		<td><c:if test="${page.pageIndex>=1}">${i.index+1+(page.pageIndex-1)*10}</c:if><c:if test="${page.pageIndex==null}">${i.index+1}</c:if></td>
	            <td>${item.name}</td>
	            <td>${item.tplMark}</td>
	            <td> <fmt:formatDate value="${item.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
	            <td>
	                <a href="${root}/scms/templet/editObject.do?id=${item.id}" class="icon-op icon-op-edit" title="编辑"></a>
               	    <a href="javascript:void(0)" class="icon-op icon-op-del" onclick="deletel('${item.id}')" title="删除"></a>
	            </td>
            </tr>
        	</c:forEach>
        </tbody>
    </table>
     <c:if test="${fn:length(list)>0}">
		  <%@ include file="../common/pagination-kk.jsp"%>
    </c:if>
</div>
<script type="text/javascript">
function deletel(id){
	if(confirm("确认删除吗")){
		$.post("${root}/scms/templet/deleteObject.do",{"id":id},function(data){
			alert(data.message);
				if(data.success){
					 window.location.reload();
				}
			
		},'json');
	}
}
function getRadio(){
	var url="${root}/scms/templet/listpage.do";
// 	var managerName= $('input[name="managerName"]').val();
// 	if(managerName != null &&managerName != undefined && managerName != ''){
// 		   url=url+"&managerName="+managerName;
// 	}
	location.href=url;
}
</script>
</body>
</html>