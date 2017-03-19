<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>街坊店宝</title>
<%@ include file="../common/head.jsp"%>

<script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
<script src="${root}/resources/vendor/jquery/ajaxfileupload.js"></script>
<style type="text/css">
	#in-area span{
		width: 81.5px;font-size: 12px;text-align: center;display: inline-block;
	}
</style>
</head>
<body class="wrap-bd">
<div class="wrap-bd bg table-border">
    <div>
        <a href="${root}/Customer/SpProduct/getSpGroupAndProduct.do?id=${supplierId}&pageIndex=${pageIndex}">返回列表页面</a>&nbsp;&nbsp;
    </div>
    <div class="txt-info mt-default" id="clickPage">商品预录入</div>
    <form action="" id="good_form">
        <input type="hidden" value="true" name="forPre"/>
        <input type="hidden" id="supplierId" name="supplierId" value="${supplierId}">
        <input type="hidden" id="itemBaseId" name="itemBaseId" value="">
        <input type="hidden" id="spGroupIdVo" name="spGroupIdVo" value="${spGroupId}">
        <div>
            <p>
                <label class="label">任务时间：</label>
                <span id="taskTimeSpan" class="" style="color:red">${taskTime}</span>
                <input id="taskTimeInput" class="input input-default" type="text" onclick="WdatePicker({el:'taskTimeInput',dateFmt:'yyyy-MM-dd HH:mm:ss',startDate:'%y-%M-%d 00:00:00'})" name="beginTime" value="${taskTime}">
                <input id="setTaskTime" type="button" class="button button-white" value="设定时间">
                <span class="txt-log">设定时间大于现有时间就执行任务</span>
            </p>
            <p>
                <label class="label">操作按钮：</label>
                <a href="#clickPage" id="showCurrentPriceBt" class="pills pills-active">显示在线价格</a>&nbsp;&nbsp;
                <a href="#clickPage" id="showPrePriceBt" class="pills">显示预设价格</a>
            </p>
            <p>
                <label class="label">商品条码：</label>
                <input type="text" id="good-brand" name="mdseId" class="input input-default" value="<c:if test="${mdseId !=null }">${mdseId}</c:if>" >
                <span class="txt-warn" id="brand-error"></span>
            </p>
            <p>
                <label class="label">商品分类：</label>
                <input type="hidden" value="" id="listItemHidden" disabled="disabled"><!--存放二级分类字符串  -->
                <input type="hidden" value="" id="listLogsHidden" disabled="disabled"><!--存放一级分类字符串  -->
                <select class="select" name="yiji" id="yiji" disabled="disabled">
                <option value="-1">没有分类</option>
                </select>
                <select class="select" name="erji" id="erji" disabled="disabled">
                <option value="-1">没有分类</option>
                </select>
            </p>
            <p>
                <label class="label">商品名称：</label>
                <input type="text" id="good-name" name="name" class="input input-default" readonly="readonly">
                <span class="txt-warn" id="name-error"></span>
            </p>
            <p>
                <label class="label">商品规格：</label>
                <input type="text" id="good-spec" name="spec" class="input input-default" readonly="readonly">
                <span class="txt-warn" id="spec-error"></span>
            </p>
            <p id="in-area">
                <label class="label">所属定格</label>
                <span>批发商出货价</span>
                <span>批发商进货价</span>
                <span>批发商毛利</span>
                <span style="display: none;">费用</span>
                <span style="display: none;">费用率</span>
                <span>市场价</span>
                <span>商品备注</span>
                <span style="display: none">数量限制</span>
                <span>优惠信息</span>
                <span>商品状态</span>
                <span>仓库</span>
                <span>库存</span>
            </p>
            <div id="priceDiv">
            <%-- <c:forEach items="${spList}" var="item">
            <p id="${item.id }" class="J_area_price">
                <label style="display:inline-block;width:100px;font-size:12px;">${item.name}</label>
                <input type="text" name="${item.id}_areaprice" id="${item.id}_areaprice"
                       onblur="getPlantItemFee(this,${item.id});" class="input input-date J_bitian">
                <input type="text" name="${item.id }_plantDisPrice" id="${item.id }_plantDisPrice"
                       onblur="getPlantItemFee(this,${item.id});" class="input input-date ml-small J_bitian">
                <input type="text" name="${item.id }_grossProfit" id="${item.id }_grossProfit"
                       onblur="getPlantItemFee(this,${item.id});" class="input input-date ml-small J_bitian">
                <input type="text" style="width:100px;background-color:#F0F0F0;" name="${item.id }_costPrice"
                       id="${item.id }_costPrice" onclick="getPlantItemFee(this,${item.id});" readonly="true"
                       class="input input-date ml-small">
                <input type="text" style="width:100px;background-color:#F0F0F0;" name="${item.id }_expenseRatio"
                       id="${item.id }_expenseRatio" onclick="getPlantItemFee(this,${item.id}	);" readonly="true"
                       class="input input-date ml-small">
                <input type="text" name="${item.id }_marketprice" id="${item.id }_marketprice"
                       class="input input-date ml-small J_bitian">
                <input type="text" name="${item.id }_remark" id="${item.id }_remark"
                       class="input input-date ml-small"><!-- 商品备注 -->
                <input type="text" name="${item.id }_restrict" id="${item.id }_restrict"
                       class="input input-date ml-small"><!--数量限制 -->
                <input type="text" name="${item.id }_youHui" id="${item.id }_youHui"
                       class="input input-date ml-small"><!-- 优惠信息 -->
                <select name="${item.id }_status" id="${item.id }_status"
                        class="input input-date ml-small">
                        <c:if test="${item.status==0}">
                          <option value="1">上架</option>
                          <option value="0">下架</option>
                        </c:if>
                        <c:if test="${item.status==1}">
                          <option value="1" selected="selected">上架</option>
                          <option value="0">下架</option>
                        </c:if>
                </select>
            </p>
            </c:forEach> --%>
            </div>
	        <p>
	            <input type="button" id="btn" class="button button-ok" value="创建商品">
	        </p>
        </div>
    </form>
</div>
<script>
var dataPath = "/zjjf-kefu/";
var catelogJson = '';
var groupJson = '';
var warehouseList = '${warehouseList}';
$(function() {

	    var errorMsg = '${errorMsg}';
	    if(errorMsg!=null&&errorMsg!=''){
	    	layer.msg(errorMsg);
	    }

	    //用来获取商品分类
	    loadinfo();

	    if($("#good-brand").val()!=""){//如果是修改商品,则不能修改商品条形码
	        $("#good-brand").attr("readonly","readonly");
	    }

	  //设定时间
	    $("#setTaskTime").on("click",function(){
	        var time=$("#taskTimeInput").val().trim();
	        if(time==""){
	            return ;
	        }else{
	            $.ajax({
	                type : "GET",
	                url : dataPath + "Pc/SystemInfo/updateTaskTime.do",
	                dataType:'json',
	                async : true,
	                data : {
	                    "taskTime" : time
	                },
	                success : function(data) {
	                    if (!data.success) {
	                    	layer.tips('更新时间出错,请联系技术!',
	                    			   '#setTaskTime',
	                    			   {
	                    		         time:2000,
	                    		         tips:[2,'#c00']
	                    		       }
	                    	           );
	                    	console.log(data.message);
	                    } else {
	                    	layer.tips('更新时间成功!',
	                    			   '#setTaskTime',
	                    			   {
	                    		         time:2000,
	                    		         tips:[2,'#0096e0']
	                    		       }
	                    	           );
	                        $("#taskTimeInput").val(time);
	                        $("#taskTimeSpan").html(time);
	                    }
	                },
	                error : function(data) {
	                	layer.tips('更新时间出错,失败的请求!',
                 			   '#setTaskTime',
                 			   {
                 		         time:2000,
                 		         tips:[2,'#c00']
                 		       }
                 	           );
	                }
	            });
	        }
	    });

	    //依据商品条码查询预设表,回显数据
	    $("#good-brand").on("blur",checkPlantIsExist);//查找在线数据

	    $("#showCurrentPriceBt").on("click",checkPlantIsExist);//查找在线数据

	    $("#showPrePriceBt").on("click",checkPrePlantIsExist);//查找预设表数据

	    $("#showCurrentPriceBt").toggleClass("pills-active");

	    if($("#good-brand").val()!=''){
	        $("#good-brand").trigger("blur");
	    }


	    //提交表单
	    $("#btn").off("click").on("click", function() {
	        var st = $(".txt-warn").text();
	        if (st.length > 0) {
	        	layer.msg('请填写正确信息再提交!');
	            return;
	        }
	        if ($("#good-brand").val().trim() == "") {
	            $("#brand-error").html("商品条码不能为空");
	            return;
	        }

	        var isEdit = '${isEdit}';

	        var jsonObject = {};
	        var jsonArr = [];
	        $('#priceDiv .J_area_price').each(function(){
	        	var areaIptArr = $(this).find('.input').serializeArray();
	        	$(areaIptArr).each(function() {
	        		jsonObject[this.name] = this.value;
        		});
	        	jsonArr.push(jsonObject);
	        	jsonObject = {};
	        });
	        var jsonStr = JSON.stringify(jsonArr);

	        var supplierId = $('#supplierId').val();
	        var itemBaseId = $('#itemBaseId').val();
	        if(supplierId==null||supplierId==''||itemBaseId==null||itemBaseId==''){
	        	layer.msg('缺少参数supplierId和itemBaseId');
	        	return;
	        }

	        var submitNum = 0;//用来判断是否要提交表单
	        var isSubmit = true;
	        //校验格式
	        $('#priceDiv .J_area_price').each(function(){
	        	var count =0;
	        	$(this).find('.J_check').each(function(){
	        		if($(this).val().trim()!=''){
	        			count++;
	        		}
	        	});
	        	if(count!=0){
	        	 submitNum++;
                 var priceReg =  /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/;
                 //校验含有Class J_price的input框值
                 $(this).find('.J_price').each(function(){
                	if(!priceReg.test($(this).val())){
                		isSubmit = false;
                		layer.tips('格式错误!',$(this),{
                			tipsMore: true,
                			tips:[1,'#E81818']
                		});
                	}
                 });

                 //校验含有Class J_text的input框值
                 $(this).find('.J_text').each(function(){
                	if($(this).val().trim().length>20){
                		isSubmit = false;
                		layer.tips('字数超长!',$(this),{
                			tipsMore: true,
                			tips:[1,'#E81818']
                		});
                	}
                 });

                 //校验含有Class J_num的input框值
                 var numReg = /^([1-9][\d]{0,4}|0)$/;
                 $(this).find('.J_num').each(function(){
                	if(!numReg.test($(this).val().trim())){
                		isSubmit = false;
                		layer.tips('格式错误!',$(this),{
                			tipsMore: true,
                			tips:[1,'#E81818']
                		});
                	}
                 });
	        	}

	        });
	        if(submitNum!=0&&isSubmit){
	        	$('#btn').hide();
	        	$.ajax({
		            type : "POST",
		            url : dataPath + "Customer/SpProduct/addItem.do",
		            dataType:'json',
		            async : true,
		            data : {'jsonStr':jsonStr,'supplierId':supplierId,'itemBaseId':itemBaseId,'isEdit':isEdit},
		            success : function(da) {
		            	console.log(da);
		            	console.log(da.message);
		                if (da.success) {
		                    if(da.message=="预修改成功!"){
		                    $('#good_form')[0].reset();
		                    layer.msg('修改成功!');
		                    }else{
		                        $('#good_form')[0].reset();
		                        layer.msg('新增成功!');
		                    }
		                }else{
		                	layer.msg(da.message);
		                }
		                $('#btn').show();
		            },
		            error : function(da) {
		            }
		        });
	        }
	    });
});


//查找在线数据
function checkPlantIsExist() {
	$('#btn').show();
    var brand = $("#good-brand").val().trim();
    if (brand == "") {
        $("#brand-error").html("商品条码不能为空");
        return;
    } else {
        $("#brand-error").html("");
        $("#good-brand").attr("value",brand);
    }
    if ((/[^\w\.\/]/ig.test(brand)) == true || brand.length > 23) {
        $("#brand-error").html("请输入正确的条码");
        return;
    } else {
        $("#brand-error").html("");
    }
    $("#showCurrentPriceBt").addClass("pills-active");
    $("#showPrePriceBt").removeClass("pills-active");
        $.ajax({
            type : "POST",
            url : dataPath + "Customer/SpProduct/isExist.do",
            async : true,
            dataType:"json",
            data : {
                "brand" : brand,
                "id":$("#supplierId").val()
            },
            success : function(data) {
                if (!data.success) {
                    $("#btn").hide();
                    layer.msg(data.message);
                } else {
                	console.log(data.message);
                    $('.noItemError').remove();
                    $("#btn").show();
                    $('#good_form')[0].reset();
                    $("#btn").val("修改商品");
                    $("#good-brand").val(data.message.itemBase.mdseId);
                    $("#good-name").val(data.message.itemBase.name);
                    $("#good-spec").val(data.message.itemBase.spec);
                    $("#good-price").val(data.message.itemBase.pfPrice);
                    $("#itemBaseId").val(data.message.itemBase.id);
                    $("#brand-error").html("");
                    selectCate(data.message.itemBase.cateId);
                    if(data.message.resultList!=null){
                    	if(data.message.resultList.length==0){
                            layer.msg('该批发商没有分配定格!');
	                   	}else{
	                   		groupJson = data.message.resultList;
	                        valuation(data.message.resultList);//回显价格数据
	                   	}
                    }
                    $("#brand-error").html("");
                }
            },
            error : function(data) {
            }
        });
}

//查找预设表数据
function checkPrePlantIsExist() {
	$('#btn').hide();
    var brand = $("#good-brand").val().trim();
    if (brand == "") {
        $("#brand-error").html("商品条码不能为空");
        return;
    } else {
        $("#brand-error").html("");
    }
    if ((/[^\w\.\/]/ig.test(brand)) == true || brand.length > 23) {
        $("#brand-error").html("请输入正确的条码");
        return;
    } else {
        $("#brand-error").html("");
    }
    $("#showCurrentPriceBt").removeClass("pills-active");
    $("#showPrePriceBt").addClass("pills-active");
    $.ajax({
        type : "GET",
        url : dataPath + "Customer/SpProduct/isPreExist.do",
        dataType:'json',
        async : true,
        data : {
            "brand" : brand,
            "id":$("#supplierId").val()
        },
        success : function(data) {
            if (!data.success) {
                $("#btn").val("创建商品");
            } else {
                $('#good_form')[0].reset();
                $("#btn").val("修改商品");
                $("#good-brand").val(data.message.itemBase.mdseId);
                $("#good-name").val(data.message.itemBase.name);
                $("#good-spec").val(data.message.itemBase.spec);
                $("#good-price").val(data.message.itemBase.pfPrice);
                $("#itemBaseId").val(data.message.itemBase.id);
                $("#brand-error").html("");
                selectCate(data.message.itemBase.cateId);
                if(data.message.resultList!=null){
                	if(data.message.resultList.length==0){
                         layer.msg('该批发商没有分配定格!');
                	}else{
                        valuation(data.message.resultList);//回显价格数据
                	}
                }
                $("#brand-error").html("");
            }
        },
        error : function(data) {
        }
    });
}

/**
 * 加载分类列表
 */
function loadinfo() {
    $.ajax({
        type : "POST",
        url : dataPath + "customer/itemCatelog/loadItemCateLogList.do",
        async : false,
        dataType:"json",
        success : function(data) {
        	if(data.success){
        		catelogJson = data.message;
        	}else{
        		layer.msg('加载分类列表出错，请联系技术!');
        		console.log('加载分类列表出错：'+data.message);
        	}
        	//生成一级分类
        	var html = '';
        	$.each(catelogJson,function(i,item){
        		html+='<option value="'+item.id+'">'+item.name+'</option>';
        		$('#yiji').append(html);
        	});
        },
        error : function(data) {
        	layer.msg('加载分类列表失败,失败的请求!')
        }
    });
}

//回显商品类别
function selectCate(data){
    //data 为二级分类id
    if(catelogJson==''){
    	layer.msg('回显商品类别出错!');
    }else{
    	$.each(catelogJson,function(i,item){
    		var pItem = item;
        	$.each(item.catelogVoList,function(i,item){
        		if(item.id == data){
        			var html = '';
        			var pId = item.pid;
        			html +='<option value="'+item.id+'">'+item.name+'</option>'
        			$('#erji').append(html);
        			$('#erji').val(data);
        			$('#yiji').val(pId);
        			return;//找到二级分类就退出循环
        		}
        	});

        });
    }
}

//生成商品在各个定格下的几个html
function valuation(data) {
	var html = '';
	var warehouseJson = $.parseJSON(warehouseList);
	$(data).each(function(i,item){
		html+='<p id="'+item.spGroupIdStr+'" class="J_area_price">'
		    +'<label class="label">'+item.spGroupName+'</label>'
		    +'<input type="hidden" name="'+item.spGroupIdStr+'_plantItemId" value="'+item.id+'" class="input">'
		    +'<input type="hidden" name="'+item.spGroupIdStr+'_isNew" value="'+item.isNew+'" class="input">';
		    //批发商出货价
		    if(item.areaPrice!=null&&item.areaPrice!=''&&item.areaPrice!='null'){
		    	html+='<input type="text" name="'+item.spGroupIdStr+'_areaprice" id="'+item.spGroupIdStr+'_areaprice" onblur="getPlantItemFee(this,'+item.spGroupIdStr+');" class="input input-date J_check J_price" style="margin-left:3px;width: 81px !important;" value="'+item.areaPrice+'">';
		    }else{
		    	html+='<input type="text" name="'+item.spGroupIdStr+'_areaprice" id="'+item.spGroupIdStr+'_areaprice" onblur="getPlantItemFee(this,'+item.spGroupIdStr+');" class="input input-date J_check J_price" value="" style="margin-left:3px;width: 81px !important;">';
		    }
		    //批发商进货价
		    if(item.plantDisPrice!=null&&item.plantDisPrice!=''&&item.plantDisPrice!='null'){
		    	html+='<input type="text" name="'+item.spGroupIdStr+'_plantDisPrice" id="'+item.spGroupIdStr+'_plantDisPrice" onblur="getPlantItemFee(this,'+item.spGroupIdStr+');" style="width: 81px !important;" class="input input-date ml-small J_check J_price" value="'+item.plantDisPrice+'">';
		    }else{
		    	html+='<input type="text" name="'+item.spGroupIdStr+'_plantDisPrice" id="'+item.spGroupIdStr+'_plantDisPrice" onblur="getPlantItemFee(this,'+item.spGroupIdStr+');" style="width: 81px !important;" class="input input-date ml-small J_check J_price" value="">';
		    }
		    //批发商毛利
		    if(item.maoli!=null&&item.maoli!=''&&item.maoli!='null'){
		    	html+='<input type="text" name="'+item.spGroupIdStr+'_grossProfit" id="'+item.spGroupIdStr+'_grossProfit" onblur="getPlantItemFee(this,'+item.spGroupIdStr+');" style="width: 81px !important;" class="input input-date ml-small J_check J_price" value="'+item.maoli+'">';
		    }else{
		    	html+='<input type="text" name="'+item.spGroupIdStr+'_grossProfit" id="'+item.spGroupIdStr+'_grossProfit" onblur="getPlantItemFee(this,'+item.spGroupIdStr+');" style="width: 81px !important;" class="input input-date ml-small J_check J_price" value="">';
		    }
		    //计算费用和费用率
		    var feiyong = '';
		    var feiyonglv = '';
		    if(item.areaPrice!=null&&item.areaPrice!=''&&item.areaPrice!='null'){
		    feiyong = parseFloat(item.maoli)+parseFloat(item.plantDisPrice)-parseFloat(item.areaPrice);
		    feiyonglv = (parseFloat(feiyong)/parseFloat(item.areaPrice))*100;
		    html+='<input type="text" style="display:none;width:81px;background-color:#F0F0F0;" name="'+item.spGroupIdStr+'_costPrice" id="'+item.spGroupIdStr+'_costPrice" onclick="getPlantItemFee(this,'+item.spGroupIdStr+');" readonly="true" class="input input-date ml-small" value="'+feiyong+'">'
		    +'<input type="text" style="display:none;width:81px;background-color:#F0F0F0;" name="'+item.spGroupIdStr+'_expenseRatio" id="'+item.spGroupIdStr+'_expenseRatio" onclick="getPlantItemFee(this,'+item.spGroupIdStr+');" readonly="true" class="input input-date ml-small"  value="'+feiyonglv+'">';
		    }else{
		    html+='<input type="text" style="display:none;idth:81px;background-color:#F0F0F0;" name="'+item.spGroupIdStr+'_costPrice" id="'+item.spGroupIdStr+'_costPrice" onclick="getPlantItemFee(this,'+item.spGroupIdStr+');" readonly="true" class="input input-date ml-small" value="">'
		    +'<input type="text" style="display:none;width:81px;background-color:#F0F0F0;" name="'+item.spGroupIdStr+'_expenseRatio" id="'+item.spGroupIdStr+'_expenseRatio" onclick="getPlantItemFee(this,'+item.spGroupIdStr+');" readonly="true" class="input input-date ml-small"  value="">';
		    }
		    //市场价
		    if(item.plantMemPrice!=null&&item.plantMemPrice!=''&&item.plantMemPrice!='null'){
		    	html+='<input type="text" name="'+item.spGroupIdStr+'_marketprice" id="'+item.spGroupIdStr+'_marketprice" style="width: 81px !important;" class="input input-date ml-small J_check J_price" value="'+item.plantMemPrice+'">';
		    }else{
		    	html+='<input type="text" name="'+item.spGroupIdStr+'_marketprice" id="'+item.spGroupIdStr+'_marketprice" style="width: 81px !important;" class="input input-date ml-small J_check J_price" value="">';
		    }
		    //商品备注
		    if(item.remark!=null&&item.remark!=''&&item.remark!='null'){
		    	html+='<input type="text" name="'+item.spGroupIdStr+'_remark"  style="width: 81px !important;" id="'+item.spGroupIdStr+'_remark" class="input input-date ml-small J_check J_text" value="'+item.remark+'">';
		    }else{
		    	html+='<input type="text" name="'+item.spGroupIdStr+'_remark" style="width: 81px !important;" id="'+item.spGroupIdStr+'_remark" class="input input-date ml-small J_check J_text" value="">';
		    }
		    //数量限制
		    //if(item.restrict!=null){
		    //	html+='<input type="text" name="'+item.spGroupIdStr+'_restrict" id="'+item.spGroupIdStr+'_restrict" style="width: 81px !important;" class="input input-date ml-small J_num" value="'+item.restrict+'">';
		    //}else{
		    //	html+='<input type="text" name="'+item.spGroupIdStr+'_restrict" id="'+item.spGroupIdStr+'_restrict" style="width: 81px !important;" class="input input-date ml-small J_num" value="0">';
		    //}
            html+='<input type="hidden" name="'+item.spGroupIdStr+'_restrict" id="'+item.spGroupIdStr+'_restrict" style="width: 81px !important;" class="input input-date ml-small J_num" value="0">';
		    //优惠信息
		    if(item.youHui!=null&&item.youHui!=''&&item.youHui!='null'){
		    	html+='<input type="text" name="'+item.spGroupIdStr+'_youHui" id="'+item.spGroupIdStr+'_youHui" style="width: 81px !important;" class="input input-date ml-small J_check J_text" value="'+item.youHui+'">';
		    }else{
		    	html+='<input type="text" name="'+item.spGroupIdStr+'_youHui" id="'+item.spGroupIdStr+'_youHui" style="width: 81px !important;" class="input input-date ml-small J_check J_text" value="">';
		    }
		    //上下架状态
		    html+='<select name="'+item.spGroupIdStr+'_status" style="width: 81px !important;" id="'+item.spGroupIdStr+'_status" class="input input-date ml-small">';
		    if(item.status==null||item.status==1){
		    	html+='<option value="1" selected="selected">上架</option>'
		    	    +'<option value="0">下架</option>';
		    }
		    if(item.status!=null&&item.status==0){
		    	html+='<option value="1">上架</option>'
		    	    +'<option value="0" selected="selected">下架</option>';
		    }
		    html+='</select>';
		    html+='<select name="'+item.spGroupIdStr+'_warehouse" style="width: 81px !important;" id="'+item.spGroupIdStr+'_warehouse" class="input input-date ml-small">'
		          /* +'<option>仓库1</option>'
		          +'<option>仓库2</option>' */
		     $.each(warehouseJson,function(i,warehouse){
		    	 if(item.warehouseId == warehouse.id){
		    	 html+='<option value="'+warehouse.id+'" selected="selected">'+warehouse.name+'</option>'
		    	 }else{
		    	 html+='<option value="'+warehouse.id+'">'+warehouse.name+'</option>'
		    	 }
		     });
		     html+='</select>';
		     html+='<select name="'+item.spGroupIdStr+'_logicStockTypeMg" id="'+item.spGroupIdStr+'_logicStockTypeMg" style="width: 81px !important;" class="input input-date ml-small">';
	    	 if(item.logicStockTypeMg ==1){
	    		 html+='<option value="1" selected="selected">正常库存</option>';
	    	   }else{
	    		 html+='<option value="1">正常库存</option>';
	    	 }
	    	 if(item.logicStockTypeMg ==2){
	    		 html+='<option value="2" selected="selected">促销库存</option>';
	    	   }else{
	    		 html+='<option value="2">促销库存</option>';
	    	 }
	    	 if(item.logicStockTypeMg ==3){
	    		 html+='<option value="3" selected="selected">安全库存</option>';
	    	   }else{
	    		 html+='<option value="3">安全库存</option>';
	    	 }
	    	 if(item.logicStockTypeMg ==4){
	    		 html+='<option value="4" selected="selected">炒货库存</option>';
	    	   }else{
	    		 html+='<option value="4">炒货库存</option>';
	    	 }
	    	 if(item.logicStockTypeMg ==5){
	    		 html+='<option value="5" selected="selected">积压库存</option>';
	    	   }else{
	    		 html+='<option value="5">积压库存</option>';
	    	 }
		     html+='</select>';
		    html+='</p>';
	});
	$('#priceDiv').html(html);
}

//回显价格数据
function toValue(arr) {
    var id = arr[0];
    $("#" + id + "_areaprice").val(arr[2]);//批发商出货价
    $("#" + id + "_marketprice").val(arr[3]);//市场价
    $("#" + id + "_plantDisPrice").val(arr[1]);//批发商进货价
    $("#" + id + "_grossProfit").val(arr[4]);//批发商毛利
    var feiyon=parseFloat(arr[4])+parseFloat(arr[1])-parseFloat(arr[2]);
    feiyon=forDight(feiyon,2);
    $("#" + id + "_costPrice").val(feiyon);//费用
    var feiyonl= (parseFloat(feiyon)/parseFloat(arr[2]))*100
    $("#" + id + "_expenseRatio").val(forDight(feiyonl,3)+"%");//费用率
    $("#" + id + "_status").val(arr[5]);//状态
    $("#" + id + "_remark").val(arr[6]);//商品备注
    $("#" + id + "_restrict").val(arr[7]);//数量限制
    $("#" + id + "_youHui").val(arr[8]);//优惠信息
}


function getPlantItemFee(obj,areaId){
    var areaprices=$("#" + areaId + "_areaprice").val();//出货价
    var plantDisPrices=$("#" + areaId + "_plantDisPrice").val();//进货价
    var grossProfit=$("#" + areaId + "_grossProfit").val();//获取费用
    var feiyon=parseFloat(grossProfit)+parseFloat(plantDisPrices)-parseFloat(areaprices);
    feiyon=forDight(feiyon,2);
    $("#" + areaId + "_costPrice").val(feiyon);
    if(areaprices!=0){
        var feiyonl= (parseFloat(feiyon)/parseFloat(areaprices))*100
        $("#" + areaId + "_expenseRatio").val(forDight(feiyonl,3)+"%");
    }
}



function getAreaPrice() {
    var str = "&arearinfo=";
    $(".J_area_price").each(function() {
        var id = $(this).attr("id");//改id为定格id
        str += id + ","
        + $("#" + id + "_areaprice").val() + ":"
        + $("#" + id + "_marketprice").val() + ":"
        + $("#" + id + "_plantDisPrice").val() +":"
        + $("#" + id + "_grossProfit").val()+":"
        + $("#" + id + "_status").val() +";";
    });
    return str;
}

/**
** 四舍五入
*/
function forDight(_num,_x){
 var n = 1;
 for(var i=0;i<_x;i++){
    n=n*10;
 }
 return Math.round(_num*n)/n;
}
</script>
</body>
</html>
