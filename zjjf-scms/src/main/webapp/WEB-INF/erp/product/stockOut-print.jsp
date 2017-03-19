<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>商品打印单</title>
    <%@ include file="../../common/head.jsp"%>
</head>
<style type="text/css">
	.todo{
		float: left;
	}
	ul,li{
		list-style: none;margin-bottom: 18px;
	}

	.lr li{
		float: left;width: 33%;margin-bottom: 0;
	}
	.table-print td{
		line-height: 20px;
	}
</style>
<body>
    <h3 class="ta-c" style="color:#333;font-size: 20px">JFUN转角&nbsp;退货单</h3>
    <div class="clearfix">
	    <ul class="todo" style="width: 42%;">
	    	<li>退货单号：${detail.orderId}</li>
	    	<%--<li>采购单号：${}</li>--%>
	    	<li>退货时间：<fmt:formatDate value="${detail.taskTime}" pattern="yyyy-MM-dd"/></li>
	    </ul>
	    <ul class="todo" style="width: 30%;">
	    	<li>供应商编号：${detail.managerCode}</li>
	    	<li>供应商名称：${detail.managerName}</li>
	    	<li>退货原因：${detail.remark}</li>
	    </ul>
	    <ul class="todo" style="width: 28%;">
	    	<li>仓库：${detail.whName}</li>
	    </ul>
    </div>
    <table class="table-print mb-default">
        <thead>
	        <tr>
	            <th>序号</th>
	            <th>箱码</th>
	            <th>商品条形码</th>
	            <th>商品名称</th>
	            <th>规格</th>
	            <th>单位</th>
	            <th>单价</th>
	            <th>数量</th>
	            <th>小计</th>
	        </tr>
        </thead>
        <tbody>
            <c:forEach items="${detail.details}" var="ele" varStatus="eleStat">
                <tr>
                    <td>${eleStat.count}</td>
                    <td>${ele.barCode}</td>
                    <td>${ele.mdseId}</td>
                    <td>${ele.name}</td>
                    <td>${ele.spec}</td>
                    <td>${ele.pkg}</td>
                    <td>${ele.areaPrice}</td>
                    <td>${ele.quantity}</td>
                    <td>${ele.totalPrice}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <ul class="lr clearfix mt-small">
    	<li>仓库联系方式：${warehouse.mobile}</li>
    	<li style="width:36%">制单时间：<fmt:formatDate value="${detail.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
    	<li style="width:30%"><span class="mr-default">退货数量:${detail.itemQuantity}</span><span class="ml-default">总计:${detail.itemPrice}</span></li>
    </ul>
    <ul class="lr clearfix">
    	<li>财务签名：</li>
    	<li style="width:36%">供应商签名：</li>
    	<li style="width:30%">仓库签名(盖章):</li>
    </ul>
    <script>
        $(function() {
            window.print();
            // chrome新窗口立即关闭处理
            setTimeout(function() {
                window.close();
            }, 500);
        });
    </script>
</body>
</html>
