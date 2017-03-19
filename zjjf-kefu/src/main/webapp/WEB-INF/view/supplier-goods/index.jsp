<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>批发商商品管理</title>
<%@ include file="../common/head.jsp"%>
<%@ include file="../common/autocomplete.jsp"%>
<style>
.sp-search{
    text-align: center;
}
.sp-list dl {
    clear:both;
}
.sp-list dl dt {
    float: left;
    width: 5%;
    min-width: 70px;
    height: 34px;
    line-height: 34px;
    color: #999;
    overflow: hidden
}
.sp-list dl dd {
    float: left;
    width: 93%;
    line-height: 34px;
    margin-left: 0;
}
.sp-list dl dd a {
    display: inline-block;
    padding: 0 12px;
    color: #545454;
    text-decoration:none;
}
.sp-list dl dd a:hover {
    color: #e40000;
    text-decoration: underline;
}
.sp-product{
    display: none;
}
.sp-product-info{
    padding: 10px 15px 20px;
    margin: 20px 0;
    border-top: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
    background: #f9f8f8;
}
.sp-product-info .div1{
    height: 26px;
    line-height: 26px;
    font-weight: bold;
}
.sp-product-info .div1 span{
    padding-right: 16px;
}
.sp-product-info .div2{
    margin-bottom: 8px;
    line-height: 25px;
}
.sp-product-info .div2 span{
    padding-right: 8px;
}
.sp-product-info .div2 span:hover{
    color: red;
    cursor: pointer;
}
.red{
  color: red;
 }
</style>
</head>
<body class="wrap-bd">
<div class="sp-search">
    <!-- <form action="#" method="get"> --><!-- ${root}/Customer/SpProduct/getSupplierList.do -->
        <input id="search" class="input input-search-text" type="text" name="" value="${supplier.supplierName}" placeholder="请输入想要查询的批发商" />
        <!--<input class="input input-search-button" value="搜索" type="submit" />-->
    <!-- </form> -->
</div>
<div class="sp-list" id="spList">
   <c:forEach items="${list}" var="list">
    <dl>
        <dt>${list.name}</dt>
          <dd>
           <c:forEach items="${list.list}" var="item">
            <a href="${root}/Customer/SpProduct/getSpGroupAndProduct.do?id=${item.id}">${item.supplierName}</a>
           </c:forEach>
          </dd>
    </dl>
    </c:forEach>
</div>
<div class="sp-product" id="sp_product">
    <div class="sp-product-info table-border clearfix">
        <div class="div1">
            <span id="name">${supplier.supplierName}</span>
            <span>${supplier.mobile}</span>
            <span>${supplier.supplierAddress}</span>
        </div>
        <div class="div2" id="spGroupDiv">
        <input type="hidden" id="spGroupId" value="${spGroupId}">
            定格选择：
            <span class="spTest"  id=""  onclick="spGroupFun('')">全部</span>
            <c:forEach var="spGroup" items="${spList}">
             <span onclick="spGroupFun(${spGroup.id})" class="spTest"  id="${spGroup.id}">${spGroup.name}</span>
            </c:forEach>
        </div><!-- getSpGroupAndProduct.do -->
        <form action="${root}/Customer/SpProduct/getSpGroupAndProduct.do?pageIndex=1" method="post" class="fl">
            <input class="input input-search-text" type="text" name="searchKey" value="${searchKey}" placeholder="请输入商品关键字/条形码" />
            <input type="hidden" name="id" value="${supplier.id}" id="id">
            <input type="hidden" name="spGroupId" value="${spGroupId}" id="searchSpGroupId">
            <input class="input input-search-button" value="搜索" type="submit" />
        </form>
        <a href="${root}/Customer/SpProduct/toPreEdit.do?id=${supplier.id}&pageIndex=${pageIndex}"><button class="button button-default fr">+新增商品</button></a>
    </div>
    <div>
        <table class="table-list table-border">
            <thead class="table-thead">
                <tr>
                    <th>推荐</th>
                    <th>商品条形码</th>
                    <th>排序</th>
                    <th>商品名称</th>
                    <th>一级分类</th>
                    <th>二级分类</th>
                    <th>商品规格</th>
                    <th>所属定格</th>
                    <th>市场价</th>
                    <th>批发商<br />出货价</th>
                    <th>批发商<br />进货价</th>
                    <th>批发商<br />毛利</th>
                    <th>费用</th>
                    <th>费用率</th>
                    <th>详情</th>
                    <th>状态</th>
                    <th>操作</th>
                    <th>备注</th>
                    <th>优惠信息</th>
                </tr>
            </thead>
            <tbody class="table-tbody" id="J_test">
                <c:forEach var="pl" items="${plList}">
                <tr>
                    <td><input type="checkbox" name="chk_list" value="1"  <c:if test="${pl.tuijian==true}">checked="checked"</c:if>/></td>
                    <td>${pl.mdseId}</td>
                    <td class="J_OrderId">
                        <input type="hidden" class="J_Goods" value="${pl.mdseId}"><!-- 商品条形码 -->
                        <input type="hidden" class="J_SpId" value="${pl.spId}"><!--供应商id  -->
                        <input type="hidden" class="J_RegId" value="${pl.spGroupId}"><!-- 定格id -->
                        <span>${pl.ordId}</span>
                    </td>
                    <td>${pl.name}</td>
                    <td>${pl.secCatename}</td>
                    <td>${pl.catename}</td>
                    <td>${pl.spec}</td>
                    <td>${pl.spGroupName}</td>
                    <td class="txt-warn">
                           <c:choose>
                                <c:when test="${empty pl.plantMemPrice }">0.00</c:when>
                                <c:otherwise>${pl.plantMemPrice}</c:otherwise>
                            </c:choose>
                    </td>
                    <td class="txt-warn">${pl.areaPrice }</td>
                    <td class="txt-warn">
                            <c:choose>
                                <c:when test="${empty pl.disPrice }">0.00</c:when>
                                <c:otherwise>${pl.disPrice }</c:otherwise>
                            </c:choose>
                    </td>
                    <td class="txt-warn">
                            <c:choose>
                                <c:when test="${empty pl.maoli }">0.00</c:when>
                                <c:otherwise>${pl.maoli }</c:otherwise>
                            </c:choose>
                    </td>
                    <td class="txt-success">
                            <c:choose>
                                <c:when test="${empty pl.butieMoney }">0.00</c:when>
                                <c:otherwise>${pl.butieMoney }</c:otherwise>
                            </c:choose>
                    </td>
                    <td>
                            <c:choose>
                                <c:when test="${empty pl.areaPrice || pl.areaPrice.unscaledValue() == 0}">0.00</c:when>
                                <c:otherwise>${(pl.butieMoney/pl.areaPrice)*100 }%</c:otherwise>
                            </c:choose>
                    </td>
                    <td>
                    <c:choose>
                        <c:when test="${pl.status==3 }">
                            <span>该商品为供应商自己添加</span>
                        </c:when>
                        <c:otherwise>
                        <a href="${root}/Customer/SpProduct/toPreEdit.do?mdseId=${pl.mdseId}&id=${pl.spId}&spGroupId=${pl.spGroupId}&pageIndex=${pageIndex}">预录入</a>
                        </c:otherwise>
                    </c:choose>
                    </td>
                    <td>
                      <c:choose>
                                <c:when test="${pl.status==1 }">
                                                                           上架
                                </c:when>
                                <c:when test="${pl.status==0 }">
                                                                             下架
                                </c:when>
                                <c:when test="${pl.status==3 }">
                                                                          该商品为供应商自己添加
                                </c:when>
                         </c:choose>
                    </td>
                    <td>
                        <c:choose>
                                <c:when test="${pl.status==1 }">
                                    <input class="button button-operate J_ipt_upordown" id="${pl.id}" type="button" value="下架"><!--  -->
                                </c:when>
                                <c:when test="${pl.status==0 }">
                                    <input class="button button-operate J_ipt_upordown" id="${pl.id}" type="button" value="上架"><!--  -->
                                </c:when>
                                <c:when test="${pl.status==3 }">
                                    <span>该商品为供应商自己添加</span>
                                </c:when>
                         </c:choose>

                         <input class="button button-operate J_clearRestrict" style="display: none" id="${pl.id}" type="button" value="清除限购">
                    </td>
                    <td class="txt-log">${pl.remark}</td>
                    <td class="txt-log">${pl.youHui}</td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <c:if test="${fn:length(plList)>0}">
          <%@ include file="../common/pagination-kk.jsp"%>
   </c:if>
</div>
<script>
    $(function(){
    	var errorMessage = '${errorMessage}';
    	if(errorMessage!=null&&errorMessage!=''){
    		layer.msg(errorMessage+' ☛☛☛☛☛☛☛请联系技术!');
    	}
        var $sp_product = $('#sp_product');
        $sp_product.hide();
        if($.trim($("#search").val())!=""){
            $('#spList').hide();
            $sp_product.slideDown();
        }

        $('#search').autocomplete({
            serviceUrl: "${root}/Customer/SpProduct/getSupplierList.do",
            paramName: 'supplierName',
            dataType:'json',
            transformResult: function(response) {
           	 	return {
           	 		suggestions: $.map(response.message, function(dataItem) {
           	 		return { value: dataItem.supplierName, data: dataItem.id };
                    })
                };
            },
            onSelect: function(dd) {
            	location.href="${root}/Customer/SpProduct/getSpGroupAndProduct.do?id="+dd.data+"&spGroupId=";
            }
       });
    });

    //清除限购按钮
    $('#J_test').on('click','.J_clearRestrict',function(){
    	var id = $(this).attr('id');
    	$.ajax({
            type: "POST",
            url: "${root}/Customer/SpProduct/clearRestrict.do",
            async: true,
            dataType:'json',
            data: {"id": id},
            success: function(da) {
                //$(this).parent('span').html($ordid);
                //location.reload();
                layer.msg(da.message,{time:1000});
            },
            error: function(da) {
            }
        });
    });


    //排序更改
    $('.J_OrderId').on('click', 'span', function(){
        var $goods = $(this).siblings('.J_Goods').val();
        var $spid = $(this).siblings('.J_SpId').val();
        var $regid = $(this).siblings('.J_RegId').val();
        $(this).html('<input type="text" style="width:30px" value="'+ $(this).text().trim() + '" />');
        $(this).children('input[type="text"]').focus();
        $(this).children('input[type="text"]').on("blur", function() {
            var $ordid = $(this).val().trim();
            if (isNaN($ordid)) {
                alert("必须为数字");
            }
            $.ajax({
                type: "POST",
                url: "${root}/Customer/SpProduct/updateOrder.do",
                async: true,
                dataType:'json',
                data: {"mdseId": $goods, "ordid": $ordid,"spid":$spid,"spGroupId":$regid},
                success: function(da) {
                    $(this).parent('span').html($ordid);
                    location.reload();
                },
                error: function(da) {
                }
            });
        });
    });
    //选中状态
        $(".spTest").each(function(i,item){
            if($("#spGroupId").val()==$(this).attr("id")){
                $(this).addClass("red");
            }
        });


    $('#J_test').on('click', '.J_ipt_upordown', function(){
        var id = $(this).attr("id");
        var upordown = $(this).attr("value");
        var cup = "";
        if(upordown == "上架"){
            cup = 1;
            layer.confirm('确定上架吗?',function(){
            	$.ajax({
                    type : "post",
                    url : "${root}/Customer/SpProduct/upordown.do?pageIndex=${pageIndex}",
                    dataType : "json",
                    data :{id:id,cup:cup},
                    success : function(data) {
                        if (data.success) {
                            if(cup == 1){
                            layer.msg('上架成功!页面即将刷新',{tiem:1000},function(){
                            	location.href = "${root}/Customer/SpProduct/getSpGroupAndProduct.do?id=${supplier.id}&pageIndex=${pageIndex}";
                            });
                            }else{
                            	layer.msg('下架成功!页面即将刷新',{tiem:1000},function(){
                                	location.href = "${root}/Customer/SpProduct/getSpGroupAndProduct.do?id=${supplier.id}&pageIndex=${pageIndex}";
                                });
                            };
                        } else {
                        	layer.msg(data.message);
                        }
                    },
                    error : function(data) {
                    }
                });
            },function(){

            });
        }else{
            cup = 0;
            layer.confirm('确定下架吗?',function(){
            	$.ajax({
                    type : "post",
                    url : "${root}/Customer/SpProduct/upordown.do",
                    dataType : "json",
                    data :{id:id,cup:cup},
                    success : function(data) {
                        if (data.success) {
                            if(cup == 1){
                            layer.msg('上架成功!页面即将刷新...',{tiem:1000},function(){
                            	location.href = "${root}/Customer/SpProduct/getSpGroupAndProduct.do?id=${supplier.id}";
                            });
                            }else{
                            	layer.msg('下架成功!页面即将刷新...',{tiem:1000},function(){
                                	location.href = "${root}/Customer/SpProduct/getSpGroupAndProduct.do?id=${supplier.id}";
                                });
                            };
                        } else {
                        	layer.msg(data.message);
                        }
                    },
                    error : function(data) {
                    }
                });
            },function(){

            });
        }
    });

    function spGroupFun(data){
        $("#searchSpGroupId").val(data);
        location.href="${root}/Customer/SpProduct/getSpGroupAndProduct.do?id="+$("#id").val()+"&spGroupId="+data;
    }
</script>
</body>
</html>
