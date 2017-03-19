<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>街坊店宝</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <%@ include file="../common/head.jsp"%>
    <link rel="stylesheet" href="${root}/resources/css/wallet.css?v">
    <script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
    <script src="${root}/resources/js/comm.js?vvv"></script>
</head>
<body>
<div class="wrap-bd">
	<div class="mb-small">
	    <span style="font-size: 14px;">当前位置：</span>
	    <a href="${root}/scms/sp/toSpWalletIndex.do" class="crumb">我的钱包</a><span class="crumb crumb-active">收支明细</span>
	</div>
	<div class="op-section">
		<div class="title mb-default">收支明细</div>
		<form action="#" name="frm_query" method="get" id="incomForm">
			<label>查询日期</label>
			<input class="input-search-date" type="text" name="startTime" id="time_start" value="" placeholder="请选择日期">&nbsp;至&nbsp;
			<input class="input-search-date" type="text" name="endTime" id="time_end" value="" placeholder="请选择日期">
			<input class="input-search-text ml-default" type="text" name="orderId" id="spWalletId" value="" placeholder="交易编号">
			<button class="input-search-button" type="button" id="sub">查询</button>
			<br /><br />
			收入：<b class="wallet-money color-green" id="income"></b>&nbsp;元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			支出：<b class="wallet-money color-red" id="outlay"></b>&nbsp;元
		</form>
	</div>
	<div>
		<table class="table-list">
			<thead class="table-thead">
				<tr class="table-header">
					<th>创建时间</th>
					<th>交易编号</th>
					<th>名称|交易号</th>
					<th>收入（元）</th>
					<th>支出（元）</th>
					<th>账户余额</th>
					<th>支付方式</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody class="table-tbody">

			</tbody>
		</table>
	</div>
	<%@ include file="../common/pagination.jsp"%>
	<script>
    	$(function() {
			dateRange('#time_start', '#time_end', 1);
            $("#jpagination").pagination({
                pageSize: 10,
                remote: {
                    url: '${root}/scms/sp/getSpWalletLog.do',
                    success: function(data) {
                        if(data.flag){
                             var html='';
                            $.each(data.list, function(i,item) {
                                html+='<tr><td><span class="color-gray">'+item.addTime+'</span></td>'
                                    +'<td><span class="color-gray">'+item.sort+'</span></td>';
                                html += '<td>'+ item.subjectTypeStr +'<br /><span class="color-gray">备注：'+ item.remark +'</span></td>';

                                html+= '<td class="wallet-money color-green">'+item.shouru+'</td>'
                                    +'<td class="wallet-money color-red">'+item.zhichu+'</td>'
                                    +'<td class="wallet-money">'+item.balance+'</td>'
                                    +'<td>'+item.tradeWayStr+'</td>';


                                switch (item.businessType){
                                    case 1001:
                                        html += '<td>'
                                            +'<form method="post" action="${root}/scms/orderctl/GetSpOrderInfo.do">'
                                            +'<input type="hidden" value="'+item.voucherSub+'" name="storeid"><input class="button-operate" type="submit" value="查看">'
                                            +'</form>'
                                            +'</td>';
                                        break;
                                    case 1002:
                                        html += '<td>充值</td>';
                                        break;
                                    case 1003:
                                        html += '<td>'
                                            +'<form method="post" action="${root}/scms/sp/toSpWithDrawDealLog.do">'
                                            +'<input type="hidden" name="withDrawId" value="'+item.voucherSub+'"></input><input class="button-operate" type="submit" value="查看">'
                                            +'</form>'
                                            +'</td>';
                                        break;
                                    case 1006:
                                        html += '<td>'
                                        html += '<form method="get" action="${root}/scms/scOrder/scorderdetail.do" target="_blank">';
                                        html += '<input type="hidden" value="'+item.voucherSub+'" name="orderId"><input class="button-operate" type="submit" value="查看">';
                                        html += '</form></td>';
                                        break;
                                    default :
                                        html += '<td>转角代扣</td>';

                                }
                            });
                            $('#income').html(data.map.income);
                            $('#outlay').html(data.map.outlay);
                            $('.table-tbody').html(html);
                        }

                    },
                    totalName:'totalSize',
                    pageParams: function (data) {
                        return {
                            pageIndex: data.pageIndex + 1,
                            pageSize: data.pageSize
                        }
                    }
                }
            });
            $('#sub').on('click', function() {
            	$("#jpagination").pagination('setParams', $('#incomForm').serialize());
              	$("#jpagination").pagination('remote');

            });
		})
	</script>
</div>
</body>
</html>
