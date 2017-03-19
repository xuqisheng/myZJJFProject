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
<script src="${root}/resources/js/comm.js"></script>
</head>
<body>
<div class="wrap-bd">
	<div>
		<div>
			定格管理 &gt; <span class="bule1" id="relevance">关联批发商</span>
			<div class="fr">
				定格名称：<b>${spGroupVo.name}</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				定格编号：<b>${spGroupVo.id}</b>
			</div>
		</div>
        <div class="mt-small">
			<span class="pills pills-active">组内</span>
			<span class="pills">组外</span>
			<input type="hidden" id="status" value="${status}"/>
		</div>
		<form action="#" id="searchForm" method="post" class="mb-small">
			<input class="input input-search-text" type="text" name="name" value="${searchName}" id="searchName" placeholder="编码/名称/联系人/手机号码/固定号码" />
			<input type="hidden" name="id" value="${spGroupVo.id}">
			<input class="input input-search-button" value="搜索" type="submit" id="search">
		</form>
	</div>
	<div class="mt-small">
		<table class="table-list table-border">
			<thead class="table-thead">
			<tr>
				<th width="50">选择</th>
				<th>批发商编码</th>
				<th>批发商全称</th>
				<th>联系人</th>
				<th>手机号码</th>
				<th>固定号码</th>
				<th>开户银行卡号</th>
				<th>开户银行名称</th>
				<th>店面地址</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody class="table-tbody">
			<c:forEach items="${spGroupVo.supplierList}" var="su">
			<tr>
				<td><input type="checkbox" name="chk_list" value="${su.id}" /></td>
				<td>${su.supplierCode}</td>
				<td>${su.supplierName}</td>
				<td>${su.managerName}</td>
				<td>${su.mobile}</td>
				<td>${su.callNum}</td>
				<td>${su.bankNum}</td>
				<td>${su.bankName}</td>
				<td>${su.supplierAddress}</td>
				<c:if test="${status == 1}">
				<td class="bule1"><a href="${root}/Customer/SpGroup/removeSupplier.do?id=${spGroupVo.id}&suId=${su.id}">删除</a></td>
				</c:if>
				<c:if test="${status == 2}">
				<td class="bule1"><a href="${root}/Customer/SpGroup/addSupplier.do?id=${spGroupVo.id}&suId=${su.id}">添加</a></td>
				</c:if>
			</tr>
			</c:forEach>
			<tr style="background:#f0f0f0">
				<td>
					<input type="checkbox" name="chk_all" class="J_chkAll" />&nbsp;全选
				</td>
				<td>
				  <c:if test="${status==1}">
				  <span class="bule1" ><a href="#" id="batchDelete">批量删除</a></span>
				  </c:if>
				  <c:if test="${status==2}">
				  <span class="bule1"><a href="#" id="batchAdd">批量添加</a></span>
				  </c:if>	
				</td>
				<td colspan="8">&nbsp;</td>
			</tr>
			</tbody>
		</table>
    </div>
    <c:if test="${fn:length(spGroupList)>0}">
		  <%@ include file="../common/pagination-kk.jsp"%>
    </c:if>
</div>
<script>
	$(function(){
        selectAll('.J_chkAll', 'input[name="chk_list"]');
		
		//批量添加功能
		$("#batchAdd").click(function(e){
			e.preventDefault();
			var chkList="";	
			if($("input[name='chk_list']").size()==0){
				return ;
               }else{
               	$("input[name='chk_list']").each(function(){
                          if(true == $(this).prop("checked")){
                          	chkList = chkList + $(this).attr("value")+",";
                          }		
  					});
               	if(chkList==""){
  						alert("请选择要添加的批发商");
  						return;
  					}
               	$.ajax({
  						type : "post",
  						url : "${root}/Customer/SpGroup/batchAddSupplier.do?id=${spGroupVo.id}",
  						dataType : "json",
  						data :{chkList:chkList},
  						success : function(data) {
  							if (data.success) {
  								location.href = "${root}/Customer/SpGroup/getSupplierOutList.do?id="+data.message;
  							} else {
  								alert(data.message);
  							}
  						},
  						error : function(data) {
  						}
  					}); 
               }
		});
		
		//批量删除功能 
		$("#batchDelete").click(function(e){
			e.preventDefault();
                  var chkList="";					
			//如果列表没有值,点击按钮不反应
                  if($("input[name='chk_list']").size()==0){
			return ;
                  }else{
                  	//如果列表有值,但没选择复选框,提示选择
  					$("input[name='chk_list']").each(function(){
                          if(true == $(this).prop("checked")){
                          	chkList = chkList + $(this).attr("value")+",";
                          }		
  					});
  					if(chkList==""){
  						alert("请选择要删除的批发商");
  						return;
  					}
  					$.ajax({
  						type : "post",
  						url : "${root}/Customer/SpGroup/batchRemoveSupplier.do?id=${spGroupVo.id}",
  						dataType : "json",
  						data :{chkList:chkList},
  						success : function(data) {
  							if (data.success) {
  								location.href = "${root}/Customer/SpGroup/toSupplierList.do?id="+data.message;
  							} else {
  								alert(data.message);
  							}
  						},
  						error : function(data) {
  						}
  					}); 
                  }
		});
		
		$(".pills").click(function(){
			if($(this).html()=="组内"){
				location.href="${root}/Customer/SpGroup/toSupplierList.do?id=${spGroupVo.id}";
			}
			if($(this).html()=="组外"){
				location.href="${root}/Customer/SpGroup/getSupplierOutList.do?id=${spGroupVo.id}";
			}
		});
		
		//前台样式显示控制
		if($("#status").val()==1){
			$(".pills").each(function(){
				if($(this).html()=="组内"){
				  $('.pills').removeClass('pills-active');
                   $(this).addClass("pills-active");						
				}
			 });					
		}else if($("#status").val()==2){
			$(".pills").each(function(){
				if($(this).html()=="组外"){
				  $('.pills').removeClass('pills-active');
                   $(this).addClass("pills-active");						
				}
			 });
		};
		
		//搜索
		$("#search").click(function(e){
			e.preventDefault();
			if($("#status").val()==1){
				$("#searchForm").attr("action","${root}/Customer/SpGroup/toSupplierList.do?pageIndex=1");
			}else if($("#status").val()==2){
				$("#searchForm").attr("action","${root}/Customer/SpGroup/getSupplierOutList.do?pageIndex=1");
			}
			$("#searchForm").submit();
		});
		
	});

	function go() {
		location.href = "/CornerV2/SpGroup/toSupplierList.do?pageIndex=" + $("#kkpager_btn_go_input").val().trim();
	};
</script>
</body>
</html>