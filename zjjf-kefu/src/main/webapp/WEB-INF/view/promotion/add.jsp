<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>促销管理</title>
    <%@ include file="../common/head.jsp"%>
</head>
<body class="wrap-bd">
    <div class="mb-small">
        <a href="${root}/Customer/proManage/toIdex.do">返回满减列表</a>
    </div>
    <fieldset>
        <legend>添加满减</legend>
		<form action="" id="promotionForm">
			<input type="hidden" value="${plantRebate.rebatecontent}" id="rule">
			<input type="hidden" value="${plantRebate.id}" name="id">
			<div class="mb-default">
				<label class="label">名称：</label>
				<input class="input input-default" type="text" name="rebatename" id="" value="${plantRebate.rebatename}"/>&nbsp;
				<span class="error-prompt" id=""></span>
			</div>
			<div class="mb-default clearfix">
				<label>优惠内容：</label><br>
				<div class="fl">
					<div class="mt-small mb-small">
						<label class="label">微信支付</label><!--payMethod=4  -->
						<input class="input input-default" type="text" name="weixingMethod" id="weixingMethod" />
					</div>
					<div class="mt-small mb-small">
						<label class="label">货到付款</label><!--payMethod=2  -->
						<input class="input input-default" type="text" name="huodaoMethod" id="huodaoMethod" />
					</div>
					<div class="mt-small mb-small">
						<label class="label">支付宝支付</label><!--payMethod=3  -->
						<input class="input input-default" type="text" name="zhifubaoMethod" id="zhifubaoMethod" />
					</div>
					<div class="mt-small mb-small">
						<label class="label">快钱支付</label><!--payMethod=1  -->
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
			<div class="mb-default clearfix">
				<div class="fl" style="width:105px">发送优惠给：</div>
				<div class="fl" style="width:870px">
					<input type="hidden" value="${spGroupStr}" id="spGroupInput">
					<input type="checkbox" id="selectAll"><span style="color:blue"> 全选定格</span><br>
					<c:forEach var="spGroup" items="${spGroupList}">
						<span style="display: inline-block;width:285px;height:20px;overflow:hidden">
							<input type="checkbox" value="${spGroup.id}"  name="spGroupId" class="spGroupCheckBox" id="${spGroup.id}" /> ${spGroup.name}
						</span>
                    </c:forEach>
                   </div>
			</div>
			<div class="mb-default">
				<label class="label">是否启用：</label> 
				<input type="radio" name="status" checked="checked" value="1"/>是 
				<input type="radio" name="status" value="0"/>否
				<input type="hidden" value="${plantRebate.status}" id="updateStatus">
			</div>
			<div>
				<input class="button button-ok" type="button" onclick="checkForm();" value="保存">
				<!-- <input class="button-cancel" type="button" onclick="" value="取消"> -->
			</div>
		</form>
	</fieldset>
	<script>
		$(function() {
			if("${update}"){
				$("#name").html("促销修改");
				//是否启用回显
				$("input:radio[name='status']").removeAttr("checked");
				$("input:radio[name='status']").each(function(){
					if($(this).val()== $("#updateStatus").val()){
						$(this).prop("checked",true);
					}
				});
				//优惠内容回显
				var rule=$("#rule").val();
				var ruleArr = rule.split("&");
				for(var i = 0;i<ruleArr.length;i++){
					var payMethod = ruleArr[i].split(",")[1];
					var pay = ruleArr[i].split(",")[2].substring(ruleArr[i].split(",")[2].indexOf(":")+1);
					pay = pay.substring(0,pay.lastIndexOf("}"));
					if(payMethod=="paymethod:1"){
						$("#kuaiqianMethod").val(pay);
					}else if(payMethod=="paymethod:2"){
						$("#huodaoMethod").val(pay);
					}else if(payMethod=="paymethod:3"){
						$("#zhifubaoMethod").val(pay);
					}else{
						$("#weixingMethod").val(pay);
					}
				}
				//定格回显
				$("input:checkbox[name='spGroupId']").removeAttr("checked");
				var spGroupInput = $("#spGroupInput").val();
				var spGroupIdArr = spGroupInput.split(",");
				for(var i =0;i<spGroupIdArr.length;i++){
					$('#'+spGroupIdArr[i]+'').prop("checked",true);
				}
			}
			$("#selectAll").click(function(){
				$(".spGroupCheckBox").prop("checked",$(this).prop("checked"));
			});
		})

		//提交表单
		function checkForm() {
			$.ajax({
    			type : "POST",
    			url : "${root}/Customer/proManage/savePromotion.do",
    			async : false,
    			data : $("#promotionForm").serialize(),
    			success : function(da) {
    				if (da.success) {
    				  alert("保存成功!");	
    				  location.href="${root}/Customer/proManage/toIdex.do";
    				}else{
    					alert(da.message);
    				}
    			},
    			error : function(da) {
    			}
    		});
		}
	</script>
</body>
</html>