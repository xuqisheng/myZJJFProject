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
</head>
<body class="wrap-bd">
<div id="${applyStore.id}_tab">
    <div>
        <span class="mr-default">开店推荐人手机号：${applyStore.tuijianRenTel}</span>
        <span class="ml-default">业务员手机号：${applyStore.yewuRenTel}</span>
    </div>
    <div class="mt-default wrap-bd bg">
        <b>店铺信息</b>
        <div class="mt-default">
            <p>
                <label class="label">注册手机号：</label>
                
                <span>
                     <span id="curr_mobile">${applyStore.mobile}</span>
                     <input type="text" id="${applyStore.id}_mobile" value="${applyStore.mobile}" class="input input-default hidden">
                     <%-- <c:if test="${applyStore.status==2}">
                     <span class="icon-op icon-op-edit J_edit"></span>
                     </c:if> --%>
                </span>
            </p>
            <p>
                <label class="label">店主名：</label>
                <span>
                    <span id="curr_contact">${applyStore.contact}</span><input type="text" id="${applyStore.id}_contact" value="${applyStore.contact}" style="display: none">
                    <span class="icon-op icon-op-edit J_edit"></span>
                    <%-- <c:if test="${applyStore.status==2}">
                    <span class="icon-op icon-op-edit J_edit"></span>
                    </c:if> --%>
                </span>
            </p>
            <p>
                <label class="label">商铺名：</label>
                <span>
                    <span id="curr_name">${applyStore.name}</span>
                    <input type="text" id="${applyStore.id}_name" value="${applyStore.name}" class="input input-default hidden">
                    <span class="icon-op icon-op-edit J_edit"></span>
                    <%-- <c:if test="${applyStore.status==2}">
                    <span class="icon-op icon-op-edit J_edit"></span>
                    </c:if> --%>
                </span>
            </p>
            <p>
                <label class="label">所在区域：</label>
                <span>
                    <select class="select" id="regionsPId1" name="provinceId" >
	                	<option value="-1" selected="selected">请选择</option>
	                	<c:forEach items="${regions}" varStatus="i" var="region" >
                			<option value="${region.id}" <c:if test="${applyStore.provinceId == region.id }">selected="selected"</c:if>>${region.name}</option>
						</c:forEach>
	                </select>
	                <select class="select" id="regionsPId2" name="cityId">
	                	<option value="-1" selected="selected">请选择</option>
	                	<c:forEach items="${citys}" varStatus="i" var="city" >
                			<option value="${city.id}" <c:if test="${applyStore.cityId == city.id }">selected="selected"</c:if>>${city.name}</option>
						</c:forEach> 
	                </select>
	                <select class="select"  id="regionsPId3" name="areaId">
	                    <option value="-1" selected="selected">请选择</option>
	                    <c:forEach items="${countys}" varStatus="i" var="county" >
                			<option value="${county.id}" <c:if test="${applyStore.areaId == county.id }">selected="selected"</c:if>>${county.name}</option>
						</c:forEach>
	                </select>
	                <select class="select"  id="regionsPId4" name="streetId">
	                    <option value="-1" selected="selected">请选择</option>
	                    <c:forEach items="${streets}" varStatus="i" var="street" >
                			<option value="${street.id}" <c:if test="${applyStore.streetId == street.id }">selected="selected"</c:if>>${street.name}</option>
						</c:forEach>
	                </select>
                </span>
            </p>
            <p>
                <label class="label">营业执照编号</label>
                <span>
                    <span id="curr_licenseNum">${applyStore.licenseNum}</span>
                    <input type="text" id="licenseNum" value="${applyStore.licenseNum}" class="input input-default hidden">
                    <span class="icon-op icon-op-edit J_edit"></span>
                    <%-- <c:if test="${applyStore.status==2}">
                    <span class="icon-op icon-op-edit J_edit"></span>
                    </c:if> --%>
                </span>
            </p>
            <p>
                <label class="label">店面地址：</label>
                <span>
                     <%-- <c:choose>
                      <c:when test="${applyStore.status==2}"> --%>
                      <input type="text" class="input input-default" id="address" value="${applyStore.address}">
                      <%-- </c:when>
                      <c:otherwise>
                      <input type="text" class="input input-default" id="address" value="${applyStore.address}" readonly="readonly">
                      </c:otherwise>
                     </c:choose> --%>
                </span>
            </p>
            <p>
                <label class="label">店面坐标：</label>
                <span>
                      <c:choose>
                          <c:when test="${applyStore.status==2}">
                              <input type="text" class="input input-date" id="${applyStore.id}_lng" placeholder="lng" value="${applyStore.lng}"> - 
                              <input type="text" class="input input-date" id="${applyStore.id}_lat" placeholder="lat" value="${applyStore.lat}">
                          </c:when>
                          <c:otherwise>
                              <input type="text" class="input input-date" id="${applyStore.id}_lng" placeholder="lng" value="${applyStore.lng}" readonly="readonly"> - 
                              <input type="text" class="input input-date" id="${applyStore.id}_lat" placeholder="lat" value="${applyStore.lat}" readonly="readonly">
                          </c:otherwise>
                      </c:choose>
                </span>
            </p>
            <c:if test="${applyStore.status!=2}">
	            <p>
	                <label class="label">连锁编号：</label>
	                <spa>
	                	<span id="curr_name">${applyStore.suppliercode}</span>
	                    <input type="text" id="${applyStore.id}_suppliercode" value="${applyStore.suppliercode}" class="input input-default hidden">
	                </span>
	            </p>
            </c:if>
        </div>
        <div>
            <div>
                <span><img src="${applyStore.idCardUpPic}" alt="身份证正面照片" width="310" class="table-border"></span>
                <span><img src="${applyStore.idCardDownPic}" alt="身份证背面照片" width="310" class="table-border"></span>
            </div>
            <div>
                <span><img src="${applyStore.licensePic}" width="300" alt="经营许可证" class="table-border"></span>
                <span><img src="${applyStore.tobaccoPic}" width="300" alt="烟草经营许可证" class="table-border"></span>
                <span><img src="${applyStore.img}" width="300" alt="门门店照" class="table-border"></span>
            </div>
        </div>
    </div>
    <div class="mt-default wrap-bd bg">
        <b>定格信息</b>
        <div class="mt-default">
	        <input name="spGroupId" value="${applyStore.spGroupId}" id="spGroupId" type="hidden">
	        <div>
	            <label>现有定格：</label>
	            <input type="text" id="spGroupIdVo" value="" readonly="readonly" class="input input-default">
	            &nbsp;&nbsp;&nbsp;&nbsp;
	            <label>商铺来源：</label>
	        	<c:if test="${applyStore.fromWho==0}">自愿加入</c:if>
	        	<c:if test="${applyStore.fromWho==1}">批发商邀请</c:if>
	        	<c:if test="${applyStore.fromWho==2}">商铺邀请</c:if>
	        </div>
	        <p>        
	            <label>指派到：</label>
	            <select id="spGroupList" class="select" >
	                <option value="">请选择</option>
	                <c:forEach var="area" items="${areas }">
	                	<option value="${area.id }">${area.name }</option>
	                </c:forEach>
	            </select>
	        </p>
        </div>
    </div>
    <div class="mt-default wrap-bd bg">
        <b>银行卡信息</b>
        <div class="mt-default">
             <span clsaa="mr-default">
                                     卡号: <span id="curr_bankCard">${applyStore.bankCard}</span>
                 <input type="text" id="bankCard" value="${applyStore.bankCard}" class="hidden">
                 <span class="icon-op icon-op-edit J_edit"></span>
                 <%-- <c:if test="${applyStore.status==2}">
                 <span class="icon-op icon-op-edit J_edit"></span>
                 </c:if> --%>
             </span>
             <p clsaa="ml-default">
                                    开户行: <span id="curr_bankName">${applyStore.bankName}</span>
                 <input type="text" id="bankName" value="${applyStore.bankName}" class="hidden">
                 <span class="icon-op icon-op-edit J_edit"></span>
                 <%-- <c:if test="${applyStore.status==2}">
                 <span class="icon-op icon-op-edit J_edit"></span>
                 </c:if> --%>
             </p>
        </div>
        <div>
            <span><img src="${applyStore.backCardPic}" alt="银行卡照片" width="300" class="table-border"></span>
        </div>
    </div>
    <div class="mt-default">
        <a href="#" class="button button-ok J_pass" id="${applyStore.id}">通过</a>
        <a href="${root}/customer/appStore/rejectApply.do?id=${applyStore.id}&mobile=${applyStore.mobile}" class="button button-cancel">拒绝</a>
    </div>
</div>
<script>
	$(function(){
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
		$('#regionsPId3').on('change' , function(){
			$.post("${root}/kefu/public/findRegionsByPId.do",{pId:$('#regionsPId3').val()},function(data){
				var html = '<option value="">请选择</option>';
				if(data.success){
					$.each(data.message,function(i,item){
						html+='<option value="'+item.id+'">'+item.name+'</option>'
					})
				}
				$('#regionsPId4').html(html);
			},'json');
			$.post("${root}/Customer/SpGroup/getSpGroupByAreaId.do",{areaId:$('#regionsPId3').val()},function(data){
				var html = '<option value="">请选择</option>';
				if(data.success){
					$.each(data.message,function(i,item){
						html+='<option value="'+item.id+'">'+item.name+'</option>'
					});
				}
				$('#spGroupList').html(html);
			},'json');
		});
	})
    //读取定格列表
    $.ajax({
            type : "GET",
            url : "${root}/customer/appStore/getSpGroupList.do",
            async:false,
            dataType : "json",
            success : function(data) {
                if (data.success) {
                    if($("#spGroupId").val()!=""){
                        $.each(data.message,function(i,item){
                            if(item.id == $("#spGroupId").val()){
                                $("#spGroupIdVo").val(item.name);
                            }
                        });
                    }else{
                    	/* $("#spGroupIdVo").addAttr("placeholder","请指派！"); */
                    }
                } 
            },
            error : function(data) {
            }
        });
    
    $("#spGroupList").change(function(){
        $("#spGroupId").val($(this).val());
    });
    
    var map = {};
    var array = new Array();
    $(".J_edit").on("click", function() {
        var input = $(this).prev();
        var id = input.attr("id");
        var real = input.prev();
        real.hide();
        input.show();
        input.focus();
        input.on("blur", function() {
            $(this).hide();
            real.show();
        });
        input.change(function() {
            array.push(id);
            real.text($(this).val());
            map[id] = $(this).val();
        });
    });

    $(".J_pass").on("click", function() {
    	$(this).attr("disabled","true");
        var id = $(this).attr("id");
        var dat = new Object();
        var lng = $("#" + id + "_lng").val();
        var lat = $("#" + id + "_lat").val();
        var supplierCode = $("#" + id + "_suppliercode").val();
        var address = $("#address").val();
        var licenseNum = $("#licenseNum").val();
        var spGroupId = $("#spGroupId").val();
        if (!licenseNum) {
        	$(this).removeAttr("disabled");
            alert("营业执照不能为空!");
            return;
        }
        if (!address) {
        	$(this).removeAttr("disabled");
            alert("请完善资料后再提交");
            return;
        }
       /*  if(!(/^\d{6}$/.test(supplierCode))){
            alert("店铺连锁编号的格式不正确，必须是6位数字");
            return;
        } */
        var applyStoreAreaId = "${applyStore.areaId}";
        console.log(applyStoreAreaId);
        if($("#spGroupId").val()=="" ||( $("#regionsPId3").val() != applyStoreAreaId && $("#spGroupList").val()=="")){
        	$(this).removeAttr("disabled");
            alert("请选择定格组");
            return;
        }
        
        dat.id = id;
        dat.address = address;
        dat.lng = lng;
        dat.lat = lat;
        dat.mobile = $("#" + id + "_mobile").val();
        dat.supplierCode = supplierCode;
        dat.spGroupId = spGroupId;
        dat.licenseNum=licenseNum;
        dat.bankCard = $("#bankCard").val();
        dat.bankName = $("#bankName").val();
        dat.provinceId=$("#regionsPId1").val();
        dat.cityId=$("#regionsPId2").val();
        dat.areaId=$("#regionsPId3").val();
        dat.streetId=$("#regionsPId4").val();
        
        for ( var prop in map) {
            var supkey = prop.substring(0, prop.indexOf("_"));
            var key = prop.substring(prop.indexOf("_") + 1);
            if (supkey == id) {
                if (map[prop] != null || map[prop] != "" || map[prop] != undefined) {
                    dat[key] = encodeURI(map[prop]);
                } else {
                	$(this).removeAttr("disabled");
                    alert("完善资料后再提交");
                    return;
                }
            }
        }
        encodeURI(dat);
        $.ajax({
            type : "GET",
            url : "${root}/customer/appStore/updateAppStore.do",
            dataType : "json",
            data : dat,
            success : function(data) {
                if (data.success) {
                    alert(data.message);
                    location.href = "${root}/customer/appStore/appStores.do?pageIndex=0&pageSize=10";
                } else{
                    alert(data.message);
                }
            },
            error : function(data) {
            }
        });
        
    });
</script>
</body>
</html>