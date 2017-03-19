<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>文章列表</title>
   	<jsp:include page="../common/header.jsp"></jsp:include>
</head>
<body class="easyui-layout">
	<div region="center" style="padding:0px;" border="false" header="false">  
	    <table border="false" fit="true" id="tt" class="easyui-datagrid"  url="${root}/admin/article/ArticleList.do" 
	    		iconCls="icon-save" sortName="createTime" sortOrder="desc" singleSelect="true"
	            rownumbers="true" idField="id" fitColumns="true" pagination="true" pageSize="20"
	            data-options="rownumbers:true,method:'post',toolbar:'#tb',queryParams:{state: 0}">
	        <thead>
	            <tr>
	            	<th data-options="field:'ck',checkbox:true"></th>
	                <th field="id" width="200" sortable="true" hidden="true">文章id</th>
	                <th field="thematicName" width="100" sortable="true">主题名</th>
	                <th field="name" width="200" sortable="true" formatter="formatOp">文章名</th>
	              	<th field="authorName" width="100" sortable="true">作者</th>
	                <th field="type" width="100" sortable="true" formatter="formatArticleType">文章类型</th>
	                <th field="publishType" width="100" sortable="true" formatter="formatPublishType">发布类型</th>
	                <th field="isTop" width="100" sortable="true" formatter="formatIsTop">是否置顶</th>
	                <th field="openType" width="100" sortable="true" formatter="formatOpenType">公开度</th>
	                <th field="recommend" width="100" sortable="true" formatter="formatRecommend">是否推荐</th>
	                <th field="readCount" width="100" sortable="true">阅读次数</th>
	                <th field="shareCount" width="100" sortable="true">分享次数</th>
	                <th field="praiseCount" width="100" sortable="true">点赞次数</th>
	                <th field="collectCount" width="100" sortable="true">收藏次数</th>
	                <th field="createTime" width="150" align="center">创建时间</th>
	            </tr>
	        </thead>
	    </table>
	    <div id="tb" style="padding:2px 5px;">
	    	<div style="margin-top: 2px;">
	    		<a href="javascript: addArticle();" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>    
         		<a href="javascript: delArticle();" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
	    		主题:<input id="thematicName"class="easyui-textbox" style="width:140px;"/>
	    		文章:<input id="name"class="easyui-textbox" style="width:140px;"/>
	    		文章类型:
	    		<select id="type" class="easyui-combobox" panelHeight="auto" style="width:100px">
		            <option value="">全部</option>
		            <option value="0">文章</option>
		            <option value="1">链接</option>
		            <option value="2">活动</option>
		            <option value="3">草稿箱</option>
		            <option value="4">banner</option>
		        </select>
				发布类型:
				<select id="publishType" class="easyui-combobox" panelHeight="auto" style="width:100px">
		            <option value="">全部</option>
		            <option value="0">医生文章</option>
		            <option value="1">后台文章</option>
		        </select>
				是否置顶:
				<select id="isTop" class="easyui-combobox" panelHeight="auto" style="width:100px">
		            <option value="">全部</option>
		            <option value="0">否</option>
		            <option value="1">是</option>
		        </select>
  				公开度:
  				<select id="openType" class="easyui-combobox" panelHeight="auto" style="width:100px">
		            <option value="">全部</option>
		            <option value="0">仅自己可见</option>
		            <option value="1">医生好友可见</option>
		            <option value="2">患者可见</option>
		            <option value="3">公开</option>
		        </select>
				是否推荐:
				<select id="recommend" class="easyui-combobox" panelHeight="auto" style="width:100px">
		            <option value="">全部</option>
		            <option value="0">否</option>
		            <option value="1">是</option>
		        </select>
		       	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" 
		       		plain="true" onclick="javascript:articleFind()">查询</a>
	       </div>
	    </div>
    </div>
</body>
<script type="text/javascript">
	// 格式化文章类型
	function formatArticleType(val, row){
		if(val == 0){
			return "文章";
		}else if(val == 1){
			return "链接";
		}else if(val == 2){
			return "活动";
		}else if(val == 3){
			return "草稿箱";
		}else if(val == 4){
			return "banner";
		}else{
			return "其它";
		}
	}
	
	// 格式化发布类型
	function formatPublishType(val, row){
		if(val == 0){
			return "医生文章";
		}else if(val == 1){
			return "后台文章";
		}else{
			return "其它";
		}
	}
	
	// 格式化是否置顶
	function formatIsTop(val, row){
		if(val == 0){
			return "否";
		}else if(val == 1){
			return "是";
		}else{
			return "其它";
		}
	}
	
	// 格式化公开度
	function formatOpenType(val, row){
		if(val == 0){
			return "仅自己可见";
		}else if(val == 1){
			return "医生好友可见";
		}else if(val == 2){
			return "患者可见";
		}else if(val == 3){
			return "公开";
		}else{
			return "其它";
		}
	}
	
	// 格式化 是否推荐
	function formatRecommend(val, row){
		if(val == 0){
			return "否";
		}else if(val == 1){
			return "是";
		}else{
			return "其它";
		}
	}
	
	// 文章搜索
	function articleFind(){
		$('#tt').edatagrid('reload',{
			thematicName: $("#thematicName").textbox('getValue'),
			name: $("#name").textbox('getValue'),
			type: $("#type").combobox('getValue'),
			publishType: $("#publishType").combobox('getValue'),
			isTop: $("#isTop").combobox('getValue'),
			openType: $("#openType").combobox('getValue'),
			recommend: $("#recommend").combobox('getValue')
		});
	}
	
	// 编辑文章链接
	function formatOp(val, row){
		return '<a href="#" mce_href="#" onclick="viewArticle(\''+ row.id + '\')">'+val+'</a> ';
	}
	
	// 编辑文章
	function viewArticle(id){
		var url = root+'/admin/article/AddPage.do?id='+id;
	   	showWindow("文章编辑", url, document.body.clientWidth*0.8, document.body.clientHeight*0.8, articleFind);
	}
	
	// 新增文章
	function addArticle(){
		var url = root+'/admin/article/AddPage.do';
	   	showWindow("文章编辑", url, document.body.clientWidth*0.8, document.body.clientHeight*0.8, articleFind);
	}
	
	// 删除文章
	function delArticle(){
		var ids=getSelections();
		if(ids.length==0){
		  	alertMsg('请选择一行进行删除！');
		   	return;
		 }
		 $.messager.confirm('提示', "是否确认删除？", function(r){
			if(r){
				$.ajax({
					type: "POST",
					url: root+"/admin/article/Delete.do",
					data: "ids="+ids.join(','),
					success: function(msg){
						articleFind();
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
	
	// 获取选中项
	function getSelections(){
	    var ss = [];
	    var rows = $('#tt').datagrid('getSelections');
	    for(var i=0; i<rows.length; i++){
	        var row = rows[i];
	        ss.push(row.id);
	    }
	    return ss;
	}
</script>
</html>