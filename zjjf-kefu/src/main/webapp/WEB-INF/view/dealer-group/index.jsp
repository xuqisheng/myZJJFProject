<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>经销区域管理</title>
	<%@ include file="../common/head.jsp"%>
	<script src="${root}/resources/js/comm.js"></script>
</head>
<body class="wrap-bd">
<div class="mb-small">
    <a class="crumb" href="${root}/scms/distributionArea/listPage.do">经销区域管理</a><a class="crumb crumb-active">经销区域管理</a>
</div>
<div class="mb-small clearfix">
    <div class="fr">
        <button class="button button-default" id="J_addArea">新增区域</button>
        <button class="button button-default" id="J_addCat">新增分类</button>
    </div>
</div>
<div class="clearfix">
    <div class="fl" style="width: 19%;">
    	<div onclick="getRadio()" style="padding: 10px;margin-bottom:1px;background:#f5f5f5;cursor:pointer">全部</div>
        <c:forEach items="${groupMapKeys}" varStatus="i" var="item" >
        <div onclick="getRadio(${item.id})" style="padding: 10px;margin-bottom:1px;background:#f5f5f5;cursor:pointer">
        	${item.name}
            <span class="fr">
                <i class="mr-small icon-op icon-op-edit J_editFenLei" data-value="${item.id}" title="编辑"></i>
                <i class="icon-op icon-op-del J_delFenLei" data-value="${item.id}" title="删除"></i>
            </span>
        </div>
        </c:forEach>        
    </div>
    <table class="table-list table-border fr" style="width: 80%;">
        <thead class="table-thead">
        <tr>
            <th width="160">编号</th>
            <th>区域名称</th>
            <th>分类</th>
            <th width="120">操作</th>
        </tr>
        </thead>
        <tbody class="table-tbody">
            <c:forEach items="${list}" varStatus="i" var="item" > 
         	<tr>	
            	<td><c:if test="${page.pageIndex>=1}">${i.index+1+(page.pageIndex-1)*10}</c:if><c:if test="${page.pageIndex==null}">${i.index+1}</c:if></td>
            	<td>${item.name}</td>
            	<td><c:if test="${item.areaName==null}">全部</c:if><c:if test="${item.areaName!=null}">${item.areaName}</c:if>
            	</td>
            	<td>
                    <span onclick="edit(${item.id})" class="icon-op icon-op-edit" title="编辑"></span>
                    <span onclick="deleteObject(${item.id})" class="icon-op icon-op-del" title="删除"></span>
	            </td> 
            </tr>  
            </c:forEach>  
        </tbody>
        <tfoot>
        	<tr>
        	<td colspan="4">
				<c:if test="${fn:length(list)>0}">
					<%@ include file="../common/pagination-kk.jsp"%>
			    </c:if>
			</td>
        	</tr>
        </tfoot>
    </table>
</div>
<div class="dialog hidden J_dialog" id="J_dialogArea">
    <form id="formArea">
    <div class="dialog-head">区域</div>
    <div class="dialog-body">
        <p class="required">
            <label class="label">区域名称：</label>
            <input type="text" class="input input-default" id="name">
            <input type="hidden" id="AreaId" value=""/>
        </p>
        <p>
            <label class="label">所属分类：</label>
            <select class="select" id="upId">
            	<option value="">--请选择--</option>
            	<option value="-1">全部</option>
            	 <c:forEach items="${groupMapKeys}" varStatus="i" var="item" > 
         				 <option value="${item.id}">${item.name}</option>
         		</c:forEach> 
                
            </select>
        </p>
        <p>
            <label class="label">所属仓库：</label>
            <select class="select" id="warehouseId">
            	<option value="">--请选择--</option>
            	 <c:forEach items="${warehouse}" varStatus="i" var="item" > 
         				 <option value="${item.id}">${item.name}</option>
         		</c:forEach> 
            </select>
        </p>
    </div>
    <div class="dialog-foot">
        <input type="button" class="dialog-button dialog-ok" id="sure" value="保存">
        <input type="button" class="dialog-button dialog-cancel" value="取消">
    </div>
    </form>
</div>
<div class="dialog hidden J_dialog" id="J_dialogCat">
    <form id="formCat">
    <input type="hidden" id="formCatId"/>
    <div class="dialog-head">分类</div>
    <div class="dialog-body">
        <p class="required">
            <label class="label">分类名称：</label>
            <input type="text" class="input input-default" id="areaName">
        </p>
    </div>
    <div class="dialog-foot">
        <input type="button" class="dialog-button dialog-ok" value="保存" id="sure1">
        <input type="button" class="dialog-button dialog-cancel" value="取消">
    </div>
    </form>
</div>
<div class="cover-all"></div>
<input type="hidden" value="${condition.upId}" id="upIdAreaId"/> 
<script>

function edit(id){
	$.post("${root}/scms/distributionArea/findById.do", {"id":id}, function (data) { 
		$("#name").val(data.message.name);
		$("#upId").val(data.message.upId);
		$("#AreaId").val(data.message.id);
		$("#warehouseId").val(data.message.warehouseId);
		$('#J_dialogArea, .cover-all').fadeIn();       
     	},"json");
}

	function deleteObject(id){
		
		if(confirm("确认删除吗")){
			$.post("${root}/scms/distributionArea/deleteArea.do", {"id":id}, function (data) { 
				layer.msg(data.message , function(){
				    window.location.reload() ;
				}); 
			
	         	},"json");
			}
		
	}

	function getParameter(name) { 
		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); 
		var r = window.location.search.substr(1).match(reg); 
		if (r!=null) return unescape(r[2]); return null;
	}
	
	function getRadio(id){
		var url ="${root}/scms/distributionArea/listPage.do";
		
	   if(id != null &&id != undefined && id != ''){
		   url=url+"?upId="+id;
	   }
	   window.location.href=url;
	}

    $(function() {
        $('.J_editFenLei').on('click', function (e) {
        	//修改
       	$.post("${root}/scms/distributionArea/findById.do", {"id":$(this).attr("data-value")}, function (data) { 
        		$("#areaName").val(data.message.name);
        		$("#formCatId").val(data.message.id);
        		 $('#J_dialogCat, .cover-all').fadeIn();
             },"json");
   			e.stopPropagation();
        });
        $('.J_delFenLei').on('click', function (e) {
        	//删除分类
        	if(confirm("确认删除吗")){
        		$.post("${root}/scms/distributionArea/deletecategory.do", {"id":$(this).attr("data-value")}, function (data) { 
                 	layer.msg(data.message,function(){
                 		window.location.href="${root}/scms/distributionArea/listPage.do";
                 	});
                 	
                 	},"json");
        	}
   			e.stopPropagation();
        });
        
        dialogPosCenter('#J_dialogArea');
        dialogPosCenter('#J_dialogCat');
        $('#J_addArea').on('click', function () {
            $('#J_dialogArea, .cover-all').fadeIn();
        });
        $('#J_addCat').on('click', function () {
            $('#J_dialogCat, .cover-all').fadeIn();
        });
        $('.J_dialog').on('click', '.dialog-cancel', function () {
        	$('#formArea')[0].reset();
        	$('#formCat')[0].reset();
        	$('#AreaId').val('');
        	$('#formCatId').val('');
        	$("#sure").removeAttr("disabled");
         	$("#sure1").removeAttr("disabled");
            $('.J_dialog, .cover-all').fadeOut();
            
           
        });
        
        //保存 区域
        $('#J_dialogArea').on('click', '.dialog-ok', function() {
        	var name=$.trim($("#name").val());
            if(name==""){layer.msg("区域名不能为空"); $("#name").focus();return;}
            if(name.length>25){layer.msg("区域名称必须在25个字以内"); $("#name").focus();return;}
            var upId=$.trim($("#upId").val());
            if(upId==""){layer.msg("请选择所属分类");return;}
            var warehouseId=$.trim($("#warehouseId").val());
            if(warehouseId==""){layer.msg("请选择仓库");return;}
        	var data;
        	var id=$.trim($("#AreaId").val());
        	var url;
        	if(id==""){
        		url="${root}/scms/distributionArea/addArea.do";
        		data={"name":name,"upId":upId,"warehouseId":warehouseId};
        	}else{
        		url="${root}/scms/distributionArea/update.do";
        		data={"name":name,"upId":upId,"id":id,"warehouseId":warehouseId};
        	}
        	 $(this).attr("disabled", "disabled");
        	 $.post(url, data, function (data) { 
             	$("#sure").removeAttr("disabled");
             	$('#formArea')[0].reset();
             	$('.J_dialog, .cover-all').fadeOut();
             	layer.msg(data.message,function(){
             		window.location.reload() ;
             	});
             	$("#sure1").removeAttr("disabled");
             	},"json");
        	
        });
        
        //新增分类
        $('#J_dialogCat').on('click', '.dialog-ok', function() {
        	var name=$.trim($("#areaName").val());
        	var id=$.trim($("#formCatId").val());
            if(name==""){layer.msg("分类名不能为空"); $("#areaName").focus();return;}
            if(name.length>25){layer.msg("分类名称必须在25个字以内"); $("#name").focus();return;}
            $(this).attr("disabled", "disabled");
            if(id==""){ //新增
            	var url="${root}/scms/distributionArea/addCategory.do";
            	var data={"name":name};
            }else{//更新
            	var url="${root}/scms/distributionArea/updateCategory.do";
            	var data={"name":name,"id":id};
            }
            $.post(url,data , function (data) { 
             	//
             	$("#sure").removeAttr("disabled");
             	$("#sure1").removeAttr("disabled");
             	$('#formCat')[0].reset();
             	$('.J_dialog, .cover-all').fadeOut();
             	layer.msg(data.message,function(){
             		window.location.reload() ;
             	});
            	
             	},"json");
        });
    });
</script>
</body>
</html>