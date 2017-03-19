<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>广告添加</title>
    <%@ include file="../common/head.jsp"%>
    <script src="${root }/resources/vendor/My97DatePicker/WdatePicker.js"></script>
    <%@ include file="../common/ztree.jsp"%>
    <script src="${root }/resources/js/comm.js"></script>
    <script src="${root }/resources/js/ztree-search.js"></script>
</head>
<body>
<div class="wrap-bd">
    <div>
        位置：<a class="crumb" href="javascript: void(0)">广告管理</a><a href="list.html" class="crumb">广告列表</a><a class="crumb crumb-active">新增广告</a>
    </div>
    <div class="wrap-bd bg border mt-small">
        <form action="">
            <div>
                <label class="label">广告名称：</label>
                <input type="text" name="name" class="input input-default">
            </div>
            <p>
                <label class="label">广告描述：</label>
                <input type="text" name="name" class="input input-default">
            </p>
            <p>
                <label class="label">有效时间：</label>
                <input type="text" name="dateStart" id="dateStart" class="input input-date"> -
                <input type="text" name="dateEnd" id="dateEnd" class="input input-date">
            </p>
            <p>
                <label class="label">排序：</label>
                <input type="text" name="name" class="input input-date">
            </p>
            <p>
                <label class="label">广告类型：</label>
                <select name="boardid" id="" class="select">
                    <option value="" selected="selected" data-groupname="adTpye" data-tab="item">普通广告</option>
                    <option value="" data-groupname="adTpye" data-tab="item">商品广告</option>
                    <option value="" data-groupname="adTpye" data-tab="item">品牌广告</option>
                </select>
            </p>
            <div data-groupname="adTpye" data-tab="content"></div>
            <div data-groupname="adTpye" data-tab="content">
                <label class="label">选择商品：</label>
                <div class="dis-ib" style="width: 800px;">
                    <div class="button button-operate" id="J_btnGoods">+增加商品</div>
                    <table class="table-list mt-small">
                        <thead>
                            <tr>
                                <th>商品条码</th>
                                <th>商品名称</th>
                                <th>规格</th>
                                <th>价格</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>1234567891230</td>
                                <td>商品商品商品名称</td>
                                <td>24*300ml</td>
                                <td>110.23</td>
                                <td>删除</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div data-groupname="adTpye" data-tab="content">
                <label class="label">选择品牌：</label>
                <div class="dis-ib" style="width: 800px;">
                    <div class="button button-operate" id="J_btnBrand">+选择品牌</div>
                    <table class="table-list mt-small">
                        <thead>
                            <tr>
                                <th>商品编号</th>
                                <th>品牌名称</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>1234567891230</td>
                                <td>农夫山泉</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <p>
                <label class="label">广告位：</label>
                <select name="boardid" id="" class="select">
                    <option value="" selected="selected">请选择</option>
                    <option value="">APP启动页</option>
                    <option value="">APP首页</option>
                </select>
            </p>
            <p>
                <label class="label">广告图：</label>
                <input type="text" name="name" class="input input-default" style="width:420px;">
                <span>（图片地址）</span>
            </p>
            <p>
                <label class="label">点击效果：</label>
                <select name="" id="" class="select">
                    <option value="" selected="selected" data-groupname="adClick" data-tab="item">点击无反应</option>
                    <option value="" data-groupname="adClick" data-tab="item">打开功能</option>
                    <option value="" data-groupname="adClick" data-tab="item">打开广告详情页</option>
                </select>
            </p>
            <p data-groupname="adClick" data-tab="content"></p>
            <p data-groupname="adClick" data-tab="content">
                <label class="label">打开功能：</label>
                <select name="" id="" class="select">
                    <option value="" selected="selected" data-groupname="adCategory" data-tab="item">品牌专区</option>
                    <option value="" data-groupname="adCategory" data-tab="item">促销专区</option>
                    <option value="" data-groupname="adCategory" data-tab="item">商品分类</option>
                </select>
                <span data-groupname="adCategory" data-tab="content"></span>
                <span data-groupname="adCategory" data-tab="content"></span>
                <span data-groupname="adCategory" data-tab="content">
                    <select class="select" name="classId" id="cateId">
                        <option value="-1">不选择</option>
                        <option value="428">碳酸饮料</option>
                        <option value="429">饮用水</option>
                    </select>
                </span>
            </p>
            <p data-groupname="adClick" data-tab="content">
                <label class="label">打开地址：</label>
                <input type="text" name="name" class="input input-default" style="width:420px;">
            </p>
            <div>
                <label class="label va-t">广告内容：</label>
                <div class="dis-ib" style="width: 800px;">
                    <script id="ueditorContent" name="ueditorContent" type="text/plain"></script>
                    <script src="../../resources/vendor/ueditor/ueditor.config.js"></script>
                    <script src="../../resources/vendor/ueditor/ueditor.all.min.js"></script>
                    <script>
                        var ue = UE.getEditor('ueditorContent');
                        ue.addListener('ready', function () {
                            ue.setContent('${ad.content}');
                        });
                    </script>
                </div>
            </div>
            <div class="mt-default">
                <label class="label">参与区域：</label>
                <div class="dis-ib" style="width: 800px;">
                    <div class="button button-operate" id="J_btnSpgroup">+增加定格区域</div>
                    <div class="dis-ib clearfix">
                        <table class="table-list fl mt-small mr-small" style="width: 395px;">
                            <thead>
                                <tr>
                                    <th>定格名称</th>
                                    <th>定格分区</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>南山科苑片区</td>
                                    <td>南山区</td>
                                    <td>删除</td>
                                </tr>
                            </tbody>
                        </table>
                        <table class="table-list fl mt-small mr-small" style="width: 395px;">
                            <thead>
                                <tr>
                                    <th>定格名称</th>
                                    <th>定格分区</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>南山科苑片区</td>
                                    <td>南山区</td>
                                    <td>删除</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <p>
                <label class="label"></label>
                <input type="button" name="" value="提交" class="button button-ok">
                <input type="button" name="" value="取消" class="button button-cancel">
            </p>
        </form>
    </div>
</div>
<!-- 选择商品 -->
<div class="dialog hidden" id="J_dialogGoods">
    <div class="dialog-head">
        选择商品
        <div class="dialog-close"></div>
    </div>
    <div class="dialog-body">
        <div>
            <label class="label">商品分类：</label>
            <span style="display:inline-block;width: 280px">
                <select class="select">
                    <option>一级分类</option>
                </select>
                <select class="select">
                    <option>二级分类</option>
                </select>
            </span>
            <label class="label">商品品牌：</label>
            <input type="text" name="" class="input input-search-text">
        </div>
        <p>
            <label class="label">商品条码：</label>
            <span style="display:inline-block;width: 280px">
                <input type="text" name="" class="input input-search-text">
            </span>
            <label class="label">商品名称：</label>
            <input type="text" name="" class="input input-search-text">
        </p>
        <div class="ta-r">
            <input type="button" name="" value="查询" class="input input-search-button">
            <input type="button" name="" value="重置" class="input input-search-button">
        </div>
        <table class="table-list mt-small" style="width:712px;">
            <thead>
                <tr>
                    <th>
                        <input type="checkbox" name="" class="checkbox">
                    </th>
                    <th>商品品牌</th>
                    <th>商品条码</th>
                    <th>商品名称</th>
                    <th>规格</th>
                    <th>价格</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>
                        <input type="checkbox" name="" class="checkbox">
                    </td>
                    <td>百事</td>
                    <td>1234567891230</td>
                    <td>商品商品商品名</td>
                    <td>24*300ml</td>
                    <td>110.23</td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="dialog-foot">
        <button class="dialog-button dialog-ok">增加</button>
        <button class="dialog-button dialog-cancel">关闭</button>
    </div>
</div>
<!-- 选择品牌 -->
<div class="dialog hidden" id="J_dialogBrand">
    <div class="dialog-head">
        选择品牌
        <div class="dialog-close"></div>
    </div>
    <div class="dialog-body">
        <div class="ta-c">
            <input type="text" name="" placeholder="商品品牌名称" class="input input-search-text">
            <input type="button" name="" value="查询" class="input input-search-button">
        </div>
        <table class="table-list mt-default">
            <thead>
                <tr>
                    <th>
                        <input type="checkbox" name="" class="checkbox">
                    </th>
                    <th>品牌编号</th>
                    <th>品牌名称</th>
                    <th>品牌商</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>
                        <input type="checkbox" name="" class="checkbox">
                    </td>
                    <td>052464652356</td>
                    <td>百事名称</td>
                    <td>百事品牌商</td>
                </tr>
                <tr>
                    <td>
                        <input type="checkbox" name="" class="checkbox">
                    </td>
                    <td>052464652356</td>
                    <td>百事名称</td>
                    <td>百事品牌商</td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="dialog-foot">
        <button class="dialog-button dialog-ok">确定</button>
        <button class="dialog-button dialog-cancel">取消</button>
    </div>
</div>
<!-- 选择定格 -->
<div class="dialog hidden" id="J_dialogSpgroup">
    <div class="dialog-head">
        选择定格区域
        <div class="dialog-close"></div>
    </div>
    <div class="dialog-body">
        <div>
            <input type="text" name="search-condition" id="search-condition" class="input" style="width:422px">
            <input type="button" name="" value="搜索" onclick="search_ztree('ztree', 'search-condition')" class="input input-search-button">
        </div>
        <div class="mt-small limit-height" style="width:500px">
            <div id="ztree" class="ztree">
            	<ul id="treeDemo" class="ztree" style="height:140px; overflow:auto"></ul>
            </div>
        </div>
    </div>
    <div class="dialog-foot">
        <button class="dialog-button dialog-ok">确定</button>
        <button class="dialog-button dialog-cancel">取消</button>
    </div>
</div>
<div class="cover-all"></div>
<script>
    $(function(){
        dateRange('#dateStart', '#dateEnd', 1);

        tab('adTpye');
        $('[data-groupname="adTpye"]').parents('select').on('change', function() {
            var ii = $(this).get(0).selectedIndex;
            $(this).find('option:selected').trigger('click');
        });
        tab('adCategory');
        $('[data-groupname="adCategory"]').parents('select').on('change', function() {
            var ii = $(this).get(0).selectedIndex;
            $(this).find('option:selected').trigger('click');
        });
        tab('adClick');
        $('[data-groupname="adClick"]').parents('select').on('change', function() {
            var ii = $(this).get(0).selectedIndex;
            $(this).find('option:selected').trigger('click');
        });

       /* 选择商品 */
       $('#J_btnGoods').on('click', function() {
           dialogPosCenter('#J_dialogGoods');
           $('#J_dialogGoods, .cover-all').show();
       });
       $('#J_dialogGoods').on('click', '.dialog-cancel, .dialog-close', function() {
           $('#J_dialogGoods, .cover-all').hide();
       });
       $('#J_dialogGoods').on('click', '.dialog-ok', function() {
            // todo
       });

       /* 选择品牌 */
       $('#J_btnBrand').on('click', function() {
           dialogPosCenter('#J_dialogBrand');
           $('#J_dialogBrand, .cover-all').show();
       });
       $('#J_dialogBrand').on('click', '.dialog-cancel, .dialog-close', function() {
           $('#J_dialogBrand, .cover-all').hide();
       });
       $('#J_dialogBrand').on('click', '.dialog-ok', function() {
            // todo
           $('#J_dialogBrand, .cover-all').hide();
       });

       /* 选择定格 */
        var zTreeObj;
    	var setting = {
    			view: {
    				showLine: false
    			},
    			data: {
    				key: {
    					children: "regionList",
    				},
    				simpleData: {
    					enable: true
    				}
    			}
    		};
        var zNodes = [
            {id:1, pId:0, name: "转角街坊"},
            {id:2, pId:1, name: "子节点1"},
            {id:3, pId:2, name: "子节点11子节点节点11"},
            {id:12, pId:1, name: "子节点2"}
        ];
        function showIconForTree(treeId, treeNode) {
            return !treeNode.isParent;
        };
        function onCheck(e, treeId, treeNode) {
            console.log(treeNode.name);
        }

       $('#J_btnSpgroup').on('click', function() {
            zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            zTreeObj.expandNode(zTreeObj.getNodeByParam("id", 1, null));
           dialogPosCenter('#J_dialogSpgroup');
           $('#J_dialogSpgroup, .cover-all').show();
       });
       $('#J_dialogSpgroup').on('click', '.dialog-cancel, .dialog-close', function() {
           $('#J_dialogSpgroup, .cover-all').hide();
       });
       $('#J_dialogSpgroup').on('click', '.dialog-ok', function() {
            // todo
           $('#J_dialogSpgroup, .cover-all').hide();
       });
    });
</script>
</body>
</html>
