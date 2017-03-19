<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>文章评论列表</title>
   	<jsp:include page="../common/header.jsp"></jsp:include>
</head>
<body class="easyui-layout">
	<div region="center" style="padding:0px;" border="false" header="false">  
	    <table border="false" fit="true" id="ttArticleComment" class="easyui-datagrid"  url="${root}/admin/comment/ArticleCommentList.do" 
	    		iconCls="icon-save" sortName="createTime" sortOrder="desc" singleSelect="true"
	            rownumbers="true" idField="id" fitColumns="true" pagination="true" pageSize="20"
	            data-options="rownumbers:true,method:'post',toolbar:'#tbArticleComment'">
	        <thead>
	            <tr>
	            	<th data-options="field:'ck',checkbox:true"></th>
	                <th field="id" width="200" sortable="true" hidden="true">评论id</th>
	                <th field="articleName" width="150" sortable="true" formatter="formatArticle">文章名</th>
	              	<th field="content" width="200" sortable="true" formatter="formatArticleComment">评论内容</th>
	                <th field="userName" width="100" sortable="true">评论者</th>
	                <th field="phoneNum" width="100" sortable="true">评论者电话</th>
	                <th field="createTime" width="120" sortable="true">评论时间</th>
	                <th data-options="field:'_operate',width:80,align:'center',formatter:formatArticleCommentOper">操作</th>
	            </tr>
	        </thead>
	    </table>
	    <div id="tbArticleComment" style="padding:2px 5px;">
	    	<div style="margin-top: 2px;">
	    		文章:<input id="tbArticleName"class="easyui-textbox" style="width:140px;">
	    		评论：<input id="tbContent"class="easyui-textbox" style="width:140px;">
		       	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" 
		       		plain="true" onclick="javascript:articleCommentFind()">查询</a>
	       </div>
	    </div>
    </div>
</body>
<script type="text/javascript">
	// 文章评论搜索
	function articleCommentFind(){
		$('#ttArticleComment').edatagrid('load',{
			articleName: $("#tbArticleName").textbox('getValue'),
			content: $("#tbContent").textbox('getValue')
		});
	}

	// 删除评论按钮
	function formatArticleCommentOper(val,row,index){
		return '<a href="#" onclick="deleteArticleComment('+row.id+')">删除</a>';
	}
	
	// 删除评论
	function deleteArticleComment(articleId){
		 $.messager.confirm('提示', "是否确认删除？", function(r){
			if(r){
				$.ajax({
					type: "POST",
					url: root+"/admin/comment/DeleteArticleComment.do",
					data: "id="+articleId,
					success: function(msg){
						articleCommentFind();
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
	
	// 查看文章连接
	function formatArticle(val, row){
		return '<a href="#" mce_href="#" onclick="viewArticle('+row.articleId + ')">'+val+'</a> ';
	}
	
	// 查看文章
	function viewArticle(articleId){
		var url = root+'/admin/article/ViewArticlePage.do?id='+articleId;
	   	showWindow("文章查看", url, 800, 600, articleCommentFind);
	}
	
	// 查看文章评论连接
	function formatArticleComment(val, row){
		return '<a href="##" onclick="viewArticleComment('+ row.id + ')">'+val+'</a> ';
	}
	
	// 查看文章评论
	function viewArticleComment(id){
		var url = root+'/admin/comment/ViewArticleCommentPage.do?id='+id;
	   	showWindow("文章评论查看", url, 400, 300, articleCommentFind);
	}
</script>
</html>