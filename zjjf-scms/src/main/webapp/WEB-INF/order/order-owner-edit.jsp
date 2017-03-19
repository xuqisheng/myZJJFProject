<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <title>附属订单</title>
    <link rel="stylesheet" type="text/css" href="${root }/resources/css/base.css" />
    <%@ include file="../common/head.jsp"%>
    <script src="${root }/resources/vendor/jquery/jquery-1.12.4.min.js"></script>
    <script src="${root }/resources/js/comm.js"></script>
</head>

<body>
<div class="wrap-bd">
    <div class="">
        <div class="mb-default title">附属订单</div>
    </div>
    <div class="hidden cashDemo">
        <select class="select" name="cashs">
            <c:forEach items="${ownerCashs}" var="cash">
                <option value="${cash.index}">${cash.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="hidden typeDemo">
        <select class="select" name="types">
            <c:forEach items="${ownerTypes}" var="type">
                <option value="${type.index}">${type.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="">
        <form id="stockForm" action="${root }/scms/ERPSpOrderOwner/addERPSpOrderOwner/${detail.orderId}.do">
            <label for="whId">仓库：</label>
            <select id="whId" name="whId" class="select">
                <c:forEach items="${warehouses}" var="warehouse">
                    <option value="${warehouse.id}">${warehouse.name}</option>
                </c:forEach>
            </select>
            <table class="table-list mb-default mt-default">
                <c:choose>
                    <c:when test="${detail.status == 3 }">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>类型</th>
                            <th>兑换对象</th>
                            <th>商品条码</th>
                            <th>商品名称</th>
                            <th>数量</th>
                            <th>兑换商品数量</th>
                            <th>抵扣金额</th>
                            <th>备注</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody class="table-tbody">
                        <c:forEach items="${ownerDetail}" var="ele" varStatus="eleStat">
                            <c:set var="count" value="${eleStat.count}" />
                            <tr class="detail">
                                <td class="count">${count}</td>
                                <td class="type">
                                    <select class="select" name="types">
                                        <c:forEach items="${ownerTypes}" var="type">
                                            <option value="${type.index}" <c:if test="${ele.type == type.index}">selected="selected"</c:if> >${type.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td class="cash">
                                    <select class="select" name="cashs">
                                        <c:forEach items="${ownerCashs}" var="cash">
                                            <option value="${cash.index}" <c:if test="${ele.cash == cash.index}">selected="selected"</c:if> >${cash.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <input type="text" class="input input-search-date mdseId" value="${ele.barCode}" disabled="disabled" />
                                    <input type="hidden" name="itemBaseIds" value="${ele.itemBaseId}" />
                                    <input type="hidden" name="ids" value="${ele.id}" />
                                    <input type="button" class="button button-operate J_addNew" value="..." />
                                </td>
                                <td class="name">${ele.name}</td>
                                <td><input type="text" class="input input-search-date" name="quantitys" value="${ele.quantity}" /></td>
                                <td><input type="text" class="input input-search-date" name="cashQuantitys" value="${ele.cashQuantity}" /></td>
                                <td><input type="text" class="input input-search-date" name="cashPrices" value="${ele.cashPrice}" /></td>
                                <td><input type="text" class="input input-default" name="remarks" value="${ele.remark}" /></td>
                                <td><input type="button" class="button button-operate J_del" value="删除" /></td>
                            </tr>
                        </c:forEach>
                        <tr id="lastTr">
                            <td colspan="11"><input type="button" value="增加一行" class="button button-operate" /></td>
                        </tr>
                        </tbody>
                    </c:when>
                    <c:otherwise>
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>类型</th>
                            <th>兑换对象</th>
                            <th>商品条码</th>
                            <th>商品名称</th>
                            <th>数量</th>
                            <th>兑换商品数量</th>
                            <th>抵扣金额</th>
                            <th>回收数量</th>
                            <th>确认兑换数量</th>
                            <th>确认抵扣金额</th>
                            <th>备注</th>
                        </tr>
                        </thead>
                        <tbody class="table-tbody">
                            <c:forEach items="${ownerDetail}" var="ele" varStatus="eleStat">
                                <tr>
                                    <td>${eleStat.count}</td>
                                    <td>${ele.typeStr}</td>
                                    <td>${ele.cashStr}</td>
                                    <td>${ele.barCode}</td>
                                    <td>${ele.name}</td>
                                    <td>${ele.quantity}</td>
                                    <td>${ele.cashQuantity}</td>
                                    <td>${ele.cashPrice}</td>
                                    <td>
                                        <input type="text" class="input input-search-date" name="backQuantitys" value="${ele.backQuantity}"/>
                                        <input type="hidden" name="ids" value="${ele.id}" />
                                        <input type="hidden" name="types" value="${ele.type}">
                                    </td>
                                    <td><input type="text" class="input input-search-date" name="sureQuantitys" value="${ele.sureQuantity}" <c:if test="${ele.type != 1 }"> readonly="readonly" </c:if>/></td>
                                    <td><input type="text" class="input input-search-date" name="surePrices" value="${ele.surePrice}"  <c:if test="${ele.type == 1 }">readonly="readonly" </c:if>/></td>
                                    <td>${ele.remark}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </c:otherwise>
                </c:choose>
            </table>
        </form>
        <div class="mt-default">
            <input type="button" class="button button-save" value="确认" />
            <input type="button" class="button-cancel" value="取消" onclick="window.close()" />
        </div>
    </div>
    <!--选择商品-->
    <div class="dialog hidden" id="J_dialogGoodsNew" style="width: 700px;">
        <div class="dialog-head" style="cursor: move;">
            选择商品
            <div class="dialog-close">
            </div>
        </div>
        <div class="dialog-body dialog-padding">

            <input class="input-search-text" type="text" name="nameAndMdseId" id="nameAndMdseId" maxlength="50" placeholder="商品编号/名称">
            <input type="hidden" name="beginStock" id="beginGoodsStock" value="1" class="input-search-date" />
            <input type="hidden" maxlength="5" name="endStock" id="endGoodsStock" value="" class="input-search-date" />
            <input type="button" class="input-search-button search" value="搜索" />

            <div class="mt-default" style="height: 250px;overflow: auto;">
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
                <%@ include file="../common/pagination.jsp"%>
            </div>
        </div>
        <div class="dialog-foot">
            <input type="button" class="dialog-button dialog-ok" value="确认">
            <input type="button" class="dialog-button dialog-cancel ml-default" value="取消">
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var status  = '${detail.status}';
    //兑换数量校验
    function duihuan(m) {
        if(m.length == 0) {
            return false;
        }
        if(m.length != 0) {
            var reg = /^\+?[1-9]\d*$/;
            var r = m.match(reg);
            if(r == null) {
                $(this).val('');
                return false;
            }
        }else{
            return true;
        }
    }
    //备注信息校验
    function beizhu(n) {

        if(!n.length >= 50) {
            return false;
        }else{
            return true;
        }
    }
    //整数校验
    function zhengshu(w) {
        if(w.length != 0) {
            var reg = /^\+?[1-9]\d*$/;
            var r = w.match(reg);
            if(r == null) {
                return false;
            }
            else{
                return true;
            }
        }
    }
    //抵扣金额校验
    function dikou(x) {
        if(x.length != 0) {
            var reg = /^\d{1,5}(\.\d{1,2})?$/;
            var r = x.match(reg);
            if(r == null) {
                return false;
            }
            else{
                return true;
            }
        }

    }
    $(function() {
        $(function () {
            $('select[name="types"]').trigger('change');
            if($('.detail').length == 0){
                $('#lastTr').trigger('click');
            }
        });

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

				/* 绑定鼠标左键按住事件 */
        $('.dialog .table-list').on('click', 'td', function() {
            $(this).parent().css({
                'background': '#009dd9',
                'color': 'white'
            }).siblings().css({
                'background': '#fff',
                'color': 'black'
            });
        });
        dialogPosCenter('#J_dialogGoodsNew');
        //      var url = '${root }/scms/ERPSpOrderOwner/addERPSpOrderOwner/${detail.orderId}.do';
        $('.button-save').on('click', function(e) {
            e.preventDefault();
            var isSubmit = true;
            if(status != 3){
                $('#stockForm .table-tbody .detail').each(function(){
                    var type = $(this).find('input[name="types"]').val();
                    var quantitys = $(this).find('input[name="backQuantitys]');
                    var trQuantitys = $.trim(quantitys.val());
                    if(quantitys.val() == null || quantitys.val() == ''){
                        layer.tips('请输入确认兑换数量' , quantitys);
                        quantitys.focus();
                        isSubmit = false;
                        return false;
                    }else if(!zhengshu(trQuantitys)){
                        layer.tips('请输入正确的确认兑换数量' , quantitys);
                        quantitys.focus();
                        isSubmit = false;
                        return false;
                    }else if(type == 1){	//商品
                        var cashQuantitys = $(this).find('input[name="sureQuantitys"]');
                        var trcashQuantitys = $.trim(cashQuantitys.val());

                        if(cashQuantitys.val() == null || cashQuantitys.val() == ''){
                            layer.tips('请输入数量' , cashQuantitys);
                            cashQuantitys.focus();
                            isSubmit = false;
                            return false;
                        }else if(!zhengshu(trcashQuantitys)){
                            layer.tips('请输入数量' , cashQuantitys);
                            cashQuantitys.focus();
                            isSubmit = false;
                            return false;
                        }

                    }else if(type == 2){	//金额
                        var cashPrices = $(this).find('input[name="surePrices"]');
                        var trcashPrices = $.trim(quantitys.val());
                        if(cashPrices.val() == null || cashPrices.val() == ''){
                            isSubmit = false;
                            layer.tips('请输入抵扣金额' , cashPrices);
                            cashPrices.focus();
                            return false;
                        }else if(!dikou(trcashPrices)){
                            isSubmit = false;
                            layer.tips('请输入正确抵扣金额' , cashPrices);
                            cashPrices.focus();
                            return false;
                        }
                    }
                });
            }else{
                $('#stockForm .table-tbody .detail').each(function(){
                    var type = $(this).find('select[name="types"]').val();
                    var quantitys = $(this).find('input[name="quantitys"]');
                    var trQuantitys=$.trim(quantitys.val());
                    var remarks = $(this).find('input[name="remarks"]');
                    var trRemarks = $.trim(remarks.val());
                    if(quantitys.val() == null || quantitys.val() == ''){
                        layer.tips('请输入数量' , quantitys);
                        quantitys.focus();
                        isSubmit = false;
                        return false;
                    }else if(!zhengshu(trQuantitys)){
                        layer.tips('请输入正确的数量' , quantitys);
                        quantitys.focus();
                        isSubmit = false;
                        return false;
                    }else if(remarks.val() == null || remarks.val() == ''){
                        layer.tips('请输入备注' , remarks);
                        remarks.focus();
                        isSubmit = false;
                        return false;
                    }else if(!beizhu(trRemarks)){
                        layer.tips('请输入备注' , remarks);
                        remarks.focus();
                        isSubmit = false;
                        return false;
                    }else if(type == 1){	//商品
                        var itemId = $(this).find('input[name="itemBaseIds"]');
                        var cashQuantitys= $(this).find('input[name="cashQuantitys"]');
                        var trcashQuantitys=$.trim(cashQuantitys.val());

                        if(itemId.val() == null || itemId.val() == ''){
                            layer.tips('请选择商品', $('.mdseId'));
                            itemId.focus();
                            isSubmit = false;
                            return false;
                        }else if(cashQuantitys.val() == null || cashQuantitys.val() == ''){
                            layer.tips('请输入数量' , cashQuantitys);
                            cashQuantitys.focus();
                            isSubmit = false;
                            return false;
                        }else if(!zhengshu(trcashQuantitys)){
                            layer.tips('请输入数量' , cashQuantitys);
                            cashQuantitys.focus();
                            isSubmit = false;
                            return false;
                        }
                    }else if(type == 2){	//金额
                        var cashPrices= $(this).find('input[name="cashPrices"]');
                        var trcashPrices=$.trim(cashPrices.val());
                        if(cashPrices.val() == null || cashPrices.val() == ''){
                            isSubmit = false;
                            layer.tips('请输入抵扣金额' , cashPrices);
                            cashPrices.focus();
                            return false;
                        }else if(!dikou(trcashPrices)){
                            isSubmit = false;
                            layer.tips('请输入正确抵扣金额' , cashPrices);
                            cashPrices.focus();
                            return false;
                        }
                    }
                });
            }

            if(isSubmit)
                $('#stockForm').submit();
        });
        $('#stockForm').on('click', 'tr', function() {
            $('#stockForm tr').removeClass('isCheck');
            $(this).addClass('isCheck');
        }).on('click', '.J_addNew', function() {
            $('#nameAndMdseId').val('');
            $('#J_dialogGoodsNew .search').trigger('click');
            $('#J_dialogGoodsNew,.cover-all').fadeIn();
        }).on('change', 'select[name="types"]', function() {
            var type = $(this).val()
            if(type == '1') { //兑换商品
                $(this).parent().parent().find('input[name="cashQuantitys"]').attr('readonly', false);
                //                $(this).parent().parent().find('input[name="sureQuantitys"]').attr('readonly',false);

                $(this).parent().parent().find('input[name="cashPrices"]').val('');
                //                $(this).parent().parent().find('input[name="surePrices"]').val('');
                $(this).parent().parent().find('input[name="cashPrices"]').attr('readonly', true);
                //                $(this).parent().parent().find('input[name="surePrices"]').attr('readonly',true);
                $(this).parent().parent().find('.J_addNew').fadeIn();
            } else if(type == '2') { //扣减金额

                $(this).parent().parent().find('input[name="cashQuantitys"]').val('');
                $(this).parent().parent().find('input[name="itemBaseIds"]').val('');
                $(this).parent().parent().find('.mdseId').val('');
                $(this).parent().parent().find('.name').html('');
                //                $(this).parent().parent().find('input[name="sureQuantitys"]').val('');
                $(this).parent().parent().find('input[name="cashQuantitys"]').attr('readonly', true);
                //                $(this).parent().parent().find('input[name="sureQuantitys"]').attr('readonly',true);

                $(this).parent().parent().find('input[name="cashPrices"]').attr('readonly', false);
                //                $(this).parent().parent().find('input[name="surePrices"]').attr('readonly',false);
                $(this).parent().parent().find('.J_addNew').fadeOut();
            }
        }).on('click', '.J_del', function() {
            if(confirm('确认删除？')) {
                var tdCount =  $('.count').length;
                console.log(tdCount);
                if(tdCount > 1) {
                    $(this).parent('td').parent('tr').nextAll().each(function(i) {
                        $(this).find('.count').html(($(this).find('.count').html() - 1));
                    });
                    $(this).parent('td').parent('tr').remove();
                } else {
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
                            html += '<tr data-itemBaseId="' + item.itemBaseId + '" data-stockNum="' + item.stockNum + '" data-mdseId="' + item.mdseId + '" data-pkg="' + item.pkg + '" data-spec="' + item.spec + '" data-name ="' + item.goodsName + '">'
                            html += '<td>' + (i + 1) + '</td>'
                            html += '<td><span class="J_mdseId">' + item.mdseId + '</span><input type="hidden" class="J_cid" value="' + item.itemBaseId + '" ></td>'
                            html += '<td><span class="J_name">' + item.goodsName + '</span></td>'
                            html += '<td>' + item.whName + '</td>';
                            /* html+='<td>'+item.typeMgName+'<input type="hidden" class="J_typeMg" value="'+item.typeMg+'" ></td>'; */
                            html += '<td>' + item.stockNum + '</td>';
                            html += '<td>' + item.spec + '</td>';
                            if(item.pkg == null || item.pkg == '' || item.pkg == 'null') {
                                html += '<td>箱</td>';
                            } else {
                                html += '<td>' + item.pkg + '</td>';
                            }
                            html += '</tr>';
                        });
                        $('#J_dialogGoodsNew .table-tbody').html(html);
                    }
                },
                totalName: 'totalSize',
            }
        });
        $('#J_dialogGoodsNew').on('click', '.search', function() {
            var nameAndMdseId = $('#nameAndMdseId').val();
            var beginGoodsStock = $('#beginGoodsStock').val();
            $("#jpagination").pagination('setParams', {
                nameAndMdseId: nameAndMdseId,
                beginStock: beginGoodsStock
            });
            $("#jpagination").pagination('remote');
        }).on('click', '.dialog-ok', function() {
            var itemBaseId = $('#J_dialogGoodsNew .isCheck').attr('data-itemBaseId');
            var stockNum = $('#J_dialogGoodsNew .isCheck').attr('data-stockNum');
            var pkg = $('#J_dialogGoodsNew .isCheck').attr('data-pkg');
            var spec = $('#J_dialogGoodsNew .isCheck').attr('data-spec');
            var mdseId = $('#J_dialogGoodsNew .isCheck').attr('data-mdseId');
            var name = $('#J_dialogGoodsNew .isCheck').attr('data-name');

            $('#stockForm .isCheck').find('input[name="itemBaseIds"]').val(itemBaseId);
            $('#stockForm .isCheck .mdseId').val(mdseId);
            $('#stockForm .isCheck').find('input[name="quantitys"]').attr('data-stockNum', stockNum);
            $('#stockForm .isCheck .name').html(name);
            //        $('#stockForm .isCheck .spec').html(spec);

            $('.dialog,.cover-all').fadeOut();
        }).on('click', 'tr', function() {
            $('#J_dialogGoodsNew tr').attr('class', '');
            $(this).attr('class', 'isCheck');
        }).on('click', '.dialog-cancel', function() {
            $('.dialog').fadeOut();
        }).on('click', '.dialog-close', function() {
            $('.dialog').fadeOut();
        });
    });
    //增加一行
    $('#lastTr').find('.button').click(function() {
        var html = '<tr class="detail">';
        html += '<td class="count">' + ($('.count').length+1) + '</td>';
        html += '<td class="type">';
        html += $('.typeDemo').html();
        html += '</td>';
        html += '<td class="cash">';
        html += $('.cashDemo').html();
        html += '</td>';
        html += '<td>';
        html += '<input type="text" class="input input-search-date mdseId"  disabled="disabled"/>';
        html += '<input type="hidden" name="itemBaseIds"/>';
        html += '<input type="hidden" name="ids"/>';
        html += '<input type="button" class="button button-operate J_addNew" value="..." />';
        html += '</td>';
        html += '<td class="name"></td>';
        html += '<td><input type="text" class="input input-search-date" name="quantitys"/></td>';
        html += '<td><input type="text" class="input input-search-date" name="cashQuantitys"/></td>';
        html += '<td><input type="text" class="input input-search-date" name="cashPrices"/></td>';
        html += '<td><input type="text" class="input input-default" name="remarks"/></td>';
        html += '<td><input type="button" class="button button-operate J_del" value="删除" /></td>';
        html += '</tr>';
        $(this).parent().parent().before(html);
        $('select[name="types"]').trigger('change');
    });
</script>

</html>
