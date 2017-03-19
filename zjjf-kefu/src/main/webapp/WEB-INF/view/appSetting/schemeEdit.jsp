<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>标贴管理</title>
<%@ include file="../common/head.jsp"%>
<%@ include file="../common/autocomplete.jsp"%>
<%@ include file="../common/ztree.jsp"%>
<script src="${root}/resources/js/comm.js"></script>
<script src="${root }/resources/js/ztree-search.js"></script>
</head>
<body class="wrap-bd">
	<div class="mb-default">
		<span>当前位置：</span>
		<a class="crumb" href="${root}/kefu/DBAppConfig/toList.do">配置方案管理</a>
	    <c:choose>
			<c:when test="${appConfig != null}">
        		<a class="crumb" href="#">编辑方案</a>
			</c:when>
			<c:otherwise>
        		<a class="crumb" href="#">添加方案</a>
			</c:otherwise>
		</c:choose>
    </div>
<div class="title mb-default">
	<c:choose>
		<c:when test="${appConfig != null}">
			编辑方案
		</c:when>
		<c:otherwise>
			添加方案
		</c:otherwise>
	</c:choose>
</div>
<input type="hidden" value="${appConfig.id }" name="id" id="id"><!-- 只有编辑时才有值 -->
<form id="form">
    <div class="wrap-bd bg table-border">
        <b>基础信息</b>
        <p class="required">
            <label class="label">方案名称：</label>
            <input type="text" name="name"  id="name" value="${appConfig.name }" placeholder="" class="input" maxlength="100" style="width: 400px">
        </p>
        <p class="required">
            <label class="label">支付方式：</label>
            <select name="payCfgId" id="payCfgId" class="select" style="width: 400px">
            	<option value="" >请选择</option>
            	<c:forEach var="pay" items="${payList }">
            		<option value="${pay.id }" <c:if test="${pay.id==appConfig.payCfgId }">selected="selected"</c:if>>${pay.name }</option>
            	</c:forEach>
            </select>
            <%-- <input type="hidden" name="paywayList"  id="paywayList" value="${appConfig.paywayList }" placeholder="" class="input" maxlength="255"> --%>
        </p>
        <p class="">
            <label class="label">关联模块:</label>
            &nbsp;&nbsp;&nbsp;
            	<c:forEach var="item" items="${moduleList}">
            		<input type="checkbox" value="${item.id}" name="itemTagRadio">${item.name} 
            	</c:forEach>
            <%-- <input type="hidden" name="paywayList"  id="paywayList" value="${appConfig.paywayList }" placeholder="" class="input" maxlength="255"> --%>
        </p>
        <div class="bg wrap">
			<div class="mb-default ">
				<label class="label">
					分配定格：
				</label>
				<button class="button button-operate mr-default" id="J_dialog-addSpGroup" type="button">增加定格</button>
			</div>
			<table class="table-list mb-default">
				<thead>
					<tr>
						<th>序号</th>
						<th>区域名称</th>
						<th>定格名称</th>
					</tr>
				</thead>
				<tbody class="table-tbody" id="spGroup_info">
					<c:choose>
						<c:when test="${groupList != null && groupList.size() > 0}">
							<c:forEach items="${groupList}" var="group" varStatus="sta">
								<tr>
									<td>
										<span class="index">${sta.index+1 }</span>
										<input type="hidden" class="spGroupId" value="${group.id }"> 
									</td>
									<td>${group.areaName }</td>
									<td>${group.name }</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr><td colspan="3">暂无数据</td></tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<div class="dialog hidden J_dialog" id="J_dialogSpGroup" style="width: 600px;">
				<form action="" method="post" onsubmit="return false">
					<div class="dialog-head">
						选择定格
						<div id="" class="dialog-close">
						</div>
					</div>
					<div class="dialog-body">
						<form action="" method="post"  onsubmit="return false">
							<div class="mb-default">
								<input type="text" name="" id="search-condition" onkeydown="entersearch()" value="" class="input input-default" placeholder="定格名称" style="width:400px ;" />
								<input type="button" name="" id="" onclick="search_ztree('treeDemo', 'search-condition')" value="搜索" class="button button-default" />
							</div>
						</form>
						<div class="ztree" id="ztree">
							<ul id="treeDemo" class="ztree" style="height:140px; overflow:auto">
							</ul>
						</div>
					</div>
					<div class="dialog-foot">
						<div class="">
							<span class="dialog-button dialog-ok ml-default" id="saveBtn">确认</span>
							<span class="dialog-button ml-small  dialog-cancel">取消</span>
						</div>
					</div>
				</form>
			</div>
			<div class="cover-all">
			</div>
		</div>
    </div>
    <div class="mt-default">
        <input type="button" value="保存" class="button button-ok" id="okSave">
        <input type="button" value="取消" class="button button-cancel" id="okClose" >
    </div>
</form>
<script>

 function entersearch() {
		var event = window.event || arguments.callee.caller.arguments[0];
		if(event.keyCode == 13) {
			search_ztree('treeDemo', 'search-condition');
		}
	} 
$(function(){
	var zTreeObj = null;
	var setting = {
		view: {
			showLine: false
		},
		data: {
			key: {
				children: "regionList",
			},
			simpleData: {
				enable: true
			}
		}
	};
	var regionList = '${regionList}';
	var zNodes = JSON.parse(regionList);
	$(document).ready(function() {
		zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
		zTreeObj.expandAll(true);
	})
	
	dialogPosCenter('#J_dialogSpGroup');
 	$('#J_dialog-addSpGroup').on('click', function() {
		$('#J_dialogSpGroup, .cover-all').fadeIn();
	});
	$('.J_dialog').on('click', '.dialog-cancel', function() {

		$('.J_dialog, .cover-all').fadeOut();
	})
	$('.dialog').on('click', '.dialog-close', function() {
		$('.J_dialog, .cover-all').fadeOut();
	})

	var spGroups = new Array();
	$('#saveBtn').click(function() {
		var indexs = $(".index");
		var index = 0;
		if(indexs.length>0){
			index = indexs.length+1;
		}else{
			index = 1;
		}
		var html= '';
		var trSpGroupIds = $(".spGroupId");
		$.each(zTreeObj.getSelectedNodes(),function(i,item){
			var chongfu = false;
			if(trSpGroupIds.length>0){
				trSpGroupIds.each(function(){
					if(item.id == $(this).val()){
						chongfu=true;
						return false;
					}
				});
			}
			if(chongfu == false){
				spGroups.push(item.id);
				html+='<tr>';
				html+='<td>';
				html+='<span class="index">'+index+'</span>';
				html+='<input type="hidden" class="spGroupId" value="'+item.id+'"> ';
				html+='</td>';
				html+='<td>'+item.getParentNode().name+'</td>';
				html+='<td>'+item.name+'</td>';
				html+='</tr>';
			}
		});
		if(trSpGroupIds.length > 0){
			$("#spGroup_info").append(html);
		}else{
			$("#spGroup_info").html(html);
		}
		
	});
	
	//选中AppItemTag
	var cfgListJson = '${cfgListJson}';
	if(cfgListJson!=null&&cfgListJson!=''){
		$($.parseJSON(cfgListJson)).each(function(i,item){
			$('input[type="checkbox"][name="itemTagRadio"][value="'+item.moduleId+'"]').attr('checked',true);
		});
	}
	/* $('${tagList}').each(function(i,item){
	}); */
	
	/* 保存 */
	$("#okSave").on("click",function(){
		var id = $("#id").val();
		//保存操作
		var url="";
		var $root = $(this);
		/* var spGroups = $(".spGroupId"); */
		$root.attr("disabled","disabled");
		if(id == null || id=='undefined' || id==""){
			url="${root}/kefu/DBAppConfig/addAppConfig.do?spGroups="+spGroups;
			//url="${root}/kefu/DBAppConfig/addAppConfig.do";
		}else{
			url="${root}/kefu/DBAppConfig/updateAppItemLabel.do?spGroups="+spGroups+"&id="+id;
			//url="${root}/kefu/DBAppConfig/updateAppItemLabel.do?id="+id;
		}
		$.post(url,$("#form").serializeArray(),function(data){
			if(data.success){
				alert(data.message);
				location.href="${root}/kefu/DBAppConfig/toList.do";
			}else{
				alert(data.message);
				$root.removeAttr("disabled");
			}
		},'json');
	});

	/* 取消 */
	$("#okClose").on("click",function(){
		location.href="${root}/kefu/DBAppConfig/toList.do";
	});
});

</script>
</body>
</html>
