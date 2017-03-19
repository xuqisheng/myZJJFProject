<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>销售出库</title>
    <link rel="stylesheet" type="text/css" href="${root}/resources/css/base.css" />
    <link rel="stylesheet" href="${root}/resources/css/order-detail.css">
    <script src="${root}/resources/vendor/jquery/jquery-1.12.4.min.js"></script>
    <script src="${root}/resources/js/comm.js"></script>
    <script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
    <%@ include file="../common/head.jsp"%>
</head>

<body>
<div class="wrap-bd noprint">
    <div class="mb-small clearfix">
        <div class="title">销售出库管理</div>
    </div>
    <div class="bg wrap-bd mb-default">
        <input type="hidden" id="pageIndex" value="${pagerIndexStr}">
        <input type="hidden" id="level" value="${levelStr}">
        <form action="${root}/scms/erpMarket/toSaleOutIndex.do" id="J_saleOutForm" method="POST">
            <div class="">
                <label>关键词：</label>
                <input type="text" class="input input-default mr-default" name="keyStr"  value="${keyStr}" placeholder="出库单号/销售单号/商户名称/手机号" />
                <label>出库状态：</label>
                <select name="stockStatus" class="select mr-default">
                    <option value="-1" <c:if test="${empty stockStatus||stockStatus==-1}">selected</c:if>>全部</option>
                    <option value="1" <c:if test="${stockStatus==1}">selected</c:if>>已出库</option>
                    <option value="0" <c:if test="${stockStatus==0}">selected</c:if>>未出库</option>
                </select>
                <label>创建时间：</label>
                <input type="text" class="input input-date J_timeS" name="startTime"  value="${startTime}" />&nbsp;至&nbsp;
                <input type="text" class="input input-date mr-default J_timeE" name="endTime"  value="${endTime}" />
                <input type="submit" class="input-search-button"  value="搜索" />
            </div>
        </form>
    </div>
    <div class="mb-default">
        <input type="button" class="button button-operate mr-small" id="J_add" value="新建" />
        <input type="button" class="button button-operate mr-small" id="J_edit" value="修改" />
        <input type="button" class="button button-operate mr-small" id="J_watch" value="查看" />
        <input type="button" class="button button-operate mr-small" id="J_del" value="删除" />
        <input type="button" class="button button-operate mr-small" id="J_print" value="打印" />
        <input type="button" class="button button-operate mr-small" id="J_check" value="审核" />
        <input type="button" class="button button-operate mr-small" id="J_outStock" value="出库" />
        <input type="button" class="button button-operate mr-small" id="J_sendOK" value="确认送达" />
        <input type="button" class="button button-operate mr-small" id="J_orderBack" value="退货" />

    </div>
    <form id="stockForm">
        <input type="hidden" name="token" value="${token}" />
        <table class="table-list mb-default">
            <thead>
            <tr>
                <th><input type="checkbox" class="checkbox" id="J_selectAll" value="" /></th>
                <th>序号</th>
                <th>出库单号</th>
                <th>店名</th>
                <th>联系人</th>
                <th>手机号</th>
                <th>订单金额</th>
                <%--<th>创建人</th>--%>
                <%--<th>创建时间</th>--%>
                <th>是否审核</th>
                <th>审核人</th>
                <th>审核时间</th>
                <th>当前状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody class="table-tbody" id="J_saleOutTbody">
            <c:forEach items="${list}" var="item" varStatus="status">
                <tr data-checkStatus="${item.checkStatus}" data-logisticsStatus="${item.logisticsStatus}" data-isSaleBack="${item.isSaleBack}">
                    <td><input type="checkbox" class="checkbox J_chk" name="ids" value="${item.id}" data-pId="${item.pId}"/></td>
                    <td>${status.index+1}</td>
                    <td>${item.orderId}</td>
                    <td>${item.storeName}</td>
                    <td>${item.consignee}</td>
                    <td>${item.storeMobile}</td>
                    <td>${item.itemPrice}</td>
                    <%--<td>${item.addUserName}</td>--%>
                    <%--<td><fmt:formatDate value="${item.addTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>--%>
                    <td>${item.checkStatusStr}</td>
                    <td>
                        <c:choose>
                            <c:when test="${not empty item.checkUserName}">
                                ${item.checkUserName}
                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${not empty item.checkTime}">
                                <fmt:formatDate value="${item.checkTime}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${item.logisticsStatusStr}</td>
                    <td><a class="button button-operate" href="${root}/scms/erpMarket/toWachMarketStockInfo.do?id=${item.id}">附属订单</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <c:if test="${fn:length(list)>0}">
            <%@ include file="../common/pagination-kk.jsp"%>
        </c:if>
    </form>
</div>
</body>
<script>
    dateRange('.J_timeS', '.J_timeE', 1);
    selectAll('#J_selectAll', '.J_chk');
    $(function(){
        /*$('.table-list td').click(function() {
            $(this).parent().css({'background':'#009dd9','color':'white'}).find('.checkbox').addClass('isChk').prop('checked',true).parent().parent().siblings().css({'background':'#fff','color':'black'}).find('.checkbox').removeClass('isChk').attr("checked",false);
            $_tr = $(this).parent();
        });*/
        $('.table-list tbody').on('click','tr',function (event) {
            var event=event||window.event;
            var target=event.target;
            event.stopPropagation();
            var inp = $(this).find("input[type='checkbox']");
            var attr = inp.prop("checked");
            var inpnum=$(this).find("input[type='checkbox']").length;
            if(target.localName=="input"){
                if (attr) {
                    $(this).css({"background":"#009dd9","color":"#fff"});
                } else {
                    $(this).css({"background":"#fff","color":"black"});
                }
            }else if(inpnum==1) {
                if (attr) {
                    inp.prop("checked", false);
                    $(this).has("input[type='checkbox']").css({"background":"#fff","color":"black"});
                } else{
                    inp.prop("checked", true);
                    $(this).has("input[type='checkbox']").css({"background":"#009dd9","color":"#fff"});
                }
            }
        })

        //新建按钮
        $('#J_add').on('click',function(){
            window.location.href="${root}/scms/erpMarket/toSaveSaleOutOrder.do";
        });
        //查看按钮
        $('#J_watch').on('click',function(){
            var chk = $('.J_chk:checked').length;
            if(chk==1){
                var id = $('.J_chk:checked').eq(0).val();
                window.location.href="${root}/scms/erpMarket/toWachMarketStockInfo.do?id="+id;
            }else if(chk==0){
                layer.msg('请选择一个出库单查看!');
            }else{
                layer.msg('最多只能选择一个出库单查看!');
            }
        });

        //审核按钮
        $('#J_check').on('click',function(){
            var chk = $('.J_chk:checked').length;
            if(chk==0){
                layer.msg('至少选择一个出库单审核!');
                return;
            }
            var checkstatus = $('.J_chk:checked').eq(0).parent('td').parent('tr').attr('data-checkstatus');
            if(checkstatus==2){
                layer.msg('该笔出库单已经审核通过!');
                return;
            }
            postSubmit('${root}/scms/ERPMarketStock/bacthCheck/2.do' , $('#stockForm').serialize());
        });

        //出库按钮
        $('#J_outStock').on('click',function(){
            layer.load(2);
            var chk = $('.J_chk:checked').length;
            if(chk==0){
                layer.msg('至少选择一个出库单出库!');
                return;
            }else{
                var errorMsg = '出库单状态异常<br/>';
                var errorNum = 0;
                //校验是否审核通过
                $('.J_chk:checked').each(function(){
                    var checkstatus = $(this).parent('td').parent('tr').attr('data-checkstatus');
                    var logisticsstatus = $(this).parent('td').parent('tr').attr('data-logisticsstatus');
                    var trIndex = $(this).parent('td').next('td').html();
                    if(checkstatus==1){
                        errorMsg+='未审核,序号'+trIndex+'<br/>';
                        errorNum++;
                    }else if(logisticsstatus>=4){
                        errorMsg+='已出库,序号'+trIndex+'<br/>';
                        errorNum++;
                    }
                });
                if(errorNum>0){
                    layer.msg(errorMsg,{time:2000});
                    return;
                }
                postSubmit('${root}/scms/ERPMarketStock/bacthOutStock.do' , $('#stockForm').serialize());
            }
        });
        //确认送达
        $('#J_sendOK').on('click',function(){
            layer.load(2);
            var chk = $('.J_chk:checked').length;
            if(chk==0){
                layer.msg('至少选择一个出库单确认送达!');
                return;
            }else{

                var errorMsg = '出库单状态异常<br/>';
                var errorNum = 0;
                //校验是否审核通过
                $('.J_chk:checked').each(function(){
                    var checkstatus = $(this).parent('td').parent('tr').attr('data-checkstatus');
                    var logisticsstatus = $(this).parent('td').parent('tr').attr('data-logisticsstatus');
                    var trIndex = $(this).parent('td').next('td').html();
                    if(checkstatus==1){
                        errorMsg+='未审核，序号'+trIndex+'<br/>';
                        errorNum++;
                    }else if(logisticsstatus < 4){
                        errorMsg+='未出库，序号'+trIndex+'<br/>';
                        errorNum++;
                    }else if(logisticsstatus==5){
                        errorMsg+='已送达，序号'+trIndex+'<br/>';
                        errorNum++;
                    }
                });
                if(errorNum>0){
                    layer.msg(errorMsg,{time:2000});
                    return;
                }
                postSubmit('${root}/scms/ERPMarketStock/bacthSend.do',$('#stockForm').serialize());
            }
        });


        //退货
        $('#J_orderBack').on('click',function(){
            var chk = $('.J_chk:checked');
            if(chk.length!=1){
                layer.msg('选择一个出库单退货!');
                return;
            }else if($(chk).parent('td').parent('tr').attr('data-logisticsstatus') != '5'){
                layer.msg('选择一个已送达的出库单退货!');
                return;
            }else if($(chk).parent('td').parent('tr').attr('data-isSaleBack') == '1'){
                layer.msg('该单已经全部退货!');
                return;
            }else{
                window.location.href='${root}/scms/ERPMarketStock/edit/'+chk.val()+'/.do';
            }
        });

        //修改
        $('#J_edit').on('click',function(){
            var chk = $('.J_chk:checked').length;
            if(chk==1){
                var checkstatus = $('.J_chk:checked').eq(0).parent('td').parent('tr').attr('data-checkstatus');
                if(checkstatus==2){
                    layer.msg('该笔出库单已经审核通过,不能修改!');
                    return;
                }
                var id = $('.J_chk:checked').eq(0).val();
                location.href='${root}/scms/erpMarket/toEditSaleOut.do?id='+id;
            }else if(chk==0){
                layer.msg('请选择一个出库单进行修改!');
            }else{
                layer.msg('一次只能选择一个出库单修改!');
            }
        })


        //删除
        $('#J_del').on('click',function(){
            layer.load(2);
            var chk = $('.J_chk:checked').length;
            var ids = '';
            if(chk==0){
                layer.msg('至少选择一个出库单删除!');
                return;
            }else{

                var errorMsg = '';
                var errorNum = 0;
                //校验是否审核通过
                $('.J_chk:checked').each(function(){
                    var checkstatus = $(this).parent('td').parent('tr').attr('data-checkstatus');
                    var trIndex = $(this).parent('td').next('td').html();
                    if(checkstatus==2){
                        errorMsg+='序号'+trIndex+'  ';
                        errorNum++;
                    }
                });
                if(errorNum>0){
                    errorMsg+='出库单已审核通过,不能删除!'
                    layer.msg(errorMsg,{time:2000});
                    return;
                }

                $('.J_chk:checked').each(function(){
                    if(ids == '')
                        ids=$(this).val();
                    else
                        ids+=','+$(this).val();
                });
                postSubmit('${root}/scms/ERPMarketStock/bacthDelete.do',$('#stockForm').serialize());
            }
        });
        setTimeout(function(){
            layer.closeAll('loading');
        }, 2000);
        //打印
        $('#J_print').on('click',function(){
            var chk = $('.J_chk:checked').length;
            if(chk==1){
                var id = $('.J_chk:checked').eq(0).val();
                window.open("${root}/scms/ERPMarketStock/print/"+id+".do");
            }else{
                layer.msg('请选择一个出库单打印!');
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
    })
</script>
</html>
