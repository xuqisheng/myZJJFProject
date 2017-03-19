$(document).ready(function() {
	// 在这里写你的代码...
	$("#inputForm").validate({
		submitHandler: function(form){
			loading('正在提交，请稍等...');
			form.submit();
		},
		errorContainer: "#messageBox",
		errorPlacement: function(error, element) {
			$("#messageBox").text("输入有误，请先更正。");
			if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
				error.appendTo(element.parent().parent());
			} else {
				error.insertAfter(element);
			}
		}
	}); 
	
	$("#addLineBtn").bind("click", function(){
		var bakLineHtml = $("#bakDiv").html();
	    $("#lineContainer").append(bakLineHtml);
	});
	
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
//              return item.userName;
             return "<font color=green>" + item.userName + "</font>";//提示的内容显示内容及格式设置
              //return "<table id='banlicc8612'><tr><td align='left'>" + item.name + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td align='right'><font style='color: #009933; font-family: 黑体; font-style: italic'>" + item.loginName + "</font></td></tr></table>";
          }
      }).result(function(e, item) {
    	  //console.log(item);
    	  $("#salesmanId").val(item.userId);
    	  queryLinePlans();
      });
});

function delLineData(obj){
// 	alert($(obj).parent().parent().html());
	if(confirm("您确定要删除该路线吗?")) {
		 var lineId = $(obj).parent().parent().find("input[name=lineId]").val();
		 //alert("lineId="+lineId);
		 //提交表单
		 loading('正在提交，请稍等...');
		 $.ajax({
				type: "post",
				url: ctx+"/shop/linePlans/deleteShopLineByLineId",
				data: "lineId="+lineId,
				dataType: "json",
				async: false,
				success: function(data) {
				 closeTip();//隐藏遮罩 
				 if(data.success) {
						$.jBox.tip(data.msg,"操作成功","success");
						$(obj).parent().parent().remove();
				
					}else{
						$.jBox.tip(data.msg,"操作失败","error");
					} 
				}
		});
	}
}

function queryLinePlans(){
	
	var salesmanId = $("#salesmanId").val();
	var salesmanName = $("#suggestionName").val();
	
	var deptId = $("#deptIdId").val();
	var deptName = $("#deptIdName").val();
	
	//alert("0000000000==="+salesmanId+",deptId="+deptId+",deptName="+deptName+",salesmanName="+salesmanName);
	/*$("#userId").val(salesmanId);
	 $.ajax({
			type: "post",
			url: ctx+"/shop/linePlans/queryLinePlans",
			data: $('#inputForm').serialize(),
			dataType: "json",
			async: false
	});*/
	window.location.href=ctx+"/shop/linePlans/queryLinePlans?salesmanId="+salesmanId
	+"&userName="+salesmanName+"&deptId="+deptId+"&deptName="+deptName;
}

//打开选择客户列表
function loadCustList(obj){
	/*var shopIds = "";
	$("#lineContainer").find("input[type=checkbox]").each(function(i){
		shopIds+=$(this).val()+",";
	});*/  

	//定位到当前按钮对应的区域
	var htmlObj = $(obj).parent().parent().find(".chkBoxDiv");
	
	var shopIds = "";
	$(htmlObj).find("input[type=checkbox]").each(function(i){
		shopIds+=$(this).val()+",";
	});
	
	//alert("shopIds="+shopIds);
	//解决layer按照get提交参数超长的问题，将值收集放入隐藏域中，等layer打开页面后，再回去父页面隐藏域中的值
	$("#shopIds").val(shopIds);
	
	var timestamp=new Date().getTime();
	var idStr = "line"+timestamp;
	//对触发区域的div进行ID绑定，同时将该ID传到选择列表页面（提供数据回写使用）
	$(htmlObj).attr("id",idStr);
	
	var salesmanId = $("#salesmanId").val();
	
	if(salesmanId == null || salesmanId ==''){
		alert("请先指定业务员！");
		return;
	}
	
    layer.open({
        type: 2,
        title: '',
        shade: false,
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['1300px' , '620px'],
        content: ctx+"/shop/shop/getMyShopList.do?divId="+idStr+"&salesmanId="+salesmanId
        //屏蔽shopIds的原因是拼接数据过多导致get请求超过最大长度失败，因此屏蔽该参数，改用子页面获取父页面的值,如：$("#shopIds", window.parent.document).val();
        +"&shopIds="+shopIds
    }); 
}

//删除客户路线方法
function delLineCust(obj){
	$(obj).parent().parent().find("input[name='chk_list']:checked").each(function() { // 遍历选中的checkbox
		$(this).parent().remove();
	});	
}

function checkLineData(){
	
	var regex = /[^\u4e00-\u9fa5a-zA-Z0-9_-]/ig;
	var obj = $("#lineContainer").find("input[name=lineName]");
	var lineTotal = $(obj).length;
    var errorLineNameIsNull = false, errorLineNameLength = false, errorLineNameRule = false;
	var arryLine = new Array();
	
	
	$(obj).each(function(i){
		var lineName = $(this).val();
		lineName = $.trim(lineName);
		
		if(lineName ==null || lineName ==""){
			errorLineNameIsNull = true;
		}
		
		if(lineName.length>10){
			errorLineNameLength = true;
		}
		
		if (regex.test(lineName)){
	        errorLineNameRule = true;
	    }
		
		if (arryLine.indexOf(lineName) == -1){
			arryLine.push(lineName);
		}
		
	});
	
	if(errorLineNameIsNull){
		return "error_line_name_is_null";//线路名为空
	}
	
	if(errorLineNameLength){
		return "error_line_name_overlength";//线路名称超长
	}
	
	if(errorLineNameRule){
		return "error_line_name_rule";//命名规则错误
	}
	
	if(arryLine.length < lineTotal){
		//当名称在数组中去重后跟路线总数比对，如果小于总的路线，则说明重复命名
		return "error_line_name_redo";
	}
	
	if(lineTotal == 0){
		//当名称在数组中去重后跟路线总数比对，如果小于总的路线，则说明重复命名
		return "error_line_required";
	}
	
}

//初始化线路数据
function checkLineShopIsNull(){
	var bool = false;
	$("#lineContainer").find("div.mark").each(function() { 
		 var chkNum = $(this).find(".chkBoxDiv input[type=checkbox]").length;
		 if(chkNum == 0){
			 bool = true;
		 }
	});
	return bool;
}

//初始化线路数据
function initLineData(){
	
	var lineDataAll = ""; 
	
	$("#lineContainer").find("div.mark").each(function() { 
		 //alert($(this).html());
		 var lineVal = $(this).find("input[name=lineName]").val();
		 var lineId = $(this).find("input[name=lineId]").val();
		 
		 //alert("lineName="+lineVal+", lineId="+lineId);
		 
		 var shopIdVal = "";
		 $(this).find(".chkBoxDiv input[type=checkbox]").each(function(){
		    if(shopIdVal != null && shopIdVal !=""){
		    	shopIdVal=shopIdVal+","+$(this).val();
		    }else{
		    	shopIdVal=$(this).val();
		    }
		});  
		 
		//将所以路线客户都放到lineDataAll中存储
		if(lineId != null && lineId !="" && lineId != "undefined"){
			lineDataAll= lineDataAll+lineVal+","+lineId+"##"+shopIdVal+"@@";
		}else{
			lineDataAll= lineDataAll+lineVal+"##"+shopIdVal+"@@";
		}
		
	});
	//将汇总信息设置到表单属性中
	$("#lineStr").val(lineDataAll);
}

/**
 * 保存路线规划函数
 */
function savaLinePlans(){
	var deptId = $("#deptIdId").val();
	var salesmanId = $("#salesmanId").val();
	
	 if(deptId == null || deptId ==""){
		 alert("部门不能为空！");
		 return;
	 }
	 if(salesmanId == null || salesmanId ==""){
		 alert("业务员不能为空！");
		 return;
	 }else{
		//将业务员ID设置到userId属性中
		$("#userId").val(salesmanId);
	 }
	
     //保存前校验
 	 var status = checkLineData();
	 if(status == "error_line_name_is_null"){
		alert("所有的线路名称不能为空!");
		return;
	 }else if(status == "error_line_name_overlength"){
		alert("线路名称长度不能超过10个字符!");
		return;
	 }else if(status == "error_line_name_rule"){
		alert("线路名称只能包含中英文、数字及下划线等字符！");
		return;
	 }else if(status == "error_line_name_redo"){
		alert("线路名称不能重复!");
		return;
	 }else if(status == "error_line_required"){
		alert("保存时必须添加一条线路!");
		return;
	 }
	 
	var boolShopIsNull = checkLineShopIsNull();
	if(boolShopIsNull){
		alert("注意：线路必须分配有客户数据才能保存！");
		return;
	}
	 
	 //初始化线路数据
	 initLineData();
	 //提交表单
	 loading('正在提交，请稍等...');
	 $.ajax({
			type: "post",
			url: ctx+"/shop/linePlans/saveLinePlans",
			data: $('#inputForm').serialize(),
			dataType: "json",
			async: false,
			success: function(data) {
			 closeTip();//隐藏遮罩 
			 if(data.success) {
					$.jBox.tip("路线规划保存成功！","操作成功","success");
					//保存成功后时隔500毫秒刷新页面
					setTimeout(function () {queryLinePlans();}, 500);
			
				}else{
					$.jBox.tip("路线规划保存失败！","操作失败","error");
				} 
			}
	});
}

function refreshLinePlans(){
	$.post(ctx+"/shop/linePlans/queryLinePlans", $("#inputForm").serialize());
}


