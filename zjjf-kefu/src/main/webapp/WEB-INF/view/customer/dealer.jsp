<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>经销商管理列表</title>
    <%@ include file="../common/head.jsp"%>
</head>
<body>
<div class="wrap-bd">
    <div class="mb-default">
        <a class="crumb" href="${root}/scms/manager/listPage.do?isUpdate=manager">经销商管理</a><a class="crumb crumb-active">经销商列表</a>
    </div>
	<div class="mt-small mb-default clearfix">
	    <form class="fl" id="managerFrom">
	        <input class="input input-search-text" type="text" name="managerName" value="${scmsManager.managerName}" placeholder="请输入经销商名称" />
	        <input class="input input-search-button" value="搜索" type="button" onclick="getRadio()"/>
	    </form>
	    <div class="fr"><a href="${root}/scms/manager/addOrUpdate.do?isUpdate=add"><button class="button button-default" id="newSpGroup">添加经销商</button></a></div>
	</div>
	<table class="table-list">
	    <thead>
	    <tr>
	        <th>编号</th>
	        <th>名称</th>
	        <th>编辑时间</th>
	        <th>操作</th>
	    </tr>
	    </thead>
	    <tbody>
	    	<c:forEach items="${scmsManagers}" varStatus="i" var="manager" > 
		    	<tr>
					<td>${manager.managerCode}</td>
					<td>${manager.managerName}</td>
					<td><fmt:formatDate value="${manager.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>
			            <span class="icon-op icon-op-edit" onclick="editManager('${manager.id}','0')" title="编辑"></span>
			            <span class="icon-op icon-op-del" onclick="editManager('${manager.id}','1')" title="删除"></span>
					</td>
				</tr>
			</c:forEach>
	    </tbody>
	</table>
	<c:if test="${fn:length(scmsManagers)>0}">
		  <%@ include file="../common/pagination-kk.jsp"%>
    </c:if>
</div>
<script>
	$(function(){
		$('#managerFrom').keydown(function(e){
			if (e.keyCode == 13) {
				e.preventDefault();
				getRadio();
			}
		});
	});
	function editManager(id,status){
		if(status == '0'){
			window.location.href = '${root}/scms/manager/addOrUpdate.do?id='+id;
		}else{
			layer.msg('确认删除？', {
		        time: 0, //20s后自动关闭
		        btn: ['删除', '取消'],
		        yes: function(index){
			        layer.close(index);
			        $.post("${root}/scms/manager/deleteScmsManager.do",{id:id},function(data){
						if(data.success){
							layer.msg(data.message, {
							    icon: 1,
							    time: 2000 //2秒关闭（如果不配置，默认是3秒）
							}, function(){
								location.reload();
							});  
						}else{
							layer.msg(data.message, {icon: 5});
						}
					 },'json');
			    }
		    });
		}
    }
    function getRadio(){
    	var url="${root}/scms/manager/listPage.do?isUpdate=manager";
		var managerName= $('input[name="managerName"]').val();
		if(managerName != null &&managerName != undefined && managerName != ''){
			   url=url+"&managerName="+managerName;
		}
		location.href=url;
	}
</script>
</body>
</html>