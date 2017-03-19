<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>

<head>
	<meta charset="UTF-8">
	<title>供应商管理</title>
	<link rel="stylesheet" type="text/css" href="${root}/resources/css/base.css" />
	<script src="${root}/resources/vendor/jquery/jquery-1.12.4.min.js"></script>
	<script src="${root}/resources/js/comm.js"></script>
	<script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
</head>

<body>
	<div class="wrap-bd">
		<div class="">
			<div class="mb-default title">损益单列表</div>
		</div>
        <form action="${root}/scms/ERPProfit.do" id="form">
            <div class="bg wrap-bd">
                <label>损益单：</label>
                <select name="type"  class="select">
                    <option value="0" selected="selected">全部</option>
                    <c:forEach items="${profitTypes}" var="profitType">
                        <option value="${profitType.index}" <c:if test="${commod.type == profitType.index}" >selected="selected"</c:if>>${profitType.name}</option>
                    </c:forEach>
                </select>
                <label class="ml-default">审核状态：</label>
                <select name="checkStatus"  class="select">
                    <option value="0" selected="selected">全部</option>
                    <c:forEach items="${checkStatus}" var="status">
                        <option value="${status.index}" <c:if test="${commod.checkStatus == status.index}" >selected="selected"</c:if>>${status.name}</option>
                    </c:forEach>
                </select>
                <label class="ml-default">选择日期：</label>
                <input type="text" class="input input-search-date J_ds" name="beginTime" value="<fmt:formatDate value="${commod.beginTime}" pattern='yyyy-MM-dd'/> "/>&nbsp;至&nbsp;
                <input type="text" class="input input-search-date mr-default J_de" name="endTime" value="<fmt:formatDate value="${commod.endTime}" pattern='yyyy-MM-dd'/>"/>
                <input type="submit" class="input-search-button" value="搜索" id="sub" />
            </div>
        </form>
		<div class="mb-default mt-default action">
			<input type="button" class="button button-operate mr-small add"  value="新建" />
			<input type="button" class="button button-operate mr-small edit"  value="修改" />
			<input type="button" class="button button-operate mr-small delete"  value="删除" />
			<input type="button" class="button button-operate mr-small check"  value="审核" />
			<input type="button" class="button button-operate mr-small detail"  value="查看" />
		</div>
        <form id="stockFrom">
		<table class="table-list table-border" id="stock_info">
			<thead>
				<tr>
					<th><input type="checkbox" class="checkbox" name="" id="J_selectAll" value="" /></th>
					<th>序号</th>
					<th>单据编号</th>
					<th>单据类型</th>
					<th>损益数量</th>
					<th>损益金额</th>
					<th>创建人</th>
                    <th>创建时间</th>
                    <th>审核状态</th>
                    <th>审核人</th>
                    <th>审核时间</th>
                    <th>备注</th>
				</tr>
			</thead>
			<tbody class="table-tbody">
                <c:forEach items="${list}" var="ele" varStatus="eleStat">
                    <tr>
                        <td><input type="checkbox" class="checkbox J_chk" name="ids" value="${ele.id}" data-checkStatus = "${ele.checkStatus}" data-count="${eleStat.count}"/></td>
                        <td>${eleStat.count}</td>
                        <td>${ele.orderId}</td>
                        <td>${ele.typeStr}</td>
                        <td><c:if test="${ele.type == 1}">-</c:if>${ele.itemQuantity}</td>
                        <td><c:if test="${ele.type == 1}">-</c:if>${ele.itemPrice}</td>
                        <td>${ele.addUserName}</td>
                        <td><fmt:formatDate value="${ele.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>${ele.checkStatusStr}</td>
                        <td>${ele.checkUserName}</td>
                        <td><fmt:formatDate value="${ele.checkTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>${ele.remark}</td>
                    </tr>
                </c:forEach>
                <c:if test="${fn:length(list) == 0}">
                    <tr><td colspan="12" class="no-data"></td></tr>
                </c:if>
			</tbody>
		</table>
        <c:if test="${fn:length(list)>0}">
            <%@ include file="../../common/pagination-kk.jsp"%>
        </c:if>
        </form>
	</div>
</body>
<script>
	$(function() {
        $('#sub').on('click', function(e) {
            e.preventDefault();
            $('#form').submit();
        });
		dateRange('.J_ds', '.J_de', 1);
		selectAll('#J_selectAll', '.J_chk');
        /*selectTr('.table-list tbody','tr');*/
		/*$('.table-list td').click(function() {
            $(this).parent().css({'background':'#009dd9','color':'white'}).find('.checkbox').addClass('isChk').prop('checked',true).parent().parent().siblings().css({'background':'#fff','color':'black'}).find('.checkbox').removeClass('isChk').attr("checked",false);

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

        var bool = true;
        $('.action').on('click','.add' , function () {
            window.location.href = '${root}/scms/ERPProfit/toEdit/.do';
        }).on('click','.edit' , function () {
            bool = true;
            if($('#stockFrom .J_chk:checked').length != 1){
                bool = false;
                alert('请选择一条记录操作');
            }else{
                var xuhao = '';
                $('#stockFrom .J_chk:checked').each(function () {
                    if($(this).attr('data-checkStatus') == '2'){
                        bool = false;
                        if(xuhao == '')
                            xuhao = $(this).attr('data-count');
                        else
                            xuhao = xuhao + ',' + $(this).attr('data-count') ;
                    }
                });
                if(!bool){
                    alert('损益单已审核：序号' + xuhao);
                }
            }
            if(bool){
                var id = $('#stock_info .J_chk:checked').val();
                window.location.href = '${root}/scms/ERPProfit/toEdit/'+id+'.do';
            }
        }).on('click','.detail' , function () {
            if($('#stock_info .J_chk:checked').length != 1){
                alert('请选择一条记录操作');
            }else{
                var id = $('#stock_info .J_chk:checked').val();
                window.location.href = '${root}/scms/ERPProfit/detail/'+id+'.do';
            }
        }).on('click','.delete' , function () {
            bool = true;
            if($('#stockFrom .J_chk:checked').length == 0){
                bool = false;
                alert('请至少选择一条记录操作');
            }else{
                var xuhao = '';
                $('#stockFrom .J_chk:checked').each(function () {
                    if($(this).attr('data-checkStatus') == '2'){
                        bool = false;
                        if(xuhao == '')
                            xuhao = $(this).attr('data-count');
                        else
                            xuhao = xuhao + ',' + $(this).attr('data-count') ;
                    }
                });
                if(!bool){
                    alert('损益单已审核：序号' + xuhao);
                }
            }
            if(bool){
                $.post('${root}/scms/ERPProfit/bacthDelete.do',$('#stockFrom').serialize(),function (data) {
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
            if($('#stock_info .J_chk:checked').length == 0){
                bool = false;
                alert('请至少选择一条记录操作');
            }else{
                var xuhao = '';
                $('#stock_info .J_chk:checked').each(function () {
                    if($(this).attr('data-checkStatus') == '2'){
                        bool = false;
                        if(xuhao == '')
                            xuhao = $(this).attr('data-count');
                        else
                            xuhao = xuhao + ',' + $(this).attr('data-count') ;
                    }
                });
                if(!bool){
                    alert('损益单已审核：序号' + xuhao);
                }
            }
            if(bool){
                console.log($('#stock_info').serialize());
                $.post('${root}/scms/ERPProfit/bacthCheck/2.do',$('#stockFrom').serialize(),function (data) {
                    if(data.success){
                        alert('审核成功');
                        window.location.reload();
                    }else{
                        alert(data.message);
                    }
                },'json');
            }
        });
	})
</script>

</html>
