<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>商品库 - 商品品牌列表</title>
    <%@ include file="../common/head.jsp"%>
    <script src="${root}/resources/js/comm.js"></script>
</head>
<body class="wrap-bd">
<div class="mb-small clearfix">
    <form class="fl">
        <input class="input input-search-text" type="text" name="noAndName" value="${brandRo.noAndName }" placeholder="品牌商编号/品牌商名称">
        <input class="input input-search-button" value="搜索" type="submit">
    </form>
    <div class="fr"><button class="button button-default" id="J_addAd">新增品牌商</button></div>
</div>
<table class="table-list table-border">
    <thead class="table-thead">
        <tr>
        	<th>ID</th>
            <th>品牌商编号</th>
            <th>品牌商名称</th>
            <th>修改时间</th>
            <th>状态</th>
            <th width="120">操作</th>
        </tr>
    </thead>
    <tbody class="table-tbody">
    <c:forEach var="brand" items="${brandList }">
        <tr>
        	<td>${brand.id }</td>
            <td>${brand.brandNo }</td>
            <td>${brand.name }</td>
            <td><fmt:formatDate value="${brand.updateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <c:if test="${brand.status==0 }">
            	 <td class="txt-warn">已下架</td>
            </c:if>
            <c:if test="${brand.status==1 }">
            	 <td class="txt-success">已上架</td>
            </c:if>
            <td>
                <c:if test="${brand.isDelete==false }">
                	<span class="button button-operate J_edit">编辑<input type="hidden" name="h_id" value="${brand.id }"></span>
                	<span class="button button-operate J_delete">删除<input type="hidden" name="h_id" value="${brand.id }"></span>
                </c:if>
                <c:if test="${brand.isDelete==true }">
                	<span class="button button-operate J_edit">编辑</span>
                	<span class="button button-operate disabled">已删除</span>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="dialog hidden" id="J_dialog">
	<form>
    <div class="dialog-head"></div>
    <div class="dialog-body">
        <p class="required">
            <label class="label">品牌商编号：</label>
            <input type="text" name="brandNo" class="input input-default" id="brandNo" placeholder="" maxlength="10">
            <input type="hidden" name="id" value="" id="id">
            <input type="hidden" name="brandNoA" id="brandNoA">
        </p>
        <p class="required">
            <label class="label">品牌商名称：</label>
            <input type="text" name="name" class="input input-default" id="name" maxlength="25">
        </p>
        <p>
            <label class="label">备注：</label>
            <input type="text" name="remark" class="input input-default" id="remark" maxlength="50">
        </p>
    </div>
    <div class="dialog-foot">
        <input type="button" value="确定" class="dialog-button dialog-ok">
        <input type="button" value="取消" class="dialog-button dialog-cancel">
        <input type="reset" id="J_reset" class="hidden" value="reset">
    </div>
    </form>
</div>
<div class="cover-all"></div>
<script>
    $(function () {
        dialogPosCenter('#J_dialog');
        $('#J_addAd').on('click', function() {
            $('#J_dialog,.cover-all').fadeIn();
            $('#J_dialog .dialog-head').html("新增品牌商");
        });
        $('#J_dialog').on('click', '.dialog-ok', function() {
            //TODO
        }).on('click', '.dialog-cancel', function() {
        	$('#J_reset').trigger('click');
            $('#J_dialog,.cover-all').fadeOut();
        });
        $('.J_edit').on('click', function(){
            var id = $(this).find('input[name=h_id]').val();
            if(id != null && id != ""){
            	$('#J_dialog, .cover-all').fadeIn();
                $('#J_dialog .dialog-head').html("编辑品牌商");
            }
            $.post("${root}/customer/brand/getBrandingById.do",{id:id},function(data){
            	if(data.success){
            		$("#id").val(data.message.id);
            		$("#brandNo").val(data.message.brandNo);
            		$("#brandNoA").val(data.message.brandNo);
            		$("#name").val(data.message.name);
            		$("#remark").val(data.message.remark);
            	}
            },'json');
            //TODO
        });
        $('.J_delete').on('click', function() {
        	if(confirm("确定删除？")){
       		 var id = $(this).find('input[name=h_id]').val();
       		 $.post("${root}/customer/brand/deleteBranding.do",{id:id},function(data){
       			 if(data.success){
       				 alert(data.message)
       				 location.href="${root}/customer/brand/getAllBrandingByParam.do";
       			 }else{
       				 alert(data.message)
       			 }
       		 },'json');
       	}
            //TODO
        });
      	/* $("#brandNo").blur(function(){
        	if($.trim($(this).val())!=$.trim($("#brandNoA").val())&&$.trim($(this).val())!=""){
        		var brandNo = $.trim($(this).val());
        		$.post("${root}/Customer/Brand/chickBrandNo.do",{brandNo:brandNo},function(data){
        			if(data.success){
        				alert(data.message);
        				$("#brandNo").val("");
        				$("#brandNo").focus();
        			}
        		})
        	}
        })  */
        function submit(){
        	var id=$("#id").val();
        	var brandNo = $.trim($("#brandNo").val());
        	var remark = $.trim($("#remark").val())
        	var name=$.trim($("#name").val());
        	var str = /^[^\u4e00-\u9fa5]{0,}$/;
        	if(!str.test(brandNo)){
        		alert("品牌商编号不能为中文");
        		$("#brandNo").focus();
        		return false;
        	}else if(name==""){
        		alert("品牌商名称不能为空！");
        		$("#name").focus();
        		return false;
        	}
        	var url="${root}/customer/brand/";
        	var param=null;
        	if($("#id").val()=="" || $("#id").val()==null){
        		url+="addBranding.do";
        		param = {name:name,brandNo:brandNo,remark:remark};
        	}else{
        		url+="updateBranding.do";
        		param = {id:id,name:name,brandNo:brandNo,remark:remark};
        	}
        	
        	$.post(url,param,function(data){
        		if(data.success){
        			alert(data.message);
        			location.href="${root}/customer/brand/getAllBrandingByParam.do?pageIndex=${page.pageIndex}";
        		}else{
        			alert(data.message);
        		}
        	},'json');
        }
        $(".dialog-ok").click(function(){
        	var id=$("#id").val();
        	var brandNo = $.trim($("#brandNo").val());
        	var remark = $.trim($("#remark").val())
        	var name=$.trim($("#name").val());
        	var str = /^[0-9a-zA-Z]{1,10}$/;
        	if(brandNo==""){
        		alert("品牌商编号不能为空！");
        		$("#brandNo").focus();
        		return false;
        	}else if(!str.test(brandNo)){
        		alert("品牌商编号为10位的数字或字母");
        		$("#brandNo").focus();
        		return false;
        	}
        	var flag = true;
        	//验证编号唯一性
        	if($.trim(brandNo)!=$.trim($("#brandNoA").val())&&$.trim(brandNo)!=""){
        		$.post("${root}/customer/brand/chickBrandNo.do",{brandNo:brandNo},function(data){
        			if(data.success){
        				alert(data.message);
        				$("#brandNo").val("");
        				$("#brandNo").focus();
        				return false;
        			}else{
        				submit();
        			}
        		},'json');
        	}else{
        		submit();
        	}
        	
        }); 
    })
</script>
<c:if test="${fn:length(brandList)>0}">
	<%@ include file="../common/pagination-kk.jsp"%>
</c:if>
</body>
</html>