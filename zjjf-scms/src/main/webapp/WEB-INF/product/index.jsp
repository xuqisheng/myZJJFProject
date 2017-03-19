<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>街坊店宝</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <%@ include file="../common/head.jsp"%>
    <script src="${root}/resources/js/comm.js"></script>
    <%@ include file="../common/autocomplete.jsp"%>
    <script>
    	var rootPath="${root}";
    </script>
</head>
<body>
<div class="wrap-bd">
    <div class="mb-small clearfix">
        <div class="fl title">商品管理</div>
        <div class="fr">
            <a class="button" href="${root}/scms/plantItem/toProductAdd.do?itemBaseId=">添加商品</a>
        </div>
    </div>
    <div class="op-section clearfix">
        <form method="post" id="searchForm">
            <label>分类：</label>
            <select class="select" id="firstSelect">
             <option value="-1" selected="selected">全部</option>
            </select>
            <select class="select" id="secondSelect">
             <option value="-1" selected="selected">全部</option>
            </select>
            <label class="ml-default">状态：</label>
            <select class="select" id="status" mane="status">
             <option value="-1" selected="selected">全部</option>
             <option value="0">已下架</option>
             <option value="1">已上架</option>
            </select>
            <input class="input-search-text ml-default" type="text" id="keyStr" value="" placeholder="商品编号/名称">
            <input id="hiddenText" type="text" style="display:none" />
            <input type="button" class="input-search-button ml-default" value="搜索" id="formSub"/>
       </form>
    </div>
    <div>
       <table class="table-list table-border" id="borderTable">
         <thead>
          <tr>
              <th>商品编号</th>
              <th>商品名称</th>
              <th>分类</th>
              <th>规格</th>
              <th>单位</th>
              <!-- <th>店宝售价</th> -->
              <th>类型</th>
              <!-- <th>上架状态</th> -->
              <th>操作</th>
          </tr>
         </thead>
         <tbody class="table-tbody">
            <!-- <tr>
                <td>6863010101</td>
                <td>(可口可乐)雪碧柠檬味1.25L/瓶</td>
                <td>1*12</td>
                <td>箱</td>
                <td>50</td>
                <td>80</td>
                <td>89</td>
                <td>
                 <a href="">编辑</a>
                </td>
            </tr> -->
         </tbody>
       </table>
    </div>
    <%@ include file="../common/pagination.jsp"%>
</div>
<script src="${root}/resources/js/product-index.js"></script>
<script>
	$(function(){
		$('.table-list').on('click', 'td', function() {
            $(this).parent().css({
                'background': '#009dd9',
                'color': 'white'
            }).siblings().css({
                'background': '#fff',
                'color': 'black'
            })
        });
	})
</script>
</body>
</html>