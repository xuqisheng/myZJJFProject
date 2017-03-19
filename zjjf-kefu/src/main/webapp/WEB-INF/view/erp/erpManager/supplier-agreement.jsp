<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setAttribute("root", request.getContextPath());%>
<%@ include file="../../common/taglib.jsp"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>供应商管理</title>
		<link rel="stylesheet" type="text/css" href="${root}/resources/css/base.css" />
		<link rel="stylesheet" type="text/css" href="${root}/resources/vendor/jquery/ztree/css/zTreeStyle/zTreeStyle.css" />			
		<script src="${root}/resources/vendor/jquery/jquery-1.12.4.min.js"></script>
		<script src="${root}/resources/js/comm.js"></script>
		<script src="${root}/resources/vendor/jquery/ztree/js/jquery.ztree.all.js"></script>
		<%@ include file="../../common/head.jsp"%>
	</head>

	<body>
		<div class="wrap-bd">
			<div class="mb-small clearfix">
				<div class="fl" style="padding-top: 10px;">供应商合同管理</div>
				<span class="button button-default fr" id="J_addContract">新增合同</span>
			</div>
			<div class="bg wrap-bd">
				<div class="">
					<label id="" class="">
							关键词：
						</label>
					<input type="text" name="keyStr" id="keyStr" value="" class="input input-default mr-default" placeholder="供应商编号/名称" />
					<input type="button" name="" id="contractSearch" value="搜索" class="input input-search-button" />
				</div>
			</div>
			
				<div class="clearfix mt-default">
					<div id="" class="fl bg" style="width: 15%;overflow-x: auto;min-height: 400px;">
						<div class="ztree" id="">
							<!-- <ul id="treeDemo" class="ztree">
							</ul> -->
							<%@ include file="../../common/ztree.jsp"%>
						</div>
					</div>
					<table class="table-list mb-default fr" style="width: 83%;">
						<thead>
							<tr>
								<th>序号</th>
								<th>合同编号</th>
								<th>供应商编号</th>
								<th>供应商名称</th>
								<th>申报时间</th>
								<th>操作</th>

							</tr>
						</thead>
						<tbody class="table-tbody" id="contractTbody">
						</tbody>
					</table>
					<%@ include file="../../common/pagination.jsp"%>
				</div>
		</div>
	</body>
	<script type="text/javascript">
	var searchObject={};
	var zTreeObj;
	var zSetting = {
        view: {
            showLine: false,
            showIcon: showIconForTree
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
	    	onClick: onClick
	    }
	};
	var zNodes = '';
    function showIconForTree(treeId, treeNode) {
        return !treeNode.isParent;
    };
	function onClick(e, treeId, treeNode) {
	    $('#regionLevel').val(treeNode.regionLevel);
	    $('#regionId').val(treeNode.id);
	    //if(treeNode.regionLevel!=0){
	    	searchObject.regionLevel=treeNode.regionLevel;
	    	searchObject.regionId=treeNode.id;
		    $("#jpagination").pagination('setParams',searchObject);
	    	$("#jpagination").pagination('setPageIndex', 0);
	    	$("#jpagination").pagination('remote'); 	    	
	    //}
    	var html = '<option value="-1" selected="selected">请选择</option>';
    	if(treeNode.regionLevel!=1){
    		if(treeNode.regionLevel==2){//省
    			//生成省html;
    			$.each(zNodes,function(i,item){
    				$.each(item.regionList,function(i,item){//省
    					html+='<option value="'+item.id+'">'+item.name+'</option>';
    				});
    			});
    		 $('#regionsPId1').html(html);
    		 $('#regionsPId1').val(treeNode.id);
    		 html = '<option value="-1" selected="selected">请选择</option>';
    		 $.each(zNodes,function(i,item){
    			$.each(item.regionList,function(i,item){//省
    				$.each(item.regionList,function(i,item){//市
    					if(item.pId==treeNode.id){
    						html+='<option value="'+item.id+'">'+item.name+'</option>';
    					}
    				});
    			}); 
    		 });
    		 $('#regionsPId2').html(html);
    		}else if(treeNode.regionLevel==3){//市
    			var provincceId = treeNode.getParentNode().id;
    		    $.each(zNodes,function(i,item){
    		    	//生成省html
    		    	$.each(item.regionList,function(i,item){//省
    		    		html+='<option value="'+item.id+'">'+item.name+'</option>';
    		    	});
    		    });
    		    $('#regionsPId1').html(html);
       		    $('#regionsPId1').val(provincceId);
       		    
       		    html = '<option value="-1" selected="selected">请选择</option>';
       		    //生成市html
       		    $.each(zNodes,function(i,item){
       		    	$.each(item.regionList,function(i,item){//省
       		    		$.each(item.regionList,function(i,item){//市
       		    			if(item.pId == provincceId){
       		    				html+='<option value="'+item.id+'">'+item.name+'</option>';
       		    			}
       		    		});
       		    	});
       		    });
       		    $('#regionsPId2').html(html);
       		    $('#regionsPId2').val(treeNode.id);
       		    
       		    //生成区html
       		    html = '<option value="-1" selected="selected">请选择</option>';
       		    $.each(zNodes,function(i,item){
       		    	$.each(item.regionList,function(i,item){//省
       		    		$.each(item.regionList,function(i,item){//市
       		    			$.each(item.regionList,function(i,item){//区
                                if(item.pId == treeNode.id){
                                	html+='<option value="'+item.id+'">'+item.name+'</option>';
                                }               		    		
               		    	});		
           		    	});	
       		    	});
       		    });
       		    $('#regionsPId3').html(html);
    		}else if(treeNode.regionLevel==4){//区
    			var provincceId = treeNode.getParentNode().getParentNode().id;//省id
    			var cityId = treeNode.getParentNode().id;//市id
    			
    			//生成省html
    			$.each(zNodes,function(i,item){
    		    	$.each(item.regionList,function(i,item){//省
    		    		html+='<option value="'+item.id+'">'+item.name+'</option>';
    		    	});
    		    });
    			$('#regionsPId1').html(html);
       		    $('#regionsPId1').val(provincceId);
       		    
       		    html = '<option value="-1" selected="selected">请选择</option>';
    		    //生成市html
    		    $.each(zNodes,function(i,item){
    		    	$.each(item.regionList,function(i,item){//省
    		    		$.each(item.regionList,function(i,item){//市
    		    			if(item.pId == provincceId){
    		    				html+='<option value="'+item.id+'">'+item.name+'</option>';
    		    			}
    		    		});
    		    	});
    		    });
    		    $('#regionsPId2').html(html);
    		    $('#regionsPId2').val(cityId);
    		    
    		    //生成区html
       		    html = '<option value="-1" selected="selected">请选择</option>';
       		    $.each(zNodes,function(i,item){
       		    	$.each(item.regionList,function(i,item){//省
       		    		$.each(item.regionList,function(i,item){//市
       		    			$.each(item.regionList,function(i,item){//区
                                if(item.pId == cityId){
                                	html+='<option value="'+item.id+'">'+item.name+'</option>';
                                }               		    		
               		    	});		
           		    	});	
       		    	});
       		    });
       		    $('#regionsPId3').html(html);
       		    $('#regionsPId3').val(treeNode.id);
    		}
    	}
	}
	
	$(function(){
		
		//查询所有启用的区域
		$.ajax({
    			type : "POST",
    			url : "${root}/Corner/Region/getAllEnabledRegion.do",
    			dataType:'json',
    			async : true,
    			//data : $('#addForm').serialize(),
    			success : function(da) {
    				if(da.success){
    					zNodes = da.message;
    					zTreeObj = $.fn.zTree.init($("#ztree"), zSetting, zNodes);
    					zTreeObj.expandAll(true);
    				}else{
    					layer.msg(da.message);
    				}
    			},
    			error : function(da) {
    				layer.msg('失败的请求!');
    			}
    		});
		
		
		//搜索按钮
		$('#contractSearch').on('click',function(){
			searchObject.keyStr=$('#keyStr').val();
			$("#jpagination").pagination('setParams',searchObject);
	    	$("#jpagination").pagination('setPageIndex', 0);
	    	$("#jpagination").pagination('remote'); 
		});
		
		
		//新增合同按钮
		$('#J_addContract').on('click',function(){
			location.href="${root}/maContract/toAddContract.do";
		});
		
		
		//J_edit
		$('#contractTbody').on('click','.J_edit',function(){
			var id = $(this).parent('td').parent('tr').attr('data-id');
			if(id==null||id==''){
				layer.msg('缺少id!');
				return;
			}
			location.href="${root}/maContract/toAddContract.do?id="+id;
		});
		
		
		//列表查询
	    $("#jpagination").pagination({
	    	    pageSize: 10,
	    	    remote: {
	    	        url: '${root}/maContract/getContractList.do',
	    	        success: function(data) {
	    	           var html = '';
	                   if(data.flag){
	                	   $.each(data.list,function(i,item){
	                		   html+='<tr data-id="'+item.id+'">'
	                		       +'<td>'+(i+1)+'</td>'
	                		       +'<td>'+item.code+'</td>'
	                		       +'<td>'+item.managerCode+'</td>'
	                		       +'<td>'+item.managerName+'</td>'
	                		       +'<td>'+item.addTimeStr+'</td>'
	                		       +'<td>'
	                		       +'<input type="button" name="" id="" value="编辑" class="button button-operate J_edit" />'
	                		       +'<input type="button" name="" id="" value="删除" class="button button-operate J_del" />'
	                		       +'</td>'
	                		       +'</tr>';
	                	   });
	                	   $('#contractTbody').html(html); 
	                   }
	    	        },
	    	        totalName:'totalSize'
	    	    }
	    	});
		
		//删除操作
		$('#contractTbody').on('click','.J_del',function(){
			var id = $(this).parent().parent().attr('data-id'); 
			$.ajax({
    			type : "POST",
    			url : "${root}/maContract/delContract.do",
    			data:{"id":id},
    			dataType:'json',
    			async : true,
    			success : function(da) {
    				if(da.success){
    					layer.msg('删除成功!',{time:1000},
    							 location.reload()
    					)
    				}else{
    					layer.msg('删除失败!')
    				}
    			},
    			error : function(da) {
    				layer.msg('失败的请求!');
    			}
    		});
		});
	});
	
    
	</script>
</html>
