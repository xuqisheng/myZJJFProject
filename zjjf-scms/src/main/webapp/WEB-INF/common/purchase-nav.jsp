<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setAttribute("root", request.getContextPath()); %>

<nav id="nav">
    <section class="w">
        <div class="welcome">
            <img src="${root}/resources/purchase/images/top-logo.png" alt="">  欢迎来到  转角街坊
        </div>
        <div class="userinfo">
                             您好  ${SUPPLY_SESSION_KEY.supplierName}
<!--             <span class="separator">|</span>
            <a href="javascript:window.close();" class="logout">关闭</a> -->
        </div>
    </section>
</nav>