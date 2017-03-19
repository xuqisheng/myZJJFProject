<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>促销管理</title>
		<link rel="stylesheet" type="text/css" href="${root}/resources/css/base.css" />
		<script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
		<script src="${root}/resources/vendor/jquery/jquery-1.12.4.min.js"></script>
		<script src="${root}/resources/js/comm.js" type="text/javascript" charset="utf-8"></script>
	</head>

	<body>
		<div class="wrap-bd">
			<div class="mb-small">
				<a href="#" class="crumb">促销管理</a>
				<a href="#" class="crumb">套装促销</a>
			</div>
			<div class="mb-small clearfix">
				<div class="fl mt-default">套装促销</div>
				<a href="${root}/suitPromotion/toAddSuitPromotion.do" class="button button-default fr">新增套装促销</a>
			</div>
			<div class="bg wrap-bd">
				<div class=" mb-default">
					<form action="" method="post">
						<label for="">
							活动时间：
							<input type="text" name="ruleStart" id="" value="" class="input input-date J_timeS" />
							至
							<input type="text" name="ruleEnd" id="" value="" class="input input-date J_timeE" />
						</label>
						<label for="" class="ml-default">
							活动名称：
							<input type="text" name="" id="" value="" class="input input-search-text ml-default"/>
						</label>
						<input type="button"  value="搜索" class="input input-search-button ml-default" id="search" />
					</form>
				</div>
				<table class="table-list table-border">
					<thead class="table-thead">
						<tr>
							<th>序号</th>
							<th>活动名称</th>
							<th>类型</th>
							<th>主商品</th>
							<th>售价</th>
							<th>说明</th>
							<th>活动时间</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody class="table-tbody">
						<tr>
							<td>1</td>
							<td>转角活动</td>
							<td>买送</td>
							<td>商品名</td>
							<td>31.5</td>
							<td>说明</td>
							<td>2016-5-20-2016-06-20</td>
							<td>新建</td>
							<td>
								<input type="button" name="" class="button button-operate" id="" value="启用" />
								<input type="button" name="" class="button button-operate" id="" value="停用" />
								<input type="button" name="" class="button button-operate" id="" value="编辑" />		
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</body>
<script type="text/javascript">
    $(function() {
    	//日期
    	dateRange('.J_timeS', '.J_timeE', 1);   	
    	if(''==1){
    		$('#isVoluntary').attr('checked',true);
    	}else{
    		$('#isVoluntary').attr('checked',false);
    	}
    	$('#ruleType').val('-1');
})
</script>
</html>