$(document).ready(function() {
	
	//加载该页面做两个事情(1、获取父页面shopIds到from变得中；2、触发查询事件按照条件查询数据)
	/*var shopIds = $("#shopIds", window.parent.document).val();
	alert(shopIds);
	$("#searchForm").submit();*/
	
});
function page(n,s){
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	$("#searchForm").submit();
	return false;
}

/*function closeLayer() {
	//当你在iframe页面关闭自身时
	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(index); //再执行关闭  
}*/

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
		html+='<label class="checkbox-inline" style="width:250px;">'+$.trim(chkBox+shopName)+'</label>';
	});	

	//回填父页面的div
	var divId = $("#divId").val();
	$("#"+divId, window.parent.document).append(html);
	closeLayer();//关闭弹出窗口
}