<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>重复商品处理</title>
    <%@ include file="../common/head.jsp"%>
</head>
<body class="wrap-bd">
<div class="mb-small">
    <div>
        <input type="text" name="searchKey" id="searchKey" value="" placeholder="店铺名称/手机号码" class="input input-search-text">
        <input type="button" name="" value="搜索" class="input input-search-button" id="storeSearch">
    </div>
</div>
<div>
    <table class="table-list">
        <thead>
        <tr>
            <th>序号</th>
            <th>用户名称</th>
            <th>手机号码</th>
            <th>钱包余额</th>
            <th>钱包状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody class="table-tbody" id="J_tableBbody">
        
        </tbody>
    </table>
        <%@ include file="../common/pagination.jsp"%>
</div>
<script type="text/javascript">
 $(function(){
	 
 });
  
</script>
</body>
</html>
