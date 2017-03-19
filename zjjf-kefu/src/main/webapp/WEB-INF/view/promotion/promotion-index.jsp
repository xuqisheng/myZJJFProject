<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>活动管理</title>
	<%@ include file="../common/head.jsp"%>
    <script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
    <script src="${root}/resources/js/comm.js"></script>
</head>
<body>
<div class="wrap-bd">
    <div class="mb-small clearfix">
        <div class="fl">
            <form method="post" action="${root}/Customer/active/toIndex.do">
                <label>活动时间：</label>
	            <input type="text" name="ruleStart" value="${ruleStart}" class="input input-date J_timeS"> -
	            <input type="text" name="ruleEnd" value="${ruleEnd}" class="input input-date J_timeE">
                <label class="ml-default">类型：</label>
                <select class="select" name="ruleType" id="ruleType">
                	<option value="-1">全部</option>
                    <option value="1">注册送优惠劵</option>
                    <option value="2">满送优惠券</option>
                    <option value="3">满减</option>
                    <option value="9">满送优惠劵+商品</option>
                    <option value="10">满减+商品</option>
                    <option value="11">满送商品</option>
                    <option value="12">满购商品送商品</option>
                    <option value="13">提前下单送优惠劵</option>
                    <option value="14">累积送劵</option>
                </select>
                <input type="text" name="searchKey" value="${searchKey}" placeholder="活动名称" class="input input-search-text ml-default">
                &nbsp;&nbsp;
                <input type="checkbox" name="isVoluntary" id="isVoluntary" class="checkbox"> 批发商自愿参与
                <input value="搜索" type="submit" class="input input-search-button ml-default" id="search">
            </form>
        </div>
        <a class="fr" href="${root}/Customer/active/toAdd.do?pageIndex=${pageIndex}"><button class="button button-default">创建活动</button></a>
    </div>
    <table class="table-list table-border">
        <thead class="table-thead">
        <tr>
        	<th>编号</th>
            <th>名称</th>
            <th>类型</th>
            <th>状态</th>
            <th>批发商参与数</th>
            <th>转角承担</th>
            <th>批发商承担</th>
            <th width="80">开始时间</th>
            <th width="80">结束时间</th>
            <th width="80">操作</th>
        </tr>
        </thead>
        <tbody class="table-tbody" id="listTbody">
        <c:forEach items="${spVoucherActiveList}" var="item">
        <tr data-id="${item.id}" data-ruleType="${item.ruleType}">
        <td>${item.id}</td>
        <td>
        <a href="${root}/Customer/active/watchActive.do?id=${item.id}&type=watch">${item.ruleName}</a>
        </td>
        <td>${item.ruleTypeStr}</td>
        <c:if test="${item.status==1}">
         <td class="txt-success J_jinxingzhong">
                     进行中
         </td>
       </c:if>
        <c:if test="${item.status==0}">
         <td class="txt-log">
                   停用
         </td>
        </c:if>
        <c:if test="${item.status==2}">
                 <td class="txt-warn">
                                    未开始
                 </td>
        </c:if>
        <c:if test="${item.status==3}">
         <td class="txt-info">
                   新建
         </td>
        </c:if>
        <td>
        <c:if test="${empty item.totalSupplier}"></c:if>
        <c:if test="${not empty item.totalSupplier}">
          <c:if test="${item.totalSupplier!=0}"><a href="${root}/Customer/active/watchActive.do?id=${item.id}&type=toInfo">${item.totalSupplier}</a></c:if>
          <c:if test="${item.totalSupplier==0}">0</c:if>
        </c:if>
        </td>
        <td>
        <c:if test="${item.ruleType!=11&&item.ruleType!=12}">
        ${item.plantHalveStr}
        </c:if>
        </td>
        <td>
        <c:if test="${item.ruleType!=11&&item.ruleType!=12}">
         ${item.spHalveStr}
        </c:if>
        </td>
        <td><fmt:formatDate value="${item.ruleStart}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        <td><fmt:formatDate value="${item.ruleEnd}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        <td style="width: 150px">
        <input type="hidden" value="${item.id}">
        <c:if test="${item.status==3}">
          <a href="javascript:void(0)" class="J_isEdit">编辑</a>
          <a href="javascript:void(0)" class="J_start">启用</a>
        </c:if>
        <c:if test="${item.status!=0}">
          <a href="javascript:void(0)" class="J_status mr-default">
                        停用
          </a>
        </c:if>
        <c:if test="${item.ruleType==14}">
            <a href="#" class="J_count">统计</a>
        </c:if>
        <c:if test="${item.ruleType==14&&item.status==0}">
                <a href="#" class="J_send">发券</a>
        </c:if>
        </td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${fn:length(spVoucherActiveList)>0}">
		<%@ include file="../common/pagination-kk.jsp"%>
	</c:if>
</div>
</body>
<script type="text/javascript">
	//回显搜索条件


    $(function() {
    	dateRange('.J_timeS', '.J_timeE', 1);



    	//setInterval(function(){ $(".J_jinxingzhong").fadeOut(100).fadeIn(100); },500);

    	if('${isVoluntary}'==1){
    		$('#isVoluntary').attr('checked',true);
    	}else{
    		$('#isVoluntary').attr('checked',false);
    	}
    	$('#ruleType').val('${ruleType}');


        //统计按钮   杨开泰 2016/10/24 0024 20:16
        $('#listTbody').on('click','.J_count',function(){
            window.location.href='${root}/Customer/active/toAccumulation.do?id='+$(this).parent('td').parent('tr').attr('data-id');
        });
        //发券按钮   杨开泰 2016/10/24 0024 20:16
        $('#listTbody').on('click','.J_send',function(){
            var id = $(this).parent('td').parent('tr').attr('data-id');
            $.ajax({
                type : "POST",
                url : "${root}/Customer/active/canSendVoucher.do",
                dataType:'json',
                async : true,
                data : {'id':id},
                success : function(da) {
                    if(da.success){
                        layer.msg('处理完成!');
                    }else{
                        layer.msg('已经发送过优惠劵,不能再次发送!');
                    }
                },
                error : function(da) {
                    layer.msg('失败的请求!');
                }
            });
        });

    	//启用按钮
    	$('.J_start').on('click',function(){
    		var id = $(this).parent('td').children('input').val();
    		var status = '';
    		var tdHtml = '';

    		var $html = $(this);
    		layer.confirm('启用后,活动不能再编辑',{},function(index){
    			$.ajax({
           			type : "POST",
           			url : "${root}/Customer/active/updateStatus.do",
           			dataType:'json',
           			async : true,
           			data : {'id':id,'status':2},
           			success : function(da) {
           				if(da.success){
           					if(da.message=='未开始'){
           						$html.parent('td').parent('tr').children().eq(3).html('未开始');
               					$html.parent('td').parent('tr').children().eq(3).css("color","red");
               					$html.prev().remove();
               					$html.remove();
           					}else if(da.message=='进行中'){
           						$html.parent('td').parent('tr').children().eq(3).html('进行中');
               					$html.parent('td').parent('tr').children().eq(3).css("color","green");
               					$html.parent('td').parent('tr').children().eq(3).addClass('J_jinxingzhong')
               					$html.prev().remove();
               					$html.remove();
           					}
           				}else{
           					layer.msg(da.message);
           				}
           				layer.close(index);
           			},
           			error : function(da) {
           				layer.msg('失败的请求!');
           			}
           		});
    		},function(index){
           				layer.close(index);
    		});
    	});

    	//停用按钮
    	$('.J_status').on('click',function(){
    		var id = $(this).parent('td').children('input').val();
    		var status = '';
    		var tdHtml = '';

    		var $html = $(this);
    		layer.confirm('停用后,活动就作废了',{},function(index){
    			$.ajax({
           			type : "POST",
           			url : "${root}/Customer/active/updateStatus.do",
           			dataType:'json',
           			async : true,
           			data : {'id':id,'status':0},
           			success : function(da) {
           				if(da.success){
           					$html.parent('td').parent('tr').children().eq(3).html('停用');
           					$html.parent('td').parent('tr').children().eq(3).removeClass('J_jinxingzhong');
           					$html.parent('td').parent('tr').children().eq(3).css("color","gray");
                            if($html.parent('td').parent('tr').attr('data-ruleType')==14){
                                var html='<a href="javascript:void(0)" class="J_count">统计</a>  <a href="javascript:void(0)" class="J_send">发券</a>'
                                $html.parent('td').empty().append(html);
                            }else{
                                $html.parent('td').empty();
                            }

           				}else{
           					layer.msg(da.message);
           				}
           			},
           			error : function(da) {
           				layer.msg('失败的请求!');
           			}
           		});
    			layer.close(index);
    		},function(index){
    			layer.close(index);
    		})
    	});



    	//编辑按钮
       $('.J_isEdit').on('click',function(){
    	   var id = $(this).parent('td').children('input').val();
    	   var status = $(this).parent('td').parent('tr').children().eq(3).html();
    	   if($.trim(status)!="新建"){
    		   if($.trim(status)=='进行中'){
    		     layer.msg('活动正在进行,不能编辑!');
    		   }else if($.trim(status)=='停用'){
    		     layer.msg('活动已经停用,不能编辑!');
    		   }else{
    		     layer.msg('该活动不能编辑!');
    		   }
    	   }else{
    		   $.ajax({
    	   			type : "POST",
    	   			url : "${root}/Customer/active/canEdit.do",
    	   			dataType:'json',
    	   			async : true,
    	   			data : {'id':id},
    	   			success : function(da) {
    	   				if(da.success){
    	   						location.href="${root}/Customer/active/toEdit.do?id="+id+"&pageIndex=${pageIndex}";
    	   					}else{
    	   						layer.msg(da.message);
    	   					}
    	   			},
    	   			error : function(da) {
    	   			    layer.msg('失败的请求!');
    	   			}
    	   		});
    	   }

       });

    });


</script>
</html>
