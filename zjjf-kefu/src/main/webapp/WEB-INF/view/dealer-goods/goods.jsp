<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>经销商商品管理 - 商品管理</title>
    <%@ include file="../common/head.jsp"%>
</head>
<body>
<div class="wrap-bd">
    <div class="mb-default clearfix">
        <a class="crumb" href="${root}/scms/manager/listPage.do">经销商商品管理</a><a class="crumb crumb-active">经销商商品</a>
        <a class="fr" href="javascript:location.href=document.referrer;">返回</a>
    </div>
    <div class="mb-default">
        <b>${scmsManager.managerName}</b>
    </div>
    <div class="mb-small clearfix">
        <form class="fl" id="itemForm">
            <input type="hidden" value="${scmsManager.id}" name="managerId"/>
            <input class="input input-search-text" type="text" name="goodName" value="${scmsItemMgRo.goodName }" placeholder="请输入商品编码/名称" />
            <input class="input input-search-button" value="搜索" type="button" onclick="getRadio()"/>
		</form>        
        <div class="fr">
        	<c:if test="${fn:length(scmsItems) != 0}">
        		<a href="${root}/scms/item/addOrUpdate.do?isUpdate=minnum&managerId=${scmsManager.id}" class="button button-default">设置起送量</a>
        	</c:if>
			<a href="${root}/scms/item/addOrUpdate.do?isUpdate=add&managerId=${scmsManager.id}"><button class="button button-default" id="newSpGroup">添加商品</button></a>
		</div>
    </div>
    <table class="table-list table-border">
        <thead class="table-thead">
        <tr>
            <th>物流编号</th>
            <th>商品条形码</th>
            <th>商品名称</th>
            <th>规格</th>
            <th>年份</th>
            <th>月份</th>
            <th>上架状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody class="table-tbody" id="itemFromBody">
        <c:forEach items="${scmsItems}" varStatus="i" var="item" >
        	<tr>
	        	<td>${item.wuliu}</td>
				<td>${item.mdseId}</td>
				<td>${item.goodName}</td>
				<td>${item.spec}</td>
				<td><c:if test="${item.year == 0}">不限</c:if><c:if test="${item.year != 0}">${item.year}</c:if></td>
				<td><c:if test="${item.month == 0}">不限</c:if><c:if test="${item.month != 0}">${item.month}</c:if></td>
				<td><c:if test="${item.status == 0}">下架</c:if><c:if test="${item.status == 1}">上架</c:if></td>
				<td>
					<c:if test="${item.status == 0}">
						<input type="button" value="上架" onclick="updateItem('${item.itemBaseId}','1','${item.year}','${item.month}')" class="button button-operate">
					</c:if>
					<c:if test="${item.status == 1}">
						<input type="button" value="下架" onclick="updateItem('${item.itemBaseId}','0','${item.year}','${item.month}')" class="button button-operate">
					</c:if>
					<input type="button" value="编辑" onclick="editItem('${item.id}')" class="button button-operate">
					<input type="button" value="删除" onclick="updateItem('${item.itemBaseId}','2','${item.year}','${item.month}')" class="button button-operate">
				</td>
			</tr>
        </c:forEach>
        </tbody>
    </table>
   	<c:if test="${fn:length(scmsItems)>0}">
		  <%@ include file="../common/pagination-kk.jsp"%>
    </c:if>
</div>
<script>
	$(function(){
		$('#itemForm').keydown(function(e){
			if (e.keyCode == 13) {
				e.preventDefault();
				getRadio();
				
			}
		});
	});
	function updateItem(itemBaseId , status , year , month){
		if(status == 2){
			layer.msg('确认删除？', {
		        time: 0, //20s后自动关闭
		        btn: ['删除', '取消'],
		        yes: function(index){
			        layer.close(index);
			        $.post("${root}/scms/item/updateScmsItemStatusOrIsDelete.do",{itemBaseId:itemBaseId,isUpdate : "delete",year:year,month:month},function(data){
						if(data.success){
							layer.msg(data.message, {
							    icon: 1,
							    time: 2000 //2秒关闭（如果不配置，默认是3秒）
							}, function(){
								getRadio();
							}); 
						}else{
							layer.msg(data.message, {icon: 5});
						}
					 },'json');
			    }
		    });
		}else{
			$.post("${root}/scms/item/updateScmsItemStatusOrIsDelete.do",{itemBaseId:itemBaseId,status : status,year:year,month:month},function(data){
				if(data.success){
					var message = '';
					if(status == '1')
						message = '上架成功';
					else
						message = '下架成功';
					
					layer.msg(message, {
					    icon: 1,
					    time: 2000 //2秒关闭（如果不配置，默认是3秒）
					}, function(){
						location.reload();
					}); 
				}else{
					layer.msg(data.message, {icon: 5});
				}
			 },'json');
		}
 	   
    }
	function editItem(id){
  	   var managerId = '${scmsManager.id}';
  	   window.location.href = '${root}/scms/item/addOrUpdate.do?isUpdate=edit&managerId='+managerId+'&id='+id;
     }
	function getRadio(){
		var managerId= $('input[name="managerId"]').val();
		var goodName= $('input[name="goodName"]').val();
		var url='${root}/scms/item/listPage.do?managerId='+managerId;
		if(goodName != null &&goodName != undefined && goodName != ''){
			   url=url+"&goodName="+goodName;
		}
		location.href=url;
	}
</script>
</body>
</html>