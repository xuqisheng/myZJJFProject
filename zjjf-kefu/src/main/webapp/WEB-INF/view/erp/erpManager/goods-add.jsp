<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setAttribute("root", request.getContextPath());%>
<%@ include file="../../common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>添加供应商商品</title>
    <%@ include file="../../common/head.jsp"%>
</head>
<body>
<div class="wrap-bd">
    <div class="mb-default">
        <span>当前位置：</span>
        <a class="crumb" href="${root }/ERPMa/toManageList.do">供应商管理</a>
        <a class="crumb" href="${root }/kefu/ERPMaItem/getAllManagerItem.do?managerId=${manager.id }">商品管理<span id="J_bbbbb">（${manager.managerName }）</span></a>
        <a class="crumb crumb-active" href="#">添加商品</a>
    </div>
    <form id="param">
    <input type="hidden" value="${manager.id }" id="managerId" name="managerId">
    <div class="table-contain">
        <pre>
            <table class="table-list">
                <thead>
                <tr>
                    <th>商品条形码</th>
                    <th>箱码</th>
                    <th>商品名称</th>
                    <th>规格</th>
                    <th>单位</th>
                    <!-- <th>保质期</th> -->
                    <th>采购价</th>
                    <th>税率</th>
                    <th>税额</th>
                    <th>金额(不含税)</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="tb">
                <tr>
                    <td><input type="hidden" class="itemBaseId" name="itemBaseIds">
                        <input type="text" class="input mdseId" maxlength="40">
                    </td>
                    <td>
                        <input type="text" class="input mdseIdx" maxlength="40">
                    </td>
                    <td class="name"></td>
                    <td>
                        <select class="select spec">
                           <!-- <option value="">300ml*25瓶</option> -->
                        </select>
                    </td>
                    <td class="pkg"></td>
                    <!-- <td></td> -->
                    <td >
	                    <input type="text" class="input-search-date areaPrice" name="areaPrices" maxlength="8">元
                    </td>
                    <td>
                        <input type="text" class="input-search-date taxRate" name="taxRates" maxlength="5">%
                    </td>
                    <td>
                        <span class="tax-account"></span>
                    </td>
                    <td>
                        <span class="after-tax-account"></span>
                    </td>
                    <td>
                        <span class="button-operate deleteTr">删除</span>
                    </td>
                </tr>
                <tr>
                    <td colspan="10">
                        <span class="button button-operate addTr">添加</span>
                    </td>
                </tr>
                </tbody>
            </table>
        </pre>
	</div>
    <div class="ta-c mt-default">
        <input type="button" value="确定" class="button button-ok" id="okSave">
        <input type="button" value="取消" class="button button-cancel" id="okClose">
    </div>
    </form>
</div>
<script type="text/javascript">
$(".mdseId").focus();
	//根据条形码获取商品
	$(function(){
		//初始化一行
		var html='<tr>'
            +'<td><input type="hidden" class="itemBaseId" name="itemBaseIds">'
            +'<input type="text" class="input mdseId" maxlength="40">'
            +'</td>'
            +'<td>'
            +'<input type="text" class="input mdseIdx" maxlength="40">'
            +'</td>'
            +'<td class="name"></td>'
            +'<td>'
            +'<select class="select spec">'
               /* <option value="">300ml*25瓶</option> */
            +'</select>'
            +'</td>'
            +'<td class="pkg"></td>'
            /* +'<td></td>' */
            +'<td class="required">'
            +'<input type="text" class="input-search-date areaPrice" name="areaPrices" maxlength="8">元<span>'
            +'</td>'
            +'<td>'
            +'<input type="text" class="input-search-date taxRate" name="taxRates" maxlength="5">%'
            +'</td>'
             +'<td>'
            +'<span class="tax-account"></span>'
            +'</td>'
            +'<td>'
            +'<span class="after-tax-account"></span>'
            +'</td>'
            +'<td>'
            +'<span class="button-operate deleteTr">删除</span>'
            +'</td>'
    		+'</tr>';
    		
    		
    	//计算税额以及税后金额
    	$('#tb').on('blur','input[name="taxRates"]',function(){
    		var areaPrices=$(this).parent().parent().find('input[name="areaPrices"]').val();
    		if(areaPrices.length!=0&&$(this).val().length!=0){
    			$(this).parent().parent().find('.tax-account').html((areaPrices*$(this).val()*0.01).toFixed(2));
    			if($(this).parent().parent().find('.tax-account').html()!=null){
    				$(this).parent().parent().find('.after-tax-account').html((areaPrices-$(this).parent().parent().find('.tax-account').html()).toFixed(2));
    			}
    		}
    	})
    	
    	//tab键添加一行
    	$("#tb").on('keydown','input[name="taxRates"]:last', function(ev) {
	      if(ev.keyCode == "13" || ev.keyCode == "9") {
	    	  $(this).parent("td").parent("tr").after(html);
	    	  $(this).parent().parent().children('td').eq(1).focus();
	      }
	  	});
		//添加一行
		$(".addTr").on("click",function(){
        	$(this).parent("td").parent("tr").before(html);
		});
		//删除一行
		$("#tb").on("click",".deleteTr",function(){
			$(this).parent("td").parent("tr").remove();
		});
		
		$("#tb").on("change",".mdseId",function(){
			var $root = $(this);
			var $tr = $root.parent("td").parent("tr");
			var mdseId = $root.val();
			if(mdseId != null && mdseId != "" && mdseId != "null" && mdseId != "undefined"){
				$.post("${root}/kefu/ERPMaItem/getitemByMdseId.do?action=1",{mdseId:mdseId},function(data){
					if(data.success){
						$tr.find(".mdseIdx").attr('disabled','disabled');
						$tr.find(".mdseIdx").val(data.message[0].mdseId);
						$tr.find(".name").html(data.message[0].name);
						$tr.find(".pkg").html(data.message[0].pkg);
						$tr.find(".itemBaseId").val(data.message[0].id);
						var html='';
						$.each(data.message,function(i,item){
							html+='<option value="'+item.id+'" outMdseId="'+item.mdseId+'">'+item.spec+'</option>';
						});
						$tr.find(".spec").html(html);
					}else{
						alert(data.message);
						$root.val("");
					}
				},'json');
			}else{
				$tr.find(".mdseIdx").removeAttr('disabled','disabled');
			}
		});
		$("#tb").on("change",".mdseIdx",function(){
			var $root = $(this);
			var $tr = $root.parent("td").parent("tr");
			var mdseId = $root.val();
			if(mdseId != null && mdseId != "" && mdseId != "null" && mdseId != "undefined"){
				$.post("${root}/kefu/ERPMaItem/getitemByMdseId.do?action=2",{mdseId:mdseId},function(data){
					if(data.success){
						if(repeat(data.message.id)==false){
							alert("商品重复,请重新输入");
							$root.val("");
						}else{
							if(data.message.upMdseId==null || data.message.upMdseId==""){
								$tr.find(".mdseId").val(data.message.mdseId);
							}else{
								$tr.find(".mdseId").val(data.message.upMdseId);
							}
							$tr.find(".name").html(data.message.name);
							$tr.find(".pkg").html(data.message.pkg);
							var html='<option value="'+data.message.id+'">'+data.message.spec+'</option>';
							$tr.find(".spec").html(html);
							$tr.find(".itemBaseId").val(data.message.id);
						}
					}else{
						alert(data.message);
						$root.val("");
					}
				},'json');
			}
		});
		
		$("#tb").on("change",".spec",function(){
			var $tr = $(this).parent("td").parent("tr"); 
			$tr.find(".itemBaseId").val($(this).val());
			$tr.find(".mdseIdx").val($(this).find("option:selected").attr('outMdseId'));
		});
		
		$("#okSave").on("click",function(){
			var picCheck =  /^\d{1,5}(\.\d{1,2})?$/;
			var managerId = "${manager.id }";
			var flag = true;
			$(".itemBaseId").each(function(){
				if($(this).val()==null || $(this).val()==""){
					flag=false;
					alert("请输入商品条形码");
					return false;
				}
			});
			if(!flag){
				return;
			}
			$(".areaPrice").each(function(){
				if($(this).val()==null || $(this).val()==""){
					flag=false;
					alert("请输入采购价");
					return false;
				}else if(!picCheck.test($(this).val())){
					flag=false;
					alert("请输入正确的采购价");
					return false;
				}
			});
			if(!flag){
				return;
			}
			var taxRateCheck =  /^\d{1,2}(\.\d{1,2})?$/;
			$(".taxRate").each(function(){
				if($(this).val() !=null && $(this).val() !=""){
					if(!taxRateCheck.test($(this).val())){
						flag=false;
						alert("请输入正确的税率");
						return false;
					}
				}
			});
			if(!flag){
				return;
			}
			
			$.post("${root}/kefu/ERPMaItem/addERPManagerItem.do",$("#param").serializeArray(),function(data){
				if(data.success){
					alert(data.message);
					location.href="${root }/kefu/ERPMaItem/getAllManagerItem.do?managerId="+managerId;
				}else{
					alert(data.message);
				}
				
			},'json')
			
		});
		
		$("#okClose").on("click",function(){
			location.href="${root }/kefu/ERPMaItem/getAllManagerItem.do?managerId=${manager.id }";
		});
	});
	//判断商品是否重复
	function repeat(itembaseId){
		var flag = false;
		$(".itemBaseId").each(function(){
			if($(this).val()!=null && $(this).val()!= "" ){
				if($(this).val() == itembaseId){
					flag=true;
					return false;
				}
			}
		});
		if(flag){
			return false;
		}
	}
	
	
</script>
</body>
</html>
