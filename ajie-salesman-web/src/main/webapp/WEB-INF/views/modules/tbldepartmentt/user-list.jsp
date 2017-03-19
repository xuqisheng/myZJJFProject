<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>部门信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
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
		<table class="table-list table-border" border="0" style="border:1px solid #4169E1;" cellpadding="0"cellspacing="1">
			<thead class="table-thead" >
				<tr class="table-header">
				    <th><input type="checkbox" id="chk_all" name="chk_all"></th>
					<th width="150px;">帐号</th>
					<th width="150px;">姓名</th>
					<th width="150px;">部门</th>
					<th>职位</th>					
				</tr>
			</thead>
				<tbody class="table-tbody">
					<c:forEach var="user" items="${user}" varStatus="status">
						<tr>
						<td><input type="checkbox" onclick="setSelectAll();" name="chk_list" value="'+user.deptId+'"></td>
							<td>${user.mobile}<input type="hidden" name="leaderId" value="${user.id}"/></td>
						    <td>${user.userName}</td>
							<td>${user.deptName}</td>
							<td>${user.parentName}</td>
						</tr>
					</c:forEach>
				</tbody>
		</table>
	</div>
</div>
		<div class="form-actions">
		<%-- <shiro:hasPermission name="tbldepartmentt:tblDepartmentt:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission> --%>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
<script type="text/javascript">
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
	//判断用户是否已经存在
	var errorCount = 0;
	$(".table-tbody",window.parent.document).find("tr").each(function(){ 
		var $parentThis = $(this);
		$("input[name='chk_list']:checked").each(function(){
			if($(this).parents('tr').children().eq(2).html()==$parentThis.children().eq(2).html()){ 
				errorCount++;
				alert($(this).parents('tr').children().eq(2).html()+"已经存在!");
				return;
			}
		});
	});
	//如果用户已存在就跳出循环
	if(errorCount>0){
		return ;
	}
	
	var userIds = "",overlapUser = "";
	
	$(".table-tbody",window.parent.document).find("tr").each(function(){ // 遍历选中的checkbox
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
	
	/* alert(html);
	console.log(html); */
	
	/* var lenNum = overlapUser.length;
	if(lenNum>0){
		 overlapUser = overlapUser.selectUser(0,lenNum-1);
		alert("您已添加了【"+overlapUser+"】等领导！");
		return;
	} */
	
	
	//$("#xiaojg",window.parent.document).find(".table-tbody").append(html);
	parent.selectUserCallback(html); 
}
</script>
</body>
</html>