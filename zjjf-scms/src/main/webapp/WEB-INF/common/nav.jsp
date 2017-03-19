<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setAttribute("root", request.getContextPath()); %>
<nav id="nav">
	<c:if test="${loginStr != null && loginStr != '' && loginStr=='supply' }">
		<h1 class="logo"></h1>
	</c:if>
	<c:if test="${loginStr != null && loginStr != '' && loginStr=='dealer' }">
		<h1 class="logo-dealer"></h1>
	</c:if>
	<c:if test="${loginStr != null && loginStr != '' && loginStr=='warehouse' }">
		<h1 class="logo-warehouse"></h1>
	</c:if>
    <ul>
        <c:forEach items="${ScmsAuthVoList }" var="ScmsAuthVo" varStatus="status">
            <li>
                <c:if test="${ScmsAuthVo.type == 2}">
                    <div class="category <c:if test="${status.index==0 }">active</c:if>">
                        <c:choose>
                            <c:when test="${ScmsAuthVo.authName=='采购中心'}">
                                <a href="${root}${ScmsAuthVo.action}" target="_blank"><i class="icon ${ScmsAuthVo.icon }"></i>${ScmsAuthVo.authName }</a>
                            </c:when>
                            <c:otherwise>
                                <a href="${root}${ScmsAuthVo.action}" target="mainiframe"><i class="icon ${ScmsAuthVo.icon }"></i>${ScmsAuthVo.authName }</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </c:if>
                <c:if test="${ScmsAuthVo.type == 1}">
                    <div class="category"
                        <c:choose>
                            <c:when test="${status.index==0 }">data-direction="up"</c:when>
                            <c:otherwise> data-direction="down"</c:otherwise>
                        </c:choose>>
                        <i class="icon ${ScmsAuthVo.icon }"></i>${ScmsAuthVo.authName }<i class="icon-direction"></i>
                    </div>
                </c:if>
                <c:if test="${ScmsAuthVo.auths != null }">
                    <div class="subcategory" <c:if test="${status.index==0 }">style="display: block;"</c:if> >
                        <c:forEach items="${ScmsAuthVo.auths }" var="auth" varStatus="authStatus">
                            <a href="${root}${auth.action}" target="mainiframe" <c:if test="${status.index==0 && authStatus.index==0}">class="active"</c:if>>${auth.authName }</a>
                        </c:forEach>
                    </div>
                </c:if>
            </li>
        </c:forEach>

        <%--<li>
          <div class="category" data-direction="up">
              <i class="icon icon-goods"></i>
           		   仓库管理
              <i class="icon-direction"></i>
          </div>
          <div class="subcategory" style="display: block;">
              <a target="mainiframe" href="${root}/erp/warehouse/getWarehouseBySupplierId.do">仓库管理</a>
              <a target="mainiframe" href="${root}/erp/warehouse/getWhareaBySupplierId.do">库区管理</a>
              <a target="mainiframe" href="${root}/erp/warehouse/getWhpositionBySupplierId.do">库位管理</a>
          </div>
      </li>--%>
    </ul>
</nav>
