<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%	request.setAttribute("root", request.getContextPath());%>
<%@ include file="./taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>转角街坊官网后台系统</title>
    <link href="${root}/resources-admin/css/normalize.css" rel="stylesheet">
    <link href="${root}/resources-admin/css/comm.css?v000" rel="stylesheet">
    <link href="${root}/resources-admin/css/recruit.css?v000" rel="stylesheet">
</head>
<body>
    <div class="w g-logo"></div>
    <div class="w wrap g-nav">
        <ul>
            <li><a href="${root}/gotoManageHome.do" class="active">主页</a></li>
            <li><a href="${root}/pc/recruit/RecruitMgPage.do">招聘管理</a></li>
            <li><a href="${root}/pc/join/toJoinJsp.do">合作加盟管理</a></li>
            <li><a href="${root}/pc/advertisement/gatAllAdvertisement.do">广告管理</a></li>
            <li class="logout"><a href="javascript:alert('Under construction……')">退出</a></li>
        </ul>
    </div>
    <div class="w g-breadcrumb"></div>
    <div class="w wrap">
        <div class="g-contain">
            <p>&nbsp;</p>
            <p class="g-title">&nbsp;&nbsp;&nbsp;&nbsp;首页！无内容！</p>
            <p>&nbsp;</p>
        </div>
    </div>
    <%@ include file="./comm/footer.jsp"%>
</body>
</html>