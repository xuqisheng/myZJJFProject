<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<title>街坊店宝</title>
<meta name="description" content="">
<meta name="keywords" content="">
<%@ include file="../common/head.jsp"%>
<script src="${root}/resources/js/comm.js?v"></script>
</head>
<body>
	<div class="wrap-bd">
		<div class="mb-small clearfix">
			<div class="fl title">客户管理</div>
			<div class="fr">
				<a href="${root}/scms/storegruop/invite.do" class="button">邀请客户</a>
			</div>
		</div>
		<div>
			<a href="${root}/scms/store/scmshome.do" class="pills pills-active">线上客户</a>
			<a href="${root}/scms/store/scmshomedown.do" class="pills">线下客户</a>
		</div>
		<div class="op-section clearfix">
			<form>
				<label>分组：</label> <select class="select" id='gID'>
					<option value=''>全部</option>
					<c:forEach items="${gruops}" var="item">
						<option value="${item.id}">${item.name}</option>
					</c:forEach>
				</select> <label class="ml-default">客户来源：</label> <select class="select"
					id='from'>
					<option value=''>全部</option>
					<option value="0">转角分配</option>
					<option value="1">批发商邀请加入</option>
				</select> <input class="input-search-text ml-default" type="text"
					id="nameOrTelphone" name="nameOrTelphone" value=""
					placeholder="店铺名称/联系方式"> <input id="hiddenText" type="text"
					style="display: none" /> <input type="button"
					class="input-search-button ml-default" value="搜索" id="sub" />
			</form>
		</div>
		<div>
			<table class="table-list">
				<thead>
					<tr>
						<th width="100">客户分组</th>
						<th width="200">店铺名称</th>
						<!-- <th width="100">联系人</th>
						<th width="150">联系方式</th> -->
						<th>收货地址</th>
						<th width="80">客户类型</th>
						<th width="80">客户来源</th>
						<th width="80">操作</th>
					</tr>
				</thead>
				<tbody id="J_tbody">
				</tbody>
			</table>
		</div>
		<%@ include file="../common/pagination.jsp"%>
	</div>
	<form>
		<div class="dialog hidden" id="J_dialog">
			<div class="dialog-head">
				<div class="dialog-title"></div>
				<div class="dialog-close"></div>
			</div>
			<div class="dialog-body dialog-padding">

				<p>
					<label class="label">客户分组：</label> <select class="select"
						id="storeGroupID">
						<option value=''>请选择分组</option>
						<c:forEach items="${gruops}" var="item">
							<option value="${item.id}">${item.name}</option>
						</c:forEach>
					</select> <input type="hidden" id="id1" value='' />
				</p>

			</div>
			<div class="dialog-foot">
				<input type="button" id="sure" class="dialog-button dialog-ok"
					value="确定"> <span class="ml-default"></span> <input
					type="button" class="dialog-button dialog-cancel" value="取消">
				<input type="reset" class="hidden" value="重置">
			</div>
		</div>
	</form>
	<div class="cover-all"></div>
	<script>
	document.onkeydown = function(event) {
		e = event ? event :(window.event ? window.event : null);
		if(e.keyCode==13){
			$("#jpagination").pagination('setParams', {'nameOrTelphone':$("#nameOrTelphone").val()});
		   	$("#jpagination").pagination('setPageIndex', 0);
			$("#jpagination").pagination('remote');
		}
	} 
	
	function setSelectChecked(selectId, checkValue){  
	    var select = document.getElementById(selectId);
	    for(var i=0; i<select.options.length; i++){
	        if(select.options[i].innerHTML == checkValue){
	            $(select.options[i]).prop("selected", "selected");
	            break;
	        }
	    }
	}; 
	
	function edit(id){
		
		var shuzu=id.split('___');
		setSelectChecked("storeGroupID", shuzu[1]);
		
			$("#id1").val(shuzu[2]);
			$('.cover-all').fadeIn();
			$('#J_dialog').fadeIn();
			$('#J_dialog .dialog-title').text("客户编辑");
	
	}
    $(function() {
    	 $("#jpagination").pagination({
             pageSize: 10,
             remote: {
                 url: '${root}/scms/store/listdown.do',
                 params: {'nameOrTelphone':$.trim($("#nameOrTelphone").val()),'gID':$("#gID").val(),'from':$("#from").val()},
                 success: function(data) {
                     var html='';
                     $.each(data.list, function(i,item) {
                    	 if(item.groupName==undefined){
                    		 html+='<tr><td>未分组</td>';
                    	 }else{
                    		 html+='<tr><td>'+item.groupName+'</td>';
                    	 }
                         html+='<td>'+item.name+'</td>';
                     	/* html+='<td>'+item.contact+'</td>';
    	                html+='<td>'+item.mobile+'</td>'; */
    	                html+='<td>'+item.address+'</td>';
                        html+='<td>便利店</td>';
                       	if(item.fromWho=='1'){
                       	 html+='<td>批发商邀请加入</td>';
                       	}else{
                       		html+='<td>转角分配</td>';
                       	}
    	                html+='<td><a href="#" onclick="edit(\'___' + item.groupName + '___' + item.id + '\')">编辑分组</a> </td></tr>';
                     });
                     if(html == "") {
                      	html = '<tr><td colspan="8" class="no-data"></td></tr>';
                      }
                     $('#J_tbody').html(html);
                 },
                 totalName:'totalSize'
             }
        });
        $('#sub').on('click', function(e) {
        	 $("#jpagination").pagination('setParams', {'nameOrTelphone':$.trim($("#nameOrTelphone").val()),'gID':$("#gID").val(),'from':$("#from").val()});
        	 $("#jpagination").pagination('setPageIndex', 0);
        	 $("#jpagination").pagination('remote');
        });
    	
        dialogPosCenter('#J_dialog');
        $('#J_addCustomer').on('click', function() {
        	$('#J_dialog,.cover-all').show();
            $('#J_dialog .dialog-title').text("新增客户");
            $('#J_dialog input[type="reset"]').trigger('click');
            $('#J_dialog .dialog-body input').not($('input[name="customType"]')).val('');
        });
     
        $('#J_dialog').on('click', '.dialog-ok', function() {
        	var id=$("#id1").val();
        	
        	if(id!=''&&id!=undefined){
        		$.post("${root}/scms/store/updategroupdown.do",{storeId:id,storeGroupID:$("#storeGroupID").val()},function(data){
            		if(data.success){
            			$("#id1").val('');
            			$('#J_dialog input[type="reset"]').trigger('click');
            			$("#jpagination").pagination('remote');
            			$('.cover-all').fadeOut();
            			$('#J_dialog').fadeOut();
            		}
            		layer.msg(data.message);
            	},"json");
        	}
        	
        }).on('click', '.dialog-cancel, .dialog-close', function() {
            $('#J_dialog input[type="reset"]').trigger('click');
        	$('.cover-all').fadeOut();
            $('#J_dialog').fadeOut();
        });
	});

        
	// 验证电话号码
    function checkTelPhone(tel){
	  if(tel!=""&& !tel.match(/^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/)){
		  alert("电话号码不正确！");
		  $("#_tel").focus();
		  return false;
	  }
    	return true;
    }
        
	
    function deleteOrder(id){
    	if(confirm("确定删除该信息?") == true) {
    		$.post("${root}/scms/store/delete.do",{id:id},function(data){
				if(data.success){
					layer.msg("删除成功！");
					$("#sub").trigger("click");
				}else{
					layer.msg(data.message);
				}
			},'json');
    	}
    }
	
	// 验证手机号码
    function checkSubmitMobil(mobile) {
		if (mobile == "") {
      	alert("手机号码不能为空！");
      	$("#_mobile").focus();
     		return false;
    	}
     if (!mobile.match(/^(0|86|17951)?(1[3-9])[0-9]{9}$/)) {
     	alert("手机号码格式不正确！");
      	$("#_mobile").focus();
     		return false;
   	 	}
    	return true;
    }
</script>
</body>
</html>