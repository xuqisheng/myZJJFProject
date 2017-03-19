<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>转角店宝</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width = 720, user-scalable = no">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <link rel="icon" href="${root}/resources/images/favicon.png" type="image/x-icon">
    <link rel="stylesheet" href="${root}/resources/css/normalize.css">
    <link rel="stylesheet" href="${root}/resources/css/invite-reg.css?v2">
    <script src="${root}/resources/vendor/jquery/jquery-2.2.3.min.js"></script>
    <link rel="stylesheet" href="${root}/resources/css/invite-reg-cascader.css?v2">
    <script src="${root}/resources/js/invite-reg-cascader.js?v2"></script>
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
    <div class="title step-title">
        填写完整信息 便于我们及时为您服务
    </div>
    <div class="step-content" id="J_info">
        <form action="" id="formId">
            <div class="form-box first">
                <input type="text" id="contactName" placeholder="店主姓名" data-placeholder="店主姓名">
            </div>
            <div class="form-box">
                <input type="text" id="storeName" placeholder="店铺名称" data-placeholder="店铺名称">
            </div>
            <div class="form-box" style="display: none" id="J_area">
                <input type="text" id="J_selectArea" placeholder="所在区域" data-placeholder="所在区域" readonly="readonly">
                <span class="area-arr"></span>
            </div>
            <div class="form-box">
                <input type="text" id="storeAddres" placeholder="店铺地址" data-placeholder="店铺地址">
            </div>
            <div class="form-box last">
                <input type="text" id="licenseNum" placeholder="营业执照编号" data-placeholder="营业执照编号">
            </div>
            <input type="button" value="立即提交拿券" class="btn-submit" id="J_submit">
            <input type="hidden" id="J_value" name="areaStr" value="">
        </form>
    </div>
    <span class="tel">
        客服热线：400-664-3833
    </span>
    <div id="aaa"></div>
    <script>
    function getQueryString(name) {
    	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    	var r = window.location.search.substr(1).match(reg);
    	if (r != null) return unescape(r[2]); return null;
    	}

        function formCheck() {
            var $contactName = $('#contactName');
            var $storeName = $('#storeName');
            var $storeAddres = $('#storeAddres');
            var $licenseNum = $('#licenseNum');
            if($.trim($contactName.val()) == '') {
                return false;
            }
            if($.trim($storeName.val()) == '') {
                return false;
            }
            if($.trim($storeAddres.val()) == '') {
                return false;
            }
            if($.trim($licenseNum.val()) == '') {
                return false;
            }
            return true;
        }
        $(function() {

        	var fromWho=getQueryString("fromWho");
        	 if(fromWho!=null&&fromWho== 2){
				 $('#J_area').removeAttr('style')
			 }

            // 所在区域
            $('#J_selectArea').on('focus', function() {
                $('#aaa').cascader({
                    name: 'a1',
                    url: '${root}/region/getAllEnableRegionList.do',
                    complete: function(txt, v) {
                        $('#J_selectArea').val(txt);
                        $('#J_value').val(v);
                    }
                });
            });

            var $contactName = $('#contactName');
            var $storeName = $('#storeName');
            var $storeAddres = $('#storeAddres');
            var $licenseNum = $('#licenseNum');
            $contactName.on('blur', function() {
                if($.trim($contactName.val()) == '') {
                    $contactName.parent('.form-box').addClass('warn');
                    $contactName.attr('placeholder', $contactName.attr('data-placeholder') + '不能为空！');
                }
            }).on('focus', function() {
                if($.trim($contactName.val()) == '') {
                    $contactName.parent('.form-box').removeClass('warn');
                    $contactName.attr('placeholder', $contactName.attr('data-placeholder'));
                }
            });
            $storeName.on('blur', function() {
                if($.trim($storeName.val()) == '') {
                    $storeName.parent('.form-box').addClass('warn');
                    $storeName.attr('placeholder', $storeName.attr('data-placeholder') + '不能为空！');
                }
            }).on('focus', function() {
                if($.trim($storeName.val()) == '') {
                    $storeName.parent('.form-box').removeClass('warn');
                    $storeName.attr('placeholder', $storeName.attr('data-placeholder'));
                }
            });
            $storeAddres.on('blur', function() {
                if($.trim($storeAddres.val()) == '') {
                    $storeAddres.parent('.form-box').addClass('warn');
                    $storeAddres.attr('placeholder', $storeAddres.attr('data-placeholder') + '不能为空！');
                }
            }).on('focus', function() {
                if($.trim($storeAddres.val()) == '') {
                    $storeAddres.parent('.form-box').removeClass('warn');
                    $storeAddres.attr('placeholder', $storeAddres.attr('data-placeholder'));
                }
            });
            $licenseNum.on('blur', function() {
                if($.trim($licenseNum.val()) == '') {
                    $licenseNum.parent('.form-box').addClass('warn');
                    $licenseNum.attr('placeholder', $licenseNum.attr('data-placeholder') + '不能为空！');
                }
            }).on('focus', function() {
                if($.trim($licenseNum.val()) == '') {
                    $licenseNum.parent('.form-box').removeClass('warn');
                    $licenseNum.attr('placeholder', $licenseNum.attr('data-placeholder'));
                }
            });

            $('#J_submit').on('click', function() {
                if(formCheck()) {
                	 //suId="+supplierId+"&code="+code+"&password="+password
                	 var suId=getQueryString("suId");
                	 var userid=getQueryString("userid");
                	 var fromWho=getQueryString("fromWho");
                	 var password=getQueryString("password");
					 var  contactName=$("#contactName").val();
					 var  storeName=$("#storeName").val();
					 var  storeAddres=$("#storeAddres").val();
					 var  licenseNum=$("#licenseNum").val();
					 var areaStr = $('#J_value').val();
					 var host = getQueryString("server");
					 if(fromWho!=null&&fromWho==2){
						 if($.trim($('#J_value').val())==''){
							 alert('区域信息不完善!');
							 return;
						 }
					 }
					 $.ajax({
		      		        url:'http://'+host+'/CornerV2/Mobile/OpenStore/saveOpenStoreInfo.do',
		      		        type:'GET',                                //jsonp 类型下只能使用GET,不能用POST,这里不写默认为GET
		      		        dataType:'jsonp',                          //指定为jsonp类型
		      		        data:{"suId":suId,"fromWho":fromWho,"registStoreUserName":contactName,"registStoreName":storeName,
		      		        	"liscenceNum":licenseNum,"storeAddress":storeAddres,"userid":userid,"areaStr":areaStr},                //数据参数
		      		        jsonp:'callback',                          //服务器端获取回调函数名的key，对应后台有$_GET['callback']='getName';callback是默认值
		      		        jsonpCallback:'getName',                   //回调函数名

		      		        success:function(result){                  //成功执行处理，对应后台返回的getName(data)方法。
		      		        	if(result.success){
		      		        		window.location="${root}/view/invite/reg-success.jsp?server="+host;
		      		        	}else{
		      		        		layer.msg(result.message);
		      		        	}

		      		        }
		      		});
                } else {
                    alert('填写信息字段有空，请填写完整!');
                }
            });
        });
    </script>
</body>
</html>
