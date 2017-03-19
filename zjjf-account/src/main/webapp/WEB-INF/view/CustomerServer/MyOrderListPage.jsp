<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>客服订单列表</title>
   	<jsp:include page="../common/header.jsp"></jsp:include>
</head>
<body class="easyui-layout">
	<div region="center" style="padding:0px;" border="false" header="false">  
	    <table border="false" fit="true" id="tt" class="easyui-datagrid"  url="${root}/CustomerServer/MyOrderList.do" 
	    		iconCls="icon-save" sortName="orderNo" sortOrder="desc" singleSelect="true"
	            rownumbers="true" idField="orderId" fitColumns="true" pagination="true" pageSize="20"
	            data-options="rownumbers:true,method:'post',toolbar:'#tb',queryParams:{orderState:0}">
	        <thead>
	            <tr>
	            	<th data-options="field:'ck',checkbox:true"></th>
	            	<th field="id" width="200" sortable="true" hidden="true">工作id(不可为idFiled，因为可能空)</th>
	                <th field="orderId" width="200" sortable="true" hidden="true">订单id</th>
	                <th field="serviceType" width="200" sortable="true" hidden="true">服务类型(int)</th>
	                <th field="orderNo" width="200" sortable="true" formatter="formatOp">订单号</th>
	                <th field="lable" width="80" align="center">订单类型</th>
	              	<th field="ptName" width="100" sortable="true">患者姓名</th>
	                <th field="ptTelNo" width="100" sortable="true">患者电话</th>
	                <th field="dtName" width="100" sortable="true">医生姓名</th>
	                <th field="dtTelNo" width="100" sortable="true">医生电话</th>
	                <th field="createTime" width="120" align="center">订单创建时间</th>
	                <th field="orderState" width="80" align="center" formatter="formatOrderState">订单状态</th>
	                <!-- <th field="state" width="80" align="center" formatter="formatState">处理状态</th>
	                <th field="remark" width="200" align="center">处理描述</th> -->
	                <th data-options="field:'_operate',width:120,align:'center',formatter:formatOper">操作</th>
	            </tr>
	        </thead>
	    </table>
	    <div id="tb" style="padding:2px 5px;">
	    	<div style="margin-top: 2px;">
		       	订单编号:<input id="orderNo" class="easyui-textbox" style="width:140px"/>
		       	订单类型:
		       	<select id="serviceType" class="easyui-combobox" panelHeight="auto" style="width:100px">
		       		<option value="">全部</option>
		            <option value="0">免费咨询</option>
		           	<option value="1">图文咨询</option>
		           	<option value="2">线上随访</option>
		           	<option value="3">预约面询</option>
		           	<option value="4">预约住院</option>
		           	<option value="5">体检报告分析</option>
		      	</select>
		       	订单状态:
		       	<select id="orderState" class="easyui-combobox" panelHeight="auto" style="width:100px">
		            <option value="0">未支付</option>
		           	<option value="1">支付完成</option>
		           	<option value="2">资料已提交</option>
		           	<option value="3">申请退款</option>
		           	<option value="4">已完结</option>
		           	<option value="5">已确认</option>
		           	<option value="6">已取消</option>
		      	</select>
		       	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" 
		       		plain="true" onclick="javascript: doSearch()">查询</a>
		       	<!-- 处理状态: 
		        <select id="state" class="easyui-combobox" panelHeight="auto" style="width:100px" data-options="onSelect:doSearch">
		            <option value="null">未处理</option>
		            <option value="1">已安排</option>
		            /** <option value="2">已取消</option>
		            <option value="">全部</option> */
		        </select> -->
	       </div>
	    </div>
    </div>
</body>
<script type="text/javascript">
	//点击表单查询
	function doSearch(){
		 //查询参数直接添加在queryParams中    
         var queryParams = $('#tt').datagrid('options').queryParams;  
         queryParams.orderNo = $("#orderNo").val();
         queryParams.serviceType = getComboboxVal("serviceType");
         queryParams.orderState = getComboboxVal("orderState");
         $('#tt').datagrid('options').queryParams=queryParams;        
         $("#tt").datagrid('reload'); 
	}
	
	// 格式化 处理状态
	function formatState(val, row){
		if(val == null || val == '' || val == 0){
			return "未处理";
		}else if(val == 1){
			return "已安排";
		}else if(val == 2){
			return "已取消";
		}else{
			return "其它";
		}
	}
	
	// 格式化 订单状态
	function formatOrderState(val, row){
		if (val == 0){
			return '未支付';
		} else if(val == 1){
		    return '支付完成';
		}else if(val == 2){
			return "资料已提交";
		}else if(val == 3){
			return "申请退款";
		}else if(val == 4){
			return "已完结";
		}else if(val == 5){
			return "已确认";
		}else if(val == 6){
			return "已取消";
		}else{
			return "其它";
		}
 	}
	
	function formatOp(val, row){
		return '<a href="#" mce_href="#" onclick="viewOrder(\''+ row.orderId + '\')">'+val+'</a>';
 	}
	
	function formatOper(val,row,index){
		return '<a href="#" onclick="orderRefund(\''+row.id+'\',\''+row.orderId+'\','+row.orderState+')">申请退款</a>';
			/* '<a href="#" onclick="dealOrder(\''+row.id+'\',\''+row.orderId+'\')">订单处理</a>'+ */
	}
    
 	function viewOrder(orderId){
  		var url = root+'/CustomerServer/ViewMyOrder.do?orderId='+orderId;
   	  	showWindow("我的工单查看", url, 520, 360, doSearch);
 	}
     
   	function dealOrder(id, orderId){
	  	var url = root+'/CustomerServer/DealOrder.do?id='+id+'&orderId='+orderId;
	  	showWindow("订单修改", url, 600, 450, doSearch);
  	}
   	
   	function orderRefund(id, orderId, orderState){
   		if(orderState==1 || orderState==2 || orderState==4 || orderState==5){
   			// 可退款
   		}else{
   			alertMsg("订单状态，不可退款");
   			return;
   		}
   		
   		$.messager.confirm('提示', "是否确认退款？", function(r){
			if(r){
				var dataJ = {id:id, orderId:orderId};
				if(id == "null"){
					dataJ = {orderId:orderId};
				}
				$.ajax({
					type: "POST",
					url: root+"/CustomerServer/orderRefund.do",
					data: dataJ,
					success: function(msg){
						doSearch();
					},
					error: function (XMLHttpRequest, textStatus, errorThrown) {
					 	// 通常 textStatus 和 errorThrown 之中
					 	// 只有一个会包含信息
					 	this; // 调用本次AJAX请求时传递的options参数
					 	$.messager.alert('抱歉', errorThrown, 'warning');
					}
				});
			}
		});
   	}
</script>
</html>