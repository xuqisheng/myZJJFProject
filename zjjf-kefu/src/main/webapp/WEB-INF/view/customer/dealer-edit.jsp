<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>编辑经销商</title>
    <%@ include file="../common/head.jsp"%>
    <script src="${root}/resources/js/comm.js"></script>
    <style>
    	table td{padding: 4px}
    </style>
</head>
<body>
<div class="wrap-bd">
    <div class="mb-default">
        <a class="crumb" href="${root}/scms/manager/listPage.do?isUpdate=manager">经销商管理</a><a class="crumb crumb-active">编辑经销商</a>
    </div>
    <form id="managerFrom" action="${root}/scms/manager/addScmsManager.do">
	    <fieldset class="wrap-bd bg mt-default">
	        <legend>基础信息</legend>
	        <div>
	            <p class="required">
	                <label class="label">经销商名称：</label>
	                <input type="hidden" name="id" value="${scmsManager.id}" maxlength="100">
	                <input type="text" class="input input-default" name="managerName" value="${scmsManager.managerName}" maxlength="16" data-shortcut="enter">
	            </p>
	            <p>
	                <label class="label">编码：</label>
	                <input type="text" class="input input-default" name="managerCode" value="${scmsManager.managerCode}" maxlength="16" disabled="disabled"  onkeyup="javascript:RepNumberAndEnglish(this)" onblur="javascript:RepNumberAndEnglish(this)">
	            </p>
	            <div class="clearfix">
	                <label class="label dis-ib va-t">经销区域：</label>
	                <div class="dis-ib" id="groups">
	                	<div class="mb-small" id="group">
	                	<table id="groups">
	                		<tr>
	                			<td colspan="3">
	                				<span class="button button-operate" id="J_addInput">添加</span>
	                			</td>
	                		</tr>
	                    </table>
		                </div>
	                </div>
	            </div>
	            <p>
	                <label class="label">地区：</label>
	                <select class="select" id="regionsPId1" name="province">
	                	<option value="">请选择</option>
	                	<c:forEach items="${regions}" varStatus="i" var="region" >
	                        <option value="${region.id}">${region.name}</option> 
						</c:forEach>
	                </select>
	                <select class="select" id="regionsPId2" name="city">
	                    <option value="" selected="selected">请选择</option>
	                </select>
	                <select class="select"  id="regionsPId3" name="county">
	                    <option value="" selected="selected">请选择</option>
	                </select>
	            </p>
	            <p class="required">
	                <label class="label">详细地址：</label>
	                <input type="text" class="input input-default" name="managerAddress" value="${scmsManager.managerAddress}" maxlength="100" data-shortcut="enter">
	            </p>
	            <p>
	                <label class="label">电话：</label>
	                <input type="tel" class="input input-default" name="callNum" value="${scmsManager.callNum}" maxlength="14"  onblur="javascript:RepTel(this)">
	            </p>
	            <p>
	                <label class="label">传真：</label>
	                <input type="text" class="input input-default" name="fax" value="${scmsManager.fax}" maxlength="20">
            	</p>
	            <p class="required">
	                <label class="label">联系人：</label>
	                <input type="text" class="input input-default" name="branderName" value="${scmsManager.branderName}"  maxlength="10" data-shortcut="enter">
	            </p>
	            <p>
	                <label class="label">职位：</label>
	                <input type="text" class="input input-default" name="job"  value="${scmsManager.job}"  maxlength="20">
            	</p>
	            <p class="required">
	                <label class="label">手机号：</label>
	                <input type="tel" class="input input-default" name="mobile"  value="${scmsManager.mobile}" maxlength="11" disabled="disabled">
	            </p>
	            <p>
	                <label class="label">Email：</label>
	                <input type="email" class="input input-default" name="email" value="${scmsManager.email}" maxlength="30">
	            </p>
	            <p hidden="true">
	                <label class="label">起购量：</label>
	                <input type="text" class="input input-default" name="minimum"  value="${scmsManager.minimum}" maxlength="4">
	            </p>
	            <p>
	                <label class="label">备注：</label>
	                <textarea class="textarea textarea-default" rows="6" name="managerMark" maxlength="100">${scmsManager.managerMark}</textarea>
	            </p>
	        </div>
	    </fieldset>
	    <fieldset class="wrap-bd bg mt-default">
	        <legend>账号信息</legend>
	        <div>
	            <p class="required">
	                <label class="label">用户名：</label>
	                <input type="text" class="input input-default" name="userName" value="${scmsManager.userName}" disabled="disabled"  maxlength="16"  onkeyup="javascript:RepNumberAndEnglish(this)" onblur="javascript:RepNumberAndEnglish(this)">
	            </p>
	            <p>
	                <label class="label">密码：</label>
	                <input type="password" class="input input-default" name="password"  maxlength="30">
	            </p>
	            <p>
	                <label class="label">确认密码：</label>
	                <input type="password" class="input input-default" id="confirmPwd"  maxlength="30">
	            </p>
	        </div>
	    </fieldset>
	    <fieldset class="wrap-bd bg mt-default">
	        <legend>财务信息</legend>
	        <div>
	            <p>
	                <label class="label">开户名称：</label>
	                <input type="text" class="input input-default" name="bankUserName" value="${scmsManager.bankUserName}"  maxlength="50">
	            </p>
	            <p>
	                <label class="label">开户银行：</label>
	                <input type="text" class="input input-default" name="bankName" value="${scmsManager.bankName}"  maxlength="50">
	            </p>
	            <p>
	                <label class="label">银行账号：</label>
	                <input type="text" class="input input-default" name="bankNum" value="${scmsManager.bankNum}"  maxlength="30" onkeyup="javascript:RepNumber(this)" onblur="javascript:RepNumber(this)">
	            </p>
	        </div>
	    </fieldset>
    </form>
    <p>
        <input type="button" class="button button-ok" value="保存" id="submit">
        <input type="button" class="button button-cancel" value="取消" onclick="javascript:location.href=document.referrer;">
    </p>
</div>
<script>
	$(function(){
		$('.button-ok').on('click' , function(){
			//加载层-风格3
			layer.load(2);
			
			var msg = ''
			var mobile = $.trim($('input[name="mobile"]').val());
			var telReg = mobile.match(/^(0|86|17951)?(1[3-9])[0-9]{9}$/);
			if($.trim($('input[name="managerName"]').val()) == '' ){
				//此处演示关闭
				setTimeout(function(){
				    layer.closeAll('loading');
				});
				layer.tips('请输入经销商名称', $('input[name="managerName"]'));
				$('input[name="managerName"]').focus();
			}else if($.trim($('#regionsPId1').val()) == ''){
				//此处演示关闭
				setTimeout(function(){
				    layer.closeAll('loading');
				});
				layer.tips('请选择省', $('#regionsPId1'));
				$('#regionsPId1').focus();
			}else if($.trim($('#regionsPId2').val()) == ''){
				//此处演示关闭
				setTimeout(function(){
				    layer.closeAll('loading');
				});
				layer.tips('请选择市', $('#regionsPId2'));
				$('#regionsPId2').focus();
			}else if($.trim($('#regionsPId3').val()) == ''){
				//此处演示关闭
				setTimeout(function(){
				    layer.closeAll('loading');
				});
				layer.tips('请选择区/县', $('#regionsPId3'));
				$('#regionsPId3').focus();
			}else if($.trim($('input[name="managerAddress"]').val()) == ''){
				//此处演示关闭
				setTimeout(function(){
				    layer.closeAll('loading');
				});
				layer.tips('请输入地址', $('input[name="managerAddress"]'));
				$('input[name="managerAddress"]').focus();
			}else if($.trim($('input[name="branderName"]').val()) == ''){
				//此处演示关闭
				setTimeout(function(){
				    layer.closeAll('loading');
				});
				layer.tips('请输入联系人', $('input[name="branderName"]'));
				$('input[name="branderName"]').focus();
			}else if(mobile == ''){
				//此处演示关闭
				setTimeout(function(){
				    layer.closeAll('loading');
				});
				layer.tips('请输入手机号', $('input[name="mobile"]'));
				$('input[name="mobile"]').focus();
			}else if(!telReg){
				//此处演示关闭
				setTimeout(function(){
				    layer.closeAll('loading');
				});
				layer.tips('请输入正确的手机号', $('input[name="mobile"]'));
				$('input[name="mobile"]').focus();
			}else if($.trim($('input[name="userName"]').val()) == ''){
				//此处演示关闭
				setTimeout(function(){
				    layer.closeAll('loading');
				});
				layer.tips('请输入用户名', $('input[name="userName"]'));
				$('input[name="userName"]').focus();
			}else if($('input[name="password"]').val() != $('#confirmPwd').val()){
				//此处演示关闭
				setTimeout(function(){
				    layer.closeAll('loading');
				});
				layer.tips('两次输入密码不匹配', $('#confirmPwd'));
				$('#confirmPwd').focus();
			}else{
				var num = 0;
				var index = 0;
				$('#groups').find('tr').each(function(){
					index ++;
					num = 0;
					var groupId1 = $(this).find('select[name="groupIds"]').val();
					$('#groups').find('tr').each(function(){
						var groupId2 = $(this).find('select[name="groupIds"]').val();
						if(groupId2 == undefined){
							return true;
						}else if(groupId1 == groupId2){
							num++;
						}
						if(num > 1){
							//此处演示关闭
							setTimeout(function(){
							    layer.closeAll('loading');
							});
							layer.tips('区域信息录入重复', $(this).find('select[name="groupIds"]'));
							$(this).find('select[name="groupIds"]').focus();
							return false;
						}
					});
				});
				
				if(num > 1){
					return false;
				}else if(index == 0 || index == 1){
					//此处演示关闭
					setTimeout(function(){
					    layer.closeAll('loading');
					});
					layer.tips('请至少选择一个经销区域', '#J_addInput');
				}else{
					$.post("${root}/scms/manager/updateScmsManager.do",$('#managerFrom').serializeArray(),function(data){
						if(data.success){
							//此处演示关闭
							setTimeout(function(){
							    layer.closeAll('loading');
							});
							layer.msg(data.message, {
							    icon: 1,
							    time: 2000 //2秒关闭（如果不配置，默认是3秒）
							}, function(){
								$('.button-cancel').trigger("click"); 
							});  
						}else{
							//此处演示关闭
							setTimeout(function(){
							    layer.closeAll('loading');
							});
							layer.msg(data.message, {icon: 5});
						}
					},'json');
				}
			}
		});
		$('#confirmPwd').on('blur' , function(){
			if($('input[type="password"][name="password"]').val() != this.val()){
				layer.tips('两次输入密码不匹配', $('#confirmPwd'));
			}
		});
		$(function(){
			$.post("${root}/scms/manager/findById.do",{id:'${scmsManager.id}'},function(scmsManagers){
				if(scmsManagers.success){
					var scmsGroups = scmsManagers.message.scmsGroups;
					//获取   区域集合
					$.each(scmsGroups , function(i,scmsGroup){
						//获取 区域集合
						$.post("${root}/scms/distributionArea/findScmsGroupByUpId.do",{upId:scmsGroup.upId},function(data){
							if(data.success){
								gorupS ++;
								var html = '';
								html += '<tr><td><select class="select groupIdsUpId">';
								html += '<option value="-1">全部</option>';
								html += '<c:forEach items="${scmsGroupUpId0s}" varStatus="i" var="scmsGroupUpId0" >';
								html += '<option value="${scmsGroupUpId0.id}">${scmsGroupUpId0.name}</option></c:forEach>';
								html += '</select></td><td><select class="select" name="groupIds">'
								$.each(data.message,function(i,scmsGroup){
									html+='<option value="'+scmsGroup.id+'">'+scmsGroup.name+'</option>'
								})
								html += '</select></td><td>';
								html += '<span class="button button-operate J_delInput">删除</span></td></tr>';
								$('#J_addInput').parent().parent().before(html);
								$('#J_addInput').parent().parent().prev().find('.groupIdsUpId').val(scmsGroup.upId);
								$('#J_addInput').parent().parent().prev().find('select[name="groupIds"]').val(scmsGroup.id);
							}
						},'json');
					});
					
					//地区信息
					if(scmsManagers.message.province > 0){
						$('#regionsPId1').val(scmsManagers.message.province);
						$.post("${root}/kefu/public/findRegionsByPId.do",{pId:scmsManagers.message.province},function(data){
							var html = '<option value="">请选择</option>';
							if(data.success){
								$.each(data.message,function(i,scmsGroup){
									html+= '<option value="'+scmsGroup.id+'">'+scmsGroup.name+'</option>';
								})
								$('#regionsPId2').html(html);
								$('#regionsPId2').val(scmsManagers.message.city);
								$.post("${root}/kefu/public/findRegionsByPId.do",{pId:scmsManagers.message.city},function(data2){
									var html2 = '<option value="">请选择</option>';
									if(data2.success){
										$.each(data2.message,function(i2,scmsGroup2){
											html2+= '<option value="'+scmsGroup2.id+'">'+scmsGroup2.name+'</option>';
										})
										$('#regionsPId3').html(html2);
										$('#regionsPId3').val(scmsManagers.message.county);
									}
								},'json');
							}
							
						},'json');
					}
				}
			},'json');
		});
		$('#regionsPId1').on('change' , function(){
			$.post("${root}/kefu/public/findRegionsByPId.do",{pId:$('#regionsPId1').val()},function(data){
				var html = '<option value="">请选择</option>';
				$('#regionsPId3').html(html);
				if(data.success){
					$.each(data.message,function(i,scmsGroup){
						html+='<option value="'+scmsGroup.id+'">'+scmsGroup.name+'</option>'
					})
				}
				$('#regionsPId2').html(html);
			},'json')
		});
		$('#regionsPId2').on('change' , function(){
			$.post("${root}/kefu/public/findRegionsByPId.do",{pId:$('#regionsPId2').val()},function(data){
				var html = '<option value="">请选择</option>';
				if(data.success){
					$.each(data.message,function(i,scmsGroup){
						html+='<option value="'+scmsGroup.id+'">'+scmsGroup.name+'</option>'
					})
				}
				$('#regionsPId3').html(html);
			},'json')
		});
		
		
		
		
		$('#groups').on('change', '.groupIdsUpId', function() {
			var groupIds = $(this).parent().next().find('select[name="groupIds"]');
			$.post("${root}/scms/distributionArea/findScmsGroupByUpId.do",{upId:$(this).val()},function(data){
				if(data.success){
					var html = '';
					$.each(data.message,function(i,scmsGroup){
						html+='<option value="'+scmsGroup.id+'">'+scmsGroup.name+'</option>';
					});
					groupIds.html(html);
				}
			},'json');
	     });
		var gorupS = 0;
		$('#groups').on('click', '.J_delInput', function() {
			if(gorupS == 1){
				layer.tips('最少保留一个经销区域', $(this));
			}else{
				gorupS = gorupS - 1;
				$(this).parent().parent().empty();
			}
	     });
		$('#groups').on('click', '#J_addInput', function() {
			gorupS ++;
			var html = '';
			html += '<tr><td><select class="select groupIdsUpId">';
			html += '<option value="-1">全部</option>';
			html += '<c:forEach items="${scmsGroupUpId0s}" varStatus="i" var="scmsGroupUpId0" >';
			html += '<option value="${scmsGroupUpId0.id}">${scmsGroupUpId0.name}</option></c:forEach>';
			html += '</select></td><td><select class="select" name="groupIds"></select></td><td>';
			html += '<span class="button button-operate J_delInput">删除</span></td></tr>';
            $(this).parent().parent().before(html);
            $(this).parent().parent().prev().find('.groupIdsUpId').change();
    	});
	});
	function RepNumberAndEnglish(obj) {
		var reg =  /^[0-9a-zA-Z]*$/g;
		if (!reg.test(obj.value)) {
			var txt = obj.value;
			txt.replace(/[^0-9]+/, function (char, index, val) {//匹配第一次非数字字符
				obj.value = val.replace(/\D/g, "");//将非数字字符替换成""
				var rtextRange = null;
				if (obj.setSelectionRange) {
					obj.setSelectionRange(index, index);
				} else {//支持ie
					rtextRange = obj.createTextRange();
					rtextRange.moveStart('character', index);
					rtextRange.collapse(true);
					rtextRange.select();
				}
			});
		}
	}
	function RepNumber(obj) {
		var reg =  /^[0-9]*$/g;
		if (!reg.test(obj.value)) {
			var txt = obj.value;
			txt.replace(/[^0-9]+/, function (char, index, val) {//匹配第一次非数字字符
				obj.value = val.replace(/\D/g, "");//将非数字字符替换成""
				var rtextRange = null;
				if (obj.setSelectionRange) {
					obj.setSelectionRange(index, index);
				} else {//支持ie
					rtextRange = obj.createTextRange();
					rtextRange.moveStart('character', index);
					rtextRange.collapse(true);
					rtextRange.select();
				}
			});
		}
	}
	function RepChinese(obj) {
		var reg = /^[u4E00-u9FA5]+$/;
		if (!reg.test(obj.value)) {
			var txt = obj.value;
			txt.replace(/[^0-9]+/, function (char, index, val) {//匹配第一次非数字字符
				obj.value = val.replace(/\D/g, "");//将非数字字符替换成""
				var rtextRange = null;
				if (obj.setSelectionRange) {
					obj.setSelectionRange(index, index);
				} else {//支持ie
					rtextRange = obj.createTextRange();
					rtextRange.moveStart('character', index);
					rtextRange.collapse(true);
					rtextRange.select();
				}
			});
		}
	}
	function RepTel(obj) {
		var reg = /(^(\d{2,4}[-_－—]?)?\d{3,8}([-_－—]?\d{3,8})?([-_－—]?\d{1,7})?$)|(^0?1[35]\d{9}$)/;
		if (!reg.test(obj.value)) {
			var txt = obj.value;
			txt.replace(/[^0-9]+/, function (char, index, val) {//匹配第一次非数字字符
				obj.value = val.replace(/\D/g, "");//将非数字字符替换成""
				var rtextRange = null;
				if (obj.setSelectionRange) {
					obj.setSelectionRange(index, index);
				} else {//支持ie
					rtextRange = obj.createTextRange();
					rtextRange.moveStart('character', index);
					rtextRange.collapse(true);
					rtextRange.select();
				}
			});
		}
	}
	function RepEmail(obj) {
		var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		if (!reg.test(obj.value)) {
			var txt = obj.value;
			txt.replace(/[^0-9]+/, function (char, index, val) {//匹配第一次非数字字符
				obj.value = val.replace(/\D/g, "");//将非数字字符替换成""
				var rtextRange = null;
				if (obj.setSelectionRange) {
					obj.setSelectionRange(index, index);
				} else {//支持ie
					rtextRange = obj.createTextRange();
					rtextRange.moveStart('character', index);
					rtextRange.collapse(true);
					rtextRange.select();
				}
			});
		}
	}
</script>
</body>
</html>