
 var option = {
    title: {
        x: "left",
        y: "top",
        text: "核心数据汇总",
        link: "http://www.tqyb.com.cn/weatherLive/climateForecast/2014-01-26/157.html",
        subtext: "www.stepday.com",
        sublink: "http://www.stepday.com/myblog/?Echarts"
    },
    toolbox: {
        show: true,
        feature: {
            mark: {
                show: true,
                title: {
                    mark: "辅助线开关",
                    markClear: "清空辅助线",
                    markUndo: "删除辅助线"
                },
                lineStyle: {
                    color: "#1e90ff",
                    type: "dashed",
                    width: 2
                }
            },
            dataZoom: {
                show: true,
                title: {
                    dataZoom: "区域缩放",
                    dataZoomReset: "区域缩放后退"
                }
            },
            dataView: {
                show: true,
                title: "数据视图",
                readOnly: true,
                lang: [
                    "数据视图",
                    "关闭",
                    "刷新"
                ]
            },
            magicType: {
                show: true,
                title: {
                    line: "折线图切换",
                    stack: "堆积",
                    bar: "柱形图切换",
                    tiled: "平铺"
                },
                type: [
                    "line",
                    "bar",
                    "stack",
                    "tiled"
                ]
            },
            restore: {
                show: true,
                title: "还原"
            },
            saveAsImage: {
                show: true,
                title: "保存为图片",
                type: "png",
                lang: [
                    "点击保存"
                ]
            }
        }
    },
    tooltip: {
        trigger: "axis"
    },
    legend: {
        show: true,
        x: "center",
        y: "top",
        data: [
            "商铺（家）",
            "下单",
            "销售金额（元）",
            "费用"
        ]
    },
    xAxis: [
        {
            data: [
                "福田区222",
                "南山区22",
                "龙岗区22",
                "坪山22",
                "横岗22",
                "南山转角22",
                "龙岗转角22",
                "石岩22",
                "坂田22",
                "龙华22"
            ],
            show: true,
            type: "category"
        }
    ],
    yAxis: [
        {
            show: true,
            type: "value",
            splitArea: {
                show: true
            }
        }
    ],
    series: [
        {
            data: [222.0, 422.9, 722.0, 2322.2, 25.6, 7622.7, 13522.6, 162.2, 3222.6, 20.0, 6.4, 3.3],
            name: "商铺（家）",
            type: "bar",
            stack: "下单"
        },
        {
        	data: [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3],
            name: "下单",
            type: "bar"
        },
        {
        	data: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3],
            name: "销售金额（元）",
            type: "bar"
           
        },
        {
            data: [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3],
            name: "费用",
            type: "bar"
           
        }
    ]
}; 

