<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>APP版本管理</title>
    <%@ include file="../common/head.jsp"%>
	<script src="${root}/resources/vendor/jquery/ajaxfileupload.js"></script>
    <script src="${root }/resources/vendor/My97DatePicker/WdatePicker.js"></script>
    <script src="${root }/resources/js/comm.js"></script>
</head>
<body class="wrap-bd">
<div class="mb-small clearfix">
<form action="${root }/Kefu/systemVersion/getAllVersionInfo.do" method="post">
	<div class="fl">
        <label>版本号：</label>
        <input type="text" name="versionNo" value="${versionRo.versionNo }" class="input input-search-text">
        <label class="ml-default">发布时间：</label>
        <input type="text" name="beginTime" value="<fmt:formatDate value="${versionRo.beginTime }" pattern="yyyy-MM-dd"/>" class="input input-date" id="J_timeS"> -
        <input type="text" name="endTime" value="<fmt:formatDate value="${versionRo.endTime }" pattern="yyyy-MM-dd"/>" class="input input-date" id="J_timeE">
        <input type="submit" name="" value="搜索" class="input input-search-button ml-default">
    </div>
    <div class="fr">
    	<input type="hidden" id="filePath"/>
        <span class="button button-default" id="J_addVersion">新增版本</span>
    </div>
</form>
</div>
<div>
    <table class="table-list">
        <thead>
        <tr>
            <th>序号</th>
            <th>版本号</th>
            <th>是否强制更新</th>
            <th>iOS最低系统版本</th>
            <th>安卓最低系统版本</th>
            <th>发布时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="J_tableBbody">
        <c:choose>
        	<c:when test="${versionVoList != null && versionVoList.size() > 0  }">
        		 <c:forEach var="versionVo" items="${versionVoList }" varStatus="var">
			        <tr>
			            <td>${var.index+1 }</td>
			            <td>${versionVo.versionNo }</td>
			            <td>
			            	<c:choose>
			            		<c:when test="${versionVo.status==1 }">
			            			<span class="txt-warn">是</span>
			            		</c:when>
			            		<c:otherwise>
			            			<span>否</span>
			            		</c:otherwise>
			            	</c:choose>
			            </td>
			            <td>${versionVo.iosLowest }</td>
			            <td>${versionVo.androidLowest }</td>
			            <td><fmt:formatDate value="${versionVo.publishTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			            <td>
			            	<span href="" class="button button-operate J_query">查看<input type="hidden" name="queryId" value="${versionVo.id }"></span>
			                <span href="" class="button button-operate J_edit">编辑<input type="hidden" name="editId" value="${versionVo.id }"></span>
			                <c:choose>
			            		<c:when test="${versionVo.status==1 }">
			            			 <span onclick="updateStatus('${versionVo.id}','0')" class="button button-operate">停用</span>
			            		</c:when>
			            		<c:otherwise>
			            			 <span onclick="updateStatus('${versionVo.id}','1')" class="button button-operate disabled">启用</span>
			            		</c:otherwise>
			            	</c:choose>
			            </td>
			        </tr>
			     </c:forEach>
        	</c:when>
        	<c:otherwise>
        		 <tr>
		            <td colspan="7">无数据</td>
		        </tr>
        	</c:otherwise>
        </c:choose>
        </tbody>
    </table>
</div>
<c:if test="${fn:length(versionVoList)>0}">
	<%@ include file="../common/pagination-kk.jsp"%>
</c:if>
<script >
	function updateStatus(id,status){
		if(id != null && id != '' && status != null && status != ''){
			$.post("${root}/Kefu/systemVersion/updateVersionInfoStatusById.do",{id:id,status:status},function(data){
				if(data.success){
					location.href = "${root}/Kefu/systemVersion/getAllVersionInfo.do";
				}
			},'json');
		}
	}
</script>
<div class="dialog hidden" id="J_dialog">
    <!-- <div class="dialog-head"></div>
    <div class="dialog-body">
    	<input type="hidden" id="id" name="id" >
        <div class="required">
            <label class="label">版本号</label>
            <input type="text" id="versionNo" name="versionNo" value="" class="input input-default">
        </div>
        <p>
            <label class="label">版本编码</label>
            <input type="text" id="versionCode" name="versionCode" value="" class="input input-default">
        </p>
        <p class="required">
            <label class="label">是否可用</label>
            <input type="radio" id="shi" name="sfky" value="1" checked="checked"> 是
            <input type="radio" id="fou" name="sfky" value="0"> 否
        </p>
        <p class="required">
            <label class="label">iOS最低版本</label>
            <input type="text" id="iosLowest" name="iosLowest" value="" class="input input-default">
        </p>
        <p class="required">
            <label class="label">安卓最低版本</label>
            <input type="text" id="androidLowest" name="androidLowest" value="" class="input input-default">
        </p>
        <p class="required">
            <label class="label">版本更新说明</label>
            <textarea id="versionExplain" name="versionExplain" value="" class="textarea textarea-default"></textarea>
        </p>
        <p class="required">
            <label class="label">发布时间</label>
            <input type="text" id="publishTime" name="publishTime" value="" class="input input-default">
        </p>
        <div>
            <label class="label">安卓版上传</label>
            <input type="file" id="file" name="file" class="input input-default" >
            <label id="la"></label>
            <input type="hidden" id="fileLocation" name="fileLocation" value="">
        </div>
    </div>
    <div class="dialog-foot">
        <input type="button" value="确认" class="dialog-button dialog-ok" id="dialog-ok">
        <input type="button" value="取消" class="dialog-button dialog-cancel" id="dialog-cancel">
    </div> -->
</div>
<div class="cover-all"></div>
<script>
    $(function() {
    	//文件上传
    	$("html, body").on("change", "#file", function(){
     		var fileVal = $("#file").val();
     		var suffix = fileVal.substring(fileVal.lastIndexOf('.')+1,fileVal.length);
    		if(suffix != 'apk'){
    			alert("请选择后缀名为.apk的文件！");
    			$("#file").val("");
    			return false;
    		}
    	 	$.ajaxFileUpload({
    			url : "${root}/Kefu/systemVersion/upload.do",
    			secureuri : false,
    			fileElementId : 'file',
    			dataType : 'text',
    			success : function(data, status) {
    				if (data == "错误") {
    					return false;
    				}
    				alert("上传成功");
    				var resultData = data.substring(1, data.length-1);
    				$("#fileLocation").val(resultData);
    				$("#la").text("已上传");
    			},
    			error : function(data, status, e) {
    				alert('上传出错');
    				$("#send-img").html("上传失败");
    			} 
    		});
    	}); 
    	
        dateRange('#J_timeS', '#J_timeE', 1);
        
        /* 新增版本 */
        $('#J_addVersion').on('click', function() {
            var html = '<div class="dialog-head">新增版本</div>';
            html += '<div class="dialog-body">';
            html += '<div class="dialog-padding">';
    		html += '<div class="required">';
    		html += '<label class="label">版本号</label>';
    		html += '<input type="text" id="versionNo" name="versionNo" value="" class="input input-default">';
    		html += '</div>';
    		html += '<p class="required">';
    		html += '<label class="label">版本编码</label>';
			html += '<input type="text" id="versionCode" name="versionCode" value="" class="input input-default">';
    		html += '</p>';
    		html += '<p >';
    		html += '<label class="label">是否强制更新</label>';
    		html += '<input type="radio" id="shi" name="sfky" value="1" checked="checked" class="radio"> 是';
    		html += '<input type="radio" id="fou" name="sfky" value="0" class="radio ml-default"> 否';
    		html += '</p>';
    		html += '<p class="required">';
    		html += '<label class="label">iOS最低版本</label>';
    		html += '<input type="text" id="iosLowest" name="iosLowest" value="" class="input input-default">';
    		html += '</p>';
    		html += '<p class="required">';
    		html += '<label class="label">安卓最低版本</label>';
    		html += '<input type="text" id="androidLowest" name="androidLowest" value="" class="input input-default">';
    		html += '</p>';
    		html += '<p class="required">';
    		html += '<label class="label">版本更新说明</label>';
    		html += '<textarea id="versionExplain" name="versionExplain" value="" class="textarea textarea-default"></textarea>';
    		html += '</p>';
    		html += '<p class="required">';
    		html += '<label class="label">发布时间</label>';
    		html += '<input type="text" id="publishTime" name="publishTime" value="" class="input input-default">';
    		html += '</p>';
    		html += '<div>';
    		html += '<label class="label">安卓版上传</label>';
    		html += '<input type="file" id="file" name="file" class="input input-default" accept=".apk">';
    		html += '<label id="la"></label>';
    		html += '<input type="hidden" id="fileLocation" name="fileLocation" value="">';
    		html += '</div>';
    		html += '</div>';
            html += '</div>';
    		html += '<div class="dialog-foot">';
    		html += '<input type="button" value="确认" class="dialog-button dialog-ok" id="dialog-ok"> ';
    		html += '<input type="button" value="取消" class="dialog-button dialog-cancel" id="dialog-cancel">';
    		html += '</div>';
    		$('#J_dialog').html(html);
    		dialogPosCenter('#J_dialog');
    		dateRange('#publishTime');
            $('#J_dialog, .cover-all').show();
        });
        /* 编辑版本 */
        $('#J_tableBbody').on('click', '.J_edit', function() {
            var id = $(this).children("[name=editId]").val();
            //回写数据
            $.post("${root}/Kefu/systemVersion/getVersionInfoById.do",{id:id},function(data){
            	if(data.success){
					var html = '<div class="dialog-head">编辑版本</div>';
            		html += '<div class="dialog-body">';
            		html += '<input type="hidden" id="id" name="id" value="'+data.message.id+'">';
            		html += '<div class="required">';
            		html += '<label class="label">版本号</label>';
            		html += '<input type="text" id="versionNo" name="versionNo" value="'+data.message.versionNo+'" class="input input-default">';
            		html += '</div>';
            		html += '<p  class="required">';
            		html += '<label class="label">版本编码</label>';
        			html += '<input type="text" id="versionCode" name="versionCode" value="'+data.message.versionCode+'" class="input input-default">';
            		html += '</p>';
            		html += '<p>';
            		html += '<label class="label">是否强制更新</label>';
            		if(data.message.status==1){
            			html += '<input type="radio" id="shi" name="sfky" value="1" checked="checked"> 是';
                		html += '<input type="radio" id="fou" name="sfky" value="0" class="ml-default"> 否';
            		}else{
            			html += '<input type="radio" id="shi" name="sfky" value="1"> 是';
                		html += '<input type="radio" id="fou" name="sfky" value="0" checked="checked" class="ml-default"> 否';
            		}
            		html += '</p>';
            		html += '<p class="required">';
            		html += '<label class="label">iOS最低版本</label>';
            		html += '<input type="text" id="iosLowest" name="iosLowest" value="'+data.message.iosLowest+'" class="input input-default">';
            		html += '</p>';
            		html += '<p class="required">';
            		html += '<label class="label">安卓最低版本</label>';
            		html += '<input type="text" id="androidLowest" name="androidLowest" value="'+data.message.androidLowest+'" class="input input-default">';
            		html += '</p>';
            		html += '<p class="required">';
            		html += '<label class="label">版本更新说明</label>';
                    html += '<textarea id="versionExplain" name="versionExplain" value="" class="textarea textarea-default">'+data.message.versionExplain+'</textarea>';
            		html += '</p>';
            		html += '<p class="required">';
            		html += '<label class="label">发布时间</label>';
            		html += '<input type="text" id="publishTime" name="publishTime" value="'+data.message.publishTime+'" class="input input-default">';
            		html += '</p>';
            		if(data.message.fileName != null && data.message.fileName != ''){
            			html += '<p >';
                		html += '<label class="label">安卓版本：</label>';
                		html +=data.message.fileName;
                		html += '</p>';
            		}
            		html += '<div>';
            		html += '<label class="label">安卓版上传</label>';
            		html += '<input type="file" id="file" name="file" value="'+data.message.fileName+'" class="input input-default" accept=".apk">';
            		if(data.message.fileLocation !=null && data.message.fileLocation != ''){
            			html += '<label id="la">已上传</label>';
            		}else{
            			html += '<label id="la">未上传</label>';
            		}
            		html += '<input type="hidden" id="fileLocation" name="fileLocation" value="">';
            		html += ' </div>';
            		html += '</div>';
            		html += '<div class="dialog-foot">';
            		html += '<input type="button" value="确认" class="dialog-button dialog-ok" id="dialog-ok"> ';
            		html += '<input type="button" value="取消" class="dialog-button dialog-cancel" id="dialog-cancel">';
            		html += '</div>';
            		$('#J_dialog').html(html);
            		
            		dialogPosCenter('#J_dialog');
            		dateRange('#publishTime');
            		$('#J_dialog, .cover-all').show();
            		$('#fileLocation').val(data.message.fileLocation);
            	}
            },'json');
            
        });
        /* 版本信息 */
        $('#J_tableBbody').on('click', '.J_query', function() {
            var id = $(this).children("[name=queryId]").val();
          	//回写数据
            $.post("${root}/Kefu/systemVersion/getVersionInfoById.do",{id:id},function(data){
				if(data.success){
            		var html = '<div class="dialog-head">版本信息</div>';
            		html += '<div class="dialog-body">';
            		html += '<div>';
            		html += '<label class="label">版本号:</label>';
            		html += ''+data.message.versionNo+'';
            		html += '</div>';
            		html += '<p>';
            		html += '<label class="label">版本编码:</label>';
        			html += ''+data.message.versionCode+'';
            		html += '</p>';
            		html += '<p>';
            		html += '<label class="label">是否强制更新:</label>';
            		if(data.message.status==1){
            			html += '<span>是</sqan>';
            		}else{
            			html += '<span>否</sqan>';
            		}
            		html += '</p>';
            		html += '<p>';
            		html += '<label class="label">iOS最低版本:</label>';
            		html += ''+data.message.iosLowest+'';
            		html += '</p>';
            		html += '<p>';
            		html += '<label class="label">安卓最低版本:</label>';
            		html += ''+data.message.androidLowest+'';
            		html += '</p>';
            		html += '<p>';
            		html += '<label class="label">版本更新说明:</label>';
            		html += ''+data.message.versionExplain+'';
            		html += '</p>';
            		html += '<p>';
            		html += '<label class="label">发布时间:</label>';
            		html += ''+data.message.publishTime+'';
            		html += '</p>';
            		html += '<div>';
            		html += '<label class="label">安卓版上传:</label>';
            		html += data.message.fileName;
            		if(data.message.fileLocation !=null && data.message.fileLocation != ''){
            			html += '&nbsp;&nbsp;<label id="la">已上传</label>';
            		}else{
            			html += '&nbsp;&nbsp;<label id="la">未上传</label>';
            		}
            		html += ' </div>';
            		html += '</div>';
            		html += '<div class="dialog-foot">';
            		html += '<a href="#" class="dialog-button dialog-cancel" id="dialog-cancel">取消</a>';
            		html += '</div>';
            		
            		$('#J_dialog').html(html);
            		dialogPosCenter('#J_dialog');
            		$('#J_dialog, .cover-all').show();
				}
            },'json');
        }); 
      	//保存数据
        $('#J_dialog').on('click', '.dialog-ok', function() {
        	var checkCode = /^[0-9]*$/;
        	var id = $("#id").val();
        	var fileVal = $("#file").val();
        	var versionNo = $.trim($("#versionNo").val());
        	var versionCode = $.trim($("#versionCode").val());
        	var iosLowest = $.trim($("#iosLowest").val());
        	var androidLowest = $.trim($("#androidLowest").val());
        	var versionExplain = $.trim($("#versionExplain").val());
        	var publishTime = $.trim($("#publishTime").val());
        	var fileLocation = $.trim($("#fileLocation").val());
        	var status = $("input[name=sfky]:checked").val();
        	if(versionNo==""){
        		alert("版本号不能为空！");
        		$("#versionNo").focus();
        		return;
        	}else if(versionCode==""){
        		alert("版本编码不能为空！");
        		$("#iosLowest").focus();
        		return;
        	}else if(!checkCode.test(versionCode)){
        		alert("版本编码只能是数字！");
        		$("#iosLowest").focus();
        		return;
        	}else if(iosLowest==""){
        		alert("ios最低版本输入不能为空！");
        		$("#iosLowest").focus();
        		return;
        	}else if(androidLowest==""){
        		alert("安卓最低版本输入不能为空！");
        		$("#androidLowest").focus();
        		return;
        	}else if(versionExplain==""){
        		alert("版本更新说明不能为空！");
        		$("#versionExplain").focus();
        		return;
        	}else if(publishTime==""){
        		alert("发布时间不能为空！");
        		$("#publishTime").focus();
        		return;
        	}else if(fileVal == "" && fileLocation==""){
        		alert("请选择文件！");
        		return; 
        	}else if(fileLocation == ""){
        		alert("文件上传未结束,请等待！");
        		return;
        	}  
        	
        	var url="${root}";
        	var param = null;
        	if(id==null || id=='' || id=='null'){
        		url+="/Kefu/systemVersion/addVersionInfo.do";
        		param={fileName:fileVal,versionCode:versionCode,status:status,versionNo:versionNo,iosLowest:iosLowest,androidLowest:androidLowest,versionExplain:versionExplain,publishTime:publishTime,fileLocation:fileLocation};
        	}else{
        		url+="/Kefu/systemVersion/updateVersionInfoById.do";
        		param={fileName:fileVal,versionCode:versionCode,status:status,versionNo:versionNo,iosLowest:iosLowest,androidLowest:androidLowest,versionExplain:versionExplain,publishTime:publishTime,fileLocation:fileLocation,id:id};
        	}
        	$.post(url,param,function(data){
        		if(data.success){
        			alert(data.message);
        			location.reload();
        		}else{
        			alert(data.message);
        		}
        	},'json');
           
        });
      	//取消
        $('#J_dialog').on('click', '.dialog-cancel', function() {
            $('#J_dialog, .cover-all').hide();
        });
    });
</script>
</body>
</html>
