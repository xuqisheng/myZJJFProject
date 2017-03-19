<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="../common/header.jsp"></jsp:include>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" style="padding:5px;">
       	<div style="padding:10px 60px 20px 60px">
        	<form id="ff" method="post">
       			<input type="hidden" value="${doctorId}" name="doctorId"/>
	            <table cellpadding="5">
	                <tr>
	                    <td>健康包:</td>
	                    <td>
	                    	<input id="healthPackageId" name="healthPackageId"></input>
	                    </td>
	            	</tr>
	            </table>
        	</form>
        </div>
        <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
	        	<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" 
	        		onclick="javascript:submitForm()" style="width:80px">修改</a>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" 
					onclick="javascript:cancelSave()" style="width:80px">关闭</a>
			</div>
	</div>
</body>
<script type="text/javascript">

	$(function(){
		$('#ff').form({
			url:root+"/admin/doctorctl/AddHealthPackage.do?",	
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success:function(data){
				var data = eval('(' + data + ')');
				if (data.success){
					closeWindow(this);
				}else{
					alertMsg(data.message);
				}
			},
			error:function(){
				alertMsg("加载失败，请检查网络！");
			}
		});
		$("#healthPackageId").combobox({
			width: 200,
			required: true,
			editable: false,
			panelHeight: "auto",
			url: root+"/admin/healthPackageCtrl/HealthPackageListUsing.do",    
			valueField: 'id',    
			textField: 'price',
			onLoadSuccess:function(){
		 		$('#healthPackageId').combobox('setValue','${Model.id}').combobox('setText','${Model.price}');
		    } 
		});		
	});
	
	function submitForm(){
		$('#ff').submit();
	}
	
	function cancelSave(){
		closeWindow();
	}
</script>
</html>