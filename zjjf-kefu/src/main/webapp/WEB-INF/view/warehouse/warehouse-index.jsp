<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>仓库管理 - 物流仓库列表</title>
	<%@ include file="../common/head.jsp"%>
</head>
<body>
<div class="wrap-bd">
    <div>
        <a class="crumb" href="${root}/scms/warehouse/listPage.do">物流仓库管理</a><a class="crumb crumb-active">列表</a>
    </div>
    <div class="mt-default mb-small clearfix">
        <div class="fl">
               <label>地区：</label>
               <select class="select" id="regionsPId1" name="province" >
               <option value=""  >请选择</option>
               	<c:forEach items="${regions}" varStatus="i" var="region" >
                       <option value="${region.id}">${region.name}</option> 
				</c:forEach>
               </select>
               <select class="select" id="regionsPId2" name="city" >
                   <option value="" >请选择</option>
                   <c:forEach items="${city}" varStatus="i" var="region" >
                       <option value="${region.id}">${region.name}</option> 
				</c:forEach>
               </select>
               <select class="select"  id="regionsPId3" name="county" >
                   <option value="" >请选择</option>
                   <c:forEach items="${county}" varStatus="i" var="region" >
                       <option value="${region.id}">${region.name}</option> 
				</c:forEach>
               </select>
               <input type="text" id="name" class="input input-search-text ml-default" placeholder="请输入仓库名称" value="${condition.name}">
         		<input type="button" class="input input-search-button ml-default" value="搜索" onclick="search()">
        </div>
        <a class="button button-default fr" href="add.do">新增仓库</a>
    </div>
    <table class="table-list">
        <thead>
        <tr>
            <th width="120" class="ta-l">编号</th>
            <th>仓库名称</th>
            <th>地区</th>
            <th>地址</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        	<c:forEach items="${list}" varStatus="i" var="item">
            <tr>
	            <td class="ta-l">${item.houseCode}</td>
	            <td>${item.name}</td>
	            <td>${item.name1}--${item.name2}--${item.name3}</td>
	            <td>${item.houseAddress}</td>
	            <td>
	                <a href="${root}/scms/warehouse/editObject.do?id=${item.id}" class="icon-op icon-op-edit" title="编辑"></a>
	                <a href="javascript:void(0)" class="icon-op icon-op-del" onclick="deletel('${item.id}')" title="删除"></a>
                </td>
            </tr>
        	</c:forEach>
        </tbody>
    </table>
    <c:if test="${fn:length(list)>0}">
		  <%@ include file="../common/pagination-kk.jsp"%>
    </c:if>
</div>
<script type="text/javascript">
$(function(){
    $(document).keypress(function(event){  
        var keycode = (event.keyCode ? event.keyCode : event.which);  
        if(keycode == '13'){  
        	search();
        }  
    });  
	$('#regionsPId1').on('change' , function(){
		$.post("${root}/kefu/public/findRegionsByPId.do",{pId:$('#regionsPId1').val()},function(data){
			var html = '<option value="">请选择</option>';
			$('#regionsPId3').html(html);
			if(data.success){
				$.each(data.message,function(i,scmsGroup){
					html+='<option value="'+scmsGroup.id+'">'+scmsGroup.name+'</option>'
				})
			}
			$('#regionsPId2').html(html);
		},'json');
	});
	$('#regionsPId2').on('change' , function(){
		$.post("${root}/kefu/public/findRegionsByPId.do",{pId:$('#regionsPId2').val()},function(data){
			var html = '<option value="">请选择</option>';
			if(data.success){
				$.each(data.message,function(i,scmsGroup){
					html+='<option value="'+scmsGroup.id+'">'+scmsGroup.name+'</option>'
				})
			}
			$('#regionsPId3').html(html);
		},'json');
	});
});

function deletel(id){
	if(confirm("确认删除吗")){
		$.post("${root}/scms/warehouse/deleteObject.do",{"id":id},function(data){
			layer.msg(data.message,function(){
				if(data.success){
					 window.location.reload();
				}
			});
			
			
		},'json');
	}
	
}

function search(){
	var name= $.trim($('#name').val());
	var name1= $('#regionsPId1').val();
	var name2= $('#regionsPId2').val();
	var name3= $('#regionsPId3').val();
	var url="${root}/scms/warehouse/listPage.do?1=1";
	if(name != null &&name != undefined && name != ''){
		   url=url+"&name="+name;
	   }
	if(name1 != null &&name1 != undefined && name1 != ''){
		   url=url+"&province="+name1;
	   }
	if(name2 != null &&name2 != undefined && name2 != ''){
		   url=url+"&city="+name2;
	   }
	if(name3 != null &&name3 != undefined && name3 != ''){
		   url=url+"&county="+name3;
	   }
	location.href=url;
}

var pro="${condition.province}";
var city="${condition.city}";
var co="${condition.county}";

$('#regionsPId1').val(pro);
$('#regionsPId2').val(city);
$('#regionsPId3').val(co);
</script>
</body>
</html>