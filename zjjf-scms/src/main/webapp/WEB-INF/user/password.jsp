<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<% request.setAttribute("root", request.getContextPath());%>
<%
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragrma","no-cache");
response.setDateHeader("Expires",0);
%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<title>修改密码</title>
	<%@ include file="../common/head.jsp"%>
		<style>
		.bar-box {
			position: relative;
			width: 90px;
			height: 6px;
			display: inline-block;
			background: #ccc;
			vertical-align: middle;
		}
		.bar-box .bar {
			position: absolute;
			top: 0;
			left: 0;
			height: 6px;
			display: inline-block;
		}
		.bar-box .red {
			width: 33.33%;
			background: #ed4b1c;
		}
		.bar-box .yellow {
			width: 66.66%;
			background: #f4a925;
		}
		.bar-box .green {
			width: 100%;
			background: #62b618;
		}
	</style>
</head>
<body class="wrap-bd">
	<div class="wrap-bd bg">
		<div class="mb-default clearfix">
			<b class="label fl">登录密码</b>
			<div class="fl">
				<span>为保护账户安全，建议您定期修改密码</span>
				<label class="label"></label>
				<c:if test="${USER_TYPE_KEY != null && USER_TYPE_KEY =='USER_SUPPLIER'}">
					<a href="${root}/scms/password/returnUpdateLoginPassword.do">修改密码</a>
					<p>
						安全程度：<c:if test="${supplier.col2==null || supplier.col2==''}">请修改您的登录密码</c:if>
						<c:if test="${supplier.col2==0}">
						<span class="bar-box"><span class="bar red"></span></span> 低
						</c:if>
						<c:if test="${supplier.col2==1}">
						<span class="bar-box"><span class="bar yellow"></span></span> 中
						</c:if>
						<c:if test="${supplier.col2==2}">
						<span class="bar-box"><span class="bar green"></span></span> 高
						</c:if>
					</p>
				</c:if>
				<c:if test="${USER_TYPE_KEY != null && USER_TYPE_KEY =='USER_SCMSMANAGER'}">
					<a href="${root}/scms/password/returnUpdateLoginPassword.do">修改密码</a>
					<p>
						安全程度：<c:if test="${scmsManager.col2==null || scmsManager.col2==''}">请修改您的登录密码</c:if>
						<c:if test="${scmsManager.col2==0}">
						<span class="bar-box"><span class="bar red"></span></span> 低
						</c:if>
						<c:if test="${scmsManager.col2==1}">
						<span class="bar-box"><span class="bar yellow"></span></span> 中
						</c:if>
						<c:if test="${scmsManager.col2==2}">
						<span class="bar-box"><span class="bar green"></span></span> 高
						</c:if>
					</p>
				</c:if>
				<c:if test="${USER_TYPE_KEY != null && USER_TYPE_KEY =='USER_WAREHOUSE'}">
					<a href="${root}/scms/password/returnUpdateLoginPassword.do">修改密码</a>
					<p>
						安全程度：<c:if test="${scmsWarehouse.col2==null || scmsWarehouse.col2==''}">请修改您的登录密码</c:if>
						<c:if test="${scmsWarehouse.col2==0}">
						<span class="bar-box"><span class="bar red"></span></span> 低
						</c:if>
						<c:if test="${scmsWarehouse.col2==1}">
						<span class="bar-box"><span class="bar yellow"></span></span> 中
						</c:if>
						<c:if test="${scmsWarehouse.col2==2}">
						<span class="bar-box"><span class="bar green"></span></span> 高
						</c:if>
					</p>
				</c:if>
			</div>
		</div>
		<c:if test="${USER_TYPE_KEY != null && USER_TYPE_KEY =='USER_SUPPLIER'}">
			<c:if test="${empty supplier.payPassword}">
			<div class="mb-default">
				<label class="label"></label>
				<a href="${root}/scms/password/toUpdatePayPassword.do" class="button-operate">设置支付密码</a>
			</div>
			</c:if>
			<c:if test="${!empty supplier.payPassword}">
			<div class="clearfix" id="payPassWordDiv">
				<b class="label fl">支付密码</b>
				<div class="fl">
					<span>为了您的财产安全，请保管好支付密码</span>
					<label class="label"></label>
					<a href="${root}/scms/password/toUpdatePayPassword.do">修改密码</a>
					<p>
						安全程度：
						<c:if test="${supplier.col1==0}">
						<span class="bar-box"><span class="bar red"></span></span> 低
						</c:if>
						<c:if test="${supplier.col1==1}">
						<span class="bar-box"><span class="bar yellow"></span></span> 中
						</c:if>
						<c:if test="${supplier.col1==2}">
						<span class="bar-box"><span class="bar green"></span></span> 高
						</c:if>
					</p>
				</div>
			</div>
		   </c:if>
		</c:if>
		<c:if test="${USER_TYPE_KEY != null && USER_TYPE_KEY =='USER_WAREHOUSE'}">
			<c:if test="${empty scmsWarehouse.payPassword}">
			<div class="mb-default">
				<label class="label"></label>
				<a href="${root}/scms/password/toUpdatePayPassword.do" class="button-operate">设置支付密码</a>
			</div>
			</c:if>
			<c:if test="${!empty scmsWarehouse.payPassword}">
			<div class="clearfix" id="payPassWordDiv">
				<b class="label fl">支付密码</b>
				<div class="fl">
					<span>为了您的财产安全，请保管好支付密码</span>
					<label class="label"></label>
					<a href="${root}/scms/password/toUpdatePayPassword.do">修改密码</a>
					<p>
						安全程度：
						<c:if test="${scmsWarehouse.col1==0}">
						<span class="bar-box"><span class="bar red"></span></span> 低
						</c:if>
						<c:if test="${scmsWarehouse.col1==1}">
						<span class="bar-box"><span class="bar yellow"></span></span> 中
						</c:if>
						<c:if test="${scmsWarehouse.col1==2}">
						<span class="bar-box"><span class="bar green"></span></span> 高
						</c:if>
					</p>
				</div>
			</div>
		   </c:if>
		</c:if>
	</div>
</body>
</html>