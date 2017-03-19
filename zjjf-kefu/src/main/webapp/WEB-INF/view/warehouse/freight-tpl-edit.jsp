<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>仓库管理 - 运费模板添加/编辑</title>
	<%@ include file="../common/head.jsp"%>
</head>
<body>
    <div class="wrap-bd">
    <div class="mb-default">
        <a class="crumb" href="${root}/scms/templet/listpage.do">运费模板管理</a><a class="crumb crumb-active"><c:if test="${tpl==null}">增加</c:if><c:if test="${tpl!=null}">编辑</c:if></a>
    </div>
    <input type="hidden" value="${tpl.id}" id="id"/>
    <form action="#" id="formAction">
        <fieldset class="wrap-bd bg">
            <p class="required">
                <label class="label">模板名称：</label>
                <input type="text" class="input input-default" id="name" name="name" value="${tpl.name}">
            </p>
            <p class="required">
                <label class="label">模板说明：</label>
                <textarea class="textarea textarea-default" cols="23" rows="2" id="tplMark" name="tplMark">${tpl.tplMark}</textarea>
            </p>
            <div class="clearfix">
                <label class="fl label">计费方式：</label>
                <div class="fl" id="J_switch">
                    <div class="mb-small">
                        <input type="radio" name="paidMethods" checked="checked" class="J_radio" value="0"> 按订单金额比例
                    </div>
                    <div class="mb-small">
                        <input type="radio" name="paidMethods" class="J_radio" value="1"> 按订单数量
                    </div>
                    <div class="mb-small">
                        <input type="radio" name="paidMethods" class="J_radio" value="2"> 商品价格中抽取运费
                    </div>
                    <div class="ml-default mb-small J_radioSection">
                    		<c:if test="${list==null}">
                    			<div class="J_section">
	                       		单笔订单金额≤ <input type="text" value="${item.orderPrice}" name="orderPrice" style="width: 80px; border:0 none;border-bottom:1px solid #000">元，百分比 <input name="freight" value="${item.freight}" type="text" style="width: 80px; border:0 none;border-bottom:1px solid #000">
                        	<span class="button button-operate ml-default J_delSection">删除</span> </div>
                    		</c:if>
                        	<c:forEach items="${list}" varStatus="i" var="item" >
                        	<div class="J_section">
	                       		单笔订单金额≤ <input type="text" value="${item.orderPrice}" name="orderPrice" style="width: 80px; border:0 none;border-bottom:1px solid #000">元，百分比 <input name="freight" value="${item.freight}" type="text" style="width: 80px; border:0 none;border-bottom:1px solid #000">
                        	<span class="button button-operate ml-default J_delSection">删除</span> </div>
							</c:forEach>
                        <span class="button button-operate J_addSection">+添加区间</span>
                    </div>
                    <div class="ml-default mb-small J_radioSection hidden">
                    <c:if test="${list==null}">
                    	<div class="J_section">
                        	单笔订单数量≤ <input value="${item.orderNum}" name="orderNum" type="text" style="width: 80px; border:0 none;border-bottom:1px solid #000">箱，运费金额为 <input value="${item.freightPrice}" name="freightPrice" type="text" style="width: 80px; border:0 none;border-bottom:1px solid #000"> 元
                            <span class="button button-operate ml-default J_delSection">删除</span>
                        </div>
                    </c:if>
                    	<c:forEach items="${list}" varStatus="i" var="item" >
                        	<div class="J_section">
                        	单笔订单数量≤ <input value="${item.orderNum}" name="orderNum" type="text" style="width: 80px; border:0 none;border-bottom:1px solid #000">箱，运费金额为 <input value="${item.freightPrice}" name="freightPrice" type="text" style="width: 80px; border:0 none;border-bottom:1px solid #000"> 元
                            <span class="button button-operate ml-default J_delSection">删除</span>
                        </div>
							</c:forEach>
                        
                        <span class="button button-operate J_addSection">+添加区间</span>
                    </div>
                    <div class="ml-default mb-small J_radioSection hidden">
                    	<div class="J_section">
                    		每箱运费金额为<input value="${tplMap.freightPrice}" name="oneFreightPrice" id="freightPrice" type="text" style="width: 80px; border:0 none;border-bottom:1px solid #000">元
                        </div>
                    </div>
                </div>
            </div>
            <p>
                <input type="button" class="button button-ok" value="保存" onclick="save()" id="saveButton">
                <a  onclick="javascript:history.go(-1)" class="button button-cancel">取消</a>
            </p>
        </fieldset>
        </form>
    </div>
    <script>
	    $(function() {
	        $('#J_switch').on('click', '.J_radio', function() {
	            var ii = $('.J_radio').index(this);
	            $('.J_radioSection').eq(ii).show().siblings('.J_radioSection').hide();
	        }).on('click', '.J_delSection', function() {
	            if($(this).parent('.J_section').parent('.J_radioSection').find('.J_delSection').length == 1) {
	                layer.msg('最后一行，不能删除！');
	            } else {
	                $(this).parent('.J_section').remove();
	            }
	        }).on('click', '.J_addSection', function() {
	            $(this).prev('.J_section').after($(this).prev('.J_section').clone());
	        });
	    });
	    
	    
	  	//正整数
	    function isPInt(str) {
	        var g = /^[1-9]*[1-9][0-9]*$/;
	        return g.test(str);
	    }
	  	
	  	//正数
	    function validate(num)
	    {
	      var reg = /^\d+(?=\.{0,1}\d+$|$)/
	      if(reg.test(num)) return true;
	      return false ;  
	    }
	  	//金额
	    function isMoney(num)
	    {
	    	var exp = /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/;
	      	if(exp.test(num)) return true;
	      	return false ;  
	    }
	    
	  	
	    function save(){
	    	var name=$.trim($('#name').val());
	    	if(name==""){layer.msg("模板名称不能为空"); $("#name").focus();return;}
	    	if(name.length>16){layer.msg("模板名称必须在16个字以内"); $("#name").focus();return;}
	    	
	    	var tplMark=$.trim($('#tplMark').val());
	    	if(tplMark==""){layer.msg("模板说明不能为空"); $("#tplMark").focus();return;}
	    	if(tplMark.length>255){layer.msg("模板说明必须在255个字以内"); $("#tplMark").focus();return;}
	    	
	    	var paidMethods=$('input[name="paidMethods"]:checked ').val();
	    	if(paidMethods==""){layer.msg("请选择计费方式"); return;}
	    	var s=false;	
	   		if(paidMethods=="0"){
	   			$("input[name='orderPrice']").each(function(index,data){ 
	   				//订单金额
	   				
	   				if($(data).val()==''||$(data) == null ||$(data)== undefined ){
	   					layer.msg("订单金额不能为空");
	   					s=true;
	   					return false;
	   				}
	   				if(!validate($(data).val())){
	   					layer.msg("订单金额必须为正数");
	   					s=true;
	   					return false;
	   				}
	   				if(s) return false;
	   				if($(data).val()>999999){
	   					layer.msg("订单金额不能大于999999");
	   					s=true;
	   					return false;
	   				}
	   				if($(data).val().length>9){
	   					layer.msg("订单金额长度必须在9位以内");
	   					s=true;
	   					return false;
	   				}
	   			})
	   			$("input[name='freight']").each(function(index,data){ 
	   				//百分比
	   				if($(data).val()==''||$(data) == null ||$(data)== undefined ){
	   					layer.msg("百分比不能为空");
	   					s=true;
	   					return false;
	   				}
	   				if(!validate($(data).val())){
	   					layer.msg("百分比必须为正数");
	   					s=true;
	   					return false;
	   				}
	   				if(s) return false;
	   				if($(data).val()>100){
	   					layer.msg("百分比不能大于100");
	   					s=true;
	   					return false;
	   				}
	   				if($(data).val().length>5){
	   					layer.msg("百分比长度必须在5位以内");
	   					s=true;
	   					return false;
	   				}
	   			})
	   		}else if(paidMethods=="1"){
	   			$("input[name='orderNum']").each(function(index,data){ 
	   				//箱数
	   				if($(data).val()==''||$(data) == null ||$(data)== undefined ){
	   					layer.msg("箱数不能为空");
	   					s=true;
	   					return false;
	   				}
					if(!validate($(data).val())){
	   					layer.msg("箱数必须为正整数");
	   					s=true;
	   					return false;
	   				}
					if(s) return false;
					if($(data).val()>32000){
	   					layer.msg("箱数不能大于32000");
	   					s=true;
	   					return false;
	   				}
	   				if($(data).val().length>5){
	   					layer.msg("箱数长度必须在5位以内");
	   					s=true;
	   					return false;
	   				}
	   			})
	   			$("input[name='freightPrice']").each(function(index,data){
   					//运费金额
	   				if($(data).val()==''||$(data) == null ||$(data)== undefined ){
	   					layer.msg("运费金额不能为空");
	   					s=true;
	   					return false;
	   				}
	   				if(!validate($(data).val())){
	   					layer.msg("运费金额必须为正数");
	   					s=true;
	   					return false;
	   				}
	   				if(s) return false;
	   				if($(data).val()>999999){
	   					layer.msg("运费金额不能大于999999");
	   					s=true;
	   					return false;
	   				}
	   				if($(data).val().length>9){
	   					layer.msg("运费金额长度必须在9位以内");
	   					s=true;
	   					return false;
	   				}
	   			})
	   		}else if(paidMethods=="2"){
  				var freightPrice = $.trim($('#freightPrice').val());
   				if(freightPrice ==''){
   					layer.msg("运费金额不能为空" , $('#freightPrice'));
   					s=true;
   					return false;
   				}else if(freightPrice.length>999999){
   					layer.msg("运费金额不能大于999999");
   					s=true;
   					return false;
   				}else if(freightPrice>7){
   					layer.msg("运费金额长度必须在9位以内");
   					s=true;
   					return false;
   				}else if(!isMoney(freightPrice)){
   					layer.msg("运费金额必须为正数" ,$('#freightPrice'));
   					s=true;
   					return false;
   				}
	   		}else{
	   			layer.msg("你的选择有误");
	   			s=true;
				return false;
	   		}
	   		if(s) return ;
	   		$("#saveButton").attr("disabled", "disabled");
	   		var id=$.trim($('#id').val());
	   		if(id==""){
	   			$.post("${root}/scms/templet/addObject.do", $("#formAction").serialize(), function (data) { 
		   			layer.msg(data.message,function(){
		   				$("#saveButton").removeAttr("disabled");
			   			
		              	if(data.success){
		              		
		              		window.location=document.referrer;
		              	}
		   			});
		   			
		   			
		   		},"json")
	   		}else{
	   			$.post("${root}/scms/templet/edit.do?id="+id, $("#formAction").serialize(), function (data) { 
		   			layer.msg(data.message,function(){
		   				$("#saveButton").removeAttr("disabled");
		              	if(data.success){
		              		window.location=document.referrer;
		              	}
		   			});
		   		},"json")
	   		}
	    }
	    
	    $('input[name="paidMethods"][value='+'${tpl.paidMethods}'+']').prop("checked","checked");	    
	    $('.J_radioSection').eq('${tpl.paidMethods}').show().siblings('.J_radioSection').hide();
    </script>
</body>
</html>