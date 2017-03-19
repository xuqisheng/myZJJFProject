<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setAttribute("root", request.getContextPath()); %>
<link rel="stylesheet" href="${root}/resources/vendor/jquery/pagination/mricode.pagination.css?v20160707">
<script src="${root}/resources/vendor/jquery/pagination/mricode.pagination.js?v20160707"></script>
<style>
#jpagination {float: right; padding: 6px 0}
.m-pagination-jump {padding-right: 0;}
</style>
<div id="jpagination"></div>
