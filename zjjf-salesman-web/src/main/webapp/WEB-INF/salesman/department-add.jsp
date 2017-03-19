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
<div class="title mb-default" id="title">添加部门</div>
<div class="wrap-bd" style="background: #fff">
	<div>
	  <form id="addDeptForm">
		<b>基本信息</b>
        <p>
            <label class="label" id="codeLabel">编号 <span style="color: red;">*</span>：</label>
            <input type="text" id="deptId" name="deptId" class="input-search-text">
        </p>
        <p>
            <label class="label" id="nameLabel">名称<span style="color: red;">*</span> ：</label>
            <input type="text" id="deptName" name="deptName" class="input-search-text">
        </p>
        <p>
            <label class="label" id="deptLabel">上级部门：</label>
            <select id="deptSelect" name="pid" style="width:209px;">
                <option value="">请选择</option>
                <c:forEach var="deptList" items="${deptList}">
              	  <option value="${deptList.deptId}">${deptList.deptName}</option>
                </c:forEach>
            </select>
        </p>
        <p>
           	<label class="label" id="codeLabel">所辖区域：</label>
            <select name="provinceId" style="width:209px;" onchange="getRegionInfo(this,'citySelect')">
                <option value="">省份</option>
                <c:forEach var="provinceList" items="${provinceList}">
              	  <option value="${provinceList.id}">${provinceList.name}</option>
                </c:forEach>
            </select>
            <select id="citySelect" name="cityId" style="width:209px;" onchange="getRegionInfo(this,'areaSelect')">
                <option value="">城市</option>
            </select>
            <select id="areaSelect" name="areaId" style="width:209px;">
                <option value="">区域</option>
            </select>
        </p>
        <p>
            <label class="label">备注：</label>
			<textarea rows="5" cols="50" id="remarks" name="remarks"></textarea>
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
	var deptSelect = $("#deptSelect").val();
	var deptId = $("#deptId").val();
	var deptName = $("#deptName").val();
	var remarks = $("#remarks").val();
	
	if(deptId =="" || deptId == null){
		alert("部门编码不能为空!");
		return;
	}
	if(deptName =="" ||deptName == null){
		alert("部门名称不能为空!");
		return;
	}
	
    var regExId = /^[a-zA-Z0-9_]{1,}$/; 
    if (!deptId.match(regExId)) { 
            alert("部门编号只能包含字母、数字及下划线等字符!"); 
            return false; 
    } 
	
	var regex = /[^\u4e00-\u9fa5a-zA-Z0-9_]/ig;
    if (regex.test(deptName))
    {
        alert("部门名称只能包含中英文、数字及下划线等字符！");
        return;
    }
	if(deptName.length>15){
	    alert("部门名称长度不能超过15个字符！");
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
			url: "${root}/dept/addDeptInfo.do",
			data: $('#addDeptForm').serialize(),
			dataType: "json",
			async: false,
			success: function(data) {
			 if(data.success) {
					alert("添加成功!");
					location.href="${root}/dept/index.do";
				} else {
					alert(data.message);
				} 
			},
			error : function(data) {
			}
		});
   
});

$('#cancelButton').on('click',function() {
	location.href="${root}/dept/index.do";
});

//弹出一个iframe层
$('#openLeaderList').on('click', function(){
	var deptId = $("#deptId").val();
    layer.open({
        type: 2,
        title: '',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['900px' , '620px'],
        content: '${root}/account/toUserList.do?deptId='+deptId
    });
});

//选择用户列表回调页面
function selectUserCallback(obj){
	$(".table-tbody").append(obj);
	layer.closeAll('iframe'); //关闭所有的iframe层
	resetSeq();
}
//刷新序号
function resetSeq(){
	$(".table-tbody").find("tr").each(function(index) { 
		$(this).find("td:eq(0)").html(index+1);
	});
}

//删除预添加列表中的用户信息
function delUser(obj){
	$(obj).parent().parent().remove()
    resetSeq();//刷新序号
}

function getRegionInfo(obj,objId){
	$.ajax({
		type: "post",
		url: "${root}/base/queryRegionList.do",
		data: 'pId='+$(obj).val(),
		dataType: "json",
		async: false,
		success: function(reObj) {
			//console.log(obj);
			if(reObj.success) {
				var selHtml = '';
				if(objId == 'citySelect'){
					selHtml = '<option value="">城市</option>';
				}else if(objId == 'areaSelect'){
					selHtml = '<option value="">区域</option>';
				} 
				$.each(reObj.list, function(i,item) {
	            	selHtml+='<option value="'+item.id+'">'+item.name+'</option>';
	            });
	            $("#"+objId).empty();
	            $("#"+objId).append(selHtml); 
	            
			} else {
				alert(reObj.msg);
			} 
		},
		error : function(data) {
		}
	});
}
</script>
</body>
</html>