<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>钱包明细 - 明细详情</title>
    <%@ include file="../common/head.jsp"%>

    <script src="../resources/vendor/My97DatePicker/WdatePicker.js"></script>
    <script src="../resources/js/comm.js"></script>
</head>
<body class="wrap-bd">
<div class="mb-small">
    <div>
        <form action="${root}/wallet/toStoreWalletLog.do" method="post">
	        <label for="J_timeS">交易时间：</label>
	        <input type="hidden" value="${id}" name="id">
	        <input type="text" name="startTime" value="${startTime}" class="input input-date" id="J_timeS"> -
	        <input type="text" name="endTime" value="${endTime}" class="input input-date" id="J_timeE">
	        <input type="submit" name="" value="搜索" class="input input-search-button ml-default" id="search">
        </form>
    </div>
</div>
<div>
    <table class="table-list">
        <thead>
        <tr>
            <th>序号</th>
            <!-- <th>用户名称</th>
            <th>手机号码</th> -->
            <th>金额</th>
            <th>交易类型</th>
            <th>交易用途</th>
            <!-- <th>交易父订单号</th>
            <th>交易子订单号</th> -->
            <th>交易流水号</th>
            <th>交易时间</th>
        </tr>
        </thead>
        <tbody id="J_tableBbody">
        <c:choose>
          <c:when test="${list!= null && list.size()>0}">
            <c:forEach items="${list}" var="item" step="1" varStatus="status" begin="0">
               <tr>
	            <td>${status.index+1}</td>
	            <%-- <td>${item.userName}</td>
	            <td>${item.mobile}</td> --%>
	            <td>${item.amount}</td>
	            <td>${item.businessTypeStr}</td>
	            <td>${item.purposeStr}</td>
	            <%-- <td>${item.faOrderId}</td> --%>
	            <%-- <td>${item.sonOrderId}</td> --%>
	            <td>${item.businessLogNo}</td>
	            <td><fmt:formatDate value="${item.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
             </tr>           
            </c:forEach>
          </c:when>
          <c:otherwise>
            <tr>
              <td colspan="8">无数据</td>
            </tr>
          </c:otherwise>
        </c:choose>
        <!-- <tr>
            <td>1</td>
            <td>店铺001</td>
            <td>13712345678</td>
            <td>充值/订单支出</td>
            <td>12345678901</td>
            <td>12345678901</td>
            <td>2016-6-16 12:12:12</td>
        </tr> -->
       <!--  <tr>
            <td colspan="7">无数据</td>
        </tr> -->
        </tbody>
    </table>
    <c:if test="${fn:length(list)>0}">
		<%@ include file="../common/pagination-kk.jsp"%>
	</c:if>
</div>
<script>
    $(function() {
        dateRange('#J_timeS', '#J_timeE', 1);
    });
</script>
</body>
</html>
