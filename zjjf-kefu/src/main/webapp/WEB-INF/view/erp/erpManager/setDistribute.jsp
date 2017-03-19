<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setAttribute("root", request.getContextPath());%>
<%@ include file="../../common/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>设置配送供应商</title>
		<link rel="stylesheet" type="text/css" href="${root}/resources/css/base.css" />
		<link rel="stylesheet" type="text/css" href="${root}/resources/vendor/jquery/ztree/css/zTreeStyle/zTreeStyle.css" />
		<script src="${root}/resources/vendor/jquery/jquery-1.12.4.min.js"></script>
		<script src="${root}/resources/js/comm.js"></script>
		<script src="${root}/resources/vendor/jquery/ztree/js/jquery.ztree.all.js"></script>
		<script src="${root}/resources/js/ztree-search.js"></script>
		<%@ include file="../../common/head.jsp"%>
	</head>
	<body>
		<div class="wrap-bd">
			<div class="mb-default">
				<a href="#" class="crumb">供应商管理</a>
				<a href="#" class="crumb">供应商管理</a>
				<a href="#" class="crumb">设置配送批发商</a>
			</div>
			<div class="">
				<div class="mb-default">设置配送批发商</div>
			</div>
			<div class="bg wrap-bd clearfix">

				<div class="mb-default">
					<label class="label">增加批发商：</label>
					<span class="button button-operate" id="J_chsSup">选择批发商</span>
				</div>
				<form id="spForm">
				<table class="table-list mb-default">
					<thead>
						<tr>
							<th>序号</th>
							<th>所属区域</th>
							<th>批发商编号</th>
							<th>批发商名称</th>
							<th>手机号</th>
							<th>操作</th>
						</tr>
					</thead>
					<input type="hidden" name="id" value="${managerId}">
					<tbody class="table-tbody" id="addSpGroupTbody">
					  <c:forEach var="item" items="${spList}" varStatus="status">
						<tr data-spId="${item.id}">
						    <input type="hidden" name="spIdArr" value="${item.id}">
							<td>${status.index+1}</td>
							<td>${item.provinceName}/${item.cityName}/${item.areaName}</td>
							<td>${item.supplierCode}</td>
							<td>${item.supplierName}</td>
							<td>${item.mobile}</td>
							<td>
								<input type="button" name="" id="" value="删除" class="button button-operate J_del" />
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				</form>
				<div class="cover-all">
				</div>
				<div class="dialog hidden J_dialog" id="J_choseSupplier">
					<form action="" method="post">
						<div class="dialog-head">
							选择批发商
							<div id="" class="dialog-close">
							</div>
						</div>
						<div class="dialog-body">
							<div class="mb-default">
								<input type='text' style='display:none'/>
								<input type="text" name="" id="search-condition" value="" onkeydown="entersearch()"  class="input input-default" placeholder="批发商名称" />
								<input type="button" name="" class="input input-search-button" onclick="search_ztree('ztree', 'search-condition')" id="" value="搜索" />
							</div>
							<div class="ztree" id="" style="height: 150px;overflow: auto;">
								<!-- <ul id="treeDemo" class="ztree">
								</ul> -->
								<%@ include file="../../common/ztree.jsp"%>
    			                <script src="${root}/resources/js/ztree-search.js"></script>
							</div>
						</div>
						<div class="dialog-foot">
							<div class="mt-default">
								<input type="button" class="dialog-button dialog-ok" name="" id="addOk" value="确认" />
								<input type="button" class="dialog-button dialog-cancel" name="" id="addCancel" value="取消" />
							</div>
						</div>
					</form>
				</div>
			</div>
			<div class="mt-default">
				<input type="button" name="" id="saveMap" value="保存" class="button button-ok" />
				<span id="cancel" class="button button-cancel">
					取消
				</span>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		function entersearch() {
			var event = window.event || arguments.callee.caller.arguments[0];
			if(event.keyCode == 13) {
				search_ztree('ztree', 'search-condition');
			}
		}
	var zTreeObj;
	var zNodes;
	var setting = {
		view: {
            showLine: false,
            showIcon: showIconForTree,
            fontCss: setFontCss_ztree
        },
        check: {
            enable: true
        },
	    data: {
	    	key: {
        		children: "regionList",
        	},
	        simpleData: {
	            enable: true
	        }
	    },
        callback: {
            onCheck: onCheck
        }
	};
		
	function showIconForTree(treeId, treeNode) {
        return !treeNode.isParent;
    };
    function onCheck(e, treeId, treeNode) {
        if(treeNode.checked){
        	if($('#ztree').find('input[id="'+treeNode.supplierId+'"]').length==0){//
        		var va = treeNode.supplierId+','+treeNode.supplierAreStr+','+treeNode.supplierCode+','+treeNode.name+','+treeNode.supplierMobile;
        		var html = '<input type="hidden"  class="J_supplier" value="'+va+'" id="'+treeNode.supplierId+'">';
            	$('#ztree').append(html);        		
        	}
        }else{
        	$('#ztree').find('input[id="'+treeNode.supplierId+'"]').remove();
        }
    };
	
		$(function() {
			dialogPosCenter('#J_choseSupplier');
			
			
			
			/**********************************添加批发商begin*******************************************/
			$('#J_chsSup').on('click', function() {
				
				$('#J_choseSupplier, .cover-all').fadeIn();
				
				$(zNodes).each(function(i,item){//省
	        		$(item.regionList).each(function(i,item){//市
	        			$(item.regionList).each(function(i,item){//区
	        				$(item.regionList).each(function(i,item){//定格
	                				 item.nocheck = false;
	        				         item.checked = false;
	                		});	
	            		});	
	        		});
	        	});
				var html = '';
				//判断是否要根据addSpGroupTbody下已经选中的定格勾上zTree中的checkbox
	        	if($('#addSpGroupTbody').find('tr').length!=0){
	        		$('#addSpGroupTbody').find('tr').each(function(i,item){
	        		   	var id = $(this).attr('data-spId');
	        		   	$(zNodes).each(function(i,item){//省
	                		$(item.regionList).each(function(i,item){//市
	                			$(item.regionList).each(function(i,item){//区
	                				$(item.regionList).each(function(i,item){//批发部
	                        			 if(item.supplierId == id){
	                        				item.checked = true;
	                        				var va = item.supplierId+','+item.supplierAreStr+','+item.supplierCode+','+item.name+','+item.supplierMobile;
	                        				html += '<input type="hidden" class="J_supplier" value="'+va+'" id="'+item.supplierId+'">';
	                        			 }
	                        		});	
	                    		});	
	                		});
	                	});
	        		});
	        	}
				zTreeObj = $.fn.zTree.init($("#ztree"), setting, zNodes);
	        	zTreeObj.expandAll(true);
	        	$('#ztree').append(html);
			}); 
			
			//选择定格确定按钮
		      $('#addOk').on('click',function(){
		    	  var html;
		    	  var spGrouphtml = $('#ztree').find('input[class="J_supplier"]');
		    	  $(spGrouphtml).each(function(i,item){
		    		  var arr = new Array();
			    	  arr = item.value.split(",");
		    		  html+='<tr data-spId='+arr[0]+'><input type="hidden" name="spIdArr" value="'+arr[0]+'">'//批发商id
	  				    +'<td>'+(i+1)+'</td>'
	  				    +'<td>'+arr[1]+'</td>'
	  				    +'<td>'+arr[2]+'</td>'
	  				    +'<td>'+arr[3]+'</td>'
	  				    +'<td>'+arr[4]+'</td>'
	  				    +'<td><input type="button" name="" id="" value="删除" class="button button-operate J_del" /></td>'
	  				    +'</tr>';			    		  
		    	  });
		    	  $('#addSpGroupTbody').html(html);
		    	  $('#J_choseSupplier, .cover-all').fadeOut();
		      });
			/**********************************添加批发商end*******************************************/
			
			$('.J_dialog').on('click', '.dialog-cancel', function() {

				$('.J_dialog, .cover-all').fadeOut();
			})
			$('.dialog').on('click', '.dialog-close', function() {
				$('.J_dialog, .cover-all').fadeOut();
			});
			
			
			//获取列表
	        $.ajax({
				type : "POST",
				url : "${root}/customer/supplier/getTreeSupplierList.do",
				dataType:'json',
				async : false,
				//data : $('#addForm').serialize(),
				success : function(da) {
					if(da.success){
						zNodes = da.message;
					}else{
						layer.msg(da.message);
					}
				},
				error : function(da) {
					layer.msg('获取定格失败!');
				}
			});
		});   
		
		//删除按钮
		$('#addSpGroupTbody').on('click','.J_del',function(){
			var id = $(this).parent('td').parent('tr').attr('data-spid');
			if(id==null||id==''){
				layer.msg('缺少id数据!');
				return;
			}
			$(this).parent('td').parent('tr').remove();
			$('#ztree').find('input[class="J_supplier"][value="'+id+'"]').remove();
		});
		
		
		//取消按钮
		$('#cancel').on('click',function(){
			location.href='${root}//ERPMa/toManageList.do';
		});
		
		//保存按钮
		$('#saveMap').on('click',function(){
			$('#saveMap').hide();
			$.ajax({
    			type : "POST",
    			url : "${root}/ERPMa/saveManagerSupplier.do",
    			dataType:'json',
    			async : false,
    			data : $('#spForm').serialize(),
    			success : function(da) {
    				if(da.success){
    					layer.msg('保存成功!',{time:1000},function(){
    						location.href="${root}/ERPMa/toManageList.do";
    					});
    				}else{
    					layer.msg(da.message);
    					$('#saveMap').show();
    				}
    			},
    			error : function(da) {
    				$('#saveMap').show();
    				layer.msg('失败的请求!');
    			}
    		});
		});
		 
	</script>
</html>
