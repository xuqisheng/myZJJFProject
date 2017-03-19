<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>客服系统 - 转角街坊</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
	<%@ include file="common/head.jsp"%>
    <link href="${root}/resources/css/layout.css?v123" rel="stylesheet">
    <script src="${root}/resources/js/layout.js"></script>
</head>
<body>
    <nav id="nav">
    <h1></h1>
    <ul>
    <c:forEach items="${authVoList }" var="authVo" varStatus="status">
        <li>
        <c:if test="${authVo.action != null && authVo.action != ''}">
            <%--
             <c:if test="${status.index==0 }">
                <div class="category active">
            </c:if>
            <c:if test="${status.index!=0 }">
                <div class="category">
            </c:if>
            --%>
            <div class="category">
            <c:if test="${authVo.authName=='采购中心' && authVo.upId=='scms_supply' && authVo.roleRemark=='进销存一级菜单' && authVo.appId==3}">
                <a href="${root}${authVo.action}" target="_blank"><i class="icon ${authVo.icon }"></i>${authVo.authName }</a>
            </c:if>
            <c:if test="${authVo.authName!='采购中心'}">
                <a href="${root}${authVo.action}" target="mainiframe"><i class="icon ${authVo.icon }"></i>${authVo.authName }</a>
            </c:if>
            </div>
        </c:if>
        <c:if test="${authVo.action == null || authVo.action == ''}">
            <%--
            <c:if test="${status.index==0 }">
                <div class="category active" data-direction="down">
            </c:if>
            <c:if test="${status.index!=0 }">
                <div class="category" data-direction="down">
            </c:if>
            --%>
            <div class="category" data-direction="down">
            <i class="icon ${authVo.icon }"></i>${authVo.authName }<i class="icon-direction"></i>
            </div>
        </c:if>
        <c:if test="${authVo.auths != null }">
            <div class="subcategory">
            <c:forEach items="${authVo.auths }" var="auth">
                <a href="${root}${auth.action}" target="mainiframe">${auth.authName }</a>
            </c:forEach>
            </div>
        </c:if>
        </li>
    </c:forEach>
    <%--<li>
            <div class="category" data-direction="down">
              <i class="icon icon-wallet"></i>钱包管理<i class="icon-direction"></i>
            </div>

            <div class="subcategory">

                <a href="${root}/wallet/toRechargeInfo.do" target="mainiframe">充值记录</a>

                <!-- <a href="#" target="mainiframe">提现记录</a> -->

                <a href="${root}/wallet/toRechargeSpVoucher.do" target="mainiframe">充值充劵</a>

                <a href="${root}/wallet/toStoreWallet.do" target="mainiframe">钱包明细</a>

            </div>
        </li>
        <li>
            <div class="category" data-direction="down">
              <i class="icon icon-wallet"></i>单品促销<i class="icon-direction"></i>
            </div>

            <div class="subcategory">
                <a href="${root}/keFu/SKUActive/getAllSKUActive.do" target="mainiframe">单品促销</a>
            </div>
        </li>
    --%>
    <%-- <li>
            <div class="category" data-direction="down"><i class="icon icon-customer"></i>广告管理<i class="icon-direction"></i></div>
            <div class="subcategory">
                <a href="${root }/Customer/AdManage/toIdex.do" target="mainiframe">广告管理</a>
                <a href="${root }/Customer/AdManage/toAdPosition.do" target="mainiframe">广告位管理</a>
            </div>
            <div class="category" data-direction="down"><i class="icon icon-customer"></i>订单管理<i class="icon-direction"></i></div>
            <div class="subcategory">
                <a href="${root }/Customer/SpOrderInfo/GetSpOrderInfos.do?status=1" target="mainiframe">定格管理</a>
                <a href="${root }/Customer/SpComment/getAllCommentByParameter.do" target="mainiframe">订单评论</a>
                <a href="${root }/CornerV2/SpFeedblack/getAllSpFeedbackByParam.do" target="mainiframe">反馈</a>
                <a href="${root }/Customer/AdManage/toAdPosition.do" target="mainiframe">财务管理</a>
            </div>
      </li>--%>
    <!-- <div class="category" data-direction="down"><i class="icon icon-customer"></i>参数设置<i class="icon-direction"></i></div> -->
        <%-- <div class="subcategory">
            <a href="${root }/keFu/systemConfig/getAllConfig.do" target="mainiframe">全局参数</a>
            <a href="${root }/kefu/DBAppConfig/toList.do" target="mainiframe">APP配置方案</a>
            <a href="${root }kefu/appModule/getAppModuleList.do" target="mainiframe">APP模板管理</a>
            <a href="${root}/kefu/appItemLabel/toList.do" target="mainiframe">label管理</a>
        </div> --%>
   <%-- <li>
    <li>
    	<div class="category"><a href="${root }/customer/appStore/TZPage.do" target="mainiframe">店铺分布图</a></div>
    </li>
    <li>
    	<div class="category"><a href="${root }/keFu/SKUActive/getAllSKUActive.do" target="mainiframe">活动</a></div>
    </li>--%>
    </ul>
    </nav>

<main id="main">
    <header id="header">
    <div class="info"><span class="username">${USER_SESSION_KEY.nickName}</span>欢迎回来 | <a href="${root}/kefu/authority/doLoginOut.do">注销</a></div>
    </header>
    <article id="article">
		<iframe id="mainiframe" name="mainiframe" src="${root }/view/index/index.jsp" width="100%" height="100%" frameborder="0"></iframe>
	</article>
</main>
</body>
</html>
