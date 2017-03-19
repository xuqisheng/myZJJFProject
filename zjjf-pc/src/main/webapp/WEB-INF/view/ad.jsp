<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%	request.setAttribute("root", request.getContextPath());%>
<%@ include file="./taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>广告管理</title>
    <link href="${root}/resources-admin/css/normalize.css" rel="stylesheet">
    <link href="${root}/resources-admin/css/comm.css?v000" rel="stylesheet">
    <link href="${root}/resources-admin/css/recruit.css?v000" rel="stylesheet">
    <script src="${root}/resources-admin/plugs/jquery/jquery-1.12.2.min.js"></script>
    <script src="${root}/resources-admin/js/recruit.js?v111"></script>
    <script type="text/javascript">
		var dataPath = "${root}";
	</script>
</head>
<body>
    <div class="w g-logo"></div>
    <div class="w wrap g-nav">
        <ul>
            <li><a href="${root}/gotoManageHome.do">主页</a></li>
            <li><a href="${root}/pc/recruit/RecruitMgPage.do">招聘管理</a></li>
            <li><a href="${root}/pc/join/toJoinJsp.do">合作加盟管理</a></li>
            <li><a href="${root}/pc/advertisement/gatAllAdvertisement.do" class="active">广告管理</a></li>
            <li class="logout"><a href="javascript:alert('Under construction……')">退出</a></li>
        </ul>
    </div>
    <div class="w g-breadcrumb">
        <a href="/">主页</a> &raquo; <a href="recruit.html" class="active">广告管理</a>
    </div>
    <div class="w wrap clearfix">
        <div class="g-contain">
            <div class="main">
	            <p class="g-title">广告列表</p>
	            <a href="${root }/pc/advertisement/returnEditPage.do" class="g-title" target="_blank">添加广告</a>
	            <table class="table" cellpadding="0" cellspacing="0">
	                <thead>
	                    <tr>
	                        <th>广告图片</th>
	                        <th>广告名称</th>
	                        <th>广告位置</th>
	                        <th>添加时间</th>
	                        <th>上架时间</th>
	                        <th>下架时间</th>
	                        <th>状态</th>
	                        <th>操作</th>
	                    </tr>
	                </thead>
	                <tbody>
	                <c:forEach var="ad" items="${adList}">
	                    <tr>
	                        <td>
	                        	<a href="http://www.izjjf.cn/${ad.adUrl }" target="_blank">
			                        <img src="http://www.izjjf.cn/${ad.adUrl }" width="90" height="90" alt="缩略图" onerror="javascript:this.src=http://www.izjjf.cn/group1/M00/00/79/cEpChlbhNLKAPH1VAAAKDKR6XpI313.png">
			                    </a>
	                        </td>
	                        <td>${ad.name}</td>
		                        <c:choose>
		                        	<c:when test="${ad.positionId==0 }">
		                        		<td>首页广告</td>
		                        	</c:when>
		                        	<c:otherwise>
		                        		<td>暂无广告位</td>
		                        	</c:otherwise>
		                        </c:choose>
	                        <td><fmt:formatDate value="${ad.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                        <td><fmt:formatDate value="${ad.upTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                        <td><fmt:formatDate value="${ad.nextTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		                        <c:choose>
		                        	<c:when test="${ad.status == 0}">
		                        		<td><span style="color:red;">已下架</span></td>
		                        	</c:when>
		                        	<c:otherwise>
		                        		<td><span style="color:green;">已上架</span></td>
		                        	</c:otherwise>
		                        </c:choose>
	                        <td>
		                        <c:choose>
		                        	<c:when test="${ad.status == 0}">
		                        		<input type="button" value="上架" onclick="upAndNext('${ad.id}',1)">
		                        	</c:when>
		                        	<c:otherwise>
		                        		<input type="button" value="下架" onclick="upAndNext('${ad.id}',0)">
		                        	</c:otherwise>
	                        	</c:choose>
	                        	<c:choose>
		                        	<c:when test="${ad.isDelete == false}">
		                        		<input type="button" value="删除" onclick="isdelete('${ad.id}',1)">
		                        		<input type="button" value="编辑" onclick="edit('${ad.id}')">
		                        	</c:when>
		                        	<c:otherwise>
		                        		<input type="button" value="恢复" onclick="isdelete('${ad.id}',0)">
		                        		<input type="button" value="编辑" >
		                        	</c:otherwise>
		                        </c:choose>
	                        </td>
	                    </tr>
	                </c:forEach>
	                </tbody>
	            </table> 
            </div>
        </div>
    </div>
    <%@ include file="./comm/footer.jsp"%>
</body>
<script>
function edit(id){
	if(id != null && id != '' && id != 'null' && id != 'undefined'){
		location.href = "${root }/pc/advertisement/returnEditPage.do?id="+id;
	}
}

/* 上下架 */
function upAndNext(id,status){
	console.log(id+"|"+status);
	$.post("${root }/pc/advertisement/updateStatus.do",{id:id,status:status},function(data){
		if(data.success){
			location.reload();
		}
	})
}

/* 删除恢复 */
function isdelete(id,isDelete){
	console.log(id+"|"+isDelete);
	$.post("${root }/pc/advertisement/updateIsDelete.do",{id:id,isDelete:isDelete},function(data){
		if(data.success){
			location.reload();
		}
	})
} 
</script>
</html>