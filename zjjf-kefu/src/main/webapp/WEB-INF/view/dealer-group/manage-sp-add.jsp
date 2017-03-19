<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>联合采购商品 - 批发商添加</title>
	<%@ include file="../common/head.jsp"%>
</head>
<body>
    <div class="wrap-bd">
        <div class="mb-default">
        	<a class="crumb" href="${root}/scms/gorupItem/getGorupItemlistPage.do">联合采购商品</a>
            <a class="crumb" href="${root}/scms/gorupItem/toIndex.do?isUpdate=edit&groupId=${group.id}">管理商品&批发商</a>
            <span class="crumb crumb-active">添加批发商</span>
        </div>
        <div class="mb-small">
            <form id="supplierForm">
                <input type="hidden" name="groupId" value="${group.id}" placeholder="批发商编号/名称" />
                <input class="input input-search-text" type="text" name="supplierCode" placeholder="批发商编号/名称" value="${scmsManager.supplierCode }"/>
                <input class="input input-search-button" value="搜索" type="button" onclick="getRadio()"/>
            </form>
        </div>
        <table class="table-list table-border">
            <thead class="table-thead">
            <tr>
                <th>批发商编号</th>
                <th>批发商名称</th>
                <th>联系方式</th>
                <th>地址</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody class="table-tbody" id="supplierFormTbody">
            	<c:forEach items="${suppliers}" varStatus="i" var="supplier" >
		            <tr>
						<td>${supplier.supplierCode}</td>
						<td>${supplier.supplierName}</td>
						<td>${supplier.mobile}</td>
						<td>${supplier.supplierAddress}</td>
						<td><input type="button" value="添加" class="button button-operate" onclick="updateSupplier('${supplier.id}')"></td>
					</tr>
				</c:forEach>
            </tbody>
        </table>
        <c:if test="${fn:length(suppliers)>0}">
		  <%@ include file="../common/pagination-kk.jsp"%>
   	</c:if>
    </div>
<script type="text/javascript">
function getRadio(){
	var supplierCode= $('input[name="supplierCode"]').val();
	var url="${root}/scms/manager/addSupplierPageList.do?groupId="+$('input[name="groupId"]').val();
	if(supplierCode != null &&supplierCode != undefined && supplierCode != ''){
		   url=url+"&supplierCode="+supplierCode;
	}
	window.location.href=url;
}
function updateSupplier(id){
	var groupId = '${group.id}';
	$.post("${root}/scms/manager/updateSupplier.do",{supplierId:id ,groupId :groupId, addOrDel:'1'},function(data){
		if(data.success){
			layer.msg("添加成功", {
			    icon: 1,
			    time: 2000 //2秒关闭（如果不配置，默认是3秒）
			}, function(){
				location.reload();
			}); 
		}else{
			layer.msg(data.message, {icon: 5});
		}
	},'json');
}
</script>
</body>
</html>