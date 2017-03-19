<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="${root }/resources/css/base.css" />
    <script src="${root }/resources/vendor/jquery/jquery-1.12.4.min.js"></script>
    <script src="${root }/resources/js/comm.js"></script>
    <script src="${root }/resources/vendor/drag/drag.1.0-min.js"></script>
    <%@ include file="../common/head.jsp"%>

</head>
<style type="text/css">
    ul {
        list-style: none;
        padding: 0;
        margin: 0;
    }

    li {
        margin-bottom: 18px;
    }
    .table-list td, .table-list th{
        word-break: keep-all;
        white-space:nowrap;
    }
</style>

<body>
<div class="wrap-bd">
    <div class="mb-small">
        <div class="title">销售出库查看</div>
    </div>
    <div class="bg wrap-bd mb-default">
        <div class="clearfix">
            <ul style="width: 33%;" class="fl">
                <li>出库单号：${stockInfo.orderId}</li>
                <li>联系人：${stockInfo.consignee}</li>
                <li>当前状态：${stockInfo.logisticsStatusStr}</li>
                <li>是否审核：${stockInfo.checkStatusStr}</li>
            </ul>
            <ul style="width: 33%;" class="fl">
                <li>销售单号：${stockInfo.pOrderId}</li>
                <li>手机号：${stockInfo.storeMobile}</li>
                <li>创建人：${stockInfo.addUserName}</li>
                <li>审核人：${stockInfo.checkUserName}</li>
            </ul>
            <ul style="width: 33%;" class="fl">
                <li>店名：${stockInfo.storeName}</li>
                <li>订单金额：${stockInfo.itemPrice}</li>
                <li>创建时间：<fmt:formatDate value="${stockInfo.addTime}" pattern="yyyy-MM-dd HH:mm:ss" /></li>
                <li>审核时间：<fmt:formatDate value="${stockInfo.checkTime}" pattern="yyyy-MM-dd HH:mm:ss" /></li>
            </ul>
        </div>
    </div>
    <div style="width: 100%;overflow: auto;">
        <table class="table-list table-border" id="stock_info">
            <thead>
            <tr>
                <th>序号</th>
                <th>商品条形码</th>
                <th>商品名称</th>
                <th>规格</th>
                <th>单价</th>
                <th>销售数量</th>
                <th>出库数量</th>
                <th>金额</th>
                <th>出库仓库</th>
                <th>出库库区</th>
                <th>出库库位</th>
            </tr>
            </thead>
            <tbody class="table-tbody">
            <c:forEach var="item" items="${list}" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${item.barCode}</td>
                    <td>${item.name}</td>
                    <td>${item.spec}</td>
                    <td>${item.price}</td>
                    <td>${item.quantity}</td>
                    <td>${item.operateStock}</td>
                    <td>${item.totalPrice}</td>
                    <td>${item.wh1Name}</td>
                    <td>${item.wh2Name}</td>
                    <td>${item.wh3Name}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="mb-default mt-default">
        <c:choose>
            <c:when test="${stockInfo.checkStatus == 1}">
                <input type="button" class="button-save check" value="确认审核" />
            </c:when>
            <c:when test="${stockInfo.checkStatus == 2 && stockInfo.logisticsStatus == 1}">
                <input type="button" class="button-save outStock" value="确认出库" />
            </c:when>
            <c:when test="${stockInfo.checkStatus == 2 && stockInfo.logisticsStatus == 4}">
                <input type="button" class="button-save send" value="确认送达" />
            </c:when>
        </c:choose>
        <input type="button" class="button-save" value="打印" onclick="print()"/>
        <input type="button" class="button-cancel" value="取消" onclick="window.location.href=document.referrer;"/>
    </div>
</div>
<c:if test="${fn:length(ownerDetail)>0 || (stockInfo.checkStatus == 2 && stockInfo.logisticsStatus == 4)}">
<div class="wrap-bd">
    <div class="">
        <div class="mb-default title">附属订单</div>
    </div>
    <div class="hidden cashDemo">
        <select class="select" name="cashs">
            <c:forEach items="${ownerCashs}" var="cash">
                <option value="${cash.index}">${cash.name}</option>
            </c:forEach>
        </select>
    </div>
    <form id="stockForm" style="width: 100%;overflow: auto;">
        <table class="table-list mb-default mt-default">
            <thead>
            <tr>
                <th>序号</th>
                <th>类型</th>
                <th>兑换对象</th>
                <th>商品条码</th>
                <th>商品名称</th>
                <th>数量</th>
                <th>兑换商品数量</th>
                <th>抵扣金额</th>
                <th>回收数量</th>
                <th>确认兑换数量</th>
                <th>确认抵扣金额</th>
                <th>备注</th>
            </tr>
            </thead>
            <tbody class="table-tbody">
                <c:choose>
                    <c:when test="${stockInfo.checkStatus == 2 && stockInfo.logisticsStatus == 4}">
                        <c:forEach items="${ownerDetail}" var="ele" varStatus="eleStat">
                            <tr class="havaDetail">
                                <td  class="count">${eleStat.count}</td>
                                <td>${ele.typeStr}</td>
                                <td>${ele.cashStr}</td>
                                <td>${ele.barCode}</td>
                                <td>${ele.name}</td>
                                <td>${ele.quantity}</td>
                                <td>${ele.cashQuantity}</td>
                                <td>${ele.cashPrice}</td>
                                <td>
                                    <input type="text" class="input input-search-date" name="backQuantitys" value="${ele.backQuantity}"/>
                                    <input type="hidden" name="ids" value="${ele.id}" />
                                    <input type="hidden" name="types" value="${ele.type}"/>
                                    <input type="hidden" name="cashs" value="${ele.cash}">
                                    <input type="hidden" name="quantitys" value="${ele.quantity}">
                                    <input type="hidden" name="cashQuantitys" value="${ele.cashQuantity}">
                                    <input type="hidden" name="cashPrices" value="${ele.cashPrice}">
                                </td>
                                <td><input type="text" class="input input-search-date" name="sureQuantitys" value="${ele.sureQuantity}" <c:if test="${ele.type != 1 }"> readonly="readonly" </c:if> data-maxValue="${ele.cashQuantity}"/></td>
                                <td><input type="text" class="input input-search-date" name="surePrices" value="${ele.surePrice}"  <c:if test="${ele.type == 1 }">readonly="readonly" </c:if>/></td>
                                <td><input type="text" class="input input-default" name="remarks" value="${ele.remark}" /></td>
                            </tr>
                        </c:forEach>
                        <tr id="lastTr">
                            <td colspan="12"><input type="button" value="增加一行" class="button button-operate" /></td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${ownerDetail}" var="ele" varStatus="eleStat">
                            <tr>
                                <td  class="count">${eleStat.count}</td>
                                <td>${ele.typeStr}</td>
                                <td>${ele.cashStr}</td>
                                <td>${ele.barCode}</td>
                                <td>${ele.name}</td>
                                <td>${ele.quantity}</td>
                                <td>${ele.cashQuantity}</td>
                                <td>${ele.cashPrice}</td>
                                <td>${ele.backQuantity}</td>
                                <td>${ele.sureQuantity}</td>
                                <td>${ele.surePrice}</td>
                                <td>${ele.remark}</td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </form>
    <div class="mb-default mt-default">
        <c:if test="${stockInfo.checkStatus == 2 && stockInfo.logisticsStatus == 4}">
            <input type="button" class="button-save editOrderOwner" value="保存" />
        </c:if>
    </div>
</div>
</c:if>
</body>
<script>
	function zhengshu(w) {
        if(w.length != 0 && w != 0) {
            var reg = /^[1-9]\d*$/;
            var r = w.match(reg);
            if(r == null) {
                return false;
            }else{
                return true;
            }
        }else{
        	return true;
        }
    }
	function beizhu(n) {
        if(n.length > 0 && n.length <= 50 ) {
            return true;
        }else{
            return false;
        }
    }
	function dikou(x) {
        if(x.length != 0) {
            var reg = /^\d{1,5}(\.\d{1,2})?$/;
            var r = x.match(reg);
            if(r == null) {
                return false;
            }else{
                return true;
            }
        }else{
        	return true;
        }

    }
    function print() {
        window.open("${root}/scms/ERPMarketStock/print/${stockInfo.id}.do");
    }
    $(function() {
        var postDate = {ids:'${stockInfo.id}' , token:'${token}'}
        setTimeout(function(){
            layer.closeAll('loading');
        }, 2000);
        $('body').on('click' , '.button-cancel' , function () {
            <%--location.href='${root}/scms/erpMarket/toSaleOutIndex.do?pageIndex=1&level=1';--%>
        }).on('click' , '.outStock' , function () {
            layer.load(2);
            postSubmit('${root}/scms/ERPMarketStock/bacthOutStock.do',postDate);
        }).on('click' , '.send' , function () {
            layer.load(2);
            postSubmit('${root}/scms/ERPMarketStock/bacthSend.do',postDate);
        }).on('click' , '.check' , function () {
            layer.load(2);
            postSubmit('${root}/scms/ERPMarketStock/bacthCheck/2.do',postDate);
        }).on('click' , '.editOrderOwner' , function () {
            var isOk = true;
	            $('#stockForm tr').each(function(){
	              	if($(this).hasClass('detail')){
                        var cashPrices= $(this).find('input[name="cashPrices"]');
                        var trcashPrices=$.trim(cashPrices.val());
                        var backQuantitys= $(this).find('input[name="backQuantitys"]');
                        var trbackQuantitys=$.trim(backQuantitys.val());
                        var sureQuantitys = $(this).find('input[name="sureQuantitys"]');
                        var trsureQuantitys=$.trim(trsureQuantitys.val());
                        var surePrices= $(this).find('input[name="surePrices"]');
			            var trsurePrices=$.trim(surePrices.val());
			            var remarks = $(this).find('input[name="remarks"]');
			            var trRemarks = $.trim(remarks.val());
			            if(backQuantitys.val() == null || backQuantitys.val() == ''){
                            layer.tips('请输入回收数量' , backQuantitys);
                            backQuantitys.focus();
                            isOk =false;
	                        return false;
	                    }else if(!zhengshu(trbackQuantitys)){
                            layer.tips('请输入正整数！' , backQuantitys);
                            backQuantitys.focus();
                            isOk =false;
	                        return false;
	                    }else if(cashPrices.val() == null || cashPrices.val() == ''){
                            layer.tips('请输入抵扣金额' , cashPrices);
                            isOk =false;
	                        cashPrices.focus();
	                        return false;
	                    }else if(!dikou(trcashPrices)){
                            layer.tips('请输入正确的金额格式！' , cashPrices);
	                        cashPrices.focus();
                            isOk =false;
	                        return false;
	                    }else if(sureQuantitys.val() == null || sureQuantitys.val() == ''){
                            layer.tips('请输入确认兑换数量' , sureQuantitys);
                            isOk =false;
                            sureQuantitys.focus();
	                        return false;
	                    }else if(!zhengshu(trsureQuantitys)){
                            layer.tips('请输入正整数！' , sureQuantitys);
                            isOk =false;
                            sureQuantitys.focus();
	                        return false;
	                    }else if(surePrices.val() == null || surePrices.val() == ''){
                            layer.tips('请输入确认抵扣金额' , surePrices);
                            isOk =false;
	                        surePrices.focus();
	                        return false;
	                    }else if(!dikou(trsurePrices)){
                            layer.tips('请输入正确的金额格式！' , surePrices);
                            isOk =false;
	                        surePrices.focus();
	                        return false;
	                    }else if(remarks.val() == null || remarks.val() == ''){
                            isOk =false;
                            layer.tips('请输入备注' , remarks);
	                        remarks.focus();
	                        return false;
	                    }else if(!beizhu(remarks.val())){
                            layer.tips('请输入备注' , remarks);
                            isOk =false;
	                        remarks.focus();
	                        return false;
	                    }
		            }else if($(this).hasClass('havaDetail')){
	              	    var type = $(this).find('input[name="types"]').val();

                        var backQuantitys= $(this).find('input[name="backQuantitys"]');
                        var trbackQuantitys=$.trim(backQuantitys.val());

                        var sureQuantitys= $(this).find('input[name="sureQuantitys"]');
                        var trsureQuantitys=$.trim(sureQuantitys.val());

                        var surePrices= $(this).find('input[name="surePrices"]');
                        var trsurePrices=$.trim(surePrices.val());

                        var remarks = $(this).find('input[name="remarks"]');

                        if(trbackQuantitys == null || trbackQuantitys == ''){
                            backQuantitys.val('0');
                        }
                        if(remarks.val() == null || remarks.val() == ''){
                            isOk =false;
                            layer.tips('请输入备注！' , remarks);
                            remarks.focus();
                            return false;
                        }else if(!beizhu(remarks.val())){
                            layer.tips('备注最多50！' , remarks);
                            isOk =false;
                            remarks.focus();
                            return false;
                        }
                        if(type == '1'){
                            if(trsureQuantitys == null || trsureQuantitys == '') {
                                sureQuantitys.val('0');
                            }
                            if(backQuantitys.val() == null || backQuantitys.val() == ''){
                                layer.tips('请输入回收数量' , backQuantitys);
                                backQuantitys.focus();
                                isOk =false;
                                return false;
                            }else if(!zhengshu(trbackQuantitys)){
                                layer.tips('请输入正整数！' , backQuantitys);
                                backQuantitys.focus();
                                isOk =false;
                                return false;
                            }else if(trsureQuantitys > Number(sureQuantitys.attr('data-maxValue'))){
                                isOk = false;
                                layer.tips('兑换数量不能大于初始兑换商品数量！' , sureQuantitys);
                                sureQuantitys.focus();
                                return false;
                            }else if(!zhengshu(trsureQuantitys)) {
                                layer.tips('请输入正整数！' , sureQuantitys);
                                isOk = false;
                                sureQuantitys.focus();
                                return false;
                            }
                        }else if(type == '2'){
                            if(trsurePrices == null || trsurePrices == ''){
                                surePrices.val('0');
                            }else if(!dikou(trsurePrices)) {
                                isOk = false;
                                surePrices.focus();
                                return false;
                            }
                        }



	              	}
                })
            if(isOk){
                layer.load(2);
                postSubmit('${root}/scms/ERPSpOrderOwner/editERPSpOrderOwner/${stockInfo.pOrderId}.do' , $('#stockForm').serialize());
            }
        }).on('click' , '#lastTr input' , function () {
            //增加一行
            var html = '<tr class="detail">';
            html += '<td class="count">' + ($('.count').length+1) + '</td>';
            html += '<td class="type">';
            html += '<input type="hidden" name="types" value="2"/>扣减金额';
            html += '</td>';
            html += '<td class="cash">';
            html += $('.cashDemo').html();
            html += '</td>';
            html += '<td>';
            html += '<input type="hidden" name="ids"/>';
            html += '</td>';
            html += '<td class="name"></td>';
            html += '<td><input type="text" class="input input-search-date" name="quantitys"/></td>';
            html += '<td><input type="text" class="input input-search-date" name="cashQuantitys"/></td>';
            html += '<td><input type="text" class="input input-search-date" name="cashPrices"/></td>';
            html += '<td>';
            html += '<input type="text" class="input input-search-date" name="backQuantitys"/>';
            html += '</td>';
            html += '<td><input type="text" class="input input-search-date" name="sureQuantitys"/></td>';
            html += '<td><input type="text" class="input input-search-date" name="surePrices"/></td>';
            html += '<td><input type="text" class="input input-default" name="remarks"/></td>';
            html += '</tr>';
            $(this).parent().parent().before(html);
            $('input[name="types"]').trigger('change');
        }).on('change', 'input[name="types"]', function() {
            var type = $(this).val();
            if(type == '1') { //兑换商品
                $(this).parent().parent().find('input[name="sureQuantitys"]').attr('readonly',false);
                $(this).parent().parent().find('input[name="surePrices"]').attr('readonly',true);

            } else if(type == '2') { //扣减金额
                $(this).parent().parent().find('input[name="cashQuantitys"]').attr('readonly', true);
                $(this).parent().parent().find('input[name="sureQuantitys"]').attr('readonly',true);
                $(this).parent().parent().find('input[name="cashPrices"]').attr('readonly', false);
                $(this).parent().parent().find('input[name="surePrices"]').attr('readonly',false);
            }
        });
        function postSubmit(url , param) {
            $.post(url,param,function (data) {
                if(data.success){
                    layer.msg('操作成功,页面即将刷新!',{time:1000},function(){
                        location.reload();
                    });
                }else{
                    layer.msg(data.message,{time:2000},function () {
                        location.reload();
                    });
                }
            },'json');
        }
    });
</script>

</html>
