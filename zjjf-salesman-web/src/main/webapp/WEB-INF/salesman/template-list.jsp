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
	<div class="mb-default title">模板列表</div>
	<div class="op-section clearfix">
		<form action="" id="tmplForm" method="" class="fl">
			<input class="input-search-text" type="text" id="name" name="name" placeholder="请输入名称">
			<input type="button" class="input-search-button" value="搜索"  id="sub"/>
			<a style="margin-left: 10px;" href="${root}/tmpl/toAddPage.do">添加模板</a>
		</form>
	</div>
	<div>
		<table class="table-list table-border">
			<thead class="table-thead">
				<tr class="table-header">
					<th>模板名称</th>
					<th>模板类型</th>
					<th>创建人</th>
					<th>创建时间</th>
					<th>备注</th>
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
                 url: '${root}/tmpl/listPage.do',
                 params: $('#tmplForm').serializeArray(),
                 success: function(data) {
                     var html='';
                     $.each(data.list, function(i,item) {
                    	 html+='<tr>';
    	                if(item.name == null){
    	                	html+='<td></td>';
    	                }else{
    	                	html+='<td>'+item.name+'</td>';
    	                }
    	                html+='<td>'+item.typeName+'</td>';
    	                html+='<td>'+item.createBy+'</td>';
    	                html+='<td>'+item.createTime+'</td>';
    	                html+='<td>'+item.remarks+'</td>';
    	                html+='<td><a href="${root}/tmpl/toEditPage.do?id='+item.id+'" style="margin-right:10px;">编辑</a>';
    	                html+='<a href="${root}/tmpl/queryTpmlDetail.do?tmplId='+item.id+'&tmplName='+item.name+'&tmplType='+item.typeName+'" style="margin-right:10px;">属性</a>';
    	                html+='<a data-id="'+item.id+'" data-name="'+item.name+'" onclick="delTmplInfo(this);">删除</a>';
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
        	 $("#jpagination").pagination('setParams', $('#tmplForm').serializeArray());
        	 $("#jpagination").pagination('setPageIndex', 0);
        	 $("#jpagination").pagination('remote');
        });
		
	});
	

	
	function delTmplInfo(obj){
		var name =  $(obj).data("name");
		if(confirm("确定要删除【"+name+"】模板吗？"))
		 {
			var id =  $(obj).data("id");
			 $.ajax({
					type: "post",
					url: "${root}/tmpl/delTmplInfo.do",
					data: "id="+$.trim(id),
					dataType: "json",
					async: false,
					success: function(data) {
					 if(data.success) {
							alert("操作成功!");
							location.href="${root}/tmpl/index.do";
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