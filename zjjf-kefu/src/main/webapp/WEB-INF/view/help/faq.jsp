<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>FAQ列表</title>
<%@ include file="../common/head.jsp"%>
</head>
<body class="wrap-bd">
<div class="mb-small clearfix">
    <form class="fl" action="${root}/customer/spHelp/getAllHelp.do?helpStr=pc" method="post">
        <input class="input input-search-text" type="text" name="title" value="${title}" placeholder="请输入问题名称" />
        <input class="input input-search-button" value="搜索" type="submit"/>
    </form>
    <div class="fr"><a href="${root}/customer/spHelp/pageContrul.do?pageStr=add"><button class="button button-default" id="newSpGroup">新建问题</button></a></div>
</div>
<table class="table-list">
	<thead>
		<tr>
			<th width="30">序号</th>
			<th width="200">问题</th>
			<th width="350">答案</th>
			<th>编辑时间</th>
			<th>排序</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="list" items="${helps}" varStatus="var">
			<tr id="helpListTr">
				<td>${var.index+1 }</td>
				<td>${list.title}</td>
				<td class="ta-l">${list.solution}</td>
				<td>
				<fmt:formatDate value="${list.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td class="J_orderid">
                	<span>${list.ordId }</span>
                	<input class="hidden" type="text" value="" style="width:23px" />
                	<input type="hidden" value="${list.id}" name="id">
                </td>
				<td>
					<input type="button" value="编辑" class="button button-operate J_edit" onclick="editHelp('${list.id}')">
					<c:if test="${list.isDelete==false}">
						<input type="button" value="删除" class="button button-operate J_del" onclick="deleteHelp('${list.id}','${list.isDelete}')">
					</c:if>
					<c:if test="${list.isDelete==true}">
						<input type="button" value="恢复" class="button button-operate disabled active J_del" onclick="deleteHelp('${list.id}','${list.isDelete}')">
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<%-- <c:if test="${fn:length(list)>0}">
	<%@ include file="../../common/pagination-kk.jsp"%>
</c:if >--%>
<script>
	function deleteHelp(id,isDeleteA){
		var isDelete;
			if(isDeleteA=='false'){
				if(confirm('确认删除吗?')){
					isDelete='true';
					$.ajax({
		                type : "post",
		                url : "${root}/customer/spHelp/deleteHelp.do",
		                dataType : "json",
		                data:{id:id,isDelete:isDelete},
		                success : function(data) {
		    				if (data != null && data.success) {
		    					if(isDeleteA=='false'){
		    						alert("删除"+data.message);
		    					}else{
		    						alert("恢复"+data.message);
		    					}
		    					location.href = "${root}/customer/spHelp/getAllHelp.do?helpStr=pc";
		    				} else {
		    					if(isDeleteA=='false'){
		    						alert("删除"+data.message);
		    					}else{
		    						alert("恢复"+data.message);
		    					}
		    				}
		                },
		                error : function(data) {
		                    alert("error");
		                }
		            });
				}
			}else{
				if(confirm('确认恢复吗?')){
					isDelete='false';
					$.ajax({
		                type : "post",
		                url : "${root}/customer/spHelp/deleteHelp.do",
		                dataType : "json",
		                data:{id:id,isDelete:isDelete},
		                success : function(data) {
		    				if (data != null && data.success) {
		    					if(isDeleteA=='false'){
		    						alert("删除"+data.message);
		    					}else{
		    						alert("恢复"+data.message);
		    					}
		    					location.href = "${root}/customer/spHelp/getAllHelp.do?helpStr=pc";
		    				} else {
		    					if(isDeleteA=='false'){
		    						alert("删除"+data.message);
		    					}else{
		    						alert("恢复"+data.message);
		    					}
		    				}
		                },
		                error : function(data) {
		                    alert("error");
		                }
		            });
				}
			}

	}
	function editHelp(id) {
		if (id != null) {
			location.href = "${root}/customer/spHelp/pageContrul.do?pageStr=edit&id=" + id;
		}
	}
	// 排序
	$('.J_orderid').on('click', 'span', function() {
	    $(this).hide();
	    $(this).next().show().val($(this).text()).focus().on('blur', function() {
	        var $thisspan = $(this);
	        $.ajax({
	            type : "post",
	            url : "${root}/customer/spHelp/updateOrderId.do?id="+$(this).next().val(),
	            dataType : "json",
	            data:{ordId:$(this).val()},
	            success : function(data) {
	                if (data.success) {
	                    $thisspan.hide();
	                    $thisspan.siblings('span').text(data.message.ordid).show();
	                    location.href = "${root}/customer/spHelp/getAllHelp.do?helpStr=pc";
	                } else {
	                    alert(data.message);
	                }
	            },
	            error : function(data) {
	                alert("error");
	            }
	        });
	    });
	});
</script>
</body>
</html>
