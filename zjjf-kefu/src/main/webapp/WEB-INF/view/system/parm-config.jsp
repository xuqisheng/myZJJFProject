<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>参数设置</title>
    <%@ include file="../common/head.jsp"%>
	<script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
	<script src="${root}/resources/js/comm.js"></script>
</head>
<body class="wrap-bd">
<div class="mb-small clearfix">
    <div class="fl">
	    <form action="${root }/keFu/systemConfig/getAllConfig.do" method="post">
	        <label>配置模块：</label>
	        <input type="text" class="input input-search-text" name="configName" value="${configRo.configName }">
	        <label class="ml-default">最后更新时间：</label>
	        <input type="text" class="input input-date J_timeS" name="beginTime" value="<fmt:formatDate value="${configRo.beginTime }" pattern="yyyy-MM-dd HH:mm:ss"/>"> -
	        <input type="text" class="input input-date J_timeE" name="endTime" value="<fmt:formatDate value="${configRo.endTime }" pattern="yyyy-MM-dd HH:mm:ss"/>">
	        <input type="submit" value="搜索" class="input input-search-button ml-default">
	    </form>
    </div>
   <!--  <div class="fr">
        <a href="parm-config-edit.html" class="button button-default">新增配置</a>
    </div> -->
</div>
<div>
    <table class="table-list">
        <thead>
        <tr>
            <th>序号</th>
            <th>配置模块</th>
            <th>最后更新时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="systemConfig" items="${systemConfigVoList }" varStatus="var">
	        <tr>
	            <td>${var.index+1 }</td>
	            <td>${systemConfig.configName }</td>
	            <td><fmt:formatDate value="${systemConfig.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	            <td>
	                <a href="${root }${systemConfig.editUrl}?configId=${systemConfig.id}&str=Q" class="button button-operate">查看</a>
	                <a href="${root }${systemConfig.editUrl}?configId=${systemConfig.id}&str=E" class="button button-operate">编辑</a>
	            </td>
	        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<c:if test="${fn:length(systemConfigVoList)>0}">
	<%@ include file="../common/pagination-kk.jsp"%>
</c:if>
<script>
$(function() {
	dateRange('.J_timeS', '.J_timeE', '1');
});
</script>
</body>
</html>
