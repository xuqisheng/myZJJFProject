<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
</head>
<body class="wrap-bd">
<div >
        <div class="title mb-small">
            	库存预警列表
        </div>
        <div class="table">
            <!--<div class="no-data"></div>-->
            <table class="table-list table-border" >
                <thead class="table-thead">
                <tr>
                    <th>条形码</th>
                    <th>商品名称</th>
                    <th>规格</th>
                    <th>剩余库存</th>
                </tr>
                </thead>
                <tbody class="table-tbody" id="stock">
                <!-- <tr>
                    <td>
                        <a href="">6547284551</a>
                    </td>
                    <td>青岛啤酒纯生罐装500ml</td>
                    <td>500ml x 12瓶</td>
                    <td class="txt-warn">1</td>
                </tr> -->
                </tbody>
            </table>
        </div>
    </div>
<%@ include file="../common/pagination.jsp"%>
<script>
$("#jpagination").pagination({
	pageSize: 20,
    remote: {
        url: '${root}/scms/plantItem/getSupplierStockDetailBysSpId.do',
        /* params: {'status': 10, "date": getNowFormatDate()}, */
        success: function(data) {
        	var html = '';
        	console.log(data)
             $.each(data.list, function(i,item) {
            	html+='<tr>';
 				html+='<td>';
 				html+='<a href="${root}/scms/plantItem/plantItemPage.do?commodityIdAndName='+item.mdseId+'">'+item.mdseId+'</a>';
 				html+='</td>';
 				html+='<td>'+item.name+'</td>';
 				html+='<td>'+item.spec+'</td>';
 				html+='<td class="txt-warn">'+item.goodsStock+'</td>';
 				html+='</tr>';
             })
             if(html == "") {
             	html = '<tr><td colspan="10" class="no-data"></td></tr>';
             }
             $("#stock").html(html);
         },
        totalName:'totalSize'
    }
});
</script>
</body>
</html>