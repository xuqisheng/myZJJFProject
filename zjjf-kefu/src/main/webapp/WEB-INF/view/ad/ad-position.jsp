<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>广告位管理</title>
    <%@ include file="../common/head.jsp"%>
    <script src="${root}/resources/js/comm.js"></script>
</head>
<body>
<div class="wrap-bd">
    <div class="mb-small">
        <a class="crumb" href="${root}/Customer/AdManage/toAdPosition.do">广告位管理</a><a class="crumb crumb-active">广告位管理</a>
    </div>
    <div class="mb-small clearfix">
        <button class="fr button button-default J_addAd" id="addAdboardButton">添加广告位</button>
    </div>
    <table class="table-list"><!-- 按id升序排序-->
        <thead>
        <tr>
            <th>编号</th>
            <th>名称</th>
            <th>描述</th>
            <th>管理操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="ad" items="${list}">
            <tr>
                <td>${ad.id}</td>
                <td>${ad.name}</td>
                <td>${ad.description}</td>
                <td>
                    <input type="hidden" value="${ad.id}">
                    <span class="button button-operate J_edit">编辑</span>
                    <span class="button button-operate J_delete">删除</span>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="dialog hidden J_dialog" id="adboardDiv">
        <div class="dialog-head"></div>
        <div class="dialog-body">
            <p class="required">
                <label>名称：</label>
                <input type="hidden" name="id" id="id">
                <input class="input input-default" type="text" name="name" id="adName"/>
            </p>
            <p>
                <label>描述：</label>
                <input class="input input-default" type="text" name="description" id="adDesc"/>
            </p>
        </div>
        <div class="dialog-foot">
            <button class="dialog-button dialog-ok">确定</button>
            <button class="dialog-button dialog-cancel">取消</button>
        </div>
    </div>
</div>
<div class="cover-all"></div>
    <script>
        $(function () {
            dialogPosCenter('.J_dialog');
            $('.J_addAd').on('click', function() {
                $('.J_dialog').fadeIn();
                $('.cover-all').fadeIn();
                $('.J_dialog .dialog-head').html("新建广告位");
            });
            $('.dialog-cancel').on('click', function() {
            	$('#adName').val("");
            	$('#adDesc').val("");
            });

            //确定按钮
            $('.J_dialog').on('click', '.dialog-ok', function() {
            	//校验参数
            	if($.trim($("#adName").val())==""){
            		alert("名称不能为空!");
            		return;
            	}

				$.ajax({
					type : "POST",
					url : "${root}/Customer/AdManage/saveAd.do",
					async : false,
					dataType : "json",
					data: {
						name:$("#adName").val(),
						description:$("#adDesc").val(),
						id:$("#id").val()
					},
					success : function(da) {
						if (da.success) {
						  alert(da.message);
						  //上传成功后刷新表单/刷新页面
						  location.href="${root}/Customer/AdManage/toAdPosition.do";

						}
					},
					error : function(da) {
					}
				});
                $('.J_dialog').fadeOut();
                $('.cover-all').fadeOut();
            }).on('click', '.dialog-cancel', function() {
                $('.J_dialog').fadeOut();
                $('.cover-all').fadeOut();
            });

            //编辑按钮
            $('.J_edit').on('click', function(){
                $('.J_dialog').fadeIn();
                $('.cover-all').fadeIn();
                $('.J_dialog .dialog-head').html("广告位修改");
                $.ajax({
						type : "post",
						url : "${root}/Customer/AdManage/editAdPosition.do?id="+$(this).prev().val(),
						dataType : "json",
						success : function(data){
						 if (data.success) {
								$("#adName").val(data.message.name);
								$("#adDesc").val(data.message.description);
								$("#id").val(data.message.id);
							} else {
								alert(data.message);
							}
						},
						error : function(data) {
						}
					});
            });

            //删除按钮
            $('.J_delete').on('click', function() {
                if(confirm("确定删除？")){
                      $.ajax({
  						type : "post",
  						url : "${root}/Customer/AdManage/deleteAdPosition.do?id="+$(this).prev().prev().val(),
  						dataType : "json",
  						success : function(data) {
  							if (data.success) {
  								alert(data.message);
  								window.location.reload();
  							} else {
  								alert(data.message);
  							}
  						},
  						error : function(data) {
  						}
  					});
                }
            });
        })
    </script>
</body>
</html>
