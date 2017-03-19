<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>添加/编辑消息</title>
<%@ include file="../common/head.jsp"%>
</head>
<body>
<body class="wrap-bd">
<div class="mb-small">
    <a href="${root}/customer/messageManage/toIndex.do?pageIndex=${pageIndex}">返回消息列表</a>
</div>
<fieldset class="bg">
    <c:if test="${empty spPushMsg}">
    <legend>添加消息</legend>
    </c:if>
    <c:if test="${!empty spPushMsg}">
    <legend>编辑消息</legend>
    </c:if>
    <form method="post" id="messageForm">
    <c:if test="${!empty spPushMsg}">
    <input type="hidden" name="isEdit" value="1">
    <input type="hidden" name="id" value="${spPushMsg.id}">
    </c:if>
    <p>
        <label class="label">消息标题：</label>
        <input class="input input-default J_disabled" type="text" name="msgTitle" value="${spPushMsg.msgTitle}" id="msgTitleId">
    </p>
    <p>
        <label class="label" style="vertical-align: top;">消息内容：</label>
        <textarea class="textarea J_disabled" name="content" cols="60" rows="4" id="conId">${spPushMsg.content}</textarea>
    </p>
    <p>
        <label class="label">消息类型：</label>
        <select class="select J_disabled" name="msgType" id="typeSele">
            <c:forEach items="${typeList}" var="spPushMsgType">
                 <option value="${spPushMsgType.id}">${spPushMsgType.name}</option>            
            </c:forEach>
        </select>
    </p>
    <p id="sendStatus">
        <input type="hidden" value="${spPushMsg.id}">
        <label class="label">是否发送：</label>
        <c:if test="${spPushMsg.status==1}">
        <input type="radio" value="1" name="status" checked="checked">&nbsp;是&nbsp;
        <input type="radio" value="0" name="status" disabled="true">&nbsp;否&nbsp;
        </c:if>
        <c:if test="${spPushMsg.status==0}">
        <input type="radio" value="1" name="status">&nbsp;是&nbsp;
        <input type="radio" value="0" name="status" checked="checked">&nbsp;否&nbsp;
        </c:if>
        <c:if test="${empty spPushMsg.status}">
        <input type="radio" value="1" name="status" checked="checked">&nbsp;是&nbsp;
        <input type="radio" value="0" name="status">&nbsp;否&nbsp;
        </c:if>
    </p>
    <div id="spGroupDiv"></div>
    <div id="DBSpGroupDiv"></div><!--存放数据中被选中的  -->
    <div id="storeDiv"></div>
    <div id="DBStoreDiv"></div><!--存放数据中被选中的  -->
    <p id="sendCustomer">
        <label class="label">发送对象：</label>
        <c:if test="${spPushMsg.umPushType=='ALL_PUSH'}">
        <input class="J_disabled" type="radio" value="ALL_PUSH" name="umPushType" checked="checked">&nbsp;全部用户&nbsp;
        <input class="J_disabled" type="radio" value="SPGROUP_PUSH" name="umPushType" >&nbsp;指定定格&nbsp;
        <input class="J_disabled" type="radio" value="SHOP_PUSH" name="umPushType" >&nbsp;指定用户&nbsp;
        </c:if>
        <c:if test="${spPushMsg.umPushType=='SPGROUP_PUSH'}">
        <input class="J_disabled" type="radio" value="ALL_PUSH" name="umPushType">&nbsp;全部用户&nbsp;
        <input class="J_disabled" type="radio" value="SPGROUP_PUSH" name="umPushType" checked="checked">&nbsp;指定定格&nbsp;
        <input class="J_disabled" type="radio" value="SHOP_PUSH" name="umPushType">&nbsp;指定用户&nbsp;
        </c:if>
        <c:if test="${spPushMsg.umPushType == 'SHOP_PUSH'}">
        <input class="J_disabled" type="radio" value="ALL_PUSH" name="umPushType">&nbsp;全部用户&nbsp;
        <input class="J_disabled" type="radio" value="SPGROUP_PUSH" name="umPushType">&nbsp;指定定格&nbsp;
        <input class="J_disabled" type="radio" value="SHOP_PUSH" name="umPushType"  checked="checked">&nbsp;指定用户&nbsp;
        </c:if>
        <c:if test="${empty spPushMsg.umPushType}">
        <input class="J_disabled" type="radio" value="ALL_PUSH" name="umPushType" checked="checked">&nbsp;全部用户&nbsp;
        <input class="J_disabled" type="radio" value="SPGROUP_PUSH" name="umPushType">&nbsp;指定定格&nbsp;
        <input class="J_disabled" type="radio" value="SHOP_PUSH" name="umPushType">&nbsp;指定用户&nbsp;
        </c:if>
    </p>
    </form>
    <div id="spGroup" class="hidden">
	    <table class="table-list">
	        <thead class="table-thead">
	        <tr>
	            <th colspan="8">
	                <div class="fl ml-default">
	                    <input class="J_selectAll J_disabled" type="checkbox" name="coupon">&nbsp;全选
	                    <span class="button button-operate ml-default J_delAll">移除</span>
	                    <span class="button button-operate spGroup J_addAll">添加</span>
	                </div>
	                <form method="post" class="fr">
	                    <input class="input input-search-text" type="text" name="keyStr" value="${keyStr}" id="spKeyStr" placeholder="定格名称/编号" />
	                    <input type="text" style='display:none' />  
	                    <input type="hidden" name="pageIndex" value="0">
	                    <input type="hidden" name="pageSize" value="10">
	                    <input type="hidden" name="id" value="${spPushMsg.id}">
	                    <input class="input input-search-button" value="搜索" type="button" id="spGroupSub"/>
	                </form>
	            </th>
	        </tr>
	        <tr>
	            <th>&nbsp;</th>
	            <th>定格编号</th>
	            <th>定格名称</th>
	            <th>操作</th>
	        </tr>
	        </thead>
	        <tbody class="table-tbody" id="spGroupTable">
	        <!-- <tr>
	            <td><input class="ml-small J_checkbox" type="checkbox" name="coupon"></td>
	            <td>0001120003</td>
	            <td>南山定格</td>
	            <td>
	                <span class="button-operate J_del">移除</span>
	            </td>
	        </tr> -->
	        </tbody>
	    </table>
		<%@ include file="../common/pagination.jsp"%>
        <div id="jpagination" class="mt-small clearfix"></div>
    </div>
    <div id="shop" class="hidden">
	    <table class="table-list">
	        <thead class="table-thead">
	        <tr>
	            <th colspan="8">
	                <div class="fl ml-default J_hide">
	                    <input class="J_selectAll J_disabled" type="checkbox" name="coupon">&nbsp;全选
	                    <span class="button button-operate ml-default J_delAll">移除</span>
	                    <span class="button button-operate store J_addAll">添加</span>
	                </div>
	                <form  method="post" class="fr" id="storeForm">
	                    <input class="input input-search-text" type="text" name="keyStr" value="${keyStr}" placeholder="商铺编号/名称/店主名/手机号/定格名称" id="storKeyStr"/>
	                    <input type="text" style='display:none' />
	                    <input type="hidden" name="pageIndex" value="0">
	                    <input type="hidden" name="pageSize" value="10">
	                    <input type="hidden" name="id" value="${spPushMsg.id}" >
	                    <input class="input input-search-button" value="搜索" type="button" id="storeSub"/>
	                </form>
	            </th>
	        </tr>
	        <tr>
	            <th>&nbsp;</th>
	            <th>商铺编号</th>
	            <th>商铺名称</th>
	            <th>店主名</th>
	            <th>手机号</th>
	            <th>定格名称</th>
	            <th>操作</th>
	        </tr>
	        </thead>
	        <tbody class="table-tbody" id="storeTable">
	        <!-- <tr>
	            <td><input class="ml-small J_checkbox" type="checkbox" name="coupon"></td>
	            <td>0001120003</td>
	            <td>佳佳便利店</td>
	            <td>小明</td>
	            <td>15013495877</td>
	            <td>南山定格</td>
	            <td>
	                <span class="button-operate J_del">移除</span>
	            </td>
	        </tr> -->
	        </tbody>
	    </table>
		<div id="pagination2" class="mt-small clearfix"></div>
    </div>
    <p>
        <button class="button button-ok J_hide J_btnSend">发送</button>
        <button class="button button-ok J_hide J_btnSave">保存</button>
        <button class="button button-cancel J_hide J_btnCancel">取消</button>
    </p>
</fieldset>
<script>
    $(function() {
    	var spGroupUrl = '${root}/customer/messageManage/getSpGroupList.do';
    	var storeUrl = '${root}/customer/messageManage/getAllStore.do';
    	
        // 消息类型  编辑
        if($.trim("${spPushMsg.msgType}") != "") {
        	$("#typeSele").val("${spPushMsg.msgType}");
        }
        // 发送状态 切换
        $('.J_btnSave').hide();
        $('#sendStatus').on('click', 'input[name="status"]', function() {
            var $i = $(this).index('input[name="status"');
            if(0 == $i) {
                $('.J_btnSend').show();
                $('.J_btnSave').hide();
            } else {
                $('.J_btnSend').hide();
                $('.J_btnSave').show();
            }
        });
        // 发送状态  编辑
    	var msgId = "${spPushMsg.id}";
    	if($.trim(msgId) != "") {
    		if($('#sendStatus input[name="status"][value="1"]').is(':checked')) {
    			$('#sendStatus').off('click');
                $('.J_disabled').attr("disabled", "true");
                $('.J_hide').hide();
        	} else {
                $('.J_btnSend').hide();
                $('.J_btnSave').show();
                $('.J_btnCancel').show();
        	}
    	}
        // 发送对象 切换
        $('#sendCustomer').on('click', 'input[name="umPushType"]', function() {
            var $i = $(this).index('input[name="umPushType"');
            if(1 == $i){
                $('#spGroup').show();
                $('#shop').hide();
            } else if(2 == $i) {
                $('#spGroup').hide();
                $('#shop').show();
            } else {
                $('#spGroup').hide();
                $('#shop').hide();
            }
        });
        // 发送对象
    	if($('input[name="umPushType"][value="SHOP_PUSH"]').is(':checked')) {
    		storeUrl='${root}/customer/messageManage/getSelectedStoreList.do?id=${spPushMsg.id}'
        	$('#shop').show();
            $('#spGroup').hide();
            $.ajax({
				type: "POST",
				url: "${root}/customer/messageManage/getSelectedStFromSpPush.do?id=${spPushMsg.id}",
				async: true,
				dataType:'json',
				success: function(da) {
					if(da.success) {
						var dbStoreHtml="";
						$.each(da.message, function(i, item) {
							dbStoreHtml+='<input name="storeIdArr" type="hidden" value="'+item.id+'">'
						});
						$("#DBStoreDiv").append(dbStoreHtml);
					} else {
						alert(da.message);
					}
				},
				error: function(da) {
				}
			});
    	}
    	if($('input[name="umPushType"][value="SPGROUP_PUSH"]').is(':checked')) {
    		spGroupUrl="${root}/customer/messageManage/getSelectedSpGroupList.do?id=${spPushMsg.id}";
            $('#shop').hide();
            $('#spGroup').show();
            $.ajax({
				type: "POST",
				url: "${root}/customer/messageManage/getSelectedSpGFromSpPush.do?id=${spPushMsg.id}",
				async: true,
				dataType:'json',
				success: function(da) {
					if(da.success) {
						var dbSpGroupHtml="";
						$.each(da.message, function(i,item) {
							dbSpGroupHtml+='<input name="spGroupIdArr" type="hidden" value="'+item.id+'">'
						});
						$("#DBSpGroupDiv").append(dbSpGroupHtml);
					} else {
						alert(da.message);
					}
				},
				error: function(da) {
				}
			});
    	}
    	if($('input[name="umPushType"][value="ALL_PUSH"]').is(':checked')) {
            $('#shop').hide();
            $('#spGroup').hide();
    	}
    	
        /******************************** 定格 begin ************************/
        // 定格添加按钮
        $("#spGroup").on('click', '.J_addAll', function() {
        	if($('#spGroup .J_checkbox:checked').length <= 0) {
        		alert("请选择定格!");
        		return;
        	}
        	// 将店铺隐藏div清空
        	$("#storeDiv").empty();
        	$("#DBStoreDiv").empty();
        	// 店铺第一页状态重置
        	$('#storeTable .J_checkbox').prop('checked','');
        	$('#storeTable span.J_del').each(function() {
        	  $(this).removeClass('j_del');
        	  $(this).addClass('j_add');
        	  $(this).html('添加');
        	});
        	
        	var html = '';
        	$('#spGroupTable .J_checkbox').each(function() {
        	  if($(this).is(':checked')) {
        		if($('#DBSpGroupDiv input[value="' + $(this).val() + '"]').length <= 0) {
    				if($('#spGroupDiv input[value="' + $(this).val() + '"]').length <= 0) {
                      html+='<input type="hidden" name="spGroupIdArr" value="' + $(this).val() + '">'        			
    				}
    			}
    			$(this).parent('td').siblings(':last').html('<span class="button-operate J_del">移除</span>');
    		  }
        	});
        	$("#spGroupDiv").append(html);
        	$('#spGroup .J_selectAll').prop('checked', '');
        });
        // 定格  全选
        $('#spGroup .J_selectAll').on('click', function() {
            if($(this).is(':checked')){
                $('#spGroup .J_checkbox').prop('checked',$(this).prop('checked'));
        	}
        });
        
        $('#spGroup .J_checkbox').on('click', function() {
            if($('#spGroup .J_checkbox').length == $('#spGroup .J_checkbox:checked').length) {
                $('#spGroup .J_selectAll').prop('checked', 'checked');
            } else {
                $('#spGroup .J_selectAll').prop('checked', '');
            }
        });
        
        // 定格移除(移除选中)
        $('#spGroup').on('click', '.J_delAll', function() {
        	if($('spGroup .J_checkbox:checked').length < 0) {
        		alert("请选择定格!");
        		return;
        	}
        	$('#spGroup .J_checkbox').each(function() {
        		if($(this).is(':checked')) {
        			$('#DBSpGroupDiv input[name="spGroupIdArr"][value="'+$(this).val()+'"]').remove();
        			$('#spGroupDiv input[name="spGroupIdArr"][value="'+$(this).val()+'"]').remove();
        			$(this).parent('td').siblings(':last').html('<span class="button-operate J_add">添加</span>')
        		}
        	});
            $('#spGroup .J_checkbox').prop('checked', '');
            $('#spGroup .J_selectAll').prop('checked', '');
        });
        // 定格 单行移除
        $('#spGroup').on('click','.J_del', function() {
        	var $th =$(this).parent('td').siblings(':first').children(); 
        	$th.prop('checked','');
        	$(this).parent('td').html('<span class="button-operate J_add">添加</span>');
        	$('#DBSpGroupDiv input[name="spGroupIdArr"][value="'+$th.val()+'"]').remove();
			$('#spGroupDiv input[name="spGroupIdArr"][value="'+$th.val()+'"]').remove();
        });
        // 定格单行添加
        $('#spGroup').on('click', '.J_add', function() {
            // 将店铺隐藏div清空
        	$("#storeDiv").empty();
        	$("#DBStoreDiv").empty();
        	// 店铺第一页状态重置
        	$('#storeTable .J_checkbox').prop('checked', '');
        	$('#storeTable span.J_del').each(function() {
        	  $(this).removeClass('j_del');
        	  $(this).addClass('j_add');
        	  $(this).html('添加');
        	});
        	
        	var html = '';
        	var $th =$(this).parent('td').siblings(':first').children(); 
        	$th.prop('checked','checked');
        	$(this).parent('td').html('<span class="button-operate J_del">移除</span>');
        	if($('#DBSpGroupDiv input[value="'+$th.val()+'"]').length<=0) {
				if($('#spGroupDiv input[value="'+$th.val()+'"]').length<=0) {
                  html+='<input type="hidden" name="spGroupIdArr" value="'+$th.val()+'">';       			
				}
			}
        	$("#spGroupDiv").append(html);
        });
        // 分页 定格
        $("#jpagination").pagination({
            pageSize: 10,
            remote: {
                url: spGroupUrl,
                totalName: 'totalSize',
                params: { keyStr: $("#spKeyStr").val()},
                dataType:'json',
                success: function(data) {
                	var html = '';
                	$.each(data.list, function(index, item) {
                		if(item.isSelected == '1') {
                	    	html+='<tr><td><input class="ml-small J_checkbox" type="checkbox" name="spGroupCheckBox" value="'+item.id+'"></td><td>'+item.id+'</td><td>'+item.name+'</td>'
               	         +'<td><span class="button-operate J_del">移除</span></td></tr>';
                		} else {
                			html+='<tr><td><input class="ml-small J_checkbox" type="checkbox" name="spGroupCheckBox" value="'+item.id+'"></td><td>'+item.id+'</td><td>'+item.name+'</td>'
                	         +'<td><span class="button-operate J_add">添加</span></td></tr>';                			
                		}
                	});
                	$("#spGroupTable").html(html);
                	$('input[name="spGroupIdArr"]').each(function() {
                		$('input[name="spGroupCheckBox"][value="'+$(this).val()+'"]').prop("checked","checked");
                	});
                }
            }
        });
        //定格搜索
        $('#spGroupSub').on('click',function() {
			$("#jpagination").pagination('setParams', {keyStr: $("#spKeyStr").val()});
			$("#jpagination").pagination('setPageIndex', 0);
        	$("#jpagination").pagination('remote');
        });
        /******************************** 定格 end ************************/
       
        /******************************** 店铺 begin ************************/
        // 店铺 添加按钮
        $(".store.J_addAll").on('click', function() {
        	var html = '';
        	// 判断有没有checkbox被选中
        	if($('#storeTable .J_checkbox:checked').length <= 0) {
        		alert("请选择店铺!");
        		return;
        	}
        	// 将定格隐藏div清空
        	$('#DBSpGroupDiv').empty();
        	$('#spGroupDiv').empty();
        	// 定格第一页状态重置
        	$('#spGroupTable .J_checkbox').prop('checked', '');
        	$('#spGroupTable span.J_del').each(function() {
        		$(this).removeClass('J_del');
        	    $(this).addClass('j_add');
        	    $(this).html('添加');
        	});
        	
        	$('#storeTable .J_checkbox').each(function() {
        		if($(this).prop('checked')){
        			if($('#DBStoreDiv input[value="'+$(this).val()+'"]').length <= 0) {
        				if($('#storeDiv input[value="'+$(this).val()+'"]').length <= 0) {
                          html+='<input type="hidden" name="storeIdArr" value="'+$(this).val()+'">'        			
        				}
        			}
        			$(this).parent('td').siblings(':last').html('<span class="button-operate J_del">移除</span>')
        		}
        	});
        	$("#storeDiv").append(html);
        	$('#shop .J_selectAll').prop('checked','');
        });
        // 店铺 移除全部按钮
        $('#shop .J_delAll').on('click', function() {
        	if($('#storeTable .J_checkbox:checked').length<0) {
        		alert("请选择店铺!");
        		return;
        	}
        	$('#shop .J_checkbox').each(function() {
        		if($(this).prop('checked')==true) {
        			$('#DBStoreDiv input[name="storeIdArr"][value="'+$(this).val()+'"]').remove();
        			$('#storeDiv input[name="storeIdArr"][value="'+$(this).val()+'"]').remove();
        			$(this).parent('td').siblings(':last').html('<span class="button-operate J_add">添加</span>')
        		}
        	});
            $('#shop .J_checkbox').prop('checked','');
            $('#shop .J_selectAll').prop('checked','');
        });
        // 店铺 单行移除
        $('#shop').on('click','.J_del',function() {
        	var $th =$(this).parent('td').siblings(':first').children(); 
        	$th.prop('checked','');
        	$(this).parent('td').html('<span class="button-operate J_add">添加</span>');
        	$('#DBStoreDiv input[name="storeIdArr"][value="'+$th.val()+'"]').remove();
			$('#storeDiv input[name="storeIdArr"][value="'+$th.val()+'"]').remove();
        });
        // 店铺 单行添加
        $('#shop').on('click', '.J_add', function() {
        	//将定格隐藏div清空
        	$('#DBSpGroupDiv').empty();
        	$('#spGroupDiv').empty();
        	//定格第一页状态重置
        	$('#spGroupTable .J_checkbox').prop('checked','');
        	$('#spGroupTable span.J_del').each(function(){
        	  $(this).removeClass('j_del');
        	  $(this).addClass('j_add');
        	  $(this).html('添加');
        	});
        	var html = '';
        	var $th =$(this).parent('td').siblings(':first').children(); 
        	$th.prop('checked','checked');
        	$(this).parent('td').html('<span class="button-operate J_del">移除</span>');
        	if($('#DBStoreDiv input[value="'+$th.val()+'"]').length<=0){
				if($('#storeDiv input[value="'+$th.val()+'"]').length<=0){
                  html+='<input type="hidden" name="storeIdArr" value="'+$th.val()+'">';       			
				}
			}
        	$("#storeDiv").append(html);
        });
        // 指定用户 全选按钮
        $('#shop .J_selectAll').on('click', function() {
        	if($(this).is(':checked')) {
            $('#shop .J_checkbox').prop('checked',$(this).prop('checked'));
        	}
        });
        $('#shop .J_checkbox').on('click', function() {
            if($('#shop .J_checkbox').length == $('#shop .J_checkbox:checked').length) {
                $('#shop .J_selectAll').prop('checked', 'checked');
            } else {
                $('#shop .J_selectAll').prop('checked', '');
            }
        });
      	// 分页 店铺
        $("#pagination2").pagination({
            pageSize: 10,
            remote: {
                url: storeUrl,
                totalName: 'totalSize',
                dataType:'json',
                success: function (data) {
                	var html = '';
                	$.each(data.list, function(index, item) {
                		if(item.isSelected=='1') {
                			html += '<tr><td><input class="ml-small J_checkbox" type="checkbox" name="storeCheckBox" value="'+item.id+'"></td><td>'+item.id+'</td><td>'+item.name+'</td><td>'+item.contact+'</td><td>'+item.mobile+'</td>'
             	           			+'<td>'+item.spGropName+'</td><td><span class="button-operate J_del">移除</span></td></tr>';
                		} else {
                			html += '<tr><td><input class="ml-small J_checkbox" type="checkbox" name="storeCheckBox" value="'+item.id+'"></td><td>'+item.id+'</td><td>'+item.name+'</td><td>'+item.contact+'</td><td>'+item.mobile+'</td>'
             	           			+'<td>'+item.spGropName+'</td><td><span class="button-operate J_add">添加</span></td></tr>';
                		}
                	});
                	$("#storeTable").html(html);
                	$('input[name="storeIdArr"]').each(function() {
                		$('input[name="storeCheckBox"][value="'+$(this).val()+'"]').prop("checked","checked");
                	}); 
                }
            }
        });
        //店铺搜索按钮
        $("#storeSub").on('click', function(){
			$("#pagination2").pagination('setParams', {keyStr: $("#storKeyStr").val()});
			$("#pagination2").pagination('setPageIndex', 0);
        	$("#pagination2").pagination('remote');
        });
        /******************************** 店铺 end ************************/
        
        function checkForm() {
        	if($.trim($("#msgTitleId").val()) == "") {
        		alert("标题不能为空!");
        		return false;
        	}
        	if($.trim($("#conId").val()) == "") {
        		alert("消息不能为空!");
        		return false;
        	}
        	if($("#conId").val().length > 60) {
        		alert("消息字数不能超过60字!");
        		return false;
        	}
        	if($('input[name="umPushType"][value="SPGROUP_PUSH"]').is(':checked') && $('input[name="spGroupIdArr"]').length <= 0) {
        		alert("请分配发送对象!");
        		return false;
        	}
        	if($('input[name="umPushType"][value="SHOP_PUSH"]').is(':checked') && $('input[name="storeIdArr"]').length <= 0) {
        		alert("请分配发送对象!");
        		return false;
        	}
			return true;
        }
        // 保存按钮
        $(".J_btnSave").on('click', function() {
        	if(checkForm()) {
            	$.ajax({
    				type: "POST",
    				url: "${root}/customer/messageManage/saveMessage.do",
    				async: false,
    				data: $("#messageForm").serialize(),
    				dataType:'json',
    				success: function(da) {
    					if(da.success) {
    						alert(da.message);
    						location.href="${root}/customer/messageManage/toIndex.do";
    					} else {
    						alert(da.message);
    					}
    				},
    				error: function(da) {
    					alert("bad request");
    				}
    			});
        	}
        });
        // 发送按钮
        $(".J_btnSend").on('click',function() {
        	if(checkForm()) {
	        	$.ajax({
					type: "POST",
					url: "${root}/customer/messageManage/sendMsg.do",
					async: false,
					data: $("#messageForm").serialize(),
					dataType:'json',
					success: function(da) {
						if(da.success) {
						alert(da.message);
						location.href="${root}/customer/messageManage/toIndex.do";
						} else {
						  alert(da.message);
						}
					},
					error: function(da) {
						alert("bad request");
					}
				});
        	}
        });
    	// 取消按钮  跳到编辑页
    	$('.J_btnCancel').on('click', function() {
    	    location.href='${root}/customer/messageManage/toIndex.do';
    	});
    });
</script>
</body>
</html>