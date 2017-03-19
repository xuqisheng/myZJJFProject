<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>便利店催单信息</title>
	<%@ include file="../common/head.jsp"%>
</head>
<body>
<div class="wrap-bd">
    <div class="mb-small">
        <form action="${root }/kefu/reminder/getAllReminderByParam.do" method="post" >
            <label>所属定格：</label>
            <select class="select" id="spGroupId" name="spGroupId">
                <option value="">请选择</option>
                <c:forEach var="spGroup" items="${spGroupList }">
                	<option <c:if test="${spGroup.id == reminderRo.spGroupId }">selected="selected"</c:if> value="${spGroup.id }">${spGroup.name }</option>
                </c:forEach>
            </select>
            <label class="ml-default">批发商：</label>
            <input class="input input-search-text" type="text" name="supplierNameOrTel" value="${reminderRo.supplierNameOrTel }" placeholder="批发商名称/联系电话">
            <label class="ml-default">商铺信息：</label>
            <input class="input input-search-text" type="text" name="storeNameOrTel" value="${reminderRo.storeNameOrTel }" placeholder="商铺名称/联系电话">
            <input class="input input-search-button ml-default" value="搜索" type="submit">
        </form>
    </div>
    <table class="table-list table-border">
        <thead class="table-thead">
        <tr>
            <th>序号</th>
            <th>商铺名称</th>
            <th>联系方式</th>
            <th>催单时间</th>
            <th>批发商名称</th>
            <th>联系方式</th>
            <th>订单编号</th>
            <th>下单时间</th>
            <th>处理状态</th>
           <!--  <th>派单时间</th>
            <th>提单时间</th>
            <th>打印时间</th> -->
        </tr>
        </thead>
        <tbody class="table-tbody">
        
        <c:choose>
        	<c:when test="${fn:length(reminderVoList)==0}">
        		 <tr>
		            <td colspan="11">无数据</td>
		        </tr>
        	</c:when>
        	<c:otherwise>
	        	<c:forEach var="reminderVo" items="${reminderVoList }" varStatus="var">
	        		<tr>
			            <td>${(page.pageIndex-1)*10+(var.index+1) }</td>
			            <td>${reminderVo.storeName }</td>
			            <td>${reminderVo.userTel }</td>
			            <td><fmt:formatDate value="${reminderVo.addTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			            <td>${reminderVo.spName }</td>
			            <td>${reminderVo.spTel }</td>
			            <td>${reminderVo.orderId }</td>
			            <td><fmt:formatDate value="${reminderVo.payTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			            <c:if test="${reminderVo.processStatus==0 }">
			            	<td><span style="color:red">未处理</span></td>
			            </c:if>
			            <c:if test="${reminderVo.processStatus==1 }">
			            	<td><span style="color:gred">已处理</span></td>
			            </c:if>
			            <%-- <td>${reminderVo }</td>
			            <td>${reminderVo }</td> --%>
			        </tr>
			    </c:forEach>
        	</c:otherwise>
        </c:choose>
        </tbody>
    </table>
    <c:if test="${fn:length(reminderVoList)>0}">
		<%@ include file="../common/pagination-kk.jsp"%>
	</c:if>
</div>
</body>
</html>