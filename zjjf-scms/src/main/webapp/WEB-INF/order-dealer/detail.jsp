<%@page import="com.corner.core.config.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>街坊店宝</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="../../resources/vendor/layer/layer.js"></script>
    <script src="${root}/resources/js/comm.js"></script>
</head>
<body class="wrap-bd">
<div class="bg no-print" id="aaa">
    <div class="wrap-bd">
        <div class="clearfix" style="padding-bottom:15px;border-bottom:1px solid #eee;line-height:28px;">
	        <b class="fl mr-default">订单号：${maOrderInfoMgVo.orderId }</b>
	        <div class="fl ml-default">订单状态：
	            <span class="orange">
	            <c:if test="${maOrderInfoMgVo.managerStatus == 1 }">未配送</c:if>
	            <c:if test="${maOrderInfoMgVo.managerStatus == 2 }" >配送中</c:if>
	            <c:if test="${maOrderInfoMgVo.managerStatus == 3 }" >已送达</c:if>
	            </span>
	        </div>
		    <div class="fr">
		        <c:if test="${maOrderInfoMgVo.managerStatus == 1 }"><input type="button" onclick="fahuo('${maOrderInfoMgVo.id}')" value="发货" id="fahuo" class="button"></c:if>
		        <input type="button" id="J_printOrder" value="打印订单" class="button">
		    </div>
        </div>
        <p>
            <span class="mr-default"><span class="label">总金额：</span><span class="orange">${maOrderInfoMgVo.outOfPrice }</span></span>
            <span class="ml-default">总数量：${maOrderInfoMgVo.countNumber }</span>
        </p>
        <p><span class="label">区域：</span>${maOrderInfoMgVo.groupName }</p>
        <p>
            <span class="mr-default"><span class="label">配送地址：</span>${maOrderInfoMgVo.address }</span>
            <span class="ml-default mr-default">收货人：${maOrderInfoMgVo.consignee }</span>
            <span class="ml-default">联系电话：${maOrderInfoMgVo.mobile }</span>
        </p>
        <p><span class="label">订单提交时间：</span><span style="color:#aaa"><fmt:formatDate value="${maOrderInfoMgVo.kfSubmitTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
			<span class="ml-default">付款状态：
			<c:if test="${maOrderInfoMgVo.acStatus == 4 }">
	   			已付款（付款时间：<fmt:formatDate value="${maOrderInfoMgVo.acPayTime }" pattern="yyyy-MM-dd HH:mm:ss"/>）
	   		</c:if>
	   		<c:if test="${maOrderInfoMgVo.acStatus == 1 || maOrderInfoMgVo.acStatus == 2 || maOrderInfoMgVo.acStatus == 3 }">
	   			未付款
	   		</c:if>
	   		</span>
		</p>
      <%--   
        <p>
        	付款凭证：
            <img id="J_voucherImg" src="<%= uri %>/${scOrderDetailVo.img }" width="120" height="60" alt="付款凭证扫描相片">（点击放大）
        </p>
        <div class="dialog hidden pos-r" id="J_voucherOgImg">
            <img  src="http://files.jb51.net/scimg/web/20101025/twitter_square_happy.png">
            <style>
                .dialog-close:hover {border-radius: 50%}
            </style>
            <div class="dialog-close"></div>
        </div> --%>
        
    </div>
    <!-- 发货 -->
    <script type="text/javascript">
   		function fahuo(id,status){
       		//用于区分修改订单状态
    		var str = "shipments";
   			$.post("${root}/scms/scOrder/updateOrderStatus.do",{id:id,str:str},function(data){
   				if(data.success){
   					layer.msg("发货成功！");
   					location.reload();
   				}else{
   					layer.msg("发货失败！");
   				}
   			})
   		}
    </script>
    <table class="table-list table-border">
        <thead class="table-thead">
        <tr>
            <th>商品</th>
            <th>价格</th>
            <th>订单数量</th>
            <th>小计（元）</th>
        </tr>
        </thead>
        <tbody class="table-tbody">
        <c:forEach var="scOrderDetailVo" items="${maOrderInfoMgVo.scOrderDetailVoList }">
        <tr>
            <td class="ta-l">
            	<c:if test=""></c:if>
               	<c:choose>
	              	<c:when test="${scOrderDetailVo.img != '' && scOrderDetailVo.img != null && scOrderDetailVo.img != 'null'}">
	              		<img class="fl" src="${USER_FASTFDSPREURL }${scOrderDetailVo.img }" width="90" height="90" alt="缩略图" onerror="javascript:this.src='${USER_DEFAULTIMG_URL}'">
	              	</c:when>
	              	<c:otherwise>
	              		<img class="fl" src="${USER_DEFAULTIMG_URL}" width="90" height="90" alt="缩略图">
	              	</c:otherwise>
              	</c:choose>
                <div class="fl ml-default">
                    ${scOrderDetailVo.name }<br>
                    ${scOrderDetailVo.spec }
                </div>
            </td>
            <td>${scOrderDetailVo.areaPrice }</td>
            <td>${scOrderDetailVo.countNum }</td>
            <td>${scOrderDetailVo.xiaoji }</td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="cover-all no-print"></div>
<style>
    #J_printOrderSection .table-list th {
        border: 0 none;
        padding: 1px;
        font-size: 12px;
    }
    #J_printOrderSection .table-list td {
        border: 1px solid #000;
        padding: 1px;
        line-height: 14px;
        font-size: 12px;
    }
</style>
<div id="J_printOrderSection" class="hidden">
    <table class="table-list">
        <thead>
        <tr>
            <th colspan="8">
                <b>转角街坊仓库送货单</b>
                <div class="ta-l">
                    <span class="mr-default">订单编号：${maOrderInfoMgVo.orderId }</span>
                    <span class="mr-default">经销商：${maOrderInfoMgVo.managerName }</span>
                    <span class="mr-default">订单时间：<fmt:formatDate value="${maOrderInfoMgVo.kfSubmitTime }" pattern="yyyy-MM-dd HH:mm:ss"/> </span>
                    <span>地区：${maOrderInfoMgVo.groupName }</span>
                </div>
                <div class="ta-l">
                    <span class="mr-default">仓库负责人：${maOrderInfoMgVo.consignee }</span>
                    <span class="mr-default">电话：${maOrderInfoMgVo.mobile }</span>
                    <span>地址：${maOrderInfoMgVo.address }</span>
                </div>
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>序号</td>
            <td>箱码</td>
            <td>商品名称</td>
            <td>规格</td>
            <td>单品条形码</td>
            <td>单价</td>
            <td>数量</td>
            <td>小计</td>
        </tr>
        <c:forEach var="scOrderDetailVo" items="${maOrderInfoMgVo.scOrderDetailVoList }" varStatus="status">
        <tr>
            <td>${status.count }</td>
            <td>${scOrderDetailVo.wayCode }</td>
            <td>${scOrderDetailVo.name }</td>
            <td>${scOrderDetailVo.spec }</td>
            <td>${scOrderDetailVo.barCode }</td>
            <td>￥${scOrderDetailVo.areaPrice }</td>
            <td>${scOrderDetailVo.countNum }</td>
            <td>￥${scOrderDetailVo.xiaoji }</td>
        </tr>
        </c:forEach>
        <tr>
            <td colspan="3" class="ta-l" style="border-right: 0 none">
                客服热线：
                400-664-3833
            </td>
            <td colspan="5" class="ta-r" style="border-left: 0 none;line-height:18px">
                总数量：${maOrderInfoMgVo.countNumber }&nbsp;&nbsp;&nbsp;&nbsp;
                总计：￥${maOrderInfoMgVo.outOfPrice }
                <br>
               <!--  付款状态：已付款&nbsp;&nbsp;&nbsp;&nbsp; -->
                实付金额：￥${maOrderInfoMgVo.outOfPrice }
                <br>
                仓库签名：<span class="label"></span>
            </td>
        </tr>
        </tbody>
    </table>
</div>

<script>
    $(function() {
        $('#J_voucherImg').on('click', function() {
            dialogPosCenter('#J_voucherOgImg');
            $('#J_voucherOgImg, .cover-all').show();
        });
        $('#J_voucherOgImg').on('click', '.dialog-close', function() {
            $('#J_voucherOgImg, .cover-all').hide();
        });
        $('.cover-all, #J_voucherOgImg img').on('click', function() {
            $('#J_voucherOgImg, .cover-all').hide();
        });
        // 打印订单
        $('#J_printOrder').on('click', function() {
        	$("#aaa").hide();
            $("#J_printOrderSection").show();
            window.print();
            $("#J_printOrderSection").hide();
            $("#aaa").show();
        });
    });
</script>
</body>
</html>