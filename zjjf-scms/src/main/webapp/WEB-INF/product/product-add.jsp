<%@page import="com.corner.core.beans.Supplier"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>街坊店宝</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <%@ include file="../common/head.jsp"%>
    <script src="${root}/resources/js/comm.js"></script>
    <%@ include file="../common/autocomplete.jsp"%>
    <script>
    	var rootPath="${root}";
    </script>
</head>
<body class="wrap-bd">
<div class="mb-default">
    <span>当前位置：</span>
    <a href="${root}/scms/plantItem/toProductIndex.do" class="crumb">商品管理</a><span class="crumb crumb-active J_title">添加/编辑商品</span>
</div>
<div class="title mb-small J_title"></div>
<div class="wrap-bd bg">
	<div>
		<h3>基本信息</h3>
		<input type="hidden" id="FASTFDSPREURL" value="${USER_FASTFDSPREURL }">
		<input type="hidden" id="DEFAULTIMGURL" value="${USER_DEFAULTIMG_URL }">
        <div class="ml-default">
	        <p>
	            <label class="label" id="productNameLabel">商品名称 ：</label>
	            <input type="text" value="" name="productName" class="input-search-text"  id="productName">
	            <input type="hidden" value="${plantItemVo.name}" id="productNameHidden">
	        </p>
	        <p>
	            <label class="label" id="mdseIdLabel">商品编号 ：</label>
	            <input type="text" id="mdseId" value="${plantItemVo.mdseId}" class="input-search-text" disabled="disabled" style="background-color: #eeeeee">
	            <input type="hidden" value="${plantItemVo.mdseId}" id="mdseIdHidden">
	        </p>
	        <p>
	            <label class="label" id="measureLabel">计量单位：</label>
	            <input type="hidden" value="${plantItemVo.pkg}" id="measureHidden">
	            <span style="display: inline-block; width: 208px;">
	                <select id="measure"  class="select" disabled="disabled" style="background-color: #eeeeee">
	                	<option value="无">无</option>
	                    <option value="箱">箱</option>
	                    <option value="件">件</option>
	                </select>
	            </span>
	        </p>
	        <p>
	            <label class="label" id="specLabel">规格：</label>
	            <input type="text" value="" id="spec" class="input-search-text" disabled="disabled" style="background-color: #eeeeee">
	            <input type="hidden" value="${plantItemVo.spec}" id="specHidden">
	        </p>
	        <p>
	            <label class="label" id="catLabel">分类：</label>
	            <input type="hidden" value="${plantItemVo.cateId}" id="catId">
	            <select id="productFirstSelect" disabled="disabled" class="select" style="background-color: #eeeeee">
	            </select>
	            <select id="productSecondSelect" disabled="disabled" class="select" style="background-color: #eeeeee">
	            </select>
	        </p>
	        <p>
	            <label class="label va-t">图片：</label>
	            <c:choose>
	            	<c:when test="${plantItemVo.imgS == null || plantItemVo.imgS == ''}">
	            		<img width="140" height="140" src="${USER_DEFAULTIMG_URL}" id="pic" style="display:inline-block;border:1px solid #eee;width:140px;height:140px"alt="图片加载失败或没有">
	            	</c:when>
	            	<c:otherwise>
	            		 <img width="140" height="140" src="${USER_FASTFDSPREURL }${plantItemVo.imgS }" id="pic" style="display:inline-block;border:1px solid #eee;width:140px;height:140px" onerror="javascript:this.src='${USER_DEFAULTIMG_URL}'">
	            	</c:otherwise>
	            </c:choose>
       		</p>
	        <input type="hidden" value="${plantItemVo.status}" id="status">
        </div>
    	<form id="priceForm" >
    	<h3>出货价格</h3>
            <div class="ml-default">
				<c:if test="${plantItemVo.status != null && plantItemVo.status != 3}">
			        <div class="clearfix">
                        <b class="label fl">转角订单：</b>
                        <table class="fl">
                        	<c:choose>
                        		<c:when test="${plantItemlist != null && plantItemlist.size() > 0 }">
                        			<c:forEach items="${plantItemlist}" var="plantItem">
                        				<tr class="required">
			                        		<td>${plantItem.groupName }
			                        			<input type="hidden" name="spGroupIds" value="${plantItem.spGroupId }">
			                        		</td>
			                        		<td><input class="input areaPrices" style="width:100px" type="text" name="areaPrices" value="${plantItem.areaPrice }">
			                        			<input type="hidden" class="plantMemPrice" value="${plantItem.plantMemPrice }">
			                        		</td>
			                        		<td>
			                        			<select class="select statuss" name="statuss" >
			                        				<option value="1" <c:if test="${plantItem.status==1 }">selected="selected"</c:if> >上架</option>
			                        				<option value="0" <c:if test="${plantItem.status==0 }">selected="selected"</c:if> >下架</option>
			                        			</select>
			                        		</td>
			                        		<td><label class="msg"></label></td>
			                        	</tr>
                        			</c:forEach>
                        		</c:when>
                        		<c:otherwise>
                        			<tr>
		                        		<td colspan="3">此商品暂无定格信息</td>
		                        	</tr>
                        		</c:otherwise>
                        	</c:choose>
                        </table>
			        </div>
	        	</c:if>
		        <p class="required">
                    <b class="label">线下订单：</b>
		            <label>便&nbsp;利&nbsp;店&nbsp;</label>
		            <input type="text" value="${storePrice}" name="storePrice" id="storePrice" class="input-search-text" placeholder="">
		        </p>
                <p class="required">
                    <b class="label"></b>
                    <label>餐&nbsp;饮&nbsp;店&nbsp;</label>
                    <input type="text" value="${resPrice}" name="resPrice" id="resPrice" class="input-search-text" placeholder="">
                </p>
                <input type="hidden" value="${plantItemVo.itemBaseId}" name="itemBaseId" id="itemBaseId">
                <input type="hidden" value="${plantItemVo.id}" name="plantItemId" id="plantItemId">
                <input type="reset" class="hidden">
            </div>
  		</form>
	</div>
	<input type="hidden" id="flag" value="${flag}">
	<br>
	<div id="submitDiv">
        <label class="label mr-default"></label>
		<button class="button-save" id="submit">确定</button>
		<button class="button-cancel ml-default" id="cancelButton">取消</button>
	</div>
</div>
<script type="text/javascript">
	/* function sxj(status){
		var testPriceCheck = /^\d{1,5}(\.\d{1,2})?$/;
		var itemBaseId = $("#itemBaseId").val();
		var plantMemPrice = $.trim($('#plantMemPrice').val());
		if(plantMemPrice == null || plantMemPrice == '' || plantMemPrice == 'null'){
    		plantMemPrice=0;
    	}
    	if(status != null && status != "" && status == '1'){
    		var jiage = parseFloat(plantMemPrice)+parseFloat(plantMemPrice*0.1);
        	if(isNaN(jiage)){
				return false;
			}
    		if($('#areaPrice').val()=="" || $('#areaPrice').val()==null){
    			alert("转角售价不能为空！");
    			return;
    		}else if($('#areaPrice').val() > (Math.floor(jiage*100)/100)){
        		$("#label").html('<span style="color:red">您的价格已经高于市场价（'+plantMemPrice+'）的10%，如需调整请联系客服  400-664-3833</span>');
        		return;
        	}else if($('#areaPrice').val() < 0){
        		$("#label").html('<span style="color:red">店宝售价不能小于0！</span>');
        		return;
        	}else if(!testPriceCheck.test($('#areaPrice').val())){
        		alert("转角售价格式不正确！");
        		return;
        	}
    	}
		$.post("${root}/scms/plantItem/updatePlantItemStatus.do",{status:status,itemBaseId:itemBaseId,areaPrice:$('#areaPrice').val()},function(data){
			if(data.success){
				alert(data.message);
				location.href="${root}/scms/plantItem/toProductIndex.do";
			}else{
				alert(data.message);
			}
		})
	} */

//从session里面获取supplierid
var SUPPLYSESSION = '${SUPPLY_SESSION_KEY.id}';
var MOBILESESSION = '${SUPPLY_SESSION_KEY.mobile}'
var supplierIdsArr = new Array();
supplierIdsArr[0] = "E83AF703CB5543C5BD790616D667F83F";
supplierIdsArr[1] = "9593FB5707554CAF90D4F2BC8F295E06";
supplierIdsArr[2] = "085EBB06D4A046EF9B3647C1647ECE77";
supplierIdsArr[3] = "1061F08DF23F4E4EAB23F66393FFEAE2";
supplierIdsArr[4] = "1437BC6C55BA4685A518095E074907A1";
supplierIdsArr[5] = "255BF1F70C714557ABF574CFF301E405";
supplierIdsArr[6] = "36E77833EFEA419BBB28317A5B6A9868";
supplierIdsArr[7] = "3EC7D9CEE9C64CA09C231C574D0734A8";
supplierIdsArr[8] = "4F4F8508384B432F8E3B99EB42E33248";
supplierIdsArr[9] = "59A4DC8300CC44CBBA3EA99026A4A675";
supplierIdsArr[10] = "59C6BB34FD05471D9394EF91D5E9389A";
supplierIdsArr[11] = "5B8AF5944CC94AB89021E170FD1C8701";
supplierIdsArr[12] = "7868EEE2ACF741DE8DFD1E5DE362C363";
supplierIdsArr[13] = "83163E97A7B64453BAF18078B2F29CCA";
supplierIdsArr[14] = "9";
supplierIdsArr[15] = "916DCDBE8A4642BCB375DCCD01F265D1";
supplierIdsArr[16] = "9231FD9733E24F4EA85B3C105D8781AC";
supplierIdsArr[17] = "A48F9D0472B64243ADAC49BD6F9120F5";
supplierIdsArr[18] = "E8BCCBD2806D426A86A97156CFB0FECA";
supplierIdsArr[19] = "F07BB2E9B633457886029360B442260B";
supplierIdsArr[20] = "FB9F1EDD8DE8416E91E143EA75705018";
supplierIdsArr[21] = "CC48A10A1F7F421FA3F6E1FA14A8992A";
supplierIdsArr[22] = "86EF0227A9DC4C328B7A382AF4B08EEC";
supplierIdsArr[23] = "18098918339";
supplierIdsArr[24] = "13826501478";
supplierIdsArr[25] = "13418661520";
supplierIdsArr[26] = "13590383950";
supplierIdsArr[27] = "18928496048";
supplierIdsArr[28] = "18781607395";
supplierIdsArr[29] = "15919826060";
supplierIdsArr[30] = "13922803615";
supplierIdsArr[31] = "15927524652";
supplierIdsArr[32] = "13723457962";
supplierIdsArr[33] = "13418859989";
supplierIdsArr[34] = "13602625058";
supplierIdsArr[35] = "13826595528";
supplierIdsArr[36] = "13528741696";
supplierIdsArr[37] = "13751050090";
supplierIdsArr[38] = "13265654465";
supplierIdsArr[39] = "13662292450";
supplierIdsArr[40] = "13538175550";
supplierIdsArr[41] = "13590280408";
supplierIdsArr[42] = "13510404834";
supplierIdsArr[43] = "15889778408";
supplierIdsArr[44] = "13418919614";
supplierIdsArr[45] = "13715283210";
supplierIdsArr[46] = "13640972950";
supplierIdsArr[47] = "18088883707";
supplierIdsArr[48] = "13602665090";
supplierIdsArr[49] = "13714511724";
supplierIdsArr[50] = "13926587115";
supplierIdsArr[51] = "18923789590";
supplierIdsArr[52] = "13428692122";

//[push 方法添加 start]  例子:supplierIdsArr.push("15980000001");
supplierIdsArr.push("13316108598");
supplierIdsArr.push("13751735057");
supplierIdsArr.push("13539435336");
supplierIdsArr.push("13500011289");
supplierIdsArr.push("15322074716");
supplierIdsArr.push("13570978722");
supplierIdsArr.push("13710346549");
supplierIdsArr.push("18665736043");
supplierIdsArr.push("13421506987");
supplierIdsArr.push("13609787359");
supplierIdsArr.push("13928936335");
supplierIdsArr.push("13002020710");
supplierIdsArr.push("18928834060");
supplierIdsArr.push("15605059988");
supplierIdsArr.push("15820274984");
supplierIdsArr.push("18127875606");
supplierIdsArr.push("13729829808");
supplierIdsArr.push("13560033077");
supplierIdsArr.push("13710228032");
supplierIdsArr.push("18664801263");
supplierIdsArr.push("13580304634");
supplierIdsArr.push("13760772471");
supplierIdsArr.push("13432084822");
supplierIdsArr.push("13527777545");
supplierIdsArr.push("13535335645");
supplierIdsArr.push("18902336703");
supplierIdsArr.push("18707551497");
supplierIdsArr.push("13710450123");
// 2016-9-27 添加
supplierIdsArr.push("13480039800");
//2016-10-14添加
supplierIdsArr.push("13433631595");
supplierIdsArr.push("15015480583");


//[push 方法添加 结束]

</script>
<script src="${root}/resources/js/product-add.js"></script>
</body>
</html>
