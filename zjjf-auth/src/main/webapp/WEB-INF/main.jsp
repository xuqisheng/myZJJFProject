<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>街坊店宝</title>
    <meta name="description" content="街坊店宝后台权限管理系统">
    <meta name="keywords" content="街坊店宝后台权限管理系统">
	<%@ include file="common/head.jsp"%>
    <link href="${root}/resources/css/layout.css?vv12" rel="stylesheet">
    <script src="${root}/resources/js/layout.js"></script>
</head>
<body>
<header id="header">
    <img src="${root}/resources/images/logo-top.png" alt="">&nbsp;转角街坊
    <div class="info"><span class="username">${USER_SESSION_KEY.userName}</span>欢迎回来 | <a href="${root}/auth/authority/doLoginOut.do">注销</a></div>
</header>
<main id="main">
    <nav id="nav">
        <h1></h1>
        <ul>
	        <c:forEach items="${authVoList }" var="authVo" varStatus="status">
	    		<li>
	    			<c:if test="${authVo.type == 1 }">
	    				<div class="category" data-direction="down"><i class="icon ${authVo.icon }"></i>${authVo.authName }<i class="icon-direction"></i></div>
	    				<div class="subcategory">
	    					<c:forEach items="${authVo.auths }" var="auth" varStatus="status">
	    						<a href="${root }${auth.action }" target="mainiframe">${auth.authName }</a>
	    					</c:forEach>
	    				</div>
	    			</c:if>
	    			<c:if test="${authVo.type == 2 }">
	    				<div class="category active">
	    					<div class="category">
	    						<a href="${root}${authVo.action}" target="mainiframe"><i class="icon ${authVo.icon }"></i>${authVo.authName }</a>
	    					</div>
	    				</div>
	    			</c:if>
	    		</li>
	   		</c:forEach>
	   		
	   		<%--
		        <li>
		                <div class="category" data-direction="down"><i class="icon icon-customer"></i>系统管理<i class="icon-direction"></i></div>
		                <div class="subcategory">
		                    <a href="${root }/auth/auth/getAuthListPage.do" target="mainiframe">权限管理</a>
		                    <a href="${root }/auth/role/getRoleListPage.do" target="mainiframe">角色管理</a>
		                    <a href="${root }/auth/user/getUserListPage.do" target="mainiframe">用户管理</a>
		                    <a href="${root }/auth/job/toJobIndex.do" target="mainiframe">岗位管理</a>
		                </div>
		          </li>
		        <li>
		                <div class="category" data-direction="down"><i class="icon icon-customer"></i>权限系统系统管理<i class="icon-direction"></i></div>
		                <div class="subcategory">
		                    <a href="${root }/auth/admAuth/getAuthListPage.do" target="mainiframe">权限管理</a>
		                    <a href="${root }/auth/admRole/getRoleListPage.do" target="mainiframe">角色管理</a>
		                    <a href="${root }/auth/admUser/getUserListPage.do" target="mainiframe">用户管理</a>
		                </div>
		          </li>
         --%>
   		</ul>
    </nav>
    <article id="article">
		<iframe id="mainiframe" name="mainiframe" src="${root }/view/index/index.jsp" width="100%" height="100%" frameborder="0"></iframe>
	</article>
</main>
</body>
</html>