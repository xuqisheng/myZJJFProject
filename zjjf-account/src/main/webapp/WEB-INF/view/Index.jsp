<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>转角财务系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="resources/images/logo.ico" type="image/x-icon">
<jsp:include page="./common/header.jsp"></jsp:include>
<link href="${root }/resources/css/index/default.css" rel="stylesheet" type="text/css" />
<script src="${root }/resources/js/index/index.js" type="text/javascript"></script>
<script src="${root }/resources/js/index/menu.js" type="text/javascript"></script>
</head>

<body class="easyui-layout" style="overflow-y: hidden"  scroll="no" id="publicLayout">
	<noscript>
		<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
		    <img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
	
	<!--顶部面板-->
    <div region="north" split="true" border="false" style="overflow: hidden; height: 40px;
        background: url(${root }/resources/images/layout-browser-hd-bg.gif)  center center repeat-x #7f99be ;
        line-height: 30px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; padding-right:20px;" class="head">
        	<span id="msgSpan"></span>
        	欢迎：${currentAccounter.userName} | 
        	<a href="#" id="editpass">修改密码</a> | 
        	<a href="#" id="loginOut">安全退出</a> 
        </span>
        <span style="padding-left:10px; font-size: 16px; "><img src="${root }/resources/images/admin.png" width="30" height="30" align="absmiddle" />&nbsp;转角街坊财务管理系统</span>
    </div>
    
    <!--底部面板-->
    <div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
        <div class="footer">By 转角团队</div>
    </div>
    
    <!--左侧窗口-->
    <div region="west" hide="true" split="true" title="导航菜单" style="width:180px;" id="west">
		<div id="nav" class="easyui-accordion" fit="true" border="false">
		<!--  导航内容 -->
		</div>
    </div>
    
    <!--主界面窗口-->
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
			<div title="欢迎使用" style="overflow:hidden; color:green;" >
				<!-- <div style="margin-left:600px; margin-top: 160px; ">
				<h1 style="font-size:24px; margin-bottom: 20px;">欢迎使用转角街坊客户管理系统</h1>
				<div style="font-size:24px;">* 技术支持：转角街坊研发团队 </div>
				<div style="font-size:24px;">* BLOG:  转角街坊团队的博客</div>
				<div style="font-size:24px;">* 讨论群：322852228</div>
				<div style="font-size:24px;">* 提示：版权归转角街坊</div>
				</div> -->
				<img alt="" src="${root }/resources/images/welcomebg.png" />
			</div>
		</div>
    </div>
 
    <!--修改密码窗口-->
    <div id="w" class="easyui-window" title="修改密码" collapsible="false" minimizable="false"   modal="true"  closed="true"
    			maximizable="false" icon="icon-save"  style="width: 320px;height: 150px; padding: 5px;background: #fafafa;">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
                <table cellpadding=3>
                    <tr>
                        <td>新密码：</td>
                        <td><input id="txtNewPass" type="Password" class="text1"  /></td>
                        <td> </td>
                    </tr>
                    <tr>
                        <td>确认密码：</td>
                        <td><input id="txtRePass" type="Password" class="text1"  /></td>
                        <td>至少6位</td>
                    </tr>
                </table>
            </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" >
                    确定</a> <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
            </div>
        </div>
    </div>

</body>
</html>