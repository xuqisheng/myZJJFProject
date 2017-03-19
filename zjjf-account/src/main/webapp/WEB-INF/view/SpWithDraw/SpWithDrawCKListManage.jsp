<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="easyui-layout"  fit="true" id="spWithDrawCKListMgdgLayout">
	<div region="center">
		<!--=================================================west-table========================================================  -->
		<table id="spWithDrawCKListMgdg" idField="spId"  
			pageSize="100" pageList="[50,100,1000,2000]"
			toolbar="#spWithDrawCKListMgtoolbar" pagination="true" fitColumns="true"  fit="true"
			data-options="rownumbers:true,singleSelect:false,method:'post'">
		    <thead>
		        <tr>
		        <th field="spId">批发商ID</th>
		        <th field="supplierName">批发商名称</th>
		        <th field="spOrderIn">订单金额</th>
		        <th field="spButieIn">订单补贴</th>
		        <th field="spCouponIn">订单优惠券</th>
		        <th field="spFreightIn">订单运费</th>
		        <th field="spWxPoundage">微信手续费</th>
		        <th field="spWdPoundage">提现手续费</th>
		        <th field="spWithDraw">体现</th>
		        </tr>
		    </thead>
		</table>
		<!--===================================================west-table-toolbar=================================================  -->
		<div id="spWithDrawCKListMgtoolbar" style="padding:5px;">
			 <div>
			  	核对时间：<input id="spWithDrawCKListMgField3" class="easyui-datebox" value="${defaultTime}" style="width:150px">
			  	至：<input id="spWithDrawCKListMgField4" class="easyui-datebox" value="${defaultTime}" style="width:150px">
		   		<a  id="spWithDrawCKListMgtoolbar-findlist"  class="easyui-linkbutton"  iconCls="icon-search">查询</a>
		    </div>
		</div>
	</div>
</div>

<script type="text/javascript">

var mibocrud = MiBoCRUD.creatNew({
	modelName:"spWithDrawCKListMg",
	dialogTitle:"信息编辑",
	//queryParams:{'supplierId':'${supplierId}',status:initStatus},
	listURL:'<c:url value="/account/spwalletcheckctl/spWithDrawCheckList.do"/>',
	queryParams:{startDay:"${defaultStart}",endDay:"${defaultEnd}"},
	creatURL:'<c:url value="/account/spwalletcheckctl/SpAdd.do"/>',
	updateURL:'<c:url value="/account/spwalletcheckctl/SpUpdate.do"/>',
	deleteURL:'<c:url value="/account/spwalletcheckctl/SpDelete.do"/>',
	findListFun:function(){
		$("#spWithDrawCKListMgdg" ).datagrid('load',{
			startDay:$("#spWithDrawCKListMgField3").datebox('getValue')+" 00:00:00",
			endDay:$("#spWithDrawCKListMgField4").datebox('getValue')+" 23:59:59"
		})
	}
}).init();

</script>

