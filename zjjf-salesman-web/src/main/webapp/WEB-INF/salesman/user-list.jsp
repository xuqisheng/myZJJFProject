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
	<div class="mb-default title">部门领导选择列表</div>
	<div class="op-section clearfix">
		<form action="" id="accountForm" method="" class="fl">
			<input type="hidden" value="${salesman.deptId}" name="deptId"/>
			<input class="input-search-text" type="text" id="userName" name="userName" placeholder="请输入用户帐号/姓名" value="">
			<input type="button" class="input-search-button" value="搜索"  id="btnSearch"/>
			<input type="button" class="input-search-button" value="确定"  id="btnConfirm" onclick="selectUser();"/>
		</form>
	</div>
	<div>
		<table class="table-list table-border">
			<thead class="table-thead">
				<tr class="table-header">
				    <th><input type="checkbox" id="chk_all" name="chk_all"></th>
					<th>帐号</th>
					<th>姓名</th>
					<th>部门</th>
					<th>职位</th>
					
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
                 url: '${root}/account/selectUserList.do',
                 params: $('#accountForm').serializeArray(),
                 success: function(data) {
                     var html='';
                     $.each(data.list, function(i,item) {
                    	html+='<tr>';
                    	html+='<td><input type="checkbox" onclick="setSelectAll();" name="chk_list" value="'+item.id+'"></td>';
                     	html+='<td>'+item.mobile+'<input type="hidden" name="leaderId" value="'+item.id+'"/></td>';
                     	html+='<td>'+item.userName+'</td>';
    	                html+='<td>'+item.deptName+'</td>';
    	                html+='<td>'+item.postName+'</td>';
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
		 
		 $('#btnSearch').on('click', function(e) {
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
	
	//复选框的事件  
	$("#chk_all").click(function(){
		//alert($("#chk_all").prop("checked"));
		if($("#chk_all").prop("checked")){
			$("input[name='chk_list']").prop("checked",true);  
		}else{
			$("input[name='chk_list']").prop("checked",false);  
		}
		
	});
	
	//子复选框的事件  
	function setSelectAll(){  
	    //当没有选中某个子复选框时，SelectAll取消选中  
	    if (!$("#chk_list").checked) {  
	        $("#chk_all").prop("checked", false);  
	    }  
	    var chsub = $("input[type='checkbox'][name='chk_list']").length; //获取subcheck的个数  
	    var checkedsub = $("input[type='checkbox'][name='chk_list']:checked").length; //获取选中的subcheck的个数
	    if (checkedsub == chsub) {  
	        $("#chk_all").prop("checked", true);  
	    }  
	}  
	
	//确定选择用户信息
	function selectUser(){
		var userIds = "",overlapUser = "";
		
		$(".table-tbody",window.parent.document).find("tr").each(function() { // 遍历选中的checkbox
			var leaderId = $(this).find("input[name=leaderId]").val();
			userIds+=leaderId+","
		});		
		
		var html = "";
		$("input[name='chk_list']:checked").each(function() { // 遍历选中的checkbox
			n = $(this).parents("tr").index();  // 获取checkbox所在行的顺序
			//$(".table-tbody").find("tr:eq("+n+")").remove();
			var trObj = $(".table-tbody").find("tr:eq("+n+")");
			var userId = $(trObj).find("input[name=leaderId]").val();
			var userName = $(trObj).find("td:eq(2)").text()
			
			if(userIds.indexOf(userId)!=-1){
				overlapUser += userName+","
			}
			
			var tmpHtml = "";
			var tds = $(".table-tbody").find("tr:eq("+n+")").html()+"<td><a data-user='"+userName+"' onclick='delUser(this);' href='#'>删除</a></td>";
			tmpHtml ="<tr>"+tds+"</tr>"; 
			html+=tmpHtml;
			
		});	
		var lenNum = overlapUser.length;
		if(lenNum>0){
			overlapUser = overlapUser.substring(0,lenNum-1);
			alert("您已添加了【"+overlapUser+"】等领导！");
			return;
		}
		
		parent.selectUserCallback(html); 
	}
	
</script>
</body>
</html>