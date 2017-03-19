<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>商品库管理 - 入库单编辑</title>
    <%@ include file="../../common/head.jsp"%>
    <script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
    <script src="${root}/resources/js/comm.js?vvv"></script>
</head>
<body>
<div class="wrap-bd">
    <div class="mb-default">
        <span>当前位置：</span>
        <span class="crumb">商品库管理</span>
        <a href="${root}/scms/ERPOrderInfo.do?status=0" class="crumb">入库管理</a>
        <span class="crumb crumb-active">编辑入库</span>
    </div>
    <div class="wrap-bd bg">
        <table border="0" cellpadding="0" cellspacing="0" width="100%" style="line-height: 28px">
            <tbody>
            <tr>
                <td>采购单号：${info.orderId}</td>
                <td>制单时间：<fmt:formatDate value="${info.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>仓库：${info.whName}</td>
            </tr>
            <tr>
                <td>
                    类型：
                    <c:choose>
                        <c:when test="${info.type == 1}">采购入库</c:when>
                        <c:otherwise>其他</c:otherwise>
                    </c:choose>
                </td>
                <td>供应商：${info.managerName}</td>
                <td>交货时间：<fmt:formatDate value="${info.gaveTime}" pattern="yyyy年MM月dd日"/></td>
            </tr>
            <tr>
                <td>备注：${info.remark}</td>
                <td></td>
                <td></td>
            </tr>
            </tbody>
        </table>
    </div>
    <c:if test="${info.status == 0}">
        <form id="detailFrom" class="mt-small">
            <div class="table-contain">
                <pre>
                <table class="table-list mt-small">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>箱码</th>
                        <th>商品条形码</th>
                        <th>商品名称</th>
                        <th>采购价</th>
                        <th>规格</th>
                        <th>需入库</th>
                        <th>已入库</th>
                        <th>本次入库</th>
                        <th>入库价</th>
                        <th>生产日期</th>
                        <th>仓位</th>
                        <th class="hidden">库存类型</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="totalQuantity" value="0"></c:set>
                    <c:set var="totalOperateQuantity" value="0"></c:set>
                    <c:forEach items="${info.details}" var="ele" varStatus="eleStat">
                        <c:if test="${ele.quantity != ele.operateQuantity}">
                            <tr>
                            <c:set var="totalQuantity" value="${totalQuantity + ele.quantity}"></c:set>
                            <c:set var="totalOperateQuantity" value="${totalOperateQuantity + ele.operateQuantity}"></c:set>
                            <td>${eleStat.count}</td>
                            <td>${ele.barCode}</td>
                            <td>${ele.mdseId}</td>
                            <td>${ele.name}</td>
                            <td class="purPrice">${ele.areaPrice}</td>
                            <td>${ele.spec}</td>
                            <td>${ele.quantity}</td>
                            <td>${ele.operateQuantity}</td>
                            <td>
                                <input type="text"  maxlength="4" data-quantitys="${ele.quantity - ele.operateQuantity }" name="quantitys" class="input-search-date" <c:if test="${ele.quantity == ele.operateQuantity}">disabled="disabled" </c:if>>
                                <input type="hidden" name="ids" value="${ele.id}" <c:if test="${ele.quantity == ele.operateQuantity}">disabled="disabled" </c:if>>
                                <input type="hidden" name="itemIds" value="${ele.itemId}" <c:if test="${ele.quantity == ele.operateQuantity}">disabled="disabled" </c:if>>
                                <input type="hidden" name="itemBaseIds" value="${ele.itemBaseId}" <c:if test="${ele.quantity == ele.operateQuantity}">disabled="disabled" </c:if>>
                            </td>
                            <td>
                                <input type="text" name="areaPrices" class="input" value="${ele.areaPrice}" <c:if test="${ele.quantity == ele.operateQuantity}">disabled="disabled" </c:if>>
                            </td>
                            <td>
                                <input type="text" name="productionDates" class="input" <c:if test="${ele.quantity == ele.operateQuantity}">disabled="disabled" </c:if>>
                            </td>
                            <td>
                                <select name="warehouseIdLevel3s" class="select">
                                    <c:forEach items="${warehouses}" var="warehouse">
                                        <option value="${warehouse.id}">${warehouse.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td class="hidden">
                                <select name="stockTypes" class="select">
                                    <c:forEach items="${stockTypes}" var="stockType">
                                        <option value="${stockType.index}">${stockType.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                    <tfoot>
                    <tr>
                        <td>合计</td>
                        <td colspan="11" class="ta-r">
                           		 需入库合计：${totalQuantity}
                            <span class="ml-default">已入库合计：${totalOperateQuantity}</span>
                        </td>
                    </tr>
                    </tfoot>
                </table>
                </pre>
            </div>
            <div class="wrap-bd bg">
                <p>
                    <label class="label">备注：</label>
                    <textarea name="remark" class="textarea" maxlength="200" style="width: 300px;"></textarea>
                </p>
                <div>
                    <input type="button" name="" value="确认入库" class="button-save mr-small">
                    <input type="button" name="" value="取消" class="button-cancel" onclick="window.location.href=document.referrer;">
                </div>
            </div>
        </form>
    </c:if>
    <c:if test="${fn:length(lists)>0}">
    <div class="title mt-default">入库流水</div>
    <c:forEach items="${lists}" var="ele">
        <div class="wrap-bd bg mt-small">
            <div class="clearfix">
                <ul class="fl mb-default" style="width: 100%;">
                    	<li style="width: 25%;float: left;">入库单号：${ele.orderId}</li>
                    	<li style="width: 25%;float: left;">入库状态：
                            <c:choose>
                                <c:when test="${ele.checkStatus == 1}">未入库</c:when>
                                <c:when test="${ele.checkStatus == 2}">已入库</c:when>
                            </c:choose>
                    	</li>
                    	<li style="width: 25%;float: left;">入库时间：<fmt:formatDate value="${ele.checkTime}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
                    	<li style="width: 25%;float: left;">入库人：${ele.checkUserName}</li>
                </ul>
               	 备注：${ele.remark}
                <a href="${root}/scms/ERPOrderInfo/detail/${info.orderId}/${ele.orderId}.do" target="_blank" class="fr button mb-default">打印入库单</a>
            </div>
            <table class="table-list table-border">
                <thead>
                <tr>
                    <th>箱码</th>
                    <th>商品条形码</th>
                    <th>商品名称</th>
                    <th>规格</th>
                    <th>需入库</th>
                    <th>本次入库</th>
                    <th>剩余</th>
                    <th>生产日期</th>
                    <th>采购价</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${ele.details}" var="ele2">
                    <tr>
                        <td>${ele2.barCode}</td>
                        <td>${ele2.mdseId}</td>
                        <td>${ele2.name}</td>
                        <td>${ele2.spec}</td>
                        <td>${ele2.quantity}</td>
                        <td>${ele2.operateStock}</td>
                        <td>${ele2.quantity - ele2.operateQuantity}</td>
                        <td><fmt:formatDate value="${ele2.productionTime}" pattern="yyyy-MM-dd"/></td>
                        <td>${ele2.areaPrice}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:forEach>
    </c:if>
</div>
<script>
    $(function () {
        dateRange('input[name="productionDates"]');
        $('.button-save').on('click' , function () {
            var orderId = '${info.orderId}';
            var xnumCheck = /^[0-9]{1,6}$/;
            var testPriceCheck = /^\d{1,5}(\.\d{1,2})?$/;
            var trueNum = 0;
            var msg = '';
            $('#detailFrom').find('input[name="quantitys"]').each(function(){
            	var purPrice=$(this).parent().parent().find('.purPrice').html()
            	console.log(purPrice)
                var quantitys = $.trim($(this).val());
                var productionDates = $(this).parent().nextAll().find('input[name="productionDates"]').val();
                var areaPrices = $(this).parent().nextAll().find('input[name="areaPrices"]').val();
                var producingAreas = $(this).parent().nextAll().find('input[name="producingAreas"]').val();
                if((Number(quantitys) == 0 || quantitys == null || quantitys == '')
                    && (productionDates == null || productionDates == '')
                    &&(areaPrices == null || areaPrices == '')){
                    return true
                }else if(quantitys == null || quantitys == ''){
                    msg ="请输入大于0小于9999的正整数";
                    $(this).focus();
                }else if(Number(quantitys) == 0 || !xnumCheck.test($.trim(quantitys))){
                    msg ="请输入大于0小于9999的正整数";
                    $(this).focus();
                    return false;
                }else if(Number(quantitys) > Number($(this).attr('data-quantitys'))){
                    msg ="数量超过需入库数量";
                    $(this).focus();
                    return false;
                }else if(Number(areaPrices)>Number(purPrice)){
                    msg="入库价不能高于采购价";
                    $(this).parent().nextAll().find('input[name="areaPrices"]').focus();
                    return false;
                }else if(!testPriceCheck.test(areaPrices)){
                    isOk = false;
                    msg ="入库价金额不正确";
                    $(this).parent().nextAll().find('input[name="areaPrices"]').focus();
                    return false;
                }else if(productionDates == null || productionDates == undefined || $.trim(productionDates) == ''){
                    msg ="生产日期不能为空";
                    $(this).parent().nextAll().find('input[name="productionDates"]').focus();
                    return false;
                }else{
                    trueNum++;
                };
            });
            if(trueNum == 0 || msg != ''){
                if(msg == ''){
                    msg = '请至少正确录入一笔数据';
                }
                alert(msg);
            }else{
                $.post('${root}/scms/ERPOrderInfo/incoming/'+orderId+'.do' , $('#detailFrom').serialize() , function (data) {
                    if(data.success){
                        alert("成功");
                        window.location.reload();
                        $('#detailFrom input[type="text"]').val('');
                        $('#detailFrom textarea').val('');
                    }else{
                        alert(data.message);
                    }
                });
            }
        });
    });
</script>
</body>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>商品库管理 - 入库单编辑</title>
    <%@ include file="../../common/head.jsp"%>
    <script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
    <script src="${root}/resources/js/comm.js?v"></script>
</head>
</html>
