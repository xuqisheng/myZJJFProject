<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>账户中心 - 账户设置</title>
    <%@ include file="../../common/head.jsp"%>
</head>
<body>
<div class="wrap-bd">
    <div class="mb-default">
        <span>当前位置：</span>
        <span class="crumb">账户中心</span>
        <span class="crumb crumb-active">账户设置</span>
    </div>
    <div class="wrap-bd bg">
        <form id="form">
            <select class="select hidden" name="whId">
                <c:forEach items="${warehouses}" var="ele">
                    <option value="${ele.id}" <c:if test="${ele.id == user.whId}">selected="selected" </c:if>>${ele.name}</option>
                </c:forEach>
            </select>
            <div>
                <label for="userName" class="label">账号：</label>
                <input type="text" name="userName" value="${user.userName}" id="userName" class="input" maxlength="50">
                <input type="hidden" name="oldUserName" value="${user.userName}" id="oldUserName" maxlength="50">
                <input type="hidden" name="id" value="${user.id}" id="id" maxlength="50">
            </div>
            <p>
                <label for="name" class="label">姓名：</label>
                <input type="text" name="name" id="name" value="${user.name}" class="input" maxlength="50">
            </p>
            <p>
                <label for="password" class="label">密码：</label>
                <input type="password" name="password" id="password" class="input" maxlength="50">
            </p>
            <div>
                <label class="label"></label>
                <input type="button" name="ok" value="确定" id="ok" class="button-save">
                <input type="reset" name="cancel" value="返回" id="cancel" class="button-cancel"  onclick="window.location.href=document.referrer;">
            </div>
        </form>
    </div>
</div>
</body>
<script>
    $(function () {
       $('#ok').on('click' , function () {
           if($('#userName').val() == null || $.trim($('#userName').val()) == '' ){
               alert("账号不能为空");
           }else if($('#name').val() == null || $.trim($('#name').val()) == '' ){
               alert("姓名不能为空");
           }else if(($('#password').val() == null || $.trim($('#password').val()) == '') && $.trim($('#id').val()) == '' ){
               alert("密码不能为空");
           }else{
               if($('#oldUserName').val() != $('#userName').val()){
                   $.post('${root}/erp/warehouse/checkUserName/'+$('#userName').val()+'.do',function (data) {
                       if(data.success){
                           $.post('${root}/erp/warehouse/addERPWarehouseUser/'+$('select[name="whId"]').val()+'.do',$('#form').serialize(),function (data) {
                               if(data.success){
                                   if($('#id').val() != '' && $('#id').val() != null){
                                       alert('修改成功，重新登陆生效');
                                   }else{
                                       alert('添加成功');
                                   }
                                   $('#cancel').trigger('click')
                               }else{
                                   alert(data.message);
                               }
                           },'json');
                       }else{
                           alert('用户名已使用');
                       }
                   },'json');
               }else{
                   $.post('${root}/erp/warehouse/addERPWarehouseUser/'+$('select[name="whId"]').val()+'.do',$('#form').serialize(),function (data) {
                       if(data.success){
                           if($('#id').val() != '' && $('#id').val() != null){
                               alert('修改成功，重新登陆生效');
                           }else{
                               alert('添加成功');
                           }
                           $('#cancel').trigger('click')
                       }else{
                           alert(data.message);
                       }
                   },'json');
               }
           }
       });
    });
</script>
</html>
