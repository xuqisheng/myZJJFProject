<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<title>街坊店宝</title>
<%@ include file="../common/head.jsp"%>
<script src="${root}/resources/js/comm.js?v"></script>
</head>
<body>
	<div class="wrap-bd">
		<div class="mb-default clearfix">
			<div class="fl title">客户分组</div>
			<div class="fr">
				<button class="button" id="J_addGroup">增加分组</button>
			</div>
		</div>

		<div class="mt-small">
			<table class="table-list table-border">
				<thead class="table-thead">
					<tr class="table-header">
						<th>分组名称</th>
						<th>客户数</th>
						<th>备注</th>
						<th>创建日期</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody class="table-tbody" id="J_abc">
				</tbody>
			</table>
			<%@ include file="../common/pagination.jsp"%>
		</div>
	</div>
	<div class="dialog hidden" id="J_dialog">



		<form method="post" id="Customer">
			<input type="hidden" id="groupId" value="" name="id" />
			<div class="dialog-head">
				<div class="dialog-title" id="abcdefg"></div>
				<div class="dialog-close"></div>
			</div>
			<div class="dialog-body">
				<div class="mb-default required">
					<label class="label">分组名称：</label> <input type="text" value=""
						id="name" name="name" class="input" style="width: 360px"
						placeholder="">
				</div>
				<p>
					<label class="label">分组备注：</label> <input type="text" value=""
						id="remark" name="remark" class="input" style="width: 360px"
						placeholder="">
				</p>
				<div class="mb-small">
					<label>分组客户：</label> 已选 <a href="javascript: void(0);" class="fr"
						id="J_addCustomer">+添加客户</a>
				</div>
				<div style="width: 480px; max-height: 250px; overflow-y: auto">
					<table class="table-list">
						<thead class="table-thead">
							<tr>
								<th width="160">店铺名称</th>
								<!-- <th width="130">联系人</th>
								<th width="130">联系电话</th> -->
								<th width="60">操作</th>
							</tr>
						</thead>
						<tbody class="table-tbody" id='abc1'>

						</tbody>
					</table>
				</div>
			</div>
			<div class="dialog-foot">
				<input type="button" class="dialog-button dialog-ok" value="确定">
				<input type="button" class="dialog-button dialog-cancel" value="取消">
			</div>
		</form>


	</div>

	<!-- 编辑 选择客户 -->
	<div class="dialog hidden" id="J_dialogSelect">
		<form method="post">
			<div class="dialog-head">
				<div class="dialog-title">选择客户</div>
				<div class="dialog-close"></div>
			</div>
			<div class="dialog-body">
				<div class="mb-small">
					<input type="text" value="" class="input-search-text"
						style="width: 420px" placeholder="" id='name12'> <input
						type="button" value="搜索" class="input-search-button"
						onclick="asdfsafd()">
				</div>
				<div style="width: 480px; max-height: 150px; overflow-y: auto">
					<table class="table-list">
						<thead class="table-thead">
							<tr>
								<th width="160">店铺名称</th>
								<!-- <th width="130">联系人</th>
								<th width="130">联系电话</th> -->
								<th width="60">操作</th>
							</tr>
						</thead>
						<tbody class="table-tbody" id='abc2'>
						</tbody>
					</table>
				</div>
				<div class="mt-default mb-small">
					<label>分组客户：</label> 已选
				</div>
				<div style="width: 480px; max-height: 150px; overflow-y: auto">
					<table class="table-list">
						<thead class="table-thead">
							<tr>
								<th width="160">店铺名称</th>
								<!-- <th width="130">联系人</th>
								<th width="130">联系电话</th> -->
								<th width="60">操作</th>
							</tr>
						</thead>
						<tbody class="table-tbody" id="abc">

						</tbody>
					</table>
				</div>
			</div>
			<div class="dialog-foot">
				<input type="button" class="dialog-button dialog-ok" value="确定">
				<input type="button" class="dialog-button dialog-cancel" value="取消">
			</div>
		</form>
	</div>


	<!-- 添加客户 -->
	<div class="dialog hidden" id="J_dialogAddCustomer">

		<div class="dialog-head">
			<div class="dialog-title">选择客户</div>
			<div class="dialog-close"></div>
		</div>
		<div class="dialog-body">
			<div class="mb-small">
				<input type="text" class="input-search-text" style="width: 420px"
					placeholder="" id='name1'> <input type="button" value="搜索"
					class="input-search-button" onclick="sousuo()">
			</div>
			<div style="width: 480px; max-height: 150px; overflow-y: auto">
				<table class="table-list">
					<thead class="table-thead">
						<tr>
							<th width="160">店铺名称</th>
							<!-- <th width="130">联系人</th>
							<th width="130">联系电话</th> -->
							<th width="60">操作</th>
						</tr>
					</thead>
					<tbody class="table-tbody" id='abcd2'>
					</tbody>
				</table>
			</div>
			<p>
			<form method="post" id="fromaaa">
				<input type="hidden" id="groupId1" name="id" value="" /> <label>分组客户：</label>
				已选
				</p>
				<div style="width: 480px; max-height: 150px; overflow-y: auto">
					<table class="table-list">
						<thead class="table-thead">
							<tr>
								<th width="160">店铺名称</th>
								<!-- <th width="130">联系人</th>
								<th width="130">联系电话</th> -->
								<th width="60">操作</th>
							</tr>
						</thead>
						<tbody class="table-tbody" id="abcd">
						</tbody>
					</table>
				</div>
		</div>
		<div class="dialog-foot">
			<input type="button" class="dialog-button dialog-ok" value="确定">
			<input type="button" class="dialog-button dialog-cancel" value="取消">
		</div>
		</form>
	</div>
	<div class="cover-all"></div>
<script>
    $(function() {
    	 $("#jpagination").pagination({
             pageSize: 10,
             remote: {
                 url: '${root}/scms/storegruop/list.do',
                 success: function(data) {
                     var html='';
                     $.each(data.list, function(i,item) {
                         html+='<tr><td>'+item.name+'</td>';
                     	html+='<td>'+item.number+'</td>';
    	                html+='<td>'+item.remark+'</td>';
    	                html+='<td>'+item.date+'</td>';
    	                if(item.name=='线下客户组'){
    	                	html+='<td></td></tr>'; 
    	                }else{
    	                	html+='<td><a href="javascript:void(0)" onclick="add(\''+item.id+'\')">添加客户</a>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="edit(\''+item.id+'\')">编辑</a>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="delete1(\''+item.id+'\')">删除</a></td></tr>'; 
    	                }
    	                });
                     if(html == "") {
                      	html = '<tr><td colspan="5" class="no-data"></td></tr>';
                      }
                     $('#J_abc').html(html);
                 },
                 totalName:'totalSize'
             }
        });
    	
        dialogPosCenter('#J_dialog');
        dialogPosCenter('#J_dialogSelect');
	    dialogPosCenter('#J_dialogAddCustomer');
        
        $('#J_addGroup').on('click', function() {
        	$.post("${root}/scms/storegruop/findAllNoGroupStore.do",null,function(data){
    			if(data.success){
    				$("#groupId").val('');
    				 var html='';
    				 $.each(data.message, function(i,item) {
    					 html+='<tr><input type="hidden" name="ids" value='+item.id+'><td>'+item.name+'</td>';
    					 //html+='<td>'+item.contact+'</td>';
    					 //'<td>'+item.mobile+'</td>
    					 html+='<td><span class="cur-p" onclick="addCustomer(this)">+</span></td></tr>';
    				 });
    				 $('#abc2').html(html);
    				 dialogPosCenter('#J_dialog');
    				 $("#abcdefg").html("新增分组");
    				 $('#J_dialog, .cover-all').fadeIn();
    			}
    			
    		},"json")
            
        });
        
        $('#J_dialog').on('click', '.dialog-ok', function() {
			var name=$.trim($("#name").val());
			var remark=$.trim($("#remark").val());
			if(name==""){layer.msg("组名不能空", {time: 5000}); $("#name").focus();return;}
			if(name.length>15){layer.msg("组名太长", {time: 5000}); $("#name").focus();return;}
			if(remark.length>30){layer.msg("备注太长", {time: 5000}); $("#remark").focus();return;}
			
			var data = $("#Customer").serialize();
			$.ajax( {
			        type : "post",
			        url : "${root}/scms/storegruop/addgroup.do",
			        data : data,
			        dataType:"json",
			        success : function(data) {
			        	$("#Customer")[0].reset();
			        	$('#J_dialog, .cover-all').fadeOut();
			        	layer.msg(data.message, {
			        		  icon: 1,
			        		  time: 2000 //2秒关闭（如果不配置，默认是3秒）
			        		}, function(){
			        			window.location.reload();
			        		});
			        }
			    });
           
        }).on('click', '.dialog-close, .dialog-cancel', function() {
        	$("#name, #remark").val('');
			$('#abc1, #abc2, #abc').html('');
            $('#J_dialog, .cover-all').fadeOut();
        });
        $('#J_addCustomer').on('click', function() {
            $('#J_dialog').hide();
        	dialogPosCenter('#J_dialogSelect');
            $('#J_dialogSelect').show();
        });
        $('#J_dialogSelect').on('click', '.dialog-ok', function() {
        	var html=$("#abc").html().replace("/removeCoustomer/g","removeObj");
        	$("#abc").html('');
        	$("#abc1").append(html);
        	dialogPosCenter('#J_dialog');
            $('#J_dialogSelect').hide();
            $('#J_dialog').show();
        }).on('click', '.dialog-close, .dialog-cancel', function() {
            $('#J_dialogSelect').hide();
            $('#J_dialog').show();
        });

        $('#J_dialogAddCustomer').on('click', '.dialog-ok', function() {
        	var data = $("#fromaaa").serialize();
        	$.ajax( {
		        type : "post",
		        url : "${root}/scms/storegruop/addcustomer.do",
		        data : data,
		        dataType:"json",
		        success : function(data) {
		        	$("#abcd").html('');
		        	$("#abcd2").html('');
		        	
		        	$('#J_dialogAddCustomer').hide();
		        	layer.msg(data.message, {
		        		  icon: 1,
		        		  time: 2000 //2秒关闭（如果不配置，默认是3秒）
		        		}, function(){
		        			window.location.reload();
				        	
		        		}); 
		        }
		    });
            
        }).on('click', '.dialog-close, .dialog-cancel', function() {
        	$("#abcd").html('');
        	$("#abcd2").html('');
            $('#J_dialogAddCustomer').hide();
        });
    })
    
    function add(id){
    	$.post("${root}/scms/storegruop/findAllNoGroupStore.do",null,function(data){
			if(data.success){
				$("#groupId1").val(id);
				 var html='';
				 $.each(data.message, function(i,item) {
					 html+='<tr><input type="hidden" name="ids" value='+item.id+'><td>'+item.name+'</td>';
					// html+='<td>'+item.contact+'</td>';
					//<td>'+item.mobile+'</td> 
					 html+='<td><span class="cur-p" onclick="aaa(this)">+</span></td></tr>';
				 });
				 $('#abcd2').html(html);

			     dialogPosCenter('#J_dialogAddCustomer');
				 $('#J_dialogAddCustomer').show();
			}
		},"json")
    	
    }
    
    function aaa(obj){
    	var str="<tr>"+$(obj).parent().parent().html()+"</tr>";
    	str=str.replace('+','删除').replace('aaa','bbbb');
    	$("#abcd").append(str);
    	$(obj).parent().parent().remove();
    	dialogPosCenter('#J_dialogAddCustomer');
    }
    
    function bbbb(obj){
    	var str="<tr>"+$(obj).parent().parent().html()+"</tr>";
    	str=str.replace('删除','+').replace('bbbb','aaa');
    	$("#abcd2").append(str);
    	$(obj).parent().parent().remove();
        dialogPosCenter('#J_dialogAddCustomer');
    }
    
    function edit(id){
    	$.post("${root}/scms/storegruop/editgroup.do",{"id":id},function(data){
			if(data.success){
				 $("#groupId").val(id);
				 $("#name").val(data.message.name);
				 $("#remark").val(data.message.remark);
				 var html='';
				 $.each(data.message.stores, function(i,item) {
					 html+='<tr><input type="hidden" name="ids" value='+item.id+'><td>'+item.name+'</td>';
					// html+='<td>'+item.contact+'</td>';
					//<td>'+item.mobile+'</td> 
					 html+='<td><span class="cur-p" onclick="removeObj(this)">刪除</span></td></tr>';
				 });
				 $('#abc1').html(html);
				 var html2='';
				 $.each(data.message.noGroup, function(i,item) {
					 html2+='<tr><input type="hidden" name="ids" value='+item.id+'><td>'+item.name+'</td>';
					 //html2+='<td>'+item.contact+'</td>';
					 //<td>'+item.mobile+'</td> 
					 html2+='<td><span class="cur-p" onclick="addCustomer(this)">+</span></td></tr>';
				 });
				 $('#abc2').html(html2);
				 dialogPosCenter('#J_dialog');
			}
			 $("#abcdefg").html("编辑分组");
			$('#J_dialog, .cover-all').fadeIn();
		},"json")
    }
    
    function addCustomer(obj){    	 
    	var str="<tr>"+$(obj).parent().parent().html()+"</tr>";
    	str=str.replace('+','刪除').replace('addCustomer','removeCoustomer');    	
    	  $("#abc").append(str);
    	$(obj).parent().parent().remove();
        dialogPosCenter('#J_dialogSelect');
    }
    
    function removeObj(obj){
    	var str="<tr>"+$(obj).parent().parent().html()+"</tr>";
    	str=str.replace('刪除','+').replace('removeObj','addCustomer');
    	$("#abc2").append(str);
    	$(obj).parent().parent().remove();
    }
    
    function removeCoustomer(obj){
    	var str="<tr>"+$(obj).parent().parent().html()+"</tr>";
    	str=str.replace('刪除','+').replace('removeCoustomer','addCustomer');
    	$("#abc2").append(str);
    	$(obj).parent().parent().remove();
    }
    
    function delete1(id){
    	if(confirm("确定删除吗?")) {
	    	$.ajax( {
		        type : "post",
		        url : "${root}/scms/storegruop/deletegroup.do",
		        data : {"id":id},
		        dataType:"json",
		        success : function(data) {
		        	layer.msg(data.message, {
		        		  icon: 1,
		        		  time: 2000 //2秒关闭（如果不配置，默认是3秒）
		        		}, function(){
		        			window.location.reload();
				        	
		        		}); 
		        }
		    });
    	}
    }
    
    function sousuo(){
    	$.post("${root}/scms/storegruop/findAllNoGroupStore.do",{"name":$.trim($("#name1").val())},function(data){
			if(data.success){
				$('#abcd2').html('');
				 var html='';
				 $.each(data.message, function(i,item) {
					 html+='<tr><input type="hidden" name="ids" value='+item.id+'><td>'+item.name+'</td>';
					 //html+='<td>'+item.contact+'</td>';
					 //<td>'+item.mobile+'</td> 
					 html+='<td><span class="cur-p" onclick="aaa(this)">+</span></td></tr>';
				 });
				 $('#abcd2').html(html);
			}
		},"json")
    }
    
    function asdfsafd(){
    	$.post("${root}/scms/storegruop/findAllNoGroupStore.do",{"name":$.trim($("#name12").val())},function(data){
			if(data.success){
				$('#abc2').html('');
				var html2='';
				 $.each(data.message, function(i,item) {
					 html2+='<tr><input type="hidden" name="ids" value='+item.id+'><td>'+item.name+'</td>';
					 //html2+='<td>'+item.contact+'</td>';
					 //<td>'+item.mobile+'</td> 
					 html2+='<td><span class="cur-p" onclick="addCustomer(this)">+</span></td></tr>';
				 });
				 $('#abc2').html(html2);
			}
		},"json")
    }
</script>
</body>
</html>