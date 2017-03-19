/**
 * Created by tujiaojiao on 2016/9/28 0028.
 */
    // 指定图表的配置项和数据
var optionFirst = {
        color: ['#69cafa'],
        tooltip: {
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
            data:['毛利率','交易额','终端数'],
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
        xAxis: [
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
                type: 'category',
                data: []
            }
        ],
        yAxis: [
            {
                axisLine:{
                    show:false
                },
                axisTick:{
                    show:false
                },
                type: 'value'
            }
        ],
        series: [
            {
                name:'',
                type:'bar',
                /*legendHoverLink:false,*/
                itemStyle:{
                    emphasis:{
                        color:'#69cafa'
                    }
                },
                data:[]
            }
            /*{
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
            }*/
        ]
    };

var optionSecond = {
    color: ['#69cafa'],
    tooltip: {
        trigger: 'axis'
    },
    toolbox: {
        top:'2.5%',
        right:'2.5%',
        feature: {
            magicType: {
                show: true,
                type: ['line', 'bar']
            }
        }
    },
    legend: {
        data:['毛利率','总订单量','终端数'],
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
    xAxis: [
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
            type: 'category',
            data: []
        }
    ],
    yAxis: [
        {
            axisLine:{
                show:false
            },
            axisTick:{
                show:false
            },
            type: 'value'
        }
    ],
    series: [
        {
            name:'',
            type:'bar',
            /*legendHoverLink:false,*/
            itemStyle:{
                emphasis:{
                    color:'#69cafa'
                }
            },
            data:[]
        }
       /* {
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
        }*/
    ]
};
