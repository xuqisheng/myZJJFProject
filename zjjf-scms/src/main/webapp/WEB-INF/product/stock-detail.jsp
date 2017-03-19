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
    <script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
    <script src="${root}/resources/js/comm.js?vvv"></script>
</head>
<body>
<div class="wrap-bd">
    <div class="mb-default">
        <span>当前位置：</span>
        <a href="${root}/scms/plantItem/plantItemPage.do" class="crumb">商品库存</a><span class="crumb crumb-active">库存明细</span>
    </div>
    <div class="mb-small clearfix">
        <div class="fl title">库存明细</div>
        <div class="fr">
            <a href="${root}/scms/plantItem/plantItemPage.do">返回</a>
        </div>
    </div>
 	<div class="op-section clearfix">
		<div class="clearfix">
			<div class="fl" style="margin-right: 150px;">商品编号：<span>${mdseId }</span></div>
			<div class="fl" style="margin-right: 150px;">商品名称：<span>${goodsName }</span></div>
			<div class="fl" style="margin-right: 150px;">规格：<span>${spec }</span></div>
			<div class="fl" style="margin-right: 150px;">
				单位：
				<span>
					<c:choose>
						<c:when test="${pkg !=null && pkg != '' && pkg != 'null' }">
							${pkg }
						</c:when>
						<c:otherwise>
							箱
						</c:otherwise>
					</c:choose>
				</span>
			</div>
		</div>
	</div>
    <div>
       <table class="table-list table-border">
         <thead class="table-thead">
          <tr class="table-header">
          	  <th>库存类型</th>
              <th>仓库</th>
              <th>库区</th>
              <th>库位</th>
              <th>批次号</th>
              <th>生产日期</th>
              <th>数量</th>
          </tr>
         </thead>
         <tbody class="table-tbody">

         </tbody>
       </table>
    </div>
</div>
<script>
	dateRange('.J_DATE_START', '.J_DATE_END', 1);
	//加载主体
	$(function(){
		$.ajax({
			url:'${root }/ERPStockManager/getSupplierStockDetail/${warehouseId}/${itemBaseId}/${typeMg}.do',
			type:'post',
			dataType:'json',
			success:function(d){
				var html = '';
				if(d.success){
					$.each(d.message,function(i,item){
						if(item.isExist){
		            		html+='<tr style="background-color: yellow">';
		            	}else{
		            		html+='<tr>';
		            	}
						html+='<td>'+item.typeMgName+'</td>';
						html+='<td>'+item.whName+'</td>';
						html+='<td>'+item.waName+'</td>';
						html+='<td>'+item.wpName+'</td>';
						html+='<td>'+item.batchNum+'</td>';
						html+='<td>'+item.productionTimeStr+'</td>';
						html+='<td>'+item.batchStock+'</td>';
						html+='</tr>';
					});
				}
				 if(html == "") {
	                 	html = '<tr><td colspan="7" class="no-data"></td></tr>';
	             }
				 $('.table-tbody').html(html);
			},
			error:function(e){

			}
		})
	})
</script>
</body>
</html>
