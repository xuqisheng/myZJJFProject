<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setAttribute("root", request.getContextPath());%>
<%@ include file="../../common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>编辑供应商商品</title>
    <%@ include file="../../common/head.jsp"%>
</head>
<style type="text/css">
	.label{
		width: 120px !important;
	}
</style>
<body>
<div class="wrap-bd">
    <div class="mb-default">
        <span>当前位置：</span>
        <a class="crumb" href="${root }/ERPMa/toManageList.do">供应商管理</a>
        <a class="crumb" href="${root }/kefu/ERPMaItem/getAllManagerItem.do?managerId=${manager.id }">商品管理<span id="J_bbbbb">（${manager.managerName }）</span></a>
        <a class="crumb crumb-active" href="#">编辑商品</a>
    </div>
    <div class="wrap-bd bg table-border">
        <div>
           <label class="label">商品供应码：</label>${managerItemVo.itemCode}
        </div>
        <p>
            <label class="label">商品条形码：</label>${managerItemVo.mdseId}
        </p>
        <p>
            <label class="label">商品名称：</label>${managerItemVo.name}
        </p>
        <p>
            <label class="label">规格：</label>${managerItemVo.spec}
        </p>
<!--         <p>
            <label class="label">保质期：</label>
        </p> -->
        <p>
            <label class="label">计量单位：</label>${managerItemVo.pkg}
        </p>
        <p>
            <label class="label">分类：</label>${managerItemVo.yiJiName}/${managerItemVo.erJiName}
        </p>
        <p>
            <label class="label va-t">图片：</label>
            <img alt="" src="${USER_FASTFDSPREURL}${managerItemVo.picUrl}" width="130" height="130" onerror="javascript:this.src='${USER_DEFAULTIMG_URL}'">
        </p>
        <form>
        <input type="hidden" value="${managerItemVo.id }" name="id" id="id">
        <p class="required">
            <label class="label">采购价：</label>
            <input type="text" name="areaPrice" id="areaPrice" value="${managerItemVo.areaPrice}" class="input" maxlength="8">元
        </p>
        <p>
            <label class="label">税率：</label>
            <input type="text" name="taxRate" id="taxRate" value="${managerItemVo.taxRate}" class="input" maxlength="5">%
        </p>
        <p>
            <label class="label">税额：</label>
            <span class="tax-account"></span>元
        </p>
        <p>
            <label class="label">金额(不含税)：</label>
            <span class="after-tax-account"></span>元
        </p>
        <p>
            <label class="label"></label>
            <input type="button" value="保存" class="button button-ok" id="okSave">
            <input type="button" value="取消" class="button button-cancel" id="okClose">
        </p>
        </form>
    </div>
</div>
<script type="text/javascript">
	$(function(){
		
		$('.tax-account').html(($('input[name=areaPrice]').val()*$('input[name=taxRate]').val()*0.01).toFixed(2))
		$('.after-tax-account').html(($('input[name=areaPrice]').val()*(1-$('input[name=taxRate]').val()*0.01)).toFixed(2))
		$("#okSave").on("click",function(){
			var picCheck =  /^\d{1,5}(\.\d{1,2})?$/;
			var id= $("#id").val();
			//参数校验
			var areaPrice = $("#areaPrice").val();
			if(areaPrice==""){
				alert("请输入采购价");
				return;
			}else if(!picCheck.test(areaPrice)){
				alert("请输入正确的采购价");
				return;
			}
			var taxRateCheck =  /^\d{1,2}(\.\d{1,2})?$/;
			var taxRate = $("#taxRate").val();
			if(taxRate != ""){
				if(!taxRateCheck.test(taxRate)){
					alert("请输入正确的税率");
					return;
				}
			}
			
			if(id != null && id != '' && id != 'null' && id != 'undefined'){
				$.post("${root}/kefu/ERPMaItem/updateERPManagerItem.do",{id:id,areaPrice:areaPrice,taxRate:taxRate},function(data){
					if(data.success){
						alert(data.message);
						location.href="${root }/kefu/ERPMaItem/getAllManagerItem.do?managerId=${manager.id }";
					}else{
						alert(data.message);
					}
				},'json')
			}
		});
		$("#okClose").on("click",function(){
			location.href="${root }/kefu/ERPMaItem/getAllManagerItem.do?managerId=${manager.id }";
		});
	})
</script>
</body>
</html>
