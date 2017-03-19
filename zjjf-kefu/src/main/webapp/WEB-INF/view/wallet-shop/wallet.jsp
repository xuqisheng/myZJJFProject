<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>钱包明细</title>
    <%@ include file="../common/head.jsp"%>
</head>
<body class="wrap-bd">
<div class="mb-small">
    <div>
        <input type="text" name="searchKey" id="searchKey" value="" placeholder="店铺名称/手机号码" class="input input-search-text">
        <input type="button" name="" value="搜索" class="input input-search-button" id="storeSearch">
    </div>
</div>
<div>
    <table class="table-list">
        <thead>
        <tr>
            <th>序号</th>
            <th>用户名称</th>
            <th>手机号码</th>
            <th>钱包余额</th>
            <th>钱包状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody class="table-tbody" id="J_tableBbody">
        <!-- <tr>
            <td>1</td>
            <td>店铺001</td>
            <td>13712345678</td>
            <td>20.00</td>
            <td>
                <span class="txt-success">正常</span>
                <span class="txt-warn">冻结</span>
            </td>
            <td>
                <a href="javascript: void(0)">冻结/解冻</a>
                <a href="wallet-deal-detail.html">明细</a>
            </td>
        </tr> -->
        <!-- <tr>
            <td colspan="6">无数据</td>
        </tr> -->
        </tbody>
    </table>
        <%@ include file="../common/pagination.jsp"%>
</div>
<script type="text/javascript">
 $(function(){
	 
	 var searchObject = {searchKey:$.trim($('#searchKey').val())};
	 
	 $("#jpagination").pagination({
 	    pageSize: 10,
 	    remote: {
 	        url: '${root}/wallet/getStoreWalletList.do',
 	        params: searchObject,
 	        success: function(data) {
 	        	var html = '';
                if(data.flag){
             	   var html = '';
             	   if(data.list!=null){
                      $(data.list).each(function(i,item){
                    	  html+='<tr data-status="'+item.status+'"  data-walletId="'+item.id+'">'
                    	       +'<td>'+(i+1)+'</td>'
                    	       +'<td>'+item.userName+'</td>'
                    	       +'<td>'+item.mobile+'</td>'
                    	       +'<td>'+item.balance+'</td>'
                    	       +'<td>';
                    	       if(item.status==1){
                    	    	   html+='<span class="txt-success">正常</span>';
                    	       }else{
                    	    	   html+='<span class="txt-warn">冻结</span>';
                    	       }
                    	       html+='</td>'
                    	       +'<td>';
                    	       if(item.status==1){
                    	    	   html+='<a href="javascript: void(0)" class="J_walletStatus">冻结</a>  ';
                    	       }else{
                    	    	   html+='<a href="javascript: void(0)" class="J_walletStatus">解冻</a>  ';
                    	       }
                    	       html+='<a href="${root}/wallet/toStoreWalletLog.do?id='+item.id+'">明细</a>'
                    	       +'</td>'
                    	       +'</tr>'
                    	       ;
                      });
                      $('#J_tableBbody').html(html);
             	   }else{
             		   //显示无数据
             		  html+='<tr><td colspan="6">无数据</td></tr>';
                      $('#J_tableBbody').html(html);
             	   }
                }else{
             	   layer.msg('获取钱包列表出错!');
                }
 	        },
            totalName:'totalSize'
 	    },
        showPageSizes: true
 	});
	 
	  //店铺搜索
	  $('#storeSearch').on('click',function(){
	       	searchObject = {searchKey:$.trim($('#searchKey').val())};
	       	$("#jpagination").pagination('setParams',searchObject);
	       	$("#jpagination").pagination('setPageIndex', 0);
	       	$("#jpagination").pagination('remote');
	       });
	 
	 //冻结/解冻操作
	 $('#J_tableBbody').on('click','.J_walletStatus',function(){
		  var status = $(this).parent('td').parent('tr').attr('data-status');
		  var id = $(this).parent('td').parent('tr').attr('data-walletid');
		  var $tr = $(this).parent('td').parent('tr');
		  var $this= $(this);
		  var $tdPrev = $(this).parent('td').prev('td');
		  $.ajax({
				type : "POST",
				url : "${root}/wallet/updateStoreFinWalletStatus.do",
				dataType:'json',
				async : true,
				data : {'status':status,'id':id},
				success : function(da) {
					if(da.success){
	                      if(status==1){
	                    	  $tr.attr('data-status','0');
	                    	  $this.html('解冻');
	                    	  $tdPrev.empty();
	                    	  var html = '<span class="txt-warn">冻结</span>';
	                    	  $tdPrev.html(html);
	                      }else{
	                    	  $tr.attr('data-status','1');
	                    	  $this.html('冻结');
	                    	  $tdPrev.empty();
	                    	  var html = '<span class="txt-success">正常</span>';
	                    	  $tdPrev.html(html);
	                      }               					
					}else{
						layer.msg('修改钱包状态失败!');
						console.log(da.message);
					}
				},
				error : function(da) {
					layer.msg('失败的请求!');
				}
			});
	  });
 });
  
</script>
</body>
</html>
