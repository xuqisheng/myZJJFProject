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
	<div class="mb-default title">定格列表</div>
	<div class="op-section clearfix">
		<form action="" id="spgLineForm" method="" class="fl">
			<input class="input-search-text" type="text" id="spGroupName" name="spGroupName" placeholder="请输入编码/名称">
			<input type="button" class="input-search-button" value="搜索"  id="sub"/>
			<a style="margin-left: 10px;" href="${root}/spgLine/toAddPage.do">添加定格</a>
		</form>
	</div>
	<div>
		<table class="table-list table-border">
			<thead class="table-thead">
				<tr class="table-header">
					<th>定格编码</th>
					<th>定格名称</th>
					<th>绑定部门</th>
					<th>包含路线</th>
					<th>BD专员</th>
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
                 url: '${root}/spgLine/listPage.do',
                 params: $('#spgLineForm').serializeArray(),
                 success: function(data) {
                     var html='';
                     $.each(data.list, function(i,item) {
                     	html+='<tr><td>'+item.spGroupId+'</td>';
                     	html+='<td>'+item.spGroupName+'</td>';
   	                	html+='<td>'+item.deptName+'</td>';
    	                if(item.line == null){
    	                	html+='<td></td>';
    	                }else{
    	                	html+='<td>'+item.line+'</td>';
    	                }
    	                if(item.dbUsers == null){
    	                	html+='<td></td>';
    	                }else{
    	                	html+='<td>'+item.dbUsers+'</td>';
    	                }
    	                html+='<td><a href="${root}/spgLine/toEditPage.do?id='+item.id+'&deptId='+item.deptId+'&pageType=view" style="margin-right:10px;">查看</a>';
    	                html+='<a href="${root}/spgLine/toEditPage.do?spGroupId='+item.spGroupId+'" style="margin-right:10px;">编辑</a>';
//     	                html+='<a data-id="'+item.id+'" data-deptid="'+item.deptId+'" onclick="delDeptInfo(this);">删除</a>';
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
        	 $("#jpagination").pagination('setParams', $('#spgLineForm').serializeArray());
        	 $("#jpagination").pagination('setPageIndex', 0);
        	 $("#jpagination").pagination('remote');
        });
		
	});
	
	//弹出一个iframe层,供选择部门管理者
	$('#openLeaderList').on('click', function(){
	    layer.open({
	        type: 2,
	        title: '',
	        maxmin: true,
	        shadeClose: true, //点击遮罩关闭层
	        area : ['800px' , '520px'],
	        content: '${root}/account/toLeaderPage.do'
	    });
	});
	
	function delDeptInfo(obj){
		var id =  $(obj).data("id");
		var deptId =  $(obj).data("deptid");
		 $.ajax({
				type: "post",
				url: "${root}/dept/delDeptInfo.do",
				data: "id="+$.trim(id)+"&deptId="+$.trim(deptId)+"&isDelete=1",
				dataType: "json",
				async: false,
				success: function(data) {
				 if(data.success) {
						alert("操作成功!");
						location.href="${root}/dept/index.do";
					} else {
						alert(data.message);
					} 
				},
				error : function(data) {
				}
			});
	}
</script>
</body>
</html>