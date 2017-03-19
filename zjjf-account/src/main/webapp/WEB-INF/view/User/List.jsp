<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="../common/header.jsp"></jsp:include>
    <script src="${root }/resources/js/User/CustomersData.js" type="text/javascript"></script>
    <script src="${root }/resources/js/User/List.js" type="text/javascript"></script>
</head>
<body style="padding:6px; overflow:hidden;">
<div id="pageloading"></div>  
<div id="searchbar">
	<table align="left" border="0"  class="condtionBar">
		<tr>
			<td><input type="text" id="txFind" ligerui="width:200"  class="l-input" /></td>
			<td><input type="text" name="selectDepartment" id="selectDepartment" /></td>
			<td><input type="text" id="selectState" /></td>
			<td><input id="btnFind"  value="查询" class="l-button"  onclick="f_search()" /></td>
		</tr>
	</table>
</div>
<div id="maingrid4" style="margin:0; padding:0;margin-top: 30px;"></div>
  <div style="display:none;">
  <!-- g data total ttt -->
</div>
 
</body>
</html>