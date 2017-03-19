<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>岗位管理</title>
	<%@ include file="../common/head.jsp"%>
	<script src="${root}/resources/js/comm.js"></script>
	<script src="${root}/resources/js/layout.js"></script>
</head>
<body class="wrap-bd">
<div class="mb-small">
    <a class="crumb" href="${root}/auth/job/toJobIndex.do">岗位管理</a><a class="crumb crumb-active">列表</a>
</div>
<div class="mb-small clearfix">
    <div class="fr">
        <button class="button button-default" id="J_addDepartment">新增部门</button>
        <button class="button button-default" id="J_addPosition">新增岗位</button>
    </div>
</div>
<div class="clearfix">
    <div class="fl" style="width: 19%;">
    	<div class="department" data-value="0" style="padding: 10px;margin-bottom:1px;background:#f5f5f5;cursor:pointer">全部</div>
        <c:forEach items="${departmentList}" varStatus="i" var="department" >
        <div class="department" data-value="${department.id}" style="padding: 10px;margin-bottom:1px;background:#f5f5f5;cursor:pointer">
        	${department.name}
            <span class="fr">
                <i class="mr-small icon-op icon-op-edit J_editFenLei" data-value="${department.id}" title="编辑"></i>
                <i class="icon-op icon-op-del J_delFenLei" data-value="${department.id}" title="删除"></i>
            </span>
        </div>
        </c:forEach>
    </div>
    <table class="table-list table-border fr" style="width: 80%;">
        <thead class="table-thead">
        <tr>
            <th width="160">编号</th>
            <th>岗位</th>
            <th>部门名称</th>
            <th width="120">操作</th>
        </tr>
        </thead>
        <tbody class="table-tbody">
            <c:forEach items="${positionList}" varStatus="i" var="position" > 
         	<tr>	
            	<td>${position.id}</td>
            	<td>${position.name}</td>
            	<td>
            		<c:if test="${position.deptId == null}">全部</c:if>
            		<c:if test="${position.deptId!=null}">
            			<c:forEach items="${departmentList}" varStatus="i" var="department" >
            				<c:if test="${position.deptId == department.id}">${department.name}</c:if>
            			</c:forEach>
            		</c:if>
            	</td>
            	<td>
                    <span data-value="${position.id}" class="icon-op icon-op-edit editPosition" title="编辑"></span>
                    <span data-value="${position.id}" class="icon-op icon-op-del delPosition" title="删除"></span>
	            </td> 
            </tr>  
            </c:forEach>  
        </tbody>
        <tfoot>
        	<tr>
        	<td colspan="4">
				<c:if test="${fn:length(positionList)>0}">
					<%@ include file="../common/pagination-kk.jsp"%>
			    </c:if>
			</td>
        	</tr>
        </tfoot>
    </table>
</div>
<div class="hidden" id="J_dialogArea">
    <form id="formPosition">
    <div class="dialog-body">
        <p class="required">
            <label class="label">岗位名称：</label>
            <input type="text" class="input input-default" id="formPositionName" name="name">
            <input type="hidden"  id="formPositionId" name="id"/>
        </p>
        <p>
            <label class="label">所属部门：</label>
            <select class="select"  id="formPositionDeptId" name="deptId">
            	<option value="0">全部</option>
            	 <c:forEach items="${departmentList}" varStatus="i" var="department" > 
         			<option value="${department.id}">${department.name}</option>
         		</c:forEach>
            </select>
        </p>
    </div>
    <div class="dialog-foot">
        <input type="button" class="dialog-button dialog-ok" id="formPositionSubmit" value="保存">
        <input type="button" class="dialog-button dialog-cancel" value="取消" onclick="location.reload()">
    </div>
    </form>
</div>
<div class="hidden" id="J_dialogCat">
    <form id="formDepartment">
	    <input type="hidden" name="id" id="formDepartmentId"/>
	    <div class="dialog-body">
	        <p class="required">
	            <label class="label">部门名称：</label>
	            <input type="text" class="input input-default" name="name"  id="formDepartmentName">
	        </p>
	    </div>
	    <div class="dialog-foot">
	        <input type="button" class="dialog-button dialog-ok" value="保存" id="formDepartmentSubmit">
	        <input type="button" class="dialog-button dialog-cancel" value="取消" onclick="location.reload()">
	    </div>
    </form>
</div>
<div class="cover-all"></div>
<input type="hidden" value="${condition.upId}" id="upIdAreaId"/> 
</body>
<script type="text/javascript">
	$(function(){
		$('.department').on('click' , function(){
			var val = $(this).attr('data-value');
			location.href = '${root}/auth/job/toJobIndex.do?deptId='+val;
		});
		$('.J_editFenLei').on('click' , function(e){
			e.stopPropagation();
			$('#formDepartmentName').val('');
			$('#formDepartmentId').val('');
			var val = $(this).attr('data-value');
			$.ajax({
     			type : "POST",
     			url : "${root }/auth/job/selectById.do",
     			dataType : "json",
     			async:false,
     			data:{id:val , type:1},
     			success : function(data) {
     				if (data.success) {
     					$('#formDepartmentName').val(data.message.name);
     					$('#formDepartmentId').val(data.message.id);
     					$('#J_addDepartment').trigger('click');
	   				}else{
	   				}
     			},
     			error : function() {
     				alert("请求失败！");
	   			}
     		});  
		});
		$('.J_delFenLei').on('click' , function(e){
			e.stopPropagation();
			var val = $(this).attr('data-value');
			layer.confirm('确认删除?',{
				btn:['删除', '取消']
			},function(index){
				$.ajax({
	     			type : "POST",
	     			url : "${root }/auth/job/saveDepartment.do",
	     			dataType : "json",
	     			async:false,
	     			data:{id:val , isDelete:true},
	     			success : function(data) {
	     				if (data.success) {
	     					location.reload();
		   				}else{
		   				}
	     			},
	     			error : function() {
	     				alert("请求失败！");
		   			}
	     		});
			},function(index){
				layer.close(index);
			}, {icon: 3, title:'提示'});
			
			
			
		});
		$('.editPosition').on('click' , function(e){
			e.stopPropagation();
			$('#formPositionName').val('');
			$('#formPositionId').val('');
			$('#formPositionDeptId').val('');
			var val = $(this).attr('data-value');
			$.ajax({
     			type : "POST",
     			url : "${root }/auth/job/selectById.do",
     			dataType : "json",
     			async:false,
     			data:{id:val , type:2},
     			success : function(data) {
     				if (data.success) {
     					$('#formPositionName').val(data.message.name);
     					$('#formPositionId').val(data.message.id);
     					$('#formPositionDeptId').val(data.message.deptId);
     					$('#J_addPosition').trigger('click');
	   				}else{
	   				}
     			},
     			error : function() {
	   			}
     		});  
		});
		$('.delPosition').on('click' , function(e){
			e.stopPropagation();
			var val = $(this).attr('data-value');
			layer.confirm('确认删除?',{
				btn:['删除', '取消']
			},function(index){
				$.ajax({
	     			type : "POST",
	     			url : "${root }/auth/job/savePosition.do",
	     			dataType : "json",
	     			async:false,
	     			data:{id:val , isDelete:true},
	     			success : function(data) {
	     				if (data.success) {
	     					location.reload();
		   				}else{
		   				}
	     			},
	     			error : function() {
		   			}
	     		});
			},function(index){
				layer.close(index);
			}, {icon: 3, title:'提示'});
		});
		
		$('#J_addPosition').on('click' , function(){
			layer.open({
			  type: 1,
			  area: ['400px', '300px'],
			  title:'新增岗位',
			  content: $('#J_dialogArea'),
			  cancel: function(){
				$('#formPositionName').val('');
				$('#formPositionId').val('');
				$('#formPositionDeptId').val('0');
				$('#formDepartmentName').val('');
				$('#formDepartmentId').val('');
			  }
			});
		});
		$('#J_addDepartment').on('click' , function(){
			layer.open({
			  type: 1,
			  area: ['400px', '250px'],
			  title:'新增部门',
			  content: $('#J_dialogCat'),
			  cancel: function(){
				$('#formPositionName').val('');
				$('#formPositionId').val('');
				$('#formPositionDeptId').val('0');
				$('#formDepartmentName').val('');
				$('#formDepartmentId').val('');
			  }
			});
		});
		$('#formPositionSubmit').on('click' , function(){
			$.ajax({
     			type : "POST",
     			url : "${root }/auth/job/savePosition.do",
     			dataType : "json",
     			async:false,
     			data:$('#formPosition').serialize(),
     			success : function(data) {
     				if (data.success) {
     					location.reload();
	   				}else{
	   				}
     			},
     			error : function() {
     				alert("请求失败！");
	   			}
     		});
		});
		$('#formDepartmentSubmit').on('click' , function(){
			$.ajax({
     			type : "POST",
     			url : "${root }/auth/job/saveDepartment.do",
     			dataType : "json",
     			async:false,
     			data:$('#formDepartment').serialize(),
     			success : function(data) {
     				if (data.success) {
     					
     					location.reload();
	   				}else{
	   				}
     			},
     			error : function() {
     				alert("请求失败！");
	   			}
     		});
		});
		$('.dialog-cancel').on('click' , function(){
			$('#formPositionName').val('');
			$('#formPositionId').val('');
			$('#formPositionDeptId').val('0');
			$('#formDepartmentName').val('');
			$('#formDepartmentId').val('');
		});
	})
</script>
</html>