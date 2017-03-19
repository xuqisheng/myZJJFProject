<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>街坊店宝 - 商铺用户组管理</title>
	<%@ include file="../common/head.jsp"%>
    <script src="${root}/resources/js/comm.js"></script>
</head>
<body>
<div class="wrap-bd">
    <div class="mb-small clearfix">
        <form class="fl" id="searchForm" method="post" action="${root}/Customer/store/getStoreGroupList.do">
            <input class="input input-search-text" type="text" name="keyStr" value="${keyStr}" placeholder="请输入商铺组名称/商铺组编号
">
            <input class="input input-search-button" value="搜索" type="submit">
        </form>
        <div class="fr">
            <input type="button" value="新增组" id="J_newShopGroup" class="button button-default">
        </div>
    </div>
    <div>
        <table class="table-list table-border">
            <thead class="table-thead">
            <tr class="table-header">
                <th>编号</th>
                <th>商铺组名称</th>
                <th>商铺数量</th>
                <th>活动数</th>
                <th>满送数</th>
                <th>注册送数</th>
                <th>满减数</th>
                <th>创建时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody class="table-tbody">
            <c:forEach items="${list}" var="item">
            <tr>
                <td>${item.code}</td>
                <td>${item.name}</td>
                <td><a href="${root}/Customer/store/getSpAcGroupWithStoreList.do?id=${item.id}">${item.totalStore}</a></td>
                <td>${item.huodong}</td>
                <td>${item.mansong}</td>
                <td>${item.zhuce}</td>
                <td>${item.manjian}</td>
                <td><fmt:formatDate value="${item.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>
                    <input type="hidden" value="${item.id}">
                    <input class="button button-operate J_editShopGroup" type="button" value="编辑">
                    <input class="button button-operate J_deleteShopGroup" type="button" value="删除">
                </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <c:if test="${fn:length(list)>0}">
		<%@ include file="../common/pagination-kk.jsp"%>
	</c:if>
	
    <div class="dialog hidden" id="J_shopGroup">
        <div class="dialog-head"></div>
        <div class="dialog-body">
            <form action="#" id="shopGroupForm">
                <p>
                    <label>商铺组编号：</label>
                    <input class="input input-default" type="text" value="" name="code" id="code">
                </p>
                <p>
                    <input type="hidden" value="" name="id" id="id">
                    <label>商铺组名称：</label>
                    <input class="input input-default" type="text" value="" name="name" id="name">
                </p>
                <p>
                	<label>商品组区域：</label>
					<select name="provinceId" id="sheng" class="select" onchange="getArea(1)">
						<option value="-1">全部</option>
						<%-- <option value="-1">全部</option>
						<c:forEach var="sheng" items="${shengList }">
							<option <c:if test="${shengId==sheng.id }">selected="selected"</c:if> value="${sheng.id }">${sheng.name }</option>
						</c:forEach> --%>
					</select>
					<select name="cityId" class="select" id="shi" onchange="getArea(2)">
						<option value="-1">全部</option>
						<%-- <c:choose>
							<c:when test="${shiList==null || shiList.size()==0 }">
								<option value="-1">全部</option>
							</c:when>
							<c:otherwise>
								<c:forEach var="shi" items="${shiList }">
									<option <c:if test="${shiId==shi.id }">selected="selected"</c:if> value="${shi.id }">${shi.name }</option>
								</c:forEach>
							</c:otherwise>
						</c:choose> --%>
					</select>
					<select id="area" class="select" name="areaId">
						<option value="-1">全部</option>
						<%-- <c:choose>
							<c:when test="${areaList== null || areaList.size()==0 }">
								<option value="-1">全部</option>
							</c:when>
							<c:otherwise>
								<c:forEach var="area" items="${areaList }">
									<option <c:if test="${supplierDetail.areaid==area.id }">selected="selected"</c:if> value="${area.id }">${area.name }</option>
								</c:forEach>
							</c:otherwise>
						</c:choose> --%>
					</select>
                </p>
                <div class="dialog-foot">
                    <button type="button" class="dialog-button dialog-ok" id="test">确定</button>
                    <button type="button" class="dialog-button dialog-cancel">取消</button>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="cover-all"></div>
<script>
function getArea(num){
	var pId = 0
	if(num=='1'){
		$("#shi").html('<option value="-1">全部</option>');
		$("#area").html('<option value="-1">全部</option>');
		pId = $("#sheng").val();
		$.post("${root}/Corner/Region/getRegionByPidOrRegionLevel.do",{pId:pId},function(data){
			if(data.success){
				var html = '<option value="-1">全部</option>';
				$.each(data.message,function(i,item){
					html += '<option value="'+item.id+'">'+item.name+'</option>';
				})
				$("#shi").html(html);
			}else{
				$("#shi").html('<option value="-1">全部</option>');
			}
		});
	}else if(num=='2'){
		$("#area").html('<option value="-1">全部</option>');
		pId = $("#shi").val();
		$.post("${root}/Corner/Region/getRegionByPidOrRegionLevel.do",{pId:pId},function(data){
			if(data.success){
				var html = '<option value="-1">全部</option>';
				$.each(data.message,function(i,item){
					html += '<option value="'+item.id+'">'+item.name+'</option>';
				})
				$("#area").html(html);
			}else{
				$("#area").html('<option value="-1">全部</option>');
			}
		});
	}
}

    $(function() {
        dialogPosCenter('#J_shopGroup');

        var $shopGroup = $('#J_shopGroup');
        // 新增组
        $('#J_newShopGroup').on('click', function() {
            $shopGroup.find('.dialog-head').html("新增组");
            $.post("${root}/Corner/Region/getRegionByPidOrRegionLevel.do",{pId:1},function(data){
            	if(data.success){
            		var html='<option value="-1">全部</option>';
            		$.each(data.message,function(i,item){
            			html+='<option value="'+item.id+'">'+item.name+'</option>';
            		})
            		$("#sheng").html(html);
            	}
            })
            $('#code').val('');
            $("#name").val("");
            $(".cover-all").show();
            $shopGroup.show();
        });
        // 编辑组
        $('.J_editShopGroup').on('click', function() {
        	console.log(11111111);
            $shopGroup.find('.dialog-head').html("编辑组");
            var id = $(this).prev('input').val();
            $('#id').val(id);
            //var name = $(this).parent('td').prev().prev().prev().text();
            var name = $(this).parent('td').parent('tr').children('td').eq(1).text();
            $("#name").val(name);
            var code = $(this).parent('td').parent('tr').children('td').eq(0).text();
            $("#code").val(code);
            $(".cover-all").show();
           
            $.post("${root}/Customer/store/selectByPrimaryKey.do",{id:id},function(data){
            	if(data.success){
            		var provinceId = data.message.provinceId;
            		var cityId = data.message.cityId;
            		var areaId = data.message.areaId;
            		console.log(provinceId);
            		console.log(cityId);
            		console.log(areaId);
            		$.post("${root}/Customer/SpGroup/getRegion.do",{pid:1},function(data){
						if (data.success) {
							var t = '<option value="-1" selected="selected">全部</option>';
							$.each(data.message,function(i,item){
								if(item.id==provinceId){
									t+='<option value="'+item.id+'" selected="selected">'+item.name+'</option>';
								}else{
									t+='<option value="'+item.id+'">'+item.name+'</option>';
								}
							});
							$("#sheng").empty();
							$("#sheng").append(t);
		            		$.post("${root}/Customer/SpGroup/getRegion.do",{pid:provinceId},function(data){
								if (data.success) {
									var t = '<option value="-1" selected="selected">全部</option>';
									$.each(data.message,function(i,item){
										if(item.id==cityId){
											t+='<option value="'+item.id+'" selected="selected">'+item.name+'</option>';
										}else{
											t+='<option value="'+item.id+'">'+item.name+'</option>';
										}
									});
									$("#shi").empty();
									$("#shi").append(t);
									$.post("${root}/Customer/SpGroup/getRegion.do",{pid:cityId},function(data){
										if (data.success) {
											var t = '<option value="-1" selected="selected">全部</option>';
											$.each(data.message,function(i,item){
												if(item.id==areaId){
													t+='<option value="'+item.id+'" selected="selected">'+item.name+'</option>';
												}else{
													t+='<option value="'+item.id+'">'+item.name+'</option>';
												}
											});
											$("#area").empty();
											$("#area").append(t);
										} else {
											alert(data.message);
										}
									});
									
								}else{
									alert(data.message);
								}
							})
						} else {
							alert(data.message);
						}
					});
            	}
            })
            $shopGroup.show();
        });

        
        //删除
        $('.J_deleteShopGroup').on('click',function(){
        	var id = $(this).prev().prev().val();
        	if(confirm('删除用户组,会删除组下的店铺')){
        		$.ajax({
        			type : "POST",
        			url : "${root}/Customer/store/deleteSpAcGroup.do",
        			async : false,
        			data : {'id':id},
        			success : function(da) {
        				if (da.success) {
        					alert("删除用户成功!");
        		            location.href='${root}/Customer/store/getStoreGroupList.do?s=1';
        				}else{
        					alert(da.message);
        				}
        			},
        			error : function(da) {
        			}
        		});
        	}
        });
        
        
        // 确定
        $shopGroup.find('.dialog-ok').on('click', function() {
        	if ($.trim($('#code').val()) == "") {
                alert("编号不能为空!");
                retrun;
            };
            if ($.trim($('#name').val()) == "") {
                alert("名称不能为空!");
                retrun;
            };
            $('#test').hide();
            $.ajax({
    			type : "POST",
    			url : "${root}/Customer/store/addStoreGroup.do",
    			async : false,
    			data : $("#shopGroupForm").serialize(),
    			success : function(da) {
    				if (da.success) {
    					var html = $shopGroup.find('.dialog-head').html();
    					if(html=='编辑组'){
    						alert('编辑成功!');	
    					}else{
    						alert('新增成功!');	
    					}
    		            location.href='${root}/Customer/store/getStoreGroupList.do?s=1';
    				}else{
    					alert(da.message);
    					$('#test').show();
    				}
    			},
    			error : function(da) {
    			}
    		});
        });
        // 取消
        $shopGroup.find('.dialog-cancel').on('click', function() {
        	$("#sheng").val("-1");
        	$("#shi").val("-1");
        	$("#area").val("-1");
        	$("#code").val("");
        	$("#name").val("");
            $shopGroup.hide();
            $(".cover-all").hide();
        });
    });
</script>
</body>
</html>