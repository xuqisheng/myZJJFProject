<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>ECharts实例</title>
</head>
<body>
<!--Step:1 Prepare a dom for ECharts which (must) has size (width & hight)-->
<!--Step:1 为ECharts准备一个具备大小（宽高）的Dom-->
<div id="mainBar" style="height:500px;border:1px solid #ccc;padding:10px;"></div>

<!--Step:2 Import echarts.js-->
<!--Step:2 引入echarts.js-->
<script src="<%=request.getContextPath() %>/resources/vendor/echarts/echarts.js"></script>
<script src="<%=request.getContextPath() %>/resources/vendor/echarts/echartDemo.js"></script>
<script type="text/javascript">

    var echarts;
    window.onload = function () {
        loadEchart();
    };
    /*require.config({
        paths: {
            echarts: '<%=request.getContextPath() %>/resources/vendor/echarts'
        }
    });
    require(
        [
            //这里的'echarts'相当于'./js'
            'echarts',
            'echarts/chart/bar',
            'echarts/chart/line'
        ],
        function (ec) {
            echarts = ec
        }
    );*/

    function loadEchart() {
        //--- 折柱 ---
        //基于准备好的dom,初始化echart图表
        var myChart = echarts.init(document.getElementById('mainBar'));
        //定义图表option
        var option2 = option;
        option2.xAxis[0].data = [ "福田区22", "南山区22","龙岗区","坪山", "横岗","南山转角","龙岗转角","石岩","坂田","龙华"],
            //为echarts对象加载数据
            myChart.setOption(option2);
    }

</script>
</body>
</html>
