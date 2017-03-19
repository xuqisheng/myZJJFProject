<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common/taglib.jsp" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title id="realTitle">添加优惠券</title>
    <%@ include file="../common/head.jsp" %>

    <script src="${root}/resources/js/comm.js"></script>
    <style>
        .goods {
            float: left;
        }

        .goods-op {
            float: left;
            padding-top: 160px;
            width: 60px;
            text-align: center;
        }

        .goods-selected {
            float: left;
        }

        .box {
            box-sizing: border-box;
            border: 1px solid #ccc;
            padding: 8px;
            width: 330px;
            height: 350px;
            overflow: auto;

        }

        .box-item {
            line-height: 22px;
        }

        .J_itemBaseDelete {
            color: red;
        }

        .error {
            color: red;
        }

        .hidden {
            display: none;
        }
    </style>
</head>
<body class="wrap-bd">
<div class="mb-default clearfix">
    <b class="fl" id="title">添加优惠券</b>
    <a class="fr" href="${root}/Customer/voucher/toIndex.do?pageIndex=${pageIndex}">返回优惠券列表</a>
</div>
<div>
<div class="wrap-bd bg table-border">
<form id="addForm">
<div class="required">
    <label class="label">名称：</label>
    <input type="text" name="ruleName" class="input input-default" placeholder="最长不超过10个字" maxlength="10" id="ruleName"
           value="${spVoucherRule.ruleName}">
    <span id="ruleNameError"></span>
</div>
<p class="required">
    <label class="label">面值：</label>
    <input type="text" name="useMoney" class="input input-default" id="useMoney" value="${useMoney}"> 元
    <span id="useMoneyError"></span>
</p>
<p class="required">
    <label class="label">有效期：</label>
    <input type="text" name="useDay" class="input input-default" id="useDay" value="${spVoucherRule.useDay}"> 天
    <span id="useDayError"></span>
</p>
<p>
    <label class="label">短信内容：</label>
    <textarea name="ruleMsg" id="ruleMsg" cols="43" rows="3" class="textarea textarea-default"
              maxlength="60">${spVoucherRule.ruleMsg}</textarea>
    还可以输入<span class="txt-warn" id="ruleMsgError">60</span>字
</p>
<p class="required">
    <label class="label">说明：</label>
    <input type="text" name="ruleRemark" class="input input-default" maxlength="12" id="ruleRemark"
           value="${spVoucherRule.ruleRemark}">
    还可以输入<span class="txt-warn" id="ruleRemarkError">12</span>字
</p>
<p>
    <label class="label">支付方式：</label>
    <input type="checkbox" value="5" name="payTypeMethod"> 钱包支付
    <input type="checkbox" value="4" name="payTypeMethod"> 微信支付
    <input type="checkbox" value="3" name="payTypeMethod"> 支付宝支付
    <input type="checkbox" value="2" name="payTypeMethod"> 货到付款
    <span id="payTypeMethodError"></span>
</p>
<p>
    <label class="label">使用条件：</label>
    <input type="radio" value="0" name="startPriceRadio" checked="checked">
    单笔满
    <input type="text" style="width: 80px" name="startPrice" id="startPrice" value="${startPrice}">
    <span id="startPriceError"></span>
    元使用
    <input class="ml-default" type="radio" value="1" name="startPriceRadio"> 不限制
</p>
<p>
<label class="label" id="sendTimeLabel">送达时间:</label>
    <input type="radio" value="-1" name="transportTimeType" <c:if test="${empty spVoucherRule || spVoucherRule.transportTimeType==-1}">checked="checked"</c:if>>不限制&nbsp;&nbsp;&nbsp;
    <c:forEach var="item" items="${sendTimeConfigs}">
    <input type="radio" value="${item.id}" name="transportTimeType" <c:if test="${not empty spVoucherRule && spVoucherRule.transportTimeType==item.id}">checked="checked"</c:if>>${item.name}&nbsp;&nbsp;&nbsp;
    </c:forEach>

<%--<input type="radio" value="-1" name="transportTimeType" <c:if test="${empty spVoucherRule || spVoucherRule.transportTimeType==-1}">checked="checked"</c:if>>不限制&nbsp;&nbsp;&nbsp;
<input type="radio" value="0" name="transportTimeType" <c:if test="${spVoucherRule.transportTimeType==0}">checked="checked"</c:if>>24小时&nbsp;&nbsp;&nbsp;
    <input type="radio" value="1" name="transportTimeType" <c:if test="${spVoucherRule.transportTimeType==1}">checked="checked"</c:if>>48小时&nbsp;&nbsp;&nbsp;
    <input type="radio" value="2" name="transportTimeType" <c:if test="${spVoucherRule.transportTimeType==2}">checked="checked"</c:if>>72小时&nbsp;&nbsp;&nbsp;--%>
</p>
    <div>
        <label class="label">商品/类别：</label>
        <input type="radio" value="0" name="useItemFlag" checked="checked">&nbsp;所有商品&nbsp;
        <input type="radio" value="2" name="useItemFlag" class="ml-default">&nbsp;排除类别/商品&nbsp;
        <input type="radio" value="1" name="useItemFlag" class="ml-default">&nbsp;指定类别/商品&nbsp;
    </div>
    <div class="mt-default">
        <div data-groupname="tab" data-tab="content"></div>
        <!-- 排除 -->
        <div data-groupname="tab" data-tab="content" class="clearfix hidden" id="paichuDiv">
            <label class="label va-t fl">排除指定商品：</label>
            <div class="fl" style="width: 90%;">
                <table class="table-list table-border">
                    <thead class="table-thead">
                    <tr>
                        <th width="80">类型</th>
                        <th>类别/商品</th>
                        <th width="80"></th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody class="table-tbody" id="paichuTbody">
                    <tr>
                        <td colspan="4" class="ta-l">
                            <span class="button button-operate ml-default" id="paiChuAdd">增加</span>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- 指定 -->
        <div data-groupname="tab" data-tab="content" class="clearfix hidden" id="zhiDingDiv">
            <label class="label va-t fl">指定购买商品：</label>
            <div class="fl" style="width: 90%;">
                <table class="table-list table-border">
                    <thead class="table-thead">
                    <tr>
                        <th width="80">类型</th>
                        <th>类别/商品</th>
                        <th width="80"></th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody class="table-tbody" id="zhidingTbody">
                    <tr>
                        <td colspan="5" class="ta-l">
                            <span class="button button-operate ml-default" id="zhidingAdd">增加</span>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <p>
        <label class="label">备注：</label>
        <input type="text" name="remark" class="input input-default" value="${remark}">
    </p>
    <p>
        <label class="label"></label>
        <input type="button" class="button button-ok" value="确定" id="saveButton">
        <input type="button" class="button button-cancel" value="取消" id="cancel">
    </p>
    <input type="hidden" name="id" value="${id}" id="tempId">
    </form>
    </div>
    </div>
    <!-- 选择类别/商品 -->
    <div class="cover-all"></div>
    <div class="dialog hidden" id="J_dialogAssignProduct">
        <div class="dialog-head">选择类别/商品</div>
        <div class="dialog-body">
            <div class="mb-default">
                <input type="text" id="search_condition" placeholder="商品类别/名称/条形码" class="input input-search-text"
                       style="width:520px;">
                <input type="button" class="input input-search-button"
                       onclick="search_ztree('ztree', 'search_condition')" value="搜索">
            </div>
            <div class="box-goods clearfix">
                <div class="goods fl">
                    <div class="mb-small">商品库</div>
                    <div class="box">
                        <%@ include file="../common/ztree.jsp" %>
                        <script src="${root}/resources/js/ztree-search.js"></script>
                    </div>
                </div>
                <div class="goods-op  fl">
                    <input type="button" value="&gt;" class="button button-white" id="J_moveRight">
                    <input type="button" value="&lt;" class="button button-white mt-default" id="J_moveLeft">
                </div>
                <div class="goods-selected  fl">
                    <div class="mb-small" id="shiFouCanyu">已选参与指定购买商品</div>
                    <div class="box" id="selectedDiv">
                        <!-- <div class="box-item">饮料/碳酸饮料</div>
                        <div class="box-item">可口可乐</div> -->
                    </div>
                </div>
            </div>
        </div>
        <div class="dialog-foot">
            <input type="button" class="dialog-button dialog-ok" value="确定" id="selectAddButton">
            <input type="button" class="dialog-button dialog-cancel" value="取消" id="selectCancelButton">
        </div>
    </div>
    <!-- 排除商品 -->
    <div class="dialog hidden" id="J_dialogRemoveGoods">
        <div class="dialog-head">排除商品</div>
        <div class="dialog-body">
            <div class="mb-default" id="paichuShanpgPingDiv">
                <input type="text" class="input input-search-text" style="width:520px;" id="searchKey">
                <input type="button" class="input input-search-button" value="搜索" id="otherSearch">
            </div>
            <input type="hidden" id="dataValue" value="" name="cateId">
            <input type="hidden" id="dataLevel" value="" name="level">
            <div style="max-width:598px;height:350px;overflow:auto">
                <table class="table-list table-border">
                    <thead class="table-thead">
                    <tr>
                        <th width="50">选择</th>
                        <th width="120">商品条码</th>
                        <th>商品名称</th>
                        <th width="50">规格</th>
                        <th width="50">单位</th>
                    </tr>
                    </thead>
                    <tbody class="table-tbody" id="selectTbody">
                    <!-- <tr>
                        <td>
                            <input type="checkbox">
                        </td>
                        <td>1234567890</td>
                        <td>百事可乐百事可乐百事可乐百事可乐百事可乐百事可乐百事可乐百事可乐百事可乐百事可乐</td>
                        <td>320ml</td>
                        <td>瓶</td>
                    </tr> -->
                    </tbody>
                </table>
            </div>
        </div>
        <div class="dialog-foot">
            <input type="button" class="dialog-button dialog-ok" value="确定" id="zhidignConfirm">
            <input type="button" class="dialog-button dialog-cancel" value="取消" id="zhidingCancel">
        </div>
    </div>
    <script>
        var zTreeObj;
        var setting = {
            view: {
                showLine: false,
                showIcon: showIconForTree,
                fontCss: setFontCss_ztree
            },
            data: {
                key: {
                    children: "catelogVoList",
                },
                simpleData: {
                    enable: true
                }
            },
            /* callback: {
             onCheck: onClick
             } */
            callback: {
                onClick: onClick
            }
        };
        var zNodes = '';
        function showIconForTree(treeId, treeNode) {
            return !treeNode.isParent;
        }
        ;
        /*  function onCheck(e, treeId, treeNode) {
         console.log(treeNode.name);
         } */
        function onClick(e, treeId, treeNode) {
            //console.log(treeNode.level);
            // console.log(treeNode.name);
            //console.log(zTreeObj.getSelectedNodes());
            /* $.each(zTreeObj.getSelectedNodes(),function(i,item){
             console.log(item.level);
             });  */
        }

        function searchItemBase(searchObject) {
            var level = searchObject.level;
            var cateId = searchObject.cateId;
            var searchKey = searchObject.searchKey;
            $.ajax({
                type: "post",
                url: '${root}/customer/itemBases/getPaiChuItemBase.do',
                data: {'level': level, 'cateId': cateId, 'searchKey': searchKey},
                dataType: "json",
                success: function (da) {
                    if (da.success) {
                        var html = '';
                        $(da.message).each(function (i, item) {
                            html += '<tr>'
                                + '<input type="hidden" value="' + item.id + '">'
                                + '<td><input type="checkbox" class="J_zhidingCheckBox"></td>'
                                + '<td>' + item.mdseId + '</td>'
                                + '<td>' + item.name + '</td>';
                            if (item.spec == null || item.spec == "" || item.spec == "null") {
                                html += '<td></td>';
                            } else {
                                html += '<td>' + item.spec + '</td>';
                            }
                            if (item.pkg == null || item.pkg == "" || item.pkg == "null") {
                                html += '<td></td>';
                            } else {
                                html += '<td>' + item.pkg + '</td>';
                            }
                            '</tr>';
                        });
                        $('#selectTbody').html(html);
                    } else {
                        layer.msg("搜索商品失败!");
                        console.log(da.message);
                    }
                },
                error: function (data) {
                }
            });
        }

        $(function () {

            //获取树形商品数据
            $.ajax({
                type: "post",
                url: '${root}/customer/itemCatelog/getTreeItemCatelog.do',
                dataType: "json",
                success: function (da) {
                    if (da.success) {
                        zNodes = da.message;
                        zTreeObj = $.fn.zTree.init($("#ztree"), setting, zNodes);
                    } else {
                        layer.msg('获取树形商品数据出错!');
                        console.log(da.message);
                    }
                },
                error: function (data) {
                }
            });

            //tab('tab');
            dialogPosCenter('#J_dialogAssignProduct');
            dialogPosCenter('#J_dialogRemoveGoods');

            /**************** 指定类别/商品begin  *************************************/
            //指定增加按钮
            $('#zhidingAdd').on('click', function () {
                zTreeObj = $.fn.zTree.init($("#ztree"), setting, zNodes);
                $('#selectedDiv').empty();
                $('#shiFouCanyu').text('已选参与指定购买商品');
                $('#search_condition').val('');
                $('#J_dialogAssignProduct').removeClass('hidden');
                $('.cover-all').fadeIn();
            });
            //右移按钮
            $('#J_moveRight').on('click', function (i, item) {
                var html = '';
                $.each(zTreeObj.getSelectedNodes(), function (i, item) {
                    var isAdd = true;
                    var $htmlObject = $('#selectedDiv').find('div input[name="catId"][value="' + item.id + '"]');
                    if ($htmlObject.length > 0) {
                        var thisLevel = $htmlObject.prev().val();
                        if (thisLevel == item.treeLevel) {
                            isAdd = false;
                        }
                    }
                    if (isAdd) {
                        if (item.treeLevel == 1) {
                            html += '<div class="box-item" data-value="cate">'
                                + '<input type="hidden" name="level" value="' + item.treeLevel + '">'
                                + '<input type="hidden" name="catId" value="' + item.id + '">'
                                + item.name + '</div>';
                        } else if (item.treeLevel == 2) {
                            var parent = item.getParentNode();
                            html += '<div class="box-item" data-value="cate">'
                                + '<input type="hidden" name="level" value="' + item.treeLevel + '">'
                                + '<input type="hidden" name="catId" value="' + item.id + '">'
                                + parent.name + '/' + item.name
                                + '</div>';
                        } else if (item.treeLevel == 3) {
                            html += '<div class="box-item" data-value="itemBase">'
                                + '<input type="hidden" name="level" value="' + item.treeLevel + '">'
                                + '<input type="hidden" name="catId" value="' + item.id + '">'
                                + item.name + '</div>';
                        }
                    }
                });
                $('#selectedDiv').append(html);
            });
            $('#selectedDiv').on('click', '.box-item', function () {
                if ($(this).hasClass('J_itemBaseDelete')) {
                    $(this).removeClass('J_itemBaseDelete');
                } else {
                    $(this).addClass('J_itemBaseDelete');
                }
            })
            //左移按钮
            $('#J_moveLeft').on('click', function () {
                $('#selectedDiv .J_itemBaseDelete').each(function (i, item) {
                    $(this).remove();
                });
            });
            //确定按钮
            $('#selectAddButton').on('click', function () {
                var html = '';
                if ($('#shiFouCanyu').text() == '已选参与指定购买商品') {
                    $('#selectedDiv .box-item').each(function () {
                        var level = $(this).children('input').eq(0).val();
                        var catelogId = $(this).children('input').eq(1).val();
                        //判断是否有重复
                        if (level == 1 || level == 2) {
                            var length = $('#zhidingTbody').find('tr[data-value="cate' + catelogId + '"]').length
                            console.log(length);
                            if (length > 0) {
                                return;
                            }
                        } else if (level == 3) {
                            var length = $('#zhidingTbody').find('tr[data-value="itemBase' + catelogId + '"]').length
                            if (length > 0) {
                                return;
                            }
                        }
                        if (level == 1 || level == 2) {
                            html += '<tr   data-value="cate' + catelogId + '"  class="cate' + catelogId + '">';
                            html += '<td>类别</td>';
                        } else {
                            html += '<tr   data-value="itemBase' + catelogId + '">';
                            html += '<td>商品</td>';
                        }
                        html += '<td>' + $(this).text();
                        if (level == 1 || level == 2) {
                            html += '<input type="hidden" name="productArr" value="cat@' + catelogId + '@' + $(this).text() + '@0@' + level + '">';
                        } else {
                            html += '<input type="hidden" name="productArr" value="item@' + catelogId + '@' + $(this).text() + '@0">';
                        }
                        html += '</td>';
                        html += '<td width="80">参与</td>';
                        html += '<td width="160">';
                        if (level == 1 || level == 2) {
                            html += '<span class="button button-operate J_zhiDingPaichu">'
                                + '<input type="hidden" name="level" value="' + level + '">'
                                + '<input type="hidden" name="cateId" value="' + catelogId + '">排除商品</span>';
                        }
                        html += '<span class="button button-operate J_zhiDingDelete">删除</span>'
                            + '</td>'
                            + '</tr>';
                    });
                    $('#zhidingTbody').append(html);
                    $('#J_dialogAssignProduct').addClass('hidden');
                } else if ($('#shiFouCanyu').text() == '已选排除类别/商品') {
                    $('#selectedDiv .box-item').each(function () {
                        var level = $(this).children('input').eq(0).val();
                        var catelogId = $(this).children('input').eq(1).val();
                        //判断是否有重复
                        if (level == 1 || level == 2) {
                            var length = $('#paichuTbody').find('tr[data-value="cate' + catelogId + '"]').length
                            if (length > 0) {
                                return;
                            }
                        } else if (level == 3) {
                            var length = $('#paichuTbody').find('tr[data-value="itemBase' + catelogId + '"]').length
                            if (length > 0) {
                                return;
                            }
                        }
                        if (level == 1 || level == 2) {
                            html += '<tr   data-value="cate' + catelogId + '">';
                            html += '<td>类别</td>';
                        } else {
                            html += '<tr   data-value="itemBase' + catelogId + '">';
                            html += '<td>商品</td>';
                        }
                        html += '<td>' + $(this).text();
                        if (level == 1 || level == 2) {
                            html += '<input type="hidden" name="itemArr" value="cat@' + catelogId + '@' + $(this).text() + '"></td>';
                        } else {
                            html += '<input type="hidden" name="itemArr" value="item@' + catelogId + '@' + $(this).text() + '"></td>';
                        }
                        html += '<td width="80">不参与</td>';
                        html += '<td width="160">';
                        html += '<span class="button button-operate J_paiChuDelete">删除</span>'
                            + '</td>'
                            + '</tr>';
                    });
                    $('#paichuTbody').append(html);
                    $('#J_dialogAssignProduct').addClass('hidden');
                }
                $('.cover-all').fadeOut();
            });
            //取消按钮
            $('#selectCancelButton').on('click', function () {
                $('#J_dialogAssignProduct').addClass('hidden');
                $('.cover-all').fadeOut()
            });

            //指定类别/商品,排除商品按钮
            $('#zhidingTbody').on('click', '.J_zhiDingPaichu', function () {
                $('#searchKey').val("");
                var level = $(this).children().eq(0).val();
                var cateId = $(this).children().eq(1).val();
                $('#dataValue').val(cateId);
                $('#dataLevel').val(level);
                var searchObject = {"level": level, "cateId": cateId, "searchKey": ""};
                searchItemBase(searchObject);
                $('#J_dialogRemoveGoods').removeClass('hidden');
            });
            //搜索
            $('#otherSearch').on('click', function () {
                var level = $('#dataLevel').val();
                var cateId = $('#dataValue').val();
                var searchKey = $.trim($('#searchKey').val());
                var searchObject = {"level": level, "cateId": cateId, "searchKey": searchKey};
                searchItemBase(searchObject);
            })

            //指定商品 删除按钮
            $('#zhidingTbody').on('click', '.J_zhiDingDelete', function () {
                var isCate = $(this).parent('td').parent('tr').attr('data-value');
                if (isCate.indexOf('cate') == 0) {
                    var classStr = $(this).parent('td').parent('tr').attr('class');
                    $('#zhidingTbody').find('tr.' + classStr + '').remove();
                    $(this).parent('td').parent('tr').remove();
                } else {
                    $(this).parent('td').parent('tr').remove();
                }
            });

            //排除商品/类别 删除按钮
            $('#paichuTbody').on('click', '.J_paiChuDelete', function () {
                $(this).parent('td').parent('tr').remove();
            });

            //选择排除商品 确定按钮
            $('#zhidignConfirm').on('click', function () {
                if ($('#selectTbody .J_zhidingCheckBox:checked').length == 0) {
                    layer.msg('请选择要排除的商品！');
                    return;
                }
                var html = '';
                $('#selectTbody .J_zhidingCheckBox').each(function (i, item) {
                    if ($(this).prop('checked') == true) {
                        var name = $(this).parent('td').parent('tr').children().eq(3).text();
                        var id = $(this).parent('td').prev('input').val();
                        if ($('#zhidingTbody').find('tr[data-value="itemBase' + id + '"]').length == 0) {
                            html += '<tr data-value="itemBase' + id + '" class="cate' + $('#dataValue').val() + '">'
                                + '<td></td>';
                            html += '<td>' + name + '<input type="hidden" name="productArr" value="item@' + id + '@' + name + '@1@cate' + $('#dataValue').val() + '"></td>'
                                + '<td width="80">不参与</td>'
                                + '<td width="160">'
                                + '<span class="button button-operate J_zhiDingDelete">删除</span>'
                                + '</td>'
                                + '</tr>';
                        }
                    }
                });
                var dataValue = $('#dataValue').val();
                $('#zhidingTbody').find('tr[data-value="cate' + dataValue + '"]').after(html);
                $('#J_dialogRemoveGoods').addClass('hidden');

            });

            //取消按钮
            $('#zhidingCancel').on('click', function () {
                $('#selectTbody').empty();
                $('#J_dialogRemoveGoods').addClass('hidden')
            });
            /**************** 指定类别/商品end  *************************************/

            /**************** 排除类别/商品begin******************************************/
            //增加按钮
            $('#paiChuAdd').on('click', function () {
                zTreeObj = $.fn.zTree.init($("#ztree"), setting, zNodes);
                $('#selectedDiv').empty();
                $('#shiFouCanyu').text('已选排除类别/商品');
                $('#search_condition').val('');
                $('#J_dialogAssignProduct').removeClass('hidden');
                $('.cover-all').fadeIn()
            });

            /**************** 排除类别/商品end******************************************/


                //商品类别切换
            var $useItemFlagRadio = $('input[type="radio"][name="useItemFlag"]');
            $useItemFlagRadio.click(function () {
                if ($(this).val() == 0) {
                    $('#paichuDiv').addClass('hidden');//排除商品
                    $('#zhiDingDiv').addClass('hidden');//指定商品
                } else if ($(this).val() == 2) {
                    $('#zhiDingDiv').addClass('hidden');//指定商品
                    $('#paichuDiv').removeClass('hidden');//排除商品
                } else {
                    $('#paichuDiv').addClass('hidden');//排除商品
                    $('#zhiDingDiv').removeClass('hidden');//指定商品
                }
            });


            //短信字数限制
            $('#ruleMsg').on('keyup', function () {
                var limtNum = 60;
                console.log($('#ruleMsg').val().length);
                var limit = limtNum - $('#ruleMsg').val().length;
                $('#ruleMsgError').html(limit);
            });
            //说明字数限制
            $('#ruleRemark').on('keyup', function () {
                var limtNum = 12;
                var limit = limtNum - $('#ruleRemark').val().length;
                $('#ruleRemarkError').html(limit);
            });

            //取消按钮
            $('#cancel').click(function () {
                location.href = '${root}/Customer/voucher/toIndex.do?pageIndex=${pageIndex}';
            });

            //金额限制--不限制功能按钮
            $('input[type="radio"][name="startPriceRadio"][value="1"]').click(function () {
                $('#startPriceError').removeClass('error');
                $('#startPriceError').text('');
            });

            //校验名字
            $('#ruleName').blur(function () {
                if ($.trim($('#ruleName').val()) == "") {
                    $('#ruleNameError').addClass("error");
                    $('#ruleNameError').text('名字不能为空!');
                } else {
                    $('#ruleNameError').removeClass('error');
                    $('#ruleNameError').text('');
                }
            });
            //校验面值
            $('#useMoney').blur(function () {
                var userMoney = $('#useMoney').val();
                if ($.trim(userMoney) == '') {
                    $('#useMoneyError').addClass('error');
                    $('#useMoneyError').text('金额不能为空!');
                    return;
                } else {
                    $('#useMoneyError').removeClass('error');
                    $('#useMoneyError').text('');
                }
                var isNum = /^[1-9]*[1-9][0-9]{0,6}$/;
                if (!isNum.test(userMoney)) {
                    $('#useMoneyError').addClass('error');
                    $('#useMoneyError').text('金额只能为从1开始的正整数!');
                    return;
                } else if (userMoney.length > 7) {
                    $('#useMoneyError').addClass('error');
                    $('#useMoneyError').text('金额长度不能超过7位有效数!');
                    return;
                } else {
                    $('#useMoneyError').removeClass('error');
                    $('#useMoneyError').text('');
                }
            });
            //校验优惠劵有效期 长度的校验
            $('#useDay').blur(function () {
                var useDay = $('#useDay').val();
                if ($.trim(useDay) == '') {
                    $('#useDayError').addClass('error');
                    $('#useDayError').text('优惠劵有效期不能为空!');
                    return;
                } else {
                    $('#useDayError').removeClass('error');
                    $('#useDayError').text('');
                }
                var isNum = /^[1-9][0-9]{0,2}$/;
                if (!isNum.test(useDay)) {
                    $('#useDayError').addClass('error');
                    $('#useDayError').text('有效期只能为正整数且不能大于127');
                    return;
                } else if (useDay > 127) {
                    $('#useDayError').addClass('error');
                    $('#useDayError').text('最大值只能为127天!');
                    return;
                } else {
                    $('#useDayError').removeClass('error');
                    $('#useDayError').text('');
                }
            });
            //校验起送金额 长度的校验
            $('#startPrice').blur(function () {
                var startPrice = $('#startPrice').val();
                if ($.trim(startPrice) == '') {
                    $('#startPriceError').addClass('error');
                    $('#startPriceError').text('订单金额不能为空!');
                    return;
                } else {
                    $('#startPriceError').removeClass('error');
                    $('#startPriceError').text('');
                }
                var isNum = /^[1-9]*[1-9][0-9]*$/;
                if (!isNum.test(startPrice)) {
                    $('#startPriceError').addClass('error');
                    $('#startPriceError').text('订单金额只能为从1开始的正整数!');
                    return;
                } else {
                    $('#startPriceError').removeClass('error');
                    $('#startPriceError').text('');
                }
                //优惠劵面额不能大于起送金额
                if (parseInt($('#useMoney').val()) > parseInt($('#startPrice').val())) {
                    $('#startPriceError').addClass('error');
                    $('#startPriceError').text('订单金额不能小于优惠劵面值');
                    return;
                } else {
                    $('#startPriceError').removeClass('error');
                    $('#startPriceError').text('');
                }
            });
            //支付方式
            $('input[type="checkbox"][name="payTypeMethod"][value="0"]').click(function () {
                if ($('input[type="checkbox"][name="payTypeMethod"][value="0"]').is(':checked')) {
                    $('input[type="checkbox"][name="payTypeMethod"][value!="0"]').prop('checked', '');
                    $('#payTypeMethodError').removeClass('error');
                    $('#payTypeMethodError').text('');
                }
            });
            $('input[type="checkbox"][name="payTypeMethod"][value!="0"]').click(function () {
                $('input[type="checkbox"][name="payTypeMethod"][value="0"]').prop('checked', '');
                if ($(this).is(':checked')) {
                    $('#payTypeMethodError').removeClass('error');
                    $('#payTypeMethodError').text('');
                }
            });


            //取消按钮
            $('#cancel').click(function () {
                location.href = '${root}/Customer/voucher/toIndex.do?pageIndex=${pageIndex}';
            });

            //新增优惠劵 确定按钮
            $('#saveButton').click(function () {
                //表单校验
                $('#ruleName').trigger('blur');
                $('#useMoney').trigger('blur');
                $('#useDay').trigger('blur');

                //优惠劵面值不能大于订单金额限制
                if (parseInt($('#useMoney').val()) > parseInt($('#startPrice').val())) {
                    $('#startPriceError').addClass('error');
                    $('#startPriceError').text('订单金额不能小于优惠劵面值');
                } else {
                    $('#startPriceError').removeClass('error');
                    $('#startPriceError').text('');
                }

                //校验金额限制
                if ($('input[type="radio"][name="startPriceRadio"][value="0"]').is(':checked')) {
                    $('#startPrice').trigger('blur');
                }
                if ($('input[type="radio"][name="startPriceRadio"][value="1"]').is(':checked')) {
                    $('#startPriceError').removeClass('error');
                    $('#startPriceError').text('');
                }
                //校验支付方式
                if ($('input[type="checkbox"][name="payTypeMethod"]:checked').length == 0) {
                    $('#payTypeMethodError').addClass('error');
                    $('#payTypeMethodError').text('请选择至少一种支付方式!');
                }
                if ($('span.error').length > 0) {
                    return;
                }
                $('#saveButton').hide();
                $.ajax({
                    type: "POST",
                    url: "${root}/Customer/voucher/addSpVoucher.do?timestamp=" + (new Date()).valueOf(),
                    data: $("#addForm").serialize(),
                    dataType: 'json',
                    success: function (da) {
                        if (da.success) {
                            layer.msg("保存成功!", {time: 1000}, function () {
                                location.href = '${root}/Customer/voucher/toIndex.do';
                            });
                        } else {
                            layer.msg(da.message);
                            $('#saveButton').show();
                        }
                    },
                    error: function (da) {
                        layer.msg("失败的请求!");
                        $('#saveButton').show();
                    }
                });
            });

        });
    </script>

    <script type="text/javascript">
        /* 编辑 */
        var id = $('#tempId').val();
        if (id != "") {
            $('#realTitle').html('编辑优惠劵');
            $('#title').html('编辑优惠劵');
            var isEdit = '${isEdit}';
            if (isEdit == 0) {
                html = '<span class="txt-warn">优惠劵已经被使用，不能编辑</span>';
                $('#saveButton').after(html);
                $('#saveButton').remove();
            }
            //回显短信内容
            if ($.trim($('#ruleMsg').val()) != "") {
                $('#ruleMsgError').html(60 - $('#ruleMsg').val().length);//回显
            }
            //回显说明
            if ($.trim($('#ruleRemark').val()) != "") {
                $('#ruleRemarkError').html(12 - $('#ruleRemark').val().length);
            }
            //回显数据
            //回显支付方式
            var payType = "${payType}";
            var payStr = "${payStr}";
            if (payType == 0) {
                $('input[type="checkbox"][name="payTypeMethod"][value="0"]').prop('checked', 'checked');
            } else {
                $('input[type="checkbox"][name="payTypeMethod"][value="0"]').prop('checked', '');
                var payStrArr = new Array();
                payStrArr = payStr.substring(1, payStr.length - 1);
                for (var i = 0; i < payStrArr.length; i++) {
                    $('input[type="checkbox"][name="payTypeMethod"][value="' + payStrArr[i] + '"]').prop('checked', 'checked');
                }
            }
            ;
            //回显金额限制
            var startPriceStr = '${startPriceStr}';
            if (startPriceStr == 0) {
                $('input[type="radio"][name="startPriceRadio"][value="0"]').prop('checked', 'checked');
            } else {
                $('input[type="radio"][name="startPriceRadio"][value="1"]').prop('checked', 'checked');
            }
            //回显送达时间
            if($('input[type="radio"][name="transportTimeType"][value="${spVoucherRule.transportTimeType}"]').length==0){
                layer.msg('没有对应的配送时间选项!');
            }else{
                $('input[type="radio"][name="transportTimeType"][value="${spVoucherRule.transportTimeType}"]').attr('checked','checked');
            }


            //回显商品类别
            var goodsType = "${goodsType}";
            $('input[type="radio"][name="useItemFlag"][value="' + goodsType + '"]').prop('checked', 'checked');
            var mgItems = "${mgItems}";
            var arr = new Array();
            arr = mgItems.split(";");
            var html = '';
            var hiddenHtml = '';
            if (goodsType == 2) {
                $('#paichuDiv').removeClass('hidden');
                for (var i = 0; i < arr.length; i++) {
                    var tempArr = new Array();
                    tempArr = arr[i].split("@");
                    if (tempArr[0] == 'cat') {
                        html += '<tr data-value="cate' + tempArr[1] + ' class="cate' + tempArr[1] + '" ">';
                        html += '<td>类别</td>';
                    } else {
                        html += '<tr data-value="itemBase' + tempArr[1] + '">';
                        html += '<td>商品</td>'
                    }
                    html += '<td>' + tempArr[2] + '<input type="hidden" name="itemArr" value="' + arr[i] + '"></td>'
                        + '<td width="80">不参与</td>'
                        + '<td width="160"><span class="button button-operate J_paiChuDelete">删除</span></td>'
                        + '</tr>';
                }
                $('#paichuTbody').append(html);
            } else if (goodsType == 1) {
                $('#zhiDingDiv').removeClass('hidden');
                for (var i = 0; i < arr.length; i++) {
                    var tempArr = new Array();
                    tempArr = arr[i].split("@");
                    if (tempArr[0] == 'cat') {
                        html += '<tr data-value="cate' + tempArr[1] + '"  class="cate' + tempArr[1] + '">';
                        html += '<td>类别</td>';
                    } else {
                        if (tempArr.length == 5) {//表示有层级关系
                            html += '<tr data-value="itemBase' + tempArr[1] + '" class="' + tempArr[4] + '">';
                            html += '<td></td>';
                        } else {
                            html += '<tr data-value="itemBase' + tempArr[1] + '">';
                            html += '<td>商品</td>'
                        }
                    }
                    html += '<td>' + tempArr[2] + '<input type="hidden" name="productArr" value="' + arr[i] + '"></td>';
                    if (tempArr[3] == 0) {
                        html += '<td width="80">参与</td>';
                    } else {
                        html += '<td width="80">不参与</td>';
                    }
                    html += '<td width="160">';
                    if (tempArr[0] == 'cat') {
                        html += '<span class="button button-operate J_zhiDingPaichu"><input type="hidden" name="level" value="' + tempArr[4] + '"><input type="hidden" name="cateId" value="' + tempArr[1] + '">排除商品</span>';
                    }
                    html += '<span class="button button-operate J_zhiDingDelete">删除</span></td>'
                        + '</tr>';
                }
                $('#zhidingTbody').append(html);
            }
        }
    </script>
    </body>
    </html>
