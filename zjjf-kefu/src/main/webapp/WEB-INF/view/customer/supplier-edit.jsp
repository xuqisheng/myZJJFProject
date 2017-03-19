<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>批发商列表</title>
    <%@ include file="../common/head.jsp"%>
</head>
<body class="wrap-bd">
<div class="mb-default">
    <a class="crumb" href="${root}/customer/supplier/supplierPage.do?s=1">批发商管理</a><a class="crumb crumb-active">批发商信息</a>
</div>
<div class="wrap-bd bg border">
    <input class="id-card-img hidden" type="image" id="id-card-img" src="">
    <div class="txt-info">供货商信息</div>
    <form action="" id="good_form">
        <input type="hidden" value="${supplierDetail.id }" name="id">
        <div>
            <p>
                <label class="label">供货商姓名：</label>
                <input type="text" id="supplierName" name="supplierName" value="${supplierDetail.supplierName }" class="input input-default">
                <span class="error-prompt" id="brand-error"></span>
            </p>
            <p>
                <label class="label">供货商编号：</label>
                <input type="text" id="supplierCode" name="supplierCode" value="${supplierDetail.supplierCode }" class="input input-default" readonly="readonly">
                <span class="error-prompt" id="brand-error"></span>
            </p>
            <p>
                <label class="label">登录密码：</label>
                <c:choose>
                    <c:when test="${action==1 }">
                        <input type="password" id="password-update" name="password" placeholder="密码为必填" value="" class="input input-default">
                    </c:when>
                    <c:when test="${action==2 }">
                        <input type="password" id="password" name="password" placeholder="填写则代表修改" value="" class="input input-default">
                    </c:when>
                </c:choose>
                 <span class="error-prompt" id="brand-error"></span>
            </p>
            <p>
                <label class="label">手机号码：</label>
                <c:choose>
                    <c:when test="${action==1 }">
                        <input type="text" id="mobile-add" name="mobile" placeholder="手机号码为必填" value="${supplierDetail.mobile }" class="input input-default"></li>
                    </c:when>
                    <c:when test="${action==2 }">
                        <input type="hidden" value="${supplierDetail.mobile }" /><input type="text" id="mobile" name="mobile" value="${supplierDetail.mobile }"  class="input input-default">
                    </c:when>
                </c:choose>
                <span class="error-prompt" id="brand-error"></span>
            </p>
            <p>
                <label class="label">固定电话：</label>
                <input type="text" id="callNum" name="callNum" value="${supplierDetail.callNum }" class="input input-default">
                <span class="error-prompt" id="brand-error"></span>
            </p>
            <p>
                <label class="label">电子邮箱：</label>
                <input type="text" id="supplier-email" name="email" value="${supplierDetail.email }" class="input input-default">
                <span class="error-prompt" id="brand-error"></span>
            </p>
            <p>
                <label class="label">区域：</label>
               	<select class="select" id="regionsPId1" name="province" >
                	<option value="-1" selected="selected">请选择</option>
                	<c:forEach items="${regions}" varStatus="i" var="region" >
                        <option value="${region.id}" <c:if test="${supplierDetail.province==region.id }">selected="selected"</c:if>>${region.name}</option> 
					</c:forEach>
                </select>
                <select class="select" id="regionsPId2" name="city">
                	<option value="-1" selected="selected">请选择</option>
                	<c:forEach items="${citys}" varStatus="i" var="city" >
                        <option value="${city.id}" <c:if test="${supplierDetail.city==city.id }">selected="selected"</c:if>>${city.name}</option> 
					</c:forEach>
                </select>
                <select class="select"  id="regionsPId3" name="areaId">
                    <option value="-1" selected="selected">请选择</option>
                    <c:forEach items="${countys}" varStatus="i" var="county" >
                        <option value="${county.id}" <c:if test="${supplierDetail.areaId==county.id }">selected="selected"</c:if>>${county.name}</option> 
					</c:forEach>
                </select>
            </p>
            <p>
                <label class="label">供应商地址：</label>
                <input type="text" id="supplierAddress" value="${supplierDetail.supplierAddress}" name="supplierAddress" class="input input-default">
                <span class="error-prompt" id="brand-error"></span>         
            </p>
            <p>
                <label class="label">供应商描述：</label>
                <input type="text" id="supplierMark" value="${supplierDetail.supplierMark }" name="supplierMark" class="input input-default">
                <span class="error-prompt" id="brand-error"></span>
            </p>
            <p>
                <label class="label">负责人姓名：</label>
                <input type="text" id="managerName" value="${supplierDetail.managerName }" name="managerName" class="input input-default">
                <span class="error-prompt" id="brand-error"></span>
            </p>
            <p>
                <label class="label">负责人电话：</label>
                <input type="text" id="managerTel" value="${supplierDetail.managerTel }" name="managerTel" class="input input-default">
                <span class="error-prompt" id="brand-error"></span>
            </p>
            <p>
                <label class="label">银行卡照：</label>
                <input type="file" id="file-img" name="file-img" class="input input-default">
                <input type="button" id="btn-send-idimg" value="上传图片" class="button button-white">
                <input type="hidden" name="bankcardurl" id="idcardimg">
            </p>
            <p>
                <label class="label">银行卡号：</label>
                <input type="text" id="bankNum" name="bankNum" value="${supplierDetail.bankNum }" class="input input-default">
                <span class="error-prompt" id="brand-error"></span>
            </p>
            <p>
                <label class="label">开户行名称：</label>
                <input type="text" id="bankName" name="bankName" value="${supplierDetail.bankName }" class="input input-default">
                <span class="error-prompt" id="brand-error"></span></li>
            </p>
            <p>
                <label class="label">开户名：</label>
                <input type="text" id="cardUser" name="cardUser" value="${supplierDetail.cardUser }" class="input input-default">
                <span class="error-prompt" id="cardUser-error"></span></li>
            </p>
            <p>
                <label class="label">起送金额：</label>
                <c:choose>
                    <c:when test="${supplierDetail.startPrice==''}">
                        <input type="text" id="startPrice" name="startPrice" value="0.0" class="input input-default">
                    </c:when>
                    <c:otherwise>
                        <input type="text" id="startPrice" name="startPrice" value="${supplierDetail.startPrice}" class="input input-default">
                    </c:otherwise>
                 </c:choose>
                 <span class="error-prompt" id="startPrice-error"></span>
            </p>
            <p>
                <label class="label">运费：</label>
                <c:choose>
                    <c:when test="${supplierDetail.freight==''}">
                        <input type="text" id="freight" name="freight" value="0.0" class="input input-default">                   
                    </c:when>
                    <c:otherwise>
                        <input type="text" id="freight" name="freight" value="${supplierDetail.freight}" class="input input-default">                  
                    </c:otherwise>
                </c:choose>
                <span class="error-prompt" id="freight-error"></span>
            </p>
            <p>
                <c:choose>
                    <c:when test="${action==1 }">
                        <input type="button" type="button" id="btn-add" class="button button-ok" value="新增" />
                    </c:when>
                    <c:when test="${action==2 }">
                        <input type="button" type="button" id="btn-update" class="button button-ok" value="修改" />
                    </c:when>
                </c:choose>
            </p>
        </div>
    </form>
</div>
<script src="${root }/resources/vendor/jquery/ajaxfileupload.js"></script>
<script>
	var dataPath = "${root }";
	$(function() {
		
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
		
		
	    $("#btn-send-idimg").on("click", function() {
	        var f = checkFileType();
	        if (f) {
	            ajaxFileUpload2();
	        }
	    });
	
	    $("#btn-update").on("click", function() {
	        if ($(".error-prompt").text().length > 0) {
	            alert("请填写正确信息");
	            return;
	        }
	        var formdata = $("#good_form").serialize();
	        $.post("${root}/customer/supplier/updateSupplier.do",formdata,function(da){
	        	if (da.success) {
                    alert(da.message);
                    $("#id-card-img").addClass("hidden");
                    location.href = '${root}/customer/supplier/supplierPage.do';
                } else {
                    alert(da.message);
                }
			},'json');
	    });
	    $("#password-update").on("blur", function() {
	        if ($(this).val() == "") {
	            $(this).nextAll("span").html("密码为必填");
	            return;
	        } else {
	            $(this).nextAll("span").html("");
	        }
	    });
	    $("#mobile").on("blur", function() {
	        if (!/^1[3|4|5|8][0-9]\d{8}$/.test($(this).val())) {
	            $(this).nextAll("span").html("请填写正确的手机号");
	            return;
	        } else {
	            $(this).nextAll("span").html("");
	        }
	        console.log($(this).prev("input").val());
	        if ($(this).prev("input").val() == $(this).val()) {
	            return;
	        } else {
	            $(this).nextAll("span").html("");
	        }
	        $.ajax({
	            type : "get",
	            url : dataPath + "/customer/supplier/isExit.do",
	            async : true,
	            dataType: 'json',
	            data : {
	                "mobile" : $("#mobile").val()
	            },
	            success : function(da) {
	                if (!da.success) {
	                    $("#mobile").nextAll("span").html(da.message);
	                } else {
	                    $("#mobile").nextAll("span").html("");
	                }
	            },
	            error : function(da) {
	            }
	        });
	    });
	    $("#managerTel").on("blur", function() {
	        var callNum = $(this).val().match(/^(0[0-9]{2,3}-)?([2-9][0-9]{6,7})+(-[0-9]{1,4})?$|(^(13[0-9]|15[0|3|6|7|8|9]|18[8|9])d{8}$)/);
	        var mobile = $(this).val().match(/^1[3|4|5|8][0-9]\d{4,8}$/);
	        if ((!callNum && !mobile) && $(this).val() != "") {
	            $(this).nextAll("span").html("请填写正确电话号码");
	        } else {
	            $(this).nextAll("span").html("");
	        }
	    });
	    $("#bankNum").on("blur", function() {
	        var bankNum = $(this).val().match(/^\d{16,19}$/);
	        if (!bankNum && $(this).val() != "") {
	            $(this).nextAll("span").html("请填写正确的银行卡号");
	        } else {
	            $(this).nextAll("span").html("");
	        }
	    });
	    $("#supplier-email").on("blur", function() {
	        var result = $(this).val().match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/);
	        if (!result && $(this).val() != "") {
	            $(this).nextAll("span").html("请填写正确的邮箱");
	        } else {
	            $(this).nextAll("span").html("");
	        }
	    });
	    $("#callNum").on("blur", function() {
	        var res = $(this).val().match(/^(0[0-9]{2,3}-)?([2-9][0-9]{6,7})+(-[0-9]{1,4})?$|(^(13[0-9]|15[0|3|6|7|8|9]|18[8|9])d{8}$)/);
	        if (!res && $(this).val() != "") {
	            $(this).nextAll("span").html("请填写正确的电话号码");
	        } else {
	            $(this).nextAll("span").html("");
	        }
	    });
	    $("#mobile-add").on("blur", function() {
	        if ($(this).val() == "") {
	            $(this).nextAll("span").html("手机号为必填项");
	            return;
	        } else {
	            $(this).nextAll("span").html("");
	        }
	        if (!/^1[3|4|5|8][0-9]\d{8}$/.test($(this).val())) {
	            $("#mobile-add").nextAll("span").html("请填写正确的手机号格式");
	            return;
	        } else {
	            $("#mobile-add").nextAll("span").html("");
	        }
	        $.ajax({
	            type : "get",
	            url : dataPath + "/customer/supplier/isExit.do",
	            async : true,
	            dataType: 'json',
	            data : {
	                "mobile" : $("#mobile-add").val()
	            },
	            success : function(da) {
	                if (!da.success) {
	                    $("#mobile-add").nextAll("span").html(da.message);
	                } else {
	                    $("#mobile-add").nextAll("span").html("");
	                }
	            },
	            error : function(da) {
	            }
	        });
	    });
	
	    $("#btn-add").on("click", function() {
	        if ($("#mobile-add").val() == "" || $("#password-update").val() == "") {
	            alert("用户名或密码不能为空");
	            return;
	        }
	        if ($(".error-prompt").text().length > 0) {
	            alert("请填写正确信息");
	            return;
	        }
	        var formdata = $("#good_form").serialize();
	        console.log(formdata);
	        $.ajax({
	            type : "POST",
	            url : dataPath + "/customer/supplier/add.do",
	            async : true,
	            data : formdata,
	            dataType : 'json',
	            success : function(da) {
	                console.log(da);
	                if (da.success) {
	                    alert("新增成功");
	                    $('#good_form')[0].reset();
	                    $("#id-card-img").addClass("hidden");
	                    location.href = '${root}/customer/supplier/supplierPage.do';
	                } else {
	                    alert(da.message);
	                }
	            },
	            error : function(da) {
	            }
	        });
	    });
	});
	
	function checkFileType() {
	    var myFile = document.getElementById("file-img");
	    var filePath = myFile.value;
	    var dotNdx = filePath.lastIndexOf('.');
	    var exetendName = filePath.slice(dotNdx + 1).toLowerCase();
	    if ((exetendName == "jpg" || exetendName == "jpeg" || exetendName == "gif" || exetendName == "png")) {
	        return true;
	    }
	
	    $("#send-img").html("请选择正确的图片格式");
	    return false;
	}
	function ajaxFileUpload2() {
	
	    $("#send-img").html("");
	    /*
	     * $("#loading").ajaxStart(function() { $(this).show();
	     * }).ajaxComplete(function() { $(this).hide(); });
	     */
	    $.ajaxFileUpload({
	        url : dataPath + "Customer/ItemBases/upload.do",
	        secureuri : false,
	        fileElementId : 'file-img',
	        dataType : 'text',
	        data : {
	            username : $("#username").val()
	        },
	        success : function(data, status) {
	            console.log(data);
	            if (data == "错误") {
	                // $("#send-img").html("上传失败");
	                return;
	            }
	            alert("上传成功");
	            // $("#send-img").html("上传成功");
	            $("#idcardimg").val(data);
	
	            $("#id-card-img").attr("src", "http://www.izjjf.cn/" + data);
	            $("#id-card-img").removeClass("hidden");
	
	        },
	        error : function(data, status, e) {
	            alert('上传出错');
	            $("#send-img").html("上传失败");
	        }
	    });
	}
    function go(status) {
        location.href = "/CornerV2/Customer/SpOrderInfo/GetSpOrderInfos.do?status=" + status + "&pageIndex=" + $("#kkpager_btn_go_input").val().trim();
    };
</script>
</body>
</html>