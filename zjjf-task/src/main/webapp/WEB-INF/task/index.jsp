<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>街坊店宝 - 作业管理</title>
	<%@ include file="../common/head.jsp"%>
	<script src="${root}/resources/js/comm.js"></script> 
</head>
<body>
<div class="wrap-bd">
    <div class="mb-small clearfix">
        <form class="fl" id="authForm" method="post" action="${root}/task/task/getTaskListPage.do">
            <input class="input input-search-text" type="text" name="taskName" value="${command.taskName }" placeholder="作业名称">
            <input class="input input-search-button" value="搜索" type="submit">
        </form>
        <div class="fr">
        	<button class="button button-default" id="J_addTask">新增作业</button>
        </div>
    </div>
    <div>
        <table class="table-list table-border">
            <thead class="table-thead">
            <tr class="table-header">
            	<th width="10%">作业名称</th>
            	<th width="10%">作业分组</th>
                <th width="10%">作业时间</th>
                <th width="16%">作业类</th>
                <th width="10%">作业方法</th>
                <th width="10%">作业请求</th>
                <th width="10%">作业参数</th>
                <th width="8%">作业状态</th>
                <th width="8%">是否删除</th>
                <th width="8%">操作</th>
            </tr>
            </thead>
            <tbody class="table-tbody">
            <c:forEach items="${list}" var="task">
            <tr ondblclick="tableDblclick('${task.id}')">
            	<td>${task.taskName}</td>
            	<td>${task.taskGroup}</td>
                <td>${task.taskTrigger}</td>
                <td>${task.taskClassName}</td>
                <td>${task.taskMethod}</td>
                <td>${task.taskAction}</td>
                <td>${task.taskParams}</td>
                <td>
                	<c:choose>
                		<c:when test="${task.status == 1}"><span class="txt-success">启用</span></c:when>
                		<c:otherwise><span class="txt-warn">不启用</span></c:otherwise>
                	</c:choose>
                </td>
                <td>
                	<c:choose>
                		<c:when test="${task.isDelete == false}"><span class="txt-success">否</span></c:when>
                		<c:otherwise><span class="txt-warn">是</span></c:otherwise>
                	</c:choose>
               </td>
                <td>
                	<c:choose>
                		<c:when test="${task.status == 1}"><span class="editStatus" data-val="0" data-id="${task.id}">停用</span></c:when>
                		<c:otherwise><span class="editStatus" data-val="1" data-id="${task.id}">启用</span></c:otherwise>
                	</c:choose>
                	<span data-id="${task.id}" class="onlyOneTime">立即执行</span>
                	<span data-id="${task.id}" class="taskDetail">详情</span>
<%--                 	<span data-value="${task.id}" class="icon-op icon-op-edit editTask" title="编辑"></span> --%>
                </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <c:if test="${fn:length(list)>0}">
		<%@ include file="../common/pagination-kk.jsp"%>
	</c:if>
</div>
<div class="hidden" id="J_dialogTaskDetail">
    <table class="table-list table-border">
       <thead class="table-thead">
       <tr class="table-header">
	       	<th width="10%">编号</th>
	       	<th width="30%">执行时间</th>
	        <th width="50%">执行备注</th>
	        <th width="10%">执行状态</th>
       </tr>
       </thead>
       <tbody class="table-tbody table_taskDetail">
       </tbody>
   </table>
   <div class="dialog-foot">
   	<%@ include file="../common/pagination.jsp"%>
   </div>
</div>
<div class="hidden" id="J_dialogTask">
    <form id="taskForm">
    <div class="dialog-body">
        <p class="required">
            <label class="label">作业名称：</label>
            <input type="text" class="input input-default" name="taskName">
            <input type="hidden" name="id"/>
        </p>
        <p>
            <label class="label">作业分组：</label>
            <input type="text" class="input input-default" name="taskGroup">
        </p>
        <p class="required">
            <label class="label">作业时间：</label>
            <input type="text" class="input input-default" name="taskTrigger" placeholder="0/5 * * * * ?">
            <span class="txt-warn">（作业时间，示例：0/5 * * * * ? 5秒执行一次）</span>
        </p>
        <p  class="required">
            <label class="label">作业类：</label>
            <input type="text" class="input input-default" name="taskClassName" placeholder="com.corner.task.tasks.ScOrderInfoTasks">
            <span class="txt-warn">（作业类-- 示例：com.corner.task.tasks.ScOrderInfoTasks）</span>
        </p>
        <p  class="required">
            <label class="label">作业方法：</label>
            <input type="text" class="input input-default" name="taskMethod" placeholder="taskBase">
            <span class="txt-warn">（作业方法-- 示例： 作业方法 --taskBase）</span>
        </p>
        <p>
            <label class="label">作业请求：</label>
            <input type="text" class="input input-default" name="taskAction" placeholder="zjjf-kefu/kefu/authority/scmsLoginPage.do">
            <span class="txt-warn">（作业请求-- 示例：zjjf-kefu/kefu/authority/scmsLoginPage.do）</span>
        </p>
        <p>
            <label class="label">作业参数：</label>
            <input type="text" class="input input-default" name="taskParams" placeholder='{"shili1":"1111","shili2":"天地银行"}'>
            <span class="txt-warn">（作业参数 json数据，示例：{"shili1":"1111","shili2":"天地银行"}）</span>
        </p>
        <p class="required">
            <label class="label">作业状态：</label>
       		<input type="radio" name="status" value="1" checked="checked"> 启用
	        <input class="ml-default" type="radio" name="status" value="0"> 停用
        </p>
    </div>
    <div class="dialog-foot">
        <input type="button" class="dialog-button dialog-ok" id="taskFormSubmit" value="保存">
        <input type="button" class="dialog-button dialog-cancel" value="取消" onclick="location.reload()">
    </div>
    </form>
</div>
</body>
<script type="text/javascript">
$(function(){
	$('input[type="text"]').attr('style' , 'width: 300px;');
	$('.editTask').on('click' , function(e){
		e.stopPropagation();
		$('#formPositionName').val('');
		$('#formPositionId').val('');
		$('#formPositionDeptId').val('');
		var val = $(this).attr('data-value');
		$.ajax({
 			type : "POST",
 			url : "${root }/task/task/getTaskById.do",
 			dataType : "json",
 			async:false,
 			data:{id:val , type:2},
 			success : function(data) {
 				if (data.success) {
 					$('input[name="id"]').val(data.message.id);
 					$('input[name="taskName"]').val(data.message.taskName);
 					$('input[name="taskGroup"]').val(data.message.taskGroup);
 					$('input[name="taskTrigger"]').val(data.message.taskTrigger);
 					$('input[name="taskClassName"]').val(data.message.taskClassName);
 					$('input[name="taskMethod"]').val(data.message.taskMethod);
 					$('input[name="taskAction"]').val(data.message.taskAction);
 					$('input[name="taskParams"]').val(data.message.taskParams);
 					$('input[name="status"]').val(data.message.status);
 					$('#J_addTask').trigger('click');
   				}else{
   				}
 			},
 			error : function() {
   			}
 		});  
	});
	$('.editStatus').on('click' , function(){
		var status = $(this).attr('data-val');
		var id = $(this).attr('data-id');
		$.ajax({
 			type : "POST",
 			url : "${root }/task/task/saveTask.do",
 			dataType : "json",
 			async:false,
 			data:{id:id , status : status},
 			success : function(data) {
 				if (data.success) {
 					location.reload()
   				}else{
   					layer.alert(data.message);
   				}
 			},
 			error : function() {
 				alert("请求失败！");
   			}
 		});
	});
	$('.onlyOneTime').on('click' , function(){
		var id = $(this).attr('data-id');
		$.ajax({
 			type : "POST",
 			url : "${root }/task/task/onlyOneTime.do",
 			dataType : "json",
 			async:false,
 			data:{id:id},
 			success : function(data) {
 				if (data.success) {
 					layer.alert("执行成功" , function(){
 						location.reload();
 					});
   				}else{
   					layer.alert(data.message);
   				}
 			},
 			error : function() {
 				alert("请求失败！");
   			}
 		});
	});
	$('.dialog-cancel').on('click' , function(){
		$('input[type="text"]').val('');
		$('input[name="id"]').val('');
	});
	$('#J_addTask').on('click' , function(){
		layer.open({
		  type: 1,
		  area: ['1000px', '600px'],
		  title:'作业管理',
		  content: $('#J_dialogTask'),
		  cancel: function(){
			$('input[type="text"]').val('');
			$('input[name="id"]').val('');
		  }
		});
	});
	 $("#jpagination").pagination({
         pageSize: 10,
         showJump: true,
         remote: {
             url: '${root}/task/task/getTaskLogPage.do',
             success: function(data) {
             	var html = "";
				 $.each(data.list,function(i,log){
					 html += '<tr><td>'+log.id+'</td>'
					 html +='<td>'+log.actionTime+'</td>';
			         html +='<td>'+log.remark+'</td>';
			         if(log.status == 0 || log.status == '0'){
			        	 html +='<td>成功</td>';
			         }else{
			        	 html +='<td>失败</td>';
			         }
			         
			      	 html += '</tr>';
				 });
              
				 $(".table_taskDetail").html(html);
		        dialogPosCenter('#J_dialogTaskDetail');
             },
             totalName:'totalSize',
             pageParams: function (data) {
 	           	return {
 	           			pageIndex: data.pageIndex + 1,
 	           			pageSize: data.pageSize
 	           		}
 	        },
         }
 	}); 
	$('.taskDetail').on('click' , function(){
		var taskId = $(this).attr('data-id');
		$("#jpagination").pagination('setParams', {taskId : taskId});
	   	$("#jpagination").pagination('setPageIndex', 0);
	   	$("#jpagination").pagination('remote');
		
		layer.open({
		  type: 1,
		  area: ['1000px', '670px'],
		  maxmin: true,
		  title:'日志查看',
		  content: $('#J_dialogTaskDetail'),
		  cancel: function(){
			  $(".table_taskDetail").html('');
		  }
		});
	});
	$('#taskFormSubmit').on('click' , function(){
		$.ajax({
 			type : "POST",
 			url : "${root }/task/task/saveTask.do",
 			dataType : "json",
 			async:false,
 			data:$('#taskForm').serialize(),
 			success : function(data) {
 				if (data.success) {
 					layer.alert(data.message , function(){
 						location.reload();
 						$('input[type="text"]').val('');
 						$('input[name="id"]').val('');
 					});
   				}else{
   					layer.alert(data.message);
   				}
 			},
 			error : function() {
 				alert("请求失败！");
   			}
 		});
	});
});
function tableDblclick(id){
	$('#formPositionName').val('');
	$('#formPositionId').val('');
	$('#formPositionDeptId').val('');
	var val = id;
	$.ajax({
		type : "POST",
		url : "${root }/task/task/getTaskById.do",
		dataType : "json",
		async:false,
		data:{id:val , type:2},
		success : function(data) {
			if (data.success) {
				$('input[name="id"]').val(data.message.id);
				$('input[name="taskName"]').val(data.message.taskName);
				$('input[name="taskGroup"]').val(data.message.taskGroup);
				$('input[name="taskTrigger"]').val(data.message.taskTrigger);
				$('input[name="taskClassName"]').val(data.message.taskClassName);
				$('input[name="taskMethod"]').val(data.message.taskMethod);
				$('input[name="taskAction"]').val(data.message.taskAction);
				$('input[name="taskParams"]').val(data.message.taskParams);
				$('input[name="status"]').val(data.message.status);
				$('#J_addTask').trigger('click');
			}else{
			}
		},
		error : function() {
		}
	});  
}

</script>
</html>