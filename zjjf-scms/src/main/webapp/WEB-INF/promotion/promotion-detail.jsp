<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>促销管理</title>
<%@ include file="../common/head.jsp"%>
<script src="${root}/resources/js/comm.js"></script>
</head>
<body>
	<div class="wrap-bd">
		<div>
			<span style="font-size: 14px;">当前位置：</span> <a class="crumb">促销管理</a><span
				class="crumb crumb-active">活动详情</span>
		</div>
		<div class="title mb-small">活动详情</div>
		<div class="wrap-bd bg">
			<div>
				<label class="label">名称：</label> ${active.ruleName}
			</div>
			<p>
				<label class="label">活动时间：</label>
				<fmt:formatDate value="${active.ruleStart}"
					pattern="yyyy-MM-dd HH:mm:ss" />
				--
				<fmt:formatDate value="${active.ruleEnd}"
					pattern="yyyy-MM-dd HH:mm:ss" />
			</p>
			<p>
				<label class="label">活动说明：</label> ${active.ruleRemark}
			</p>
			<div>
				<label class="label va-t">活动类型：</label>
				<div style="width: 800px; line-height: 34px; display: inline-block;">
                    <div class="mb-small">${active.ruleTypeStr}</div>
					<!--注册送优惠劵  -->
					<c:if test="${active.ruleType==1 || active.ruleType==13}">
						<table class="table-list table-border">
							<thead class="table-thead">
								<tr>
									<th>名称</th>
									<th>面值</th>
									<th>有效期</th>
									<th>使用金额限制</th>
									<th>满多少送优惠劵</th>
									<th>每天限制张数</th>
								</tr>
							</thead>
							<tbody class="table-tbody">
								<tr>
									<td>${temp.ruleName}</td>
									<td>￥${temp.useMoney}</td>
									<td>${temp.useDay}天</td>
									<td>${temp.startPrice}</td>
									<td></td>
									<td>
                                        <c:if test="${active.ruleType==13}">
                                            ${active.ruleSendLimit}
                                        </c:if>
                                    </td>
								</tr>
							</tbody>
						</table>
					</c:if>
					<!-- 满送优惠劵和满送优惠劵+商品 -->
					<c:if test="${active.ruleType==2||active.ruleType==9}">
						<table class="table-list table-border">
							<thead class="table-thead">
								<tr>
									<th>名称</th>
									<th>面值</th>
									<th>有效期</th>
									<th>使用金额限制</th>
									<th>满多少送优惠劵</th>
									<th>每天限制张数</th>
								</tr>
							</thead>
							<tbody class="table-tbody">
								<c:forEach var="item" items="${gradeVoList}">
									<tr>
										<td>${item.spVoucherTemp.ruleName}</td>
										<td>￥${item.spVoucherTemp.useMoney}</td>
										<td>${item.spVoucherTemp.useDay}天</td>
										<td>${item.spVoucherTemp.startPrice}</td>
										<td>${item.startPrice}</td>
										<td>${item.sendLimit}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>

				</div>
			</div>
            <%--提前下单送优惠劵--%>
            <c:if test="${active.ruleType==13}">
                <p>
                    <label class="label">送达时间：</label> ${sendTimeConfig.name}
                </p>
            </c:if>
			<c:if test="${active.ruleType==12}">
				<div class="mt-small condition" id="buyGoodsDiv">
					<input type="hidden" value="" name="buyGoodsItemBaseId"
						id="buyGoodsItemBaseIdInput"> <label class="label va-t">购满商品：</label>
					<div style="width: 800px; display: inline-block">
						<p id="buyGoodsP">
							<span id="seeBuyGoodsNameInput"></span>&nbsp;&nbsp; 购买数量： <span
								id="buyGoodsNumInput"></span>
						</p>
					</div>
				</div>
			</c:if>
			<c:if test="${active.ruleType==3||active.ruleType==10}">
				<div class="condition" id="ruleContentDiv">
					<div class="clearfix">
						<div class="fl">
						   <div class="mt-small mb-small">
								<label class="label">钱包支付：</label>
								<!--payMethod=4  -->
								<span id="qianbaogMethod"></span>
								<!-- <input class="input input-default" type="text" name="weixingMethod" id="weixingMethod"> -->
							</div>
							<div class="mt-small mb-small">
								<label class="label">微信支付：</label>
								<!--payMethod=4  -->
								<span id="weixingMethod"></span>
								<!-- <input class="input input-default" type="text" name="weixingMethod" id="weixingMethod"> -->
							</div>
							<div class="mt-small mb-small">
								<label class="label">货到付款：</label>
								<!--payMethod=2  -->
								<span id="huodaoMethod"></span>
								<!-- <input class="input input-default" type="text" name="huodaoMethod" id="huodaoMethod"> -->
							</div>
							<div class="mt-small mb-small">
								<label class="label">支付宝支付：</label>
								<!--payMethod=3  -->
								<span id="zhifubaoMethod"></span>
								<!-- <input class="input input-default" type="text" name="zhifubaoMethod" id="zhifubaoMethod"> -->
							</div>
							<div class="mt-small mb-small">
								<label class="label">快捷支付：</label>
								<!--payMethod=1  -->
								<span id="kuaiqianMethod"></span>
								<!-- <input class="input input-default" type="text" name="kuaiqianMethod" id="kuaiqianMethod"> -->
							</div>
						</div>
					</div>
				</div>

				<div>
		       <label class="label">商品/类别：</label>
		       ${useItemFlag}
		 </div>
		 <c:if test="${active.useItemFlag==2}">
		 <div>
		    <label class="label va-t fl">排除指定商品：</label>
		    <div class="fl" style="width: 90%;">
                   <table class="table-list table-border">
                       <thead class="table-thead">
                       <tr>
                           <th width="80">类型</th>
                           <th>类别/商品</th>
                           <th width="80"></th>
                           <th>操作</th>
                       </tr>
                       </thead>
                       <tbody class="table-tbody" id="paichuTbody">
                       <tr>
                           <!-- <td colspan="4" class="ta-l">
                               <span class="button button-operate ml-default" id="paiChuAdd">增加</span>
                           </td> -->
                       </tr>
                       </tbody>
                   </table>
             </div>
		 </div>
		 </c:if>
		 <c:if test="${active.useItemFlag==1}">
		 <label class="label va-t fl">指定购买商品：</label>
              <div class="fl" style="width: 90%;">
                  <table class="table-list table-border">
                      <thead class="table-thead">
                      <tr>
                          <th width="80">类型</th>
                          <th>类别/商品</th>
                          <th width="80"></th>
                          <th>操作</th>
                      </tr>
                      </thead>
                      <tbody class="table-tbody" id="zhidingTbody">
                      <tr>
                          <!-- <td colspan="5" class="ta-l">
                              <span class="button button-operate ml-default" id="zhidingAdd">增加</span>
                          </td> -->
                      </tr>
                      </tbody>
                  </table>
           </div>
		 </c:if>
			</c:if>
			<%-- <c:if
				test="${active.ruleType==2||active.ruleType==9||active.ruleType==11||active.ruleType==12}">
				<p>
					<label class="label">支付方式：</label>
					<c:forEach items="${RulePayStr}" var="item">
						<c:if test="${item==1}">快钱支付&nbsp;&nbsp; </c:if>
						<c:if test="${item==2}">货到付款&nbsp;&nbsp;</c:if>
						<c:if test="${item==3}">支付宝支付&nbsp;&nbsp;</c:if>
						<c:if test="${item==4}">微信支付 &nbsp;&nbsp;</c:if>
					</c:forEach>


				</p>
			</c:if> --%>
			<%-- <c:if
				test="${active.ruleType==2||active.ruleType==9||active.ruleType==11}">
				<p class="required condition" id="ruleStartPriceP">
					<label class="label">活动规则：</label> 单笔订单满 <input type="text"
						style="width: 80px" name="ruleStartPriceStr" id="ruleStartPrice"
						value="${active.ruleStartPrice}"> <span class=""
						style="color: red;" id="startPriceError"></span> 元
				</p>
			</c:if> --%>
			<c:if
				test="${active.ruleType==1}">
				<p>
					<label class="label">每天限制：</label> ${active.ruleSendLimit}张
				</p>
			</c:if>
			<p>
				<label class="label">支付方式：</label>
				${RulePayStr}
				<%-- <c:forEach items="${RulePayStr}" var="item">
					<c:if test="${item==1}">快钱支付&nbsp;&nbsp; </c:if>
					<c:if test="${item==2}">货到付款&nbsp;&nbsp;</c:if>
					<c:if test="${item==3}">支付宝支付&nbsp;&nbsp;</c:if>
					<c:if test="${item==4}">微信支付 &nbsp;&nbsp;</c:if>
				</c:forEach> --%>
			</p>

			<c:if test="${show==null}">
			<p>
				<label class="label">费用分摊：</label> 转角承担${active.plantHalveStr}%
				批发商承担${active.spHalveStr}%
			</p>
			</c:if>
			<%-- <div class="mb-default">
            <label class="label va-t">满减内容：</label>
            <div style="width:800px;display: inline-block;">
                ${active.ruleContent}<br>
            </div>
        </div> --%>
			<c:if
				test="${active.ruleType==9||active.ruleType==10||active.ruleType==11||active.ruleType==12}">
				<div class="mb-default">
					<label class="label">满送商品：</label>
					<div style="width: 800px; display: inline-block;">
						<c:forEach items="${result}" var="item">
							<span class="mr-default"> ${item[1]}</span>
							<span class="ml-default mr-default">赠送数量：${item[2]}</span>
							<span class="ml-default">赠送总量： ${item[3]}</span>
							<br>
						</c:forEach>
					</div>
				</div>
			</c:if>
			<div>
				<label class="label"></label>

				<c:if test="${numberInt!=1}">
					<input type="button" value="参与" class="button-save" id="J_join">
				</c:if>

				<input type="button" value="返回" class="button-cancel"
					onclick="history.go(-1)">
			</div>

		</div>
	</div>
	<!-- 参加活动客户dialog -->
	<form action="#" id="formId">
		<input type="hidden" value="${active.id}" name="spVoucherActiveId"
			id="spVoucherActiveId">
		<div class="dialog hidden" id="J_dialogJoinCustomer"
			style="min-width: 600px;">
			<div class="dialog-head">
				参加活动客户 <span class="dialog-close"></span>
			</div>
			<div class="dialog-body">

				<div id="J_zssp">
					<label class="label va-t">赠送商品</label>
					<div style="display: inline-block; width: 600px">
						<div class="mb-default">
							<span class="button-operate" id="J_selectGoods">+选择商品</span>
						</div>
						<div style="max-height: 220px; overflow: auto;" id="product">


						</div>
					</div>
				</div>


				<!--
        <div class="mt-default">
            <label class="label">参与客户</label>
             <input type="radio" name="c" data-groupname="aa" data-tab="item"> 全部客户
            <input type="radio" name="c" data-groupname="aa" data-tab="item" class="ml-default"> 部分客户
        </div>
        <div data-groupname="aa" data-tab="content"></div>
        -->
				<div data-groupname="aa" data-tab="content">
					<label class="label"></label>
					<div
						style="display: inline-block; width: 600px; max-height: 220px; overflow: auto;"
						id="group1">
						<!-- <p>指定分组（2 / 5）</p> -->
						<!--  <p>
                    <input type="checkbox"> 分组名1
                    <input type="checkbox" class="ml-default" > 分组名2
                    <input type="checkbox" class="ml-default"> 分组名3
                    <input type="checkbox" class="ml-default"> 分组名4
                    <input type="checkbox" class="ml-default"> 分组名5
                </p>
                -->
					</div>
				</div>
			</div>
			<div class="dialog-foot">
				<input type="button" class="dialog-button dialog-ok" value="确定">
				<input type="button" class="dialog-button dialog-cancel ml-default"
					value="取消">
			</div>
		</div>
	</form>
	<!--
dialog -->
	<div class="dialog hidden" id="J_dialogSelectGoods">
		<div class="dialog-head">
			选择商品 <span class="dialog-close"></span>
		</div>
		<div class="dialog-body">
			<div>
				<input type="text" class="input-search-text" style="width: 500px;"
					placeholder="商品名称"> <input type="button"
					class="input-search-button" value="搜索">
			</div>
			<div style="height: 350px; overflow: auto">
				<table class="table-list table-border mt-default">
					<thead class="table-thead">
						<tr>
							<th>商品条码</th>
							<th>商品名称</th>
							<th>规格</th>
							<!--   <th>单位</th> -->
						</tr>
					</thead>
					<tbody class="table-tbody" id="productG">
					</tbody>
				</table>
			</div>
		</div>
		<div class="dialog-foot">
			<input type="button" class="dialog-button dialog-ok" value="确定">
			<input type="button" class="dialog-button dialog-cancel ml-default"
				value="取消">
		</div>
	</div>
	<div class="cover-all"></div>
	<script>
    $(function() {

    	 var ruleType = '${active.ruleType}';
         if(ruleType==3||ruleType==10){
          var ruleContent = '${active.ruleContent}';
          var ruleArr = new Array();
          ruleArr = ruleContent.split('&');
          for(var i=0;i<ruleArr.length;i++){
         	 var jsonObject = JSON.parse(ruleArr[i]);
         	 if(jsonObject.paymethod==4){//微信支付
         		 $('#weixingMethod').text(jsonObject.rule);
         	 }else if(jsonObject.paymethod==3){//支付宝支付
         		 $('#zhifubaoMethod').text(jsonObject.rule);
         	 }else if(jsonObject.paymethod==2){//货到付款
         		 $('#huodaoMethod').text(jsonObject.rule);
         	 }else if(jsonObject.paymethod==1){//快捷支付
         		 $('#kuaiqianMethod').text(jsonObject.rule);
         	 }else if(jsonObject.paymethod==5){//钱包支付
         		 $('#qianbaogMethod').text(jsonObject.rule);
         	 }
          }
         }else if(ruleType==12){
         	var buyGoods = '${active.buyGoods}';
         	var goodsArr = new Array();
         	goodsArr = buyGoods.split(":");
         	$('#seeBuyGoodsNameInput').text(goodsArr[1]);
         	$('#buyGoodsNumInput').text(goodsArr[2]);
         }


        dialogPosCenter('#J_dialogJoinCustomer');
        dialogPosCenter('#J_dialogSelectGoods');
        tab('aa');

        // 参加活动客户
        $('#J_join').on('click', function() {
        	var id=$("#spVoucherActiveId").val();
        	$.post('${root}/scms/promotion/groupcheck.do',{"id":id}, function (data) {

    			if(data.success){
    				if(!data.message.product){
    					$('#J_zssp').hide();
    				}else{
    					$('#J_zssp').show();
    				}

    				var html='已参与活动的分组：';
    				 $.each(data.message.already, function(i,item) {
    					 if((i+1)%4==0){
    						 html+="<br/>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp";
    					 }
    					 html+='<input type="checkbox"  class="ml-default" checked="checked" disabled="disabled">'+item.name;
    				})
    				html+="<br/>未参与活动的分组：";
    				 $.each(data.message.all, function(i,item) {
    					 if((i+1)%4==0){
    						 html+="<br/>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp";
    					 }
    					 html+='<input type="checkbox" value="'+item.id+'" class="ml-default"  name="ids">'+item.name;
    				})
    				 $('#group1').html(html);
    				$('#J_dialogJoinCustomer, .cover-all').show();
    			}
    	 	},"json");


        });
        $('#J_dialogJoinCustomer').on('click', '.dialog-close, .dialog-cancel', function() {
            $('#J_dialogJoinCustomer, .cover-all').hide();
        });
        $('#J_dialogJoinCustomer').on('click', '.dialog-ok', function() {
        	 //提交数据
            var data=$("#formId").serialize();

        	$.post('${root}/scms/promotion/addpromotion.do',data, function (data) {
        		if(data.success){
        			window.location="${root}/scms/promotion/moreactive.do";
        			layer.msg(data.message);
        		}else{
        			layer.msg(data.message);
        		}

    	 	},"json");
        });

        // 选择商品
        $('#J_selectGoods').on('click', function() {
        	var len = $("#product p").length;
        	var spVoucherActiveId=$("#spVoucherActiveId").val();
        	if(len <= 0){
        		$.post('${root}/scms/promotion/findproduct.do',{"id":spVoucherActiveId}, function (data) {
	    			if(data.success){
	    				var html='';
	    				 $.each(data.message, function(i,item) {
	    					 html+='<tr class="J_tr"><td>'+item.mdseId+'</td>';
	    					 html+='<td class="J_name"><input type="hidden" name="scmsItemId" value="'+item.scmsid+'">'+item.name+'</td>';
	    					 html+='<td>'+item.spec+'</td></tr>';
	    				 })
	    				$('#productG').html(html);
	    				 dialogPosCenter('#J_dialogSelectGoods');
	    				$('#J_dialogJoinCustomer').hide();
	           		 	$('#J_dialogSelectGoods').show();
	    			}
    	 	},"json");


         }else{
        	 layer.msg("只能选择一种商品");
         }
        });
        $('#J_dialogSelectGoods').on('click', '.dialog-close, .dialog-cancel', function() {
            $('#J_dialogSelectGoods').hide();
            $('#J_dialogJoinCustomer').show();
        });
        $('#J_dialogSelectGoods').on('click', '.J_tr', function() {
            $(this).addClass('J_select').css('background', '#ccc').siblings().removeClass('J_select').css('background', '');
        });
        $('#J_dialogSelectGoods').on('click', '.dialog-ok', function() {
        	 $('#J_dialogSelectGoods').hide();
             $('#J_dialogJoinCustomer').show();
             //var itemId = $('#J_dialogSelectGoods .J_select').find('input[name="scmsItemId"]').val();

             var nameHtml = $('#J_dialogSelectGoods .J_select').find('.J_name').html();
             var html =   '<p>'
                 + '<span class="mr-default">' + nameHtml + '</span>'
                 + '数量：<input type="text" class="input-search-date mr-default" name="number">'
             	+ '赠送总量：<input type="text" class="input-search-date" name="count">'
             	+ '<span onclick="removeproduct(this)" class="button-operate ml-default">删除</span>'
             	+ '</p>'

             $('#product').append(html);
        });


        var useItemFlag = '${active.useItemFlag}';
        var mgItems = "${active.mgItems}";
        var arr = new Array();
     	arr = mgItems.split(";");
     	var html = '';
     	var hiddenHtml='';
        if(useItemFlag==1){//指定商品
        	for(var i=0;i<arr.length;i++){
        		 var tempArr = new Array();
        		 tempArr=arr[i].split("@");
        		 if(tempArr[0]=='cat'){
        			 html+='<tr data-value="cate'+tempArr[1]+'"  class="cate'+tempArr[1]+'">';
        			 html+='<td>类别</td>';
        		 }else{
        			 if(tempArr.length==5){//表示有层级关系
        				 html+='<tr data-value="itemBase'+tempArr[1]+'" class="'+tempArr[4]+'">';
        				 html+='<td></td>';
        			 }else{
        			 html+='<tr data-value="itemBase'+tempArr[1]+'">';
        			 html+='<td>商品</td>'
        			 }
        		 }
        		 html+='<td>'+tempArr[2]+'<input type="hidden" name="productArr" value="'+arr[i]+'"></td>';
        		 if(tempArr[3]==0){
        			 html+='<td width="80" colspan="2">参与</td>';
        		 }else{
        			 html+='<td width="80" colspan="2">不参与</td>';
        		 }
        		 html+='</tr>';
        	 }
       	   $('#zhidingTbody').append(html);
        }else if(useItemFlag==2){//排除商品
        	for(var i=0;i<arr.length;i++){
         		 var tempArr = new Array();
         		 tempArr=arr[i].split("@");
         		 if(tempArr[0]=='cat'){
         			 html+='<tr data-value="cate'+tempArr[1]+' class="cate'+tempArr[1]+'" ">';
         			 html+='<td>类别</td>';
         		 }else{
         			 html+='<tr data-value="itemBase'+tempArr[1]+'">';
         			 html+='<td>商品</td>'
         		 }
         		 html+='<td>'+tempArr[2]+'<input type="hidden" name="itemArr" value="'+arr[i]+'"></td>'
         		     +'<td width="80" colspan="2">不参与</td>'
         		     +'</tr>';
         	 }
       	    $('#paichuTbody').append(html);
        }

    });
</script>
</body>
</html>
