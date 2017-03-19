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
				<button class="button" id="J_addCustomer">新增客户</button>
			</div>
		</div>
		<div>
			<a href="${root}/scms/store/scmshome.do" class="pills">线上客户</a> <a
				href="${root}/scms/store/scmshomedown.do" class="pills pills-active">线下客户</a>
		</div>
		<div class="op-section clearfix">
			<form>
				<input class="input-search-text ml-default" type="text"
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
						<th width="100">联系人</th>
						<th width="150">联系方式</th>
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
				<div class="required">
					<label class="label">店铺名称：</label> <input type="text" value=""
						name="name" class="input" placeholder="" id="_name" maxlength="64">
					<input type="hidden" name="" id="_id" value="">
				</div>
				<p class="required">
					<label class="label">联系人：</label> <input type="text" value=""
						name="contact" class="input" placeholder="" id="_contact"
						maxlength="12">
				</p>
				<p class="required">
					<label class="label">手机号：</label> <input type="text" value=""
						name="mobile" class="input" placeholder="" id="_mobile"
						maxlength="11">
				</p>
				<p class="required">
					<label class="label va-t">收货地址：</label>
					<textarea class="textarea" name="address" rows="2" id="_address"
						maxlength="200"></textarea>
				</p>
				<p>
					<label class="label">固定电话：</label>
                    <input type="text" value="" name="tel" class="input" id="_tel" maxlength="13">
				</p>
				<p>
					<label class="label">客户类型：</label> <input type="radio" value="1"
						name="customType" checked="checked"> 便利店 <input
						type="radio" class="ml-default" value="2" name="customType">
					餐饮店
				</p>
				<p>
					<label class="label">客户分组：</label> <select class="select">
						<option>线下客户组</option>
					</select>
				</p>
				<div>
					<label class="label va-t">备注：</label>
					<textarea class="textarea" name="remark" rows="2" id="_remark"
						maxlength="200"></textarea>
				</div>
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
	function edit(id){
		$.post('${root}/scms/store/find.do', { "id": id}, function (data) {
			$("#_name").val(data.message.name);
			$("#_contact").val(data.message.contact);
			$("#_mobile").val(data.message.mobile);
			$("#_address").val(data.message.address);
			$("#_tel").val(data.message.tel);
			$("#_remark").val(data.message.remark);
			$("#_id").val(data.message.id);
			$('input[name="customType"][value='+data.message.suType+']').prop("checked","checked");
			$('.cover-all').fadeIn();
			$('#J_dialog').fadeIn();
			$('#J_dialog .dialog-title').text("客户编辑");
		},"json");
	}
    $(function() {
    	 $("#jpagination").pagination({
             pageSize: 10,
             remote: {
                 url: '${root}/scms/store/list.do',
                 params: {'nameOrTelphone':$("#nameOrTelphone").val()},
                 success: function(data) {
                     var html='';
                     $.each(data.list, function(i,item) {
                         html+='<tr><td>线下客户组</td>';
                         html+='<td>'+item.name+'</td>';
                     	html+='<td>'+item.contact+'</td>';
    	                html+='<td>'+item.mobile+'</td>';
    	                html+='<td>'+item.address+'</td>';
                        html+='<td>'+item.suTypeName+'</td>';
                        html+='<td>线下客户</td>';
    	                html+='<td><a href="#" onclick="edit('+item.id+')">编辑</a>&nbsp;&nbsp;<a href="#" onclick="deleteOrder('+item.id+')">删除</a></td></tr>';
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
        	 $("#jpagination").pagination('setParams', {'nameOrTelphone':$.trim($("#nameOrTelphone").val())});
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

            var id=$.trim($("#_id").val());
            var name=$.trim($("#_name").val());
            if(name==""){alert("店铺名不能为空"); $("#_name").focus();return;}
            if(name.length>64){alert("店铺名太长"); $("#_name").focus();return;}
          	var contact=$.trim($("#_contact").val());
           if(contact==""){alert("联系人不能空"); $("#_contact").focus();return;}
           if(contact.length>12){alert("联系人不能超过12个字符"); $("#_contact").focus();return;}
            var mobile=$.trim($("#_mobile").val());
            if(!checkSubmitMobil(mobile))return;
            var address=$.trim($("#_address").val());
            if(address==""){alert("地址不能为空"); $("#_address").focus();return;}
            if(address.length>255){alert("地址不能超过255个字符"); $("#_address").focus();return;}
            var tel=$.trim($("#_tel").val());
            if(!checkTelPhone(tel)) return;
            var remark=$.trim($("#_remark").val());
            if(remark.length>200){alert("备注内容太长"); $("#_remark").focus();return;}
            var suType=$('input[name="customType"]:checked ').val();
            var url="";
            var data="";
            if (id == '' || id == null || id == undefined) {
            	url="${root}/scms/store/add.do";
            	data={ "name": name,"contact": contact,"mobile":mobile,"address":address,"tel":tel,"remark":remark,"suType":suType};
            }else{
            	url="${root}/scms/store/update.do";
            	data={ "name": name,"contact": contact,"mobile":mobile,"address":address,"tel":tel,"remark":remark,"suType":suType,"id":id};
            }
            $(this).attr("disabled", "disabled");
            $.post(url, data, function (data) {
            	$("#sure").removeAttr("disabled");
            	$('#J_dialog, .cover-all').fadeOut();
            	layer.msg(data.message);
           	 	$("#jpagination").pagination('remote');
            	$('#J_dialog .dialog-body input').not($('input[name="customType"]')).val('');
            	},"json");
        }).on('click', '.dialog-cancel, .dialog-close', function() {
            $('#J_dialog input[type="reset"]').trigger('click');
            $('#J_dialog, .cover-all').fadeOut();
        });
	});

	// 验证电话号码
    function checkTelPhone(tel){
	  if(tel!=""&& !tel.match(/^0\d{2,3}-\d{5,9}$/)){
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
