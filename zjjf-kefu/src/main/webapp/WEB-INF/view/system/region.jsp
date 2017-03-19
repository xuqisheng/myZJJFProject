<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>区域管理</title>
		<%@ include file="../common/head.jsp"%>
		<script src="${root}/resources/js/ztree-search.js"></script>
		<script src="${root}/resources/js/comm.js"></script>

	</head>

	<body class="wrap-bd">
		<div class="fl bg" style="width: 20%; overflow: auto">
			<div class="ml-default mb-default mt-default">
				<input type="text" name="" id="search-condition" onkeydown="entersearch1()" value="" class="input input-default" placeholder="名称" style="width:200px ;" />
				<input type="button" name="" id="" onclick="search_ztree('ztree','search-condition')" value="搜索" class="button button-default" />				
			</div>
			<div class="box-sz wrap-bd bg border" style="margin-top: -1px;">
				<%@ include file="../common/ztree.jsp"%>
			</div>
		</div>
		<div class="fr box-sz wrap-bd bg border" id="editpage" style="width: 79%;">
			请选择左边区域
			<!--     <div>
        <label class="label">名称：</label>
        南山区
    </div>
    <p>
        <label class="label">编号：</label>
        <input type="text" class="input input-default">
    </p>
    <p>
        <label class="label">是否启用：</label>
        <input type="radio" name="qy" checked="checked"> 是
        <input type="radio" name="qy" class="ml-default"> 否
    </p>
    <p>
        <label class="label"></label>
        <input type="button" class="button button-ok" value="确认">
        <input type="button" class="button button-cancel" value="取消">
    </p>
 -->
		</div>
		<%-- 定格树形图 --%>
		<div class="dialog J_dialog hidden" id="J_dialogArea">
			<div class="dialog-head">
				选择定格区域
				<div id="" class="dialog-close">
				</div>
			</div>
			<div class="dialog-body">
				<form action="" method="post" onsubmit="return false">
					<div class="mb-default ">
						<input type="text" name="" id="search-condition1" onkeydown="entersearch()" value="" class="input input-default" placeholder="定格名称" style="width:400px ;" />
						<input type="button" name="" id="" onclick="search_ztree('ztreeSpGroupId', 'search-condition1')" value="搜索" class="button button-default" />
					</div>
				</form>
				<div class="ztree" id="ztreeSpGroupId" style="height:140px; overflow:auto">
				</div>
			</div>
			<div class="dialog-foot">
				<div class="">
					<span class="dialog-button dialog-ok ml-default" id="saveArea">保存</span>
					<span class="dialog-button ml-small  dialog-cancel">取消</span>
				</div>
			</div>
		</div>
		<div class="cover-all">
		</div>

		<script>
			var zTreeObj;
			var zSetting = {
				view: {
					showLine: false,
					showIcon: showIconForTree,
				},
				data: {
					key: {
						children: "regionList",
					},
					simpleData: {
						enable: true
					}
				},
				callback: {
					onClick: onClick
				}
			};
			var zNodes = JSON.parse('${regionList}');

			var zTreeObj2;
			var zSetting2 = {
				view: {
					showLine: false,
					showIcon: showIconForTree,
					fontCss: setFontCss_ztree
				},
				data: {
					key: {
						children: "regionList",
					},
					simpleData: {
						enable: true
					}
				},
				callback: {
					onClick: onClickSpGroup
				}
			};
			var zNodes2 = JSON.parse('${regionListSPGroup}');

			/*  var zNodes = [
			        {id:1, pId:0, name: "转角街坊", open: true},
			        {id:2, pId:1, name: "子节点1"},
			        {id:3, pId:2, name: "子节点11子节点节点11"},
			        {id:12, pId:1, name: "子节点2"}
			       
			    ]; */
			function showIconForTree(treeId, treeNode) {
				return !treeNode.isParent;
			};

			function entersearch1() {
				var event = window.event || arguments.callee.caller.arguments[0];
				if(event.keyCode == 13) {
					search_ztree('ztree', 'search-condition');
				}
			}

			function entersearch() {
				var event = window.event || arguments.callee.caller.arguments[0];
				if(event.keyCode == 13) {
					search_ztree('ztreeSpGroupId', 'search-condition1');
				}
			}

			function onClickSpGroup(e, treeId, treeNode) {
				if(treeNode.level == 4) {
					var spGroupId = treeNode.id;
					var spGroupName = treeNode.name;
					var id = $('#id').val();
					$.post("${root}/Corner/Region/setRegionDefaultSpGroupId.do", {
						id: id,
						spGroupId: spGroupId
					}, function(data) {
						var data = JSON.parse(data);
						if(data.success) {
							var info = data.message.provinceName + '-' + data.message.cityName + '-' + data.message.areaName + '-' + '[' + data.message.spGroupName + ']';
							$('#spGroupInfo').innerText = info;
							initData(data);
						}
					});
					$('.selectSpGroupId').hide();
				}
			}
			function clearRegionDefaultSpGroupId(){
				var id = $('#id').val();
				$.post("${root}/Corner/Region/clearRegionDefaultSpGroupId.do", {
					id: id
				}, function(data) {
					var data = JSON.parse(data);
					if(data.success) {
						var info = data.message.provinceName + '-' + data.message.cityName + '-' + data.message.areaName + '-' + '[' + data.message.spGroupName + ']';
						$('#spGroupInfo').innerText = info;
						initData(data);
					}
				});
			}

			function initData(data) {
				var html = '';
				if(data.success) {
					html += ' <div>';
					html += '<label class="label">名称：</label>';
					html += '' + data.message.name + '';
					html += '</div>';
					html += '<p>';
					html += '<label class="label">编号：</label>';
					html += '<input type="text" id="regionCode" value="' + data.message.regionCode + '" class="input input-default">';
					html += '<input type="hidden" id="id" value="' + data.message.id + '" class="input input-default">';
					html += '</p>';
					html += '<p>';
					html += '<label class="label">是否启用：</label>';
					if(data.message.status == 1) {
						html += '<input type="radio" name="qy" value="1" checked="checked"> 是';
						html += '&nbsp;&nbsp;&nbsp;&nbsp;';
						html += '<input type="radio" name="qy" value="0" > 否';
					} else if(data.message.status == 0) {
						html += '<input type="radio" name="qy" value="1" > 是';
						html += '&nbsp;&nbsp;&nbsp;&nbsp;';
						html += '<input type="radio" name="qy" value="0" checked="checked"> 否';
					} else {
						html += '<input type="radio" name="qy" class="ml-default"> 否';
					}
					html += '</p>';

					html += '<p>';
					html += '<label class="label">默认定格：</label>';
					if(data.message.regionSpGroupMapId == undefined) {
						html += '<lable id="spGroupInfo">未设置</lable">';
						html += '<input type="hidden" name = "oldRegionSpGroupId" id="oldRegionSpGroupId" value="">';
						html += '<input type="hidden" name = "newRegionSpGroupId" id="newRegionSpGroupId" value="">';
					} else {
						html += '<lable id="spGroupInfo">' + data.message.provinceName + '-' + data.message.cityName + '-' + data.message.areaName + '-' + '[' + data.message.spGroupName + ']</lable>';
						html += '<input type="hidden" name = "oldRegionSpGroupId" id="oldRegionSpGroupId" value="' + data.message.spGroupId + '">';
						html += '<input type="hidden" name = "newRegionSpGroupId" id="newRegionSpGroupId" value="' + data.message.spGroupId + '">';
						html += '<input type="button" name="setdefaultSpGroupId" class="ml-default button button-operate" onclick="clearRegionDefaultSpGroupId()" value="清除默认定格">';
					}
					html += '<input type="button" name="setdefaultSpGroupId" class="ml-default button button-operate" id="J_addArea" value="设置默认定格">';
					
					html += '</p>';

					html += '<p>';
					html += '<label class="label"></label>';
					html += '<input type="button" id="okButton" class="button button-ok" value="确认">';
					html += '<input type="button" class="button button-cancel ml-small" value="取消">';
					html += '</p>';
				}
				if(html == '') {
					html = "<div>请选择左边区域</div>";
				}
				$("#editpage").html(html);

			}
			dialogPosCenter('#J_dialogArea');
			$('#editpage').on('click', '#J_addArea', function() {
				$('#J_dialogArea, .cover-all').fadeIn();
			});
			$('.J_dialog').on('click', '.dialog-cancel ,.dialog-ok', function() {
				$('.J_dialog, .cover-all').fadeOut();
			})
			$('.dialog').on('click', '.dialog-close', function() {

				$('.J_dialog, .cover-all').fadeOut();
			})

			function onClick(e, treeId, treeNode) {
				if(treeNode.pId > 0) {
					$.post("${root}/Corner/Region/getRegionById4SpGroupId.do", {
						id: treeNode.id
					}, function(data) {
						initData(data);
					}, 'json')
				} else {
					if(html == '') {
						html = "<div>请选择区域</div>";
					}
					$("#editpage").html(html);
				}
			}

			$(function() {
				zTreeObj = $.fn.zTree.init($("#ztree"), zSetting, zNodes);
				zTreeObj.expandNode(zTreeObj.getNodeByParam("id", 1, null));

				zTreeObj2 = $.fn.zTree.init($("#ztreeSpGroupId"), zSetting2, zNodes2);
				

				$("#editpage").on('click', '#okButton', function() {
					var regionCode = $("#regionCode").val();
					var status = $("input[name='qy']:checked").val();
					var id = $("#id").val();
					$.post("${root}/Corner/Region/updateRegionStatus.do", {
						id: id,
						status: status,
						regionCode: regionCode
					}, function(data) {
						if(data.success) {
							alert("操作成功！");
						} else {
							alert("操作失败！");
						}
					}, 'json')
				});
			});
		</script>
	</body>

</html>