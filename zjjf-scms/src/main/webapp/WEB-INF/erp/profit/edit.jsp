<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="${root }/resources/css/base.css" />
    <script src="${root}/resources/vendor/jquery/jquery-1.12.4.min.js"></script>
    <script src="${root}/resources-src/js/fm.validator.js"></script>
    <script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
    <script src="${root}/resources/js/comm.js?vvv"></script>
</head>
<style type="text/css">
    .validator-error {
        color: red
    }
</style>

<body>
<div class="wrap-bd">
    <form action="${root }/scms/ERPProfit/save.do" class="validator" id="form" onkeypress="if(event.keyCode==13||event.which==13){return false;}">
        <div class="mb-small">
            <div class="title">损益单</div>
        </div>
        <input type="hidden" name="id" value="${detail.id}">
        <div class="bg wrap-bd mb-default">
            <label class="label">损益单：</label>
            <select name="type"  class="select mr-default">
                <c:forEach items="${profitTypes}" var="profitType">
                    <option value="${profitType.index}" <c:if test="${detail.type == profitType.index}" >selected="selected"</c:if>>${profitType.name}</option>
                </c:forEach>
            </select>
            <label class="label">仓库：</label>
            <select name="whId"  class="select mr-default">
                <c:forEach items="${warehouses}" var="warehouse">
                    <option value="${warehouse.id}" <c:if test="${detail.whId == warehouse.id}" >selected="selected"</c:if>>${warehouse.name}</option>
                </c:forEach>
            </select>
             <label class="ml-default label">选择日期：</label>
                <input type="text" name="taskTime" class="input" onfocus="WdatePicker()" value="<fmt:formatDate value="${detail.taskTime}" pattern='yyyy-MM-dd'/>"  data-required/>
            <div class="clearfix mt-default">

            </div>
            <c:if test="${detail.orderId != null}">
                <div class="clearfix">
                    <label class="label">订单号:</label>&nbsp;${detail.orderId}
                </div>
            </c:if>
            <div>
                <label class="label">备注:</label>
                <input type="text" class="input" name="remark" value="${detail.remark}" style="width: 500px;" />
            </div>
        </div>
        <table class="table-list table-border" id="stock_info">
            <thead>
            <tr>
                <th>序号</th>
                <th>商品条形码</th>
                <th>商品名称</th>
                <th>规格</th>
                <th>数量</th>
                <th>单价</th>
                <th>备注</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody class="table-tbody">
            <c:forEach items="${detail.details}" var="ele" varStatus="eleStat">
                <c:set var="count" value="${eleStat.count}" />
                <tr>
                    <td class="count">${count}</td>
                    <td  class="itemBaseId">
                        <input type="hidden" name="itemBaseIds" value="${ele.itemBaseId}" />
                        <input type="hidden" name="ids" value="${ele.id}"/>
                        <input type="text" class="input input-search-date" value="${ele.barCode}" disabled="disabled" data-required/>
                        <input type="button" class="button button-operate J_addNew" value="..." />
                    </td>
                    <td class="name">${ele.name}</td>
                    <td class="spec">${ele.spec}</td>
                    <td><input type="text" class="input input-search-date mr-default" name="quantitys" value="${ele.quantity}"  maxlength="4" data-required/></td>
                    <td><input type="text" class="input input-search-date mr-default" name="prices" value="${ele.price}"  maxlength="8" data-type="deNumber" data-required/></td>
                    <td><input type="text" class="input input-default mr-default" name="remarks" value="${ele.remark}" maxlength="50" data-required/></td>
                    <td><input type="button" class="button button-operate J_del ml-small" value="删除" /></td>
                </tr>
            </c:forEach>
            <c:if test="${fn:length(detail.details) == 0}">
                <c:set var="count" value="0" />
                <%--<tr>
                    <td class="count">${count}</td>
                    <td  class="itemBaseId">
                        <input type="text" class="input input-search-date" name="itemCode" data-type="number" data-required/>
                        <input type="button" class="button button-operate J_addNew" value="..." />
                    </td>
                    <td class="itemCode"></td>
                    <td class="mdseId"></td>
                    <td class="name"></td>
                    <td class="spec"></td>
                    <td><input type="text" class="input input-search-date mr-default" name="quantity" data-type="number" data-required/></td>
                    <td><input type="text" class="input input-default mr-default" name="remark" data-required/></td>
                    <td><input type="button" class="button button-operate J_del" value="删除" /></td>
                </tr>--%>
            </c:if>
            <tr id="lastTr">
                <td colspan="8"><input type="button" value="增加一行" class="button button-operate" /></td>
            </tr>
            </tbody>
        </table>
        <!--选择商品-->
        <div class="dialog hidden" id="J_dialogGoodsNew" style="width: 800px;">
            <div class="dialog-head" style="cursor: move;">
                选择商品
                <div class="dialog-close">
                </div>
            </div>
            <div class="dialog-body dialog-padding">

                <input class="input-search-text" type="text" name="nameAndMdseId" id="nameAndMdseId" maxlength="50" placeholder="商品编号/名称">
                <input type="hidden" name="beginStock" id="beginGoodsStock" value="1" class="input-search-date"/>
                <input type="hidden" maxlength="5" name="endStock" id="endGoodsStock" value="" class="input-search-date"/>
                <input type="button" class="input-search-button search" value="搜索" />

                <div class="mt-default" style="height: 350px;overflow: auto;">
                    <table class="table-list table-border">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>商品编号</th>
                            <th>商品名称</th>
                            <th>仓库</th>
                            <th>可售量</th>
                            <th>规格</th>
                            <th>单位</th>
                        </tr>
                        </thead>
                        <tbody class="table-tbody">

                        </tbody>
                    </table>
                    <%@ include file="../../common/pagination.jsp"%>
                </div>
            </div>
            <div class="dialog-foot">
                <input type="button" class="dialog-button dialog-ok"value="确认">
                <input type="button" class="dialog-button dialog-cancel ml-default" value="取消">
            </div>
        </div>
        <div class="mb-default mt-default">
            <input type="submit" class="button-save" value="确认" />
            <input type="button" class="button-cancel" value="取消"/>
        </div>
    </form>
</div>
</body>
<script>
    selectAll('#J_selectAll', '.J_chk');
    dialogPosCenter('#J_dialogGoodsNew');
    var count = '${count}';
    $(function() {
    	//拖拽
		drag('#J_dialogGoodsNew .dialog-head','#J_dialogGoodsNew');
		function drag(dragSon,dragFather){
    		$(dragSon).bind("mousedown", function(event) {
					/* 获取需要拖动节点的坐标 */
					var offset_x = $(dragFather)[0].offsetLeft; //x坐标
					console.log(offset_x)
					var offset_y = $(dragFather)[0].offsetTop; //y坐标
					/* 获取当前鼠标的坐标 */
					var mouse_x = event.pageX;
					var mouse_y = event.pageY;
					/* 绑定拖动事件 */
					/* 由于拖动时，可能鼠标会移出元素，所以应该使用全局（document）元素 */
					$(document).bind("mousemove", function(ev) {
						/* 计算鼠标移动了的位置 */
						var _x = ev.pageX - mouse_x;
						var _y = ev.pageY - mouse_y;
						/* 设置移动后的元素坐标 */
						var now_x = (offset_x + _x) + "px";
						var now_y = (offset_y + _y) + "px";
						/* 改变目标元素的位置 */
						$(dragFather).css({
							top: now_y,
							left: now_x
						});
					});
				});
				/* 当鼠标左键松开，接触事件绑定 */
				$(document).bind("mouseup", function() {
					$(this).unbind("mousemove");
				});
    	}
        $('.button-cancel').on('click', function() {
            window.location.href = '${root}/scms/ERPProfit.do';
        });
        Validator.language = 'en';
        if(count == 0){
            $('#lastTr').trigger('click');
        }
        $('.dialog').on('click', '.dialog-cancel', function() {
            $('#J_dialogGoodsNew input[name="nameAndMdseId"]').val('');
            $('.dialog,.cover-all').fadeOut();
        }).on('click', '.dialog-close', function() {
            $('.dialog .dialog-cancel').trigger('click');
        })
//        $('#form').on('click' , '.button-save' , function (e) {
//            e.preventDefault();
//            var submit = true;
//            if($(this).find('select[name="type"]').val() == 2){
//                submit = true;
//            }else{
//                $('input[name="quantitys"]').each(function(){
//                    if($(this).val() > $(this).attr('data-stockNum')){
//                        alert('库存不足，请重新输入');
//                        $(this).focus();
//                        submit = false;
//                        return false;
//                    }
//                });
//            }
//            if(submit)
//                $('#form').submit();
//        });
        $('tbody').on('click', '.J_del', function() {
            if(confirm('确认删除？')) {
                if(count > 1) {
                    $(this).parent('td').parent('tr').nextAll().each(function (i) {
                        $(this).find('.count').html(($(this).find('.count').html() - 1));
                    });
                    $(this).parent('td').parent('tr').remove();
                    count -= 1;
                } else{
                    alert('至少保留一条数据');
                }

            }
        });
        $("#jpagination").pagination({
            pageSize: 10,
            remote: {
                url: '${root}/ERPStockManager/getSupplierStock.do',
                success: function(data) {
                    if(data.flag) {
                        var html = '';
                        $.each(data.list, function(i, item) {
                            html+='<tr data-itemBaseId="'+item.itemBaseId+'" data-stockNum="'+item.stockNum+'" data-mdseId="'+item.mdseId+'" data-pkg="'+item.pkg+'" data-spec="'+item.spec+'" data-name ="'+item.goodsName+'" data-price="'+item.areaPrice+'">'
                            html+='<td>'+(i+1)+'</td>'
                            html+='<td>'+item.mdseId+'</td>'
                            html+='<td><span class="J_name">'+item.goodsName+'</span></td>'
                            html+='<td>'+item.whName+'</td>';
                            /* html+='<td>'+item.typeMgName+'<input type="hidden" class="J_typeMg" value="'+item.typeMg+'" ></td>'; */
                            html+='<td>'+item.stockNum+'</td>';
                            html+='<td>'+item.spec+'</td>';
                            if(item.pkg==null || item.pkg=='' || item.pkg=='null'){
                                html+='<td>箱</td>';
                            }else{
                                html+='<td>'+item.pkg+'</td>';
                            }
                            html+='</tr>';
                        });
                        $('#J_dialogGoodsNew .table-tbody').html(html);
                    }
                },
                totalName: 'totalSize',
            }
        });
        //选中标识
        $('.dialog').on('click', 'td', function() {
            $(this).parent().css({
                'background': '#009dd9',
                'color': 'white'
            }).siblings().css({
                'background': '#fff',
                'color': 'black'
            });
            $_tr = $(this).parent();
        });
        $('#J_dialogGoodsNew').on('click', '.search', function() {
            var nameAndMdseId = $('#nameAndMdseId').val();
            var beginGoodsStock = $('#beginGoodsStock').val();
            $("#jpagination").pagination('setParams', {nameAndMdseId:nameAndMdseId ,beginGoodsStock:beginGoodsStock });
            $("#jpagination").pagination('remote');
        }).on('click', '.dialog-ok', function() {
            var itemBaseId = $('#J_dialogGoodsNew .isCheck').attr('data-itemBaseId');
                if(itemBaseId == null || itemBaseId == undefined || itemBaseId == ''){
                    alert('请选择一个商品');
                }else{
                var chongfu = false;
                var checkItemBaseId = $('#stock_info .isCheck').find('input[name="itemBaseIds"]').val();
                if (checkItemBaseId != itemBaseId){
                    $('#stock_info tr').each(function () {
                        if($(this).find('input[name="itemBaseIds"]').val() == itemBaseId){
                            chongfu = true;
                            alert('该商品已经选择');
                        }
                    });
                }
                if(!chongfu){
                    var stockNum = $('#J_dialogGoodsNew .isCheck').attr('data-stockNum');
                    var pkg = $('#J_dialogGoodsNew .isCheck').attr('data-pkg');
                    var spec = $('#J_dialogGoodsNew .isCheck').attr('data-spec');
                    var mdseId = $('#J_dialogGoodsNew .isCheck').attr('data-mdseId');
                    var name = $('#J_dialogGoodsNew .isCheck').attr('data-name');
                    var price = $('#J_dialogGoodsNew .isCheck').attr('data-price');

                    $('#stock_info .isCheck').find('input[name="itemBaseIds"]').val(itemBaseId);
                    $('#stock_info .isCheck').find('input[name="mdseId"]').val(mdseId);
                    $('#stock_info .isCheck').find('input[name="quantitys"]').attr('data-stockNum', stockNum);
                    $('#stock_info .isCheck').find('input[name="prices"]').val(price == null || price == 'null' || price == '' ? '' : price);

                    $('#stock_info .isCheck .name').html(name);
                    $('#stock_info .isCheck .spec').html(spec);

                    $('.dialog,.cover-all').fadeOut();
                }
            }
        }).on('click', 'tr', function() {
            $('#J_dialogGoodsNew tr').attr('class' , '');
            $(this).attr('class' , 'isCheck');
        });
        $('#stock_info').on('click' , 'tr' , function(){
            $('#stock_info tr').attr('class' , '');
            $(this).attr('class' , 'isCheck');
        }).on('click' ,'.J_addNew' ,function () {
            $('#J_dialogGoodsNew .search').trigger('click');
            $('#J_dialogGoodsNew,.cover-all').fadeIn();
        });
    });
    //增加一行
    $('#lastTr').find('.button').click(function(){
        count ++;
        var html= '<tr>';
        html += '<td class="count">'+count+'</td>';
        html += '<td  class="itemBaseId">';
        html += '<input type="hidden" name="itemBaseIds"/>';
        html += '<input type="hidden" name="ids"/>';
        html += '<input type="text" class="input input-search-date" name="mdseId" disabled="disabled" data-required/>';
        html += '<input type="button" class="button button-operate J_addNew ml-small" value="..." />';
        html += '</td>';
        html += '<td class="name"></td>';
        html += '<td class="spec"></td>';
        html += '<td><input type="text" class="input input-search-date mr-default" name="quantitys" maxlength="4" data-type="intNumber" data-required/></td>';
        html += '<td><input type="text" class="input input-default mr-default" name="prices" maxlength="8"  data-type="deNumber" data-required/></td>';
        html += '<td><input type="text" class="input input-search-date mr-default" name="remarks" maxlength="50" data-required/></td>';
        html += '<td><input type="button" class="button button-operate J_del" value="删除" /></td>';
        html += '</tr>';
        $(this).parent().parent().before(html);
    });
</script>
</html>
