<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setAttribute("root", request.getContextPath());%>
<%@ include file="../../common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>供应商列表</title>
  	<%@ include file="../../common/head.jsp"%>
</head>
<body>
<div class="wrap-bd">
    <div class="mt-small mb-default clearfix">
        <form class="fl" method="post" action="${root }/scms/ERPMa/getAllERPManager.do">
            <input class="input-search-text" type="text" name="codeOrName" value="${managerRo.codeOrName }" placeholder="供应商编号/名称" maxlength="25"/>
            <select name="status" id="status" class="select">
                <option value="" <c:if test="${managerRo.status=='' || managerRo.status==null }">selected="selected"</c:if>>全部</option>
                <c:forEach items="${menagerStatus}" var="status">
                    <option value="${status.index}" <c:if test="${managerRo.status == status.index }">selected="selected"</c:if>>${status.name}</option>
                </c:forEach>
            </select>
            <input value="搜索" type="submit" class="input-search-button ml-default" id="searchmanager">
        </form>
        <%--<div class="fr">
            <a href="${root }/scms/ERPMa/returnAddOrEditPage.do?action=1" class="button">添加供应商</a>
        </div>--%>
    </div>
    <table class="table-list">
        <thead>
        <tr>
            <th>编号</th>
            <th>名称</th>
            <th>状态</th>
            <th>编辑时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
        	<c:when test="${managerList != null && managerList.size()>0 }">
        		<c:forEach items="${managerList }" var="manager">
        			<tr>
			            <td>${manager.managerCode }</td>
			            <td>${manager.managerName }</td>
			            <c:if test="${manager.status==0 }"><td class="txt-log">引进中</td></c:if>
			            <c:if test="${manager.status==1 }"><td class="txt-success">合作中</td></c:if>
			            <c:if test="${manager.status==2 }"><td class="txt-warn">停止合作</td></c:if>
			            <td><fmt:formatDate value="${manager.updateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			            <td>
			                <!--<a href="${root }/scms/ERPMaItem/getAllManagerItem.do?managerId=${manager.id}">管理商品</a>&nbsp;&nbsp;
			                <a href="${root }/scms/ERPMa/returnAddOrEditPage.do?action=2&id=${manager.id}">编辑</a>&nbsp;&nbsp;
			                <a href="javascript:void(0)" onclick="deleteManager('${manager.id}')">删除</a>-->
                            <a href="${root }/scms/ERPMaItem/getAllManagerItem.do?managerId=${manager.id}">管理商品</a>&nbsp;&nbsp;
			                <a href="${root }/scms/ERPMa/ERPManagerDetail/${manager.id}.do">查看</a>
			            </td>
			        </tr>
        		</c:forEach>
        	</c:when>
        	<c:otherwise>
        		<tr>
		            <tr><td colspan="5" class="no-data"></td></tr>
		        </tr>
        	</c:otherwise>
        </c:choose>
        </tbody>
    </table>
    <c:if test="${fn:length(managerList)>0}">
		  <%@ include file="../../common/pagination-kk.jsp"%>
    </c:if>
</div>
<script type="text/javascript">
	function deleteManager(id){
		if(id != null && id != '' && id != 'null' && id != 'undefined'){
			if(confirm("确定要删除吗？")){
				$.post("${root}/scms/ERPMa/deleteERPManager.do",{id:id},function(data){
					if(data.success){
						location.reload();
					}else{
						alert(data.message);
					}
				},'json');
			}
		}
	}
</script>
</body>
</html>
