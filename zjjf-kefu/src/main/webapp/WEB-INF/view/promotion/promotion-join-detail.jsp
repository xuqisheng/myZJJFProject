<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
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
    <div class="mb-default">
        <span style="font-size: 14px;">当前位置：</span>
        <a class="crumb">促销管理</a><span class="crumb crumb-active">活动详情</span>
    </div>
    <div class="wrap-bd bg">
        <div class="mb-small">
            <label class="label">活动编号:</label>
            ${active.id}
        </div>
        <p class="mb-small">
            <label class="label">名称：</label>
            ${active.ruleName}
        </p>
        <p class="mb-small">
            <label class="label">活动时间：</label>
            <fmt:formatDate value="${active.ruleStart}" pattern="yyyy-MM-dd HH:mm:ss"/>
             --  <fmt:formatDate value="${active.ruleEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>
        </p>
        <p class="mb-default">
            <label class="label">活动说明：</label>
           ${active.ruleRemark}
        </p>
        <div class="mb-small">
            <label class="label va-t">活动类型：</label>
            <div style="width:800px;display: inline-block;">
            <c:if test="${active.ruleType==1}"><div class=" mb-default">注册送优惠劵</div></c:if>
             <c:if test="${active.ruleType==2}"><div class=" mb-default">满送优惠劵</div></c:if>
             <c:if test="${active.ruleType==3}"><div class=" mb-default">满减</div></c:if>
             <c:if test="${active.ruleType==9}"><div class=" mb-default">满送优惠劵+商品</div></c:if>
             <c:if test="${active.ruleType==10}"><div class=" mb-default">满减金额+商品</div></c:if>
             <c:if test="${active.ruleType==11}"><div class=" mb-default">满送商品</div></c:if>
             <c:if test="${active.ruleType==12}"><div class=" mb-default">满购商品送商品</div></c:if>
             <c:if test="${active.ruleType==13}"><div class=" mb-default">提前下单送优惠劵</div></c:if>

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

        <c:if test="${active.ruleType==12}">
        <div class="mt-small condition" id="buyGoodsDiv">
            <input type="hidden" value="" name="buyGoodsItemBaseId" id="buyGoodsItemBaseIdInput">
            <label class="label va-t">购满商品：</label>
            <div style="width: 800px; display:inline-block">
                <div id="buyGoodsP">
                    <span id="seeBuyGoodsNameInput"></span>&nbsp;&nbsp;
                                                  购买数量：
                    <span id="buyGoodsNumInput"></span>
                </div>
            </div>
        </div>
        </c:if>

        <c:if test="${active.ruleType==3||active.ruleType==10}">
        <div class="condition" id="ruleContentDiv">
        <div class="clearfix">
	        <div class="fl">
	            <div class="mb-default">
					<label class="label">钱包支付：</label><!--payMethod=4  -->
					<span id="qianbaoMethod"></span>
					<!-- <input class="input input-default" type="text" name="weixingMethod" id="weixingMethod"> -->
			    </div>
				<div class="mb-default">
					<label class="label">微信支付：</label><!--payMethod=4  -->
					<span id="weixingMethod"></span>
					<!-- <input class="input input-default" type="text" name="weixingMethod" id="weixingMethod"> -->
			    </div>
				<div class="mb-default">
					<label class="label">货到付款：</label><!--payMethod=2  -->
					<span id="huodaoMethod"></span>
					<!-- <input class="input input-default" type="text" name="huodaoMethod" id="huodaoMethod"> -->
				</div>
				<div class="mb-default">
					<label class="label">支付宝支付：</label><!--payMethod=3  -->
					<span id="zhifubaoMethod"></span>
					<!-- <input class="input input-default" type="text" name="zhifubaoMethod" id="zhifubaoMethod"> -->
				</div>
				<div class="mb-default">
					<label class="label">快捷支付：</label><!--payMethod=1  -->
					<span id="kuaiqianMethod"></span>
					<!-- <input class="input input-default" type="text" name="kuaiqianMethod" id="kuaiqianMethod"> -->
				</div>
			</div>
        </div>
      </div>

      <div class="mb-default">
		       <label class="label">商品/类别：</label>
		       ${active.useItemFlagStr}
		 </div>
		 <c:if test="${active.useItemFlag==2}">
		 <div class="clearfix mb-default">
		    <label class="label va-t fl">排除指定商品：</label>
		    <div class="fl" style="width: 90%;">
                   <table class="table-list table-border">
                       <thead class="table-thead">
                       <tr>
                           <th width="80">类型</th>
                           <th>类别/商品</th>
                           <th width="80"></th>
                           <!-- <th>操作</th> -->
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
		 	<div class="mb-default clearfix">
		 		<label class="label va-t fl">指定购买商品：</label>
              <div class="fl" style="width: 90%;">
                  <table class="table-list table-border">
                      <thead class="table-thead">
                      <tr>
                          <th width="80">类型</th>
                          <th>类别/商品</th>
                          <th width="80"></th>
                          <!-- <th>操作</th> -->
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
		 	</div>

		 </c:if>
      </c:if>

        <%--提前下单送优惠劵--%>
        <c:if test="${active.ruleType==13}">
            <p class="mb-default">
                <label class="label va-t" style="width: 120px;">预计送达时间：</label>
            <div class="mb-default">
                <c:if test="${active.transportTimeType==-1}">
                    不限制
                </c:if>
                <c:if test="${active.transportTimeType==0}">
                    24小时
                </c:if>
                <c:if test="${active.transportTimeType==1}">
                    48小时
                </c:if>
                <c:if test="${active.transportTimeType==2}">
                    72小时
                </c:if>
            </div>
            </p>
        </c:if>


      <c:if test="${active.ruleType==2||active.ruleType==9||active.ruleType==11||active.ruleType==12||active.ruleType==13}">
       <p>
            <label class="label">支付方式：</label>
            ${RulePayStr}
        </p>
        </c:if>

        <c:if test="${active.ruleType==11}">
        <p class="required condition" id="ruleStartPriceP">
            <label class="label">活动规则：</label>
                              单笔订单满
             ${active.ruleStartPrice}
            <span class="" style="color: red;" id="startPriceError"></span>
                               元
        </p>
        </c:if>

        <c:if test="${active.ruleType==1}">
        <p>
            <label class="label">每天限制：</label>
            ${active.ruleSendLimit}张
        </p>
        </c:if>

        <p class="condition" id="ruleScopFlagP">
            <label class="label">参与区域：</label>
            <c:if test="${active.ruleScopFlag==0}">
                              全部定格
            </c:if>
            <c:if test="${active.ruleScopFlag==1}">
                            指定定格
            </c:if>
            <c:if test="${active.ruleScopFlag==2}">
                            批发商自愿参与
            </c:if>
        </p>

        <c:if test="${active.ruleScopFlag!=0}">
        <div class="mt-small condition" id="spGroupDiv">
            <label class="label va-t">参与定格：</label>
            <div style="width: 800px; display:inline-block">
                <table class="table-list table-border mt-small">
                    <thead class="table-thead">
                        <tr>
                            <th>定格名称</th>
                            <th>定格分区</th>
                        </tr>
                    </thead>
                    <tbody class="table-tbody" id="addSpGroupTbody">
                    <c:forEach var="spGroupVo" items="${spGroupVoList}">
                     <tr>
                      <td>${spGroupVo.name}</td>
                      <td>${spGroupVo.areaName}</td>
                      </tr>
                     </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
       </c:if>

        <c:if test="${active.ruleType!=11&&active.ruleType!=12}">
        <p>
            <label class="label">费用分摊：</label>
            转角承担${active.plantHalveStr}%
            批发商承担${active.spHalveStr}%
        </p>
        </c:if>

         <c:if test="${active.ruleType==9||active.ruleType==10||active.ruleType==11||active.ruleType==12}">
        <div class="mb-default">
            <label class="label va-t">满送商品：</label>


            		<div style="width:800px;display: inline-block;">
            		<c:forEach items="${result}" var="item">
		                <span class="mr-default"> ${item[1]}</span>
		                <span class="ml-default mr-default">赠送数量：${item[2]}</span>
		                <span class="ml-default">赠送总量：  ${item[3]}</span><br></c:forEach>
            </div>
        </div>
        </c:if>
        <div>
            <label class="label"></label>
            <input type="button" value="返回" class="button button-cancel" onclick="history.go(-1)">
        </div>
    </div>
    <div class="mt-default">
        <table class="table-list table-border">
            <thead class="table-thead">
            <tr>
                <th>编号</th>
                <th>批发商名称</th>
                <th>参与客户数</th>
                <th>转角成本</th>
            </tr>
            </thead>
            <tbody class="table-tbody" id="activeSupplierTbody">

            </tbody>
        </table>
        <%@ include file="../common/pagination.jsp"%>
    </div>
</div>
<div class="dialog hidden" id="canYuDiv">
    <input type="hidden" value="" id="hiddenSpId">
    <div class="dialog-head" id="canyuChildrenDiv">【批发商名称】参与客户</div>
    <div class="dialog-body">
        <div class="limit-height">

        <table class="table-list table-border">
            <thead class="table-thead">
            <tr>
                <th>编号</th>
                <th>客户分组</th>
                <th>客户名称</th>
                <th>下单次数</th>
            </tr>
            </thead>
            <tbody class="table-tbody" id="fengZuTbody">
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            </tbody>
        </table>
        </div>
    </div>
    <div class="dialog-foot">
        <input type="button" value="关闭" class="dialog-button dialog-cancel" id="guanBi">
    </div>
</div>
<script>
    /* function openDialog(supplierId){
    	alert(typeof supplierId);
    	alert(supplierId);
    	$('#canYuDiv').removeClass('hidden');
    } */

    $(function() {
        dialogPosCenter('.dialog');
        $(window).on('resize', function() {
            dialogPosCenter('.dialog');
        });

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
        		 $('#qianbaoMethod').text(jsonObject.rule);
        	 }
         }
        }else if(ruleType==12){
        	var buyGoods = '${active.buyGoods}';
        	var goodsArr = new Array();
        	goodsArr = buyGoods.split(":");
        	$('#seeBuyGoodsNameInput').text(goodsArr[1]);
        	$('#buyGoodsNumInput').text(goodsArr[2]);
        }

        //var jsonStr = '${jsonStr}';

        //关闭按钮
        $('#guanBi').on('click',function(){
    	$('#canYuDiv').addClass('hidden');
        })

        //查看店铺订单点击事件
        $('#fengZuTbody').on('click','.J_seeOrder',function(){
        	var storeId = $(this).parent('td').prev().prev().html();
          	location.href='${root}/Customer/active/toSeeOrders.do?spId='+$('#hiddenSpId').val()+'&activeId=${active.id}&storeId='+storeId;
        });

        //参与客户数点击事件
        $('#activeSupplierTbody').on('click','.J_clickClass',function(){
        	var supplierName = $(this).parent().prev().html();
        	$('#canyuChildrenDiv').html('【'+supplierName+'】参与客户');
        	var supplierId = $(this).attr('data-value');
        	$('#hiddenSpId').val(supplierId);
        	var spVoucherActiveId = '${active.id}';
        	var ruleType = '${active.ruleType}';
        	var pathUrl = '';
        	if(ruleType==1||ruleType==2||ruleType==9){//送优惠劵
        		pathUrl='${root}/Customer/active/getActiveStoreMember.do?status=1';
        	}else if(ruleType==3||ruleType==10){//满减
        		pathUrl='${root}/Customer/active/getActiveStoreMember.do?status=2';
        	}else{
        		layer.msg('程序出错,请联系技术!')
        		console.log('查看参与活动客户出错,ruleType不合法!');
        		return;
        	}
        	$.ajax({
    			type : "POST",
    			url : pathUrl,
    			dataType:'json',
    			async : true,
    			data : {'supplierId':supplierId,'spVoucherActiveId':spVoucherActiveId},
    			success : function(da) {
    				if(da.success){
    					var html = '';
    					$.each(da.message,function(i,item){
    						html+='<tr>'
    						    +'<td>'+item.id+'</td>';
    						    if(item.storeGroupName==null||item.storeGroupName==''||item.storeGroupName=='null'){
    						    	html+='<td></td>';
    						    }else{
    						    	html+='<td>'+item.storeGroupName+'</td>';
    						    }
                                html+='<td><a href="javascript:void(0)" class="J_seeOrder">'+item.name+'</></td>';
                                if(item.totalOrder==null||item.totalOrder==''||item.totalOrder=='null'){
                                	html+='<td>0</td>';
                                }else{
                                	html+='<td>'+item.totalOrder+'</td>';
                                }
    						    html+='</tr>';
    					});
    					$('#fengZuTbody').html(html);
    					$('#canYuDiv').removeClass('hidden');
    				}else{
    					layer.msg(da.message);
    				}
    			},
    			error : function(da) {
    				layer.msg('失败的请求!');
    			}
    		});
        });


        //获取参与活动的批发商列表
        $("#jpagination").pagination({
    	    pageSize: 10,
    	    remote: {
    	        url: '${root}/Customer/active/getActiveSupplierList.do?id=${active.id}',
    	       // params: searchObject,
    	        success: function(data) {
    	        	if(data.flag){
    	        		var html = '';
    	        		$.each(data.list,function(i,item){
    	        			html+='<tr>'
    	        			    +'<td>'+item.supplierCode+'</td>'
    	        			    +'<td>'+item.supplierName+'</td>';
    	        			    if(item.totalConsumer==0){
    	        			    	html+='<td>'+item.totalConsumer+'</td>';
    	        			    }else{
    	        			    	html+='<td><a href="javascript:void(0)" class="J_clickClass" data-value="'+item.id+'">'+item.totalConsumer+'</a></td>';
    	        			    }
    	        			    if(item.cost==null||item.cost=='null'||item.cost==''){
    	        			    	html+='<td>0</td>';
    	        			    }else{
    	        			    html+='<td>'+item.cost+'</td>';
    	        			    }
    	        			    html+='</tr>';
    	        		});
    	        		$('#activeSupplierTbody').html(html);
    	        	}
    	        },
    	        totalName:'totalSize'
    	    }
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
        			 html+='<td width="80">参与</td>';
        		 }else{
        			 html+='<td width="80">不参与</td>';
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
         		     +'<td width="80">不参与</td>'
         		     +'</tr>';
         	 }
       	    $('#paichuTbody').append(html);
        }
    });
</script>
</body>
</html>
