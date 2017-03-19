<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>医生头像编辑</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="../common/header.jsp"></jsp:include>
	<!-- 头像裁剪 -->
	<link href="${root }/resources/js/jcrop/jquery.Jcrop.css" rel="stylesheet" type="text/css" />
	<script src="${root }/resources/js/jcrop/jquery.Jcrop.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		var myJcrop;
		var imgCrop;
		function loadMyPic(){
			if(myJcrop!=null){
				myJcrop.destroy();
			}
			var img='<img style="vertical-align: middle;" src="'+$("#mepic").val()+'" id="imgCrop" name="imgCrop" border="0" />';
			$("#td_pic").html(img);
		    
		    var img = new Image;
		    img.src = $("#mepic").val();
		    img.onload = function(){
		    	myJcrop = $.Jcrop('#imgCrop',{
					minSize: [0,0],
					maxSize: [500,500],
					setSelect: [0,0,500,500],
					boxWidth: 300,
					borderOpacity: 0.3,
					keySupport: false,
					dragEdges: true,
					aspectRatio: 1,
					allowSelect: false,
					allowResize: true,
					bgOpacity: 0.2,
					boundary: 0,
					onChange: showCoords,
					onSelect: showCoords
				});
		    };
		}
		
		function showCoords(c){
			jQuery('#pic_x').val(c.x);
			jQuery('#pic_y').val(c.y);
			jQuery('#pic_x2').val(c.x2);
			jQuery('#pic_y2').val(c.y2);
			jQuery('#pic_w').val(c.w);
			jQuery('#pic_h').val(c.h);	
		}
	
		function saveCutImg(){
			var x = jQuery('#pic_x').val();
			var y = jQuery('#pic_y').val();
			var x2 = jQuery('#pic_x2').val();
			var y2 = jQuery('#pic_y2').val();
			var w = jQuery('#pic_w').val();
			var h = jQuery('#pic_h').val();
			var oldImg=$("#imgCrop").attr("src");
			$.ajax({
				type: "POST",
				url: root + "/admin/doctorctl/CutDoctorFace.do",
				data: "oldImg="+oldImg+"&doctorId="+$("#id").val()+"&x="+x+"&y="+y+"&x2="+x2+"&y2="+y2+"&w="+w+"&h="+h,
				success: function(msg){
					closeWindow();
				},
				error: function (XMLHttpRequest, textStatus, errorThrown) {
				 	this; // 调用本次AJAX请求时传递的options参数
				 	$.messager.alert('抱歉', errorThrown, 'warning');
				}
			});
		}
		
		$(function(){
			$('#ff').form({
				url:root+"/admin/doctorctl/SaveDoctorFace.do",	
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');
				},
				success:function(data){
					var data = eval('(' + data + ')');
					if (data.success){
						$("#mepic").val(data.message);
						loadMyPic();
					}else{
						alertMsg(data.message);
					}
				},
				error:function(){
					alertMsg("加载失败，请检查网络！");
				}
			});
		});
		
		function submitForm(){
			var fileName=$("#uploadify").val();
			if(fileName==''){
				alertMsg("请选择一个图片！");
				return;
			}
			$('#ff').submit();
		}
		
		function cancelSave(){
			closeWindow();
		}
		
		var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
		function fileChange(target) {
			var fileSize = 0;
			if (isIE && !target.files) {
				var filePath = target.value;
				var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
				var file = fileSystem.GetFile (filePath);
				fileSize = file.Size;
			} else {
				fileSize = target.files[0].size;
			}
			var size = fileSize / 1024;
			if(size>200){
				alert("附件大于200K，建议更换或压缩");
			}
		}
	</script>
</head>
<body onload="loadMyPic()">
	<!-- 头像裁剪 begin -->
	<input type="hidden"  id="pic_x" name="x" />
	<input type="hidden"  id="pic_y" name="y" />
	<input type="hidden"  id="pic_x2" name="x2" />
	<input type="hidden"  id="pic_y2" name="y2" />
	<input type="hidden"  id="pic_w" name="w" />
	<input type="hidden"  id="pic_h" name="h" />
	<!-- 头像裁剪 end -->
	<div data-options="region:'center',border:false" >
	<input type="hidden" value="${Model.face}" name="id" id="mepic"/>
		<table  cellpadding="5" width="100%" height="100%" align="center" style="table-layout:fixed;">
			<tr>
				<td  id="td_pic" align="center" style="padding-top: 40px;width: 300px;max-width: 300px;max-height: 300px;"></td>
			</tr>
			<tr>
				<td style="padding-top: 50px;">
					<form id="ff" method="post" enctype="multipart/form-data" target="hidendIframe"> <!-- action="${root}/admin/doctorctl/SaveDoctorFace.do" -->
						<c:if test="${Model!=null}">
			       			<input type="hidden" value="${Model.id}" name="id" id="id"/>
			       		</c:if>
						<input type="file" name="uploadify" id="uploadify" onchange="fileChange(this);"/>
						<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:submitForm()" style="width:80px">上传</a>
					</form>
					<iframe name="hidendIframe" id="hidendIframe" style="display: none;"></iframe>
				</td>
			</tr>
			<tr>
				<td align="right">
					<div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;margin-top: 10px;">
						<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:saveCutImg()" style="width:100px">裁剪并保存</a>
						<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:cancelSave()" style="width:80px">取消</a>
					</div>
				</td>
			</tr>
		</table>
 	</div>
</body>
</html>