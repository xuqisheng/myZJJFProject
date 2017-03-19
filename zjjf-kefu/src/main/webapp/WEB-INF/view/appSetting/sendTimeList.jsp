<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>参数设置</title>
    <%@ include file="../common/head.jsp"%>
	<script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
	<script src="${root}/resources/js/comm.js"></script>
</head>
<body class="wrap-bd">
<div class="mb-small clearfix">
    <div class="fl">
	    <form id="fm">
	        <label>名称：</label>
	        <input type="text" class="input input-search-text" name="name">
	        <input type="button" value="搜索" class="input input-search-button ml-default">
	    </form>
    </div>
    <div class="fr">
        <a href="${root }/keFu/systemConfig/toEditPage/1.do" class="button button-default">新增送货时间配置</a>
    </div>
</div>
<div id="abc">
    <table class="table-list">
        <thead>
        <tr>
            <th>序号</th>
            <th>名称</th>
            <th>备注</th>
            <th>排序</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody class="table-tbody">
        <%-- <tr>
            <td></td>
            <td></td>
            <td></td>
            <td>
                <a href="${root }${systemConfig.editUrl}?configId=${systemConfig.id}&str=Q" class="button button-operate">停用/启用</a>
                <a href="${root }${systemConfig.editUrl}?configId=${systemConfig.id}&str=Q" class="button button-operate">删除/恢复</a>
                <a href="${root }${systemConfig.editUrl}?configId=${systemConfig.id}&str=E" class="button button-operate">编辑</a>
            </td>
        </tr> --%>
        </tbody>
    </table>
    <%@ include file="../common/pagination.jsp"%>
</div>
<script>
$(function() {
	//加载列表
	$("#jpagination").pagination({
	    pageSize: 10,
	    remote: {
	        url: '${root}/keFu/systemConfig/getSendTimeConfig.do',
	        params: $('#fm').serializeArray(),
	        success: function(data) {
	            var html='';
	            $.each(data.list, function(i,item) {
	            	html+='<tr>';
	            	html+='<td>'+(i+1)+'<input type="hidden" class="id" value="'+item.id+'"></td>';
	            	html+='<td>'+item.name+'</td>';
	            	html+='<td>'+item.remark+'</td>';
	            	html+='<td>'+item.ordId+'</td>';
	            	if(item.status==0){
	            		html+='<td>停用</td>';
	            	}else{
	            		html+='<td>正常</td>';
	            	}
	            	html+='<td>';
	            	/* if(item.status==0){
	            		html+='<input type="button" value="启用" class="button button-operate staBtn">';
	            		html+='<input type="hidden" value="1" class="status">';
	            	}else{
	            		html+='<input type="button" value="停用" class="button button-operate staBtn">';
	            		html+='<input type="hidden" value="0" class="status">';
	            	} */
	            	if(item.isDelete){
	            		html+='<input type="button" value="恢复" class="button button-operate delBtn">';
	            		html+='<input type="hidden" value="false" class="delete">';
	            		html+='<a href="#" class="button button-operate">编辑</a>';
	            	}else{
	            		html+='<input type="button" value="删除" class="button button-operate delBtn">';
	            		html+='<input type="hidden" value="true" class="delete">';
	            		html+='<a href="${root}/keFu/systemConfig/toEditPage/2.do?id='+item.id+'" class="button button-operate">编辑</a>';
	            	}
	            	html+='</td>';
                    html+='</tr>';
	            });
                if(html == "") {
                 	html = '<tr><td colspan="6">暂无数据</td></tr>';
                }
	            $('#abc .table-tbody').html(html);
	        },
	        
	        totalName:'totalSize',
            pageParams: function(data) {
                return {
                    pageIndex: data.pageIndex + 1,
                    pageSize: data.pageSize
                }
            }
	    }
	});
	$('#fm').on('click',function(){
		$("#jpagination").pagination('setParams', $('#fm').serializeArray());
  		$("#jpagination").pagination('setPageIndex', 0);
  	 	$("#jpagination").pagination('remote');
	});
	
	/* $('#abc').on('click','.staBtn',function(){
		var j_tr = $(this).parent().parent();
		var id=j_tr.find('.id').val();
		var status=j_tr.find('.status').val();
		console.log(id);
		console.log(status);
		$.ajax({
			url:'${root}/kefu/DBAppConfig/updateStopOrStart/'+id+'/'+status+'.do',
			type:'post',
			dataType:'json',
			success:function(data){
				if(data.success){
					alert(data.message);
					location.reload();
				}else{
					alert(data.message);
				}
			},
			error:function(error){
				alert("请求有误");
			}
		});
	}); */
	$('#abc').on('click','.delBtn',function(){
		var j_tr = $(this).parent().parent();
		var id=j_tr.find('.id').val();
		var isdelete=j_tr.find('.delete').val();
		console.log(id);
		console.log(isdelete);
		$.ajax({
			url:'${root}/keFu/systemConfig/updateDelOrReco/'+id+'/'+isdelete+'.do',
			type:'post',
			dataType:'json',
			success:function(data){
				if(data.success){
					alert(data.message);
					location.reload();
				}else{
					alert(data.message);
				}
			},
			error:function(error){
				alert("请求有误");
			}
		});
	});
});
</script>
</body>
</html>
