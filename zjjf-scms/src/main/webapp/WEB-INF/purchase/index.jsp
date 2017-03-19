<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>

<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>快销宝 - 采购中心</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <%@ include file="../common/purchase-head.jsp"%>
    <link rel="stylesheet" href="${root}/resources/purchase/css/index.css">
	<link rel="stylesheet" type="text/css" href="${root}/resources/vendor/kkpager-1.3/kkpager_orange_custom.css">
    <script src="${root}/resources/vendor/kkpager-1.3/kkpager.min.js"></script>
</head>
<body>
<%@ include file="../common/purchase-nav.jsp"%>
<header id="header">
    <section class="w clearfix">
        <a href="${root}/scms/procurementcenter/listPage.do"><div class="logo"></div></a>
        <div class="search">
            <div class="search-box">
                <input type="text" class="search-text" id="name1" value="${condition.name }" placeholder="搜搜您需要的商品">
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
                <span class="icon"><i id="caigoudan2">${object}</i></span>
                <span>采购单</span>
            </a>
        </div>
    </section>
</header>
<main id="main">
    <section class="category-cont">
        <div class="w">
            <%@ include file="../common/purchase-category.jsp"%>
            <div class="banner">
                <img src="../../resources/purchase/images/banner.png" alt="">
            </div>
        </div>
    </section>
    <section style="height: 50px; margin-top: 60px;">
        <div class="goods-title-cont" id="J_page">
            <div class="goods-title">
                <span class="glist">
                    <span class="title"><c:if test="${name1!=null}">
                    	${name1}&nbsp;<c:if test="${name2!=null}">&gt;${name2}</c:if>
                    </c:if>
                    <c:if test="${name1==null}">商品列表
                    </c:if></span>
                    <span class="all-category" id="J_allCategory">
                        所有商品分类 <i class="down"></i>
                    </span>
                    <span class="crumb">${name1}&nbsp;<span class="main-color"><c:if test="${name2!=null}">&gt;&nbsp;&nbsp;${name2}</c:if></span></span>
                    		
                </span>
                <div class="operate-section">
                    <div class="sort" id="J_sort">
                        <span class="item" data-value="0">综合</span> <!--  不知道怎么 综合   -->
                        <span class="item" data-value="1">销量<i class="icon icon-xl"></i></span> <!--  销量降序 -->
                        <span class="item" data-value="2">价格<i class="icon icon-jg"></i></span><!-- 2 价格升序  3降序-->
                        <input type="hidden" id="orderNum" value="${condition.orderNum}"/>
                    </div>
                    <div class="search">
                        <form>
                            <input type="text" placeholder="搜搜您需要的商品" id="name2" class="search-ipt-txt" value="${condition.name }">
                            <input type="button" value="" class="search-ipt-btn" onclick="seach()">
                        </form>
                    </div>
                    <input type="hidden" value="${condition.name }" id="productname">
                    <div class="pagination" id="fenye">
                        <!--<span class="prev">&lt;</span>-->
                        <span class="prev disable J_prev">&lt;</span>
                        <span class="main-color J_currPg" ></span>/<span class="J_totalPg" ></span>
                        <span class="next disable J_next">&gt;</span>
                    </div>
                    <a href="${root}/scms/cart/getCartList.do" class="shopcart">
                        <span class="icon"><i id="caigoudan">${object}</i></span>
                        <span>&nbsp;采购单</span>
                    </a>
                </div>
            </div>
        </div>
    </section>
   
    <section class="goods-list-cont clearfix" id="J_goodsList">
     <c:if test="${list.size()<=0}">
    	<div class="no-data">
                            无搜索结果
        </div>
    </c:if>
        
    <input type="hidden" id="cateID" value="${condition.cateID}"/><!-- 二级分类的id -->
    <input type="hidden" id="cateID1" value="${condition.cateID1}"/><!-- 一级分类的id -->
    <c:forEach items="${list}" varStatus="i" var="item">
    		<dl class="goods">
            <dt class="img">
                <a href="${root}/scms/procurementcenter/productDetail.do?id=${item.scmsid}" target="_blank">
                <c:choose>
            		<c:when test="${item.imgS != '' && item.imgS != null}">
                		<img class="fl" src="${USER_FASTFDSPREURL }${item.imgS}" width="150" height="150" alt="" onerror="javascript:this.src='${USER_DEFAULTIMG_URL}'"/>
                	</c:when>
                	<c:otherwise>
                		<img class="fl" src="${USER_DEFAULTIMG_URL}" width="150" height="150" alt=""/>
                	</c:otherwise>
            	</c:choose>
                </a>
                
            </dt>
            <dd class="price">￥${item.zjjfPrice}</dd>
            <dd class="name"><a href="${root}/scms/procurementcenter/productDetail.do?id=${item.scmsid}" target="_blank">${item.goodName}</a></dd>
            <dd class="buy">
                <div class="number-box">
	                <input type="text" value="1" class="input text J_number" maxlength="4" onblur="if(this.value.length==''){this.value=1}" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}">
	                <input type="hidden" value="${item.scmsid}" class="J_id">
	                <div class="div">
	                    <span class="span add J_add"></span>
	                    <span class="span reduce active J_reduce"></span>
	                </div>
                </div>
                <input type="button" value="加入采购单" class="input button" onclick="addShop(this)">
            </dd>
        </dl>
    </c:forEach>
    </section>
    <div class="w clearfix">
        <div id="kkpager"></div>
    </div>
</main>
<%@ include file="../common/purchase-footer.jsp"%>
<script>
function seach(){
	var name=$("#name2").val();
	$("#productname").val(name);
	$("#cateID").val('');
	$("#cateID1").val('');
	var url = kkpager.getHref(1);
	window.location.href=url;
}


function search(){
	var name=$("#name1").val();
	$("#productname").val(name);
	$("#cateID").val('');
	$("#cateID1").val('');
	var url = kkpager.getHref(1);
	window.location.href=url;
}

function searchByItemid(id){
	$("#cateID").val(id);
	$("#cateID1").val('');
	var name1=$("#name1").val();
	$("#productname").val('');

	
	var url = kkpager.getHref(1);
	window.location.href=url;
}

function searchByItemId(id){
	$("#cateID1").val(id);
	$("#cateID").val('');
	$("#productname").val('');
	
	var url = kkpager.getHref(1);
	window.location.href=url;
}

function addShop(obj){
	//数量
	var num=$(obj).prev('.number-box').find('.J_number').val();
	//商品的id
	var id=$(obj).prev('.number-box').find('.J_id').val();
	if(num>10000){
		layer.msg("最多只能买10000件商品");
		return ;
	}
	$.post('${root}/scms/procurementcenter/addShop.do', { "scmsItemId": id,"num":num}, function (data) { 
		layer.msg(data.message);
		$.post('${root}/scms/procurementcenter/selectCount.do', null, function (data) { 
			$("#caigoudan").html(data);
			$("#caigoudan2").html(data);
		},"json");
	},"json");
	
}

function getParameter(name) { 
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r!=null) return unescape(r[2]); return null;
}

    $(function() {
    	$('name1').on('change' , function(){
    		$('name2').val($('name1').val());
    	});
    	$('name2').on('change' , function(){
    		$('name1').val($('name2').val());
    	});
		//分页
    	
    	var totalRecords = ${size}; 
    	
    	var totalPage = (totalRecords % 50 == 0 ? totalRecords / 50 : Math.ceil(totalRecords / 50));
    	var pageIndex = '${pageIndex}';
    
    	if(!pageIndex){
    		pageIndex = 1;
    	}
    	

    	// fenye
    	$("#fenye .J_currPg").html(pageIndex);
    	$("#fenye .J_totalPg").html(totalPage);
    	// prev next 图标状态
    	if(pageIndex > 1) {
    		$("#fenye .J_prev").removeClass('disable');
    	}
    	if(totalPage > 1 && pageIndex < totalPage) {
    		$("#fenye .J_next").removeClass('disable');
    	}
    	// prev next
    	$("#fenye").on('click', '.J_prev:not(.disable)', function() {//前一页
    		var url = kkpager.getHref(parseInt(pageIndex)-1);
    		window.location.href=url;
    	}).on('click', '.J_next:not(.disable)', function() {//后一页
    		var url = kkpager.getHref(1+parseInt(pageIndex));
    		window.location.href=url;
    	});
    	
    	//生成分页
    	//有些参数是可选的，比如lang，若不传有默认值
    	kkpager.generPageHtml({
    		pno : pageIndex,
    		//总页码
    		total :  totalPage,
    		
    		//链接前部
    		hrefFormer : 'listPage',
    		//链接尾部
    		hrefLatter : '.do',
    		mode:'click',
    		click:function(n){
    			
    			window.location.href=this.getHref(n);
    			
    		},
    		getHref : function(n){
    		
    			var url=this.hrefFormer + this.hrefLatter + "?pageIndex="+n;
    			
    			var orderNum=$.trim($("#orderNum").val());
    			if(orderNum != null &&orderNum != undefined && orderNum != ''){
  				   url=url+"&orderNum="+orderNum;
  			  	 }
    			var name=$.trim($("#productname").val());
    			if(name != null &&name != undefined && name != ''){
    				   url=url+"&name="+name;
    			  	 }
    			var cateID=$("#cateID").val();
    			if(cateID != null &&cateID != undefined && cateID != ''){
   				   url=url+"&cateID="+cateID;
   			  	 }
    			var cateID1=$("#cateID1").val();
    			if(cateID1 != null &&cateID1 != undefined && cateID1 != ''){
   				   url=url+"&cateID1="+cateID1;
   			  	 }
    			return url+"&#J_page";
    		}
    		
    	});
    	
    	
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
        /* 排序操作 */
        $('#J_sort').on('click', '.item', function() {
            // $(this).addClass('active').siblings('.item').removeClass('active');
            if($(this).index() == 2) {
            	  if($("#orderNum").val()==2){
            		  $("#orderNum").val(3);
            	  }else{
            		  $("#orderNum").val(2);
            	  }
            }else{
            	$("#orderNum").val($(this).attr("data-value"));
            }
            var url = kkpager.getHref(1);
       	 	window.location.href=url;
        });
        var iii = $("#orderNum").val();
        if(iii ==3) {
        	iii=2;
        }
        $('#J_sort .item').eq(iii).addClass('active').siblings().removeClass('active');
        
        
        /* 数量加减 */
        $('#J_goodsList').on('click', '.J_add', function() {
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
        /* 滚动 */
        var $page = $('#J_page');
        var $category = $('#J_category');
        var $allCategory = $('#J_allCategory');
        var pageTop = $page.offset().top;
        $(window).on('scroll', function() {
            if($(this).scrollTop() >= pageTop) {
                $page.addClass('active');
            } else {
                $page.removeClass('active');
                $category.removeClass('active');
                $category.off('mouseenter').off('mouseleave');
            }
        });
        var flag;
        $allCategory.hover(function() {
            flag = true;
            $category.addClass('active');
        }, function() {
            if(flag) {
                $category.removeClass('active');
            }
            $category.hover(function() {
                flag = false;
                $category.addClass('active');
            }, function() {
                $category.removeClass('active');
            });
        });  
    });
    
        
</script>
</body>
</html>