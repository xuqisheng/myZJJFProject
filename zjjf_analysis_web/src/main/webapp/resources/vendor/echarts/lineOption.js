/**
 * Created by tujiaojiao on 2016/9/27 0027.
 */
    // 指定图表的配置项和数据
var optionTwo = {
        tooltip : {
            trigger: 'axis'
        },
        toolbox: {
            top:'2.3%',
            right:'2.5%',
            feature: {
                magicType: {show: true, type: ['line', 'bar']}
            }
        },
        legend: {
            data:['总交易额','自营额','终端总数','活跃终端','移动平均价'],
            right:'6%',
            top:'3%',
            orient:'horizontal',
            icon:'stack'
        },
        grid: {
            left: '3.5%',
            right: '3%',
            bottom: '7%',
            containLabel: true
        },
        xAxis : [
            {
                axisLabel:{
                    textStyle:{
                        color:'#393939'
                    }
                },
                axisLine:{
                    lineStyle:{
                        color:'#cccccc'
                    }
                },
                type : 'category',
                boundaryGap : false,
                data : []
            }
        ],
        yAxis : [
            {
                axisLine:{
                    show:false
                },
                axisTick:{
                    show:false
                },
                type : 'value'
            }
        ],
        series : [
            {
                name:'',
                type:'line',
                smooth:true,
                symbolSize:7,
                areaStyle: {
                    normal: {
                        color:'rgba(176, 222, 251, 0.5)'
                        /*  color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                         offset: 0, color: '#bae2fb' // 0% 处的颜色
                         }, {
                         offset: 1, color: 'white' // 100% 处的颜色
                         }], false)*/
                    }},
                itemStyle: {
                    normal: {
                        color:'#3caef4'
                    }},
                data:[]
            },
            {
                name:'',
                type:'line',
                smooth:true,
                symbolSize:7,
                areaStyle: {
                    normal: {
                        color:'rgba(165, 215, 195, 0.5)'
                        /*color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                         offset: 0, color: '#b2ddb7' // 0% 处的颜色
                         }, {
                         offset: 1, color: 'white' // 100% 处的颜色
                         }], false)*/
                    }},
                itemStyle: {
                    normal: {
                        color:'#4eb15a'
                    }},
                data:[]
            }
        ]
    };
var optionOne = {
    tooltip : {
        trigger: 'axis'
    },
    toolbox: {
        top:'2.3%',
        right:'2.5%',
        feature: {
            magicType: {show: true, type: ['line', 'bar']}
        }
    },
    legend: {
        data:['总交易额','自营额','终端总数','活跃终端','移动平均价'],
        right:'6%',
        top:'3%',
        orient:'horizontal',
        icon:'stack'
    },
    grid: {
        left: '3.5%',
        right: '3%',
        bottom: '7%',
        containLabel: true
    },
    xAxis : [
        {
            axisLabel:{
                textStyle:{
                    color:'#393939'
                }
            },
            axisLine:{
                lineStyle:{
                    color:'#cccccc'
                }
            },
            type : 'category',
            boundaryGap : false,
            data : []
        }
    ],
    yAxis : [
        {
            axisLine:{
                show:false
            },
            axisTick:{
                show:false
            },
            type : 'value'
        }
    ],
    series : [
        {
            name:'',
            type:'line',
            smooth:true,
            symbolSize:7,
            areaStyle: {
                normal: {
                    color:'rgba(176, 222, 251, 0.5)'
                    /*  color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                     offset: 0, color: '#bae2fb' // 0% 处的颜色
                     }, {
                     offset: 1, color: 'white' // 100% 处的颜色
                     }], false)*/
                }},
            itemStyle: {
                normal: {
                    color:'#3caef4'
                }},
            data:[]
        }
    ]
};

