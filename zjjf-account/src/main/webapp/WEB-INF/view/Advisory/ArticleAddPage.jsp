<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="../common/header.jsp"></jsp:include>
	<jsp:include page="../common/UMediter.jsp"></jsp:include>
</head>
<body>
<input id="picPrefix" type="hidden"  value="${picPrefix}" />
<div class="easyui-layout" data-options="fit:true" style="padding:5px;">
       <div data-options="region:'center',border:false" >
        	<form id="ff" method="post" enctype="multipart/form-data">
        		<c:if test="${Model!=null}">
        			<input type="hidden" value="${Model.id}" name="id"/>
        		</c:if>
        		<table  cellpadding="5"  height="100%">
        			<tr>
        				<td>
        					<table>
        						<tr>
	        						<td align="center" width="120">文章标题图片：</td><td rowspan="3" align="left" >	            			
	       								<c:if test="${Model!=null}">
	            							<img id="pic" name="pic" src="${Model.pic}" style="width:120px; height:110px" class="miboshowBigPic"/>
	            						</c:if> 
		            				</td>
	            				</tr>
        						<tr>
	        						<td align="right">
	        							<input type="file" name="uploadify" id="uploadify"/>
	        						</td>
        						</tr>
        					</table>
        				</td>
        				<td>
        				    <table>
        						<tr>
        							<td>所属专题：</td>
        							<td><input id="thematicId" name="thematicId"/></td>
        							<td>文章名称：</td>
        							<td><input class="easyui-textbox" type="text" id="name" name="name" value="${Model.name}" 
        									style="width: 155px;" data-options="required:true"></td>
        						</tr>
        						<tr>
        							<td>文章类型：</td>
        							<td>
										<select id="type" class="easyui-combobox" panelHeight="auto" name="type" style="width: 155px;" data-options="required:true, editable:false">
			                       			<option value="0" <c:if test="${Model.type==0}">selected="selected"</c:if>>文章</option>
			                       			<option value="1" <c:if test="${Model.type==1}">selected="selected"</c:if>>链接</option>
			                       			<option value="2" <c:if test="${Model.type==2}">selected="selected"</c:if>>活动</option>
			                       			<option value="3" <c:if test="${Model.type==3}">selected="selected"</c:if>>草稿箱</option>
			                       			<option value="4" <c:if test="${Model.type==4}">selected="selected"</c:if>>banner</option>
								        </select>
									</td>
        							<td>发布类型：</td>
        							<td>
					                	<select id="publishType" class="easyui-combobox" panelHeight="auto" name="publishType" style="width: 155px;" data-options="required:true, editable:false">
				                        	<c:if test="${Model!=null}">
				                       			<option value="0" <c:if test="${Model.publishType==0}">selected="selected"</c:if>>医生发表</option>
				                       			<option value="1" <c:if test="${Model.publishType==1}">selected="selected"</c:if>>小编发表</option>
				                        	</c:if>
				                        	<c:if test="${Model==null}">
				                        	   	<!-- <option value="0">医生发表</option> -->
								            	<option value="1">小编发表</option>
				                        	</c:if>
								        </select>
									</td>
								</tr>
        						<tr>
        							<td>是否置顶：</td><td>
				                        <select id="isTop" class="easyui-combobox" panelHeight="auto" name="isTop" style="width: 155px;" data-options="required:true, editable:false">
		                        			<option value="0" <c:if test="${Model.isTop==false}">selected="selected"</c:if>>否</option>
		                        			<option value="1" <c:if test="${Model.isTop==true}">selected="selected"</c:if>>是</option>
								        </select>
        							</td>
        							<td>是否推荐：</td><td>
				                        <select id="recommend" class="easyui-combobox" panelHeight="auto" name="recommend" style="width: 155px;" data-options="required:true, editable:false">
		                        			<option value="0" <c:if test="${Model.recommend==false}">selected="selected"</c:if>>否</option>
		                        			<option value="1" <c:if test="${Model.recommend==true}">selected="selected"</c:if>>是</option>
								        </select>
        							</td>
        						</tr>
        					</table>
        				</td>
        			</tr>
        			<tr>
        				<td colspan="2">
        					<input  id="newArticleContent"  type="hidden"  name="content"  >
				            <div  id="doctorMgArticleUMeditor"  >${Model.content}</div>
        				</td>
        			</tr>
        		</table>
        	</form>
        </div>
      	<div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:submitForm()" style="width:80px">确认</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:cancelSave()" style="width:80px">取消</a>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		$('#ff').form({
			url:root+"/admin/article/AddArticle.do",	
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success:function(data){
				var data = eval('(' + data + ')');
				if (data.success){
					closeWindow(this);
				}else{
					alertMsg(data.message);
				}
			},
			error:function(){
				alertMsg("加载失败，请检查网络！");
			}
		});
		$("#thematicId").combobox({
			editable: false,
			required: true,
			url: root+"/admin/thematic/getList.do",    
			valueField: 'id',    
			textField: 'name',
			width: 155,
		 	onLoadSuccess:function(){
		 		$("#thematicId").combobox('setValue','${Model.thematicId}').combobox('setText','${Model.thematicName}');
		 	}
		});
		
		/********************初始化编辑器*********************************/
		UM.userParam_articleid="${Model.id}";//图片上传参数
		var um = UM.getEditor('doctorMgArticleUMeditor',{
			imageUrl:root+"/admin/uploadctl/addPicInArticle.do",
			imagePath:$("#picPrefix").val(),
		    imageMaxSize: 2048,
		    imageAllowFiles: [".png", ".jpg", ".jpeg", ".gif", ".bmp"]
		});
		um.setWidth("100%");
		um.setHeight("300");
		
		
	});
	
	function submitForm(){
		// 验证文章名称不超过20字符
		var name = $("#name").val();
		if(name.length > 20){
			alert("文章名称超过20个字，不可保存");
			return;
		}

		// 验证图片必填
		var index = "${Model.pic}".lastIndexOf(".");
		var imgType = "${Model.pic}".substring(index + 1);
		if($.trim(imgType).length == 0){
			var img = $.trim($("#uploadify").val());
			if(img.length == 0){
				alert("标题图片必填");
				return;
			}else{
				var ldot = img.lastIndexOf(".");
				var type = img.substring(ldot + 1).toUpperCase();
				if(type!="BMP" && type!="JPG" && type!="JPEG" && type!="PNG"){
					alert("请上传BMP、JPG、JPEG、PNG格式的图片，不区分大小写");
					return;
				}
			}
		}
		
		// 富文本编辑器
		var um =UM.getEditor('doctorMgArticleUMeditor');
		$("#newArticleContent").val( um.getContent());
		
		// 提交
		$('#ff').submit();
	}
	
	function cancelSave(){
		closeWindow();
	}
	
	function clearForm(){
		$('#ff').form('clear');
	}
</script>
</html>