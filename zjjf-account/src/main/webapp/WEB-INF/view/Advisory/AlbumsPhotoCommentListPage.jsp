<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>相册照片评论列表</title>
   	<jsp:include page="../common/header.jsp"></jsp:include>
</head>
<body class="easyui-layout">
	<div region="center" style="padding:0px;" border="false" header="false">  
	    <table border="false" fit="true" id="ttAlbumsPhotoComment" class="easyui-datagrid" 
	    		url="${root}/admin/comment/AlbumsPhotoCommentList.do" 
	    		iconCls="icon-save" sortName="createTime" sortOrder="desc" singleSelect="true"
	            rownumbers="true" idField="id" fitColumns="true" pagination="true" pageSize="20"
	            data-options="rownumbers:true,method:'post',toolbar:'#tbAlbumsPhotoComment'">
	        <thead>
	            <tr>
	            	<th data-options="field:'ck',checkbox:true"></th>
	                <th field="id" width="200" sortable="true" hidden="true">评论id</th>
	                <th field="albumsId" width="200" sortable="true" hidden="true">相册id</th>
	                <th field="photoId" width="200" sortable="true" hidden="true">照片id</th>
	                <th field="albumsName" width="150" sortable="true" hidden="true">相册</th>
	                <th field="photoName" width="150" sortable="true" formatter="formatPhoto">照片</th>
	              	<th field="content" width="200" sortable="true" formatter="formatAlbumsPhotoComment">评论内容</th>
	                <th field="userName" width="100" sortable="true">评论者</th>
	                <th field="phoneNum" width="100" sortable="true">评论者电话</th>
	                <th field="createTime" width="120" sortable="true">评论时间</th>
	                <th data-options="field:'_operate',width:80,align:'center',formatter:formatAlbumsPhotoCommentOper">操作</th>
	            </tr>
	        </thead>
	    </table>
	    <div id="tbAlbumsPhotoComment" style="padding:2px 5px;">
	    	<div style="margin-top: 2px;">
	    		<!-- 相册：<input id="tbAlbumsName" class="easyui-textbox" style="width:140px;"/> -->
	    		照片：<input id="tbPhotoName" class="easyui-textbox" style="width:140px;"/>
	    		评论：<input id="tbContent" class="easyui-textbox" style="width:140px;"/>
		       	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" 
		       		plain="true" onclick="javascript:albumsPhotoCommentFind()">查询</a>
	       	</div>
	    </div>
    </div>
</body>
<script type="text/javascript">
	// 相册照片评论搜索
	function albumsPhotoCommentFind(){
		$('#ttAlbumsPhotoComment').edatagrid('load',{
			// albumsName: $("#tbAlbumsName").textbox('getValue'),
			photoName: $("#tbPhotoName").textbox('getValue'),
			content: $("#tbContent").textbox('getValue')
		});
	}
	
	// 查看照片链接
	function formatPhoto(val, row){
		return '<a href="#" mce_href="#" onclick="viewPhoto('+row.photoId + ')">'+val+'</a>';
	}
	
	// 查看照片
	function viewPhoto(photoId){
		var url = root+'/admin/comment/ViewPhotoPage.do?id='+photoId;
	   	showWindow("照片查看", url, 400, 300, albumsPhotoCommentFind);
	}
	
	// 查看评论内容链接
	function formatAlbumsPhotoComment(val, row){
		return '<a href="#" mce_href="#" onclick="viewAlbumsPhotoComment(\''+row.id + '\')">'+val+'</a>';
	}
	
	// 查看评论内容
	function viewAlbumsPhotoComment(id){
		var url = root+'/admin/comment/ViewAlbumsPhotoComment.do?id='+id;
	   	showWindow("评论查看", url, 400, 300, albumsPhotoCommentFind);
	}
	
	// 删除评论链接
	function formatAlbumsPhotoCommentOper(val, row, index){
		return '<a href="#" onclick="deleteAlbumsPhotoComment('+row.id+')">删除</a>';
	}
	
	// 删除评论
	function deleteAlbumsPhotoComment(id){
		$.messager.confirm('提示', "是否确认删除？", function(r){
			if(r){
				$.ajax({
					type: "POST",
					url: root+"/admin/comment/DeleteAlbumsPhotoComment.do",
					data: "id="+id,
					success: function(msg){
						albumsPhotoCommentFind();
					},
					error: function (XMLHttpRequest, textStatus, errorThrown) {
					 	// 通常 textStatus 和 errorThrown 之中
					 	// 只有一个会包含信息
					 	this; // 调用本次AJAX请求时传递的options参数
					 	$.messager.alert('抱歉', errorThrown, 'warning');
					}
				});
			}
		});
	}
</script>
</html>