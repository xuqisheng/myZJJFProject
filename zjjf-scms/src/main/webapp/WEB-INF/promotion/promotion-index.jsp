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

<script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
<script src="${root}/resources/js/comm.js?vvv"></script>
</head>
<body>
	<div class="wrap-bd">
		<div>
			<span style="font-size: 14px;">当前位置：</span> <a class="crumb">促销管理</a><span
				class="crumb crumb-active">活动列表</span>
		</div>
		<div class="title mb-small">促销管理</div>
		<div class="op-section mb-small">
			<form method="post" action="#" id="formconditon">
				<label>活动时间：</label> <input type="text" name="ruleStart" value=""
					class="input-search-date J_timeS"> - <input type="text"
					name="ruleEnd" value="" class="input-search-date J_timeE">
				<label class="ml-default">活动类型：</label>
				<select name="ruleType" class="select">
					<option value="">全部</option>
                    <c:forEach var="activityType" items="${activityTypes}">
                        <option value="${activityType.index}">${activityType.name}</option>
                    </c:forEach>
				</select> <label class="ml-default">活动状态：</label>
				<select name="status" class="select">
					<option value="">全部</option>
					<option value="0">停止</option>
					<option value="1">进行中</option>
					<option value="2">未开始</option>
				</select>
				<input type="text" name="ruleName" id='ruleName' value=""
					placeholder="活动名称" class="input-search-text ml-default"
					onkeydown="return banInputSapce(event);"> <input value="搜索"
					type="button" onclick="search()"
					class="input-search-button ml-default" id='sub'>
			</form>
		</div>
		<table class="table-list table-border">
			<thead class="table-thead">
				<tr>
					<th>编号</th>
					<th width="200">名称</th>
					<th>类型</th>
					<th>状态</th>
					<th>参与客户数</th>
					<!-- <th width="200">优惠券名称</th> -->
					<th>使用金额限制</th>
					<th>已用成本</th>
					<th>开始时间</th>
					<th>结束时间</th>
				</tr>
			</thead>
			<tbody class="table-tbody" id="J_tableBody">


			</tbody>
		</table>
	</div>
	<div class="dialog hidden" id="J_dialog" style="min-width: 400px;">
		<div class="dialog-head">
			参加活动客户 <span class="dialog-close"></span>
		</div>
		<div class="dialog-body">
			<!--
	        <div>
	            <input type="radio" name="c" data-groupname="aa" data-tab="item"> 全部客户
	            <input type="radio" name="c" data-groupname="aa" data-tab="item" class="ml-default"> 部分客户
	        </div>
	        <div data-groupname="aa" data-tab="content"></div>
		-->
			<form action="" id="formId">
				<input type="hidden" value="" name="spVoucherActiveId"
					id="spVoucherActiveId">
				<div data-groupname="aa" data-tab="content" id="countId"></div>
			</form>
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
        dialogPosCenter('#J_dialog');
        //tab('aa');
        dateRange('.J_timeS', '.J_timeE', 1)
        $('#J_tableBody').on('click', '.J_joinCustomerDetail', function() {
        	var id=$(this).attr('data-id');
        	$("#spVoucherActiveId").val(id);
        	$.post('${root}/scms/promotion/group.do', { "spVoucherActiveId": id}, function (data) {
    			if(data.success){
    				var  html='<br/>已参与该活动的分组：';
   				 	$.each(data.message.alread, function(i,item) {
   					 if((i+1)%4==0){
   						 html+="<br/>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp";
   					 }
   					 html+='<input type="checkbox" class="checkbox ml-default" value="'+item.id+'" name="ids" checked="checked" disabled="disabled"> '+item.name;
   					 html+='<input type="hidden"  class="ml-default" value="'+item.id+'" name="ids" checked="checked" >'
   					})
   					html+="<br/>未参与活动的分组：";
    				 $.each(data.message.all, function(i,item) {
    					 if((i+1)%4==0){
    						 html+="<br/>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp";
    					 }
    					 html+='<input type="checkbox" value="'+item.id+'" class="checkbox ml-default" name="ids"> '+item.name;
    				});
    				$("#countId").html(html);
    				$('#J_dialog, .cover-all').show();
    			}
    	 	},"json");


        });
        $('#J_dialog').on('click', '.dialog-close, .dialog-cancel', function() {
            $('#J_dialog, .cover-all').hide();
        });
        $('#J_dialog').on('click', '.dialog-ok', function() {
            //
        	$.post('${root}/scms/promotion/updateActive_supplier.do',$("#formId").serialize(), function (data) {
    			if(data.success){
    				layer.msg(data.message, {
		        		  icon: 1,
		        		  time: 2000 //2秒关闭（如果不配置，默认是3秒）
		        		}, function(){
		        			window.location.reload();

		        		});
    			}else{
    				layer.msg(data.message);
    			}
	 	},"json");
        });
        aab();
    });

    function search(){

    }


    function aab(){
    	 var data=$("#formconditon").serialize();
         $.post("${root}/scms/promotion/list.do",data,function(data){
 			if(data.success){
 				 var html='<tr><td colspan="10"><a href="${root}/scms/promotion/moreactive.do">参与更多活动</a></td></tr>';
 				 $.each(data.message, function(i,item) {
 					 html+='<tr><td>'+(i+1)+'</td>';
 					 html+='<td><a href="${root}/scms/promotion/watchActive.do?numberInt=1&id='+item.id+'">'+item.ruleName+'</a></td>';
 					 html+='<td>'+item.ruleTypeStr+'</td>';
 					 if(item.status==0){
 						 html+='<td>已停止</td>';
 						 }
 					 else if(item.status==1){
 						 html+='<td>进行中</td>';
 					 }else if(item.status==2){
 						 html+='<td>待开始</td>';
 					 }
 					 //
 					 if(item.number==null){
 						 if(item.ziyuan==1){
 							html+='<td><a href="javascript: void(0)" class="J_joinCustomerDetail" data-id="' + item.id +'">0</a></td>';
 						 } else{
 							html+='<td>0</td>';
 						 }
 						 }else{
 							 if(item.ziyuan==1){
 								 html+='<td><a href="javascript: void(0)" class="J_joinCustomerDetail" data-id="' + item.id +'">'+item.number+'</a></td>';
 							 }else{
 								 html+='<td>'+item.number+'</a></td>';
 							 }

 						}

 					 html+='<td>'+item.ruleStartPrice+'</td>';
 					 if(item.money==null||item.money==0){
 						 html+='<td>0</td>';
 					 }else{
 						 html+='<td><a href="${root}/scms/promotion/promotiondetail.do?id='+item.id+'">'+item.money+'</a></td>';
 					 }
 					 html+='<td>'+item.ruleStart+'</td>';
 					 html+='<td>'+item.ruleEnd+'</td>';
 				 });
 				 $('#J_tableBody').html(html);

 			}

 		},"json")
    }

    $('#sub').on('click', function(e) {
    	aab();
   });


    /**
     * 禁止空格输入
     * @param e
     * @returns {Boolean}
     */
     function banInputSapce(e)
     {
     var keynum;
     if(window.event) // IE
     {
     keynum = e.keyCode
     }
     else if(e.which) // Netscape/Firefox/Opera
     {
     keynum = e.which
     }
     if(keynum == 32){
     return false;
     }
     return true;
     }
</script>
</body>
</html>
