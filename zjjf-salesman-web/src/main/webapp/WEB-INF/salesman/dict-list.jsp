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
</head>
<body>
<div class="wrap-bd">
	<div class="mb-default title">字典列表</div>
	<div class="op-section clearfix">
		<form action="" id="dictForm" method="" class="fl">
			<input class="input-search-text" type="text" id="description" name="description" placeholder="请输入类型/描述">
			<input type="radio" name="isDelete" value="0" checked="checked">正常<input type="radio" name="isDelete" value="1" style="margin-left: 10px;">删除
			<input type="button" class="input-search-button" value="搜索"  id="sub"/>
			<a style="margin-left: 10px;" href="${root}/dict/toAddDict.do">添加字典</a>
		</form>
	</div>
	<div>
		<table class="table-list table-border">
			<thead class="table-thead">
				<tr class="table-header">
					<th>键值</th>
					<th>标签</th>
					<th>类型</th>
					<th>描述</th>
					<th>排序</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody class="table-tbody"></tbody>
		</table>
	</div>
	
</div>
<%@ include file="../common/pagination.jsp"%>
<script>
	$(function() {
		 $("#jpagination").pagination({
             pageSize: 10,
             remote: {
                 url: '${root}/dict/listPage.do',
                 params: $('#dictForm').serializeArray(),
                 success: function(data) {
                     var html='';
                     $.each(data.list, function(i,item) {
                     	html+='<tr><td>'+item.value+'</td>';
                     	html+='<td>'+item.label+'</td>';
    	                html+='<td>'+item.type+'</td>';
    	                html+='<td>'+item.description+'</td>';
    	                html+='<td>'+item.sort+'</td>';
    	                html+='<td><a href="${root}/dict/toEditPage.do?id='+item.id+'" style="margin-right:10px;">编辑</a>';
    	                if(item.isDelete==0){
    	                	html+='<a data-id="'+item.id+'" data-isdelete="1" onclick="delDictInfo(this);" style="margin-right:10px;">删除</a>';
    	                }else if(item.isDelete==1){
    	                	 html+='<a data-id="'+item.id+'" data-isdelete="0" onclick="delDictInfo(this);" style="margin-right:10px;">启用</a>';
    	                }
    	                html+='<a href="${root}/dict/toAddDict.do?type='+item.type+'&sort='+item.sort+'" style="margin-right:10px;">添加键值</a>';
    	                html+='</td></tr>';
    	                
                     });
                     if(html == "") {
                     	html = '<tr><td colspan="10" class="no-data"></td></tr>';
                     }
                     $('.table-tbody').html(html);
                 },
                 totalName:'totalSize',
                 pageParams: function (data) {
                    	return {
                    			pageIndex: data.pageIndex + 1,
                    			pageSize: data.pageSize
                    		}
                 }
             }
         });
		 
		 $('#sub').on('click', function(e) {
        	 $("#jpagination").pagination('setParams', $('#dictForm').serializeArray());
        	 $("#jpagination").pagination('setPageIndex', 0);
        	 $("#jpagination").pagination('remote');
        });
		
	});
	
	function delDictInfo(obj){
		var isdelete =  $(obj).data("isdelete");
		var confirmMsg = isdelete==0?"确定要启用此数据吗？":"确定要删除此数据吗？";
		if(confirm(confirmMsg)){
			var id =  $(obj).data("id");
			 $.ajax({
				type: "post",
				url: "${root}/dict/delDict.do",
				data: "id="+$.trim(id)+"&isDelete="+isdelete,
				dataType: "json",
				async: false,
				success: function(data) {
				 if(data.success) {
					 var successMsg = isdelete==0?"启用成功！":"删除成功!";
						alert(successMsg);
						location.href="${root}/dict/index.do";
					} else {
						alert(data.message);
					} 
				},
				error : function(data) {
				}
			});
		}
	}
</script>
</body>
</html>