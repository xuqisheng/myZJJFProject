<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setAttribute("root", request.getContextPath());%>
<%@ include file="../../common/taglib.jsp"%>
<!DOCTYPE html>

<head>
	<meta charset="UTF-8">
	<title>供应商管理</title>
	<link rel="stylesheet" type="text/css" href="${root}/resources/css/base.css" />
	<script src="${root}/resources/vendor/jquery/jquery-1.12.4.min.js"></script>
	<script src="${root}/resources/js/comm.js"></script>
</head>
<style type="text/css">
	ul,
	ol,
	li {
		padding: 0;
		margin: 0;
		list-style: none;
	}

	li {
		margin-bottom: 18px;
	}

	ul li select {
		width: 200px;
	}

	ul li input {
		width: 200px;
	}
</style>

<body>
	<div class="wrap-bd">
		<div class="">
			<div class="mb-default title">基础资料</div>
		</div>
		<div class="bg wrap-bd clearfix">
			<ul class="fl" style="width: 33%;">
				<li>
					<label class="label">
							供应商名称：
					</label>
					<span>${detail.managerName}</span>
				</li>
				<li>
					<label class="label">
							固定电话：
						</label>
					<span>${detail.tel}</span>
				</li>
				<li>
					<label class="label">
							职位：
						</label>
					<span>${detail.job}</span>
				</li>
				<li>
					<label class="label">
							仓库联系人：
						</label>
					<span>${detail.branderName}</span>
				</li>
				<li>
					<label class="label">供应商地址：</label>
					<span>${detail.address}</span>
				</li>
			</ul>
			<ul class="fl" style="width: 33%;">
				<li>
					<label class="label">
							状态：
						</label>
					<span>${detail.statusStr}</span>
				</li>
				<li>
					<label class="label">
							传真：
						</label>
					<span>${detail.fax}</span>
				</li>
				<li>
					<label class="label">
							手机号：
						</label>
					<span>${detail.mobile}</span>
				</li>
				<li>
					<label class="label">
							仓库电话：
						</label>
					<span>${detail.whMobile}</span>
				</li>
				<li>
					<label class="label">备注：</label>
					<span>${detail.remark}</span>
				</li>
			</ul>
			<ul class="fl" style="width: 33%;">
				<li>
					<label class="label">
							合作方式：
						</label>
					<span>${detail.cooperationStr}</span>
				</li>
				<li>
					<label class="label">
							联系人：
						</label>
					<span>${detail.branderName}</span>
				</li>
				<li>
					<label class="label">
							Email：
						</label>
					<span>${detail.email}</span>
				</li>
				<li>
					<label class="label">
							仓库手机号：
						</label>
					<span>${detail.whMobile}</span>
				</li>
			</ul>
			<div class="mb-default">

			</div>
			<div class="mb-default">

			</div>
		</div>
		<div class="mt-default">
			<div class="mb-default title">财务信息</div>
		</div>
		<div class="bg wrap-bd clearfix">
			<ul class="fl" style="width: 33%;">
				<li>
					<label class="label">
							开户人：
					</label>
					<span>${detail.bankUserName}</span>
				</li>
				<li>
					<label class="label">
							税号：
						</label>
					<span>${detail.taxNumber}</span>
				</li>
			</ul>
			<ul class="fl" style="width: 33%;">
				<li>
					<label class="label">
							开户银行：
						</label>
					<span>${detail.bankName}</span>
				</li>
				<li>
					<label class="label">
							税率：
						</label>
					<span>${detail.taxRate}%</span>
				</li>
			</ul>
			<ul class="fl" style="width: 33%;">
				<li>
					<label class="label">
							银行账户：
						</label>
					<span>${detail.bankNum}</span>
				</li>
				<li>
					<label class="label">
							结算方式：
						</label>
					<span>
                        <c:choose>
                            <c:when test="${detail.cleaningDayStatus == 0}">预付</c:when>
                            <c:when test="${detail.cleaningDay == 1}">到付</c:when>
                            <c:otherwise>账期${detail.cleaningDay}天</c:otherwise>
                        </c:choose>
					</span>
				</li>
			</ul>
		</div>
	</div>
</body>
<script type="text/javascript">
	//定格区域树
</script>

</html>
