<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>

<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>快销宝 - 商品详情</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <%@ include file="../common/purchase-head.jsp"%>
    <link rel="stylesheet" href="${root}/resources/purchase/css/item.css">
</head>
<body>
<%@ include file="../common/purchase-nav.jsp"%>
<header id="header">
    <section class="w">
        <a href="${root}/scms/procurementcenter/listPage.do"><div class="logo"></div></a>
        <div class="search">
            <div class="search-box">
                <input type="text" class="search-text" id="name" placeholder="搜搜您需要的商品">
                <input type="button" value="搜索" class="search-button" onclick="search()">
            </div>
            <div class="keyword">
                <a href="${root}/scms/procurementcenter/listPage.do?name=百威啤酒">百威啤酒</a>
                <a href="${root}/scms/procurementcenter/listPage.do?name=青岛啤酒">青岛啤酒</a>
                <a href="${root}/scms/procurementcenter/listPage.do?name=红牛">红牛</a>
                <a href="${root}/scms/procurementcenter/listPage.do?name=怡宝">怡宝</a>
                <a href="${root}/scms/procurementcenter/listPage.do?name=康师傅" >康师傅</a>
                <a href="${root}/scms/procurementcenter/listPage.do?name=百事">百事</a>
                <a href="${root}/scms/procurementcenter/listPage.do?name=统一">统一</a>
                <a href="${root}/scms/procurementcenter/listPage.do?name=达利园">达利园</a>
                <a href="${root}/scms/procurementcenter/listPage.do?name=加多宝">加多宝</a>
                <a href="${root}/scms/procurementcenter/listPage.do?name=劲酒" class="last">劲酒</a>
            </div>
        </div>
        <div class="order">
             <a href="${root}/scms/scOrder/myOrderInfo.do" class="order-track">我的采购单</a>
            <a href="${root}/scms/cart/getCartList.do" class="shopcart">
                <span class="icon"><i id="caigoudan">${object}</i></span>
                <span>&nbsp;采购单</span>
            </a>
        </div>
    </section>
</header>
<section class="category-cont">
    <div class="w">
        <div class="all" id="J_allCategory">所有商品分类</div>
        <%@ include file="../common/purchase-category.jsp"%>
    </div>
</section>
<main class="w">
    <section class="product-detail clearfix" id="J_product">
        <div class="pic">
            <c:choose>
           		<c:when test="${itemBase.imgB != '' && itemBase.imgB != null}">
               		<img class="fl" src="${USER_FASTFDSPREURL }${itemBase.imgB}" width="418" height="418" alt="商品图片" onerror="javascript:this.src='${USER_DEFAULTIMG_URL}'"/>
               	</c:when>
               	<c:otherwise>
               		<img class="fl" src="${USER_DEFAULTIMG_URL}" width="418" height="418" alt="商品图片">
               	</c:otherwise>
           	</c:choose>
        </div>
        <div class="info">
            <h1>${itemBase.goodName}</h1>
            <div class="panel">
                <span class="label">价格</span><span class="price">￥${itemBase.zjjfPrice}</span><br>
                <span class="label">规格</span>${itemBase.spec}
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span class="label">商品条形码：</span>${itemBase.mdseId}
            </div>
            <br>
            <c:if test="${itemBase.year!=0||itemBase.month!=0}">
            	<p>
	                <span class="label">生产年月</span><c:if test="${itemBase.year!=0}">${itemBase.year}年</c:if>
	                <c:if test="${itemBase.month!=0}">${itemBase.month}月</c:if>
	            </p>
            </c:if>
            
            <p>
                <span class="label">单位</span>${itemBase.pkg}
            </p>
            <div>
                <span class="label">数量</span>
                <span class="number-box">
                    <input type="text" value="1" maxlength="4" id="num" class="input text J_number" onblur="if(this.value.length==''){this.value=1}" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}">
                   	<input type="hidden" value="${itemBase.scmsid}" class="J_id" id="scmsId">
                    <div class="div">
                        <span class="span add J_add"></span>
                        <span class="span reduce active J_reduce"></span>
                    </div>
                </span>
            </div>
            <br><br>
            <div class="buy" id="J_addShopcartAnimation">
                <span class="label"></span>
                <input type="button" value="&nbsp;&nbsp;&nbsp;&nbsp;加入采购单" class="button button-buynow" onclick="addShop(this)">
                <div class="add-shopcart-img">
                	<c:choose>
	            		<c:when test="${itemBase.imgB != '' && itemBase.imgB != null}">
	                		<img class="fl" src="${USER_FASTFDSPREURL }${itemBase.imgB}"  width="30" height="30" alt="" onerror="javascript:this.src='${USER_DEFAULTIMG_URL}'"/>
	                	</c:when>
	                	<c:otherwise>
	                		<img class="fl" src="${USER_DEFAULTIMG_URL}" width="30" height="30" alt="">
	                	</c:otherwise>
	            	</c:choose>
                </div>
            </div>
        </div>
    </section>
    <section class="product-desc">
        <div class="title">商品详情</div>
        <div class="info">
           	${itemBase.mark}
        </div>
    </section>
</main>

<%@ include file="../common/purchase-footer.jsp"%>
<script>
function search(){
	var url = '${root}/scms/procurementcenter/listPage.do';
	var name=$("#name").val();
	if(name != null &&name != undefined && name != ''){
		   url=url+"?&name="+name;
	  	 }
	 window.location.href=url;
}

function searchByItemid(id){
	var url = '${root}/scms/procurementcenter/listPage.do';
	if(id != null &&id != undefined && id != ''){
		   url=url+"?&cateID="+id;
	  	 }
	window.location.href=url;
}

function searchByItemId(id){
	var url = '${root}/scms/procurementcenter/listPage.do';
	if(id != null &&id != undefined && id != ''){
		   url=url+"?&cateID1="+id;
	  	 }
	window.location.href=url;
}

function addShop(obj) {
	//数量
	var num=$("#num").val();
	//商品的id
	var id=$("#scmsId").val();
	if(num > 10000) {
		layer.msg("最多只能买10000件商品");
		return ;
	}
	$.post('${root}/scms/procurementcenter/addShop.do', { "scmsItemId": id,"num":num}, function (data) { 
		if(data.success) {
			$('#J_addShopcartAnimation .add-shopcart-img').addClass('active');
		    var pp = setInterval(function() {
                $.post('${root}/scms/procurementcenter/selectCount.do', null, function(data) {
                    $("#caigoudan").html(data);
                    clearInterval(pp);
                    $('#J_addShopcartAnimation .add-shopcart-img').removeClass('active');
                }, "json");
		    }, 1000);
		}
	}, "json");
}

    $(function() {

        /* 类目 */
        $('#J_nav').on('mouseover', 'li', function() {
            var index = $(this).index();
            $(this).addClass('active').siblings('li').removeClass('active');
            $('#J_subNav .sub-category').eq(index).addClass('active').siblings('.sub-category').removeClass('active');
        });
        $('#J_category').on('mouseout', function() {
            $('#J_nav li, #J_subNav .sub-category').removeClass('active');
        });
        $('#J_subNav').on('mouseover', '.sub-category', function() {
            $(this).addClass('active').siblings('.sub-category').removeClass('active');
        }).on('mouseout', function() {
            $(this).removeClass('active');
        });

        var flag;
        $('#J_allCategory').hover(function() {
            flag = true;
            $('#J_category').addClass('show');
        }, function() {
            if(flag) {
                $('#J_category').removeClass('show');
            }
            $('#J_category').hover(function() {
                flag = false;
                $('#J_category').addClass('show');
            }, function() {
                $('#J_category').removeClass('show');
            });
        });

        /* 数量加减 */
        $('#J_product').on('click', '.J_add', function() {
            var $number = $(this).parent('.div').parent('.number-box').find('.J_number');
            $(this).parent('.div').find('.J_reduce').removeClass('active');
            if($number.val() == 1)
            	$number.val(10);
            else
            	$number.val(parseInt($number.val()) + 10);
        }).on('click', '.J_reduce', function() {
            var $number = $(this).parent('.div').parent('.number-box').find('.J_number');
            if($number.val()  == 1) {
                return false;
            } else if($number.val()  == 2) {
                $number.val(parseInt($number.val()) - 1);
                $(this).parent('.div').find('.J_reduce').addClass('active');
            } else {
                $number.val(parseInt($number.val()) - 1);
            }
        });

    });
</script>
</body>
</html>