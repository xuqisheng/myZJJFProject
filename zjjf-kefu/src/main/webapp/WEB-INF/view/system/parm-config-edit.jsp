<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>参数配置</title>
    <%@ include file="../common/head.jsp"%>
    
    <script src="${root }/resources/js/comm.js"></script>
</head>
<body class="wrap-bd">
<div>
    <span onclick="query(0)" class="pills <c:if test="${systemConfigDetailRo.payType==null || systemConfigDetailRo.payType=='' || systemConfigDetailRo.payType== 0 }" >pills-active</c:if> ">微信支付</span>
    <span onclick="query(1)" class="pills <c:if test="${systemConfigDetailRo.payType== 1 }" >pills-active</c:if>">货到付款</span>
<%--     <span onclick="query(2)" class="pills <c:if test="${systemConfigDetailRo.payType== 2 }" >pills-active</c:if>">支付宝支付</span>
    <span onclick="query(3)" class="pills <c:if test="${systemConfigDetailRo.payType== 3 }" >pills-active</c:if>">快捷支付</span> --%>
    <input type="hidden" id="payType" value="${systemConfigDetailRo.payType}">
    <input type="hidden" id="configId" value="1">
</div>
<div class="wrap-bd bg table-border">
    <div>
    	<c:choose>
    		<c:when test="${systemConfigDetailVo.selectType==0 }">
    			<c:if test="${str != null && str !=''&& str == 'E'}">
    				<input type="radio" name="spGroup" value="0" checked="checked" data-groupname="spgroup" data-tab="item" > 全部定格
        			<input type="radio" name="spGroup" value="1" data-groupname="spgroup" data-tab="item" class="ml-default"> 指定定格
    			</c:if>
    			<c:if test="${str != null && str !=''&& str == 'Q'}">
    				<span data-groupname="spgroup" data-tab="item">全部定格</span>
    				<span data-groupname="spgroup" data-tab="item"></span>
    			</c:if>
    		</c:when>
    		<c:when test="${systemConfigDetailVo.selectType==1 }">
    			<c:if test="${str != null && str !=''&& str == 'E'}">
    				<input type="radio" name="spGroup" value="0" data-groupname="spgroup" data-tab="item"> 全部定格
        			<input type="radio" name="spGroup" value="1" checked="checked" data-groupname="spgroup" data-tab="item" class="active ml-default"> 指定定格
    			</c:if>
    			<c:if test="${str != null && str !=''&& str == 'Q'}">
    				<span data-groupname="spgroup" data-tab="item"></span>
    				<span data-groupname="spgroup" data-tab="item" class="active">指定定格</span>
    			</c:if>
    		</c:when>
    		<c:otherwise>
    			<c:if test="${str != null && str !=''&& str == 'E'}">
	    			<input type="radio" name="spGroup" value="0" checked="checked" data-groupname="spgroup" data-tab="item"> 全部定格
	        		<input type="radio" name="spGroup" value="1" data-groupname="spgroup" data-tab="item" class="ml-default"> 指定定格
	        	</c:if>
	        	<c:if test="${str != null && str !=''&& str == 'Q'}">无数据</c:if>
    		</c:otherwise>
    	</c:choose>
    </div>
    <div data-groupname="spgroup" data-tab="content"  class="hidden" ></div>
    <div data-groupname="spgroup" data-tab="content" <c:if test="${systemConfigDetailVo.selectType==0 }">class="hidden"</c:if>>
    	<c:if test="${str != null && str !='' && str == 'E'}">
        	<div class="button button-operate mt-default mb-small" id="J_btnAddSpGroup">增加定格区域</div>
        </c:if>
        <c:choose>
        	<c:when test="${str != null && str !='' && str == 'E'}">
        		<table class="table-list table-border">
		            <thead class="table-thead">
		            <tr>
		                <th width="50"></th>
		                <th width="190">定格名称</th>
		                <th>定格分区</th>
		                <th>操作</th>
		            </tr>
		            </thead>
		            <tbody class="table-tbody">
		            <tr>
		                <td>
		                    <input type="checkbox" id="J_selectAll"> 全选
		                </td>
		                <td colspan="3" class="ta-l">
		                    <a href="javascript:void(0)" class="delAll">批量删除</a>
		                </td>
		            </tr>
		            <c:forEach var="spGroup" items="${systemConfigDetailVo.spGroupList }" varStatus="var">
		            	 <tr >
			                <td class="ta-l">
			                    <input type="checkbox" name="box" value="${spGroup.id }" class="J_chk">
			                </td>
			                <td>${spGroup.name }</td>
			                <td>${spGroup.areaName }</td>
			                <td>
			                    <a href="javascript:void(0)" class="delOne" >删除</a>
			                </td>
			            </tr>
		            </c:forEach>
		            </tbody>
		        </table>
        	</c:when>
        	<c:otherwise>
        		<c:choose>
        			<c:when test="${systemConfigDetailVo.spGroupList!=null && systemConfigDetailVo.spGroupList.size()>0 }">
        				<table class="table-list table-border">
				            <thead class="table-thead">
				            <tr>
				                <th width="190">定格名称</th>
				                <th>定格分区</th>
				            </tr>
				            </thead>
				            <tbody class="table-tbody">
				            <c:forEach var="spGroup" items="${systemConfigDetailVo.spGroupList }" varStatus="var">
				            	 <tr >
					                <td>${spGroup.name }</td>
					                <td>${spGroup.areaName }</td>
					            </tr>
				            </c:forEach>
				            </tbody>
				        </table>
        			</c:when>
        			<c:otherwise>
        			无数据！
        			</c:otherwise>
        		</c:choose>
        	</c:otherwise>
        </c:choose>
        
    </div>
    <p>
    	<c:if test="${str != null && str !=''&& str == 'E'}">
        	<input type="button" value="确认" class="button button-ok" id="button-ok">
        </c:if>
        <input type="button" value="取消" class="button button-cancel" id="button-cancel">
    </p>
</div>
<div class="dialog hidden" id="J_dialogSelectSpGroup">
    <div class="dialog-head">选择定格区域<div class="dialog-close"></div></div>
    <div class="dialog-body">
        <div class=" dialog-padding">
	        <input type="text" id="search-condition" class="input input-full" placeholder="定格名称">
	        <div class="limit-height mt-small">
	            <%@ include file="../common/ztree.jsp"%>
	            <script src="${root }/resources/js/ztree-search.js"></script>
	        </div>
        </div>
    </div>
    <div class="dialog-foot">
        <input type="button" value="确认" class="dialog-button dialog-ok">
        <input type="button" value="取消" class="dialog-button dialog-cancel">
    </div>
</div>
<div class="cover-all"></div>
<script>
	$('#search-condition').on('keyup', function() {
	    search_ztree('ztree', 'search-condition');
	});
    var zTreeObj;
    var setting = {
        view: {
            showLine: false,
            showIcon: showIconForTree,
            fontCss: setFontCss_ztree
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
            onClick: onClick,
            onCollapse: onClick,
            onExpand: onClick
        }
    };
    var zNodes = null;
    function showIconForTree(treeId, treeNode) {
        return !treeNode.isParent;
    };
    function onClick() {
    	dialogPosCenter('#J_dialogSelectSpGroup');
    }
  
    $(function() {
        tab('spgroup');
        selectAll('#J_selectAll', '.J_chk');
        
    	//加载区域
    	$.post("${root}/Corner/Region/getAllAreaAndSpGroup.do",function(data){
    		if(data.success){
    			zNodes = data.message;
    			zTreeObj = $.fn.zTree.init($("#ztree"), setting, zNodes);
    			/* zTreeObj.expandNode(zTreeObj.getNodeByParam("regionLevel", 1, null));  */
    			zTreeObj.expandAll(true);
    		}
    	},'json');
        
        $('#J_btnAddSpGroup').on('click', function() {
            dialogPosCenter('#J_dialogSelectSpGroup');
            $('#J_dialogSelectSpGroup, .cover-all').show();
        });
        
        $('#J_dialogSelectSpGroup').on('click', '.dialog-ok', function() {
        	var html='';
        	$.each(zTreeObj.getSelectedNodes(),function(i,item){
        		 if(item.level==4){
        	   		if($('input[name="box"][value="'+item.id+'"]').length==0){
        	   			html+='<tr>';
              			html+='<td class="ta-l">';
              			html+='<input type="checkbox" name="box" value="'+item.id+'" class="J_chk">';
              			html+='</td>';
              			html+='<td>'+item.name+'</td>';
              			html+='<td>'+item.getParentNode().name+'</td>';
              			html+='<td>';
              			html+='<a href="javascript:void(0)" class="delOne" >删除</a>';
              			html+='</td>';
              			html+='</tr>';
        	   		}
        		} 
        	});
        	$(".table-tbody").append(html);
            $('#J_dialogSelectSpGroup, .cover-all').hide();
        });
        $('#J_dialogSelectSpGroup').on('click', '.dialog-cancel, .dialog-close', function() {
            $('#J_dialogSelectSpGroup, .cover-all').hide();
        });
    });
    
    //删除一行
    $(".delOne").on("click",function(){
    	$(this).parent("td").parent("tr").remove();
    })
    //删除选中的
    $(".delAll").on("click",function(){
    	$('input[name="box"]:checked').each(function(){ 
    		$(this).parent("td").parent("tr").remove();
   		});
    })
    //取消
    $("#button-cancel").on("click",function(){
    	location.href="${root}/keFu/systemConfig/getAllConfig.do";
    })
    //确定
    $("#button-ok").on("click",function(){
    	var selectType = $("input[name=spGroup]:checked").val();
    	var payType = $("#payType").val();
    	var configId = $("#configId").val();
    	var spGroupId = new Array();
    	var param = null;
    	if(selectType==0){
    		param = {configId:configId,payType:payType,selectType:selectType};
    	}else{
	   		$('input[name="box"]').each(function(){ 
	   			spGroupId.push($(this).val()); 
	   		});
	   		/* if(spGroupId==null || spGroupId.length==0){
	   			alert("请增加定格区域");
	   			return;
	   		} */
	   		param = {configId:configId,payType:payType,selectType:selectType,spGroupId:spGroupId};
    	}
    	$.post("${root}/keFu/systemConfigDetail/updateSystemConfigDetailByPayType.do",param,function(data){
    		if(data.success){
    			alert(data.message);
    			location.reload();
    		}else{
    			alert(data.message);
    		}
    	},'json');
    	
    });
    
    function query(num){
    	if(num==null || num=='null' || num=='undefined'){
    		return;
    	} 
    	$("#payType").val(num);
    	location.href="${root}/keFu/systemConfigDetail/getSystemConfigDetailById.do?payType="+num+"&configId="+$("#configId").val()+"&str=${str}";
    }
</script>
</body>
</html>
