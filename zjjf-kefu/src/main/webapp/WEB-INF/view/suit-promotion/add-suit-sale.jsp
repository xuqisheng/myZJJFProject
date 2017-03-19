<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>增加促销管理</title>
		<%-- <link rel="stylesheet" type="text/css" href="${root}/resources/css/base.css" /> --%>
		<%@ include file="../common/head.jsp"%>
		<link rel="stylesheet" type="text/css" href="${root}/resources/vendor/jquery/ztree/css/zTreeStyle/zTreeStyle.css" />
		<link href="${root}/resources/css/imgupload.css" rel="stylesheet">
		<script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
		<%-- <script src="${root}/resources/vendor/jquery/jquery-1.12.4.min.js"></script> --%>
		<script src="${root}/resources/vendor/jquery/ztree/js/jquery.ztree.all.js"></script>
		<script src="${root}/resources/js/comm.js" type="text/javascript" charset="utf-8"></script>
		<script src="${root}/resources/js/imgupload.js"></script>

		<style type="text/css">
			.ctr-num {
				width: 82px;
				height: 24px;
				position: relative;
				display: inline-block;
			}
			
			.ctr-num a {
				display: block;
				position: absolute;
				top: 0;
				width: 22px;
				height: 22px;
				border: 1px solid #ccc;
				text-align: center;
				text-decoration: none;
				line-height: 22px;
				color: black;
			}
			
			.ctr-num input {
				width: 32px;
				height: 20px;
				text-align: center;
				border: 1px solid #ccc;
				position: absolute;
				left: 24px;
				top: 0;
			}
			
			#goods tr td {
				padding: 5px;
			}
		</style>
	</head>

	<body>
		<div class="wrap-bd">
			<div class="mb-small">
				<a href="#" class="crumb">促销管理</a>
				<a href="#" class="crumb">套装促销</a>
			</div>
			<div class="bg wrap-bd">
				<form action="">
					<div class="mb-default">
						<label class="label">
						活动名称：
					</label>
						<input type="text" name="activeName" value="" class="input input-default mr-default" />

					</div>
					<div class="mb-default">
						<label for="" class="label">
						套装类型：
						</label>
						<select name="" class="select" id="J_select">
							<option value="1" data-groupname="tab" data-tab="item" id="bs" name="suitType">买送</option>
							<option value="2" data-groupname="tab" data-tab="item" id="cb" name="suitType">套装</option>
						</select>
						<script>
							$(function() {
								$('#J_select').on('change', function() {
									var ii = $(this).get(0).selectedIndex;
									console.log(ii);
									$(this).find('option:selected').trigger('click');
								});
								$('#bs').click(function() {
									$('#combine').hide();
									$('#buy-send').show();
									$('#cbPri').hide();
									$('#bs-goods').show();
									$('#cb-goods').hide();
								})
								$('#cb').click(function() {
									$('#combine').show();
									$('#buy-send').hide();
									$('#cbPri').show();
									$('#bs-goods').hide();
									$('#cb-goods').show();
								})
							})
						</script>
					</div>
					<div id="" class="clearfix mb-default">
						<div class="mr-default fl">
							<div class="mr-default">
								<label for="" class="label va-t">
								主商品：
								</label>
								<button class="button button-operate J_dialog-addGoods" type="button">选择商品</button>
							</div>
							<div>
								<label class="label">
								
							</label>
								<div class="dis-ib">
									<table class="table" border="0" id="goods">
										<tr>
											<td><img src="" style="width: 100px;height: 100px;" /></td>
										</tr>
										<tr class="mb-small">
											<td>商品名称</td>
										</tr>
										<tr class="mb-small">
											<td>商品规格</td>
										</tr>
										<tr class="mb-default">
											<td>
												价格
												<input type="text" name="" id="" value="" class="input input-date ml-default" />
											</td>
										</tr>
										<tr class="mb-small">
											<td class="clearfix">
												<span class="va-t">数量</span>
												<div class=" ml-default ctr-num">
													<a href="javascript:void(0);" class="fl" style="left: 0;" data="-">-</a>
													<input type="text" name="" class="num" value="1" />
													<a href="javascript:void(0);" class="fr" style="right: 0;" data="+">+</a>
												</div>
											</td>
										</tr>
									</table>
								</div>
							</div>

						</div>
						<div class="mr-default fl hidden" id="cb-goods">
							<div class="mr-default">
								<label for="" class="label va-t">
								组合商品：
								</label>
								<button class="button button-operate J_dialog-addGoods" type="button">选择商品</button>
							</div>
							<div>
								<label class="label">
							</label>
								<div class="dis-ib">
									<table class="table" border="0" id="goods">
										<tr>
											<td><img src="" style="width: 100px;height: 100px;" /></td>
										</tr>
										<tr class="mb-small">
											<td>商品名称</td>
										</tr>
										<tr class="mb-small">
											<td>商品规格</td>
										</tr>
										<tr class="mb-default">
											<td>
												价格
												<input type="text" name="" id="" value="" class="input input-date ml-default" />
											</td>
										</tr>
										<tr class="mb-small">
											<td class="clearfix">
												<span class="va-t">数量</span>
												<div class="ml-default ctr-num">
													<a href="javascript:void(0);" class="fl" style="left: 0;" data="-">-</a>
													<input type="text" name="" class="num" value="1" />
													<a href="javascript:void(0);" class="fr" style="right: 0;" data="+">+</a>
												</div>
											</td>
										</tr>
									</table>
								</div>
							</div>

						</div>
						<div class="mr-default fl" id="bs-goods">
							<div class="mr-default">
								<label for="" class="label va-t">
								赠送商品：
								</label>
								<button class="button button-operate J_dialog-addGoods" type="button">选择商品</button>
							</div>
							<div>
								<label class="label">								
								</label>
								<div class="dis-ib">
									<table class="table" border="0" id="goods">
										<tr>
											<td><img src="" style="width: 100px;height: 100px;" /></td>
										</tr>
										<tr class="mb-small">
											<td>商品名称</td>
										</tr>
										<tr class="mb-small">
											<td>商品规格</td>
										</tr>
										<tr class="mb-small">
											<td class="clearfix">
												<span class="va-t">数量</span>
												<div class="ml-default ctr-num">
													<a href="javascript:void(0);" class="fl" style="left: 0;" data="-">-</a>
													<input type="text " name=" " value="1 " class="num " />
													<a href="javascript:void(0);" class="fr" style="right: 0;" data="+">+</a>
												</div>
											</td>
										</tr>
									</table>
								</div>
							</div>
						</div>
					</div>
					<div class="mb-default hidden" id="cbPri">
						<label class="label">
						组合售价：
					</label>
						<input type="text" name="" value="" class="input input-default" />
					</div>
					<div class="mb-default">
						<label class="label">
						组合说明：
					</label>
						<input type="text" name="" value="" class="input input-default mr-default" />
					</div>
					<div class="mb-default">
						<label for="" class="label">
							活动时间：
							</label>
						<input type="text" name="startTime" id="" value="" class="input input-date J_timeS" /> -
						<input type="text" name="endTime" onblur="time()" id="" value="" class="input input-date J_timeE mr-default" />
						<span id="times">	
						</span>
					</div>
					<div class="mb-default">
						<label for=" " class="label">
							发布区域：
						</label>
						<span id="buy-send">
							<input type="checkbox" name="publish" id="" value="1" class="checkbox" />热销区域
							<input type="checkbox" name="publish" id="" value="2" class="checkbox ml-default" />特价专区
							<input type="checkbox" name="publish" id="" value="3" class="checkbox ml-default" />爆款推荐
							<input type="checkbox" name="publish" id="" value="4" class="checkbox ml-default" />新品推荐
							<input type="checkbox" name="publish" id="" value="5" class="checkbox ml-default" />品牌促销
						</span>
						<span class="hidden" id="combine">
							<input type="checkbox" name="publish" id="" value="6" class="checkbox combine" />超值套餐							
						</span>
					</div>
					<div class="mb-default">
						<label class="label">
						单个ID限购：
						</label>
						<input type="text" name="buyLimtNum" value="" class="input input-default mr-default" />
					</div>
					<div class="mb-default">
						<label class="label">
						商品标签：
					</label>
						<select name="" class="select">
							<c:forEach items="${tagList}" var="item">
							  <option value="${item.id}">${item.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="mb-default">
						<label class="label">
						参与批发商：
					</label>
						<button class="button button-operate" id="J_dialog-addSupplier" type="button">增加批发商</button>
					</div>
					<table class="table-list mb-default">
						<thead>
							<tr>
								<th>序号</th>
								<th>所属区域</th>
								<th>所属定格</th>
								<th>批发商编号</th>
								<th>批发商名称</th>
								<th>手机号码</th>
								<th>商品限量</th>
								<th>操作 </th>
							</tr>
						</thead>
						<tbody class="table-tbody" id="supplier_info">
							<tr>
								<td>1</td>
								<td>广东/深圳/南山</td>
								<td>定格的名称</td>
								<td>粤B1111</td>
								<td>批发商1</td>
								<td>12345654</td>
								<td><input type="text" name="" id="" value="" class="input input-date" /></td>
								<td><span class="icon-op icon-op-del J_del" title="删除 "></span></td>
							</tr>
						</tbody>
					</table>
					<div class="">
						<span class="button button-ok">确认</span>
						<span class="button button-cancel ml-small">取消</span>
					</div>
				</form>
			</div>

			<div class="dialog hidden J_dialog" id="J_dialogArea">
				<div class="dialog-head">
					选择商品
					<div id="J_dialogClose" class="dialog-close">
					</div>
				</div>

				<div class="dialog-body clearfix">
				<form id="fm">
					<div class="mb-default mb-default clearfix">
						<div class="fl mr-default ">
							<label for="">商品分类:
								<select name="yiJiId" id="firstGrade">
									<option value="-1" class="select">请选择</option>
								</select>
								<select name="erJiId" id="secondGrade">
									<option value="-1" class="select">请选择</option>
								</select>
							</label>
						</div>
						<div class="fr ml-default">
							<label for=" ">商品品牌:
								<input type="text" name="brandName" id="productBrand" value="" class="input input-default"/>
							</label>
						</div>
					</div>
					<div class="mb-default clearfix">
						<div class="fl mr-default">
							<label for="">商品条码:
								<input type="text" name="mdseId" id="productMdseId" value="" class="input input-default mr-small"/>						
							</label>
						</div>
						<div class="fr ml-default">
							<label for="">商品名称:
								<input type="text" name="name" id="productName" value="" class="input input-default"/>
							</label>
						</div>
					</div>
					<div class="fr mb-default">
						<span class="dialog-button dialog-ok ml-default" id="J_dialogSearch">查询</span>
						<span class="dialog-button ml-small" id="J_dialogCancel">重置</span><!--  dialog-cancel -->
					</div>
					</form>
					<table class="table-list mb-default">
						<thead>
							<tr>
								<th>
									<input type="checkbox" name=" " id="J_selectAll" value="" />
								</th>
								<th>商品品牌</th>
								<th>商品条码</th>
								<th>商品名称</th>
								<th>规格</th>
								<th>价格</th>
							</tr>
						</thead>
						<tbody class="table-tbody" id="J_dialogTbody">
							<!-- <tr>
								<td>
									<input type="checkbox" name="" id="" value="" class="checkbox J_chk" />
								</td>
								<td>百事</td>
								<td>1234 5</td>
								<td>
									我的名称
								</td>
								<td>24*300ml</td>
								<td>100</td>
							</tr> -->
							<tr>
								<td colspan="6">暂无数据</td>
							</tr>
						</tbody>
					</table>
					<%@ include file="../common/pagination.jsp"%>
				</div>
				<div class="dialog-foot">
					<div class=" ">
						<span class="dialog-button dialog-ok ml-default" id="add">增加</span>
						<span class="dialog-button ml-small dialog-cancel">关闭</span>
					</div>
				</div>
				</form>
			</div>
			<div class="cover-all">

			</div>
			<div class="dialog hidden J_dialog" id="J_dialogSupplier">
				<form action="" method="post">
					<div class="dialog-head">
						选择批发商
						<div id="" class="dialog-close">
						</div>
					</div>
					<div class="dialog-body">
						<form action="" method="post">
							<div class="mb-default">

								<input type="text" name="" id="" value="" class="input input-default" placeholder="批发商名称" />
								<input type="button" name="" id="" value="搜索" class="button button-default" />
							</div>
						</form>
						<div class="wrap-bd zTreeDemoBackground">
							<ul id="treeDemo" class="ztree" style="height:140px; overflow:auto">
							</ul>
						</div>
					</div>
					<div class="dialog-foot">
						<div class="">
							<span class="dialog-button dialog-ok ml-default" id="conf">确认</span>
							<span class="dialog-button ml-small dialog-cancel">取消</span>
						</div>
					</div>
				</form>
			</div>
		</div>
	</body>
	<script type="text/javascript">
	    //商品分类
		var cateLogList = '';
		$(function() {
			
			/******************************商品查询begin******************************/
			
			//获取商品分类
			$.ajax({
  	    			type : "POST",
  	    			url : "${root}/customer/itemCatelog/loadItemCateLogList.do",
  	    			async : true,
  	    			dataType:"json",
  	    			success : function(da) {
  	    			     if(da.success){
  	    				   cateLogList=da.message;
  	    				 }else{
  	    					 console.log(da.message);
  	    					 layer.msg('获取商品分类失败，请联系技术！');
  	    				 } 
  	    			},
  	    			error : function(da) {
  	    				layer.msg("获取商品分类失败，请联系技术！",{time:1000});
  	    			}
     	    	});
			
			
			//选中商品--商品分类表单联动
			$('#firstGrade').on('change',function(){
				var firstGradeValue = $('#firstGrade').val();
				firstGradeValue = parseInt(firstGradeValue);
				var html = '';
                if(firstGradeValue==-1){
                	html = '<option value="-1" class="select" name="secondGrade">请选择</option>';
                }else{
                	$(cateLogList).each(function(i1,item1){
                		if(item1.id == firstGradeValue){
                			$(item1.catelogVoList).each(function(i2,item2){
                    			html+= '<option value="item2.id" class="select" name="secondGrade">'+item2.name+'</option>';
                    		});
                			return;
                		}
                	});
                }
                $('#secondGrade').html(html);
			});
			
			//重置按钮
			$('#J_dialogCancel').on('click',function(){
				initCateLog();
				$('#firstGrade').trigger('change');
			});
			
			
			//查询按钮
			$('#J_dialogSearch').on('click',function(){
				$('#J_dialogTbody').empty();
				$("#jpagination").pagination('setParams', $('#fm').serializeArray());
	      		$("#jpagination").pagination('setPageIndex', 0);
	      	 	$("#jpagination").pagination('remote');
			});
			
			//查询商品
			$("#jpagination").pagination({
			    pageSize: 5,
			    remote: {
			        url: '${root}/keFu/SKUActive/getGoodsList.do',
			        params: $('#fm').serializeArray(),
			        success: function(data) {
			            var html='';
						if(data.totalSize>0){
							$.each(data.list, function(i,item) {
								html+='<tr>' ;
								html+='<td>' ;
								html+='<input type="checkbox" name="J_chk" id="" value="" class="checkbox J_chk"/>' ;
								html+='<input type="hidden" id="" value="'+item.id+'" class="id" />' ;
								html+='</td>+' ;
								html+='<td><spen class="brandName">';
								if(item.brandName != null && item.brandName != ""){
									html+= item.brandName
								}else{
									html+= '<spen style="font-style: red">无品牌</spen>';
								}
								html+='</spen></td>' ;
								html+='<td><spen class="mdseId">'+item.mdseId+'</spen></td>' ;
								html+='<td><spen class="name">'+item.name+'</spen></td>' ;
								html+='<td><spen class="spec">'+item.spec+'</spen></td>' ;
								if(item.pfPrice==null){
									html+='<td><spen class="pfPrice">0</spen></td>' ;
								}else{
									html+='<td><spen class="pfPrice">'+item.pfPrice+'</spen></td>' ;
								}
								html+='</tr>';
				            });
						}else{
							html = '<tr><td colspan="6">暂无数据</td></tr>';
						}
						console.log(html);
						$('#J_dialogTbody').html(html);
			        },
			        totalName:'totalSize'
			    }
			});
			
			/******************************商品查询end******************************/
			
			
			
			
			
			
			
			
			
			
			//日期
			dateRange('.J_timeS', '.J_timeE', 1);
			if('' == 1) {
				$('#isVoluntary').attr('checked', true);
			} else {
				$('#isVoluntary').attr('checked', false);
			}
			$('#ruleType').val('-1');
			//上传单个图片
			$('#imguploadChose').fileupload({
				complete: function(v) {
					console.log(v);
				}
			});
			
			$('#imguploadSend').fileupload({
				complete: function(v) {
					console.log(v);
				}
			});
			
			//dialog
			dialogPosCenter('#J_dialogArea');
			dialogPosCenter('#J_dialogSupplier');
			$('#J_dialog-addSupplier').on('click', function() {
				$('#J_dialogSupplier, .cover-all').fadeIn();
			});
			$('.J_dialog-addGoods').on('click', function() {
				initCateLog();
				$('#J_dialogArea, .cover-all').fadeIn();
			});
			$('.J_dialog').on('click', '.dialog-cancel', function() {

				$('.J_dialog, .cover-all').fadeOut();
			})
			$('.dialog').on('click', '.dialog-close', function() {

				$('.J_dialog, .cover-all').fadeOut();
			})
			selectAll('#J_selectAll', '.J_chk');
			$('.J_chkone').click(function() {
				$(this).attr("checked", true).siblings().attr("checked", false)
			})
			
			//数量添加
			$(".ctr-num a").click(function() {
				var count = $(this).attr("data");
				$(this).parent().find('input').val(function() {
					var numb = $(this).val();
					count == "-" ? numb-- : numb++;
					if(numb > 1) {
						return numb;
					} else if(numb = 1) {
						var numb = 1;
						return numb;
					}
				})
			});
			
			//定格区域
			var html = "";
			html +=
				'<tr>' +
				'<td>1</td>' +
				'<td>广州/深圳/南山</td>' +
				'<td>定格的名称</td>' +
				'<td>粤B111</td>' +
				'<td>我的名称</td>' +
				'<td>15465653453</td>' +
				'<td><input type="text " name=" " id=" " value=" " class="input input-date" /></td>' +
				'<td><span class="icon-op icon-op-del J_del " title="删除 "></span></td>' +
				'</tr>';
			$('#conf').click(function() {
				$('#supplier_info').append(html);
				$('.J_dialog, .cover-all').fadeOut();
			})
			$('#supplier_info').on('click', '.J_del', function() {
				if(confirm('确认删除？')) {
					$(this).parent('td').parent('tr').remove();
				}
			})

		});
		
		
		
		
		
		
		
		
		
		
		/******************************商品查询begin******************************/
		//初始化商品类别菜单
		function initCateLog(){
			var html = '';
			//生成一级分类html
			html+='<option value="-1" class="select" name="firstGrade">请选择</option>';
			$(cateLogList).each(function(i,item){
				html += '<option value="'+item.id+'" class="select" name="firstGrade">'+item.name+'</option>';
			})
			$('#firstGrade').html(html);
			//清空查询条件
			clearSerarchProductCondition();
		}
		//清空商品查询条件
		function clearSerarchProductCondition(){
			$('#productBrand').val('');			
			$('#productMdseId').val('');			
			$('#productName').val('');
			//$('#J_dialogTbody').empty();
		}
		/******************************商品查询end******************************/
		
		
		
		
		
		
		
		
		
		
		
		
		//地域树
		var setting = {
			view: {
				showIcon: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			check: {
				enable: true,
				nocheckInherit: false
			}
		};

		var zNodes = [
		              {
				name: "广东 ",
				open: true,
				children: [{
					name: "深圳 ",
					children: [{
						name: "南山 ",
					}, {
						name: "福田 ",
					}, {
						name: "宝安 ",
					}, {
						name: "罗湖 ",
					}]
				}, {
					name: "广州 ",
					children: [{
						name: "广州1 "
					}, {
						name: "广州2 "
					}, {
						name: "广州3 "
					}, {
						name: "广州4 "
					}]
				}, {
					name: "汕头 ",
					isParent: true
				}]
			}, {
				name: "北京 ",
				isParent: true
			}

		];
		$(document).ready(function() {
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
	</script>
</html>