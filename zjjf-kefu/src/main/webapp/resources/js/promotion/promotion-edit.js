
function echoSpVoucherActive(){
	//回显优惠劵
    $('#spVoucherDiv').removeClass('hidden');
    if(spVoucherActive.ruleType==1 || spVoucherActive.ruleType==13){
		var spVoucherTempName = spVoucherTemp.ruleName;//名称
		var useMoney = spVoucherTemp.useMoney;//面值
		var useDay = spVoucherTemp.useDay;//有效期
		var startPrice = spVoucherTemp.startPrice;//使用金额限制
		var id = spVoucherTemp.id;//优惠劵id
		if(startPrice==0){
			startPrice="";
		}
		$('#sendIdInput').val(id);
		var html = '<tr>';
		html+='<input type="hidden" class="J_voucherTempId" value="'+id+'" name="voucherTempIdArr">'
		    +'<td>'+spVoucherTempName+'</td>'
		    +'<td>￥'+useMoney+'</td>'
	        +'<td>'+useDay+'天</td>'
	        +'<td>'+startPrice+'</td>'
	        +'<td></td>';
        if(spVoucherActive.ruleType==1){
            html+='<td></td>';
        }else if(spVoucherActive.ruleType==13){
            html+='<td><input type="text" style="width:75px" name="ruleSendLimit" class="J_sendLimit" value="'+spVoucherActive.ruleSendLimit+'"></td>';
        }
        html+='<td><input type="button" value="删除" class="J_voucherDelete"></td>'
	        +'</tr>'
		$('#confirmSpVoucherTbody').html(html);
	}else if(spVoucherActive.ruleType==2||spVoucherActive.ruleType==9 || spVoucherActive.ruleType==14){
		var html = '';
		$.each(gradeListVos,function(i,item){
			html+='<tr>'
				+'<input type="hidden" class="J_voucherTempId" value="'+item.tempId+'" name="voucherTempIdArr">'
				+'<td>'+item.spVoucherTemp.ruleName+'</td>'
				+'<td>￥'+item.spVoucherTemp.useMoney+'</td>'
				+'<td>'+item.spVoucherTemp.useDay+'天</td>';
	            if(item.spVoucherTemp.startPrice==null||item.spVoucherTemp.startPrice==''||item.spVoucherTemp.startPrice=='null'){
	            	html+='<td></td>';
	            }else{
	            	html+='<td>'+item.spVoucherTemp.startPrice+'元</td>';
	            }
	            html+='<td><input type="text" style="width:75px" name="startPriceArr" class="J_startPrice" value="'+item.startPrice+'"></td>';
                if(spVoucherActive.ruleType!=14){
                    html+='<td><input type="text" style="width:75px" name="sendLimitArr" class="J_sendLimit" value="'+item.sendLimit+'"></td>';
                }else{
                    html+='<td></td>';
                }
                html+='<td><input type="button" value="删除" class="J_voucherDelete"></td>';
		});
		$('#confirmSpVoucherTbody').append(html);
	}
 }

function echoBasicMessage(){//基本信息
	$('#ruleTypeSelect').val(spVoucherActive.ruleType);
	$('#ruleName').val(spVoucherActive.ruleName);
	$('#ruleRemark').val(spVoucherActive.ruleRemark);
}

function echoRuleStartPrice(){//单笔订单金额
	$('#ruleStartPriceP').removeClass('hidden');
	var ruleStartPrice = spVoucherActive.ruleStartPrice;
	if(ruleStartPrice==0){
		ruleStartPrice=0;
	}
	$('#ruleStartPrice').val(ruleStartPrice);
}

function echoRuleSendLimit(){//优惠劵每天的张数
	$('#ruleSendLimitP').removeClass('hidden');
	$('#ruleSendLimitInput').val(spVoucherActive.ruleSendLimit);
}

function echoRulePayStr(){//支付方式
	$('#payStrP').removeClass('hidden');
	var payStr = spVoucherActive.rulePayStr;
	var payStrArr = new Array();
	payStrArr = payStr.split(',');
	$(payStrArr).each(function(i, item){
		$('input[name="rulePayStrArr"][value="'+item+'"]:checkbox').prop('checked', 'checked');
	});
}

function echoRuleScopFlag(){//回显定格
	$('#ruleScopFlagP').removeClass('hidden');
	var ruleScopFlag = spVoucherActive.ruleScopFlag;
    var ruleType = spVoucherActive.ruleType;
    if(ruleType==14){//累积送劵活动,隐藏指定批发商选项,显示指定用户选项
      $('#ruleScopFlagSelect option[value="2"]').addClass('hidden');
      $('#ruleScopFlagSelect option[value="3"]').removeClass('hidden');
    }else{
        $('#ruleScopFlagSelect option[value="3"]').addClass('hidden');
        $('#ruleScopFlagSelect option[value="2"]').removeClass('hidden');
    }
	if(ruleScopFlag==1||ruleScopFlag==2){
		$('#spGroupDiv').removeClass('hidden');
		var html = '';
		$(spGroupVos).each(function(i, item){
			html+='<tr>'
				+'<input type="hidden" value="'+item.id+'" name="spGroupIdArr">'
				+'<td>'+item.name+'</td>'
				+'<td>'+item.areaName+'</td>'
				+'<td><span class="button button-operate J_spGroupDelete">删除</span></td>'
				+'</tr>'
				;
		});
		$('#addSpGroupTbody').html(html);
	}
	if(ruleScopFlag==2){
		if(ruleType!=11&&ruleType!=12){//满送商品,满购商品送商品不需要回显比例
			$('#halveDiv').removeClass('hidden');
			//var plantHalve = spVoucherActive.plantHalve;
			//var spHalve = 100-spVoucherActive.plantHalve;
			$('#plantHalveInput').val(spVoucherActive.plantHalveStr);
			$('#spHalveInput').val(spVoucherActive.spHalveStr);
		}
	}
	if(ruleScopFlag==3){
	    $('#assignStoreP').removeClass('hidden');
    }
	$('#ruleScopFlagSelect').val(ruleScopFlag);
}

function echoRuleContent(){//回显满减字符串
  $('#ruleContentDiv').removeClass('hidden');
  var ruleContentArr = new Array();
  ruleContentArr = ruleContent.split('&');
  $(ruleContentArr).each(function(i, item){
	  if(item!=null && item!=""){
		  var jsonObject = JSON.parse(item);
			 if(jsonObject.paymethod==4){//微信支付
				 $('#weixingMethod').val(jsonObject.rule);
			 }else if(jsonObject.paymethod==2){//货到付款
				 $('#huodaoMethod').val(jsonObject.rule);
			 }else if(jsonObject.paymethod==3){//支付宝
				 $('#zhifubaoMethod').val(jsonObject.rule);
			 } else if(jsonObject.paymethod==1){//快钱
				 $('#kuaiqianMethod').val(jsonObject.rule);
			 }else if(jsonObject.paymethod==5){//钱包
				 $('#qianbaoMethod').val(jsonObject.rule);
			 }
	  }
  });

  //回显商品类别
  var goodsType = spVoucherActive.useItemFlag;
  var mgItems = spVoucherActive.mgItems;
  $('input[type="radio"][name="useItemFlag"][value="'+goodsType+'"]').prop('checked','checked');
  var arr = new Array();
	arr = mgItems.split(";");
	var html = '';
	var hiddenHtml='';
  if(goodsType==2){
  	$('#paichuDiv').removeClass('hidden');
  	for(var i=0;i<arr.length;i++){
    		 var tempArr = new Array();
    		 tempArr=arr[i].split("@");
    		 if(tempArr[0]=='cat'){
    			 html+='<tr data-value="cate'+tempArr[1]+' class="cate'+tempArr[1]+'" ">';
    			 html+='<td>类别</td>';
    		 }else{
    			 html+='<tr data-value="itemBase'+tempArr[1]+'">';
    			 html+='<td>商品</td>'
    		 }
    		 html+='<td>'+tempArr[2]+'<input type="hidden" name="itemArr" value="'+arr[i]+'"></td>'
    		     +'<td width="80">不参与</td>'
    		     +'<td width="160"><span class="button button-operate J_paiChuDelete">删除</span></td>'
    		     +'</tr>';
    	 }
  	$('#paichuTbody').append(html);
  }else if(goodsType==1){
  	$('#zhiDingDiv').removeClass('hidden');
  	for(var i=0;i<arr.length;i++){
   		 var tempArr = new Array();
   		 tempArr=arr[i].split("@");
   		 if(tempArr[0]=='cat'){
   			 html+='<tr data-value="cate'+tempArr[1]+'"  class="cate'+tempArr[1]+'">';
   			 html+='<td>类别</td>';
   		 }else{
   			 if(tempArr.length==5){//表示有层级关系
   				 html+='<tr data-value="itemBase'+tempArr[1]+'" class="'+tempArr[4]+'">';
   				 html+='<td></td>';
   			 }else{
   			 html+='<tr data-value="itemBase'+tempArr[1]+'">';
   			 html+='<td>商品</td>'
   			 }
   		 }
   		 html+='<td>'+tempArr[2]+'<input type="hidden" name="productArr" value="'+arr[i]+'"></td>';
   		 if(tempArr[3]==0){
   			 html+='<td width="80">参与</td>';
   		 }else{
   			 html+='<td width="80">不参与</td>';
   		 }
   		 html+='<td width="160">';
   		 if(tempArr[0]=='cat'){
   			 html+='<span class="button button-operate J_zhiDingPaichu"><input type="hidden" name="level" value="'+tempArr[4]+'"><input type="hidden" name="cateId" value="'+tempArr[1]+'">排除商品</span>';
   		 }
   		 html+='<span class="button button-operate J_zhiDingDelete">删除</span></td>'
   		     +'</tr>';
   	 }
  	$('#zhidingTbody').append(html);
  }
}

function echoSendGoods(){//回显赠送商品
	$('#sendGoodsDiv').removeClass('hidden');
	var sendGoodsStr = spVoucherActive.sendGoods;
	if(sendGoodsStr!=null&&sendGoodsStr!=""&&sendGoodsStr!="null"){
		var sendGoodsArr = new Array();
		sendGoodsArr = sendGoodsStr.split('&');
		var html = '';
		$(sendGoodsArr).each(function(i, item){
			var itemArr = new Array();
			itemArr = item.split(':');
			html+='<p>'
				+'<input type="hidden" value="'+itemArr[0]+'" name="sendGoodsIdArr">'
				+'<input type="text" value="'+itemArr[1]+'" name="sendGoodsNameArr" class="input" readonly="readonly">&nbsp;&nbsp;'
				+'数量:'
				+'<input type="text" value="'+itemArr[2]+'" name="sendGoodsNumArr" class="input">&nbsp;&nbsp;'
				+'赠送总量:'
				+'<input type="text" value="'+itemArr[3]+'" name="sendGoodsTotalNumArr" class="input">'
				+'<span class="button button-operate J_sendGoodsDelete">删除</span>'
				+'</p>';
		});
		$('#addSendGoodsInDiv').html(html);
	}
	//如果优惠劵是赠送商品类型  则必须是批发商自愿参与
	var ruleType = spVoucherActive.ruleType;
	if(ruleType==9||ruleType==10||ruleType==11||ruleType==12){
		$('#ruleScopFlagSelect').attr("disabled", true);
		$('#ruleScopFlagSelect').trigger('changer');
	}
}

function echoBuyGoods(){//回显购买商品
	$('#buyGoodsDiv').removeClass('hidden');
	var buyGoodsStr = spVoucherActive.buyGoods;
	var buyGoodsStrArr = new Array();
	buyGoodsStrArr = buyGoodsStr.split(":");
	$('#buyGoodsItemBaseIdInput').val(buyGoodsStrArr[0]);
	var html = '<input type="text" name="buyGoodName" id="seeBuyGoodsNameInput" class="input" readonly="readonly" value="'+buyGoodsStrArr[1]+'">&nbsp;&nbsp;';
	html+='购买数量：'
		+'<input type="text" id="buyGoodsNumInput" name="buyGoodsNum" class="input" value="'+buyGoodsStrArr[2]+'">';
	$('#buyGoodsP').html(html);

}

function echoTiQian(){
   $('#transportTimeTypeP').removeClass('hidden');
    generTransportHtml();//该function在promotion-add.jsp 中
    var transportTimeType = spVoucherActive.transportTimeType;
    if($('#transportTimeDiv').find('input[value="'+transportTimeType+'"]').length==0){
        layer.msg('没有对应的预计送达时间选项!')
    }else {
        $('#transportTimeDiv input[value="'+transportTimeType+'"]').attr("checked","checked");
    }

}

function echoImgUrl(){
    $('#imgUrlP').removeClass('hidden');
    //todo
}

function zhuCheSong(){//回显注册送
	echoBasicMessage();//基本信息
	echoSpVoucherActive();//赠送的优惠劵
	echoRuleScopFlag();
}

function acculate(){//累积送劵活动
    echoBasicMessage();//基本信息
    echoSpVoucherActive();//赠送的优惠劵
    echoRuleScopFlag();//回显定格或指定用户
    echoImgUrl();//上传图片回显
    echoRulePayStr();//支付方式
}


function manSongYouHuiQuan(){//回显满送优惠劵
	echoBasicMessage();//基本信息
	echoSpVoucherActive();//赠送的优惠劵
	//echoRuleStartPrice();//单笔订单金额
	//echoRuleSendLimit();//优惠劵每天限制张数
	echoRulePayStr();//支付方式
	echoRuleScopFlag();//回显定格
}

function manJian(){//满减
	echoBasicMessage();//基本信息
	echoRuleContent();//满减字符串和商品类别信息
	echoRuleScopFlag();//回显定格
}

function manSongYouHuiQuanHeShangPing(){//满送优惠劵和商品
	manSongYouHuiQuan();//满送优惠劵
	echoSendGoods();//回显赠送商品
}

function manJianHeSongShangPing(){
	manJian();//满减
	echoSendGoods();//回显赠送商品
}

function manSongShangPing(){//满送商品
	echoBasicMessage();//基本信息
	echoRuleStartPrice();//单笔订单金额
	echoRulePayStr();//支付方式
	echoRuleScopFlag();//回显定格
	echoSendGoods();//赠送商品
}

function manGouShangPingSongShangPing(){//满购商品送商品
	echoBasicMessage();//基本信息
	//manSongShangPing();
	echoRulePayStr();//支付方式
	echoRuleScopFlag();//回显定格
	echoBuyGoods();
}

function tiQianXiaDan(){//提前下单送优惠劵
    echoBasicMessage();//基本信息
    echoSpVoucherActive();//赠送的优惠劵
    echoRulePayStr();
    echoTiQian();
    echoRuleScopFlag();//回显定格选择
}


$(function(){
	//回显数据
	//活动类型
    // console.log(spVoucherActive);
	var ruleType = spVoucherActive.ruleType;

	if(ruleType==1){//注册优惠劵
		zhuCheSong();
	}else if(ruleType==2){//满送优惠劵
		manSongYouHuiQuan();
	}else if(ruleType==3){//满减
		manJian();
	}else if(ruleType==9){//满送优惠劵+商品
		manSongYouHuiQuanHeShangPing();
	}else if(ruleType==10){//满减+商品
		manJianHeSongShangPing();
	}else if(ruleType==11){//满送商品
		manSongShangPing();
	}else if(ruleType==12){//满购商品送商品
		manGouShangPingSongShangPing();
	}else if(ruleType==13){//提前下单送优惠劵
        tiQianXiaDan();
    }else if(ruleType==14){//累积送劵活动
       acculate();
    }
});
