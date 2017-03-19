<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%	request.setAttribute("root", request.getContextPath());%>
<%@ include file="./taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>合作加盟管理</title>
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
            <li><a href="${root}/pc/join/toJoinJsp.do" class="active">合作加盟管理</a></li>
            <li><a href="${root}/pc/advertisement/gatAllAdvertisement.do" >广告管理</a></li>
            <li class="logout"><a href="javascript:alert('Under construction……')">退出</a></li>
        </ul>
    </div>
    <div class="w g-breadcrumb">
        <a href="/">主页</a> &raquo; <a href="recruit.html" class="active">合作加盟管理</a>
    </div>
    <div class="w wrap clearfix">
        <div class="g-contain">
            <div class="main">
	            <p class="g-title">合作加盟信息列表</p>
	            <table class="table" cellpadding="0" cellspacing="0">
	                <thead>
	                    <tr>
	                        <th>类型</th>
	                        <th>姓名</th>
	                        <th>电话</th>
	                        <th>名称</th>
	                        <th>地址</th>
	                        <th>留言</th>
	                        <th>提交时间</th>
	                    </tr>
	                </thead>
	                <tbody>
	                <c:forEach var="joinInfo" items="${list}">
	                    <tr>
	                        <td>
	                        <c:if test="${empty joinInfo.type}"></c:if>
	                        <c:if test="${joinInfo.type==1}">
	                                                    便利店
	                        </c:if>
	                        <c:if test="${joinInfo.type==2}">
	                                                    合作商
	                        </c:if>
	                        <c:if test="${joinInfo.type==3}">
	                                                    品牌商
	                        </c:if>
	                        </td>
	                        <td>${joinInfo.supplierName}</td>
	                        <td>${joinInfo.mobile}</td>
	                        <td>${joinInfo.col1}</td>
	                        <td>${joinInfo.col2}</td>
	                        <td>${joinInfo.col3}</td>
	                        <td><fmt:formatDate value="${joinInfo.regTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                    </tr>
	                </c:forEach>
	                </tbody>
	            </table>
            </div>
        </div>
    </div>
    <%@ include file="./comm/footer.jsp"%>
</body>
</html>