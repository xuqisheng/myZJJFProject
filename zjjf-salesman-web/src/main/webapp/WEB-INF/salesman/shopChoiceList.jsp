<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>阿街足迹</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <%@ include file="../common/head.jsp"%>
</head>
<body>
<div class="wrap-bd">
	<div class="mb-default title">客户列表</div>
	<div class="op-section clearfix">
 		<!-- 父页面div标识 -->
		<input type="hidden" id="divId" value="${divId}"/>
		<form action="" id="shopForm" method="" class="fl">
			<input type="hidden" name="provinceId" value="${shopVO.provinceId}"/>
			<input type="hidden" name="cityId" value="${shopVO.cityId}"/>
			<input type="hidden" name="areaId" value="${shopVO.areaId}"/>
			<input type="hidden" name="spGroupId" value="${shopVO.spGroupId}"/>
			<input type="hidden" name="shopIds" id="shopIds" />
			
			<input class="input-search-text" type="text" name="shopNo" placeholder="商铺编号/名称">
			<input type="button" class="input-search-button" value="搜索"  id="sub"/>
			<a style="margin-left: 50px;" onclick="selectShop();">添加选中客户</a>
		</form>
	</div>
	<div>
		<table class="table-list table-border">
			<thead class="table-thead">
				<tr class="table-header">
					<th><input type="checkbox" id="chk_all" name="chk_all"></th>
					<th>商铺编号</th>
					<th>商铺名称</th>
					<th>所属区域</th>
					<th>详细地址</th>
					<th>固定电话</th>
					<th>商铺类型</th>
					<th>是否注册</th>
				</tr>
			</thead>
			<tbody class="table-tbody"></tbody>
		</table>
	</div>
	
</div>
<%@ include file="../common/pagination.jsp"%>
<script>
	$(function() {
		 var shopIds= $("#shopIds", window.parent.document).val();
		 //var userIdSet33= $("#shopIds", window.top.frames[0].document).val();
		 $("#shopIds").val(shopIds);
		
		 $("#jpagination").pagination({
             pageSize: 10,
             remote: {
                 url: '${root}/shop/getChoiceList.do',
                 params: $('#shopForm').serializeArray(),
                 success: function(data) {
                     var html='';
                     $.each(data.list, function(i,item) {
                    	html+='<tr><td><input type="checkbox" onclick="setSelectAll();" name="chk_list" value="'+item.shopId+'"></td>';
                     	html+='<td>'+item.shopNo+'</td>';
                     	html+='<td>'+item.shopName+'</td>';
   	                	html+='<td>'+item.area+'</td>';
    	                if(item.shopAddress == null){
    	                	html+='<td></td>';
    	                }else{
    	                	html+='<td>'+item.shopAddress+'</td>';
    	                }
    	                if(item.telephone == null){
    	                	html+='<td></td>';
    	                }else{
    	                	html+='<td>'+item.telephone+'</td>';
    	                }
    	                if(item.shopTypeName == null){
    	                	html+='<td></td>';
    	                }else{
    	                	html+='<td>'+item.shopTypeName+'</td>';
    	                }
    	                
    	                if(item.isRegister == null || item.isRegister == '0'){
    	                	html+='<td>未注册</td>';
    	                }else if(item.isRegister == '1'){
    	                	html+='<td>已注册</td>';
    	                }
    	                html+='</tr>';
    	                
                     });
                     if(html == "") {
                     	html = '<tr><td colspan="10" class="no-data"></td></tr>';
                     }
                     $('.table-tbody').html(html);
                 },
                 totalName:'totalSize',
                 pageParams: function (data) {
                    	return {
                    			pageIndex: data.pageIndex + 1,
                    			pageSize: data.pageSize
                    		}
                 }
             }
         });
		 
		 $('#sub').on('click', function(e) {
        	 $("#jpagination").pagination('setParams', $('#shopForm').serializeArray());
        	 $("#jpagination").pagination('setPageIndex', 0);
        	 $("#jpagination").pagination('remote');
        });
		
	});
	
	function delShopInfo(obj){
		var shopId =  $(obj).data("id");
		 $.ajax({
				type: "post",
				url: "${root}/shop/delShopInfo.do",
				data: "shopId="+$.trim(shopId)+"&isDelete=1",
				dataType: "json",
				async: false,
				success: function(data) {
				 if(data.success) {
						alert("操作成功!");
						location.href="${root}/shop/index.do";
					} else {
						alert(data.message);
					} 
				},
				error : function(data) {
				}
			});
	}
	
	//复选框的事件  
	$("#chk_all").click(function(){
		//alert($("#chk_all").prop("checked"));
		if($("#chk_all").prop("checked")){
			$("input[name='chk_list']").prop("checked",true);  
		}else{
			$("input[name='chk_list']").prop("checked",false);  
		}
		
	});
	//子复选框的事件  
	function setSelectAll(){  
	    //当没有选中某个子复选框时，SelectAll取消选中  
	    if (!$("#chk_list").checked) {  
	        $("#chk_all").prop("checked", false);  
	    }  
	    var chsub = $("input[type='checkbox'][name='chk_list']").length; //获取subcheck的个数  
	    var checkedsub = $("input[type='checkbox'][name='chk_list']:checked").length; //获取选中的subcheck的个数
	    if (checkedsub == chsub) {  
	        $("#chk_all").prop("checked", true);  
	    }  
	} 
	
	//确定选择用户信息
	function selectShop(){	
		
		var chkLen = $("input[name='chk_list']:checked").length;
		if(chkLen == 0){
			alert("请选择店铺信息！");
			return;
		}
		
		var html = "";
		$("input[name='chk_list']:checked").each(function() { // 遍历选中的checkbox
			n = $(this).parents("tr").index();  // 获取checkbox所在行的顺序
			//$(".table-tbody").find("tr:eq("+n+")").remove();
			var trObj = $(".table-tbody").find("tr:eq("+n+")");
			$(trObj).find("td:eq(0)").find("input[type='checkbox']").removeAttr("onclick","");//移除点击事件
			var chkBox = $(trObj).find("td:eq(0)").html();
			var shopName = $(trObj).find("td:eq(2)").html();
			html+='<li style="width: 250px;">'+chkBox+shopName+'</li>';
		});	

		var divId = $("#divId").val();
		//回填父页面的div
		parent.selectShopCallback(divId,html); 
	}
	
</script>
</body>
</html>