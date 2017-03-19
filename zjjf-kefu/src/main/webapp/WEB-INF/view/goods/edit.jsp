<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>商品库 - 新增/编辑商品</title>
    <%@ include file="../common/head.jsp"%>
    <script src="${root}/resources/vendor/jquery/ajaxfileupload.js"></script>
    <%@ include file="../common/autocomplete.jsp"%>
</head>
<body>
<body>
<div class="wrap-bd">
    <div class="mb-default">
        <a class="crumb" href="${root}/customer/itemBases/getAllItemBaseByPatam.do">基础商品库</a><a class="crumb crumb-active">编辑商品</a>
    </div>
    <div class="wrap-bd bg">
    <form id="addItemBaseParam">
        <p class="required">
        	<input class="input input-default" type="hidden" name="id" value="${itemBase.id }" id="id">
            <label class="label">商品条形码：</label>
            <input class="input input-default" type="text" name="mdseId" value="${itemBase.mdseId }" id="mdseId" onblur="chickUniqueness(this,1)" maxlength="32">
            <input type="hidden" value="${itemBase.mdseId }" id="mdseIdA">
            <input type="hidden" value="${itemBase.tgId }" name="tgId" id="tgId">
        </p>
        <p class="required">
            <label class="label">商品名称：</label>
            <input class="input input-default" type="text" name="name" value="${itemBase.name }" id="name" maxlength="25">
        </p>
         <p>
        	<label class="label">库存上限：</label>
            <input class="input input-default" type="text" name="upper" value="${itemBase.upper }" id="upper" maxlength="5">
        </p>
        <p>
        	<label class="label">库存下限：</label>
            <input class="input input-default" type="text" name="lower" value="${itemBase.lower }" id="lower" maxlength="5">
        </p>
        <p>
            <label class="label">分类：</label>
           <select class="select" onchange="selectErJiByYiJi()" id="yiJi" name="yiJiId">
                <option value="">请选择</option>
                <c:forEach var="itemCatelogYiJi" items="${itemCatelogYiJiList }">
                	<option  <c:if test="${itemCatelogYiJi.id==itemBase.yiJiId }">selected="selected"</c:if> value="${itemCatelogYiJi.id }">${itemCatelogYiJi.name }</option>
                </c:forEach>
            </select>
            <select class="select" id="erJi" name="erJiId">
                <option value="" selected="selected">请选择</option>
                <c:forEach var="itemCatelogErJi" items="${itemCatelogErJiList }">
                	<option <c:if test="${itemCatelogErJi.id==itemBase.erJiId }">selected="selected"</c:if> value="${itemCatelogErJi.id }">${itemCatelogErJi.name }</option>
                </c:forEach>
            </select>
        </p>
        <p>
            <label class="label">单位：</label>
            <select class="select" id="danwei">
                <!-- <option value="0">全部</option> -->
                <option value="瓶">瓶</option>
                <option value="包">包</option>
                <option value="盒">盒</option>
                <option value="罐">罐</option>
                <option value="支">支</option>
                <option value="桶">桶</option>
                <option value="箱">箱</option>
                <option value="件">件</option>
                <option value="其它">其它</option>
            </select>
             <input type="text" name="pkg" value="${itemBase.pkg}" class="input input-date hidden" id="pkg" >
            <script>
            	$(function() {
            		$('#danwei').on('change', function() {
            			var pkg = "${itemBase.pkg}"
            			if('其它' == $.trim($(this).val())) {
            				if(pkg=="瓶"||pkg=="包"||pkg=="盒"||pkg=="罐"||pkg=="包"||pkg=="支"||pkg=="箱"||pkg=="件"||pkg=="其它"){
            					$('#pkg').val('');
            				}else{
            					$('#pkg').val(pkg);
            				}
            				$('#pkg').show();
            			} else {
            				$('#pkg').val($.trim($(this).val()));
            				$('#pkg').hide();
            			}
            		});
            	}); 
            </script>
        </p>
       <%--  <p class="required">
            <label class="label">数量：</label>
            <input class="input input-default" type="text" name="pkgNum" value="${itemBase.pkgNum }" id="pkgNum" maxlength="3">
        </p> --%>
        <p class="required">
            <label class="label">规格：</label>
            <input class="input input-default" type="text" name="spec" value="${itemBase.spec }" id="spec" maxlength="32">
        </p>
        <p>
            <label class="label">口味：</label>
            <input class="input input-default" type="text" name="taste" value="${itemBase.taste }" maxlength="5">
        </p>
        <p>
            <label class="label">颜色：</label>
            <input class="input input-default" type="text" name="color" value="${itemBase.color }" maxlength="5">
        </p>
        <p class="required">
            <label class="label">品牌：</label>
            <input class="input input-default" type="text" id="brandName" name="brand" value=${itemBase.brand }>
            <c:if test="${itemBase.brandId ==null||itemBase.brandId ==''}">
            	<c:if test="${itemBase.name != null && itemBase.name != ''}">
            		<label><span style="color:red" id="span">此品牌名称没有和品牌关联，请重新录入！</span></label>
            	</c:if>
            </c:if>
            <input type="hidden" id ="brandId" name="brandId" value="${itemBase.brandId }"> 
           <%--  <select class="select" name="brandId" id="brandId">
               <option value="0">请选择</option>
                <c:forEach var="brand" items="${brandList }">
                	<option value="${brand.id }">${brand.name }</option>
                </c:forEach>
            </select> --%>
        </p>
        <script type="text/javascript">
        $('#brandName').on('keydown' , function(){
        	$("#brandId").val("");
        });
       $('#brandName').autocomplete({
            serviceUrl: '${root}/customer/brand/getBrandByName.do?str=brand',
            paramName: 'brandName',
            transformResult: function(response) {
 	            var res = JSON.parse(response)
 	            if(res.message != null) {
 		           	return {
 		            	suggestions: $.map(res.message, function(value, key) {
 		                 	return { value: value.name, data: value };
 		            	})
 		            };
 	            } else {
 	            	return {
 		            	suggestions: [{ value: "无数据"}]
 		            };
 	            }
            },
            onSelect: function(dd) {
            	if(dd.value=="无数据") {
               		$(this).val("");
                	return;
            	}
 				$("#brandId").val(dd.data.id);
 				$("#span").text("");
            }
       }); 
        </script>
        
        <div class="clearfix">
            <label class="fl label">物流规格：</label>
            <div class="fl" style="width: 800px">
                <table class="table-list table-border">
                    <thead class="table-thead">
                    <tr>
                        <th width="220">箱条形码</th>
                        <th width="80">数量</th>
                        <th width="100" >规格</th>
                        <th width="40">单位</th>
                        <th width="120">操作</th>
                    </tr>
                    </thead>
                    <tbody class="table-tbody" id="J_WLGG">
                    <c:forEach var="itemBaseA" items="${itemBaseList }">
	                    <tr>
	                        <td>
	                        	<input type="hidden" name="xid" value="${itemBaseA.id }">
	                        	<input class="input input-full" type="text" name="xmdseId" value="${itemBaseA.mdseId }" onblur="chickUniqueness(this,2)" id="xmdseId" maxlength="32">
	                        	<input type="hidden" id="xmdseIdA" value="${itemBaseA.mdseId }">
	                        </td>
	                        <td><input class="input input-full xpkgNum" type="text" name="xpkgNum" value="${itemBaseA.pkgNum }" id="xpkgNum" maxlength="5"></td>
	                        <td><input class="input input-full" type="text" name="xguige" value="${itemBase.spec } x ${itemBaseA.pkgNum }${itemBase.pkg}" readonly="readonly" style="border: 0 none;"></td>
	                        <td><input class="input input-full" type="text" name="xpkg" value="${itemBaseA.pkg }" maxlength="5"></td>
	                        <td>
	                            <span class="button button-operate J_addWLGG">新增</span>
	                             <c:if test="${itemBaseA.isDelete==false}">
	                            	<span class="button button-operate J_delWLGG">删除<input type="hidden" name="xIsDelete" value="true"></span>
	                            </c:if>
	                            <c:if test="${itemBaseA.isDelete==true}">
	                            	<span class="button button-operate J_delWLGG">恢复<input type="hidden" name="xIsDelete" value="false"></span>
	                            </c:if>
	                        </td>
	                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        
       <p>
         <span class="cont-n">商品照片(小)：</span>&nbsp;&nbsp;&nbsp;
         <input type="file" id="file-img" name="file-img" class="cont-input">
         <input id="up-img-to" type="button" class="send-img" value="上传图片"/>
         <input type="hidden" name="imgs" id="good_img" value="${itemBase.imgS}"/>
         <span class="error-prompt" id="send-img"></span>
         <span>
             <img id="good-img-show" src="${USER_FASTFDSPREURL}${itemBase.imgS}" width="55" height="55" alt="(小图片)">
             <img id="goodImgShowBig" style="position:absolute;bottom:-20px;left:58px;border:2px solid #ccc;display:none;" src="${USER_FASTFDSPREURL}${itemBase.imgS}" alt="">
         </span>
       </p>
        <p>
           <span class="cont-n">商品照片(大)：</span>&nbsp;&nbsp;&nbsp;
           <input type="file" id="file-imgb" name="file-imgb" class="cont-input">
           <input id="up-img-tob" type="button" class="send-img" value="上传图片"/>
           <input type="hidden" name="imgb" id="good_imgb" value="${itemBase.imgB}"/>
           <span class="error-prompt" id="send-imgb"></span>
           <span>
               <img id="good-img-showb" src="${USER_FASTFDSPREURL}${itemBase.imgB}" width="55" height="55" alt="(大图片)">
               <img id="goodImgShowBigb" style="position:absolute;bottom:-20px;left:58px;border:2px solid #ccc;display:none;" src="${USER_FASTFDSPREURL}${itemBase.imgB}" alt="">
           </span>
       </p>
       <!-- <div class="clearfix">
            <label class="fl label vat">商品详情页：</label>
            <div class="fl" style="width: 800px">
                <script id="ueditorContent" name="ueditorContent" type="text/plain"></script>
                <script src="../../resources/js/ueditor/ueditor.config.js"></script>
                <script src="../../resources/js/ueditor/ueditor.all.min.js"></script>
                <script>
                    var ue = UE.getEditor('ueditorContent');
                    ue.addListener('ready', function () {
                        ue.setContent('${ad.content}');
                    });
                </script>
            </div>
        </div> -->
         <p>
         	<span class="cont-n">核对状态：</span>&nbsp;&nbsp;
         	<c:if test="${itemBase.checkStatus==1}">
	         	<input type="radio" name="checkStatus" value="1" checked="checked">已核对&nbsp;&nbsp;&nbsp;
	        	<input type="radio" name="checkStatus" value="0">未核对&nbsp;&nbsp;&nbsp;(
	        	<span>核对人：${itemBase.checkUser}</span>&nbsp;&nbsp;&nbsp;
	        	<span>审对时间：<fmt:formatDate value="${itemBase.checkTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
         	</c:if>
         	<c:if test="${itemBase.checkStatus==0}">
         		<input type="radio" name="checkStatus" value="1">已核对&nbsp;&nbsp;&nbsp;
        		<input type="radio" name="checkStatus" value="0" checked="checked">未核对
         	</c:if>
        </p>
         <p >
         	<span class="cont-n">状态：</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         	<c:if test="${itemBase.status==1}">
	         	<input type="radio" name="status" value="1" checked="checked">正常&nbsp;&nbsp;&nbsp;
	        	<input type="radio" name="status" value="0">停用
         	</c:if>
         	<c:if test="${itemBase.status==0}">
         		<input type="radio" name="status" value="1">正常&nbsp;&nbsp;&nbsp;
        		<input type="radio" name="status" value="0" checked="checked">停用
         	</c:if>
        </p>
        <div class="mt-default">
            <label class="label"></label>
            <input type="button" class="button button-ok" value="保存" onclick="editItemBase()">
            <a href="${root}/customer/itemBases/getAllItemBaseByPatam.do" class="button button-cancel">取消</a>
        </div>
    </form>
    </div>
</div>
<!-- 处理图片的js -->
	<script type="text/javascript">
	//上传图片功能(小图)
		
	$(function(){
		goodImgShowBig("good-img-show","goodImgShowBig");
		goodImgShowBig("good-img-showb","goodImgShowBigb");
	});
	$("#up-img-to").on("click", function() {
		if ($("#file-img").val() == null || $("#file-img").val() == "") {
			alert("请先选择上传图片");
			return;
		}
		var f = checkFileType();
		if (f) {
			ajaxFileUpload2();
		}
	});
	//上传图片功能(大图)
	$("#up-img-tob").on("click", function() {
		if ($("#file-imgb").val() == null || $("#file-imgb").val() == "") {
			alert("请先选择上传图片");
			return;
		}
		var f = checkFileTypeb();
		if (f) {
			ajaxFileUpload1();
		}
	}); 
	
	function checkFileTypeb() {
		var myFile = document.getElementById("file-imgb");
		var filePath = myFile.value;
		var dotNdx = filePath.lastIndexOf('.');
		var exetendName = filePath.slice(dotNdx + 1).toLowerCase();
		if ((exetendName == "jpg" || exetendName == "jpeg" || exetendName == "gif" || exetendName == "png")) {
			return true;
		}

		$("#send-imgb").html("请选择正确的图片格式");
		return false;
	} 
	
	function checkFileType() {
		var myFile = document.getElementById("file-img");
		var filePath = myFile.value;
		var dotNdx = filePath.lastIndexOf('.');
		var exetendName = filePath.slice(dotNdx + 1).toLowerCase();
		if ((exetendName == "jpg" || exetendName == "jpeg" || exetendName == "gif" || exetendName == "png")) {
			return true;
		}
		$("#send-img").html("请选择正确的图片格式");
		return false;
	}
	
	function ajaxFileUpload1() {
		var fileServiceName = '${USER_FASTFDSPREURL }';
		$("#send-imgb").html("");
		$.ajaxFileUpload({
			url : "${root}/customer/itemBases/upload.do",
			secureuri : false,
			fileElementId : 'file-imgb',
			dataType : 'text',
			success : function(data, status) {
				if (data == "错误") {
					return;
				}
				alert("上传成功");
				var resultData = data.substring(1, data.length-1);
				$("#good_imgb").val(resultData);
				$("#good-img-showb").attr("src", fileServiceName + resultData);
				goodImgShowBig("good-img-showb","goodImgShowBigb");
			},
			error : function(data, status, e) {
				alert('上传出错');
				$("#send-imgb").html("上传失败");
			}
		});
	} 
	function ajaxFileUpload2() {
		var fileServiceName = '${USER_FASTFDSPREURL }';
		$("#send-img").html("");
		$.ajaxFileUpload({
			url : "${root}/customer/itemBases/upload.do",
			secureuri : false,
			fileElementId : 'file-img',
			dataType : 'text',
			success : function(data, status) {
				if (data == "错误") {
					return;
				}
				alert("上传成功");
				var resultData = data.substring(1, data.length-1);
				$("#good_img").val(resultData);
				$("#good-img-show").attr("src", fileServiceName + resultData);
				goodImgShowBig("good-img-show","goodImgShowBig");

			},
			error : function(data, status, e) {
				alert('上传出错');
				$("#send-img").html("上传失败");
			}
		});
	}
	
	//产品图片放大展示
	function goodImgShowBig(id1, id2){
	  var $goodimg = $('#' + id1 + '');
	  var $goodimgshow = $('#' + id2 + '');
	  $goodimg.parent('span').css("position","relative");
	  var $imgsrc = $goodimg.attr("src");
	  if($.trim($imgsrc)!=""){
	      $goodimg.hover(
	          function(){
	              $goodimgshow.attr("src",$imgsrc);
	              $goodimgshow.fadeIn("slow");
	          },
	          function(){
	              $goodimgshow.attr("src","");
	              $goodimgshow.fadeOut("fast");
	          }
	      );
	  }
	}
	</script>
	
<script >
	 $(function(){
		var html = '<tr>'
	           + '<td><input type="hidden" name="xid" value=""><input class="input input-full" type="text" name="xmdseId" onblur="chickUniqueness(this,2)" maxlength="32"></td>'
	           + '<td><input class="input input-full xpkgNum" type="text" name="xpkgNum" maxlength="5"></td>'
	           + '<td><input class="input input-full" type="text" name="xguige" readonly="readonly" style="border: 0 none;"></td>'
	           + '<td><input class="input input-full" type="text" name="xpkg" maxlength="5"></td>'
	           + '<td>'
	               + '<span class="button button-operate J_addWLGG">新增</span>'
	               + '<span class="button button-operate J_delWLGG">删除</span>'
	           + '</td></tr>'
	           
        var list = "${empty itemBaseList }"
   		if(list=='true'){
   			 $('#J_WLGG').append(html);
   		}       
		$('#J_WLGG').on('click', '.J_addWLGG', function() {
			
	   		   $('#J_WLGG').append(html);
	   	   });
		$('#J_WLGG').on('click', '.J_delWLGG', function() {
			   if($('#J_WLGG .J_delWLGG').length == 1) {
				   alert('亲,已经是最后一行了');
			   } else {
				   var id=$(this).parent('td').siblings('td:first').find('input[name="xid"]').val();
				   var isDelete = $(this).find("input[name=xIsDelete]").val();
				   if(id=="" || id==null){
					   $(this).parent('td').parent('tr').remove();
				   }else{
					   if(isDelete == 'true'){
						   isDelete=true;
					   }else if(isDelete == 'false'){
						   isDelete=false;
					   }
					   $.post("${root}/customer/itemBases/deleteLogisticsById.do",{id:id,isDelete:isDelete},function(date){
						   if(date.success){
							   location = location;
						   }
					   },'json');
				   }
			   }
		   }); 
			//填充物流规格中的规格
		   $('#J_WLGG').on('keyup', '.xpkgNum', function() {
			   var xpkgNum = $(this).val();
	  	  		if($("#spec").val()=='' || $(this).val() == ''){
	  	  			$(this).parent("td").next("td").find("input[name=xguige]").val('');
	  	  		}else{
	  	  			$(this).parent("td").next("td").find("input[name=xguige]").val($("#spec").val()+" x "+$(this).val()+$("#pkg").val());
	  	  		}
	  	  	})
	  	  	
  		$('#J_WLGG').on('blur', '.xpkgNum', function() {
  			var xpkgNum = $(this).val();
	  	  	var i = 0;
	  	  $('#J_WLGG').find('tr').each(function(){
	  			if($(this).find('input[name="xpkgNum"]').val() == xpkgNum){
	  				i ++ ; 
	  			}
	  			if(i == 2 ){
		  			alert("数量不能相同！");
		  			$(this).find('input[name="xpkgNum"]').focus();
		  			return false;
		  		}
	  		});
  	  	})  	
	  	$('#spec').on('keyup' , function(){
	  		$('#J_WLGG').find('tr').each(function(){
	  			if($.trim($(this).find('input[name="xpkgNum"]').val()) == '' || $.trim($('#spec').val()) == ''){
	  				$(this).find('input[name=xguige]').val('');
	  			}else{
	  				$(this).find('input[name=xguige]').val($('#spec').val()+" x "+$(this).find('input[name="xpkgNum"]').val()+$("#pkg").val());
	  			}
	  		});
  	  });
		  
		
		var pkg = "${itemBase.pkg}";
		if(pkg!=null){
			if(pkg=="瓶"||pkg=="包"||pkg=="盒"||pkg=="罐"||pkg=="包"||pkg=="支"||pkg=="箱"||pkg=="件"||pkg=="其它"){
				$("#danwei").val(pkg);
			}else{
				$("#danwei").val("其它");
				$("#pkg").val(pkg);
				$("#pkg").show();
			}
			
		}
	}) 

       function selectErJiByYiJi(){
    	   var pid = $("#yiJi").val();
    	   if(pid == 489){
      			$("#spec").removeAttr("maxlength");
      			$("#mdseId").removeAttr("maxlength");
      		}else{
      			$("#spec").attr("maxlength","32");
      			$("#mdseId").attr("maxlength","32");
      		}
    	  
	   		$.post("${root}/customer/itemCatelog/getAllItemCateByPid.do",{pid:pid},function(data){
	   			if(data.success){
	   				var html = '<option value="0">请选择</option>';
	   				$.each(data.message,function(i,item){
	   					html+='<option value="'+item.id+'">'+item.name+'</option>'
	   				})
	   				$("#erJi").html(html);
	   			}
	   		},'json') 
   	   }
  
       function chickUniqueness(element,num){
 	   		var mdseId = element.value;
    	   if(element.value!=$(element).next().val()){
    		   $.post("${root}/customer/itemBases/okUniqueness.do",{mdseId:mdseId},function(data){
    	     		  if(data.success){
    	     			  alert(data.message);
    	     			  element.value="";
    	     		  }
    	     	  },'json') 
    	   }
       }
       
       function editItemBase(){
    	   var upperOrLowerCheck = /^[0-9]{1,5}$/;
      	 	var zhingze = /^[0-9]{0,16}$/; 
      	   var mdseId = $("#mdseId").val();
      	   var name = $("#name").val();
      	 	var upper = $("#upper").val();
      	 	var lower = $("#lower").val();
      	   var yiJi = $("#yiJi").val();
      	   var erJi = $("#erJi").val();
      	   var pkg = $("#pkg").val();
      	   var spec = $("#spec").val();
      	   var brandId = $("#brandId").val();
      	   /* var pkgNm = $("#pkgNum").val(); */
      	   if(mdseId==""){
      		   alert("商品条形码不能为空！");
      		 $("#mdseId").focus()
      		   return;
      	  /*  }else if(!zhingze.test(mdseId)){
      		   alert("商品条形码输入有误！");
      		 $("#mdseId").focus()
      		   return; */
      	   }else if(name==""){
      		   alert("商品名称不能为空！");
      		 $("#name").focus()
      		   return;
      	   }else if(!upperOrLowerCheck.test(upper) && upper != ''){
      		 alert("上限为整数！");
      		 $("#upper").focus()
      			return;
      	   }else if(!upperOrLowerCheck.test(lower) && lower != ''){
      		 alert("下限为整数！");
      		 $("#lower").focus()
      			return;
      	   }else if(yiJi==null || yiJi=="" || yiJi==0){
      		   alert("请选择分类！");
      		   return;
      	   }else if(erJi==null || erJi=="" || erJi==0){
      		   alert("请选择分类！");
      		   return;
      	   }else if(pkg==null || pkg==""){
      		   alert("请选择单位！");
      		   return;
      	  /*  }else if(pkgNm==null || pkgNm==""){
      		   alert("请输入数量！");
      		   return; */
      	   }else if(spec==""){
      		   alert("请输入规格！");
      		 $("#spec").focus()
      		   return;
      	   }else if(brandId==null || brandId==""||brandId==0){
      		   alert("请输入品牌！");
      		   return;
      	   }
    	 
    	var isOk = true;
     	$('#J_WLGG').find('tr').each(function(){
     		if($(this).find('input[name="xmdseId"]').val() != ""){
     			if(!zhingze.test($(this).find('input[name="xmdseId"]').val())){
     				alert("箱条形码输入有误！");
     				$(this).find('input[name="xmdseId"]').focus();
     				isOk = false;
    				return false;
     			}
     		}else if($(this).find('input[name="xmdseId"]').val() == $("#mdseId").val()){
 				alert("箱的条形码不能与商品的条形码相同！");
 				$(this).find('input[name="xmdseId"]').focus();
 				isOk = false;
				return false;
 			}else if(!zhingze.test($(this).find('input[name="xpkgNum"]').val())){
 				alert("数量必须为数字！");
 				$(this).find('input[name="xpkgNum"]').focus();
 				isOk = false;
				return false;
			}
		}); 
   	    if(isOk){ 
    	   $.ajax({
    			type : "POST",
    			url : "${root}/customer/itemBases/updateItemBaseOneById.do",
    			dataType : "json",
    			data:$('#addItemBaseParam').serialize(),
    			success : function(data) {
    				if (data.success) {
	   					alert(data.message);
	   					location.href="${root}/customer/itemBases/getAllItemBaseByPatam.do?pageIndex=${pageIndex}";
	   				}else{
	   					alert(data.message);
	   				}
    			},
    			error : function() {
    				alert("请求失败！");
	   			}
    		});  
   	    }
      }  
   </script>
</body>
</html>