<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>定格管理</title>
<%@ include file="../common/head.jsp"%>
<script src="${root}/resources/js/comm.js?vvv"></script>
</head>
<body>
<div class="wrap-bd">
	<div class="mb-default">
	    <a class="crumb" href="#">客户管理</a>
	    <a class="crumb crumb-active">定格管理</a>
	</div>
	<div class="mb-small clearfix">
			<input class="input input-search-text" type="text" name="name" value="${spGroup.name}" placeholder="请输入定格编码/名称查询" id="searchName"/>
			<input class="input input-search-button" value="搜索" type="submit" id="search">
		<div class="fr">
			<button class="button button-default" id="newSpGroup">新建定格</button>
		</div>
	</div>
	<div>
        <div class="fl bg border" style="width:20%;overflow: auto">
            <%@ include file="../common/ztree.jsp"%>
        </div>
        <div class="fr" style="width: 79%;">
	        <table class="table-list">
				<thead>
					<tr>
						<th>定格编号</th>
						<th>定格名称</th>
						<th>定格区域</th>
						<th>下属批发部</th>
						<th>下属便利店</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="spGroupTbody">
				</tbody>
			</table>
			<input type="hidden" name="regionLevel" value="" id="regionLevel">
			<input type="hidden" name="regionId" value="" id="regionId">
			<%@ include file="../common/pagination.jsp"%>
        </div>
	</div>
	<div class="dialog hidden" id="SpGroup" >
		<div class="dialog-head"></div>
		<div class="dialog-body">
			<form id="frm_spgroup" action="#">
				<p>
					<input type="hidden" value="" name="id" id="id">
					<label>定格名称：</label>
					<input class="input input-default" type="text" value="" name="name"id="name" />
					<input type="hidden" value="1" name="pid" id="pid"/>
				</p>
				<p>
					<label>定格区域：</label>
					<select class="select" id="regionsPId1" name="provinceId">
	                	<option value="-1" selected="selected">请选择</option>
	                </select>
	                <select class="select" id="regionsPId2" name="cityId">
	                    <option value="-1" selected="selected">请选择</option>
	                </select>
	                <select class="select"  id="regionsPId3" name="areaId">
	                    <option value="-1" selected="selected">请选择</option>
	                </select>
				</p>
				<p>
					<label>配置方案：</label>
					<select class="select" id="appCfgId" name="appCfgId">
	                	<option value="-1" selected="selected">请选择</option>
	                </select>
				</p>
				<div class="dialog-foot">
					<button type="button" class="dialog-button dialog-ok" id="test">确定</button>
					<button type="button" class="dialog-button dialog-cancel">取消</button>
				</div>
			</form>
		</div>
	</div>
</div>
<div class="cover-all"></div>
<script>
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
		console.log(treeNode.regionLevel);
	    $('#regionLevel').val(treeNode.regionLevel);
	    $('#regionId').val(treeNode.id);
	    //if(treeNode.regionLevel!=0){
	    	var searchObject = {'regionLevel':treeNode.regionLevel,'regionId':treeNode.id}
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
	$(function() {

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

		//查询所有可用配置方案
		$.ajax({
			url : "${root}/kefu/DBAppConfig/getAllAppConfig.do",
			type : "POST",
			dataType:'json',
			async: true ,
			success : function(da) {
				var htmlx = '<option value="-1" selected="selected">请选择</option>';
				if(da.success){
					$.each(da.message,function(i,item){
						htmlx+='<option value="'+item.id+'">'+item.name+'</option>';
					});
				}
				$('#appCfgId').html(htmlx);
			}
		});

        //列表查询
        $("#jpagination").pagination({
        	    pageSize: 10,
        	    remote: {
        	        url: '${root}/Customer/SpGroup/getSpGropuWithList.do',
        	        success: function(data) {
        	           var html = '';
                       if(data.flag){
                    	   $.each(data.list,function(i,item){
                    		   html+='<tr data-appConfigId="'+item.appCfgId+'"]>'
                    		       +'<td>'+item.id+'</td>'
                    		       +'<td><input type="hidden" name="areaId" value="'+item.areaId+'"><input type="hidden" name="areaPid" value="'+item.areaPid+'">'+item.name+'&nbsp;<span class="icon-op icon-op-edit J_edit"></td>'
                     		       +'<td>'+item.areaName+'</td>'
                     		       +'<td><a href="${root}/Customer/SpGroup/toSupplierList.do?pageIndex=1&id='+item.id+'">'+item.totalSupplier+'</a></td>'
                     		       +'<td><a href="${root}/Customer/SpGroup/toStoreList.do?pageIndex=1&id='+item.id+'">'+item.totalStore+'</a></td>'
                     		       +'<td>';
                     		       if(item.status==0){
                     		    	   html+='<input class="button button-operate disabled J_up" type="button" value="上架">';
                     		       }else{
                     		    	   html+='<input class="button button-operate J_down" type="button" value="下架">';
                     		       }
                     		       html+='</td>';
                     		       +'</tr>';
                    	   });
                    	   $('#spGroupTbody').html(html);
                       }
        	        },
        	        totalName:'totalSize'
        	    }
        	});

        //定格搜索功能
		$('#search').on('click',function(){
			var searchName = $.trim($('#searchName').val());
				var searchObject = {name:searchName};
	        	$("#jpagination").pagination('setParams',searchObject);
	        	$("#jpagination").pagination('setPageIndex', 0);
	        	$("#jpagination").pagination('remote');
		});


		//省下拉框
		$('#regionsPId1').on('change' , function(){
			var pId = $('#regionsPId1').val();//省id
			//生成市下拉框列表
			var html = '<option value="-1" selected="selected">请选择</option>';
			$.each(zNodes,function(i,item){
				$.each(item.regionList,function(i,item){//省
					$.each(item.regionList,function(i,item){//市
						if(item.pId == pId){
							html+='<option value="'+item.id+'">'+item.name+'</option>';
						}
					});
				});
			});
			$('#regionsPId2').html(html);
		});
		//市下拉框
		$('#regionsPId2').on('change' , function(){
			var pId = $('#regionsPId2').val();//市id
			//生成区下拉框
			var html = '<option value="-1" selected="selected">请选择</option>';
			$.each(zNodes,function(i,item){
				$.each(item.regionList,function(i,item){//省
					$.each(item.regionList,function(i,item){//市
						$.each(item.regionList,function(i,item){//区
							if(item.pId == pId){
								html+='<option value="'+item.id+'">'+item.name+'</option>';
							}
						});
					});
				});
			});
			$('#regionsPId3').html(html);
		});
		dialogPosCenter('.dialog');

		var $SpGroup = $('#SpGroup');

		$("#pid").on("blur",function(){
			if($("#pid").val()==""){
				alert("请输入市级区域id!");
				return;
			}
			$.ajax({
				type : "post",
				url : "${root}/Customer/SpGroup/getRegion.do",
				dataType : "json",
				data : {pid:$("#pid").val()},
				async:false,
				success : function(data) {
					if (data.success) {
						var t = '<option value="-1" selected="selected">请选择</option>';
						$.each(data.message,function(i,item){
							t+='<option value="'+item.id+'">'+item.name+'</option>';
						});
						$("#sheng").empty();
						$("#sheng").append(t);
					} else {
						alert(data.message);
					}
				},
				error : function(data) {
				}
			});
		});
	    $("#pid").trigger("blur");


		//新建按钮
		$('#newSpGroup').on('click', function() {
			$(".cover-all").show();
			$SpGroup.find('.dialog-head').html("新建定格");
			$("#name").val("");
			$("#id").val("");
			if($('#regionsPId1').children().length==1){
				var html = '<option value="-1" selected="selected">请选择</option>';
				//生成省下拉框
				$.each(zNodes,function(i,item){
					$.each(item.regionList,function(i,item){
	                   html+='<option value="'+item.id+'">'+item.name+'</option>';
					});
				});
				$('#regionsPId1').html(html);
			}
			$SpGroup.show();
		});

		//确定按钮
		$SpGroup.find('.dialog-ok').on('click', function() {
			if ($.trim($('#name').val()) == "") {
				layer.msg("定格名不能为空!");
				retrun;
			}
			;
			if ($('#regionsPId1').val() == -1 || $('#regionsPId2').val() == -1 ||$('#regionsPId3').val() == -1) {
				layer.msg("请选择区域!");
				retrun;
			};

			var url = "${root}/Customer/SpGroup/updateSpGroup.do";

			$.ajax({
				type : "post",
				url : url,
				dataType : "json",
				data : $('#frm_spgroup').serialize(),
				success : function(data) {
					if (data.success) {
						if($.trim($("#id").val()) != ""){
						    layer.msg('修改成功!',{
						    	time:1500
						    },function(){
						    location.href = "${root}/Customer/SpGroup/toDG.do";
						});
						}else{
							 layer.msg('新增成功!',{
								 time:1500
							 },function(){
								    location.href = "${root}/Customer/SpGroup/toDG.do";
								});
						}
					} else {
						layer.msg(data.message);
					}
				},
				error : function(data) {
				}
			});
		});

		//取消按钮
		$SpGroup.find('.dialog-cancel').on('click', function() {
			$("#sheng").val("-1");
        	$("#shi").val("-1");
        	$("#area").val("-1");
        	$("#name").val("");
			$SpGroup.hide();
			$(".cover-all").hide();
			var html = '<option value="-1" selected="selected">请选择</option>';
			$('#regionsPId1').html(html);
			$('#regionsPId2').html(html);
			$('#regionsPId3').html(html);
		});

		//修改定格
		$('#spGroupTbody').on('click','.J_edit',function() {
			$SpGroup.find('.dialog-head').html("修改定格");
			$(".cover-all").show();
			var areaId = $(this).prev().prev().val();//区id
			var areaPid = $(this).prev().val();//市id
			$('#name').val($(this).parent().text().trim());
			$('#id').val($(this).parent().prev().text());
			var html = '<option value="-1" selected="selected">请选择</option>';
			//生成区html
			$.each(zNodes,function(i,item){
				$.each(item.regionList,function(i,item){//省
					$.each(item.regionList,function(i,item){//市
						$.each(item.regionList,function(i,item){//区
							if(item.pId==areaPid){
								html+='<option value="'+item.id+'">'+item.name+'</option>';
							}
						});
					});
				});
			});
			$('#regionsPId3').html(html);
			$('#regionsPId3').val(areaId);
			//生成市html
			var areaPidPid = '';//省id
			html = '<option value="-1" selected="selected">请选择</option>';
			$.each(zNodes,function(i,item){
				$.each(item.regionList,function(i,item){//省
					$.each(item.regionList,function(i,item){//市
						if(item.id == areaPid){
							areaPidPid = item.pId;
							return;
						}
					});
				});
			});
            $.each(zNodes,function(i,item){
            	$.each(item.regionList,function(i,item){//省
            		$.each(item.regionList,function(i,item){//市
            			if(item.pId == areaPidPid){
            				html+='<option value="'+item.id+'">'+item.name+'</option>';
            			}
            		});
            	});
            });
            $('#regionsPId2').html(html);
			$('#regionsPId2').val(areaPid);
			//生成省html
			html = '<option value="-1" selected="selected">请选择</option>';
			$.each(zNodes,function(i,item){
				$.each(item.regionList,function(i,item){//省
					html+='<option value="'+item.id+'">'+item.name+'</option>';
				});
			});
			$('#regionsPId1').html(html);
			$('#regionsPId1').val(areaPidPid);

			//选中配置方案
			var appCfgId = $(this).parent('td').parent('tr').attr('data-appconfigid');
			$('#appCfgId').val(appCfgId);

			$SpGroup.show();
		});


		//上下架功能
		$('#spGroupTbody').on('click','.J_up',function(){
			var id = $(this).parent().parent().children().eq(0).html();
			layer.confirm('确定上架定格?',{},function(){
				$.ajax({
					type : "post",
					url : '${root}/Customer/SpGroup/upSpGroup.do',
					dataType : "json",
					data : {'id':id},
					success : function(data) {
						if (data.success) {
							layer.msg('上架定格成功!',{
								time:1000
							},function(){
							 location.reload();
							});
						} else {
							layer.msg('上架定格失败!');
							console.log(data.message);
						}
					},
					error : function(data) {
					}
				});
			},function(){
			});
		});
		$('#spGroupTbody').on('click','.J_down',function(){
			var id = $(this).parent().parent().children().eq(0).html();
			layer.confirm('确定下架定格?',{},function(){
				$.ajax({
					type : "post",
					url : '${root}/Customer/SpGroup/deleteSpGroup.do',
					dataType : "json",
					data : {'id':id},
					success : function(data) {
						if (data.success) {
							layer.msg('下架定格成功!',{
								time:1000
							},function(){
							 location.reload();
							});
						} else {
							layer.msg('下架定格失败!');
							console.log(data.message);
						}
					},
					error : function(data) {
					}
				});
			},function(){
			});
		});
	});

</script>
</body>
</html>
