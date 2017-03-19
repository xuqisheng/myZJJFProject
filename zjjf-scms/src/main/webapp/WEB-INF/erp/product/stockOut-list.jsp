<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>商品库管理 - 采购退货列表</title>
    <%@ include file="../../common/head.jsp"%>
    <script src="${root}/resources/js/comm.js"></script>
</head>
<body>
<div class="wrap-bd noprint">
    <div class="mb-small title">采购退货</div>
    <div class="op-section clearfix">
        <form class="fl ml-default" action="${root}/scms/ERPPurchaseStock/list/${commod.level}.do" id="form">
            <input class="input-search-text" type="text" name="managerName" value="${commod.managerName}" placeholder="供应商名称" />
            <select name="checkStatus"  class="select">
                <option value="0" selected="selected">全部</option>
                <c:forEach items="${checkStatus}" var="status">
                    <option value="${status.index}" <c:if test="${commod.checkStatus == status.index}" >selected="selected"</c:if>>${status.name}</option>
                </c:forEach>
            </select>
            <input value="搜索" type="button" class="input-search-button" id="sub">
        </form>

    </div>
    <div class="mt-default mb-default" id="action">
        <input type="button" class="button button-operate mr-small add" value="新建" />
        <input type="button" class="button button-operate mr-small edit" value="修改" />
        <input type="button" class="button button-operate mr-small delete" value="删除" />
        <input type="button" class="button button-operate mr-small detail" value="查看" />
        <input type="button" class="button button-operate mr-small print" value="打印" />
    <%--<input type="button" class="button button-operate mr-small check" value="审核" />--%>
        </div>
    <div class="table-contain">
        <form id="tableFrom">
            <table class="table-list">
                <thead>
                <tr>
                    <th><input type="checkbox" class="checkbox" id="J_selectAll" /></th>
                    <th>序号</th>
                    <th>退货单号</th>
                    <th>退货仓库</th>
                    <th>供应商</th>
                    <th>退货金额</th>
                    <th>退货数量</th>
                    <th>退货人</th>
                    <th>退货时间</th>
                    <th>是否审核</th>
                    <th>审核人</th>
                    <th>审核时间</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="ele" varStatus="eleStat">
                    <tr>
                        <td><input type="checkbox" class="checkbox J_chk" name="ids" value="${ele.id}" data-checkStatus = "${ele.checkStatus}" data-count="${eleStat.count}" data-pOrderId="${ele.pOrderId}" data-orderId="${ele.orderId}"/></td>
                        <td>${eleStat.count}</td>
                        <td>${ele.orderId}</td>
                        <td>${ele.whName}</td>
                        <td>${ele.managerName}</td>
                        <td>${ele.itemPrice}</td>
                        <td>${ele.itemQuantity}</td>
                        <td>${ele.addUserName}</td>
                        <td><fmt:formatDate value="${ele.taskTime}" pattern="yyyy-MM-dd"/></td>
                        <td>${ele.checkStatusStr}</td>
                        <td>${ele.checkUserName}</td>
                        <td><fmt:formatDate value="${ele.checkTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    </tr>
                </c:forEach>
                <c:if test="${fn:length(list) == 0}">
                    <tr><td colspan="12"  class="no-data"></td></tr>
                </c:if>
                </tbody>
            </table>
        </form>
    </div>
    <c:if test="${fn:length(list)>0}">
        <%@ include file="../../common/pagination-kk.jsp"%>
    </c:if>
</div>
<script>
    selectAll('#J_selectAll', '.J_chk');
    /*selectTr('.table-list tbody','tr');*/
    $(function() {
    	/*$('.table-list').on('click', 'td', function() {
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

        $('#sub').on('click', function(e) {
            e.preventDefault();
            $('#form').submit();
        });
        var bool = true;
        $('#action').on('click','.print' , function () {
            if($('#tableFrom .J_chk:checked').length != 1){
                alert('请选择一条记录操作');
            }else{
                var id = $('#tableFrom .J_chk:checked').val();
                window.open('${root}/scms/ERPPurchaseStock/print/'+id+'.do');
            }
        }).on('click','.add' , function () {
            window.location.href = '${root}/scms/ERPPurchaseStock/stockOut/toEdit/.do';
        }).on('click','.edit' , function () {
            bool = true;
            if($('#tableFrom .J_chk:checked').length != 1){
                bool = false;
                alert('请选择一条记录操作');
            }else{
                var xuhao = '';
                $('#tableFrom .J_chk:checked').each(function () {
                    if($(this).attr('data-checkStatus') == '2'){
                        bool = false;
                        if(xuhao == '')
                            xuhao = $(this).attr('data-count');
                        else
                            xuhao = xuhao + ',' + $(this).attr('data-count') ;
                    }
                });
                if(!bool){
                    alert('采购退货单已审核：序号' + xuhao);
                }
            }
            if(bool){
                var id = $('#tableFrom .J_chk:checked').val();
                window.location.href = '${root}/scms/ERPPurchaseStock/stockOut/toEdit/'+id+'.do';
            }
        }).on('click','.detail' , function () {
            if($('#tableFrom .J_chk:checked').length != 1){
                alert('请选择一条记录操作');
            }else{
                var id = $('#tableFrom .J_chk:checked').val();
                window.location.href = '${root}/scms/ERPPurchaseStock/detail/'+id+'.do';
            }
        }).on('click','.delete' , function () {
            bool = true;
            if($('#tableFrom .J_chk:checked').length == 0){
                bool = false;
                alert('请至少选择一条记录操作');
            }else{
                var xuhao = '';
                $('#tableFrom .J_chk:checked').each(function () {
                    if($(this).attr('data-checkStatus') == '2'){
                        bool = false;
                        if(xuhao == '')
                            xuhao = $(this).attr('data-count');
                        else
                            xuhao = xuhao + ',' + $(this).attr('data-count') ;
                    }
                });
                if(!bool){
                    alert('采购退货单已审核：序号' + xuhao);
                }
            }
            if(bool){
                $.post('${root}/scms/ERPPurchaseStock/bacthDelete.do',$('#tableFrom').serialize(),function (data) {
                    if(data.success){
                        alert('删除成功');
                        window.location.reload();
                    }else{
                        alert(data.message);
                    }
                },'json');
            }
        }).on('click','.check' , function () {
            bool = true;
            if($('#tableFrom .J_chk:checked').length == 0){
                bool = false;
                alert('请至少选择一条记录操作');
            }else{
                var xuhao = '';
                $('#tableFrom .J_chk:checked').each(function () {
                    if($(this).attr('data-checkStatus') == '2'){
                        bool = false;
                        if(xuhao == '')
                            xuhao = $(this).attr('data-count');
                        else
                            xuhao = xuhao + ',' + $(this).attr('data-count') ;
                    }
                });
                if(!bool){
                   alert('采购退货单已审核：序号' + xuhao);
                }
            }
            if(bool){
                console.log($('#tableFrom').serialize());
                $.post('${root}/scms/ERPPurchaseStock/bacthCheck/2.do',$('#tableFrom').serialize(),function (data) {
                    if(data.success){
                        alert('审核成功');
                        window.location.reload();
                    }else{
                        alert(data.message);
                    }
                },'json');
            }
        });
    });
</script>
</body>
</html>
