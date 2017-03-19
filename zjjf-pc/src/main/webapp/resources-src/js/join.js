function checkFrm(){
    var $name = $('#name');
    var $mobile = $('#mobile');
    var $storeName = $('#storeName');
    var $storeAdress = $('#storeAdress');
    var $userMessage = $('#userMessage');
    var $checkCode = $('#checkCode');
    var name = $name.val().trim();
    var mobile = $mobile.val().trim();
    var storeName = $storeName.val().trim();
    var storeAdress = $storeAdress.val().trim();
    var userMessage = $('#userMessage').val().trim();
    var checkCode = $('#checkCode').val().trim();
    if(name.length>16||name.length==0){
        $name.siblings('.tips').text("姓名为空或填写错误！");
        $name.focus().keyup(function(){
            $(this).siblings('.tips').empty();
        });
        return false;
    }
    if(mobile.length>15||mobile.length==0){
        $mobile.siblings('.tips').text("电话号码为空或填写错误！");
        $mobile.focus().keyup(function(){
            $(this).siblings('.tips').empty();
        });
        return false;
    }
    if(storeName.length>100||storeName.length==0){
        $storeName.siblings('.tips').text("名称为空或填写错误！");
        $storeName.focus().keyup(function(){
            $(this).siblings('.tips').empty();
        });
        return false;
    }
    if(storeAdress.length>600||storeAdress.length==0){
        $storeAdress.siblings('.tips').text("地址为空或填写错误！");
        $storeAdress.focus().keyup(function(){
            $(this).siblings('.tips').empty();
        });
        return false;
    }
    if(userMessage.length>1600){
        $userMessage.siblings('.tips').text("留言字数太多！");
        $userMessage.focus().keyup(function(){
            $(this).siblings('.tips').empty();
        });
        return false;
    }
    if(checkCode.length==0){
        $checkCode.siblings('.tips').text("验证码为空！");
        $checkCode.focus().keyup(function(){
            $(this).siblings('.tips').empty();
        });
        return false;
    }
    return true;
}
function changeImg(){
    $("#codeimg").attr('src', '../checkcode.do?' + Math.random());
}
//ajax form
function ajaxSubmit(frm, fn) {
    var dataPara = getFormJson(frm);
    $.ajax({
        url: "../pc/join/submitInfo.do",
        type: frm.method,
        data: dataPara,
        success: fn
    });
}
function getFormJson(frm) {
    var o = {};
    var a = $(frm).serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
}
$(function() {
    $("#codeimg").attr('src', '../checkcode.do');
    $('#formjoin').on('submit', function() {
        var falg = checkFrm();
        if(falg){
            ajaxSubmit(this, function(data) {
                if(data.success == true) {
                    alert("您的信息已经提交成功，请等待转角街坊审核。");
                    $('#formjoin')[0].reset();
                } else if(data.message == "验证码错误"){
                    $('#checkCode').siblings('.tips').text("验证码错误，请点击图片刷新！");
                    $('#checkCode').focus().keyup(function(){
                        $(this).siblings('.tips').empty();
                    });
                    return false;
                } else {
                    alert("请求失败，请联系客服！");
                    return false;
                }
            });
        }
    });
});
