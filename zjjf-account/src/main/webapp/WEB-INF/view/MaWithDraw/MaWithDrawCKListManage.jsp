<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
function FormatMaWalletStatus(value, row){
	var result ;
	if(value == 0){
		result = "<span style='color:red'>关闭</span>"
	}else if(value == 1){
		result ="<span style='color:green'>正常</span>";
	}else{
		result ="<span style='color:red'>锁定</span>";
	}
	return result;
}
</script>

<div class="easyui-layout"  fit="true" id="maWithDrawCKListMgdgLayout">
	<div region="center">
		<!--=================================================west-table========================================================  -->
		<table id="maWithDrawCKListMgdg" idField="spId"  
			pageSize="100" pageList="[50,100,1000,2000]"
			toolbar="#maWithDrawCKListMgtoolbar" pagination="true" fitColumns="true"  fit="true"
			data-options="rownumbers:true,singleSelect:true,method:'post'">
		    <thead>
		        <tr>
		        <th field="maId">厂商ID</th>
		        <th field="managerName">厂商名称</th>
		        <th field="wallet">厂商钱包余额</th>
		        <th field="walletAddTime">钱包添加时间</th>
		        <th field="walletStauts"  formatter="FormatMaWalletStatus">钱包状态</th>
		        <th field="maOrderIn">订单金额</th>
		        <th field="maButieIn">订单补贴</th>
		        <th field="maCouponIn">订单优惠券</th>
		        <th field="maFreightIn">订单运费</th>
		        <th field="maWxPoundage">微信手续费</th>
		        <th field="maWdPoundage">提现手续费</th>
		        <th field="maWithDraw">体现</th>
		        </tr>
		    </thead>
		</table>
		<!--===================================================west-table-toolbar=================================================  -->
		<div id="maWithDrawCKListMgtoolbar" style="padding:5px;">
			 <div>
			  	核对时间：<input id="maWithDrawCKListMgField3" class="easyui-datebox" value="${defaultTime}" style="width:150px">
			  	至：<input id="maWithDrawCKListMgField4" class="easyui-datebox" value="${defaultTime}" style="width:150px">
		   		<a  id="maWithDrawCKListMgtoolbar-findlist"  class="easyui-linkbutton"  iconCls="icon-search">查询</a>
		    </div>
		</div>
	</div>
</div>

<script type="text/javascript">

var mibocrud = MiBoCRUD.creatNew({
	modelName:"maWithDrawCKListMg",
	dialogTitle:"信息编辑",
	//queryParams:{'supplierId':'${supplierId}',status:initStatus},
	listURL:'<c:url value="/account/mawalletcheckctl/MaWithDrawCheckList.do"/>',
	queryParams:{startDay:"${defaultStart}",endDay:"${defaultEnd}"},
	creatURL:'<c:url value="/account/mawalletcheckctl/SpAdd.do"/>',
	updateURL:'<c:url value="/account/mawalletcheckctl/SpUpdate.do"/>',
	deleteURL:'<c:url value="/account/mawalletcheckctl/SpDelete.do"/>',
	findListFun:function(){
		$("#maWithDrawCKListMgdg" ).datagrid('load',{
			startDay:$("#maWithDrawCKListMgField3").datebox('getValue')+" 00:00:00",
			endDay:$("#maWithDrawCKListMgField4").datebox('getValue')+" 23:59:59"
		})
	}
}).init();

</script>

