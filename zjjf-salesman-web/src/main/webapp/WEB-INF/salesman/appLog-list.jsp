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
    <script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
    <script src="${root}/resources/js/comm.js"></script>
</head>
<body>
<div class="wrap-bd">
	<div class="mb-default title">app运行日志监控</div>
	<div class="op-section clearfix">
		<form action="" id="logForm" method="" class="fl">
			<input class="input-search-text" type="text" id="userName" name="userName" placeholder="请输入用户名" value="">
			<select name="deviceType">
				<option value="">设备类型</option>
				<option value="1">Android</option>
				<option value="2">IOS</option>
			</select>
			<input class="input input-search-date J_DATE_START" type="text" id="createTime" name="createTime" placeholder="选择时间">
			<input type="button" class="input-search-button" value="搜索"  id="sub"/>
<!-- 			<input type="button" class="input-search-button" value="导出"  id="btnExport" style="margin-left: 10px;"/> -->
		</form>
	</div>
	<div>
		<table class="table-list table-border">
			<thead class="table-thead">
				<tr class="table-header">
					<th>用户</th>
					<th>设备类型</th>
					<th>设备名称</th>
					<th>是否活着</th>
					<th>上传状态</th>
					<th>网络</th>
					<th>GPS</th>
					<th>App时间</th>
					<th>服务器时间</th>
					<th>数据信息</th>
				</tr>
			</thead>
			<tbody class="table-tbody"></tbody>
		</table>
	</div>
	
</div>
<%@ include file="../common/pagination.jsp"%>
<script>

	$(function() {
		  //日期初始化加载
		 dateRangeSimple('.J_DATE_START', '.J_DATE_END');
		  
		 $("#jpagination").pagination({
             pageSize: 10,
             remote: {
                 url: '${root}/log/appLogList.do',
                 params: $('#logForm').serializeArray(),
                 success: function(data) {
                     var html='';
                     $.each(data.list, function(i,item) {
                     	html+='<tr>';
    	                if(item.createBy == null){
    	                	html+='<td></td>';
    	                }else{
    	                	html+='<td>'+item.createBy+'</td>';
    	                }
    	                if(item.deviceType == null){
    	                	html+='<td></td>';
    	                }else{
    	                	html+='<td>'+item.deviceType+'</td>';
    	                }
    	                if(item.deviceName == null){
    	                	html+='<td></td>';
    	                }else{
    	                	html+='<td>'+item.deviceName+'</td>';
    	                }
    	                html+='<td>'+item.keepAlive+'</td>';
    	                html+='<td>'+item.uploadState+'</td>';
    	                html+='<td>'+item.network+'</td>';
    	                html+='<td>'+item.gps+'</td>'
    	                html+='<td>'+item.appTime+'</td>'
    	                html+='<td>'+item.createTime+'</td>';
    	                html+='<td>'+item.dataInfo+'</td>';
    	                html+='</tr>';
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
        	 $("#jpagination").pagination('setParams', $('#logForm').serializeArray());
        	 $("#jpagination").pagination('setPageIndex', 0);
        	 $("#jpagination").pagination('remote');
        });
		 
		$("#btnExport").click(function(){
			 if(confirm("确定要导出日志数据吗？")){
				 $("#logForm").attr("action","${root}/log/exportAppLog.do");
				 $("#logForm").submit();
			 }
		});
		 
	});
</script>
</body>
</html>