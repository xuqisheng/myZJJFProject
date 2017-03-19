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
            top:'3%',
            orient:'horizontal',
            icon:'stack'
        },
        grid: {
            left: '2.5%',
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
        top:'3%',
        orient:'horizontal',
        icon:'stack'
    },
    grid: {
        left: '2.5%',
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
                }},
            itemStyle: {
                normal: {
                    color:'#3caef4'
                }},
            data:[]
        }
    ]
};
var optionOneSecond = {
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
        top:'3%',
        orient:'horizontal',
        icon:'stack'
    },
    grid: {
        left: '2.5%',
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
                }},
            itemStyle: {
                normal: {
                    color:'#3caef4'
                }},
            data:[]
        }
    ]
};

