$(document).ready(function() {
	//触发用户信息加载（载入页面时加载）
	 $('#suggestionName').bind("input.autocomplete", function () {
		 //alert($('#suggestionName').val());
         $(this).trigger('keydown.autocomplete');
     });
	
	  $("#suggestionName").autocomplete(ctx+'/sys/user/getUserDynamicList', {
          matchContains: true,
          minChars: 1,
          extraParams: {
        	  userName:function(){return $('#suggestionName').val();},
        	  deptId:function(){return $('#deptIdId').val();}
          }, 
          /*width: 200,
          max: 20,
          scrollHeight: 300,
          matchCase: false,*/
          dataType: "json",//数据格式  
          mustMatch:false,
          parse: function(data) {
              return $.map(data, function(row) {
                  return {
                      data: row,
                      value: row.userId,
                      result: row.userName
                  }
              });
          },
          formatItem: function(item) {
             return "<font color=green>" + item.userName + "</font>";//提示的内容显示内容及格式设置
          }
      }).result(function(e, item) {
    	  //console.log(item);
    	  $("#spMobileId").text(item.mobile);
    	  $("#salesmanId").val(item.userId);
    	  
      });
});


function bindShopFun(){
	
	var shopIds = "";
	var userObj = new Array();
	var chkObj = $("#contentTable",window.parent.document).find("input[type=checkbox]:checked");
	//检查是否有选择的客户
	if($(chkObj).length<=0){
		$.jBox.tip("请选择要绑定的客户！","校验失败","error");
		return;
	}
	
	var salesmanId = $("#salesmanId").val();
	if(salesmanId == null || salesmanId == ""){
		$.jBox.tip("请选择正确的业务员！","校验失败","error");
		return;
	}
	
	//收集客户shopId
	$(chkObj).each(function(i){
		shopIds+=$(this).val()+",";
		
		var salesman = $(this).parent().parent().find("td").eq(5).text();
		if(salesman !=null && $.trim(salesman) !=""){
			userObj.push(salesman);
		}
	});  
	
	//解决layer按照get提交参数超长的问题，将值收集放入隐藏域中，等layer打开页面后，再回去父页面隐藏域中的值
	$("#shopIds").val(shopIds);
	
	if(userObj.length>0){
		$.jBox.confirm("存在已分配的客户，您确定要重新分配吗？",'系统提示',function(v,h,f){
			if(v =='ok'){
				//提交批量绑定方法
				bacthBindShop();
			}
		});
	}else{
		//提交批量绑定方法
		bacthBindShop();
	}
}

/**
 * 提交批量绑定方法
 */
function bacthBindShop(){
	 //提交表单
	 loading('正在提交，请稍等...');
	 $.ajax({
			type: "post",
			url: ctx+"/shop/shop/bacthBindShop",
			data: $('#inputForm').serialize(),
			dataType: "json",
			async: false,
			success: function(data) {
			 closeTip();//隐藏遮罩 
			 if(data.success) {
					$.jBox.tip("批量绑定客户信息成功！","操作成功","success");
					
					setTimeout("finishFun()",2000);
					
			 }else{
				$.jBox.tip("批量绑定客户信息失败！","操作失败","error");
			 }
		}
	});
}

//完成处理逻辑函数
function finishFun(){
	//点击刷新父页面
	$("#searchForm",window.parent.document).submit();
	//关闭弹出窗口
	closeLayer();
}

