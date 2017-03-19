<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>确认优惠券页面</title>
	<%@ include file="../common/head.jsp"%>
     <%@ include file="../common/autocomplete.jsp"%>
    <script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
    <script src="${root}/resources/js/comm.js"></script>
</head>
<body class="wrap-bd">
<div class="wrap-bd bg">
    <div class="mb-small clearfix">
        <b class="fl">确认优惠券</b>
        <a class="fr" href="${root}/Customer/voucher/toIndex.do">返回优惠券列表</a>
    </div>
    <div>
	    <form id="sendForm">
		    <div>
		        <p>
		                      优惠劵名称:${rule.ruleName}&nbsp;
		        </p>
		        <p>
		                     面值:${rule.useMoney}&nbsp;
		        </p>
		        <p>
		                        有效期:${startDate}至${endDate}&nbsp;
		        </p>
		        <p>
		                   金额限制:${startPrice}&nbsp;
		        </p>
		        <p>
		                     优惠说明:${rule.ruleRemark}&nbsp;
		        </p>
		        <%-- <p id="spGroupP" class="">
		                      用户列表
		           <span>本次选中用户数:${count}</span>
		           <span id="real">实际发送数:${realSend}</span>
		           <span>已经有${reCount}用户有该优惠劵(包括未使用,已使用,已过期,已被删除)</span>
		        </p> --%>
		        <%-- <p>
		          <c:if test="${condition==3}">
		            <c:forEach items="${errorList}" var="item">
		                                  第${item}行出错! <br>
		            </c:forEach>
		          </c:if>
		        </p> --%>
		        <!-- <div id="saveSpGroup"></div>
		        <div id="saveStore"></div> -->
		        <div id="delDiv">
		        
		        </div>
		     
		    </div>
	    <table class="table-list table-border" id="storeTable">
	        <thead class="table-thead">
	        <tr>
	            <th width="20"><input type="checkbox" id="allCheck"></th>
	            <th style="text-align:left"><span class="button button-operate" id="delAll">删除</span></th>
	            <th colspan="5" style="text-align:right">
	            	<input type="text" class="input input-search-text" name="keyStr" value="" id="keyStr">
	            	<input type="button" class="input input-search-button" name="" value="搜索" id="search">
	            </th>
	        </tr>
	        </form>
	        <tr>
	            <th></th>
	            <th>商铺编号</th>
	            <th>商铺名称</th>
	            <th>店主名</th>
	            <th>手机号</th>
	            <th>定格名称</th>
	            <th>管理操作</th>
	        </tr>
	        </thead>
	        <tbody class="table-tbody" id="storeTbody">
	        </tbody>
	        <tfoot>
            	<tr>
            		<td colspan="7"><%@ include file="../common/pagination.jsp"%></td>
            	</tr>
		    </tfoot>
	    </table>
	    <p>
	        <input type="button" class="button button-ok" id="send" value="发送">
	        <button class="button button-cancel" id="cancel" type="button">取消</button>
	    </p>
    </div>
    <script>
        $(function(){
            dateRange('.J_TIME_START', '.J_TIME_END');
            $('.J_selectAll').on('click', function() {
                $('.J_checkbox').prop('checked',$(this).prop('checked'));
            });
            $('.J_checkbox').on('click', function() {
                if($('.J_checkbox').length == $('.J_checkbox:checked').length) {
                    $('.J_selectAll').prop('checked', 'checked');
                } else {
                    $('.J_selectAll').prop('checked', '');
                }
            });
            //取消按钮
            $('#cancel').on('click',function(){
            	location.href='${root}/Customer/voucher/delTempStore.do?jsonStr=${jsonStr}';
            });
            //发送
            /* $('#send').click(function(){
            	$('#send').hide();
            	if(confirm('是否确定发布?')){
            		$.ajax({
    	    			type : "POST",
    	    			url : "${root}/Customer/voucher/asynSendSpVoucher.do?jsonStr=${jsonStr}",
    	    			async : true,
    	    			data : $('#sendForm').serialize(),
    	    			dataType:'json',
    	    			success : function(da) {
    	    				if(da.success){
    	    					layer.msg('正在发送优惠券,页面即将刷新!',{tiem:2000},function(){
    	    					location.href="${root}/Customer/voucher/toIndex.do";
    	    					});
    	    				}else{
    	    					layer.msg(da.message);
    	    				}
    	    			},
    	    			error : function(da) {
    	    				layer.msg('请求失败!');
    	    			}
    	    		});
            	}else{
            		$('#send').show();
            	}
            }); */
            $('#send').click(function(){
            	$('#send').hide();
            	var url = '${root}/Customer/voucher/asynSendSpVoucher.do?jsonStr=${jsonStr}';
            	//var keyStrJson = {'keyStr':$.trim($('#keyStr').val())};
            	layer.confirm('是否确定发布?',function(){
            		$.ajax({
    	    			type : "POST",
    	    			url : url,
    	    			async : true,
    	    			data :$('#sendForm').serialize(),
    	    			dataType:'json',
    	    			success : function(da) {
    	    				if(da.success){
    	    					layer.msg('正在发送优惠券,页面即将刷新!',{tiem:500},function(){
    	    					location.href="${root}/Customer/voucher/toIndex.do";
    	    					});
    	    				}else{
    	    					layer.msg(da.message);
    	    				}
    	    			},
    	    			error : function(da) {
    	    				layer.msg('请求失败!');
    	    			}
    	    		});
            	},function(){
            		$('#send').show();
            	})
            	/* if(confirm('是否确定发布?')){
            		$.ajax({
    	    			type : "POST",
    	    			url : url,
    	    			async : true,
    	    			//data : $('#sendForm').serialize(),
    	    			dataType:'json',
    	    			success : function(da) {
    	    				if(da.success){
    	    					layer.msg('正在发送优惠券,页面即将刷新!',{tiem:2000},function(){
    	    					location.href="${root}/Customer/voucher/toIndex.do";
    	    					});
    	    				}else{
    	    					layer.msg(da.message);
    	    				}
    	    			},
    	    			error : function(da) {
    	    				layer.msg('请求失败!');
    	    			}
    	    		});
            	}else{
            		$('#send').show();
            	} */
            });
            
            //指定用户列表
            var condition = '${condition}';
        	var pageUrl='${root}/Customer/voucher/searchConfig.do?jsonStr=${jsonStr}';
        	if(condition==3){//excel导入
        		pageUrl='${root}/Customer/voucher/getTempStoreList.do?jsonStr=${jsonStr}';
        	}
            $("#jpagination").pagination({
                pageSize: 10,
                remote: {
                    url: pageUrl,
                    params: {'keyStr': $("#keyStr").val()},
                    success: function(data) {
                    	var html = '';
                        $.each(data.list,function(i,item){
                        	html+='<tr>'
                        	    +'<td><input class="ml-small J_checkbox" type="checkbox" name="storeCheckBox" value="'+item.id+'"></td>'
                        	    +'<td>'+item.suppliercode+'</td>'
                        	    +'<td>'+item.name+'</td>'
                        	    +'<td>'+item.contact+'</td>'
                        	    +'<td>'+item.mobile+'</td>'
                        	    +'<td>'+item.spGropName+'</td>'
                        	    +'<td>'
                        	    +'<input type="hidden" value="'+item.id+'">'
                        	    +'<input type="button" class="storeDel button button-operate" value="删除">'
                        	    +'</td>'
                        	    +'</tr>';
                        });
                       $('#storeTbody').html(html);
                       
                       $('#delDiv input[name="delStoreArr"][type="hidden"]').each(function(){
                    	   var delInput=$(this).val();
                     	   $('.storeDel').each(function(){
                     		   if($(this).prev().val()==delInput){
                     			   $(this).removeClass('storeDel').addClass('storeAdd').val('已删除');
                     		   }
                     	   })
                       });
                    },
                    totalName: 'totalSize'
                }
            });
            //搜索按钮
            $('#search').on('click',function() {
    			$("#jpagination").pagination('setParams', {'keyStr': $('#keyStr').val()});
    			$("#jpagination").pagination('setPageIndex', 0);
            	$("#jpagination").pagination('remote');
            });
            
            
          //全选按钮
            $('#allCheck').click(function(){
            	$('input[type="checkbox"][name="storeCheckBox"]').prop('checked',$(this).prop('checked'));
            });
            
            //删除全部按钮
            $('#delAll').click(function(){
            	var $InputChecxBox = $('input[type="checkbox"][name="storeCheckBox"]:checked');
            	if($InputChecxBox.length==0){
            		layer.msg('请选择要删除的店铺!',{time:2000});
            		return;
            	}
            	var html='';
            	$.each($InputChecxBox,function(i,item){
            		$(this).parent('td').siblings('td:last').children('input[type="button"]').removeClass('storeDel').addClass('storeAdd').val('已删除');
            		$('#delDiv input[name="delStoreArr"][value="'+$(this).val()+'"]').remove();
            		html+='<input type="hidden" name="delStoreArr" value="'+$(this).val()+'">';
            	});
            	$('#delDiv').append(html);
            });
            
            //列表删除按钮
            $('#storeTbody').on('click','.storeDel',function(){
            	$(this).removeClass('storeDel').addClass('storeAdd').val('已删除');
            	var html = '<input type="hidden" name="delStoreArr" value="'+$(this).prev().val()+'">';
            	$('#delDiv input[name="delStoreArr"][value="'+$(this).prev().val()+'"]').remove();
            	$('#delDiv').append(html);
            	//勾住checkbox
            	$(this).parent('td').siblings('td:first').children('input').prop('checked','checked');
            });
            
          //列表取消删除
            $('#storeTbody').on('click','.storeAdd',function(){
            	$(this).removeClass('storeAdd').addClass('storeDel').val('删除');
            	$('#delDiv input[name="delStoreArr"][value="'+$(this).prev().val()+'"]').remove();
            	//勾住checkbox
            	$(this).parent('td').siblings('td:first').children('input').prop('checked','');
            });
        });
    </script>
</body>
</html>