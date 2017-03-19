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
<div class="easyui-layout" data-options="fit:true" style="padding:5px;">
       <div style="padding:20px" data-options="region:'center',border:false" >
        	<form id="ff" method="post" enctype="multipart/form-data">
        		<c:if test="${Model!=null}">
        			<input type="hidden" value="${Model.id}" name="id"/>
        		</c:if>
	            <table cellpadding="5">
	            	<tr>
	            		<td>文章图片：</td>
	            		<td colspan="3">
	            			<c:if test="${Model!=null}">
	            				<img id="pic" name="pic" src="${Model.pic}" style="width:120px; height:110px"/>
	            			</c:if> 
	            		</td>
	            	</tr>
	            	<tr>
	                    <td>所属专题：</td>
	                    <td><input id="thematicId" name="thematicId"/></td>
	                    <td>文章名称：</td>
	                    <td><input class="easyui-textbox" type="text" name="name" value="${Model.name}" 
	                    		style="width: 155px;" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                	<td>文章类型：</td>
	                	<td>
		                	<select id="type" class="easyui-combobox" panelHeight="auto" name="type" style="width: 155px;" data-options="required:true">
	                        	<c:if test="${Model!=null}">
	                       			<option value="0" <c:if test="${Model.type==0}">selected="selected"</c:if>>文章</option>
	                       			<option value="1" <c:if test="${Model.type==1}">selected="selected"</c:if>>链接</option>
	                        	</c:if>
	                        	<c:if test="${Model==null}">
	                        	   	<option value="0">文章</option>
					            	<!-- <option value="1">链接</option> -->
	                        	</c:if>
					        </select>
				        </td>
	                	<td>发布类型：</td>
	                	<td>
		                	<select id="publishType" class="easyui-combobox" panelHeight="auto" name="publishType" style="width: 155px;" data-options="required:true">
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
	                    <td>是否置顶：</td>
	                    <td>
	                        <select id="isTop" class="easyui-combobox" panelHeight="auto" name="isTop" style="width: 155px;" data-options="required:true">
	                        	<c:if test="${Model!=null}">
                        			<option value="0" <c:if test="${Model.isTop==false}">selected="selected"</c:if>>是</option>
                        			<option value="1" <c:if test="${Model.isTop==true}">selected="selected"</c:if>>否</option>
	                        	</c:if>
	                        	<c:if test="${Model==null}">
	                        	   	<option value="0">是</option>
					            	<option value="1">否</option>
	                        	</c:if>
					        </select>
	                    </td>
	                    <td></td>
	                    <td></td>
	                </tr>
	                <tr>
	                    <td colspan="4"><input  id="newArticleContent"  type="hidden"  name="content"  ></input>
	                    </td>
	                </tr>
	            </table>
	            <div  id="doctorMgArticleUMeditor">${Model.content}</div>
        	</form>
        </div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		$("#thematicId").combobox({
			editable: false,
			url: root+"/admin/thematic/getList.do",    
			valueField: 'id',    
			textField: 'name',
			width: 155,
		 	onLoadSuccess:function(){
		 		$("#thematicId").combobox('setValue','${Model.thematicId}').combobox('setText','${Model.thematicName}');
		 	}
		});
		
		/********************初始化编辑器*********************************/
		var um = UM.getEditor('doctorMgArticleUMeditor',{
			//imageUrl:root+"/admin/uploadctl/addPicInArticle.do",
			//imagePath:"/asdfasdfa",
		    imageMaxSize: 2048,
		    imageAllowFiles: [".png", ".jpg", ".jpeg", ".gif", ".bmp"]
		});
		um.setWidth("100%");
		um.setHeight("300");
	});
</script>
</html>