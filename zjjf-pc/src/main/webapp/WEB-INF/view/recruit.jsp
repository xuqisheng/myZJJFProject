<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%	request.setAttribute("root", request.getContextPath());%>
<%@ include file="./taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>招聘管理</title>
    <link href="${root}/resources-admin/css/normalize.css" rel="stylesheet">
    <link href="${root}/resources-admin/css/comm.css?v" rel="stylesheet">
    <link href="${root}/resources-admin/css/recruit.css?v" rel="stylesheet">
    <script src="${root}/resources-admin/plugs/jquery/jquery-1.12.2.min.js"></script>
    <script>
    var ROOT = '${root}';
    </script>
    <script src="${root}/resources-admin/js/recruit.js?v"></script>
</head>
<body>
    <div class="w g-logo"></div>
    <div class="w wrap g-nav">
        <ul>
            <li><a href="${root}/gotoManageHome.do" id="index">主页</a></li>
            <li><a href="${root}/pc/recruit/RecruitMgPage.do" id="recruit" class="active">招聘管理</a></li>
            <li><a href="${root}/pc/join/toJoinJsp.do" id="join">合作加盟管理</a></li>
            <li><a href="${root}/pc/advertisement/gatAllAdvertisement.do" id="ad">广告管理</a></li>
            <li class="logout"><a href="javascript:alert('Under construction……')">退出</a></li>
        </ul>
    </div>
    <div class="w g-breadcrumb">
        <a href="${root}/gotoManageHome.do">主页</a> &raquo; <a href="${root}/pc/recruit/RecruitMgPage.do" class="active">招聘管理</a>
    </div>
    <div class="w wrap clearfix">
        <div class="g-contain">
            <div class="main">
	            <p class="g-title">招聘信息列表</p>
	            <table class="table" cellpadding="0" cellspacing="0">
	                <thead>
	                    <tr>
	                        <th>招聘类型</th>
	                        <th>职位名称</th>
	                        <th>更新时间</th>
	                        <th>操作</th>
	                    </tr>
	                </thead>
	                <tbody>
	                <c:choose>
	                	<c:when test="${recruits != null && recruits.size()>0}">
	                		<c:forEach var="recruit"  items="${recruits}" varStatus="status">
		                    <tr <c:if test="${(status.index%2)==0 }">class="odd"</c:if>>
		                        <td>${recruit.typeName}</td>
		                        <td>${recruit.postName}</td>
		                        <td>
		                            <fmt:formatDate value="${recruit.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
		                        </td>
		                        <td class="action">
		                            <a href="#recruitId" name="${recruit.id}" class="view J_view">查看</a>
		                            <a href="#recruitId" name="${recruit.id}" class="edit J_edit">编辑</a>
		                            <a href="#recruitId" name="${recruit.id}" class="delete J_delete">删除</a>
		                        </td>
		                    </tr>
		                    </c:forEach>
	                	</c:when>
	                	<c:otherwise>
	                		<tr><td colspan="4">无数据</td></tr>
	                	</c:otherwise>
	                </c:choose>
	                </tbody>
	            </table>
	            <p class="g-title">招聘信息编辑</p>
				<fieldset>
                    <form id="recruitForm" method="post" action="">
                        <input type="hidden" name="id" id="recruitId" />
                        <p>
                            <label>招聘类型:</label>
                            <select name="recruitTypeId" id="recruitTypeId">
                            	<option value="-1">请选择</option>
                            	<c:if test="${recruitTypeList != null && recruitTypeList.size()>0 }">
                            		<c:forEach var="recruitType" items="${recruitTypeList }">
                            			<option value="${recruitType.id }">${recruitType.typeName }</option>
                            		</c:forEach>
                            	</c:if>
                            </select>
                        </p>
                        <p>
                            <label>职位名称:</label>
                            <input type="text" class="text-long" name='postName' id="postName" />
                        </p>
                        <p>
                            <label>内容:</label>
                            <%@ include file="./uediter.jsp"%>
                        </p>
                         <input id="recruitSubmit"  type="button" value="提交" />
                    </form>
                </fieldset>
            </div>
        </div>
    </div>
    <%@ include file="./comm/footer.jsp"%>
</body>
</html>