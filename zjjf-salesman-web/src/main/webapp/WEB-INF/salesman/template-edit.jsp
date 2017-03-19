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
    <script src="${root}/resources/js/comm.js"></script>
    <%@ include file="../common/autocomplete.jsp"%>
    <script>
    	var rootPath="${root}";
    </script>
</head>
<body class="wrap-bd">
<div class="title mb-default" id="title">添加/编辑部门</div>
<div class="wrap-bd" style="background: #fff">
	<div>
	  <form id="editTmplForm">
	  	<input type="hidden" name="id" value="${tmplInfo.id}"/>
		<b>基本信息</b>
        <p>
            <label class="label" id="codeLabel">标题<span style="color: red;">*</span>：</label>
            <input type="text" id="name" name="name" class="input-search-text" value="${tmplInfo.name}">
        </p>
        <p>
            <label class="label" id="deptLabel">类型<span style="color: red;">*</span>：</label>
            <select id="typeSelect" name="type" style="width:209px;">
                <option value="">请选择</option>
                <c:forEach var="tmplTypeList" items="${tmplTypeList}">
              	  <option value="${tmplTypeList.value}" <c:if test="${tmplTypeList.value==tmplInfo.type}">selected="selected"</c:if>>${tmplTypeList.label}</option>
                </c:forEach>
            </select>
        </p>
        <p>
            <label class="label">备注：</label>
			<textarea rows="5" cols="50" id="remarks" name="remarks">${tmplInfo.name}</textarea>
        </p>
	   </form>  		
	</div>
	<div id="submitDiv" style="margin-top: 20px;">
		<button class="button-save" id="submit">确定</button>
		<button class="button-cancel ml-default" id="cancelButton">取消</button>
	</div>
</div>
<script>
$('#submit').on('click',function() {
	var typeSelect = $("#typeSelect").val();
	var name = $("#name").val();
	var remarks = $("#remarks").val();
	
	if(name =="" || name == null){
		alert("模板名称不能为空!");
		return;
	}
	if(typeSelect =="" ||typeSelect == null){
		alert("模板类型不能为空!");
		return;
	}
	
	var regex = /[^\u4e00-\u9fa5a-zA-Z0-9_]/ig;
    if (regex.test(name))
    {
        alert("模板名称只能包含中英文、数字及下划线等字符！");
        return;
    }
	if(name.length>20){
	    alert("模板名称长度不能超过20个字符！");
	    return;
	}
	if(remarks.length>200){
		alert("备注长度不能超过200个字符！");
		return;
	}
	var regexRmk = /[^\u4e00-\u9fa5a-zA-Z0-9_,.，;；。！!？?\\、%]/ig;
    if (regexRmk.test(remarks))
    {
    	alert("备注只能包含中英文、数字、下划线及常用标点符号等字符！");
        return;
    }
    
	 $.ajax({
			type: "post",
			url: "${root}/tmpl/updateTmplInfo.do",
			data: $('#editTmplForm').serialize(),
			dataType: "json",
			async: false,
			success: function(data) {
			 if(data.success) {
					alert("修改成功!");
					location.href="${root}/tmpl/index.do";
				} else {
					alert(data.message);
				} 
			},
			error : function(data) {
			}
		});
   
});

$('#cancelButton').on('click',function() {
	location.href="${root}/tmpl/index.do";
});

</script>
</body>
</html>