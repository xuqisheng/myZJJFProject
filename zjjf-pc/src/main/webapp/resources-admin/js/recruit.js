//获取单个信息
function getRecruitInfo(objectId) {
	$.ajax({
		type: "POST",
		url: ROOT + "/pc/recruit/List.do",
		data: {"id": objectId, "isDelete": false, "pageIndex": 0, "pageSize": 10, "sortOrder": "desc", "sortName": "a.id"},
		success: function(data) {
			var recruitlist = data.rows;
			var total=data.total;
			if(recruitlist && total > 0) {
				var recruitinfo =recruitlist[0];
				$("#recruitId").val(recruitinfo.id);
				$("#recruitTypeId").val(recruitinfo.recruitTypeId);
				$("#postName").val(recruitinfo.postName);
				$("#ueditorContent").val(recruitinfo.content);
				ue.setContent(recruitinfo.content);
			} else {
				alert("未取到数据");
			}
		}
	});
}
//提交表单
function deleteRecruitInfo(objectId) {
	if(confirm("确定删除吗?")) {
		$.ajax({
			type: "POST",
			url:  ROOT + "/pc/recruit/Delete.do",
			data: {"ids": objectId},
			success: function(data) {
				alert(data.message);
				if(data.success) {
					location.reload();
				}
			}
		});
	}
}
//提交表单
function recruitSubmit() {
	if($("#recruitTypeId").val()=="" && $("#recruitTypeId").val()==-1){
		alert("请选择招聘类型!");
		return;
	}
	if($.trim($("#postName").val())==""){
		alert("请填写职位名称!");
		return;
	}
	if($.trim(ue.getContentTxt())==""){
		alert("请填写招聘内容!");
		return;
	}
	if($("#recruitId").val()==""){
		$.ajax({
			type: "POST",
			url: ROOT + "/pc/recruit/Add.do",
			data: $('#recruitForm').serialize(),
			success: function(data) {
				if(data.success) {
                    alert(data.message);
                    location.href = ROOT + "/pc/recruit/RecruitMgPage.do";
				} else {
					alert(data.message);
				}
			}
		});
	} else {
		$.ajax({
			type: "POST",
			url: ROOT + "/pc/recruit/Update.do",
			data: $('#recruitForm').serialize(),
			success: function(data) {
				if(data.success) {
					if(data.success) {
	                    alert(data.message);
	                    location.href = ROOT + "/pc/recruit/RecruitMgPage.do";
					} else {
						alert(data.message);
					}
				}
			}
		});
	}
}
$(function() {
	$(".J_view").on("click", function() {
		var id= $(this).attr("name");
		getRecruitInfo(id);
	});
	$(".J_edit").on("click", function() {
		var id= $(this).attr("name");
		getRecruitInfo(id);
	});
	$(".J_delete").on("click", function() {
		var id= $(this).attr("name");
		deleteRecruitInfo(id);
	});
	$("#recruitSubmit").on("click", function() {
		recruitSubmit();
	});
});