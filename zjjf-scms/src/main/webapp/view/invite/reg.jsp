<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>转角店宝</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=720, user-scalable = no">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <link rel="icon" href="${root}/resources/images/favicon.png" type="image/x-icon">
    <link rel="stylesheet" href="${root}/resources/css/normalize.css">
    <link rel="stylesheet" href="${root}/resources/css/invite-reg.css">
     <script src="${root}/resources/vendor/jquery/jquery-2.2.3.min.js"></script>
<script src="${root}/resources/vendor/layer/layer.js"></script>

</head>
<body>
    <div class="top">
        欢<span class="sep">·</span>
        迎<span class="sep">·</span>
        光<span class="sep">·</span>
        临<span class="sep">·</span>
        转<span class="sep">·</span>
        角<span class="sep">·</span>
        街<span class="sep">·</span>
        坊
    </div>
    <div class="logo"></div>
    <div class="index-banner"></div>
    <div class="title">
        手机下单价格更低 <span class="main-color">注册送20元代金券</span>
    </div>
    <div class="index-content" id="J_info">
        <form action="reg-step.html">
            <div class="form-box first clearfix">
                <i class="icon icon-tel"></i>
                <input type="tel" placeholder="输入您的手机号码" class="J_ipt"  value="" id="phoneNumber">
            </div>
            <div class="form-box clearfix">
                <i class="icon icon-code"></i>
                <input type="text" placeholder="输入验证码" class="input-code J_ipt" id='code' value="">
                <input type="button" value="获取验证码" class="button-code" onclick="getCode()" id="sendMsgCode" >
                <!--<input type="button" value="109秒重新获取" disabled="disabled" class="button-code">-->
            </div>
            <div class="form-box last clearfix">
                <i class="icon icon-pwd"></i>
                <input type="password" placeholder="设置您的密码" class="J_ipt" id='password' value="">
            </div>
            <input type="button" onclick="nextHtml()" value="下一步" class="btn-submit">
        </form>
    </div>
    <span class="tel">
        客服热线：400-664-3833
    </span>
    <script>
    function getQueryString(name) {
    	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    	var r = window.location.search.substr(1).match(reg);
    	if (r != null) return unescape(r[2]); return null;
    	}

        $(function() {
            $('#J_info').on('focus', '.J_ipt', function() {
                $(this).parent('.form-box').find('.icon').addClass('active');
            });
            $('#J_info').on('blur', '.J_ipt', function() {
                $(this).parent('.form-box').find('.icon').removeClass('active');
            });

        });

        function getCode(){
        	var phoneNumber=$.trim($("#phoneNumber").val());
        	if(phoneNumber==""){layer.msg("手机号不能为空"); $("#phoneNumber").focus();return;}
        	if(!checkSubmitMobil(phoneNumber)){
        		layer.msg("手机号码格式错误！");
        		return ;
        	}
        	var $sendMsgCode = $('#sendMsgCode');
        	 var host = getQueryString("server");
        	$sendMsgCode.prop('disabled', 'disabled');
    		$sendMsgCode.css('background', '#ccc');
        	 $.ajax({
      		        url:'http://'+host+'/CornerV2/Mobile/Captcha.do',
      		        type:'GET',                                //jsonp 类型下只能使用GET,不能用POST,这里不写默认为GET
      		        dataType:'jsonp',                          //指定为jsonp类型
      		        data:{"mobile":phoneNumber,"smsTypes":0,"callback":'getName'},                //数据参数
      		        jsonp:'callback',                          //服务器端获取回调函数名的key，对应后台有$_GET['callback']='getName';callback是默认值
      		        jsonpCallback:'getName',                   //回调函数名
      		        success:function(result){                  //成功执行处理，对应后台返回的getName(data)方法。
      		            if(result.success){
      		            	var i = 60;
      	        			function cd() {
      	        				i--;
      	        				if(i == 0) {
      	        					$sendMsgCode.prop('disabled', '').css('background', '');
      	        					$sendMsgCode.val('重新获取');
      	        					clearInterval(c);
      	        				} else {
      	        					$sendMsgCode.val(i + '秒');
      	        				}
      	        			}
      	        			var c = setInterval(cd, 1000);
      		            }else{
      		            	alert(result.message);
      		            }
      		        },
      		        error:function(msg){
      		        	$sendMsgCode.prop('disabled', '').css('background', '');
            			$sendMsgCode.val('获取短信校验码');           //执行错误
      		        }
      		});


        }
      //验证手机号码
        function checkSubmitMobil(mobile) {

         if (!mobile.match(/^(0|86|17951)?(1[3-9])[0-9]{9}$/)) {
         	layer.msg("手机号码格式不正确！");
         		return false;
        	 	}
        	return true;
        }


      function nextHtml(){
    	  var phoneNumber=$.trim($("#phoneNumber").val());
      	if(phoneNumber==""){layer.msg("手机号不能为空"); $("#phoneNumber").focus();return;}
      	if(!checkSubmitMobil(phoneNumber)){
      		layer.msg("手机号码格式错误！");
      		return ;
      	}
      	var suId=getQueryString("suId");
      	 var host = getQueryString("server");
      	var code = $.trim($("#code").val());
      	if(code==""){layer.msg("验证码不能为空"); $("#code").focus();return;}
      	var password=$.trim($("#password").val());
      	if(password==""){layer.msg("密码不能为空"); $("#password").focus();return;}
      	if(password.length<6){layer.msg("密码长度不能小于6位"); $("#password").focus();return;}
      	var fromWho=getQueryString("fromWho");
      	// http://zjjf.f3322.net:8077/CornerV2//Mobile/OpenStore/NewOpenStoreLogin.do
      	//window.location="${root}/view/invite/reg-step.jsp?suId="+supplierId+"&code="+code+"&password="+password+"&phoneNumber"+phoneNumber;
      	 $.ajax({
		        url:'http://'+host+'/CornerV2/Mobile/OpenStore/NewOpenStoreLogin.do',
		        type:'GET',                                //jsonp 类型下只能使用GET,不能用POST,这里不写默认为GET
		        dataType:'jsonp',                          //指定为jsonp类型
		        data:{"mobile":phoneNumber,"captcha":code,"callback":'getName',"pwd":password},                //数据参数
		        jsonp:'callback',                          //服务器端获取回调函数名的key，对应后台有$_GET['callback']='getName';callback是默认值
		        jsonpCallback:'getName',                   //回调函数名
		        success:function(result){                  //成功执行处理，对应后台返回的getName(data)方法。
		            if(result.success){
		            	window.location="${root}/view/invite/reg-step.jsp?suId="+suId+"&phoneNumber="+phoneNumber+"&userid="+result.message.id+"&fromWho="+fromWho+"&server="+host;
		            }else{
		            	alert(result.message);
		            }
		        }
		});
      }
    </script>
</body>
</html>
