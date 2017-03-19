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
<div class="wrap-bd bg table-border">
    <input class="id-card-img hidden" type="image" id="id-card-img" src="" />
    <b class="txt-info">业务员信息</b>
    <form id="salesman_form">
        <c:if test="${action eq 2}">
            <input type="hidden" value="${salesman.id }"  name="id"/>
        </c:if>
        <div class="main-cont">
            <p>
                <label class="label">业务员姓名：</label>
                <input type="text" id="username" name="userName" class="input input-default" placeholder="业务员姓名为必填" value="${salesman.userName}">
                <span class="txt-warn" id="brand-error"></span>
            </p>
            <p>
            <c:if test="${action eq 1}">
               <label class="label">手机号码：</label>
               <input type="text" id="mobile" name="mobile" placeholder="手机号码为必填" class="input input-default" value="${salesman.mobile}"/>
               <span class="txt-warn" id="brand-error"></span>
            </c:if>
            <c:if test="${action eq 2}">
               <span class="label">手机号码：<input type="hidden" id="mobile_hidden" value="${salesman.mobile}"></span>
               <input type="text" id="mobile_update" name="mobile" placeholder="手机号码为必填" class="input input-default" value="${salesman.mobile}"/>
               <span class="txt-warn" id="brand-error"></span>
            </c:if>
            </p>
            <p>
                <span class="label">性别：</span>
                <c:if test="${action == 1}">
                <label for="boy"><input type="radio" name="gender" value="1" id="boy"  checked="checked"/> 男</label>
                <label for="girl"><input type="radio" name="gender" value="0" id="girl"/> 女</label>
                </c:if>
                <c:if test="${action == 2}">
                <c:if test="${salesman.gender == 0}">
                <label for="boy"><input type="radio" name="gender" value="1" id="boy"/> 男</label>
                <label for="girl"><input type="radio" name="gender" value="0" id="girl" checked="checked"/> 女</label>
                </c:if>
                <c:if test="${salesman.gender == 1}">
                <label for="boy"><input type="radio" name="gender" value="1" id="boy"  checked="checked"/> 男</label>
                <label for="girl"><input type="radio" name="gender" value="0" id="girl"/> 女</label>
                </c:if>
                </c:if>
            </p>
            <p>
               <label class="label">身份证号码：</label>
               <input type="text" id="identity_card" name="identitycard" class="input input-default" placeholder="身份证号码为可填" value="${salesman.identitycard}">
               <span class="txt-warn" id="brand-error"></span>
            </p>
            <p>
               <span class="label">分配区域：</span>
               <select name="province" id="sheng" onchange="getArea(1)" class="select">
                    <option value="-1">全部</option>
                    <c:forEach var="sheng" items="${shengList }">
                        <option <c:if test="${salesman.province==sheng.id }">selected="selected"</c:if> value="${sheng.id }">${sheng.name }</option>
                    </c:forEach>
                </select>
                <select name="city" id="shi" onchange="getArea(2)" class="select">
                    <c:choose>
                        <c:when test="${shiList==null || shiList.size()==0 }">
                            <option value="-1">全部</option>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="shi" items="${shiList }">
                                <option <c:if test="${salesman.city==shi.id }">selected="selected"</c:if> value="${shi.id }">${shi.name }</option>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </select>
                <select id="area" name="area" class="select">
                    <c:choose>
                        <c:when test="${areaList== null || areaList.size()==0 }">
                            <option value="-1">全部</option>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="area" items="${areaList }">
                                <option <c:if test="${salesman.area==area.id }">selected="selected"</c:if> value="${area.id }">${area.name }</option>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </select>
            </p>
            <p>
               <label class="label">所住地址：</label>
               <input type="text" id="address" name="address" class="input input-default" placeholder="所住地址为可填" value="${salesman.address}">
            </p>
            <p>
               <label class="label">电子邮箱：</label>
               <input type="text" id="email" name="email" class="input input-default" placeholder="电子邮箱为可填" value="${salesman.email}">
               <span class="txt-warn" id="brand-error"></span>
            </p>
            <p>
            <c:if test="${action eq 1}"><input type="button" type="button" id="btn-add" class="button button-ok" value="新增"/></c:if>
            <c:if test="${action eq 2}"><input type="button" type="button" id="btn-update" class="button button-ok" value="提交"/></c:if>
            </p>
        </div>
    </form>
</div>
<script>
var dataPath = "/zjjf-kefu/";
function isNull(data){
	if(data==null||data== 'undefined' ||data==""){
		return true;
	}
	return false;
}
$(function(){
    var flag = 0;
    $("#username").on("blur",function(){
        var username = $("#username").val().trim();
        if(isNull(username)){
            $("#username").next("#brand-error").html("业务员姓名必填");
            return;
        }else if(username.length>16){
            $("#username").next("#brand-error").html("业务员姓名的长度不能超过16个字符");
            return;
        }else{
            $("#username").next("#brand-error").html("");
        }
    });
    
    
    $("#mobile").on("blur",function(){
        var mobile = $("#mobile").val().trim();
        if(isNull(mobile)){
            $("#mobile").next("#brand-error").html("手机号必填");
            return;
        }else if(!/^1[3|4|5|7|8][0-9]\d{4,8}$/.test(mobile)){
            $("#mobile").next("#brand-error").html("请填写正确的手机号");
            return;
        }else{
            $.ajax({
                type : "get",
                url : dataPath + "/customer/salesman/isExitMobile.do",
                async : true,
                data : {"mobile" : mobile},
                dataType:'json',
                success : function(date) {
                    if (!date.success) {
                        $("#mobile").next("#brand-error").html(date.message);
                        flag = 1;
                        return;
                    } else {
                        flag = 0;
                        $("#mobile").next("#brand-error").html("");
                    }
                },
                error : function(date) {
                }
            });
        }
    });
    $("#mobile_update").on("blur",function(){
        var mobile_update = $("#mobile_update").val().trim(),mobile_hidden = $("#mobile_hidden").val().trim();
        if(isNull(mobile_update)){
            $("#mobile_update").next("#brand-error").html("手机号必填");
            return;
        }else if(!/^1[3|4|5|7|8][0-9]\d{4,8}$/.test(mobile_update)){
            $("#mobile_update").next("#brand-error").html("请填写正确的手机号");
            return;
        }else if(mobile_update!=mobile_hidden){
            $.ajax({
                type : "get",
                url : dataPath + "/customer/salesman/isExitMobile.do",
                async : true,
                data : {"mobile" : mobile_update},
                dataType:'json',
                success : function(date) {
                    if (!date.success) {
                        $("#mobile_update").next("#brand-error").html(date.message);
                        flag = 1;
                        return;
                    } else {
                        flag = 0;
                        $("#mobile_update").next("#brand-error").html("");
                    }
                },
                error : function(date) {
                }
            });
        }else{
            flag = 0;
            $("#mobile_update").next("#brand-error").html("");
        }
    });
    $("#email").on("blur",function(){
        var email = $("#email").val().trim();
        if(!isNull(email)&&!/^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/.test(email)){
            $("#email").next("#brand-error").html("邮箱格式不正确");
            return;
        }else{
            $("#email").next("#brand-error").html("");
        }
    });
    $("#identity_card").on("blur",function(){
        var identity_card =$("#identity_card").val().trim();
        if(!isNull(identity_card)&&!/(^\d{14}([0-9]|x|X)$)|(^\d{17}([0-9]|x|X)$)/.test(identity_card)){
            $("#identity_card").next("#brand-error").html("身份证号码不正确");
            return;
        }else{
            $("#identity_card").next("#brand-error").html("");
        }
    });
    $("#btn-add").click(function(){
        var username = $("#username").val().trim(),mobile = $("#mobile").val().trim(),email = $("#email").val().trim(),identity_card =$("#identity_card").val().trim();
        if(isNull(username)){
            $("#username").next("#brand-error").html("业务员姓名必填");
            return;
        }else if(isNull(mobile)){
            $("#mobile").next("#brand-error").html("手机号必填");
            return;
        }else if(flag == 1){
            $("#mobile").next("#brand-error").html("该帐号已经存在");
            return;
        }else if(!isNull(identity_card)&&!/(^\d{14}([0-9]|x|X)$)|(^\d{17}([0-9]|x|X)$)/.test(identity_card)){
            $("#identity_card").next("#brand-error").html("身份证号码不正确");
            return;
        }else if(!isNull(email)&&!/^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/.test(email)){
            $("#email").next("#brand-error").html("邮箱格式不正确");
            return;
        }else{
            $.ajax({
                type : "get",
                url : dataPath + "/customer/salesman/saveSalesman.do?action=1",
                async : true,
                data : encodeURI($("#salesman_form").serialize()),
                dataType:'json',
                success : function(date) {
                    if (!date.success) {
                        alert(date.message);
                    } else {
                        location.href = dataPath + "/customer/salesman/SalesManView.do";
                    }
                },
                error : function(date) {
                }
            });
        }
        
    });
    
    $("#btn-update").click(function(){
    	
        var username = $("#username").val().trim(),mobile = $("#mobile_update").val().trim(),email = $("#email").val().trim(),identity_card =$("#identity_card").val().trim();
        if(isNull(username)){
            $("#username").next("#brand-error").html("业务员姓名必填");
            return;
        }else if(isNull(mobile)){
            $("#mobile_update").next("#brand-error").html("手机号必填");
            return;
        }else if(flag == 1){
            $("#mobile_update").next("#brand-error").html("该帐号已经存在");
            return;
        }else if(!isNull(identity_card)&&!/[\d]{6}(19|20)*[\d]{2}((0[1-9])|(11|12))([012][\d]|(30|31))[\d]{3}[xX\d]*/.test(identity_card)){
            $("#identity_card").next("#brand-error").html("身份证号码不正确");
            return;
        }else if(!isNull(email)&&!/^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/.test(email)){
            $("#email").next("#brand-error").html("邮箱格式不正确");
            return;
        }else{
	        $.ajax({
	            type : "get",
	            url : dataPath + "/customer/salesman/saveSalesman.do?action=2",
	            async : true,
	            data : encodeURI($("#salesman_form").serialize()),
	            dataType:'json',
	            success : function(date) {
	                if (!date.success) {
	                    alert(date.message);
	                } else {
	                    location.href = dataPath + "/customer/salesman/SalesListView.do?s=1";
	                }
	            },
	            error : function(date) {
	            }
	        });
      }
       
    });
});


function getArea(num){
	var pId = 0
	if(num=='1'){
		$("#shi").html('<option value="-1">全部</option>');
		$("#area").html('<option value="-1">全部</option>');
		pId = $("#sheng").val();
		$.post("${root}/Corner/Region/getRegionByPidOrRegionLevel.do",{pId:pId},function(data){
			if(data.success){
				var html = '<option value="-1">全部</option>';
				$.each(data.message,function(i,item){
					html += '<option value="'+item.id+'">'+item.name+'</option>';
				})
				$("#shi").html(html);
			}else{
				$("#shi").html('<option value="-1">全部</option>');
			}
		},'json');
	}else if(num=='2'){
		$("#area").html('<option value="-1">全部</option>');
		pId = $("#shi").val();
		$.post("${root}/Corner/Region/getRegionByPidOrRegionLevel.do",{pId:pId},function(data){
			if(data.success){
				var html = '<option value="-1">全部</option>';
				$.each(data.message,function(i,item){
					html += '<option value="'+item.id+'">'+item.name+'</option>';
				})
				$("#area").html(html);
			}else{
				$("#area").html('<option value="-1">全部</option>');
			}
		},'json');
	}
}
</script>
</body>
</html>