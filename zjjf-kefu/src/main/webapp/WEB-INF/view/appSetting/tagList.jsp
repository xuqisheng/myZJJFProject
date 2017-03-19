<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>app标签管理</title>
<%@ include file="../common/head.jsp"%>
<%@ include file="../common/autocomplete.jsp"%>
</head>
<body>
	<div>
		<div class="clearfix" style="padding:0;margin:0">
			<p class="fr va-t" style="line-height:65px;margin:0"><a href="${root}/kefu/appModule/returnAddOrEditPage.do?action=1"><button class="button button-default">添加标签</button></a></p>
		</div>
			<table class="table-list table-border">
				<thead class="table-thead">
					<tr>
						<td>名称</td>
						<td>描述</td>
						<td>图片</td>
						<td>所在模板</td>
						<td>排序</td>
						<td>操作</td>
					</tr>
				</thead>
				<tbody class="table-tbody">
				<c:choose>
					<c:when test="${appTagList != null && appTagList.size() > 0}">
						<c:forEach items="${appTagList }" var="moduleDetail">
							<tr>
								<td>${moduleDetail.name }<input type="hidden" value="${moduleDetail.id }" class="id"></td>
								<td>${moduleDetail.remark }</td>
								<td><img style="width:50px;height:50px; alt="" src="${USER_FASTFDSPREURL}${moduleDetail.picUrl }" onerror="javascript:this.src='${USER_DEFAULTIMG_URL}'"></td>
								<td>${moduleDetail.modelName }</td>
								<td>${moduleDetail.orderId }</td>
								<td>
									<c:choose>
										<c:when test="${moduleDetail.isDelete}">
											<input type="button" value="恢复" class="button button-operate delBtn">
											<input type="hidden" value="false" class="delete">
											<a href="#" class="button button-operate">编辑</a>
										</c:when>
										<c:otherwise>
											<input type="button" value="删除" class="button button-operate delBtn">
											<input type="hidden" value="true" class="delete">
											<a href="${root}/kefu/appModule/returnAddOrEditPage.do?action=2&id=${moduleDetail.id }" class="button button-operate">编辑</a>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr><td colspan="6">暂无数据</td></tr>
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
		url:'${root}/kefu/appModule/updateDelOrRecoTag/'+id+'/'+isdelete+'.do',
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