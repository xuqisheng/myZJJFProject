<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setAttribute("root", request.getContextPath()); %>
<nav id="nav">
    <h1></h1>
    <ul>
        <li>
            <div class="category active"><a href="${root}/account/index.do" target="mainiframe"><i class="icon icon-account"></i>帐号管理</a></div>
        </li>
        <li>
            <div class="category"><a href="${root}/device/index.do" target="mainiframe"><i class="icon icon-account"></i>设备管理</a></div>
        </li>
        <li>
            <div class="category"><a href="${root}/dept/index.do" target="mainiframe"><i class="icon icon-account"></i>部门管理</a></div>
        </li>
        <li>
            <div class="category"><a href="${root}/tmpl/index.do" target="mainiframe"><i class="icon icon-account"></i>模板配置</a></div>
        </li>
        <li>
            <div class="category"><a href="${root}/dict/index.do" target="mainiframe"><i class="icon icon-account"></i>字典管理</a></div>
        </li>
        <li>
            <div class="category"><a href="${root}/log/index.do" target="mainiframe"><i class="icon icon-account"></i>APP日志监控</a></div>
        </li>
        <li>
            <div class="category"><a href="${root}/log/appLog.do" target="mainiframe"><i class="icon icon-account"></i>APP运行日志</a></div>
        </li>
<!--         <li> -->
<%--             <div class="category"><a href="${root}/spgLine/index.do" target="mainiframe"><i class="icon icon-account"></i>定格管理</a></div> --%>
<!--         </li> -->
        <li>
            <div class="category"><a href="${root}/shop/index.do" target="mainiframe"><i class="icon icon-account"></i>客户管理</a></div>
        </li>
        
    </ul>
</nav>