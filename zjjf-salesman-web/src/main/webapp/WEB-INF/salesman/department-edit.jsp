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
    <style type="text/css">
    	a{cursor:hand;}
    </style>
</head>
<body class="wrap-bd">
<div class="title mb-default" id="title">编辑部门</div>
<div class="wrap-bd" style="background: #fff">
	<div>
	  <form id="editDeptForm">
	  	<input type="hidden" id="tmpId" name="id" value="${dept.id}">
	  	<input type="hidden" id="deptId" name="deptId" value="${dept.deptId}">
	  	<input type="hidden" id="tmpProvinceId" name="oldProvinceId"  value="${dept.provinceId}">
	  	<input type="hidden" id="tmpCityId" name="oldCityId" value="${dept.cityId}">
	  	<input type="hidden" id="tmpAreaId" name="oldAreaId" value="${dept.areaId}">
		<b>基本信息</b>
        <p>
            <label class="label" id="codeLabel">编号 <span style="color: red;">*</span>：</label>
            <input type="text" name="deptId" value="${dept.deptId}" class="input-search-text" disabled="disabled">
        </p>
        <p>
            <label class="label" id="nameLabel">名称<span style="color: red;">*</span> ：</label>
            <input type="text" id="deptName" name="deptName" value="${dept.deptName}" class="input-search-text">
        </p>
        <p>
            <label class="label" id="deptLabel">上级部门：</label>
            <select id="deptSelect" name="pid" style="width:209px;">
                <option value="">请选择</option>
                <c:forEach var="deptList" items="${deptList}">
              	  <option value="${deptList.deptId}" <c:if test="${deptList.deptId==dept.pid}">selected="selected"</c:if> >${deptList.deptName}</option>
                </c:forEach>
            </select>
        </p>
        <p>
           	<label class="label" id="codeLabel">所辖区域：</label>
            <select id="provinceSelect" name="provinceId" style="width:209px;" onchange="getRegionInfo(this,'citySelect',0)">
                <option value="">省份</option>
                <c:forEach var="provinceList" items="${provinceList}">
              	  <option value="${provinceList.id}">${provinceList.name}</option>
                </c:forEach>
            </select>
            <select id="citySelect" name="cityId" style="width:209px;" onchange="getRegionInfo(this,'areaSelect',0)">
                <option value="">城市</option>
            </select>
            <select id="areaSelect" name="areaId" style="width:209px;" onchange="setSpGroupListDisplay(this);">
                <option value="">区域</option>
            </select>
        </p>
        
        <p>
            <label class="label">备注：</label>
			<textarea rows="5" cols="50" id="remarks" name="remarks" value="${dept.remarks}">${dept.remarks}</textarea>
        </p>
       <p>
			<label class="label">部门领导：</label>
            <a style="margin-left: 10px;cursor:hand;" id="openLeaderList" href="#">添加</a>
			<table class="table-list table-border">
				<thead class="table-thead">
					<tr class="table-header">
	            		<th>序号</th>
	            		<th>账号</th>
	            		<th>姓名</th>
	            		<th>部门</th>
	            		<th>职位</th>
	            		<th>操作</th>
					</tr>
				</thead>
				<tbody class="table-tbody">
					<c:forEach var="list" items="${leaderList}" varStatus="status">
						<tr>
							<td>
								${status.index+1}
							</td>
							<td>
								${list.mobile}
								<input type="hidden" name="leaderId" value="${list.id}"/>
							</td>
							<td>${list.userName}</td>
							<td>${list.deptName}</td>
							<td>${list.postName}</td>
							<td><a data-user="${list.userName}" onclick="delUser(this);" style="cursor:hand;" >删除</a></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
        </p> 

	   </form>  
	   </di>
	   
	   <div style="margin-top:50px;">
	   <p>
			<label class="label">定格管理：</label>
<!-- 			<a style="margin-left: 10px;" href="#" onclick="toSpGroupAddPage();">添加定格</a> -->
			<a style="margin-left: 10px;cursor:hand;" href="#" onclick="syncSpGroupData();">同步定格</a>
			<table class="table-list table-border">
				<thead class="table-thead">
					<tr class="table-header">
						<th>定格编码</th>
						<th>定格名称</th>
					</tr>
				</thead>
				<tbody class="table-tbody">
					<c:forEach var="list" items="${spgList}" varStatus="status">
						<tr>
							<td>
								${list.spGroupId}
							</td>
							<td>
								${list.spGroupName}
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</p>
	</div>
	<div id="submitDiv" style="margin-top: 20px;">
		<button class="button-save" id="submit">确定</button>
		<button class="button-cancel ml-default" id="cancelButton">取消</button>
	</div>
</div>
<script>

/**
 * 如果原始的区域ID 同当前的区域ID 不等时，隐藏uanl
 */
function setSpGroupListDisplay(obj){
	var areaId = $("#areaSelect").val();
	var tmpAreadId = $("#tmpAreaId").val();
	if(areaId != tmpAreadId){
		$(".table-tbody").eq(1).hide();
	}else if(areaId == tmpAreadId){
		$(".table-tbody").eq(1).show();
	}
}

/**
 * 跳转到定格添加页面
 */
function toSpGroupAddPage(){
	var provinceId = $("#provinceSelect").val();
	var cityId = $("#citySelect").val();
	var areaId = $("#areaSelect").val();
	location.href="${root}/spgLine/toAddPage.do?provinceId="+provinceId+"&cityId="+ cityId + "&areaId="+areaId;
}

function editSpGroupLine(obj){
	var provinceId = $("#provinceSelect").val();
	var cityId = $("#citySelect").val();
	var areaId = $("#areaSelect").val();
	var deptId = $("#deptId").val();
	
	var spGroupId = $(obj).data("groupid");
	location.href="${root}/spgLine/toEditPage.do?provinceId="+provinceId
		+"&cityId="+ cityId + "&areaId="+areaId+"&spGroupId="+spGroupId+"&deptId="+deptId;
}

function syncSpGroupData(){
	var id = $("#tmpId").val();
	var deptId = $("#deptId").val();
	var provinceId = $("#provinceSelect").val();
	var cityId = $("#citySelect").val();
	var areaId = $("#areaSelect").val();
	var hisProvinceId = $("#tmpProvinceId").val();
	var hisCityId = $("#tmpCityId").val();
	var hisAreaId = $("#tmpAreaId").val();
	
	if(provinceId == null || provinceId ==""){
		alert("省份不能为空！");
		return;
	}
	if(cityId == null || cityId ==""){
		alert("城市不能为空！");
		return;
	}
	if(areaId == null || areaId ==""){
		alert("区域不能为空！");
		return;
	}
	
	$.ajax({
		type: "post",
		url: "${root}/dept/editDeptInfo.do",
		data: $('#editDeptForm').serialize(),
		dataType: "json",
		async: false,
		success: function(data) {
		 if(data.success) {
			 $.ajax({
					type: "post",
					url: "${root}/spgLine/syncSpGroupData.do",
					data: "provinceId="+provinceId+"&cityId="+ cityId + "&areaId="+areaId+"&deptId="+deptId
						+"&hisProvinceId="+hisProvinceId+"&hisCityId="+hisCityId+"&hisAreaId="+hisAreaId,
					dataType: "json",
					async: false,
					success: function(data) {
					 if(data.success) {
							alert("操作成功!");
							location.href="${root}/dept/toEditPage.do?id="+id+"&deptId="+deptId;
						} else {
							alert(data.message);
						} 
					},
					error : function(data) {
					}
				});
			} else {
				alert(data.message);
			} 
		},
		error : function(data) {
		}
	});
	
	
}

/**
 * 修改部门信息
 */
function updateDeptData(){
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
		alert("备注长度不能超过200字符！");
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
			url: "${root}/dept/editDeptInfo.do",
			data: $('#editDeptForm').serialize(),
			dataType: "json",
			async: false,
			success: function(data) {
			 if(data.success) {
					alert("修改成功!");
					location.href="${root}/dept/index.do";
				} else {
					alert(data.message);
				} 
			},
			error : function(data) {
			}
		});
}

$('#submit').on('click',function() {
	//修改部门信息
	updateDeptData();
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
	$(".table-tbody").eq(0).append(obj);
	layer.closeAll('iframe'); //关闭所有的iframe层
	resetSeq();
}
//刷新序号
function resetSeq(){
	$(".table-tbody").eq(0).find("tr").each(function(index) { 
		$(this).find("td:eq(0)").html(index+1);
	});
}

//删除预添加列表中的用户信息
function delUser(obj){
	var user = $(obj).data("user");
	/* layer.confirm('您确定要将'+user+'删除吗?', {icon: 3, title:'提示'}, function(index){
	    //do something
	    $(obj).parent().parent().remove()
	    resetSeq();//刷新序号
	    layer.close(index);
	}); */
	if(confirm("您确定要将"+user+"删除吗?") == true) {
		$(obj).parent().parent().remove()
	    resetSeq();//刷新序号
	}
}

function getRegionInfo(obj,objId,opType){
	//根据操作类型决定取值
	var pid = opType==0?$(obj).val():obj;
	$.ajax({
		type: "post",
		url: "${root}/base/queryRegionList.do",
		data: 'pId='+pid,
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

$(document).ready(function(){
	  
	var provinceId = $("#tmpProvinceId").val();
	var cityId = $("#tmpCityId").val();
	var areaId = $("#tmpAreaId").val();
	$("#provinceSelect").val(provinceId);
	//如果省份ID不为空，则查询对应省份对应城市
	if(provinceId != null && provinceId !=""){
		//alert("provinceId="+provinceId);
		getRegionInfo(provinceId,"citySelect",1);
		$("#citySelect").val(cityId);
	}
	//如果省份ID不为空，则查询对应省份对应城市
	if(cityId != null && cityId !=""){
		//alert("cityId="+cityId);
		getRegionInfo(cityId,"areaSelect",1);
		$("#areaSelect").val(areaId);
	}
	
	// 如果身份数据改变的时候清空城市和区域的值
	$("#provinceSelect").change( function() {
		$("#citySelect").val("");
		$("#areaSelect").val("");
	});
});

</script>
</body>
</html>