<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>促销管理</title>
<%@ include file="../common/head.jsp"%>
</head>
<body>
	<div class="wrap-bd">
		<div>
			<span style="font-size: 14px;">当前位置：</span> <a class="crumb">促销管理</a><span
				class="crumb crumb-active">优惠券详情</span>
		</div>
		<div class="title mb-small">优惠券详情</div>
		<div class="wrap-bd bg">
			<div>
				<label class="label">名称：</label> ${spVoucherTempVo.ruleName}
			</div>
			<p>
				<label class="label">面值：</label> ${spVoucherTempVo.useMoney}元
			</p>
			<p>
				<label class="label">有效期：</label> ${spVoucherTempVo.useDay}天
			</p>
			<p>
				<label class="label">说明：</label> ${spVoucherTempVo.ruleRemark}
			</p>
			<p>
				<label class="label">支付方式：</label>
				${spVoucherTempVo.spVoucherTempPayStr}
			</p>
			<p>
				<label class="label">使用条件：</label>
				<c:if test="${spVoucherTempVo.startPrice+''!='0.00'}">
                                 单笔订单金额满${spVoucherTempVo.startPrice}元可使用
            </c:if>
				<c:if test="${spVoucherTempVo.startPrice+''=='0.00'}">
                                 不限制
            </c:if>
			</p>
			<div class="mb-default hidden" id="limitDiv">
				<label class="label va-t" id="goodId">指定/排除商品：</label>
				<div style="width: 800px; display: inline-block;">
					<table class="table-list table-border">
						<thead class="table-thead">
							<tr>
								<th>商品/类别</th>
								<th>商品条码</th>
								<th>商品/类别名称</th>
								<th>规格</th>
								<th>单位</th>
								<th>是否参与</th>
							</tr>
						</thead>
						<tbody class="table-tbody" id="goodTbody">
							<!-- <tr>
                        <td></td>
                        <td>1234567890</td>
                        <td>百事可乐</td>
                        <td>320ml</td>
                        <td>瓶</td>
                        <td>参与</td>
                    </tr> -->
						</tbody>
					</table>
				</div>
			</div>
			<div>
				<label class="label"></label> <input type="button" value="返回"
					class="button-cancel" onclick="history.go(-1)">
			</div>
		</div>
	</div>
	<script>

      var limit = '${spVoucherTempVo.useItemFlag}';
      if(limit==1){
    	  $('#goodId').html('指定商品');
    	  $('#limitDiv').removeClass('hidden');
    	  generatorHtml(limit);
      }
      if(limit==2){
    	  $('#goodId').html('排除商品');
    	  $('#limitDiv').removeClass('hidden');
    	  generatorHtml(limit);
      }
      
      function generatorHtml(li){
    	  var html = '';
    	  var mgItemsStr = '${spVoucherTempVo.mgItemsStr}';
    	  mgItemsStr = JSON.parse(mgItemsStr);
    	  $(mgItemsStr).each(function(i,item){
    		  if(item.no1=='cat'){
    			  html+='<tr>'
    			       +'<td>类别</td>'
    			       +'<td></td>'
    			       +'<td>'+item.no2+'</td>'
    			       +'<td></td>'
    			       +'<td></td>';
    			       if(item.no3==0){
    			    	   html+='<td>参与</td></tr>';
    			       }else if(item.no3==1){
    			    	   html+='<td>不参与</td></tr>';
    			       }
    		  }else if(item.no1=='item'){
    			  html+='<tr>'
   			       +'<td>商品</td>'
   			       +'<td>'+item.no2.mdseId+'</td>'
   			       +'<td>'+item.no2.name+'</td>';
   			       if(item.no2.spec!=null&&item.no2.spec!=''&&item.no2.spec!='null'){
   			    	   html+='<td>'+item.no2.spec+'</td>';
   			       }else{
   			    	   html+='<td></td>';
   			       }
   			       if(item.no2.pkg!=null&&item.no2.pkg!=''&&item.no2.pkg!='null'){
			    	   html+='<td>'+item.no2.pkg+'</td>';
			       }else{
			    	   html+='<td></td>';
			       }
   			       if(item.no3==0){
   			    	   html+='<td>参与</td></tr>';
   			       }else if(item.no3==1){
   			    	   html+='<td>不参与</td></tr>';
   			       }
    		  }
    	  });
    	  $('#goodTbody').html(html);
    	 };
</script>
</body>
</html>
