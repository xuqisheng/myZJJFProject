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
    <script src="${root }/resources/vendor/jquery/jquery-1.12.4.min.js"></script>
    <script src="${root }/resources-src/js/fm.validator.js"></script>
    <script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
    <script src="${root}/resources/js/comm.js?vvv"></script>
</head>
<style type="text/css">
    ul {
        list-style: none;
        padding: 0;
        margin: 0;
    }

    li {
        margin-bottom: 18px;
    }

    .validator-error {
        color: red
    }
</style>

<body>
<div class="wrap-bd">
    <form action="${root }/scms/ERPPurchaseStock/stockOut/save.do" class="validator" onkeypress="if(event.keyCode==13||event.which==13){return false;}">
        <div class="mb-small">
            <div class="title">新增采购退货</div>
        </div>
        <div class="bg wrap-bd mb-default">
            <label class="label">仓库：</label>
            <select name="whId"  class="select">
                <c:forEach items="${warehouses}" var="warehouse">
                    <option value="${warehouse.id}" <c:if test="${warehouse.id == detail.whId}">selected="selected"</c:if> >${warehouse.name}</option>
                </c:forEach>
            </select>

            <div class="mb-default mt-default">
                <label class="label">供应商：</label>
                <input type="button" class="button button-operate mr-default" id="J_addManager"
                    <c:choose>
                        <c:when test="${detail == null}">value="选择供应商"</c:when>
                        <c:otherwise>value="${detail.managerName}"</c:otherwise>
                    </c:choose> />
                <input type="hidden" id="managerId" name="managerId" value="${detail.managerId}"/>
                <input type="hidden" name="id" value="${detail.id}"/>
            </div>
            <div class="clearfix mb-default">
                <label class="label">退货时间:</label>
                <input type="text" name="taskTime" class="input" onfocus="WdatePicker()" value="<fmt:formatDate value="${detail.taskTime}" pattern='yyyy-MM-dd'/>" data-required/>
            </div>
            <div>
                <label class="label">退货备注:</label>
                <input type="text" class="input" name="remark" value="${detail.remark}" style="width: 400px;" />
            </div>
        </div>
        <table class="table-list table-border" id="stock_info">
            <thead>
            <tr>
                <th>序号</th>
                <th>商品供应码</th>
                <th>箱码</th>
                <th>商品条形码</th>
                <th>商品名称</th>
                <th>规格</th>
                <th>退货价格</th>
                <th>退货数量</th>
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
                        <input type="hidden" name="itemBaseIds" value="${ele.itemBaseId}">
                        <input type="hidden" name="ids" value="${ele.id}">
                        <input type="text" class="input input-search-date" name="itemCode" disabled="disabled" value="${ele.barCode}" data-required/>
                        <input type="button" class="button button-operate J_addNew ml-small" value="..." />
                    </td>
                    <td class="itemCode">${ele.itemCode}
                        <input type="hidden" name="itemCodes" value="${ele.itemCode}">
                        <input type="hidden" name="itemIds" value="${ele.id}">
                    </td>
                    <td class="mdseId">${ele.mdseId}</td>
                    <td class="name">${ele.name}</td>
                    <td class="spec">${ele.name}</td>
                    <td><input type="text" class="input input-search-date mr-default" name="areaPrices" data-type="deNumber" value="${ele.areaPrice}" data-required/></td>
                    <td><input type="text" class="input input-search-date mr-default" name="quantitys" data-type="intNumber" value="${ele.quantity}" data-required/></td>
                    <td><input type="text" class="input input-default mr-default" name="remarks" value="${ele.remark}"/></td>
                    <td><input type="button" class="button button-operate J_del" value="删除" /></td>
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
                <td colspan="10"><input type="button" value="增加一行" class="button button-operate" /></td>
            </tr>
            </tbody>
        </table>
        <!--选择商品-->
        <div class="dialog hidden" id="J_dialogGoodsNew" style="width: 700px;">
            <div class="dialog-head" style="cursor: move;">
                选择商品
                <div class="dialog-close">
                </div>
            </div>
            <div class="dialog-body dialog-padding">

                    <input class="input-search-text" type="text" name="nameAndMdseId" id="nameAndMdseId" maxlength="50" placeholder="商品编号/名称">
                    <input type="hidden" name="beginStock" id="beginGoodsStock" value="1" class="input-search-date" placeholder="" />
                    <input type="hidden" maxlength="5" name="endStock" id="endGoodsStock" value="" class="input-search-date" placeholder="" />
                    <input type="button" class="input-search-button search" value="搜索" />

                <div class="mt-default" style="height: 250px;overflow: auto;">
                    <table class="table-list table-border">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>商品供应码</th>
                            <th>箱码</th>
                            <th>条形码</th>
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
                    <div id="jpaginationItem"></div>
                </div>
            </div>
            <div class="dialog-foot">
                <input type="button" class="dialog-button dialog-ok"value="确认">
                <input type="button" class="dialog-button dialog-cancel ml-default" value="取消">
            </div>
        </div>

        <!--选择供应商-->
        <div class="dialog hidden" id="J_dialogSupplier" style="width: 700px;">
            <div class="dialog-head" style="cursor: move;">
                选择供应商
                <div class="dialog-close">
                </div>
            </div>
            <div class="dialog-body dialog-padding">
                <input type="text" placeholder="供应商名称/编码" name="codeOrName" class="input input-default mr-default" />
                <input type="button" class="input-search-button search" value="搜索" />
                <div class="mt-default" style="height: 250px;overflow: auto;">
                    <table class="table-list table-border">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>编号</th>
                            <th>名称</th>
                            <%--<th>手机号</th>--%>
                        </tr>
                        </thead>
                        <tbody class="table-tbody">
                        </tbody>
                    </table>
                    <%@ include file="../../common/pagination.jsp"%>
                </div>
            </div>
            <div class="dialog-foot">
                <input type="button" class="dialog-button dialog-ok" value="确认">
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
    drag('#J_dialogGoodsNew .dialog-head','#J_dialogGoodsNew');
    drag('#J_dialogSupplier .dialog-head','#J_dialogSupplier');
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
    selectAll('#J_selectAll', '.J_chk');
    dialogPosCenter('#J_dialogGoodsNew');
    dialogPosCenter('#J_dialogSupplier');
    var count = '${count}';
    $(function() {
        $('.button-cancel').on('click', function() {
            window.location.href = '${root}/scms/ERPPurchaseStock/list/2.do';
        });
        Validator.language = 'en';
        if(count == 0){
            $('#lastTr').trigger('click');
        }
        $('#J_addManager').on('click', function() {
            $('#J_dialogSupplier').fadeIn();
            $('#J_dialogSupplier input[name="codeOrName"]').val('');
            $('#managerId').val('');
            $('#J_dialogSupplier .search').trigger('click');
        });
        $('.dialog').on('click', '.dialog-cancel', function() {
            $('#J_dialogSupplier input[name="codeOrName"]').val('');
            $('#J_dialogGoodsNew input[name="nameAndMdseId"]').val('');
            $('.dialog').fadeOut();
        }).on('click', '.dialog-close', function() {
            $('.dialog .dialog-cancel').trigger('click');
        })
        $('.dialog .table-list').on('click', 'td', function() {

            $(this).parent().css({
                'background': '#009dd9',
                'color': 'white'
            }).siblings().css({
                'background': '#fff',
                'color': 'black'
            }).parent().find('#lastTr').css('background','none');
            $_tr = $(this).parent();
        });
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
                url: '${root}/scms/ERPMa/getAllERPManagerJSON.do',
                success: function(data) {
                    if(data.flag) {
                        var html = '';
                        $.each(data.list, function(i, ele) {
                            html += '<tr data-managerId=' + ele.id + ' data-managerName='+ele.managerName+'>' +
                                '<td>' + (i + 1) + '</td>' +
                                '<td>' + ele.managerCode + '</td>' +
                                '<td>' + ele.managerName + '</td>' +
                                /*'<td>'+ele.mobile+'</td>' +*/
                                '</tr>';
                        });
                        $('#J_dialogSupplier .table-tbody').html(html);
                    }
                },
                totalName: 'totalSize',
                pageParams: function(data) {
                    return {
                        pageIndex: data.pageIndex + 1,
                        pageSize: data.pageSize
                    }
                }
            }
        });
        $('#J_dialogSupplier').on('click', '.search', function() {
            $("#jpagination").pagination('setParams', {
                'codeOrName': $('#J_dialogSupplier input[name="codeOrName"]').val()
            });
            $("#jpagination").pagination('remote');
        }).on('click', '.dialog-ok', function() {
            $('#managerId').val($('#J_dialogSupplier .isCheck').attr('data-managerId'));
            $('#J_addManager').val($('#J_dialogSupplier .isCheck').attr('data-managerName'));
            $('.dialog').fadeOut();
        }).on('click', 'tr', function() {
            $('#J_dialogSupplier tr').attr('class' , '');
            $(this).attr('class' , 'isCheck');
        });
        $("#jpaginationItem").pagination({
            pageSize: 10,
            remote: {
                url: '${root}/ERPStockManager/getSupplierStock.do',
                success: function(data) {
                    if(data.flag) {
                        var html = '';
                        $.each(data.list, function(i, item) {
                            html+='<tr data-itemBaseId="'+item.itemBaseId+'" data-stockNum="'+item.stockNum+'"' +
                                ' data-mdseId="'+item.mdseId+'" data-pkg="'+item.pkg+'"' +
                                ' data-spec="'+item.spec+'" data-itemCode ="'+item.itemCode+'"' + 'data-itemId ="'+item.itemId+'"' +
                                ' data-barCode="'+item.barCode+'" ' +
                                ' data-name ="'+item.goodsName+'">'
                            html+='<td>'+(i+1)+'</td>'
                            html+='<td>'+item.itemCode+'</td>'
                            html+='<td>'+item.mdseId+'</td>'
                            html+='<td>'+item.barCode+'</td>'
                            html+='<td>'+item.goodsName+'</td>'
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
        $('#J_dialogGoodsNew').on('click', '.search', function() {
            var managerId =$('#managerId').val();
            var nameAndMdseId = $('#nameAndMdseId').val();
            var beginGoodsStock = $('#beginGoodsStock').val();
            if($('#managerId').val() == ''){
                alert('请选择供应商');
            }
            $("#jpaginationItem").pagination('setParams', {managerId:managerId , nameAndMdseId:nameAndMdseId ,beginGoodsStock:beginGoodsStock });
            $("#jpaginationItem").pagination('remote');
        }).on('click', '.dialog-ok', function() {
            if($('#managerId').val() == ''){
                alert('请选择供应商');
            }
            var itemBaseId = $('#J_dialogGoodsNew .isCheck').attr('data-itemBaseId');
            if(itemBaseId == null || itemBaseId == undefined || itemBaseId == ''){
                alert('请选择一个商品');
            }else {
                var chongfu = false;
                var checkItemBaseId = $('#stock_info .isCheck').find('input[name="itemBaseIds"]').val();
                if (checkItemBaseId != itemBaseId) {
                    $('#stock_info tr').each(function () {
                        if ($(this).find('input[name="itemBaseIds"]').val() == itemBaseId) {
                            chongfu = true;
                            alert('该商品已经选择');
                        }
                    });
                }
                if (!chongfu) {
                    var stockNum = $('#J_dialogGoodsNew .isCheck').attr('data-stockNum');
                    var pkg = $('#J_dialogGoodsNew .isCheck').attr('data-pkg');
                    var spec = $('#J_dialogGoodsNew .isCheck').attr('data-spec');
                    var itemCode = $('#J_dialogGoodsNew .isCheck').attr('data-itemCode');
                    var itemId = $('#J_dialogGoodsNew .isCheck').attr('data-itemId');
                    var mdseId = $('#J_dialogGoodsNew .isCheck').attr('data-mdseId');
                    var barCode = $('#J_dialogGoodsNew .isCheck').attr('data-barCode');
                    var name = $('#J_dialogGoodsNew .isCheck').attr('data-name');

                    $('#stock_info .isCheck').find('input[name="itemCode"]').val(itemCode);
                    $('#stock_info .isCheck').find('input[name="itemBaseIds"]').val(itemBaseId);
                    $('#stock_info .isCheck .itemCode').html(mdseId+'<input type="hidden" name="itemCodes" value="'+itemCode+'"/><input type="hidden" name="itemIds" value="'+itemId+'"/>');
                    $('#stock_info .isCheck .mdseId').html(barCode);
                    $('#stock_info .isCheck .name').html(name);
                    $('#stock_info .isCheck .spec').html(spec);

                    $('.dialog').fadeOut();
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
            if($('#managerId').val() == ''){
                alert('请选择供应商');
                $('#J_addManager').trigger('click');
            }else{
                $('#J_dialogGoodsNew .search').trigger('click');
                $('#J_dialogGoodsNew').fadeIn();
            }
        });
    });
    //增加一行
    $('#lastTr').find('.button').click(function(){
        count ++;
        var html= '<tr>';
        html += '<td class="count">'+count+'</td>';
        html += '<td  class="itemBaseId">';
        html += '<input type="hidden" name="itemBaseIds">';
        html += '<input type="hidden" name="ids">';
        html += '<input type="text" class="input input-search-date" name="itemCode" disabled="disabled" data-required/>';
        html += '<input type="button" class="button button-operate J_addNew ml-small" value="..." />';
        html += '</td>';
        html += '<td class="itemCode"></td>';
        html += '<td class="mdseId"></td>';
        html += '<td class="name"></td>';
        html += '<td class="spec"></td>';
        html += '<td><input type="text" class="input input-search-date mr-default" name="areaPrices" data-type="deNumber" data-required/></td>';
        html += '<td><input type="text" class="input input-search-date mr-default" name="quantitys" data-type="intNumber" data-required/></td>';
        html += '<td><input type="text" class="input input-default mr-default" name="remarks"/></td>';
        html += '<td><input type="button" class="button button-operate J_del" value="删除" /></td>';
        html += '</tr>';
        $(this).parent().parent().before(html);
    });
</script>

</html>
