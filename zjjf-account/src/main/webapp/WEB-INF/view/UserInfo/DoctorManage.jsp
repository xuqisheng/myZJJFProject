<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>医生信息管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/UMediter.jsp"></jsp:include>
<jsp:include page="../common/Upload.jsp"></jsp:include>
<script type="text/javascript">

/**
 * 格式化附加信息doctortFormatState
 */
function doctortFormatState(value, row) {
	var user = '<a href="#" onclick="getDocShengHeInfo(\'' + row.id + '\')">审核</a>';
	var tongguo =  "";
	if(value == 0 ){
		tongguo = "<span style='color:green' >已通过</span>";
	}else{
		tongguo = "<span style='color:red' >未通过</span>";
	}
	var space = "&nbsp;&nbsp;";
	return tongguo + space + user + space;
}
var currentDoctorId ="";
function getDocShengHeInfo( rowid ){
	currentDoctorId = rowid;
	$("#doctorMgShengHedlg").dialog('open').dialog('setTitle','医生资料审核');
	 $('#doctorMgShengHeListdg').datagrid({
		 url:'<c:url value="/admin/doctorctl/getUserAttachements.do"/>',
		 queryParams:{ dbId:rowid,type:0}
	});
}
function getShengHeDetailInfo( rowIndex,rowData ){
	var picHtml = "" ;
	$("#doctorMgShengHeDetail").html(picHtml);
	picHtml+=    '<img class="doc-pic-small miboshowBigPic" src="'+$("#picPrefix").val()+rowData.url+'" name="'+rowData.name+'" alt="点击查看大图" >' ;
	$("#doctorMgShengHeDetail").html(picHtml);
}

/**
 * 格式化附加信息
 */
function doctorIdFormatter(value, row) {
	var user = '<a href="#" onclick="getUserInfo(\'' + value + '\')">查看对应用户</a>';
	var extend = '<a href="#" onclick="getDoctorExtendsInfo(\'' + value + '\')">附加信息</a>';
	var album = '<a href="#" onclick="getDoctorAlbum(\'' + value + '\')">相册</a>';
	var article =  '<a href="#" onclick="getDoctorArticle(\'' + value + '\')">文章</a>';
	var space = "&nbsp;&nbsp;";
	return user + space + extend + space + album + space + article ;
}

/**
 * 获取附加信息函数
 */
function getDoctorExtendsInfo(id){
	 $("#doctorMgDetailtb").tabs("select",0);
	 $('#doctorMgExtenddg').datagrid({
		 url:'<c:url value="/admin/doctorctl/getDocExtendByDocId.do"/>',
		 queryParams:{ id:id}
	});
}
/**
 * 获取用户信息
 */
function getUserInfo(id){
	 $("#doctorMgDetailtb").tabs("select",1);
	 $('#doctorMgUserdg').datagrid({
		 url:'<c:url value="/admin/doctorctl/getUserInfo.do"/>',
		 queryParams:{ id:id}
	});
}
/**
 * 获取医生相册
 */
function getDoctorAlbum(id){
	var pathPrefix = $("#albumPrefix").val();
	$("#doctorMgAlbumdlg").dialog('open').dialog('setTitle','医生相册');
	//获取医生相册
	$.getJSON( '<c:url value="/admin/doctorctl/getDoctorPhotos.do"/>',{id:id},function(result){
		var listPhotos = "" ;
		$("#doctorMgAlbumtb").html(listPhotos);
		if(result.length > 0){
			for(var i =0,len =result.length;i<len; i++ ){
				listPhotos+=    '<div class="doc-photo-list" name="'+id+'">'
									+	   '<a><img name="'+result[i].id+'" src="'+ pathPrefix + result[i].picUrl + '" /></a> '
									+     '<div class="doc-photo-title">相片名称：'+result[i].name+'</div>' 
									+     '<div class="doc-photo-des">相片描述：' +result[i].description+'</div>'
									+     '<div class="doc-photo-time">创建时间：' +result[i].createTime+'</div>'
									+     '<div class="doc-photo-zan">点赞次数：'+result[i].praiseCount+'</div>'
									+     '<div class="doc-photo-delete">是否删除：'+(result[i].isDelete?"<span style='color:red' >是</span>":"否")+'</div>'
									+     '<div style="clear:both"></div>'
									+  '</div>';
			}
		}else{
			listPhotos = "<span>该用户没有照片</span>" ;
		}
		$("#doctorMgAlbumtb").html(listPhotos);
	});
}
//时间绑定
$(".doc-photo-list img").live("click",function(){
	$(this).toggleClass("picSelected");
});
$("#doctorMgAlbumdlgbt-cancel").live("click",function(){
	$("#doctorMgAlbumdlg").dialog('close');
});
$("#doctorMgAlbumdlgbt-delete").live("click",function(){
	var ids= new Array();
	$("#doctorMgAlbumtb .picSelected").each(function(index, element) {
		ids.push($(element).attr("name"));
    });
	if(ids.length >0){
		$.messager.confirm("警告","确定删除"+ids.length +"张图片吗?",function(r){
			if (r){
				$.post( '<c:url value="/admin/photoctl/Delete.do"/>' ,{ids:ids.join(",")},function(result){
					if (result.success){
						$(".doc-photo-list").has(".picSelected").children(".doc-photo-delete").html("<span style='color:red' >是</span>");
						$("#doctorMgAlbumtb .picSelected").toggleClass("picSelected");
					} else {
						$.messager.show({ // show error message
							title: 'Error',
							msg: result.message
						});
					}
				},'json');
			}		
		});
	}
});

/**
 * 获取医生文章
 */
function getDoctorArticle(id){
	$("#doctorMgArticledlg").dialog('open').dialog('setTitle','文章');
	setArticleValue("","","","","");
	//$("#doctorMgArticleDetail").html("");
	//获取医生文章列表
	$("#doctorMgArticleListdg").datagrid({
		 url:'<c:url value="/admin/doctorctl/getDoctorArticles.do"/>',
		 queryParams:{ publishId:id}
	});
}
/**
 * ********************************************************查看文章内容************************************ */

function getArticleInfo( rowIndex,rowData ){
	setArticleValue(rowData.id,rowData.name,rowData.createTime,rowData.content, rowData.pic);
}


function setArticleValue(id,title,time,content,imgsrc){
	var pathPrefix = $("#articlePrefix").val();
	var um = UM.getEditor('doctorMgArticleUMeditor');
	um.setWidth($("#doctorMgArticleDetailfm").width());
	um.setContent("");
	um.setContent(content);
	UM.userParam_articleid=id;//图片上传参数
	$("#doctorMgArticleImg").attr("src",pathPrefix+imgsrc);
	$("#doctorMgArticleImg").attr("name",imgsrc);
	$("#dmgAD-id").val(id);
	$("#dmgAD-title").textbox('setValue',title);
	$("#dmgAD-time").datetimebox('setValue',time);
}

function doctorMgArticledlgbtSave(){
	if( !$(doctorMgArticleDetailfm).form('validate')){
		$.messager.alert("提示", "请完善文章信息后再保存！");
		return;
	}
	if($("#dmgAD-id").val() =="" ){
		$.messager.alert("提示", "请先选择需要编辑的文章");
		return;
	}
	$.messager.confirm('提示','确定更改文章内容?',function(r){
		if(r){
			var um = UM.getEditor('doctorMgArticleUMeditor');
			var saveObj ={
					"id":$("#dmgAD-id").val(),
					"content": um.getContent(),
					"name":$("#dmgAD-title").textbox('getValue'),
					"createTime":$("#dmgAD-time").datetimebox('getValue'),
					"pic":$("#doctorMgArticleImg").attr("name")
			};
			$.post( '<c:url value="/admin/doctorctl/UpdateArticle.do"/>' ,saveObj,function(result){
				if (result.success){
					$.messager.alert("提示", "修改成功");
					$("#doctorMgArticleListdg").datagrid('reload');
				} else {
					$.messager.show({ // show error message
						title: 'Error',
						msg: result.message
					});
				}
			},'json');
		}
	})
}

/* ********************************************************查看文章内容end************************************ */


$(function(){
	
	$("input",$("#phoneNum_validate").next("span")).blur(function(){  
   	 	var re = /^1\d{10}$/;
   	    if(!re.test($("#phoneNum_validate").val())){
   	    	$('#phoneNum_validate').textbox('setValue', '');
   	    	alert("手机号码验证错误");
   	    }
   	});
	$("input",$("#idCard_validate").next("span")).blur(function(){  
   	 	var re = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
   	    if(!re.test($("#idCard_validate").val())){
   	    	$('#idCard_validate').textbox('setValue', '');
   	    	alert("身份证号码验证错误");
   	    }
   	});
	
	//获取医生所在医院
	 $('#doctorMg-hospital').combogrid({
		 	url: '<c:url value="/admin/hospital/HospitalList.do"/>',
			queryParams:{state:0,isDelete:0},
			fitColumns:true,
			pagination:true,
			panelWidth:450,
			idField:"id",
			textField:"name",
			mode: 'remote',
			pageSize:50,
			onShowPanel:function(){
				$('#doctorMg-hospital').combogrid("grid").datagrid("load");
			},
			//value:"",//default value
			columns:[[
			          {field:'name',title:'医院名称',width:160},
			          {field:'address',title:'医院地址',width:240},
			]]
	 });
	//获取医生所在科室
	 $('#doctorMg-office').combogrid({
		 	url: '<c:url value="/admin/office/OfficeList.do"/>',
			queryParams:{state:0,isDelete:0},
			fitColumns:true,
			pagination:true,
			panelWidth:450,
			idField:"id",
			textField:"name",
			mode: 'remote',
			pageSize:50,
			editable: false,
			onShowPanel:function(){
				$('#doctorMg-office').combogrid("grid").datagrid("load");
			},
			//value:"",//default value
			columns:[[
			          {field:'name',title:'科室名称',width:40},
			          {field:'remark',title:'科室描述',width:100},
			]]
	 });
	
	//获取医生类型数据字典
	$.getJSON( '<c:url value="/admin/fetchconfigctl/getDoctorPro.do"/>',function(result){
		miboDoctorProType = result;
		//初始化编辑区域（医生职称）
		$("#doctorMg-docPtype").combobox({
			data: miboDoctorProType ,valueField:'value',textField:'lable',panelHeight:'auto', editable:false
		});
	});
	//初始化编辑区域（医生职称）
	//$("#doctorMg-docPtype").combobox({
		//data: miboDoctorProType ,valueField:'value',textField:'lable',panelHeight:'auto'
	//});
	
	//初始化编辑区域（医生类型）
	$("#doctorMg-doctype").combobox({
		data:miboDoctorType, valueField:'value', textField:'lable', panelHeight:'auto', editable:false
	});
	//初始化审核按钮
	$("#doctorMgShengHedlgbt-close").click(function(){
		$("#doctorMgShengHedlg").dialog('close');
	});
	$("#doctorMgShengHedlgbt-pass").click(function(){
		//发送json请求
		$.getJSON( '<c:url value="/admin/doctorctl/passDoctorSH.do"/>',{id:currentDoctorId},function(result){
			$.messager.alert("审核提示", result.message);
			if(result.success){
				$("#doctorMgShengHedlg").dialog('close');
				$("#doctorMgdg").datagrid('reload');
			}
		});
	});
	$("#doctorMgShengHedlgbt-unpass").click(function(){
		//发送json请求
		$.getJSON( '<c:url value="/admin/doctorctl/unpassDoctorSH.do"/>',{id:currentDoctorId},function(result){
			$.messager.alert("取消审核提示", result.message);
			if(result.success){
				$("#doctorMgShengHedlg").dialog('close');
				$("#doctorMgdg").datagrid('reload');
			}
		});
	});
	
	var mibocrud = MiBoCRUD.creatNew({
		modelName:"doctorMg",
		dialogTitle:"医生信息编辑",
		listURL:'<c:url value="/admin/doctorctl/List.do"/>',
		creatURL:'<c:url value="/admin/doctorctl/Add.do"/>',
		updateURL:'<c:url value="/admin/doctorctl/Update.do"/>',
		deleteURL:'<c:url value="/admin/doctorctl/Delete.do"/>',
		findListFun:function(){
			$("#doctorMgdg" ).datagrid('load',{
				name:$("#doctorMglikeName").textbox('getValue'),
				state:$("#doctorMgstate").textbox('getValue'),
				isDelete:$("#doctorMgIsDelete").combobox('getValue')
			})
		}
	}).init();
	
	/********************初始化编辑器*********************************/
	var um = UM.getEditor('doctorMgArticleUMeditor',{
		imageUrl:root+"/admin/uploadctl/addPicInArticle.do",
		imagePath:$("#articlePrefix").val(),
	    imageMaxSize: 2048,
	    imageAllowFiles: [".png", ".jpg", ".jpeg", ".gif", ".bmp"]
	});
	um.setWidth("100%");
	um.setHeight("100%");

    $("#doctorMgArticleDetailfmPicbt").fileupload({
        url:'<c:url value="/admin/uploadctl/addArticlePic.do"/>',//文件上传地址，当然也可以直接写在input的data-url属性内
        //formData:{articleId:""+$("#dmgAD-id").val()},//如果需要额外添加参数可以在这里添加
        singleFileUploads:true,
        done:function(e, data){
        	var  result = data.result;
        	if(result.success){
        		var pathPrefix = $("#articlePrefix").val();
    	        $.each(result.message, function (index, attachment) {
    	        	$("#doctorMgArticleImg").attr("src",pathPrefix+attachment.url);
    	        	$("#doctorMgArticleListdg").datagrid('reload');
    	        });	
        	}else{
        		$.messager.alert("提示",result.message);
        	}
        }
    }).bind('fileuploadsubmit', function (e, data) {
        var input = $('#dmgAD-id');
        data.formData = {articleId: input.val()};
        if (!data.formData.articleId) {
          alert("文章标识为空，不能修该图片！");
          return false;
        }
    });
    
    $('#brokerId').combogrid({
	 	url: '<c:url value="/admin/brokerctl/BrokerList.do"/>',
		queryParams:{IsDelete:0},
		fitColumns:true,
		pagination:true,
		panelWidth:200,
		idField:"id",
		textField:"name",
		mode: 'remote',
		pageSize:50,
		onShowPanel:function(){
			$('#brokerId').combogrid("grid").datagrid("load");
		},
		columns:[[
		          {field:'name',title:'名称',width:80},
		          {field:'phoneNum',title:'电话',width:120},
		]]
 	});

});

	// 编辑头像
	function editDoctorFace(){
		var ids=getSelections();
		if(ids.length==0){
		  	alertMsg('请选择一位医生！');
		   	return;
		}
		var url = root+'/admin/doctorctl/EditDoctorFace.do?id='+ids;
	   	showWindow("医生头像编辑", url, 600, 500, doSearch);
	}
	
	function doSearch(){
		$("#doctorMgdg").datagrid('reload');
	}
	
	// 获取选中项
	function getSelections(){
	    var ss = [];
	    var rows = $('#doctorMgdg').datagrid('getSelections');
	    for(var i=0; i<rows.length; i++){
	        var row = rows[i];
	        ss.push(row.id);
	    }
	    return ss;
	}
	
</script>
<!-- csss for page -->
<style type="text/css">
.doc-photo-list {  width: 200px;  height: 300px;  margin:5px; float: left; background-color: #EEE; }
.doc-photo-list img{ width:196px; height: 190px;}
.doc-photo-title {  font-size: 12px;  padding: 4px; }
.doc-photo-des {  font-size: 12px;  padding: 4px; }
.doc-photo-time {  font-size: 12px;  padding: 4px; }
.doc-photo-zan {  font-size: 12px;  padding: 4px; }
/*
.doc-Article-name{  font-size: 14px; font-weight: bolder; text-align: center;padding:10px;}
.doc-Article-box img{ padding:10px; width:200px; height: 200px;}
.doc-Article-time{ text-align: right;}
.doc-Article-con textarea{ width: 100%; height:600px;background-color: #EEE; border:none }
*/
.picSelected{ border:red 3px solid;}
</style>
</head>
<body>
<input id="picPrefix" type="hidden"  value="${picPrefix}" />
<input id="albumPrefix" type="hidden"  value="${albumPrefix}" />
<input id="articlePrefix" type="hidden"  value="${articlePrefix}" />
<div class="easyui-layout"  fit="true"><!-- bord div -->
<div region="center" ><!-- ceter div -->
 <table id="doctorMgdg" 
	idField="id" sortName="createTime" sortOrder="desc"  pageSize =50
	toolbar="#doctorMgtoolbar" pagination="true" fitColumns="false"  fit="true"
	data-options="rownumbers:true,singleSelect:true,method:'post'">
    <thead>
        <tr>
        <th field="face" sortable="true" editor="text" formatter="publicPicFormatter">医生头像</th>
        <th field="loginName"  sortable="true" editor="text" width="100">登录名称</th>
        <th field="hName"  sortable="true" editor="text" width="200">所属医院</th>
        <th field="kName"  sortable="true" editor="text" width="100">所属科室</th>
        <th field="settledDate"  sortable="true" editor="text" width="150">入驻时间</th>
        <th field="type"  sortable="true" editor="text"  formatter="publicFormatDoctorType" width="100">医生类型</th>
        <th field="professional"  sortable="true" editor="combobox"  formatter="publicFormatDoctorPType" width="100">医生专业</th>
        <th field="name"  sortable="true" editor="text" width="100">医生名称</th>
        <th field="telNo" sortable="true" editor="text" width="100">联系电话</th>
        <th field="rank" sortable="true" editor="text" width="100">职称</th>
        <!-- 
        <th field="summary" sortable="true" editor="text">医生描述</th>
        <th field="motto" sortable="true" editor="text">医生座右铭</th>
        <th field="introduction" sortable="true" editor="text">专业技能</th>
        -->
        <th field="qualificationCard" sortable="true" editor="text" width="150">资历证书号</th>
        <th field="idCard" sortable="true" editor="text" width="200">身份证号</th>
        <th field="sex" sortable="true" editor="text"  formatter="publicFormatSex" width="50">性别</th>
        <th field="birthday" sortable="true" editor="text"  formatter="publicFormatDate" width="100">生日</th>
        <th field="createTime" sortable="true" editor="text" width="150">创建时间</th>
        <th field="creatorName" sortable="false" width="80">创建人</th>
        <th field="state" sortable="true" editor="text"  formatter="doctortFormatState" width="120">审核</th>
        <th field="isDelete" sortable="true"  editor="text"  formatter="publicFormatDel" width="80">是否删除</th>
        <th field="id"  sortable="true"  editor="text"  formatter="doctorIdFormatter" width="260">信息查看</th>
        <!-- 
        <th field="col1" sortable="true"  editor="text"  >备用字段1</th>
        <th field="col2" sortable="true"  editor="text"  >备用字段2</th>
        <th field="col3" sortable="true"  editor="text"  >备用字段3</th>
        <th field="col4" sortable="true"  editor="text"  >备用字段4</th>
        <th field="col5" sortable="true"  editor="text"  >备用字段5</th>
        <th field="col6" sortable="true"  editor="text"  >备用字段6</th>
        <th field="col7" sortable="true"  editor="text"  >备用字段7</th>
        <th field="col8" sortable="true"  editor="text"  >备用字段8</th>
        <th field="col9" sortable="true"  editor="text"  >备用字段9</th>
        <th field="col10" sortable="true"  editor="text"  >备用字段10</th>
         -->
        </tr>
    </thead>
</table>
<div id="doctorMgtoolbar">
    <a id="doctorMgtoolbar-new"   class="easyui-linkbutton" iconCls="icon-add" plain="true"  >新建医生</a>
    <a id="doctorMgtoolbar-edit"  class="easyui-linkbutton" iconCls="icon-edit" plain="true"  >编辑医生</a>
    <a id="doctorMgtoolbar-delete"  class="easyui-linkbutton" iconCls="icon-remove" plain="true" >删除医生</a>
    <a href="javascript:editDoctorFace();" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑头像</a>    
           医生名称:<input id="doctorMglikeName"class="easyui-textbox" style="width:140px;">
           审核状态：
      <select id="doctorMgstate" class="easyui-combobox" panelHeight="auto" style="width:100px" >
	        <option value="1" >未通过</option>
	        <option value="0">已通过</option>
	        <option value=""  selected="selected">全部</option>
      </select>
   	       是否删除: 
       <select id="doctorMgIsDelete" class="easyui-combobox" panelHeight="auto" style="width:100px" >
	        <option value="false"  selected="selected">否</option>
	        <option value="true">是</option>
	        <option value="" >全部</option>
       </select>
    <a id="doctorMgtoolbar-findlist"  class="easyui-linkbutton" iconCls="icon-search" plain="true"  >查询</a>
</div>
<!-- -------------------------------------------------------------------------信息编辑区域-------------------------------------------------------------------------------------------- -->
																									<!-- document.body.clientHeight*0.8 -->
 <div id="doctorMgdlg" class="easyui-dialog" modal="true" style="padding:10px 20px; width:600px; height:500;" closed="true" buttons="#doctorMgdlgbt">
    <div class="ftitle">医生信息编辑</div>
    <form id="doctorMgfm" method="post" novalidate>
    <div class="fitem">
    	<!-- <label>头像:</label><input name="face" class="easyui-textbox"  editable="false"> -->
		<label>登录名称:</label><input onkeyup="value=value.replace(/[\W]/g,'') " name="loginName" placeholder="5-20位数字字母"
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
								<!-- <input name="loginName" class="easyui-textbox" required="true"> -->
		<label>经纪人:</label><input name="brokerId" id="brokerId"/>
	</div>
    <div class="fitem">
		<label>所属医院:</label><input id="doctorMg-hospital" name="hospitalId" class="easyui-textbox"  required="true" >
		<label>所属科室:</label><input id="doctorMg-office"  name="hospitalOfficeId" class="easyui-textbox"  required="true" >
	</div>
    <div class="fitem">
     	<label>入住时间:</label><input name="settledDate" class="easyui-datetimebox" required="true" data-options="editable:false">
		<label>职称/头衔:</label><input name="rank" class="easyui-textbox"  required="true" >
    </div>
    <div class="fitem">
		<label>医生姓名:</label><input name="name" class="easyui-textbox"  required="true" >
		<label>手机号码:</label><input id="phoneNum_validate" name="telNo" class="easyui-numberbox" required="true" >
	</div>
    <div class="fitem">
    	<label>医生类型:</label><input name="type" required="true" id="doctorMg-doctype"  >
    	<label>医生专业:</label><input name="professional" required="true" id="doctorMg-docPtype"  >
    </div>
    <div class="fitem">
    	<label>擅长及概要介绍:</label><input name="famous" class="easyui-textbox" style="height:40px; width:420px" data-options="multiline:true, required:true, prompt:'建议20字'"/>
    </div>
    <!-- <div class="fitem">
    	<label>概要介绍:</label><input name="summary" class="easyui-textbox" style="height:40px; width:420px" data-options="multiline:true, required:true"/>
    </div> -->
    <div class="fitem">
    	<label>座右铭:</label><input name="motto" class="easyui-textbox" style="height:40px; width:420px" data-options="multiline:true, prompt:'建议20字'"/>
    </div>
    <div class="fitem">
    	<label>诊所介绍:</label><input name="introduction" class="easyui-textbox" style="height:40px; width:420px" data-options="multiline:true, prompt:'建议20字'"/>
    </div>
    <div class="fitem">
    	<label>个人成就:</label><input name="achievement" class="easyui-textbox" style="height:40px; width:420px" data-options="multiline:true, prompt:'建议20字'"/>
    </div>
    <div class="fitem">
    	<label>资历证书号:</label><input name="qualificationCard" class="easyui-textbox"/>
    	<label>身份证号:</label><input id="idCard_validate" name="idCard" class="easyui-textbox" >
  	</div>
    <div class="fitem">
    	<label>性别:</label><input name="sex"  class="easyui-combobox"  required="true" data-options="data:miboUserSex,valueField:'value',textField:'lable',panelHeight:'auto', editable:false" >
    	<label>生日:</label><input name="birthday" class="easyui-datebox" data-options="editable:false"></div>
    <!--<div class="fitem">
    	 <label>创建用户:</label><input name="creatorId" class="easyui-textbox" >
     	<label>创建时间:</label><input name="createTime" class="easyui-datetimebox" >
	</div>
	-->
	  <input name="teacherId"  type="hidden">
	  <input name="face"  type="hidden">
	 <input name="creatorId"  type="hidden">
     <input name="createTime"  type="hidden">
     <input name="isDelete"  type="hidden">
    </form>
</div>
<div id="doctorMgdlgbt">
	<a id="doctorMgdlgbt-save" class="easyui-linkbutton c6" iconCls="icon-ok"  style="width:90px">保存</a>
	<a id="doctorMgdlgbt-cancel" class="easyui-linkbutton" iconCls="icon-cancel"  style="width:90px">取消</a>
</div>
<!-- ---------------------------------------------------------------------------相册管理------------------------------------------------------------------------------------------- -->
<div id="doctorMgAlbumdlg" class="easyui-dialog"  style="width:80%;height:80%;" closed="true" buttons="#doctorMgAlbumdlgbt">
	<div id ="doctorMgAlbumtb"  class="easyui-panel"  fit="true" border="false"  style="padding:5px;" >
			
	</div>
	<div id="doctorMgAlbumdlgbt">
	<a id="doctorMgAlbumdlgbt-delete" class="easyui-linkbutton c6"  iconCls="icon-ok"  style="width:90px">删除</a>
	<a id="doctorMgAlbumdlgbt-cancel" class="easyui-linkbutton" iconCls="icon-cancel"  style="width:90px">取消</a>
</div>
</div>
<!-- ---------------------------------------------------------------------------审核管理------------------------------------------------------------------------------------------- -->
<div id="doctorMgShengHedlg" class="easyui-dialog"  style="width:90%;height:90%;" closed="true"  buttons="#doctorMgShengHedlgbt">
	<div  class="easyui-layout"  fit="true"  >
		<div region="west"  title="资质资料列表"  style="width:40%;">
			<table id="doctorMgShengHeListdg"  fitColumns="true"  fit="true"  class="easyui-datagrid"   data-options="rownumbers:true,singleSelect:true,method:'post',onClickRow:getShengHeDetailInfo">
				<thead><tr>
			       <th field="name"  sortable="true" editor="text">文件名称</th>
			       <th field="type" sortable="true" editor="text">文件类型</th>
			       <th field="url" sortable="true" editor="text">文件地址</th>
			       <th field="createTime" sortable="true" editor="text">创建时间</th>
			       <th field="isDelete" sortable="true"  editor="text" formatter="publicFormatDel">删除</th>
			   	</tr></thead>
			</table>
		</div>
		<div region="center"  title="资质资料详情"  >
			<div  id="doctorMgShengHeDetail">
			
			</div>
		</div>		
	</div>
</div>
<div id="doctorMgShengHedlgbt">
	<a id="doctorMgShengHedlgbt-pass" class="easyui-linkbutton c6" iconCls="icon-ok"  style="width:90px">审核通过</a>
	<a id="doctorMgShengHedlgbt-unpass" class="easyui-linkbutton c6" iconCls="icon-remove"  style="width:90px">审核不通过</a>
	<a id="doctorMgShengHedlgbt-close" class="easyui-linkbutton" iconCls="icon-cancel"  style="width:90px">关闭窗口</a>
</div>

<!-- ---------------------------------------------------------------------------文章管理------------------------------------------------------------------------------------------- -->
<div id="doctorMgArticledlg" class="easyui-dialog"  style="width:90%;height:90%;" closed="true"  >
	<div  class="easyui-layout"  fit="true"  >
		
		<div region="center"  title="文章列表">
			<table id="doctorMgArticleListdg"  fitColumns="true"  pagination="true"  fit="true"  class="easyui-datagrid" data-options="rownumbers:true,singleSelect:true,method:'post',onClickRow:getArticleInfo">
    			<thead><tr>
       				<th field="name" sortable="true" editor="text">文章名称</th>
       				<th field="createTime" sortable="true" editor="text">创建时间</th>
       				<th field="content" sortable="true" editor="text"  formatter="publicFormatArticleViewLength">文章内容</th>
       				<th field="type" sortable="true" editor="text" formatter="publicFormatArticleType">类型</th>
       				<th field="publishType" sortable="true" editor="text"  formatter="publicFormatArticlePubType">发布类型</th>
       				<th field="isTop" sortable="true" editor="text" formatter="publicFormatArticleIsTop">置顶</th>
       				<th field="pic" sortable="true" editor="text">图片地址</th>
       				<th field="orderIndex" sortable="true" editor="text">排序</th>
       			</tr></thead>
       		</table>		
		</div>
		<div region="east"  style="width:50%;background: #eee;" title="文章详情 — 编辑" data-options="iconCls:'icon-ok',tools:'#doctorMgArticledlgbt'">
			<div  id="doctorMgArticleDetail">
				<form  id="doctorMgArticleDetailfm" method="post"  novalidate>
					<div style="margin: 12px 0; ">
					<div style="width: 280px; float: left">
						<input  name="id"   id="dmgAD-id"  type="hidden" >
						<div style="margin: 10px;"><label>文章标题:</label><input id="dmgAD-title"  type="text"  class="easyui-textbox"  style="width: 180px;" required="true" /></div>
						<div style="margin: 10px;"><label>发表时间:</label><input id="dmgAD-time"    type="text"  class="easyui-datetimebox"  style="width: 180px;"  required="true" /></div>
					</div>
					<div style="width: 300px; float: left">
						<div style="width: 120px; float: left;line-height: 40px;">
							<label>文章图片:</label>
							<span class="btn btn-success fileinput-button">
						        <i class="glyphicon glyphicon-plus"></i>
						        <span>修改图片</span>
						        <!-- The file input field used as target for the file upload widget -->
						        <input id="doctorMgArticleDetailfmPicbt"  type="file" name="files[]" multiple>
						    </span>
						</div>
						<img  id="doctorMgArticleImg" class="miboshowBigPic"  style="width: 170px; max-height:180px; right" src="" />
						<div   style="clear:both"></div>
					</div>
					<div   style="clear:both"></div>
					</div>
					<div  id="doctorMgArticleUMeditor"  name="123132">
					</div>
				</form>
				<div id="doctorMgArticledlgbt">
					<!-- <a id="doctorMgArticledlgbt-save" class="easyui-linkbutton c6" iconCls="icon-ok"  style="width:90px">保存修改</a>
					<a id="doctorMgShengHedlgbt-delete" class="easyui-linkbutton c6" iconCls="icon-remove"  style="width:90px">删除文章</a>
					<a id="doctorMgShengHedlgbt-close" class="easyui-linkbutton" iconCls="icon-cancel"  style="width:90px">关闭窗口</a>
					<a href="#" class="icon-add" onclick="javascript:alert('add')"></a> 
					<a href="#" class="icon-edit" onclick="javascript:alert('edit')"></a>-->
					<span style="color:red">点击右侧按钮保存修改</span>
					<a href="#" class="icon-save" onclick="javascript:doctorMgArticledlgbtSave();"></a>
				</div>	
			</div>
		</div>
	</div>
</div>

</div><!-- ceter div -->
<!-- ---------------------------------------------------------------------------其他信息管理------------------------------------------------------------------------------------------- -->
<div region="south"  style="height:120px;background: #eee; overflow-y:hidden" >
    <div id="doctorMgDetailtb" class="easyui-tabs"  fit="true" border="false" >
   		<!-- 医生扩展信息面板 -->
		<div title="医生扩展信息"  >
			<table id="doctorMgExtenddg"  fitColumns="true"  fit="true"  class="easyui-datagrid" data-options="rownumbers:true,singleSelect:true,method:'post'">
    			<thead><tr>
       				<th field="totalScore" sortable="true" editor="text" width="100">评价总分</th>
       				<th field="times" sortable="true" editor="text" width="100">服务次数</th>
       				<th field="overage" sortable="true" editor="text" width="100">余额</th>
       				<!-- <th field="col1" sortable="true" editor="text">扩展一</th>
       				<th field="col2" sortable="true" editor="text">扩展二</th>
       				<th field="col3" sortable="true" editor="text">扩展三</th> -->
       			</tr></thead>
       		</table>
		</div>
		<!-- 关联用户信息面板 -->
		<div title="登录用户信息" >
			<table id="doctorMgUserdg"  fitColumns="true"  fit="true"  class="easyui-datagrid"   data-options="rownumbers:true,singleSelect:true,method:'post'">
    			<thead><tr>
			        <th field="loginName"  sortable="true" editor="text" width="100">登录名称</th>
			        <th field="nikeName" sortable="true" editor="text" width="100">昵称</th>
			        <th field="sex" sortable="true" editor="text" formatter="publicFormatSex" width="100">性别</th>
			        <th field="email" sortable="true" editor="text" width="100">Email</th>
			        <th field="callNum" sortable="true"  editor="text" width="100">电话</th>
			        <th field="phoneNum" sortable="true"  editor="text" width="100">手机</th>
			        <th field="departmentName" sortable="true" editor="text" width="100">部门名称</th>
			        <th field="state" sortable="true" editor="text"  formatter="pulbicFormatState" width="100">审核</th>
			        <th field="isDelete" sortable="true"  editor="text" formatter="publicFormatDel" width="100">删除</th>
			        <th field="createTime" sortable="true" editor="text" width="100">创建时间</th>
			        <th field="lastLoginTime" sortable="true" editor="text" width="100">最后登录时间</th>
			        <!-- <th field="backupOne" sortable="true" editor="text">备用字段一</th>
			        <th field="backupTwo" sortable="true" editor="text">备用字段二</th> -->
       			</tr></thead>
       		</table>
		</div>
		
	</div>
</div><!-- south div -->
</div><!-- bord div -->
</body>
</html>
