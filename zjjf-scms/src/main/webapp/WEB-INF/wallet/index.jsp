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
</head>
<body>
<div class="wrap-bd">
	<div class="wallet-center">
	    <div class="wallet-center-title">
	        <h2>钱包中心</h2>
	        <a href="${root}/scms/sp/toSpWalletLog.do" class="color-a">收支明细</a>
	    </div>
	    <div class="wallet-center-content">
	        <h3>钱包余额（元）</h3>
	        <div>
	            <span class="balance">${wallet}</span>
	            <!--${root}/Customer/SupplierWallet/toSpWithDraw.do  -->
	            <a href="${root}/scms/sp/toSpWithDraw.do" class="color-a"><button class="button-save fr">提现</button></a>
	        </div>
	    </div>
	</div>
	<div class="title mt-default mb-small">提现记录</div>
	<div class="op-section mt-small">
		<form name="frm_query" method="post" id="test">
			<label>起止日期：</label>
			<input class="input-search-date" type="text" name="startDate" id="time_start" value="" placeholder="选择日期"> -
			<input class="input-search-date" type="text" name="endDate" id="time_end" value="" placeholder="选择日期">
			<input type="hidden" name="thisMonth" id="thisMonthStr" value="">
			<input type="hidden" name="thisDay" id="thisDayStr" value="">
			<input type="button" class="input-search-button" value="查询" id="sub"/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<span class="pills" id="thisDay">今天</span>
			<span class="pills" id="thisMonth">本月</span><!-- pills-active -->
		</form>
		<script>
			$(function(){
				var $time_start = $('#time_start');
				var $time_end = $('#time_end');
 				var date = new Date();
				var $month = date.getMonth() + 1;
				var $day = date.getDate();
				var $date_end = date.getFullYear() + '-' + ($month>9?$month:('0'+$month)) + '-' + ($day>9?$day:('0'+$day));
				$time_start.on('focus',function(){
					WdatePicker({
						isShowClear:false,
						isShowWeek:true,
						maxDate:$date_end,
						onpicked:function(){
							$time_end.focus();
							$("#thisMonthStr").val("");
							$("#thisDayStr").val("");
						}
					});
				});
				$time_end.on('focus',function(){
					WdatePicker({
						isShowClear:false,
						isShowWeek:true,
						minDate:$time_start.val(),
						maxDate:$date_end
					});
				});


			});
		</script>
	</div>
	<div>
        <table class="table-list table-border">
            <thead class="table-thead">
                <tr>
                    <th>提现编号</th>
                    <th>创建时间</th>
                    <th>提现记录</th>
                    <th>对方</th>
                    <th>金额（元）</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody class="table-tbody">
                <!--
                <tr>
                    <td colspan="7" style="padding:45px 0;text-align: center;color:#aaa;font-size:16px;">
                        <img src="resources/images/walletnotice.png" alt=""/>&nbsp;&nbsp;提现记录为空
                    </td>
                </tr>
                <tr>
                    <td>
                        2015.09.16<br />
                        <span class="color-gray">16:36</span>
                    </td>
                    <td>
                        钱包-提现<br />
                        <span class="color-gray">银行流水号：201509…458</span>
                    </td>
                    <td>
                        中国建设银行<br />
                        <span class="color-gray">…6587|*平</span>
                    </td>
                    <td class="money">-800.0</td>
                    <td>
                        待处理
                        处理中
                        处理完成
                        提现失败
                    </td>
                    <td>
                        <a class="color-a" href="#">查看</a>
                    </td>
                </tr> -->

                <%-- <c:if test="${SpWithDrawList.size()==0}">
                <tr>
                    <td colspan="7" style="padding:45px 0;text-align: center;color:#aaa;font-size:16px;">
                        <img src="${root}/resources/images/walletnotice.png" alt=""/>&nbsp;&nbsp;提现记录为空
                    </td>
                </tr>
                </c:if> --%>

                <%-- <c:forEach items="${list}" var="log">
                <tr>
                   <td>
                        <span class="color-gray">${log.id}</span>
                    </td>
                    <td>
                        <fmt:formatDate value="${log.applyTime}" pattern="yyyy-MM-dd"/><br />
                        <span class="color-gray"><fmt:formatDate value="${log.applyTime}" pattern="HH:mm"/></span>
                    </td>
                    <td>
                        钱包-提现<br />
                        <span class="color-gray">银行流水号：${log.bankDealNo}</span>
                    </td>
                    <td>
                        ${log.cardBankName}<br/>
                        <span class="color-gray">${log.cardUserName}|${log.cardNo}</span>
                    </td>
                    <td class="money">${log.amount}</td>
                    <td>
                      <c:choose>
                       <c:when test="${log.status==1}">
                            <span style="color: #333">待处理</span>
                       </c:when>
                       <c:when test="${log.status==2}">
                           <span style="color: #06ada3">审核通过，等待打款</span>
                       </c:when>
                       <c:when test="${log.status==3}">
                           <span style="color: #ed4b1c">没能通过审核，提现失败</span>
                       </c:when>
                       <c:when test="${log.status==4}">
                           <span style="color: #333">生成计算单，打款中</span>
                       </c:when>
                       <c:when test="${log.status==5}">
                           <span style="color: #333">已打款</span>
                       </c:when>
                       <c:when test="${log.status==6}">
                           <span style="color: #333">已退款</span>
                       </c:when>
                      </c:choose>
                    </td>
                    <td>
                       <c:choose>
                          <c:when test="${log.status==6}">
                            <a class="color-a" href="#">查看</a>
                          </c:when>
                          <c:otherwise>
                            <a class="color-a" href="${root}/Customer/SupplierWallet/toSpWithDrawDealLog.do?status=${log.id}">查看</a>
                          </c:otherwise>
                       </c:choose>

                    </td>
                </tr>
              </c:forEach> --%>
            </tbody>
        </table>
    </div>
    <%@ include file="../common/pagination.jsp"%>
</div>
<script>
	$(function() {
           $("#jpagination").pagination({
               pageSize: 10,
               remote: {
                   url: '${root}/scms/sp/getWithDrawList.do',
                   success: function(data) {
                       var html='';
                       $.each(data.list, function(i,item) {
                       	html+='<tr><td><span class="color-gray">'+item.id+'</span></td>'
                       	      +'<td><span class="color-gray">'+item.applyTime+'<span/></td>';
                       	      if(item.bankDealNo!=null){
                       	    	  html+='<td>钱包-提现<br /><span class="color-gray">银行流水号：'+item.bankDealNo+'</span></td>';
                       	      }else{
                       	    	  html+='<td>钱包-提现<br /><span class="color-gray">银行流水号：</span></td>';
                       	      }
                       	      html+='<td>'+item.cardBankName+'<br/><span class="color-gray">'+item.cardUserName+'|'+item.cardNo+'</span></td>'
                       	      +'<td class="money">'+item.amount+'</td>';
                       	     if(item.status==1){
                       	    	     html+='<td><span class="txt-log">待处理</span></td>'
                       	    	         +'<td><a href="${root}/scms/sp/toSpWithDrawDealLog.do?withDrawId='+item.id+'">查看</a></td>';
                       	     }
                       	     if(item.status==2){
                       	    	 html+='<td><span class="txt-info">生成结算单，审核中</span></td>'
                       	    	     +'<td><a href="${root}/scms/sp/toSpWithDrawDealLog.do?withDrawId='+item.id+'">查看</a></td>'
                       	     }
                       	     if(item.status==3){
                       	    	 html+='<td><span>生成结算单，打款中</span></td>'
                       	    	     +'<td><a href="${root}/scms/sp/toSpWithDrawDealLog.do?withDrawId='+item.id+'">查看</a></td>'

                       	     }
                       	     if(item.status==4){
                       	    	 html+='<td><span class="txt-warn">没能通过审核，提现失败</span></td>'
                       	    	     +'<td><a href="${root}/scms/sp/toSpWithDrawDealLog.do?withDrawId='+item.id+'">查看</a></td>'
                       	     }
                       	     if(item.status==5){
                       	    	 html+='<td><span class="txt-success">已打款</span></td>'
                       	    	     +'<td><a href="${root}/scms/sp/toSpWithDrawDealLog.do?withDrawId='+item.id+'">查看</a></td>'
                       	     }
                       	     if(item.status==6){
                       	    	 html+='<td><span class="txt-info">已退款</span></td><td></td>';
                       	     }
                       });
                       if('' == html) {
                    	   html = '<tr><td colspan="7" class="no-data"></td></tr>';
                       }
                       $('.table-tbody').html(html);
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
           $('#sub').on('click', function(e) {
           	e.preventDefault();
			$("#thisMonth").removeClass("pills-active");
			$("#thisDay").removeClass("pills-active");
           	 $("#jpagination").pagination('setParams', $('#test').serialize());
              	 $("#jpagination").pagination('remote');
           });

           if($("#thisMonthStr").val()==1){
			$("#thisMonth").addClass("pills-active");
		}

		if($("#thisDayStr").val()==1){
			$("#thisDay").addClass("pills-active");
		}

		$("#thisDay").click(function(e){
			$("#thisMonth").removeClass("pills-active");
			$(this).addClass("pills-active");
			$("#thisMonthStr").val("");
			$('#time_start').val("");
			$('#time_end').val("");
			$("#thisDayStr").val("1");

			$("#jpagination").pagination('setParams', $('#test').serialize());
			$("#jpagination").pagination('setPageIndex', 0);
             	$("#jpagination").pagination('remote');
		});

		$("#thisMonth").click(function(e){
			$("#thisDay").removeClass("pills-active");
			$(this).addClass("pills-active");
			$('#time_start').val("");
			$('#time_end').val("");
			$("#thisDayStr").val("");
			$("#thisMonthStr").val("1");

			$("#jpagination").pagination('setParams', $('#test').serialize());
			$("#jpagination").pagination('setPageIndex', 0);
             	$("#jpagination").pagination('remote');
		});

	})
</script>
</body>
</html>
