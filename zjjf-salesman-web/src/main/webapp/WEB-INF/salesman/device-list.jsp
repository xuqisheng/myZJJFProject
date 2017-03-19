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
	<div class="mb-default title">设备管理</div>
	<div class="op-section clearfix">
		<form action="" id="accountForm" method="" class="fl">
			<input class="input-search-text" type="text" id="userName" name="userName" placeholder="请输入用户帐号/姓名" value="">
			<input type="button" class="input-search-button" value="搜索"  id="sub"/>
			<input type="button" class="input-search-button" value="解绑历史记录"  id="btnUnbind"/>
		</form>
	</div>
	<div>
		<table class="table-list table-border">
			<thead class="table-thead">
				<tr class="table-header">
					<th>账号</th>
					<th>姓名</th>
					<th>设备名称</th>
					<th>设备标识</th>
					<th>最后登录时间</th>
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
                 url: '${root}/device/listPage.do',
                 params: $('#accountForm').serializeArray(),
                 success: function(data) {
                     var html='';
                     $.each(data.list, function(i,item) {
                     	html+='<tr><td>'+item.mobile+'</td>';
                     	html+='<td>'+item.userName+'</td>';
    	                if(item.deviceName == null){
    	                	html+='<td></td>';
    	                }else{
    	                	html+='<td>'+item.deviceName+'</td>';
    	                }
    	                html+='<td>'+item.deviceUUID+'</td>';
    	                html+='<td style="color:red">'+item.lastTime+'</td>';
    	                html+='<td>';
    	                html+='<a data-id="'+item.id+'" onclick="unbindDeviceId(this);">解绑</a>';
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
        	 $("#jpagination").pagination('setParams', $('#accountForm').serializeArray());
        	 $("#jpagination").pagination('setPageIndex', 0);
        	 $("#jpagination").pagination('remote');
        });
		 
		 $('#btnUnbind').on('click', function(e) {
			 location.href="${root}/device/deviceLogIndex.do";
        });
		
	});
	
	function unbindDeviceId(obj){
		var userId =  $(obj).data("id");
		 $.ajax({
				type: "post",
				url: "${root}/device/unbindDeviceId.do",
				data: "id="+$.trim(userId),
				dataType: "json",
				async: false,
				success: function(data) {
				 if(data.success) {
					 alert("解绑成功!");
						location.href="${root}/device/index.do";
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