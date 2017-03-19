<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>app模块管理</title>
<%@ include file="../common/head.jsp"%>
<%@ include file="../common/autocomplete.jsp"%>
<script src="${root}/resources/vendor/jquery/ajaxfileupload.js"></script>
</head>
<body>
	<div>
		<div class="clearfix" style="padding:0;margin:0">
			<p class="fr va-t" style="line-height:65px;margin:0"><a href="${root}/kefu/appModule/toAddOrEditPage/1.do"><button class="button button-default">添加模板</button></a></p>
		</div>
		<table class="table-list table-border">
			<thead class="table-thead">
				<tr>
					<td>名称</td>
					<td>描述</td>
					<td>模块类型</td>
					<td>(开始/结束)时间</td>
					<td>图片</td>
					<td>类型</td>
					<td>是否可见</td>
					<td>广告位</td>
					<td>排序</td>
					<td>状态</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody class="table-tbody">
			<c:choose>
				<c:when test="${appModuleList != null && appModuleList.size() >0}">
					<c:forEach var="appModule" items="${appModuleList}">
						<tr>
							<td>${appModule.name }<input type="hidden" class="id" value="${appModule.id }"></td>
							<td>${appModule.remark }</td>
							<td>${appModule.codeName }</td>
							<td>
								<fmt:formatDate value="${appModule.beginTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
								/
								<fmt:formatDate value="${appModule.stopTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
							</td>
							<td><img style="width:50px;height:50px; alt="" src="${USER_FASTFDSPREURL}${appModule.viewImg }" onerror="javascript:this.src='${USER_DEFAULTIMG_URL}'"></td>
							<td>${appModule.typeName }</td>
							<td>${appModule.seeName }</td>
							<td>${appModule.boardName }</td>
							<td>${appModule.orderId }</td>
							<td>${appModule.staName }</td>
							<td>
								<c:choose>
									<c:when test="${appModule.isDelete }">
										<input type="button" value="恢复" class="button button-operate delBtn">
										<input type="hidden" value="false" class="delete">
										<a href="#" class="button button-operate">编辑</a>
									</c:when>
									<c:otherwise>
										<input type="button" value="删除" class="button button-operate delBtn">
										<input type="hidden" value="true" class="delete">
										<a href="${root}/kefu/appModule/toAddOrEditPage/2.do?id=${appModule.id }" class="button button-operate">编辑</a>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr><td colspan="11">暂无数据</td></tr>
				</c:otherwise>
			</c:choose>
			</tbody>
		</table>
	</div>
</body>
<script type="text/javascript">
$('.delBtn').on('click',function(){
	var j_tr = $(this).parent().parent();
	var id=j_tr.find('.id').val();
	var isdelete=j_tr.find('.delete').val();
	$.ajax({
		url:'${root}/kefu/appModule/updateDelOrRecoModule/'+id+'/'+isdelete+'.do',
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
</script>
</html>