<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>充值送券</title>
    <%@ include file="../common/head.jsp"%>

    <script src="../resources/js/comm.js"></script>
</head>
<body>
<div class="wrap-bd">
    <table class="table-list">
        <thead>
        <tr>
            <th>序号</th>
            <th>充值金额满</th>
            <th>赠送优惠券</th>
            <th>优惠券面额</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="J_tableBbody">
        <c:forEach items="${list}" var="item" step="1" varStatus="status">
        <tr data-gradeId="${item.id}">
            <td>${status.index+1}</td>
            <td>
                ${item.gradeMoney}
                <%-- <input type="text" name="" id="" class="input input-default" value="${item.gradeMoney}"> --%>
            </td>
            <td>${item.voucherName}</td>
            <td>${item.useMoney}</td>
            <td>
                <input type="hidden" name="gradeId" value="${item.id}"></input>
                <input type="button" value="删除" class="button button-operate J_del">
            </td>
        </tr>
        </c:forEach>
         <tr>
            <td colspan="5">
                <span class="button button-operate" id="J_addCoupon">+增加</span>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<div class="dialog hidden" id="J_dialogCoupon">
    <div class="dialog-head">
    选择优惠券<span class="dialog-close"></span>
    </div>
    <div class="dialog-body">
        <div class="mb-small">
            <input type="text" name="" id="spVoucherSearchKey" placeholder="优惠券名称" class="input" style="width: 600px;">
            <input type="button" name="" id="addSpVoucherSearch" value="搜索" class="input input-search-button">
        </div>
        <table class="table-list" style="width: 679px">
            <thead>
            <tr>
                <th>优惠券名称</th>
                <th>面值</th>
                <th>有效期</th>
                <th>使用金额限制</th>
            </tr>
            </thead>
            <tbody class="hover-select" id="J_tableBody">
            <!-- <tr>
                <td>优惠券名称优惠券</td>
                <td width="40">￥10</td>
                <td width="50">10天</td>
                <td>1000</td>
            </tr>
            <tr>
                <td>优惠券名称优惠券名称优惠</td>
                <td width="40">￥10</td>
                <td width="50">10天</td>
                <td>1000</td>
            </tr> -->
            </tbody>
        </table>
        <%@ include file="../common/pagination.jsp"%>
    </div>
    <div class="dialog-foot">
        <input type="button" name="" id="" value="确定" class="dialog-button dialog-ok">
        <input type="button" name="" id="" value="取消" class="dialog-button dialog-cancel">
    </div>
</div>
<div class="cover-all"></div>
<script>
    $(function() {
        //增加一行
        $('#J_addCoupon').on('click',function(){
	        var $lastTr = $('#J_tableBbody tr:last').prev('tr');
	        var index = '';
	        if($lastTr.length != 0) {
                index = parseInt($.trim($lastTr.children('td:first').html()));
	        } else {
	        	index = 0;
	        }
	        var html = '<tr>'
		             +     '<td>' + (index + 1) + '</td>'
	                 +     '<td><input type="text" value="" class="input input-date J_Add"></input></td>'
	                 +     '<td><input class="button button-operate J_select" value="选择优惠劵" type="button"> <span class="J_text"></span></td>'
	                 +     '<td></td>'
	                 +     '<td>'
	                 +         '<input type="button" value="保存" class="button button-operate J_save"> '
	                 +         '<input type="button" value="删除" class="button button-operate J_del">'
	                 +     '</td>'
	                 + '</tr>';
	        $(this).parent('td').parent('tr').before(html);            
        });
    	
    	//保存充值数据
    	$('#J_tableBbody').on('click','.J_save',function(){
    		var regx = /^(([1-9]\d*)|\d)(\.\d{1,2})?$/;
    		$tr = $(this).parent('td').parent('tr');
    		var gradeMoney = $.trim($tr.find('td').eq(1).children('input').val());
    		if(!regx.test(gradeMoney)){
         		layer.msg('充值金额格式不正确!',{time:1000});
         		return;
         	}
    		var voucherId = $tr.attr('data-voucherId');
    		var jsonObject = {'gradeMoney':gradeMoney,'spVoucherId':voucherId};
    		var $this = $(this);
    		$.ajax({
     			type: "POST",
     			url: "${root}/wallet/addRechargeGrade.do",
     			dataType: 'json',
     			data: jsonObject,
     			async: false
     		}).done(function(da) {
                if(da.success) {
                    layer.msg('保存成功!',{
                        time:500
                    },function(){
                        location.href="${root}/wallet/toRechargeSpVoucher.do"; 
                    });
                } else {
                }
            }).fail(function(da) {
                layer.msg('失败的请求!');
            });
    	});
    		
    	//编辑优惠劵
    	var $editIndex = '';
    	$('#J_tableBbody').on('click','.J_select', function() {
    		$editIndex = $(this);
    		var searchObject = {searchKey:$('#spVoucherSearchKey').val()};
        	//获取可用优惠劵列表
        	$("#jpagination").pagination({
        	    pageSize: 5,
        	    remote: {
        	        url: '${root}/Customer/voucher/getPageSpVoucherTempList.do',
        	        params: searchObject,
        	        success: function(data) {
        	           var html = '';
                       if(data.flag){
                    	   $.each(data.list,function(i,item) {
                    		   html += '<tr class="spVoucherTr">'
                    		         + '<input type="hidden" value="' + item.id + '">'
                    		         + '<td>' + item.ruleName + '</td>'
                    		         + '<td>￥' + item.useMoney + '</td>'
                    		         + '<td>' + item.useDay + '天</td>';
                    		   if(item.startPrice==null || item.startPrice=='' || item.startPrice==0){
                    		       html+='<td></td>';
                    		   } else {
                    		       html+='<td>'+item.startPrice+'</td>';
                    		   }
                    		   html += '</tr>';
                    	   });
                    	   $('#J_tableBody').html(html);
                    	   dialogPosCenter('#J_dialogCoupon');
                       } else {
                    	   layer.msg('获取优惠劵列表出错!');
                       }
        	        },
        	        totalName:'totalSize'
        	    }
        	});
        	$('#J_dialogCoupon, .cover-all').fadeIn();
    	});
    	
    	//选中优惠劵
        $('#J_tableBody').on('click', 'tr', function() {
            $(this).addClass('active').siblings('tr').removeClass('active');
        });
    	
       //选择优惠劵搜索按钮
       $('#addSpVoucherSearch').on('click',function(){
        	//$('.spVoucherTr').removeClass('active'); 
        	var searchObject = {searchKey:$.trim($('#spVoucherSearchKey').val())};
        	$("#jpagination").pagination('setParams',searchObject);
        	$("#jpagination").pagination('setPageIndex', 0);
        	$("#jpagination").pagination('remote');
        });
    	
       $('#J_dialogCoupon').on('click', '.dialog-close, .dialog-cancel', function() {
           $('#J_dialogCoupon, .cover-all').fadeOut();
           $("#jpagination").pagination('destroy');
       }).on('click', '.dialog-ok', function() {
    	   if($('#J_tableBody tr.active').size()==0){
    		   layer.msg('请选择一张优惠劵!',{time:1000});
    	   }else{
    		   var html = '';
    		   console.log($editIndex);
    		   $('#J_tableBody tr.active').each(function(){
    			   //获得优惠劵名称
                   $editIndex.siblings('.J_text').html($(this).children('td:first').html());
    			   //获得优惠劵面额
    			   $editIndex.parent('td').next('td').html($(this).children('td:first').next('td').html());
    			   //获得优惠劵id
    			   $editIndex.parent('td').parent('tr').attr('data-voucherId',$(this).children('input[type="hidden"]:first').val());
    		   });
               $('#J_dialogCoupon, .cover-all').fadeOut();
    		   $("#jpagination").pagination('destroy');
    	   }
        }); 
       
   		//输入金额后判断格式是否正确
         /* $('#J_tableBbody').on('blur','.J_Add',function(){
         	var regx = /^(([1-9]\d*)|\d)(\.\d{1,2})?$/;
         	if(!regx.test($.trim($(this).val()))){
         		layer.msg('格式不正确!');
         		return;
         	}
         }); */
    		
        //删除功能
        $('#J_tableBbody').on('click','.J_del',function(){
        	var id = $(this).parent('td').parent('tr').attr('data-gradeid');
        	if(id!=null){
        		layer.confirm('确定删除吗?', function(){
        			$.ajax({
            			type : "POST",
            			url : "${root}/wallet/deleteRechargeGrade.do",
            			dataType:'json',
            			data:{'id':id},
            			async : false
            		}).done(function(da) {
            		    if(da.success) {
                            layer.msg('删除成功!', {time:500}, function() {
                                location.href="${root}/wallet/toRechargeSpVoucher.do"; 
                            });
                        } else {
                            layer.msg('删除失败!');
                        }
                    }).fail(function(da) {
                        layer.msg('失败的请求!');
                    });
        		}, function() {
        		})
        	} else {
        		layer.msg('删除成功!', {time:500}, function() {
        		    $(this).parent('td').parent('tr').remove();
			    	location.href="${root}/wallet/toRechargeSpVoucher.do"; 
			    });
        	}
        });
    });
</script>
</body>
</html>
