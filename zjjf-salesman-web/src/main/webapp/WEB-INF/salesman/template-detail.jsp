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
<body>
<div class="wrap-bd">
<div class="title mb-default" id="title">添加/编辑模板</div>
	<div class="op-section clearfix">
		<span>模板名称：</span>
		<input class="input-search-text" id="tmplName" type="text" value="${detailVo.tmplName}" readonly="readonly"/>
		<span>模板类型：</span>
		<input class="input-search-text" id="tmplType" type="text" value="${detailVo.tmplType}" readonly="readonly"/>
	</div>
    <div>
	  <form id="addTmplForm">
	    <input type ="hidden" id="tmplTotal" name="tmplTotal"/>
	    <input type ="hidden" id="tmplId" name="tmplId" value="${detailVo.tmplId}"/>
		<table class="table-list table-border">
			<thead class="table-thead">
				<tr class="table-header">
					<th>序号</th>
					<th>字段名称</th>
					<th>字段类型</th>
					<th>是否必填</th>
					<th>是否生效</th>
					<th>长度</th>
					<th>排序</th>
					<th>描述</th>
					<th><a onclick="addTrFun();">添加</a></th>
				</tr>
			</thead>
			<tbody id="tblBodyTr" class="table-tbody">
				<c:forEach items="${detailList}" var="detailList" varStatus="status">
					<tr>
						<td>${status.index+1}</td>
						<td>
							<input type="text" name="fieldCnName" value="${detailList.fieldCnName}" class="input-search-text">
						</td>
						<td>
							<select name="fieldType">
								<option value="1" <c:if test="${detailList.fieldType eq '1'}">selected="selected"</c:if>>字符</option>
								<option value="2" <c:if test="${detailList.fieldType eq '2'}">selected="selected"</c:if>>整数</option>
								<option value="3" <c:if test="${detailList.fieldType eq '3'}">selected="selected"</c:if>>小数</option>
							</select>
						</td>
						<td>
							<select name="isRequired">
								<option value="0" <c:if test="${detailList.isRequired eq '0'}">selected="selected"</c:if>>否</option>
								<option value="1" <c:if test="${detailList.isRequired eq '1'}">selected="selected"</c:if>>是</option>
							</select>
						</td>
						<td>
							<select name="isDelete">
								<option value="0" <c:if test="${detailList.isDelete eq '0'}">selected="selected"</c:if>>是</option>
								<option value="1" <c:if test="${detailList.isDelete eq '1'}">selected="selected"</c:if>>否</option>
							</select>
						</td>
						<td>
							<input type="text" name="length" value="${detailList.length}" class="input-search-date" style="width:80px;">
						</td>
						<td>
							<input type="text" name="sort" value="${detailList.sort}" class="input-search-date" style="width:50px;">
						</td>
						<td>
							<input type="text" name="description" value="${detailList.description}" class="input-search-text" style="width:360px;">
							<input type="hidden" name="tmplTrVals" value="">
						</td>
						<td onclick="delTrFun(this);">删除</td>
					</tr>
					</c:forEach>
			</tbody>
		</table>
	   </form>  		
	</div>
	<div id="submitDiv" style="margin-top: 20px;">
		<button class="button-save" id="submit">确定</button>
		<button class="button-cancel ml-default" id="cancelButton">取消</button>
	</div>
	<table id="tblTmplTr" style="display: none;">
		<tr>
			<td></td>
			<td>
				<input type="text" name="fieldCnName" class="input-search-text"/>
			</td>
			<td>
				<select name="fieldType">
					<option value="1">字符</option>
					<option value="2">整数</option>
					<option value="3">小数</option>
				</select>
			</td>
			<td>
				<select name="isRequired">
					<option value="0">否</option>
					<option value="1">是</option>
				</select>
			</td>
			<td>
				<select name="isDelete">
					<option value="0">是</option>
					<option value="1">否</option>
				</select>
			</td>
			<td>
				<input type="text" name="length" class="input-search-date" style="width:80px;">
			</td>
			<td>
				<input type="text" name="sort" class="input-search-date" style="width:50px;">
			</td>
			<td>
				<input type="text" name="description" class="input-search-text" style="width:360px;"/>
				<input type="hidden" name="tmplTrVals" value="" />
			</td>
			<td onclick="delTrFun(this);">删除</td>
		</tr>
	</table>
</div>
<script>
$('#submit').on('click',function() {
	//删除form表单内多余的空字段（切勿删除）
	$("#tmplTrVals").remove();
	
	var typeSelect = $("#typeSelect").val();
	var name = $("#name").val();
	var remarks = $("#remarks").val();
	var tmplTotal = 0 ,requiredTotal = 0;
	
	 var errorMsg = [];
	$("#tblBodyTr").find("tr").each(function(i){
		
	   var checkLineName = null,checkLineLength = null ,checkLineSort = null,checkLineDesc = null;
	   var isRequired = null,isDelete = null;
	   tmplTotal = i+1;
	   var tmplVal = [];
	   var bool = false;//该局部变量用于记录行是否有错
	   $(this).find("td").each(function(j){
		  
		   if(j==1){
			   var fieldCnName = $(this).find("input[name='fieldCnName']").val();
			   if(checkIsNULL(fieldCnName)){
				   bool = true;
				   checkLineName="字段名称不能为空! ";
			   }else{
				   var regex = /[^\u4e00-\u9fa5a-zA-Z0-9_]/ig;
				    if (regex.test(fieldCnName)){
					    bool = true;
					    checkLineName = "字段名称只能包含中英文、数字及下划线等字符! ";
					}else{
						
						if(fieldCnName.length>12){
						    bool = true;
						    checkLineName = "字段名称不能超过12个字符! ";
						}else{
							tmplVal.push(fieldCnName);
							tmplVal.push(",");
						}
					}
			   }
		   }
		   if(j==2){
			   var fieldType = $(this).find("select[name=fieldType]").val();
			   //alert(fieldType);
			   tmplVal.push(fieldType);
			   tmplVal.push(",");
		   }
		   
		   
		   if(j==3){
			   isRequired = $(this).find("select[name=isRequired]").val();
			   //alert(isRequired);
			   tmplVal.push(isRequired);
			   tmplVal.push(",");
		   }
		   if(j==4){
			   isDelete = $(this).find("select[name=isDelete]").val();
			   //alert(isDelete);
			   tmplVal.push(isDelete);
			   tmplVal.push(",");
		   }
		   if(j==5){
			   var length = $(this).find("input[name='length']").val();
			   //alert(length);
			   if(checkIsNULL(length)){
				   bool = true;
				   checkLineLength="长度值不能为空! ";
			   }else{
				   if(!/^[0-9]*$/.test(length)){  
					    bool = true;
					    checkLineLength = "长度值只能为正整数! ";
					}else{
						if(length<=0 || length>1000){
							bool = true;
							checkLineLength = "长度值必须介于0到1000之间! ";
						}
						tmplVal.push(length);
						tmplVal.push(",");
					}
			   }
		   }
		   if(j==6){
			   var sort = $(this).find("input[name='sort']").val();
			   if(checkIsNULL(sort)){
				   tmplVal.push("NULL");
				   tmplVal.push(",");
			   }else{
				   if(!/^[0-9]*$/.test(sort)){ 
					    bool = true;
					    checkLineSort = "排序值只能为正整数! ";
					}else{
						tmplVal.push(sort);
						tmplVal.push(",");
					}
			   }
			   
		   }
		   if(j==7){
			   var description = $(this).find("input[name='description']").val();
			   if(checkIsNULL(description)){
				   tmplVal.push("NULL");
			   }else{
				   
				   if(description.length>50){
					    bool = true;
					    checkLineDesc = "字段名称不能超过50个字符! ";
					}else{
						tmplVal.push(description);
					}
			   }
		   }
	   });
	   
	   //统计日志生效字段必须生效而且必填
	   //alert("isRequired="+isRequired+",isDelete="+isDelete);
	   if(isRequired=="1" && isDelete =="0"){
		   requiredTotal+=1;
		  // alert(requiredTotal);
	   }
	   
	   $(this).find("input[name='tmplTrVals']").val(tmplVal.join(""));
	    var tmplTrVals = $(this).find("input[name='tmplTrVals']").val();
	   // alert("第"+i+"行结果为："+tmplTrVals);
	   if(bool){
		   errorMsg.push("第"+(i+1)+"行:");
		   if(!checkIsNULL(checkLineName)){
			   errorMsg.push(checkLineName);
		   }
		   if(!checkIsNULL(checkLineLength)){
			   errorMsg.push(checkLineLength);
		   }
		   if(!checkIsNULL(checkLineSort)){
			   errorMsg.push(checkLineSort);
		   }
		   if(!checkIsNULL(checkLineDesc)){
			   errorMsg.push(checkLineDesc);
		   }
		   errorMsg.push("\n");
	   }
	   
	});
	
	$("#tmplTotal").val(tmplTotal);
	
	//如果表单数据一行都没有则添加一个默认的空串，防止接收数组报错
	if(tmplTotal<=0){
		$("#addTmplForm").append('<input type="hidden" id="tmplTrVals" name="tmplTrVals" value="NULL,NULL,NULL,NULL">');
	}
	
	var alertMsg = errorMsg.join("");
	if(!checkIsNULL(alertMsg)){
		alertMsg = alertMsg.substr(0,alertMsg.length-1);
		alert(alertMsg);
		return;
	}
	//如果必填项小于等于0时，提示必须有一项为必填
	if(requiredTotal<=0){
		alert("注意：模板字段必须有一列为必填且是生效的！");
		return;
	}
	//保存模板属性
	addTmplDetail();
   
});

function checkIsNULL(val){
	var bool = false;
	if(val == null || val.trim() == "" || val == "undefined"){
		bool = true;
	}
	return bool;
}

function addTmplDetail(){
	var tmplId = $("#tmplId").val();
	var tmplName = $("#tmplName").val();
	var tmplType = $("#tmplType").val();
	
	 $.ajax({
			type: "post",
			url: "${root}/tmpl/addTmplDetail.do",
			data: $('#addTmplForm').serialize(),
			dataType: "json",
			async: false,
			success: function(data) {
			 if(data.success){
					alert("保存成功!");
					location.href="${root}/tmpl/queryTpmlDetail.do?tmplId="+tmplId+"&tmplName="+tmplName+"&tmplType="+tmplType;
				}else{
					alert(data.message);
				} 
			},
			error : function(data) {
			}
		});
}

$('#cancelButton').on('click',function() {
	location.href="${root}/tmpl/index.do";
});

function delTrFun(obj){
	$(obj).parent().remove();
	resetSeq();
}

function addTrFun(){
	var tblTmplTr = $("#tblTmplTr").children().html();
	$("#tblBodyTr").append(tblTmplTr);
	resetSeq();
}

//刷新序号
function resetSeq(){
	$(".table-tbody").find("tr").each(function(index) { 
		$(this).find("td:eq(0)").html(index+1);
	});
}

</script>
</body>
</html>