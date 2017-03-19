<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<%
response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
response.addHeader("Cache-Control", "no-store"); //Firefox
response.setHeader("Pragma", "no-cache"); //HTTP 1.0
response.setDateHeader("Expires", -1);
response.setDateHeader("max-age", 0);
%>
<!DOCTYPE html>
<html lang="zh-CN">
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
        .J_itemBaseDelete{ color:red; }
        .error{ color:red; }
        .hidden{display:none}
        .hiddn{
            display:none;
        }
    </style>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-CompaMtible" content="IE=edge">
	<title>添加活动</title>
	<%@ include file="../common/head.jsp"%>
	<script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
    <script src="${root}/resources/js/comm.js"></script>
    <script src="${root}/resources/vendor/jquery/ajaxfileupload.js"></script>
</head>
<body class="wrap-bd">
<div class="mb-default">
    <a href="${root}/Customer/active/toIndex.do">返回活动列表</a>
</div>
<fieldset class="wrap-bd bg">
    <legend id="legend">活动管理</legend>
    <form id="addForm">
        <div class="required">
            <label class="label">名称：</label>
            <input class="input input-default" type="text" name="ruleName" value="" id="ruleName"/>&nbsp;
            <span id="ruleNameError" class="" style="color: red"></span>
        </div>
        <p>
            <label class="label">活动时间：</label>
            <input class="input input-date J_timeStart" type="text" name="ruleStart" value="${ruleStartStr}" id="ruleStart"/> -
            <input class="input input-date J_timeEnd" type="text" name="ruleEnd" value="${ruleEndStr}" id="ruleEnd"/>&nbsp;
            <span id="tiemStartError" class="" style="color: red"></span>
            <span id="tiemEndError" class="" style="color: red"></span>
        </p>
        <p>
            <label class="label">活动说明：</label>
            <textarea class="textarea textarea-default" cols="25" rows="3" name="ruleRemark" id="ruleRemark"></textarea>
        </p>
        <p>
            <label class="label">活动类型：</label>
            <select id="ruleTypeSelect" name="ruleType" class="select">
              <option value="1" selected="selected">注册送优惠劵</option>
              <option value="2">满送优惠劵</option>
              <option value="3">满减</option>
              <option value="9">满送优惠劵+商品</option>
              <option value="10">满减金额+商品</option>
              <option value="11">满送商品</option>
              <option value="12">满购商品送商品</option>
              <option value="13">提前下单送优惠劵</option>
              <option value="14">累积送劵</option>
            </select>
        </p>
        <p class="condition hidden" id="imgUrlP">
            <label class="label">活动图：</label>
            <input type="file" id="file" name="file" class="input input-default">
            <input id="up-img-to" type="button" class="send-img" value="上传图片"/>
            <input type="hidden" name="imgUrl" id="good_img" value="${imgUrl}"/>
            <span class="error-prompt" id="send-img"></span>
            <span>
                <img id="good-img-show" src="${showImgUrl}" width="55" height="55" alt="">
            <%-- <img id="goodImgShowBig" style="position:absolute;bottom:-20px;left:58px;border:2px solid #ccc;display:none;"  src="${USER_FASTFDSPREURL}${itemBase.imgS}" alt="">--%>
           </span>
        </p>
        <div class="hidden condition" id="spVoucherDiv">
        <!-- <input type="hidden" name="sendId" value="" id="sendIdInput"> -->
            <label class="label">优惠券：</label>
            <div style="width: 800px; display:inline-block">
	            <div>
	               <span class="button button-operate" id="addSpVoucher">+选择优惠券</span>
	            </div>
	            <table class="table-list table-border mt-small">
	                <thead class="table-thead">
	                    <tr>
		                    <th>名称</th>
		                    <th>面值</th>
		                    <th>有效期</th>
		                    <th>使用金额限制</th>
		                    <th>满多少送优惠劵</th>
		                    <th>每天限制张数</th>
		                    <th>操作</th>
	                    </tr>
	                </thead>
	                <tbody class="table-tbody" id="confirmSpVoucherTbody">
	                </tbody>
	            </table>
            </div>
        </div>
        <div class="mt-small hidden condition" id="buyGoodsDiv">
            <input type="hidden" value="" name="buyGoodsItemBaseId" id="buyGoodsItemBaseIdInput">
            <label class="label va-t">购满商品：</label>
            <div style="width: 800px; display:inline-block">
                <div>
                   <span class="button button-operate" id="addBuyGoods">+选择商品</span>
                </div>
                <p id="buyGoodsP">
                    <input type="text" readonly="readonly" class="input" id="seeBuyGoodsNameInput" name="buyGoodName">&nbsp;&nbsp;
                                                  购买数量：
                    <input type="text" class="input" name="buyGoodsNum" id="buyGoodsNumInput">
                    <!-- <span class="button button-operate">删除</span> -->
                </p>
            </div>
        </div>
        <div class="hidden condition  mt-default" style="overflow: hidden" id="transportTimeTypeP">
            <label class="label fl">送达时间：</label>
            <div id="transportTimeDiv" class="fl">


            </div>

            <%--<input type="radio" value="-1" name="transportTimeType">不限制&nbsp;&nbsp;&nbsp;
            <input type="radio" value="0" name="transportTimeType">24小时&nbsp;&nbsp;&nbsp;
            <input type="radio" value="1" name="transportTimeType">48小时&nbsp;&nbsp;&nbsp;
            <input type="radio" value="2" name="transportTimeType">72小时&nbsp;&nbsp;&nbsp;--%>
        </div>
        <p class="required hidden condition" id="ruleStartPriceP">
            <label class="label">活动规则：</label>
                              单笔订单满
            <input type="text" style="width: 80px" name="ruleStartPriceStr" id="ruleStartPrice" value="">
            <span class="" style="color: red;" id="startPriceError"></span>
                               元
        </p>
        <p id="ruleSendLimitP" class="hidden condition">
        	<label class="label">每天限制：</label>
			<input class="input input-date" type="text" name="ruleSendLimit" value="1" id="ruleSendLimitInput"> 张
			<span class="" style="color: red;" id="ruleSendLimitInputError"></span>
        </p>
        <p class="hidden condition" id="payStrP">
          <label class="label">支付方式:</label>
            <input type="checkbox" value="5" name="rulePayStrArr"> 钱包支付
            <input class="ml-default" type="checkbox" value="4" name="rulePayStrArr"> 微信支付
            <input class="ml-default" type="checkbox" value="3" name="rulePayStrArr"> 支付宝支付
            <input class="ml-default" type="checkbox" value="1" name="rulePayStrArr"> 快捷支付
            <input class="ml-default" type="checkbox" value="2" name="rulePayStrArr"> 货到付款
            <span id="checkboxPayError" class="" style="color: red"></span>
        </p>
        <div class="hidden condition" id="ruleContentDiv">
        <div class="clearfix">
	        <div class="fl">
	            <div class="mt-small mb-small">
					<label class="label">钱包支付：</label><!--payMethod=5  -->
					<input class="input input-default" type="text" name="qianbaoMethod" id="qianbaoMethod" />
			    </div>
				<div class="mt-small mb-small">
					<label class="label">微信支付：</label><!--payMethod=4  -->
					<input class="input input-default" type="text" name="weixingMethod" id="weixingMethod" />
			    </div>
				<div class="mt-small mb-small">
					<label class="label">货到付款：</label><!--payMethod=2  -->
					<input class="input input-default" type="text" name="huodaoMethod" id="huodaoMethod" />
				</div>
				<div class="mt-small mb-small">
					<label class="label">支付宝支付：</label><!--payMethod=3  -->
					<input class="input input-default" type="text" name="zhifubaoMethod" id="zhifubaoMethod" />
				</div>
				<div class="mt-small mb-small">
					<label class="label">快钱支付：</label><!--payMethod=1  -->
					<input class="input input-default" type="text" name="kuaiqianMethod" id="kuaiqianMethod" />
				</div>
			</div>
			<div class="fl" style="margin-left:12px;width:560px;font-size:14px;color:#aaa">
				<label>操作提示：</label><br>
				<span>* 如：满1000送50，请输入：<span style="color: #ee5f5f;">1000:50</span>，中间用“:”（英文符号）隔开；</span><br>
				<span>* 如果有几种档位促销，则输入如：<span style="color: #ee5f5f;">500:30;1000:50</span>，中间用“;”（英文符号）隔开，表示满500送30或者满1000送30，金额按从小到大顺序；</span><br>
				<span>* 如果某种支付方式没有优惠，则不填；</span>
			</div>
        </div>
        <div>
                    <label class="label">商品/类别：</label>
                    <input type="radio" value="0" name="useItemFlag" checked="checked">&nbsp;所有商品&nbsp;
                    <input type="radio" value="2" name="useItemFlag"  class="ml-default">&nbsp;排除类别/商品&nbsp;
                    <input type="radio" value="1" name="useItemFlag" class="ml-default">&nbsp;指定类别/商品&nbsp;
                </div>
                <div class="mt-default mb-default clearfix">
                    <div data-groupname="tab" data-tab="content"></div>
                    <!-- 排除 -->
                    <div data-groupname="tab" data-tab="content" class="hidden" id="paichuDiv">
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
                    <div data-groupname="tab" data-tab="content" class="hidden" id="zhiDingDiv">
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
                                    <td colspan="4" class="ta-l">
                                        <span class="button button-operate ml-default" id="zhidingAdd">增加</span>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- 排除商品 -->

                </div>
        </div>
        <div class="mt-small hidden condition" id="sendGoodsDiv">
            <label class="label va-t">赠送商品：</label>
            <div style="width: 800px; display:inline-block">
                <div>
                   <span class="button button-operate" id="addSendGoods">+选择商品</span>
                </div>
                <div id="addSendGoodsInDiv"></div>
                <!-- <p>
                    <input type="text" readonly="readonly" class="input">&nbsp;&nbsp;
                                                  购买数量：
                    <input type="text" class="input">&nbsp;&nbsp;
                                                  赠送总量：
                    <input type="text" class="input">
                    <span class="button button-operate">删除</span>
                </p> -->
            </div>
        </div>
        <p class="hidden condition" id="ruleScopFlagP">
            <label class="label">参与区域：</label>
            <select class="select" id="ruleScopFlagSelect" name="ruleScopFlag">
                <option value="0" selected="selected">全部定格区域</option>
                <option value="1">指定定格区域</option>
                <option value="2">批发商自愿参与</option>
                <option value="3">指定用户</option>
            </select>
        </p>
        <div class="mt-small hidden condition" id="spGroupDiv">
            <label class="label va-t">参与定格：</label>
            <div style="width: 800px; display:inline-block">
                <div>
                   <span class="button button-operate" id="addSpGroupSpan">+增加定格区域</span>
                </div>
                <table class="table-list table-border mt-small">
                    <thead class="table-thead">
                        <tr>
                            <th>定格名称</th>
                            <th>定格分区</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody class="table-tbody" id="addSpGroupTbody">
                    </tbody>
                </table>
            </div>
        </div>
        <div class="mt-default hidden condition" id="halveDiv">
	        <label class="label va-t">费用分摊：</label>
	        <div style="width: 800px; display: inline-block;">
	           <div class="required">
                  <label class="label">转角承担:</label>
	              <input class="input input-default" type="text" id="plantHalveInput" name="zjHalveStr"> %
               </div>
	           <div class="required mt-small">
                  <label class="label">批发商承担:</label>
	              <input class="input input-default" type="text" id="spHalveInput" name="pfHalveStr"> %
	           </div>
	        </div>
        </div>
        <div class="condition hidden" id="assignStoreP">
            <label class="label">参与用户：</label>
            <div style="display: inline-block" style="width: 80%">
                <button class="button button-operate" type="button" id="addPerson">+增加用户</button>
                <table class="table-list table-border mt-small">
                    <thead>
                    <tr>
                        <th>商铺名称</th>
                        <th>店主</th>
                        <th>手机号</th>
                        <th>所属定格</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="canyuStoreTbody">
                    <c:forEach items="${storeMgVos}" var="item">
                        <tr data-id="${item.id}">
                            <td>${item.name}</td>
                            <td>${item.contact}</td>
                            <td>${item.mobile}</td>
                            <td>${item.storeGroupName}</td>
                            <td><input type="button" value="删除" class="storeDel"></td>
                            <input type="hidden" name="storeIdArr" value="${item.id}">
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
             </div>
        </div>

	    <p>
	       <span id="saveButton" class="button button-ok">确定</span>
	       <span id="cancel" class="button button-cancel">取消</span>
	    </p>
	    <input type="hidden" name="id" value="" id="id">
    </form>
</fieldset>


<%--增加用户--%>
<div class="dialog hiddn"   id="J_dialogAddPerson">
    <div class="dialog-head">选择用户</div>
    <div class="dialog-body clearfix">
        <div class="mb-default">
            <label class="label">关键词:</label>
            <input type="text" class="input input-default" id="assignStoreKeyStr" name="keyStr">
        </div>
        <div class="mb-default">
            <label class="label">所属定格:</label>
            <select name="cityId" id="allCity" class="select mr-small">
                <option value="-1">全部城市</option>

            </select>
            <select name="areaId" id="allArea" class="select mr-small">
                <option value="-1">全部区域</option>

            </select>
            <select name="groupId" id="allSpGroup" class="select mr-small">
                <option value="-1">全部定格</option>
            </select>
            <input type="button" class="button button-default" value="搜索" id="assignStoreSearch">
        </div>

        <div style="height: 260px;overflow: auto;">
        <table class="table-list table-border mt-small">
            <thead>
            <tr>
                <th>
                    <input type="checkbox" class="checkbox" id="assignSelectAll">
                </th>
                <th>商铺名称</th>
                <th>店主</th>
                <th>手机号</th>
                <th>所属定格</th>
            </tr>
            </thead>
            <tbody id="assignStoreTbody">
                <%--<td>
                    <input type="checkbox">
                </td>
                <td>2</td>
                <td>3</td>
                <td>54</td>
                <td>5</td>--%>
            </tbody>
        </table>
        </div>
        <div id="jpagination1"></div>
    </div>
    <div class="dialog-foot">
        <input type="button" class="dialog-button dialog-ok" value="确定" id="assignStoreOK" >
        <input type="button" class="dialog-button dialog-cancel" value="取消" id="assignStoreCancel">
    </div>
</div>



 <!--选择类别/商品-->
    <div class="dialog hidden" id="J_dialogAssignProduct">
        <div class="dialog-head">选择类别/商品</div>
        <div class="dialog-body">
            <div class="mb-default">
                <input type="text" id="search_condition" placeholder="商品类别/名称/条形码" class="input input-search-text" style="width:520px;">
                <input type="button" class="input input-search-button" onclick="search_ztree('ztreeOne', 'search_condition')" value="搜索">
            </div>
            <div class="box-goods clearfix">
                <div class="goods">
                    <div class="mb-small">商品库</div>
                    <div class="box">
                        <%@ include file="../common/ztreeOne.jsp"%>
                        <script src="${root}/resources/js/ztree-search.js"></script>
                    </div>
                </div>
                <div class="goods-op">
                    <input type="button" value="&gt;" class="button button-white" id="J_moveRight">
                    <input type="button" value="&lt;" class="button button-white mt-default" id="J_moveLeft">
                </div>
                <div class="goods-selected">
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


<!-- 优惠券dialog -->
<div class="dialog hiddn"  id="addSpVoucherDiv">
    <div class="dialog-head">选择优惠券</div>
    <div class="dialog-body clearfix">
        <div>
            <input type="text" class="input" style="width:600px;" placeholder="优惠券名称" name="searchKey" id="spVoucherSearchKey">
            <input type="button" value="搜索" class="input input-search-button" id="addSpVoucherSearch">
        </div>
        <table class="table-list table-border mt-small" style="width:680px;">
            <thead>
                <tr>
                    <th width="280">名称</th>
                    <th>面值</th>
                    <th>有效期</th>
                    <th>使用金额限制</th>
                </tr>
            </thead>
            <tbody class="hover-select" id="spVoucherTbody">
            </tbody>
        </table>
        <%@ include file="../common/pagination.jsp"%>
    </div>
    <div class="dialog-foot">
        <button class="dialog-button dialog-ok" id="spVoucherOk">确认</button>
        <button class="dialog-button dialog-cancel" id="spVoucherCancel">取消</button>
    </div>
</div>
<!-- 购满商品dialog -->
<div class="dialog hidden" id="addBuyGoodsDiv">
    <div class="dialog-head">选择商品</div>
    <div class="dialog-body clearfix">
        <div>
            <input type="text" class="input" style="width:600px;" placeholder="商品名称" name="searchKey" id="addBuyGoodsSearchKey" value="">
            <input type="button" value="搜索" class="input input-search-button" id="addBuyGoodsSearch">
        </div>
        <table class="table-list table-border mt-small" style="width:680px;">
            <thead>
                <tr >
                    <th width="120">商品条码</th>
                    <th>商品名称</th>
                    <th width="100">规格</th>
                    <th width="30">单位</th>
                </tr>
            </thead>
            <tbody class="hover-select" id="buyGoodsTbody">
            </tbody>
        </table>
        <div id="jpagination_ItemBase"></div>
    </div>
    <div class="dialog-foot">
        <button class="dialog-button dialog-ok" id="confirmBuyGoods">确认</button>
        <button class="dialog-button dialog-cancel" id="buyGoodsCancel">取消</button>
    </div>
</div>
<!-- 赠送商品dialog -->
<div class="dialog hidden" id="addSendGoodsDiv">
    <div class="dialog-head">选择商品</div>
    <div class="dialog-body clearfix">
        <div>
            <input type="text" class="input" style="width:600px;" placeholder="商品名称" name="searchKey" id="addSendGoodsSearchKey" value="">
            <input type="button" value="搜索" class="input input-search-button" id="addSendGoodsSearch">
        </div>
        <table class="table-list table-border mt-small" style="width:680px;">
            <thead>
                <tr >
                    <th width="120">商品条码</th>
                    <th>商品名称</th>
                    <th width="100">规格</th>
                    <th width="30">单位</th>
                </tr>
            </thead>
            <tbody class="hover-select" id="sendGoodsTbody">
            </tbody>
        </table>
        <div id="jpagination_sendGoods"></div>
    </div>
    <div class="dialog-foot">
        <button class="dialog-button dialog-ok" id="confirmSendGoods">确认</button>
        <button class="dialog-button dialog-cancel" id="sendGoodsCancel">取消</button>
    </div>
</div>
<!-- 定格dialog -->
<div class="dialog hidden" id="addSpGroupDiv">
    <div class="dialog-head">选择定格区域</div>
    <div class="dialog-body clearfix">
	        <div>
	            <input type="text" id="search-condition" class="input" style="width:360px;" placeholder="定格名称">
	            <input type="button" onclick="search_ztree('ztree', 'search-condition')" value="搜索" class="input input-search-button">
	        </div>
	        <div class="limit-height" style="height: 300px;width:430px;">
	            <%@ include file="../common/ztree.jsp"%>
    			<script src="${root}/resources/js/ztree-search.js"></script>
	        </div>
    </div>
    <div class="dialog-foot">
        <button class="dialog-button dialog-ok" id="addSpGroupOk">确认</button>
        <button class="dialog-button dialog-cancel" id="addSpGroupCancel">取消</button>
    </div>
</div>
<div class="cover-all"></div>
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
var zTreeObjOne;
var settingOne = {
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
var zNodesOne = '';
function showIconForTree(treeId, treeNode) {
    return !treeNode.isParent;
};
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




	var zTreeObj;
    var sendConfigJsonObj=null;
    var shiQuDingGeJsonObj = null;
	var zSetting = {
        view: {
            showLine: false,
            showIcon: showIconForTree,
            fontCss: setFontCss_ztree
        },
        check: {
            enable: true
        },
	    data: {
	    	key: {
        		children: "regionList",
        	},
	        simpleData: {
	            enable: true
	        }
	    },
        callback: {
            onCheck: onCheck
        }
	};
	var zNodes = '';
    function showIconForTree(treeId, treeNode) {
        return !treeNode.isParent;
    };
    function onCheck(e, treeId, treeNode) {
        if(treeNode.checked){
        	if($('#ztree').find('input[id="'+treeNode.id+'"]').length==0){
        		var html = '<input type="hidden" class="J_spGroupId" id = "'+treeNode.id+'" value="'+treeNode.name+','+treeNode.id+','+treeNode.getParentNode().name+'">'
            	$('#ztree').append(html);
        	}
        }else{
        	$('#ztree').find('input[id="'+treeNode.id+'"]').remove();
        }
    }

    function clearBuyGoodsDiv(){//清空购买商品P标签下数据
       $('#seeBuyGoodsNameInput').val('');
       $('#buyGoodsNumInput').val('');
    }

    function clearAddSpVoucherTbody(){//清空选择的优惠劵
    	$('#confirmSpVoucherTbody').empty();
    }

    function clearRuleStartPrice(){//单笔订单满多少元
    	$('#ruleStartPrice').val('');
    }

    function clearRuleSendLimitInput(){//优惠劵每天张数
    	$('#ruleSendLimitInput').val('1');
    }

    function clearRulePayStrArr(){//支付方式
    	$('#payStrP').find('input[type="checkbox"][name="rulePayStrArr"]').attr('checked',false);
    }

    function clearAddSpGroupTbody(){//定格选择
    	$('#addSpGroupTbody').empty();
    	$('#ruleScopFlagSelect').val(0);
        $('#ruleScopFlagSelect').removeAttr("disabled");
    }

    function clearManJian(){//满减
    	$('#qianbaoMethod').val('');
    	$('#weixingMethod').val('');
        $('#huodaoMethod').val('');
        $('#zhifubaoMethod').val('');
        $('#kuaiqianMethod').val('');
    }

    function clearAddSendGoodsInDiv(){//赠送商品
    	$('#addSendGoodsInDiv').empty();
    }

    function clearFee(){//批发商,平台费用平摊比例
    	$('#plantHalveInput').val('');
    	$('#spHalveInput').val('');
    }

    function clearTransportTimeType() {//预计送达时间
        $('#transportTimeDiv').empty();
    }

    function generTransportHtml(){
        if(sendConfigJsonObj!=null){
            var html = '';
            $(sendConfigJsonObj).each(function(i,item){
               html+='<input type="radio" value="'+item.id+'" name="transportTimeType">'+item.name+'     '
            });
            $('#transportTimeDiv').html(html);
        }else{
            layer.msg('缺少配送时间选项!');
        }
    }

    function clearRuleScopFlagSelect(ruleType){
        if(ruleType == 14){
            $('#ruleScopFlagSelect').find('option[value="2"]').addClass('hidden');
            $('#ruleScopFlagSelect').find('option[value="3"]').removeClass('hidden');
        }else{
            $('#ruleScopFlagSelect').find('option[value="3"]').addClass('hidden');
            $('#ruleScopFlagSelect').find('option[value="2"]').removeClass('hidden');
        }
    }

    function clearAll(ruleType){//清空所有
    	clearBuyGoodsDiv();
    	clearAddSpVoucherTbody();
    	clearRuleStartPrice();
    	clearRuleSendLimitInput();
    	clearRulePayStrArr();
    	clearAddSpGroupTbody();
    	clearManJian();
    	clearAddSendGoodsInDiv();
    	clearFee();
        clearTransportTimeType();
        //累积送劵活动,定格选项没有指定批发商参与
        clearRuleScopFlagSelect(ruleType)
    };

    function searchItemBase(searchObject){
    	var level = searchObject.level;
    	var cateId = searchObject.cateId;
    	var searchKey = searchObject.searchKey;
    	$.ajax({
			type : "post",
			url : '${root}/customer/itemBases/getPaiChuItemBase.do',
			data:{'level':level,'cateId':cateId,'searchKey':searchKey},
			dataType : "json",
			success : function(da) {
				if(da.success){
					var html = '';
                   $(da.message).each(function(i,item){
                	   html+='<tr>'
                	       +'<input type="hidden" value="'+item.id+'">'
                	       +'<td><input type="checkbox" class="J_zhidingCheckBox"></td>'
                	       +'<td>'+item.mdseId+'</td>'
                	       +'<td>'+item.name+'</td>';
                	       if(item.spec==null||item.spec==""||item.spec=="null"){
                	    	   html+='<td></td>';
                	       }else{
                	    	   html+='<td>'+item.spec+'</td>';
                	       }
                	       if(item.pkg==null||item.pkg==""||item.pkg=="null"){
                	    	   html+='<td></td>';
                	       }else{
                	    	   html+='<td>'+item.pkg+'</td>';
                	       }
                	       '</tr>';
                   });
                   $('#selectTbody').html(html);
				}else{
					layer.msg("搜索商品失败!");
					console.log(da.message);
				}
			},
			error : function(data) {
			}
		});
    };

    $(function(){
    	dialogPosCenter('#J_dialogAssignProduct');
        dialogPosCenter('#J_dialogAddPerson');

    	//获取商品树形图
    	$.ajax({
			type : "post",
			url : '${root}/customer/itemCatelog/getTreeItemCatelog.do',
			dataType : "json",
			success : function(da) {
				if(da.success){
					zNodesOne = da.message;
				   zTreeObj = $.fn.zTree.init($("#ztreeOne"), settingOne, zNodesOne);
				}else{
					layer.msg('获取树形商品数据出错!');
					console.log(da.message);
				}
			},
			error : function(data) {
			}
		});


        //获取配送时间方案
        $.ajax({
            type : "post",
            url : '${root}/Customer/active/getSendTimeConfig.do',
            async : false,
            dataType : "json",
            success : function(da) {
                if(da!=null){
                    sendConfigJsonObj = da;
                }
            },
            error : function(data) {
            }
        });

        //获取市 区域 定格 json数据
        $.ajax({
            type : "post",
            url : '${root}/Corner/Region/getShiQuDingGeData.do',
            async : true,
            dataType : "json",
            success : function(da) {
                if(da.success){
                    shiQuDingGeJsonObj=da.message;
                }else{
                   layer.msg('获取市,区域,定格数据错误!')
                    console.log(da.message);
                }
            },
            error : function(data) {
            }
        });

    	//商品类别切换
        var $useItemFlagRadio = $('input[type="radio"][name="useItemFlag"]');
        $useItemFlagRadio.click(function(){
             if($(this).val()==0){
            	 $('#paichuDiv').addClass('hidden');//排除商品
                 $('#zhiDingDiv').addClass('hidden');//指定商品
             }else if($(this).val()==2){
            	 $('#zhiDingDiv').addClass('hidden');//指定商品
            	 $('#paichuDiv').removeClass('hidden');//排除商品
             }else{
            	 $('#paichuDiv').addClass('hidden');//排除商品
            	 $('#zhiDingDiv').removeClass('hidden');//指定商品
             }
        });


        var $time_start = $('.J_timeStart');
        var $time_end = $('.J_timeEnd');
        $time_start.on('focus', function() {
            var idx = $('.J_timeStart').index(this);
            WdatePicker({
                dateFmt: "yyyy-MM-dd",
                isShowWeek: true,
                minDate: '%y-%M-{%d}',
                onpicked: function() {
                    $('.J_timeEnd').eq(idx).focus();
                }
            });
        });
        $time_end.on('focus', function() {
            var idx = $('.J_timeEnd').index(this);
            WdatePicker({
                dateFmt: "yyyy-MM-dd",
                isShowWeek: true,
                minDate: $('.J_timeStart').eq(idx).val()
            });
        });
        // dateRange('.J_timeStart', '.J_timeEnd', 1);

       //获取定格列表
        $.ajax({
			type : "POST",
			url : "${root}/Customer/SpGroup/getAcTiveSpGroupList.do",
			dataType:'json',
			async : true,
			data : $('#addForm').serialize(),
			success : function(da) {
				if(da.success){
					zNodes = da.message;
				}else{
					layer.msg(da.message);
				}
			},
			error : function(da) {
				alert('失败的请求!');
			}
		});

        //活动类型下拉框
        $('#ruleTypeSelect').on('change',function(){
            console.log('有没有change');
        	clearAll($('#ruleTypeSelect').val());//清空所有条件
        	var ruleType = $('#ruleTypeSelect').val();
        	$('.condition').addClass('hidden');
        	$('#ruleScopFlagSelect').val(0);
        	$('#addSpVoucherDiv').addClass('hidden');
        	$('#buyGoodsDiv').addClass('hidden');
        	if(ruleType==1){//注册送优惠劵
        	    $('#spVoucherDiv').removeClass('hidden');
        	    $('#ruleScopFlagP').removeClass('hidden');
        	}
        	if(ruleType==2){//满送优惠劵
        		$('#spVoucherDiv').removeClass('hidden');
        	    //$('#ruleStartPriceP').removeClass('hidden');
        	    //$('#ruleSendLimitP').removeClass('hidden');
        	    $('#payStrP').removeClass('hidden');
        	    $('#ruleScopFlagP').removeClass('hidden');
        	}
        	if(ruleType==3){//满减金额
        		$('#ruleContentDiv').removeClass('hidden');
        		$('#ruleScopFlagP').removeClass('hidden');
        	}
        	if(ruleType==9){//满送优惠劵+商品
        		$('#spVoucherDiv').removeClass('hidden');
        		//$('#ruleSendLimitP').removeClass('hidden');
        		//$('#ruleStartPriceP').removeClass('hidden');
        		$('#sendGoodsDiv').removeClass('hidden');
        		$('#ruleScopFlagP').removeClass('hidden');
        		$('#ruleScopFlagSelect').val(2);
        		$('#ruleScopFlagSelect').attr("disabled",true);
        		$('#ruleScopFlagSelect').trigger('change');
        		$('#spGroupDiv').removeClass('hidden');
        		$('#payStrP').removeClass('hidden');
        	}
        	if(ruleType==10){//满减金额+商品
        		$('#ruleContentDiv').removeClass('hidden');
        		$('#sendGoodsDiv').removeClass('hidden');
        		$('#ruleScopFlagP').removeClass('hidden');
        		$('#ruleScopFlagSelect').val(2);
        		$('#ruleScopFlagSelect').attr("disabled",true);
        		$('#ruleScopFlagSelect').trigger('change');
        	}
        	if(ruleType==11){//满送商品
        		$('#ruleStartPriceP').removeClass('hidden');
        		$('#payStrP').removeClass('hidden');
        	    $('#ruleScopFlagP').removeClass('hidden');
        	    $('#sendGoodsDiv').removeClass('hidden');
        		$('#ruleScopFlagP').removeClass('hidden');
        		$('#ruleScopFlagSelect').val(2);
        		$('#ruleScopFlagSelect').attr("disabled",true);
        		$('#ruleScopFlagSelect').trigger('change');
        	}
        	if(ruleType==12){//满购商品送商品
        		$('#buyGoodsDiv').removeClass('hidden');
        		$('#payStrP').removeClass('hidden');
        		$('#sendGoodsDiv').removeClass('hidden');
        		$('#ruleScopFlagP').removeClass('hidden');
        		/* $('#ruleStartPriceP').removeClass('hidden'); */
        		$('#ruleScopFlagSelect').val(2);
        		$('#ruleScopFlagSelect').attr("disabled",true);
        		$('#ruleScopFlagSelect').trigger('change');
        	}
        	if(ruleType==13){//提前订货送优惠劵
                $('#spVoucherDiv').removeClass('hidden');
                $('#ruleScopFlagP').removeClass('hidden');
                $('#transportTimeTypeP').removeClass('hidden');
                $('#payStrP').removeClass('hidden');
                generTransportHtml();
            }
            if(ruleType==14){//累积送劵
                $('#imgUrlP,#spVoucherDiv,#ruleScopFlagP,#payStrP').removeClass('hidden');
            }
        });
        var isEdit = '${edit}';
        if(isEdit!='true'){//如果不是编辑页面,就触发change事件

        $('#ruleTypeSelect').trigger('change');
        }

        //参与区域下拉列表
        $('#ruleScopFlagSelect').on('change',function(){
        	var ruleScopFlag = $("#ruleScopFlagSelect").val();
        	if(ruleScopFlag==1||ruleScopFlag==2){
        		$('#spGroupDiv').removeClass('hidden');
                $('#assignStoreP').addClass('hidden');
                if(ruleScopFlag==2){
                	var ruleType = $('#ruleTypeSelect').val();
                	if(ruleType!=11&&ruleType!=12){
        			 $('#halveDiv').removeClass('hidden');
                	}
        		}else{
        			$('#halveDiv').addClass('hidden');
        		}
        	}else{
        		$('#halveDiv,#spGroupDiv').addClass('hidden');
        	}
        	if(ruleScopFlag==3){
              $('#assignStoreP').removeClass('hidden');
            }
        });

        //选择优惠劵
        $('#addSpVoucher').on('click',function(){
        	//如果活动类型是注册送优惠劵的话
        	var ruleType = $('#ruleTypeSelect').val();
        	if(ruleType==2||ruleType==9){
        		$('#spVoucherCancel').text('关闭');
        	}else{
        		$('#spVoucherCancel').text('取消');
        	}
        	$('#addSpVoucherDiv, .cover-all').show();
        	var searchObject = {searchKey:$('#spVoucherSearchKey').val()};
        	$("#jpagination").pagination({
        	    pageSize: 5,
        	    remote: {
        	        url: '${root}/Customer/voucher/getPageSpVoucherTempList.do',
        	        params: searchObject,
        	        success: function(data) {
        	           var html = '';
                       if(data.flag){
                    	   $.each(data.list,function(i,item){
                    		   html+='<tr class="spVoucherTr">'
                    		       +'<input type="hidden" value="'+item.id+'">'
                    		       +'<td>'+item.ruleName+'</td>'
                    		       +'<td>￥'+item.useMoney+'</td>'
                    		       +'<td>'+item.useDay+'天</td>';
                    		    if(item.startPrice==null||item.startPrice==''||item.startPrice==0){
                    		    	html+='<td></td>';
                    		    }else{
                    		    	html+='<td>'+item.startPrice+'</td>';
                    		    }
                    		    html+='</tr>';
                    	   });
                    	   $('#spVoucherTbody').html(html);
                       }

                       dialogPosCenter('#addSpVoucherDiv');
        	        },
        	        totalName:'totalSize'
        	    }
        	});
        });
        //选优惠劵取消按钮
        $('#spVoucherCancel').on('click',function(){
        	$('#addSpVoucherDiv, .cover-all').hide();
        	$('#spVoucherSearchKey').val('');
        	$("#jpagination").pagination('destroy');
        });
        //选取优惠券
        $('#spVoucherTbody').on('click', '.spVoucherTr', function() {
        	//注册送优惠劵 和提前下单送优惠劵 只能选择一张卷，其它送券类型可以选择多张
        	if($('#ruleTypeSelect').val()==1 || $('#ruleTypeSelect').val()==13){
        	  $(this).siblings('.spVoucherTr').removeClass('active');
        	}
            if($(this).hasClass('active')){
            	$(this).removeClass('active');
            }else{
        	   $(this).addClass('active');
            }
        });
        //选优惠劵确定按钮
        $('#spVoucherOk').on('click',function(){
        	var count = 0;
        	var html ='';
        	$('.spVoucherTr').each(function(){
        		if($(this).hasClass('active')){
        			count++;
        			if($('.J_voucherTempId[value="'+$(this).children().eq(0).val()+'"]').length==0){
        				html+='<tr>'
        			        +'<input type="hidden" class="J_voucherTempId" value="'+$(this).children().eq(0).val()+'" name="voucherTempIdArr">'//优惠劵id
        				    +'<td>'+$(this).children().eq(1).html()+'</td>'//优惠劵名
        				    +'<td>'+$(this).children().eq(2).html()+'</td>'//面额
        				    +'<td>'+$(this).children().eq(3).html()+'</td>'//有效期
        				    +'<td>'+$(this).children().eq(4).html()+'</td>'//使用金额限制
        				    ;
        			if($('#ruleTypeSelect').val()==1){//如果是注册送优惠劵 不用填满多少送和每天限制张数
        				    html+='<td></td>'
        				        +'<td></td>'
        				        +'<td><input type="button" value="删除" class="J_voucherDelete"></td>'
        				        +'</tr>'
        			}else if($('#ruleTypeSelect').val()==13){//如果是提前下单送优惠劵 不用填满多少送,但需要填每天限制张数
                        html+='<td></td>'
                            +'<td><input type="text" style="width:75px" name="ruleSendLimit" class="J_sendLimit"></td>'
                            +'<td><input type="button" value="删除" class="J_voucherDelete"></td>'
                            +'</tr>'
                    }else if($('#ruleTypeSelect').val()==14){//如果是累积送劵 不用填每天限制张数
                        html+='<td><input type="text" style="width:75px" name="startPriceArr" class="J_startPrice"></td>'
                            +'<td></td>'
                            +'<td><input type="button" value="删除" class="J_voucherDelete"></td>'
                            +'</tr>';
                    }
                    else{
        				html+='<td><input type="text" style="width:75px" name="startPriceArr" class="J_startPrice"></td>'
    				        +'<td><input type="text" style="width:75px" name="sendLimitArr" class="J_sendLimit"></td>'
    				        +'<td><input type="button" value="删除" class="J_voucherDelete"></td>'
    				        +'</tr>'
        			}
        			}
        		}
        	});
        	if(count==0){
        		layer.tips('请选择优惠劵!','#spVoucherOk',{time:1000});
        		return;
        	}

        	if($('#ruleTypeSelect').val()==1 || $('#ruleTypeSelect').val()==13){//注册送优惠劵 和 提前下单送优惠劵 只能选一张优惠劵
        	  $('#addSpVoucherDiv, .cover-all').hide();
        	  $('#spVoucherSearchKey').val('');
        	  $('#jpagination').pagination('destroy');
        	  $('#confirmSpVoucherTbody').html(html);
        	}else{
        		$('#confirmSpVoucherTbody').append(html);
        	}
        });
        //选择优惠劵搜索按钮
        $('#addSpVoucherSearch').on('click',function(){
        	$('.spVoucherTr').removeClass('active');
        	var searchObject = {searchKey:$.trim($('#spVoucherSearchKey').val())};
        	$("#jpagination").pagination('setParams',searchObject);
        	$("#jpagination").pagination('setPageIndex', 0);
        	$("#jpagination").pagination('remote');
        });
        //选择优惠劵删除按钮
        $('#confirmSpVoucherTbody').on('click','.J_voucherDelete',function(){
        	$(this).parent().parent().remove();
        });


        //选择购满商品
        $('#addBuyGoods').on('click',function(){
        	$('#addBuyGoodsDiv, .cover-all').show();
        	var serarchObject = {searchKey:$('#addBuyGoodsSearchKey').val()};
        	$("#jpagination_ItemBase").pagination({
        	    pageSize: 5,
        	    remote: {
        	        url: '${root}/customer/itemBases/getPagingItemBaseList.do',
        	        params: serarchObject,
        	        success: function(data) {
        	        var html = '';
                       if(data.flag){
                    	   $.each(data.list,function(i,item){
                               html+='<tr class="buyGoodsTr">'
                                   +'<input type="hidden" value="'+item.id+'">'
                                   +'<td>'+item.mdseId+'</td>'
                                   +'<td>'+item.name+'</td>'
                                   +'<td>'+item.spec+'</td>';
                               if(item.pkg==null||item.pkg==''||item.pkg=='null'){
                            	   html+='<td></td>';
                               }else{
                            	   html+='<td>'+item.pkg+'</td>';
                               }
                               html+='</tr>';
                    	   });
                    	   $('#buyGoodsTbody').html(html);
                       }
                       dialogPosCenter('#addBuyGoodsDiv');
        	        },
        	        totalName:'totalSize'
        	    }
        	});
        });
        //选购满商品取消按钮
        $('#buyGoodsCancel').on('click',function(){
        	$('#addBuyGoodsDiv').addClass('hidden');
        	$('#addBuyGoodsSearchKey').val('');
        	$('#addBuyGoodsDiv, .cover-all').hide();
        	$('#jpagination_ItemBase').pagination('destroy');
        });
        //选择购满商品
        $('#buyGoodsTbody').on('click','.buyGoodsTr',function(){
         	$(this).siblings('.buyGoodsTr').removeClass('active');
            if($(this).hasClass('active')){
            	$(this).removeClass('active');
            }else{
                $(this).addClass('active');
            }
        });
        //选购满商品确定按钮
        $('#confirmBuyGoods').on('click',function(){
        	var count=0;
        	$('.buyGoodsTr').each(function(){
        		if($(this).hasClass('active')){
        			count++;
        			$('#buyGoodsItemBaseIdInput').val($(this).children().eq(0).val());
        			$('#buyGoodsNameInput').val($(this).children().eq(2).text());
        			$('#seeBuyGoodsNameInput').val($(this).children().eq(2).text());
        		}
        	});
        	if(count==0){
        		layer.tips('请选择商品!','#confirmBuyGoods',{
        			time:1000
        		});
        		return;
        	}
        	$('#addBuyGoodsDiv').addClass('hidden');
        	$('#addBuyGoodsSearchKey').val('');
        	$('#addBuyGoodsDiv, .cover-all').hide();
        	$('#jpagination_ItemBase').pagination('destroy');
        });
        //选购满商品搜索按钮
        $('#addBuyGoodsSearch').on('click',function(){
        	$('.buyGoodsTr').removeClass('active');
        	var searchObject = {searchKey:$('#addBuyGoodsSearchKey').val()};
        	$("#jpagination_ItemBase").pagination('setParams',searchObject);
        	$("#jpagination_ItemBase").pagination('setPageIndex', 0);
        	$("#jpagination_ItemBase").pagination('remote');
        });

        //选赠送商品
        $('#addSendGoods').on('click',function(){
        	$('#addSendGoodsDiv, .cover-all').show();
        	var serarchObject = {searchKey:$('#addSendGoodsSearchKey').val()};
        	$("#jpagination_sendGoods").pagination({
        	    pageSize: 5,
        	    remote: {
        	        url: '${root}/customer/itemBases/getPagingItemBaseList.do',
        	        params: serarchObject,
        	        success: function(data) {
        	        var html = '';
                       if(data.flag){
                    	   $.each(data.list,function(i,item){
                               html+='<tr class="sendGoodsTr">'
                                   +'<input type="hidden" value="'+item.id+'">'
                                   +'<td>'+item.mdseId+'</td>'
                                   +'<td>'+item.name+'</td>'
                                   +'<td>'+item.spec+'</td>';
                               if(item.pkg==null||item.pkg==''||item.pkg=='null'){
                            	   html+='<td></td>';
                               }else{
                            	   html+='<td>'+item.pkg+'</td>';
                               }
                               html+='</tr>';
                    	   });
                    	   $('#sendGoodsTbody').html(html);
                       }
                       dialogPosCenter('#addSendGoodsDiv');
        	        },
        	        totalName:'totalSize'
        	    }
        	});
        });

        //选赠送商品取消按钮
        $('#sendGoodsCancel').on('click',function(){
        	$('#addSendGoodsDiv, .cover-all').hide();
        	$('#addSendGoodsDiv').addClass('hidden');
        	$('#addSendGoodsSearchKey').val('');
        	$('#jpagination_sendGoods').pagination('destroy');
        });

        //选中赠送商品
        $('#sendGoodsTbody').on('click','.sendGoodsTr',function(){
        	if($(this).hasClass('active')){
        		$(this).removeClass('active')
        	}else{
        		$(this).addClass('active')
        	}
        });

        //选赠送商品确定按钮
        $('#confirmSendGoods').on('click',function(){
        	var count = 0;
        	var html = '';
        	$('.sendGoodsTr').each(function(){
        		if($(this).hasClass('active')){
        			count ++;
        			var $tr = $(this);
        			var length = $('#addSendGoodsInDiv').find('input[value='+$(this).children().eq(0).val()+']').length;
        			if(length>0){
        				html+='';
        			}else{
        				html+='<p><input type="hidden" name="sendGoodsIdArr" value="'+$(this).children().eq(0).val()+'">'
            		    +'<input type="text" readonly="readonly" class="input"  name="sendGoodsNameArr" value="'+$(this).children().eq(2).text()+'">&nbsp;&nbsp;'
            		    +'数量:<input type="text" class="input"  name="sendGoodsNumArr" value="">&nbsp;&nbsp;'
            		    +'赠送总量:<input type="text" class="input"  name="sendGoodsTotalNumArr" value="">&nbsp;&nbsp;'
            		    +'<span class="button button-operate J_sendGoodsDelete">删除</span>'
            		    +'</p>';
        			}
        		}
        	});
        	if(count==0){
        		layer.tips('请选择商品!','#confirmSendGoods',{
        			time:1000
        		});
        		return;
        	}
        	$('#addSendGoodsInDiv').append(html);
        	$('#addSendGoodsDiv, .cover-all').hide();
        	$('#addSendGoodsDiv').addClass('hidden');
        	$('#addSendGoodsSearchKey').val('');
        	$('#jpagination_sendGoods').pagination('destroy');
        });

        //赠送商品删除按钮
        $('#addSendGoodsInDiv').on('click','.J_sendGoodsDelete',function(){
        	$(this).parent().remove();
        });

      //选购满商品搜索按钮
        $('#addSendGoodsSearch').on('click',function(){
        	$('.sendGoodsTr').removeClass('txt-warn');
        	var searchObject = {searchKey:$('#addSendGoodsSearchKey').val()};
        	$("#jpagination_sendGoods").pagination('setParams',searchObject);
        	$("#jpagination_sendGoods").pagination('setPageIndex', 0);
        	$("#jpagination_sendGoods").pagination('remote');
        });


      //增加定格区域按钮
        $('#addSpGroupSpan').on('click',function(){
        	$('#addSpGroupDiv, .cover-all').show();
        	$(zNodes).each(function(i,item){//省
        		$(item.regionList).each(function(i,item){//市
        			$(item.regionList).each(function(i,item){//区
        				$(item.regionList).each(function(i,item){//定格
                				 item.nocheck = false;
        				         item.checked = false;
                		});
            		});
        		});
        	});
        	var html = '';
        	//判断是否要根据addSpGroupTbody下已经选中的定格勾上zTree中的checkbox
        	if($('#addSpGroupTbody').find('tr [name="spGroupIdArr"]').length!=0){
        		$('#addSpGroupTbody').find('tr [name="spGroupIdArr"]').each(function(i,item){
        		   	var id = $(this).val();
        		   	$(zNodes).each(function(i,item){//省
                		$(item.regionList).each(function(i,item){//市
                			$(item.regionList).each(function(i,item){//区
                				var parantName = item.name;
                				$(item.regionList).each(function(i,item){//定格
                        			 if(item.id == id){
                        				 item.checked = true;
                        				 html+='<input type="hidden" class="J_spGroupId" id = "'+item.id+'" value="'+item.name+','+item.id+','+parantName+'">';
                        			 }
                        		});
                    		});
                		});
                	});
        		});
        		//onCheck(e, treeId, treeNode);
        	}
        	zTreeObj = $.fn.zTree.init($("#ztree"), zSetting, zNodes);
        	zTreeObj.expandAll(true);
        	$('#ztree').append(html);
			dialogPosCenter('#addSpGroupDiv');
        });
      //选择定格取消按钮
      $('#addSpGroupCancel').on('click',function(){
    	  $('#addSpGroupDiv, .cover-all').hide();
      });
      //选择定格确定按钮
      $('#addSpGroupOk').on('click',function(){
    	  var html = '';
    	  var spGrouphtml = $('#ztree').find('input[class="J_spGroupId"]');
    	  $(spGrouphtml).each(function(i,item){
    		var arr = new Array();
    		arr = item.value.split(",");
    		//if($('#addSpGroupTbody').find('tr input[value="'+arr[1]+'"]').length==0){
    			html+='<tr><input type="hidden" name="spGroupIdArr" value="'+arr[1]+'">'
    	        +'<td>'+arr[0]+'</td>'
    	        +'<td>'+arr[2]+'</td>'
    	        +'<td><span class="button button-operate J_spGroupDelete">删除</span></td>'
                +'</tr>';
    		//}
    	  });
    	  $('#addSpGroupTbody').html(html);
    	  $('#addSpGroupDiv, .cover-all').hide();
      });
      //选中定格删除按钮
       $('#addSpGroupTbody').on('click','.J_spGroupDelete',function(){
    	   $(this).parent('td').parent('tr').remove();
       });


      //赠送商品  数量,数量总量格式校验
      $('#addSendGoodsInDiv').on('blur','.J_sendGoodsNum',function(){
    	  var reg =/^[1-9]+$/;
    	  if(!reg.test($(this).val())){
    		  layer.tips('必须为大于1的正整数!',this,{
    			 time:1500,
    			 tips:1
    		  });
    		  return;
    	  }
      });
      $('#addSendGoodsInDiv').on('blur','.J_sendGoodsTotalNum',function(){
    	  var reg =/^[1-9]+$/;
    	  if(!reg.test($(this).val())){
    		  layer.tips('必须为大于1的正整数!',this,{
    			 time:1500,
    			 tips:1
    		  });
    		  return;
    	  }
      });

        /*************************指定用户begin*****************************/
        //增加用户
        $('#addPerson').on('click',function(){
            //shiQuDingGeJsonObj
            //生成全部城市下拉框
            var html = '';
            $('#allCity').html('<option value="-1">全部城市</option>')
            $(shiQuDingGeJsonObj).each(function(i,item){
                html+='<option value="'+item.id+'">'+item.name+'</option>';
            });
            $('#allCity').append(html);
            $('#allArea').html('<option value="-1">全部区域</option>')
            $('#allSpGroup').html('<option value="-1">全部定格</option>')
            $('#assignStoreKeyStr').val('');
            //$('#assignSelectAll').prop('checked',false);
            var searchObject = {};
            searchObject.keyStr = $.trim($('#assignStoreKeyStr').val());
            searchObject.cityId = $('#allCity').val();
            searchObject.areaId = $('#allArea').val();
            searchObject.groupId = $('#allSpGroup').val();
            $("#jpagination1").pagination({
                pageSize: 5,
                remote: {
                    url: '${root}/Customer/active/getStore.do',
                    params: searchObject,
                    success: function(data) {
                        if(data.flag){
                            var html='';
                            $(data.list).each(function(i,item){
                                html+='<tr data-id="'+item.id+'">'
                                      +'<td>';
                                      //+<input type="checkbox" class=" assignCheckbox" value="'+item.id+'">
                                      if($('#canyuStoreTbody tr[data-id="'+item.id+'"]').length>0){
                                          html+='<input type="checkbox" class="assignCheckbox" value="'+item.id+'" checked="checked">';
                                      }else{
                                          html+='<input type="checkbox" class="assignCheckbox" value="'+item.id+'">';
                                      }
                                html+='</td>'
                                      +'<td>'+item.name+'</td>'
                                      +'<td>'+item.contact+'</td>'
                                      +'<td>'+item.mobile+'</td>'
                                      +'<td>'+item.storeGroupName+'</td>'
                                      +'</tr>';
                            });
                            $('#assignStoreTbody').html(html);
                        }
                    },
                    totalName:'totalSize'
                }
            });

            $('#J_dialogAddPerson').removeClass('hiddn');
        });
        //城市 区域 定格 三级联动
        $('#allCity').on('change',function(){//城市
          if($('#allCity').val()!=-1){
              $('#allArea').html('<option value="-1">全部区域</option>')
              //生成区域下拉框
              var cityId = $('#allCity').val();
              var html = '';
              $(shiQuDingGeJsonObj).each(function(i,item){
                 $(item.regionList).each(function(i,item){
                     if(item.pId == cityId){
                         html+='<option value="'+item.id+'">'+item.name+'</option>'
                     }
                 });
                  $('#allArea').append(html);
                  if(item.id==cityId){
                      return;
                  }
              });
          }else{
              $('#allArea').html('<option value="-1">全部区域</option>')
              $('#allSpGroup').html('<option value="-1">全部定格</option>')
          }
        });
        $('#allArea').on('change',function(){//区域
            if($('#allArea').val()!=-1){
                $('#allSpGroup').html('<option value="-1">全部定格</option>')
              var areaId = $('#allArea').val();
              var html = '';
                $(shiQuDingGeJsonObj).each(function(i,item){//城市
                   $(item.regionList).each(function(i,item){//区域
                       $(item.regionList).each(function(i,item){//定格
                           if(item.pId == areaId){
                               html+='<option value="'+item.id+'">'+item.name+'</option>';
                           }
                       })
                   })
                });
               $('#allSpGroup').append(html);
            }else{
                $('#allSpGroup').html('<option value="-1">全部定格</option>')
            }
        });
        //指定用户弹出框确定按钮
        $('#assignStoreOK').on('click',function(){
             if($('#assignStoreTbody .assignCheckbox:checked').length==0){
                layer.msg('请选择店铺!',{time:1000});
                 return;
             }
             $($('#assignStoreTbody .assignCheckbox:checked')).each(function(i,item){//选中的
                 var $parentTr = $(this).parent('td').parent('tr');
                 var storeId = $(this).val();
                 if($('#canyuStoreTbody').find('tr[data-id="'+storeId+'"]').length==0){
                     var html = '<td><input type="button" value="删除" class="storeDel"></td>';
                     $parentTr.append(html).append('<input type="hidden" name="storeIdArr" value="'+storeId+'">').children().eq(0).remove();
                     $('#canyuStoreTbody').append('<tr data-id="'+storeId+'">'+$parentTr.html()+'</tr>');
                 }
             });
            $($('#assignStoreTbody .assignCheckbox').not(':checked')).each(function(i,item){//未选中
               var storeId = $(this).val();
                $('#canyuStoreTbody tr[data-id="'+storeId+'"]').remove();
            });
            $('#J_dialogAddPerson').addClass('hiddn');
            $("#jpagination1").pagination('destroy');
        });
        //指定用户弹出框取消按钮
        $('#assignStoreCancel').on('click',function(){
           $('#J_dialogAddPerson').addClass('hiddn');
            $("#jpagination1").pagination('destroy');
        });
        //指定用户弹出框搜索按钮
        $('#assignStoreSearch').on('click',function(){
            var searchObject = {};
            searchObject.keyStr = $.trim($('#assignStoreKeyStr').val());
            searchObject.cityId = $('#allCity').val();
            searchObject.areaId = $('#allArea').val();
            searchObject.groupId = $('#allSpGroup').val();
            $("#jpagination1").pagination('setParams',searchObject);
            $("#jpagination1").pagination('setPageIndex', 0);
            $("#jpagination1").pagination('remote');
        });
        //指定用户全选按钮
        $('#assignSelectAll').on('click',function(){
            if($(this).is(':checked')){
              $('#assignStoreTbody .assignCheckbox').prop('checked',true);
            }else{
                $('#assignStoreTbody .assignCheckbox').prop('checked',false);
            }
        });
        //指定用户单选按钮
        /*$('#assignStoreTbody .assignCheckbox').on('click',function(){
            alert($('#assignStoreTbody tr').length);

           if($('#assignStoreTbody .assignCheckbox:checked').length==$('#assignStoreTbody tr').length){
               $('#assignSelectAll').prop('checked',true);
           }else{
               $('#assignSelectAll').prop('checked',false);
           }
        });*/
        /*$('#assignStoreTbody .assignCheckbox').click(function(){
             alert(1);
        });*/
        //指定用户删除按钮
        $('#canyuStoreTbody').on('click','.storeDel',function(){
            var $html = $(this);
            layer.confirm('确定删除?',function(){
                $html.parent('td').parent('tr').remove();
                layer.closeAll('dialog');
            },function(){
            })
        });
        /*************************指定用户end*****************************/

        /*************************上传图片begin****************************/
        $("#up-img-to").on("click", function() {
            if ($("#file").val() == null || $("#file").val() == "") {
                alert("请先选择上传图片");
                return;
            }
            var f = checkFileTypeb();
            if (f) {
                ajaxFileUpload();
            }
        });
        function checkFileTypeb() {
            var myFile = document.getElementById("file");
            var filePath = myFile.value;
            var dotNdx = filePath.lastIndexOf('.');
            var exetendName = filePath.slice(dotNdx + 1).toLowerCase();
            if ((exetendName == "jpg" || exetendName == "jpeg" || exetendName == "gif" || exetendName == "png")) {
                return true;
            }
            $("#send-img").html("请选择正确的图片格式");
            return false;
        }
        function ajaxFileUpload() {
            var fileServiceName = '${USER_FASTFDSPREURL }';
            $("#send-imgb").html("");
            $.ajaxFileUpload({
                url : "${root}/customer/itemBases/upload.do",
                secureuri : false,
                fileElementId : 'file',
                dataType : 'text',
                success : function(data, status) {
                    if (data == "错误") {
                        return;
                    }
                    //alert("上传成功");
                    layer.msg('上传成功!');
                    var resultData = data.substring(1, data.length-1);
                    $("#good_img").val(resultData);
                    $("#good-img-show").attr("src", fileServiceName + resultData);
                    //goodImgShowBig("good-img-showb","goodImgShowBigb");
                },
                error : function(data, status, e) {
                    layer.msg('上传出错');
                    $("#send-imgb").html("上传失败");
                }
            });
        }
        /*************************上传图片end****************************/


      //确定按钮
        $('#saveButton').on('click',function(){
        	var ruleType = $('#ruleTypeSelect').val();
        	var priceRegex = /^0{1}([.]\d{1,2})?$|^[1-9]\d*([.]{1}[0-9]{1,2})?$/
        	var numRegex = /^[1-9][0-9]*$/
        	//将下拉框的disabled属性,留着的话页面表单序列化不能将下拉框选中的值传到后台
        	$('#ruleScopFlagSelect').removeAttr('disabled');


        	var errorLength = '';
        	//定格区域是批发商资源参与，而且活动类型不是满购商品送商品和满送商品的时候需要校验分摊比例的格式
        	if($('#ruleScopFlagSelect').val()==2&&$('#ruleTypeSelect').val()!=11&&$('#ruleTypeSelect').val()!=12){
        		//校验分摊比例格式
        		if(!priceRegex.test($.trim($('#plantHalveInput').val()))){
        			errorLength++;
        			layer.tips('格式不正确!','#plantHalveInput',{
         			   tipsMore: true,
         			   tips:[2,'#FA2929']
         		   });
        			//return;
        		}
        		if(!priceRegex.test($.trim($('#spHalveInput').val()))){
        			errorLength++;
        			layer.tips('格式不正确!','#spHalveInput',{
         			   tipsMore: true,
         			   tips:[2,'#FA2929']
         		   });
        			//return;
        		}
        	}
        	//TODO  必要的参数校验

        	if($('#ruleTypeSelect').val()==2||$('#ruleTypeSelect').val()==9){
        	   //活动类型为满送优惠劵或者满送优惠劵+商品
        	   var voucherTempLength = $('.J_voucherTempId[type="input"][value!=""]').length;
        	   var startPriceLength = $('.J_startPrice[type="input"][value!=""]').length;
        	   var sendLimitLength = $('.J_sendLimit[type="input"][value!=""]').length;
        	   //满多少送优惠劵校验
               $('.J_startPrice').each(function(){
           		   //校验格式
           		   if(!numRegex.test($.trim($(this).val()))){
           		      errorLength++;
           			   layer.tips('格式不正确!',this,{
               			   tipsMore: true,
               			   tips:[2,'#FA2929']
               		   });
           		   }
               });
        	   //每天限制张数
        	   $('.J_sendLimit').each(function(){
        		   //校验格式
           		   if(!numRegex.test($.trim($(this).val()))){
           		      errorLength++;
           			   layer.tips('格式不正确!',this,{
               			   tipsMore: true,
               			   tips:[2,'#FA2929']
               		   });
           		   }else if($.trim($(this).val()) > 127){
                       errorLength++;
                       layer.tips('每天限制不能超过127张!',this,{
                           tipsMore: true,
                           tips:[2,'#FA2929']
                       });
                   }
        	   });
        	}
        	if($('#ruleTypeSelect').val()==13){//提前下单送优惠劵,需要校验每天限制张数
                //每天限制张数
                $('.J_sendLimit').each(function(){
                    //校验格式
                    if(!numRegex.test($.trim($(this).val()))){
                        errorLength++;
                        layer.tips('格式不正确!',this,{
                            tipsMore: true,
                            tips:[2,'#FA2929']
                        });
                    }else if($.trim($(this).val()) > 127){
                        errorLength++;
                        layer.tips('每天限制不能超过127张!',this,{
                            tipsMore: true,
                            tips:[2,'#FA2929']
                        });
                    }
                });
            }
            if($('#ruleTypeSelect').val()==14){//累积送券活动要判断满送金额
                //满多少送优惠劵校验
                $('.J_startPrice').each(function(){
                    //校验格式
                    if(!numRegex.test($.trim($(this).val()))){
                        errorLength++;
                        layer.tips('格式不正确!',this,{
                            tipsMore: true,
                            tips:[2,'#FA2929']
                        });
                    }
                });
            }
        	if(errorLength!=0){
        		if(ruleType==9||ruleType==10||ruleType==11||ruleType==12){
					  $('#ruleScopFlagSelect').attr('disabled',true);
					}
               return;
         	}
        	//确定按钮
        	$('#saveButton').hide();

        	$.ajax({
    			type : "POST",
    			url : "${root}/Customer/active/saveActive.do",
    			dataType:'json',
    			async : true,
    			data : $('#addForm').serialize(),
    			success : function(da) {
    				if(da.success){
    					if($('#id').val()!=''){
    						layer.msg('修改成功！',{time:1000},function(){
    							location.href='${root}/Customer/active/toIndex.do';
    						});
    					}else{
    						layer.msg('保存成功！',{time:1000},function(){
    							location.href='${root}/Customer/active/toIndex.do';
    						});
    					}
    				}else{
    					$('#saveButton').show();
    					if(ruleType==9||ruleType==10||ruleType==11||ruleType==12){
    					  $('#ruleScopFlagSelect').attr('disabled',true);
    					}
    					if(da.message.indexOf('赠送商品错误')>=0){
    					  var errorArr = new Array();
    					  errorArr = da.message.split('&');
    					  if(errorArr[2]=='sendGoodsNumArr'){
    						  $('#addSendGoodsInDiv input[name="sendGoodsNumArr"]').each(function(i,item){
        						  if(i==errorArr[3]){
        							  layer.tips(errorArr[1],$(this));
        						  }
        					  });
    					  }else if(errorArr[2]=='sendGoodsTotalNumArr'){
    						  $('#addSendGoodsInDiv input[name="sendGoodsTotalNumArr"]').each(function(i,item){
        						  if(i==errorArr[3]){
        							  layer.tips(errorArr[1],$(this));
        						  }
        					  });
    					  }
    					}else{
    						if($('#ruleScopFlagSelect').val()==2){
    							$('#ruleScopFlagSelect').attr("disabled",true);
    			        	}
    					   layer.msg(da.message);
    					}
    					console.log(da.message);
    				}
    			},
    			error : function(da) {
    				if($('#ruleScopFlagSelect').val()==2){
						$('#ruleScopFlagSelect').attr("disabled",true);
		        	}
    				$('#saveButton').show();
    				layer.msg('失败的请求!');
    			}
    		});
        });

      //取消按钮
      $('#cancel').on('click',function(){
    	location.href='${root}/Customer/active/toIndex.do?pageIndex=${pageIndex}';
      });




       //tab('tab');
      dialogPosCenter('#J_dialogAssignProduct');
      dialogPosCenter('#J_dialogRemoveGoods');


      /**************** 指定类别/商品begin  *************************************/
      //指定增加按钮
      $('#zhidingAdd').on('click',function(){
    	zTreeObjOne = $.fn.zTree.init($("#ztreeOne"), settingOne, zNodesOne);
      	$('#selectedDiv').empty();
      	$('#shiFouCanyu').text('已选参与指定购买商品');
      	$('#search_condition').val('');
      	$('#J_dialogAssignProduct').removeClass('hidden');
      });
      //右移按钮
      $('#J_moveRight').on('click',function(i,item){
      	var html = '';
      	$.each(zTreeObjOne.getSelectedNodes(),function(i,item){
      		var isAdd = true;
      			var $htmlObject = $('#selectedDiv').find('div input[name="catId"][value="'+item.id+'"]');
      			if($htmlObject.length>0){
      				var thisLevel = $htmlObject.prev().val();
      				if(thisLevel==item.treeLevel){
      					isAdd = false;
      				}
      			}
      		if(isAdd){
      			if(item.treeLevel == 1){
                  	html+='<div class="box-item" data-value="cate">'
                  	      +'<input type="hidden" name="level" value="'+item.treeLevel+'">'
                  	      +'<input type="hidden" name="catId" value="'+item.id+'">'
                  	      +item.name+'</div>';
                  }else if(item.treeLevel == 2){
                  	var parent = item.getParentNode();
                  	html+='<div class="box-item" data-value="cate">'
                  		+'<input type="hidden" name="level" value="'+item.treeLevel+'">'
                	        +'<input type="hidden" name="catId" value="'+item.id+'">'
                  	    +parent.name+'/'+item.name
                  	    +'</div>';
                  }else if(item.treeLevel == 3){
                  	html+='<div class="box-item" data-value="itemBase">'
                	      +'<input type="hidden" name="level" value="'+item.treeLevel+'">'
                	      +'<input type="hidden" name="catId" value="'+item.id+'">'
                	      +item.name+'</div>';
                  }
      		}
           });
      	$('#selectedDiv').append(html);
      });
      $('#selectedDiv').on('click','.box-item',function(){
      	if($(this).hasClass('J_itemBaseDelete')){
      	$(this).removeClass('J_itemBaseDelete');
      	}else{
      	$(this).addClass('J_itemBaseDelete');
      	}
      })
      //左移按钮
      $('#J_moveLeft').on('click',function(){
      	$('#selectedDiv .J_itemBaseDelete').each(function(i,item){
      		$(this).remove();
      	});
      });
      //确定按钮
      $('#selectAddButton').on('click',function(){
      	var html ='';
      	if($('#shiFouCanyu').text()=='已选参与指定购买商品'){
      		$('#selectedDiv .box-item').each(function(){
      			var level = $(this).children('input').eq(0).val();
      			var catelogId = $(this).children('input').eq(1).val();
      			//判断是否有重复
      			if(level == 1 || level == 2){
      				var length = $('#zhidingTbody').find('tr[data-value="cate'+catelogId+'"]').length
      				console.log(length);
      				if(length>0){
      					return;
      				}
      			}else if(level == 3){
      				var length = $('#zhidingTbody').find('tr[data-value="itemBase'+catelogId+'"]').length
      				if(length>0){
      					return;
      				}
      			}
      			if(level ==1 || level == 2){
      			html+='<tr   data-value="cate'+catelogId+'"  class="cate'+catelogId+'">';
      			html+='<td>类别</td>';
      			}else{
      			html+='<tr   data-value="itemBase'+catelogId+'">';
      		    html+='<td>商品</td>';
      			}
      			html+='<td>'+$(this).text();
      			if(level ==1 || level == 2){
      				html+='<input type="hidden" name="productArr" value="cat@'+catelogId+'@'+$(this).text()+'@0@'+level+'">';
      			}else{
      				html+='<input type="hidden" name="productArr" value="item@'+catelogId+'@'+$(this).text()+'@0">';
      			}
      			html+='</td>';
      			html+='<td width="80">参与</td>';
      			html+='<td width="160">';
      			if(level == 1 || level == 2){
      				  html+='<span class="button button-operate J_zhiDingPaichu">'
      				       +'<input type="hidden" name="level" value="'+level+'">'
      				       +'<input type="hidden" name="cateId" value="'+catelogId+'">排除商品</span>';
      			}
      			html+='<span class="button button-operate J_zhiDingDelete">删除</span>'
      			    +'</td>'
      			    +'</tr>';
      		});
      		$('#zhidingTbody').append(html);
      		$('#J_dialogAssignProduct').addClass('hidden');
      	}else if($('#shiFouCanyu').text()=='已选排除类别/商品'){
      		$('#selectedDiv .box-item').each(function(){
      			var level = $(this).children('input').eq(0).val();
      			var catelogId = $(this).children('input').eq(1).val();
      			//判断是否有重复
      			if(level == 1 || level == 2){
      				var length = $('#paichuTbody').find('tr[data-value="cate'+catelogId+'"]').length
      				if(length>0){
      					return;
      				}
      			}else if(level == 3){
      				var length = $('#paichuTbody').find('tr[data-value="itemBase'+catelogId+'"]').length
      				if(length>0){
      					return;
      				}
      			}
      			if(level ==1 || level == 2){
      			html+='<tr   data-value="cate'+catelogId+'">';
      			html+='<td>类别</td>';
      			}else{
      			html+='<tr   data-value="itemBase'+catelogId+'">';
      		    html+='<td>商品</td>';
      			}
      			html+='<td>'+$(this).text();
      			if(level == 1 || level == 2){
      				html+='<input type="hidden" name="itemArr" value="cat@'+catelogId+'@'+$(this).text()+'"></td>';
      			}else{
      				html+='<input type="hidden" name="itemArr" value="item@'+catelogId+'@'+$(this).text()+'"></td>';
      			}
      			html+='<td width="80">不参与</td>';
      			html+='<td width="160">';
      			html+='<span class="button button-operate J_paiChuDelete">删除</span>'
      			    +'</td>'
      			    +'</tr>';
      		});
      		$('#paichuTbody').append(html);
      		$('#J_dialogAssignProduct').addClass('hidden');
      	}
      });
      //取消按钮
      $('#selectCancelButton').on('click',function(){
      	$('#J_dialogAssignProduct').addClass('hidden');
      	//$('.cover-all').fadeOut();
      });

      //指定类别/商品,排除商品按钮
      $('#zhidingTbody').on('click','.J_zhiDingPaichu',function(){
      	$('.cover-all').fadeIn()
      	$('#searchKey').val("");
      	var level = $(this).children().eq(0).val();
      	var cateId = $(this).children().eq(1).val();
      	$('#dataValue').val(cateId);
      	$('#dataLevel').val(level);
      	var searchObject = {"level":level,"cateId":cateId,"searchKey":""};
      	searchItemBase(searchObject);
      	$('#J_dialogRemoveGoods').removeClass('hidden');
      });
      //搜索
      $('#otherSearch').on('click',function(){
      	var level = $('#dataLevel').val();
      	var cateId = $('#dataValue').val();
      	var searchKey = $.trim($('#searchKey').val());
      	var searchObject = {"level":level,"cateId":cateId,"searchKey":searchKey};
      	searchItemBase(searchObject);
      })

      //指定商品 删除按钮
      $('#zhidingTbody').on('click','.J_zhiDingDelete',function(){
      	var isCate = $(this).parent('td').parent('tr').attr('data-value');
      	if(isCate.indexOf('cate')==0){
      	  var classStr = $(this).parent('td').parent('tr').attr('class');
      	  $('#zhidingTbody').find('tr.'+classStr+'').remove();
      	  $(this).parent('td').parent('tr').remove();
      	}else{
      	  $(this).parent('td').parent('tr').remove();
      	}
      });

      //排除商品/类别 删除按钮
      $('#paichuTbody').on('click','.J_paiChuDelete',function(){
      	  $(this).parent('td').parent('tr').remove();
      });

      //选择排除商品 确定按钮
      $('#zhidignConfirm').on('click',function(){
      	if($('#selectTbody .J_zhidingCheckBox:checked').length==0){
      		layer.msg('请选择要排除的商品！');
      		return;
      	}
      	var html = '';
      	$('#selectTbody .J_zhidingCheckBox').each(function(i,item){
      		if($(this).prop('checked')==true){
      		var name = $(this).parent('td').parent('tr').children().eq(3).text();
      		var id = $(this).parent('td').prev('input').val();
      		if($('#zhidingTbody').find('tr[data-value="itemBase'+id+'"]').length==0){
      			html+='<tr data-value="itemBase'+id+'" class="cate'+$('#dataValue').val()+'">'
                  +'<td></td>';
                  html+='<td>'+name+'<input type="hidden" name="productArr" value="item@'+id+'@'+name+'@1@cate'+$('#dataValue').val()+'"></td>'
                  +'<td width="80">不参与</td>'
                  +'<td width="160">'
                  +'<span class="button button-operate J_zhiDingDelete">删除</span>'
                  +'</td>'
                  +'</tr>';
      		 }
      		}
      	});
         var dataValue = $('#dataValue').val();
          $('#zhidingTbody').find('tr[data-value="cate'+dataValue+'"]').after(html);
          $('#J_dialogRemoveGoods').addClass('hidden');
      });

      //取消按钮
      $('#zhidingCancel').on('click',function(){
      	$('#selectTbody').empty();
      	$('#J_dialogRemoveGoods').addClass('hidden')
      });
      /**************** 指定类别/商品end  *************************************/

      /**************** 排除类别/商品begin******************************************/
      //增加按钮
      $('#paiChuAdd').on('click',function(){
    	zTreeObjOne = $.fn.zTree.init($("#ztreeOne"), settingOne, zNodesOne);
      	$('#selectedDiv').empty();
      	$('#shiFouCanyu').text('已选排除类别/商品');
      	$('#search_condition').val('');
      	$('#J_dialogAssignProduct').removeClass('hidden');
      });

      /**************** 排除类别/商品end******************************************/
    });
</script>
<script >
         var isEdit = '${edit}';
         if(isEdit=='true'){
	       var spVoucherActive = JSON.parse('${spVoucherActive}');
             console.log(spVoucherActive);
	       $('#legend').html('编辑活动');
	       $('#id').val(spVoucherActive.id);
	       if(spVoucherActive.ruleType==1 || spVoucherActive.ruleType==13){
               console.log('有没有进来!');
               var spVoucherTemp = JSON.parse('${spVoucherTemp}');
               console.log(spVoucherTemp);
	       }
	       if(spVoucherActive.ruleType==2||spVoucherActive.ruleType==9 || spVoucherActive.ruleType==14){
	    	   var gradeListVos = JSON.parse('${gradeVoObjectStr}');
	       }
	       if(spVoucherActive.ruleScopFlag==1||spVoucherActive.ruleScopFlag==2){
	    	   var spGroupVos = JSON.parse('${spGroupVos}');
	       }
	       if(spVoucherActive.ruleType==3||spVoucherActive.ruleType==10){
	    	   var ruleContent = '${ruleContent}';
	       }

         }
</script>
<script src="${root}/resources/js/promotion/promotion-edit.js"></script>
</body>
</html>
