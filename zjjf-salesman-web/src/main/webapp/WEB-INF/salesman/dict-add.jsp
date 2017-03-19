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
<div class="title mb-default" id="title">添加/编辑字典</div>
<div class="wrap-bd" style="background: #fff">
	<div>
	  <form id="dictForm">
	  	<input type="hidden" name="timeHzType" id="timeHzType">
	  	<input type="hidden" name="timeHzVal" id="timeHzVal">
		<b>基本信息</b>
        <p>
            <label class="label" id="codeLabel">键值 <span style="color: red;">*</span>:</label>
            <input type="text" id="value" name="value"  class="input-search-text">
        </p>
        <p>
            <label class="label" id="nameLabel">标签<span style="color: red;">*</span>:</label>
            <input type="text" id="label" name="label" class="input-search-text">
        </p>
        <p>
            <label class="label" id="deptLabel">类型<span style="color: red;">*</span>:</label>
            <input type="text" id="type" name="type" value="${dictVo.type}" class="input-search-text">
        </p>
        <p>
            <label class="label" id="deptLabel">排序:</label>
            <input type="text" id="sort" name="sort" value="${dictVo.sort}" class="input-search-text">
        </p>
        <p>
            <label class="label">描述：</label>
			<textarea rows="5" cols="50" id="description" name="description"></textarea>
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
	var value = $("#value").val();
	var label = $("#label").val();
	var type = $("#type").val();
	var sort = $("#sort").val();
	//var remarks = $("#remarks").val();
	var description = $("#description").val();
	
	if(value =="" || value == null){
		alert("键值不能为空!");
		return;
	}
	if(label =="" ||label == null){
		alert("标签不能为空!");
		return;
	}
	if(type =="" ||type == null){
		alert("类型不能为空!");
		return;
	}
	
	var regex = /[^\u4e00-\u9fa5a-zA-Z0-9_]/ig;
    if (regex.test(value))
    {
    	alert("键值只能包含中英文、数字及下划线等字符！");
        return;
    }
    if (regex.test(label))
    {
        alert("标签只能包含中英文、数字及下划线等字符！");
        return;
    }
    if (regex.test(type))
    {
        alert("类型只能包含中英文、数字及下划线等字符！");
        return;
    }
	if(sort != null && sort !=""){
		var regex = new RegExp("^[0-9]*$");
	    if (!regex.test(sort)){
	        alert("排序字段只允许输数字！");
	        return;
	    }
	}
	
	if(value.length>20){
	    alert("键值长度不能超过20个字符！");
	    return;
	}
	if(label.length>20){
	    alert("标签长度不能超过20个字符！");
	    return;
	}
	if(type.length>20){
	    alert("类型长度不能超过20个字符！");
	    return;
	}
	/* if(remarks.length>200){
		alert("备注长度不能超过200字符！");
		return;
	} */
	if(description.length>200){
		alert("描述长度不能超过200字符！");
		return;
	}
	var regexRmk = /[^\u4e00-\u9fa5a-zA-Z0-9_,.，;；。！!？?\\、%]/ig;
    /* if (regexRmk.test(remarks))
    {
    	alert("备注只能包含中英文、数字、下划线及常用标点符号等！");
        return;
    } */
    if (regexRmk.test(description))
    {
    	alert("描述只能包含中英文、数字、下划线及常用标点符号等！");
        return;
    }
    
    
    //========兼容修改时候借助的timeHzType、timeHzVal属性 start======
	if(type == "time_hz_type" && label == "time_hz_val"){
		$("#timeHzType").val("time_hz_type");
		$("#timeHzVal").val("time_hz_val");
	}
    //========兼容修改时候借助的timeHzType、timeHzVal属性 end========
	 $.ajax({
			type: "post",
			url: "${root}/dict/addDict.do",
			data: $('#dictForm').serialize(),
			dataType: "json",
			async: false,
			success: function(data) {
			 if(data.success) {
					alert("添加成功!");
					location.href="${root}/dict/index.do";
				} else {
					alert(data.message);
				} 
			},
			error : function(data) {
			}
		});
   
});

$('#cancelButton').on('click',function() {
	location.href="${root}/dict/index.do";
});
</script>
</body>
</html>