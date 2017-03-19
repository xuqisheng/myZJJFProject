<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>商品库管理 - 新增入库单</title>
    <%@ include file="../../common/head.jsp"%>
    <script src="${root}/resources/js/comm.js"></script>
    <script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<div class="wrap-bd">
    <div class="mb-default">
        <span>当前位置：</span>
        <span class="crumb">商品库管理</span>
        <a href="${root}/scms/ERPOrderInfo.do?status=0" class="crumb">入库管理</a>
        <span class="crumb crumb-active">新增入库单</span>
    </div>
    <form id="form">
        <div class="op-section clearfix">
            <select class="select" name="whId">
                <c:forEach items="${warehouses}" var="ele">
                    <option value="${ele.id}">${ele.name}</option>
                </c:forEach>
            </select>
            <select class="select hidden" name="type">
                <option value="1">采购入库</option>
            </select>
            <span class="fr button" id="J_btnAddGoods">添加商品</span>
        </div>
        <div class="table-contain">
            <pre>
                <table class="table-list">
                    <thead>
                        <tr>
                            <th>商品供应码</th>
                            <th>箱码</th>
                            <th>商品条形码</th>
                            <th>商品名称</th>
                            <th>规格</th>
                            <th>数量</th>
                            <th>采购价</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody id="form_tbody">
                        <tr class="addClass">
                            <td colspan="8">
                                <span class="button-operate itemAdd">添加</span>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </pre>
        </div>
        <div class="wrap-bd bg mt-small">
            <div>
                <label for="gaveTime" class="label">交货时间：</label>
                <input type="text" name="gaveTime" id="gaveTime" onfocus="WdatePicker({startDate:'%y',dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'});" class="input">
            </div>
            <p>
                <label for="textareabb" class="label">备注：</label>
                <textarea name="remark" id="textareabb" class="textarea"  maxlength="200"></textarea>
            </p>
        </div>
    </form>
    <div class="ta-c mt-default">
        <input type="button" value="确定" class="button-save">
        <input type="button" value="取消" class="button-cancel" onclick="window.location.href=document.referrer;">
    </div>
</div>
<div class="dialog hidden" id="J_dialogAddGoods">
    <div class="dialog-head">
        添加商品
        <span class="dialog-close"></span>
    </div>
    <div class="dialog-body">
        <div class="mb-small">
            <input type="text" name="managerCode" placeholder="供应商编号/名称" class="input-search-text">
            <input type="text" name="itemCode" placeholder="商品名称/条形码" class="input-search-text">
            <input type="button" value="搜索" class="input-search-button" id="addItemList">
        </div>
        <div class="table-contain" style="max-width: 790px">
            <pre>
            <table class="table-list">
                <thead>
                <tr>
                    <th><input type="checkbox" class="checkbox" name="" id="J_selectAll" value="" /></th>
                    <th>商品供应码</th>
                    <th>供应商名称</th>
                    <th>箱码</th>
                    <th>商品条形码</th>
                    <th>商品名称</th>
                    <th>规格</th>
                    <th>单位</th>
                </tr>
                </thead>
                <tbody id="addGoods_tbody"/>
            </table>
            </pre>
        </div>
        <div class="clearfix"><%@ include file="../../common/pagination.jsp"%></div>
    </div>
    <div class="dialog-foot">
        <input type="button" value="确定" class="dialog-button dialog-ok">
        <input type="button" value="取消" class="dialog-button dialog-cancel">
    </div>
</div>
<div class="cover-all"></div>
<script>
	selectAll('#J_selectAll', '.J_chk');
    $(function() {
        var num = 0;
        $(function () {
            $('#form_tbody .itemAdd').trigger('click');
        });
        $('html, body').on('keydown', 'input[data-shortcut="enter"]:last', function(ev) {
            if(ev.keyCode == "13"||ev.keyCode == "9") {
                $('#form_tbody .itemAdd').trigger('click');
            }
        });
        $('#form_tbody').on('change' , '.itemCode' , function () {
            $(this).siblings('input[name="itemIds"]').val('');
            $(this).siblings('input[name="itemBaseIds"]').val('');
            $(this).siblings('input[name="managerIds"]').val('');
            $(this).parent().siblings('.mdseIdX').html('');
            $(this).parent().siblings('.mdseId').html('');
            $(this).parent().siblings('.name').html('');
            $(this).parent().siblings('.spec').html('');
        }).on('blur' , '.itemCode' , function () {
            var local = $(this);
            if($(this).val()  != null && $(this).val() != ''){
                $.post('${root}/scms/ERPOrderInfo/getMangerItem/'+$(this).val()+'.do',function (data) {
                    if(data.success){
                        var obj = data.message;
                        console.log(obj);
                        local.val(obj.itemCode);
                        local.siblings('input[name="itemIds"]').val(obj.id);
                        local.siblings('input[name="itemBaseIds"]').val(obj.itemBaseId);
                        local.siblings('input[name="managerIds"]').val(obj.managerId);
                        local.parent().siblings('.mdseIdX').html(obj.mdseIdX);
                        local.parent().siblings('.mdseId').html(obj.mdseId);
                        local.parent().siblings('.name').html(obj.name);
                        local.parent().siblings('.spec').html(obj.spec);
                        local.parent().nextAll().find('input[name="areaPrices"]').val(obj.areaPrice);
                        local.parent().nextAll().find('input[name="quantitys"]').focus();
                    }else{
                        local.focus();
                    }
                },'json');
            }
        }).on('click', '.J_delInput', function() {
            if(num == 1){
                alert('至少保留一条数据');
            }else{
                num--;
                $(this).parent().empty();
            }
        }).on('click' , '.itemAdd',  function () {
            $('.addClass').before(addHtml(null));
        });
        $('#J_btnAddGoods').on('click', function() {
            $('#J_dialogAddGoods, .cover-all').show();
            $('input[name="managerCode"]').val('');
            $('input[name="itemCode"]').val('');
            $('#addItemList').trigger('click');
        });
        $('#J_dialogAddGoods').on('click', '.dialog-cancel, .dialog-close', function() {
            // reset
            $('#J_dialogAddGoods, .cover-all').hide();
        }).on('click', '.dialog-ok', function() {
            $('#addGoods_tbody').find('.checkbox:checked').each(function () {
                var data = {
                    id:$(this).attr('data-id'),
                    itemBaseId:$(this).attr('data-itemBaseId'),
                    managerId:$(this).attr('data-managerId'),
                    areaPrices:$(this).attr('data-areaPrices'),
                    itemCode:$(this).parent().siblings('.itemCode').html(),
                    managerName:$(this).parent().siblings('.managerName').html(),
                    mdseIdX:$(this).parent().siblings('.mdseIdX').html(),
                    mdseId:$(this).parent().siblings('.mdseId').html(),
                    name:$(this).parent().siblings('.name').html(),
                    spec:$(this).parent().siblings('.spec').html()
                };
                $('.addClass').before(addHtml(data));
            });
            $('#form').find('input[name="itemIds"]').each(function () {
                var itemIds = $(this).val();
                if(itemIds == '' || itemIds == null){
                    $(this).parent().siblings('.J_delInput').trigger('click');
                }
            });
            $('#J_dialogAddGoods, .cover-all').hide();
        });
        $('.button-save').on('click' , function () {
            var xnumCheck = /^[0-9]{1,6}$/;
            var testPriceCheck = /^\d{1,5}(\.\d{1,2})?$/;
            var trueNum = 0;
            var msg = '';
            var gaveTime = $('input[name="gaveTime"]').val();
            if(gaveTime == '' || gaveTime == null){
                msg = "请选择交货日期";
            }else if(gaveTime < new Date()){
                msg = "请选择大于今天的日期";
            }

            $('#form').find('input[name="itemIds"]').each(function () {
                var itemIds = $(this).val();
                var itemBaseIds = $(this).siblings('input[name="itemBaseIds"]').val();
                var managerIds = $(this).siblings('input[name="managerIds"]').val();
                var quantitys = $.trim($(this).parent().nextAll().find('input[name="quantitys"]').val());
                var areaPrices = $.trim($(this).parent().nextAll().find('input[name="areaPrices"]').val());
                if(quantitys == null || quantitys == ''){
                    return true;
                }else if(Number(quantitys) == 0 || !xnumCheck.test(quantitys)){
                    msg = "请输入大于0小于9999的正整数";
                    $(this).parent().nextAll().find('input[name="quantitys"]').focus();
                    return false;
                }else if(areaPrices == null || areaPrices == ''){
                    msg = "请输入采购价";
                    $(this).parent().nextAll().find('input[name="areaPrices"]').focus();
                    return false;
                }else if(!testPriceCheck.test(areaPrices)){
                    msg = "请输入正确采购价";
                    $(this).parent().nextAll().find('input[name="areaPrices"]').focus();
                    return false;
                }else if(itemIds == null || itemIds == ''){
                    msg = "请使用正确的商品";
                    $(this).siblings('.itemCode').focus();
                    return false;
                }else{
                    var i = 0;
                    $('#form').find('input[name="itemIds"]').each(function () {
                        var itemIds2 = $(this).val();
                        if(itemIds2 == itemIds){
                            i ++;
                        }
                    });
                    if (i > 1){
                        isOk = false;
                        msg = "请勿操作相同的商品";
                        return false;
                    }else{
                        trueNum ++;
                    }
                };
            });
            if(trueNum == 0 || msg != ''){
                if(msg == ''){
                    msg = '请至少正确录入一笔数据';
                }
                alert(msg);
            }else{
                $.post('${root}/scms/ERPOrderInfo/addOrderInfo.do',$('#form').serialize(),function (data) {
                    if(data.success){
                        alert(data.message);
                        $('.button-cancel').trigger('click');
                    }else{
                        alert(data.message);
                    }
                },'json');
            }
        });

		$('.table-list tbody').on('click','tr',function (event) {
            var event=event||window.event;
            var target=event.target;
            event.stopPropagation();
            var inp = $(this).find("input[type='checkbox']");
            var attr = inp.prop("checked");
            var inpnum=$(this).find("input[type='checkbox']").length;
            if(target.localName=="input"){
                if (attr) {
                    $(this).css({"background":"#009dd9","color":"#fff"});
                } else {
                    $(this).css({"background":"#fff","color":"black"});
                }
            }else if(inpnum==1) {
                if (attr) {
                    inp.prop("checked", false);
                    $(this).has("input[type='checkbox']").css({"background":"#fff","color":"black"});
                } else{
                    inp.prop("checked", true);
                    $(this).has("input[type='checkbox']").css({"background":"#009dd9","color":"#fff"});
                }
            }
        })

        $("#jpagination").pagination({
            pageSize: 5,
            remote: {
                url: '${root}/scms/ERPOrderInfo/addItemList/json.do',
                params: {},
                success: function(data) {
                    var html = '';
                    $.each(data.list, function(i,ele) {
                        html+='<tr>';
                        html+='<td>';
                        html+='<input type="checkbox" data-id="'+ele.id+'" data-areaPrices="'+ele.areaPrice+'" data-itemBaseId="'+ele.itemBaseId+'" data-managerId="'+ele.managerId+'" class="checkbox J_chk">';
                        html+='</td>';
                        html+='<td class="itemCode">'+ele.itemCode+'</td>';
                        html+='<td class="managerName">'+ele.managerName+'</td>';
                        html+='<td class="mdseId">'+ele.mdseId+'</td>';
                        html+='<td class="mdseIdX">'+ele.mdseIdX+'</td>';
                        html+='<td class="name">'+ele.name+'</td>';
                        html+='<td class="spec">'+ele.spec+'</td>';
                        if(ele.pkg == null || ele.pkg == '' || ele.pkg == 'null'){
                            html+='<td class="pkg"></td>';
                        }else{
                            html+='<td class="pkg">'+ele.pkg+'</td>';
                        }
                        html+='</tr>';
                    });
                    if(html == "") {
                        html = '<tr><td colspan="8" class="no-data"></td></tr>';
                    }
                    $('#addGoods_tbody').html(html);
                    dialogPosCenter('#J_dialogAddGoods');
                },
                totalName:'totalSize',
                pageParams: function (data) {
                    return {
                        pageIndex: data.pageIndex + 1,
                        pageSize: data.pageSize
                    }
                }
            }
        });
        $('#addItemList').on('click', function() {
            $("#jpagination").pagination('setParams', {managerCode:$('input[name="managerCode"]').val(), numOrName:$('input[name="itemCode"]').val()});
            $("#jpagination").pagination('setPageIndex', 0);
            $("#jpagination").pagination('remote');
            dialogPosCenter('#J_dialogAddGoods');
        });
        function addHtml(data) {
            num++;
            var html = '';
            html+='<tr>';
            html+='<td>';
            if(data == null){
                html+='<input type="text" class="input itemCode" name="itemCodes" data-shortcut="enter">';
                html+='<input type="hidden" name="itemIds" class="input">';
                html+='<input type="hidden" name="itemBaseIds" class="input">';
                html+='<input type="hidden" name="managerIds" class="input">';
                html+='</td>';
                html+='<td class="mdseIdX"></td>';
                html+='<td class="mdseId"></td>';
                html+='<th class="name"></th>';
                html+='<td class="spec"></td>';
                html+='<td><input type="text" name="quantitys"  maxlength="4" data-shortcut="enter" class="input-search-date"></td>';
                html+='<td><input type="text" name="areaPrices"  maxlength="8" data-shortcut="enter" class="input-search-date"></td>';
            }else{
                html+='<input type="text" class="input itemCode" name="itemCodes" data-shortcut="enter" value="'+data.itemCode+'">';
                html+='<input type="hidden" name="itemIds" class="input" value="'+data.id+'">';
                html+='<input type="hidden" name="itemBaseIds" class="input"  value="'+data.itemBaseId+'">';
                html+='<input type="hidden" name="managerIds" class="input"  value="'+data.managerId+'">';
                html+='</td>';
                html+='<td class="mdseIdX">'+data.mdseId+'</td>';
                html+='<td class="mdseId">'+data.mdseIdX+'</td>';
                html+='<th class="name">'+data.name+'</th>';
                html+='<td class="spec">'+data.spec+'</td>';
                html+='<td><input type="text"  maxlength="4" name="quantitys" data-shortcut="enter" class="input-search-date"></td>';
                html+='<td><input type="text"  maxlength="8" name="areaPrices" data-shortcut="enter" class="input-search-date" value="'+data.areaPrices+'"></td>';
            }
            html+='<td class="J_delInput"><span class="button-operate">删除</span></td>';
            html+='</tr>';
            return html;
        }
    });
</script>
</body>
</html>
