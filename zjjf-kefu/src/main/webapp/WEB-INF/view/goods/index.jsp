<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>商品库 - 商品列表</title>
    <%@ include file="../common/head.jsp"%>
</head>
<body class="wrap-bd">
<div class="mb-default">
    <a class="crumb" href="${root}/customer/itemBases/getAllItemBaseByPatam.do">商品库管理</a><a class="crumb crumb-active">基础商品库</a>
</div>
<div class="mb-small clearfix">
    <form class="fl">
        <div class="fl mr-default">
        	<span>分类:</span>
            <select class="select" onchange="selectErJiByYiJi()" id="yiJi" name="yiJiId">
                <option value="">全部</option>
                <c:forEach var="itemCatelogYiJi" items="${itemCatelogYiJiList }">
                	<option <c:if test="${itemCatelogYiJi.id==itemBaseRo.yiJiId }">selected="selected"</c:if> value="${itemCatelogYiJi.id }">${itemCatelogYiJi.name }</option>
                </c:forEach>
            </select>
            <select class="select" id="erJi" name="erJiId" >
            	<option value="">全部</option>
           		<c:forEach var="itemCatelogErJi" items="${itemCatelogErJiList }">
               		<option <c:if test="${itemCatelogErJi.id==itemBaseRo.erJiId }">selected="selected"</c:if> value="${itemCatelogErJi.id }" ;>${itemCatelogErJi.name }</option>
               	</c:forEach> 
            </select>
        </div>
        <div class="fl mr-default">
        	<span>状态:</span>
	        <select class="select" id="status" name="status">
	                <option value="" selected="selected">全部</option>
	                <option value="0">停用</option>
	                <option value="1">正常</option>
	         </select>
         </div>
         <div class="fl mr-default">
        	<span>核对状态:</span>
	        <select class="select" id="checkStatus" name="checkStatus">
	                <option value="" selected="selected">全部</option>
	                <option value="0">未核对</option>
	                <option value="1">已核对</option>
	         </select>
         </div>
        <input class="input input-search-text" type="text" name="noAndName" value="${itemBaseRo.noAndName }" placeholder="商品条形码/商品名称">
        <input class="input input-search-button" value="搜索" type="submit">
    </form>
    <div class="fr"><a href="${root}/customer/itemBases/returnAddOrEditPage.do?requestPageStr=add"><button class="button button-default">新增商品</button></a></div>
</div>
<table class="table-list">
    <thead>
        <tr>
        	<th>商品图片</th>
            <th>商品条形码</th>
            <th>上一级条形码</th>
            <th>物流规格数</th>
            <th>商品名称</th>
            <th>分类</th>
            <th>规格</th>
            <th>单位</th>
            <th>库存上/下限</th>
            <th>核对状态</th>
            <th>状态</th>
            <th width="130">操作</th>
        </tr>
    </thead>
    <tbody class="table-tbody">
    <c:forEach var="itemBase" items="${itemBaseList }">
        <tr>
        	<td><img alt="" src="${USER_FASTFDSPREURL}${itemBase.imgS }" width="40" height="40" onerror="javascript:this.src='${USER_DEFAULTIMG_URL}'"> </td>
            <td>${itemBase.mdseId }</td>
            <td>${itemBase.upMdseId }</td>
            <td>${itemBase.countNum }</td>
            <td>${itemBase.name }</td>
            <td>${itemBase.yiJiName },${itemBase.erJiName }</td>
            <td>${itemBase.spec }</td>
            <td>${itemBase.pkg }</td>
            <td>${itemBase.upper }/${itemBase.lower }</td>
            <td>
	            <c:if test="${itemBase.checkStatus==0 }">
	            	 <span class="txt-warn">未核对</span>
	            </c:if>
	            <c:if test="${itemBase.checkStatus==1 }">
	            	 <span class="txt-success">已核对</span>
	            </c:if>
            </td>
            <td>
	            <c:if test="${itemBase.status==0 }">
	            	 <span class="txt-warn">停用</span>
	            </c:if>
	            <c:if test="${itemBase.status==1 }">
	            	 <span class="txt-success">正常</span>
	            </c:if>
            </td>
            <td>
            	<input type="hidden" value="${itemBase.id }">
            	<input type="button" class="button button-operate setup" value="设置">
            	<%-- <a href="${root}/customer/itemBases/returnAddOrEditPage.do?requestPageStr=edit&id=${itemBase.id }&pageIndex=${page.pageIndex}" class="button button-operate">编辑</a> --%>
            	<c:if test="${itemBase.isDelete==false }">
            		<a href="${root}/customer/itemBases/returnAddOrEditPage.do?requestPageStr=edit&id=${itemBase.id }&pageIndex=${page.pageIndex}" class="button button-operate">编辑</a>
                	<button class="button button-operate" onclick="deleteItem('${itemBase.id }','false')">删除</button>
            	</c:if>
            	<c:if test="${itemBase.isDelete==true }">
            	 	<a class="button button-operate">编辑</a>
                	<button class="button button-operate disabled"  onclick="deleteItem('${itemBase.id }','true')">恢复</button>
            	</c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:if test="${fn:length(itemBaseList)>0}">
	<%@ include file="../common/pagination-kk.jsp"%>
</c:if>
<script >
   
$(function(){
	layer.config({
		  extend: 'extend/layer.ext.js'
		});
	
	var status = '${itemBaseRo.status}';
	if(status != null){
		$("#status").val(status);
	}
	var checkStatus = '${itemBaseRo.checkStatus}';
	if(checkStatus != null){
		$("#checkStatus").val(checkStatus);
	}
	
	//设置上下级关系
	$(".setup").click(function(){
		var id = $(this).prev("input").val();
		console.log(id);
		layer.prompt({title: '商品条形码', maxlength: 20},function(val){
			console.log(val)
			$.post("${root}/customer/itemBases/updateUpperLowerRelationshop.do",{id:id,mdseId:val},function(data){
				if(data.success){
					layer.msg(data.message);
					location.href="${root}/customer/itemBases/getAllItemBaseByPatam.do?pageIndex=${page.pageIndex}";
				}else{
					layer.msg(data.message);
				}
				
			},'json');
			
		});
	});
})

/* function deleteItem(id){
	if(confirm("确定删除吗？")){
		$.post("${root}/customer/itemBases/deleteItem.do",{id:id},function(data){
			if(data.success){
				alert(data.message);
				location.href="${root}/customer/itemBases/getAllItemBaseByPatam.do";
			}else{
				alert(data.message);
			}
		})
	}
} */

function deleteItem(id,isDel){
	console.log(isDel);
	var isDelete = false;
	if(isDel=='false'){
		isDelete = true;
		if(confirm("确定删除吗？")){
			$.post("${root}/customer/itemBases/deleteLogisticsById.do",{id:id,isDelete:isDelete},function(date){
			   if(date.success){
				   location.href="${root}/customer/itemBases/getAllItemBaseByPatam.do?pageIndex=${page.pageIndex}";
			   }
			},'json');
		}
	}else{
		$.post("${root}/customer/itemBases/deleteLogisticsById.do",{id:id,isDelete:isDelete},function(date){
		   if(date.success){
			   location.href="${root}/customer/itemBases/getAllItemBaseByPatam.do?pageIndex=${page.pageIndex}";
		   }
		},'json');
	}
}

function selectErJiByYiJi(){
	var pid = $("#yiJi").val();
	$.post("${root}/customer/itemCatelog/getAllItemCateByPid.do",{pid:pid},function(data){
		if(data.success){
			var html = '<option value="0">全部</option>';
			$.each(data.message,function(i,item){
				html+='<option value="'+item.id+'">'+item.name+'</option>'
			})
			$("#erJi").html(html);
			
		}
	},'json')
}
</script>
</body>
</html>