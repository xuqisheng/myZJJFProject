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
	<div class="mb-default title">业务员管理</div>
	<div class="op-section clearfix">
		<form action="" id="accountForm" method="" class="fl">
			<input class="input-search-text" type="text" id="userName" name="userName" placeholder="请输入用户帐号/姓名/角色名称" value="">
			<select id="deptSelect" name="deptId" class="select">
                <option value="">请选择部门</option>
                <c:forEach var="deptList" items="${deptList}">
              	  <option value="${deptList.deptId}">${deptList.deptName}</option>
                </c:forEach>
            </select>
			<input type="radio" value="0" name="isDelete" checked="checked">正常
            <input type="radio" value="1" name="isDelete">停用
			<input type="button" class="input-search-button" value="搜索"  id="sub"/>
		</form>
		<ul class="fr" id="orderNav">
			<a href="${root}/account/toAddSalesman.do"><span class="pills">添加用户</span></a>
		</ul>
	</div>
	<div>
		<table class="table-list">
			<thead>
				<tr>
					<th>账号</th>
					<th>姓名</th>
					<th>部门</th>
					<th>岗位</th>
					<th>角色</th>
					<th>创建者</th>
					<th>最后登录时间</th>
					<th>app版本号</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="J_tbody"></tbody>
		</table>
        <%@ include file="../common/pagination.jsp"%>
	</div>
</div>
<script>
	$(function() {
		 $("#jpagination").pagination({
             pageSize: 10,
             remote: {
                 url: '${root}/account/listPage.do',
                 params: $('#accountForm').serializeArray(),
                 success: function(data) {
                     var html='';
                     $.each(data.list, function(i,item) {
                     	html+='<tr><td>'+item.mobile+'</td>';
                     	html+='<td>'+item.userName+'</td>';
    	                html+='<td>'+item.deptName+'</td>';
    	                html+='<td>'+item.postName+'</td>';
    	                if(item.userType==0){
    	                	html+='<td>员工</td>';
    	                }
    	                if(item.userType==1){
    	                	html+='<td>管理员</td>';
    	                }
    	                html+='<td>'+item.createBy+'</td>';
    	                if(item.lastTime == null){
    	                	html+='<td></td>';
    	                }else{
    	                	html+='<td style="color:red">'+item.lastTime+'</td>';
    	                }
    	                if(item.version == null){
    	                	html+='<td></td>';
    	                }else{
    	                	html+='<td>'+item.version+'</td>';
    	                }
    	                html+='<td>';
    	                html+='<a href="${root}/account/toEditSalesman.do?id='+item.id+'&deptId='+item.deptId+'" style="margin-right:10px;">编辑</a>';
    	                if(item.isDelete==0){
    	                	html+='<a data-id="'+item.id+'" data-isdelete="1" onclick="delSalesmanMg(this);">停用</a>';
//     	                	html+='<td><a href="${root}/account/delSalesmanMg.do?='+item.id+'" >停用</a></td></tr>';
    	                }else if(item.isDelete==1){
//     	                	html+='<td><a href="${root}/account/delSalesmanMg.do?='+item.id+'">启用</a></td></tr>';
    	                	html+='<a data-id="'+item.id+'" data-isdelete="0" onclick="delSalesmanMg(this);">启用</a>';
    	                } 
    	                html+='</td></tr>';
    	                
                     });
                     if(html == "") {
                     	html = '<tr><td colspan="10" class="no-data"></td></tr>';
                     }
                     $('#J_tbody').html(html);
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
		
	});
	
	function delSalesmanMg(obj){
		var userId =  $(obj).data("id");
		var isDelete =  $(obj).data("isdelete");
		 $.ajax({
				type: "post",
				url: "${root}/account/delSalesmanMg.do",
				data: "id="+$.trim(userId)+"&isDelete="+isDelete,
				dataType: "json",
				async: false,
				success: function(data) {
				 if(data.success) {
						alert("操作成功!");
						$(obj).data("isdelete",isDelete==0?"1":"0");
						$(obj).text(isDelete==0?"停用":"启用");
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