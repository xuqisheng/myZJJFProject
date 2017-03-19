<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="../common/header.jsp"></jsp:include>
    <script src="${root }/resources/js/User/Add.js" type="text/javascript"></script>
     <style type="text/css">
         body{ font-size:12px;}
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
    </style>
</head>
<body style="padding:10px">
<div id="pageloading"></div> 
    <form name="userFrom" method="post"  id="userFrom" class="ligerForm" action="${root }/User/Add.do">
        <table cellpadding="0" cellspacing="0" class="l-table-edit" >
            <tr>
                <td align="right" class="l-table-edit-td">登录名称:</td>
                <td align="left" class="l-table-edit-td" style="width:160px">
                	<input name="loginName" type="text" id="txtLoginName" ltype="text" validate="{required:true,minlength:3,maxlength:10}" />
                </td>
                <td align="left"></td>
                <td align="right" class="l-table-edit-td">姓名:</td>
                <td align="left" class="l-table-edit-td" style="width:160px">
                	<input name="realName" type="text" id="txtRealName" ltype="text" validate="{required:true,minlength:3,maxlength:10}" />
                </td>
                <td align="left"></td>
            </tr> 
            
            <tr>
                <td align="right" class="l-table-edit-td" valign="top">性别:</td>
                <td align="left" class="l-table-edit-td" style="width:160px">
                    <input id="rbtnl_0" type="radio" name="sex" value="0" checked="checked" />
                    <label for="rbtnl_0">男</label> 
                    <input id="rbtnl_1" type="radio" name="sex" value="1" />
                    <label for="rbtnl_1">女</label>
                </td>
                <td align="left"></td>
                  <td align="right" class="l-table-edit-td">邮箱:</td>
                  <td align="left" class="l-table-edit-td" style="width:160px">
                	<input name="email" type="text" id="txtEmail" ltype="text" validate="{required:true,email:true}" />
                 </td>
                <td align="left"></td>
            </tr>
               
            <tr>
                <td align="right" class="l-table-edit-td">电话号码:</td>
                <td align="left" class="l-table-edit-td" style="width:160px">
                	<input name="telPhone" type="text" id="txtMobile" ltype="text" validate="{required:true,minlength:3,maxlength:10}" />
                </td>
                <td align="left"></td>
                <td align="right" class="l-table-edit-td">手机:</td>
                <td align="left" class="l-table-edit-td" style="width:160px">
                	<input name="phone" type="text" id="txtPhone" ltype="text" validate="{required:true,minlength:3,maxlength:10}" />
                </td>
                <td align="left"></td>
            </tr> 
            
             <tr>
                <td align="right" class="l-table-edit-td">所属部门:</td>
                <td align="left" class="l-table-edit-td" style="width:180px">
	                <input type="text" name="departmentId_" id="selectDepartment" />
	                <input type="hidden" name="departmentId" id="departmentId"> 
                </td>
                <td align="left"></td>
                <td align="right" class="l-table-edit-td">状态:</td>
                <td align="left" class="l-table-edit-td" style="width:180px">
	              <select name="state" id="ddlState" ltype="select">
					<option value="0" selected="selected">启用</option>
					<option value="1">禁用</option>
				</select>
                </td>
                <td align="left"></td>
            </tr>
            
            <tr>
                <td align="right" class="l-table-edit-td">地址:</td>
                <td align="left" class="l-table-edit-td" colspan="4"> 
                	<textarea cols="100" rows="4" class="l-textarea" id="address" name="address" style="width:420px" validate="{required:true}" ></textarea>
                </td> 
                <td align="left"></td>
            </tr>
        </table>
 		<br />
		<input type="submit" value="提交" id="btnSubmit"  style="display: none;" class="l-button l-button-submit" />
		<input type="button" value="测试" class="l-button l-button-test" name="cancle"  style="display: none;"/>
    </form>
    <div style="display:none"><!--  数据统计代码 --></div>
</body>
</html>