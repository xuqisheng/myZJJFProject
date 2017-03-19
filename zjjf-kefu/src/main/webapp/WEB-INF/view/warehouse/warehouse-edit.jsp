<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>仓库管理 - 物流仓库添加/编辑</title>
	<%@ include file="../common/head.jsp"%>
	<script src="${root}/resources/js/comm.js"></script>
</head>
<body class="wrap-bd">
<input type="hidden" value="${warehouse.id}" id="id"/>
<input type="hidden" value="${warehouse.userName}" id="warehouse_userName"/>
<div>
    <a class="crumb" href="${root}/scms/warehouse/listPage.do">物流仓库管理</a><a class="crumb crumb-active" id="aLink">添加</a>
</div>
<form id="scmsWarehouse">
<fieldset class="wrap-bd bg mt-default">
    <legend>基础信息</legend>
    <div>
        <p class="required">
            <label class="label">仓库名称：</label>
            <input type="text" class="input input-default" id="name" name="name" value="${warehouse.name}">
        </p>
        <p class="required">
            <label class="label">编码：</label>
            <input type="text" class="input input-default" id="houseCode" name="houseCode" value="${warehouse.houseCode}" oninput="javascript:replaceAndSetCursor(this)">
        </p>
         <p>
	                <label class="label">地区：</label>
	                <select class="select" id="regionsPId1" name="province" >
	                <option value=""  >请选择</option>
	                	<c:forEach items="${regions}" varStatus="i" var="region" >
	                        <option value="${region.id}">${region.name}</option>
						</c:forEach>
	                </select>
	                <select class="select" id="regionsPId2" name="city" >
	                    <option value="" >请选择</option>
	                    <c:forEach items="${city}" varStatus="i" var="region" >
	                        <option value="${region.id}">${region.name}</option>
						</c:forEach>
	                </select>
	                <select class="select"  id="regionsPId3" name="county" >
	                    <option value="" >请选择</option>
	                    <c:forEach items="${county}" varStatus="i" var="region" >
	                        <option value="${region.id}">${region.name}</option>
						</c:forEach>
	                </select>
	            </p>
        <p class="required">
            <label class="label">详细地址：</label>
            <input type="text" class="input input-default" id="houseAddress" name="houseAddress" value="${warehouse.houseAddress}">
        </p>
        <p>
            <label class="label">电话：</label>
            <input type="text" class="input input-default" id="callNum" name="callNum"  value="${warehouse.callNum}">(电话号码格式例如：0755-8578452)
        </p>
        <p>
            <label class="label">传真：</label>
            <input type="text" class="input input-default" id="fax" name="fax" value="${warehouse.fax}">
        </p>
        <p class="required">
            <label class="label">负责人：</label>
            <input type="text" class="input input-default" id="branderName" name="branderName" value="${warehouse.branderName}">
        </p>
        <p class="required">
            <label class="label">手机号：</label>
            <input type="text" class="input input-default" id="branderTel" name="branderTel" value="${warehouse.branderTel}">
        </p>
        <p>
            <label class="label">备注：</label>
            <textarea class="textarea" cols="23" rows="6" id="houseMark" name="houseMark" >${warehouse.houseMark}</textarea>
        </p>
    </div>
</fieldset>
<fieldset class="wrap-bd bg mt-default">
    <legend>物流费用</legend>
    <div>
        <label class="label">运费模板：</label>
        <input type="text" class="input input-default" value="${warehouse.tplName}" readonly="readonly" id="tplName" name="tplName">
        <input type="hidden" class="input input-default" value="${warehouse.tplId}" readonly="readonly" id="tplId" name="tplId">
        <span class="button button-white" id="J_selectTpl">选择</span>
    </div>
</fieldset>
<fieldset class="wrap-bd bg mt-default">
    <legend>财务信息</legend>
    <div>
        <p>
            <label class="label">开户名称：</label>
            <input type="text" class="input input-default" id="bankUserName" name="bankUserName" value="${warehouse.bankUserName}" maxlength="50">
        </p>
        <p>
            <label class="label">开户银行：</label>
            <input type="text" class="input input-default" id="bankName" name="bankName"  value="${warehouse.bankName}" maxlength="50">
        </p>
        <p>
            <label class="label">银行账号：</label>
            <input type="text" class="input input-default" id="bankNum" name="bankNum" value="${warehouse.bankNum}" maxlength="30" onkeyup="javascript:RepNumber(this)" onblur="javascript:RepNumber(this)">
        </p>
    </div>
</fieldset>
<fieldset class="wrap-bd bg mt-default">
    <legend>账号信息</legend>
    <div>
        <p class="required">
            <label class="label">用户名：</label>
            <input type="text" class="input input-default" id="userName" name="userName" value="${warehouse.userName}" oninput="javascript:replaceAndSetCursor(this)" >
        </p>
        <p>
            <label class="label">密码：</label>
            <input type="password" class="input input-default" id="password" name="password" maxlength="15">
        </p>
        <p>
            <label class="label">账号状态：</label>
            <input type="radio" name="status"  value="2" checked="checked"> 正常
            <input type="radio" name="status" class="ml-default" value="0"> 停用
        </p>
    </div>
</fieldset>

<p>
    <input type="button" id="saveButton" class="button button-ok" value="保存" onclick="save()">
    <input type="button" onclick="javascript:history.go(-1)" class="button button-cancel" value="取消" >
</p>
<div class="cover-all"></div>
<div class="dialog hidden" id="J_dialog">
    <div class="dialog-head">
        选择运费模板<div class="dialog-close"></div>
    </div>
    <div>
        <table class="table-list">
            <thead>
            <tr>
                <th>编号</th>
                <th>名称</th>
                <th>说明</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody class="table-tbody" id="itemFromBody">

            </tbody>
        </table>
    </div>
    <div class="dialog-foot">
        <%@ include file="../common/pagination.jsp"%>
    </div>
</div>
</form>
<script>
function getCursorPos(obj){
    return obj.selectionStart;
}

function setCursorPos(obj, pos){
    if (obj.setSelectionRange) {
        var obj1 = obj.value;
        obj.setSelectionRange(pos,pos);
    } else if (obj.createTextRange) {
        var range = obj.createTextRange();
        range.collapse(true);
        range.moveEnd('character', pos);
        range.moveStart('character', pos);
        range.select();
    }
}

function replaceAndSetCursor(obj){
    var pattern = /[^a-zA-Z\d]/g;
    var pos=getCursorPos(obj);
    var orgValue=obj.value;
    obj.value=orgValue.replace(pattern,"");
    pos=pos-Math.ceil(orgValue.length-obj.value.length);
    setCursorPos(obj,pos);
}

   $(function() {

	  $("#jpagination").pagination({
           pageSize: 10,
           showJump: true,
           remote: {
               url: '${root}/scms/warehouse/listTemplet.do',
               success: function(data) {
               	var html = "";
  				 $.each(data.list,function(i,item){
  					 html += '<tr><td>'+(i+1)+'</td>'
  					 html +='<td>'+item.name+'</td>';
  			         html +='<td>'+item.tplMark+'</td>';
  			         html +='<td><input type="hidden" value="'+item.id+'"/><input type="button" class="button button-operate J_select" value="选择" ></td>';

  			      	 html += '</tr>';
  				 });

  				 $("#itemFromBody").html(html);

                 dialogPosCenter('#J_dialog');

               },
               totalName:'totalSize'
           }
   	});

      $('#J_selectTpl').on('click', function(e) {
     	 $("#jpagination").pagination('setParams', $('#supplierForm').serializeArray());
     	 $("#jpagination").pagination('setPageIndex', 0);
     	 $("#jpagination").pagination('remote');
     });
        var $btnSelect = $('#J_selectTpl');
        var $dialog = $('#J_dialog');
        var $cover = $('.cover-all');
        $btnSelect.on('click', function() {

        	 $cover.fadeIn();
            $dialog.fadeIn();
        });
        $dialog.on('click', '.dialog-close', function() {
            $cover.hide();
            $dialog.hide();
        });

 	   $("#itemFromBody").on('click', '.J_select', function() {
 		   $("#tplId").val($(this).prev('input').val());
 		   $("#tplName").val($(this).parent().prev().prev().html());
 		   $cover.hide();
 	       $dialog.hide();
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
        	},'json');
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
        	},'json');
        });
    });


	// 验证手机号码
   function checkSubmitMobil(mobile) {
		if (mobile == "") {
     	layer.msg("手机号码不能为空！");
     	$("#_mobile").focus();
    		return false;
   	}
    if (!mobile.match(/^(0|86|17951)?(1[3-9])[0-9]{9}$/)) {
    	layer.msg("手机号码格式不正确！");
     	$("#_mobile").focus();
    		return false;
  	 	}
   	return true;
   }

	// 验证电话号码
   function checkTelPhone(tel){
	  if(tel!=""&& !tel.match(/^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/)){
		  layer.msg("电话号码不正确！");
		  $("#_tel").focus();
		  return false;
	  }
   	return true;
   }


    function save(){
    	var name=$.trim($('#name').val());
    	if(name==""){layer.msg("仓库名称不能为空"); $("#name").focus();return;}
    	if(name.length>16){layer.msg("仓库名称必须在16个字以内"); $("#name").focus();return;}
    	var houseCode=$.trim($('#houseCode').val());
    	if(houseCode==""){layer.msg("编码不能为空"); $("#houseCode").focus();return;}
    	if(houseCode.length>16){layer.msg("编码必须在16个字以内");$("#houseCode").focus();return;}
    	var province=$.trim($('#regionsPId1').val());
    	if(province==""){layer.msg("省不能为空"); $("#regionsPId1").focus();return;}
    	var city=$.trim($('#regionsPId2').val());
    	if(city==""){layer.msg("市不能为空"); $("#regionsPId2").focus();return;}
    	var county=$.trim($('#regionsPId3').val());
    	if(county==""){layer.msg("县不能为空"); $("#regionsPId3").focus();return;}
    	var houseAddress=$.trim($('#houseAddress').val());
    	if(houseAddress==""){layer.msg("地址不能为空"); $("#houseAddress").focus();return;}
    	if(houseAddress.length>255){layer.msg("地址必须在255个字以内");$("#houseAddress").focus();return;}

    	var houseMark=$.trim($('#houseMark').val());
    	if(houseMark.length>100){layer.msg("备注必须在100字以内");$("#houseMark").focus();return;}

    	var callNum=$.trim($('#callNum').val());
    	if(callNum!=""&&!checkTelPhone(callNum)){layer.msg("电话号码格式错误");$("#callNum").focus();return;}
    	var fax=$.trim($('#fax').val());
    	if(fax.length>20){layer.msg("传真必须在20字以内");$("#fax").focus();return;}


    	var branderName=$.trim($('#branderName').val()); //负责人
    	if(branderName==""){layer.msg("负责人不能为空"); $("#branderName").focus();return;}
    	if(branderName.length>20){layer.msg("负责人名字必须在20字以内");$("#branderName").focus();return;}


    	var branderTel=$.trim($('#branderTel').val());
    	if(branderTel==""){layer.msg("负责人手机号不能为空"); $("#branderTel").focus();return;}
    	if(!checkSubmitMobil(branderTel)){ $("#branderTel").focus();return;}

    	var tplId=$.trim($('#tplId').val());
    	if(tplId==""){layer.msg("请选择运费模板");return;}

    	var tplName=$.trim($('#tplName').val());


    	var userName=$.trim($('#userName').val());
    	if(userName==""){layer.msg("用户名不能为空"); $("#userName").focus();return;}
    	if(userName.length>255){layer.msg("用户名必须在16个字以内");$("#userName").focus();return;}

    	var password=$.trim($('#password').val());
    	var id=$.trim($('#id').val());
    	if(id==""){
    		if(password==""){layer.msg("密码不能为空"); $("#password").focus();return;}
        	if(password.length>20){layer.msg("密码必须在20个字以内");$("#password").focus();return;}
    	}

    	var status=$('input[name="status"]:checked ').val();
    	if(status==""){layer.msg("状态不能为空"); return;}

    	var warehouse_userName=$.trim($('#warehouse_userName').val());
    	if(id==""){ //新增
    		$.post("${root}/scms/warehouse/checkName.do", {"userName":userName}, function (data) {
        		$("#saveButton").attr("disabled", "disabled");
              	if(data.success){
              		url="${root}/scms/warehouse/addWareHouse.do";
            		$.post(url, $('#scmsWarehouse').serializeArray(), function (data) {
              			layer.msg(data.message,function(){
              				if(data.success){

                          		window.location=document.referrer;
                          	}else{
                          		$("#saveButton").removeAttr("disabled");
                          	}
              			});

                      	},"json");
              	}else{
              		layer.msg(data.message);
              		$("#saveButton").removeAttr("disabled");
              	}

    		},"json");
    	}else{//修改
    		$("#saveButton").attr("disabled", "disabled");
    		if(warehouse_userName==userName){
    			url="${root}/scms/warehouse/edit.do";
        		data={"name":name,"houseCode":houseCode,"province":province,"city":city,"county":county,"houseAddress":houseAddress
          				,"password":password,"status":status,"houseMark":houseMark,"callNum":callNum
          				,"fax":fax,"branderName":branderName,"branderTel":branderTel,"id":id,"tplName":tplName,"tplId":tplId
          				,"bankUserName":$('#bankUserName').val(),"bankName":$('#bankName').val(),"bankNum":$('#bankNum').val()};

        		$.post(url, data, function (data) {
          			layer.msg(data.message,function(){
          				if(data.success){

                      		window.location=document.referrer;
                      	}else{
                      		$("#saveButton").removeAttr("disabled");
                      	}
          			});

                  	},"json");
    		}else{
    			$.post("${root}/scms/warehouse/checkName.do", {"userName":userName}, function (data) {

                  	if(data.success){
                  		url="${root}/scms/warehouse/edit.do";
                  		data={"name":name,"houseCode":houseCode,"province":province,"city":city,"county":county,"houseAddress":houseAddress
                  				,"password":password,"status":status,"houseMark":houseMark,"callNum":callNum
                  				,"fax":fax,"branderName":branderName,"branderTel":branderTel,"id":id,"tplName":tplName,"tplId":tplId};
                		$.post(url, data, function (data) {
                  			layer.msg(data.message,function(){
                  				if(data.success){

                              		window.location=document.referrer;
                              	}else{
                              		$("#saveButton").removeAttr("disabled");
                              	}
                  			});

                          	},"json");
                  	}else{
                  		layer.msg(data.message);
                  		$("#saveButton").removeAttr("disabled");
                  	}

        		},"json");
    		}
    	}

    }

    var pro="${warehouse.province}";
    var city="${warehouse.city}";
    var co="${warehouse.county}";

    $('#regionsPId1').val(pro);

    $('#regionsPId2').val(city);
    $('#regionsPId3').val(co);

    $('input[name="status"][value='+'${warehouse.status}'+']').prop("checked","checked");

    var id=$("#id").val();
    if(id!=''&&id!=null&&id!=undefined){
    	$("#userName").attr({readonly:"true"});
    	$("#aLink").text("编辑");
    }


</script>
</body>
</html>
