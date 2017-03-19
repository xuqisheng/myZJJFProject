<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>采购退货</title>
		<link rel="stylesheet" type="text/css" href="${root }/resources/css/base.css" />
		<script src="${root }/resources/vendor/jquery/jquery-1.12.4.min.js"></script>
		<script src="${root }/resources/js/comm.js"></script>
		<script src="${root }/resources/vendor/My97DatePicker/WdatePicker.js"></script>
		<%@ include file="../../common/head.jsp"%>
	</head>

	<body>
		<div class="wrap-bd">
			<div class="mb-small clearfix">
				<div class="title">销售退货管理</div>
			</div>
			<div class="bg wrap-bd mb-default">
                <form action="${root}/scms/ERPMarketStock/list/2.do" id="form">
                    <div class="">
                        <label >关键词：</label>
                        <input type="text" class="input input-default mr-default" name="orderId" value="${commod.orderId}" placeholder="退货单号/店铺名称" />
                        <label >审核状态：</label>
                        <select  class="select mr-default" name="checkStatus">
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
			</div>
            <div class="mt-default mb-default" id="action">
                <input type="button" class="button button-operate mr-small add" id="J_add" value="新建" />
                <input type="button" class="button button-operate mr-small edit" value="修改" />
                <input type="button" class="button button-operate mr-small detail" value="查看" />
                <input type="button" class="button button-operate mr-small delete" value="删除" />
                <input type="button" class="button button-operate mr-small check" value="审核" />
            </div>
            <form id="tableFrom">
                <input type="hidden" name="token" value="${token}" />
                <table class="table-list mb-default">
                    <thead>
                        <tr>
                            <th><input type="checkbox" class="checkbox"  id="J_selectAll"  /></th>
                            <th>序号</th>
                            <th>退货单号</th>
                            <th>店名</th>
                            <th>退货金额</th>
                            <th>退货数量</th>
                            <th>退货人</th>
                            <th>退货时间</th>
                            <th>是否审核</th>
                            <th>审核人</th>
                            <th>审核时间</th>
                        </tr>
                    </thead>
                    <tbody class="table-tbody" id="J_salBackTbody">

                    <c:forEach items="${list}" var="ele" varStatus="eleStat">
                        <tr>
                            <td><input type="checkbox" class="checkbox J_chk" name="ids" value="${ele.id}" data-pId="${ele.pId}" data-checkStatus = "${ele.checkStatus}" data-count="${eleStat.count}" data-pOrderId="${ele.pOrderId}" data-orderId="${ele.orderId}"/></td>
                            <td>${eleStat.count}</td>
                            <td>${ele.orderId}</td>
                            <td>${ele.storeName}</td>
                            <td>${ele.itemPrice}</td>
                            <td>${ele.itemQuantity}</td>
                            <td>${ele.addUserName}</td>
                            <td><fmt:formatDate value="${ele.addTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                            <td>${ele.checkStatusStr}</td>
                            <td>${ele.checkUserName}</td>
                            <td><fmt:formatDate value="${ele.checkTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                        </tr>
                    </c:forEach>
                    <c:if test="${fn:length(list) == 0}">
                        <tr><td colspan="11" class="no-data"></td></tr>
                    </c:if>
                    </tbody>

                    </table>
                </form>
                <c:if test="${fn:length(list)>0}">
                    <%@ include file="../../common/pagination-kk.jsp"%>
                </c:if>
		</div>
	</body>
	<script>
		$(function(){
            dateRange('.J_ds', '.J_de', 1);
            selectAll('#J_selectAll', '.J_chk');
            /*selectTr('.table-list tbody','tr');*/
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
            });

            $('#sub').on('click', function(e) {
                e.preventDefault();
                $('#form').submit();
            });

			//选中checkBox
			$('#J_salBackTbody').on('click','.J_chk',function(){
				if($(this).is(':checked')){
					$(this).addClass('isChk');
				}else{
					$(this).removeClass('isChk');
				}
			});
            $('#action').on('click','.add' , function () {
                window.location.href = '${root}/scms/ERPMarketStock/edit/1/1.do';
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
                        alert('入库单已审核：序号' + xuhao);
                    }
                }
                if(bool){
                    var id = $('#tableFrom .J_chk:checked').val();
                    var pId = $('#tableFrom .J_chk:checked').attr('data-pId');
                    window.location.href = '${root}/scms/ERPMarketStock/edit/'+pId+'/'+id+'.do';
                }
            }).on('click','.detail' , function () {
                if($('#tableFrom .J_chk:checked').length != 1){
                    alert('请选择一条记录操作');
                }else{
                    var id = $('#tableFrom .J_chk:checked').val();
                    window.location.href = '${root}/scms/ERPMarketStock/detail/'+id+'.do';
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
                        alert('入库单已审核：序号' + xuhao);
                    }
                }
                if(bool){
                    $.post('${root}/scms/ERPMarketStock/bacthDelete.do',$('#tableFrom').serialize(),function (data) {
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
                        alert('入库单已审核：序号' + xuhao);
                    }
                }
                if(bool){
                    console.log($('#tableFrom').serialize());
                    $.post('${root}/scms/ERPMarketStock/bacthCheck/2.do',$('#tableFrom').serialize(),function (data) {
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
